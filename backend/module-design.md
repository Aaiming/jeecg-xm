# 后端新增模块设计（Jeecg-Boot 3.9.1）

## 1. 模块边界
- 新增模块名：`jeecg-module-coal-config`
- 包路径建议：`org.jeecg.modules.coalconfig`
- 原则：仅新增，不改动开源原模块与原数据库。

## 2. 分层
- controller：REST API
- service：业务逻辑（含控制权限校验、告警规则计算）
- repository/mapper：数据库访问
- adapter：协议适配（Modbus/OPC/MQTT）
- scheduler：采集任务、归档任务、统计任务

## 3. 核心接口（示例）
- `GET /coal/device/page` 设备分页查询
- `POST /coal/device` 新增设备
- `POST /coal/screen/publish/{id}` 画面发布
- `GET /coal/realtime/{pointCode}` 获取实时值
- `POST /coal/control/issue` 下发控制命令（双确认）
- `GET /coal/alarm/event/page` 报警事件分页

## 4. 事务与并发
- 控制命令日志写入与命令下发同事务边界拆分（消息投递确保最终一致性）。
- 采集数据批量入库 + 分片缓存 + 异步落盘。

## 5. 安全
- 接入 Jeecg 权限注解，控制接口最小权限。
- 控制 API 增加操作令牌和防重放 nonce。
