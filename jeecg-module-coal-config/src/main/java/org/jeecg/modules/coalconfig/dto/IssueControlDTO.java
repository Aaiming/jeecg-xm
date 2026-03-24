package org.jeecg.modules.coalconfig.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IssueControlDTO {
    @NotNull
    private Long deviceId;
    private Long pointId;
    @NotBlank
    private String command;
    private String commandValue;
    @NotBlank
    private String operator;
}
