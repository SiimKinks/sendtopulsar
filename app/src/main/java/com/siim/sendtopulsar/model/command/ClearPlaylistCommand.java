package com.siim.sendtopulsar.model.command;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class ClearPlaylistCommand extends BaseCommand {
    @Getter
    private final String method = "Playlist.Clear";
    @Getter
    private final Map<String, Integer> params = new HashMap<>();

    public ClearPlaylistCommand(int playlistId) {
        this.params.put("playlistid", playlistId);
    }
}
