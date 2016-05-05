package com.example.home.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;

/**
 * Created by Home on 4/29/16.
 */
public class PrefsFragment extends PreferenceFragment {

    public static final String PREFS_FRAGMENT_TAG = "PREFS_FRAGMENT";

    public static final String USERNAME_TAG = "THIS_USERNAME";
    public static final String MODE_TAG = "THIS_MODE";

    public SharedPreferences mSP;
    public SharedPreferences.Editor mEditor;
    public String mUsername;
    public String mMode;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }


    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        getResults();
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    public void getResults(){
        mSP= PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        mUsername = mSP.getString("username", "NA").trim();
        mMode = mSP.getString("mode", "2");
        getUsername();
        getMode();
        startOrStop(mMode);
//        if(mMode.equals("1")){
//            startFloatingHead();
//        }else if(mMode.equals("2")){
//            stopFloatingHead();
//        }
    }

    public String getUsername(){
        Log.d(USERNAME_TAG, mUsername);
        return mUsername;

    }


    public String getMode(){
        Log.d(MODE_TAG, mMode);
        return mMode;
    }



    public void clearSP() {
        if (mSP != null) {
            mEditor = mSP.edit();
            mEditor.remove("username");
            mEditor.remove("mode");
            mEditor.apply();
        }
    }

//    public void startFloatingHead(){
//        final Activity activity = getActivity();
//        activity.startService(new Intent(activity, FloatingHead.class));
//    }
//
//
//    public void stopFloatingHead(){
//        final Activity activity = getActivity();
//        activity.stopService(new Intent(activity, FloatingHead.class));
//    }

    public void startOrStop(String mode){
        switch(mode){
            case "1":
                getActivity().startService(new Intent(getActivity(), FloatingHead.class));
                break;

            case "2":
                getActivity().stopService(new Intent(getActivity(), FloatingHead.class));
                break;
        }

    }


}
