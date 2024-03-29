-- country

CREATE SEQUENCE country_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE country
(
    id bigint DEFAULT nextval('country_seq') NOT NULL,
    taxationRule varchar(255) NOT NULL,
    taxAmount numeric(7,2),
    taxRate numeric(7,2),

    PRIMARY KEY (id)
);

-- trader

CREATE SEQUENCE trader_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE trader
(
    id bigint DEFAULT nextval('trader_seq') NOT NULL,
    country_id bigint NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (country_id) REFERENCES country(id)
);