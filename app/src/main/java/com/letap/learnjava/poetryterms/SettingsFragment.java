package com.letap.learnjava.poetryterms;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

/**
 * Created by Dhvani on 31/10/2015.
 */
public class SettingsFragment extends PreferenceFragment {

        public SettingsFragment(){}

    private CheckBoxPreference volume;
    private Preference rate;
    int counter = 0;


        @Override
        public void onCreate( Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.perferences);



            volume = (CheckBoxPreference) getPreferenceManager().findPreference("checkbox_preference");
            rate = (Preference)getPreferenceManager().findPreference("rate_preference");

            volume.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(counter % 2 == 0){
                        AudioManager amanager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
                        //amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
                      //  amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
                        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                      //  amanager.setStreamMute(AudioManager.STREAM_RING, true);
                        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
                    }
                    else{
                        AudioManager amanager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
                    //    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
                      //  amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
                        amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                     //   amanager.setStreamMute(AudioManager.STREAM_RING, false);
                        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
                    }
                    counter++;

                    return false;
                }
            });

            rate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    //Try Google play
                    intent.setData(Uri.parse("market://details?id=com.letap.learnjava.poetryterms"));
                    if (!MyStartActivity(intent)) {
                        //Market (Google play) app seems not installed, let's try to open a webbrowser
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.letap.learnjava.poetryterms"));
                        if (!MyStartActivity(intent)) {
                            //Well if this also fails, we have run out of options, inform the user.
                            Toast.makeText(getActivity(), "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return false;
                }
            });




        }

    private boolean MyStartActivity(Intent aIntent) {
        try
        {
            startActivity(aIntent);
            return true;
        }
        catch (ActivityNotFoundException e)
        {
            return false;
        }
    }



}
