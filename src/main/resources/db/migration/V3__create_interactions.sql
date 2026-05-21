CREATE TABLE interaction (
                             id              BIGSERIAL       PRIMARY KEY,
                             client_id       BIGINT          NOT NULL REFERENCES client(id),
                             notes           TEXT            NOT NULL DEFAULT '',
                             next_contact    TIMESTAMP       NOT NULL DEFAULT NOW(),
                             created_at      TIMESTAMP       NOT NULL DEFAULT NOW()
);