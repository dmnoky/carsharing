create table cars.user_table
(
    id          BIGSERIAL not null,
    last_name   varchar(255) not null,
    first_name  varchar(255) not null,
    middle_name varchar(255),
    email       varchar(255),
    phone       varchar(255),
    version     int8 not null,
    primary key (id)
);
create table cars.transport
(
    id          BIGSERIAL not null,
    brand       varchar(255) not null,
    version     int8 not null,
    primary key (id)
);
create table cars.order
(
    id              BIGSERIAL not null,
    client_id       BIGINT not null,
    transport_id    BIGINT not null,
    status          varchar(255) not null,
    date_open       DATE not null default CURRENT_DATE,
    date_close      DATE,
    version         int8 not null,
    primary key (id)
);
alter table cars.order add foreign key (transport_id) references cars.transport (id);
alter table cars.order add foreign key (client_id) references cars.user_table (id);