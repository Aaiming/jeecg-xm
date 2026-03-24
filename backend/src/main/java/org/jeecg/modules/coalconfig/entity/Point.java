package org.jeecg.modules.coalconfig.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("cfg_point")
public class Point {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String pointCode;
    private Long deviceId;
    private String pointName;
    private String dataType;
    private String unit;
    private String rwType;
    private String addr;
    private Integer collectCycleMs;
    private Boolean alarmEnable;
    private LocalDateTime createdAt;
}
