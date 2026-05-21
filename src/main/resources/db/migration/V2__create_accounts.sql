CREATE TABLE account (
                         id              BIGSERIAL       PRIMARY KEY,
                         client_id       BIGINT          NOT NULL REFERENCES client(id),
                         payment_method  VARCHAR(20)     NOT NULL DEFAULT 'DINHEIRO',
                         issue_date      DATE            NOT NULL DEFAULT CURRENT_DATE,
                         payment_date    DATE,
                         due_date        DATE,
                         value_payment   NUMERIC(15,2)   NOT NULL DEFAULT 0,
                         status_payment  VARCHAR(20)     NOT NULL DEFAULT 'VINCENDO',
                         notes           TEXT            NOT NULL DEFAULT ''
);