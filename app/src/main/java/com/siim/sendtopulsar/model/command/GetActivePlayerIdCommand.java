package com.siim.sendtopulsar.model.command;

import lombok.Getter;

public class GetActivePlayerIdCommand extends BaseCommand {
    @Getter
    private final String method = "Player.GetActivePlayers";
}
