package com.siim.sendtopulsar.model.command;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class StopPlayerCommand extends BaseCommand {
    @Getter
    private final String method = "Player.Stop";
    @Getter
    private final Map<String, Integer> params = new HashMap<>();

    public StopPlayerCommand(int playerId) {
        params.put("playerid", playerId);
    }
}
