package com.siim.sendtopulsar.module;

import android.content.SharedPreferences;
import android.util.Log;

import com.siim.sendtopulsar.SendToPulsarApp;
import com.siim.sendtopulsar.api.service.PulsarService;
import com.siim.sendtopulsar.controller.MagnetController;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

@Module(
        includes = ApiModule.class,
        library = true,
        complete = false)
public class DataModule {

    public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    private static final String TAG = DataModule.class.getSimpleName();

    static OkHttpClient createOkHttpClient(SendToPulsarApp app) {
        OkHttpClient client = new OkHttpClient();
        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(app.getCacheDir(), "http");
            Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
            client.setCache(cache);
        } catch (IOException e) {
            Log.e(TAG, "Unable to install disk cache.", e);
        }
        return client;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(SendToPulsarApp app) {
        return createOkHttpClient(app);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(SendToPulsarApp app) {
        return app.getSharedPreferences("prefs", MODE_PRIVATE);
    }

    @Provides
    @Singleton
    MagnetController provideMagnetController(SendToPulsarApp app, PulsarService pulsarService) {
        return new MagnetController(app, pulsarService);
    }
}
