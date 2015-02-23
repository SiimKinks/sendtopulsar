package com.siim.sendtopulsar.api;

import com.siim.sendtopulsar.model.PlayerResult;
import com.siim.sendtopulsar.model.command.Base;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class PulsarResponse extends Base {
    private List<PlayerResult> result;
}
