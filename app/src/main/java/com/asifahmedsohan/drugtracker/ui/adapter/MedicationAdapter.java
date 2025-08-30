package com.asifahmedsohan.drugtracker.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asifahmedsohan.drugtracker.R;
import com.asifahmedsohan.drugtracker.domain.entity.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.ItemViewHolder> {

    private List<Medication> items = new ArrayList<>();

    public void setItems(List<Medication> items) {
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
        Medication medication = items.get(position);
        holder.tvName.setText(medication.getName());
        holder.tvRxcui.setText(medication.getRxcui());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Medication getItem(int position) {
        return items.get(position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvRxcui;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvDrugName);
            tvRxcui = itemView.findViewById(R.id.tvRxcui);
        }
    }
}


