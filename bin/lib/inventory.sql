--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-16-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table INVENTORY
--------------------------------------------------------

  CREATE TABLE "ADMIN"."INVENTORY" 
   (	"INVENTORYID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"ORDERID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"CURRENTQ" NUMBER, 
	"LOCATION" VARCHAR2(150 BYTE) COLLATE "USING_NLS_COMP"
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "DATA" ;
REM INSERTING into ADMIN.INVENTORY
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0050673
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."SYS_C0050673" ON "ADMIN"."INVENTORY" ("INVENTORYID") 
  PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA" ;
--------------------------------------------------------
--  Constraints for Table INVENTORY
--------------------------------------------------------

  ALTER TABLE "ADMIN"."INVENTORY" ADD PRIMARY KEY ("INVENTORYID")
  USING INDEX PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA"  ENABLE;
