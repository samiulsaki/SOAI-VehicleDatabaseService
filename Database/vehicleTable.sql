--------------------------------------------------------
--  File created - Tuesday-September-26-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table VEHICLE_REGS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."VEHICLE_REGS" 
   (	"VEHICLE_REGS_REG" VARCHAR2(10 BYTE), 
	"VEHICLE_REGS_BRAND" VARCHAR2(20 BYTE), 
	"VEHICLE_REGS_MODEL" VARCHAR2(10 BYTE), 
	"VEHICLE_REGS_MANU_YEAR" NUMBER(*,0), 
	"VEHICLE_REGS_OWNER_FIRST_NAME" VARCHAR2(20 BYTE), 
	"VEHICLE_REGS_OWNER_LAST_NAME" VARCHAR2(20 BYTE), 
	"VEHICLE_REGS_FIRST_REG" VARCHAR2(15 BYTE), 
	"VEHICLE_REGS_PK" NUMBER(*,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SYSTEM.VEHICLE_REGS
SET DEFINE OFF;
Insert into SYSTEM.VEHICLE_REGS (VEHICLE_REGS_REG,VEHICLE_REGS_BRAND,VEHICLE_REGS_MODEL,VEHICLE_REGS_MANU_YEAR,VEHICLE_REGS_OWNER_FIRST_NAME,VEHICLE_REGS_OWNER_LAST_NAME,VEHICLE_REGS_FIRST_REG,VEHICLE_REGS_PK) values ('NO2277S12','Audi','S8',2014,'Sam','Saki','05-Jan-2015',1);
Insert into SYSTEM.VEHICLE_REGS (VEHICLE_REGS_REG,VEHICLE_REGS_BRAND,VEHICLE_REGS_MODEL,VEHICLE_REGS_MANU_YEAR,VEHICLE_REGS_OWNER_FIRST_NAME,VEHICLE_REGS_OWNER_LAST_NAME,VEHICLE_REGS_FIRST_REG,VEHICLE_REGS_PK) values ('NO128GA5','Audi','F16',2009,'Nils-Tore','Nilsen','18-Mar-2010',2);
Insert into SYSTEM.VEHICLE_REGS (VEHICLE_REGS_REG,VEHICLE_REGS_BRAND,VEHICLE_REGS_MODEL,VEHICLE_REGS_MANU_YEAR,VEHICLE_REGS_OWNER_FIRST_NAME,VEHICLE_REGS_OWNER_LAST_NAME,VEHICLE_REGS_FIRST_REG,VEHICLE_REGS_PK) values ('NO1212121','Ferari','Speed',2016,'Aase-Perly','Pederson','10-Jun-2016',4);
Insert into SYSTEM.VEHICLE_REGS (VEHICLE_REGS_REG,VEHICLE_REGS_BRAND,VEHICLE_REGS_MODEL,VEHICLE_REGS_MANU_YEAR,VEHICLE_REGS_OWNER_FIRST_NAME,VEHICLE_REGS_OWNER_LAST_NAME,VEHICLE_REGS_FIRST_REG,VEHICLE_REGS_PK) values ('NO66EX211','Mercedez Benz','EAX30',2011,'Kari-Anette','Nilsen','12-May-2012',3);
Insert into SYSTEM.VEHICLE_REGS (VEHICLE_REGS_REG,VEHICLE_REGS_BRAND,VEHICLE_REGS_MODEL,VEHICLE_REGS_MANU_YEAR,VEHICLE_REGS_OWNER_FIRST_NAME,VEHICLE_REGS_OWNER_LAST_NAME,VEHICLE_REGS_FIRST_REG,VEHICLE_REGS_PK) values ('NOXXXX11','Toyota','FFF',2004,'Frode','Brotenberg','22-jan-2011',13);
Insert into SYSTEM.VEHICLE_REGS (VEHICLE_REGS_REG,VEHICLE_REGS_BRAND,VEHICLE_REGS_MODEL,VEHICLE_REGS_MANU_YEAR,VEHICLE_REGS_OWNER_FIRST_NAME,VEHICLE_REGS_OWNER_LAST_NAME,VEHICLE_REGS_FIRST_REG,VEHICLE_REGS_PK) values ('NOXXXdsaas','Toyota','asda',2001,'Alex','Ferdinando','dd-MM-YYYY',22);
Insert into SYSTEM.VEHICLE_REGS (VEHICLE_REGS_REG,VEHICLE_REGS_BRAND,VEHICLE_REGS_MODEL,VEHICLE_REGS_MANU_YEAR,VEHICLE_REGS_OWNER_FIRST_NAME,VEHICLE_REGS_OWNER_LAST_NAME,VEHICLE_REGS_FIRST_REG,VEHICLE_REGS_PK) values ('NO11111112','El','power',2016,'Mike','Hunt','21-Feb-2016',42);
--------------------------------------------------------
--  DDL for Index VEHICLE_REGS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."VEHICLE_REGS_PK" ON "SYSTEM"."VEHICLE_REGS" ("VEHICLE_REGS_PK") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table VEHICLE_REGS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."VEHICLE_REGS" MODIFY ("VEHICLE_REGS_REG" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."VEHICLE_REGS" MODIFY ("VEHICLE_REGS_BRAND" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."VEHICLE_REGS" MODIFY ("VEHICLE_REGS_MODEL" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."VEHICLE_REGS" MODIFY ("VEHICLE_REGS_MANU_YEAR" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."VEHICLE_REGS" MODIFY ("VEHICLE_REGS_OWNER_FIRST_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."VEHICLE_REGS" MODIFY ("VEHICLE_REGS_OWNER_LAST_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."VEHICLE_REGS" MODIFY ("VEHICLE_REGS_FIRST_REG" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."VEHICLE_REGS" ADD CONSTRAINT "VEHICLE_REGS_PK" PRIMARY KEY ("VEHICLE_REGS_PK")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table VEHICLE_REGS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."VEHICLE_REGS" ADD CONSTRAINT "VEHICLE_REGS_VEHICLE_REGS_FK1" FOREIGN KEY ("VEHICLE_REGS_PK")
	  REFERENCES "SYSTEM"."VEHICLE_REGS" ("VEHICLE_REGS_PK") ENABLE;
--------------------------------------------------------
--  DDL for Trigger VEHICLE_REGS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."VEHICLE_REGS_TRG" 
BEFORE INSERT ON VEHICLE_REGS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.VEHICLE_REGS_PK IS NULL THEN
      SELECT VEHICLE_REGS_SEQ.NEXTVAL INTO :NEW.VEHICLE_REGS_PK FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."VEHICLE_REGS_TRG" ENABLE;
