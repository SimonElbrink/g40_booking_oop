create database vaccine_booking;
use vaccine_booking;
create table address
(
    id       varchar(255) not null primary key,
    city     varchar(255) null,
    street   varchar(255) null,
    zip_code varchar(20)  null
);

create table app_role
(
    id        varchar(255) not null
        primary key,
    user_role int          null
);

create table app_user
(
    id       varchar(255) not null
        primary key,
    password varchar(255) null,
    username varchar(100) null,
    constraint UK_3k4cplvh82srueuttfkwnylq0
        unique (username)
);

create table contact_info
(
    id    varchar(255) not null
        primary key,
    email varchar(255) null,
    phone varchar(255) null,
    constraint UK_gsp1iln2jy1n3h7iui85cc9o7
        unique (email)
);

create table patient
(
    id                 varchar(255) not null
        primary key,
    birth_date         date         null,
    first_name         varchar(255) null,
    last_name          varchar(255) null,
    pnr                varchar(20)  null,
    fk_contact_info_id varchar(255) null,
    fk_app_user_id     varchar(255) null,
    constraint UK_cge813ujauh2xohwqhwyn85se
        unique (pnr),
    constraint FK2pejcjsxpiibfo9hmyo9ccv9c
        foreign key (fk_contact_info_id) references contact_info (id),
    constraint FK8aa7rr730wus782e9ejhq34x7
        foreign key (fk_app_user_id) references app_user (id)
);

create table premises
(
    id            varchar(255) not null
        primary key,
    name          varchar(255) null,
    fk_address_id varchar(255) null,
    constraint FKbn7o94n80ya1b9p0k3riyrdbn
        foreign key (fk_address_id) references address (id)
);

create table booking
(
    id               varchar(255)   not null
        primary key,
    administrator_id varchar(255)   null,
    date_time        datetime(6)    null,
    price            decimal(19, 2) null,
    vacant           bit            not null,
    vaccine_type     varchar(255)   null,
    fk_patient_id    varchar(255)   null,
    fk_premises_id   varchar(255)   null,
    constraint FK2fq3er1p8a8bqygnnhvr9709y
        foreign key (fk_patient_id) references patient (id),
    constraint FKay7xxlvqithkkc93wkx32e9tr
        foreign key (fk_premises_id) references premises (id)
);

create table role_app_user
(
    fk_app_role_id varchar(255) not null,
    fk_app_user_id varchar(255) not null,
    primary key (fk_app_role_id, fk_app_user_id),
    constraint FKfh0yl57vt99ay4mp3w8hmwcd4
        foreign key (fk_app_role_id) references app_role (id),
    constraint FKsm1hge1axc39v0v1xc2imtqku
        foreign key (fk_app_user_id) references app_user (id)
);

