package org.jeecg.modules.coalconfig.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.coalconfig.dto.DeviceCreateDTO;
import org.jeecg.modules.coalconfig.entity.Device;

public interface DeviceService {
    Device create(DeviceCreateDTO dto);
    Page<Device> page(String keyword, long pageNo, long pageSize);
}
