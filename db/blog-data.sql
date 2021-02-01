/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/1/16 20:41:50                           */
/*==============================================================*/


/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
INSERT INTO `admin` (`id`, `status_id`, `username`, `password`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (1, 1, 'lostred', '123456', now(), now(), 0);

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
INSERT INTO `article` (`id`, `status_id`, `user_id`, `catalogue_id`, `title`, `precis`, `cover`, `content`, `hot`,
                       `gmt_create`,
                       `gmt_modified`, `deleted`)
VALUES (1, 1, 1, 1, '测试文章1', '这是一个测试内容文字', 'example.jpg', '这是一个测试内容文字', 0, now(), now(), 0);

/*==============================================================*/
/* Table: catalogue                                             */
/*==============================================================*/
INSERT INTO `catalogue` (`id`, `status_id`, `name`)
VALUES (1, 1, 'Java');
INSERT INTO `catalogue` (`id`, `status_id`, `name`)
VALUES (2, 1, '音乐');
INSERT INTO `catalogue` (`id`, `status_id`, `name`)
VALUES (3, 1, '诗词');
/*==============================================================*/
/* Table: status                                                */
/*==============================================================*/
INSERT INTO `status` (`id`, `name`, `type`, `value`)
VALUES (1, '启用', '使用状态', 1);
INSERT INTO `status` (`id`, `name`, `type`, `value`)
VALUES (2, '禁用', '使用状态', 0);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
INSERT INTO `user` (`id`, `status_id`, `username`, `password`, `avatar`, `name`, `sex`, `email`, `gmt_create`,
                    `gmt_modified`, `deleted`)
VALUES (1, 1, 'lostred', '123456', 'avatar.jpg', '邓路炜', 1, 'lostred@outlook.com', now(), now(), 0);
