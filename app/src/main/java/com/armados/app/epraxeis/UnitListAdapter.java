package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.armados.app.epraxeis.diavgeia.Unit;

import java.util.ArrayList;
import java.util.List;

public class UnitListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = getClass().getSimpleName();

    private final List<Unit> items = new ArrayList<>();

    public List<Unit> getItems() {
        return items;
    }

    public Unit getItem(int position) {
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
    public void setItems(List<Unit> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(context).inflate(R.layout.list_item_view_unit, parent, false);
        return new ViewHolderUnit(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolderUnit) holder).bindData(getItem(position));
    }

    public class ViewHolderUnit extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtLabel;
        //private final TextView txtParentId;
        private final TextView txtStatus;
        private final TextView txtActiveFrom;
        private final TextView txtActiveUntil;

        public ViewHolderUnit(View view) {
            super(view);

            view.setOnClickListener(this);

            txtLabel = view.findViewById(R.id.txtLabel);
            //txtParentId = view.findViewById(R.id.txtParentId);
            txtStatus = view.findViewById(R.id.txtOrgType);
            txtActiveFrom = view.findViewById(R.id.txtActiveFrom);
            txtActiveUntil = view.findViewById(R.id.txtActiveUntil);
        }

        @Override
        public void onClick(View view) {
            final Unit rec = getItem(getAdapterPosition());

            Intent i = new Intent(view.getContext(), UnitActivity.class);
            i.putExtra("uid", rec.getUid());
            view.getContext().startActivity(i);
        }

        public void bindData(@NonNull Unit rec) {
            txtLabel.setText(rec.getLabel());

 /*           DictionaryEntity dictRec = Database.getInstance(itemView.getContext())
                    .getDictionaryDao()
                    .getEntry(Configuration.ORGANIZATIONS, rec.getParentId());

            if (dictRec != null)
                txtParentId.setText(dictRec.getLabel());
            else */
               // txtParentId.setText("");

            if (rec.isActive())
                txtStatus.setText("");
            else
                txtStatus.setText(UnitHelper.getActiveText(rec.isActive()));

            if (rec.getActiveFrom() != null) {
                final String activeFrom = DateTimeFormatters.DATE_FORMAT.format(rec.getActiveFrom());
                txtActiveFrom.setText(String.format("Ημερομηνία εγγραφής %s", activeFrom));
            } else {
                txtActiveFrom.setText("");
            }

                txtActiveUntil.setText("");


        }

    }
}