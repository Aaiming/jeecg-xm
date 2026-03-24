package org.jeecg.modules.coalconfig.service.impl;

import lombok.RequiredArgsConstructor;
import org.jeecg.modules.coalconfig.dto.RequirementItemDTO;
import org.jeecg.modules.coalconfig.service.OverviewService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OverviewServiceImpl implements OverviewService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> metrics() {
        Integer deviceTotal = jdbcTemplate.queryForObject("select count(1) from coal_config.cfg_device", Integer.class);
        Integer pointTotal = jdbcTemplate.queryForObject("select count(1) from coal_config.cfg_point", Integer.class);
        Integer onlineDevice = jdbcTemplate.queryForObject("select count(1) from coal_config.cfg_device where enabled = true", Integer.class);
        Integer alarmToday = jdbcTemplate.queryForObject("""
                select count(1)
                from coal_config.cfg_alarm_event
                where created_at >= date_trunc('day', now())
                """, Integer.class);
        Integer controlToday = jdbcTemplate.queryForObject("""
                select count(1)
                from coal_config.cfg_control_log
                where created_at >= date_trunc('day', now())
                """, Integer.class);
        return Map.of(
                "deviceTotal", deviceTotal,
                "pointTotal", pointTotal,
                "onlineDevice", onlineDevice,
                "alarmToday", alarmToday,
                "controlToday", controlToday,
                "refreshMinMs", 100
        );
    }

    @Override
    public List<RequirementItemDTO> requirements() {
        return List.of(
                new RequirementItemDTO("画面组态", "可视化拖拽/模板/层级", "主子弹窗画面、模板复用、版本发布", "P0", "规划中", "建议优先建设画布引擎与模板中心"),
                new RequirementItemDTO("实时监控", "全流程展示与高频刷新", "支持100ms~5s刷新、趋势图与仪表显示", "P0", "部分完成", "现有设备与控制页可扩展到实时总览"),
                new RequirementItemDTO("设备覆盖", "全系统设备台账与监控", "原煤准备/重介/浮选/脱水/煤泥水/储运/辅助系统", "P0", "部分完成", "先补充设备分类字典与通信配置"),
                new RequirementItemDTO("数据采集", "多协议并行采集", "PLC/仪表/传感器接入、补采与重传", "P0", "规划中", "建议建设采集网关与质量码机制"),
                new RequirementItemDTO("数据管理", "时序+缓存+备份", "PostgreSQL+Redis、备份恢复、压缩归档", "P1", "部分完成", "新增缓存层和历史归档任务"),
                new RequirementItemDTO("报警管理", "三级报警+闭环处理", "弹窗、确认、备注、统计报表", "P0", "规划中", "优先实现规则配置与确认流程"),
                new RequirementItemDTO("系统管理", "参数/通信/界面/版本", "系统参数、通信链路检测、主题布局、回滚", "P1", "规划中", "新增配置中心与版本审计"),
                new RequirementItemDTO("日志管理", "多维日志", "运行日志/操作日志/通信日志/报警日志", "P1", "部分完成", "整合查询筛选与自动清理策略")
        );
    }
}
