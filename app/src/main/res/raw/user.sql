CREATE TABLE users (
    id          integer         NOT NULL DEFAULT 1,
    name        varchar(32)     NOT NULL DEFAULT 'name default',
    lastname    varchar(32)     NOT NULL DEFAULT 'lastname default',
    email       varchar(32)     NOT NULL DEFAULT 'email default',
    cellphone   integer         NOT NULL DEFAULT 0000000,
    password    varchar(8)      NOT NULL DEFAULT "123"
);

INSERT INTO user (id, name, lastname, email, cellphone, password) VALUES (1, "Username", "UserLastName", "user@teste.com", 12345678, "123");