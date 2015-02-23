package com.siim.sendtopulsar;

import android.app.Application;

import dagger.ObjectGraph;

public class SendToPulsarApp extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraphAndInject();
    }

    public void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(new AppModule(this));
        objectGraph.inject(this);
    }

    public void inject(Object target) {
        objectGraph.inject(target);
    }
}
