package com.siim.sendtopulsar;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SendToPulsarApp app = (SendToPulsarApp) getApplication();
        app.inject(this);
    }
}
