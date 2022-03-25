package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteSignerAdapter extends RecyclerView.Adapter<FavoriteSignerAdapter.ViewHolder> {

    private final String TAG = getClass().getSimpleName();

    private List<FavoriteEntity> data = new ArrayList<>();

    private Context context;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @NonNull
    @Override
    public FavoriteSignerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_watch_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteSignerAdapter.ViewHolder holder, int position) {
        final FavoriteEntity rec = data.get(position);
        holder.label.setText(rec.getLabel());
        holder.comment.setText(rec.getComment());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<FavoriteEntity> getData() {
        return data;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<FavoriteEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView label;
        TextView comment;

        public ViewHolder(@NonNull View view) {
            super(view);
            view.setOnClickListener(this);

            label = view.findViewById(R.id.txtLabel);
            comment = view.findViewById(R.id.txtComment);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "func onClick()");

            final FavoriteEntity rec = data.get(getAdapterPosition());

            Intent i = new Intent(context, SignerActivity.class);
            i.putExtra("uid", rec.getUid());
            context.startActivity(i);
        }

    }
}