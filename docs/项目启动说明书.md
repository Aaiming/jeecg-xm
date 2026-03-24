# 项目启动说明书

## 1. 后端（Jeecg-Boot 3.9.1 / Java17）
1. 在 Jeecg-Boot 主工程中新增 `jeecg-module-coal-config` 模块并注册。
2. 配置 PostgreSQL/Redis/RabbitMQ 连接。
3. 执行 `sql/coal_config_schema.sql` 初始化新增业务对象。
4. 启动服务并检查 `/coal/*` 接口可用性。

## 2. 前端（Vue3）
1. 在 Jeecg 前端工程新增 `views/coal/*` 页面。
2. 注册新增路由与菜单权限码。
3. 对接后端 `/coal/*` 接口与实时订阅通道。

## 3. 部署
- Nginx 代理前后端。
- Linux/Windows 工控机安装 JDK17、Node 环境。
- 建议配置系统服务守护与日志轮转。

## 4. 测试流程
- 第一轮：静态校验（文档、SQL 语法、结构一致性）。
- 第二轮：回归校验（重复执行脚本检查、打包校验）。
