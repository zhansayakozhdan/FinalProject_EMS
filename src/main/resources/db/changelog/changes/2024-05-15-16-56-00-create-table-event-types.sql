--liquibase formatted sql
-- changeset k.zhansaya:1
CREATE TABLE IF NOT EXISTS EVENT_TYPES
(
    ID   BIGSERIAL PRIMARY KEY NOT NULL,
    NAME VARCHAR(100)          NOT NULL,
    CREATED_AT TIMESTAMP,
    UPDATED_AT TIMESTAMP
);

-- changeset k.zhansaya:2
INSERT INTO EVENT_TYPES(NAME)
VALUES ('Концерт'),
       ('Спорт'),
       ('Театр'),
       ('Экскурсия');
