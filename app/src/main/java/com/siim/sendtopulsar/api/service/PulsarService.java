package com.siim.sendtopulsar.api.service;

import com.siim.sendtopulsar.api.NetConst;
import com.siim.sendtopulsar.api.PulsarResponse;
import com.siim.sendtopulsar.model.command.AddToPlaylistCommand;
import com.siim.sendtopulsar.model.command.ClearPlaylistCommand;
import com.siim.sendtopulsar.model.command.GetActivePlayerIdCommand;
import com.siim.sendtopulsar.model.command.OpenCommand;
import com.siim.sendtopulsar.model.command.StopPlayerCommand;
import com.siim.sendtopulsar.module.ApiModule;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import rx.Observable;

public interface PulsarService {

    @Headers(ApiModule.CONTENT_TYPE_JSON)
    @POST(NetConst.JSON_RPC)
    Observable<Response> clearPlaylist(@Body ClearPlaylistCommand command);

    @Headers(ApiModule.CONTENT_TYPE_JSON)
    @POST(NetConst.JSON_RPC)
    Observable<PulsarResponse> getActivePlayerId(@Body GetActivePlayerIdCommand command);

    @Headers(ApiModule.CONTENT_TYPE_JSON)
    @POST(NetConst.JSON_RPC)
    Observable<Response> stopPlayer(@Body StopPlayerCommand command);

    @Headers(ApiModule.CONTENT_TYPE_JSON)
    @POST(NetConst.JSON_RPC)
    Observable<Response> addToPlaylist(@Body AddToPlaylistCommand command);

    @Headers(ApiModule.CONTENT_TYPE_JSON)
    @POST(NetConst.JSON_RPC)
    Observable<Response> open(@Body OpenCommand command);
}
