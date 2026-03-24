-- 选煤厂组态软件 PostgreSQL 数据结构（新增业务库对象）
-- 说明：不修改 Jeecg 原有表；全部使用 cfg_ 前缀新增。

create schema if not exists coal_config;

create table if not exists coal_config.cfg_device (
  id bigserial primary key,
  device_code varchar(64) not null unique,
  device_name varchar(128) not null,
  device_type varchar(64) not null,
  process_section varchar(64) not null,
  protocol_type varchar(32) not null,
  enabled boolean not null default true,
  created_at timestamp not null default now(),
  updated_at timestamp not null default now()
);

create table if not exists coal_config.cfg_point (
  id bigserial primary key,
  point_code varchar(128) not null unique,
  device_id bigint not null references coal_config.cfg_device(id),
  point_name varchar(128) not null,
  data_type varchar(16) not null,
  unit varchar(32),
  rw_type varchar(16) not null,
  addr varchar(128) not null,
  collect_cycle_ms int not null default 1000,
  alarm_enable boolean not null default false,
  created_at timestamp not null default now()
);

create table if not exists coal_config.cfg_screen (
  id bigserial primary key,
  screen_code varchar(64) not null unique,
  screen_name varchar(128) not null,
  screen_level varchar(16) not null,
  status varchar(16) not null default 'DRAFT',
  version_no int not null default 1,
  canvas_json jsonb not null,
  published_at timestamp,
  created_by varchar(64),
  created_at timestamp not null default now(),
  updated_at timestamp not null default now()
);

create table if not exists coal_config.cfg_alarm_rule (
  id bigserial primary key,
  rule_code varchar(64) not null unique,
  point_id bigint not null references coal_config.cfg_point(id),
  level smallint not null,
  expr varchar(512) not null,
  message varchar(256) not null,
  enabled boolean not null default true,
  created_at timestamp not null default now()
);

create table if not exists coal_config.cfg_alarm_event (
  id bigserial primary key,
  rule_id bigint not null references coal_config.cfg_alarm_rule(id),
  point_id bigint not null references coal_config.cfg_point(id),
  level smallint not null,
  event_status varchar(16) not null,
  trigger_value numeric(20,6),
  ack_by varchar(64),
  ack_time timestamp,
  clear_time timestamp,
  event_msg varchar(512),
  created_at timestamp not null default now()
);

create table if not exists coal_config.cfg_realtime_data (
  id bigserial primary key,
  point_id bigint not null references coal_config.cfg_point(id),
  collect_time timestamp not null,
  num_value numeric(20,6),
  str_value varchar(256),
  quality smallint not null default 1
);

create table if not exists coal_config.cfg_control_log (
  id bigserial primary key,
  device_id bigint not null references coal_config.cfg_device(id),
  point_id bigint,
  command varchar(64) not null,
  command_value varchar(128),
  execute_result varchar(16) not null,
  execute_msg varchar(512),
  operator varchar(64) not null,
  created_at timestamp not null default now()
);

create index if not exists idx_cfg_point_device on coal_config.cfg_point(device_id);
create index if not exists idx_cfg_realtime_point_time on coal_config.cfg_realtime_data(point_id, collect_time desc);
create index if not exists idx_cfg_alarm_event_status on coal_config.cfg_alarm_event(event_status, created_at desc);

-- 加密建议（按环境安装 pgcrypto 扩展后启用）
-- create extension if not exists pgcrypto;
-- alter table coal_config.cfg_control_log add column operator_enc bytea;
-- update coal_config.cfg_control_log set operator_enc = pgp_sym_encrypt(operator, 'replace-with-kms-key');

