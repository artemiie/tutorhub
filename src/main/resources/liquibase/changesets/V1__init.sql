CREATE TABLE IF NOT EXISTS USERS
(
    ID BIGSERIAL PRIMARY KEY,
    FULLNAME VARCHAR(255) NOT NULL,
    USERNAME VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL,
    ENABLED BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS USERS_ROLE
(
    USER_ID BIGINT NOT NULL,
    ROLE VARCHAR(255) NOT NULL,

    PRIMARY KEY (USER_ID, ROLE),

    CONSTRAINT FK_USERS_ROLE_USERS FOREIGN KEY (USER_ID)
    REFERENCES USERS (ID) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS COURSE
(
    ID BIGSERIAL PRIMARY KEY,
    USER_ID BIGINT NOT NULL,
    NAME VARCHAR(255) NOT NULL,

    CONSTRAINT FK_COURSE_USERS FOREIGN KEY (USER_ID)
    REFERENCES USERS (ID) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS CONTENT (
    ID BIGSERIAL PRIMARY KEY,
    FILE_ID UUID NOT NULL,
    TITLE VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    ORIGINAL_NAME VARCHAR(255) NOT NULL,
    TYPE VARCHAR(50) NOT NULL CHECK (TYPE IN ('VIDEO', 'IMAGE', 'TEXT'))
);

CREATE TABLE IF NOT EXISTS MODULE
(
    ID BIGSERIAL PRIMARY KEY,
    COURSE_ID BIGINT  NOT NULL,
    NAME VARCHAR(255) NOT NULL,

    CONSTRAINT FK_MODULE_COURSE FOREIGN KEY (COURSE_ID)
    REFERENCES COURSE (ID) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS SUBMODULE
(
    ID BIGSERIAL PRIMARY KEY,
    MODULE_ID BIGINT  NOT NULL,
    CONTENT_ID BIGINT,
    NAME VARCHAR(255) NOT NULL,

    CONSTRAINT FK_SUBMODULE_COURSE FOREIGN KEY (MODULE_ID)
    REFERENCES MODULE (ID) ON DELETE CASCADE ON UPDATE NO ACTION,

    CONSTRAINT FK_SUBMODULE_CONTENT FOREIGN KEY (CONTENT_ID)
    REFERENCES CONTENT (ID) ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS COURSE_INFO
(
    ID BIGSERIAL NOT NULL UNIQUE,
    COURSE_ID BIGINT NOT NULL,
    USER_ID BIGINT NOT NULL,

    PRIMARY KEY (COURSE_ID, USER_ID),

    CONSTRAINT FK_COURSE_INFO_USERS FOREIGN KEY (USER_ID)
    REFERENCES USERS (ID) ON DELETE CASCADE ON UPDATE NO ACTION,

    CONSTRAINT FK_COURSE_INFO_COURSE FOREIGN KEY (COURSE_ID)
    REFERENCES COURSE (ID) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS PROGRESS
(
    COURSE_INFO_ID BIGINT NOT NULL,
    MODULE_ID BIGINT NOT NULL,
    SUBMODULE_ID BIGINT NOT NULL,

    PRIMARY KEY (MODULE_ID, SUBMODULE_ID, COURSE_INFO_ID),

    CONSTRAINT FK_PROGRESS_MODULE FOREIGN KEY (MODULE_ID)
    REFERENCES MODULE (ID) ON DELETE CASCADE ON UPDATE NO ACTION,

    CONSTRAINT FK_PROGRESS_SUBMODULE FOREIGN KEY (SUBMODULE_ID)
    REFERENCES SUBMODULE (ID) ON DELETE CASCADE ON UPDATE NO ACTION,

    CONSTRAINT FK_PROGRESS_COURSE_INFO FOREIGN KEY (COURSE_INFO_ID)
    REFERENCES COURSE_INFO (ID) ON DELETE CASCADE ON UPDATE NO ACTION
);