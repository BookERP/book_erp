--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-16-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table PRODUCT
--------------------------------------------------------

  CREATE TABLE "ADMIN"."PRODUCT" 
   (	"PRODUCTID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"SUPPLIERID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"NAME" VARCHAR2(120 BYTE) COLLATE "USING_NLS_COMP", 
	"AUTHOR" VARCHAR2(60 BYTE) COLLATE "USING_NLS_COMP", 
	"PUBLISHER" VARCHAR2(120 BYTE) COLLATE "USING_NLS_COMP", 
	"PRICE" NUMBER, 
	"STOCKQ" NUMBER, 
	"CATEGORY" VARCHAR2(60 BYTE) COLLATE "USING_NLS_COMP"
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "DATA" ;
REM INSERTING into ADMIN.PRODUCT
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0050668
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."SYS_C0050668" ON "ADMIN"."PRODUCT" ("PRODUCTID") 
  PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA" ;
--------------------------------------------------------
--  Constraints for Table PRODUCT
--------------------------------------------------------

  ALTER TABLE "ADMIN"."PRODUCT" ADD PRIMARY KEY ("PRODUCTID")
  USING INDEX PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PRODUCT
--------------------------------------------------------

  ALTER TABLE "ADMIN"."PRODUCT" ADD FOREIGN KEY ("SUPPLIERID")
	  REFERENCES "ADMIN"."SUPPLIER" ("SUPPLIERID") ENABLE;
