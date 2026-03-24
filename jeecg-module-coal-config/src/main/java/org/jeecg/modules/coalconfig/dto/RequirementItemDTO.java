package org.jeecg.modules.coalconfig.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequirementItemDTO {
    private String module;
    private String feature;
    private String capability;
    private String priority;
    private String status;
    private String suggestion;
}
