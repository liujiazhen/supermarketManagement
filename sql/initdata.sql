/*
 Navicat Premium Data Transfer

 Source Server         : forMy
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : billmannagement

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 11/04/2020 14:08:27
*/
-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '员工');
INSERT INTO `t_role` VALUES (2, '财务');
INSERT INTO `t_role` VALUES (3, '领导');
-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, NULL, '账单管理', 'billmanagement');
INSERT INTO `t_menu` VALUES (2, NULL, '历史查询', 'historicalquery');
INSERT INTO `t_menu` VALUES (3, NULL, '审核管理', 'billmanagementtop');
INSERT INTO `t_menu` VALUES (4, NULL, '审批管理', 'approvalmanagement');

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1, 1);
INSERT INTO `t_role_menu` VALUES (2, 2, 1);
INSERT INTO `t_role_menu` VALUES (3, 3, 2);
INSERT INTO `t_role_menu` VALUES (4, 4, 3);

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1, 1);
INSERT INTO `t_role_permission` VALUES (2, 2, 2);
INSERT INTO `t_role_permission` VALUES (3, 3, 3);


-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '18', '$2a$10$2FcgAaE6GyD8t7DXhLo4bea6bZIbgKCCuVO9rIuJ5ZECOViIgTy1S', NULL, 'xiaoli');
INSERT INTO `t_user` VALUES (2, '19', '$2a$10$2FcgAaE6GyD8t7DXhLo4bea6bZIbgKCCuVO9rIuJ5ZECOViIgTy1S', NULL, 'caiwu');
INSERT INTO `t_user` VALUES (3, '20', '$2a$10$2FcgAaE6GyD8t7DXhLo4bea6bZIbgKCCuVO9rIuJ5ZECOViIgTy1S', NULL, 'laoban');
