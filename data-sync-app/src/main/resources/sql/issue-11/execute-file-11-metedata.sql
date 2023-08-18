
create table ATTRIBUTE
(
    ATTRIBUTE_ID            not null
        primary key,
    PATH                    VARCHAR,
    TARGET_PROPERTY         VARCHAR,
);

create tabele QUALIFIER_ATTRIBUTE
(
    QUALIFIED_PROPERTY      VARCHAR,
    QUALIFIED_PARENT        VARCHAR,
    QUALIFIER               VARCHAR
);
