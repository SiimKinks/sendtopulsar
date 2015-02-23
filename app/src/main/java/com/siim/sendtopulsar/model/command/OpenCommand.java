package com.siim.sendtopulsar.model.command;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class OpenCommand extends BaseCommand {
    @Getter
    private final String method = "Player.Open";
    @Getter
    private final Params params = new Params();

    private static class Params {
        @Getter
        private final Map<String, Integer> item = new HashMap<>();

        public Params() {
            item.put("playlistid", 1);
            item.put("position", 0);
        }
    }
}
