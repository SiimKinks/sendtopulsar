package com.siim.sendtopulsar.controller;

import android.net.Uri;
import android.util.Log;

import com.siim.sendtopulsar.SendToPulsarApp;
import com.siim.sendtopulsar.api.service.PulsarService;
import com.siim.sendtopulsar.model.command.AddToPlaylistCommand;
import com.siim.sendtopulsar.model.command.ClearPlaylistCommand;
import com.siim.sendtopulsar.model.command.GetActivePlayerIdCommand;
import com.siim.sendtopulsar.model.command.OpenCommand;
import com.siim.sendtopulsar.model.command.StopPlayerCommand;

import retrofit.client.Response;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

public class MagnetController {

    private static final String TAG = MagnetController.class.getSimpleName();

    private final SendToPulsarApp app;
    private final PulsarService pulsarService;

    public MagnetController(SendToPulsarApp app, PulsarService pulsarService) {
        this.app = app;
        this.pulsarService = pulsarService;
    }

    public void sendToPulsar(final Uri magnetLink) {
        pulsarService.getActivePlayerId(new GetActivePlayerIdCommand())
                .flatMap(pulsarResponse -> Observable.from(pulsarResponse.getResult()))
                .flatMap(playerResult -> {
                    int playerId = playerResult.getPlayerid();
                    return pulsarService.stopPlayer(new StopPlayerCommand(playerId));
                })
                .toList()
                .flatMap(pulsarResponse -> pulsarService.clearPlaylist(new ClearPlaylistCommand(1)))
                .flatMap(pulsarResponse -> pulsarService.clearPlaylist(new ClearPlaylistCommand(0)))
                .flatMap(response -> pulsarService.addToPlaylist(new AddToPlaylistCommand(magnetLink.toString())))
                .flatMap(response -> pulsarService.open(new OpenCommand()))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Magnet sending complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error sending magnet to pulsar", e);
                    }

                    @Override
                    public void onNext(Response response) {
                        int status = response.getStatus();
                        Log.d(TAG, "Got next with status=" + status);
                    }
                });
    }
}
