# 前端新增模块设计（Vue3）

## 页面清单
- 组态编辑器：`/coal/config/editor`
- 实时监控：`/coal/monitor/realtime`
- 设备台账：`/coal/device`
- 报警中心：`/coal/alarm`
- 数据分析：`/coal/analysis`
- 系统配置：`/coal/settings`

## 组件建议
- `ProcessCanvas.vue`：流程画布
- `DeviceWidget.vue`：设备元件
- `PointBindingPanel.vue`：点位绑定面板
- `AlarmTicker.vue`：报警跑马灯
- `ControlConfirmDialog.vue`：控制二次确认

## 性能策略
- WebSocket + 增量更新。
- 按区域分片渲染。
- 虚拟列表展示报警与日志。
