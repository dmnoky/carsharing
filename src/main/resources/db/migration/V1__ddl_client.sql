create table carsharing.client
(
    id          UUID not null,
    version     int8 not null,
    created_by   UUID not null,
    created_date timestamptz not null default CURRENT_DATE,
    last_modified_by   UUID not null,
    last_modified_date timestamptz not null default CURRENT_DATE,
    last_name   varchar(255) not null,
    first_name  varchar(255) not null,
    middle_name varchar(255),
    primary key (id)
);
-- start phone
create table carsharing.phone
(
    id          UUID not null,
    version     int8 not null,
    created_by   UUID not null,
    created_date timestamptz not null default CURRENT_DATE,
    last_modified_by   UUID not null,
    last_modified_date timestamptz not null default CURRENT_DATE,
    parent_id   UUID not null, --client,
    phone varchar(18) not null,
    is_primary bool default false,
    primary key (id)
);
alter table carsharing.phone
    add constraint phone_uk unique (parent_id, phone),
    add constraint phone_uk_1 unique (parent_id, is_primary),
    add foreign key (parent_id) references carsharing.client (id);
--create index phone_is_primary_i on carsharing.phone (is_primary);
-- end phone
-- start email
create table carsharing.email
(
    id          UUID not null,
    version     int8 not null,
    created_by   UUID not null,
    created_date timestamptz not null default CURRENT_DATE,
    last_modified_by   UUID not null,
    last_modified_date timestamptz not null default CURRENT_DATE,
    parent_id   UUID not null, --client,
    email varchar(255) not null,
    is_primary bool default false,
    primary key (id)
);
alter table carsharing.email
    add constraint email_uk unique (parent_id, email),
    add constraint email_uk_1 unique (parent_id, is_primary),
    add foreign key (parent_id) references carsharing.client (id);
-- end email