--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-16-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RECOMMAND
--------------------------------------------------------

  CREATE TABLE "ADMIN"."RECOMMAND" 
   (	"RECOMMENDID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"PRODUCTID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"AUTHOR" VARCHAR2(60 BYTE) COLLATE "USING_NLS_COMP", 
	"PUBLISHER" VARCHAR2(120 BYTE) COLLATE "USING_NLS_COMP", 
	"PRICE" NUMBER, 
	"STOCKQ" NUMBER, 
	"CATEGORY" VARCHAR2(60 BYTE) COLLATE "USING_NLS_COMP"
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "DATA" ;
REM INSERTING into ADMIN.RECOMMAND
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0050658
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."SYS_C0050658" ON "ADMIN"."RECOMMAND" ("RECOMMENDID") 
  PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA" ;
--------------------------------------------------------
--  Constraints for Table RECOMMAND
--------------------------------------------------------

  ALTER TABLE "ADMIN"."RECOMMAND" ADD PRIMARY KEY ("RECOMMENDID")
  USING INDEX PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA"  ENABLE;
