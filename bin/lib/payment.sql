--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-16-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table Payment
--------------------------------------------------------

  CREATE TABLE "ADMIN"."Payment" 
   (	"PAYMENTID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"ORDERID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"PDATE" DATE, 
	"PMETHOD" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"PAMOUNT" NUMBER
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "DATA" ;
REM INSERTING into ADMIN."Payment"
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0050674
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."SYS_C0050674" ON "ADMIN"."Payment" ("PAYMENTID") 
  PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA" ;
--------------------------------------------------------
--  Constraints for Table Payment
--------------------------------------------------------

  ALTER TABLE "ADMIN"."Payment" ADD PRIMARY KEY ("PAYMENTID")
  USING INDEX PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table Payment
--------------------------------------------------------

  ALTER TABLE "ADMIN"."Payment" ADD FOREIGN KEY ("ORDERID")
	  REFERENCES "ADMIN"."ORDER" ("ORDERID") ENABLE;
