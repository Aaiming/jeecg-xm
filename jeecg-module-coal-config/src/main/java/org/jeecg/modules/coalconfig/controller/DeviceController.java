package org.jeecg.modules.coalconfig.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jeecg.modules.coalconfig.dto.DeviceCreateDTO;
import org.jeecg.modules.coalconfig.entity.Device;
import org.jeecg.modules.coalconfig.service.DeviceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coal/device")
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping
    public Device create(@RequestBody @Valid DeviceCreateDTO dto) {
        return deviceService.create(dto);
    }

    @GetMapping("/page")
    public Page<Device> page(@RequestParam(required = false) String keyword,
                             @RequestParam(defaultValue = "1") long pageNo,
                             @RequestParam(defaultValue = "10") long pageSize) {
        return deviceService.page(keyword, pageNo, pageSize);
    }
}
