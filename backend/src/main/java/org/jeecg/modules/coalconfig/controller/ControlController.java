package org.jeecg.modules.coalconfig.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jeecg.modules.coalconfig.dto.IssueControlDTO;
import org.jeecg.modules.coalconfig.service.ControlService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coal/control")
public class ControlController {
    private final ControlService controlService;

    @PostMapping("/issue")
    public Map<String, Object> issue(@RequestBody @Valid IssueControlDTO dto) {
        return controlService.issue(dto);
    }
}
