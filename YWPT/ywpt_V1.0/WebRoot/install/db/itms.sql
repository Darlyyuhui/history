----------------------------------------------------
-- Export file for user ADMIN                     --
-- Created by Administrator on 2014-2-25, 9:39:49 --
----------------------------------------------------

spool itms.log

prompt
prompt Creating table EMERGENCY_ATTACH_INFO
prompt ====================================
prompt
create table ADMIN.EMERGENCY_ATTACH_INFO
(
  ID         VARCHAR2(32) not null,
  PID        VARCHAR2(32) not null,
  NAME       VARCHAR2(100),
  TYPE       VARCHAR2(200),
  PATH       VARCHAR2(200),
  UPLOADNAME VARCHAR2(100)
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_ATTACH_INFO.NAME
  is '附件名称';
comment on column ADMIN.EMERGENCY_ATTACH_INFO.TYPE
  is '附件类型';
comment on column ADMIN.EMERGENCY_ATTACH_INFO.PATH
  is '附件路径';
comment on column ADMIN.EMERGENCY_ATTACH_INFO.UPLOADNAME
  is '上传名称';
alter table ADMIN.EMERGENCY_ATTACH_INFO
  add constraint PK_EMERGENCY_ATTACH_INFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_CAR
prompt ============================
prompt
create table ADMIN.EMERGENCY_CAR
(
  ID              VARCHAR2(32) not null,
  CARTYPE         VARCHAR2(30),
  UNIT            VARCHAR2(100),
  STATE           VARCHAR2(20),
  POSITION        VARCHAR2(20),
  PRINCIPAL       VARCHAR2(50),
  NUM             VARCHAR2(3),
  SPECIFICATION   VARCHAR2(20),
  PRINCIPALCALL   VARCHAR2(50),
  USE             VARCHAR2(500),
  CONTRACTEDUNITS VARCHAR2(100)
)
tablespace ATMS
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
comment on table ADMIN.EMERGENCY_CAR
  is '应急车辆表';
comment on column ADMIN.EMERGENCY_CAR.ID
  is 'id';
comment on column ADMIN.EMERGENCY_CAR.CARTYPE
  is '车辆类型';
comment on column ADMIN.EMERGENCY_CAR.UNIT
  is '所属单位';
comment on column ADMIN.EMERGENCY_CAR.STATE
  is '状态';
comment on column ADMIN.EMERGENCY_CAR.POSITION
  is '所在位置';
comment on column ADMIN.EMERGENCY_CAR.PRINCIPAL
  is '负责人';
comment on column ADMIN.EMERGENCY_CAR.NUM
  is '数量';
comment on column ADMIN.EMERGENCY_CAR.SPECIFICATION
  is '规格';
comment on column ADMIN.EMERGENCY_CAR.PRINCIPALCALL
  is '负责电话';
comment on column ADMIN.EMERGENCY_CAR.USE
  is '用途';
comment on column ADMIN.EMERGENCY_CAR.CONTRACTEDUNITS
  is '签约单位';
alter table ADMIN.EMERGENCY_CAR
  add constraint EMER_CAR_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_CASE
prompt =============================
prompt
create table ADMIN.EMERGENCY_CASE
(
  ID          VARCHAR2(32) not null,
  FILETYPE    VARCHAR2(2),
  NAME        VARCHAR2(100),
  FILEPATH    VARCHAR2(100),
  CONTEXT     CLOB,
  CASETYPE    VARCHAR2(2),
  INSERTDATE  DATE,
  SUFFIX      VARCHAR2(10),
  SWFFILEPATH VARCHAR2(100)
)
tablespace ATMS
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
comment on table ADMIN.EMERGENCY_CASE
  is '应急案例管理表';
comment on column ADMIN.EMERGENCY_CASE.ID
  is '主键id';
comment on column ADMIN.EMERGENCY_CASE.FILETYPE
  is '文件来源：1是手工录入，0是文件上传';
comment on column ADMIN.EMERGENCY_CASE.NAME
  is '文件名称（文件上传有）';
comment on column ADMIN.EMERGENCY_CASE.FILEPATH
  is '文件存放路径（文件上传有）';
comment on column ADMIN.EMERGENCY_CASE.CONTEXT
  is '文件内容（手工录入用）';
comment on column ADMIN.EMERGENCY_CASE.CASETYPE
  is '案例分类';
comment on column ADMIN.EMERGENCY_CASE.INSERTDATE
  is '录入时间';
comment on column ADMIN.EMERGENCY_CASE.SUFFIX
  is '文件后缀';
comment on column ADMIN.EMERGENCY_CASE.SWFFILEPATH
  is 'swf文件路径(上传文件有)';
alter table ADMIN.EMERGENCY_CASE
  add constraint EM_CASE_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_CATALOG_INFO
prompt =====================================
prompt
create table ADMIN.EMERGENCY_CATALOG_INFO
(
  ID       VARCHAR2(32) not null,
  NAME     VARCHAR2(100) not null,
  DISABLED INTEGER not null,
  PID      VARCHAR2(32) not null,
  USERID   VARCHAR2(32) not null,
  OPERTYPE INTEGER,
  OPERTIME DATE,
  LEVELS   INTEGER not null,
  NOTE     VARCHAR2(220)
)
tablespace ATMS
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
alter table ADMIN.EMERGENCY_CATALOG_INFO
  add constraint PK_EMERGENCY_CATALOG_INFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_DUTY
prompt =============================
prompt
create table ADMIN.EMERGENCY_DUTY
(
  ID            VARCHAR2(32) not null,
  DAYTIME       DATE not null,
  USERS         VARCHAR2(600),
  LEADER        VARCHAR2(50),
  DAYS          VARCHAR2(20),
  LEADER_MOBILE VARCHAR2(20),
  REMARK        VARCHAR2(600)
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_DUTY.DAYTIME
  is '值班时间';
comment on column ADMIN.EMERGENCY_DUTY.USERS
  is '值班员';
comment on column ADMIN.EMERGENCY_DUTY.LEADER
  is '值班领导';
comment on column ADMIN.EMERGENCY_DUTY.DAYS
  is '星期几';
comment on column ADMIN.EMERGENCY_DUTY.LEADER_MOBILE
  is '值班领导手机号';
comment on column ADMIN.EMERGENCY_DUTY.REMARK
  is '备注';
alter table ADMIN.EMERGENCY_DUTY
  add constraint EMERGENCY_DUTY_PK primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_FILE_INFO
prompt ==================================
prompt
create table ADMIN.EMERGENCY_FILE_INFO
(
  ID         VARCHAR2(32) not null,
  FILENAME   VARCHAR2(200) not null,
  CONTENT    VARCHAR2(2000),
  DISABLED   INTEGER not null,
  PID        VARCHAR2(32) not null,
  USERID     VARCHAR2(32) not null,
  OPERTYPE   INTEGER,
  OPERTIME   DATE,
  HASATTACH  INTEGER default 0 not null,
  ATTACHNOTE VARCHAR2(200)
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_FILE_INFO.ID
  is 'id';
comment on column ADMIN.EMERGENCY_FILE_INFO.FILENAME
  is '文件名称';
comment on column ADMIN.EMERGENCY_FILE_INFO.CONTENT
  is '文件内容';
comment on column ADMIN.EMERGENCY_FILE_INFO.DISABLED
  is '是否显示';
comment on column ADMIN.EMERGENCY_FILE_INFO.PID
  is '父节点编号';
comment on column ADMIN.EMERGENCY_FILE_INFO.USERID
  is '用户编号';
comment on column ADMIN.EMERGENCY_FILE_INFO.OPERTYPE
  is '操作类型';
comment on column ADMIN.EMERGENCY_FILE_INFO.OPERTIME
  is '操作时间';
comment on column ADMIN.EMERGENCY_FILE_INFO.HASATTACH
  is '是否包含附件';
comment on column ADMIN.EMERGENCY_FILE_INFO.ATTACHNOTE
  is '附件备注';
alter table ADMIN.EMERGENCY_FILE_INFO
  add constraint PK_EMERGENCY_FILE_INFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_INTELLECTUAL
prompt =====================================
prompt
create table ADMIN.EMERGENCY_INTELLECTUAL
(
  ID                VARCHAR2(32) not null,
  TYPE              VARCHAR2(2),
  NAME              VARCHAR2(100) not null,
  PATH              VARCHAR2(100),
  CONTEXT           CLOB,
  INTELLECTUAL_TYPE VARCHAR2(3),
  USERID            VARCHAR2(32) not null,
  OPERTYPE          INTEGER,
  OPERTIME          DATE,
  SUFFIX            VARCHAR2(10)
)
tablespace ATMS
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
comment on table ADMIN.EMERGENCY_INTELLECTUAL
  is '应急知识管理表';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.ID
  is '主键id';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.TYPE
  is '文件来源：1是手工录入，0是文件上传';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.NAME
  is '文件名称';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.PATH
  is '文件存放路径（文件上传有）';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.CONTEXT
  is '文件内容（手工录入用）';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.INTELLECTUAL_TYPE
  is '知识类型';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.USERID
  is '用户ID';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.OPERTYPE
  is '操作类型';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.OPERTIME
  is '操作时间';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.SUFFIX
  is '文件后缀';
alter table ADMIN.EMERGENCY_INTELLECTUAL
  add constraint EMERGENCY_INTELLECTUAL_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_MATERIAL
prompt =================================
prompt
create table ADMIN.EMERGENCY_MATERIAL
(
  ID              VARCHAR2(32) not null,
  MATERIALTYPE    VARCHAR2(30),
  UNIT            VARCHAR2(100),
  STATE           VARCHAR2(20),
  POSITION        VARCHAR2(20),
  PRINCIPAL       VARCHAR2(50),
  NUM             VARCHAR2(3),
  SPECIFICATION   VARCHAR2(20),
  PRINCIPALCALL   VARCHAR2(50),
  USE             VARCHAR2(500),
  CONTRACTEDUNITS VARCHAR2(100),
  LONGITUDE       VARCHAR2(20),
  LATITUDE        VARCHAR2(20)
)
tablespace ATMS
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
comment on table ADMIN.EMERGENCY_MATERIAL
  is '应急物资表';
comment on column ADMIN.EMERGENCY_MATERIAL.ID
  is ' id';
comment on column ADMIN.EMERGENCY_MATERIAL.MATERIALTYPE
  is '物资类型';
comment on column ADMIN.EMERGENCY_MATERIAL.UNIT
  is '所属单位';
comment on column ADMIN.EMERGENCY_MATERIAL.STATE
  is '状态';
comment on column ADMIN.EMERGENCY_MATERIAL.POSITION
  is '所属位置';
comment on column ADMIN.EMERGENCY_MATERIAL.PRINCIPAL
  is '负责人';
comment on column ADMIN.EMERGENCY_MATERIAL.NUM
  is '数量';
comment on column ADMIN.EMERGENCY_MATERIAL.SPECIFICATION
  is '规格';
comment on column ADMIN.EMERGENCY_MATERIAL.PRINCIPALCALL
  is '负责电话';
comment on column ADMIN.EMERGENCY_MATERIAL.USE
  is '用途';
comment on column ADMIN.EMERGENCY_MATERIAL.CONTRACTEDUNITS
  is '签约单位';
comment on column ADMIN.EMERGENCY_MATERIAL.LONGITUDE
  is '经度';
comment on column ADMIN.EMERGENCY_MATERIAL.LATITUDE
  is '纬度';
alter table ADMIN.EMERGENCY_MATERIAL
  add constraint EMER_MATERIAL_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_PLANS
prompt ==============================
prompt
create table ADMIN.EMERGENCY_PLANS
(
  ID              VARCHAR2(32) not null,
  CODE            VARCHAR2(50),
  NAME            VARCHAR2(500) not null,
  TYPES           VARCHAR2(32),
  KIND            CHAR(1) default 0 not null,
  LEVELS          VARCHAR2(20) not null,
  CREATE_DATE     TIMESTAMP(6) default sysdate,
  CREATE_USERID   VARCHAR2(32),
  UPDATE_DATE     TIMESTAMP(6),
  UPDATE_USERID   VARCHAR2(32),
  VERSIONS        NUMBER not null,
  ORG_ID          VARCHAR2(32) not null,
  ORG_NAME        VARCHAR2(500),
  KEYWORDS        VARCHAR2(1000),
  STATUS          VARCHAR2(10) default 0 not null,
  PUBLISH_DATE    TIMESTAMP(6),
  PUBLISH_USER    VARCHAR2(32),
  SUIT_USE        VARCHAR2(1000),
  START_CONDITION VARCHAR2(1000),
  CASES           VARCHAR2(1000),
  AUDIT_STATUS    VARCHAR2(1) default 0
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_PLANS.CODE
  is '预案编码';
comment on column ADMIN.EMERGENCY_PLANS.NAME
  is '预案名称';
comment on column ADMIN.EMERGENCY_PLANS.TYPES
  is '预案类型';
comment on column ADMIN.EMERGENCY_PLANS.KIND
  is '0表示文字预案   1表示数字预案';
comment on column ADMIN.EMERGENCY_PLANS.LEVELS
  is '预案级别';
comment on column ADMIN.EMERGENCY_PLANS.CREATE_DATE
  is '创建日期';
comment on column ADMIN.EMERGENCY_PLANS.CREATE_USERID
  is '创建人';
comment on column ADMIN.EMERGENCY_PLANS.UPDATE_DATE
  is '修改日期';
comment on column ADMIN.EMERGENCY_PLANS.UPDATE_USERID
  is '修改人';
comment on column ADMIN.EMERGENCY_PLANS.VERSIONS
  is '版本号';
comment on column ADMIN.EMERGENCY_PLANS.ORG_ID
  is '部门id';
comment on column ADMIN.EMERGENCY_PLANS.ORG_NAME
  is '部门名称';
comment on column ADMIN.EMERGENCY_PLANS.KEYWORDS
  is '关键字';
comment on column ADMIN.EMERGENCY_PLANS.STATUS
  is '状态0 未启用  1启用  2停用 3删除
';
comment on column ADMIN.EMERGENCY_PLANS.PUBLISH_DATE
  is '发布日期';
comment on column ADMIN.EMERGENCY_PLANS.PUBLISH_USER
  is '发布人';
comment on column ADMIN.EMERGENCY_PLANS.SUIT_USE
  is '适用范围';
comment on column ADMIN.EMERGENCY_PLANS.START_CONDITION
  is '启动条件';
comment on column ADMIN.EMERGENCY_PLANS.CASES
  is '相关案例';
comment on column ADMIN.EMERGENCY_PLANS.AUDIT_STATUS
  is '审核状态 0未审核   1提交待审    2审核通过   3 审核未通过   4发布';
alter table ADMIN.EMERGENCY_PLANS
  add constraint EMERGENCY_PLANS_PK primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_PLANS_ATTR
prompt ===================================
prompt
create table ADMIN.EMERGENCY_PLANS_ATTR
(
  ID          VARCHAR2(32) not null,
  CONTENT_TXT CLOB,
  FILE_PATH   VARCHAR2(600)
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_PLANS_ATTR.CONTENT_TXT
  is '预案内容';
comment on column ADMIN.EMERGENCY_PLANS_ATTR.FILE_PATH
  is '附件路径';

prompt
prompt Creating table EMERGENCY_PLANS_ATTR_VERS
prompt ========================================
prompt
create table ADMIN.EMERGENCY_PLANS_ATTR_VERS
(
  ID          VARCHAR2(32) not null,
  CONTENT_TXT CLOB,
  FILE_PATH   VARCHAR2(600)
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_PLANS_ATTR_VERS.CONTENT_TXT
  is '预案内容';
comment on column ADMIN.EMERGENCY_PLANS_ATTR_VERS.FILE_PATH
  is '附件路径';
alter table ADMIN.EMERGENCY_PLANS_ATTR_VERS
  add constraint EMERGENCY_PLANS_ATTR_VERS_PK primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_PLANS_DOC
prompt ==================================
prompt
create table ADMIN.EMERGENCY_PLANS_DOC
(
  ID            VARCHAR2(32) not null,
  PLANS_CODE    VARCHAR2(30) not null,
  START_TIME    TIMESTAMP(6),
  STOP_TIME     TIMESTAMP(6),
  START_USER_ID VARCHAR2(32),
  STOP_USER_ID  VARCHAR2(32),
  ACCIDENT_ID   VARCHAR2(30),
  SUMUP         CLOB
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255;
comment on column ADMIN.EMERGENCY_PLANS_DOC.PLANS_CODE
  is '预案编码';
comment on column ADMIN.EMERGENCY_PLANS_DOC.START_TIME
  is '启动时间';
comment on column ADMIN.EMERGENCY_PLANS_DOC.STOP_TIME
  is '终止时间';
comment on column ADMIN.EMERGENCY_PLANS_DOC.START_USER_ID
  is '启动人';
comment on column ADMIN.EMERGENCY_PLANS_DOC.STOP_USER_ID
  is '终止人';
comment on column ADMIN.EMERGENCY_PLANS_DOC.ACCIDENT_ID
  is '事故编号';
comment on column ADMIN.EMERGENCY_PLANS_DOC.SUMUP
  is '事故档案总结';
alter table ADMIN.EMERGENCY_PLANS_DOC
  add constraint EMERGENCY_PLANS_DOC_PK primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table EMERGENCY_PLANS_ITEM
prompt ===================================
prompt
create table ADMIN.EMERGENCY_PLANS_ITEM
(
  ID            VARCHAR2(32) not null,
  TITLE         VARCHAR2(600) not null,
  CONTENT       VARCHAR2(4000),
  ACTION        VARCHAR2(2000),
  NOTE          VARCHAR2(1000),
  CODE          VARCHAR2(20),
  CHARGER       VARCHAR2(200),
  EXECUTORS     VARCHAR2(500),
  STEP          NUMBER not null,
  STATUS        VARCHAR2(2) default 0 not null,
  EXESTARTTIME  TIMESTAMP(6),
  EXEFINISHTIME TIMESTAMP(6),
  IS_SHOW_GPS   CHAR(1) default 1,
  IS_SHOW_VIDEO CHAR(1) default 1,
  TEAMS         VARCHAR2(2000),
  UINITS        VARCHAR2(2000),
  CARS          VARCHAR2(2000),
  GOODS         VARCHAR2(2000),
  EXPERTS       VARCHAR2(2000),
  KNOWS         VARCHAR2(2000),
  PLANS_ID      VARCHAR2(32) not null
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_PLANS_ITEM.TITLE
  is '标题';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.CONTENT
  is '内容';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.ACTION
  is '处理器识别码';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.NOTE
  is '备注';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.CODE
  is '任务编码';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.CHARGER
  is '负责人';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.EXECUTORS
  is '实施人';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.STEP
  is '步骤标识';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.EXESTARTTIME
  is '开始执行时间';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.EXEFINISHTIME
  is '执行完成时间';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.IS_SHOW_GPS
  is '是否显示gps位置信息';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.IS_SHOW_VIDEO
  is '是否显示相关视频';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.TEAMS
  is '应急队伍';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.UINITS
  is '应急单位,用逗号分隔';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.CARS
  is '应急车辆';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.GOODS
  is '物资';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.EXPERTS
  is '相关专家';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.KNOWS
  is '知识库';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.PLANS_ID
  is '预案id';
create index ADMIN.PLANS_TITLE_STEP_INDEX on ADMIN.EMERGENCY_PLANS_ITEM (TITLE, STEP)
  tablespace ATMS
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
prompt Creating table EMERGENCY_PLANS_ITEM_VERS
prompt ========================================
prompt
create table ADMIN.EMERGENCY_PLANS_ITEM_VERS
(
  ID            VARCHAR2(32) not null,
  TITLE         VARCHAR2(600) not null,
  CONTENT       VARCHAR2(4000),
  ACTION        VARCHAR2(2000),
  NOTE          VARCHAR2(1000),
  CODE          VARCHAR2(20),
  CHARGER       VARCHAR2(200),
  EXECUTORS     VARCHAR2(500),
  STEP          NUMBER not null,
  STATUS        VARCHAR2(2) default 0 not null,
  EXESTARTTIME  TIMESTAMP(6),
  EXEFINISHTIME TIMESTAMP(6),
  IS_SHOW_GPS   CHAR(1) default 1,
  IS_SHOW_VIDEO CHAR(1) default 1,
  TEAMS         VARCHAR2(2000),
  UINITS        VARCHAR2(2000),
  CARS          VARCHAR2(2000),
  GOODS         VARCHAR2(2000),
  EXPERTS       VARCHAR2(2000),
  KNOWS         VARCHAR2(2000),
  PLANS_ID      VARCHAR2(32) not null
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.TITLE
  is '标题';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.CONTENT
  is '内容';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.ACTION
  is '处理器识别码';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.NOTE
  is '备注';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.CODE
  is '任务编码';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.CHARGER
  is '负责人';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.EXECUTORS
  is '实施人';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.STEP
  is '步骤标识';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.EXESTARTTIME
  is '开始执行时间';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.EXEFINISHTIME
  is '执行完成时间';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.IS_SHOW_GPS
  is '是否显示gps位置信息';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.IS_SHOW_VIDEO
  is '是否显示相关视频';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.TEAMS
  is '应急队伍';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.UINITS
  is '应急单位,用逗号分隔';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.CARS
  is '应急车辆';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.GOODS
  is '物资';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.EXPERTS
  is '相关专家';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.KNOWS
  is '知识库';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.PLANS_ID
  is '预案id';
alter table ADMIN.EMERGENCY_PLANS_ITEM_VERS
  add constraint EMERGENCY_PLANS_ITEM_VERS_PK primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_PLANS_VERIFYS
prompt ======================================
prompt
create table ADMIN.EMERGENCY_PLANS_VERIFYS
(
  ID            VARCHAR2(32) not null,
  PLANS_ID      VARCHAR2(50) not null,
  VERIFY_USERID VARCHAR2(32),
  VERIFY_DATE   TIMESTAMP(6),
  VERIFY_NOTE   VARCHAR2(1000),
  VERIFY_STATUS VARCHAR2(50) not null
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS.PLANS_ID
  is '预案ID';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS.VERIFY_USERID
  is '审批人';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS.VERIFY_DATE
  is '审批日期';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS.VERIFY_NOTE
  is '审批意见';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS.VERIFY_STATUS
  is '审批状态   0未审核   1提交待审    2审核通过   3 审核未通过';
alter table ADMIN.EMERGENCY_PLANS_VERIFYS
  add constraint EMERGENCY_VERIFYS_PK primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_PLANS_VERIFYS_VERS
prompt ===========================================
prompt
create table ADMIN.EMERGENCY_PLANS_VERIFYS_VERS
(
  ID            VARCHAR2(32) not null,
  PLANS_ID      VARCHAR2(50) not null,
  VERIFY_USERID VARCHAR2(32),
  VERIFY_DATE   TIMESTAMP(6),
  VERIFY_NOTE   VARCHAR2(1000),
  VERIFY_STATUS VARCHAR2(50) not null
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS_VERS.PLANS_ID
  is '预案ID';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS_VERS.VERIFY_USERID
  is '审批人';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS_VERS.VERIFY_DATE
  is '审批日期';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS_VERS.VERIFY_NOTE
  is '审批意见';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS_VERS.VERIFY_STATUS
  is '审批状态   0未审核   1提交待审    2审核通过   3 审核未通过';
alter table ADMIN.EMERGENCY_PLANS_VERIFYS_VERS
  add constraint EMERGENCY_PLANS_VERIFYS_VERSPK primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table EMERGENCY_PLANS_VERSIONS
prompt =======================================
prompt
create table ADMIN.EMERGENCY_PLANS_VERSIONS
(
  ID              VARCHAR2(32) not null,
  CODE            VARCHAR2(50),
  NAME            VARCHAR2(500) not null,
  TYPES           VARCHAR2(32),
  KIND            CHAR(1) default 0 not null,
  LEVELS          VARCHAR2(20) not null,
  CREATE_DATE     TIMESTAMP(6) default sysdate,
  CREATE_USERID   VARCHAR2(32),
  UPDATE_DATE     TIMESTAMP(6),
  UPDATE_USERID   VARCHAR2(32),
  VERSIONS        NUMBER not null,
  ORG_ID          VARCHAR2(32) not null,
  ORG_NAME        VARCHAR2(500),
  KEYWORDS        VARCHAR2(1000),
  STATUS          VARCHAR2(10) default 0 not null,
  PUBLISH_DATE    TIMESTAMP(6),
  PUBLISH_USER    VARCHAR2(32),
  SUIT_USE        VARCHAR2(1000),
  START_CONDITION VARCHAR2(1000),
  CASES           VARCHAR2(1000),
  AUDIT_STATUS    VARCHAR2(1)
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.CODE
  is '预案编码';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.NAME
  is '预案名称';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.TYPES
  is '预案类型';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.KIND
  is '0表示文字预案   1表示数字预案';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.LEVELS
  is '预案级别';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.CREATE_DATE
  is '创建日期';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.CREATE_USERID
  is '创建人';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.UPDATE_DATE
  is '修改日期';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.UPDATE_USERID
  is '修改人';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.VERSIONS
  is '版本号';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.ORG_ID
  is '部门id';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.ORG_NAME
  is '部门名称';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.KEYWORDS
  is '关键字';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.STATUS
  is '状态0 未启用  1启用  2停用 3删除
';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.PUBLISH_DATE
  is '发布日期';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.PUBLISH_USER
  is '发布人';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.SUIT_USE
  is '适用范围';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.START_CONDITION
  is '启动条件';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.CASES
  is '相关案例';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.AUDIT_STATUS
  is '审核状态';
alter table ADMIN.EMERGENCY_PLANS_VERSIONS
  add constraint EMERGENCY_PLANS_VERSIONS_PK primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_PROFICIENT
prompt ===================================
prompt
create table ADMIN.EMERGENCY_PROFICIENT
(
  ID                  VARCHAR2(32) not null,
  NAME                VARCHAR2(30) not null,
  DEPTNAME            VARCHAR2(100) not null,
  POSITION            VARCHAR2(100),
  SEX                 VARCHAR2(2) not null,
  AGE                 INTEGER,
  FUNCTION            VARCHAR2(100),
  TYPE                VARCHAR2(50),
  SPECIALITY          VARCHAR2(200),
  NOTE                VARCHAR2(2000),
  SPECIALITY_DESC     VARCHAR2(2000),
  WORK_RESUME         VARCHAR2(2000),
  NATION              VARCHAR2(10),
  ORIGIN_PLACE        VARCHAR2(100),
  ID_CARD             VARCHAR2(18),
  MOBILE_CODE         VARCHAR2(20),
  TELEPHONE           VARCHAR2(20),
  EMAIL               VARCHAR2(50),
  POLITICS_FACE       VARCHAR2(50),
  GRADUATE_COLLEGE    VARCHAR2(100),
  TOP_RECORD          VARCHAR2(20),
  JOIN_WORK_TIME      DATE,
  HEALTH_STATUS       VARCHAR2(100),
  REGION              VARCHAR2(100),
  PHOTO_URL           VARCHAR2(200),
  HOME_ADDRESS        VARCHAR2(200),
  COMMUNICATE_ADDRESS VARCHAR2(200)
)
tablespace ATMS
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
comment on column ADMIN.EMERGENCY_PROFICIENT.ID
  is 'id';
comment on column ADMIN.EMERGENCY_PROFICIENT.NAME
  is '姓名';
comment on column ADMIN.EMERGENCY_PROFICIENT.DEPTNAME
  is '所属单位';
comment on column ADMIN.EMERGENCY_PROFICIENT.POSITION
  is '职位';
comment on column ADMIN.EMERGENCY_PROFICIENT.SEX
  is '性别';
comment on column ADMIN.EMERGENCY_PROFICIENT.AGE
  is '年龄';
comment on column ADMIN.EMERGENCY_PROFICIENT.FUNCTION
  is '职能';
comment on column ADMIN.EMERGENCY_PROFICIENT.TYPE
  is '专家类别';
comment on column ADMIN.EMERGENCY_PROFICIENT.SPECIALITY
  is '应急特长';
comment on column ADMIN.EMERGENCY_PROFICIENT.NOTE
  is '备注';
comment on column ADMIN.EMERGENCY_PROFICIENT.SPECIALITY_DESC
  is '特长描述';
comment on column ADMIN.EMERGENCY_PROFICIENT.WORK_RESUME
  is '工作简历';
comment on column ADMIN.EMERGENCY_PROFICIENT.NATION
  is '民族';
comment on column ADMIN.EMERGENCY_PROFICIENT.ORIGIN_PLACE
  is '籍贯';
comment on column ADMIN.EMERGENCY_PROFICIENT.ID_CARD
  is '身份证号码';
comment on column ADMIN.EMERGENCY_PROFICIENT.MOBILE_CODE
  is '手机号码';
comment on column ADMIN.EMERGENCY_PROFICIENT.TELEPHONE
  is '联系电话';
comment on column ADMIN.EMERGENCY_PROFICIENT.EMAIL
  is '电子邮箱';
comment on column ADMIN.EMERGENCY_PROFICIENT.POLITICS_FACE
  is '政治面貌';
comment on column ADMIN.EMERGENCY_PROFICIENT.GRADUATE_COLLEGE
  is '毕业院校';
comment on column ADMIN.EMERGENCY_PROFICIENT.TOP_RECORD
  is '最高学历';
comment on column ADMIN.EMERGENCY_PROFICIENT.JOIN_WORK_TIME
  is '参加工作时间';
comment on column ADMIN.EMERGENCY_PROFICIENT.HEALTH_STATUS
  is '健康状况';
comment on column ADMIN.EMERGENCY_PROFICIENT.REGION
  is '所属区域';
comment on column ADMIN.EMERGENCY_PROFICIENT.PHOTO_URL
  is '照片路径';
comment on column ADMIN.EMERGENCY_PROFICIENT.HOME_ADDRESS
  is '家庭住址';
comment on column ADMIN.EMERGENCY_PROFICIENT.COMMUNICATE_ADDRESS
  is '通信地址';
alter table ADMIN.EMERGENCY_PROFICIENT
  add constraint PK_EMERGENCY_PROFICIENT_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_TEAM
prompt =============================
prompt
create table ADMIN.EMERGENCY_TEAM
(
  ID            VARCHAR2(32) not null,
  PRINCIPAL     VARCHAR2(50),
  NUM           VARCHAR2(3),
  PRINCIPALCALL VARCHAR2(50)
)
tablespace ATMS
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
comment on table ADMIN.EMERGENCY_TEAM
  is '应急队伍';
comment on column ADMIN.EMERGENCY_TEAM.ID
  is 'id';
comment on column ADMIN.EMERGENCY_TEAM.PRINCIPAL
  is '负责人';
comment on column ADMIN.EMERGENCY_TEAM.NUM
  is '数量';
comment on column ADMIN.EMERGENCY_TEAM.PRINCIPALCALL
  is '负责电话';
alter table ADMIN.EMERGENCY_TEAM
  add constraint EMER_TEM_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table EMERGENCY_UNIT
prompt =============================
prompt
create table ADMIN.EMERGENCY_UNIT
(
  ID            VARCHAR2(32) not null,
  UNITNAME      VARCHAR2(100),
  CORPORATE     VARCHAR2(32),
  UNITSHORT     VARCHAR2(50),
  OPERATIONTYPE VARCHAR2(50),
  MANAGETYPE    VARCHAR2(50),
  DUTYCALL      VARCHAR2(20),
  LONGITUDE     VARCHAR2(20),
  LATITUDE      VARCHAR2(20)
)
tablespace ATMS
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
comment on table ADMIN.EMERGENCY_UNIT
  is '应急单位';
comment on column ADMIN.EMERGENCY_UNIT.ID
  is 'id';
comment on column ADMIN.EMERGENCY_UNIT.UNITNAME
  is '单位名称';
comment on column ADMIN.EMERGENCY_UNIT.CORPORATE
  is '法人代表';
comment on column ADMIN.EMERGENCY_UNIT.UNITSHORT
  is '单位简称';
comment on column ADMIN.EMERGENCY_UNIT.OPERATIONTYPE
  is '业务类别';
comment on column ADMIN.EMERGENCY_UNIT.MANAGETYPE
  is '管理类别';
comment on column ADMIN.EMERGENCY_UNIT.DUTYCALL
  is '值班电话';
comment on column ADMIN.EMERGENCY_UNIT.LONGITUDE
  is '经度';
comment on column ADMIN.EMERGENCY_UNIT.LATITUDE
  is '纬度';
alter table ADMIN.EMERGENCY_UNIT
  add constraint EMER_UNIT_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_AUDIBLEALERTDEVICE
prompt ======================================
prompt
create table ADMIN.JTSS_AUDIBLEALERTDEVICE
(
  ID                  VARCHAR2(32) not null,
  ROAD                VARCHAR2(50),
  INTERSECTIONNAME    VARCHAR2(50),
  MOUNTINGORIENTATION VARCHAR2(50),
  COUNSTRUCTIONTIME   DATE,
  COUNSTRUCTIONUNIT   VARCHAR2(50),
  DEVICESIGNALS       VARCHAR2(50),
  DEVICETYPE          VARCHAR2(50),
  VOCALFORM           VARCHAR2(50),
  PICTURESCENE        VARCHAR2(100),
  EQUIPMENTMADE       VARCHAR2(50),
  DESIGNERS           VARCHAR2(50),
  ROADID              VARCHAR2(32) not null,
  STARTPOINT          VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_AUDIBLEALERTDEVICE
  is '盲人过街声响提示装置';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.ID
  is '主键id';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.ROAD
  is '道路';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.INTERSECTIONNAME
  is '路口名称';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.MOUNTINGORIENTATION
  is '安装方位(东侧、西侧、东北侧、西北侧等,数据字典)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.COUNSTRUCTIONTIME
  is '建设时间';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.COUNSTRUCTIONUNIT
  is '建设单位';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.DEVICESIGNALS
  is '设备信号';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.DEVICETYPE
  is '设备类型(内置式、外挂式等,数据字典)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.VOCALFORM
  is '发声形式(国标、其他,数据字典)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.PICTURESCENE
  is '现场图片(图片路径)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.EQUIPMENTMADE
  is '设备产地';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.DESIGNERS
  is '设计人员';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.ROADID
  is '外键id,道路表id';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.STARTPOINT
  is '位置';
alter table ADMIN.JTSS_AUDIBLEALERTDEVICE
  add constraint JTSS_MRGJSXTSZZ primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_AUDIBLEALERTDEVICE_HIS
prompt ==========================================
prompt
create table ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS
(
  ID                  VARCHAR2(32) not null,
  HISID               VARCHAR2(32) not null,
  ROAD                VARCHAR2(50),
  INTERSECTIONNAME    VARCHAR2(50),
  MOUNTINGORIENTATION VARCHAR2(50),
  COUNSTRUCTIONTIME   DATE,
  COUNSTRUCTIONUNIT   VARCHAR2(50),
  DEVICESIGNALS       VARCHAR2(50),
  DEVICETYPE          VARCHAR2(50),
  VOCALFORM           VARCHAR2(50),
  PICTURESCENE        VARCHAR2(100),
  EQUIPMENTMADE       VARCHAR2(50),
  DESIGNERS           VARCHAR2(50),
  ROADID              VARCHAR2(32) not null,
  STARTPOINT          VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS
  is '盲人过街声响提示装置历史数据表';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.ID
  is '主键id';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.HISID
  is '外键';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.ROAD
  is '道路';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.INTERSECTIONNAME
  is '路口名称';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.MOUNTINGORIENTATION
  is '安装方位(东侧、西侧、东北侧、西北侧等,数据字典)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.COUNSTRUCTIONTIME
  is '建设时间';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.COUNSTRUCTIONUNIT
  is '建设单位';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.DEVICESIGNALS
  is '设备信号';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.DEVICETYPE
  is '设备类型(内置式、外挂式等,数据字典)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.VOCALFORM
  is '发声形式(国标、其他,数据字典)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.PICTURESCENE
  is '现场图片(图片路径)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.EQUIPMENTMADE
  is '设备产地';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.DESIGNERS
  is '设计人员';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.ROADID
  is '道路id';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.STARTPOINT
  is '位置';
alter table ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS
  add constraint JTSS_MRGJSXTSZZ_HIS primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_AUDIBLEALERTDEVICE_ROAD
prompt ===========================================
prompt
create table ADMIN.JTSS_AUDIBLEALERTDEVICE_ROAD
(
  ID               VARCHAR2(32) not null,
  ROAD             VARCHAR2(50),
  INTERSECTIONNAME VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_AUDIBLEALERTDEVICE_ROAD
  is '盲人过街声响提示装置道路信息表';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_ROAD.ID
  is '主键id';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_ROAD.ROAD
  is '道路';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_ROAD.INTERSECTIONNAME
  is '路口名称';
alter table ADMIN.JTSS_AUDIBLEALERTDEVICE_ROAD
  add constraint JTSS_MRGJSXTSZZ_ROAD primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_ISOLATIONFACILITIES
prompt =======================================
prompt
create table ADMIN.JTSS_ISOLATIONFACILITIES
(
  ID                     VARCHAR2(32) not null,
  ROADPARTNAME           VARCHAR2(50),
  ORIGIN                 VARCHAR2(50),
  DESTINATION            VARCHAR2(50),
  ROADPARTCODE           VARCHAR2(50),
  TYPE                   VARCHAR2(50),
  RAILHEIGHT             NUMBER(10),
  RAILWIDTH              NUMBER(10),
  MATERIAL               VARCHAR2(50),
  COLOR                  VARCHAR2(50),
  BASELONG               VARCHAR2(50),
  BASEWIDTH              VARCHAR2(50),
  NUM                    NUMBER(10),
  TOOL                   VARCHAR2(2),
  PERSONOPENNUM          NUMBER(10),
  PERSONOPENWIDTH        NUMBER(10),
  CAROPENNUM             NUMBER(10),
  CAROPENWIDTH           NUMBER(10),
  TOTALLENGTH            NUMBER(10),
  PRODUCTIONPLACE        VARCHAR2(50),
  PRODUCER               VARCHAR2(50),
  INSTALLUNIT            VARCHAR2(50),
  INSTALLSTARTDATE       DATE,
  INSTALLENDDATE         DATE,
  CONSTRUCTIONNAME       VARCHAR2(50),
  CHARGECONSTRUCTIONNAME VARCHAR2(50),
  CONSTRUCTIONBEFOREIMG  VARCHAR2(50),
  CONSTRUCTIONAFTERIMG   VARCHAR2(50),
  UNITMAINTENANCE        VARCHAR2(50),
  FECES                  VARCHAR2(2),
  DESIGNUNITS            VARCHAR2(50),
  CONNECT1               VARCHAR2(50),
  CARCROSS               VARCHAR2(500),
  PEOCROSS               VARCHAR2(500),
  ROADCOLORVALUE         VARCHAR2(50),
  STOPCOORD              VARCHAR2(500)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_ISOLATIONFACILITIES
  is '交通隔离设施 ,护栏';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.ID
  is '主键sys_guid()';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.ROADPARTNAME
  is '路段名称';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.ORIGIN
  is '起点（相交路名称）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.DESTINATION
  is '终点（相交路名称）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.ROADPARTCODE
  is '路段编号（按照规则生成）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.TYPE
  is '类型(新建、改造、维修等 数据字典)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.RAILHEIGHT
  is '护栏高度（单位：米）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.RAILWIDTH
  is '护栏长度（单位：米）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.MATERIAL
  is '材质（塑料、钢材等 数据字典）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.COLOR
  is '颜色（白色等）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.BASELONG
  is '底座长（单位：厘米）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.BASEWIDTH
  is '底座宽(单位：厘米)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.NUM
  is '数量(单位：组)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.TOOL
  is '工具(是否是专用工具)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.PERSONOPENNUM
  is '人开口数量(单位：个)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.PERSONOPENWIDTH
  is '人开口长度';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CAROPENNUM
  is '车开口数量';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CAROPENWIDTH
  is '车卡口长度';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.TOTALLENGTH
  is '总长度';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.PRODUCTIONPLACE
  is '产地';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.PRODUCER
  is '生产商';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.INSTALLUNIT
  is '安装单位';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.INSTALLSTARTDATE
  is '安装开始日期';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.INSTALLENDDATE
  is '安装结束日期';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CONSTRUCTIONNAME
  is '施工人姓名';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CHARGECONSTRUCTIONNAME
  is '施工负责人姓名';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CONSTRUCTIONBEFOREIMG
  is '施工前图片';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CONSTRUCTIONAFTERIMG
  is '施工后图片';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.UNITMAINTENANCE
  is '本单位维护';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.FECES
  is '隔离栏是否取消';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.DESIGNUNITS
  is '设计单位';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CONNECT1
  is '连接件(卡件、螺丝等)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CARCROSS
  is '车开口坐标对';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.PEOCROSS
  is '人开口坐标对';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.ROADCOLORVALUE
  is '路段颜色';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.STOPCOORD
  is '停靠点坐标对1';
alter table ADMIN.JTSS_ISOLATIONFACILITIES
  add constraint JTSS_JTGLSSHL primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_ISOLATIONFACILITIES_HIS
prompt ===========================================
prompt
create table ADMIN.JTSS_ISOLATIONFACILITIES_HIS
(
  ID                     VARCHAR2(32) not null,
  HISID                  VARCHAR2(32) not null,
  ROADPARTNAME           VARCHAR2(50),
  ORIGIN                 VARCHAR2(50),
  DESTINATION            VARCHAR2(50),
  ROADPARTCODE           VARCHAR2(50),
  TYPE                   VARCHAR2(50),
  RAILHEIGHT             NUMBER(10),
  RAILWIDTH              NUMBER(10),
  MATERIAL               VARCHAR2(50),
  COLOR                  VARCHAR2(50),
  BASELONG               VARCHAR2(50),
  BASEWIDTH              VARCHAR2(50),
  NUM                    NUMBER(10),
  TOOL                   VARCHAR2(2),
  PERSONOPENNUM          NUMBER(10),
  PERSONOPENWIDTH        NUMBER(10),
  CAROPENNUM             NUMBER(10),
  CAROPENWIDTH           NUMBER(10),
  TOTALLENGTH            NUMBER(10),
  PRODUCTIONPLACE        VARCHAR2(50),
  PRODUCER               VARCHAR2(50),
  INSTALLUNIT            VARCHAR2(50),
  INSTALLSTARTDATE       DATE,
  INSTALLENDDATE         DATE,
  CONSTRUCTIONNAME       VARCHAR2(50),
  CHARGECONSTRUCTIONNAME VARCHAR2(50),
  CONSTRUCTIONBEFOREIMG  VARCHAR2(50),
  CONSTRUCTIONAFTERIMG   VARCHAR2(50),
  UNITMAINTENANCE        VARCHAR2(50),
  FECES                  VARCHAR2(2),
  DESIGNUNITS            VARCHAR2(50),
  CONNECT1               VARCHAR2(50),
  CARCROSS               VARCHAR2(500),
  PEOCROSS               VARCHAR2(500),
  ROADCOLORVALUE         VARCHAR2(50),
  STOPCOORD              VARCHAR2(500)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_ISOLATIONFACILITIES_HIS
  is '交通隔离设施 ,护栏历史数据表';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.ID
  is '主键sys_guid()';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.HISID
  is '外键id';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.ROADPARTNAME
  is '路段名称';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.ORIGIN
  is '起点（相交路名称）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.DESTINATION
  is '终点（相交路名称）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.ROADPARTCODE
  is '路段编号（按照规则生成）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.TYPE
  is '类型(新建、改造、维修等 数据字典)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.RAILHEIGHT
  is '护栏高度（单位：米）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.RAILWIDTH
  is '护栏长度（单位：米）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.MATERIAL
  is '材质（塑料、钢材等 数据字典）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.COLOR
  is '颜色（白色等）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.BASELONG
  is '底座长（单位：厘米）';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.BASEWIDTH
  is '底座宽(单位：厘米)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.NUM
  is '数量(单位：组)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.TOOL
  is '工具(是否是专用工具)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.PERSONOPENNUM
  is '人开口数量(单位：个)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.PERSONOPENWIDTH
  is '人开口长度';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CAROPENNUM
  is '车开口数量';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CAROPENWIDTH
  is '车卡口长度';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.TOTALLENGTH
  is '总长度';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.PRODUCTIONPLACE
  is '产地';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.PRODUCER
  is '生产商';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.INSTALLUNIT
  is '安装单位';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.INSTALLSTARTDATE
  is '安装开始日期';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.INSTALLENDDATE
  is '安装结束日期';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CONSTRUCTIONNAME
  is '施工人姓名';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CHARGECONSTRUCTIONNAME
  is '施工负责人姓名';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CONSTRUCTIONBEFOREIMG
  is '施工前图片';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CONSTRUCTIONAFTERIMG
  is '施工后图片';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.UNITMAINTENANCE
  is '本单位维护';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.FECES
  is '隔离栏是否取消';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.DESIGNUNITS
  is '设计单位';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CONNECT1
  is '连接件(卡件、螺丝等)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CARCROSS
  is '车开口坐标对';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.PEOCROSS
  is '人开口坐标对';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.ROADCOLORVALUE
  is '路段颜色';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.STOPCOORD
  is '停靠点坐标对1';
alter table ADMIN.JTSS_ISOLATIONFACILITIES_HIS
  add constraint JTSS_JTGLSSHL_HIS primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_ISOLATIONFACILITIES_IMAGE
prompt =============================================
prompt
create table ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE
(
  STARTPOINT VARCHAR2(100),
  ENDPOINT   VARCHAR2(100),
  STOPPOINT  VARCHAR2(100),
  CARCROSS   VARCHAR2(100),
  PEOCROSS   VARCHAR2(100),
  ID         VARCHAR2(32) not null
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE
  is '交通隔离设施 护栏图标维护表';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.STARTPOINT
  is '起点';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.ENDPOINT
  is '终点';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.STOPPOINT
  is '停靠点';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.CARCROSS
  is '车开口';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.PEOCROSS
  is '人开口';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.ID
  is 'id ';
alter table ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE
  add constraint JTSS_JTGLSSHL_IMAGE primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_LONGROADMARKING
prompt ===================================
prompt
create table ADMIN.JTSS_LONGROADMARKING
(
  ID                         VARCHAR2(32) not null,
  ROADNAME                   VARCHAR2(50),
  STARTPOINT                 VARCHAR2(50),
  END                        VARCHAR2(50),
  ROADCODE                   VARCHAR2(50),
  ISMARKING                  VARCHAR2(2),
  ROADBOARDS                 VARCHAR2(50),
  PLATES1WIDTH               NUMBER(10),
  PLATES2WIDTH               NUMBER(10),
  PLATES3WIDTH               NUMBER(10),
  PLATES4WIDTH               NUMBER(10),
  LINELENGTH                 NUMBER(10),
  TYPE                       VARCHAR2(10),
  STANDARDWIDTH              NUMBER(10),
  STANDARDCABLELENGTH        NUMBER(10),
  LONGDASHED                 NUMBER(10),
  DASHEDEMPTY                NUMBER(10),
  NUM                        NUMBER(10),
  AREA                       NUMBER(10),
  CONSTRUCTIONTIME           DATE,
  CONSTRUCTIONUNIT           VARCHAR2(50),
  CONSTRUCTIONTYPE           VARCHAR2(50),
  BEFORECONSTRUCTIONPICTURES VARCHAR2(50),
  AFTERCONSTRUCTIONPICTURES  VARCHAR2(50),
  MATERIALTYPE               VARCHAR2(50),
  MATERIALORIGIN             VARCHAR2(50),
  MATERIALFACTORY            VARCHAR2(50),
  MATERIALUNITVOLUME         NUMBER(10),
  TOTALESTIMATEDMATERIAL     NUMBER(10),
  DESIGNUNITS                VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_LONGROADMARKING
  is '路段长标线';
comment on column ADMIN.JTSS_LONGROADMARKING.ID
  is '//主键sys_guid()';
comment on column ADMIN.JTSS_LONGROADMARKING.ROADNAME
  is '路段名称';
comment on column ADMIN.JTSS_LONGROADMARKING.STARTPOINT
  is '起点';
comment on column ADMIN.JTSS_LONGROADMARKING.END
  is '终点';
comment on column ADMIN.JTSS_LONGROADMARKING.ROADCODE
  is '路段编号';
comment on column ADMIN.JTSS_LONGROADMARKING.ISMARKING
  is '是否还有标线(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING.ROADBOARDS
  is '道路块板(道路分为4个块板，分别是1块板....4块板,数据字典)';
comment on column ADMIN.JTSS_LONGROADMARKING.PLATES1WIDTH
  is '块板1的宽度(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING.PLATES2WIDTH
  is '块板2的宽度(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING.PLATES3WIDTH
  is '块板3的宽度(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING.PLATES4WIDTH
  is '块板4的宽度(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING.LINELENGTH
  is '标线长度';
comment on column ADMIN.JTSS_LONGROADMARKING.TYPE
  is '类别(双实线、单实线、虚线等)';
comment on column ADMIN.JTSS_LONGROADMARKING.STANDARDWIDTH
  is '标线宽(单位：CM)';
comment on column ADMIN.JTSS_LONGROADMARKING.STANDARDCABLELENGTH
  is '标线长(=路段长-路口渠化延伸长度*2)';
comment on column ADMIN.JTSS_LONGROADMARKING.LONGDASHED
  is '虚线长(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING.DASHEDEMPTY
  is '虚线空(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING.NUM
  is '数量(单位：条)';
comment on column ADMIN.JTSS_LONGROADMARKING.AREA
  is '面积(平方米)';
comment on column ADMIN.JTSS_LONGROADMARKING.CONSTRUCTIONTIME
  is '施工时间';
comment on column ADMIN.JTSS_LONGROADMARKING.CONSTRUCTIONUNIT
  is '施工单位';
comment on column ADMIN.JTSS_LONGROADMARKING.CONSTRUCTIONTYPE
  is '施工类型(新建、修补等)';
comment on column ADMIN.JTSS_LONGROADMARKING.BEFORECONSTRUCTIONPICTURES
  is '施工前图片';
comment on column ADMIN.JTSS_LONGROADMARKING.AFTERCONSTRUCTIONPICTURES
  is '施工后图片';
comment on column ADMIN.JTSS_LONGROADMARKING.MATERIALTYPE
  is '材质类型';
comment on column ADMIN.JTSS_LONGROADMARKING.MATERIALORIGIN
  is '材质产地';
comment on column ADMIN.JTSS_LONGROADMARKING.MATERIALFACTORY
  is '材质厂家';
comment on column ADMIN.JTSS_LONGROADMARKING.MATERIALUNITVOLUME
  is '材质单位量';
comment on column ADMIN.JTSS_LONGROADMARKING.TOTALESTIMATEDMATERIAL
  is '材质总估算';
comment on column ADMIN.JTSS_LONGROADMARKING.DESIGNUNITS
  is '设计单位 ';
alter table ADMIN.JTSS_LONGROADMARKING
  add constraint JTSS_LDCBX primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_LONGROADMARKING_HIS
prompt =======================================
prompt
create table ADMIN.JTSS_LONGROADMARKING_HIS
(
  ID                         VARCHAR2(32) not null,
  HISID                      VARCHAR2(32) not null,
  ROADNAME                   VARCHAR2(50),
  STARTPOINT                 VARCHAR2(50),
  END                        VARCHAR2(50),
  ROADCODE                   VARCHAR2(50),
  ISMARKING                  VARCHAR2(2),
  ROADBOARDS                 VARCHAR2(50),
  PLATES1WIDTH               NUMBER(10),
  PLATES2WIDTH               NUMBER(10),
  PLATES3WIDTH               NUMBER(10),
  PLATES4WIDTH               NUMBER(10),
  LINELENGTH                 NUMBER(10),
  TYPE                       VARCHAR2(10),
  STANDARDWIDTH              NUMBER(10),
  STANDARDCABLELENGTH        NUMBER(10),
  LONGDASHED                 NUMBER(10),
  DASHEDEMPTY                NUMBER(10),
  NUM                        NUMBER(10),
  AREA                       NUMBER(10),
  CONSTRUCTIONTIME           DATE,
  CONSTRUCTIONUNIT           VARCHAR2(50),
  CONSTRUCTIONTYPE           VARCHAR2(50),
  BEFORECONSTRUCTIONPICTURES VARCHAR2(50),
  AFTERCONSTRUCTIONPICTURES  VARCHAR2(50),
  MATERIALTYPE               VARCHAR2(50),
  MATERIALORIGIN             VARCHAR2(50),
  MATERIALFACTORY            VARCHAR2(50),
  MATERIALUNITVOLUME         NUMBER(10),
  TOTALESTIMATEDMATERIAL     NUMBER(10),
  DESIGNUNITS                VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_LONGROADMARKING_HIS
  is '路段长标线';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.ID
  is '//主键sys_guid()';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.HISID
  is '外键id';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.ROADNAME
  is '路段名称';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.STARTPOINT
  is '起点';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.END
  is '终点';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.ROADCODE
  is '路段编号';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.ISMARKING
  is '是否还有标线(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.ROADBOARDS
  is '道路块板(道路分为4个块板，分别是1块板....4块板,数据字典)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.PLATES1WIDTH
  is '块板1的宽度(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.PLATES2WIDTH
  is '块板2的宽度(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.PLATES3WIDTH
  is '块板3的宽度(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.PLATES4WIDTH
  is '块板4的宽度(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.LINELENGTH
  is '标线长度';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.TYPE
  is '类别(双实线、单实线、虚线等)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.STANDARDWIDTH
  is '标线宽(单位：CM)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.STANDARDCABLELENGTH
  is '标线长(=路段长-路口渠化延伸长度*2)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.LONGDASHED
  is '虚线长(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.DASHEDEMPTY
  is '虚线空(单位：M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.NUM
  is '数量(单位：条)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.AREA
  is '面积(平方米)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.CONSTRUCTIONTIME
  is '施工时间';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.CONSTRUCTIONUNIT
  is '施工单位';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.CONSTRUCTIONTYPE
  is '施工类型(新建、修补等)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.BEFORECONSTRUCTIONPICTURES
  is '施工前图片';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.AFTERCONSTRUCTIONPICTURES
  is '施工后图片';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.MATERIALTYPE
  is '材质类型';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.MATERIALORIGIN
  is '材质产地';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.MATERIALFACTORY
  is '材质厂家';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.MATERIALUNITVOLUME
  is '材质单位量';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.TOTALESTIMATEDMATERIAL
  is '材质总估算';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.DESIGNUNITS
  is '设计单位 ';
alter table ADMIN.JTSS_LONGROADMARKING_HIS
  add constraint JTSS_LDCBX_HIS primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_SHORTROADMARKING
prompt ====================================
prompt
create table ADMIN.JTSS_SHORTROADMARKING
(
  ID                         VARCHAR2(32) not null,
  ROADNAME                   VARCHAR2(50),
  STARTPOINT                 VARCHAR2(50),
  END                        VARCHAR2(50),
  ROADCODE                   VARCHAR2(50),
  ISMARKING                  VARCHAR2(2),
  ROADBOARDS                 VARCHAR2(50),
  PLATES1WIDTH               NUMBER(10),
  PLATES2WIDTH               NUMBER(10),
  PLATES3WIDTH               NUMBER(10),
  PLATES4WIDTH               NUMBER(10),
  LINELENGTH                 NUMBER(10),
  TYPE                       VARCHAR2(10),
  STANDARDWIDTH              NUMBER(10),
  STANDARDCABLELENGTH        NUMBER(10),
  NUM                        NUMBER(10),
  AREA                       NUMBER(10),
  CONSTRUCTIONTIME           DATE,
  CONSTRUCTIONUNIT           VARCHAR2(50),
  CONSTRUCTIONTYPE           VARCHAR2(50),
  BEFORECONSTRUCTIONPICTURES VARCHAR2(50),
  AFTERCONSTRUCTIONPICTURES  VARCHAR2(50),
  MATERIALTYPE               VARCHAR2(50),
  MATERIALORIGIN             VARCHAR2(50),
  MATERIALFACTORY            VARCHAR2(50),
  MATERIALUNITVOLUME         NUMBER(10),
  TOTALESTIMATEDMATERIAL     NUMBER(10),
  MARKINGDESIGNUNITS         VARCHAR2(50),
  ISCANALIZATIONINTERSECTION VARCHAR2(2)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_SHORTROADMARKING
  is '路段短标线';
comment on column ADMIN.JTSS_SHORTROADMARKING.ID
  is '主键sys_guid()';
comment on column ADMIN.JTSS_SHORTROADMARKING.ROADNAME
  is '路段名称';
comment on column ADMIN.JTSS_SHORTROADMARKING.STARTPOINT
  is '起点';
comment on column ADMIN.JTSS_SHORTROADMARKING.END
  is '终点';
comment on column ADMIN.JTSS_SHORTROADMARKING.ROADCODE
  is '路段编号';
comment on column ADMIN.JTSS_SHORTROADMARKING.ISMARKING
  is '是否还有标线';
comment on column ADMIN.JTSS_SHORTROADMARKING.ROADBOARDS
  is '道路块板';
comment on column ADMIN.JTSS_SHORTROADMARKING.PLATES1WIDTH
  is '块板1的宽度';
comment on column ADMIN.JTSS_SHORTROADMARKING.PLATES2WIDTH
  is '块板2的宽度';
comment on column ADMIN.JTSS_SHORTROADMARKING.PLATES3WIDTH
  is '块板3的宽度';
comment on column ADMIN.JTSS_SHORTROADMARKING.PLATES4WIDTH
  is '块板4的宽度';
comment on column ADMIN.JTSS_SHORTROADMARKING.LINELENGTH
  is '标线长度';
comment on column ADMIN.JTSS_SHORTROADMARKING.TYPE
  is '类别';
comment on column ADMIN.JTSS_SHORTROADMARKING.STANDARDWIDTH
  is '标线宽';
comment on column ADMIN.JTSS_SHORTROADMARKING.STANDARDCABLELENGTH
  is '标线长';
comment on column ADMIN.JTSS_SHORTROADMARKING.NUM
  is '数量';
comment on column ADMIN.JTSS_SHORTROADMARKING.AREA
  is '面积';
comment on column ADMIN.JTSS_SHORTROADMARKING.CONSTRUCTIONTIME
  is '施工时间';
comment on column ADMIN.JTSS_SHORTROADMARKING.CONSTRUCTIONUNIT
  is '施工单位';
comment on column ADMIN.JTSS_SHORTROADMARKING.CONSTRUCTIONTYPE
  is '施工类型';
comment on column ADMIN.JTSS_SHORTROADMARKING.BEFORECONSTRUCTIONPICTURES
  is '施工前图片';
comment on column ADMIN.JTSS_SHORTROADMARKING.AFTERCONSTRUCTIONPICTURES
  is '施工后图片';
comment on column ADMIN.JTSS_SHORTROADMARKING.MATERIALTYPE
  is '材质类型';
comment on column ADMIN.JTSS_SHORTROADMARKING.MATERIALORIGIN
  is '材质产地';
comment on column ADMIN.JTSS_SHORTROADMARKING.MATERIALFACTORY
  is '材质厂家';
comment on column ADMIN.JTSS_SHORTROADMARKING.MATERIALUNITVOLUME
  is '材质单位量';
comment on column ADMIN.JTSS_SHORTROADMARKING.TOTALESTIMATEDMATERIAL
  is '材质总估算';
comment on column ADMIN.JTSS_SHORTROADMARKING.MARKINGDESIGNUNITS
  is '标线设计单位';
comment on column ADMIN.JTSS_SHORTROADMARKING.ISCANALIZATIONINTERSECTION
  is '是否是渠化路口(是路口，而且是渠化路口) ';
alter table ADMIN.JTSS_SHORTROADMARKING
  add constraint JTSS_LDDBX primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_SHORTROADMARKING_HIS
prompt ========================================
prompt
create table ADMIN.JTSS_SHORTROADMARKING_HIS
(
  ID                         VARCHAR2(32) not null,
  HISID                      VARCHAR2(32) not null,
  ROADNAME                   VARCHAR2(50),
  STARTPOINT                 VARCHAR2(50),
  END                        VARCHAR2(50),
  ROADCODE                   VARCHAR2(50),
  ISMARKING                  VARCHAR2(2),
  ROADBOARDS                 VARCHAR2(50),
  PLATES1WIDTH               NUMBER(10),
  PLATES2WIDTH               NUMBER(10),
  PLATES3WIDTH               NUMBER(10),
  PLATES4WIDTH               NUMBER(10),
  LINELENGTH                 NUMBER(10),
  TYPE                       VARCHAR2(10),
  STANDARDWIDTH              NUMBER(10),
  STANDARDCABLELENGTH        NUMBER(10),
  NUM                        NUMBER(10),
  AREA                       NUMBER(10),
  CONSTRUCTIONTIME           DATE,
  CONSTRUCTIONUNIT           VARCHAR2(50),
  CONSTRUCTIONTYPE           VARCHAR2(50),
  BEFORECONSTRUCTIONPICTURES VARCHAR2(50),
  AFTERCONSTRUCTIONPICTURES  VARCHAR2(50),
  MATERIALTYPE               VARCHAR2(50),
  MATERIALORIGIN             VARCHAR2(50),
  MATERIALFACTORY            VARCHAR2(50),
  MATERIALUNITVOLUME         NUMBER(10),
  TOTALESTIMATEDMATERIAL     NUMBER(10),
  MARKINGDESIGNUNITS         VARCHAR2(50),
  ISCANALIZATIONINTERSECTION VARCHAR2(2)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_SHORTROADMARKING_HIS
  is '路段短标线';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ID
  is '主键sys_guid()';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ROADNAME
  is '路段名称';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.STARTPOINT
  is '起点';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.END
  is '终点';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ROADCODE
  is '路段编号';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ISMARKING
  is '是否还有标线';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ROADBOARDS
  is '道路块板';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.PLATES1WIDTH
  is '块板1的宽度';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.PLATES2WIDTH
  is '块板2的宽度';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.PLATES3WIDTH
  is '块板3的宽度';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.PLATES4WIDTH
  is '块板4的宽度';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.LINELENGTH
  is '标线长度';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.TYPE
  is '类别';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.STANDARDWIDTH
  is '标线宽';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.STANDARDCABLELENGTH
  is '标线长';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.NUM
  is '数量';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.AREA
  is '面积';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.CONSTRUCTIONTIME
  is '施工时间';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.CONSTRUCTIONUNIT
  is '施工单位';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.CONSTRUCTIONTYPE
  is '施工类型';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.BEFORECONSTRUCTIONPICTURES
  is '施工前图片';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.AFTERCONSTRUCTIONPICTURES
  is '施工后图片';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.MATERIALTYPE
  is '材质类型';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.MATERIALORIGIN
  is '材质产地';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.MATERIALFACTORY
  is '材质厂家';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.MATERIALUNITVOLUME
  is '材质单位量';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.TOTALESTIMATEDMATERIAL
  is '材质总估算';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.MARKINGDESIGNUNITS
  is '标线设计单位';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ISCANALIZATIONINTERSECTION
  is '是否是渠化路口(是路口，而且是渠化路口) ';
alter table ADMIN.JTSS_SHORTROADMARKING_HIS
  add constraint JTSS_LDDBX_HIS primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_TRAFFICSIGNS
prompt ================================
prompt
create table ADMIN.JTSS_TRAFFICSIGNS
(
  ID                         VARCHAR2(32) not null,
  ROADNAME                   VARCHAR2(50),
  STARTPOINT                 VARCHAR2(50),
  END                        VARCHAR2(50),
  LINKNUBER                  VARCHAR2(50),
  TYPE                       VARCHAR2(50),
  FLAGNAME                   VARCHAR2(50),
  FLAGCODE                   VARCHAR2(50),
  INSTALLATION               VARCHAR2(50),
  HIGHCOLUMN                 NUMBER(10),
  COLUMNDIAMETER             NUMBER(10),
  CANTILEVERLENGTH           NUMBER(10),
  CANTILEVERDIAMETER         NUMBER(10),
  LOCATION                   VARCHAR2(50),
  LOCATED                    VARCHAR2(50),
  POSITION                   VARCHAR2(50),
  SHAPE                      VARCHAR2(50),
  LOGOSIDELENGTH             NUMBER(10),
  LOGOSIDEWIDTH              NUMBER(10),
  CONSTRUCTIONTIME           DATE,
  DESIGNUNITS                VARCHAR2(50),
  CONSTRUCTIONUNITS          VARCHAR2(50),
  OVERALLINSTALLATIONDIAGRAM VARCHAR2(100),
  BOARDENLARGERMENT          VARCHAR2(100),
  MATERIAL                   VARCHAR2(50),
  MATERIALORIGIN             VARCHAR2(100),
  ROADID                     VARCHAR2(32)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_TRAFFICSIGNS
  is '交通标志';
comment on column ADMIN.JTSS_TRAFFICSIGNS.ID
  is '主键id sys_guid()';
comment on column ADMIN.JTSS_TRAFFICSIGNS.ROADNAME
  is '路段名';
comment on column ADMIN.JTSS_TRAFFICSIGNS.STARTPOINT
  is '起点';
comment on column ADMIN.JTSS_TRAFFICSIGNS.END
  is '终点';
comment on column ADMIN.JTSS_TRAFFICSIGNS.LINKNUBER
  is '路段编号';
comment on column ADMIN.JTSS_TRAFFICSIGNS.TYPE
  is '类别';
comment on column ADMIN.JTSS_TRAFFICSIGNS.FLAGNAME
  is '名称(标志名称)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.FLAGCODE
  is '标志编号(是否有国标)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.INSTALLATION
  is '安装方式(立柱式、双立柱式、单悬臂式(默认)、双悬臂式、三悬臂式、横梁门式、附着式、其他,数据字典)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.HIGHCOLUMN
  is '立柱高(单位：M)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.COLUMNDIAMETER
  is '立柱直径(单位：CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.CANTILEVERLENGTH
  is '悬臂长(单位：M)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.CANTILEVERDIAMETER
  is '悬臂直径(单位： CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.LOCATION
  is '位置(路段起点距离或者路段重点的距离)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.LOCATED
  is '位于(中央隔离带、机非隔离带、人行道等,数据字典)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.POSITION
  is '方位(东侧、西侧、东北侧、西北侧等,数据字典)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.SHAPE
  is '形状(圆形、方形、三角形、长方形等,数据字典)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.LOGOSIDELENGTH
  is '标志边长/直径(单位：CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.LOGOSIDEWIDTH
  is '标志边宽(有直径的可以为空，单位：cm)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.CONSTRUCTIONTIME
  is '建设时间';
comment on column ADMIN.JTSS_TRAFFICSIGNS.DESIGNUNITS
  is '设计单位';
comment on column ADMIN.JTSS_TRAFFICSIGNS.CONSTRUCTIONUNITS
  is '建设单位';
comment on column ADMIN.JTSS_TRAFFICSIGNS.OVERALLINSTALLATIONDIAGRAM
  is '整体安装图(路径)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.BOARDENLARGERMENT
  is '板面放大图';
comment on column ADMIN.JTSS_TRAFFICSIGNS.MATERIAL
  is '材质';
comment on column ADMIN.JTSS_TRAFFICSIGNS.MATERIALORIGIN
  is '材质产地';
comment on column ADMIN.JTSS_TRAFFICSIGNS.ROADID
  is '路段id(外键) ';
alter table ADMIN.JTSS_TRAFFICSIGNS
  add constraint JTSS_JTBZ primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_TRAFFICSIGNS_HIS
prompt ====================================
prompt
create table ADMIN.JTSS_TRAFFICSIGNS_HIS
(
  ID                         VARCHAR2(32) not null,
  HISID                      VARCHAR2(32) not null,
  ROADNAME                   VARCHAR2(50),
  STARTPOINT                 VARCHAR2(50),
  END                        VARCHAR2(50),
  LINKNUBER                  VARCHAR2(50),
  TYPE                       VARCHAR2(50),
  FLAGNAME                   VARCHAR2(50),
  FLAGCODE                   VARCHAR2(50),
  INSTALLATION               VARCHAR2(50),
  HIGHCOLUMN                 NUMBER(10),
  COLUMNDIAMETER             NUMBER(10),
  CANTILEVERLENGTH           NUMBER(10),
  CANTILEVERDIAMETER         NUMBER(10),
  LOCATION                   VARCHAR2(50),
  LOCATED                    VARCHAR2(50),
  POSITION                   VARCHAR2(50),
  SHAPE                      VARCHAR2(50),
  LOGOSIDELENGTH             NUMBER(10),
  LOGOSIDEWIDTH              NUMBER(10),
  CONSTRUCTIONTIME           DATE,
  DESIGNUNITS                VARCHAR2(50),
  CONSTRUCTIONUNITS          VARCHAR2(50),
  OVERALLINSTALLATIONDIAGRAM VARCHAR2(100),
  BOARDENLARGERMENT          VARCHAR2(100),
  MATERIAL                   VARCHAR2(50),
  MATERIALORIGIN             VARCHAR2(100),
  ROADID                     VARCHAR2(32)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_TRAFFICSIGNS_HIS
  is '交通标志历史数据表';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.ID
  is '主键id sys_guid()';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.HISID
  is '交通标志id';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.ROADNAME
  is '路段名';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.STARTPOINT
  is '起点';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.END
  is '终点';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.LINKNUBER
  is '路段编号';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.TYPE
  is '类别';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.FLAGNAME
  is '名称(标志名称)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.FLAGCODE
  is '标志编号(是否有国标)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.INSTALLATION
  is '安装方式(立柱式、双立柱式、单悬臂式(默认)、双悬臂式、三悬臂式、横梁门式、附着式、其他,数据字典)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.HIGHCOLUMN
  is '立柱高(单位：M)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.COLUMNDIAMETER
  is '立柱直径(单位：CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.CANTILEVERLENGTH
  is '悬臂长(单位：M)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.CANTILEVERDIAMETER
  is '悬臂直径(单位： CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.LOCATION
  is '位置(路段起点距离或者路段重点的距离)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.LOCATED
  is '位于(中央隔离带、机非隔离带、人行道等,数据字典)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.POSITION
  is '方位(东侧、西侧、东北侧、西北侧等,数据字典)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.SHAPE
  is '形状(圆形、方形、三角形、长方形等,数据字典)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.LOGOSIDELENGTH
  is '标志边长/直径(单位：CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.LOGOSIDEWIDTH
  is '标志边宽(有直径的可以为空，单位：cm)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.CONSTRUCTIONTIME
  is '建设时间';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.DESIGNUNITS
  is '设计单位';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.CONSTRUCTIONUNITS
  is '建设单位';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.OVERALLINSTALLATIONDIAGRAM
  is '整体安装图(路径)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.BOARDENLARGERMENT
  is '板面放大图';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.MATERIAL
  is '材质';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.MATERIALORIGIN
  is '材质产地';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.ROADID
  is '路段id(外键) ';
alter table ADMIN.JTSS_TRAFFICSIGNS_HIS
  add constraint JTSS_JTBZ_HIS primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_TRAFFICSIGNS_INFO
prompt =====================================
prompt
create table ADMIN.JTSS_TRAFFICSIGNS_INFO
(
  ID          VARCHAR2(32) not null,
  TRAFFICID   VARCHAR2(32),
  TRAFFICPID  VARCHAR2(32),
  TRAFFICNAME VARCHAR2(50),
  TRAFFICICON VARCHAR2(100),
  LEAF        VARCHAR2(2)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_TRAFFICSIGNS_INFO
  is '交通标志信息维护';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.ID
  is '主键';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.TRAFFICID
  is '标志id';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.TRAFFICPID
  is '父标志id,根节点值为0';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.TRAFFICNAME
  is '标志名称';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.TRAFFICICON
  is '交通标志图标';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.LEAF
  is '是否是叶子节点,0是叶子节点，1是非叶子节点';
alter table ADMIN.JTSS_TRAFFICSIGNS_INFO
  add constraint JTSS_JTBZ_INFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_TRAFFIC_IMAGE
prompt =================================
prompt
create table ADMIN.JTSS_TRAFFIC_IMAGE
(
  ID        VARCHAR2(32) not null,
  NAME      VARCHAR2(50),
  IMAGEPATH VARCHAR2(100),
  CODE      VARCHAR2(32)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_TRAFFIC_IMAGE
  is '交通标志图标';
comment on column ADMIN.JTSS_TRAFFIC_IMAGE.ID
  is '主键';
comment on column ADMIN.JTSS_TRAFFIC_IMAGE.NAME
  is '交通标志图标名称';
comment on column ADMIN.JTSS_TRAFFIC_IMAGE.IMAGEPATH
  is '图片路径';
comment on column ADMIN.JTSS_TRAFFIC_IMAGE.CODE
  is '标志编码';
alter table ADMIN.JTSS_TRAFFIC_IMAGE
  add constraint JTSS_JTBZ_IMAGE primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_TRAFFIC_ROAD
prompt ================================
prompt
create table ADMIN.JTSS_TRAFFIC_ROAD
(
  ID         VARCHAR2(32) not null,
  ROADNAME   VARCHAR2(50),
  STARTPOINT VARCHAR2(50),
  END        VARCHAR2(50),
  LINKNUBER  VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_TRAFFIC_ROAD
  is '交通标志路段表';
comment on column ADMIN.JTSS_TRAFFIC_ROAD.ID
  is '主键id';
comment on column ADMIN.JTSS_TRAFFIC_ROAD.ROADNAME
  is '路段名称';
comment on column ADMIN.JTSS_TRAFFIC_ROAD.STARTPOINT
  is '起点';
comment on column ADMIN.JTSS_TRAFFIC_ROAD.END
  is '终点';
comment on column ADMIN.JTSS_TRAFFIC_ROAD.LINKNUBER
  is '路段编号 ';
alter table ADMIN.JTSS_TRAFFIC_ROAD
  add constraint JTSS_JTBZ_ROAD primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_VEHICLEDETECTOR
prompt ===================================
prompt
create table ADMIN.JTSS_VEHICLEDETECTOR
(
  ID                  VARCHAR2(32) not null,
  ROADNAME            VARCHAR2(50),
  INTERSECTIONNUMBERS VARCHAR2(50),
  MOUNTINGPOINTS      VARCHAR2(50),
  MOUNTINGORIENTATION VARCHAR2(50),
  CONSTRUCTIONTIME    DATE,
  CONSTRUCTIONUNIT    VARCHAR2(50),
  DEVICENAME          VARCHAR2(50),
  DEVICETYPE          VARCHAR2(50),
  FACTORYNUMBER       VARCHAR2(50),
  SINCENUMBER         VARCHAR2(50),
  DEVICELOCATED       VARCHAR2(50),
  NUM                 NUMBER(10),
  SCENEPHOTOS         VARCHAR2(50),
  PLACEORIGIN         VARCHAR2(50),
  MANUFACTURERS       VARCHAR2(50),
  ROADID              VARCHAR2(32) not null,
  STARTPOINT          VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_VEHICLEDETECTOR
  is '车辆检测器';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.ID
  is '主键id sys_guid()';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.ROADNAME
  is '道路名称';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.INTERSECTIONNUMBERS
  is '路口编号';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.MOUNTINGPOINTS
  is '安装点';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.MOUNTINGORIENTATION
  is '安装方位';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.CONSTRUCTIONTIME
  is '建设时间';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.CONSTRUCTIONUNIT
  is '建设单位';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.DEVICENAME
  is '设备名称';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.DEVICETYPE
  is '设备型号';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.FACTORYNUMBER
  is '出厂编号';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.SINCENUMBER
  is '自编号';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.DEVICELOCATED
  is '设备位于(左转车道、执行车道,数据字典)';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.NUM
  is '数量';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.SCENEPHOTOS
  is '现场图片';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.PLACEORIGIN
  is '产地';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.MANUFACTURERS
  is '生产商';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.ROADID
  is '道路id,外键id';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.STARTPOINT
  is '位置';
alter table ADMIN.JTSS_VEHICLEDETECTOR
  add constraint JTSS_CLJCQ primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_VEHICLEDETECTOR_HIS
prompt =======================================
prompt
create table ADMIN.JTSS_VEHICLEDETECTOR_HIS
(
  ID                  VARCHAR2(32) not null,
  HISID               VARCHAR2(32) not null,
  ROADNAME            VARCHAR2(50),
  INTERSECTIONNUMBERS VARCHAR2(50),
  MOUNTINGPOINTS      VARCHAR2(50),
  MOUNTINGORIENTATION VARCHAR2(50),
  CONSTRUCTIONTIME    DATE,
  CONSTRUCTIONUNIT    VARCHAR2(50),
  DEVICENAME          VARCHAR2(50),
  DEVICETYPE          VARCHAR2(50),
  FACTORYNUMBER       VARCHAR2(50),
  SINCENUMBER         VARCHAR2(50),
  DEVICELOCATED       VARCHAR2(50),
  NUM                 NUMBER(10),
  SCENEPHOTOS         VARCHAR2(50),
  PLACEORIGIN         VARCHAR2(50),
  MANUFACTURERS       VARCHAR2(50),
  ROADID              VARCHAR2(32) not null,
  STARTPOINT          VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_VEHICLEDETECTOR_HIS
  is '车辆检测器历史数据表';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.ID
  is '主键id sys_guid()';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.HISID
  is '外键id,数据id';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.ROADNAME
  is '道路名称';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.INTERSECTIONNUMBERS
  is '路口编号';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.MOUNTINGPOINTS
  is '安装点';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.MOUNTINGORIENTATION
  is '安装方位';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.CONSTRUCTIONTIME
  is '建设时间';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.CONSTRUCTIONUNIT
  is '建设单位';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.DEVICENAME
  is '设备名称';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.DEVICETYPE
  is '设备型号';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.FACTORYNUMBER
  is '出厂编号';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.SINCENUMBER
  is '自编号';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.DEVICELOCATED
  is '设备位于(左转车道、执行车道,数据字典)';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.NUM
  is '数量';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.SCENEPHOTOS
  is '现场图片';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.PLACEORIGIN
  is '产地';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.MANUFACTURERS
  is '生产商';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.ROADID
  is '道路id,外键id';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.STARTPOINT
  is '位置';
alter table ADMIN.JTSS_VEHICLEDETECTOR_HIS
  add constraint JTSS_CLJCQ_HIS primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table JTSS_VEHICLEDETECTOR_ROAD
prompt ========================================
prompt
create table ADMIN.JTSS_VEHICLEDETECTOR_ROAD
(
  ID                  VARCHAR2(32) not null,
  ROADNAME            VARCHAR2(50),
  INTERSECTIONNUMBERS VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.JTSS_VEHICLEDETECTOR_ROAD
  is '车辆检测器道路信息表';
alter table ADMIN.JTSS_VEHICLEDETECTOR_ROAD
  add constraint JTSS_CLJCQ_ROAD primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table LEDSHOW_CONTROL_LOG
prompt ==================================
prompt
create table ADMIN.LEDSHOW_CONTROL_LOG
(
  ID            VARCHAR2(32) not null,
  OPERATOR      VARCHAR2(20) not null,
  OPERATOR_TIME DATE not null,
  TERMINAL_NAME VARCHAR2(100) not null,
  TERMINAL_CODE VARCHAR2(20),
  ACTION        VARCHAR2(20) not null,
  RESULT        VARCHAR2(100) not null,
  ACTIONCONTENT VARCHAR2(200)
)
tablespace ATMS
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
comment on column ADMIN.LEDSHOW_CONTROL_LOG.OPERATOR
  is '操作账号';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.OPERATOR_TIME
  is '操作时间';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.TERMINAL_NAME
  is '操作终端名';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.TERMINAL_CODE
  is '操作终端编号';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.ACTION
  is '控制动作';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.RESULT
  is '结果';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.ACTIONCONTENT
  is '动作内容';
alter table ADMIN.LEDSHOW_CONTROL_LOG
  add constraint PK_CONTROL_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table LEDSHOW_PROGRAMGROUP_INFO
prompt ========================================
prompt
create table ADMIN.LEDSHOW_PROGRAMGROUP_INFO
(
  ID   VARCHAR2(20) not null,
  NAME VARCHAR2(50) not null,
  NOTE VARCHAR2(500)
)
tablespace ATMS
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
alter table ADMIN.LEDSHOW_PROGRAMGROUP_INFO
  add constraint PK_PROGRAMGROUP_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table LEDSHOW_PROGRAM_INFO
prompt ===================================
prompt
create table ADMIN.LEDSHOW_PROGRAM_INFO
(
  ID              VARCHAR2(20) not null,
  NAME            VARCHAR2(100) not null,
  TIME_ADD        DATE not null,
  OPERATOR        VARCHAR2(20) not null,
  STARTDAY        DATE,
  ENDDAY          DATE,
  STARTTIME       VARCHAR2(10),
  ENDTIME         VARCHAR2(10),
  WEEKDAY         VARCHAR2(50),
  ISSHOWBYTIME    VARCHAR2(2) default 0,
  SHOWBYTIME      NUMBER,
  ISSHOWBYQUEUE   VARCHAR2(2) default 0,
  SHOWBYQUEUE     NUMBER,
  CODE            VARCHAR2(20) not null,
  WIDTH           NUMBER not null,
  HEIGHT          NUMBER not null,
  TERMINAL_CODE   VARCHAR2(20) not null,
  BORDERPIC       VARCHAR2(100),
  BORDEREFFECT    VARCHAR2(20),
  BORDERSTEP      NUMBER,
  BORDERSPEED     NUMBER,
  VERIFYFLAG      VARCHAR2(2) default 2,
  VERIFYOPERATOR  VARCHAR2(20),
  VERIFY_TIME     DATE,
  VERIFY_RESULT   VARCHAR2(2),
  REQUESTFLAG     VARCHAR2(2) default 2,
  REQUESTOPERATOR VARCHAR2(20),
  REQUEST_TIME    DATE
)
tablespace ATMS
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
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.NAME
  is '节目名';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.TIME_ADD
  is '添加时间';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.OPERATOR
  is '添加人账号';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.STARTDAY
  is '开始日期';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.ENDDAY
  is '结束日期';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.STARTTIME
  is '开始时间点';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.ENDTIME
  is '结束时间点';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.WEEKDAY
  is '按周配置生效日期(1,2,3,4,5,6,7)';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.ISSHOWBYTIME
  is '按时间播放：0否 1是';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.SHOWBYTIME
  is '按时间播放多少秒';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.ISSHOWBYQUEUE
  is '按顺序连续播放：0否 1是';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.SHOWBYQUEUE
  is '按顺序播放多少次';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.CODE
  is '节目编号';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.WIDTH
  is '节目适应屏幕的宽度';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.HEIGHT
  is '节目适应屏幕的高度';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.TERMINAL_CODE
  is '对应的终端屏幕编号';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.BORDERPIC
  is '节目边框';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.BORDEREFFECT
  is '边框特技';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.BORDERSTEP
  is '移动步长';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.BORDERSPEED
  is '运行速度';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.VERIFYFLAG
  is '是否审核:1已审核 2未审核';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.VERIFYOPERATOR
  is '审核人账号';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.VERIFY_TIME
  is '审核时间';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.VERIFY_RESULT
  is '审核结果:1通过 2驳回';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.REQUESTFLAG
  is '是否已申请:1已申请 2未申请';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.REQUESTOPERATOR
  is '申请人账号';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.REQUEST_TIME
  is '申请时间';
alter table ADMIN.LEDSHOW_PROGRAM_INFO
  add constraint PK_PROGRAM_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table LEDSHOW_PUBLISH_LOG
prompt ==================================
prompt
create table ADMIN.LEDSHOW_PUBLISH_LOG
(
  ID             VARCHAR2(20) not null,
  PROGRAM_NAME   VARCHAR2(100) not null,
  PROGRAM_CODE   VARCHAR2(20) not null,
  OPERATOR       VARCHAR2(20) not null,
  ADD_TIME       DATE not null,
  TERMINAL_NAME  VARCHAR2(100),
  TERMINAL_CODE  VARCHAR2(20),
  VERIFYOPERATOR VARCHAR2(20),
  PUB_TIME       DATE,
  PUB_RESULT     VARCHAR2(10),
  PUB_ERROR      VARCHAR2(200)
)
tablespace ATMS
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
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.PROGRAM_NAME
  is '节目名称';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.PROGRAM_CODE
  is '节目编号';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.OPERATOR
  is '节目添加人';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.ADD_TIME
  is '添加时间';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.TERMINAL_NAME
  is '显示终端';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.TERMINAL_CODE
  is '显示终端编号';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.VERIFYOPERATOR
  is '审核人';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.PUB_TIME
  is '发布时间';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.PUB_RESULT
  is '发布结果:1成功 2失败';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.PUB_ERROR
  is '失败原因';
alter table ADMIN.LEDSHOW_PUBLISH_LOG
  add constraint PK_PUBLISH_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table LEDSHOW_RELEASEJOB_INFO
prompt ======================================
prompt
create table ADMIN.LEDSHOW_RELEASEJOB_INFO
(
  ID            VARCHAR2(20) not null,
  JOBCODE       VARCHAR2(10) not null,
  JOBNAME       VARCHAR2(100) not null,
  JOBNOTE       VARCHAR2(200),
  PROGRAM_ID    VARCHAR2(20) not null,
  PROGRAM_NAME  VARCHAR2(100) not null,
  TERMINAL_ID   VARCHAR2(20) not null,
  TERMINAL_NAME VARCHAR2(100) not null,
  JOBSTATUS     VARCHAR2(2),
  VERIFY_TIME   DATE
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255;
alter table ADMIN.LEDSHOW_RELEASEJOB_INFO
  add constraint PK_LEDJOB_ID primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table LEDSHOW_TERMINALGROUP_INFO
prompt =========================================
prompt
create table ADMIN.LEDSHOW_TERMINALGROUP_INFO
(
  ID   VARCHAR2(20) not null,
  NAME VARCHAR2(200) not null,
  NOTE VARCHAR2(300)
)
tablespace ATMS
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
alter table ADMIN.LEDSHOW_TERMINALGROUP_INFO
  add constraint PK_TERMINALGRO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table LEDSHOW_TERMINAL_INFO
prompt ====================================
prompt
create table ADMIN.LEDSHOW_TERMINAL_INFO
(
  ID               VARCHAR2(20) not null,
  COMPANY_ID       VARCHAR2(20) not null,
  CONTROLCARD_ID   VARCHAR2(200),
  NAME             VARCHAR2(200) not null,
  CODE             VARCHAR2(20) not null,
  SCREEN_TYPE      VARCHAR2(50),
  CONNMODEL_CODE   VARCHAR2(50) not null,
  WIDTH            NUMBER not null,
  HEIGHT           NUMBER not null,
  NOTE             VARCHAR2(300),
  TERMINALGROUP_ID VARCHAR2(20),
  ADDRESS          VARCHAR2(300)
)
tablespace ATMS
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
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.COMPANY_ID
  is '生产厂商';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.CONTROLCARD_ID
  is '控制卡类型';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.NAME
  is '终端名称';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.CODE
  is '终端编号';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.SCREEN_TYPE
  is '屏幕类型:单色,双色';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.CONNMODEL_CODE
  is '通信模式:U盘下载,GPRS无线通讯,串行通讯';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.WIDTH
  is '屏长';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.HEIGHT
  is '屏高';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.NOTE
  is '备注';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.TERMINALGROUP_ID
  is '终端分组ID';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.ADDRESS
  is '安装地址';
alter table ADMIN.LEDSHOW_TERMINAL_INFO
  add constraint PK_TERMINALFINO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table LEDSHOW_TERMINAL_PROGRAM
prompt =======================================
prompt
create table ADMIN.LEDSHOW_TERMINAL_PROGRAM
(
  ID            VARCHAR2(20) not null,
  TERMINAL_CODE VARCHAR2(20) not null,
  PROGRAM_CODE  VARCHAR2(20) not null
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255;
alter table ADMIN.LEDSHOW_TERMINAL_PROGRAM
  add constraint PK_TERMINAL_PROGRAM_ID primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table LEDSHOW_TEXTCONTENT_INFO
prompt =======================================
prompt
create table ADMIN.LEDSHOW_TEXTCONTENT_INFO
(
  ID             VARCHAR2(32) not null,
  NAME           VARCHAR2(100) not null,
  FONT           VARCHAR2(10),
  FONTSIZE       NUMBER,
  FONTCOLOR      VARCHAR2(10),
  LEFTSIZE       NUMBER not null,
  TOPSIZE        NUMBER not null,
  CONTENT_WIDTH  NUMBER not null,
  CONTENT_HEIGHT NUMBER not null,
  MOVEEFFECT     VARCHAR2(20),
  MOVESPEED      NUMBER,
  LAYOUT         VARCHAR2(2),
  FONTSPACESIZE  NUMBER,
  ROWSPACESIZE   NUMBER,
  PROGRAMCODE    VARCHAR2(20) not null,
  STOPTIME       NUMBER not null
)
tablespace ATMS
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
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.NAME
  is '文本内容';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.FONT
  is '字体';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.FONTSIZE
  is '字体大小（单位：点）';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.FONTCOLOR
  is '字体颜色';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.LEFTSIZE
  is '左边距';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.TOPSIZE
  is '上边距';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.CONTENT_WIDTH
  is '内容区域长度';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.CONTENT_HEIGHT
  is '内容区域高度';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.MOVEEFFECT
  is '显示特效外键';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.MOVESPEED
  is '移动速度';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.LAYOUT
  is '布局方式: 1左对齐、2居中、3右对齐';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.FONTSPACESIZE
  is '字间距';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.ROWSPACESIZE
  is '行间距';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.PROGRAMCODE
  is '所属节目编号';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.STOPTIME
  is '停留时间';
alter table ADMIN.LEDSHOW_TEXTCONTENT_INFO
  add constraint PK_TEXTCONTENT_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table LEDSHOW_TIMECONTENT_INFO
prompt =======================================
prompt
create table ADMIN.LEDSHOW_TIMECONTENT_INFO
(
  ID              VARCHAR2(20) not null,
  NAME            VARCHAR2(100) not null,
  LEFTSIZE        NUMBER not null,
  TOPSIZE         NUMBER not null,
  CONTENT_WIDTH   NUMBER not null,
  CONTENT_HEIGHT  NUMBER not null,
  ISSHOW_YYYYMMDD VARCHAR2(2) default 0,
  ISSHOW_HHMMSS   VARCHAR2(2) default 0,
  ISSHOW_WEEKDAY  VARCHAR2(2) default 0,
  FONT            VARCHAR2(10),
  FONTSIZE        NUMBER,
  FONTCOLOR       VARCHAR2(10),
  FONTCONTENT     VARCHAR2(100)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255;
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.NAME
  is '时间区域名称';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.LEFTSIZE
  is '左边距';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.TOPSIZE
  is '上边距';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.CONTENT_WIDTH
  is '时间区域宽度';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.CONTENT_HEIGHT
  is '时间区域高度';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.ISSHOW_YYYYMMDD
  is '是否显示年月日';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.ISSHOW_HHMMSS
  is '是否显示时分秒';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.ISSHOW_WEEKDAY
  is '是否显示星期几';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.FONT
  is '字体';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.FONTCONTENT
  is '时间前置文字信息';
alter table ADMIN.LEDSHOW_TIMECONTENT_INFO
  add constraint PK_TIMECONTENT_ID primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
tablespace ATMS
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
  tablespace ATMS
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
prompt Creating table PARKINGLOT_EQUIP
prompt ===============================
prompt
create table ADMIN.PARKINGLOT_EQUIP
(
  EQUIPSERIAL    VARCHAR2(20) not null,
  EQUIPID        VARCHAR2(20) not null,
  PARKID         VARCHAR2(20) not null,
  EQUIPNAME      VARCHAR2(100) not null,
  EQUIPTYPE      INTEGER not null,
  EQUIPTIME      DATE not null,
  RESERVEDTIMET1 DATE,
  RESERVEDTIMET2 DATE,
  LNG            VARCHAR2(50),
  LAT            VARCHAR2(50),
  RESERVEDINTN1  INTEGER,
  RESERVEDINTN2  INTEGER
)
tablespace ATMS
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
comment on column ADMIN.PARKINGLOT_EQUIP.EQUIPID
  is '设备编号';
comment on column ADMIN.PARKINGLOT_EQUIP.PARKID
  is '停车场编号';
comment on column ADMIN.PARKINGLOT_EQUIP.EQUIPNAME
  is '设备名称（例：B区入口led）';
comment on column ADMIN.PARKINGLOT_EQUIP.EQUIPTYPE
  is '设备类型（0为控制器,   1为LED屏）';
comment on column ADMIN.PARKINGLOT_EQUIP.EQUIPTIME
  is '更新时间（每次数据包请求时候，更新时间）';
comment on column ADMIN.PARKINGLOT_EQUIP.RESERVEDTIMET1
  is '预留字段';
comment on column ADMIN.PARKINGLOT_EQUIP.RESERVEDTIMET2
  is '预留字段';
comment on column ADMIN.PARKINGLOT_EQUIP.LNG
  is '经度';
comment on column ADMIN.PARKINGLOT_EQUIP.LAT
  is '预留字段';
comment on column ADMIN.PARKINGLOT_EQUIP.RESERVEDINTN1
  is 'LED屏的级别  1为1级，2为2级， 3为3级';
comment on column ADMIN.PARKINGLOT_EQUIP.RESERVEDINTN2
  is '预留字段';
alter table ADMIN.PARKINGLOT_EQUIP
  add constraint PK_PARKINGLOT_EQUIP_ID primary key (EQUIPSERIAL)
  using index 
  tablespace ATMS
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
prompt Creating table PARKINGLOT_INFO
prompt ==============================
prompt
create table ADMIN.PARKINGLOT_INFO
(
  ID              VARCHAR2(32) not null,
  PARKID          VARCHAR2(32),
  PARKNAME        VARCHAR2(200),
  PARKTYPE        NUMBER,
  PARKSTATUS      NUMBER,
  FREEPARK        NUMBER,
  TOTALPARK       NUMBER,
  LNG             VARCHAR2(50),
  LAT             VARCHAR2(50),
  AREA            NUMBER,
  UPDATETIME      DATE,
  REMARKS         VARCHAR2(255),
  PARKCHARGESTIME DATE,
  PARKCHARGEETIME DATE,
  SUPERVISEPHONE  VARCHAR2(20),
  REPORTPHONE     VARCHAR2(20),
  PRICE           NUMBER,
  ISPOLICEMANAGE  NUMBER,
  ROADNAME        VARCHAR2(500)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.PARKINGLOT_INFO.PARKID
  is '停车场编号';
comment on column ADMIN.PARKINGLOT_INFO.PARKNAME
  is '停车场名称';
comment on column ADMIN.PARKINGLOT_INFO.PARKTYPE
  is '车场类型（0为地上，1为地下， 2包括地上地下。默认为0）';
comment on column ADMIN.PARKINGLOT_INFO.PARKSTATUS
  is '停车场状态（0为正常，1为停用，默认为0）';
comment on column ADMIN.PARKINGLOT_INFO.FREEPARK
  is '空车位';
comment on column ADMIN.PARKINGLOT_INFO.TOTALPARK
  is '总车位';
comment on column ADMIN.PARKINGLOT_INFO.LNG
  is '经度';
comment on column ADMIN.PARKINGLOT_INFO.LAT
  is '纬度';
comment on column ADMIN.PARKINGLOT_INFO.AREA
  is '所属区域';
comment on column ADMIN.PARKINGLOT_INFO.UPDATETIME
  is '更新时间';
comment on column ADMIN.PARKINGLOT_INFO.REMARKS
  is '备注（对特殊情况做说明）';
comment on column ADMIN.PARKINGLOT_INFO.PARKCHARGESTIME
  is '收费开始时间';
comment on column ADMIN.PARKINGLOT_INFO.PARKCHARGEETIME
  is '收费结束时间';
comment on column ADMIN.PARKINGLOT_INFO.SUPERVISEPHONE
  is '监督电话';
comment on column ADMIN.PARKINGLOT_INFO.REPORTPHONE
  is '投诉电话';
comment on column ADMIN.PARKINGLOT_INFO.PRICE
  is '收费价格元/小时';
comment on column ADMIN.PARKINGLOT_INFO.ISPOLICEMANAGE
  is '是否直属停车场（1是0否）';
comment on column ADMIN.PARKINGLOT_INFO.ROADNAME
  is '临近道路名';

prompt
prompt Creating table PARKINGLOT_LEADDEVICE_INFO
prompt =========================================
prompt
create table ADMIN.PARKINGLOT_LEADDEVICE_INFO
(
  ID            VARCHAR2(32) not null,
  EQUIPID       VARCHAR2(32),
  PARKID        VARCHAR2(255),
  EQUIPNAME     VARCHAR2(200),
  EQUIPTYPE     NUMBER,
  EQUIPTIME     DATE,
  LNG           VARCHAR2(50),
  LAT           VARCHAR2(50),
  EQUIPLEVECODE NUMBER
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.EQUIPID
  is '设备ID';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.PARKID
  is '停车场编号';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.EQUIPNAME
  is '设备名称（例：B区入口led）';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.EQUIPTYPE
  is '设备类型（0为控制器,   1为LED屏）';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.EQUIPTIME
  is '更新时间（每次数据包请求时候，更新时间）';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.LNG
  is '经度';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.LAT
  is '纬度';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.EQUIPLEVECODE
  is 'LED屏的级别  1为1级，2为2级， 3为3级';

prompt
prompt Creating table PARKINGLOT_PARK_DATA
prompt ===================================
prompt
create table ADMIN.PARKINGLOT_PARK_DATA
(
  PARKSERIAL       VARCHAR2(20) not null,
  PARKID           VARCHAR2(20),
  PARKNAME         VARCHAR2(100),
  PARKTYPE         INTEGER,
  PARKSTATUS       INTEGER,
  FREEPARK         INTEGER,
  TOTALPARK        INTEGER,
  LNG              VARCHAR2(50),
  LAT              VARCHAR2(50),
  AREA             INTEGER,
  UPDATETIME       DATE,
  REMARKS          VARCHAR2(600),
  RESERVEDTIMET1   DATE,
  RESERVEDTIMET2   DATE,
  RESERVEDSTRINGS1 VARCHAR2(50),
  RESERVEDSTRINGS2 VARCHAR2(50),
  RESERVEDINTN1    INTEGER,
  RESERVEDINTN2    INTEGER default 0,
  ROADNAME         VARCHAR2(100),
  ORGCODE          VARCHAR2(50)
)
tablespace ATMS
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
comment on column ADMIN.PARKINGLOT_PARK_DATA.PARKID
  is '停车场编号';
comment on column ADMIN.PARKINGLOT_PARK_DATA.PARKNAME
  is '停车场名称';
comment on column ADMIN.PARKINGLOT_PARK_DATA.PARKTYPE
  is '车场类型（0为地上，1为地下， 2包括地上地下。默认为0）';
comment on column ADMIN.PARKINGLOT_PARK_DATA.PARKSTATUS
  is '停车场状态（0为正常，1为停用，默认为0）';
comment on column ADMIN.PARKINGLOT_PARK_DATA.FREEPARK
  is '空车位';
comment on column ADMIN.PARKINGLOT_PARK_DATA.TOTALPARK
  is '总车位';
comment on column ADMIN.PARKINGLOT_PARK_DATA.LNG
  is '经度';
comment on column ADMIN.PARKINGLOT_PARK_DATA.LAT
  is '纬度';
comment on column ADMIN.PARKINGLOT_PARK_DATA.AREA
  is '所属区域';
comment on column ADMIN.PARKINGLOT_PARK_DATA.UPDATETIME
  is '更新时间';
comment on column ADMIN.PARKINGLOT_PARK_DATA.REMARKS
  is '备注（对特殊情况做说明）';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDTIMET1
  is '收费开始时间ParkChargeStime';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDTIMET2
  is '收费结束时间ParkChargeEtime';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDSTRINGS1
  is '监督电话supervisephone';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDSTRINGS2
  is '投诉电话reportphone';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDINTN1
  is '收费价格元/小时ParkCharge';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDINTN2
  is '是否直属停车场（1是0否）';
comment on column ADMIN.PARKINGLOT_PARK_DATA.ROADNAME
  is '临近道路名';
comment on column ADMIN.PARKINGLOT_PARK_DATA.ORGCODE
  is '预留字段';
alter table ADMIN.PARKINGLOT_PARK_DATA
  add constraint PK_PARKINGLOT_PARK_DATA_ID primary key (PARKSERIAL)
  using index 
  tablespace ATMS
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
prompt Creating table PAS_PASSPORT_INFO
prompt ================================
prompt
create table ADMIN.PAS_PASSPORT_INFO
(
  ID           VARCHAR2(32) not null,
  CODE         VARCHAR2(20) not null,
  TYPE         VARCHAR2(10) not null,
  STARTTIME    DATE not null,
  ENDTIME      DATE not null,
  APPLYPER     VARCHAR2(255),
  PLATE_NUMBER VARCHAR2(20),
  PLATE_TYPE   VARCHAR2(10),
  CARTYPE      VARCHAR2(10),
  STATE        VARCHAR2(10),
  GIVETIME     VARCHAR2(20),
  IMAGE_PATH   VARCHAR2(200)
)
tablespace ATMS
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
comment on column ADMIN.PAS_PASSPORT_INFO.CODE
  is '通行证编号';
comment on column ADMIN.PAS_PASSPORT_INFO.TYPE
  is '通行证类型';
comment on column ADMIN.PAS_PASSPORT_INFO.STARTTIME
  is '有效起始日期';
comment on column ADMIN.PAS_PASSPORT_INFO.ENDTIME
  is '有效终止日期';
comment on column ADMIN.PAS_PASSPORT_INFO.APPLYPER
  is '申请人';
comment on column ADMIN.PAS_PASSPORT_INFO.PLATE_NUMBER
  is '号牌号码';
comment on column ADMIN.PAS_PASSPORT_INFO.PLATE_TYPE
  is '号牌种类';
comment on column ADMIN.PAS_PASSPORT_INFO.CARTYPE
  is '车辆类别';
comment on column ADMIN.PAS_PASSPORT_INFO.STATE
  is '通行证状态';
comment on column ADMIN.PAS_PASSPORT_INFO.GIVETIME
  is '发证日期';
comment on column ADMIN.PAS_PASSPORT_INFO.IMAGE_PATH
  is '审核图片。';
alter table ADMIN.PAS_PASSPORT_INFO
  add constraint PAS_PASSPORT_INFO_PK primary key (ID)
  using index 
  tablespace ATMS
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
create unique index ADMIN.PASSPORT_CODE on ADMIN.PAS_PASSPORT_INFO (CODE)
  tablespace ATMS
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
prompt Creating table PAS_PASSPORT_INFO_HIS
prompt ====================================
prompt
create table ADMIN.PAS_PASSPORT_INFO_HIS
(
  ID           VARCHAR2(32) not null,
  CODE         VARCHAR2(20) not null,
  TYPE         VARCHAR2(10) not null,
  STARTTIME    DATE not null,
  ENDTIME      DATE not null,
  APPLYPER     VARCHAR2(255),
  PLATE_NUMBER VARCHAR2(20),
  PLATE_TYPE   VARCHAR2(10),
  CARTYPE      VARCHAR2(10),
  STATE        VARCHAR2(10),
  GIVETIME     VARCHAR2(20),
  IMAGE_PATH   VARCHAR2(200),
  ROADS        VARCHAR2(4000)
)
tablespace ATMS
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
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.CODE
  is '通行证编号';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.TYPE
  is '通行证类型';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.STARTTIME
  is '有效起始日期';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.ENDTIME
  is '有效终止日期';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.APPLYPER
  is '申请人';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.PLATE_NUMBER
  is '号牌号码';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.PLATE_TYPE
  is '号牌种类';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.CARTYPE
  is '车辆类别';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.STATE
  is '通行证状态';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.GIVETIME
  is '发证日期';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.IMAGE_PATH
  is '审核图片';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.ROADS
  is '可通行道路';
alter table ADMIN.PAS_PASSPORT_INFO_HIS
  add constraint PAS_PASSPORT_INFO_HIS_PK primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table PAS_ROAD
prompt =======================
prompt
create table ADMIN.PAS_ROAD
(
  ID       VARCHAR2(32) not null,
  NAME     VARCHAR2(50) not null,
  PID      VARCHAR2(20),
  COUNTS   NUMBER,
  REMARK   VARCHAR2(600),
  ORG_ID   VARCHAR2(32),
  ISIMPORT VARCHAR2(1) default 0,
  HASCOUNT NUMBER(19) default 0
)
tablespace ATMS
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
comment on column ADMIN.PAS_ROAD.NAME
  is '道路名称';
comment on column ADMIN.PAS_ROAD.PID
  is '父节点id';
comment on column ADMIN.PAS_ROAD.COUNTS
  is '限制的通行证数量';
comment on column ADMIN.PAS_ROAD.REMARK
  is '备注';
comment on column ADMIN.PAS_ROAD.ORG_ID
  is '部门id';
comment on column ADMIN.PAS_ROAD.ISIMPORT
  is '是否重点路段';
comment on column ADMIN.PAS_ROAD.HASCOUNT
  is '已办数量';
alter table ADMIN.PAS_ROAD
  add constraint PAS_ROAD_PK primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table PAS_ROAD_PASSPORT
prompt ================================
prompt
create table ADMIN.PAS_ROAD_PASSPORT
(
  ID            VARCHAR2(32) not null,
  ROAD_ID       VARCHAR2(32) not null,
  PASSPORT_CODE VARCHAR2(32)
)
tablespace ATMS
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
alter table ADMIN.PAS_ROAD_PASSPORT
  add constraint PAS_ROAD_PASSPORT_PK primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SIGNAL_AREAS_INFO
prompt ================================
prompt
create table ADMIN.SIGNAL_AREAS_INFO
(
  ID           VARCHAR2(20) not null,
  NAME         VARCHAR2(200) not null,
  CROSSROADNUM NUMBER default 0 not null,
  NOTE         VARCHAR2(500),
  CROSSROADIDS CLOB
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_AREAS_INFO.NAME
  is '区域名称';
comment on column ADMIN.SIGNAL_AREAS_INFO.CROSSROADNUM
  is '下属路口数量';
comment on column ADMIN.SIGNAL_AREAS_INFO.NOTE
  is '备注';
comment on column ADMIN.SIGNAL_AREAS_INFO.CROSSROADIDS
  is '该区域下所有路口ID用都好分隔';
alter table ADMIN.SIGNAL_AREAS_INFO
  add constraint PK_AREA_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SIGNAL_CONTROL_CROSSROAD
prompt =======================================
prompt
create table ADMIN.SIGNAL_CONTROL_CROSSROAD
(
  ID           VARCHAR2(32) not null,
  CONTROL_ID   VARCHAR2(20) not null,
  CROSSROAD_ID VARCHAR2(20) not null
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_CONTROL_CROSSROAD.CONTROL_ID
  is '信号控制机ID';
comment on column ADMIN.SIGNAL_CONTROL_CROSSROAD.CROSSROAD_ID
  is '管辖路口的ID';
alter table ADMIN.SIGNAL_CONTROL_CROSSROAD
  add constraint PK_CONTROL_CROSSROAD_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SIGNAL_CONTROL_INFO
prompt ==================================
prompt
create table ADMIN.SIGNAL_CONTROL_INFO
(
  ID                  VARCHAR2(20) not null,
  NAME                VARCHAR2(200) not null,
  FUNCTION_CLASS_CODE VARCHAR2(20),
  ENV_CLASS_CODE      VARCHAR2(20),
  IP                  VARCHAR2(20),
  PORT                VARCHAR2(10),
  USERNAME            VARCHAR2(200),
  PASSWORD            VARCHAR2(200),
  COMPANY_ID          VARCHAR2(20),
  INSPECTTIME         DATE,
  INSPECTNUMBER       VARCHAR2(50),
  STANDARDCODE        VARCHAR2(200),
  STANDARDNAME        VARCHAR2(200)
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_CONTROL_INFO.NAME
  is '信号机名称';
comment on column ADMIN.SIGNAL_CONTROL_INFO.FUNCTION_CLASS_CODE
  is '信号机按功能分类编码';
comment on column ADMIN.SIGNAL_CONTROL_INFO.ENV_CLASS_CODE
  is '信号机按安装环境分类编码';
comment on column ADMIN.SIGNAL_CONTROL_INFO.IP
  is '所分配IP地址';
comment on column ADMIN.SIGNAL_CONTROL_INFO.PORT
  is '端口号';
comment on column ADMIN.SIGNAL_CONTROL_INFO.USERNAME
  is '用户名';
comment on column ADMIN.SIGNAL_CONTROL_INFO.PASSWORD
  is '密码';
comment on column ADMIN.SIGNAL_CONTROL_INFO.COMPANY_ID
  is '制造公司的ID';
comment on column ADMIN.SIGNAL_CONTROL_INFO.INSPECTTIME
  is '检验日期';
comment on column ADMIN.SIGNAL_CONTROL_INFO.INSPECTNUMBER
  is '检验编号';
comment on column ADMIN.SIGNAL_CONTROL_INFO.STANDARDCODE
  is '执行的标准编号';
comment on column ADMIN.SIGNAL_CONTROL_INFO.STANDARDNAME
  is '执行的标准名称';
alter table ADMIN.SIGNAL_CONTROL_INFO
  add constraint PK_SIGNALCONTROL_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SIGNAL_CROSSROADS_INFO
prompt =====================================
prompt
create table ADMIN.SIGNAL_CROSSROADS_INFO
(
  ID              VARCHAR2(20) not null,
  NAME            VARCHAR2(200) not null,
  TYPE            VARCHAR2(10) not null,
  LONGITUDE       VARCHAR2(100),
  LATITUDE        VARCHAR2(100),
  COMMUNICATION   VARCHAR2(10),
  CONNECTIONROADS VARCHAR2(500)
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.NAME
  is '路口名称';
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.TYPE
  is '路口类型';
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.LONGITUDE
  is '经度';
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.LATITUDE
  is '纬度';
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.COMMUNICATION
  is '通信串口号';
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.CONNECTIONROADS
  is '交汇道路信息';
alter table ADMIN.SIGNAL_CROSSROADS_INFO
  add constraint PK_SIGNAL_CROSSROADS primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SIGNAL_FLOWDEVICE_DATE
prompt =====================================
prompt
create table ADMIN.SIGNAL_FLOWDEVICE_DATE
(
  ID              VARCHAR2(32) not null,
  FLOWDEVICE_CODE VARCHAR2(20) not null,
  FLOWALL         NUMBER,
  FLOWBIG         NUMBER,
  FLOWSMALL       NUMBER,
  OCCUPANCY       NUMBER(5,2),
  SPEED           NUMBER(5,2),
  CARLENGTH       NUMBER,
  INSERTTIME      DATE,
  ANALYSTTIME     DATE
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.FLOWDEVICE_CODE
  is '流量检测器编号';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.FLOWALL
  is '总流量';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.FLOWBIG
  is '大型车流量';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.FLOWSMALL
  is '小型车流量';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.OCCUPANCY
  is '路面占有率';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.SPEED
  is '平均速度';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.CARLENGTH
  is '车长';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.INSERTTIME
  is '插入时间';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.ANALYSTTIME
  is '分析时间';
alter table ADMIN.SIGNAL_FLOWDEVICE_DATE
  add constraint PK_FLOWDEV_DATE_ID primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table SIGNAL_FLOWDEVICE_INFO
prompt =====================================
prompt
create table ADMIN.SIGNAL_FLOWDEVICE_INFO
(
  ID             VARCHAR2(20) not null,
  NAME           VARCHAR2(200) not null,
  TYPE_CODE      VARCHAR2(32) not null,
  DATECYCLE      NUMBER,
  PULSECYCLE     NUMBER,
  COM            VARCHAR2(10) not null,
  CODE           VARCHAR2(32) not null,
  DIRECTION_CODE VARCHAR2(21) not null,
  XIANGWEI       VARCHAR2(32),
  CONTROL_ID     VARCHAR2(20) not null,
  CONTROL_NAME   VARCHAR2(200) not null
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.NAME
  is '标识名称';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.TYPE_CODE
  is '检测器分类(来自数据字典分类)';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.DATECYCLE
  is '数据采集周期(秒)';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.PULSECYCLE
  is '脉冲数据采集周期';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.COM
  is '通道号';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.CODE
  is '流量检测器设备编号';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.DIRECTION_CODE
  is '检测器方向编号';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.XIANGWEI
  is '对应的请求相位';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.CONTROL_ID
  is '所属控制机';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.CONTROL_NAME
  is '所属控制机名称';
alter table ADMIN.SIGNAL_FLOWDEVICE_INFO
  add constraint PK_SIGNALFLOWDEVICE_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SIGNAL_GREENSCHEME_CROSSTIME
prompt ===========================================
prompt
create table ADMIN.SIGNAL_GREENSCHEME_CROSSTIME
(
  ID             VARCHAR2(20) not null,
  GREENSCHEME_ID VARCHAR2(20) not null,
  CROSSROADS_ID  VARCHAR2(20) not null,
  TIMEVALUE      VARCHAR2(8) not null
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_GREENSCHEME_CROSSTIME.GREENSCHEME_ID
  is '绿波带方案ID';
comment on column ADMIN.SIGNAL_GREENSCHEME_CROSSTIME.CROSSROADS_ID
  is '路口ID';
comment on column ADMIN.SIGNAL_GREENSCHEME_CROSSTIME.TIMEVALUE
  is '相位时间差';
alter table ADMIN.SIGNAL_GREENSCHEME_CROSSTIME
  add constraint PK_GREENS_CROSS_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SIGNAL_GREENSCHEME_INFO
prompt ======================================
prompt
create table ADMIN.SIGNAL_GREENSCHEME_INFO
(
  ID           VARCHAR2(20) not null,
  NAME         VARCHAR2(200) not null,
  START_TIME   VARCHAR2(8) not null,
  PREPARE_TIME VARCHAR2(8),
  SENDFLAG     NUMBER default 0 not null,
  NOTE         VARCHAR2(200)
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_GREENSCHEME_INFO.NAME
  is '方案名称';
comment on column ADMIN.SIGNAL_GREENSCHEME_INFO.START_TIME
  is '开始时间';
comment on column ADMIN.SIGNAL_GREENSCHEME_INFO.PREPARE_TIME
  is '预备时间';
comment on column ADMIN.SIGNAL_GREENSCHEME_INFO.SENDFLAG
  is '是否已下发';
comment on column ADMIN.SIGNAL_GREENSCHEME_INFO.NOTE
  is '备注说明';
alter table ADMIN.SIGNAL_GREENSCHEME_INFO
  add constraint PK_GREENSCHEME_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SIGNAL_LAMP_INFO
prompt ===============================
prompt
create table ADMIN.SIGNAL_LAMP_INFO
(
  ID                 VARCHAR2(20) not null,
  NAME               VARCHAR2(200) not null,
  MODELCODE          VARCHAR2(15) not null,
  LIGHTAREA_CODE     VARCHAR2(10) not null,
  SHELLMATERIAL_CODE VARCHAR2(30) not null,
  LIGHTSOURCE_CODE   VARCHAR2(30) not null,
  FUNCTION_CODE      VARCHAR2(30) not null,
  CROSSROAD_ID       VARCHAR2(30),
  CONTROL_ID         VARCHAR2(30)
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_LAMP_INFO.NAME
  is '信号灯名称';
comment on column ADMIN.SIGNAL_LAMP_INFO.MODELCODE
  is '由功能分类、透光面尺寸、光源类型、6位生产单位自定代号组成';
comment on column ADMIN.SIGNAL_LAMP_INFO.LIGHTAREA_CODE
  is '发光单元透光面尺寸';
comment on column ADMIN.SIGNAL_LAMP_INFO.SHELLMATERIAL_CODE
  is '外壳材料编号';
comment on column ADMIN.SIGNAL_LAMP_INFO.LIGHTSOURCE_CODE
  is '光源类型编号';
comment on column ADMIN.SIGNAL_LAMP_INFO.FUNCTION_CODE
  is '信号灯功能分类编号';
comment on column ADMIN.SIGNAL_LAMP_INFO.CROSSROAD_ID
  is '所在路口信息ID';
comment on column ADMIN.SIGNAL_LAMP_INFO.CONTROL_ID
  is '所对应的控制机ID';
alter table ADMIN.SIGNAL_LAMP_INFO
  add constraint PK_SIGNALLAMP_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SIGNAL_PRECINCTSCHEME_INFO
prompt =========================================
prompt
create table ADMIN.SIGNAL_PRECINCTSCHEME_INFO
(
  ID                  VARCHAR2(20) not null,
  CROSSROADS_ID       VARCHAR2(20) not null,
  PRECINCTSCHEME_CODE VARCHAR2(20) not null,
  START_TIME          VARCHAR2(10) not null,
  END_TIME            VARCHAR2(10) not null,
  SENDFLAG            VARCHAR2(2) not null
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_PRECINCTSCHEME_INFO.CROSSROADS_ID
  is '路口ID';
comment on column ADMIN.SIGNAL_PRECINCTSCHEME_INFO.PRECINCTSCHEME_CODE
  is '管制方式编码';
comment on column ADMIN.SIGNAL_PRECINCTSCHEME_INFO.START_TIME
  is '开始时间';
comment on column ADMIN.SIGNAL_PRECINCTSCHEME_INFO.END_TIME
  is '结束时间';
comment on column ADMIN.SIGNAL_PRECINCTSCHEME_INFO.SENDFLAG
  is '是否已下发：N未下发  Y已下发';
alter table ADMIN.SIGNAL_PRECINCTSCHEME_INFO
  add constraint PK_PRECINCT_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SIGNAL_TIMEWORK_INFO
prompt ===================================
prompt
create table ADMIN.SIGNAL_TIMEWORK_INFO
(
  ID                    VARCHAR2(20) not null,
  NAME                  VARCHAR2(200) not null,
  IP                    VARCHAR2(15),
  SHOWCLASS_CODE        VARCHAR2(20) not null,
  WORKCLASS_CODE        VARCHAR2(10) not null,
  POWERSOURCECLASS_CODE VARCHAR2(10) not null,
  SHOWEXTENT_CODE       VARCHAR2(10),
  MODELCODE             VARCHAR2(12) not null,
  CROSSROAD_ID          VARCHAR2(30),
  CONTROL_ID            VARCHAR2(30)
)
tablespace ATMS
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
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.SHOWCLASS_CODE
  is '按显示方式分类，倒计时器可分为S 数码显示倒计时器和 M 模拟显示倒计时器';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.WORKCLASS_CODE
  is '按工作方式分类，倒计时器可分为T 通讯式倒计时器和 X 自适应式倒计时器';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.POWERSOURCECLASS_CODE
  is '按供电方式分类，倒计时器可分为0 直接供电式倒计时器和 1 信号灯取电式倒计时器';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.SHOWEXTENT_CODE
  is '如果是数码显示类型，则有 1位：10， 2位：20，2位半 25, 3位:30 数码显示倒计时器';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.MODELCODE
  is '倒计时显示器型号：DX+由显示方式、工作方式、供电方式、企业自定义代码组成';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.CROSSROAD_ID
  is '所在路口信息ID';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.CONTROL_ID
  is '所对应的控制机ID';
alter table ADMIN.SIGNAL_TIMEWORK_INFO
  add constraint PK_TIMEWORK_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SMS_CONTACT_GROUP
prompt ================================
prompt
create table ADMIN.SMS_CONTACT_GROUP
(
  GROUP_ID   VARCHAR2(20) not null,
  CONTACT_ID VARCHAR2(20) not null
)
tablespace ATMS
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

prompt
prompt Creating table SMS_CONTACT_INFO
prompt ===============================
prompt
create table ADMIN.SMS_CONTACT_INFO
(
  ID       VARCHAR2(20) not null,
  NAME     VARCHAR2(50) not null,
  MOBILE   VARCHAR2(11) not null,
  NOTE     VARCHAR2(200),
  ADDTIME  DATE not null,
  ISIMPORT VARCHAR2(2) not null,
  POSITION VARCHAR2(100),
  SEX      VARCHAR2(2)
)
tablespace ATMS
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
comment on column ADMIN.SMS_CONTACT_INFO.ISIMPORT
  is '0 手动录入 1 EXCEL导入 ';
comment on column ADMIN.SMS_CONTACT_INFO.POSITION
  is '职位';
alter table ADMIN.SMS_CONTACT_INFO
  add constraint PK_CONTACT_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SMS_GROUP_INFO
prompt =============================
prompt
create table ADMIN.SMS_GROUP_INFO
(
  ID      VARCHAR2(20) not null,
  NAME    VARCHAR2(20) not null,
  NOTE    VARCHAR2(200),
  ADDTIME DATE not null
)
tablespace ATMS
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
alter table ADMIN.SMS_GROUP_INFO
  add constraint PK_GROUP_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SMS_ORDERTYPE_INFO
prompt =================================
prompt
create table ADMIN.SMS_ORDERTYPE_INFO
(
  ID      VARCHAR2(20) not null,
  NAME    VARCHAR2(200) not null,
  SYMBOL  VARCHAR2(20) not null,
  NOTE    VARCHAR2(500),
  SMSFLAG VARCHAR2(50) not null
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
comment on column ADMIN.SMS_ORDERTYPE_INFO.NAME
  is '类别名称';
comment on column ADMIN.SMS_ORDERTYPE_INFO.SYMBOL
  is '订阅服务号';
comment on column ADMIN.SMS_ORDERTYPE_INFO.NOTE
  is '备注';
comment on column ADMIN.SMS_ORDERTYPE_INFO.SMSFLAG
  is '发送标识内容';
alter table ADMIN.SMS_ORDERTYPE_INFO
  add constraint PK_ORDERTYPE_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SMS_ORDER_LOG
prompt ============================
prompt
create table ADMIN.SMS_ORDER_LOG
(
  ID           VARCHAR2(20) not null,
  TELEPHONE    VARCHAR2(40) not null,
  ORDER_TIME   DATE not null,
  TYPE_NAME    VARCHAR2(200) not null,
  TYPE_SYMBOL  VARCHAR2(20) not null,
  TYPE_SMSFLAG VARCHAR2(50) not null,
  SMSCONTENT   VARCHAR2(500) not null
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
alter table ADMIN.SMS_ORDER_LOG
  add constraint PK_ORDERLOG_ID primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table SMS_RECEIVEBLACK_INFO
prompt ====================================
prompt
create table ADMIN.SMS_RECEIVEBLACK_INFO
(
  ID             VARCHAR2(20) not null,
  CONTACT_NAME   VARCHAR2(20),
  CONTACT_MOBLIE VARCHAR2(11) not null,
  ADDTIME        DATE not null,
  NOTE           VARCHAR2(200)
)
tablespace ATMS
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
alter table ADMIN.SMS_RECEIVEBLACK_INFO
  add constraint PK_RECEIVEBLACK_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SMS_RECEIVE_INFO
prompt ===============================
prompt
create table ADMIN.SMS_RECEIVE_INFO
(
  ID             VARCHAR2(20) not null,
  CONTACT_NAME   VARCHAR2(50),
  CONTACT_MOBLIE VARCHAR2(11) not null,
  RECEIVECONTENT VARCHAR2(500) not null,
  RECEIVETIME    DATE not null
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
alter table ADMIN.SMS_RECEIVE_INFO
  add constraint PK_RECEIVE_ID primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table SMS_SENDLOG_INFO
prompt ===============================
prompt
create table ADMIN.SMS_SENDLOG_INFO
(
  ID             VARCHAR2(20) not null,
  CONTACT_NAME   VARCHAR2(20),
  CONTACT_MOBLIE VARCHAR2(11) not null,
  SENDCONTENT    VARCHAR2(500) not null,
  SENDTIME       DATE not null,
  SENDSTATUS     VARCHAR2(2) not null,
  OPERATOR_ID    VARCHAR2(20) not null,
  CONTACT_ID     VARCHAR2(20),
  OPERATOR_NAME  VARCHAR2(100)
)
tablespace ATMS
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
comment on column ADMIN.SMS_SENDLOG_INFO.SENDSTATUS
  is '发送结果 0失败 1成功';
comment on column ADMIN.SMS_SENDLOG_INFO.OPERATOR_ID
  is '操作人账号';
comment on column ADMIN.SMS_SENDLOG_INFO.CONTACT_ID
  is '联系人ID';
comment on column ADMIN.SMS_SENDLOG_INFO.OPERATOR_NAME
  is '操作人姓名';
alter table ADMIN.SMS_SENDLOG_INFO
  add constraint PK_SENDLOG_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SMS_SENDTASK_INFO
prompt ================================
prompt
create table ADMIN.SMS_SENDTASK_INFO
(
  ID             VARCHAR2(20) not null,
  CONTACT_NAME   VARCHAR2(20),
  CONTACT_MOBLIE VARCHAR2(11) not null,
  SENDCONTENT    VARCHAR2(500) not null,
  SENDTIME       DATE not null,
  OPERATOR_ID    VARCHAR2(20) not null,
  NOTE           VARCHAR2(500),
  ADDTIME        DATE,
  SENDSTATUS     VARCHAR2(2) default 0 not null,
  OPERATOR_NAME  VARCHAR2(100)
)
tablespace ATMS
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
comment on column ADMIN.SMS_SENDTASK_INFO.OPERATOR_ID
  is '操作人账号';
comment on column ADMIN.SMS_SENDTASK_INFO.SENDSTATUS
  is '发送结果 0失败 1成功';
comment on column ADMIN.SMS_SENDTASK_INFO.OPERATOR_NAME
  is '操作人姓名';
alter table ADMIN.SMS_SENDTASK_INFO
  add constraint PK_SENDTASK_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SMS_SUBSCRIBE_LOG
prompt ================================
prompt
create table ADMIN.SMS_SUBSCRIBE_LOG
(
  ID VARCHAR2(20) not null
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

prompt
prompt Creating table SMS_TEMPLET_INFO
prompt ===============================
prompt
create table ADMIN.SMS_TEMPLET_INFO
(
  ID      VARCHAR2(20) not null,
  CONTENT VARCHAR2(500) not null,
  NOTE    VARCHAR2(200),
  ADDTIME DATE not null
)
tablespace ATMS
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
alter table ADMIN.SMS_TEMPLET_INFO
  add constraint PK_TEMPLET_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SMS_VIOTEMPLET_INFO
prompt ==================================
prompt
create table ADMIN.SMS_VIOTEMPLET_INFO
(
  ID      VARCHAR2(20) not null,
  TITLE   VARCHAR2(100) not null,
  CONTENT VARCHAR2(600) not null,
  NOTE    VARCHAR2(500),
  CODE    VARCHAR2(20) not null
)
tablespace ATMS
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
comment on column ADMIN.SMS_VIOTEMPLET_INFO.TITLE
  is '标题';
comment on column ADMIN.SMS_VIOTEMPLET_INFO.CONTENT
  is '内容';
comment on column ADMIN.SMS_VIOTEMPLET_INFO.NOTE
  is '备注';
comment on column ADMIN.SMS_VIOTEMPLET_INFO.CODE
  is '编码';
alter table ADMIN.SMS_VIOTEMPLET_INFO
  add constraint PK_VIOTEMPLET_INFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_DEVICECOMPANY_INFO
prompt =====================================
prompt
create table ADMIN.SYS_DEVICECOMPANY_INFO
(
  ID            VARCHAR2(20) not null,
  NAME          VARCHAR2(100),
  NOTE          VARCHAR2(200),
  CONTACTPHONE  VARCHAR2(20),
  POSITION      INTEGER,
  CONTACTPERSON VARCHAR2(100),
  DATEKEY       VARCHAR2(10)
)
tablespace ATMS
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
alter table ADMIN.SYS_DEVICECOMPANY_INFO
  add constraint PK_COMPANYINFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_DEVICEGROUP_INFO
prompt ===================================
prompt
create table ADMIN.SYS_DEVICEGROUP_INFO
(
  ID   VARCHAR2(20) not null,
  NAME VARCHAR2(40) not null,
  NOTE VARCHAR2(100)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table SYS_DEVICEMAINTAIN_INFO
prompt ======================================
prompt
create table ADMIN.SYS_DEVICEMAINTAIN_INFO
(
  ID                VARCHAR2(20) not null,
  ROADNAME          VARCHAR2(200) not null,
  DEVICENAME        VARCHAR2(200) not null,
  DEVICECODE        VARCHAR2(20) not null,
  DEVICEFUNCTION    VARCHAR2(1000),
  COMPANYNAME       VARCHAR2(200) not null,
  CONTACTPERSON     VARCHAR2(200) not null,
  CONTACTPHONE      VARCHAR2(50) not null,
  SMSCONTENT        VARCHAR2(1000) not null,
  PLANTIME          DATE not null,
  OPERATEPERSONID   VARCHAR2(200) not null,
  OPERATEPERSONNAME VARCHAR2(200) not null,
  ISRETRIEVAL       VARCHAR2(2) default 0 not null,
  OPERATEID         VARCHAR2(200),
  OPERATENAME       VARCHAR2(200)
)
tablespace ATMS
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
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.ROADNAME
  is '道路名称';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.DEVICENAME
  is '设备名称';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.DEVICECODE
  is '设备编号';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.DEVICEFUNCTION
  is '设备功能';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.COMPANYNAME
  is '建设厂商';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.CONTACTPERSON
  is '厂商联系人';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.CONTACTPHONE
  is '联系电话';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.SMSCONTENT
  is '短信内容';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.PLANTIME
  is '工单派发时间';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.OPERATEPERSONID
  is '派发人账号';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.OPERATEPERSONNAME
  is '派发人';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.ISRETRIEVAL
  is '1已恢复 0未恢复';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.OPERATEID
  is '派发工单结果操作人账号';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.OPERATENAME
  is '派发工单结果操作人姓名';
alter table ADMIN.SYS_DEVICEMAINTAIN_INFO
  add constraint PK_ID_MAINTAIN primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_DEVICETYPE_INFO
prompt ==================================
prompt
create table ADMIN.SYS_DEVICETYPE_INFO
(
  ID       VARCHAR2(20) not null,
  PID      VARCHAR2(20),
  NAME     VARCHAR2(40),
  NOTE     VARCHAR2(200),
  POSITION INTEGER,
  LEVELS   INTEGER,
  CODE     VARCHAR2(10)
)
tablespace ATMS
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
alter table ADMIN.SYS_DEVICETYPE_INFO
  add constraint PK_DEVICETYPEINFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_DEVICE_DIRECT_SPEED
prompt ======================================
prompt
create table ADMIN.SYS_DEVICE_DIRECT_SPEED
(
  ID                    VARCHAR2(20) not null,
  DEVICE_CODE           VARCHAR2(20),
  DIRECTION_CODE        VARCHAR2(20),
  LANDTOTAL             VARCHAR2(10),
  CARLIMITSPEED         VARCHAR2(3),
  CARLIMITSPEEDCATCH    VARCHAR2(3),
  BIGCARLIMITSPEED      VARCHAR2(3),
  BIGCARLIMITSPEEDCATCH VARCHAR2(3)
)
tablespace ATMS
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
alter table ADMIN.SYS_DEVICE_DIRECT_SPEED
  add constraint PK_DEVICE_DIRECT primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_DEVICE_GROUP
prompt ===============================
prompt
create table ADMIN.SYS_DEVICE_GROUP
(
  DEVICE_ID VARCHAR2(20),
  GROUP_ID  VARCHAR2(20)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table SYS_DEVICE_INFO
prompt ==============================
prompt
create table ADMIN.SYS_DEVICE_INFO
(
  ID                VARCHAR2(20) not null,
  ORG_ID            VARCHAR2(32) not null,
  CODE              VARCHAR2(18),
  IP                VARCHAR2(100),
  NAME              VARCHAR2(100),
  COMPANY_ID        VARCHAR2(20),
  FTP_ID            VARCHAR2(20),
  ROAD_ID           VARCHAR2(20),
  ISSHOW            VARCHAR2(1) default 0,
  BUILDTIME         DATE,
  BUILDISP_ID       VARCHAR2(20),
  BUILDNETWORK_ID   VARCHAR2(20),
  POWERSOURCE       VARCHAR2(100),
  TIMEOUT           INTEGER,
  NOTE              VARCHAR2(200),
  ISBLACKANALYSE    VARCHAR2(1),
  PHOTO1            VARCHAR2(200),
  PHOTO2            VARCHAR2(200),
  PHOTO3            VARCHAR2(200),
  IMPORTCODE        VARCHAR2(20),
  LICENSE           VARCHAR2(20),
  LASTDATATIME      DATE,
  PICMIX_ID         VARCHAR2(2),
  TOPORGCODE        VARCHAR2(12),
  DEVICETYPECODE    VARCHAR2(2),
  TRADEMARK         VARCHAR2(100),
  PATTERN           VARCHAR2(100),
  EFFICACIOUS_STIME DATE,
  EFFICACIOUS_ETIME DATE,
  INSPECTORG        VARCHAR2(150),
  INSPECTTIME       DATE,
  INSPECTNUMBER     VARCHAR2(50),
  MAPX              VARCHAR2(50),
  MAPY              VARCHAR2(50),
  VIDEO_CODE        VARCHAR2(20),
  ISSEND            VARCHAR2(1)
)
tablespace ATMS
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
comment on column ADMIN.SYS_DEVICE_INFO.PICMIX_ID
  is '图片合成方式ID';
comment on column ADMIN.SYS_DEVICE_INFO.TOPORGCODE
  is '设备所在地机构代码';
comment on column ADMIN.SYS_DEVICE_INFO.DEVICETYPECODE
  is '设备类型编码';
comment on column ADMIN.SYS_DEVICE_INFO.TRADEMARK
  is '设备品牌';
comment on column ADMIN.SYS_DEVICE_INFO.PATTERN
  is '设备型号';
comment on column ADMIN.SYS_DEVICE_INFO.EFFICACIOUS_STIME
  is '设备有效期开始日期';
comment on column ADMIN.SYS_DEVICE_INFO.EFFICACIOUS_ETIME
  is '设备有效期结束日期';
comment on column ADMIN.SYS_DEVICE_INFO.INSPECTORG
  is '检验单位';
comment on column ADMIN.SYS_DEVICE_INFO.INSPECTTIME
  is '检验日期';
comment on column ADMIN.SYS_DEVICE_INFO.INSPECTNUMBER
  is '检验证书编号';
comment on column ADMIN.SYS_DEVICE_INFO.MAPX
  is '设备经度';
comment on column ADMIN.SYS_DEVICE_INFO.MAPY
  is '设备纬度';
comment on column ADMIN.SYS_DEVICE_INFO.VIDEO_CODE
  is '关联视频监控信息';
comment on column ADMIN.SYS_DEVICE_INFO.ISSEND
  is '是否已派发工单（默认未派发）';
alter table ADMIN.SYS_DEVICE_INFO
  add constraint PK_DEVICEINFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
create unique index ADMIN.DEVICE_CODE on ADMIN.SYS_DEVICE_INFO (CODE)
  tablespace ATMS
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
create index ADMIN.DEVICE_CODE_ORG on ADMIN.SYS_DEVICE_INFO (ORG_ID, CODE)
  tablespace ATMS
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
prompt Creating table SYS_DEVICE_LOGINFO
prompt =================================
prompt
create table ADMIN.SYS_DEVICE_LOGINFO
(
  ID              VARCHAR2(20) not null,
  DEVICE_NAME     VARCHAR2(100) not null,
  DEVICE_CODE     VARCHAR2(18) not null,
  STATUS          VARCHAR2(1) not null,
  ROAD_ID         VARCHAR2(20) not null,
  DEVICETYPE_CODE VARCHAR2(2) not null,
  IP              VARCHAR2(15) not null,
  COMPANY_ID      VARCHAR2(20) not null,
  ORG_ID          VARCHAR2(32) not null,
  OPERATOR_ID     VARCHAR2(32) not null,
  OPERATE_TIME    DATE not null
)
tablespace ATMS
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
comment on column ADMIN.SYS_DEVICE_LOGINFO.DEVICE_NAME
  is '设备名称';
comment on column ADMIN.SYS_DEVICE_LOGINFO.DEVICE_CODE
  is '设备编码';
comment on column ADMIN.SYS_DEVICE_LOGINFO.STATUS
  is '设备状态';
comment on column ADMIN.SYS_DEVICE_LOGINFO.ROAD_ID
  is '道路编码';
comment on column ADMIN.SYS_DEVICE_LOGINFO.DEVICETYPE_CODE
  is '设备类型';
comment on column ADMIN.SYS_DEVICE_LOGINFO.IP
  is 'ip地址';
comment on column ADMIN.SYS_DEVICE_LOGINFO.COMPANY_ID
  is '设备厂家';
comment on column ADMIN.SYS_DEVICE_LOGINFO.ORG_ID
  is '所属部门';
comment on column ADMIN.SYS_DEVICE_LOGINFO.OPERATOR_ID
  is '操作人员';
comment on column ADMIN.SYS_DEVICE_LOGINFO.OPERATE_TIME
  is '操作时间';
alter table ADMIN.SYS_DEVICE_LOGINFO
  add constraint PK_DEVICE_LOGINFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_DEVICE_PICMIX
prompt ================================
prompt
create table ADMIN.SYS_DEVICE_PICMIX
(
  ID            VARCHAR2(20) not null,
  NAME          VARCHAR2(150),
  LAYOUTTYPE    VARCHAR2(10) default 0 not null,
  FEATUREPICNUM VARCHAR2(2),
  DEVICE_CODE   VARCHAR2(18) not null,
  ADDTIME       DATE not null,
  PICDIRECTION  VARCHAR2(2) default 0,
  PICCOUNT      VARCHAR2(4)
)
tablespace ATMS
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
comment on column ADMIN.SYS_DEVICE_PICMIX.NAME
  is '合成信息名字';
comment on column ADMIN.SYS_DEVICE_PICMIX.LAYOUTTYPE
  is '布局类型：横向1 ，竖向 2 ，混合 0';
comment on column ADMIN.SYS_DEVICE_PICMIX.FEATUREPICNUM
  is '特写图片位置为第几张';
comment on column ADMIN.SYS_DEVICE_PICMIX.DEVICE_CODE
  is '所属设备编号';
comment on column ADMIN.SYS_DEVICE_PICMIX.ADDTIME
  is '添加时间';
comment on column ADMIN.SYS_DEVICE_PICMIX.PICDIRECTION
  is '非特写图片排列顺序： 顺时针0  逆时针1';
comment on column ADMIN.SYS_DEVICE_PICMIX.PICCOUNT
  is '图片总共几张,含特写图片';
alter table ADMIN.SYS_DEVICE_PICMIX
  add constraint PK_DEVICEPICMIX_ID primary key (ID)
  using index 
  tablespace ATMS
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
create index ADMIN.PIC_DEVICE_CODE_INDEX on ADMIN.SYS_DEVICE_PICMIX (DEVICE_CODE)
  tablespace ATMS
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
prompt Creating table SYS_DEVICE_STATUS_DAY
prompt ====================================
prompt
create table ADMIN.SYS_DEVICE_STATUS_DAY
(
  ID            VARCHAR2(32) not null,
  DEVICETYPE    VARCHAR2(2) not null,
  FAULT_NUM     NUMBER default 0 not null,
  NORMAL_NUM    NUMBER default 0 not null,
  NOCONNECT_NUM NUMBER default 0 not null,
  TARGET_TIME   DATE not null,
  ADD_TIME      DATE not null
)
tablespace ATMS
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
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.DEVICETYPE
  is '设备类型';
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.FAULT_NUM
  is '故障数';
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.NORMAL_NUM
  is '正常数';
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.NOCONNECT_NUM
  is '数据未接入';
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.TARGET_TIME
  is '统计日期YYYY-MM-DD';
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.ADD_TIME
  is '插入时间';
alter table ADMIN.SYS_DEVICE_STATUS_DAY
  add constraint PK_DEVICESTATUSDAY_ID primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table SYS_DEVICE_STATUS_HOUR
prompt =====================================
prompt
create table ADMIN.SYS_DEVICE_STATUS_HOUR
(
  ID            VARCHAR2(32) not null,
  DEVICETYPE    VARCHAR2(2) not null,
  FAULT_NUM     NUMBER default 0 not null,
  NORMAL_NUM    NUMBER default 0 not null,
  NOCONNECT_NUM NUMBER default 0 not null,
  TARGET_TIME   DATE not null,
  ADD_TIME      DATE not null
)
tablespace ATMS
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
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.DEVICETYPE
  is '设备类型';
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.FAULT_NUM
  is '故障数';
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.NORMAL_NUM
  is '正常数';
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.NOCONNECT_NUM
  is '数据未接入';
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.TARGET_TIME
  is '统计日期YYYY-MM-DD HH24';
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.ADD_TIME
  is '插入时间';
alter table ADMIN.SYS_DEVICE_STATUS_HOUR
  add constraint PK_DEVICESTATUSHOUR_ID primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table SYS_DEVICE_STATUS_LOG
prompt ====================================
prompt
create table ADMIN.SYS_DEVICE_STATUS_LOG
(
  ID          VARCHAR2(32) not null,
  DEVICE_CODE VARCHAR2(30) not null,
  DEVICETYPE  VARCHAR2(2) not null,
  STATUS      VARCHAR2(1) not null,
  BRUSH_TIME  DATE not null
)
tablespace ATMS
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
comment on column ADMIN.SYS_DEVICE_STATUS_LOG.DEVICE_CODE
  is '设备编号';
comment on column ADMIN.SYS_DEVICE_STATUS_LOG.DEVICETYPE
  is '设备类型';
comment on column ADMIN.SYS_DEVICE_STATUS_LOG.STATUS
  is '运行状态';
comment on column ADMIN.SYS_DEVICE_STATUS_LOG.BRUSH_TIME
  is '刷新时间';
alter table ADMIN.SYS_DEVICE_STATUS_LOG
  add constraint PK_DEVICESTATUS_ID primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table SYS_DEVICE_TYPE
prompt ==============================
prompt
create table ADMIN.SYS_DEVICE_TYPE
(
  DEVICE_ID     VARCHAR2(20) not null,
  DEVICETYPE_ID VARCHAR2(20) not null
)
tablespace ATMS
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

prompt
prompt Creating table SYS_DIC
prompt ======================
prompt
create table ADMIN.SYS_DIC
(
  ID     VARCHAR2(32) not null,
  CODE   VARCHAR2(50) not null,
  NAME   VARCHAR2(60) not null,
  TYPE   VARCHAR2(50) not null,
  REMARK VARCHAR2(600)
)
tablespace ATMS
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
comment on column ADMIN.SYS_DIC.CODE
  is '编码';
comment on column ADMIN.SYS_DIC.NAME
  is '名称';
comment on column ADMIN.SYS_DIC.TYPE
  is '字典类型';
comment on column ADMIN.SYS_DIC.REMARK
  is '备注';
alter table ADMIN.SYS_DIC
  add constraint SYS_DIC_PK primary key (ID, CODE, NAME, TYPE)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_FTP_INFO
prompt ===========================
prompt
create table ADMIN.SYS_FTP_INFO
(
  ID          VARCHAR2(20) not null,
  FTPIP       VARCHAR2(100) not null,
  FTPUSER     VARCHAR2(50),
  FTPPASSWORD VARCHAR2(50),
  FTPPORT     VARCHAR2(5) default 21,
  NAME        VARCHAR2(100) not null,
  HTTPPORT    VARCHAR2(5) default 80,
  DIRNAME     VARCHAR2(50)
)
tablespace ATMS
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
alter table ADMIN.SYS_FTP_INFO
  add constraint PK_FTPINFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_LOGIN_LOG
prompt ============================
prompt
create table ADMIN.SYS_LOGIN_LOG
(
  ID             VARCHAR2(32) not null,
  OPERATOR_ID    VARCHAR2(40),
  IP_ADDRESS     VARCHAR2(50),
  OPERATOR_NAME  VARCHAR2(50),
  TYPE           VARCHAR2(2),
  LOG_LEVEL      VARCHAR2(2),
  OPERATION_TIME DATE,
  CONTENT        VARCHAR2(200),
  CREATE_DATE    DATE,
  CREATE_BY      VARCHAR2(40),
  UPDATE_BY      VARCHAR2(40),
  UPDATE_DATE    DATE,
  STATUS         VARCHAR2(2)
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 2M
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.SYS_LOGIN_LOG
  add primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 896K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SYS_OPER_LOG
prompt ===========================
prompt
create table ADMIN.SYS_OPER_LOG
(
  ID          VARCHAR2(32) not null,
  CLASSNAME   VARCHAR2(50),
  METHODNAME  VARCHAR2(30),
  MODULENAME  VARCHAR2(50),
  OPERATETYPE VARCHAR2(10),
  ISSUCCESS   VARCHAR2(10),
  DES         VARCHAR2(50),
  OPERATETIME DATE,
  OPERATOR    VARCHAR2(20),
  IP          VARCHAR2(50),
  PARAMS      CLOB,
  CONTENT     CLOB
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 9M
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.SYS_OPER_LOG
  add constraint SYS_OPER_LOG_PK primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 832K
    next 1M
    minextents 1
    maxextents unlimited
  );
create bitmap index ADMIN.OPER_TIME_IP_INDEX on ADMIN.SYS_OPER_LOG (OPERATETIME, IP)
  tablespace ATMS
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
prompt Creating table SYS_ORG
prompt ======================
prompt
create table ADMIN.SYS_ORG
(
  ID            VARCHAR2(32) not null,
  PARENTID      VARCHAR2(32),
  NAME          VARCHAR2(80) not null,
  MEMO          VARCHAR2(500),
  STATUS        VARCHAR2(2),
  LEVELS        INTEGER,
  CODE          VARCHAR2(50),
  PRINCIPAL     VARCHAR2(32),
  MOBILE        VARCHAR2(13),
  PRINCIPALNAME VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.SYS_ORG.CODE
  is '部门编码';
comment on column ADMIN.SYS_ORG.PRINCIPAL
  is '责任人';
comment on column ADMIN.SYS_ORG.MOBILE
  is '联系电话';
comment on column ADMIN.SYS_ORG.PRINCIPALNAME
  is '责任人姓名';
alter table ADMIN.SYS_ORG
  add primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_PARAMS
prompt =========================
prompt
create table ADMIN.SYS_PARAMS
(
  ID    VARCHAR2(32) default sys_guid() not null,
  TYPES VARCHAR2(30) not null,
  NAME  VARCHAR2(100) not null,
  VALUE VARCHAR2(600) not null,
  MEMO  VARCHAR2(600)
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.SYS_PARAMS
  add primary key (ID)
  using index 
  tablespace ATMS
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
create bitmap index ADMIN.DIC_TYPES_NAME_INDEX on ADMIN.SYS_PARAMS (TYPES, NAME)
  tablespace ATMS
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
create index ADMIN.PARAMS_TYPE_NAME on ADMIN.SYS_PARAMS (TYPES)
  tablespace ATMS
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
prompt Creating table SYS_PICMIXTURE_INFO
prompt ==================================
prompt
create table ADMIN.SYS_PICMIXTURE_INFO
(
  ID      VARCHAR2(20),
  NAME    VARCHAR2(100),
  PICPATH VARCHAR2(200),
  NOTE    VARCHAR2(200)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table SYS_POLICE_CAR
prompt =============================
prompt
create table ADMIN.SYS_POLICE_CAR
(
  ID             VARCHAR2(32) not null,
  PLATE_NUM      VARCHAR2(20),
  PLATE_TYPE     VARCHAR2(10),
  CAR_BRAND      VARCHAR2(32),
  ORG_ID         VARCHAR2(32),
  IS_ALARM       CHAR(1),
  CREATE_TIME    DATE,
  BUY_TIME       DATE,
  CAR_USE_TYPE   NUMBER,
  TYPES          VARCHAR2(10),
  HAS_GPS        CHAR(1),
  NOTE           VARCHAR2(500),
  CALLNUMBER     VARCHAR2(20),
  FREQUENCY      VARCHAR2(20),
  SKY_SHOOT_CODE VARCHAR2(20),
  STATUS         CHAR(1),
  GPS_TELNUMBER  VARCHAR2(20),
  GPS_SERIAL_ID  VARCHAR2(200),
  IS_TRACK       CHAR(1) default 0
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table ADMIN.SYS_POLICE_CAR
  is '勤务车辆信息表';
comment on column ADMIN.SYS_POLICE_CAR.PLATE_NUM
  is '号牌号码';
comment on column ADMIN.SYS_POLICE_CAR.PLATE_TYPE
  is '号牌种类';
comment on column ADMIN.SYS_POLICE_CAR.CAR_BRAND
  is '车辆中文品牌';
comment on column ADMIN.SYS_POLICE_CAR.ORG_ID
  is '所属部门';
comment on column ADMIN.SYS_POLICE_CAR.IS_ALARM
  is '是否有报警器';
comment on column ADMIN.SYS_POLICE_CAR.CREATE_TIME
  is '添加时间';
comment on column ADMIN.SYS_POLICE_CAR.BUY_TIME
  is '购买时间';
comment on column ADMIN.SYS_POLICE_CAR.CAR_USE_TYPE
  is '车辆使用类型';
comment on column ADMIN.SYS_POLICE_CAR.TYPES
  is '车辆类型';
comment on column ADMIN.SYS_POLICE_CAR.HAS_GPS
  is '是否有GPS定位设备';
comment on column ADMIN.SYS_POLICE_CAR.NOTE
  is '备注';
comment on column ADMIN.SYS_POLICE_CAR.CALLNUMBER
  is '电台呼号';
comment on column ADMIN.SYS_POLICE_CAR.FREQUENCY
  is '电台的频段信息';
comment on column ADMIN.SYS_POLICE_CAR.SKY_SHOOT_CODE
  is '航拍号';
comment on column ADMIN.SYS_POLICE_CAR.STATUS
  is '是否有效1表示有效，0表示无效已删除';
comment on column ADMIN.SYS_POLICE_CAR.GPS_TELNUMBER
  is 'GPS通讯手机号';
comment on column ADMIN.SYS_POLICE_CAR.GPS_SERIAL_ID
  is 'GPS设备序列号';
comment on column ADMIN.SYS_POLICE_CAR.IS_TRACK
  is '是否正在定位跟踪';
alter table ADMIN.SYS_POLICE_CAR
  add constraint PK_SYS_POLICE_CAR primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_POLICE_USER
prompt ==============================
prompt
create table ADMIN.SYS_POLICE_USER
(
  ID            VARCHAR2(32) not null,
  CODE          VARCHAR2(12),
  TYPES         VARCHAR2(32),
  NAME          VARCHAR2(50),
  ORG_ID        VARCHAR2(32),
  POST_CODE     VARCHAR2(32),
  TEL_PHONE     VARCHAR2(11),
  GPS_TELNUMBER VARCHAR2(20),
  CREATE_TIME   DATE,
  NOTE          VARCHAR2(500),
  CALLNUMBER    VARCHAR2(20),
  FREQUENCY     VARCHAR2(20),
  STATUS        CHAR(1) default 1,
  HAS_GPS       CHAR(1) not null,
  GPS_SERIAL_ID VARCHAR2(200),
  IS_TRACK      CHAR(1) default 0
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.SYS_POLICE_USER.CODE
  is '警员编号';
comment on column ADMIN.SYS_POLICE_USER.TYPES
  is '种类(主要包括民警、协警等)';
comment on column ADMIN.SYS_POLICE_USER.NAME
  is '姓名';
comment on column ADMIN.SYS_POLICE_USER.ORG_ID
  is '部门编号';
comment on column ADMIN.SYS_POLICE_USER.POST_CODE
  is '职位编码';
comment on column ADMIN.SYS_POLICE_USER.TEL_PHONE
  is '手机号';
comment on column ADMIN.SYS_POLICE_USER.GPS_TELNUMBER
  is 'GPS通讯手机号';
comment on column ADMIN.SYS_POLICE_USER.CREATE_TIME
  is '添加时间';
comment on column ADMIN.SYS_POLICE_USER.NOTE
  is '备注';
comment on column ADMIN.SYS_POLICE_USER.CALLNUMBER
  is '电台呼号';
comment on column ADMIN.SYS_POLICE_USER.FREQUENCY
  is '电台频段信息';
comment on column ADMIN.SYS_POLICE_USER.STATUS
  is '状态';
comment on column ADMIN.SYS_POLICE_USER.HAS_GPS
  is '是否有GPS定位设备';
comment on column ADMIN.SYS_POLICE_USER.GPS_SERIAL_ID
  is 'GPS设备序列号';
comment on column ADMIN.SYS_POLICE_USER.IS_TRACK
  is '是否正在定位跟踪 1表示正在跟踪  0 表示未启用跟踪';
alter table ADMIN.SYS_POLICE_USER
  add constraint T_ORDER_CAR_PK primary key (ID)
  using index 
  tablespace ATMS
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
create unique index ADMIN.GPSTELNUMBER_INDEX on ADMIN.SYS_POLICE_USER (GPS_TELNUMBER)
  tablespace ATMS
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
create unique index ADMIN.GPS_SERIAL_INDEX on ADMIN.SYS_POLICE_USER (GPS_SERIAL_ID)
  tablespace ATMS
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
prompt Creating table SYS_RESOURCE
prompt ===========================
prompt
create table ADMIN.SYS_RESOURCE
(
  ID          VARCHAR2(32) not null,
  TYPE        VARCHAR2(255) not null,
  NAME        VARCHAR2(100) not null,
  LEAF        NUMBER(1),
  DISABLED    NUMBER(1),
  CODE        VARCHAR2(40),
  ICON        VARCHAR2(20),
  SORT_ORDER  NUMBER(10),
  CONTENT     VARCHAR2(100),
  MEMO        VARCHAR2(500),
  CREATE_DATE DATE,
  CREATE_BY   VARCHAR2(40),
  UPDATE_BY   VARCHAR2(40),
  UPDATE_DATE DATE,
  STATUS      VARCHAR2(2),
  PARENTID    VARCHAR2(32),
  MODULE_CODE VARCHAR2(50)
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.SYS_RESOURCE
  add primary key (ID)
  using index 
  tablespace ATMS
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
create index ADMIN.RES_NAME_CONTENT_TYPE on ADMIN.SYS_RESOURCE (NAME, CONTENT, TYPE)
  tablespace ATMS
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
prompt Creating table SYS_RETRIEVE_INFO
prompt ================================
prompt
create table ADMIN.SYS_RETRIEVE_INFO
(
  ID         VARCHAR2(20) not null,
  CONTENT    CLOB not null,
  DELTIME    DATE not null,
  OPERATOR   VARCHAR2(200) not null,
  CLEARFLAG  VARCHAR2(2) not null,
  POJOCLASS  VARCHAR2(200) not null,
  OPERATORIP VARCHAR2(15)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 384K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ADMIN.SYS_RETRIEVE_INFO.CONTENT
  is '删除内容';
comment on column ADMIN.SYS_RETRIEVE_INFO.DELTIME
  is '删除时间';
comment on column ADMIN.SYS_RETRIEVE_INFO.OPERATOR
  is '操作人';
comment on column ADMIN.SYS_RETRIEVE_INFO.POJOCLASS
  is '数据对象类型';
comment on column ADMIN.SYS_RETRIEVE_INFO.OPERATORIP
  is '操作IP';
alter table ADMIN.SYS_RETRIEVE_INFO
  add constraint PK_SYS_RETRIEVE_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_ROAD_DEVICE_DEPT
prompt ===================================
prompt
create table ADMIN.SYS_ROAD_DEVICE_DEPT
(
  ID        VARCHAR2(20) not null,
  ROAD_ID   VARCHAR2(20),
  DEVICE_ID VARCHAR2(20),
  DEPT_ID   VARCHAR2(20)
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table SYS_ROAD_INFO
prompt ============================
prompt
create table ADMIN.SYS_ROAD_INFO
(
  ID           VARCHAR2(20) not null,
  NAME         VARCHAR2(180),
  NOTE         VARCHAR2(200),
  GROUP_ID     VARCHAR2(20),
  PID          VARCHAR2(20),
  LEVELS       INTEGER,
  STATUS       VARCHAR2(2),
  UPLOADCODE   VARCHAR2(20),
  CODEROADTYPE VARCHAR2(2)
)
tablespace ATMS
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
alter table ADMIN.SYS_ROAD_INFO
  add constraint PK_ROADINFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_ROLE
prompt =======================
prompt
create table ADMIN.SYS_ROLE
(
  ID     VARCHAR2(32) not null,
  CODE   VARCHAR2(30) not null,
  NAME   VARCHAR2(30) not null,
  MEMO   VARCHAR2(400),
  STATUS VARCHAR2(2)
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.SYS_ROLE
  add primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_ROLE_RESOURCES
prompt =================================
prompt
create table ADMIN.SYS_ROLE_RESOURCES
(
  RESOURCE_ID VARCHAR2(32) not null,
  ROLE_ID     VARCHAR2(32) not null
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.SYS_ROLE_RESOURCES
  add primary key (ROLE_ID, RESOURCE_ID)
  using index 
  tablespace ATMS
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
alter table ADMIN.SYS_ROLE_RESOURCES
  add constraint FK7612814E7BDEC0E foreign key (ROLE_ID)
  references ADMIN.SYS_ROLE (ID);
alter table ADMIN.SYS_ROLE_RESOURCES
  add constraint FK7612814E876FB0E foreign key (RESOURCE_ID)
  references ADMIN.SYS_RESOURCE (ID);

prompt
prompt Creating table SYS_USER
prompt =======================
prompt
create table ADMIN.SYS_USER
(
  ID       VARCHAR2(32) not null,
  ACCOUNT  VARCHAR2(30) not null,
  PWD      VARCHAR2(100) not null,
  NAME     VARCHAR2(30) not null,
  DISABLED NUMBER(1) not null,
  MOBILE   VARCHAR2(20),
  DEPTID   VARCHAR2(40),
  MEMO     VARCHAR2(600)
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 8M
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.SYS_USER
  add primary key (ID)
  using index 
  tablespace ATMS
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
create index ADMIN.USER_NAME_ACCOUNT_INDEX on ADMIN.SYS_USER (NAME, ACCOUNT)
  tablespace ATMS
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
create index ADMIN.USER_ORGAN_ID_INDEX on ADMIN.SYS_USER (DEPTID)
  tablespace ATMS
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

prompt
prompt Creating table SYS_USER_DATA_AUTHORITY
prompt ======================================
prompt
create table ADMIN.SYS_USER_DATA_AUTHORITY
(
  USERID VARCHAR2(32) not null,
  MENUID VARCHAR2(32) not null,
  DEPTID VARCHAR2(32) not null
)
tablespace ATMS
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
create index ADMIN.DATA_INDEX on ADMIN.SYS_USER_DATA_AUTHORITY (USERID, MENUID)
  tablespace ATMS
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

prompt
prompt Creating table SYS_USER_DESK
prompt ============================
prompt
create table ADMIN.SYS_USER_DESK
(
  ID          VARCHAR2(20) not null,
  USER_ID     VARCHAR2(32) not null,
  RESOURCE_ID VARCHAR2(4000) not null,
  SCREENNUM   VARCHAR2(2) not null
)
tablespace ATMS
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
alter table ADMIN.SYS_USER_DESK
  add constraint PK_USERDESK_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_USER_DESKTOP_MENU
prompt ====================================
prompt
create table ADMIN.SYS_USER_DESKTOP_MENU
(
  ID     VARCHAR2(32) not null,
  MENUID VARCHAR2(32) not null,
  USERID VARCHAR2(32) not null
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
  );
alter table ADMIN.SYS_USER_DESKTOP_MENU
  add primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
  );

prompt
prompt Creating table SYS_USER_ROLE
prompt ============================
prompt
create table ADMIN.SYS_USER_ROLE
(
  ROLEID VARCHAR2(32) not null,
  USERID VARCHAR2(32) not null
)
tablespace ATMS
  pctfree 10
  pctused 0
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN.SYS_USER_ROLE
  add primary key (USERID, ROLEID)
  using index 
  tablespace ATMS
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
alter table ADMIN.SYS_USER_ROLE
  add constraint FKAABB7D587E839E9B foreign key (ROLEID)
  references ADMIN.SYS_ROLE (ID);
alter table ADMIN.SYS_USER_ROLE
  add constraint FKAABB7D5883D8F405 foreign key (USERID)
  references ADMIN.SYS_USER (ID);

prompt
prompt Creating table SYS_USER_SKIN
prompt ============================
prompt
create table ADMIN.SYS_USER_SKIN
(
  ID      VARCHAR2(32) not null,
  USER_ID VARCHAR2(32) not null,
  SKIN    VARCHAR2(2) not null
)
tablespace ATMS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255;
comment on table ADMIN.SYS_USER_SKIN
  is '用户皮肤表';
alter table ADMIN.SYS_USER_SKIN
  add constraint PK_USERSKIN_ID primary key (ID)
  using index 
  tablespace ATMS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table SYS_VERSION_NOTE
prompt ===============================
prompt
create table ADMIN.SYS_VERSION_NOTE
(
  ID         VARCHAR2(10) not null,
  JOBNAME    VARCHAR2(500),
  JOBCONTENT VARCHAR2(500),
  RUNTIME    DATE,
  NOTE       VARCHAR2(200),
  VERSION    VARCHAR2(20)
)
tablespace ATMS
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

prompt
prompt Creating table SYS_VIDEOADDRESS_INFO
prompt ====================================
prompt
create table ADMIN.SYS_VIDEOADDRESS_INFO
(
  ID          VARCHAR2(20) not null,
  IP          VARCHAR2(20),
  PORT        VARCHAR2(10),
  TRACODE     VARCHAR2(20),
  USERNAME    VARCHAR2(20),
  PASSWORD    VARCHAR2(50),
  ISREALITIME VARCHAR2(10),
  NAME        VARCHAR2(100) not null
)
tablespace ATMS
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
alter table ADMIN.SYS_VIDEOADDRESS_INFO
  add constraint PK_VIDEOADDRESS_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating table SYS_VIDEO_INFO
prompt =============================
prompt
create table ADMIN.SYS_VIDEO_INFO
(
  ID                 VARCHAR2(20) not null,
  DIRECTION_CODE     VARCHAR2(20) not null,
  VIDEOTYPE_CODE     VARCHAR2(20) not null,
  ISCLOUD            VARCHAR2(2),
  ROADINFO_ID        VARCHAR2(20) not null,
  NAME               VARCHAR2(200) not null,
  ORG_ID             VARCHAR2(32) not null,
  IP                 VARCHAR2(15),
  PORT               VARCHAR2(10),
  TRACODE            VARCHAR2(10),
  USERNAME           VARCHAR2(50),
  PASSWORD           VARCHAR2(50),
  IPHIS              VARCHAR2(15),
  PORTHIS            VARCHAR2(10),
  TRACODEHIS         VARCHAR2(10),
  USERNAMEHIS        VARCHAR2(50),
  PASSWORDHIS        VARCHAR2(50),
  COMPANYID          VARCHAR2(20),
  PHOTO1             VARCHAR2(200),
  PHOTO2             VARCHAR2(200),
  PHOTO3             VARCHAR2(200),
  NOTE               VARCHAR2(200),
  USETEMPLATE        VARCHAR2(2) not null,
  REALVIDEOADDRESSID VARCHAR2(20),
  HISVIDEOADDRESSID  VARCHAR2(20),
  MAPX               VARCHAR2(50),
  MAPY               VARCHAR2(50),
  CODE               VARCHAR2(18),
  DEVICE_IP          VARCHAR2(15)
)
tablespace ATMS
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
comment on column ADMIN.SYS_VIDEO_INFO.IP
  is '流媒体服务器IP';
comment on column ADMIN.SYS_VIDEO_INFO.PORT
  is '端口号';
comment on column ADMIN.SYS_VIDEO_INFO.USERNAME
  is '用户名';
comment on column ADMIN.SYS_VIDEO_INFO.PASSWORD
  is '密码';
comment on column ADMIN.SYS_VIDEO_INFO.MAPX
  is '设备经度';
comment on column ADMIN.SYS_VIDEO_INFO.MAPY
  is '设备纬度';
comment on column ADMIN.SYS_VIDEO_INFO.CODE
  is '设备编号:设备管理机构+设备类型05+4位自增';
comment on column ADMIN.SYS_VIDEO_INFO.DEVICE_IP
  is '监控设备IP地址';
alter table ADMIN.SYS_VIDEO_INFO
  add constraint PK_VIDEOINFO_ID primary key (ID)
  using index 
  tablespace ATMS
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
prompt Creating sequence ATTACHMENT_ID_SEQ
prompt ===================================
prompt
create sequence ADMIN.ATTACHMENT_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence BOOLEANEXPR_ID_SEQ
prompt ====================================
prompt
create sequence ADMIN.BOOLEANEXPR_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence COMMENT_ID_SEQ
prompt ================================
prompt
create sequence ADMIN.COMMENT_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence CONTENT_ID_SEQ
prompt ================================
prompt
create sequence ADMIN.CONTENT_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence DBOBJECTID_SEQUENCE
prompt =====================================
prompt
create sequence ADMIN.DBOBJECTID_SEQUENCE
minvalue 1
maxvalue 999999999999999999999999
start with 1
increment by 50
cache 50;

prompt
prompt Creating sequence DEADLINE_ID_SEQ
prompt =================================
prompt
create sequence ADMIN.DEADLINE_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence EMAILNOTIFHEAD_ID_SEQ
prompt =======================================
prompt
create sequence ADMIN.EMAILNOTIFHEAD_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence ESCALATION_ID_SEQ
prompt ===================================
prompt
create sequence ADMIN.ESCALATION_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence HIBERNATE_SEQUENCE
prompt ====================================
prompt
create sequence ADMIN.HIBERNATE_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence I18NTEXT_ID_SEQ
prompt =================================
prompt
create sequence ADMIN.I18NTEXT_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence NODE_INST_LOG_ID_SEQ
prompt ======================================
prompt
create sequence ADMIN.NODE_INST_LOG_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence NOTIFICATION_ID_SEQ
prompt =====================================
prompt
create sequence ADMIN.NOTIFICATION_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence PROC_INST_LOG_ID_SEQ
prompt ======================================
prompt
create sequence ADMIN.PROC_INST_LOG_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence REASSIGNMENT_ID_SEQ
prompt =====================================
prompt
create sequence ADMIN.REASSIGNMENT_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SESSIONINFO_ID_SEQ
prompt ====================================
prompt
create sequence ADMIN.SESSIONINFO_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence VAR_INST_LOG_ID_SEQ
prompt =====================================
prompt
create sequence ADMIN.VAR_INST_LOG_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence WORKITEMINFO_ID_SEQ
prompt =====================================
prompt
create sequence ADMIN.WORKITEMINFO_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;


spool off
