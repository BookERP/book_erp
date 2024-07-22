--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-16-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table EMPLOYEE
--------------------------------------------------------

  CREATE TABLE "ADMIN"."EMPLOYEE" 
   (	"EMPLOYEEID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"NAME" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"POSITION" VARCHAR2(60 BYTE) COLLATE "USING_NLS_COMP", 
	"PHONE" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"EMAIL" VARCHAR2(40 BYTE) COLLATE "USING_NLS_COMP", 
	"EPW" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"HIREDATE" DATE
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "DATA" ;
REM INSERTING into ADMIN.EMPLOYEE
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0050678
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."SYS_C0050678" ON "ADMIN"."EMPLOYEE" ("EMPLOYEEID") 
  PCTFREE 10 INITRANS 20 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "DATA" ;
--------------------------------------------------------
--  Constraints for Table EMPLOYEE
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EMPLOYEE" ADD PRIMARY KEY ("EMPLOYEEID")
  USING INDEX PCTFREE 10 INITRANS 20 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "DATA"  ENABLE;
