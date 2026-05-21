CREATE TABLE client (
                        id            BIGSERIAL    PRIMARY KEY,
                        cnpj          VARCHAR(18)  NOT NULL UNIQUE,
                        razao_social  VARCHAR(255) NOT NULL DEFAULT '',
                        nome_fantasia VARCHAR(255),
                        active        BOOLEAN      NOT NULL DEFAULT TRUE,
                        created_at    DATE         NOT NULL DEFAULT CURRENT_DATE
);