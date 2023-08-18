create table CURRENCY
(
    CURRENCY_ID   BIGINT not null
        primary key,
    CURRENCY_NAME VARCHAR(255)
    CONSTRAINT UC_CURRENCY UNIQUE (CURRENCY_NAME)
);

create table EMPLOYEE
(
    EMPLOYEE_ID   BIGINT not null
        primary key,
    ACCESS_LEVEL  VARCHAR(255),
    EMPLOYEE_NAME VARCHAR(255),
    POSITION      VARCHAR(255)
    CONSTRAINT UC_EMPLOYEE UNIQUE (EMPLOYEE_NAME)
);

create table MARGIN
(
    MARGIN_ID   BIGINT not null
        primary key,
    MARGIN_NAME VARCHAR(255)
CONSTRAINT UC_MARGIN UNIQUE (MARGIN_NAME)
);

create table SUPPLIER
(
    SUPPLIER_ID   BIGINT not null
        primary key,
    SUPPLIER_NAME VARCHAR(255)
    CONSTRAINT UC_SUPPLIER UNIQUE (SUPPILER_NAME)
);

create table WAREHOUSE
(
    WAREHOUSE_ID   BIGINT not null
        primary key,
    WAREHOUSE_NAME VARCHAR(255)
    CONSTRAINT UC_WAREHOUSE UNIQUE (WAREHOUSE_NAME)
);

create table MATERIAL
(
    MATERIAL_ID      BIGINT not null
        primary key,
    CONTENT          VARCHAR(255),
    STATUS_INVENTORY VARCHAR(255),
    LOT              VARCHAR(255),
    MATERIAL_NAME    VARCHAR(255),
    WEIGHT           VARCHAR(255),
    SUPPLIER         BIGINT,
    WAREHOUSE        BIGINT,
    constraint FK_MAT_SUP
        foreign key (SUPPLIER) references SUPPLIER,
    constraint FK_MAT_WAR
        foreign key (WAREHOUSE) references WAREHOUSE
);

create table EXTRACTION
(
    EXTRACTION_ID    BIGINT not null
        primary key,
    PREPARED_ON        TIMESTAMP,
    RECEIVED_IN_BERN   TIMESTAMP,
    SAMPLE_TEST_RESULT NUMERIC(38, 2),
    WEIGHT_AFTER       NUMERIC(38, 2),
    WEIGHT_BEFORE      NUMERIC(38, 2),
    MATERIAL           BIGINT,
    constraint FK_EXT_MAT
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
    constraint FK_PPR_MAR
        foreign key (MARGIN) references MARGIN,
    constraint FK_PPR_EXT
        foreign key (EXTRACTION) references EXTRACTION,
    constraint FK_PPR_CUR
        foreign key (CURRENCY) references CURRENCY
);

create table SALE_PRICE
(
    ID             BIGINT not null
        primary key,
    SALE_PRICE NUMERIC(38, 2),
    EXTRACTION     BIGINT,
    MARGIN         BIGINT,
    constraint FK_SPR_MAR
        foreign key (MARGIN) references MARGIN,
    CONSTRAINT UC_SALE_PRICE UNIQUE (EXTRACTION, MARGIN)
);

create table CURRENCY_PURCHASE_PRICES
(
    CURRENCY_CURRENCY_ID BIGINT not null,
    PURCHASE_PRICES_ID   BIGINT not null
        constraint UC_CURPPR
            unique,
    constraint FK_CURPPR_PPR
        foreign key (PURCHASE_PRICES_ID) references PURCHASE_PRICE,
    constraint FK_CURPPR_CUR
        foreign key (CURRENCY_CURRENCY_ID) references CURRENCY
);

create table SUPPLIER_MATERIALS
(
    SUPPLIER_SUPPLIER_ID           BIGINT not null,
    SUPPLIER_MATERIALS_MATERIAL_ID BIGINT not null
        constraint UC_SUPMAT
            unique,
    constraint FK_SUPMAT_MAT
        foreign key (SUPPLIER_MATERIALS_MATERIAL_ID) references MATERIAL,
    constraint FK_SUPMAT_SUP
        foreign key (SUPPLIER_SUPPLIER_ID) references SUPPLIER
);

create table WAREHOUSE_LOCATION
(
    WAREHOUSE_LOCATION_ID BIGINT not null
        primary key,
    LOCATION_NAME         VARCHAR(255)
    CONSTRAINT UC_WAR_LOC UNIQUE (LOCATION_NAME)
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

