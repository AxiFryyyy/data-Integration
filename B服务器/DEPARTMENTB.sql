/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : Oracle
 Source Server Version : 110200 (Oracle Database 11g Enterprise Edition Release 11.2.0.1.0 - 64bit Production
With the Partitioning, OLAP, Data Mining and Real Application Testing options)
 Source Host           : 127.0.0.1:1521
 Source Schema         : DEPARTMENTB

 Target Server Type    : Oracle
 Target Server Version : 110200 (Oracle Database 11g Enterprise Edition Release 11.2.0.1.0 - 64bit Production
With the Partitioning, OLAP, Data Mining and Real Application Testing options)
 File Encoding         : 65001

 Date: 22/03/2023 08:55:45
*/


-- ----------------------------
-- Table structure for ACCOUNT
-- ----------------------------
DROP TABLE "DEPARTMENTB"."ACCOUNT";
CREATE TABLE "DEPARTMENTB"."ACCOUNT" (
  "USERNAME" VARCHAR2(12 BYTE) NOT NULL,
  "PASSWORD" VARCHAR2(12 BYTE),
  "ACCOUNT_GRADE" NUMBER(2,0),
  "HANDLER_ID" VARCHAR2(9 BYTE)
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of ACCOUNT
-- ----------------------------
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('7691107620', 'ZDoxjp2yd6', '2', '200000001');
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('7555805018', 'y30v2rVkyW', '2', '200000005');
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('15170663123', 'nhQT5rPbn5', '2', '200000002');
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('14527406166', '6xHuDubyPd', '2', '200000007');
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('16832190517', 'Emxf4flHJA', '2', '200000006');
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('15331576875', 'xXbnpxew7P', '2', '200000008');
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('13566063971', '9QAGiDkO4h', '2', '200000009');
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('17384957212', '5f1ekrcXBd', '2', '200000003');
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('1070204161', '972rv8cNaW', '2', '200000000');
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('15871195263', 'bhukVjS3qf', '2', '200000004');
INSERT INTO "DEPARTMENTB"."ACCOUNT" VALUES ('admin', '206477', '1', '200000000');

-- ----------------------------
-- Table structure for COURSES
-- ----------------------------
DROP TABLE "DEPARTMENTB"."COURSES";
CREATE TABLE "DEPARTMENTB"."COURSES" (
  "COURSE_ID" VARCHAR2(5 BYTE) NOT NULL,
  "COURSE_NAME" VARCHAR2(16 BYTE),
  "COURSE_HOURS" VARCHAR2(2 BYTE),
  "CREDIT" VARCHAR2(1 BYTE),
  "TEACHER" VARCHAR2(10 BYTE),
  "LOCATION" VARCHAR2(20 BYTE),
  "SHARE_WITH" CHAR(1 BYTE)
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of COURSES
-- ----------------------------
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10002', '金融服务业', '32', '2', '工藤翼', '工程部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10003', '贸易行业', '48', '3', '钱云熙', '工程部', 'A');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10004', '贸易行业', '16', '4', '宣仲賢', '物流部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10005', '制造业', '14', '3', '向志遠', '产品质量部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10006', '饮食业', '16', '5', '李天榮', '采购部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10007', '电讯業', '32', '1', '酒井詩乃', '行政管理部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10008', '制药业', '32', '2', '大野百花', '销售部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10009', '饮食业', '14', '1', '董睿', '公关部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10010', '咨询业', '14', '1', '翁頴思', '销售部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10011', '物流业', '14', '2', '渡部翼', '信息技术支持部', 'A');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10012', '资讯科技业', '32', '5', '村田樹', '研究及开发部', 'A');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10013', '电子行业', '16', '3', '小島優奈', '会计及金融部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10014', '金融服务业', '48', '5', '陆睿', '生产部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10015', '资讯科技业', '32', '4', '沈睿', '生产部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10016', '电子行业', '48', '1', '黎明詩', '会计及金融部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10017', '贸易行业', '32', '2', '谭子韬', '工程部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10018', '工业', '14', '2', '酒井愛梨', '行政管理部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10019', '金融服务业', '48', '3', '段睿', '信息技术支持部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10020', '金融服务业', '32', '4', '小山翼', '外销部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10021', '工业', '32', '3', '孔璐', '会计及金融部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10022', '饮食业', '32', '3', '任岚', '会计及金融部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10023', '贸易行业', '48', '5', '史子韬', '人力资源部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10024', '物流业', '14', '5', '小林百恵', '生产部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10025', '物流业', '16', '2', '西村拓哉', '会计及金融部', 'A');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10026', '物流业', '32', '1', '村上愛梨', '物流部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10027', '咨询业', '32', '5', '廖慧琳', '市场部', 'C');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10028', '贸易行业', '32', '2', '遠藤美月', '外销部', 'A');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10029', '饮食业', '48', '1', '许子韬', '人力资源部', NULL);
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10000', '物流业', '32', '3', '平野大地', '外销部', 'A');
INSERT INTO "DEPARTMENTB"."COURSES" VALUES ('10001', '资讯科技业', '32', '2', '钟云熙', '工程部', 'C');

-- ----------------------------
-- Table structure for COURSES_CHOSE
-- ----------------------------
DROP TABLE "DEPARTMENTB"."COURSES_CHOSE";
CREATE TABLE "DEPARTMENTB"."COURSES_CHOSE" (
  "COURSE_ID" VARCHAR2(5 BYTE) NOT NULL,
  "STUDENT_ID" VARCHAR2(9 BYTE) NOT NULL,
  "RESULT" VARCHAR2(3 BYTE)
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of COURSES_CHOSE
-- ----------------------------
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10020', '200000009', '84');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10021', '200000005', '01');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10010', '200000006', '94');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10023', '200000009', '41');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10005', '200000008', '12');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10014', '200000009', '42');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10002', '200000000', '98');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10015', '200000002', '01');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10027', '200000004', '86');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10021', '200000007', '42');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10015', '200000009', '28');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10027', '200000009', '26');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10017', '200000005', '69');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10024', '200000009', '80');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10025', '200000007', '93');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10026', '200000009', '87');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10005', '200000009', '28');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10007', '200000008', '03');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10021', '200000008', '32');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10005', '200000004', '62');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10026', '200000000', '06');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10025', '200000002', '23');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10021', '200000002', '07');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10006', '200000007', '99');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10010', '200000004', '06');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10022', '200000007', '45');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10019', '200000003', '67');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10020', '200000008', '60');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10022', '200000001', '07');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10024', '200000002', '91');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10019', '200000004', '40');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10024', '200000003', '06');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10012', '200000007', '92');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10011', '200000002', '67');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10013', '200000003', '45');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10022', '200000005', '45');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10026', '200000001', '53');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10010', '200000000', '52');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10012', '200000005', '03');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10028', '200000000', '40');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10009', '200000000', '26');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10012', '200000009', '97');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10018', '200000005', '76');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10022', '200000000', '11');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10027', '200000007', '97');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10004', '200000000', '27');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10009', '200000001', '47');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10009', '200000002', '48');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10000', '200000003', '42');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10014', '200000003', '86');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10015', '200000007', '16');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10000', '200000007', '96');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10003', '200000009', '07');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10028', '200000008', '29');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10019', '200000007', '70');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10003', '200000006', '99');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10004', '200000001', '59');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10013', '200000007', '25');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10002', '200000006', '97');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10001', '200000008', '15');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10024', '200000001', '40');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10006', '200000003', '07');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10016', '200000006', '67');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10023', '200000001', '69');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10018', '200000004', '88');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10009', '200000007', '50');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10001', '200000005', '47');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10028', '200000003', '16');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10021', '200000003', '54');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10029', '200000008', '27');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10016', '200000008', '13');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10028', '200000002', '77');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10016', '200000002', '80');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10009', '200000005', '18');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10019', '200000009', '26');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10007', '200000002', '76');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10015', '200000006', '81');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10010', '200000002', '60');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10000', '200000006', '23');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10016', '200000009', '69');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10002', '200000009', '93');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10029', '200000004', '15');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10015', '200000008', '06');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10026', '200000003', '75');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10008', '200000002', '98');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10020', '200000005', '99');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10026', '200000005', '20');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10010', '200000008', '58');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10025', '200000000', '81');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10002', '200000004', '97');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10025', '200000001', '84');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10008', '200000005', '85');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10007', '200000003', '33');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10026', '200000006', '21');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10024', '200000008', '13');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10023', '200000006', '64');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10006', '200000005', '60');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10019', '200000005', '39');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10014', '200000007', '95');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10011', '200000000', '01');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10013', '200000009', '33');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10027', '200000006', '85');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10025', '200000008', '73');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10018', '200000008', '04');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10004', '200000005', '64');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10029', '200000005', '26');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10001', '200000001', '57');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10019', '200000006', '88');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10010', '200000007', '70');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10003', '200000007', '70');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10015', '200000005', '61');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10026', '200000008', '49');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10020', '200000002', '95');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10002', '200000007', '02');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10025', '200000003', '25');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10009', '200000009', '18');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10000', '200000009', '52');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10021', '200000009', '21');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10012', '200000003', '85');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10014', '200000000', '38');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10003', '200000003', '82');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10007', '200000006', '47');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10009', '200000004', '06');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10013', '200000004', '23');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10011', '200000003', '68');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10001', '200000002', '03');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10028', '200000005', '15');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10029', '200000007', '88');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10003', '200000005', '83');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10016', '200000000', '99');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10014', '200000008', '10');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10018', '200000003', '45');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10015', '200000000', '45');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10024', '200000005', '32');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10012', '200000004', '31');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10022', '200000006', '27');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10009', '200000003', '75');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10013', '200000008', '86');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10002', '200000002', '01');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10012', '200000001', '00');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10027', '200000000', '48');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10023', '200000005', '47');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10021', '200000000', '94');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10016', '200000003', '42');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10008', '200000006', '97');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10006', '200000004', '85');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10008', '200000009', '19');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10028', '200000009', '95');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10023', '200000004', '52');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10000', '200000004', '15');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10014', '200000006', '44');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10002', '200000001', '08');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10019', '200000001', '16');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10018', '200000002', '99');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10000', '200000001', '79');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10007', '200000000', '58');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10029', '200000006', '85');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10004', '200000007', '62');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10015', '200000001', '25');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10007', '200000007', '24');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10020', '200000003', '47');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10011', '200000004', '78');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10025', '200000006', '24');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10022', '200000009', '32');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10011', '200000008', '90');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10002', '200000003', '58');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10008', '200000000', '00');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10019', '200000008', '09');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10023', '200000003', '95');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10025', '200000005', '58');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10005', '200000007', '14');
INSERT INTO "DEPARTMENTB"."COURSES_CHOSE" VALUES ('10008', '200000001', '48');

-- ----------------------------
-- Table structure for STUDENTS
-- ----------------------------
DROP TABLE "DEPARTMENTB"."STUDENTS";
CREATE TABLE "DEPARTMENTB"."STUDENTS" (
  "STUDENT_ID" VARCHAR2(9 BYTE) NOT NULL,
  "STUDENT_NAME" VARCHAR2(10 BYTE),
  "GENDER" VARCHAR2(2 BYTE),
  "MAJOR" VARCHAR2(16 BYTE),
  "PASSWORD" VARCHAR2(6 BYTE)
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of STUDENTS
-- ----------------------------
INSERT INTO "DEPARTMENTB"."STUDENTS" VALUES ('200000000', '吕安琪', '女', '軟件工程', 'cnTGCJ');
INSERT INTO "DEPARTMENTB"."STUDENTS" VALUES ('200000001', '徐國明', '男', '軟件工程', 'nrufxG');
INSERT INTO "DEPARTMENTB"."STUDENTS" VALUES ('200000002', '常致远', '男', '軟件工程', 'pz3Srf');
INSERT INTO "DEPARTMENTB"."STUDENTS" VALUES ('200000003', '曹裕玲', '女', '軟件工程', 'D2zrVH');
INSERT INTO "DEPARTMENTB"."STUDENTS" VALUES ('200000004', '田國榮', '男', '軟件工程', '1vEZel');
INSERT INTO "DEPARTMENTB"."STUDENTS" VALUES ('200000005', '罗璐', '女', '軟件工程', 'ISg3Wa');
INSERT INTO "DEPARTMENTB"."STUDENTS" VALUES ('200000006', '潘杰宏', '男', '軟件工程', 'tIZpYu');
INSERT INTO "DEPARTMENTB"."STUDENTS" VALUES ('200000007', '陈秀英', '女', '軟件工程', 'tq8H9v');
INSERT INTO "DEPARTMENTB"."STUDENTS" VALUES ('200000008', '雷致远', '男', '軟件工程', 'NSo8Fr');
INSERT INTO "DEPARTMENTB"."STUDENTS" VALUES ('200000009', '丁震南', '男', '軟件工程', 'W2IPDe');

-- ----------------------------
-- Primary Key structure for table ACCOUNT
-- ----------------------------
ALTER TABLE "DEPARTMENTB"."ACCOUNT" ADD CONSTRAINT "SYS_C0011048" PRIMARY KEY ("USERNAME");

-- ----------------------------
-- Primary Key structure for table COURSES
-- ----------------------------
ALTER TABLE "DEPARTMENTB"."COURSES" ADD CONSTRAINT "SYS_C0011102" PRIMARY KEY ("COURSE_ID");

-- ----------------------------
-- Primary Key structure for table COURSES_CHOSE
-- ----------------------------
ALTER TABLE "DEPARTMENTB"."COURSES_CHOSE" ADD CONSTRAINT "PK_COURSES_CHOSE" PRIMARY KEY ("COURSE_ID", "STUDENT_ID");

-- ----------------------------
-- Checks structure for table COURSES_CHOSE
-- ----------------------------
ALTER TABLE "DEPARTMENTB"."COURSES_CHOSE" ADD CONSTRAINT "SYS_C0011103" CHECK ("COURSE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "DEPARTMENTB"."COURSES_CHOSE" ADD CONSTRAINT "SYS_C0011104" CHECK ("STUDENT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table STUDENTS
-- ----------------------------
ALTER TABLE "DEPARTMENTB"."STUDENTS" ADD CONSTRAINT "SYS_C0011047" PRIMARY KEY ("STUDENT_ID");

-- ----------------------------
-- Foreign Keys structure for table ACCOUNT
-- ----------------------------
ALTER TABLE "DEPARTMENTB"."ACCOUNT" ADD CONSTRAINT "FK_OBJECT" FOREIGN KEY ("HANDLER_ID") REFERENCES "DEPARTMENTB"."STUDENTS" ("STUDENT_ID") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Foreign Keys structure for table COURSES_CHOSE
-- ----------------------------
ALTER TABLE "DEPARTMENTB"."COURSES_CHOSE" ADD CONSTRAINT "FK_COURSE_CHOSE_COURSES" FOREIGN KEY ("COURSE_ID") REFERENCES "DEPARTMENTB"."COURSES" ("COURSE_ID") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "DEPARTMENTB"."COURSES_CHOSE" ADD CONSTRAINT "FK_COURSE_CHOSE_STUDENTS" FOREIGN KEY ("STUDENT_ID") REFERENCES "DEPARTMENTB"."STUDENTS" ("STUDENT_ID") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
