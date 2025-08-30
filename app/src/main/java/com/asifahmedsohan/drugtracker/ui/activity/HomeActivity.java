package com.asifahmedsohan.drugtracker.ui.activity;

import android.os.Bundle;

import com.asifahmedsohan.drugtracker.R;
import com.asifahmedsohan.drugtracker.ui.fragment.MedicationFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Load default fragment (SearchFragment)
        showFragment(new MedicationFragment(), R.id.fragment_container, false);
    }
}
