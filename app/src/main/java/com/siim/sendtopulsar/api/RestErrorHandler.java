package com.siim.sendtopulsar.api;

import com.siim.sendtopulsar.SendToPulsarApp;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

@Singleton
public class RestErrorHandler implements ErrorHandler {

    private final SendToPulsarApp app;

    @Inject
    public RestErrorHandler(SendToPulsarApp app) {
        this.app = app;
    }

    @Override
    public Throwable handleError(RetrofitError cause) {
        return cause.getCause();
    }
}
