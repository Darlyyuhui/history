----------------------------------------------------
-- Export file for user ADMIN                     --
-- Created by Administrator on 2014-2-25, 9:41:56 --
----------------------------------------------------

spool gps.log

prompt
prompt Creating table GPS_ALARM_GUARD
prompt ==============================
prompt
create table ADMIN.GPS_ALARM_GUARD
(
  ID              VARCHAR2(50) not null,
  ORDER_TYPE_NAME VARCHAR2(20),
  STATION_NAME    VARCHAR2(100),
  ISEXTENT        CHAR(1),
  EXTENT_TYPE     CHAR(1),
  TELNUMBER       VARCHAR2(20),
  CARTYPE         VARCHAR2(20),
  ORG_NAME        VARCHAR2(150),
  NAME            VARCHAR2(50),
  TIME            DATE,
  STERTTIME       DATE,
  ENDTIME         DATE,
  GPSTIME         DATE
)
tablespace OM
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
comment on column ADMIN.GPS_ALARM_GUARD.ORDER_TYPE_NAME
  is '岗位类型';
comment on column ADMIN.GPS_ALARM_GUARD.STATION_NAME
  is '岗位名称/网格/电子围栏';
comment on column ADMIN.GPS_ALARM_GUARD.ISEXTENT
  is '1：正常 0：越界';
comment on column ADMIN.GPS_ALARM_GUARD.EXTENT_TYPE
  is '0：电子围栏 1：网格 2：岗位';
comment on column ADMIN.GPS_ALARM_GUARD.TELNUMBER
  is 'GPSR号码';
comment on column ADMIN.GPS_ALARM_GUARD.CARTYPE
  is '类型';
comment on column ADMIN.GPS_ALARM_GUARD.ORG_NAME
  is '部门名称';
comment on column ADMIN.GPS_ALARM_GUARD.NAME
  is '名称';
comment on column ADMIN.GPS_ALARM_GUARD.TIME
  is '越界时间';
comment on column ADMIN.GPS_ALARM_GUARD.STERTTIME
  is '执勤开始时间';
comment on column ADMIN.GPS_ALARM_GUARD.ENDTIME
  is '执勤结束时间';
comment on column ADMIN.GPS_ALARM_GUARD.GPSTIME
  is 'gps时间';
alter table ADMIN.GPS_ALARM_GUARD
  add constraint PK_ALARM_GUARD_ID primary key (ID)
  using index 
  tablespace USERS
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
prompt Creating table GPS_OM_ALARM_TIME
prompt ================================
prompt
create table ADMIN.GPS_OM_ALARM_TIME
(
  ORGNAME         VARCHAR2(50),
  STATIONNAME     VARCHAR2(50),
  STARTTIME       DATE,
  ENDTIME         DATE,
  TELNUMBER       VARCHAR2(50),
  REALTIME_NAME   VARCHAR2(50),
  TIMES           VARCHAR2(50) default 0,
  CARTYPE         VARCHAR2(50),
  ORDER_TYPE_NAME VARCHAR2(50),
  COUNT           NUMBER
)
tablespace GPS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 512K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.GPS_OM_ALARM_TIME.ORGNAME
  is '部门名称';
comment on column ADMIN.GPS_OM_ALARM_TIME.STATIONNAME
  is '岗位名称';
comment on column ADMIN.GPS_OM_ALARM_TIME.STARTTIME
  is '执勤开始时间';
comment on column ADMIN.GPS_OM_ALARM_TIME.ENDTIME
  is '执勤结束时间';
comment on column ADMIN.GPS_OM_ALARM_TIME.TELNUMBER
  is 'GPRS号码';
comment on column ADMIN.GPS_OM_ALARM_TIME.REALTIME_NAME
  is '设备名称';
comment on column ADMIN.GPS_OM_ALARM_TIME.TIMES
  is '越界时长';
comment on column ADMIN.GPS_OM_ALARM_TIME.CARTYPE
  is '设备类型';
comment on column ADMIN.GPS_OM_ALARM_TIME.ORDER_TYPE_NAME
  is '岗位类型';
comment on column ADMIN.GPS_OM_ALARM_TIME.COUNT
  is '越界次数';

prompt
prompt Creating table GPS_OM_COMPARE
prompt =============================
prompt
create table ADMIN.GPS_OM_COMPARE
(
  ORGID         VARCHAR2(32),
  ORG_NAME      VARCHAR2(50),
  STATION_NAME  VARCHAR2(50),
  STARTTIME     DATE,
  ENDTIME       DATE,
  TELNUMBER     VARCHAR2(50),
  STATETIME     DATE,
  ONLINETIME    VARCHAR2(50) default 0,
  REALTIME_NAME VARCHAR2(50),
  REALTIME_ID   VARCHAR2(32),
  CARTYPE       VARCHAR2(2),
  PLANTIMES     VARCHAR2(20)
)
tablespace GPS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 512K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.GPS_OM_COMPARE.ORGID
  is '部门id';
comment on column ADMIN.GPS_OM_COMPARE.ORG_NAME
  is '部门名称';
comment on column ADMIN.GPS_OM_COMPARE.STATION_NAME
  is '岗位名称';
comment on column ADMIN.GPS_OM_COMPARE.STARTTIME
  is '执勤开始时间';
comment on column ADMIN.GPS_OM_COMPARE.ENDTIME
  is '执勤结束时间';
comment on column ADMIN.GPS_OM_COMPARE.TELNUMBER
  is 'GPRS号码';
comment on column ADMIN.GPS_OM_COMPARE.STATETIME
  is '记录时间';
comment on column ADMIN.GPS_OM_COMPARE.ONLINETIME
  is '在线时长';
comment on column ADMIN.GPS_OM_COMPARE.REALTIME_NAME
  is '警员或警车';
comment on column ADMIN.GPS_OM_COMPARE.REALTIME_ID
  is '警员或警车id';
comment on column ADMIN.GPS_OM_COMPARE.CARTYPE
  is '设备类型';
comment on column ADMIN.GPS_OM_COMPARE.PLANTIMES
  is '排班时长';

prompt
prompt Creating table GPS_REALTIME
prompt ===========================
prompt
create table ADMIN.GPS_REALTIME
(
  ID         VARCHAR2(32) not null,
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 832K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.GPS_REALTIME.NAME
  is '设备名称';
comment on column ADMIN.GPS_REALTIME.TELNUMBER
  is 'GPRS号';
comment on column ADMIN.GPS_REALTIME.SERIAL_ID
  is '设备序列号';
comment on column ADMIN.GPS_REALTIME.CARTYPE
  is '类型(见数据字典policecartype)';
comment on column ADMIN.GPS_REALTIME.ORG_ID
  is '部门Id';
comment on column ADMIN.GPS_REALTIME.CALLNUMBER
  is '电台呼号';
comment on column ADMIN.GPS_REALTIME.FREQUENCY
  is '电台频段';
comment on column ADMIN.GPS_REALTIME.GPSTIME
  is 'GPS时间';
comment on column ADMIN.GPS_REALTIME.LATITUDE
  is '纬度';
comment on column ADMIN.GPS_REALTIME.LONGITUDE
  is '经度';
comment on column ADMIN.GPS_REALTIME.ALTITUDE
  is '高度';
comment on column ADMIN.GPS_REALTIME.VELOCITY
  is '速度';
comment on column ADMIN.GPS_REALTIME.ANGLE
  is '方位角';
comment on column ADMIN.GPS_REALTIME.ISONLINE
  is '是否在线';
comment on column ADMIN.GPS_REALTIME.GPSSTATE
  is 'GPS状态 ';
alter table ADMIN.GPS_REALTIME
  add constraint GPS_REALTIME_PK primary key (ID)
  using index 
  tablespace GPS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 448K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.GPS_REALTIME
  add constraint SERIAL_ID_UNIQUE unique (SERIAL_ID)
  using index 
  tablespace GPS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 256K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.GPS_REALTIME
  add constraint TELNUMBER_UNIQUE unique (TELNUMBER)
  using index 
  tablespace GPS
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
create index ADMIN.IS_ONLINE_INDEX on ADMIN.GPS_REALTIME (ISONLINE)
  tablespace GPS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 192K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.ORG_INDEX on ADMIN.GPS_REALTIME (ORG_ID)
  tablespace GPS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 448K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table GPS_REALTIME_ISEXTENT
prompt ====================================
prompt
create table ADMIN.GPS_REALTIME_ISEXTENT
(
  ID             VARCHAR2(32),
  ISEXTENT       VARCHAR2(4) default 0,
  EXTENT_TYPE    CHAR(1),
  EXTENTSCOPE    VARCHAR2(50),
  EXTENTSCOPE_ID VARCHAR2(32)
)
tablespace GPS
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
comment on column ADMIN.GPS_REALTIME_ISEXTENT.ID
  is '警员id或警车id';
comment on column ADMIN.GPS_REALTIME_ISEXTENT.ISEXTENT
  is '0：正常 1：越界';
comment on column ADMIN.GPS_REALTIME_ISEXTENT.EXTENT_TYPE
  is '0：电子围栏  1：网格   2：岗位';
comment on column ADMIN.GPS_REALTIME_ISEXTENT.EXTENTSCOPE
  is '越界范围';
comment on column ADMIN.GPS_REALTIME_ISEXTENT.EXTENTSCOPE_ID
  is '范围id';

prompt
prompt Creating table GPS_RECORD_20140201
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140201
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS6
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
create index ADMIN.INDEX_20140201_TELNUMBER on ADMIN.GPS_RECORD_20140201 (TELNUMBER)
  tablespace GPS6
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
create index ADMIN.RECORD_20140201_ONLINE_INDEX on ADMIN.GPS_RECORD_20140201 (ISONLINE)
  tablespace GPS6
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
create index ADMIN.RECORD_20140201_ORG_INDEX on ADMIN.GPS_RECORD_20140201 (ORG_ID)
  tablespace GPS6
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
create index ADMIN.RECORD_20140201_SERIAL_INDEX on ADMIN.GPS_RECORD_20140201 (SERIAL_ID)
  tablespace GPS6
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
prompt Creating table GPS_RECORD_20140202
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140202
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS7
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
create index ADMIN.INDEX_20140202_TELNUMBER on ADMIN.GPS_RECORD_20140202 (TELNUMBER)
  tablespace GPS7
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
create index ADMIN.RECORD_20140202_ONLINE_INDEX on ADMIN.GPS_RECORD_20140202 (ISONLINE)
  tablespace GPS7
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
create index ADMIN.RECORD_20140202_ORG_INDEX on ADMIN.GPS_RECORD_20140202 (ORG_ID)
  tablespace GPS7
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
create index ADMIN.RECORD_20140202_SERIAL_INDEX on ADMIN.GPS_RECORD_20140202 (SERIAL_ID)
  tablespace GPS7
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
prompt Creating table GPS_RECORD_20140210
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140210
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 10M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.INDEX_20140210_TELNUMBER on ADMIN.GPS_RECORD_20140210 (TELNUMBER)
  tablespace GPS1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 4M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.RECORD_20140210_ONLINE_INDEX on ADMIN.GPS_RECORD_20140210 (ISONLINE)
  tablespace GPS1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 2M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.RECORD_20140210_ORG_INDEX on ADMIN.GPS_RECORD_20140210 (ORG_ID)
  tablespace GPS1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 7M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.RECORD_20140210_SERIAL_INDEX on ADMIN.GPS_RECORD_20140210 (SERIAL_ID)
  tablespace GPS1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 3M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table GPS_RECORD_20140211
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140211
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS2
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 15M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.INDEX_20140211_TELNUMBER on ADMIN.GPS_RECORD_20140211 (TELNUMBER)
  tablespace GPS2
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 5M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.RECORD_20140211_ONLINE_INDEX on ADMIN.GPS_RECORD_20140211 (ISONLINE)
  tablespace GPS2
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 2M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.RECORD_20140211_ORG_INDEX on ADMIN.GPS_RECORD_20140211 (ORG_ID)
  tablespace GPS2
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 9M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index ADMIN.RECORD_20140211_SERIAL_INDEX on ADMIN.GPS_RECORD_20140211 (SERIAL_ID)
  tablespace GPS2
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 5M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table GPS_RECORD_20140212
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140212
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS3
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140212_TELNUMBER on ADMIN.GPS_RECORD_20140212 (TELNUMBER)
  tablespace GPS3
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140212_ONLINE_INDEX on ADMIN.GPS_RECORD_20140212 (ISONLINE)
  tablespace GPS3
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140212_ORG_INDEX on ADMIN.GPS_RECORD_20140212 (ORG_ID)
  tablespace GPS3
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140212_SERIAL_INDEX on ADMIN.GPS_RECORD_20140212 (SERIAL_ID)
  tablespace GPS3
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140213
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140213
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS4
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140213_TELNUMBER on ADMIN.GPS_RECORD_20140213 (TELNUMBER)
  tablespace GPS4
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140213_ONLINE_INDEX on ADMIN.GPS_RECORD_20140213 (ISONLINE)
  tablespace GPS4
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140213_ORG_INDEX on ADMIN.GPS_RECORD_20140213 (ORG_ID)
  tablespace GPS4
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140213_SERIAL_INDEX on ADMIN.GPS_RECORD_20140213 (SERIAL_ID)
  tablespace GPS4
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140214
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140214
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS5
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140214_TELNUMBER on ADMIN.GPS_RECORD_20140214 (TELNUMBER)
  tablespace GPS5
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140214_ONLINE_INDEX on ADMIN.GPS_RECORD_20140214 (ISONLINE)
  tablespace GPS5
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140214_ORG_INDEX on ADMIN.GPS_RECORD_20140214 (ORG_ID)
  tablespace GPS5
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140214_SERIAL_INDEX on ADMIN.GPS_RECORD_20140214 (SERIAL_ID)
  tablespace GPS5
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140215
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140215
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS6
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140215_TELNUMBER on ADMIN.GPS_RECORD_20140215 (TELNUMBER)
  tablespace GPS6
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140215_ONLINE_INDEX on ADMIN.GPS_RECORD_20140215 (ISONLINE)
  tablespace GPS6
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140215_ORG_INDEX on ADMIN.GPS_RECORD_20140215 (ORG_ID)
  tablespace GPS6
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140215_SERIAL_INDEX on ADMIN.GPS_RECORD_20140215 (SERIAL_ID)
  tablespace GPS6
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140216
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140216
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS7
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140216_TELNUMBER on ADMIN.GPS_RECORD_20140216 (TELNUMBER)
  tablespace GPS7
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140216_ONLINE_INDEX on ADMIN.GPS_RECORD_20140216 (ISONLINE)
  tablespace GPS7
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140216_ORG_INDEX on ADMIN.GPS_RECORD_20140216 (ORG_ID)
  tablespace GPS7
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140216_SERIAL_INDEX on ADMIN.GPS_RECORD_20140216 (SERIAL_ID)
  tablespace GPS7
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140217
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140217
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140217_TELNUMBER on ADMIN.GPS_RECORD_20140217 (TELNUMBER)
  tablespace GPS1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140217_ONLINE_INDEX on ADMIN.GPS_RECORD_20140217 (ISONLINE)
  tablespace GPS1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140217_ORG_INDEX on ADMIN.GPS_RECORD_20140217 (ORG_ID)
  tablespace GPS1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140217_SERIAL_INDEX on ADMIN.GPS_RECORD_20140217 (SERIAL_ID)
  tablespace GPS1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140218
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140218
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS2
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140218_TELNUMBER on ADMIN.GPS_RECORD_20140218 (TELNUMBER)
  tablespace GPS2
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140218_ONLINE_INDEX on ADMIN.GPS_RECORD_20140218 (ISONLINE)
  tablespace GPS2
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140218_ORG_INDEX on ADMIN.GPS_RECORD_20140218 (ORG_ID)
  tablespace GPS2
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140218_SERIAL_INDEX on ADMIN.GPS_RECORD_20140218 (SERIAL_ID)
  tablespace GPS2
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140219
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140219
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS3
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140219_TELNUMBER on ADMIN.GPS_RECORD_20140219 (TELNUMBER)
  tablespace GPS3
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140219_ONLINE_INDEX on ADMIN.GPS_RECORD_20140219 (ISONLINE)
  tablespace GPS3
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140219_ORG_INDEX on ADMIN.GPS_RECORD_20140219 (ORG_ID)
  tablespace GPS3
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140219_SERIAL_INDEX on ADMIN.GPS_RECORD_20140219 (SERIAL_ID)
  tablespace GPS3
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140220
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140220
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS4
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140220_TELNUMBER on ADMIN.GPS_RECORD_20140220 (TELNUMBER)
  tablespace GPS4
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140220_ONLINE_INDEX on ADMIN.GPS_RECORD_20140220 (ISONLINE)
  tablespace GPS4
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140220_ORG_INDEX on ADMIN.GPS_RECORD_20140220 (ORG_ID)
  tablespace GPS4
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140220_SERIAL_INDEX on ADMIN.GPS_RECORD_20140220 (SERIAL_ID)
  tablespace GPS4
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140221
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140221
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS5
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140221_TELNUMBER on ADMIN.GPS_RECORD_20140221 (TELNUMBER)
  tablespace GPS5
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140221_ONLINE_INDEX on ADMIN.GPS_RECORD_20140221 (ISONLINE)
  tablespace GPS5
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140221_ORG_INDEX on ADMIN.GPS_RECORD_20140221 (ORG_ID)
  tablespace GPS5
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140221_SERIAL_INDEX on ADMIN.GPS_RECORD_20140221 (SERIAL_ID)
  tablespace GPS5
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140222
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140222
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS6
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140222_TELNUMBER on ADMIN.GPS_RECORD_20140222 (TELNUMBER)
  tablespace GPS6
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140222_ONLINE_INDEX on ADMIN.GPS_RECORD_20140222 (ISONLINE)
  tablespace GPS6
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140222_ORG_INDEX on ADMIN.GPS_RECORD_20140222 (ORG_ID)
  tablespace GPS6
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140222_SERIAL_INDEX on ADMIN.GPS_RECORD_20140222 (SERIAL_ID)
  tablespace GPS6
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140223
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140223
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(10)
)
tablespace GPS7
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.INDEX_20140223_TELNUMBER on ADMIN.GPS_RECORD_20140223 (TELNUMBER)
  tablespace GPS7
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140223_ONLINE_INDEX on ADMIN.GPS_RECORD_20140223 (ISONLINE)
  tablespace GPS7
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140223_ORG_INDEX on ADMIN.GPS_RECORD_20140223 (ORG_ID)
  tablespace GPS7
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );
create index ADMIN.RECORD_20140223_SERIAL_INDEX on ADMIN.GPS_RECORD_20140223 (SERIAL_ID)
  tablespace GPS7
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table GPS_RECORD_20140224
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140224
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(20)
)
tablespace GPS1
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
create index ADMIN.INDEX_20140224_TELNUMBER on ADMIN.GPS_RECORD_20140224 (TELNUMBER)
  tablespace GPS1
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
create index ADMIN.RECORD_20140224_ONLINE_INDEX on ADMIN.GPS_RECORD_20140224 (ISONLINE)
  tablespace GPS1
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
create index ADMIN.RECORD_20140224_ORG_INDEX on ADMIN.GPS_RECORD_20140224 (ORG_ID)
  tablespace GPS1
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
create index ADMIN.RECORD_20140224_SERIAL_INDEX on ADMIN.GPS_RECORD_20140224 (SERIAL_ID)
  tablespace GPS1
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
prompt Creating table GPS_RECORD_20140225
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140225
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(20)
)
tablespace GPS2
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
create index ADMIN.INDEX_20140225_TELNUMBER on ADMIN.GPS_RECORD_20140225 (TELNUMBER)
  tablespace GPS2
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
create index ADMIN.RECORD_20140225_ONLINE_INDEX on ADMIN.GPS_RECORD_20140225 (ISONLINE)
  tablespace GPS2
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
create index ADMIN.RECORD_20140225_ORG_INDEX on ADMIN.GPS_RECORD_20140225 (ORG_ID)
  tablespace GPS2
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
create index ADMIN.RECORD_20140225_SERIAL_INDEX on ADMIN.GPS_RECORD_20140225 (SERIAL_ID)
  tablespace GPS2
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
prompt Creating table GPS_RECORD_20140226
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140226
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(20)
)
tablespace GPS3
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
create index ADMIN.INDEX_20140226_TELNUMBER on ADMIN.GPS_RECORD_20140226 (TELNUMBER)
  tablespace GPS3
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
create index ADMIN.RECORD_20140226_ONLINE_INDEX on ADMIN.GPS_RECORD_20140226 (ISONLINE)
  tablespace GPS3
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
create index ADMIN.RECORD_20140226_ORG_INDEX on ADMIN.GPS_RECORD_20140226 (ORG_ID)
  tablespace GPS3
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
create index ADMIN.RECORD_20140226_SERIAL_INDEX on ADMIN.GPS_RECORD_20140226 (SERIAL_ID)
  tablespace GPS3
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
prompt Creating table GPS_RECORD_20140227
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140227
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(20)
)
tablespace GPS4
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
create index ADMIN.INDEX_20140227_TELNUMBER on ADMIN.GPS_RECORD_20140227 (TELNUMBER)
  tablespace GPS4
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
create index ADMIN.RECORD_20140227_ONLINE_INDEX on ADMIN.GPS_RECORD_20140227 (ISONLINE)
  tablespace GPS4
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
create index ADMIN.RECORD_20140227_ORG_INDEX on ADMIN.GPS_RECORD_20140227 (ORG_ID)
  tablespace GPS4
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
create index ADMIN.RECORD_20140227_SERIAL_INDEX on ADMIN.GPS_RECORD_20140227 (SERIAL_ID)
  tablespace GPS4
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
prompt Creating table GPS_RECORD_20140228
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140228
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(20)
)
tablespace GPS5
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
create index ADMIN.INDEX_20140228_TELNUMBER on ADMIN.GPS_RECORD_20140228 (TELNUMBER)
  tablespace GPS5
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
create index ADMIN.RECORD_20140228_ONLINE_INDEX on ADMIN.GPS_RECORD_20140228 (ISONLINE)
  tablespace GPS5
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
create index ADMIN.RECORD_20140228_ORG_INDEX on ADMIN.GPS_RECORD_20140228 (ORG_ID)
  tablespace GPS5
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
create index ADMIN.RECORD_20140228_SERIAL_INDEX on ADMIN.GPS_RECORD_20140228 (SERIAL_ID)
  tablespace GPS5
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
prompt Creating table GPS_RECORD_20140301
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140301
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(20)
)
tablespace GPS6
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
create index ADMIN.INDEX_20140301_TELNUMBER on ADMIN.GPS_RECORD_20140301 (TELNUMBER)
  tablespace GPS6
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
create index ADMIN.RECORD_20140301_ONLINE_INDEX on ADMIN.GPS_RECORD_20140301 (ISONLINE)
  tablespace GPS6
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
create index ADMIN.RECORD_20140301_ORG_INDEX on ADMIN.GPS_RECORD_20140301 (ORG_ID)
  tablespace GPS6
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
create index ADMIN.RECORD_20140301_SERIAL_INDEX on ADMIN.GPS_RECORD_20140301 (SERIAL_ID)
  tablespace GPS6
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
prompt Creating table GPS_RECORD_20140302
prompt ==================================
prompt
create table ADMIN.GPS_RECORD_20140302
(
  NAME       VARCHAR2(50) not null,
  TELNUMBER  VARCHAR2(20) not null,
  SERIAL_ID  VARCHAR2(100) not null,
  CARTYPE    VARCHAR2(32) not null,
  ORG_ID     VARCHAR2(32) not null,
  CALLNUMBER CHAR(11),
  FREQUENCY  VARCHAR2(50),
  GPSTIME    TIMESTAMP(6),
  LATITUDE   VARCHAR2(20),
  LONGITUDE  VARCHAR2(20),
  ALTITUDE   VARCHAR2(10),
  VELOCITY   VARCHAR2(10),
  ANGLE      VARCHAR2(10),
  ISONLINE   CHAR(1) not null,
  GPSSTATE   VARCHAR2(20)
)
tablespace GPS7
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
create index ADMIN.INDEX_20140302_TELNUMBER on ADMIN.GPS_RECORD_20140302 (TELNUMBER)
  tablespace GPS7
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
create index ADMIN.RECORD_20140302_ONLINE_INDEX on ADMIN.GPS_RECORD_20140302 (ISONLINE)
  tablespace GPS7
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
create index ADMIN.RECORD_20140302_ORG_INDEX on ADMIN.GPS_RECORD_20140302 (ORG_ID)
  tablespace GPS7
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
create index ADMIN.RECORD_20140302_SERIAL_INDEX on ADMIN.GPS_RECORD_20140302 (SERIAL_ID)
  tablespace GPS7
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
prompt Creating table OM_BASE_GIS_TYPE
prompt ===============================
prompt
create table ADMIN.OM_BASE_GIS_TYPE
(
  ID            NUMBER not null,
  GIS_TYPE_NAME VARCHAR2(10),
  NOTE          VARCHAR2(100)
)
tablespace OM
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
alter table ADMIN.OM_BASE_GIS_TYPE
  add constraint OM_GIS_ORDER_TYPE_ID primary key (ID)
  using index 
  tablespace OM
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
prompt Creating table OM_BASE_ORDER_TYPE
prompt =================================
prompt
create table ADMIN.OM_BASE_ORDER_TYPE
(
  ID           NUMBER not null,
  ORDER_NAME   VARCHAR2(20),
  GIS_TYPE_ID  NUMBER,
  MAP_URL      VARCHAR2(200),
  AVAILABITITY CHAR(1),
  NOTE         VARCHAR2(500)
)
tablespace OM
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
alter table ADMIN.OM_BASE_ORDER_TYPE
  add constraint PK_OM_ORDER_TYPE_ID primary key (ID)
  using index 
  tablespace OM
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
prompt Creating table OM_CAST_STATION
prompt ==============================
prompt
create table ADMIN.OM_CAST_STATION
(
  ORDER_MANAGER_ID VARCHAR2(32) not null,
  USERNAME         VARCHAR2(1000),
  USERID           VARCHAR2(1000),
  CAST_START_TIME  DATE,
  CAST_END_TIME    DATE,
  REASON           VARCHAR2(1000),
  ID               VARCHAR2(32) not null
)
tablespace OM
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
comment on column ADMIN.OM_CAST_STATION.USERNAME
  is '警员姓名';
comment on column ADMIN.OM_CAST_STATION.USERID
  is '警员Id';
comment on column ADMIN.OM_CAST_STATION.CAST_START_TIME
  is '脱岗开始时间';
comment on column ADMIN.OM_CAST_STATION.CAST_END_TIME
  is '脱岗结束时间';
comment on column ADMIN.OM_CAST_STATION.REASON
  is '脱岗原因';
alter table ADMIN.OM_CAST_STATION
  add constraint PK_CASE_ID primary key (ID)
  using index 
  tablespace USERS
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
prompt Creating table OM_GPS_COMPARE_RT
prompt ================================
prompt
create table ADMIN.OM_GPS_COMPARE_RT
(
  ID              VARCHAR2(32) default sys_guid() not null,
  DEPT_CODE       VARCHAR2(50),
  STATION_NAME    VARCHAR2(100),
  ORDER_TYPE_NAME VARCHAR2(100),
  TIMES           DATE,
  USER_NAME       VARCHAR2(50),
  PTYPE           CHAR(10),
  SZT             CHAR(2),
  RZT             CHAR(2),
  GZT             CHAR(2),
  STATION_ID      VARCHAR2(32),
  USER_ID         VARCHAR2(32),
  LONGITUDE       VARCHAR2(20),
  LATITUDE        VARCHAR2(20)
)
tablespace OM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 30M
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OM_GPS_COMPARE_RT.DEPT_CODE
  is '所属部门';
comment on column ADMIN.OM_GPS_COMPARE_RT.STATION_NAME
  is '执勤地点';
comment on column ADMIN.OM_GPS_COMPARE_RT.ORDER_TYPE_NAME
  is '岗位类型';
comment on column ADMIN.OM_GPS_COMPARE_RT.TIMES
  is '踩点时间';
comment on column ADMIN.OM_GPS_COMPARE_RT.USER_NAME
  is '警员';
comment on column ADMIN.OM_GPS_COMPARE_RT.PTYPE
  is '警员类型';
comment on column ADMIN.OM_GPS_COMPARE_RT.SZT
  is '设备状态   0：在线、1：离线、2：越界、3：未注册、4：数据错误';
comment on column ADMIN.OM_GPS_COMPARE_RT.RZT
  is '人员状态   1：在岗、0：脱岗'';';
comment on column ADMIN.OM_GPS_COMPARE_RT.GZT
  is '岗位状态   1：在岗、0：脱岗';
comment on column ADMIN.OM_GPS_COMPARE_RT.STATION_ID
  is '岗位模板id';

prompt
prompt Creating table OM_GPS_COMPARE_RT_20131228
prompt =========================================
prompt
create table ADMIN.OM_GPS_COMPARE_RT_20131228
(
  ID              VARCHAR2(32) not null,
  DEPT_CODE       VARCHAR2(50),
  STATION_NAME    VARCHAR2(100),
  ORDER_TYPE_NAME VARCHAR2(100),
  TIMES           DATE,
  USER_NAME       VARCHAR2(50),
  PTYPE           CHAR(10),
  SZT             CHAR(2),
  RZT             CHAR(2),
  GZT             CHAR(2),
  STATION_ID      VARCHAR2(32),
  USER_ID         VARCHAR2(32),
  LONGITUDE       VARCHAR2(20),
  LATITUDE        VARCHAR2(20)
)
tablespace OM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 8M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OM_GPS_COMPARE_RT_20131231
prompt =========================================
prompt
create table ADMIN.OM_GPS_COMPARE_RT_20131231
(
  ID              VARCHAR2(32) not null,
  DEPT_CODE       VARCHAR2(50),
  STATION_NAME    VARCHAR2(100),
  ORDER_TYPE_NAME VARCHAR2(100),
  TIMES           DATE,
  USER_NAME       VARCHAR2(50),
  PTYPE           CHAR(10),
  SZT             CHAR(2),
  RZT             CHAR(2),
  GZT             CHAR(2),
  STATION_ID      VARCHAR2(32),
  USER_ID         VARCHAR2(32),
  LONGITUDE       VARCHAR2(20),
  LATITUDE        VARCHAR2(20)
)
tablespace OM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 17M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OM_GPS_COMPARE_RT_20140101
prompt =========================================
prompt
create table ADMIN.OM_GPS_COMPARE_RT_20140101
(
  ID              VARCHAR2(32) not null,
  DEPT_CODE       VARCHAR2(50),
  STATION_NAME    VARCHAR2(100),
  ORDER_TYPE_NAME VARCHAR2(100),
  TIMES           DATE,
  USER_NAME       VARCHAR2(50),
  PTYPE           CHAR(10),
  SZT             CHAR(2),
  RZT             CHAR(2),
  GZT             CHAR(2),
  STATION_ID      VARCHAR2(32),
  USER_ID         VARCHAR2(32),
  LONGITUDE       VARCHAR2(20),
  LATITUDE        VARCHAR2(20)
)
tablespace OM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table OM_GPS_COMPARE_RT_20140102
prompt =========================================
prompt
create table ADMIN.OM_GPS_COMPARE_RT_20140102
(
  ID              VARCHAR2(32) not null,
  DEPT_CODE       VARCHAR2(50),
  STATION_NAME    VARCHAR2(100),
  ORDER_TYPE_NAME VARCHAR2(100),
  TIMES           DATE,
  USER_NAME       VARCHAR2(50),
  PTYPE           CHAR(10),
  SZT             CHAR(2),
  RZT             CHAR(2),
  GZT             CHAR(2),
  STATION_ID      VARCHAR2(32),
  USER_ID         VARCHAR2(32),
  LONGITUDE       VARCHAR2(20),
  LATITUDE        VARCHAR2(20)
)
tablespace OM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 9M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OM_INSPECT_STATION
prompt =================================
prompt
create table ADMIN.OM_INSPECT_STATION
(
  ORDER_MANAGER_ID VARCHAR2(32) not null,
  USERNAMES        VARCHAR2(1000),
  USERIDS          VARCHAR2(1000),
  RECORDTIME       DATE,
  RECORDUSER       VARCHAR2(50)
)
tablespace OM
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
comment on column ADMIN.OM_INSPECT_STATION.ORDER_MANAGER_ID
  is '排班id';
comment on column ADMIN.OM_INSPECT_STATION.USERNAMES
  is '不在岗警员名称（多）';
comment on column ADMIN.OM_INSPECT_STATION.USERIDS
  is '不在岗警员id（多）';
comment on column ADMIN.OM_INSPECT_STATION.RECORDTIME
  is '查岗时间';
comment on column ADMIN.OM_INSPECT_STATION.RECORDUSER
  is '查岗人';

prompt
prompt Creating table OM_ORDER_MANAGER
prompt ===============================
prompt
create table ADMIN.OM_ORDER_MANAGER
(
  ID                       VARCHAR2(32) not null,
  STATION_NAME             VARCHAR2(50),
  OM_STATION_MANAGER_ID    VARCHAR2(20),
  OM_STATION_RECORDTIME_ID VARCHAR2(20),
  ORDER_TYPE               NUMBER,
  ORG_ID                   VARCHAR2(32),
  ORDER_TIME_START         DATE,
  ORDER_TIME_END           DATE,
  WORKDAY                  DATE,
  RECORD_TIME              DATE,
  STATUS                   CHAR(1) default 0,
  ORDER_TYPE_NAME          VARCHAR2(50),
  DEPT_NAME                VARCHAR2(50)
)
tablespace OM
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
comment on column ADMIN.OM_ORDER_MANAGER.STATION_NAME
  is '岗位名称';
comment on column ADMIN.OM_ORDER_MANAGER.OM_STATION_MANAGER_ID
  is '引用日岗位管理id';
comment on column ADMIN.OM_ORDER_MANAGER.OM_STATION_RECORDTIME_ID
  is '在岗时间id';
comment on column ADMIN.OM_ORDER_MANAGER.ORDER_TYPE
  is '岗位i类型';
comment on column ADMIN.OM_ORDER_MANAGER.ORG_ID
  is '部门编号';
comment on column ADMIN.OM_ORDER_MANAGER.ORDER_TIME_START
  is '执勤开始时间';
comment on column ADMIN.OM_ORDER_MANAGER.ORDER_TIME_END
  is '执勤结束时间';
comment on column ADMIN.OM_ORDER_MANAGER.WORKDAY
  is '执勤日期';
comment on column ADMIN.OM_ORDER_MANAGER.RECORD_TIME
  is '记录时间';
comment on column ADMIN.OM_ORDER_MANAGER.STATUS
  is '0：未审核 1：审核通过';
comment on column ADMIN.OM_ORDER_MANAGER.ORDER_TYPE_NAME
  is '岗位类型名称';
comment on column ADMIN.OM_ORDER_MANAGER.DEPT_NAME
  is '部门名称';
alter table ADMIN.OM_ORDER_MANAGER
  add constraint OM_ORDER_MANAGER_ID_PK primary key (ID)
  using index 
  tablespace OM
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
prompt Creating table OM_ORDER_MANAGER_CAR
prompt ===================================
prompt
create table ADMIN.OM_ORDER_MANAGER_CAR
(
  ORDER_MANAGER_ID VARCHAR2(32),
  CAR_ID           VARCHAR2(32),
  TYPES            VARCHAR2(10),
  CALLNUMBER       VARCHAR2(20),
  GPS_TELNUMBER    VARCHAR2(20),
  PLATE_NUM        VARCHAR2(20)
)
tablespace OM
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
prompt Creating table OM_ORDER_MANAGER_USER
prompt ====================================
prompt
create table ADMIN.OM_ORDER_MANAGER_USER
(
  ORDER_MANAGER_ID VARCHAR2(32),
  USER_ID          VARCHAR2(32),
  USER_NAME        VARCHAR2(50),
  USER_TYPE        VARCHAR2(20),
  CALLNUMBER       VARCHAR2(20),
  GPS_TELNUMBER    VARCHAR2(20),
  TEL_PHONE        VARCHAR2(20),
  USER_CODE        VARCHAR2(20),
  CAR_ID           VARCHAR2(32)
)
tablespace OM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OM_ORDER_MANAGER_USER.USER_ID
  is '编号';
comment on column ADMIN.OM_ORDER_MANAGER_USER.USER_NAME
  is '名字';
comment on column ADMIN.OM_ORDER_MANAGER_USER.USER_TYPE
  is '警员类型';
comment on column ADMIN.OM_ORDER_MANAGER_USER.CALLNUMBER
  is '呼号';
comment on column ADMIN.OM_ORDER_MANAGER_USER.GPS_TELNUMBER
  is 'GPS设备号码';
comment on column ADMIN.OM_ORDER_MANAGER_USER.TEL_PHONE
  is '电话';
comment on column ADMIN.OM_ORDER_MANAGER_USER.USER_CODE
  is '警员编号';
comment on column ADMIN.OM_ORDER_MANAGER_USER.CAR_ID
  is '执勤警车编号';

prompt
prompt Creating table OM_ORDER_RECESS_USER
prompt ===================================
prompt
create table ADMIN.OM_ORDER_RECESS_USER
(
  ID                VARCHAR2(32) not null,
  USER_ID           VARCHAR2(32),
  POLICE_CODE       VARCHAR2(20),
  USER_NAME         VARCHAR2(20),
  ORG_ID            VARCHAR2(32),
  GPS_TELNUMBER     VARCHAR2(20),
  RECESS_START_TIME DATE,
  RECESS_END_TIME   DATE,
  RECESS_RESULT     VARCHAR2(500),
  POLICE_TYPE       VARCHAR2(10),
  CREATE_TIME       DATE,
  CREATE_USER       VARCHAR2(20)
)
tablespace OM
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
prompt Creating table OM_ORG_TEMP
prompt ==========================
prompt
create table ADMIN.OM_ORG_TEMP
(
  ID       VARCHAR2(32) not null,
  PARENTID VARCHAR2(32),
  NAME     VARCHAR2(80) not null,
  STATUS   VARCHAR2(2),
  LEVELS   INTEGER,
  CODE     VARCHAR2(50)
)
tablespace OM
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
alter table ADMIN.OM_ORG_TEMP
  add primary key (ID)
  using index 
  tablespace OM
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
prompt Creating table OM_STATION_MANAGER
prompt =================================
prompt
create table ADMIN.OM_STATION_MANAGER
(
  ID                     VARCHAR2(20) not null,
  STATION_NAME           VARCHAR2(50),
  OM_STATION_TEMPLATE_ID VARCHAR2(20),
  ORDER_TYPE             NUMBER,
  ORG_ID                 VARCHAR2(32),
  AVAILABILITYSTART      DATE,
  AVAILABILITYEND        DATE,
  WORKDAY                DATE,
  NOTE                   VARCHAR2(2000),
  ORDER_TYPE_NAME        VARCHAR2(30),
  DEPT_NAME              VARCHAR2(50)
)
tablespace OM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 384K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.OM_STATION_MANAGER
  add constraint STATION_MANAGER_ID_PK primary key (ID)
  using index 
  tablespace OM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OM_STATION_RECORDTIME
prompt ====================================
prompt
create table ADMIN.OM_STATION_RECORDTIME
(
  ID                  VARCHAR2(20) not null,
  STATION_TEMPLATE_ID VARCHAR2(20) not null,
  RECORD_START        VARCHAR2(10),
  RECORD_END          VARCHAR2(10),
  ORDERSTATUS         CHAR(1) default 0
)
tablespace OM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 192K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.OM_STATION_RECORDTIME.ORDERSTATUS
  is '0：未排班 1：已排班';
alter table ADMIN.OM_STATION_RECORDTIME
  add constraint STATION_RECORD_TIME_ID primary key (ID)
  using index 
  tablespace OM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table OM_STATION_TEMPLATE
prompt ==================================
prompt
create table ADMIN.OM_STATION_TEMPLATE
(
  ID                VARCHAR2(20) not null,
  STATION_NAME      VARCHAR2(50),
  ORDER_TYPE        NUMBER,
  ORG_ID            VARCHAR2(32),
  AVAILABILITYSTART DATE,
  AVAILABILITYEND   DATE,
  AFFIRM_PEOPLE     VARCHAR2(32),
  AFFIRM_STATUS     NUMBER default 0,
  NOTE              VARCHAR2(2000),
  DATA_AVI          VARCHAR2(20),
  HOLDSCOPE         INTEGER default 0,
  ORDER_TYPE_NAME   VARCHAR2(50),
  DEPT_NAME         VARCHAR2(50),
  GEOMETRY          VARCHAR2(32),
  EXTENTS           VARCHAR2(32)
)
tablespace OM
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
comment on column ADMIN.OM_STATION_TEMPLATE.STATION_NAME
  is '岗位地点名称';
comment on column ADMIN.OM_STATION_TEMPLATE.ORDER_TYPE
  is '岗位类型';
comment on column ADMIN.OM_STATION_TEMPLATE.ORG_ID
  is '部门编号';
comment on column ADMIN.OM_STATION_TEMPLATE.AVAILABILITYSTART
  is '有效开始日期';
comment on column ADMIN.OM_STATION_TEMPLATE.AVAILABILITYEND
  is '有效结束日期';
comment on column ADMIN.OM_STATION_TEMPLATE.AFFIRM_PEOPLE
  is '确认人';
comment on column ADMIN.OM_STATION_TEMPLATE.AFFIRM_STATUS
  is '确认状态';
comment on column ADMIN.OM_STATION_TEMPLATE.NOTE
  is '备注';
comment on column ADMIN.OM_STATION_TEMPLATE.DATA_AVI
  is '数据的使用时效 0表示都能使用，1,2,3表示星期一，星期二，星期三都有效';
comment on column ADMIN.OM_STATION_TEMPLATE.HOLDSCOPE
  is '执勤范围';
comment on column ADMIN.OM_STATION_TEMPLATE.ORDER_TYPE_NAME
  is '岗位名称';
comment on column ADMIN.OM_STATION_TEMPLATE.DEPT_NAME
  is '部门名称';
comment on column ADMIN.OM_STATION_TEMPLATE.GEOMETRY
  is '岗位地图位置信息';
comment on column ADMIN.OM_STATION_TEMPLATE.EXTENTS
  is '岗位范围信息';
alter table ADMIN.OM_STATION_TEMPLATE
  add constraint STATION_TEMPLATE_ID_PK primary key (ID)
  using index 
  tablespace OM
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
prompt Creating table T_ANALYSIS
prompt =========================
prompt
create table ADMIN.T_ANALYSIS
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.T_ANALYSIS
  is '此表用于勤务统计';

prompt
prompt Creating table T_ANALYSIS_201311
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201311
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_ANALYSIS_201312
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201312
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_ANALYSIS_201401
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201401
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201402
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201402
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201403
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201403
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201404
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201404
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201405
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201405
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201406
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201406
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201407
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201407
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201408
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201408
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201409
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201409
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201410
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201410
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201411
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201411
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating table T_ANALYSIS_201412
prompt ================================
prompt
create table ADMIN.T_ANALYSIS_201412
(
  ID        VARCHAR2(32) default sys_guid() not null,
  ORG_ID    VARCHAR2(50),
  CARTYPE   VARCHAR2(50),
  TELNUMBER VARCHAR2(4000),
  NAME      VARCHAR2(50),
  STARTTIME DATE,
  ENDTIME   DATE,
  TIMES     VARCHAR2(50) default 0,
  COUNTS    VARCHAR2(50) default 0,
  STATETYPE VARCHAR2(50) default 0
)
tablespace ANALYSIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 32M
  );

prompt
prompt Creating sequence SEQ_GPS_ALARM_GUARD
prompt =====================================
prompt
create sequence ADMIN.SEQ_GPS_ALARM_GUARD
minvalue 1
maxvalue 999999999999999999999999
start with 706
increment by 1
cache 64;

prompt
prompt Creating sequence SEQ_GPS_GUARD
prompt ===============================
prompt
create sequence ADMIN.SEQ_GPS_GUARD
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

prompt
prompt Creating procedure PROC_OM_GPS_COMPARE
prompt ======================================
prompt
CREATE OR REPLACE PROCEDURE ADMIN.proc_om_gps_compare(dayTime varchar2) IS
 tablename varchar(50);
 --stateTimesSql varchar(1000);
    CURSOR csr_plan IS
                
             select a.org_id,
                   a.dept_name,
                   a.station_name,
                   to_char(a.order_time_start, 'yyyy-mm-dd hh24:mi:ss'),
                   to_char(a.order_time_end, 'yyyy-mm-dd hh24:mi:ss'),
                   b.car_id,b.gps_telnumber,b.plate_num,
                   to_char(ROUND(TO_NUMBER(order_time_end - order_time_start) * 24,2)) AS totaltime,
                   0 ttype
             from om_order_manager a, om_order_manager_car b
             where a.id = b.order_manager_id
             and b.gps_telnumber is not null
             and a.order_time_start >= TO_DATE (dayTime||' 00:00:00', 'yyyy-mm-dd HH24:MI:SS')
             and a.order_time_end <= TO_DATE (dayTime||' 23:59:59', 'yyyy-mm-dd HH24:MI:SS')
            union
            select a.org_id,
                   a.dept_name,
                   a.station_name,
                   to_char(a.order_time_start, 'yyyy-mm-dd hh24:mi:ss'),
                   to_char(a.order_time_end, 'yyyy-mm-dd hh24:mi:ss'),
                   b.user_id,b.gps_telnumber,b.user_name,
                   to_char(ROUND(TO_NUMBER(order_time_end - order_time_start) * 24,2)) AS totaltime,
                   1 ttype
             from om_order_manager a, om_order_manager_user b
             where a.id = b.order_manager_id
             and a.order_time_start >= TO_DATE (dayTime||' 00:00:00', 'yyyy-mm-dd HH24:MI:SS')
             and a.order_time_end <= TO_DATE (dayTime||' 23:59:59', 'yyyy-mm-dd HH24:MI:SS')
             and b.gps_telnumber is not null;
/******************************************************************************
   NAME:       proc_om_gps_compare
   PURPOSE:    GPS勤务比对统计表，在每天结束时候统计当天gps岗位执勤情况
******************************************************************************/
   deptcode varchar2(50);
   deptname varchar2(50);
   placename varchar(50);
   startTime varchar2(50);
   endTime varchar2(50);
   planid  varchar(500);
   planname varchar(20);
   totalTime varchar(50) default'0';
   planTime varchar(50);
   ttype   number;
   cartelnumbert varchar2(50) default '0';
   times  number default 0;
   querystr varchar(10000);

BEGIN
   select replace(substr(dayTime,0,7),'-','') into tablename  from dual;
   delete  from gps_om_compare t where  starttime>=TO_DATE(dayTime||' 00:00:00','yyyy-mm-dd hh24:mi:ss') AND endtime <=TO_DATE(dayTime||' 23:59:59','yyyy-mm-dd hh24:mi:ss');
   OPEN csr_plan;
  LOOP
    FETCH csr_plan INTO deptcode,deptname,placename,startTime,endTime,planid,cartelnumbert,planname,planTime,ttype;
    EXIT WHEN csr_plan%NOTFOUND;


   querystr:=' select sum(times)  from t_analysis_'||tablename||' t  where  t.telnumber='''||cartelnumbert||
          ''' and to_date('''||endtime||''',''yyyy-mm-dd hh24:mi:ss'')>t.starttime and to_date('''||starttime||''',''yyyy-mm-dd hh24:mi:ss'')<=t.starttime
            AND T.STATETYPE=0 group by t.telnumber';
  begin
    dbms_output.put(querystr);
    execute immediate querystr into times;
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
      times:=0;
  END;
    insert into gps_om_compare
      (ORGID,
       ORG_NAME,
       STATION_NAME,
       STARTTIME,
       ENDTIME,
       REALTIME_ID,
       CARTYPE,
       statetime,
       TELNUMBER,
       REALTIME_NAME,
       onlinetime,
       PLANTIMES)
    values
      (deptcode,
       deptname,
       placename,
       to_date(startTime, 'yyyy-mm-dd hh24:mi:ss'),
       to_date(endTime, 'yyyy-mm-dd hh24:mi:ss'),
       planid,
       ttype,
       sysdate,
       cartelnumbert,
       planname,
       times,
       to_char(ROUND(TO_NUMBER(to_date(endTime, 'yyyy-mm-dd hh24:mi:ss') - to_date(startTime, 'yyyy-mm-dd hh24:mi:ss')) * 24,2)));

   END LOOP;
  /*CLose the cursor */
  CLOSE csr_plan;
END proc_om_gps_compare;
/

prompt
prompt Creating procedure PROC_OM_GPS_COMPARE_RT
prompt =========================================
prompt
create or replace procedure admin.proc_om_gps_compare_rt is
sdatetime varchar(20);
etime number;
ctime varchar(20);
dtime varchar(20);
sqlstr varchar(4000);
tcount number;
vcount number;
sqls   varchar(200);
vsql   varchar(200);
begin
  select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss'),
         to_char(sysdate, 'yyyy-mm-dd'),
         to_char(sysdate, 'yyyymmdd'),
         to_number(to_char(sysdate,'hh24'))
    into sdatetime,ctime,dtime,etime
    from dual;

  if etime>=7 and  etime<23 then
   delete from om_gps_compare_rt tt where tt.times=to_date(substr(sdatetime,0,length(sdatetime)-1)||'0','yyyy-mm-dd hh24:mi:ss');
   insert into OM_GPS_COMPARE_RT (DEPT_CODE,ORDER_TYPE_NAME,STATION_NAME,STATION_ID,USER_NAME,USER_ID,PTYPE,Longitude,Latitude,SZT,RZT,GZT,TIMES)
  select   dept_code,order_type,station_name,ostid,user_name unames, user_id,user_type ptype,longitude,latitude,
         case when isonline=3 then 3   -- 0：在线，1：离线，2：越界，3：未注册，4:坐标错误 5:设备注册错误
              when isonline=0 then 1
              when isonline=1 and isextent=1 then 2
              when isonline=1 and longitude=0 then 4
              else 0  end  szt,
          case when isonline=3 then 0                   -- '0:脱岗 1:在岗'
              when isonline=0 then 0
              when isonline=1 and isextent=1 then 0
              when isonline=1 and longitude=0 then 0
              else 1 end    rzt,
              SUM(case when isonline=3 then 0
              when isonline=0 then 0
              when isonline=1 and isextent=1 then 0
              when isonline=1 and longitude=0 then 0
              else 1 end) OVEr( PARTITION BY station_name order by order_type)  gzt,
              to_date(substr(sdatetime,0,length(sdatetime)-1)||'0','yyyy-mm-dd hh24:mi:ss')
   from (
      select distinct aa.dept_code,
             aa.station_name,
             aa.ostid,
             aa.geometry,
             order_type,
             order_time_start,
             order_time_end,
             aa.user_name,
             aa.user_type,
             aa.user_id,
             pdanum,
             case
               when isonline is null or pdanum = 1 then
                '3'
               when isonline = 0 then
                '0'
               else
                '1'
             end isonline,
             case when pdanum=1 then '3'
              when (select tt.isextent||''
                             from gps_realtime_isextent tt
                            where tt.EXTENTSCOPE_ID = aa.geometry
                              and tt.extent_type = '2'
                              and  bb.id=tt.id  ) is null then '3'
              else 
               (select tt.isextent||''
                             from gps_realtime_isextent tt
                            where tt.EXTENTSCOPE_ID = aa.geometry
                              and tt.extent_type = '2'
                              and  bb.id=tt.id  )
             end isextent,
             bb.gpstime,
             bb.longitude,
             bb.latitude
        from (
               select distinct a.org_id dept_code,
                      a.dept_name,
                      a.station_name,
                      (select t.geometry from om_station_template t where t.id=c.om_station_template_id) as  geometry,
                      c.om_station_template_id as ostid,
                      a.order_type,
                      b.user_id,
                      a.order_time_start,
                      a.order_time_end,
                      b.user_name,
                      b.user_type,
                      case
                       when b.gps_telnumber is null then
                        '1'
                       else
                        b.gps_telnumber
                     end pdanum
                 from om_order_manager a, om_order_manager_user b,om_station_manager c
                where a.id = b.order_manager_id
                and a.om_station_manager_id=c.id
                and a.status=1
                and a.order_time_start <= sysdate
                and a.order_time_end >= sysdate
               ) aa,
             gps_realtime bb
            -- gps_realtime_isextent cc
       where aa.pdanum = bb.telnumber(+)
        -- and bb.id = cc.id (+)
     );
    elsif etime=23   then
       vsql:='select count(*)  from user_tables t where t.table_name=''OM_GPS_COMPARE_RT_'||dtime||'''';
       execute immediate vsql into tcount ;
       if tcount=0 then
         sqlstr:=' create table OM_GPS_COMPARE_RT_'||dtime||' TABLESPACE OM as select * from OM_GPS_COMPARE_RT';
         execute immediate sqlstr;
         sqls:='truncate table OM_GPS_COMPARE_RT';
         execute immediate sqls;
       end if;
       --select count(*) into vcount from t_gps_user_week_compare guc where guc.recordtime=to_date(ctime,'yyyy-mm-dd');
      --- if vcount=0 then
      --  proc_gps_user_week_compare;
      -- end if;
    end if;
end proc_om_gps_compare_rt;
/

prompt
prompt Creating procedure PROC_STATION_OUTSID_TIMES
prompt ============================================
prompt
create or replace procedure admin.PROC_STATION_OUTSID_TIMES( starttime varchar )
is
 sysdatetime  varchar2(200);
 begin
    select  to_char(to_date(starttime,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') into sysdatetime from dual;
    DELETE FROM GPS_OM_ALARM_TIME TA WHERE 
    TA.STARTTIME>=TO_date(''||sysdatetime||' 00:00:00','yyyy-mm-dd hh24:mi:ss')
    and TA.STARTTIME<=TO_date(''||sysdatetime||' 23:59:59','yyyy-mm-dd hh24:mi:ss');
    insert into GPS_OM_ALARM_TIME(ORGNAME,TELNUMBER,REALTIME_NAME,CARTYPE,STATIONNAME,ORDER_TYPE_NAME,STARTTIME,ENDTIME,TIMES,count)
    select ORG_NAME,
              TELNUMBER,
              NAME,
              CARTYPE,
              STATION_NAME,
              order_type_NAME,
              sterttime,
              endtime,
              round(sum(time_second) / 3600, 3),
              count(*)
         FROM (SELECT distinct t2.ORG_NAME,
                               t2.telnumber,
                               t2.NAME,
                               t2.CARTYPE,
                               t2.STATION_NAME,
                               t2.order_type_NAME,
                               t2.sterttime,
                               t2.endtime,
                               MIN((ROUND(TO_NUMBER(t2.time - t1.time) * 1440 * 60))) AS time_second
                 FROM (SELECT a.ORG_NAME,
                              a.telnumber,
                              a.NAME,
                              a.CARTYPE,
                              a.STATION_NAME,
                              a.order_type_NAME,
                              a.time,
                              a.sterttime,
                              a.endtime
                         FROM gps_alarm_guard a
                        WHERE a.ISEXTENT = 1
                          AND a.time >= a.sterttime
                          AND a.time <= a.endtime
                          AND a.sterttime>=TO_date(''||sysdatetime||' 00:00:00','yyyy-mm-dd hh24:mi:ss')
                          AND a.sterttime<=TO_date(''||sysdatetime||' 23:59:59','yyyy-mm-dd hh24:mi:ss')
                       ) t1
                INNER JOIN (SELECT a.id,
                                  a.ORG_NAME,
                                  a.telnumber,
                                  a.NAME,
                                  a.CARTYPE,
                                  a.STATION_NAME,
                                  a.ORDER_TYPE_NAME,
                                  a.time,
                                  a.sterttime,
                                  a.endtime
                             FROM GPS_ALARM_GUARD a
                            WHERE a.ISEXTENT = 2
                              AND a.time >= a.sterttime
                              AND a.time <= a.endtime
                              AND a.sterttime>=TO_date(''||sysdatetime||' 00:00:00','yyyy-mm-dd hh24:mi:ss')
                              AND a.sterttime<=TO_date(''||sysdatetime||' 23:59:59','yyyy-mm-dd hh24:mi:ss')
                                  
                                  ) t2 ON t1.TELNUMBER =
                                                                        t2.NAME
                                                                    AND t1.sterttime =
                                                                        t2.sterttime
                                                                    AND t1.endtime =
                                                                        t2.endtime
                                                                    AND (ROUND(TO_NUMBER(t2.time -
                                                                                         t1.time) * 1440 * 60)) >= 0
                GROUP BY t2.id,
                         t2.ORG_NAME,
                         t2.telnumber,
                         t2.NAME,
                         t2.CARTYPE,
                         t2.STATION_NAME,
                         t2.order_type_NAME,
                         t2.sterttime,
                         t2.endtime)
        GROUP BY ORG_NAME,
                 telnumber,
                 NAME,
                 CARTYPE,
                 STATION_NAME,
                 order_type_NAME,
                 sterttime,
                 endtime;
    
end PROC_STATION_OUTSID_TIMES;
/


spool off
