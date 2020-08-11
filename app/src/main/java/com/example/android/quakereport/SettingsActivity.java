package com.example.android.quakereport;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

    }
    public static class EarthquakePreferenceFragment extends PreferenceFragment implements Preference

            .OnPreferenceChangeListener{
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key));
            bindPreferenceSummaryToValue(minMagnitude);

            Preference orderby = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderby);
        }

        private void bindPreferenceSummaryToValue(Preference minMagnitude) {
            minMagnitude.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(minMagnitude.getContext());
            String prefernceString = preferences.getString(minMagnitude.getKey(),"");
            onPreferenceChange(minMagnitude,prefernceString);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            // The code in this method takes care of updating the displayed preference summary after it has been changed
            String stringValue = newValue.toString();
            preference.setSummary(stringValue);
            if(preference instanceof ListPreference){
                // it will first find convert the preference to listprefernce then it will find the index of that particular
                //selected item in the listprefernce by its string value then if the prefvalue>0 then the pref is present and
                //get its entries using getEntries() and then set it to summary otherwise use for min mag entries
                ListPreference listPreference = (ListPreference) preference;
                int prefindex = listPreference.findIndexOfValue(stringValue);
                if(prefindex >= 0 ){
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefindex]);
                }

            }else {
                preference.setSummary(stringValue);
            }

            return true;
        }
    }

}
