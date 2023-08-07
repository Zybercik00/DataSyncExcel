create table CURRENCY
(
    CURRENCY_ID   BIGINT not null
        primary key,
    CURRENCY_NAME CHARACTER VARYING(255)
);

create table EMPLOYEE
(
    EMPLOYEE_ID   BIGINT not null
        primary key,
    ACCESS_LEVEL  CHARACTER VARYING(255),
    EMPLOYEE_NAME CHARACTER VARYING(255),
    POSITIN       CHARACTER VARYING(255)
);

create table MAPPING_ATTRIBUTE_ENTITY
(
    ID              BIGINT not null
        primary key,
    PATH            CHARACTER VARYING(255),
    TARGET_PROPERTY CHARACTER VARYING(255)
);

create table MARGIN
(
    MARGIN_ID   BIGINT not null
        primary key,
    MARGIN_NAME CHARACTER VARYING(255)
);

create table PRICE
(
    PRICE_ID BIGINT not null
        primary key
);

create table QUALIFIER_MAPPING_ATTRIBUTE_ENTITY
(
    ID                 BIGINT not null
        primary key,
    PATH               CHARACTER VARYING(255),
    QUALIFIER          CHARACTER VARYING(255),
    QUALIFIER_PARENT   CHARACTER VARYING(255),
    QUALIFIER_PROPERTY CHARACTER VARYING(255),
    TARGET_PROPERTY    CHARACTER VARYING(255)
);

create table REFERENCE_MAPPING_ATTRIBUTE_ENTITY
(
    ID              BIGINT not null
        primary key,
    NESTED_PROPERTY CHARACTER VARYING(255),
    PATH            CHARACTER VARYING(255),
    TARGET_PROPERTY CHARACTER VARYING(255)
);

create table SUPPLIER
(
    SUPPILER_ID   BIGINT not null
        primary key,
    SUPPILER_NAME CHARACTER VARYING(255)
);

create table WAREHOUSE
(
    WAREHOUSE_ID   BIGINT not null
        primary key,
    WAREHOUSE_NAME CHARACTER VARYING(255)
);

create table MATERIAL
(
    MATERIAL_ID      BIGINT not null
        primary key,
    CONTENT          CHARACTER VARYING(255),
    STATUS_INVENTORY CHARACTER VARYING(255),
    LOT              CHARACTER VARYING(255),
    MATERIAL_NAME    CHARACTER VARYING(255),
    WEIGHT           CHARACTER VARYING(255),
    SUPPILER         BIGINT,
    WAREHOUSE        BIGINT,
    constraint FKFMH5NRQM4NHXVXO7PX9MISBC7
        foreign key (SUPPILER) references SUPPLIER,
    constraint FKOFSKC3C96YJQLD3U81X9254VA
        foreign key (WAREHOUSE) references WAREHOUSE
);

create table EXTRACTION
(
    "extraction_|d"    BIGINT not null
        primary key,
    PREPARED_ON        TIMESTAMP,
    RECEIVED_IN_BERN   TIMESTAMP,
    SAMPLE_TEST_RESULT NUMERIC(38, 2),
    WEIGHT_AFTER       NUMERIC(38, 2),
    WEIGHT_BEFORE      NUMERIC(38, 2),
    MATERIAL           BIGINT,
    constraint FKMS8DLT6JOUB0DGGKC4OGHTWAS
        foreign key (MATERIAL) references MATERIAL
);

create table PURCHASE_PRICE
(
    ID             BIGINT not null
        primary key,
    PURCHASE_PRICE NUMERIC(38, 2),
    SALE_PRICE     NUMERIC(38, 2),
    CURRENCY       BIGINT,
    EXTRACTION     BIGINT,
    MARGIN         BIGINT,
    constraint FK1SO3RU3QTROKR17DF22O0HJF7
        foreign key (MARGIN) references MARGIN,
    constraint FK30WL5CMP8PID2WQ85L53CN42S
        foreign key (EXTRACTION) references EXTRACTION,
    constraint FKT1Q36QGL8FTTD0BREXB2LMTBL
        foreign key (CURRENCY) references CURRENCY
);

create table CURRENCY_PURCHASE_PRICES
(
    CURRENCY_CURRENCY_ID BIGINT not null,
    PURCHASE_PRICES_ID   BIGINT not null
        constraint UK_LJL3HMOQM5HX4DYJNLTACLNSJ
            unique,
    constraint FK3D8D62PKAGCX3ECLAQBBAWJ63
        foreign key (PURCHASE_PRICES_ID) references PURCHASE_PRICE,
    constraint FKSXL27388SBKRY5ORAICWYDDU2
        foreign key (CURRENCY_CURRENCY_ID) references CURRENCY
);

create table SUPPLIER_SUPPLIER_MATERIALS
(
    SUPPLIER_SUPPILER_ID           BIGINT not null,
    SUPPLIER_MATERIALS_MATERIAL_ID BIGINT not null
        constraint UK_T8G2WFTEQGWM9EXD1R5KVJ3XK
            unique,
    constraint FKDM2N0RTDWL5UD918DJ9PYKEX1
        foreign key (SUPPLIER_MATERIALS_MATERIAL_ID) references MATERIAL,
    constraint FKML69H4R2KMOYA245WWPI0R5YD
        foreign key (SUPPLIER_SUPPILER_ID) references SUPPLIER
);

create table WAREHOUSE_LOCATION
(
    WAREHOUSE_LOCATION_ID BIGINT not null
        primary key,
    LOCATION_NAME         CHARACTER VARYING(255)
);

create table WASTE
(
    WASTE_ID                      BIGINT not null
        primary key,
    LOSS_AFTER_EXTRACTION_KG      NUMERIC(38, 2),
    LOSS_AFTER_EXTRACTION_PERCENT NUMERIC(38, 2),
    LOSS_TOTAL_KG                 NUMERIC(38, 2),
    LOSS_TOTAL_PERCENTS           NUMERIC(38, 2),
    PACKED_KG                     NUMERIC(38, 2)
);

