-- start order
create table carsharing.order
(
    id                  UUID not null,
    version             int8 not null,
    created_by          UUID not null,
    created_date        timestamptz not null default CURRENT_DATE,
    last_modified_by    UUID not null,
    last_modified_date  timestamptz not null default CURRENT_DATE,
    client_id           UUID not null,
    transport_id        UUID not null,
    status              varchar(255) not null,
    date_open           timestamptz,
    date_close          timestamptz,
    primary key (id)
);
alter table carsharing.order
    add foreign key (transport_id) references carsharing.transport (id),
    add foreign key (client_id) references carsharing.client (id);
-- end order