package org.jeecg.modules.coalconfig.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.jeecg.modules.coalconfig.dto.DeviceCreateDTO;
import org.jeecg.modules.coalconfig.entity.Device;
import org.jeecg.modules.coalconfig.mapper.DeviceMapper;
import org.jeecg.modules.coalconfig.service.DeviceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceMapper deviceMapper;

    @Override
    public Device create(DeviceCreateDTO dto) {
        Device d = new Device();
        d.setDeviceCode(dto.getDeviceCode());
        d.setDeviceName(dto.getDeviceName());
        d.setDeviceType(dto.getDeviceType());
        d.setProcessSection(dto.getProcessSection());
        d.setProtocolType(dto.getProtocolType());
        d.setEnabled(Boolean.TRUE);
        d.setCreatedAt(LocalDateTime.now());
        d.setUpdatedAt(LocalDateTime.now());
        deviceMapper.insert(d);
        return d;
    }

    @Override
    public Page<Device> page(String keyword, String processSection, long pageNo, long pageSize) {
        LambdaQueryWrapper<Device> wrapper = new LambdaQueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            wrapper.and(w -> w.like(Device::getDeviceName, keyword)
                    .or().like(Device::getDeviceCode, keyword));
        }
        if (processSection != null && !processSection.isBlank()) {
            wrapper.eq(Device::getProcessSection, processSection);
        }
        wrapper.orderByDesc(Device::getUpdatedAt);
        return deviceMapper.selectPage(Page.of(pageNo, pageSize), wrapper);
    }
}
