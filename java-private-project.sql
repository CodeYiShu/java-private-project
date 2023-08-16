/*
 Navicat Premium Data Transfer

 Source Server         : 本机MySQL8.0
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : java-private-project

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 16/08/2023 15:33:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for for_sql_warning_dept
-- ----------------------------
DROP TABLE IF EXISTS `for_sql_warning_dept`;
CREATE TABLE `for_sql_warning_dept`  (
  `id` bigint NOT NULL,
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for for_sql_warning_post
-- ----------------------------
DROP TABLE IF EXISTS `for_sql_warning_post`;
CREATE TABLE `for_sql_warning_post`  (
  `id` bigint NOT NULL,
  `post_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for for_sql_warning_user
-- ----------------------------
DROP TABLE IF EXISTS `for_sql_warning_user`;
CREATE TABLE `for_sql_warning_user`  (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `dept_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for for_sql_warning_user_post
-- ----------------------------
DROP TABLE IF EXISTS `for_sql_warning_user_post`;
CREATE TABLE `for_sql_warning_user_post`  (
  `id` bigint NOT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `post_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for interview_spring_account
-- ----------------------------
DROP TABLE IF EXISTS `interview_spring_account`;
CREATE TABLE `interview_spring_account`  (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `money` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for left_join_plan
-- ----------------------------
DROP TABLE IF EXISTS `left_join_plan`;
CREATE TABLE `left_join_plan`  (
  `id` bigint NOT NULL COMMENT '主键',
  `plan_type` int NULL DEFAULT NULL COMMENT '巡检类型,1、日 2、周 3、月 4、季度 5、年 6、单次',
  `plan_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计划名称',
  `issue_time` datetime NULL DEFAULT NULL COMMENT '触发时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '巡检计划' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for left_join_plan_task
-- ----------------------------
DROP TABLE IF EXISTS `left_join_plan_task`;
CREATE TABLE `left_join_plan_task`  (
  `id` bigint NOT NULL COMMENT '主键',
  `plan_id` bigint NULL DEFAULT NULL COMMENT '巡检计划id',
  `task_id` bigint NULL DEFAULT NULL COMMENT '巡检任务id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `detection_task_detection_plan_task_fk`(`task_id` ASC) USING BTREE,
  INDEX `detection_plan_detection_plan_task_fk`(`plan_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '巡检计划任务关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for left_join_task
-- ----------------------------
DROP TABLE IF EXISTS `left_join_task`;
CREATE TABLE `left_join_task`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '巡检任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mybatis_aspect_product
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_aspect_product`;
CREATE TABLE `mybatis_aspect_product`  (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `project_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mybatis_aspect_project
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_aspect_project`;
CREATE TABLE `mybatis_aspect_project`  (
  `id` bigint NOT NULL,
  `project_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mybatis_aspect_user
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_aspect_user`;
CREATE TABLE `mybatis_aspect_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mybatis_aspect_user_project
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_aspect_user_project`;
CREATE TABLE `mybatis_aspect_user_project`  (
  `id` bigint NOT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `project_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mybatis_batch_user
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_batch_user`;
CREATE TABLE `mybatis_batch_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mybatis_mapper_class
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_mapper_class`;
CREATE TABLE `mybatis_mapper_class`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教室名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = 'Mybatis的映射技巧' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mybatis_mapper_class_student
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_mapper_class_student`;
CREATE TABLE `mybatis_mapper_class_student`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `class_id` bigint NULL DEFAULT NULL COMMENT '教室id',
  `student_id` bigint NULL DEFAULT NULL COMMENT '学生id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = 'Mybatis的映射技巧' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mybatis_mapper_student
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_mapper_student`;
CREATE TABLE `mybatis_mapper_student`  (
  `id` bigint NOT NULL,
  `student_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '学生名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = 'Mybatis的映射技巧' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mysql_count_project_shop_activity
-- ----------------------------
DROP TABLE IF EXISTS `mysql_count_project_shop_activity`;
CREATE TABLE `mysql_count_project_shop_activity`  (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '门店名字',
  `type` int NULL DEFAULT NULL COMMENT '1-打折活动 2-促销活动 3-抢购活动 4-秒杀活动 5-普通活动',
  `open_time` datetime NULL DEFAULT NULL COMMENT '开办时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
