package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.armados.app.epraxeis.diavgeia.Signer;

import java.util.ArrayList;
import java.util.List;

public class SignerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = getClass().getSimpleName();

    private final AdapterView.OnItemClickListener listener;

    private final List<Signer> items = new ArrayList<>();

    public SignerListAdapter(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    public List<Signer> getItems() {
        return items;
    }

    public Signer getItem(int position) {
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
        return 0;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Signer> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(context).inflate(R.layout.list_item_view_signer, parent, false);
        return new ViewHolderSigner(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolderSigner) holder).bindData(getItem(position));

    }

    public class ViewHolderSigner extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView txtName;
       // public final TextView txtOrganizationLabel;
        public final TextView txtStatus;
        public final TextView txtActiveFrom;
        public final TextView txtActiveUntil;

        public ViewHolderSigner(View view) {
            super(view);

            view.setOnClickListener(this);

            txtName = view.findViewById(R.id.txtPosition);
            //txtOrganizationLabel = view.findViewById(R.id.txtOrganizationLabel);
            txtStatus = view.findViewById(R.id.txtOrgType);
            txtActiveFrom = view.findViewById(R.id.txtActiveFrom);
            txtActiveUntil = view.findViewById(R.id.txtActiveUntil);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }

        public void bindData(@NonNull Signer rec) {
            txtName.setText(SignerHelper.getFullNameText(rec.getLastName(), rec.getFirstName()));

/*            DictionaryEntity dictRec = Database.getInstance(itemView.getContext())
                    .getDictionaryDao()
                    .getEntry(Configuration.ORGANIZATIONS, rec.getOrganizationId());

            if (dictRec != null)
                txtOrganizationLabel.setText(dictRec.getLabel());
            else */
              //  txtOrganizationLabel.setText("");

            if (rec.getActiveFrom() != null) {
                final String activeFrom = DateTimeFormatters.DATE_FORMAT.format(rec.getActiveFrom());
                txtActiveFrom.setText(String.format("Ημερομηνία εγγραφής %s", activeFrom));
            } else {
                txtActiveFrom.setText("");
            }


                txtActiveUntil.setText("");

            if (rec.isActive())
                txtStatus.setText("");
            else
                txtStatus.setText(SignerHelper.getActiveText(rec.isActive()));

            /*for (Signer.SignerUnit sunit : rec.getUnits()) {
                 txtActiveUntil.append(sunit.getUid() + " " + sunit.getPositionLabel() + "\n");
            }*/
        }

    }

}