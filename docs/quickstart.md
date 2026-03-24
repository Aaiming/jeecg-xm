# 快速启动（完整代码）

## 后端
```bash
cd backend
mvn spring-boot:run
```

## 前端
```bash
cd frontend
npm install
npm run dev
```

## 数据库
```bash
psql -U postgres -d jeecg_boot -f sql/coal_config_schema.sql
```

## 核心接口
- `POST /coal/device`
- `GET /coal/device/page`
- `GET /coal/point/list`
- `POST /coal/control/issue`
