package org.jeecg.modules.coalconfig.service.impl;

import lombok.RequiredArgsConstructor;
import org.jeecg.modules.coalconfig.dto.IssueControlDTO;
import org.jeecg.modules.coalconfig.service.ControlService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ControlServiceImpl implements ControlService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> issue(IssueControlDTO dto) {
        jdbcTemplate.update("""
            insert into coal_config.cfg_control_log(device_id, point_id, command, command_value, execute_result, execute_msg, operator, created_at)
            values(?, ?, ?, ?, 'SUCCESS', 'SIMULATED', ?, ?)
        """, dto.getDeviceId(), dto.getPointId(), dto.getCommand(), dto.getCommandValue(), dto.getOperator(), LocalDateTime.now());

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "命令已受理（模拟下发）");
        return result;
    }
}
