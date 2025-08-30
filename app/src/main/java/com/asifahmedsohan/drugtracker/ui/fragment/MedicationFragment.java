package com.asifahmedsohan.drugtracker.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asifahmedsohan.drugtracker.R;
import com.asifahmedsohan.drugtracker.domain.entity.Medication;
import com.asifahmedsohan.drugtracker.ui.activity.BaseActivity;
import com.asifahmedsohan.drugtracker.ui.adapter.MedicationAdapter;
import com.asifahmedsohan.drugtracker.ui.viewmodel.MedicationViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MedicationFragment extends Fragment {

    private RecyclerView rvMedications;
    private ProgressBar pBar;
    private Button btnSearchMedications;
    private FloatingActionButton fabAdd;

    private MedicationViewModel viewModel;
    private MedicationAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medication, container, false);
        viewModel = new ViewModelProvider(this).get(MedicationViewModel.class);

        pBar = view.findViewById(R.id.progressBar);
        rvMedications = view.findViewById(R.id.rvMedications);
        btnSearchMedications = view.findViewById(R.id.btnSearchMedications);
        fabAdd = view.findViewById(R.id.fabAddMedication);

        bindRecycleView();
        serOnclickListener();
        return view;
    }

    private void serOnclickListener() {
        btnSearchMedications.setOnClickListener(v -> {
            if (getActivity() instanceof BaseActivity) {
                ((BaseActivity) getActivity()).showFragment(
                        new SearchFragment(),
                        R.id.fragment_container,
                        true
                );
            }
        });

        fabAdd.setOnClickListener(v -> showAddMedicationDialog());
    }

    private void showAddMedicationDialog() {
        // Create dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add New Medication");

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_medication, null);

        final EditText etName = view.findViewById(R.id.etMedicationName);
        final EditText etRxcui = view.findViewById(R.id.etMedicationRxcui);

        builder.setView(view);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String name = etName.getText().toString().trim();
            String rxcui = etRxcui.getText().toString().trim();

            if (!name.isEmpty() && !rxcui.isEmpty()) {
                Medication medication = new Medication(rxcui, name, true);
                viewModel.addMedication(medication); // Add to database
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }


    private void bindRecycleView() {
        rvMedications.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MedicationAdapter();
        rvMedications.setAdapter(adapter);

        // Swipe to delete
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false; // no move
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Medication medication = adapter.getItem(position);
                viewModel.deleteMedication(medication);
            }
        });

        itemTouchHelper.attachToRecyclerView(rvMedications);
    }

    private void fetchMedications() {
        pBar.setVisibility(View.VISIBLE);

        viewModel.getMedications().observe(getViewLifecycleOwner(), result -> {
            pBar.setVisibility(View.GONE);
            adapter.setItems(result);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchMedications();
    }
}
