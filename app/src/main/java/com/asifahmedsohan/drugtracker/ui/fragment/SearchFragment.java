package com.asifahmedsohan.drugtracker.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asifahmedsohan.drugtracker.R;
import com.asifahmedsohan.drugtracker.ui.adapter.DrugAdapter;
import com.asifahmedsohan.drugtracker.ui.viewmodel.DrugViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends Fragment {

    private EditText etSearch;
    private Button btnSearch;
    private ProgressBar pBar;
    private RecyclerView rvDrugs;
    private DrugAdapter adapter;
    private DrugViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        viewModel = new ViewModelProvider(this).get(DrugViewModel.class);
        etSearch = view.findViewById(R.id.etSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        pBar = view.findViewById(R.id.progressBar);
        rvDrugs = view.findViewById(R.id.rvDrugs);

        rvDrugs.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DrugAdapter(drug -> {
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            viewModel.saveMedication(drug);
        });

        rvDrugs.setAdapter(adapter);

        btnSearch.setOnClickListener(v -> {
            String query = etSearch.getText().toString().trim();
            if (!query.isEmpty()) {
                fetchDrugs(query);
            } else {
                Toast.makeText(getContext(), "Enter drug name", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void fetchDrugs(String name) {
        pBar.setVisibility(View.VISIBLE);

        viewModel.getDrugs(name).observe(getViewLifecycleOwner(), result -> {
            pBar.setVisibility(View.GONE);
            if (result.isSuccess()) {
                adapter.setItems(result.getData());
            } else {
                Toast.makeText(getActivity(), result.getError(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
