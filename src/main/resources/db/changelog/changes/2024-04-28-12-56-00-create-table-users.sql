--liquibase formatted sql

-- changeset k.zhansaya:1
CREATE TABLE IF NOT EXISTS USERS
(
    ID BIGSERIAL PRIMARY KEY NOT NULL,
    EMAIL VARCHAR(255) UNIQUE NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    FULL_NAME VARCHAR(255),
    PHONE VARCHAR(100),
    CREATED_AT TIMESTAMP,
    UPDATED_AT TIMESTAMP
);

--changeset k.zhansaya:2
INSERT INTO USERS(EMAIL, FULL_NAME, PASSWORD, PHONE)
VALUES ('qwerty@gmail.com', 'Zhansaya Kozhdan', '$2a$12$1e8GpAUsuxHqix.9aL7yK.Oub7VsORmFcOyEMezONT4D6ryAQ5AyG', '+7 777 777 77 77'),
       ('zhansaya@gmail.com', 'Zhansaya Kozhdan', '$2a$12$1e8GpAUsuxHqix.9aL7yK.Oub7VsORmFcOyEMezONT4D6ryAQ5AyG', '+7 707 707 70 70'),
       ('madina@gmail.com', 'Madina Kozhdan', '$2a$12$1e8GpAUsuxHqix.9aL7yK.Oub7VsORmFcOyEMezONT4D6ryAQ5AyG', '+7 707 777 07 07')