/*
Navicat PGSQL Data Transfer

Source Server         : PostgreSQL
Source Server Version : 90602
Source Host           : localhost:5432
Source Database       : hibernate
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90602
File Encoding         : 65001

Date: 2018-02-02 22:51:39
*/


-- ----------------------------
-- Sequence structure for main_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."main_seq";
CREATE SEQUENCE "public"."main_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 9
 CACHE 1;
SELECT setval('"public"."main_seq"', 9, true);

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS "public"."author";
CREATE TABLE "public"."author" (
"id" int8 NOT NULL,
"name" varchar(300) COLLATE "default" NOT NULL,
"second_name" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO "public"."author" VALUES ('1', 'Лермонтов99', '12314');
INSERT INTO "public"."author" VALUES ('2', 'Пушкин', '1');
INSERT INTO "public"."author" VALUES ('3', 'Некрасов', '2');
INSERT INTO "public"."author" VALUES ('4', 'Шолохов', '3');
INSERT INTO "public"."author" VALUES ('5', 'Акунин', '4');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS "public"."book";
CREATE TABLE "public"."book" (
"id" int8 NOT NULL,
"name" varchar(45) COLLATE "default" NOT NULL,
"author_id" int8 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO "public"."book" VALUES ('1', 'Бородино', '1');
INSERT INTO "public"."book" VALUES ('2', 'Сказка', '2');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS "public"."reader";
CREATE TABLE "public"."reader" (
"id" int4 NOT NULL,
"name" varchar(255) COLLATE "default",
"book_id" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of reader
-- ----------------------------

-- ----------------------------
-- Table structure for test_table
-- ----------------------------
DROP TABLE IF EXISTS "public"."test_table";
CREATE TABLE "public"."test_table" (
"id" int4 NOT NULL,
"name" varchar(255) COLLATE "default" NOT NULL,
"cnt" varchar(255) COLLATE "default" DEFAULT 0,
"second_field" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of test_table
-- ----------------------------

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table author
-- ----------------------------
ALTER TABLE "public"."author" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table book
-- ----------------------------
CREATE INDEX "fk_author_id_idx" ON "public"."book" USING btree ("author_id");

-- ----------------------------
-- Primary Key structure for table book
-- ----------------------------
ALTER TABLE "public"."book" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table reader
-- ----------------------------
ALTER TABLE "public"."reader" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table test_table
-- ----------------------------
ALTER TABLE "public"."test_table" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."book"
-- ----------------------------
ALTER TABLE "public"."book" ADD FOREIGN KEY ("author_id") REFERENCES "public"."author" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
