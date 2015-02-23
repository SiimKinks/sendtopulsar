package com.siim.sendtopulsar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.siim.sendtopulsar.controller.MagnetController;

import javax.inject.Inject;

public class MagnetActivity extends BaseActivity {

    @Inject
    protected MagnetController magnetController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnet);
        sendToPulsar();
    }

    private void sendToPulsar() {
        Intent intent = getIntent();
        Uri magnetLink = intent.getData();
        magnetController.sendToPulsar(magnetLink);
        finish();
    }
}
