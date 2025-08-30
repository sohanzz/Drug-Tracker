package com.asifahmedsohan.drugtracker.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asifahmedsohan.drugtracker.R;
import com.asifahmedsohan.drugtracker.domain.entity.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.ItemViewHolder> {

    private List<Drug> items = new ArrayList<>();
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Drug drug);
    }

    public DrugAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<Drug> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drug, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Drug drug = items.get(position);
        holder.tvName.setText(drug.getName());
        holder.tvRxcui.setText(drug.getRxcui());

        holder.ivSave.setOnClickListener(v -> listener.onItemClick(drug));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvRxcui;
        ImageView ivSave;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvDrugName);
            tvRxcui = itemView.findViewById(R.id.tvRxcui);
            ivSave = itemView.findViewById(R.id.ivSave);
        }
    }
}

