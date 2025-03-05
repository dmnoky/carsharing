-- start transport_type
create table carsharing.transport_type
(
    id                  UUID not null,
    version             int8 not null,
    created_by          UUID not null,
    created_date        timestamptz not null default CURRENT_DATE,
    last_modified_by    UUID not null,
    last_modified_date  timestamptz not null default CURRENT_DATE,
    type                varchar(255) not null,
    primary key (id)
);
-- end transport_type
-- start transport_brand
create table carsharing.transport_brand
(
    id                  UUID not null,
    version             int8 not null,
    created_by          UUID not null,
    created_date        timestamptz not null default CURRENT_DATE,
    last_modified_by    UUID not null,
    last_modified_date  timestamptz not null default CURRENT_DATE,
    brand               varchar(255) not null,
    type_id             UUID not null,
    primary key (id)
);
alter table carsharing.transport_brand add foreign key (type_id) references carsharing.transport_type (id);
-- end transport_brand
-- start transport_model
create table carsharing.transport_model
(
    id                  UUID not null,
    version             int8 not null,
    created_by          UUID not null,
    created_date        timestamptz not null default CURRENT_DATE,
    last_modified_by    UUID not null,
    last_modified_date  timestamptz not null default CURRENT_DATE,
    model               varchar(255) not null,
    brand_id            UUID not null,
    primary key (id)
);
alter table carsharing.transport_model add foreign key (brand_id) references carsharing.transport_brand (id);
-- end transport_model
-- start transport
create table carsharing.transport
(
    id                  UUID not null,
    version             int8 not null,
    created_by          UUID not null,
    created_date        timestamptz not null default CURRENT_DATE,
    last_modified_by    UUID not null,
    last_modified_date  timestamptz not null default CURRENT_DATE,
    model_id            UUID not null,
    primary key (id)
);
alter table carsharing.transport
    add foreign key (model_id) references carsharing.transport_model (id);
-- end transport