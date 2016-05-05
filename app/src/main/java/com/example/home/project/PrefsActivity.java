package com.example.home.project;

import android.app.FragmentManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Home on 4/29/16.
 */
public class PrefsActivity extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefsFragment(), PrefsFragment.PREFS_FRAGMENT_TAG).commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fm = getFragmentManager();
        PrefsFragment fragment = (PrefsFragment) fm.findFragmentByTag(PrefsFragment.PREFS_FRAGMENT_TAG);
        fragment.clearSP();

    }
}
