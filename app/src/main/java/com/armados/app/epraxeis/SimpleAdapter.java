package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private List<Simple> items = new ArrayList<>();

    private ItemClickListener mClickListener;

    private Context context;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @NonNull
    @Override
    public SimpleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_view_simple, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleAdapter.ViewHolder holder, int position) {
        Simple rec = items.get(position);

        if (rec.getLabel() != null)
            holder.txtTitle.setText(rec.getLabel());
        else
            holder.txtTitle.setVisibility(View.GONE);

        if (rec.getLabelHeader() != null)
            holder.txtLabelHeader.setText(rec.getLabelHeader());
        else
            holder.txtLabelHeader.setVisibility(View.GONE);

        if (rec.getSubtitle() != null)
            holder.txtSubTitle.setText(rec.getSubtitle());
        else
            holder.txtSubTitle.setVisibility(View.GONE);

        if (rec.getDescription() != null)
            holder.txtDescription.setText(rec.getDescription());
        else
            holder.txtDescription.setVisibility(View.GONE);

        if (items.get(position) instanceof SimplePersonAFM)
            holder.imgIcon.setImageResource(R.drawable.ic_baseline_person_24);
        else if (items.get(position) instanceof SimpleSigner)
            holder.imgIcon.setImageResource(R.drawable.ic_baseline_how_to_reg_24);
        else if (items.get(position) instanceof SimpleOrganization)
            holder.imgIcon.setImageResource(R.drawable.ic_baseline_business_24);
        else if (items.get(position) instanceof SimpleUnit)
            holder.imgIcon.setImageResource(R.drawable.ic_baseline_snippet_folder_24);
        else if (items.get(position) instanceof SimpleLabel)
            holder.imgIcon.setImageResource(R.drawable.ic_baseline_topic_24);
        else
            holder.imgIcon.setImageResource(0);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Simple> getItems() {
        return items;
    }

    public Simple getItem(int position) {
        return items.get(position);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Simple> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItem(Simple item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<Simple> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void removeAllItems() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle;
        TextView txtLabelHeader;
        TextView txtSubTitle;
        TextView txtDescription;
        ImageView imgIcon;

        public ViewHolder(@NonNull View view) {
            super(view);
            view.setOnClickListener(this);

            txtTitle = view.findViewById(R.id.txtTitle);
            txtLabelHeader = view.findViewById(R.id.txtLabelHeader);
            txtSubTitle = view.findViewById(R.id.txtSubTitle);
            txtDescription = view.findViewById(R.id.txtDescription);

            imgIcon = view.findViewById(R.id.imgIcon);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "func onClick()");

            if (mClickListener == null) return;

            // safety check, sometimes getAdapterPosition() return RecyclerView.NO_POSITION
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Ignore item click when Uid is null
            if (items.get(getAdapterPosition()).getUid() == null) return;

            mClickListener.onItemClick(view, getAdapterPosition());
        }

    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
