package com.siim.sendtopulsar.model.command;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class AddToPlaylistCommand extends BaseCommand {
    @Getter
    protected final String method = "Playlist.Add";
    @Getter
    private final Params params;

    public AddToPlaylistCommand(String url) {
        params = new Params(url);
    }

    private static class Params {
        @Getter
        private final int playlistid = 1;
        @Getter
        private final Map<String, String> item = new HashMap<>();

        public Params(String url) {
            item.put("file", "plugin://plugin.video.pulsar/play?uri=" + url);
        }
    }
}
