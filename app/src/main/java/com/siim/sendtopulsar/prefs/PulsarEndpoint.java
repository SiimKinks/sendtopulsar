package com.siim.sendtopulsar.prefs;

import android.content.SharedPreferences;
import android.util.Log;

public class PulsarEndpoint extends StringPreference {

    public PulsarEndpoint(SharedPreferences preferences) {
        super(preferences, "endpoint", "http://localhost:8080");
    }

    public void set(String url, String port) {
        String fullUrl = String.format("http://%s:%s", url, port);
        Log.d(PulsarEndpoint.class.getSimpleName(), "Setting url as " + fullUrl);
        set(fullUrl);
    }
}
