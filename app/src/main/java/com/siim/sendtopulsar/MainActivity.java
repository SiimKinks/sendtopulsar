package com.siim.sendtopulsar;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.siim.sendtopulsar.api.service.PulsarService;
import com.siim.sendtopulsar.controller.MagnetController;
import com.siim.sendtopulsar.prefs.PulsarEndpoint;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @InjectView(R.id.url_input)
    EditText urlView;
    @InjectView(R.id.port_input)
    EditText portView;

    @Inject
    protected PulsarEndpoint endpoint;
    @Inject
    protected SendToPulsarApp app;
    @Inject
    protected MagnetController magnetController;
    @Inject
    protected PulsarService pulsarService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        String url = endpoint.get();
        System.out.println(url);
        Uri uri = Uri.parse(url);
        urlView.setText(uri.getHost());
        portView.setText(String.valueOf(uri.getPort()));
    }

    @OnClick(R.id.save_button)
    protected void onSaveButtonClick() {
        String url = urlView.getText().toString();
        String port = portView.getText().toString();
        endpoint.set(url, port);
        app.buildObjectGraphAndInject();
        Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
        finish();
    }
}
