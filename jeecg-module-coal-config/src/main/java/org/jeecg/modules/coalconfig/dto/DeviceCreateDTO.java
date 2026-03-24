package org.jeecg.modules.coalconfig.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeviceCreateDTO {
    @NotBlank
    private String deviceCode;
    @NotBlank
    private String deviceName;
    @NotBlank
    private String deviceType;
    @NotBlank
    private String processSection;
    @NotBlank
    private String protocolType;
}
