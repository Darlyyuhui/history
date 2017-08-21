----------------------------------------------------
-- Export file for user ADMIN                     --
-- Created by Administrator on 2014-2-25, 9:40:56 --
----------------------------------------------------

spool cross.log

prompt
prompt Creating table ANALYSIS_CROSS_BLACK_DAYS
prompt ========================================
prompt
create table ADMIN.ANALYSIS_CROSS_BLACK_DAYS
(
  ID                 VARCHAR2(32) default sys_guid() not null,
  ROAD_CODE          VARCHAR2(20),
  DEVICE_CODE        VARCHAR2(20),
  DIRECTION_CODE     VARCHAR2(20),
  BLACK_TYPE_CODE    VARCHAR2(10),
  BLACK_ALARM_COUNTS NUMBER,
  BEGINTIME          TIMESTAMP(6),
  ENDTIME            TIMESTAMP(6),
  STATETIME          TIMESTAMP(6) default sysdate
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.ANALYSIS_CROSS_BLACK_DAYS.ID
  is '主键';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_DAYS.ROAD_CODE
  is '道路编号';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_DAYS.DEVICE_CODE
  is '设备编号';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_DAYS.DIRECTION_CODE
  is '方向编号';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_DAYS.BLACK_TYPE_CODE
  is '布控类型编号';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_DAYS.BLACK_ALARM_COUNTS
  is '布控报警数';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_DAYS.BEGINTIME
  is '开始时间';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_DAYS.ENDTIME
  is '结束时间';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_DAYS.STATETIME
  is '统计时间';
alter table ADMIN.ANALYSIS_CROSS_BLACK_DAYS
  add primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ANALYSIS_CROSS_BLACK_MONTH
prompt =========================================
prompt
create table ADMIN.ANALYSIS_CROSS_BLACK_MONTH
(
  ID                 VARCHAR2(32) default sys_guid() not null,
  ROAD_CODE          VARCHAR2(20),
  DEVICE_CODE        VARCHAR2(20),
  DIRECTION_CODE     VARCHAR2(20),
  BLACK_TYPE_CODE    VARCHAR2(10),
  BLACK_ALARM_COUNTS NUMBER,
  DAYTIME            TIMESTAMP(6),
  STATETIME          TIMESTAMP(6) default sysdate
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.ANALYSIS_CROSS_BLACK_MONTH.ID
  is '主键';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_MONTH.ROAD_CODE
  is '道路编号';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_MONTH.DEVICE_CODE
  is '设备编号';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_MONTH.DIRECTION_CODE
  is '方向编号';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_MONTH.BLACK_TYPE_CODE
  is '布控类型编号';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_MONTH.BLACK_ALARM_COUNTS
  is '布控报警数';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_MONTH.DAYTIME
  is '时间';
comment on column ADMIN.ANALYSIS_CROSS_BLACK_MONTH.STATETIME
  is '统计时间';
alter table ADMIN.ANALYSIS_CROSS_BLACK_MONTH
  add primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ANALYSIS_CROSS_FLOW_DAYS
prompt =======================================
prompt
create table ADMIN.ANALYSIS_CROSS_FLOW_DAYS
(
  ID             VARCHAR2(32) default sys_guid() not null,
  DEVICE_CODE    VARCHAR2(32) not null,
  LANE           VARCHAR2(10) not null,
  COUNTS         NUMBER not null,
  BEGINTIME      TIMESTAMP(6) not null,
  STATETIME      TIMESTAMP(6) default sysdate not null,
  AVGSPEED       NUMBER not null,
  DIRECTION_CODE VARCHAR2(32) not null,
  ENDTIME        TIMESTAMP(6) not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.ANALYSIS_CROSS_FLOW_DAYS.DEVICE_CODE
  is '设备编码';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_DAYS.LANE
  is '车道';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_DAYS.COUNTS
  is '流量数';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_DAYS.BEGINTIME
  is '开始时间';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_DAYS.STATETIME
  is '统计时间';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_DAYS.AVGSPEED
  is '平均速度';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_DAYS.DIRECTION_CODE
  is '方向编码';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_DAYS.ENDTIME
  is '结束时间';
alter table ADMIN.ANALYSIS_CROSS_FLOW_DAYS
  add constraint ANALYSIS_CROSS_DAYS_PK primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.FLOW_DAYS_DEVICE_TIMES_INDEX on ADMIN.ANALYSIS_CROSS_FLOW_DAYS (DEVICE_CODE, BEGINTIME, ENDTIME)
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ANALYSIS_CROSS_FLOW_MONTH
prompt ========================================
prompt
create table ADMIN.ANALYSIS_CROSS_FLOW_MONTH
(
  ID             VARCHAR2(32) default sys_guid() not null,
  DEVICE_CODE    VARCHAR2(32) not null,
  DIRECTION_CODE VARCHAR2(32) not null,
  LANE           VARCHAR2(10) not null,
  COUNTS         NUMBER not null,
  DAYTIME        TIMESTAMP(6) not null,
  STATETIME      TIMESTAMP(6) default sysdate not null,
  AVGSPEED       NUMBER not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.ANALYSIS_CROSS_FLOW_MONTH.DEVICE_CODE
  is '设备编码';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_MONTH.DIRECTION_CODE
  is '方向编码';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_MONTH.LANE
  is '车道';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_MONTH.COUNTS
  is '流量数';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_MONTH.DAYTIME
  is '时间';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_MONTH.STATETIME
  is '统计时间';
comment on column ADMIN.ANALYSIS_CROSS_FLOW_MONTH.AVGSPEED
  is '平均速度';
alter table ADMIN.ANALYSIS_CROSS_FLOW_MONTH
  add constraint ANALYSIS_CROSS_FLOW_MONTH_PK primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.CROSS_FLOW_MONTH_INDEX on ADMIN.ANALYSIS_CROSS_FLOW_MONTH (DEVICE_CODE, DAYTIME)
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ANALYSIS_CROSS_NOLOCAL_DAYS
prompt ==========================================
prompt
create table ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS
(
  ID              VARCHAR2(32) default sys_guid() not null,
  DEVICE_CODE     VARCHAR2(32) not null,
  LANE            VARCHAR2(10) not null,
  COUNTS          NUMBER not null,
  NOLOCAL_COUNTS  NUMBER not null,
  BEGINTIME       TIMESTAMP(6) not null,
  STATETIME       TIMESTAMP(6) default sysdate not null,
  DIRECTION_CODE  VARCHAR2(32) not null,
  ENDTIME         TIMESTAMP(6) not null,
  LOCAL_COUNTS    NUMBER not null,
  NOTKNOWN_COUNTS NUMBER not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.ID
  is '主键ID';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.DEVICE_CODE
  is '设备编码';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.LANE
  is '车道';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.COUNTS
  is '车辆总数';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.NOLOCAL_COUNTS
  is '外地车辆总数';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.BEGINTIME
  is '开始时间';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.STATETIME
  is '统计时间';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.DIRECTION_CODE
  is '方向编码';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.ENDTIME
  is '结束时间';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.LOCAL_COUNTS
  is '本地车辆总数';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS.NOTKNOWN_COUNTS
  is '未识别车辆总数';
alter table ADMIN.ANALYSIS_CROSS_NOLOCAL_DAYS
  add primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ANALYSIS_CROSS_NOLOCAL_MONTH
prompt ===========================================
prompt
create table ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH
(
  ID              VARCHAR2(32) default sys_guid() not null,
  DEVICE_CODE     VARCHAR2(32) not null,
  DIRECTION_CODE  VARCHAR2(32) not null,
  LANE            VARCHAR2(10) not null,
  COUNTS          NUMBER not null,
  NOLOCAL_COUNTS  NUMBER not null,
  DAYTIME         TIMESTAMP(6) not null,
  STATETIME       TIMESTAMP(6) default sysdate not null,
  LOCAL_COUNTS    NUMBER not null,
  NOTKNOWN_COUNTS NUMBER not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH.ID
  is '主键ID';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH.DEVICE_CODE
  is '设备编码';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH.DIRECTION_CODE
  is '方向编码';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH.LANE
  is '车道';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH.COUNTS
  is '车辆总数';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH.NOLOCAL_COUNTS
  is '外地车辆总数';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH.DAYTIME
  is '时间';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH.STATETIME
  is '统计时间';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH.LOCAL_COUNTS
  is '本地车辆总数';
comment on column ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH.NOTKNOWN_COUNTS
  is '未识别车辆总数';
alter table ADMIN.ANALYSIS_CROSS_NOLOCAL_MONTH
  add primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ANALYSIS_VIO_COUNT_DAYS
prompt ======================================
prompt
create table ADMIN.ANALYSIS_VIO_COUNT_DAYS
(
  ID              VARCHAR2(32) default sys_guid() not null,
  VIO_TYPE_CODE   VARCHAR2(32),
  ROAD_CODE       VARCHAR2(32),
  ROAD_NAME       VARCHAR2(200),
  DEVICE_CODE     VARCHAR2(32),
  DEVICE_NAME     VARCHAR2(200),
  DIRECTION_CODE  VARCHAR2(32),
  LANE_CODE       VARCHAR2(50),
  COUNT_ALL       NUMBER,
  COUNT_DISTINCT  NUMBER,
  STATETIME       TIMESTAMP(6) default sysdate,
  BEGINTIME       TIMESTAMP(6),
  ENDTIME         TIMESTAMP(6),
  AVGSPEED        NUMBER,
  COUNT_AFFIRM    NUMBER,
  COUNT_NOTAFFIRM NUMBER
)
tablespace VIO
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.ID
  is '主键';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.VIO_TYPE_CODE
  is '违法类型编号';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.ROAD_CODE
  is '路段编号';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.ROAD_NAME
  is '路段名称';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.DEVICE_CODE
  is '设备编号';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.DEVICE_NAME
  is '设备名称';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.DIRECTION_CODE
  is '方向编号';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.LANE_CODE
  is '车道编号';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.COUNT_ALL
  is '违法车次总计';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.COUNT_DISTINCT
  is '违法车辆数总计（同一辆车违法多次，只统计一次）';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.STATETIME
  is '统计数据插入时间';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.BEGINTIME
  is '统计时段开始时间';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.ENDTIME
  is '统计时段结束时间';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.AVGSPEED
  is '平均车速';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.COUNT_AFFIRM
  is '已确认违法车辆数';
comment on column ADMIN.ANALYSIS_VIO_COUNT_DAYS.COUNT_NOTAFFIRM
  is '未确认违法车辆数';
alter table ADMIN.ANALYSIS_VIO_COUNT_DAYS
  add constraint PK_VIOCOUNT_ID primary key (ID)
  using index 
  tablespace VIO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ANALYSIS_VIO_COUNT_MONTH
prompt =======================================
prompt
create table ADMIN.ANALYSIS_VIO_COUNT_MONTH
(
  ID              VARCHAR2(32) default sys_guid() not null,
  VIO_TYPE_CODE   VARCHAR2(32),
  ROAD_CODE       VARCHAR2(32),
  ROAD_NAME       VARCHAR2(200),
  DEVICE_CODE     VARCHAR2(32),
  DEVICE_NAME     VARCHAR2(200),
  DIRECTION_CODE  VARCHAR2(32),
  LANE_CODE       VARCHAR2(50),
  COUNT_ALL       NUMBER,
  COUNT_DISTINCT  NUMBER,
  STATETIME       TIMESTAMP(6) default sysdate,
  AVGSPEED        NUMBER,
  COUNT_AFFIRM    NUMBER,
  COUNT_NOTAFFIRM NUMBER,
  DAYTIME         TIMESTAMP(6)
)
tablespace VIO
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.ID
  is '主键';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.VIO_TYPE_CODE
  is '违法类型编号';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.ROAD_CODE
  is '路段编号';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.ROAD_NAME
  is '路段名称';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.DEVICE_CODE
  is '设备编号';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.DEVICE_NAME
  is '设备名称';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.DIRECTION_CODE
  is '方向编号';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.LANE_CODE
  is '车道编号';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.COUNT_ALL
  is '违法车次总计';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.COUNT_DISTINCT
  is '违法车辆数总计';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.STATETIME
  is '统计数据插入时间';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.AVGSPEED
  is '平均车速';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.COUNT_AFFIRM
  is '已确认违法车辆数';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.COUNT_NOTAFFIRM
  is '未确认违法车辆数';
comment on column ADMIN.ANALYSIS_VIO_COUNT_MONTH.DAYTIME
  is '时间';
alter table ADMIN.ANALYSIS_VIO_COUNT_MONTH
  add constraint PK_VIOCOUNTMONTH_ID primary key (ID)
  using index 
  tablespace VIO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BLACKTYPECOLOR
prompt =============================
prompt
create table ADMIN.BLACKTYPECOLOR
(
  ID             VARCHAR2(32) not null,
  NAME           VARCHAR2(20),
  COLORVALUE     VARCHAR2(10),
  BLACKETYPE     VARCHAR2(50),
  BLACKETYPECODE VARCHAR2(50)
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.BLACKTYPECOLOR
  is '布控类型颜色表';
comment on column ADMIN.BLACKTYPECOLOR.ID
  is '主键';
comment on column ADMIN.BLACKTYPECOLOR.NAME
  is '布控类型汉字拼音缩写';
comment on column ADMIN.BLACKTYPECOLOR.COLORVALUE
  is '颜色值';
comment on column ADMIN.BLACKTYPECOLOR.BLACKETYPE
  is '布控类型汉字';
comment on column ADMIN.BLACKTYPECOLOR.BLACKETYPECODE
  is '布控类型code';
alter table ADMIN.BLACKTYPECOLOR
  add constraint CROSS_BTCOLOR_PRI primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_BLACK_ALARM
prompt ================================
prompt
create table ADMIN.CROSS_BLACK_ALARM
(
  ID               VARCHAR2(32) not null,
  RECORD_ID        VARCHAR2(32),
  BLACK_ID         VARCHAR2(32),
  ALARMTIME        DATE,
  ROAD_CODE        VARCHAR2(20),
  DEVICE_CODE      VARCHAR2(20),
  DIRECTION_CODE   VARCHAR2(20),
  PLATE_NUM        VARCHAR2(10),
  PLATE_COLOR_CODE VARCHAR2(10),
  IMG_PATH         VARCHAR2(500),
  BLACK_TYPE_CODE  VARCHAR2(10) not null,
  LANE_CODE        VARCHAR2(2),
  CAR_SPEED        NUMBER(3),
  IMG2_PATH        VARCHAR2(500),
  IMG3_PATH        VARCHAR2(500),
  IMG4_PATH        VARCHAR2(500)
)
tablespace ALARM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.CROSS_BLACK_ALARM.RECORD_ID
  is '通行记录ID';
comment on column ADMIN.CROSS_BLACK_ALARM.BLACK_ID
  is '布控ID';
comment on column ADMIN.CROSS_BLACK_ALARM.ALARMTIME
  is '报警时间';
comment on column ADMIN.CROSS_BLACK_ALARM.ROAD_CODE
  is '道路编号';
comment on column ADMIN.CROSS_BLACK_ALARM.DEVICE_CODE
  is '设备编号';
comment on column ADMIN.CROSS_BLACK_ALARM.DIRECTION_CODE
  is '方向编号';
comment on column ADMIN.CROSS_BLACK_ALARM.PLATE_NUM
  is '号牌号码';
comment on column ADMIN.CROSS_BLACK_ALARM.PLATE_COLOR_CODE
  is '号牌颜色代码';
comment on column ADMIN.CROSS_BLACK_ALARM.IMG_PATH
  is '图片路径';
comment on column ADMIN.CROSS_BLACK_ALARM.BLACK_TYPE_CODE
  is '布控类型编号';
comment on column ADMIN.CROSS_BLACK_ALARM.LANE_CODE
  is '行驶车道';
comment on column ADMIN.CROSS_BLACK_ALARM.CAR_SPEED
  is '车辆速度(Km/h)';
comment on column ADMIN.CROSS_BLACK_ALARM.IMG2_PATH
  is '图片路径2';
comment on column ADMIN.CROSS_BLACK_ALARM.IMG3_PATH
  is '图片路径3';
comment on column ADMIN.CROSS_BLACK_ALARM.IMG4_PATH
  is '图片路径4';
alter table ADMIN.CROSS_BLACK_ALARM
  add constraint PK_BLACKALARM_ID primary key (ID);
create unique index ADMIN.PK_ALARM_ID on ADMIN.CROSS_BLACK_ALARM (ID)
  tablespace ALARM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_BLACK_ALARMHIS
prompt ===================================
prompt
create table ADMIN.CROSS_BLACK_ALARMHIS
(
  ID               VARCHAR2(32) not null,
  RECORD_ID        VARCHAR2(32),
  BLACK_ID         VARCHAR2(32),
  ALARMTIME        DATE,
  ROAD_CODE        VARCHAR2(20),
  DEVICE_CODE      VARCHAR2(20),
  DIRECTION_CODE   VARCHAR2(20),
  PLATE_NUM        VARCHAR2(10) not null,
  PLATE_COLOR_CODE VARCHAR2(10) not null,
  IMG_PATH         VARCHAR2(500),
  BLACK_TYPE_CODE  VARCHAR2(10) not null,
  LANE_CODE        VARCHAR2(2),
  CAR_SPEED        NUMBER(3),
  IMG2_PATH        VARCHAR2(500),
  IMG3_PATH        VARCHAR2(500),
  IMG4_PATH        VARCHAR2(500)
)
tablespace ALARM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.CROSS_BLACK_ALARMHIS.IMG_PATH
  is '图片路径';
comment on column ADMIN.CROSS_BLACK_ALARMHIS.BLACK_TYPE_CODE
  is '布控类型编号';
comment on column ADMIN.CROSS_BLACK_ALARMHIS.LANE_CODE
  is '行驶车道';
comment on column ADMIN.CROSS_BLACK_ALARMHIS.CAR_SPEED
  is '车辆速度(Km/h)';
comment on column ADMIN.CROSS_BLACK_ALARMHIS.IMG2_PATH
  is '图片路径2';
comment on column ADMIN.CROSS_BLACK_ALARMHIS.IMG3_PATH
  is '图片路径3';
comment on column ADMIN.CROSS_BLACK_ALARMHIS.IMG4_PATH
  is '图片路径4';
alter table ADMIN.CROSS_BLACK_ALARMHIS
  add constraint PK_ALARMHIS_ID primary key (ID)
  using index 
  tablespace ALARM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_BLACK_HIS
prompt ==============================
prompt
create table ADMIN.CROSS_BLACK_HIS
(
  ID                      VARCHAR2(20) not null,
  CAR_PLATE_NUM           VARCHAR2(15) not null,
  CAR_PLATE_COLOR_CODE    VARCHAR2(10),
  CAR_PLATE_TYPE_CODE     VARCHAR2(10),
  CAR_STATE_CODE          VARCHAR2(10),
  CAR_CLASS_CODE          VARCHAR2(10),
  CAR_COLOR_CODE          VARCHAR2(10),
  CAR_TYPE_CODE           VARCHAR2(10),
  CAR_BRAND_CODE          VARCHAR2(10),
  TIME_ADD                DATE not null,
  TIME_START              DATE not null,
  TIME_END                DATE not null,
  TIME_VERIFY             DATE,
  IS_VERIFYPASS           VARCHAR2(2) default 0 not null,
  TIME_CANCEL             DATE,
  IS_CANCEL               VARCHAR2(2) default 0 not null,
  TIME_CANCEL_VERIFY      DATE,
  IS_CANCELPASS           VARCHAR2(2) default 0 not null,
  USER_CODE_ENTRY         VARCHAR2(10) not null,
  USER_CODE_VERIFY        VARCHAR2(10),
  USER_CODE_CANCEL        VARCHAR2(10),
  USER_CODE_CANCEL_VERIFY VARCHAR2(10),
  BLACK_TYPE_CODE         VARCHAR2(10),
  BLACK_LEVEL_CODE        VARCHAR2(10),
  ROAD_CODE               VARCHAR2(1000),
  IS_ILLEGIBILITY         VARCHAR2(2) default 0 not null,
  IS_SOUNDALERM           VARCHAR2(2),
  IS_SMSALERM             VARCHAR2(2),
  IS_MUSTOPERATE          VARCHAR2(2),
  SMS_CONTACTIDS          VARCHAR2(200),
  ORG_ID                  VARCHAR2(200) not null,
  ORG_NAME                VARCHAR2(200) not null,
  NOTE                    VARCHAR2(600),
  PRECAUTION              VARCHAR2(20),
  SOURCE                  VARCHAR2(2) default 0 not null,
  CANCELCAUSE_CODE        VARCHAR2(20),
  DEVICE_CODE             CLOB
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.CROSS_BLACK_HIS.CAR_PLATE_NUM
  is '号牌号码';
comment on column ADMIN.CROSS_BLACK_HIS.CAR_PLATE_COLOR_CODE
  is '号牌颜色编号';
comment on column ADMIN.CROSS_BLACK_HIS.CAR_PLATE_TYPE_CODE
  is '号牌类型编号';
comment on column ADMIN.CROSS_BLACK_HIS.CAR_STATE_CODE
  is '车辆状态编号';
comment on column ADMIN.CROSS_BLACK_HIS.CAR_CLASS_CODE
  is '车辆分类编号';
comment on column ADMIN.CROSS_BLACK_HIS.CAR_COLOR_CODE
  is '车身颜色编号';
comment on column ADMIN.CROSS_BLACK_HIS.CAR_TYPE_CODE
  is '车辆类型编号';
comment on column ADMIN.CROSS_BLACK_HIS.CAR_BRAND_CODE
  is '车辆品牌编号';
comment on column ADMIN.CROSS_BLACK_HIS.TIME_ADD
  is '插入时间';
comment on column ADMIN.CROSS_BLACK_HIS.TIME_START
  is '布控开始时间';
comment on column ADMIN.CROSS_BLACK_HIS.TIME_END
  is '布控结束时间';
comment on column ADMIN.CROSS_BLACK_HIS.TIME_VERIFY
  is '布控审核时间';
comment on column ADMIN.CROSS_BLACK_HIS.IS_VERIFYPASS
  is '审核结果: 0 未审核 1 通过审核 2 删除该布控';
comment on column ADMIN.CROSS_BLACK_HIS.TIME_CANCEL
  is '布控撤销申请时间';
comment on column ADMIN.CROSS_BLACK_HIS.IS_CANCEL
  is '是否已提交布控撤销申请 0 没有提交撤销申请 1已经提交撤销申请';
comment on column ADMIN.CROSS_BLACK_HIS.TIME_CANCEL_VERIFY
  is '布控撤销审核时间';
comment on column ADMIN.CROSS_BLACK_HIS.IS_CANCELPASS
  is '布控撤销审核结果 0 还没有审核 1 代表通过撤销审核 2 代表不通过';
comment on column ADMIN.CROSS_BLACK_HIS.USER_CODE_ENTRY
  is '布控录入人ID';
comment on column ADMIN.CROSS_BLACK_HIS.USER_CODE_VERIFY
  is '布控审核人ID';
comment on column ADMIN.CROSS_BLACK_HIS.USER_CODE_CANCEL
  is '布控撤销人ID';
comment on column ADMIN.CROSS_BLACK_HIS.USER_CODE_CANCEL_VERIFY
  is '撤销审核人ID';
comment on column ADMIN.CROSS_BLACK_HIS.BLACK_TYPE_CODE
  is '布控类型编号';
comment on column ADMIN.CROSS_BLACK_HIS.BLACK_LEVEL_CODE
  is '布控级别编号';
comment on column ADMIN.CROSS_BLACK_HIS.ROAD_CODE
  is '布控地点编号';
comment on column ADMIN.CROSS_BLACK_HIS.IS_ILLEGIBILITY
  is '布控模式 0精确布控 1模糊布控';
comment on column ADMIN.CROSS_BLACK_HIS.IS_SOUNDALERM
  is '是否声音报警';
comment on column ADMIN.CROSS_BLACK_HIS.IS_SMSALERM
  is '是否短信报警';
comment on column ADMIN.CROSS_BLACK_HIS.IS_MUSTOPERATE
  is '是否必填处理结果';
comment on column ADMIN.CROSS_BLACK_HIS.SMS_CONTACTIDS
  is '短信报警时 自动发送到哪些联系人的ID集合';
comment on column ADMIN.CROSS_BLACK_HIS.ORG_ID
  is '部门id';
comment on column ADMIN.CROSS_BLACK_HIS.ORG_NAME
  is '部门名称';
comment on column ADMIN.CROSS_BLACK_HIS.NOTE
  is '备注';
comment on column ADMIN.CROSS_BLACK_HIS.PRECAUTION
  is '预案编号';
comment on column ADMIN.CROSS_BLACK_HIS.SOURCE
  is '布控数据来源 0 手动录入 1 EXCEL导入  2 违法确认进入 ';
comment on column ADMIN.CROSS_BLACK_HIS.CANCELCAUSE_CODE
  is '布控撤销原因编号';
comment on column ADMIN.CROSS_BLACK_HIS.DEVICE_CODE
  is '布控地点';
alter table ADMIN.CROSS_BLACK_HIS
  add constraint PK_CROSSBLACK_HIS_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_BLACK_INFO
prompt ===============================
prompt
create table ADMIN.CROSS_BLACK_INFO
(
  ID                      VARCHAR2(20) not null,
  CAR_PLATE_NUM           VARCHAR2(15) not null,
  CAR_PLATE_COLOR_CODE    VARCHAR2(10),
  CAR_PLATE_TYPE_CODE     VARCHAR2(10),
  CAR_STATE_CODE          VARCHAR2(10),
  CAR_CLASS_CODE          VARCHAR2(10),
  CAR_COLOR_CODE          VARCHAR2(10),
  CAR_TYPE_CODE           VARCHAR2(10),
  CAR_BRAND_CODE          VARCHAR2(10),
  TIME_ADD                DATE not null,
  TIME_START              DATE not null,
  TIME_END                DATE not null,
  TIME_VERIFY             DATE,
  IS_VERIFYPASS           VARCHAR2(2) default 0 not null,
  TIME_CANCEL             DATE,
  IS_CANCEL               VARCHAR2(2) default 0 not null,
  TIME_CANCEL_VERIFY      DATE,
  IS_CANCELPASS           VARCHAR2(2) default 0 not null,
  USER_CODE_ENTRY         VARCHAR2(10) not null,
  USER_CODE_VERIFY        VARCHAR2(10),
  USER_CODE_CANCEL        VARCHAR2(10),
  USER_CODE_CANCEL_VERIFY VARCHAR2(10),
  BLACK_TYPE_CODE         VARCHAR2(10),
  BLACK_LEVEL_CODE        VARCHAR2(10),
  ROAD_CODE               VARCHAR2(1000),
  IS_ILLEGIBILITY         VARCHAR2(2) default 0 not null,
  IS_SOUNDALERM           VARCHAR2(2) default 0,
  IS_SMSALERM             VARCHAR2(2) default 0,
  IS_MUSTOPERATE          VARCHAR2(2) default 0,
  SMS_CONTACTIDS          VARCHAR2(200),
  ORG_ID                  VARCHAR2(200) not null,
  ORG_NAME                VARCHAR2(200) not null,
  NOTE                    VARCHAR2(600),
  PRECAUTION              VARCHAR2(20),
  SOURCE                  VARCHAR2(2) default 0 not null,
  CANCELCAUSE_CODE        VARCHAR2(20),
  DEVICE_CODE             CLOB not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.CROSS_BLACK_INFO.CAR_PLATE_NUM
  is '号牌号码';
comment on column ADMIN.CROSS_BLACK_INFO.CAR_PLATE_COLOR_CODE
  is '号牌颜色编号';
comment on column ADMIN.CROSS_BLACK_INFO.CAR_PLATE_TYPE_CODE
  is '号牌类型编号';
comment on column ADMIN.CROSS_BLACK_INFO.CAR_STATE_CODE
  is '车辆状态编号';
comment on column ADMIN.CROSS_BLACK_INFO.CAR_CLASS_CODE
  is '车辆分类编号';
comment on column ADMIN.CROSS_BLACK_INFO.CAR_COLOR_CODE
  is '车身颜色编号';
comment on column ADMIN.CROSS_BLACK_INFO.CAR_TYPE_CODE
  is '车辆类型编号';
comment on column ADMIN.CROSS_BLACK_INFO.CAR_BRAND_CODE
  is '车辆品牌编号';
comment on column ADMIN.CROSS_BLACK_INFO.TIME_ADD
  is '插入时间';
comment on column ADMIN.CROSS_BLACK_INFO.TIME_START
  is '布控开始时间';
comment on column ADMIN.CROSS_BLACK_INFO.TIME_END
  is '布控结束时间';
comment on column ADMIN.CROSS_BLACK_INFO.TIME_VERIFY
  is '布控审核时间';
comment on column ADMIN.CROSS_BLACK_INFO.IS_VERIFYPASS
  is '审核结果: 0 未审核 1 已审核 2 被驳回';
comment on column ADMIN.CROSS_BLACK_INFO.TIME_CANCEL
  is '布控撤销申请时间';
comment on column ADMIN.CROSS_BLACK_INFO.IS_CANCEL
  is '是否已提交布控撤销申请 0 没有提交撤销申请 1已经提交撤销申请';
comment on column ADMIN.CROSS_BLACK_INFO.TIME_CANCEL_VERIFY
  is '布控撤销审核时间';
comment on column ADMIN.CROSS_BLACK_INFO.IS_CANCELPASS
  is '布控撤销审核结果 0 还没有审核 1 代表通过撤销审核 2 代表不通过';
comment on column ADMIN.CROSS_BLACK_INFO.USER_CODE_ENTRY
  is '布控录入人ID';
comment on column ADMIN.CROSS_BLACK_INFO.USER_CODE_VERIFY
  is '布控审核人ID';
comment on column ADMIN.CROSS_BLACK_INFO.USER_CODE_CANCEL
  is '布控撤销申请人ID';
comment on column ADMIN.CROSS_BLACK_INFO.USER_CODE_CANCEL_VERIFY
  is '撤销审核人ID';
comment on column ADMIN.CROSS_BLACK_INFO.BLACK_TYPE_CODE
  is '布控类型编号';
comment on column ADMIN.CROSS_BLACK_INFO.BLACK_LEVEL_CODE
  is '布控级别编号';
comment on column ADMIN.CROSS_BLACK_INFO.ROAD_CODE
  is '布控地点编号集合';
comment on column ADMIN.CROSS_BLACK_INFO.IS_ILLEGIBILITY
  is '布控模式 0精确布控 1模糊布控';
comment on column ADMIN.CROSS_BLACK_INFO.IS_SOUNDALERM
  is '是否声音报警';
comment on column ADMIN.CROSS_BLACK_INFO.IS_SMSALERM
  is '是否短信报警';
comment on column ADMIN.CROSS_BLACK_INFO.IS_MUSTOPERATE
  is '是否必填处理结果';
comment on column ADMIN.CROSS_BLACK_INFO.SMS_CONTACTIDS
  is '短信报警时 自动发送到哪些联系人的ID集合';
comment on column ADMIN.CROSS_BLACK_INFO.ORG_ID
  is '部门id';
comment on column ADMIN.CROSS_BLACK_INFO.ORG_NAME
  is '部门名称';
comment on column ADMIN.CROSS_BLACK_INFO.NOTE
  is '备注';
comment on column ADMIN.CROSS_BLACK_INFO.PRECAUTION
  is '预案编号';
comment on column ADMIN.CROSS_BLACK_INFO.SOURCE
  is '布控数据来源 0 手动录入 1 EXCEL导入  2 违法确认进入 ';
comment on column ADMIN.CROSS_BLACK_INFO.CANCELCAUSE_CODE
  is '布控撤销原因编号';
comment on column ADMIN.CROSS_BLACK_INFO.DEVICE_CODE
  is '布控设备编号集合';
alter table ADMIN.CROSS_BLACK_INFO
  add constraint PK_CROSSBLACK_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_COUNT_MONTH
prompt ================================
prompt
create table ADMIN.CROSS_COUNT_MONTH
(
  ID          VARCHAR2(20) not null,
  DEVICE_CODE VARCHAR2(20) not null,
  SUMNUM      NUMBER not null,
  AVGSPEED    NUMBER not null,
  COUNTTIME   DATE not null,
  INSERTTIME  DATE not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.CROSS_COUNT_MONTH
  add constraint PK_CROSS_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table CROSS_DEVICE_STATUS
prompt ==================================
prompt
create table ADMIN.CROSS_DEVICE_STATUS
(
  ID          VARCHAR2(20) not null,
  DEVICE_CODE VARCHAR2(20) not null,
  RECORD_ID   VARCHAR2(32) not null,
  RECORDTIME  DATE not null,
  INSERTTIME  DATE not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.CROSS_DEVICE_STATUS
  add constraint PK_DEVICESTATUS_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table CROSS_JOB_EXCEPTION
prompt ==================================
prompt
create table ADMIN.CROSS_JOB_EXCEPTION
(
  ID               VARCHAR2(32) not null,
  JOBNAME          VARCHAR2(50),
  JOBPROCEDURES    VARCHAR2(200),
  TTIME_STAR       DATE,
  TTIME_END        DATE,
  UNITTIME         VARCHAR2(50),
  JOBFORMAT        VARCHAR2(300),
  EXCEPTIONCODE    VARCHAR2(100),
  EXCEPTIONMESSAGE VARCHAR2(500),
  TIME_EXCEPTION   DATE
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.CROSS_JOB_EXCEPTION.JOBNAME
  is 'JOB标识名称';
comment on column ADMIN.CROSS_JOB_EXCEPTION.JOBPROCEDURES
  is 'JOB调用的具体存储过程';
comment on column ADMIN.CROSS_JOB_EXCEPTION.TTIME_STAR
  is '执行开始时间';
comment on column ADMIN.CROSS_JOB_EXCEPTION.TTIME_END
  is '执行结束时间';
comment on column ADMIN.CROSS_JOB_EXCEPTION.UNITTIME
  is 'JOB计划运行间隔时间';
comment on column ADMIN.CROSS_JOB_EXCEPTION.JOBFORMAT
  is 'JOB计划间隔表达式';
comment on column ADMIN.CROSS_JOB_EXCEPTION.EXCEPTIONCODE
  is '异常代码';
comment on column ADMIN.CROSS_JOB_EXCEPTION.EXCEPTIONMESSAGE
  is '异常信息';
comment on column ADMIN.CROSS_JOB_EXCEPTION.TIME_EXCEPTION
  is '异常发生时间';
alter table ADMIN.CROSS_JOB_EXCEPTION
  add constraint PK_CROSSJOB_EXCEPTION primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131220
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131220
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131221
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131221
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131222
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131222
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131223
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131223
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131224
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131224
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131225
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131225
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131226
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131226
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131227
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131227
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131228
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131228
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131229
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131229
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131230
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131230
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20131231
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20131231
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201312
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140101
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140101
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140102
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140102
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140103
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140103
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140104
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140104
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140105
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140105
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140106
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140106
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140107
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140107
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140108
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140108
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140109
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140109
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140110
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140110
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140111
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140111
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140112
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140112
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140113
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140113
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140114
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140114
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140115
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140115
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140116
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140116
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140117
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140117
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140118
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140118
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140119
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140119
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140120
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140120
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140121
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140121
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140122
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140122
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140123
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140123
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140124
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140124
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140125
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140125
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140126
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140126
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140127
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140127
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140128
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140128
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140129
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140129
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140130
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140130
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140131
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140131
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201301
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140201
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140201
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140202
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140202
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140203
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140203
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140204
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140204
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140205
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140205
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140206
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140206
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140207
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140207
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140208
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140208
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140209
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140209
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140210
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140210
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140211
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140211
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140212
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140212
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140213
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140213
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140214
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140214
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140215
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140215
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140216
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140216
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140217
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140217
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140218
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140218
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140219
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140219
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140220
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140220
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140221
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140221
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140222
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140222
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140223
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140223
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140224
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140224
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140225
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140225
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140226
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140226
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140227
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140227
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140228
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140228
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201302
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140301
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140301
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140302
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140302
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140303
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140303
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140304
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140304
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140305
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140305
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140306
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140306
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140307
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140307
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140308
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140308
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140309
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140309
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140310
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140310
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140311
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140311
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140312
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140312
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140313
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140313
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140314
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140314
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140315
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140315
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140316
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140316
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140317
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140317
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140318
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140318
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140319
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140319
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140320
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140320
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140321
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140321
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140322
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140322
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140323
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140323
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140324
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140324
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140325
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140325
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140326
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140326
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140327
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140327
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140328
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140328
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140329
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140329
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140330
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140330
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_20140331
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_20140331
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS_RECORDLIST_T_201303
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORDLIST_MADESELF
prompt ========================================
prompt
create table ADMIN.CROSS_RECORDLIST_MADESELF
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         DATE,
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 768K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.CROSS_RECORDLIST_MADESELF.DEVICE_CODE
  is '设备编号';
comment on column ADMIN.CROSS_RECORDLIST_MADESELF.DIRECTION_CODE
  is '方向编号';
comment on column ADMIN.CROSS_RECORDLIST_MADESELF.CAR_PLATE_FLAG
  is '1表示前牌，2表示后牌，3表示不能确定';
comment on column ADMIN.CROSS_RECORDLIST_MADESELF.CAR_PLATE_COLOR_CODE
  is '号牌颜色编号';
comment on column ADMIN.CROSS_RECORDLIST_MADESELF.CAR_PLATE_TYPE_CODE
  is '号牌类型编号';
comment on column ADMIN.CROSS_RECORDLIST_MADESELF.CAR_CLASS_CODE
  is '车辆分类编号';
comment on column ADMIN.CROSS_RECORDLIST_MADESELF.CAR_COLOR_CODE
  is '车身颜色编号';
comment on column ADMIN.CROSS_RECORDLIST_MADESELF.CAR_TYPE_CODE
  is '车辆类型编号';
comment on column ADMIN.CROSS_RECORDLIST_MADESELF.IMG_PLATE_PATH
  is '号牌图片';
alter table ADMIN.CROSS_RECORDLIST_MADESELF
  add constraint PK_RECORDLIST_MADESELF_ID primary key (ID)
  disable;

prompt
prompt Creating table CROSS_RECORDLIST_TEMP
prompt ====================================
prompt
create table ADMIN.CROSS_RECORDLIST_TEMP
(
  ID                   VARCHAR2(32),
  DEVICE_CODE          VARCHAR2(20),
  DIRECTION_CODE       VARCHAR2(3),
  LANE_CODE            VARCHAR2(2),
  CAR_PLATE_FLAG       VARCHAR2(2),
  CAR_PLATE_NUM        VARCHAR2(20),
  CAR_PLATE_COLOR_CODE VARCHAR2(20),
  CAR_PLATE_TYPE_CODE  VARCHAR2(20),
  CAR_SPEED            NUMBER(3),
  CAR_DATETIME         TIMESTAMP(6),
  CAR_CLASS_CODE       VARCHAR2(20),
  CAR_COLOR_CODE       VARCHAR2(20),
  CAR_TYPE_CODE        VARCHAR2(20),
  CAR_BRAND_CODE       VARCHAR2(20),
  IMG1_PATH            VARCHAR2(500),
  IMG2_PATH            VARCHAR2(500),
  IMG3_PATH            VARCHAR2(500),
  IMG4_PATH            VARCHAR2(500),
  IMG_PLATE_PATH       VARCHAR2(500),
  INSERT_TIME          DATE
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 768K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.CROSS_RECORDLIST_TEMP.DEVICE_CODE
  is '设备编号';
comment on column ADMIN.CROSS_RECORDLIST_TEMP.DIRECTION_CODE
  is '方向编号';
comment on column ADMIN.CROSS_RECORDLIST_TEMP.CAR_PLATE_FLAG
  is '1表示前牌，2表示后牌，3表示不能确定';
comment on column ADMIN.CROSS_RECORDLIST_TEMP.CAR_PLATE_COLOR_CODE
  is '号牌颜色编号';
comment on column ADMIN.CROSS_RECORDLIST_TEMP.CAR_PLATE_TYPE_CODE
  is '号牌类型编号';
comment on column ADMIN.CROSS_RECORDLIST_TEMP.CAR_CLASS_CODE
  is '车辆分类编号';
comment on column ADMIN.CROSS_RECORDLIST_TEMP.CAR_COLOR_CODE
  is '车身颜色编号';
comment on column ADMIN.CROSS_RECORDLIST_TEMP.CAR_TYPE_CODE
  is '车辆类型编号';
comment on column ADMIN.CROSS_RECORDLIST_TEMP.IMG_PLATE_PATH
  is '号牌图片';
alter table ADMIN.CROSS_RECORDLIST_TEMP
  add constraint PK_CRESOSS_RECORDLISTTEMP_ID primary key (ID)
  disable;

prompt
prompt Creating table CROSS_RECORD_SUSPICION_OTHER
prompt ===========================================
prompt
create table ADMIN.CROSS_RECORD_SUSPICION_OTHER
(
  DEVICE_CODE      VARCHAR2(20),
  LIST_ID          CLOB,
  LIST_PLATE       VARCHAR2(10),
  SUSPICION_ID     CLOB,
  SUSPICION_PLATE  VARCHAR2(10),
  SUSPICION_TYPE   NUMBER,
  SUSPICION_COUNT  NUMBER,
  SUSPICION_DATE   VARCHAR2(20),
  SUSPICION_MEMO   VARCHAR2(200),
  ID               VARCHAR2(32) not null,
  SUSPICION_MAX_ID NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 960K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.DEVICE_CODE
  is '设备编号(仅用于徘徊车辆)';
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.LIST_ID
  is '被跟踪车辆ID';
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.LIST_PLATE
  is '被跟踪车辆号牌';
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.SUSPICION_ID
  is '嫌疑车辆ID';
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.SUSPICION_PLATE
  is '嫌疑车辆号牌';
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.SUSPICION_TYPE
  is '0为跟踪、1为徘徊';
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.SUSPICION_COUNT
  is '跟踪、徘徊次数';
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.SUSPICION_DATE
  is '记录日期(仅用于徘徊车辆)';
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.SUSPICION_MEMO
  is '补充说明';
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.ID
  is '主键';
comment on column ADMIN.CROSS_RECORD_SUSPICION_OTHER.SUSPICION_MAX_ID
  is '嫌疑车辆最大ID，仅用于数据入库的判断';
alter table ADMIN.CROSS_RECORD_SUSPICION_OTHER
  add constraint PK_SUSPICION_OTHER_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORD_SUSPICION_TAOPAI
prompt ============================================
prompt
create table ADMIN.CROSS_RECORD_SUSPICION_TAOPAI
(
  ID             VARCHAR2(32) not null,
  PLATE_NUM      VARCHAR2(10),
  NOTE           VARCHAR2(200),
  LIST_ID_A      VARCHAR2(100),
  LIST_ID_B      VARCHAR2(100),
  DEVICE_CODE_A  VARCHAR2(20),
  DEVICE_CODE_B  VARCHAR2(20),
  DEVICE_NAME_A  VARCHAR2(100),
  DEVICE_NAME_B  VARCHAR2(100),
  ROAD_CODE_A    VARCHAR2(20),
  ROAD_CODE_B    VARCHAR2(20),
  ROAD_NAME_A    VARCHAR2(100),
  ROAD_NAME_B    VARCHAR2(100),
  IMG1_A         VARCHAR2(200),
  IMG2_A         VARCHAR2(200),
  IMG3_A         VARCHAR2(200),
  IMG4_A         VARCHAR2(200),
  IMG1_B         VARCHAR2(200),
  IMG2_B         VARCHAR2(200),
  IMG3_B         VARCHAR2(200),
  IMG4_B         VARCHAR2(200),
  CAR_DATETIME_A DATE,
  CAR_DATETIME_B DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 2M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.ID
  is '主键';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.PLATE_NUM
  is '号牌号码';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.NOTE
  is '补充说明';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.LIST_ID_A
  is '车辆ID_A';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.LIST_ID_B
  is '车辆ID_B';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.DEVICE_CODE_A
  is '设备编号A';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.DEVICE_CODE_B
  is '设备编号B';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.DEVICE_NAME_A
  is '设备名称A';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.DEVICE_NAME_B
  is '设备名称B';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.ROAD_CODE_A
  is '道路编号A';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.ROAD_CODE_B
  is '道路编号B';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.ROAD_NAME_A
  is '道路名称A';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.ROAD_NAME_B
  is '道路名称B';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.IMG1_A
  is 'A车辆图片路径';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.IMG1_B
  is 'B车辆图片路径';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.CAR_DATETIME_A
  is 'A车辆通行经过时间';
comment on column ADMIN.CROSS_RECORD_SUSPICION_TAOPAI.CAR_DATETIME_B
  is 'B车辆通行经过时间';
alter table ADMIN.CROSS_RECORD_SUSPICION_TAOPAI
  add constraint PK_SUSPICION_TAOPAI_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CROSS_RECORD_VIP_PASS
prompt ====================================
prompt
create table ADMIN.CROSS_RECORD_VIP_PASS
(
  ID            VARCHAR2(32) not null,
  PLATE_NUM     VARCHAR2(20),
  PLATE_TYPE    VARCHAR2(20),
  DEVICECODE    VARCHAR2(20),
  DIRECTIONCODE VARCHAR2(20),
  RECORDLIST_ID VARCHAR2(32),
  FILTERTYPE    VARCHAR2(2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 50M
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.CROSS_RECORD_VIP_PASS.PLATE_NUM
  is '车牌号';
comment on column ADMIN.CROSS_RECORD_VIP_PASS.PLATE_TYPE
  is '车牌类型';
comment on column ADMIN.CROSS_RECORD_VIP_PASS.DEVICECODE
  is '设备编号';
comment on column ADMIN.CROSS_RECORD_VIP_PASS.DIRECTIONCODE
  is '方向编号';
comment on column ADMIN.CROSS_RECORD_VIP_PASS.RECORDLIST_ID
  is '通行记录ID';
comment on column ADMIN.CROSS_RECORD_VIP_PASS.FILTERTYPE
  is '过滤作用类型';
alter table ADMIN.CROSS_RECORD_VIP_PASS
  add constraint PK_VIPPASS_RECORD primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOWST_LINKS
prompt ===========================
prompt
create table ADMIN.FLOWST_LINKS
(
  LINKID        NUMBER not null,
  ENTERNODEID   NUMBER,
  EXITNODEID    NUMBER,
  LENGTH        NUMBER,
  ROADNAME      VARCHAR2(50),
  DIRECTIONNAME VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.FLOWST_LINKS
  add constraint PK_LINKS primary key (LINKID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOWST_LINKS_POSCONF
prompt ===================================
prompt
create table ADMIN.FLOWST_LINKS_POSCONF
(
  LINKID NUMBER not null,
  POSID  NUMBER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOWST_NODES
prompt ===========================
prompt
create table ADMIN.FLOWST_NODES
(
  NODEID   NUMBER not null,
  NODENAME VARCHAR2(32),
  XPOS     NUMBER,
  YPOS     NUMBER,
  COMMENTS VARCHAR2(256) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.FLOWST_NODES
  add constraint PK_NODES primary key (NODEID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOWST_POSCONF
prompt =============================
prompt
create table ADMIN.FLOWST_POSCONF
(
  ID      NUMBER not null,
  POSID   NUMBER not null,
  POSDESC VARCHAR2(200),
  POSTYPE NUMBER,
  XPOS    NUMBER,
  YPOS    NUMBER,
  UPLANES NUMBER,
  UPDIR   VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOWST_ROADSTATUS
prompt ================================
prompt
create table ADMIN.FLOWST_ROADSTATUS
(
  LINKID      NUMBER,
  MODIFYTIME  DATE,
  CONFIRMTIME DATE,
  CALCULATE   NUMBER,
  CONFIRM     NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOWST_TRAFFINFO
prompt ===============================
prompt
create table ADMIN.FLOWST_TRAFFINFO
(
  ID        NUMBER,
  POSID     NUMBER(5),
  LANENO    NUMBER(2),
  DATATIME  DATE,
  RECTIME   DATE,
  VOLUME    NUMBER(3),
  SPEED     NUMBER(5,2),
  OCCUPANCY NUMBER(5,2),
  VOLLONG   NUMBER(3)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 3584M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOWST_TRAFFINFO.LANENO
  is '第几车道';
comment on column ADMIN.FLOWST_TRAFFINFO.SPEED
  is '平均速度';
comment on column ADMIN.FLOWST_TRAFFINFO.OCCUPANCY
  is '占有率';
comment on column ADMIN.FLOWST_TRAFFINFO.VOLLONG
  is '车长';
create index ADMIN.DATAINDEX on ADMIN.FLOWST_TRAFFINFO (DATATIME)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 2880M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOW_DETECTOR_DATE
prompt =================================
prompt
create table ADMIN.FLOW_DETECTOR_DATE
(
  ID           VARCHAR2(32) not null,
  CODE         VARCHAR2(20) not null,
  FLOWALL      NUMBER,
  FLOWBIG      NUMBER,
  FLOWSMALL    NUMBER,
  OCCUPANCY    NUMBER(5,2),
  SPEED        NUMBER(5,2),
  CARLENGTH    NUMBER,
  INSERTTIME   DATE not null,
  ANALYSTTIME  DATE not null,
  LANE_NUM     VARCHAR2(2),
  DETECTORNAME VARCHAR2(200) not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOW_DETECTOR_DATE.CODE
  is '检测器编号';
comment on column ADMIN.FLOW_DETECTOR_DATE.FLOWALL
  is '总流量';
comment on column ADMIN.FLOW_DETECTOR_DATE.FLOWBIG
  is '大车流量';
comment on column ADMIN.FLOW_DETECTOR_DATE.FLOWSMALL
  is '小车流量';
comment on column ADMIN.FLOW_DETECTOR_DATE.OCCUPANCY
  is '路面占有率';
comment on column ADMIN.FLOW_DETECTOR_DATE.SPEED
  is '平均速度';
comment on column ADMIN.FLOW_DETECTOR_DATE.CARLENGTH
  is '车长';
comment on column ADMIN.FLOW_DETECTOR_DATE.INSERTTIME
  is '插入时间';
comment on column ADMIN.FLOW_DETECTOR_DATE.ANALYSTTIME
  is '分析时间';
comment on column ADMIN.FLOW_DETECTOR_DATE.LANE_NUM
  is '哪个车道';
comment on column ADMIN.FLOW_DETECTOR_DATE.DETECTORNAME
  is '检测器名称';
alter table ADMIN.FLOW_DETECTOR_DATE
  add constraint PK_DETECTORDATE_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOW_DETECTOR_INFO
prompt =================================
prompt
create table ADMIN.FLOW_DETECTOR_INFO
(
  ID             VARCHAR2(20) not null,
  CODE           VARCHAR2(20) not null,
  NAME           VARCHAR2(200) not null,
  TYPE_CODE      VARCHAR2(20) not null,
  DIRECTION_CODE VARCHAR2(20) not null,
  DATECYCLE      NUMBER,
  COM            NUMBER not null,
  LINK_ID        VARCHAR2(20) not null,
  LONGITUDE      VARCHAR2(40),
  LATITUDE       VARCHAR2(40)
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOW_DETECTOR_INFO.CODE
  is '流量设备编码';
comment on column ADMIN.FLOW_DETECTOR_INFO.NAME
  is '流量设备名称标识';
comment on column ADMIN.FLOW_DETECTOR_INFO.TYPE_CODE
  is '流量设备类型,来自数据字典中';
comment on column ADMIN.FLOW_DETECTOR_INFO.DIRECTION_CODE
  is '方向编号,来之数据字典中';
comment on column ADMIN.FLOW_DETECTOR_INFO.DATECYCLE
  is '数据采集周期(秒)';
comment on column ADMIN.FLOW_DETECTOR_INFO.COM
  is '通道号';
comment on column ADMIN.FLOW_DETECTOR_INFO.LINK_ID
  is '所属路段ID';
comment on column ADMIN.FLOW_DETECTOR_INFO.LONGITUDE
  is '经度';
comment on column ADMIN.FLOW_DETECTOR_INFO.LATITUDE
  is '纬度';
alter table ADMIN.FLOW_DETECTOR_INFO
  add constraint PK_FLOWDETECTOR_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOW_EQUIP
prompt =========================
prompt
create table ADMIN.FLOW_EQUIP
(
  NAME               VARCHAR2(100),
  X                  NUMBER,
  Y                  NUMBER,
  PLACE              VARCHAR2(100),
  UNIT               VARCHAR2(100),
  MODLEID            VARCHAR2(100),
  PRODUCECORPORATION VARCHAR2(100),
  ROADS_ID           VARCHAR2(500),
  ID                 VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOW_EQUIP.X
  is 'x坐标';
comment on column ADMIN.FLOW_EQUIP.Y
  is 'Y坐标';
comment on column ADMIN.FLOW_EQUIP.PLACE
  is '地点（xx路口名）';
comment on column ADMIN.FLOW_EQUIP.UNIT
  is '建设单位';
comment on column ADMIN.FLOW_EQUIP.MODLEID
  is '型号';
comment on column ADMIN.FLOW_EQUIP.PRODUCECORPORATION
  is '生产厂商';
comment on column ADMIN.FLOW_EQUIP.ROADS_ID
  is '诱导屏对应路段的ID(1：N)';

prompt
prompt Creating table FLOW_GUIDE_DATE
prompt ==============================
prompt
create table ADMIN.FLOW_GUIDE_DATE
(
  ID           VARCHAR2(20) not null,
  TEXTTYPE     VARCHAR2(200),
  TEXTCONTENT  VARCHAR2(500),
  TEXTCOLOR    VARCHAR2(10),
  TEXTSIZE     VARCHAR2(10),
  SHOWMODE     VARCHAR2(10),
  CUTOVERTIME  VARCHAR2(2) default 5,
  POSITIONMODE VARCHAR2(10)
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255;
comment on column ADMIN.FLOW_GUIDE_DATE.TEXTTYPE
  is '文字信息类型';
comment on column ADMIN.FLOW_GUIDE_DATE.TEXTCONTENT
  is '文字内容';
comment on column ADMIN.FLOW_GUIDE_DATE.TEXTCOLOR
  is '字体颜色';
comment on column ADMIN.FLOW_GUIDE_DATE.TEXTSIZE
  is '字体大小(XX点)';
comment on column ADMIN.FLOW_GUIDE_DATE.SHOWMODE
  is '出字方式';
comment on column ADMIN.FLOW_GUIDE_DATE.CUTOVERTIME
  is '间隔刷新时间，单位秒';
comment on column ADMIN.FLOW_GUIDE_DATE.POSITIONMODE
  is '对齐方式';

prompt
prompt Creating table FLOW_GUIDE_DETECTOR
prompt ==================================
prompt
create table ADMIN.FLOW_GUIDE_DETECTOR
(
  ID            VARCHAR2(32) not null,
  GUIDE_CODE    VARCHAR2(20) not null,
  DETECTOR_CODE VARCHAR2(20) not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOW_GUIDE_DETECTOR.GUIDE_CODE
  is '诱导屏设备编码';
comment on column ADMIN.FLOW_GUIDE_DETECTOR.DETECTOR_CODE
  is '流量检测器设备编码';

prompt
prompt Creating table FLOW_GUIDE_INFO
prompt ==============================
prompt
create table ADMIN.FLOW_GUIDE_INFO
(
  ID           VARCHAR2(20) not null,
  CODE         VARCHAR2(20) not null,
  NAME         VARCHAR2(200) not null,
  LEVELCODE    VARCHAR2(20) not null,
  LONGITUDE    VARCHAR2(40),
  LATITUDE     VARCHAR2(40),
  UPDATETIME   DATE,
  DATE_ID      VARCHAR2(20),
  ROAD_ID      VARCHAR2(20),
  PICNAME      VARCHAR2(20),
  TERMINALCODE VARCHAR2(20)
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOW_GUIDE_INFO.CODE
  is '诱导屏设备编号';
comment on column ADMIN.FLOW_GUIDE_INFO.NAME
  is '诱导屏设备名称';
comment on column ADMIN.FLOW_GUIDE_INFO.LEVELCODE
  is '诱导屏级别编号[1级、2级、3级]';
comment on column ADMIN.FLOW_GUIDE_INFO.LONGITUDE
  is '经度';
comment on column ADMIN.FLOW_GUIDE_INFO.LATITUDE
  is '纬度';
comment on column ADMIN.FLOW_GUIDE_INFO.UPDATETIME
  is '发布数据更新时间';
comment on column ADMIN.FLOW_GUIDE_INFO.DATE_ID
  is '当前显示数据ID';
comment on column ADMIN.FLOW_GUIDE_INFO.ROAD_ID
  is '道路ID';
comment on column ADMIN.FLOW_GUIDE_INFO.TERMINALCODE
  is '关联LED屏编号';
alter table ADMIN.FLOW_GUIDE_INFO
  add constraint PK_GUIDEINFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOW_GUIDE_PIC
prompt =============================
prompt
create table ADMIN.FLOW_GUIDE_PIC
(
  FILENAME      VARCHAR2(200) not null,
  LENGTH        NUMBER default 0 not null,
  MODIFIED_TIME DATE not null,
  CONTENT       BLOB not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOW_GUIDE_PIC.FILENAME
  is '文件名';
comment on column ADMIN.FLOW_GUIDE_PIC.LENGTH
  is '长度';
comment on column ADMIN.FLOW_GUIDE_PIC.MODIFIED_TIME
  is '修改时间';
comment on column ADMIN.FLOW_GUIDE_PIC.CONTENT
  is '图片内容';
alter table ADMIN.FLOW_GUIDE_PIC
  add constraint PK_FLOWGUIDE_PIC primary key (FILENAME)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOW_LINKS_INFO
prompt ==============================
prompt
create table ADMIN.FLOW_LINKS_INFO
(
  ID             VARCHAR2(20) not null,
  NODEID_START   VARCHAR2(20) not null,
  NODEID_END     VARCHAR2(20) not null,
  LENGTH         NUMBER,
  ROADNAME       VARCHAR2(200) not null,
  DIRECTIONNAME  VARCHAR2(20) not null,
  NODENAME_START VARCHAR2(200) not null,
  NODENAME_END   VARCHAR2(200) not null,
  CODE           NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOW_LINKS_INFO.LENGTH
  is '长度';
comment on column ADMIN.FLOW_LINKS_INFO.ROADNAME
  is '路段名称';
comment on column ADMIN.FLOW_LINKS_INFO.DIRECTIONNAME
  is '路段方向';
comment on column ADMIN.FLOW_LINKS_INFO.NODENAME_START
  is '开始节点名称';
comment on column ADMIN.FLOW_LINKS_INFO.NODENAME_END
  is '结束节点名称';
comment on column ADMIN.FLOW_LINKS_INFO.CODE
  is '路段编号';
alter table ADMIN.FLOW_LINKS_INFO
  add constraint PK_FLOWLINKS_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOW_LINKS_STATUS
prompt ================================
prompt
create table ADMIN.FLOW_LINKS_STATUS
(
  ID            VARCHAR2(32) not null,
  MODIFYTIME    DATE not null,
  STATUSVALUE   NUMBER not null,
  LINKS_ID      VARCHAR2(20) not null,
  CONFIRMTIME   DATE,
  CONFIRMSTATUS VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOW_LINKS_STATUS.MODIFYTIME
  is '检测值刷新时间';
comment on column ADMIN.FLOW_LINKS_STATUS.STATUSVALUE
  is '设备检测值(1-100)';
comment on column ADMIN.FLOW_LINKS_STATUS.LINKS_ID
  is '路段CODE';
comment on column ADMIN.FLOW_LINKS_STATUS.CONFIRMTIME
  is '人工确认时间';
comment on column ADMIN.FLOW_LINKS_STATUS.CONFIRMSTATUS
  is '人工确认路况';
alter table ADMIN.FLOW_LINKS_STATUS
  add constraint PK_FLOWROADSTATUS_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FLOW_LINKS_STATUSHIS
prompt ===================================
prompt
create table ADMIN.FLOW_LINKS_STATUSHIS
(
  ID            VARCHAR2(20) not null,
  LINKS_ID      VARCHAR2(20) not null,
  CALCULATETIME DATE not null,
  CALCULATE     NUMBER not null,
  CONFIRMTIME   DATE,
  CONFIRM       NUMBER,
  STATUS        NUMBER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOW_LINKS_STATUSHIS.LINKS_ID
  is '数据对应路段的ID';
comment on column ADMIN.FLOW_LINKS_STATUSHIS.CALCULATETIME
  is '自动检测时间';
comment on column ADMIN.FLOW_LINKS_STATUSHIS.CALCULATE
  is '检测值(0--25：拥堵、25--60：缓慢、60--100：畅通)';
comment on column ADMIN.FLOW_LINKS_STATUSHIS.CONFIRMTIME
  is '人工确认时间';
comment on column ADMIN.FLOW_LINKS_STATUSHIS.CONFIRM
  is '确认值(-1：没有确认、0拥堵、50：缓慢、100：畅通)';
comment on column ADMIN.FLOW_LINKS_STATUSHIS.STATUS
  is '最终值';
alter table ADMIN.FLOW_LINKS_STATUSHIS
  add constraint PK_FLOWHISTORY_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table FLOW_NODES_INFO
prompt ==============================
prompt
create table ADMIN.FLOW_NODES_INFO
(
  ID       VARCHAR2(20) not null,
  NODENAME VARCHAR2(200) not null,
  XPOS     NUMBER not null,
  YPOS     NUMBER not null,
  COMMENTS VARCHAR2(256),
  CODE     NUMBER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.FLOW_NODES_INFO.NODENAME
  is '路网节点名称';
comment on column ADMIN.FLOW_NODES_INFO.COMMENTS
  is '备注';
comment on column ADMIN.FLOW_NODES_INFO.CODE
  is '路网节点编号';
alter table ADMIN.FLOW_NODES_INFO
  add constraint PK_FLOWNODES_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_ADVISETEMPLET_INFO
prompt ========================================
prompt
create table ADMIN.OCCUPY_ADVISETEMPLET_INFO
(
  ID      VARCHAR2(20) not null,
  CODE    VARCHAR2(20) not null,
  CONTENT VARCHAR2(300) not null,
  NOTE    VARCHAR2(600)
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_ADVISETEMPLET_INFO.CODE
  is '编码';
comment on column ADMIN.OCCUPY_ADVISETEMPLET_INFO.CONTENT
  is '内容';
comment on column ADMIN.OCCUPY_ADVISETEMPLET_INFO.NOTE
  is '备注';
alter table ADMIN.OCCUPY_ADVISETEMPLET_INFO
  add constraint PK_ADVISETEMPLTE_INFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_APPLY_INFO
prompt ================================
prompt
create table ADMIN.OCCUPY_APPLY_INFO
(
  ID                       VARCHAR2(20) not null,
  APPLICANT                VARCHAR2(50) not null,
  APPLY_TIME               DATE not null,
  BUILDER_ID               VARCHAR2(50) not null,
  TYPE                     VARCHAR2(10) not null,
  APPLY_STARTTIME          DATE not null,
  APPLY_ENDTIME            DATE not null,
  LENGTHS                  VARCHAR2(100) not null,
  WIDTHS                   VARCHAR2(100) not null,
  ROAD_STYLE               VARCHAR2(100) not null,
  APPLY_CODE               VARCHAR2(20),
  CONTACTS                 VARCHAR2(20) not null,
  COMM_ADDRESS             VARCHAR2(200) not null,
  PHONE                    VARCHAR2(20) not null,
  PROPORTION               VARCHAR2(100) not null,
  APPROVE_STARTTIME        DATE not null,
  APPROVE_ENDTIME          DATE not null,
  GREENDEPT_SUGGESTION     VARCHAR2(200) not null,
  GREENDEPT_LEADER         VARCHAR2(50) not null,
  TRAFFICPOLICE_SUGGESTION VARCHAR2(200) not null,
  TRAFFICPOLICE_LEADER     VARCHAR2(50) not null,
  CONSTRUCT_MODE           VARCHAR2(10) not null,
  IS_CROSSROADS            VARCHAR2(1) default 0 not null,
  IS_NEARBYHAS             VARCHAR2(1) default 0 not null,
  IS_BLOCKING              VARCHAR2(1) default 0 not null,
  IS_MAINROAD              VARCHAR2(1) default 0 not null,
  ISPROFESSION             VARCHAR2(1) default 0,
  ISREMIND                 VARCHAR2(1) default 0,
  ATTACH_PATH              VARCHAR2(1000),
  PIC_PATH                 VARCHAR2(1000),
  LONGITUDE                VARCHAR2(100) not null,
  LATITUDE                 VARCHAR2(100) not null,
  ICON_TYPE                VARCHAR2(1) default 0 not null,
  STATUS                   VARCHAR2(1) default 0,
  PROJECT_NAME             VARCHAR2(100) not null,
  PROJECT_CODE             VARCHAR2(30) not null,
  ROAD_ID                  VARCHAR2(20) not null,
  ROAD_TYPE                VARCHAR2(2) not null,
  ROAD_ORG                 VARCHAR2(20) not null,
  IS_DELAY                 VARCHAR2(1) default 0,
  MUNICIPAL_SUGGESTION     VARCHAR2(200),
  MUNICIPAL_LEADER         VARCHAR2(50),
  PLANNING_SUGGESTION      VARCHAR2(200),
  PLANNING_LEADER          VARCHAR2(50)
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_APPLY_INFO.APPLICANT
  is '申请单位';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPLY_TIME
  is '申请时间';
comment on column ADMIN.OCCUPY_APPLY_INFO.BUILDER_ID
  is '施工单位';
comment on column ADMIN.OCCUPY_APPLY_INFO.TYPE
  is '施工类别';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPLY_STARTTIME
  is '申请起始时间';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPLY_ENDTIME
  is '申请截止时间';
comment on column ADMIN.OCCUPY_APPLY_INFO.LENGTHS
  is '挖占长度';
comment on column ADMIN.OCCUPY_APPLY_INFO.WIDTHS
  is '挖占宽度';
comment on column ADMIN.OCCUPY_APPLY_INFO.ROAD_STYLE
  is '占用道路类型';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPLY_CODE
  is '编号';
comment on column ADMIN.OCCUPY_APPLY_INFO.CONTACTS
  is '申请单位联系人';
comment on column ADMIN.OCCUPY_APPLY_INFO.COMM_ADDRESS
  is '申请单位通讯地址';
comment on column ADMIN.OCCUPY_APPLY_INFO.PHONE
  is '申请单位联系电话';
comment on column ADMIN.OCCUPY_APPLY_INFO.PROPORTION
  is '挖占面积';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPROVE_STARTTIME
  is '批准起始时间';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPROVE_ENDTIME
  is '批准截止时间';
comment on column ADMIN.OCCUPY_APPLY_INFO.GREENDEPT_SUGGESTION
  is '绿化部门意见';
comment on column ADMIN.OCCUPY_APPLY_INFO.GREENDEPT_LEADER
  is '绿化部门领导';
comment on column ADMIN.OCCUPY_APPLY_INFO.TRAFFICPOLICE_SUGGESTION
  is '公安交警意见';
comment on column ADMIN.OCCUPY_APPLY_INFO.TRAFFICPOLICE_LEADER
  is '公安交警领导';
comment on column ADMIN.OCCUPY_APPLY_INFO.CONSTRUCT_MODE
  is '施工方式';
comment on column ADMIN.OCCUPY_APPLY_INFO.IS_CROSSROADS
  is '是否在十字路口';
comment on column ADMIN.OCCUPY_APPLY_INFO.IS_NEARBYHAS
  is '附近是否有挖占（1000米以内）';
comment on column ADMIN.OCCUPY_APPLY_INFO.IS_BLOCKING
  is '是否属于易堵地段';
comment on column ADMIN.OCCUPY_APPLY_INFO.IS_MAINROAD
  is '是否在主干道';
comment on column ADMIN.OCCUPY_APPLY_INFO.ISPROFESSION
  is '是否专项挖占';
comment on column ADMIN.OCCUPY_APPLY_INFO.ISREMIND
  is '是否到期提醒';
comment on column ADMIN.OCCUPY_APPLY_INFO.ATTACH_PATH
  is '附件保存路径';
comment on column ADMIN.OCCUPY_APPLY_INFO.PIC_PATH
  is '图片上传路径';
comment on column ADMIN.OCCUPY_APPLY_INFO.LONGITUDE
  is '挖占点经度';
comment on column ADMIN.OCCUPY_APPLY_INFO.LATITUDE
  is '挖占点纬度';
comment on column ADMIN.OCCUPY_APPLY_INFO.ICON_TYPE
  is '挖占图标类型（0：一般挖占；1：重点挖占。）';
comment on column ADMIN.OCCUPY_APPLY_INFO.STATUS
  is '挖占状态（0：未开工；1：正在施工；2：快到期；3：延期；4：已完工）';
comment on column ADMIN.OCCUPY_APPLY_INFO.PROJECT_NAME
  is '挖占项目名称';
comment on column ADMIN.OCCUPY_APPLY_INFO.PROJECT_CODE
  is '挖占项目编号';
comment on column ADMIN.OCCUPY_APPLY_INFO.ROAD_ID
  is '挖占点所属道路';
comment on column ADMIN.OCCUPY_APPLY_INFO.ROAD_TYPE
  is '挖占道路类型';
comment on column ADMIN.OCCUPY_APPLY_INFO.ROAD_ORG
  is '道路所属部门';
comment on column ADMIN.OCCUPY_APPLY_INFO.IS_DELAY
  is '是否申请延期';
comment on column ADMIN.OCCUPY_APPLY_INFO.MUNICIPAL_SUGGESTION
  is '市政部门意见';
comment on column ADMIN.OCCUPY_APPLY_INFO.MUNICIPAL_LEADER
  is '市政部门领导';
comment on column ADMIN.OCCUPY_APPLY_INFO.PLANNING_SUGGESTION
  is '规划部门意见';
comment on column ADMIN.OCCUPY_APPLY_INFO.PLANNING_LEADER
  is '规划部门领导';
alter table ADMIN.OCCUPY_APPLY_INFO
  add constraint PK_APPLY_INFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_BLACK_MANAGE
prompt ==================================
prompt
create table ADMIN.OCCUPY_BLACK_MANAGE
(
  ID            VARCHAR2(20) not null,
  BUILDER_ID    VARCHAR2(20) not null,
  SCORE         NUMBER not null,
  NOTE          VARCHAR2(600),
  ISCANCEL      NUMBER default 0,
  CANCEL_REASON VARCHAR2(200),
  OPERATOR_ID   VARCHAR2(32),
  CANCEL_TIME   DATE
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_BLACK_MANAGE.BUILDER_ID
  is '施工单位';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.SCORE
  is '考核分数';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.NOTE
  is '备注信息';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.ISCANCEL
  is '是否撤销';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.CANCEL_REASON
  is '撤销原因';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.OPERATOR_ID
  is '操作人员';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.CANCEL_TIME
  is '撤销时间';
alter table ADMIN.OCCUPY_BLACK_MANAGE
  add constraint PK_BLACK_MANAGE_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_BUILDER_INFO
prompt ==================================
prompt
create table ADMIN.OCCUPY_BUILDER_INFO
(
  ID                      VARCHAR2(20) not null,
  NAME                    VARCHAR2(100) not null,
  PROPERTY                VARCHAR2(50) not null,
  LEADING_OFFICIAL        VARCHAR2(30) not null,
  COMM_ADDRESS            VARCHAR2(200) not null,
  PHONE                   VARCHAR2(20) not null,
  INSERT_TIME             DATE,
  NOTE                    VARCHAR2(600),
  OPERATOR_ID             VARCHAR2(32) not null,
  LEVEL_CODE              VARCHAR2(10) default 1,
  DISABLED                VARCHAR2(1) default 0,
  ISBLACK                 VARCHAR2(1) default 0,
  LEADING_OFFICIAL_MOBILE VARCHAR2(20)
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_BUILDER_INFO.NAME
  is '施工单位名称';
comment on column ADMIN.OCCUPY_BUILDER_INFO.PROPERTY
  is '施工单位性质';
comment on column ADMIN.OCCUPY_BUILDER_INFO.LEADING_OFFICIAL
  is '负责人';
comment on column ADMIN.OCCUPY_BUILDER_INFO.COMM_ADDRESS
  is '通讯地址';
comment on column ADMIN.OCCUPY_BUILDER_INFO.PHONE
  is '联系电话';
comment on column ADMIN.OCCUPY_BUILDER_INFO.INSERT_TIME
  is '建档时间';
comment on column ADMIN.OCCUPY_BUILDER_INFO.NOTE
  is '备注信息';
comment on column ADMIN.OCCUPY_BUILDER_INFO.OPERATOR_ID
  is '操作人员';
comment on column ADMIN.OCCUPY_BUILDER_INFO.LEVEL_CODE
  is '信用级别';
comment on column ADMIN.OCCUPY_BUILDER_INFO.DISABLED
  is '是否显示';
comment on column ADMIN.OCCUPY_BUILDER_INFO.ISBLACK
  is '是否黑名单';
comment on column ADMIN.OCCUPY_BUILDER_INFO.LEADING_OFFICIAL_MOBILE
  is '负责人电话';
alter table ADMIN.OCCUPY_BUILDER_INFO
  add constraint PK_BUILDER_INFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_DELAY_INFO
prompt ================================
prompt
create table ADMIN.OCCUPY_DELAY_INFO
(
  ID                       VARCHAR2(20) not null,
  PID                      VARCHAR2(20) not null,
  PROJECT_CODE             VARCHAR2(30) not null,
  APPLY_TIME               DATE not null,
  OPERATOR                 VARCHAR2(32) not null,
  REASON                   VARCHAR2(200) not null,
  APPLY_STARTTIME          DATE not null,
  APPLY_ENDTIME            DATE not null,
  GREENDEPT_SUGGESTION     VARCHAR2(200) not null,
  GREENDEPT_LEADER         VARCHAR2(50) not null,
  TRAFFICPOLICE_SUGGESTION VARCHAR2(200) not null,
  TRAFFICPOLICE_LEADER     VARCHAR2(50) not null,
  APPROVE_STARTTIME        DATE not null,
  APPROVE_ENDTIME          DATE not null,
  ATTACH_PATH              VARCHAR2(1000)
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_DELAY_INFO.PID
  is '项目id';
comment on column ADMIN.OCCUPY_DELAY_INFO.PROJECT_CODE
  is '项目编号';
comment on column ADMIN.OCCUPY_DELAY_INFO.APPLY_TIME
  is '申请时间';
comment on column ADMIN.OCCUPY_DELAY_INFO.OPERATOR
  is '申请人';
comment on column ADMIN.OCCUPY_DELAY_INFO.REASON
  is '延期理由';
comment on column ADMIN.OCCUPY_DELAY_INFO.APPLY_STARTTIME
  is '申请起始时间';
comment on column ADMIN.OCCUPY_DELAY_INFO.APPLY_ENDTIME
  is '申请截止时间';
comment on column ADMIN.OCCUPY_DELAY_INFO.GREENDEPT_SUGGESTION
  is '绿化部门意见';
comment on column ADMIN.OCCUPY_DELAY_INFO.GREENDEPT_LEADER
  is '绿化部门领导';
comment on column ADMIN.OCCUPY_DELAY_INFO.TRAFFICPOLICE_SUGGESTION
  is '公安交警意见';
comment on column ADMIN.OCCUPY_DELAY_INFO.TRAFFICPOLICE_LEADER
  is '公安交警领导';
comment on column ADMIN.OCCUPY_DELAY_INFO.APPROVE_STARTTIME
  is '批准起始时间';
comment on column ADMIN.OCCUPY_DELAY_INFO.APPROVE_ENDTIME
  is '批准截止时间';
comment on column ADMIN.OCCUPY_DELAY_INFO.ATTACH_PATH
  is '附件保存路径';
alter table ADMIN.OCCUPY_DELAY_INFO
  add constraint PK_OCCUPY_APPLYDELAY_INFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_ICON_INFO
prompt ===============================
prompt
create table ADMIN.OCCUPY_ICON_INFO
(
  ID          VARCHAR2(20) not null,
  TYPE        VARCHAR2(2) not null,
  STATUS      VARCHAR2(2) not null,
  OPERATOR    VARCHAR2(32) not null,
  PATH        VARCHAR2(200) not null,
  UPLOAD_TIME DATE not null,
  CODE        VARCHAR2(4) not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_ICON_INFO.TYPE
  is '挖占类型';
comment on column ADMIN.OCCUPY_ICON_INFO.STATUS
  is '挖占状态';
comment on column ADMIN.OCCUPY_ICON_INFO.OPERATOR
  is '操作人员';
comment on column ADMIN.OCCUPY_ICON_INFO.PATH
  is '图标路径';
comment on column ADMIN.OCCUPY_ICON_INFO.UPLOAD_TIME
  is '上传时间';
comment on column ADMIN.OCCUPY_ICON_INFO.CODE
  is '图标编码';
alter table ADMIN.OCCUPY_ICON_INFO
  add constraint PK_ICON_INFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_INSPECT_INFO
prompt ==================================
prompt
create table ADMIN.OCCUPY_INSPECT_INFO
(
  ID           VARCHAR2(20) not null,
  PROJECT_CODE VARCHAR2(20) not null,
  BUILDER_ID   VARCHAR2(20) not null,
  ROAD_ID      VARCHAR2(20),
  OCCUPY_TYPE  VARCHAR2(2) not null,
  INSPECTOR    VARCHAR2(20) not null,
  INSPECT_TIME DATE not null,
  ILLUSTRATION VARCHAR2(600) not null,
  PIC_TYPE     VARCHAR2(2) not null,
  PIC_PATH     VARCHAR2(1000) not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_INSPECT_INFO.PROJECT_CODE
  is '项目编号';
comment on column ADMIN.OCCUPY_INSPECT_INFO.BUILDER_ID
  is '施工单位';
comment on column ADMIN.OCCUPY_INSPECT_INFO.ROAD_ID
  is '挖占道路';
comment on column ADMIN.OCCUPY_INSPECT_INFO.OCCUPY_TYPE
  is '挖占类型';
comment on column ADMIN.OCCUPY_INSPECT_INFO.INSPECTOR
  is '检查人';
comment on column ADMIN.OCCUPY_INSPECT_INFO.INSPECT_TIME
  is '检查时间';
comment on column ADMIN.OCCUPY_INSPECT_INFO.ILLUSTRATION
  is '检查说明';
comment on column ADMIN.OCCUPY_INSPECT_INFO.PIC_TYPE
  is '图片类型';
comment on column ADMIN.OCCUPY_INSPECT_INFO.PIC_PATH
  is '图片路径';
alter table ADMIN.OCCUPY_INSPECT_INFO
  add constraint PK_INSPECT_INFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_LEVEL_INFO
prompt ================================
prompt
create table ADMIN.OCCUPY_LEVEL_INFO
(
  ID          VARCHAR2(20) not null,
  SCORE_END   NUMBER not null,
  NOTE        VARCHAR2(600),
  SCORE_START NUMBER not null,
  CODE        VARCHAR2(10) not null,
  LEVELS      VARCHAR2(20) not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_LEVEL_INFO.SCORE_END
  is '评分终止区间';
comment on column ADMIN.OCCUPY_LEVEL_INFO.NOTE
  is '备注信息';
comment on column ADMIN.OCCUPY_LEVEL_INFO.SCORE_START
  is '评分起始区间';
comment on column ADMIN.OCCUPY_LEVEL_INFO.CODE
  is '等级编码';
comment on column ADMIN.OCCUPY_LEVEL_INFO.LEVELS
  is '信用等级';
alter table ADMIN.OCCUPY_LEVEL_INFO
  add constraint PK_CREDIT_INFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_PICTURE_INFO
prompt ==================================
prompt
create table ADMIN.OCCUPY_PICTURE_INFO
(
  ID          VARCHAR2(20) not null,
  PID         VARCHAR2(20) not null,
  TYPE        VARCHAR2(2) not null,
  PATH        VARCHAR2(200) not null,
  OPERATOR    VARCHAR2(32) not null,
  UPLOAD_TIME DATE not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_PICTURE_INFO.PID
  is '父id';
comment on column ADMIN.OCCUPY_PICTURE_INFO.TYPE
  is '图片类别';
comment on column ADMIN.OCCUPY_PICTURE_INFO.PATH
  is '保存路径';
comment on column ADMIN.OCCUPY_PICTURE_INFO.OPERATOR
  is '上传者';
comment on column ADMIN.OCCUPY_PICTURE_INFO.UPLOAD_TIME
  is '上传时间';
alter table ADMIN.OCCUPY_PICTURE_INFO
  add constraint PK_PICTURE_INFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_RULER_INFO
prompt ================================
prompt
create table ADMIN.OCCUPY_RULER_INFO
(
  ID      VARCHAR2(20) not null,
  NAME    VARCHAR2(100) not null,
  NOTE    VARCHAR2(600),
  CODE    VARCHAR2(10) not null,
  CONTENT VARCHAR2(600) not null,
  SCORE   NUMBER not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_RULER_INFO.NAME
  is '扣分规则概述';
comment on column ADMIN.OCCUPY_RULER_INFO.NOTE
  is '备注';
comment on column ADMIN.OCCUPY_RULER_INFO.CODE
  is '规则编码';
comment on column ADMIN.OCCUPY_RULER_INFO.CONTENT
  is '扣分规则详细';
comment on column ADMIN.OCCUPY_RULER_INFO.SCORE
  is '分值';
alter table ADMIN.OCCUPY_RULER_INFO
  add constraint PK_SUBTRACT_SCORE_INFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_SCORE_MANAGE
prompt ==================================
prompt
create table ADMIN.OCCUPY_SCORE_MANAGE
(
  BUILDER_ID   VARCHAR2(20) not null,
  SCORE        NUMBER default 100,
  UPDATETIME   DATE,
  OPERATOR_ID  VARCHAR2(32) not null,
  ID           VARCHAR2(20) not null,
  RULER_CODE   VARCHAR2(10),
  DEDUCT_SCORE NUMBER,
  LEVEL_CODE   VARCHAR2(10) default 1,
  ISLATEST     VARCHAR2(1) default 0,
  DISABLED     VARCHAR2(1) default 0,
  ISBLACK      VARCHAR2(1) default 0
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_SCORE_MANAGE.BUILDER_ID
  is '施工单位';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.SCORE
  is '当前分数';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.UPDATETIME
  is '操作时间';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.OPERATOR_ID
  is '操作人员';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.RULER_CODE
  is '违反规则编码';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.DEDUCT_SCORE
  is '扣除分数';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.LEVEL_CODE
  is '信用等级';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.ISLATEST
  is '是否最新记录';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.DISABLED
  is '是否显示';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.ISBLACK
  is '是否黑名单';
alter table ADMIN.OCCUPY_SCORE_MANAGE
  add constraint PK_SCORE_MANAGE_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OCCUPY_SPECIAL_INFO
prompt ==================================
prompt
create table ADMIN.OCCUPY_SPECIAL_INFO
(
  ID   VARCHAR2(20) not null,
  CODE VARCHAR2(20) not null,
  NAME VARCHAR2(100) not null,
  NOTE VARCHAR2(600)
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OCCUPY_SPECIAL_INFO.CODE
  is '编号';
comment on column ADMIN.OCCUPY_SPECIAL_INFO.NAME
  is '名称';
comment on column ADMIN.OCCUPY_SPECIAL_INFO.NOTE
  is '备注';
alter table ADMIN.OCCUPY_SPECIAL_INFO
  add constraint PK_SPECIAL_INFO_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table TRAFFICONTROL_AREA_INFO
prompt ======================================
prompt
create table ADMIN.TRAFFICONTROL_AREA_INFO
(
  ID        VARCHAR2(20) not null,
  NAME      VARCHAR2(50) not null,
  ROAD_ID   VARCHAR2(1000),
  STARTDATE DATE,
  NOTE      VARCHAR2(600),
  ENDDATE   DATE,
  STOPTIME  VARCHAR2(100),
  IS_ALLDAY VARCHAR2(1) default 0
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.TRAFFICONTROL_AREA_INFO.NAME
  is '名称';
comment on column ADMIN.TRAFFICONTROL_AREA_INFO.ROAD_ID
  is '道路';
comment on column ADMIN.TRAFFICONTROL_AREA_INFO.STARTDATE
  is '开始日期：yyyy-MM-dd';
comment on column ADMIN.TRAFFICONTROL_AREA_INFO.NOTE
  is '备注';
comment on column ADMIN.TRAFFICONTROL_AREA_INFO.ENDDATE
  is '终止日期：yyyy-MM-dd';
comment on column ADMIN.TRAFFICONTROL_AREA_INFO.STOPTIME
  is '管制时间：HH：mm';
comment on column ADMIN.TRAFFICONTROL_AREA_INFO.IS_ALLDAY
  is '是否全天';
alter table ADMIN.TRAFFICONTROL_AREA_INFO
  add constraint PK_TRAFFICONTROL_AREA_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table TRAFFICONTROL_SINGLE_INFO
prompt ========================================
prompt
create table ADMIN.TRAFFICONTROL_SINGLE_INFO
(
  ID        VARCHAR2(20) not null,
  NAME      VARCHAR2(50) not null,
  ROAD_ID   VARCHAR2(1000) not null,
  DIRECTION VARCHAR2(2) not null,
  STARTDATE DATE,
  NOTE      VARCHAR2(600),
  ENDDATE   DATE,
  STOPTIME  VARCHAR2(100),
  IS_ALLDAY VARCHAR2(1) default 0
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.TRAFFICONTROL_SINGLE_INFO.NAME
  is '名称';
comment on column ADMIN.TRAFFICONTROL_SINGLE_INFO.ROAD_ID
  is '道路';
comment on column ADMIN.TRAFFICONTROL_SINGLE_INFO.DIRECTION
  is '方向';
comment on column ADMIN.TRAFFICONTROL_SINGLE_INFO.STARTDATE
  is '开始日期：yyyy-MM-dd';
comment on column ADMIN.TRAFFICONTROL_SINGLE_INFO.NOTE
  is '备注';
comment on column ADMIN.TRAFFICONTROL_SINGLE_INFO.ENDDATE
  is '终止日期：yyyy-MM-dd';
comment on column ADMIN.TRAFFICONTROL_SINGLE_INFO.STOPTIME
  is '管制时间：HH：mm';
comment on column ADMIN.TRAFFICONTROL_SINGLE_INFO.IS_ALLDAY
  is '是否全天';
alter table ADMIN.TRAFFICONTROL_SINGLE_INFO
  add constraint PK_TRAFFICONTROL_SINGLE_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table TRAFFICONTROL_TEMP_INFO
prompt ======================================
prompt
create table ADMIN.TRAFFICONTROL_TEMP_INFO
(
  ID        VARCHAR2(20) not null,
  NAME      VARCHAR2(50) not null,
  ROAD_ID   VARCHAR2(1000) not null,
  DIRECTION VARCHAR2(2) not null,
  STARTDATE DATE,
  NOTE      VARCHAR2(600),
  ENDDATE   DATE,
  STOPTIME  VARCHAR2(100),
  IS_ALLDAY VARCHAR2(1) default 0
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.TRAFFICONTROL_TEMP_INFO.NAME
  is '名称';
comment on column ADMIN.TRAFFICONTROL_TEMP_INFO.ROAD_ID
  is '道路';
comment on column ADMIN.TRAFFICONTROL_TEMP_INFO.DIRECTION
  is '方向';
comment on column ADMIN.TRAFFICONTROL_TEMP_INFO.STARTDATE
  is '开始日期：yyyy-MM-dd';
comment on column ADMIN.TRAFFICONTROL_TEMP_INFO.NOTE
  is '备注';
comment on column ADMIN.TRAFFICONTROL_TEMP_INFO.ENDDATE
  is '终止日期：yyyy-MM-dd';
comment on column ADMIN.TRAFFICONTROL_TEMP_INFO.STOPTIME
  is '管制时间：HH：mm';
comment on column ADMIN.TRAFFICONTROL_TEMP_INFO.IS_ALLDAY
  is '是否全天';
alter table ADMIN.TRAFFICONTROL_TEMP_INFO
  add constraint PK_TRAFFICONTROL_TEMP_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table VIO_AREA
prompt =======================
prompt
create table ADMIN.VIO_AREA
(
  ID           VARCHAR2(32) not null,
  CODE         VARCHAR2(50) not null,
  NAME         VARCHAR2(150) not null,
  START_POINT  VARCHAR2(32) not null,
  END_POINT    VARCHAR2(32) not null,
  START_DIRECT VARCHAR2(50) not null,
  END_DIRECT   VARCHAR2(50) not null,
  SM_SPEED     INTEGER not null,
  BG_SPEED     INTEGER not null,
  DISTANCE     FLOAT not null,
  SM_TIME      VARCHAR2(20),
  BG_TIME      VARCHAR2(20),
  REMARK       VARCHAR2(600),
  MATCH_LEVEL  VARCHAR2(1),
  SM_SNAP      VARCHAR2(50),
  BG_SNAP      VARCHAR2(50)
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.VIO_AREA.CODE
  is '区间编码';
comment on column ADMIN.VIO_AREA.NAME
  is '区间名称';
comment on column ADMIN.VIO_AREA.START_POINT
  is '起点设备编码';
comment on column ADMIN.VIO_AREA.END_POINT
  is '终点设备编码';
comment on column ADMIN.VIO_AREA.START_DIRECT
  is '起点方向';
comment on column ADMIN.VIO_AREA.END_DIRECT
  is '终点方向';
comment on column ADMIN.VIO_AREA.SM_SPEED
  is '小车限速值(公里/时)';
comment on column ADMIN.VIO_AREA.BG_SPEED
  is '大车限速值(公里/时)';
comment on column ADMIN.VIO_AREA.DISTANCE
  is '距离';
comment on column ADMIN.VIO_AREA.SM_TIME
  is '小车最小时间(分钟) ';
comment on column ADMIN.VIO_AREA.BG_TIME
  is '大车最小时间(分钟) ';
comment on column ADMIN.VIO_AREA.REMARK
  is '备注';
comment on column ADMIN.VIO_AREA.MATCH_LEVEL
  is '号牌匹配级别      1 全字匹配   2 匹配数字和字母   3 匹配5位及以上的数字即可';
comment on column ADMIN.VIO_AREA.SM_SNAP
  is '小车抓拍值(km/h)';
comment on column ADMIN.VIO_AREA.BG_SNAP
  is '大车抓拍值(km/h)';
alter table ADMIN.VIO_AREA
  add constraint VIO_AREA_PK primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table VIO_CAR_YELLOW
prompt =============================
prompt
create table ADMIN.VIO_CAR_YELLOW
(
  ID            VARCHAR2(32) not null,
  CARPLATENUM   VARCHAR2(20),
  CARPLATECOLOR VARCHAR2(10),
  TYPE          VARCHAR2(10),
  ENDTIME       DATE,
  CREATETIME    DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.VIO_CAR_YELLOW
  is '黄标车表';
comment on column ADMIN.VIO_CAR_YELLOW.CARPLATENUM
  is '车牌号码';
comment on column ADMIN.VIO_CAR_YELLOW.CARPLATECOLOR
  is '车牌颜色';
comment on column ADMIN.VIO_CAR_YELLOW.TYPE
  is '标志类型';
comment on column ADMIN.VIO_CAR_YELLOW.ENDTIME
  is '有效期至';
comment on column ADMIN.VIO_CAR_YELLOW.CREATETIME
  is '核发日期';
alter table ADMIN.VIO_CAR_YELLOW
  add constraint ID_PRIMARY primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table VIO_LIMIT_ROADDEVICE_RELATION
prompt ============================================
prompt
create table ADMIN.VIO_LIMIT_ROADDEVICE_RELATION
(
  LIMIT_ID    VARCHAR2(20) not null,
  ROAD_ID     VARCHAR2(20) not null,
  DEVICE_CODE VARCHAR2(18) not null,
  DIRECTION   VARCHAR2(10),
  ID          VARCHAR2(20) not null
)
tablespace CROSS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.VIO_LIMIT_ROADDEVICE_RELATION.LIMIT_ID
  is '限行规则';
comment on column ADMIN.VIO_LIMIT_ROADDEVICE_RELATION.ROAD_ID
  is '道路编码';
comment on column ADMIN.VIO_LIMIT_ROADDEVICE_RELATION.DEVICE_CODE
  is '设备编码';
comment on column ADMIN.VIO_LIMIT_ROADDEVICE_RELATION.DIRECTION
  is '限行方向';
alter table ADMIN.VIO_LIMIT_ROADDEVICE_RELATION
  add constraint PK_LIMIT_ROADDEVICE_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table VIO_LIMIT_RULE
prompt =============================
prompt
create table ADMIN.VIO_LIMIT_RULE
(
  ID              VARCHAR2(20) not null,
  CARTYPE         VARCHAR2(1000),
  STOPDAY         VARCHAR2(100),
  STOPTIME        VARCHAR2(100),
  TAILRULE        VARCHAR2(100),
  ISVALID         VARCHAR2(1) not null,
  STARTTIME       DATE,
  LIMITTYPE       VARCHAR2(1),
  ORG_ID          VARCHAR2(32),
  ENDTIME         DATE,
  CERTIFICATE_ORG VARCHAR2(20)
)
tablespace VIO
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.VIO_LIMIT_RULE
  is '限行规则表';
comment on column ADMIN.VIO_LIMIT_RULE.CARTYPE
  is '车辆类型：多个车辆类型的时候用逗号分割';
comment on column ADMIN.VIO_LIMIT_RULE.STOPDAY
  is '限行日期：多个日期用逗号分割（星期）';
comment on column ADMIN.VIO_LIMIT_RULE.STOPTIME
  is '限行时段：HH：mm';
comment on column ADMIN.VIO_LIMIT_RULE.TAILRULE
  is '限行规则：#0表示全部，#1表示单号，#2表示双号，具体数字表示尾号中含有这些数字的都限行';
comment on column ADMIN.VIO_LIMIT_RULE.ISVALID
  is '是否有效：0表示有效，1表示无效';
comment on column ADMIN.VIO_LIMIT_RULE.STARTTIME
  is '开始时间：yyyy-MM-dd';
comment on column ADMIN.VIO_LIMIT_RULE.LIMITTYPE
  is '限行类型：1尾号限行2黄标车限行3货车禁行4外地车限行';
comment on column ADMIN.VIO_LIMIT_RULE.ORG_ID
  is '部门编号';
comment on column ADMIN.VIO_LIMIT_RULE.ENDTIME
  is '终止时间：yyyy-MM-dd';
comment on column ADMIN.VIO_LIMIT_RULE.CERTIFICATE_ORG
  is '发证机关';
alter table ADMIN.VIO_LIMIT_RULE
  add constraint PK_VIO_LIMIT_RULE_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table VIO_LIST_HCJX
prompt ============================
prompt
create table ADMIN.VIO_LIST_HCJX
(
  ID                   VARCHAR2(20) not null,
  CAR_PLATE_COLOR_CODE VARCHAR2(100),
  CAR_PLATE_TYPE_CODE  VARCHAR2(100),
  CAR_CLASS_CODE       VARCHAR2(100),
  CAR_COLOR_CODE       VARCHAR2(100),
  CAR_TYPE_CODE        VARCHAR2(100),
  CAR_BRAND_CODE       VARCHAR2(100),
  CAR_PLATE_NUM        VARCHAR2(150),
  START_DATETIME       DATE,
  END_DATETIME         DATE,
  ROADID               VARCHAR2(12),
  TELEPHONE            VARCHAR2(20),
  OWNER_NAME           VARCHAR2(100),
  TEMP1                VARCHAR2(100),
  TEMP2                VARCHAR2(100),
  TEMP3                VARCHAR2(100),
  TEMP4                VARCHAR2(100)
)
tablespace VIO
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.VIO_LIST_HCJX.CAR_PLATE_COLOR_CODE
  is '号牌颜色';
comment on column ADMIN.VIO_LIST_HCJX.CAR_PLATE_TYPE_CODE
  is '号牌种类';
comment on column ADMIN.VIO_LIST_HCJX.CAR_CLASS_CODE
  is '车辆分类';
comment on column ADMIN.VIO_LIST_HCJX.CAR_COLOR_CODE
  is '车辆颜色';
comment on column ADMIN.VIO_LIST_HCJX.CAR_TYPE_CODE
  is '车辆类型';
comment on column ADMIN.VIO_LIST_HCJX.CAR_BRAND_CODE
  is '车辆品牌';
comment on column ADMIN.VIO_LIST_HCJX.CAR_PLATE_NUM
  is '号牌号码';
comment on column ADMIN.VIO_LIST_HCJX.TELEPHONE
  is '电话';
alter table ADMIN.VIO_LIST_HCJX
  add constraint PK_VIO_LIST_HCJX_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table VIO_MARKER_TYPE
prompt ==============================
prompt
create table ADMIN.VIO_MARKER_TYPE
(
  CODE VARCHAR2(32) not null,
  NAME VARCHAR2(200) not null,
  MEMO VARCHAR2(500)
)
tablespace VIO
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.VIO_MARKER_TYPE
  add constraint TABLE_BASE_XX_VIOTYPE_PK primary key (CODE)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table VIO_RECORD_LIST
prompt ==============================
prompt
create table ADMIN.VIO_RECORD_LIST
(
  ID                 VARCHAR2(32) not null,
  VIO_TYPE_CODE      VARCHAR2(32),
  ROAD_CODE          VARCHAR2(32),
  ROAD_NAME          VARCHAR2(50),
  DEVICE_CODE        VARCHAR2(32),
  DEV_NAME           VARCHAR2(50),
  DIRECTION_CODE     VARCHAR2(32),
  LANE_CODE          VARCHAR2(50),
  CAR_NUM            VARCHAR2(32),
  CAR_NUM_TYPE_CODE  VARCHAR2(32),
  CAR_NUM_COLOR_CODE VARCHAR2(32),
  CAR_TYPE_CODE      VARCHAR2(32),
  CAR_COLOR_CODE     VARCHAR2(23),
  CAR_CLASS_CODE     VARCHAR2(32),
  CAR_DATETIME       TIMESTAMP(6),
  CAR_SPEED          NUMBER(22,5),
  STD_SPEED          NUMBER(22,5),
  SPEED_RATE         NUMBER(22,5),
  IMG1               VARCHAR2(400),
  IMG2               VARCHAR2(400),
  IMG3               VARCHAR2(400),
  IMG4               VARCHAR2(400),
  INTER_SPEED        NUMBER(22,5),
  INTER_LENGTH       NUMBER(2),
  INTER_START_TIME   DATE,
  INTER_END_TIME     DATE,
  INTER_DEV_CODE     VARCHAR2(32),
  MARKER             VARCHAR2(10) not null,
  IS_WHITE           INTEGER,
  OP_USER            VARCHAR2(32),
  OP_TIME            DATE,
  IS_EXPORT          CHAR(1) default 0,
  EXPORT_USER        VARCHAR2(32),
  EXPORT_TIME        DATE,
  IS_LOCKED          CHAR(1) default 0,
  LOCK_USER          VARCHAR2(32),
  LOCK_TIME          DATE,
  IS_DEAL            CHAR(1) default 0,
  DEAL_USER          VARCHAR2(32),
  DEAL_TIMEL         DATE,
  IS_SIMPLE          CHAR(1),
  IS_RIGHT           CHAR(1),
  FINE               FLOAT,
  REAL_FINE          FLOAT,
  DISCOUNT           FLOAT,
  DRIVER_NAME        VARCHAR2(40),
  DRIVER_ADD         VARCHAR2(200),
  DRIVER_PHONE       VARCHAR2(40),
  DRIVER_LICENSE     VARCHAR2(40),
  DRIVER_CODE        VARCHAR2(40),
  JUDGE_CODE         VARCHAR2(40),
  LICENSE_DEP        VARCHAR2(10),
  DRIVE_TYPE         VARCHAR2(10),
  ROUND_FINE         FLOAT,
  OP_USER_CODE       VARCHAR2(32),
  LOCK_USER_CODE     VARCHAR2(32),
  DEAL_USER_CODE     VARCHAR2(32),
  SIMPLEEXPORTFLAG   INTEGER,
  SIMPLEEXPORTUSER   VARCHAR2(32),
  SIMPLEEXPORTTIME   DATE,
  EDIT_USER_CODE     VARCHAR2(32),
  EDIT_USER_NAME     VARCHAR2(32),
  EDIT_TIME          DATE,
  DISSENTRESULT_ID   INTEGER,
  DISSENTRESULT_USER VARCHAR2(40),
  IMG5               VARCHAR2(400),
  IMG6               VARCHAR2(400)
)
tablespace VIO
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 184M
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.VIO_RECORD_LIST
  is '违法表';
comment on column ADMIN.VIO_RECORD_LIST.ID
  is '主键';
comment on column ADMIN.VIO_RECORD_LIST.VIO_TYPE_CODE
  is '违法代码';
comment on column ADMIN.VIO_RECORD_LIST.ROAD_CODE
  is '道路编号';
comment on column ADMIN.VIO_RECORD_LIST.ROAD_NAME
  is '道路名称';
comment on column ADMIN.VIO_RECORD_LIST.DEVICE_CODE
  is '设备代码(区间的话，记录终点的设备号)';
comment on column ADMIN.VIO_RECORD_LIST.DEV_NAME
  is '设备名称';
comment on column ADMIN.VIO_RECORD_LIST.DIRECTION_CODE
  is '方向编码';
comment on column ADMIN.VIO_RECORD_LIST.LANE_CODE
  is '车道号';
comment on column ADMIN.VIO_RECORD_LIST.CAR_NUM
  is '号牌号码';
comment on column ADMIN.VIO_RECORD_LIST.CAR_NUM_TYPE_CODE
  is '号牌种类';
comment on column ADMIN.VIO_RECORD_LIST.CAR_NUM_COLOR_CODE
  is '号牌颜色';
comment on column ADMIN.VIO_RECORD_LIST.CAR_TYPE_CODE
  is '车辆类型';
comment on column ADMIN.VIO_RECORD_LIST.CAR_COLOR_CODE
  is '车身颜色';
comment on column ADMIN.VIO_RECORD_LIST.CAR_CLASS_CODE
  is '车辆分类';
comment on column ADMIN.VIO_RECORD_LIST.CAR_DATETIME
  is '违法时间(区间的话，记录终点的时间)';
comment on column ADMIN.VIO_RECORD_LIST.CAR_SPEED
  is '车辆速度';
comment on column ADMIN.VIO_RECORD_LIST.STD_SPEED
  is '标准限速';
comment on column ADMIN.VIO_RECORD_LIST.SPEED_RATE
  is '超速百分比';
comment on column ADMIN.VIO_RECORD_LIST.INTER_SPEED
  is '区间速度';
comment on column ADMIN.VIO_RECORD_LIST.INTER_LENGTH
  is '区间长度';
comment on column ADMIN.VIO_RECORD_LIST.INTER_START_TIME
  is '区间开始时间';
comment on column ADMIN.VIO_RECORD_LIST.INTER_END_TIME
  is '区间结束时间';
comment on column ADMIN.VIO_RECORD_LIST.INTER_DEV_CODE
  is '区间设备号';
comment on column ADMIN.VIO_RECORD_LIST.MARKER
  is '违法数据类型标记';
comment on column ADMIN.VIO_RECORD_LIST.IS_WHITE
  is '是否是白名单的数据 0-否 1-是';
comment on column ADMIN.VIO_RECORD_LIST.OP_USER
  is '确认人员';
comment on column ADMIN.VIO_RECORD_LIST.OP_TIME
  is '确认时间';
comment on column ADMIN.VIO_RECORD_LIST.IS_EXPORT
  is '是否导出（通过excel表导出） 0-否 1-是';
comment on column ADMIN.VIO_RECORD_LIST.EXPORT_USER
  is '导出人员';
comment on column ADMIN.VIO_RECORD_LIST.EXPORT_TIME
  is '导出时间';
comment on column ADMIN.VIO_RECORD_LIST.IS_LOCKED
  is '是否加锁（通过接口上传公安网） 0-否 1-是';
comment on column ADMIN.VIO_RECORD_LIST.LOCK_USER
  is '加锁（上传公安网）人员';
comment on column ADMIN.VIO_RECORD_LIST.LOCK_TIME
  is '加锁（上传公安网）时间';
comment on column ADMIN.VIO_RECORD_LIST.IS_DEAL
  is '是否已经处理 0-否 1-是';
comment on column ADMIN.VIO_RECORD_LIST.DEAL_USER
  is '处理人员';
comment on column ADMIN.VIO_RECORD_LIST.DEAL_TIMEL
  is '处理时间';
comment on column ADMIN.VIO_RECORD_LIST.IS_SIMPLE
  is '是否简易处罚（裁决） 0-否 1-是';
comment on column ADMIN.VIO_RECORD_LIST.IS_RIGHT
  is '现场拦截时，如果有疑问，需要提交中心进行审核';
comment on column ADMIN.VIO_RECORD_LIST.FINE
  is '罚款金额(每条记录的默认罚款金额)';
comment on column ADMIN.VIO_RECORD_LIST.REAL_FINE
  is '实缴金额(打折后的金额：罚款金额乘以折扣)';
comment on column ADMIN.VIO_RECORD_LIST.DISCOUNT
  is '折扣';
comment on column ADMIN.VIO_RECORD_LIST.DRIVER_NAME
  is '驾驶员名称';
comment on column ADMIN.VIO_RECORD_LIST.DRIVER_ADD
  is '驾驶员地址';
comment on column ADMIN.VIO_RECORD_LIST.DRIVER_PHONE
  is '驾驶员电话';
comment on column ADMIN.VIO_RECORD_LIST.DRIVER_LICENSE
  is '驾驶证编号';
comment on column ADMIN.VIO_RECORD_LIST.DRIVER_CODE
  is '档案编号';
comment on column ADMIN.VIO_RECORD_LIST.JUDGE_CODE
  is '裁决书号';
comment on column ADMIN.VIO_RECORD_LIST.LICENSE_DEP
  is '发证机关';
comment on column ADMIN.VIO_RECORD_LIST.DRIVE_TYPE
  is '准驾车型';
comment on column ADMIN.VIO_RECORD_LIST.ROUND_FINE
  is '四舍五入后的金额（总的罚款金额除以总的条数）';
comment on column ADMIN.VIO_RECORD_LIST.OP_USER_CODE
  is '确认人员ID';
comment on column ADMIN.VIO_RECORD_LIST.LOCK_USER_CODE
  is '加锁ID';
comment on column ADMIN.VIO_RECORD_LIST.DEAL_USER_CODE
  is '处理ID';
comment on column ADMIN.VIO_RECORD_LIST.SIMPLEEXPORTFLAG
  is '简易导出的标记位';
comment on column ADMIN.VIO_RECORD_LIST.SIMPLEEXPORTUSER
  is '简易导出人员姓名';
comment on column ADMIN.VIO_RECORD_LIST.SIMPLEEXPORTTIME
  is '简易导出时间';
comment on column ADMIN.VIO_RECORD_LIST.EDIT_USER_CODE
  is '最后修改人员';
comment on column ADMIN.VIO_RECORD_LIST.EDIT_USER_NAME
  is '最后修改姓名ID';
comment on column ADMIN.VIO_RECORD_LIST.EDIT_TIME
  is '最后修改时间';
comment on column ADMIN.VIO_RECORD_LIST.DISSENTRESULT_ID
  is '违法疑义数据原因';
comment on column ADMIN.VIO_RECORD_LIST.DISSENTRESULT_USER
  is '提出违法异议的人员姓名';
alter table ADMIN.VIO_RECORD_LIST
  add constraint PK_TAB_VIOLATE primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.VIO_CAR_DATETIME_INDEX on ADMIN.VIO_RECORD_LIST (CAR_DATETIME)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 24M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.VIO_LIST_CAR_SPEED_INDEX on ADMIN.VIO_RECORD_LIST (CAR_SPEED)
  tablespace VIO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.VIO_LIST_DEVICE_CODE_INDEX on ADMIN.VIO_RECORD_LIST (DEVICE_CODE)
  tablespace VIO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.VIO_LIST_ROAD_CODE_INDEX on ADMIN.VIO_RECORD_LIST (ROAD_CODE)
  tablespace VIO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.VIO_NUM_INDEX on ADMIN.VIO_RECORD_LIST (CAR_NUM)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 24M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table VIO_RECORD_TEMP
prompt ==============================
prompt
create table ADMIN.VIO_RECORD_TEMP
(
  ID                 VARCHAR2(32) not null,
  VIO_TYPE_CODE      VARCHAR2(32),
  ROAD_CODE          VARCHAR2(32),
  ROAD_NAME          VARCHAR2(32),
  DEVICE_CODE        VARCHAR2(32),
  DEV_NAME           VARCHAR2(50),
  DIRECTION_CODE     VARCHAR2(32),
  LANE_CODE          VARCHAR2(50),
  CAR_NUM            VARCHAR2(32),
  CAR_NUM_TYPE_CODE  VARCHAR2(32),
  CAR_NUM_COLOR_CODE VARCHAR2(32),
  CAR_TYPE_CODE      VARCHAR2(32),
  CAR_COLOR_CODE     VARCHAR2(32),
  CAR_CLASS_CODE     VARCHAR2(32),
  CAR_DATETIME       TIMESTAMP(6),
  CAR_SPEED          INTEGER,
  STD_SPEED          INTEGER,
  SPEED_RATE         NUMBER,
  IMG1               VARCHAR2(400),
  IMG2               VARCHAR2(400),
  IMG3               VARCHAR2(400),
  IMG4               VARCHAR2(400),
  INTER_SPEED        NUMBER,
  INTER_LENGTH       NUMBER,
  INTER_START_TIME   DATE,
  INTER_END_TIME     DATE,
  INTER_DEV_ID       INTEGER,
  MARKER             VARCHAR2(10) not null,
  IS_WHITE           CHAR(1),
  IS_EXPORT          CHAR(1),
  EXPORT_USER        VARCHAR2(32),
  EXPORT_TIME        DATE,
  INTER_START_NUM    VARCHAR2(32),
  IS_BIG             VARCHAR2(32),
  IMG5               VARCHAR2(400),
  IMG6               VARCHAR2(500)
)
tablespace VIO
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 152M
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.VIO_RECORD_TEMP
  is '违法表临时表(所有的违法数据分析后先到这个表)';
comment on column ADMIN.VIO_RECORD_TEMP.ID
  is '主键';
comment on column ADMIN.VIO_RECORD_TEMP.VIO_TYPE_CODE
  is '违法代码(标准的违法代码)';
comment on column ADMIN.VIO_RECORD_TEMP.ROAD_CODE
  is '道路编号';
comment on column ADMIN.VIO_RECORD_TEMP.ROAD_NAME
  is '道路名称';
comment on column ADMIN.VIO_RECORD_TEMP.DEVICE_CODE
  is '设备代码(区间记录终点的设备号)';
comment on column ADMIN.VIO_RECORD_TEMP.DEV_NAME
  is '设备名称';
comment on column ADMIN.VIO_RECORD_TEMP.DIRECTION_CODE
  is '方向编码';
comment on column ADMIN.VIO_RECORD_TEMP.LANE_CODE
  is '车道号';
comment on column ADMIN.VIO_RECORD_TEMP.CAR_NUM
  is '号牌号码';
comment on column ADMIN.VIO_RECORD_TEMP.CAR_NUM_TYPE_CODE
  is '号牌种类';
comment on column ADMIN.VIO_RECORD_TEMP.CAR_NUM_COLOR_CODE
  is '号牌颜色';
comment on column ADMIN.VIO_RECORD_TEMP.CAR_TYPE_CODE
  is '车辆类型';
comment on column ADMIN.VIO_RECORD_TEMP.CAR_COLOR_CODE
  is '车身颜色';
comment on column ADMIN.VIO_RECORD_TEMP.CAR_CLASS_CODE
  is '车辆分类';
comment on column ADMIN.VIO_RECORD_TEMP.CAR_DATETIME
  is '违法时间';
comment on column ADMIN.VIO_RECORD_TEMP.CAR_SPEED
  is '车辆速度';
comment on column ADMIN.VIO_RECORD_TEMP.STD_SPEED
  is '标准限速';
comment on column ADMIN.VIO_RECORD_TEMP.SPEED_RATE
  is '超速百分比';
comment on column ADMIN.VIO_RECORD_TEMP.INTER_SPEED
  is '区间速度';
comment on column ADMIN.VIO_RECORD_TEMP.INTER_LENGTH
  is '区间长度';
comment on column ADMIN.VIO_RECORD_TEMP.INTER_START_TIME
  is '区间开始时间';
comment on column ADMIN.VIO_RECORD_TEMP.INTER_END_TIME
  is '区间结束时间';
comment on column ADMIN.VIO_RECORD_TEMP.INTER_DEV_ID
  is '区间设备号码';
comment on column ADMIN.VIO_RECORD_TEMP.MARKER
  is '违法数据标记';
comment on column ADMIN.VIO_RECORD_TEMP.IS_WHITE
  is '是否是白名单的数据 0-否 1-是';
comment on column ADMIN.VIO_RECORD_TEMP.IS_EXPORT
  is '是否导出 0-否 1-是';
comment on column ADMIN.VIO_RECORD_TEMP.EXPORT_TIME
  is '导出时间';
comment on column ADMIN.VIO_RECORD_TEMP.INTER_START_NUM
  is '区间匹配到的起点车辆的号牌号码';
comment on column ADMIN.VIO_RECORD_TEMP.IS_BIG
  is '大小车';
alter table ADMIN.VIO_RECORD_TEMP
  add constraint VIO_RECORD_TEMP_PK primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.TEMP_VIO_CARDATETIME_INDEX on ADMIN.VIO_RECORD_TEMP (CAR_DATETIME)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 23M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.VIO_CAR_PLATENUM on ADMIN.VIO_RECORD_TEMP (CAR_NUM)
  tablespace VIO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 512K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.VIO_TEMP_DEVICE_CODE_INDEX on ADMIN.VIO_RECORD_TEMP (DEVICE_CODE)
  tablespace VIO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 576K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.VIO_TEMP_ROAD_CODE_INDEX on ADMIN.VIO_RECORD_TEMP (ROAD_CODE)
  tablespace VIO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 768K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.VIO_TEMP_SPEED_INDEX on ADMIN.VIO_RECORD_TEMP (CAR_SPEED)
  tablespace VIO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 320K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table VIO_TYPE
prompt =======================
prompt
create table ADMIN.VIO_TYPE
(
  CODE         VARCHAR2(10) not null,
  NAME         VARCHAR2(500) not null,
  FINE_DEFAULT NUMBER(4) not null,
  FINE_MIN     NUMBER(4) not null,
  FINE_MAX     NUMBER(4) not null,
  LAW          VARCHAR2(500) not null,
  RULE         VARCHAR2(500) not null,
  METHOD       VARCHAR2(500) not null,
  PUNISH       VARCHAR2(500),
  SIMPLE_NAME  VARCHAR2(500),
  ISSHOW       VARCHAR2(3) default 1,
  ORDERNUM     NUMBER(3),
  MARKER       VARCHAR2(32) default 1 not null
)
tablespace VIO
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.VIO_TYPE_CODE_INDEX on ADMIN.VIO_TYPE (CODE)
  tablespace VIO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table VIO_VIPNAME
prompt ==========================
prompt
create table ADMIN.VIO_VIPNAME
(
  ID               VARCHAR2(20) not null,
  PLATENUM         VARCHAR2(16) not null,
  DEPTNAME         VARCHAR2(200),
  PLATE_COLOR_CODE VARCHAR2(10),
  PLATETYPECODE    VARCHAR2(10),
  NOTE             VARCHAR2(600),
  ISVIO            VARCHAR2(1),
  ISBLANK          VARCHAR2(1)
)
tablespace VIO
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.VIO_VIPNAME
  add constraint PK_VIPNAME_ID primary key (ID)
  using index 
  tablespace CROSS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating package PACK_RECORDLIST_PROCESS
prompt ========================================
prompt
create or replace package admin.pack_recordlist_process is

  -- Author  : yantao
  -- Purpose : 处理跟 cross_recordlist_temp相关的过程

  record_list cross_recordlist_temp%rowtype;

  ----包的总流程控制
  procedure processindex;

  ----白名单过滤处理方法块
  function proc_vip_black_filter(record_list cross_recordlist_temp%rowtype)return number;
  
  function proc_vip_vio_filter(record_list cross_recordlist_temp%rowtype)return number;

  ----布控数据对比方法块
  procedure proc_black_filter(record_list cross_recordlist_temp%rowtype);
  

  ----保存布控数据方法块
  procedure save_blackalarm_data(record_list cross_recordlist_temp%rowtype,crossblack cross_black_info%rowtype,vionum varchar2);


end pack_recordlist_process;
/

prompt
prompt Creating function GET_MARKER_BY_VIOTYPE
prompt =======================================
prompt
create or replace function admin.get_marker_by_viotype
(
       viotype varchar2
)
return varchar2
is Result varchar2(10);
begin
select t.marker into Result from vio_type t where t.code=viotype;
return(Result);
end get_marker_by_viotype;
/

prompt
prompt Creating procedure CROSS_ALARMLIST_HISMOVE
prompt ==========================================
prompt
create or replace procedure admin.CROSS_ALARMLIST_HISMOVE
--每日定时检查并转移非本月的历史报警数据
--YanTao 2013.11.16 add
is

  alarm_record cross_black_alarm%rowtype;

  month_yyyymmdd VARCHAR2(8);

  month_yyyymm VARCHAR2(6);

begin

    -- 获得当前时间的月
    select to_char(sysdate,'yyyyMM') into month_yyyymm from dual;
    month_yyyymmdd := month_yyyymm || '01';

    -- 复制转移非本月的历史报警数据
    insert into cross_black_alarmhis
    select * from cross_black_alarm temp where temp.alarmtime < to_date(month_yyyymmdd,'yyyyMMdd');

    -- 删除当前报警表数据
    delete from cross_black_alarm t where t.alarmtime  < to_date(month_yyyymmdd,'yyyyMMdd');

     exception
     when others then
     null;

end CROSS_ALARMLIST_HISMOVE;
/

prompt
prompt Creating procedure CROSS_RECORDLIST_CTABLE
prompt ==========================================
prompt
create or replace procedure admin.CROSS_RECORDLIST_CTABLE
--定时任务 按下周日期 创建动态表 格式: YYYYMMDD 四位年份 + 两位月份 + 两位天数
--YanTao 2013.05.31 add
is

  next_month_daycount NUMBER(2);
  next_month_yyyymm VARCHAR2(6);
  next_month_mm VARCHAR2(2);
  his_tablespace_name VARCHAR2(50);
  his_table_name VARCHAR2(50);
  querySql VARCHAR2(1000);
  exist_table_flag NUMBER(2);
  createSql VARCHAR2(1000);
  
  v_sqlcode number;
  v_sqlerrm varchar2(400);

  i NUMBER(2);

begin
    
     -- 获得下个月的天数
     next_month_daycount := add_months(trunc((add_months(last_day(sysdate),0)+1),'mm'),1)-trunc((add_months(last_day(sysdate),0)+1),'mm');

     -- 获得下个月表的年月格式
     next_month_yyyymm :=  to_char(add_months(last_day(sysdate),0)+1,'yyyymm') ;
     
     -- 获得下个月
     next_month_mm := to_char(add_months(last_day(sysdate),0)+1,'mm');

     -- 创建表空间名
     his_tablespace_name := 'CROSS_RECORDLIST_T_2013'||next_month_mm;

     --动态循环判断下个月的每周表是否存在，不存在就建表
     for i in 0..next_month_daycount-1 loop
        -- 动态封装表名
        if i<9 then
           his_table_name := 'CROSS_RECORDLIST_'||next_month_yyyymm||'0'||(i+1) ;
        else
           his_table_name := 'CROSS_RECORDLIST_'||next_month_yyyymm||(i+1) ;
        end if;

        --判断表是否存在
        querySql := 'select count(*) from user_tables where table_name = '''||his_table_name||''' ';
        execute immediate querySql INTO exist_table_flag ;

        IF exist_table_flag <= 0 THEN

            --  数据库初始化时，必须要手动执行这步，否则无权限建立表
            --  grant create table,create session,dba to admin;
            --如果不存在 则创建空表
            createSql:='create table '||his_table_name||' tablespace '||his_tablespace_name||' pctfree 10 initrans 1 maxtrans 255 storage (initial 50M minextents 1 maxextents unlimited) as select * from cross_recordlist_temp where 2=1 ';
            execute immediate createSql;

        END IF;

     end loop;

  exception
  
    when others then
    
    v_sqlcode := SQLCODE;
    
    v_sqlerrm := SQLERRM;
    
    update  cross_job_exception set TIME_EXCEPTION = sysdate,EXCEPTIONCODE = v_sqlcode,EXCEPTIONMESSAGE = substr(v_sqlerrm,1,200) where ID = 'job001';

end CROSS_RECORDLIST_CTABLE;
/

prompt
prompt Creating procedure CROSS_RECORDLIST_CTABLEDIY
prompt =============================================
prompt
create or replace procedure admin.CROSS_RECORDLIST_CTABLEDIY
--测试阶段 补建历史表使用
--YanTao 2013.05.31 add
is

  next_month_daycount NUMBER(2);
  next_month_yyyymm VARCHAR2(6);
  his_tablespace_name VARCHAR2(50);
  his_table_name VARCHAR2(50);
  querySql VARCHAR2(1000);
  exist_table_flag NUMBER(2);
  createSql VARCHAR2(1000);

  i NUMBER(2);

begin

     -- 获得下个月的天数
     next_month_daycount := 31;

     -- 获得下个月表的年月格式
     next_month_yyyymm :=  '201310' ;

     -- 创建表空间名
     his_tablespace_name := 'CROSS_RECORDLIST_T_'||next_month_yyyymm;

     --动态循环判断下个月的每周表是否存在，不存在就建表
     for i in 0..next_month_daycount-1 loop
        -- 动态封装表名
        if i<9 then
           his_table_name := 'CROSS_RECORDLIST_'||next_month_yyyymm||'0'||(i+1) ;
        else
           his_table_name := 'CROSS_RECORDLIST_'||next_month_yyyymm||(i+1) ;
        end if;

        --判断表是否存在
        querySql := 'select count(*) from user_tables where table_name = '''||his_table_name||''' ';
        execute immediate querySql INTO exist_table_flag ;

        IF exist_table_flag <= 0 THEN

            --  数据库初始化时，必须要手动执行这步，否则无权限建立表
            --  grant create table,create session,dba to admin;
            --如果不存在 则创建空表
            createSql:='create table '||his_table_name||' tablespace '||his_tablespace_name||' pctfree 10 initrans 1 maxtrans 255 storage (initial 50M minextents 1 maxextents unlimited) as select * from cross_recordlist_temp where 2=1 ';
            execute immediate createSql;

        END IF;

     end loop;

  exception
    when others then
    null;

end CROSS_RECORDLIST_CTABLEDIY;
/

prompt
prompt Creating procedure CROSS_RECORDLIST_ITABLE
prompt ==========================================
prompt
create or replace procedure admin.CROSS_RECORDLIST_ITABLE(list  cross_recordlist_temp%rowtype)
--通行车辆表 触发器调用 对新插入的记录进行按日期分表
--YanTao 2013.05.29 add
is

  his_format_str VARCHAR2(8);
  his_table_name VARCHAR2(50);
  exist_table_flag NUMBER(2);
  insertSql VARCHAR2(3000);
  querySql VARCHAR2(1000);

begin

     his_format_str := to_char(list.CAR_DATETIME,'yyyymmdd');

     his_table_name := 'CROSS_RECORDLIST_'||his_format_str ;


     --判断表是否存在
     querySql := 'select count(*) from user_tables where table_name = '''||his_table_name||''' ';
     execute immediate querySql INTO exist_table_flag ;

    IF exist_table_flag > 0 THEN

     --开始插入日期表

     insertSql := 'insert into '||his_table_name||' (id,device_code,direction_code,LANE_CODE,CAR_PLATE_FLAG,CAR_PLATE_NUM,car_plate_color_code,car_plate_type_code,
     CAR_SPEED,CAR_DATETIME,CAR_CLASS_CODE,CAR_COLOR_CODE,CAR_TYPE_CODE,CAR_BRAND_CODE,IMG1_PATH,IMG2_PATH,IMG3_PATH,IMG4_PATH,IMG_PLATE_PATH,INSERT_TIME) VALUES
     (:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20)';
     execute immediate insertSql using list.id,list.device_code,list.direction_code,list.LANE_CODE,list.CAR_PLATE_FLAG,list.CAR_PLATE_NUM,list.car_plate_color_code,list.car_plate_type_code,
     list.CAR_SPEED,list.CAR_DATETIME,list.CAR_CLASS_CODE,list.CAR_COLOR_CODE,list.CAR_TYPE_CODE,list.CAR_BRAND_CODE,list.IMG1_PATH,list.IMG2_PATH,list.IMG3_PATH,list.IMG4_PATH,list.IMG_PLATE_PATH,list.INSERT_TIME;
     
   END IF;

     exception
      when others then
      null;

end CROSS_RECORDLIST_ITABLE;
/

prompt
prompt Creating procedure CROSS_RECORDLIST_TEMPDEL
prompt ===========================================
prompt
create or replace procedure admin.CROSS_RECORDLIST_TEMPDEL
--JOB每分定时检查
--YanTao 2013.11.16 add
is


  countTemp number;

begin

     select count(*) into countTemp from cross_recordlist_temp;

     if(countTemp>10000) then
        delete from cross_recordlist_temp where id in (select id from (select * from ADMIN.cross_recordlist_temp order by car_datetime asc) where rownum <= 9000);
     end if;

     exception
     when others then
     null;

end CROSS_RECORDLIST_TEMPDEL;
/

prompt
prompt Creating package body PACK_RECORDLIST_PROCESS
prompt =============================================
prompt
create or replace package body admin.pack_recordlist_process is

  createSql VARCHAR2(2000);
  procedure processindex is
  begin
  
    if (record_list.car_plate_num = '00000000' or record_list.car_plate_num = '-') then
      return;
    end if;

    if(proc_vip_black_filter(record_list)=1)then
    return;
    end if;

    proc_black_filter(record_list);

  end;
  

  function proc_vip_black_filter(record_list cross_recordlist_temp%rowtype)
   return number is

   conum number;
   begin
   
    select count(*) into conum from vio_vipname v where v.platenum = record_list.car_plate_num 
    and v.plate_color_code = record_list.car_plate_color_code and v.isblank = '1';
    
    if conum > 0 then
    insert into CROSS_RECORD_VIP_PASS values(sys_guid(),record_list.car_plate_num,
    record_list.car_plate_type_code,record_list.device_code,record_list.direction_code,
    record_list.id,'布控');
    return 1;
    else
    return 0;
    end if;

  end proc_vip_black_filter;
  
  
  
  function proc_vip_vio_filter(record_list cross_recordlist_temp%rowtype)
   return number is

   conum number;
   begin
   
    select count(*) into conum from vio_vipname v where v.platenum = record_list.car_plate_num 
    and v.plate_color_code = record_list.car_plate_color_code and v.isvio = '1';
    
    if conum > 0 then
    insert into CROSS_RECORD_VIP_PASS values(sys_guid(),record_list.car_plate_num,
    record_list.car_plate_type_code,record_list.device_code,record_list.direction_code,
    record_list.id,'违法');
    return 1;
    else
    return 0;
    end if;

  end proc_vip_vio_filter;
  
  
  procedure proc_black_filter(record_list cross_recordlist_temp%rowtype) is
     
    crossblack cross_black_info%rowtype;
    querySql VARCHAR2(1000);
    blackdevicecode VARCHAR2(4000);
     
    begin
    
     select DBMS_LOB.SUBSTR(t.device_code,32767) into blackdevicecode from cross_black_info t where t.car_plate_num = record_list.car_plate_num and rownum = 1 ;

     if (blackdevicecode = 'all') then

           select * into crossblack from (
           select * from cross_black_info b where  b.TIME_START <= sysdate and b.TIME_END >= sysdate and b.is_verifypass = 1 
           and record_list.car_plate_num like b.CAR_PLATE_NUM and record_list.car_plate_color_code = b.CAR_PLATE_COLOR_CODE 
           and rownum < 2 
           order by b.TIME_ADD desc);

     else

       querySql := 'select * from (select * from cross_black_info b where  b.TIME_START <= sysdate and b.TIME_END >= sysdate and b.is_verifypass = 1 '||
        ' and b.CAR_PLATE_NUM = '''||record_list.car_plate_num||'''  and  b.CAR_PLATE_COLOR_CODE = '||record_list.car_plate_color_code||
        ' and '''||record_list.device_code||''' in ('||blackdevicecode||') and rownum < 2 order by b.TIME_ADD desc)';

        execute immediate querySql INTO crossblack ;
   
     end if; 
      

     save_blackalarm_data(record_list,crossblack,'');
     
     exception
     
     when no_data_found then
  
     return;
     

 end proc_black_filter;
    
  
 
  procedure save_blackalarm_data(record_list cross_recordlist_temp%rowtype,crossblack cross_black_info%rowtype,vionum varchar2)
   is 
   
   begin
    
     
     
     if(crossblack.id is not null or crossblack.id != '')then
       
         insert into CROSS_BLACK_ALARM
          (ID,
           RECORD_ID,
           BLACK_ID,
           ALARMTIME,
           ROAD_CODE,
           DEVICE_CODE,
           DIRECTION_CODE,
           PLATE_NUM,
           PLATE_COLOR_CODE,
           IMG_PATH,
           BLACK_TYPE_CODE,
           LANE_CODE,
           CAR_SPEED,
           IMG2_PATH,
           IMG3_PATH,
           IMG4_PATH
           )
        values
          (sys_guid(),
           record_list.id,
           crossblack.id,
           sysdate,
           '',
           record_list.device_code,
           record_list.direction_code,
           record_list.car_plate_num,
           record_list.car_plate_color_code,
           record_list.img1_path,
           crossblack.black_type_code,
           record_list.lane_code,
           record_list.car_speed,
           record_list.img2_path,
           record_list.img3_path,
           record_list.img4_path
           );
    
      end if;
      
   end save_blackalarm_data;

end pack_recordlist_process;
/

prompt
prompt Creating trigger CROSS_T_ALARMLIST_HISCOPY
prompt ==========================================
prompt
create or replace trigger ADMIN.CROSS_T_ALARMLIST_HISCOPY
  after insert ON ADMIN.cross_black_alarm   for each row
--YanTao 2013.11.16 add
--作用：不符合当前月的数据，直接插入历史表
declare

  alarm_record cross_black_alarm%rowtype;
  
  month_yyyymmdd VARCHAR2(8);
  
  month_yyyymm VARCHAR2(6);
  
begin

    -- 获得当前时间的月
    select to_char(sysdate,'yyyyMM') into month_yyyymm from dual;
    month_yyyymmdd := month_yyyymm || '01';
    -- 判断并转移到历史表
    alarm_record.alarmtime := :new.alarmtime;
    
    if alarm_record.alarmtime < to_date(month_yyyymmdd,'yyyyMMdd') then
    
      insert into cross_black_alarmhis 
      (ID,RECORD_ID,BLACK_ID,ALARMTIME,ROAD_CODE,DEVICE_CODE,DIRECTION_CODE,
      PLATE_NUM,PLATE_COLOR_CODE,IMG_PATH ,BLACK_TYPE_CODE,LANE_CODE ,CAR_SPEED,IMG2_PATH ,IMG3_PATH,IMG4_PATH)  
      VALUES 
      (:new.ID,:new.RECORD_ID,:new.BLACK_ID,:new.ALARMTIME,:new.ROAD_CODE,:new.DEVICE_CODE,:new.DIRECTION_CODE,
      :new.PLATE_NUM,:new.PLATE_COLOR_CODE,:new.IMG_PATH ,:new.BLACK_TYPE_CODE,:new.LANE_CODE ,:new.CAR_SPEED,:new.IMG2_PATH ,
      :new.IMG3_PATH,:new.IMG4_PATH);
      
    end if;
    

end CROSS_T_ALARM_HISCOPY;
/

prompt
prompt Creating trigger CROSS_T_RECORDLIST_DATACOPY
prompt ============================================
prompt
create or replace trigger ADMIN.CROSS_T_RECORDLIST_datacopy
  after insert ON ADMIN.CROSS_RECORDLIST_TEMP   for each row
--YanTao 2013.05.29 add
declare

  record_temp cross_recordlist_temp%rowtype;
  
begin

    record_temp.ID := :new.ID;
    record_temp.DEVICE_CODE := :new.DEVICE_CODE;
    record_temp.direction_code := :new.direction_code;
    record_temp.LANE_CODE := :new.LANE_CODE;
    record_temp.CAR_PLATE_FLAG := :new.CAR_PLATE_FLAG;
    record_temp.CAR_PLATE_NUM := :new.CAR_PLATE_NUM;
    record_temp.CAR_PLATE_COLOR_CODE := :new.CAR_PLATE_COLOR_CODE;
    record_temp.CAR_PLATE_TYPE_CODE := :new.CAR_PLATE_TYPE_CODE;
    record_temp.CAR_SPEED := :new.CAR_SPEED;
    record_temp.CAR_DATETIME := :new.CAR_DATETIME;
    record_temp.CAR_CLASS_CODE := :new.CAR_CLASS_CODE;
    record_temp.CAR_COLOR_CODE := :new.CAR_COLOR_CODE;
    record_temp.CAR_TYPE_CODE := :new.CAR_TYPE_CODE;
    record_temp.CAR_BRAND_CODE := :new.CAR_BRAND_CODE;
    record_temp.IMG1_PATH := :new.IMG1_PATH;
    record_temp.IMG2_PATH := :new.IMG2_PATH;
    record_temp.IMG3_PATH := :new.IMG3_PATH;
    record_temp.IMG4_PATH := :new.IMG4_PATH;
    record_temp.IMG_PLATE_PATH := :new.IMG_PLATE_PATH;
    record_temp.INSERT_TIME := :new.INSERT_TIME;
    CROSS_RECORDLIST_ITABLE(record_temp);

end CROSS_T_RECORDLIST_datacopy;
/

prompt
prompt Creating trigger CROSS_T_RECORDLIST_PROCESS
prompt ===========================================
prompt
create or replace trigger ADMIN.CROSS_T_RECORDLIST_PROCESS
  before insert on cross_recordlist_temp
  for each row
declare

begin
  --copy coloum data to package
  pack_recordlist_process.record_list.ID:=:new.ID;
  pack_recordlist_process.record_list.DEVICE_CODE:=:new.DEVICE_CODE;
  pack_recordlist_process.record_list.DIRECTION_CODE:=:new.DIRECTION_CODE;
  pack_recordlist_process.record_list.LANE_CODE:=:new.LANE_CODE;
  pack_recordlist_process.record_list.CAR_PLATE_FLAG:=:new.CAR_PLATE_FLAG;
  pack_recordlist_process.record_list.CAR_PLATE_NUM:=:new.CAR_PLATE_NUM;
  pack_recordlist_process.record_list.CAR_PLATE_COLOR_CODE:=:new.CAR_PLATE_COLOR_CODE;
  pack_recordlist_process.record_list.CAR_PLATE_TYPE_CODE:=:new.CAR_PLATE_TYPE_CODE;
  pack_recordlist_process.record_list.CAR_SPEED:=:new.CAR_SPEED;
  pack_recordlist_process.record_list.CAR_DATETIME:=:new.CAR_DATETIME;
  pack_recordlist_process.record_list.CAR_CLASS_CODE:=:new.CAR_CLASS_CODE;
  pack_recordlist_process.record_list.CAR_COLOR_CODE:=:new.CAR_COLOR_CODE;
  pack_recordlist_process.record_list.CAR_TYPE_CODE:=:new.CAR_TYPE_CODE;
  pack_recordlist_process.record_list.CAR_BRAND_CODE:=:new.CAR_BRAND_CODE;
  pack_recordlist_process.record_list.IMG1_PATH:=:new.IMG1_PATH;
  pack_recordlist_process.record_list.IMG2_PATH:=:new.IMG2_PATH;
  pack_recordlist_process.record_list.IMG3_PATH:=:new.IMG3_PATH;
  pack_recordlist_process.record_list.IMG4_PATH:=:new.IMG4_PATH;
  pack_recordlist_process.record_list.IMG_PLATE_PATH:=:new.IMG_PLATE_PATH;
  pack_recordlist_process.record_list.INSERT_TIME:=:new.INSERT_TIME;

  pack_recordlist_process.processindex;

end CROSS_T_RECORDLIST_PROCESS;
/


spool off
