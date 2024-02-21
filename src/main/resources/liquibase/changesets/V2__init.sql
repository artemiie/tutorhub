CREATE TABLE IF NOT EXISTS COURSES
(
    ID BIGSERIAL PRIMARY KEY,
    USER_ID BIGINT NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    CONSTRAINT FK_COURSES_USERS FOREIGN KEY (USER_ID) REFERENCES USERS (ID) ON DELETE CASCADE ON UPDATE NO ACTION
);