--liquibase formatted sql
--changeset ravil.mashakaev:create-id-sequence
create sequence id_sequence start 1;

--changeset ravil.mashakaev:create-user-table
create table user_info
(
    id            bigint
        CONSTRAINT pk_user PRIMARY KEY,
    created       timestamp    NOT NULL,
    modified      timestamp    NOT NULL,
    version       int          NOT NULL,
    first_name    varchar(255) NOT NULL,
    last_name     varchar(255) NOT NULL,
    email         varchar(255) NOT NULL
        CONSTRAINT user_email_uniq UNIQUE,
    password_hash varchar(255) NOT NULL
);

create index idx_user_email on user_info (email);

--changeset ravil.mashakaev:create-todo-list-table
create table todo_item
(
    id       bigint
        CONSTRAINT pk_todo_list PRIMARY KEY,
    created  timestamp    NOT NULL,
    modified timestamp    NOT NULL,
    version  int          NOT NULL,
    body     text,
    status   varchar(255) NOT NULL
)