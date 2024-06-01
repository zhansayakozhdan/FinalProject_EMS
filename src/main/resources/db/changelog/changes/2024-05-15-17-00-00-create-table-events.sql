--liquibase formatted sql
-- changeset k.zhansaya:1

CREATE TABLE IF NOT EXISTS EVENTS(
                                     ID BIGSERIAL PRIMARY KEY NOT NULL,
                                     EVENT_NAME VARCHAR(255) NOT NULL,
                                     VENUE VARCHAR(255) NOT NULL,
                                     DESCRIPTION TEXT,
                                     EVENT_DATE DATE,
                                     EVENT_TIME TIME,
                                     CREATED_AT TIMESTAMP,
                                     UPDATED_AT TIMESTAMP,
                                     QUANTITY_OF_TICKETS BIGINT,
                                     TICKET_COST NUMERIC,
                                     EVENT_TYPE_ID BIGINT,
                                     CONSTRAINT FK_EVENT_TYPE FOREIGN KEY (EVENT_TYPE_ID) REFERENCES EVENT_TYPES(ID)
);

-- changeset k.zhansaya:2
INSERT INTO EVENTS(EVENT_NAME, VENUE, DESCRIPTION, EVENT_DATE, EVENT_TIME, QUANTITY_OF_TICKETS, TICKET_COST, EVENT_TYPE_ID)
VALUES('OYU Fest в Алматы', 'г. Алматы, Первомайская промзона, 11Б, Первомайские пруды', 'OYU Fest - это казахстанские артисты, развлечения в традиционном стиле, поддержка локального бизнеса и огромный вклад в культуру города.',
       '9.05.2024', '15:00', 1000, 5000, 1),
      ('ALMATY MARATHON 2024', 'Площадь Республики, ул. Сатпаева', '«Almaty Marathon» – самое крупное беговое соревнование в Центральной Азии, городской праздник спорта с основной дистанцией 42.2 км.',
       '29.09.2024', '6:00', 1000, 12000, 2);