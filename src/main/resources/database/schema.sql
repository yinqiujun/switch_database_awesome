-- sql server
CREATE TABLE switch_database_demo.dbo.TB_USER
(
    id   int                                    NOT NULL,
    name varchar(100) COLLATE Chinese_PRC_CI_AS NULL,
    age  int                                    NULL,
    CONSTRAINT USER_PK PRIMARY KEY (id)
);

INSERT INTO switch_database_demo.dbo.TB_USER
    (id, name, age)
VALUES (1, N'张三-sqlserver', 18);
INSERT INTO switch_database_demo.dbo.TB_USER
    (id, name, age)
VALUES (2, N'李四-sqlserver', 20);


-- mysql
CREATE TABLE `tb_user`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL COMMENT '姓名',
    `age`  int          DEFAULT NULL COMMENT '年龄',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO switch_database_demo.tb_user
    (id, name, age)
VALUES (1, '张三-mysql', 10);
INSERT INTO switch_database_demo.tb_user
    (id, name, age)
VALUES (2, '李四-mysql', 12);