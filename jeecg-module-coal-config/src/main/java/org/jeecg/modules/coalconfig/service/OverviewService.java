package org.jeecg.modules.coalconfig.service;

import org.jeecg.modules.coalconfig.dto.RequirementItemDTO;

import java.util.List;
import java.util.Map;

public interface OverviewService {
    Map<String, Object> metrics();

    List<RequirementItemDTO> requirements();
}
