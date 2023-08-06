
CREATE SEQUENCE CURRENCY_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE EXTRACTION_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE MARGIN_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE MATERIAL_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE PURCHASE_PRICE_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE SALE_PRICE_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE SUPPLIER_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE WAREHOUSE_LOCATION_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE WAREHOUSE_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE WASTE_SEQ
    START WITH 1
    INCREMENT BY 50;

CREATE TABLE CURRENCY (
    CURRENCY_ID BIGINT NOT NULL,
    CURRENCY_NAME VARCHAR(255),
    PRIMARY KEY (CURRENCY_ID),
    CONSTRAINT UC_CURRENCY_NME UNIQUE (CURRENCY_NAME)
);

CREATE TABLE EMPLOYEE (
    EMPLOYEE_ID BIGINT NOT NULL,
    ACCESS_LEVEL VARCHAR(255),
    EMPLOYEE_NAME VARCHAR(255),
    EMP_POSITION VARCHAR(255),
    PRIMARY KEY (EMPLOYEE_ID),
    CONSTRAINT UC_EMPLOYEE_NME UNIQUE (EMPLOYEE_NAME)
);

CREATE TABLE EXTRACTION (
    SAMPLE_TEST_RESULT NUMERIC(38,2),
    WEIGHT_AFTER NUMERIC(38,2),
    WEIGHT_BEFORE NUMERIC(38,2),
    EXTRACTION_ID BIGINT NOT NULL,
    MATERIAL BIGINT,
    PREPARED_ON TIMESTAMP(6),
    RECEIVED_IN_BERN TIMESTAMP(6),
    PRIMARY KEY (EXTRACTION_ID)
);

CREATE TABLE MARGIN (
    MARGIN_ID BIGINT NOT NULL,
    MARGIN_NAME VARCHAR(255),
    PRIMARY KEY (MARGIN_ID),
    CONSTRAINT UC_MARGIN_NME UNIQUE (MARGIN_NAME)
);

CREATE TABLE MATERIAL (
    MATERIAL_ID BIGINT NOT NULL,
    SUPPLIER BIGINT,
    WAREHOUSE BIGINT,
    CONTENT VARCHAR(255),
    LOT VARCHAR(255),
    MATERIAL_NAME VARCHAR(255),
    STATUS_INVENTORY VARCHAR(255),
    WEIGHT VARCHAR(255),
    PRIMARY KEY (MATERIAL_ID),
    CONSTRAINT UC_MATERIAL_NME UNIQUE (LOT)
);

CREATE TABLE PURCHASE_PRICE (
    ID BIGINT NOT NULL,
    PURCHASE_PRICE NUMERIC(38,2),
    CURRENCY BIGINT,
    EXTRACTION BIGINT,
    PRIMARY KEY (ID),
    CONSTRAINT UC_PURCHASE_PRICE UNIQUE (CURRENCY, EXTRACTION)
);

CREATE TABLE SALE_PRICE (
    ID BIGINT NOT NULL,
    SALE_PRICE NUMERIC(38,2),
    EXTRACTION BIGINT,
    MARGIN BIGINT,
    PRIMARY KEY (ID),
    CONSTRAINT UC_SALE_PRICE UNIQUE (EXTRACTION, MARGIN)
);

CREATE TABLE SUPPLIER (
    SUPPLIER_ID BIGINT NOT NULL,
    SUPPLIER_NAME VARCHAR(255),
    PRIMARY KEY (SUPPLIER_ID),
    CONSTRAINT UC_SUPPLIER UNIQUE (SUPPLIER_NAME)
);

CREATE TABLE WAREHOUSE (
    WAREHOUSE_ID BIGINT NOT NULL,
    WAREHOUSE_NAME VARCHAR(255),
    PRIMARY KEY (WAREHOUSE_ID),
    CONSTRAINT UC_WAREHOUSE UNIQUE (WAREHOUSE_NAME)
);

CREATE TABLE WAREHOUSE_LOCATION (
    WAREHOUSE_LOCATION_ID BIGINT NOT NULL,
    LOCATION_NAME VARCHAR(255),
    PRIMARY KEY (WAREHOUSE_LOCATION_ID),
    CONSTRAINT UC_WAREHOUSE_LOCATION UNIQUE (LOCATION_NAME)
);

CREATE TABLE WASTE (
    WASTE_ID BIGINT NOT NULL,
    LOSS_AFTER_EXTRACTION_KG NUMERIC(38,2),
    LOSS_AFTER_EXTRACTION_PERCENT NUMERIC(38,2),
    LOSS_TOTAL_KG NUMERIC(38,2),
    LOSS_TOTAL_PERCENTS NUMERIC(38,2),
    PACKED_KG NUMERIC(38,2),
    PRIMARY KEY (WASTE_ID)
);

ALTER TABLE EXTRACTION
    ADD CONSTRAINT FK_EXT_MAT
    FOREIGN KEY (MATERIAL)
    REFERENCES MATERIAL;

ALTER TABLE MATERIAL
    ADD CONSTRAINT FK_MAT_SPL
    FOREIGN KEY (SUPPLIER)
    REFERENCES SUPPLIER;

ALTER TABLE MATERIAL
    ADD CONSTRAINT FK_MAT_WHS
    FOREIGN KEY (WAREHOUSE)
    REFERENCES WAREHOUSE;

ALTER TABLE PURCHASE_PRICE
    ADD CONSTRAINT FK_PPR_CUR
    FOREIGN KEY (CURRENCY)
    REFERENCES CURRENCY;

ALTER TABLE PURCHASE_PRICE
    ADD CONSTRAINT FK_PPR_EXT
    FOREIGN KEY (EXTRACTION)
    REFERENCES EXTRACTION;

ALTER TABLE SALE_PRICE
    ADD CONSTRAINT FK_SPR_EXT
    FOREIGN KEY (EXTRACTION)
    REFERENCES EXTRACTION;

ALTER TABLE SALE_PRICE
    ADD CONSTRAINT FK_SPR_MRG
    FOREIGN KEY (MARGIN)
    REFERENCES MARGIN;