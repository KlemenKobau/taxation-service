CREATE sequence traders_seq start with 1 increment by 50;

CREATE TABLE traders
(
    id bigint DEFAULT nextval('traders_seq') not null
);