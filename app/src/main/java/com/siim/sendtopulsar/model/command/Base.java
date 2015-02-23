package com.siim.sendtopulsar.model.command;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Base {
    @Getter
    private final int id = 1;
    @Getter
    private final String jsonrpc = "2.0";
}
