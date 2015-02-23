package com.siim.sendtopulsar;

import com.siim.sendtopulsar.module.DataModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                DataModule.class,
        },
        injects = {
                SendToPulsarApp.class,
                MainActivity.class,
                MagnetActivity.class
        },
        library = true,
        complete = false)
public class AppModule {

    private final SendToPulsarApp app;

    public AppModule(SendToPulsarApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public SendToPulsarApp provideApplication() {
        return app;
    }
}
