--liquibase formatted sql
--changeset ravil.mashakaev:create-id-sequence
create sequence id_sequence start 1;