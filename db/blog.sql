/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/1/16 20:41:50                           */
/*==============================================================*/


drop table if exists admin;

drop table if exists admin_log;

drop table if exists article;

drop table if exists catalogue;

drop table if exists status;

drop table if exists user;

drop table if exists user_log;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   id                   int not null auto_increment comment 'id',
   status_id            int comment '状态id',
   username             varchar(30) comment '用户名',
   password             varchar(30) comment '密码',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   deleted              int comment '删除',
   primary key (id)
);

alter table admin comment '管理员';

/*==============================================================*/
/* Table: admin_log                                             */
/*==============================================================*/
create table admin_log
(
   id                   int not null auto_increment comment 'id',
   admin_id             int comment '管理员id',
   event                varchar(30) comment '事件',
   remark               varchar(50) comment '备注',
   gmt_create           datetime comment '创建时间',
   primary key (id)
);

alter table admin_log comment '管理员操作日志';

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
   id                   int not null auto_increment comment 'id',
   status_id            int comment '状态id',
   user_id              int comment '用户id',
   catalogue_id         int comment '文章类型id',
   title                varchar(100) comment '标题',
   cover                varchar(500) comment '封面',
   content              text comment '内容',
   hot                  int comment '热度',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   deleted              int comment '删除',
   primary key (id)
);

alter table article comment '文章';

/*==============================================================*/
/* Table: catalogue                                             */
/*==============================================================*/
create table catalogue
(
   id                   int not null auto_increment comment 'id',
   status_id            int comment '状态id',
   name                 varchar(10) comment '名称',
   primary key (id)
);

alter table catalogue comment '文章类型';

/*==============================================================*/
/* Table: status                                                */
/*==============================================================*/
create table status
(
   id                   int not null auto_increment comment 'id',
   name                 varchar(30) comment '状态名',
   type                 varchar(30) comment '状态类型',
   value                int comment '状态值',
   primary key (id)
);

alter table status comment '状态';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null auto_increment comment 'id',
   status_id            int comment '状态id',
   username             varchar(30) comment '用户名',
   password             varchar(30) comment '密码',
   name                 varchar(10) comment '姓名',
   sex                  int comment '性别',
   email                varchar(50) comment '邮箱',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   deleted              int comment '删除',
   primary key (id)
);

alter table user comment '用户';

/*==============================================================*/
/* Table: user_log                                              */
/*==============================================================*/
create table user_log
(
   id                   int not null auto_increment comment 'id',
   user_id              int comment '用户id',
   event                varchar(30) comment '事件',
   remark               varchar(50) comment '备注',
   gmt_create           datetime comment '创建时间',
   primary key (id)
);

alter table user_log comment '用户操作日志';

