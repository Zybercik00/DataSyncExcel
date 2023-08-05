
create sequence mapping_seq
    start with 1
    increment by 50;

create table mapping (
    id bigint not null,
    target bigint not null,
    source varchar(32) not null,
    primary key (id),
    constraint UC_mapping unique (target, source)
);

alter table mapping
    add constraint FK_MEN_ATTR
    foreign key (target)
    references attribute;

