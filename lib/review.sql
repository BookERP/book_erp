--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-16-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table REVIEW
--------------------------------------------------------

  CREATE TABLE "ADMIN"."REVIEW" 
   (	"REVIEWID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"CUSTOMERID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"PRODUCTID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"REVIEWCONTENT" VARCHAR2(300 BYTE) COLLATE "USING_NLS_COMP", 
	"RATING" NUMBER, 
	"REVIEWDATE" DATE
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "DATA" ;
REM INSERTING into ADMIN.REVIEW
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0050670
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."SYS_C0050670" ON "ADMIN"."REVIEW" ("REVIEWID") 
  PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA" ;
--------------------------------------------------------
--  Constraints for Table REVIEW
--------------------------------------------------------

  ALTER TABLE "ADMIN"."REVIEW" ADD PRIMARY KEY ("REVIEWID")
  USING INDEX PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REVIEW
--------------------------------------------------------

  ALTER TABLE "ADMIN"."REVIEW" ADD FOREIGN KEY ("CUSTOMERID")
	  REFERENCES "ADMIN"."CUSTOMER" ("CUSTOMERID") ENABLE;
  ALTER TABLE "ADMIN"."REVIEW" ADD FOREIGN KEY ("PRODUCTID")
	  REFERENCES "ADMIN"."PRODUCT" ("PRODUCTID") ENABLE;
