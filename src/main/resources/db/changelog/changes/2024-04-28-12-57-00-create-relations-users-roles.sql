--liquibase formatted sql
-- changeset k.zhansaya:1
CREATE TABLE USERS_ROLES
(
    USER_ID BIGSERIAL,
    ROLES_ID BIGSERIAL,
        CONSTRAINT FK_USER FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
        CONSTRAINT FK_ROLE FOREIGN KEY (ROLES_ID) REFERENCES ROLES(ID)
);

-- changeset k.zhansaya:2
INSERT INTO USERS_ROLES(USER_ID, ROLES_ID)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 3);