# 选煤厂组态项目（目录重构版）

已按你要求整理为四大模块：

```text
.
├── jeecg-module-coal-config   # Jeecg-Boot 后端模块（Java 17 / Spring Boot）
├── vue3                       # Vue3 前端工程（TypeScript + Vite）
├── sql                        # PostgreSQL 建库与初始化脚本
└── README.md                  # 项目总说明
```

## 1) jeecg-module-coal-config
后端模块代码，包含：
- 设备管理（Device）
- 点位管理（Point）
- 控制下发（Control）

### 后端启动
```bash
cd jeecg-module-coal-config
mvn spring-boot:run
```

## 2) vue3
前端工程代码，包含：
- 设备台账页面
- 控制操作页面
- 路由与 API 封装

### 前端启动
```bash
cd vue3
npm install
npm run dev
```

## 3) sql
数据库脚本：
- `coal_config_schema.sql`

### 数据库导入（PostgreSQL）
```bash
psql -U postgres -d jeecg_boot -f sql/coal_config_schema.sql
```

## 4) 核心接口（后端）
- `POST /coal/device`
- `GET /coal/device/page`
- `GET /coal/point/list`
- `POST /coal/control/issue`

## 5) 技术栈
- 后端：Jeecg-Boot 3.9.1 / Java 17 / MyBatis-Plus
- 前端：Vue3 / TypeScript / Vite
- 数据库：PostgreSQL

