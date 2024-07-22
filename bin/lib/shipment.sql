--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-16-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SHIPMENT
--------------------------------------------------------

  CREATE TABLE "ADMIN"."SHIPMENT" 
   (	"SHIPMENTID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"ORDERID" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"SDATE" DATE, 
	"STATUS" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"SADDRESS" VARCHAR2(150 BYTE) COLLATE "USING_NLS_COMP"
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "DATA" ;
REM INSERTING into ADMIN.SHIPMENT
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0050676
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."SYS_C0050676" ON "ADMIN"."SHIPMENT" ("SHIPMENTID") 
  PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA" ;
--------------------------------------------------------
--  Constraints for Table SHIPMENT
--------------------------------------------------------

  ALTER TABLE "ADMIN"."SHIPMENT" ADD PRIMARY KEY ("SHIPMENTID")
  USING INDEX PCTFREE 10 INITRANS 20 MAXTRANS 255 
  TABLESPACE "DATA"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SHIPMENT
--------------------------------------------------------

  ALTER TABLE "ADMIN"."SHIPMENT" ADD FOREIGN KEY ("ORDERID")
	  REFERENCES "ADMIN"."ORDER" ("ORDERID") ENABLE;
