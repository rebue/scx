/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : oap

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 27/08/2021 16:59:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for OAP_APP
-- ----------------------------
DROP TABLE IF EXISTS `OAP_APP`;
CREATE TABLE `OAP_APP`  (
  `ID` bigint UNSIGNED NOT NULL COMMENT '主键',
  `APP_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'rac_app主键',
  `CLIENT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'oidc client id',
  `SECRET` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'oidc secret',
  `CREATE_TIMESTAMP` bigint UNSIGNED NOT NULL COMMENT '建立时间戳',
  `UPDATE_TIMESTAMP` bigint UNSIGNED NOT NULL COMMENT '修改时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `CLIENT_ID`(`CLIENT_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for OAP_GRANT
-- ----------------------------
DROP TABLE IF EXISTS `OAP_GRANT`;
CREATE TABLE `OAP_GRANT`  (
  `ID` bigint UNSIGNED NOT NULL COMMENT '主键',
  `ACCOUNT_ID` bigint UNSIGNED NULL DEFAULT NULL COMMENT 'rac_account主键',
  `ACCESS_TOKEN` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'oidc access token',
  `REFRESH_TOKEN` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'oidc refresh token',
  `EXPIRE_TIMESTAMP` bigint UNSIGNED NULL DEFAULT NULL COMMENT '过期时间',
  `CREATE_TIMESTAMP` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for OAP_IP_WHITE_LIST
-- ----------------------------
DROP TABLE IF EXISTS `OAP_IP_WHITE_LIST`;
CREATE TABLE `OAP_IP_WHITE_LIST`  (
  `ID` bigint UNSIGNED NOT NULL COMMENT '主键',
  `APP_ID` bigint UNSIGNED NOT NULL COMMENT 'OAP_APP主键',
  `IP_ADDR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '白名单IP',
  `CREATE_TIMESTAMP` bigint UNSIGNED NOT NULL COMMENT '建立时间戳',
  `UPDATE_TIMESTAMP` bigint UNSIGNED NOT NULL COMMENT '修改时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_IP_WHITE_LIST_APP_ID_REF_APP_ID`(`APP_ID`) USING BTREE,
  CONSTRAINT `FK_IP_WHITE_LIST_APP_ID_REF_APP_ID` FOREIGN KEY (`APP_ID`) REFERENCES `OAP_APP` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for OAP_REDIRECT_URI
-- ----------------------------
DROP TABLE IF EXISTS `OAP_REDIRECT_URI`;
CREATE TABLE `OAP_REDIRECT_URI`  (
  `ID` bigint UNSIGNED NOT NULL COMMENT '主键',
  `APP_ID` bigint UNSIGNED NOT NULL COMMENT 'OAP_APP主键',
  `REDIRECT_URI` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '允许的重定向URI, 最后一个字符可以是通配符*',
  `CREATE_TIMESTAMP` bigint UNSIGNED NOT NULL COMMENT '建立时间戳',
  `UPDATE_TIMESTAMP` bigint UNSIGNED NOT NULL COMMENT '修改时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_REDIRECT_URI_APP_ID_REF_APP_ID`(`APP_ID`) USING BTREE,
  CONSTRAINT `FK_REDIRECT_URI_APP_ID_REF_APP_ID` FOREIGN KEY (`APP_ID`) REFERENCES `OAP_APP` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
