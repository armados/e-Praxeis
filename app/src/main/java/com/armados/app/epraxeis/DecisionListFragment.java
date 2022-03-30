package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.armados.app.epraxeis.diavgeia.Decision;
import com.armados.app.epraxeis.diavgeia.DecisionSearchResult;
import com.armados.app.epraxeis.listitems.ItemDateHeader;
import com.armados.app.epraxeis.listitems.ItemFetchNextPage;
import com.armados.app.epraxeis.search.SearchQuery;

import java.util.ArrayList;
import java.util.List;


public class DecisionListFragment extends BaseFragment {

    private final String TAG = getClass().getSimpleName();

    private DecisionListEventListener mListener;

    private final DecisionListAdapter mAdapter = new DecisionListAdapter();

    private SearchQuery searchParams;

    private final List<Decision> listDecisions = new ArrayList<>();

    private boolean isLoading = false;

    public void setListener(DecisionListEventListener mListener) {
        this.mListener = mListener;
    }

    private String dateStrOld = null; // remember last date in list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            searchParams = (SearchQuery) savedInstanceState.getSerializable("QUERY_PARAMETERS");
        } else {
            searchParams = (SearchQuery) this.getArguments().getSerializable("QUERY_PARAMETERS");
        }

        if (searchParams == null) {
            Log.e(TAG, "Missing required parameters");
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("QUERY_PARAMETERS", searchParams);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_decisions, container, false);

        mBoxLoading = view.findViewById(R.id.mBoxLoading);
        mBoxEmpty = view.findViewById(R.id.mBoxEmpty);
        mBoxError = view.findViewById(R.id.mBoxError);

        RecyclerView mRecViewDecisions = view.findViewById(R.id.listDecisions);

        LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecViewDecisions.setLayoutManager(mLayoutManager);
        mRecViewDecisions.setAdapter(mAdapter);
        //mRecViewDecisions.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        mRecViewDecisions.addOnScrollListener(new RecyclerView.OnScrollListener() {
            static final int visibleThreshold = 0;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    int visibleItemCount = mLayoutManager.getChildCount();
                    int totalItemCount = mLayoutManager.getItemCount();
                    int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (!isLoading
                            && mAdapter.getItemViewType(totalItemCount - 1) == DecisionListAdapter.ITEM_TYPE_FETCH_NEXT_PAGE
                            && totalItemCount <= visibleItemCount + pastVisibleItems + visibleThreshold) {
                        isLoading = true;
                        Log.d(TAG, "Last item reached");
                        fetchRemoteDataNextPage();
                    }
                }
            }
        });

        if (listDecisions.size() == 0) {
            Log.d(TAG, "fragment has no data. loading ...");
            fetchRemoteData();
        }

        return view;
    }

    private void fetchRemoteData() {
        AsyncTaskRunner2 myTask = new AsyncTaskRunner2();
        myTask.execute();
    }

    private void fetchRemoteDataNextPage() {
        searchParams.setPage(searchParams.getPage() + 1);
        fetchRemoteData();
    }

    private boolean hasMoreToLoad(@NonNull final DecisionSearchResult arr) {
        final int fetched = (arr.getInfo().getPage() + 1) * arr.getInfo().getSize();
        return fetched < arr.getInfo().getTotal();
    }

    @SuppressLint("StaticFieldLeak")
    class AsyncTaskRunner2 extends AsyncTask<Void, Void, DecisionSearchResult> {

        @Override
        protected void onPreExecute() {
            if (mListener != null) mListener.onSearchStarted();

            isLoading = true;

            if (mAdapter.getItems().size() == 0)
                setFragmentIsLoadingData(true);
            else {
                showErrorView(false); // reset view
                showEmptyView(false);  // reset view
            }
        }

        @Override
        protected DecisionSearchResult doInBackground(Void... params) {
            return Api.fetchDecisions(searchParams);
        }

        @Override
        protected void onProgressUpdate(Void... params) {

        }

        @Override
        protected void onPostExecute(final DecisionSearchResult result) {
            setFragmentIsLoadingData(false);

            if (result == null) {
                // error fetching remote data
                if (mListener != null) mListener.onSearchFailure();
                return;
            }

            if (mListener != null) mListener.onSearchSuccess();

            listDecisions.addAll(result.getDecisions());

            if (listDecisions.size() == 0)
                showEmptyView(true);

            mAdapter.removeLastItem();


            for (Decision p : result.getDecisions()) {
                String dateStr = DateTimeFormatters.DATE_FORMAT.format(p.getSubmissionTimestamp());

                if (!dateStr.equals(dateStrOld)) {
                    ItemDateHeader item = new ItemDateHeader();
                    item.setDate(p.getSubmissionTimestamp());
                    mAdapter.addItem(item);
                    dateStrOld = dateStr;
                }

                mAdapter.addItem(p);
            }

            if (hasMoreToLoad(result)) {
                ItemFetchNextPage item = new ItemFetchNextPage();
                item.setInfo(result.getInfo());
                mAdapter.addItem(item);
            }

            Log.d(TAG, "query: " + result.getInfo().getQuery());
            Log.d(TAG, "total: " + result.getInfo().getTotal());
            Log.d(TAG, "size: " + result.getInfo().getSize());
            Log.d(TAG, "page: " + result.getInfo().getPage());
            Log.d(TAG, "actual size: " + result.getInfo().getActualSize());
            Log.d(TAG, "order: " + result.getInfo().getOrder());

            isLoading = false;
        }
    }


}