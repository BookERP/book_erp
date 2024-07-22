--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-16-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ORDER
--------------------------------------------------------

  CREATE TABLE "ADMIN"."ORDER" 
   (	"ORDERID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"ORDERDATE" DATE, 
	"SHIPPINGDATE" DATE, 
	"STATUS" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"AMOUNT" NUMBER
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "DATA" ;
REM INSERTING into ADMIN."ORDER"
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0050665
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."SYS_C0050665" ON "ADMIN"."ORDER" ("ORDERID") 
  PCTFREE 10 INITRANS 20 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "DATA" ;
--------------------------------------------------------
--  Constraints for Table ORDER
--------------------------------------------------------

  ALTER TABLE "ADMIN"."ORDER" ADD PRIMARY KEY ("ORDERID")
  USING INDEX PCTFREE 10 INITRANS 20 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "DATA"  ENABLE;
