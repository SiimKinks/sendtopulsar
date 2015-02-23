package com.siim.sendtopulsar.prefs;

import android.content.SharedPreferences;

public class StringPreference extends BasePreference<String> {

    public StringPreference(SharedPreferences preferences, String key, String defaultValue) {
        super(preferences, key, defaultValue);
    }

    public StringPreference(SharedPreferences preferences, String key) {
        super(preferences, key, null);
    }

    @Override
    public String get() {
        return preferences.getString(key, defaultValue);
    }

    @Override
    public void set(String value) {
        preferences.edit().putString(key, value).apply();
    }

}
