/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/1/16 20:41:50                           */
/*==============================================================*/


/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
insert into admin (status_id, username, password, gmt_create, gmt_modified, deleted)
values (1, 'lostred', '123456', now(), now(), 0);

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
insert into article (status_id, user_id, catalogue_id, title, cover, content, hot, gmt_create, gmt_modified, deleted)
values (1, 1, 1, '测试文章1', 'url路径', '这是一个测试内容文字', 0, now(), now(), 0);

/*==============================================================*/
/* Table: catalogue                                             */
/*==============================================================*/
insert into catalogue (status_id, name)
values (1, 'Java');
insert into catalogue (status_id, name)
values (1, '音乐');
insert into catalogue (status_id, name)
values (1, '诗词');

/*==============================================================*/
/* Table: status                                                */
/*==============================================================*/
insert into status (name, type, value)
values ('启用', '使用状态', 1);
insert into status (name, type, value)
values ('禁用', '使用状态', 0);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
insert into user (status_id, username, password, name, sex, email, gmt_create, gmt_modified, deleted)
values (1, 'lostred', '123456', '邓路炜', 1, 'lostred@outlook.com', now(), now(), 0);
