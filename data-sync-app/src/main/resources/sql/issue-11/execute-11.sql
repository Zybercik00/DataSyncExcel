
create sequence attribute_seq
    start with 1
    increment by 50;

create sequence qualifier_value_seq
    start with 1
    increment by 50;

create table attribute (
    id bigint not null,
    target_property varchar(128) not null,
    path varchar(1024) not null,
    primary key (id),
    constraint UC_ATTRIBUTE unique (path)
);

create table qualifier_attribute (
    id bigint not null,
    parent_property varchar(128) not null,
    qualifier_property varchar(128) not null,
    primary key (id)
);

create table qualifier_value (
    attribute_id bigint not null,
    id bigint not null,
    qcode varchar(32) not null,
    qvalue varchar(1024) not null,
    primary key (id),
    constraint UC_QUALIFIER_VALUE unique (attribute_id, qcode)
);

create table reference_attribute (
    id bigint not null,
    nested_property varchar(32) not null,
    primary key (id)
);

create table simple_attribute (
    id bigint not null,
    primary key (id)
);

alter table qualifier_attribute
    add constraint FK_QAT_ATR
    foreign key (id)
    references attribute;

alter table qualifier_value
    add constraint FK_QVL_QAT
    foreign key (attribute_id)
    references qualifier_attribute;

alter table reference_attribute
    add constraint FK_RAT_ATR
    foreign key (id)
    references attribute;

alter table simple_attribute
    add constraint FK_SAT_ATR
    foreign key (id)
    references attribute;
