--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-16-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CUSTOMER
--------------------------------------------------------

  CREATE TABLE "ADMIN"."CUSTOMER" 
   (	"CUSTOMERID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"CUSTOMERCPW" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"NAME" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"PHONE" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"EMAIL" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"ADRESS" VARCHAR2(150 BYTE) COLLATE "USING_NLS_COMP", 
	"RDATE" DATE
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "DATA" ;
REM INSERTING into ADMIN.CUSTOMER
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0050663
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."SYS_C0050663" ON "ADMIN"."CUSTOMER" ("CUSTOMERID") 
  PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA" ;
--------------------------------------------------------
--  Constraints for Table CUSTOMER
--------------------------------------------------------

  ALTER TABLE "ADMIN"."CUSTOMER" MODIFY ("CUSTOMERCPW" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."CUSTOMER" ADD PRIMARY KEY ("CUSTOMERID")
  USING INDEX PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA"  ENABLE;
