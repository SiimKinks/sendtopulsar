package com.siim.sendtopulsar.module;

import android.content.SharedPreferences;

import com.siim.sendtopulsar.api.JsonUtils;
import com.siim.sendtopulsar.api.RestErrorHandler;
import com.siim.sendtopulsar.api.service.PulsarService;
import com.siim.sendtopulsar.prefs.PulsarEndpoint;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

@Module(complete = false, library = true)
public class ApiModule {

    public static final String CONTENT_TYPE_JSON = "Content-Type: application/json;charset=UTF-8";

    public static RestAdapter buildBasicRestAdapter(PulsarEndpoint endpoint, Client client, RestErrorHandler errorHandler) {
        String endpointString = endpoint.get();
        System.out.println("setting endpoint as " + endpointString);
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(client)
                .setEndpoint(endpointString)
                .setConverter(new JacksonConverter(JsonUtils.objectMapper))
                .setErrorHandler(errorHandler)
                .build();
    }

    @Provides
    @Singleton
    Client provideClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(PulsarEndpoint endpoint, Client client, RestErrorHandler errorHandler) {
        return buildBasicRestAdapter(endpoint, client, errorHandler);
    }

    @Provides
    @Singleton
    PulsarEndpoint providePulsarEndpoint(SharedPreferences sharedPreferences) {
        return new PulsarEndpoint(sharedPreferences);
    }

    @Provides
    @Singleton
    PulsarService providePulsarService(RestAdapter restAdapter) {
        return restAdapter.create(PulsarService.class);
    }
}
