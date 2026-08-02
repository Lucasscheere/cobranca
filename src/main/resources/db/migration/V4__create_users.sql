CREATE TABLE app_user (
                          id            BIGSERIAL     PRIMARY KEY,
                          name          VARCHAR(255)  NOT NULL,
                          email         VARCHAR(255)  NOT NULL UNIQUE,
                          role          VARCHAR(20)   NOT NULL DEFAULT 'COLLECTOR',
                          active        BOOLEAN       NOT NULL DEFAULT TRUE,
                          created_at    TIMESTAMP     NOT NULL DEFAULT NOW()
);