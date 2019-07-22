/*
 Navicat Premium Data Transfer

 Source Server         : MySQL80
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : school

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 22/07/2019 21:05:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '年级，如2015级',
  `class_number` int(11) NOT NULL COMMENT '班级，如1班，2班',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (6, '2019', 1);
INSERT INTO `class` VALUES (8, '2015', 3);
INSERT INTO `class` VALUES (13, '2015', 1);
INSERT INTO `class` VALUES (14, '2015', 2);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `teacher` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程教师',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'C语言程序设计', '1');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `student_id` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `score` int(11) NOT NULL COMMENT ' 成绩',
  PRIMARY KEY (`course_id`, `student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `class_id` int(11) NULL DEFAULT NULL COMMENT '学生所属班级，外键所引导class表的id字段',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student`(`class_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('151220071', '李四', '男', 6);
INSERT INTO `student` VALUES ('151220077', '小芳', '女', 14);
INSERT INTO `student` VALUES ('151220085', '张三', '男', 8);
INSERT INTO `student` VALUES ('151220101', '张佳佳', '女', 13);
INSERT INTO `student` VALUES ('151220102', '李俊杰', '男', 13);
INSERT INTO `student` VALUES ('151220104', '赵娜', '女', 13);
INSERT INTO `student` VALUES ('151220112', '王强', '男', 13);
INSERT INTO `student` VALUES ('151221155', '王五', '男', 13);
INSERT INTO `student` VALUES ('151332211', '李丽丽', '女', 13);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '老师编号',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '老师姓名',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('T08110175', '孙悦', '女');
INSERT INTO `teacher` VALUES ('T09120016', '李强', '男');
INSERT INTO `teacher` VALUES ('T09120019', '赵丽', '女');
INSERT INTO `teacher` VALUES ('T09120025', '王秀秀', '女');
INSERT INTO `teacher` VALUES ('T10000011', '王羽', '男');
INSERT INTO `teacher` VALUES ('T13121215', '吴军', '男');
INSERT INTO `teacher` VALUES ('T15120018', '张健', '男');
INSERT INTO `teacher` VALUES ('T16112255', '赵静', '女');

-- ----------------------------
-- Table structure for teacher_class
-- ----------------------------
DROP TABLE IF EXISTS `teacher_class`;
CREATE TABLE `teacher_class`  (
  `teacher_id` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '老师编号',
  `class_id` int(11) NOT NULL COMMENT '班级编号',
  `advisor` int(1) NOT NULL COMMENT '是否为班主任，1代表是班主任，2代表是普通任课老师',
  PRIMARY KEY (`teacher_id`, `class_id`, `advisor`) USING BTREE,
  INDEX `fk_class`(`class_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_class
-- ----------------------------
INSERT INTO `teacher_class` VALUES ('T08110175', 8, 1);
INSERT INTO `teacher_class` VALUES ('T09120025', 8, 0);
INSERT INTO `teacher_class` VALUES ('T09120019', 13, 1);
INSERT INTO `teacher_class` VALUES ('T09120025', 13, 0);
INSERT INTO `teacher_class` VALUES ('T09120016', 14, 1);
INSERT INTO `teacher_class` VALUES ('T09120019', 14, 0);

SET FOREIGN_KEY_CHECKS = 1;
