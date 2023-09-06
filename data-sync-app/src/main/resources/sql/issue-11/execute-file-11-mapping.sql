create table MAPPING_ATTRIBUTE
(
    ID              BIGINT not null
        primary key,
    PATH            VARCHAR(255),
    TARGET_PROPERTY VARCHAR(255)
);

create table QUALIFIER_MAPPING_ATTRIBUTE
(
    ID                 BIGINT not null
        primary key,
    PATH               VARCHAR(255),
    QUALIFIER          VARCHAR(255),
    QUALIFIER_PARENT   VARCHAR(255),
    QUALIFIER_PROPERTY VARCHAR(255),
    TARGET_PROPERTY    VARCHAR(255)
);

create table REFERENCE_MAPPING_ATTRIBUTE
(
    ID              BIGINT not null
        primary key,
    NESTED_PROPERTY VARCHAR(255),
    PATH            VARCHAR(255),
    TARGET_PROPERTY VARCHAR(255)
);