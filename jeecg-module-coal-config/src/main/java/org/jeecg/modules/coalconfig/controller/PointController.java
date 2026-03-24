package org.jeecg.modules.coalconfig.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.jeecg.modules.coalconfig.entity.Point;
import org.jeecg.modules.coalconfig.mapper.PointMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coal/point")
public class PointController {
    private final PointMapper pointMapper;

    @GetMapping("/list")
    public List<Point> list(@RequestParam(required = false) Long deviceId) {
        LambdaQueryWrapper<Point> wrapper = new LambdaQueryWrapper<>();
        if (deviceId != null) {
            wrapper.eq(Point::getDeviceId, deviceId);
        }
        wrapper.orderByDesc(Point::getId);
        return pointMapper.selectList(wrapper);
    }
}
