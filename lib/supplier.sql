--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-16-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SUPPLIER
--------------------------------------------------------

  CREATE TABLE "ADMIN"."SUPPLIER" 
   (	"SUPPLIERID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"NAME" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"PHONE" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"EMAIL" VARCHAR2(50 BYTE) COLLATE "USING_NLS_COMP", 
	"ADRESS" VARCHAR2(150 BYTE) COLLATE "USING_NLS_COMP", 
	"SUPPLIERNAME" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP"
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "DATA" ;
REM INSERTING into ADMIN.SUPPLIER
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0050664
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."SYS_C0050664" ON "ADMIN"."SUPPLIER" ("SUPPLIERID") 
  PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA" ;
--------------------------------------------------------
--  Constraints for Table SUPPLIER
--------------------------------------------------------

  ALTER TABLE "ADMIN"."SUPPLIER" ADD PRIMARY KEY ("SUPPLIERID")
  USING INDEX PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA"  ENABLE;
