
ALTER TABLE WASTE
ADD EXTRACTION_ID BIGINT;

UPDATE WASTE SET EXTRACTION_ID = WASTE_ID;

ALTER TABLE WASTE
MODIFY EXTRACTION_ID BIGINT NOT NULL;

ALTER TABLE WASTE
ADD CONSTRAINT UC_WASTE UNIQUE (EXTRACTION_ID);

ALTER TABLE WASTE
ADD FOREIGN KEY FK_WST_EXT REFERENCES (EXTRACTION_ID);
