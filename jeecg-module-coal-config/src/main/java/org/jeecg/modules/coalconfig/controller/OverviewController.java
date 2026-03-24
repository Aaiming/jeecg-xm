package org.jeecg.modules.coalconfig.controller;

import lombok.RequiredArgsConstructor;
import org.jeecg.modules.coalconfig.dto.RequirementItemDTO;
import org.jeecg.modules.coalconfig.service.OverviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coal/overview")
public class OverviewController {
    private final OverviewService overviewService;

    @GetMapping("/metrics")
    public Map<String, Object> metrics() {
        return overviewService.metrics();
    }

    @GetMapping("/requirements")
    public List<RequirementItemDTO> requirements() {
        return overviewService.requirements();
    }
}
