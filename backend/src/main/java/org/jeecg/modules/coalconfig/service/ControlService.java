package org.jeecg.modules.coalconfig.service;

import org.jeecg.modules.coalconfig.dto.IssueControlDTO;

import java.util.Map;

public interface ControlService {
    Map<String, Object> issue(IssueControlDTO dto);
}
