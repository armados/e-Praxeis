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

import com.armados.app.epraxeis.diavgeia.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private final List<Organization> items = new ArrayList<>();

    public List<Organization> getItems() {
        return items;
    }

    public Organization getItem(int position) {
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
    public void setItems(List<Organization> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(context).inflate(R.layout.list_item_view_organization, parent, false);
        return new ViewHolderOrganization(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolderOrganization) holder).bindData(getItem(position));

    }

    public class ViewHolderOrganization extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtLabel;
       // private final TextView txtSupervisorLabel;
        private final TextView txtStatus;
      //  private final TextView txtCategory;

        public ViewHolderOrganization(View view) {
            super(view);

            view.setOnClickListener(this);

            txtLabel = view.findViewById(R.id.txtLabel);
           // txtSupervisorLabel = view.findViewById(R.id.txtOrganizationLabel);
            txtStatus = view.findViewById(R.id.txtOrgType);
          //  txtCategory = view.findViewById(R.id.txtCategory);
        }

        @Override
        public void onClick(View view) {
            final Organization rec = getItem(getAdapterPosition());

            Intent i = new Intent(view.getContext(), OrganizationActivity.class);
            i.putExtra("uid", rec.getUid());
            view.getContext().startActivity(i);
         }

        public void bindData(Organization rec) {
            txtLabel.setText(rec.getLabel());

            //txtSupervisorLabel.setText(rec.getSupervisorLabel());
           // txtSupervisorLabel.setText("");

            if (!rec.getStatus().equals(OrganizationStatus.ACTIVE))
                txtStatus.setText(OrganizationHelper.getStatusText(rec.getStatus()));
            else
                txtStatus.setText("");

      /*      DictionaryEntity category = Database.getInstance(txtCategory.getContext())
                    .getDictionaryDao()
                    .getEntry(Configuration.DICTIONARY_ORG_CATEGORY, rec.getCategory());

            if (category!=null)
                txtCategory.setText(category.getLabel());
            else */
              //  txtCategory.setText("");
        }

    }
}