CREATE TABLE IF NOT EXISTS users
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(255)                            NOT NULL,
    email VARCHAR(512)                            NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
    );

create table if not exists categories
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(64)                             NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT UQ_CATEGORY_NAME UNIQUE (name)
    );

CREATE TABLE IF NOT EXISTS events
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    annotation         VARCHAR(2000)                           NOT NULL,
    category_id        BIGINT                                  NOT NULL,
    confirmed_requests BIGINT  DEFAULT 0,
    description        VARCHAR(7000)                           NOT NULL,
    event_date         TIMESTAMP                               NOT NULL,
    created_on         TIMESTAMP,
    latitude           FLOAT                                   NOT NULL,
    longitude          FLOAT                                   NOT NULL,
    paid               BOOLEAN DEFAULT FALSE,
    participant_limit  INTEGER DEFAULT 0,
    request_moderation BOOLEAN DEFAULT TRUE,
    title              VARCHAR(120)                            NOT NULL,
    initiator_id       BIGINT                                  NOT NULL,
    published_on       TIMESTAMP,
    event_state        VARCHAR(20),
    CONSTRAINT pk_events PRIMARY KEY (id),
    CONSTRAINT EVENT_USER_FK
    FOREIGN KEY (initiator_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT EVENT_CATEGORY_FK
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE NO ACTION
    );

create table if not exists locations
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    latitude  FLOAT                                   NOT NULL,
    longitude FLOAT                                   NOT NULL,
    CONSTRAINT pk_location PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS requests
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created      TIMESTAMP,
    event_id     BIGINT                                  NOT NULL,
    requester_id BIGINT                                  NOT NULL,
    status       VARCHAR(20),
    CONSTRAINT pk_requests PRIMARY KEY (id),
    CONSTRAINT REQ_EVENT_FK
    FOREIGN KEY (event_id) REFERENCES events (id),
    CONSTRAINT REQ_USER_FK
    FOREIGN KEY (requester_id) REFERENCES users (id),
    CONSTRAINT requester_event_unique_pk UNIQUE (requester_id, event_id)
    );

CREATE TABLE IF NOT EXISTS compilations
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    event_id BIGINT,
    pinned   BOOLEAN                                 NOT NULL,
    title    VARCHAR(200),
    CONSTRAINT pk_compilations PRIMARY KEY (id),
    CONSTRAINT COMP_EVENT_FK
    FOREIGN KEY (event_id) REFERENCES events (id)
    );

CREATE TABLE IF NOT EXISTS compilations_events
(
    compilation_id BIGINT,
    event_id       BIGINT,
    PRIMARY KEY (compilation_id, event_id),
    CONSTRAINT COMP_EVENTS_FK
    FOREIGN KEY (compilation_id) REFERENCES compilations (id) ON DELETE CASCADE,
    CONSTRAINT EVENT_COMPS_FK
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS comments
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    text      VARCHAR(2000)                           NOT NULL,
    event_id  BIGINT                                  NOT NULL,
    author_id BIGINT                                  NOT NULL,
    created   TIMESTAMP                               NOT NULL,
    published TIMESTAMP,
    comment_state VARCHAR(20),
    CONSTRAINT COMMENT_EVENTS_FK
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE,
    CONSTRAINT COMMENT_USERS_FK
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE
    );