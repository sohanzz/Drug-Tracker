package com.asifahmedsohan.drugtracker.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * BaseActivity provides a generic method to handle fragment transactions
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Replace fragment in the specified container
     *
     * @param fragment       The fragment to display
     * @param containerId    The FrameLayout or container id
     * @param addToBackStack Whether to add this transaction to backstack
     */
    public void showFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        transaction.commit();
    }

    /**
     * Add fragment in the specified container
     *
     * @param fragment       The fragment to add
     * @param containerId    The FrameLayout or container id
     * @param addToBackStack Whether to add this transaction to backstack
     */
    public void addFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(containerId, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        transaction.commit();
    }
}
