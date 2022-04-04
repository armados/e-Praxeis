package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.armados.app.epraxeis.diaugeia.Decision;
import com.armados.app.epraxeis.listitems.ItemDateHeader;
import com.armados.app.epraxeis.listitems.ItemFetchNextPage;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DecisionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private final List<Object> items = new ArrayList<>();

    public static final int ITEM_TYPE_DECISION = 0;
    public static final int ITEM_TYPE_DATE_HEADER = 1;
    public static final int ITEM_TYPE_FETCH_NEXT_PAGE = 2;

    public List<Object> getItems() {
        return items;
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    private Context context;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof Decision)
            return ITEM_TYPE_DECISION;
        else if (getItem(position) instanceof ItemDateHeader)
            return ITEM_TYPE_DATE_HEADER;
        else if (getItem(position) instanceof ItemFetchNextPage)
            return ITEM_TYPE_FETCH_NEXT_PAGE;
        return -1;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItem(Object num) {
        items.add(num);
        notifyItemInserted(items.size());
    }

    private void removeAt(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

    public void removeLastItem() {
        if (items.size() == 0) return;
        removeAt(items.size() - 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem;

        switch (viewType) {
            case ITEM_TYPE_DECISION:
                rowItem = LayoutInflater.from(context).inflate(R.layout.list_item_view_decision, parent, false);
                return new ViewHolderDecision(rowItem);
            case ITEM_TYPE_DATE_HEADER:
                rowItem = LayoutInflater.from(context).inflate(R.layout.list_item_view_date_header, parent, false);
                return new ViewHolderDateHeader(rowItem);
            case ITEM_TYPE_FETCH_NEXT_PAGE:
                rowItem = LayoutInflater.from(context).inflate(R.layout.list_item_view_loading, parent, false);
                return new ViewHolderFetchNextPage(rowItem);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case ITEM_TYPE_DECISION:
                ((ViewHolderDecision) holder).bindData((Decision) getItem(position));
                break;
            case ITEM_TYPE_DATE_HEADER:
                ((ViewHolderDateHeader) holder).bindData((ItemDateHeader) getItem(position));
                break;
            case ITEM_TYPE_FETCH_NEXT_PAGE:
                ((ViewHolderFetchNextPage) holder).bindData((ItemFetchNextPage) getItem(position));
                break;
        }
    }

    public class ViewHolderDecision extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtAda;
        private final TextView txtType;
        private final TextView txtStatus;
        private final TextView txtTitle;
        private final TextView txtDate;
        private final TextView txtInfo;
        private final TextView txtOrganization;

        private final SimpleLiteAdapter mAdapter = new SimpleLiteAdapter();

        public ViewHolderDecision(View view) {
            super(view);
 
            view.setOnClickListener(this);

            txtAda = view.findViewById(R.id.txtAda);
            txtType = view.findViewById(R.id.txtType);
            txtStatus = view.findViewById(R.id.txtOrgType);
            txtTitle = view.findViewById(R.id.txtTitle);
            txtDate = view.findViewById(R.id.txtDate);
            txtInfo = view.findViewById(R.id.txtInfo);
            txtOrganization = view.findViewById(R.id.txtOrganization);

            RecyclerView rv_Tmp = view.findViewById(R.id.listTmp);
            rv_Tmp.setLayoutManager(new LinearLayoutManager(view.getContext()));
            rv_Tmp.setHasFixedSize(true);
            rv_Tmp.setNestedScrollingEnabled(false);
            rv_Tmp.setAdapter(mAdapter);
        }

        @Override
        public void onClick(View view) {
            final Decision rec = (Decision) getItem(getAdapterPosition());

            Intent i = new Intent(view.getContext(), DecisionActivity.class);
            i.putExtra("ada", rec.getAda());
            view.getContext().startActivity(i);
        }

        @SuppressLint("ResourceAsColor")
        public void bindData(Decision rec) {
            txtAda.setText(String.format("ΑΔΑ %s", rec.getAda()));

            DictionaryEntity decType = Database.getInstance(txtAda.getContext())
                    .getDictionaryDao()
                    .getEntry(Configuration.DECISION_TYPES, rec.getDecisionTypeId());

            if (decType != null)
                txtType.setText(decType.getLabel());
            else
                txtType.setText(R.string.missing_dict_entry);

            txtStatus.setText(DecisionHelper.getStatusText(rec.getStatus()));

            switch (rec.getStatus()) {
                case DecisionStatus.PUBLISHED:
                    txtStatus.setVisibility(View.INVISIBLE);
                    break;
                case DecisionStatus.PENDING_REVOCATION:
                    txtStatus.setVisibility(View.VISIBLE);
                    txtStatus.setBackgroundResource(R.drawable.bg_declist_pending_revocation);
                    break;
                case DecisionStatus.REVOKED:
                    txtStatus.setVisibility(View.VISIBLE);
                    txtStatus.setBackgroundResource(R.drawable.bg_declist_revoked);
                    break;
            }

            txtTitle.setText(rec.getSubject());

            txtDate.setText(DateTimeFormatters.DATE_FORMAT.format(rec.getSubmissionTimestamp()));

            txtInfo.setText("");

            DictionaryEntity dictRec = Database.getInstance(itemView.getContext())
                    .getDictionaryDao()
                    .getEntry(Configuration.ORGANIZATIONS, rec.getOrganizationId());

            if (decType != null)
                txtOrganization.setText(dictRec.getLabel());
            else
                txtOrganization.setText(R.string.missing_dict_entry);

            mAdapter.setItems(rec.getExtraFields().getDetailInfoItems(itemView.getContext()));
        }

    }

    public class ViewHolderDateHeader extends RecyclerView.ViewHolder {

        private final TextView txtTitle;

        public ViewHolderDateHeader(View view) {
            super(view);

            txtTitle = view.findViewById(R.id.txtTitle);
        }

        public void bindData(ItemDateHeader rec) {
            // today
            Calendar nowDate = new GregorianCalendar();
            // reset hour, minutes, seconds and millis
            nowDate.set(Calendar.HOUR_OF_DAY, 0);
            nowDate.set(Calendar.MINUTE, 0);
            nowDate.set(Calendar.SECOND, 0);
            nowDate.set(Calendar.MILLISECOND, 0);

            Calendar decisionDate = Calendar.getInstance();
            decisionDate.setTime(rec.getDate());
            decisionDate.set(Calendar.HOUR_OF_DAY, 0);
            decisionDate.set(Calendar.MINUTE, 0);
            decisionDate.set(Calendar.SECOND, 0);
            decisionDate.set(Calendar.MILLISECOND, 0);

            long daysBetween = ChronoUnit.DAYS.between(decisionDate.toInstant(), nowDate.toInstant());

            switch ((int) daysBetween) {
                case 0:
                    txtTitle.setText("Σήμερα");
                    break;
                case 1:
                    txtTitle.setText("Χτές");
                    break;
                default:
                    String dateStr = DateTimeFormatters.HEADER_DATE_FORMAT.format(rec.getDate());

                    txtTitle.setText(dateStr);
            }
        }
    }

    public class ViewHolderFetchNextPage extends RecyclerView.ViewHolder {

        public ViewHolderFetchNextPage(View view) {
            super(view);

        }
        
        public void bindData(ItemFetchNextPage rec) {
        }

    }

}