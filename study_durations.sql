/*
 Navicat Premium Data Transfer

 Source Server         : First_Connect
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : study_durations

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 05/01/2022 17:01:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for durations
-- ----------------------------
DROP TABLE IF EXISTS `durations`;
CREATE TABLE `durations`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `active` double NOT NULL COMMENT '活动时长',
  `ideal` double NOT NULL COMMENT '理想信念',
  `recreation` double NOT NULL COMMENT '文体时长',
  `innovate` double NOT NULL COMMENT '创新创业',
  `wish` double NOT NULL COMMENT '志愿时长',
  `total` double NOT NULL COMMENT '时长总计',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `durations_ibfk_1` FOREIGN KEY (`id`) REFERENCES `students` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '时长记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of durations
-- ----------------------------
INSERT INTO `durations` VALUES ('1639967281', 30, 20, 10, 40, 20, 120);
INSERT INTO `durations` VALUES ('1639967282', 40, 25, 15, 15, 35, 130);

-- ----------------------------
-- Table structure for majors
-- ----------------------------
DROP TABLE IF EXISTS `majors`;
CREATE TABLE `majors`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业名称',
  `college` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属学院',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '专业信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of majors
-- ----------------------------
INSERT INTO `majors` VALUES ('1639967280', '系统测试', '软件工程');
INSERT INTO `majors` VALUES ('1639967281', '软件开发', '软件工程');
INSERT INTO `majors` VALUES ('1641361575250', '计算机科学与技术', '计算机学院');
INSERT INTO `majors` VALUES ('1641361602138', '英语', '外国语学院');
INSERT INTO `majors` VALUES ('1641361611130', '政治', '马克思主义学院');
INSERT INTO `majors` VALUES ('1641361628238', '大数据', '自动化学院');
INSERT INTO `majors` VALUES ('1641361648458', '物理', '光电物理工程学院');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `grade` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属班级',
  `major_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所学专业',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `major_id`(`major_id`) USING BTREE,
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `students_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `majors` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('1639967281', '一年级一班', '1639967280');
INSERT INTO `students` VALUES ('1639967282', '一年级一班', '1639967280');
INSERT INTO `students` VALUES ('1641300861551', '一年级一班', '1639967281');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `pass_word` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户性别',
  `age` int NOT NULL COMMENT '用户年龄',
  `type` int NOT NULL COMMENT '用户身份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1639967280', 'admin', 'admin', '张三丰', '男', 45, 0);
INSERT INTO `users` VALUES ('1639967281', 'zhang', 'zhang', '张无忌', '男', 25, 1);
INSERT INTO `users` VALUES ('1639967282', 'zhao', 'zhao', '赵敏', '女', 23, 1);
INSERT INTO `users` VALUES ('1641300815795', 'sdwsdw', 'dsdwdwdwed', '大哥', '男', 32, 0);
INSERT INTO `users` VALUES ('1641300861551', '28374837432', '2u88283423', '呵呵哒', '男', 18, 0);
INSERT INTO `users` VALUES ('1641367790105', '3119005419', '3119005419', '黎余明', '男', 21, 0);
INSERT INTO `users` VALUES ('1641367833015', '3119005424', '3119005424', '刘煜', '男', 20, 0);

SET FOREIGN_KEY_CHECKS = 1;
