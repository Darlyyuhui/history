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
  is '��������';
comment on column ADMIN.EMERGENCY_ATTACH_INFO.TYPE
  is '��������';
comment on column ADMIN.EMERGENCY_ATTACH_INFO.PATH
  is '����·��';
comment on column ADMIN.EMERGENCY_ATTACH_INFO.UPLOADNAME
  is '�ϴ�����';
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
  is 'Ӧ��������';
comment on column ADMIN.EMERGENCY_CAR.ID
  is 'id';
comment on column ADMIN.EMERGENCY_CAR.CARTYPE
  is '��������';
comment on column ADMIN.EMERGENCY_CAR.UNIT
  is '������λ';
comment on column ADMIN.EMERGENCY_CAR.STATE
  is '״̬';
comment on column ADMIN.EMERGENCY_CAR.POSITION
  is '����λ��';
comment on column ADMIN.EMERGENCY_CAR.PRINCIPAL
  is '������';
comment on column ADMIN.EMERGENCY_CAR.NUM
  is '����';
comment on column ADMIN.EMERGENCY_CAR.SPECIFICATION
  is '���';
comment on column ADMIN.EMERGENCY_CAR.PRINCIPALCALL
  is '����绰';
comment on column ADMIN.EMERGENCY_CAR.USE
  is '��;';
comment on column ADMIN.EMERGENCY_CAR.CONTRACTEDUNITS
  is 'ǩԼ��λ';
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
  is 'Ӧ�����������';
comment on column ADMIN.EMERGENCY_CASE.ID
  is '����id';
comment on column ADMIN.EMERGENCY_CASE.FILETYPE
  is '�ļ���Դ��1���ֹ�¼�룬0���ļ��ϴ�';
comment on column ADMIN.EMERGENCY_CASE.NAME
  is '�ļ����ƣ��ļ��ϴ��У�';
comment on column ADMIN.EMERGENCY_CASE.FILEPATH
  is '�ļ����·�����ļ��ϴ��У�';
comment on column ADMIN.EMERGENCY_CASE.CONTEXT
  is '�ļ����ݣ��ֹ�¼���ã�';
comment on column ADMIN.EMERGENCY_CASE.CASETYPE
  is '��������';
comment on column ADMIN.EMERGENCY_CASE.INSERTDATE
  is '¼��ʱ��';
comment on column ADMIN.EMERGENCY_CASE.SUFFIX
  is '�ļ���׺';
comment on column ADMIN.EMERGENCY_CASE.SWFFILEPATH
  is 'swf�ļ�·��(�ϴ��ļ���)';
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
  is 'ֵ��ʱ��';
comment on column ADMIN.EMERGENCY_DUTY.USERS
  is 'ֵ��Ա';
comment on column ADMIN.EMERGENCY_DUTY.LEADER
  is 'ֵ���쵼';
comment on column ADMIN.EMERGENCY_DUTY.DAYS
  is '���ڼ�';
comment on column ADMIN.EMERGENCY_DUTY.LEADER_MOBILE
  is 'ֵ���쵼�ֻ���';
comment on column ADMIN.EMERGENCY_DUTY.REMARK
  is '��ע';
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
  is '�ļ�����';
comment on column ADMIN.EMERGENCY_FILE_INFO.CONTENT
  is '�ļ�����';
comment on column ADMIN.EMERGENCY_FILE_INFO.DISABLED
  is '�Ƿ���ʾ';
comment on column ADMIN.EMERGENCY_FILE_INFO.PID
  is '���ڵ���';
comment on column ADMIN.EMERGENCY_FILE_INFO.USERID
  is '�û����';
comment on column ADMIN.EMERGENCY_FILE_INFO.OPERTYPE
  is '��������';
comment on column ADMIN.EMERGENCY_FILE_INFO.OPERTIME
  is '����ʱ��';
comment on column ADMIN.EMERGENCY_FILE_INFO.HASATTACH
  is '�Ƿ��������';
comment on column ADMIN.EMERGENCY_FILE_INFO.ATTACHNOTE
  is '������ע';
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
  is 'Ӧ��֪ʶ�����';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.ID
  is '����id';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.TYPE
  is '�ļ���Դ��1���ֹ�¼�룬0���ļ��ϴ�';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.NAME
  is '�ļ�����';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.PATH
  is '�ļ����·�����ļ��ϴ��У�';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.CONTEXT
  is '�ļ����ݣ��ֹ�¼���ã�';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.INTELLECTUAL_TYPE
  is '֪ʶ����';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.USERID
  is '�û�ID';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.OPERTYPE
  is '��������';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.OPERTIME
  is '����ʱ��';
comment on column ADMIN.EMERGENCY_INTELLECTUAL.SUFFIX
  is '�ļ���׺';
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
  is 'Ӧ�����ʱ�';
comment on column ADMIN.EMERGENCY_MATERIAL.ID
  is ' id';
comment on column ADMIN.EMERGENCY_MATERIAL.MATERIALTYPE
  is '��������';
comment on column ADMIN.EMERGENCY_MATERIAL.UNIT
  is '������λ';
comment on column ADMIN.EMERGENCY_MATERIAL.STATE
  is '״̬';
comment on column ADMIN.EMERGENCY_MATERIAL.POSITION
  is '����λ��';
comment on column ADMIN.EMERGENCY_MATERIAL.PRINCIPAL
  is '������';
comment on column ADMIN.EMERGENCY_MATERIAL.NUM
  is '����';
comment on column ADMIN.EMERGENCY_MATERIAL.SPECIFICATION
  is '���';
comment on column ADMIN.EMERGENCY_MATERIAL.PRINCIPALCALL
  is '����绰';
comment on column ADMIN.EMERGENCY_MATERIAL.USE
  is '��;';
comment on column ADMIN.EMERGENCY_MATERIAL.CONTRACTEDUNITS
  is 'ǩԼ��λ';
comment on column ADMIN.EMERGENCY_MATERIAL.LONGITUDE
  is '����';
comment on column ADMIN.EMERGENCY_MATERIAL.LATITUDE
  is 'γ��';
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
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS.NAME
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS.TYPES
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS.KIND
  is '0��ʾ����Ԥ��   1��ʾ����Ԥ��';
comment on column ADMIN.EMERGENCY_PLANS.LEVELS
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS.CREATE_DATE
  is '��������';
comment on column ADMIN.EMERGENCY_PLANS.CREATE_USERID
  is '������';
comment on column ADMIN.EMERGENCY_PLANS.UPDATE_DATE
  is '�޸�����';
comment on column ADMIN.EMERGENCY_PLANS.UPDATE_USERID
  is '�޸���';
comment on column ADMIN.EMERGENCY_PLANS.VERSIONS
  is '�汾��';
comment on column ADMIN.EMERGENCY_PLANS.ORG_ID
  is '����id';
comment on column ADMIN.EMERGENCY_PLANS.ORG_NAME
  is '��������';
comment on column ADMIN.EMERGENCY_PLANS.KEYWORDS
  is '�ؼ���';
comment on column ADMIN.EMERGENCY_PLANS.STATUS
  is '״̬0 δ����  1����  2ͣ�� 3ɾ��
';
comment on column ADMIN.EMERGENCY_PLANS.PUBLISH_DATE
  is '��������';
comment on column ADMIN.EMERGENCY_PLANS.PUBLISH_USER
  is '������';
comment on column ADMIN.EMERGENCY_PLANS.SUIT_USE
  is '���÷�Χ';
comment on column ADMIN.EMERGENCY_PLANS.START_CONDITION
  is '��������';
comment on column ADMIN.EMERGENCY_PLANS.CASES
  is '��ذ���';
comment on column ADMIN.EMERGENCY_PLANS.AUDIT_STATUS
  is '���״̬ 0δ���   1�ύ����    2���ͨ��   3 ���δͨ��   4����';
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
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS_ATTR.FILE_PATH
  is '����·��';

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
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS_ATTR_VERS.FILE_PATH
  is '����·��';
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
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS_DOC.START_TIME
  is '����ʱ��';
comment on column ADMIN.EMERGENCY_PLANS_DOC.STOP_TIME
  is '��ֹʱ��';
comment on column ADMIN.EMERGENCY_PLANS_DOC.START_USER_ID
  is '������';
comment on column ADMIN.EMERGENCY_PLANS_DOC.STOP_USER_ID
  is '��ֹ��';
comment on column ADMIN.EMERGENCY_PLANS_DOC.ACCIDENT_ID
  is '�¹ʱ��';
comment on column ADMIN.EMERGENCY_PLANS_DOC.SUMUP
  is '�¹ʵ����ܽ�';
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
  is '����';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.CONTENT
  is '����';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.ACTION
  is '������ʶ����';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.NOTE
  is '��ע';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.CODE
  is '�������';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.CHARGER
  is '������';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.EXECUTORS
  is 'ʵʩ��';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.STEP
  is '�����ʶ';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.EXESTARTTIME
  is '��ʼִ��ʱ��';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.EXEFINISHTIME
  is 'ִ�����ʱ��';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.IS_SHOW_GPS
  is '�Ƿ���ʾgpsλ����Ϣ';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.IS_SHOW_VIDEO
  is '�Ƿ���ʾ�����Ƶ';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.TEAMS
  is 'Ӧ������';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.UINITS
  is 'Ӧ����λ,�ö��ŷָ�';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.CARS
  is 'Ӧ������';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.GOODS
  is '����';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.EXPERTS
  is '���ר��';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.KNOWS
  is '֪ʶ��';
comment on column ADMIN.EMERGENCY_PLANS_ITEM.PLANS_ID
  is 'Ԥ��id';
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
  is '����';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.CONTENT
  is '����';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.ACTION
  is '������ʶ����';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.NOTE
  is '��ע';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.CODE
  is '�������';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.CHARGER
  is '������';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.EXECUTORS
  is 'ʵʩ��';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.STEP
  is '�����ʶ';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.EXESTARTTIME
  is '��ʼִ��ʱ��';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.EXEFINISHTIME
  is 'ִ�����ʱ��';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.IS_SHOW_GPS
  is '�Ƿ���ʾgpsλ����Ϣ';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.IS_SHOW_VIDEO
  is '�Ƿ���ʾ�����Ƶ';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.TEAMS
  is 'Ӧ������';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.UINITS
  is 'Ӧ����λ,�ö��ŷָ�';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.CARS
  is 'Ӧ������';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.GOODS
  is '����';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.EXPERTS
  is '���ר��';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.KNOWS
  is '֪ʶ��';
comment on column ADMIN.EMERGENCY_PLANS_ITEM_VERS.PLANS_ID
  is 'Ԥ��id';
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
  is 'Ԥ��ID';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS.VERIFY_USERID
  is '������';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS.VERIFY_DATE
  is '��������';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS.VERIFY_NOTE
  is '�������';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS.VERIFY_STATUS
  is '����״̬   0δ���   1�ύ����    2���ͨ��   3 ���δͨ��';
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
  is 'Ԥ��ID';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS_VERS.VERIFY_USERID
  is '������';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS_VERS.VERIFY_DATE
  is '��������';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS_VERS.VERIFY_NOTE
  is '�������';
comment on column ADMIN.EMERGENCY_PLANS_VERIFYS_VERS.VERIFY_STATUS
  is '����״̬   0δ���   1�ύ����    2���ͨ��   3 ���δͨ��';
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
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.NAME
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.TYPES
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.KIND
  is '0��ʾ����Ԥ��   1��ʾ����Ԥ��';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.LEVELS
  is 'Ԥ������';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.CREATE_DATE
  is '��������';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.CREATE_USERID
  is '������';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.UPDATE_DATE
  is '�޸�����';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.UPDATE_USERID
  is '�޸���';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.VERSIONS
  is '�汾��';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.ORG_ID
  is '����id';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.ORG_NAME
  is '��������';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.KEYWORDS
  is '�ؼ���';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.STATUS
  is '״̬0 δ����  1����  2ͣ�� 3ɾ��
';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.PUBLISH_DATE
  is '��������';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.PUBLISH_USER
  is '������';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.SUIT_USE
  is '���÷�Χ';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.START_CONDITION
  is '��������';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.CASES
  is '��ذ���';
comment on column ADMIN.EMERGENCY_PLANS_VERSIONS.AUDIT_STATUS
  is '���״̬';
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
  is '����';
comment on column ADMIN.EMERGENCY_PROFICIENT.DEPTNAME
  is '������λ';
comment on column ADMIN.EMERGENCY_PROFICIENT.POSITION
  is 'ְλ';
comment on column ADMIN.EMERGENCY_PROFICIENT.SEX
  is '�Ա�';
comment on column ADMIN.EMERGENCY_PROFICIENT.AGE
  is '����';
comment on column ADMIN.EMERGENCY_PROFICIENT.FUNCTION
  is 'ְ��';
comment on column ADMIN.EMERGENCY_PROFICIENT.TYPE
  is 'ר�����';
comment on column ADMIN.EMERGENCY_PROFICIENT.SPECIALITY
  is 'Ӧ���س�';
comment on column ADMIN.EMERGENCY_PROFICIENT.NOTE
  is '��ע';
comment on column ADMIN.EMERGENCY_PROFICIENT.SPECIALITY_DESC
  is '�س�����';
comment on column ADMIN.EMERGENCY_PROFICIENT.WORK_RESUME
  is '��������';
comment on column ADMIN.EMERGENCY_PROFICIENT.NATION
  is '����';
comment on column ADMIN.EMERGENCY_PROFICIENT.ORIGIN_PLACE
  is '����';
comment on column ADMIN.EMERGENCY_PROFICIENT.ID_CARD
  is '���֤����';
comment on column ADMIN.EMERGENCY_PROFICIENT.MOBILE_CODE
  is '�ֻ�����';
comment on column ADMIN.EMERGENCY_PROFICIENT.TELEPHONE
  is '��ϵ�绰';
comment on column ADMIN.EMERGENCY_PROFICIENT.EMAIL
  is '��������';
comment on column ADMIN.EMERGENCY_PROFICIENT.POLITICS_FACE
  is '������ò';
comment on column ADMIN.EMERGENCY_PROFICIENT.GRADUATE_COLLEGE
  is '��ҵԺУ';
comment on column ADMIN.EMERGENCY_PROFICIENT.TOP_RECORD
  is '���ѧ��';
comment on column ADMIN.EMERGENCY_PROFICIENT.JOIN_WORK_TIME
  is '�μӹ���ʱ��';
comment on column ADMIN.EMERGENCY_PROFICIENT.HEALTH_STATUS
  is '����״��';
comment on column ADMIN.EMERGENCY_PROFICIENT.REGION
  is '��������';
comment on column ADMIN.EMERGENCY_PROFICIENT.PHOTO_URL
  is '��Ƭ·��';
comment on column ADMIN.EMERGENCY_PROFICIENT.HOME_ADDRESS
  is '��ͥסַ';
comment on column ADMIN.EMERGENCY_PROFICIENT.COMMUNICATE_ADDRESS
  is 'ͨ�ŵ�ַ';
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
  is 'Ӧ������';
comment on column ADMIN.EMERGENCY_TEAM.ID
  is 'id';
comment on column ADMIN.EMERGENCY_TEAM.PRINCIPAL
  is '������';
comment on column ADMIN.EMERGENCY_TEAM.NUM
  is '����';
comment on column ADMIN.EMERGENCY_TEAM.PRINCIPALCALL
  is '����绰';
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
  is 'Ӧ����λ';
comment on column ADMIN.EMERGENCY_UNIT.ID
  is 'id';
comment on column ADMIN.EMERGENCY_UNIT.UNITNAME
  is '��λ����';
comment on column ADMIN.EMERGENCY_UNIT.CORPORATE
  is '���˴���';
comment on column ADMIN.EMERGENCY_UNIT.UNITSHORT
  is '��λ���';
comment on column ADMIN.EMERGENCY_UNIT.OPERATIONTYPE
  is 'ҵ�����';
comment on column ADMIN.EMERGENCY_UNIT.MANAGETYPE
  is '�������';
comment on column ADMIN.EMERGENCY_UNIT.DUTYCALL
  is 'ֵ��绰';
comment on column ADMIN.EMERGENCY_UNIT.LONGITUDE
  is '����';
comment on column ADMIN.EMERGENCY_UNIT.LATITUDE
  is 'γ��';
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
  is 'ä�˹���������ʾװ��';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.ID
  is '����id';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.ROAD
  is '��·';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.INTERSECTIONNAME
  is '·������';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.MOUNTINGORIENTATION
  is '��װ��λ(���ࡢ���ࡢ�����ࡢ�������,�����ֵ�)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.COUNSTRUCTIONTIME
  is '����ʱ��';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.COUNSTRUCTIONUNIT
  is '���赥λ';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.DEVICESIGNALS
  is '�豸�ź�';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.DEVICETYPE
  is '�豸����(����ʽ�����ʽ��,�����ֵ�)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.VOCALFORM
  is '������ʽ(���ꡢ����,�����ֵ�)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.PICTURESCENE
  is '�ֳ�ͼƬ(ͼƬ·��)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.EQUIPMENTMADE
  is '�豸����';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.DESIGNERS
  is '�����Ա';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.ROADID
  is '���id,��·��id';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE.STARTPOINT
  is 'λ��';
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
  is 'ä�˹���������ʾװ����ʷ���ݱ�';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.ID
  is '����id';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.HISID
  is '���';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.ROAD
  is '��·';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.INTERSECTIONNAME
  is '·������';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.MOUNTINGORIENTATION
  is '��װ��λ(���ࡢ���ࡢ�����ࡢ�������,�����ֵ�)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.COUNSTRUCTIONTIME
  is '����ʱ��';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.COUNSTRUCTIONUNIT
  is '���赥λ';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.DEVICESIGNALS
  is '�豸�ź�';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.DEVICETYPE
  is '�豸����(����ʽ�����ʽ��,�����ֵ�)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.VOCALFORM
  is '������ʽ(���ꡢ����,�����ֵ�)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.PICTURESCENE
  is '�ֳ�ͼƬ(ͼƬ·��)';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.EQUIPMENTMADE
  is '�豸����';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.DESIGNERS
  is '�����Ա';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.ROADID
  is '��·id';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_HIS.STARTPOINT
  is 'λ��';
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
  is 'ä�˹���������ʾװ�õ�·��Ϣ��';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_ROAD.ID
  is '����id';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_ROAD.ROAD
  is '��·';
comment on column ADMIN.JTSS_AUDIBLEALERTDEVICE_ROAD.INTERSECTIONNAME
  is '·������';
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
  is '��ͨ������ʩ ,����';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.ID
  is '����sys_guid()';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.ROADPARTNAME
  is '·������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.ORIGIN
  is '��㣨�ཻ·���ƣ�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.DESTINATION
  is '�յ㣨�ཻ·���ƣ�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.ROADPARTCODE
  is '·�α�ţ����չ������ɣ�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.TYPE
  is '����(�½������졢ά�޵� �����ֵ�)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.RAILHEIGHT
  is '�����߶ȣ���λ���ף�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.RAILWIDTH
  is '�������ȣ���λ���ף�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.MATERIAL
  is '���ʣ����ϡ��ֲĵ� �����ֵ䣩';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.COLOR
  is '��ɫ����ɫ�ȣ�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.BASELONG
  is '����������λ�����ף�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.BASEWIDTH
  is '������(��λ������)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.NUM
  is '����(��λ����)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.TOOL
  is '����(�Ƿ���ר�ù���)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.PERSONOPENNUM
  is '�˿�������(��λ����)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.PERSONOPENWIDTH
  is '�˿��ڳ���';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CAROPENNUM
  is '����������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CAROPENWIDTH
  is '�����ڳ���';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.TOTALLENGTH
  is '�ܳ���';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.PRODUCTIONPLACE
  is '����';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.PRODUCER
  is '������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.INSTALLUNIT
  is '��װ��λ';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.INSTALLSTARTDATE
  is '��װ��ʼ����';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.INSTALLENDDATE
  is '��װ��������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CONSTRUCTIONNAME
  is 'ʩ��������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CHARGECONSTRUCTIONNAME
  is 'ʩ������������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CONSTRUCTIONBEFOREIMG
  is 'ʩ��ǰͼƬ';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CONSTRUCTIONAFTERIMG
  is 'ʩ����ͼƬ';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.UNITMAINTENANCE
  is '����λά��';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.FECES
  is '�������Ƿ�ȡ��';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.DESIGNUNITS
  is '��Ƶ�λ';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CONNECT1
  is '���Ӽ�(��������˿��)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.CARCROSS
  is '�����������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.PEOCROSS
  is '�˿��������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.ROADCOLORVALUE
  is '·����ɫ';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES.STOPCOORD
  is 'ͣ���������1';
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
  is '��ͨ������ʩ ,������ʷ���ݱ�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.ID
  is '����sys_guid()';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.HISID
  is '���id';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.ROADPARTNAME
  is '·������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.ORIGIN
  is '��㣨�ཻ·���ƣ�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.DESTINATION
  is '�յ㣨�ཻ·���ƣ�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.ROADPARTCODE
  is '·�α�ţ����չ������ɣ�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.TYPE
  is '����(�½������졢ά�޵� �����ֵ�)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.RAILHEIGHT
  is '�����߶ȣ���λ���ף�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.RAILWIDTH
  is '�������ȣ���λ���ף�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.MATERIAL
  is '���ʣ����ϡ��ֲĵ� �����ֵ䣩';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.COLOR
  is '��ɫ����ɫ�ȣ�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.BASELONG
  is '����������λ�����ף�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.BASEWIDTH
  is '������(��λ������)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.NUM
  is '����(��λ����)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.TOOL
  is '����(�Ƿ���ר�ù���)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.PERSONOPENNUM
  is '�˿�������(��λ����)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.PERSONOPENWIDTH
  is '�˿��ڳ���';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CAROPENNUM
  is '����������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CAROPENWIDTH
  is '�����ڳ���';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.TOTALLENGTH
  is '�ܳ���';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.PRODUCTIONPLACE
  is '����';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.PRODUCER
  is '������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.INSTALLUNIT
  is '��װ��λ';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.INSTALLSTARTDATE
  is '��װ��ʼ����';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.INSTALLENDDATE
  is '��װ��������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CONSTRUCTIONNAME
  is 'ʩ��������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CHARGECONSTRUCTIONNAME
  is 'ʩ������������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CONSTRUCTIONBEFOREIMG
  is 'ʩ��ǰͼƬ';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CONSTRUCTIONAFTERIMG
  is 'ʩ����ͼƬ';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.UNITMAINTENANCE
  is '����λά��';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.FECES
  is '�������Ƿ�ȡ��';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.DESIGNUNITS
  is '��Ƶ�λ';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CONNECT1
  is '���Ӽ�(��������˿��)';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.CARCROSS
  is '�����������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.PEOCROSS
  is '�˿��������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.ROADCOLORVALUE
  is '·����ɫ';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_HIS.STOPCOORD
  is 'ͣ���������1';
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
  is '��ͨ������ʩ ����ͼ��ά����';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.STARTPOINT
  is '���';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.ENDPOINT
  is '�յ�';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.STOPPOINT
  is 'ͣ����';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.CARCROSS
  is '������';
comment on column ADMIN.JTSS_ISOLATIONFACILITIES_IMAGE.PEOCROSS
  is '�˿���';
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
  is '·�γ�����';
comment on column ADMIN.JTSS_LONGROADMARKING.ID
  is '//����sys_guid()';
comment on column ADMIN.JTSS_LONGROADMARKING.ROADNAME
  is '·������';
comment on column ADMIN.JTSS_LONGROADMARKING.STARTPOINT
  is '���';
comment on column ADMIN.JTSS_LONGROADMARKING.END
  is '�յ�';
comment on column ADMIN.JTSS_LONGROADMARKING.ROADCODE
  is '·�α��';
comment on column ADMIN.JTSS_LONGROADMARKING.ISMARKING
  is '�Ƿ��б���(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING.ROADBOARDS
  is '��·���(��·��Ϊ4����壬�ֱ���1���....4���,�����ֵ�)';
comment on column ADMIN.JTSS_LONGROADMARKING.PLATES1WIDTH
  is '���1�Ŀ��(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING.PLATES2WIDTH
  is '���2�Ŀ��(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING.PLATES3WIDTH
  is '���3�Ŀ��(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING.PLATES4WIDTH
  is '���4�Ŀ��(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING.LINELENGTH
  is '���߳���';
comment on column ADMIN.JTSS_LONGROADMARKING.TYPE
  is '���(˫ʵ�ߡ���ʵ�ߡ����ߵ�)';
comment on column ADMIN.JTSS_LONGROADMARKING.STANDARDWIDTH
  is '���߿�(��λ��CM)';
comment on column ADMIN.JTSS_LONGROADMARKING.STANDARDCABLELENGTH
  is '���߳�(=·�γ�-·���������쳤��*2)';
comment on column ADMIN.JTSS_LONGROADMARKING.LONGDASHED
  is '���߳�(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING.DASHEDEMPTY
  is '���߿�(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING.NUM
  is '����(��λ����)';
comment on column ADMIN.JTSS_LONGROADMARKING.AREA
  is '���(ƽ����)';
comment on column ADMIN.JTSS_LONGROADMARKING.CONSTRUCTIONTIME
  is 'ʩ��ʱ��';
comment on column ADMIN.JTSS_LONGROADMARKING.CONSTRUCTIONUNIT
  is 'ʩ����λ';
comment on column ADMIN.JTSS_LONGROADMARKING.CONSTRUCTIONTYPE
  is 'ʩ������(�½����޲���)';
comment on column ADMIN.JTSS_LONGROADMARKING.BEFORECONSTRUCTIONPICTURES
  is 'ʩ��ǰͼƬ';
comment on column ADMIN.JTSS_LONGROADMARKING.AFTERCONSTRUCTIONPICTURES
  is 'ʩ����ͼƬ';
comment on column ADMIN.JTSS_LONGROADMARKING.MATERIALTYPE
  is '��������';
comment on column ADMIN.JTSS_LONGROADMARKING.MATERIALORIGIN
  is '���ʲ���';
comment on column ADMIN.JTSS_LONGROADMARKING.MATERIALFACTORY
  is '���ʳ���';
comment on column ADMIN.JTSS_LONGROADMARKING.MATERIALUNITVOLUME
  is '���ʵ�λ��';
comment on column ADMIN.JTSS_LONGROADMARKING.TOTALESTIMATEDMATERIAL
  is '�����ܹ���';
comment on column ADMIN.JTSS_LONGROADMARKING.DESIGNUNITS
  is '��Ƶ�λ ';
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
  is '·�γ�����';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.ID
  is '//����sys_guid()';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.HISID
  is '���id';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.ROADNAME
  is '·������';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.STARTPOINT
  is '���';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.END
  is '�յ�';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.ROADCODE
  is '·�α��';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.ISMARKING
  is '�Ƿ��б���(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.ROADBOARDS
  is '��·���(��·��Ϊ4����壬�ֱ���1���....4���,�����ֵ�)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.PLATES1WIDTH
  is '���1�Ŀ��(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.PLATES2WIDTH
  is '���2�Ŀ��(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.PLATES3WIDTH
  is '���3�Ŀ��(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.PLATES4WIDTH
  is '���4�Ŀ��(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.LINELENGTH
  is '���߳���';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.TYPE
  is '���(˫ʵ�ߡ���ʵ�ߡ����ߵ�)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.STANDARDWIDTH
  is '���߿�(��λ��CM)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.STANDARDCABLELENGTH
  is '���߳�(=·�γ�-·���������쳤��*2)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.LONGDASHED
  is '���߳�(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.DASHEDEMPTY
  is '���߿�(��λ��M)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.NUM
  is '����(��λ����)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.AREA
  is '���(ƽ����)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.CONSTRUCTIONTIME
  is 'ʩ��ʱ��';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.CONSTRUCTIONUNIT
  is 'ʩ����λ';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.CONSTRUCTIONTYPE
  is 'ʩ������(�½����޲���)';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.BEFORECONSTRUCTIONPICTURES
  is 'ʩ��ǰͼƬ';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.AFTERCONSTRUCTIONPICTURES
  is 'ʩ����ͼƬ';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.MATERIALTYPE
  is '��������';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.MATERIALORIGIN
  is '���ʲ���';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.MATERIALFACTORY
  is '���ʳ���';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.MATERIALUNITVOLUME
  is '���ʵ�λ��';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.TOTALESTIMATEDMATERIAL
  is '�����ܹ���';
comment on column ADMIN.JTSS_LONGROADMARKING_HIS.DESIGNUNITS
  is '��Ƶ�λ ';
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
  is '·�ζ̱���';
comment on column ADMIN.JTSS_SHORTROADMARKING.ID
  is '����sys_guid()';
comment on column ADMIN.JTSS_SHORTROADMARKING.ROADNAME
  is '·������';
comment on column ADMIN.JTSS_SHORTROADMARKING.STARTPOINT
  is '���';
comment on column ADMIN.JTSS_SHORTROADMARKING.END
  is '�յ�';
comment on column ADMIN.JTSS_SHORTROADMARKING.ROADCODE
  is '·�α��';
comment on column ADMIN.JTSS_SHORTROADMARKING.ISMARKING
  is '�Ƿ��б���';
comment on column ADMIN.JTSS_SHORTROADMARKING.ROADBOARDS
  is '��·���';
comment on column ADMIN.JTSS_SHORTROADMARKING.PLATES1WIDTH
  is '���1�Ŀ��';
comment on column ADMIN.JTSS_SHORTROADMARKING.PLATES2WIDTH
  is '���2�Ŀ��';
comment on column ADMIN.JTSS_SHORTROADMARKING.PLATES3WIDTH
  is '���3�Ŀ��';
comment on column ADMIN.JTSS_SHORTROADMARKING.PLATES4WIDTH
  is '���4�Ŀ��';
comment on column ADMIN.JTSS_SHORTROADMARKING.LINELENGTH
  is '���߳���';
comment on column ADMIN.JTSS_SHORTROADMARKING.TYPE
  is '���';
comment on column ADMIN.JTSS_SHORTROADMARKING.STANDARDWIDTH
  is '���߿�';
comment on column ADMIN.JTSS_SHORTROADMARKING.STANDARDCABLELENGTH
  is '���߳�';
comment on column ADMIN.JTSS_SHORTROADMARKING.NUM
  is '����';
comment on column ADMIN.JTSS_SHORTROADMARKING.AREA
  is '���';
comment on column ADMIN.JTSS_SHORTROADMARKING.CONSTRUCTIONTIME
  is 'ʩ��ʱ��';
comment on column ADMIN.JTSS_SHORTROADMARKING.CONSTRUCTIONUNIT
  is 'ʩ����λ';
comment on column ADMIN.JTSS_SHORTROADMARKING.CONSTRUCTIONTYPE
  is 'ʩ������';
comment on column ADMIN.JTSS_SHORTROADMARKING.BEFORECONSTRUCTIONPICTURES
  is 'ʩ��ǰͼƬ';
comment on column ADMIN.JTSS_SHORTROADMARKING.AFTERCONSTRUCTIONPICTURES
  is 'ʩ����ͼƬ';
comment on column ADMIN.JTSS_SHORTROADMARKING.MATERIALTYPE
  is '��������';
comment on column ADMIN.JTSS_SHORTROADMARKING.MATERIALORIGIN
  is '���ʲ���';
comment on column ADMIN.JTSS_SHORTROADMARKING.MATERIALFACTORY
  is '���ʳ���';
comment on column ADMIN.JTSS_SHORTROADMARKING.MATERIALUNITVOLUME
  is '���ʵ�λ��';
comment on column ADMIN.JTSS_SHORTROADMARKING.TOTALESTIMATEDMATERIAL
  is '�����ܹ���';
comment on column ADMIN.JTSS_SHORTROADMARKING.MARKINGDESIGNUNITS
  is '������Ƶ�λ';
comment on column ADMIN.JTSS_SHORTROADMARKING.ISCANALIZATIONINTERSECTION
  is '�Ƿ�������·��(��·�ڣ�����������·��) ';
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
  is '·�ζ̱���';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ID
  is '����sys_guid()';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ROADNAME
  is '·������';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.STARTPOINT
  is '���';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.END
  is '�յ�';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ROADCODE
  is '·�α��';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ISMARKING
  is '�Ƿ��б���';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ROADBOARDS
  is '��·���';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.PLATES1WIDTH
  is '���1�Ŀ��';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.PLATES2WIDTH
  is '���2�Ŀ��';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.PLATES3WIDTH
  is '���3�Ŀ��';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.PLATES4WIDTH
  is '���4�Ŀ��';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.LINELENGTH
  is '���߳���';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.TYPE
  is '���';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.STANDARDWIDTH
  is '���߿�';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.STANDARDCABLELENGTH
  is '���߳�';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.NUM
  is '����';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.AREA
  is '���';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.CONSTRUCTIONTIME
  is 'ʩ��ʱ��';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.CONSTRUCTIONUNIT
  is 'ʩ����λ';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.CONSTRUCTIONTYPE
  is 'ʩ������';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.BEFORECONSTRUCTIONPICTURES
  is 'ʩ��ǰͼƬ';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.AFTERCONSTRUCTIONPICTURES
  is 'ʩ����ͼƬ';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.MATERIALTYPE
  is '��������';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.MATERIALORIGIN
  is '���ʲ���';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.MATERIALFACTORY
  is '���ʳ���';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.MATERIALUNITVOLUME
  is '���ʵ�λ��';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.TOTALESTIMATEDMATERIAL
  is '�����ܹ���';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.MARKINGDESIGNUNITS
  is '������Ƶ�λ';
comment on column ADMIN.JTSS_SHORTROADMARKING_HIS.ISCANALIZATIONINTERSECTION
  is '�Ƿ�������·��(��·�ڣ�����������·��) ';
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
  is '��ͨ��־';
comment on column ADMIN.JTSS_TRAFFICSIGNS.ID
  is '����id sys_guid()';
comment on column ADMIN.JTSS_TRAFFICSIGNS.ROADNAME
  is '·����';
comment on column ADMIN.JTSS_TRAFFICSIGNS.STARTPOINT
  is '���';
comment on column ADMIN.JTSS_TRAFFICSIGNS.END
  is '�յ�';
comment on column ADMIN.JTSS_TRAFFICSIGNS.LINKNUBER
  is '·�α��';
comment on column ADMIN.JTSS_TRAFFICSIGNS.TYPE
  is '���';
comment on column ADMIN.JTSS_TRAFFICSIGNS.FLAGNAME
  is '����(��־����)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.FLAGCODE
  is '��־���(�Ƿ��й���)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.INSTALLATION
  is '��װ��ʽ(����ʽ��˫����ʽ��������ʽ(Ĭ��)��˫����ʽ��������ʽ��������ʽ������ʽ������,�����ֵ�)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.HIGHCOLUMN
  is '������(��λ��M)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.COLUMNDIAMETER
  is '����ֱ��(��λ��CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.CANTILEVERLENGTH
  is '���۳�(��λ��M)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.CANTILEVERDIAMETER
  is '����ֱ��(��λ�� CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.LOCATION
  is 'λ��(·�����������·���ص�ľ���)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.LOCATED
  is 'λ��(�������������Ǹ���������е���,�����ֵ�)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.POSITION
  is '��λ(���ࡢ���ࡢ�����ࡢ�������,�����ֵ�)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.SHAPE
  is '��״(Բ�Ρ����Ρ������Ρ������ε�,�����ֵ�)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.LOGOSIDELENGTH
  is '��־�߳�/ֱ��(��λ��CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.LOGOSIDEWIDTH
  is '��־�߿�(��ֱ���Ŀ���Ϊ�գ���λ��cm)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.CONSTRUCTIONTIME
  is '����ʱ��';
comment on column ADMIN.JTSS_TRAFFICSIGNS.DESIGNUNITS
  is '��Ƶ�λ';
comment on column ADMIN.JTSS_TRAFFICSIGNS.CONSTRUCTIONUNITS
  is '���赥λ';
comment on column ADMIN.JTSS_TRAFFICSIGNS.OVERALLINSTALLATIONDIAGRAM
  is '���尲װͼ(·��)';
comment on column ADMIN.JTSS_TRAFFICSIGNS.BOARDENLARGERMENT
  is '����Ŵ�ͼ';
comment on column ADMIN.JTSS_TRAFFICSIGNS.MATERIAL
  is '����';
comment on column ADMIN.JTSS_TRAFFICSIGNS.MATERIALORIGIN
  is '���ʲ���';
comment on column ADMIN.JTSS_TRAFFICSIGNS.ROADID
  is '·��id(���) ';
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
  is '��ͨ��־��ʷ���ݱ�';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.ID
  is '����id sys_guid()';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.HISID
  is '��ͨ��־id';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.ROADNAME
  is '·����';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.STARTPOINT
  is '���';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.END
  is '�յ�';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.LINKNUBER
  is '·�α��';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.TYPE
  is '���';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.FLAGNAME
  is '����(��־����)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.FLAGCODE
  is '��־���(�Ƿ��й���)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.INSTALLATION
  is '��װ��ʽ(����ʽ��˫����ʽ��������ʽ(Ĭ��)��˫����ʽ��������ʽ��������ʽ������ʽ������,�����ֵ�)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.HIGHCOLUMN
  is '������(��λ��M)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.COLUMNDIAMETER
  is '����ֱ��(��λ��CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.CANTILEVERLENGTH
  is '���۳�(��λ��M)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.CANTILEVERDIAMETER
  is '����ֱ��(��λ�� CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.LOCATION
  is 'λ��(·�����������·���ص�ľ���)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.LOCATED
  is 'λ��(�������������Ǹ���������е���,�����ֵ�)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.POSITION
  is '��λ(���ࡢ���ࡢ�����ࡢ�������,�����ֵ�)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.SHAPE
  is '��״(Բ�Ρ����Ρ������Ρ������ε�,�����ֵ�)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.LOGOSIDELENGTH
  is '��־�߳�/ֱ��(��λ��CM)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.LOGOSIDEWIDTH
  is '��־�߿�(��ֱ���Ŀ���Ϊ�գ���λ��cm)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.CONSTRUCTIONTIME
  is '����ʱ��';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.DESIGNUNITS
  is '��Ƶ�λ';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.CONSTRUCTIONUNITS
  is '���赥λ';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.OVERALLINSTALLATIONDIAGRAM
  is '���尲װͼ(·��)';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.BOARDENLARGERMENT
  is '����Ŵ�ͼ';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.MATERIAL
  is '����';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.MATERIALORIGIN
  is '���ʲ���';
comment on column ADMIN.JTSS_TRAFFICSIGNS_HIS.ROADID
  is '·��id(���) ';
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
  is '��ͨ��־��Ϣά��';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.ID
  is '����';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.TRAFFICID
  is '��־id';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.TRAFFICPID
  is '����־id,���ڵ�ֵΪ0';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.TRAFFICNAME
  is '��־����';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.TRAFFICICON
  is '��ͨ��־ͼ��';
comment on column ADMIN.JTSS_TRAFFICSIGNS_INFO.LEAF
  is '�Ƿ���Ҷ�ӽڵ�,0��Ҷ�ӽڵ㣬1�Ƿ�Ҷ�ӽڵ�';
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
  is '��ͨ��־ͼ��';
comment on column ADMIN.JTSS_TRAFFIC_IMAGE.ID
  is '����';
comment on column ADMIN.JTSS_TRAFFIC_IMAGE.NAME
  is '��ͨ��־ͼ������';
comment on column ADMIN.JTSS_TRAFFIC_IMAGE.IMAGEPATH
  is 'ͼƬ·��';
comment on column ADMIN.JTSS_TRAFFIC_IMAGE.CODE
  is '��־����';
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
  is '��ͨ��־·�α�';
comment on column ADMIN.JTSS_TRAFFIC_ROAD.ID
  is '����id';
comment on column ADMIN.JTSS_TRAFFIC_ROAD.ROADNAME
  is '·������';
comment on column ADMIN.JTSS_TRAFFIC_ROAD.STARTPOINT
  is '���';
comment on column ADMIN.JTSS_TRAFFIC_ROAD.END
  is '�յ�';
comment on column ADMIN.JTSS_TRAFFIC_ROAD.LINKNUBER
  is '·�α�� ';
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
  is '���������';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.ID
  is '����id sys_guid()';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.ROADNAME
  is '��·����';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.INTERSECTIONNUMBERS
  is '·�ڱ��';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.MOUNTINGPOINTS
  is '��װ��';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.MOUNTINGORIENTATION
  is '��װ��λ';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.CONSTRUCTIONTIME
  is '����ʱ��';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.CONSTRUCTIONUNIT
  is '���赥λ';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.DEVICENAME
  is '�豸����';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.DEVICETYPE
  is '�豸�ͺ�';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.FACTORYNUMBER
  is '�������';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.SINCENUMBER
  is '�Ա��';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.DEVICELOCATED
  is '�豸λ��(��ת������ִ�г���,�����ֵ�)';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.NUM
  is '����';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.SCENEPHOTOS
  is '�ֳ�ͼƬ';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.PLACEORIGIN
  is '����';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.MANUFACTURERS
  is '������';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.ROADID
  is '��·id,���id';
comment on column ADMIN.JTSS_VEHICLEDETECTOR.STARTPOINT
  is 'λ��';
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
  is '�����������ʷ���ݱ�';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.ID
  is '����id sys_guid()';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.HISID
  is '���id,����id';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.ROADNAME
  is '��·����';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.INTERSECTIONNUMBERS
  is '·�ڱ��';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.MOUNTINGPOINTS
  is '��װ��';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.MOUNTINGORIENTATION
  is '��װ��λ';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.CONSTRUCTIONTIME
  is '����ʱ��';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.CONSTRUCTIONUNIT
  is '���赥λ';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.DEVICENAME
  is '�豸����';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.DEVICETYPE
  is '�豸�ͺ�';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.FACTORYNUMBER
  is '�������';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.SINCENUMBER
  is '�Ա��';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.DEVICELOCATED
  is '�豸λ��(��ת������ִ�г���,�����ֵ�)';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.NUM
  is '����';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.SCENEPHOTOS
  is '�ֳ�ͼƬ';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.PLACEORIGIN
  is '����';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.MANUFACTURERS
  is '������';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.ROADID
  is '��·id,���id';
comment on column ADMIN.JTSS_VEHICLEDETECTOR_HIS.STARTPOINT
  is 'λ��';
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
  is '�����������·��Ϣ��';
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
  is '�����˺�';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.OPERATOR_TIME
  is '����ʱ��';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.TERMINAL_NAME
  is '�����ն���';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.TERMINAL_CODE
  is '�����ն˱��';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.ACTION
  is '���ƶ���';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.RESULT
  is '���';
comment on column ADMIN.LEDSHOW_CONTROL_LOG.ACTIONCONTENT
  is '��������';
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
  is '��Ŀ��';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.TIME_ADD
  is '���ʱ��';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.OPERATOR
  is '������˺�';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.STARTDAY
  is '��ʼ����';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.ENDDAY
  is '��������';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.STARTTIME
  is '��ʼʱ���';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.ENDTIME
  is '����ʱ���';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.WEEKDAY
  is '����������Ч����(1,2,3,4,5,6,7)';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.ISSHOWBYTIME
  is '��ʱ�䲥�ţ�0�� 1��';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.SHOWBYTIME
  is '��ʱ�䲥�Ŷ�����';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.ISSHOWBYQUEUE
  is '��˳���������ţ�0�� 1��';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.SHOWBYQUEUE
  is '��˳�򲥷Ŷ��ٴ�';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.CODE
  is '��Ŀ���';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.WIDTH
  is '��Ŀ��Ӧ��Ļ�Ŀ��';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.HEIGHT
  is '��Ŀ��Ӧ��Ļ�ĸ߶�';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.TERMINAL_CODE
  is '��Ӧ���ն���Ļ���';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.BORDERPIC
  is '��Ŀ�߿�';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.BORDEREFFECT
  is '�߿��ؼ�';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.BORDERSTEP
  is '�ƶ�����';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.BORDERSPEED
  is '�����ٶ�';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.VERIFYFLAG
  is '�Ƿ����:1����� 2δ���';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.VERIFYOPERATOR
  is '������˺�';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.VERIFY_TIME
  is '���ʱ��';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.VERIFY_RESULT
  is '��˽��:1ͨ�� 2����';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.REQUESTFLAG
  is '�Ƿ�������:1������ 2δ����';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.REQUESTOPERATOR
  is '�������˺�';
comment on column ADMIN.LEDSHOW_PROGRAM_INFO.REQUEST_TIME
  is '����ʱ��';
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
  is '��Ŀ����';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.PROGRAM_CODE
  is '��Ŀ���';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.OPERATOR
  is '��Ŀ�����';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.ADD_TIME
  is '���ʱ��';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.TERMINAL_NAME
  is '��ʾ�ն�';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.TERMINAL_CODE
  is '��ʾ�ն˱��';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.VERIFYOPERATOR
  is '�����';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.PUB_TIME
  is '����ʱ��';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.PUB_RESULT
  is '�������:1�ɹ� 2ʧ��';
comment on column ADMIN.LEDSHOW_PUBLISH_LOG.PUB_ERROR
  is 'ʧ��ԭ��';
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
  is '��������';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.CONTROLCARD_ID
  is '���ƿ�����';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.NAME
  is '�ն�����';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.CODE
  is '�ն˱��';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.SCREEN_TYPE
  is '��Ļ����:��ɫ,˫ɫ';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.CONNMODEL_CODE
  is 'ͨ��ģʽ:U������,GPRS����ͨѶ,����ͨѶ';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.WIDTH
  is '����';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.HEIGHT
  is '����';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.NOTE
  is '��ע';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.TERMINALGROUP_ID
  is '�ն˷���ID';
comment on column ADMIN.LEDSHOW_TERMINAL_INFO.ADDRESS
  is '��װ��ַ';
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
  is '�ı�����';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.FONT
  is '����';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.FONTSIZE
  is '�����С����λ���㣩';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.FONTCOLOR
  is '������ɫ';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.LEFTSIZE
  is '��߾�';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.TOPSIZE
  is '�ϱ߾�';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.CONTENT_WIDTH
  is '�������򳤶�';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.CONTENT_HEIGHT
  is '��������߶�';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.MOVEEFFECT
  is '��ʾ��Ч���';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.MOVESPEED
  is '�ƶ��ٶ�';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.LAYOUT
  is '���ַ�ʽ: 1����롢2���С�3�Ҷ���';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.FONTSPACESIZE
  is '�ּ��';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.ROWSPACESIZE
  is '�м��';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.PROGRAMCODE
  is '������Ŀ���';
comment on column ADMIN.LEDSHOW_TEXTCONTENT_INFO.STOPTIME
  is 'ͣ��ʱ��';
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
  is 'ʱ����������';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.LEFTSIZE
  is '��߾�';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.TOPSIZE
  is '�ϱ߾�';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.CONTENT_WIDTH
  is 'ʱ��������';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.CONTENT_HEIGHT
  is 'ʱ������߶�';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.ISSHOW_YYYYMMDD
  is '�Ƿ���ʾ������';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.ISSHOW_HHMMSS
  is '�Ƿ���ʾʱ����';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.ISSHOW_WEEKDAY
  is '�Ƿ���ʾ���ڼ�';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.FONT
  is '����';
comment on column ADMIN.LEDSHOW_TIMECONTENT_INFO.FONTCONTENT
  is 'ʱ��ǰ��������Ϣ';
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
  is '����';
comment on column ADMIN.OCCUPY_ADVISETEMPLET_INFO.CONTENT
  is '����';
comment on column ADMIN.OCCUPY_ADVISETEMPLET_INFO.NOTE
  is '��ע';
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
  is '���뵥λ';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPLY_TIME
  is '����ʱ��';
comment on column ADMIN.OCCUPY_APPLY_INFO.BUILDER_ID
  is 'ʩ����λ';
comment on column ADMIN.OCCUPY_APPLY_INFO.TYPE
  is 'ʩ�����';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPLY_STARTTIME
  is '������ʼʱ��';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPLY_ENDTIME
  is '�����ֹʱ��';
comment on column ADMIN.OCCUPY_APPLY_INFO.LENGTHS
  is '��ռ����';
comment on column ADMIN.OCCUPY_APPLY_INFO.WIDTHS
  is '��ռ���';
comment on column ADMIN.OCCUPY_APPLY_INFO.ROAD_STYLE
  is 'ռ�õ�·����';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPLY_CODE
  is '���';
comment on column ADMIN.OCCUPY_APPLY_INFO.CONTACTS
  is '���뵥λ��ϵ��';
comment on column ADMIN.OCCUPY_APPLY_INFO.COMM_ADDRESS
  is '���뵥λͨѶ��ַ';
comment on column ADMIN.OCCUPY_APPLY_INFO.PHONE
  is '���뵥λ��ϵ�绰';
comment on column ADMIN.OCCUPY_APPLY_INFO.PROPORTION
  is '��ռ���';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPROVE_STARTTIME
  is '��׼��ʼʱ��';
comment on column ADMIN.OCCUPY_APPLY_INFO.APPROVE_ENDTIME
  is '��׼��ֹʱ��';
comment on column ADMIN.OCCUPY_APPLY_INFO.GREENDEPT_SUGGESTION
  is '�̻��������';
comment on column ADMIN.OCCUPY_APPLY_INFO.GREENDEPT_LEADER
  is '�̻������쵼';
comment on column ADMIN.OCCUPY_APPLY_INFO.TRAFFICPOLICE_SUGGESTION
  is '�����������';
comment on column ADMIN.OCCUPY_APPLY_INFO.TRAFFICPOLICE_LEADER
  is '���������쵼';
comment on column ADMIN.OCCUPY_APPLY_INFO.CONSTRUCT_MODE
  is 'ʩ����ʽ';
comment on column ADMIN.OCCUPY_APPLY_INFO.IS_CROSSROADS
  is '�Ƿ���ʮ��·��';
comment on column ADMIN.OCCUPY_APPLY_INFO.IS_NEARBYHAS
  is '�����Ƿ�����ռ��1000�����ڣ�';
comment on column ADMIN.OCCUPY_APPLY_INFO.IS_BLOCKING
  is '�Ƿ������׶µض�';
comment on column ADMIN.OCCUPY_APPLY_INFO.IS_MAINROAD
  is '�Ƿ������ɵ�';
comment on column ADMIN.OCCUPY_APPLY_INFO.ISPROFESSION
  is '�Ƿ�ר����ռ';
comment on column ADMIN.OCCUPY_APPLY_INFO.ISREMIND
  is '�Ƿ�������';
comment on column ADMIN.OCCUPY_APPLY_INFO.ATTACH_PATH
  is '��������·��';
comment on column ADMIN.OCCUPY_APPLY_INFO.PIC_PATH
  is 'ͼƬ�ϴ�·��';
comment on column ADMIN.OCCUPY_APPLY_INFO.LONGITUDE
  is '��ռ�㾭��';
comment on column ADMIN.OCCUPY_APPLY_INFO.LATITUDE
  is '��ռ��γ��';
comment on column ADMIN.OCCUPY_APPLY_INFO.ICON_TYPE
  is '��ռͼ�����ͣ�0��һ����ռ��1���ص���ռ����';
comment on column ADMIN.OCCUPY_APPLY_INFO.STATUS
  is '��ռ״̬��0��δ������1������ʩ����2���쵽�ڣ�3�����ڣ�4�����깤��';
comment on column ADMIN.OCCUPY_APPLY_INFO.PROJECT_NAME
  is '��ռ��Ŀ����';
comment on column ADMIN.OCCUPY_APPLY_INFO.PROJECT_CODE
  is '��ռ��Ŀ���';
comment on column ADMIN.OCCUPY_APPLY_INFO.ROAD_ID
  is '��ռ��������·';
comment on column ADMIN.OCCUPY_APPLY_INFO.ROAD_TYPE
  is '��ռ��·����';
comment on column ADMIN.OCCUPY_APPLY_INFO.ROAD_ORG
  is '��·��������';
comment on column ADMIN.OCCUPY_APPLY_INFO.IS_DELAY
  is '�Ƿ���������';
comment on column ADMIN.OCCUPY_APPLY_INFO.MUNICIPAL_SUGGESTION
  is '�����������';
comment on column ADMIN.OCCUPY_APPLY_INFO.MUNICIPAL_LEADER
  is '���������쵼';
comment on column ADMIN.OCCUPY_APPLY_INFO.PLANNING_SUGGESTION
  is '�滮�������';
comment on column ADMIN.OCCUPY_APPLY_INFO.PLANNING_LEADER
  is '�滮�����쵼';
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
  is 'ʩ����λ';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.SCORE
  is '���˷���';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.NOTE
  is '��ע��Ϣ';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.ISCANCEL
  is '�Ƿ���';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.CANCEL_REASON
  is '����ԭ��';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.OPERATOR_ID
  is '������Ա';
comment on column ADMIN.OCCUPY_BLACK_MANAGE.CANCEL_TIME
  is '����ʱ��';
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
  is 'ʩ����λ����';
comment on column ADMIN.OCCUPY_BUILDER_INFO.PROPERTY
  is 'ʩ����λ����';
comment on column ADMIN.OCCUPY_BUILDER_INFO.LEADING_OFFICIAL
  is '������';
comment on column ADMIN.OCCUPY_BUILDER_INFO.COMM_ADDRESS
  is 'ͨѶ��ַ';
comment on column ADMIN.OCCUPY_BUILDER_INFO.PHONE
  is '��ϵ�绰';
comment on column ADMIN.OCCUPY_BUILDER_INFO.INSERT_TIME
  is '����ʱ��';
comment on column ADMIN.OCCUPY_BUILDER_INFO.NOTE
  is '��ע��Ϣ';
comment on column ADMIN.OCCUPY_BUILDER_INFO.OPERATOR_ID
  is '������Ա';
comment on column ADMIN.OCCUPY_BUILDER_INFO.LEVEL_CODE
  is '���ü���';
comment on column ADMIN.OCCUPY_BUILDER_INFO.DISABLED
  is '�Ƿ���ʾ';
comment on column ADMIN.OCCUPY_BUILDER_INFO.ISBLACK
  is '�Ƿ������';
comment on column ADMIN.OCCUPY_BUILDER_INFO.LEADING_OFFICIAL_MOBILE
  is '�����˵绰';
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
  is '��Ŀid';
comment on column ADMIN.OCCUPY_DELAY_INFO.PROJECT_CODE
  is '��Ŀ���';
comment on column ADMIN.OCCUPY_DELAY_INFO.APPLY_TIME
  is '����ʱ��';
comment on column ADMIN.OCCUPY_DELAY_INFO.OPERATOR
  is '������';
comment on column ADMIN.OCCUPY_DELAY_INFO.REASON
  is '��������';
comment on column ADMIN.OCCUPY_DELAY_INFO.APPLY_STARTTIME
  is '������ʼʱ��';
comment on column ADMIN.OCCUPY_DELAY_INFO.APPLY_ENDTIME
  is '�����ֹʱ��';
comment on column ADMIN.OCCUPY_DELAY_INFO.GREENDEPT_SUGGESTION
  is '�̻��������';
comment on column ADMIN.OCCUPY_DELAY_INFO.GREENDEPT_LEADER
  is '�̻������쵼';
comment on column ADMIN.OCCUPY_DELAY_INFO.TRAFFICPOLICE_SUGGESTION
  is '�����������';
comment on column ADMIN.OCCUPY_DELAY_INFO.TRAFFICPOLICE_LEADER
  is '���������쵼';
comment on column ADMIN.OCCUPY_DELAY_INFO.APPROVE_STARTTIME
  is '��׼��ʼʱ��';
comment on column ADMIN.OCCUPY_DELAY_INFO.APPROVE_ENDTIME
  is '��׼��ֹʱ��';
comment on column ADMIN.OCCUPY_DELAY_INFO.ATTACH_PATH
  is '��������·��';
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
  is '��ռ����';
comment on column ADMIN.OCCUPY_ICON_INFO.STATUS
  is '��ռ״̬';
comment on column ADMIN.OCCUPY_ICON_INFO.OPERATOR
  is '������Ա';
comment on column ADMIN.OCCUPY_ICON_INFO.PATH
  is 'ͼ��·��';
comment on column ADMIN.OCCUPY_ICON_INFO.UPLOAD_TIME
  is '�ϴ�ʱ��';
comment on column ADMIN.OCCUPY_ICON_INFO.CODE
  is 'ͼ�����';
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
  is '��Ŀ���';
comment on column ADMIN.OCCUPY_INSPECT_INFO.BUILDER_ID
  is 'ʩ����λ';
comment on column ADMIN.OCCUPY_INSPECT_INFO.ROAD_ID
  is '��ռ��·';
comment on column ADMIN.OCCUPY_INSPECT_INFO.OCCUPY_TYPE
  is '��ռ����';
comment on column ADMIN.OCCUPY_INSPECT_INFO.INSPECTOR
  is '�����';
comment on column ADMIN.OCCUPY_INSPECT_INFO.INSPECT_TIME
  is '���ʱ��';
comment on column ADMIN.OCCUPY_INSPECT_INFO.ILLUSTRATION
  is '���˵��';
comment on column ADMIN.OCCUPY_INSPECT_INFO.PIC_TYPE
  is 'ͼƬ����';
comment on column ADMIN.OCCUPY_INSPECT_INFO.PIC_PATH
  is 'ͼƬ·��';
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
  is '������ֹ����';
comment on column ADMIN.OCCUPY_LEVEL_INFO.NOTE
  is '��ע��Ϣ';
comment on column ADMIN.OCCUPY_LEVEL_INFO.SCORE_START
  is '������ʼ����';
comment on column ADMIN.OCCUPY_LEVEL_INFO.CODE
  is '�ȼ�����';
comment on column ADMIN.OCCUPY_LEVEL_INFO.LEVELS
  is '���õȼ�';
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
  is '��id';
comment on column ADMIN.OCCUPY_PICTURE_INFO.TYPE
  is 'ͼƬ���';
comment on column ADMIN.OCCUPY_PICTURE_INFO.PATH
  is '����·��';
comment on column ADMIN.OCCUPY_PICTURE_INFO.OPERATOR
  is '�ϴ���';
comment on column ADMIN.OCCUPY_PICTURE_INFO.UPLOAD_TIME
  is '�ϴ�ʱ��';
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
  is '�۷ֹ������';
comment on column ADMIN.OCCUPY_RULER_INFO.NOTE
  is '��ע';
comment on column ADMIN.OCCUPY_RULER_INFO.CODE
  is '�������';
comment on column ADMIN.OCCUPY_RULER_INFO.CONTENT
  is '�۷ֹ�����ϸ';
comment on column ADMIN.OCCUPY_RULER_INFO.SCORE
  is '��ֵ';
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
  is 'ʩ����λ';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.SCORE
  is '��ǰ����';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.UPDATETIME
  is '����ʱ��';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.OPERATOR_ID
  is '������Ա';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.RULER_CODE
  is 'Υ���������';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.DEDUCT_SCORE
  is '�۳�����';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.LEVEL_CODE
  is '���õȼ�';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.ISLATEST
  is '�Ƿ����¼�¼';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.DISABLED
  is '�Ƿ���ʾ';
comment on column ADMIN.OCCUPY_SCORE_MANAGE.ISBLACK
  is '�Ƿ������';
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
  is '���';
comment on column ADMIN.OCCUPY_SPECIAL_INFO.NAME
  is '����';
comment on column ADMIN.OCCUPY_SPECIAL_INFO.NOTE
  is '��ע';
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
  is '�豸���';
comment on column ADMIN.PARKINGLOT_EQUIP.PARKID
  is 'ͣ�������';
comment on column ADMIN.PARKINGLOT_EQUIP.EQUIPNAME
  is '�豸���ƣ�����B�����led��';
comment on column ADMIN.PARKINGLOT_EQUIP.EQUIPTYPE
  is '�豸���ͣ�0Ϊ������,   1ΪLED����';
comment on column ADMIN.PARKINGLOT_EQUIP.EQUIPTIME
  is '����ʱ�䣨ÿ�����ݰ�����ʱ�򣬸���ʱ�䣩';
comment on column ADMIN.PARKINGLOT_EQUIP.RESERVEDTIMET1
  is 'Ԥ���ֶ�';
comment on column ADMIN.PARKINGLOT_EQUIP.RESERVEDTIMET2
  is 'Ԥ���ֶ�';
comment on column ADMIN.PARKINGLOT_EQUIP.LNG
  is '����';
comment on column ADMIN.PARKINGLOT_EQUIP.LAT
  is 'Ԥ���ֶ�';
comment on column ADMIN.PARKINGLOT_EQUIP.RESERVEDINTN1
  is 'LED���ļ���  1Ϊ1����2Ϊ2���� 3Ϊ3��';
comment on column ADMIN.PARKINGLOT_EQUIP.RESERVEDINTN2
  is 'Ԥ���ֶ�';
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
  is 'ͣ�������';
comment on column ADMIN.PARKINGLOT_INFO.PARKNAME
  is 'ͣ��������';
comment on column ADMIN.PARKINGLOT_INFO.PARKTYPE
  is '�������ͣ�0Ϊ���ϣ�1Ϊ���£� 2�������ϵ��¡�Ĭ��Ϊ0��';
comment on column ADMIN.PARKINGLOT_INFO.PARKSTATUS
  is 'ͣ����״̬��0Ϊ������1Ϊͣ�ã�Ĭ��Ϊ0��';
comment on column ADMIN.PARKINGLOT_INFO.FREEPARK
  is '�ճ�λ';
comment on column ADMIN.PARKINGLOT_INFO.TOTALPARK
  is '�ܳ�λ';
comment on column ADMIN.PARKINGLOT_INFO.LNG
  is '����';
comment on column ADMIN.PARKINGLOT_INFO.LAT
  is 'γ��';
comment on column ADMIN.PARKINGLOT_INFO.AREA
  is '��������';
comment on column ADMIN.PARKINGLOT_INFO.UPDATETIME
  is '����ʱ��';
comment on column ADMIN.PARKINGLOT_INFO.REMARKS
  is '��ע�������������˵����';
comment on column ADMIN.PARKINGLOT_INFO.PARKCHARGESTIME
  is '�շѿ�ʼʱ��';
comment on column ADMIN.PARKINGLOT_INFO.PARKCHARGEETIME
  is '�շѽ���ʱ��';
comment on column ADMIN.PARKINGLOT_INFO.SUPERVISEPHONE
  is '�ල�绰';
comment on column ADMIN.PARKINGLOT_INFO.REPORTPHONE
  is 'Ͷ�ߵ绰';
comment on column ADMIN.PARKINGLOT_INFO.PRICE
  is '�շѼ۸�Ԫ/Сʱ';
comment on column ADMIN.PARKINGLOT_INFO.ISPOLICEMANAGE
  is '�Ƿ�ֱ��ͣ������1��0��';
comment on column ADMIN.PARKINGLOT_INFO.ROADNAME
  is '�ٽ���·��';

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
  is '�豸ID';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.PARKID
  is 'ͣ�������';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.EQUIPNAME
  is '�豸���ƣ�����B�����led��';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.EQUIPTYPE
  is '�豸���ͣ�0Ϊ������,   1ΪLED����';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.EQUIPTIME
  is '����ʱ�䣨ÿ�����ݰ�����ʱ�򣬸���ʱ�䣩';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.LNG
  is '����';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.LAT
  is 'γ��';
comment on column ADMIN.PARKINGLOT_LEADDEVICE_INFO.EQUIPLEVECODE
  is 'LED���ļ���  1Ϊ1����2Ϊ2���� 3Ϊ3��';

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
  is 'ͣ�������';
comment on column ADMIN.PARKINGLOT_PARK_DATA.PARKNAME
  is 'ͣ��������';
comment on column ADMIN.PARKINGLOT_PARK_DATA.PARKTYPE
  is '�������ͣ�0Ϊ���ϣ�1Ϊ���£� 2�������ϵ��¡�Ĭ��Ϊ0��';
comment on column ADMIN.PARKINGLOT_PARK_DATA.PARKSTATUS
  is 'ͣ����״̬��0Ϊ������1Ϊͣ�ã�Ĭ��Ϊ0��';
comment on column ADMIN.PARKINGLOT_PARK_DATA.FREEPARK
  is '�ճ�λ';
comment on column ADMIN.PARKINGLOT_PARK_DATA.TOTALPARK
  is '�ܳ�λ';
comment on column ADMIN.PARKINGLOT_PARK_DATA.LNG
  is '����';
comment on column ADMIN.PARKINGLOT_PARK_DATA.LAT
  is 'γ��';
comment on column ADMIN.PARKINGLOT_PARK_DATA.AREA
  is '��������';
comment on column ADMIN.PARKINGLOT_PARK_DATA.UPDATETIME
  is '����ʱ��';
comment on column ADMIN.PARKINGLOT_PARK_DATA.REMARKS
  is '��ע�������������˵����';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDTIMET1
  is '�շѿ�ʼʱ��ParkChargeStime';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDTIMET2
  is '�շѽ���ʱ��ParkChargeEtime';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDSTRINGS1
  is '�ල�绰supervisephone';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDSTRINGS2
  is 'Ͷ�ߵ绰reportphone';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDINTN1
  is '�շѼ۸�Ԫ/СʱParkCharge';
comment on column ADMIN.PARKINGLOT_PARK_DATA.RESERVEDINTN2
  is '�Ƿ�ֱ��ͣ������1��0��';
comment on column ADMIN.PARKINGLOT_PARK_DATA.ROADNAME
  is '�ٽ���·��';
comment on column ADMIN.PARKINGLOT_PARK_DATA.ORGCODE
  is 'Ԥ���ֶ�';
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
  is 'ͨ��֤���';
comment on column ADMIN.PAS_PASSPORT_INFO.TYPE
  is 'ͨ��֤����';
comment on column ADMIN.PAS_PASSPORT_INFO.STARTTIME
  is '��Ч��ʼ����';
comment on column ADMIN.PAS_PASSPORT_INFO.ENDTIME
  is '��Ч��ֹ����';
comment on column ADMIN.PAS_PASSPORT_INFO.APPLYPER
  is '������';
comment on column ADMIN.PAS_PASSPORT_INFO.PLATE_NUMBER
  is '���ƺ���';
comment on column ADMIN.PAS_PASSPORT_INFO.PLATE_TYPE
  is '��������';
comment on column ADMIN.PAS_PASSPORT_INFO.CARTYPE
  is '�������';
comment on column ADMIN.PAS_PASSPORT_INFO.STATE
  is 'ͨ��֤״̬';
comment on column ADMIN.PAS_PASSPORT_INFO.GIVETIME
  is '��֤����';
comment on column ADMIN.PAS_PASSPORT_INFO.IMAGE_PATH
  is '���ͼƬ��';
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
  is 'ͨ��֤���';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.TYPE
  is 'ͨ��֤����';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.STARTTIME
  is '��Ч��ʼ����';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.ENDTIME
  is '��Ч��ֹ����';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.APPLYPER
  is '������';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.PLATE_NUMBER
  is '���ƺ���';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.PLATE_TYPE
  is '��������';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.CARTYPE
  is '�������';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.STATE
  is 'ͨ��֤״̬';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.GIVETIME
  is '��֤����';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.IMAGE_PATH
  is '���ͼƬ';
comment on column ADMIN.PAS_PASSPORT_INFO_HIS.ROADS
  is '��ͨ�е�·';
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
  is '��·����';
comment on column ADMIN.PAS_ROAD.PID
  is '���ڵ�id';
comment on column ADMIN.PAS_ROAD.COUNTS
  is '���Ƶ�ͨ��֤����';
comment on column ADMIN.PAS_ROAD.REMARK
  is '��ע';
comment on column ADMIN.PAS_ROAD.ORG_ID
  is '����id';
comment on column ADMIN.PAS_ROAD.ISIMPORT
  is '�Ƿ��ص�·��';
comment on column ADMIN.PAS_ROAD.HASCOUNT
  is '�Ѱ�����';
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
  is '��������';
comment on column ADMIN.SIGNAL_AREAS_INFO.CROSSROADNUM
  is '����·������';
comment on column ADMIN.SIGNAL_AREAS_INFO.NOTE
  is '��ע';
comment on column ADMIN.SIGNAL_AREAS_INFO.CROSSROADIDS
  is '������������·��ID�ö��÷ָ�';
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
  is '�źſ��ƻ�ID';
comment on column ADMIN.SIGNAL_CONTROL_CROSSROAD.CROSSROAD_ID
  is '��Ͻ·�ڵ�ID';
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
  is '�źŻ�����';
comment on column ADMIN.SIGNAL_CONTROL_INFO.FUNCTION_CLASS_CODE
  is '�źŻ������ܷ������';
comment on column ADMIN.SIGNAL_CONTROL_INFO.ENV_CLASS_CODE
  is '�źŻ�����װ�����������';
comment on column ADMIN.SIGNAL_CONTROL_INFO.IP
  is '������IP��ַ';
comment on column ADMIN.SIGNAL_CONTROL_INFO.PORT
  is '�˿ں�';
comment on column ADMIN.SIGNAL_CONTROL_INFO.USERNAME
  is '�û���';
comment on column ADMIN.SIGNAL_CONTROL_INFO.PASSWORD
  is '����';
comment on column ADMIN.SIGNAL_CONTROL_INFO.COMPANY_ID
  is '���칫˾��ID';
comment on column ADMIN.SIGNAL_CONTROL_INFO.INSPECTTIME
  is '��������';
comment on column ADMIN.SIGNAL_CONTROL_INFO.INSPECTNUMBER
  is '������';
comment on column ADMIN.SIGNAL_CONTROL_INFO.STANDARDCODE
  is 'ִ�еı�׼���';
comment on column ADMIN.SIGNAL_CONTROL_INFO.STANDARDNAME
  is 'ִ�еı�׼����';
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
  is '·������';
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.TYPE
  is '·������';
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.LONGITUDE
  is '����';
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.LATITUDE
  is 'γ��';
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.COMMUNICATION
  is 'ͨ�Ŵ��ں�';
comment on column ADMIN.SIGNAL_CROSSROADS_INFO.CONNECTIONROADS
  is '�����·��Ϣ';
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
  is '������������';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.FLOWALL
  is '������';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.FLOWBIG
  is '���ͳ�����';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.FLOWSMALL
  is 'С�ͳ�����';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.OCCUPANCY
  is '·��ռ����';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.SPEED
  is 'ƽ���ٶ�';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.CARLENGTH
  is '����';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.INSERTTIME
  is '����ʱ��';
comment on column ADMIN.SIGNAL_FLOWDEVICE_DATE.ANALYSTTIME
  is '����ʱ��';
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
  is '��ʶ����';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.TYPE_CODE
  is '���������(���������ֵ����)';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.DATECYCLE
  is '���ݲɼ�����(��)';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.PULSECYCLE
  is '�������ݲɼ�����';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.COM
  is 'ͨ����';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.CODE
  is '����������豸���';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.DIRECTION_CODE
  is '�����������';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.XIANGWEI
  is '��Ӧ��������λ';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.CONTROL_ID
  is '�������ƻ�';
comment on column ADMIN.SIGNAL_FLOWDEVICE_INFO.CONTROL_NAME
  is '�������ƻ�����';
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
  is '�̲�������ID';
comment on column ADMIN.SIGNAL_GREENSCHEME_CROSSTIME.CROSSROADS_ID
  is '·��ID';
comment on column ADMIN.SIGNAL_GREENSCHEME_CROSSTIME.TIMEVALUE
  is '��λʱ���';
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
  is '��������';
comment on column ADMIN.SIGNAL_GREENSCHEME_INFO.START_TIME
  is '��ʼʱ��';
comment on column ADMIN.SIGNAL_GREENSCHEME_INFO.PREPARE_TIME
  is 'Ԥ��ʱ��';
comment on column ADMIN.SIGNAL_GREENSCHEME_INFO.SENDFLAG
  is '�Ƿ����·�';
comment on column ADMIN.SIGNAL_GREENSCHEME_INFO.NOTE
  is '��ע˵��';
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
  is '�źŵ�����';
comment on column ADMIN.SIGNAL_LAMP_INFO.MODELCODE
  is '�ɹ��ܷ��ࡢ͸����ߴ硢��Դ���͡�6λ������λ�Զ��������';
comment on column ADMIN.SIGNAL_LAMP_INFO.LIGHTAREA_CODE
  is '���ⵥԪ͸����ߴ�';
comment on column ADMIN.SIGNAL_LAMP_INFO.SHELLMATERIAL_CODE
  is '��ǲ��ϱ��';
comment on column ADMIN.SIGNAL_LAMP_INFO.LIGHTSOURCE_CODE
  is '��Դ���ͱ��';
comment on column ADMIN.SIGNAL_LAMP_INFO.FUNCTION_CODE
  is '�źŵƹ��ܷ�����';
comment on column ADMIN.SIGNAL_LAMP_INFO.CROSSROAD_ID
  is '����·����ϢID';
comment on column ADMIN.SIGNAL_LAMP_INFO.CONTROL_ID
  is '����Ӧ�Ŀ��ƻ�ID';
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
  is '·��ID';
comment on column ADMIN.SIGNAL_PRECINCTSCHEME_INFO.PRECINCTSCHEME_CODE
  is '���Ʒ�ʽ����';
comment on column ADMIN.SIGNAL_PRECINCTSCHEME_INFO.START_TIME
  is '��ʼʱ��';
comment on column ADMIN.SIGNAL_PRECINCTSCHEME_INFO.END_TIME
  is '����ʱ��';
comment on column ADMIN.SIGNAL_PRECINCTSCHEME_INFO.SENDFLAG
  is '�Ƿ����·���Nδ�·�  Y���·�';
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
  is '����ʾ��ʽ���࣬����ʱ���ɷ�ΪS ������ʾ����ʱ���� M ģ����ʾ����ʱ��';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.WORKCLASS_CODE
  is '��������ʽ���࣬����ʱ���ɷ�ΪT ͨѶʽ����ʱ���� X ����Ӧʽ����ʱ��';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.POWERSOURCECLASS_CODE
  is '�����緽ʽ���࣬����ʱ���ɷ�Ϊ0 ֱ�ӹ���ʽ����ʱ���� 1 �źŵ�ȡ��ʽ����ʱ��';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.SHOWEXTENT_CODE
  is '�����������ʾ���ͣ����� 1λ��10�� 2λ��20��2λ�� 25, 3λ:30 ������ʾ����ʱ��';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.MODELCODE
  is '����ʱ��ʾ���ͺţ�DX+����ʾ��ʽ��������ʽ�����緽ʽ����ҵ�Զ���������';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.CROSSROAD_ID
  is '����·����ϢID';
comment on column ADMIN.SIGNAL_TIMEWORK_INFO.CONTROL_ID
  is '����Ӧ�Ŀ��ƻ�ID';
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
  is '0 �ֶ�¼�� 1 EXCEL���� ';
comment on column ADMIN.SMS_CONTACT_INFO.POSITION
  is 'ְλ';
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
  is '�������';
comment on column ADMIN.SMS_ORDERTYPE_INFO.SYMBOL
  is '���ķ����';
comment on column ADMIN.SMS_ORDERTYPE_INFO.NOTE
  is '��ע';
comment on column ADMIN.SMS_ORDERTYPE_INFO.SMSFLAG
  is '���ͱ�ʶ����';
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
  is '���ͽ�� 0ʧ�� 1�ɹ�';
comment on column ADMIN.SMS_SENDLOG_INFO.OPERATOR_ID
  is '�������˺�';
comment on column ADMIN.SMS_SENDLOG_INFO.CONTACT_ID
  is '��ϵ��ID';
comment on column ADMIN.SMS_SENDLOG_INFO.OPERATOR_NAME
  is '����������';
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
  is '�������˺�';
comment on column ADMIN.SMS_SENDTASK_INFO.SENDSTATUS
  is '���ͽ�� 0ʧ�� 1�ɹ�';
comment on column ADMIN.SMS_SENDTASK_INFO.OPERATOR_NAME
  is '����������';
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
  is '����';
comment on column ADMIN.SMS_VIOTEMPLET_INFO.CONTENT
  is '����';
comment on column ADMIN.SMS_VIOTEMPLET_INFO.NOTE
  is '��ע';
comment on column ADMIN.SMS_VIOTEMPLET_INFO.CODE
  is '����';
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
  is '��·����';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.DEVICENAME
  is '�豸����';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.DEVICECODE
  is '�豸���';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.DEVICEFUNCTION
  is '�豸����';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.COMPANYNAME
  is '���賧��';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.CONTACTPERSON
  is '������ϵ��';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.CONTACTPHONE
  is '��ϵ�绰';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.SMSCONTENT
  is '��������';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.PLANTIME
  is '�����ɷ�ʱ��';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.OPERATEPERSONID
  is '�ɷ����˺�';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.OPERATEPERSONNAME
  is '�ɷ���';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.ISRETRIEVAL
  is '1�ѻָ� 0δ�ָ�';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.OPERATEID
  is '�ɷ���������������˺�';
comment on column ADMIN.SYS_DEVICEMAINTAIN_INFO.OPERATENAME
  is '�ɷ������������������';
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
  is 'ͼƬ�ϳɷ�ʽID';
comment on column ADMIN.SYS_DEVICE_INFO.TOPORGCODE
  is '�豸���ڵػ�������';
comment on column ADMIN.SYS_DEVICE_INFO.DEVICETYPECODE
  is '�豸���ͱ���';
comment on column ADMIN.SYS_DEVICE_INFO.TRADEMARK
  is '�豸Ʒ��';
comment on column ADMIN.SYS_DEVICE_INFO.PATTERN
  is '�豸�ͺ�';
comment on column ADMIN.SYS_DEVICE_INFO.EFFICACIOUS_STIME
  is '�豸��Ч�ڿ�ʼ����';
comment on column ADMIN.SYS_DEVICE_INFO.EFFICACIOUS_ETIME
  is '�豸��Ч�ڽ�������';
comment on column ADMIN.SYS_DEVICE_INFO.INSPECTORG
  is '���鵥λ';
comment on column ADMIN.SYS_DEVICE_INFO.INSPECTTIME
  is '��������';
comment on column ADMIN.SYS_DEVICE_INFO.INSPECTNUMBER
  is '����֤����';
comment on column ADMIN.SYS_DEVICE_INFO.MAPX
  is '�豸����';
comment on column ADMIN.SYS_DEVICE_INFO.MAPY
  is '�豸γ��';
comment on column ADMIN.SYS_DEVICE_INFO.VIDEO_CODE
  is '������Ƶ�����Ϣ';
comment on column ADMIN.SYS_DEVICE_INFO.ISSEND
  is '�Ƿ����ɷ�������Ĭ��δ�ɷ���';
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
  is '�豸����';
comment on column ADMIN.SYS_DEVICE_LOGINFO.DEVICE_CODE
  is '�豸����';
comment on column ADMIN.SYS_DEVICE_LOGINFO.STATUS
  is '�豸״̬';
comment on column ADMIN.SYS_DEVICE_LOGINFO.ROAD_ID
  is '��·����';
comment on column ADMIN.SYS_DEVICE_LOGINFO.DEVICETYPE_CODE
  is '�豸����';
comment on column ADMIN.SYS_DEVICE_LOGINFO.IP
  is 'ip��ַ';
comment on column ADMIN.SYS_DEVICE_LOGINFO.COMPANY_ID
  is '�豸����';
comment on column ADMIN.SYS_DEVICE_LOGINFO.ORG_ID
  is '��������';
comment on column ADMIN.SYS_DEVICE_LOGINFO.OPERATOR_ID
  is '������Ա';
comment on column ADMIN.SYS_DEVICE_LOGINFO.OPERATE_TIME
  is '����ʱ��';
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
  is '�ϳ���Ϣ����';
comment on column ADMIN.SYS_DEVICE_PICMIX.LAYOUTTYPE
  is '�������ͣ�����1 ������ 2 ����� 0';
comment on column ADMIN.SYS_DEVICE_PICMIX.FEATUREPICNUM
  is '��дͼƬλ��Ϊ�ڼ���';
comment on column ADMIN.SYS_DEVICE_PICMIX.DEVICE_CODE
  is '�����豸���';
comment on column ADMIN.SYS_DEVICE_PICMIX.ADDTIME
  is '���ʱ��';
comment on column ADMIN.SYS_DEVICE_PICMIX.PICDIRECTION
  is '����дͼƬ����˳�� ˳ʱ��0  ��ʱ��1';
comment on column ADMIN.SYS_DEVICE_PICMIX.PICCOUNT
  is 'ͼƬ�ܹ�����,����дͼƬ';
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
  is '�豸����';
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.FAULT_NUM
  is '������';
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.NORMAL_NUM
  is '������';
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.NOCONNECT_NUM
  is '����δ����';
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.TARGET_TIME
  is 'ͳ������YYYY-MM-DD';
comment on column ADMIN.SYS_DEVICE_STATUS_DAY.ADD_TIME
  is '����ʱ��';
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
  is '�豸����';
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.FAULT_NUM
  is '������';
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.NORMAL_NUM
  is '������';
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.NOCONNECT_NUM
  is '����δ����';
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.TARGET_TIME
  is 'ͳ������YYYY-MM-DD HH24';
comment on column ADMIN.SYS_DEVICE_STATUS_HOUR.ADD_TIME
  is '����ʱ��';
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
  is '�豸���';
comment on column ADMIN.SYS_DEVICE_STATUS_LOG.DEVICETYPE
  is '�豸����';
comment on column ADMIN.SYS_DEVICE_STATUS_LOG.STATUS
  is '����״̬';
comment on column ADMIN.SYS_DEVICE_STATUS_LOG.BRUSH_TIME
  is 'ˢ��ʱ��';
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
  is '����';
comment on column ADMIN.SYS_DIC.NAME
  is '����';
comment on column ADMIN.SYS_DIC.TYPE
  is '�ֵ�����';
comment on column ADMIN.SYS_DIC.REMARK
  is '��ע';
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
  is '���ű���';
comment on column ADMIN.SYS_ORG.PRINCIPAL
  is '������';
comment on column ADMIN.SYS_ORG.MOBILE
  is '��ϵ�绰';
comment on column ADMIN.SYS_ORG.PRINCIPALNAME
  is '����������';
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
  is '��������Ϣ��';
comment on column ADMIN.SYS_POLICE_CAR.PLATE_NUM
  is '���ƺ���';
comment on column ADMIN.SYS_POLICE_CAR.PLATE_TYPE
  is '��������';
comment on column ADMIN.SYS_POLICE_CAR.CAR_BRAND
  is '��������Ʒ��';
comment on column ADMIN.SYS_POLICE_CAR.ORG_ID
  is '��������';
comment on column ADMIN.SYS_POLICE_CAR.IS_ALARM
  is '�Ƿ��б�����';
comment on column ADMIN.SYS_POLICE_CAR.CREATE_TIME
  is '���ʱ��';
comment on column ADMIN.SYS_POLICE_CAR.BUY_TIME
  is '����ʱ��';
comment on column ADMIN.SYS_POLICE_CAR.CAR_USE_TYPE
  is '����ʹ������';
comment on column ADMIN.SYS_POLICE_CAR.TYPES
  is '��������';
comment on column ADMIN.SYS_POLICE_CAR.HAS_GPS
  is '�Ƿ���GPS��λ�豸';
comment on column ADMIN.SYS_POLICE_CAR.NOTE
  is '��ע';
comment on column ADMIN.SYS_POLICE_CAR.CALLNUMBER
  is '��̨����';
comment on column ADMIN.SYS_POLICE_CAR.FREQUENCY
  is '��̨��Ƶ����Ϣ';
comment on column ADMIN.SYS_POLICE_CAR.SKY_SHOOT_CODE
  is '���ĺ�';
comment on column ADMIN.SYS_POLICE_CAR.STATUS
  is '�Ƿ���Ч1��ʾ��Ч��0��ʾ��Ч��ɾ��';
comment on column ADMIN.SYS_POLICE_CAR.GPS_TELNUMBER
  is 'GPSͨѶ�ֻ���';
comment on column ADMIN.SYS_POLICE_CAR.GPS_SERIAL_ID
  is 'GPS�豸���к�';
comment on column ADMIN.SYS_POLICE_CAR.IS_TRACK
  is '�Ƿ����ڶ�λ����';
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
  is '��Ա���';
comment on column ADMIN.SYS_POLICE_USER.TYPES
  is '����(��Ҫ�����񾯡�Э����)';
comment on column ADMIN.SYS_POLICE_USER.NAME
  is '����';
comment on column ADMIN.SYS_POLICE_USER.ORG_ID
  is '���ű��';
comment on column ADMIN.SYS_POLICE_USER.POST_CODE
  is 'ְλ����';
comment on column ADMIN.SYS_POLICE_USER.TEL_PHONE
  is '�ֻ���';
comment on column ADMIN.SYS_POLICE_USER.GPS_TELNUMBER
  is 'GPSͨѶ�ֻ���';
comment on column ADMIN.SYS_POLICE_USER.CREATE_TIME
  is '���ʱ��';
comment on column ADMIN.SYS_POLICE_USER.NOTE
  is '��ע';
comment on column ADMIN.SYS_POLICE_USER.CALLNUMBER
  is '��̨����';
comment on column ADMIN.SYS_POLICE_USER.FREQUENCY
  is '��̨Ƶ����Ϣ';
comment on column ADMIN.SYS_POLICE_USER.STATUS
  is '״̬';
comment on column ADMIN.SYS_POLICE_USER.HAS_GPS
  is '�Ƿ���GPS��λ�豸';
comment on column ADMIN.SYS_POLICE_USER.GPS_SERIAL_ID
  is 'GPS�豸���к�';
comment on column ADMIN.SYS_POLICE_USER.IS_TRACK
  is '�Ƿ����ڶ�λ���� 1��ʾ���ڸ���  0 ��ʾδ���ø���';
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
  is 'ɾ������';
comment on column ADMIN.SYS_RETRIEVE_INFO.DELTIME
  is 'ɾ��ʱ��';
comment on column ADMIN.SYS_RETRIEVE_INFO.OPERATOR
  is '������';
comment on column ADMIN.SYS_RETRIEVE_INFO.POJOCLASS
  is '���ݶ�������';
comment on column ADMIN.SYS_RETRIEVE_INFO.OPERATORIP
  is '����IP';
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
  is '�û�Ƥ����';
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
  is '��ý�������IP';
comment on column ADMIN.SYS_VIDEO_INFO.PORT
  is '�˿ں�';
comment on column ADMIN.SYS_VIDEO_INFO.USERNAME
  is '�û���';
comment on column ADMIN.SYS_VIDEO_INFO.PASSWORD
  is '����';
comment on column ADMIN.SYS_VIDEO_INFO.MAPX
  is '�豸����';
comment on column ADMIN.SYS_VIDEO_INFO.MAPY
  is '�豸γ��';
comment on column ADMIN.SYS_VIDEO_INFO.CODE
  is '�豸���:�豸�������+�豸����05+4λ����';
comment on column ADMIN.SYS_VIDEO_INFO.DEVICE_IP
  is '����豸IP��ַ';
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
