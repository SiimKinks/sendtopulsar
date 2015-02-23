package com.siim.sendtopulsar.prefs;

import android.content.SharedPreferences;

import lombok.AllArgsConstructor;

@AllArgsConstructor(suppressConstructorProperties = true)
public abstract class BasePreference<T> {

    protected final SharedPreferences preferences;
    protected final String key;
    protected final T defaultValue;

    public abstract T get();

    public abstract void set(T value);

    public boolean isSet() {
        return preferences.contains(key);
    }

    public void delete() {
        preferences.edit().remove(key).apply();
    }
}
