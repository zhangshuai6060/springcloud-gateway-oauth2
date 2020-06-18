/*
 Navicat Premium Data Transfer


wwwwww

 Target Server Type    : MySQL
 Target Server Version : 100413
 File Encoding         : 65001

 Date: 15/06/2020 08:55:47
*/

CREATE DATABASE oauth2 charset=utf8;
use oauth2;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', NULL, '$2a$10$rfjh0xBSbAih956yoisDFOFKVNjJzByET0ShRNzVs9M9oqQQlvjki', 'app', 'authorization_code,password,refresh_token,client_credentials,mobile,openid', 'http://www.baidu.com', NULL, NULL, NULL, NULL, 'true');

-- ----------------------------
-- Table structure for sys_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_user`;
CREATE TABLE `sys_auth_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `password` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `mobile_phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `state` tinyint(4) NOT NULL COMMENT '用户状态1.启用2.禁用',
  `open_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信openid\n',
  `expire_time` datetime(0) DEFAULT NULL COMMENT '过期时间',
  `create_by` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `last_modify_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '上次修改人id',
  `last_modify_time` datetime(0) NOT NULL DEFAULT current_timestamp() COMMENT '上次修改时间',
  `logical_deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '逻辑删除1.正常2.已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_auth_user
-- ----------------------------
INSERT INTO `sys_auth_user` VALUES (1, 'admin', '$2a$10$rfjh0xBSbAih956yoisDFOFKVNjJzByET0ShRNzVs9M9oqQQlvjki', '11111111112', 1, '', '2020-06-27 10:14:50', 1, '2020-05-29 15:03:08', 2, '2020-06-05 10:37:48', 1);
INSERT INTO `sys_auth_user` VALUES (2, '张三', '456', '11111111111', 1, '', NULL, 1, '2020-06-01 10:49:01', 1, '2020-06-01 10:49:01', 1);
INSERT INTO `sys_auth_user` VALUES (3, '张无忌', '111', '11111111111', 1, '', NULL, 1, '2020-06-02 10:08:28', 1, '2020-06-02 10:08:28', 1);
INSERT INTO `sys_auth_user` VALUES (28, '2', '$2a$10$lNdX.LEcG6efs3syvwuBluor33JE.JO0UZPHT0SPwLx86sESnpuvG', '', 1, '', NULL, 2, '2020-06-09 08:58:33', 2, '2020-06-09 08:58:33', 1);
INSERT INTO `sys_auth_user` VALUES (29, '2', '$2a$10$6g8nKbg5rsMMFFPbWfC4Ru8nFyqqirP9lFot/J0oV6OjwgL49LDv.', '', 1, '555', NULL, 2, '2020-06-09 09:02:02', 2, '2020-06-09 09:02:02', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单或者按钮名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `type` tinyint(4) UNSIGNED NOT NULL COMMENT '类型1.菜单2.按钮3.元素',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '按钮显示code',
  `parent_id` bigint(20) NOT NULL COMMENT '父级菜单id',
  `is_system_menu` tinyint(4) UNSIGNED NOT NULL COMMENT '是否是系统菜单1.是2.不是(业务菜单)',
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限',
  `sort` tinyint(4) NOT NULL COMMENT '排序',
  `create_by` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `last_modify_by` bigint(20) NOT NULL COMMENT '上次修改人id',
  `last_modify_time` datetime(0) NOT NULL DEFAULT current_timestamp() COMMENT '上次修改时间',
  `logical_deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '逻辑删除1.正常2.已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '标题1', '/1', '1', 1, '1', 0, 1, '1', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (2, '1', '/2', '2', 1, '1', 0, 1, '2', 0, 1, '2020-06-03 17:30:18', 2, '2020-06-05 11:04:33', 1);
INSERT INTO `sys_menu` VALUES (3, '标题3', '/3', '3', 1, '1', 0, 1, '3', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (4, '1标题4', '/4', '4', 1, '1', 1, 1, '4', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (5, '1标题5', '/5', '4', 1, '1', 1, 1, '5', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (6, '1标题6', '/6', '4', 1, '1', 1, 1, '6', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (7, '1标题7', '/7', '4', 1, '1', 1, 1, '7', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (8, '2标题8', '/8', '4', 1, '1', 2, 1, '8', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (9, '2标题9', '/8', '4', 1, '1', 2, 1, '9', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (10, '2标题10', '/8', '4', 1, '1', 2, 1, '10', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (11, '4标题11', '/8', '4', 1, '1', 4, 1, '11', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (12, '4标题12', '/8', '4', 1, '1', 4, 1, '12', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (13, '4标题13', '/8', '4', 1, '1', 4, 1, '13', 0, 1, '2020-06-03 17:30:18', 1, '2020-06-03 17:30:18', 1);
INSERT INTO `sys_menu` VALUES (14, '1', '开发1', '0', 1, '', 1, 1, '14', 1, 2, '2020-06-05 10:59:42', 2, '2020-06-05 10:59:42', 1);
INSERT INTO `sys_menu` VALUES (15, '1', '开发1', '0', 1, '', 1, 1, '15', 1, 2, '2020-06-05 11:03:55', 2, '2020-06-05 11:03:55', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门简述',
  `state` tinyint(4) UNSIGNED NOT NULL COMMENT '角色状态1.启用2.禁用',
  `type` tinyint(4) DEFAULT NULL COMMENT '1.后台2.前台',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT current_timestamp() COMMENT '创建时间',
  `last_modify_by` bigint(20) DEFAULT NULL COMMENT '上次修改人id',
  `last_modify_time` datetime(0) DEFAULT current_timestamp() COMMENT '上次修改时间',
  `logical_deleted` tinyint(4) DEFAULT 1 COMMENT '逻辑删除1.正常2.已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '客服', '角色1描述', 1, 1, 2, '2020-06-03 16:12:34', 2, '2020-06-03 16:12:31', 2);
INSERT INTO `sys_role` VALUES (2, '销售', '角色2的描述', 1, 1, 2, '2020-06-03 16:13:49', NULL, '2020-06-03 16:13:45', 1);
INSERT INTO `sys_role` VALUES (3, '角色3', '角色3的描述', 1, 1, 2, '2020-06-03 16:13:57', 2, '2020-06-03 16:13:54', 2);
INSERT INTO `sys_role` VALUES (4, '角色4', '角色4的描述', 1, 1, 2, '2020-06-03 16:14:22', NULL, '2020-06-03 16:14:19', 1);
INSERT INTO `sys_role` VALUES (7, '角色6', '角色6的描述', 1, 1, 2, '2020-06-03 16:17:27', NULL, '2020-06-03 16:17:24', 1);
INSERT INTO `sys_role` VALUES (8, '角色5', '角色5的描述', 1, 1, 2, '2020-06-03 16:17:48', NULL, '2020-06-03 16:17:45', 1);
INSERT INTO `sys_role` VALUES (9, '固定角色1', '固定角色1的描述', 1, 1, 2, '2020-06-03 16:41:43', NULL, '2020-06-03 16:42:00', 1);
INSERT INTO `sys_role` VALUES (10, '固定角色2', '固定角色2的描述', 1, 1, 2, '2020-06-03 16:41:47', NULL, '2020-06-03 16:42:03', 1);
INSERT INTO `sys_role` VALUES (11, '固定角色3', '固定角色3的描述', 1, 1, 2, '2020-06-03 16:41:54', NULL, '2020-06-03 16:42:05', 1);
INSERT INTO `sys_role` VALUES (12, '固定角色4', '固定角色4的描述', 1, 1, 2, '2020-06-03 16:41:57', NULL, '2020-06-03 16:42:08', 1);
INSERT INTO `sys_role` VALUES (13, '业务角色1', '业务角色1描述', 1, 1, 2, '2020-06-05 15:27:04', 2, '2020-06-05 15:27:04', 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) UNSIGNED NOT NULL COMMENT '菜单或者按钮id',
  `create_by` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `last_modify_by` bigint(20) NOT NULL COMMENT '上次修改人id',
  `last_modify_time` datetime(0) NOT NULL DEFAULT current_timestamp() COMMENT '上次修改时间',
  `logical_deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '逻辑删除1.正常2.已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (16, 1, 1, 2, '2020-06-10 17:08:12', 2, '2020-06-10 17:08:12', 1);
INSERT INTO `sys_role_menu` VALUES (17, 1, 2, 2, '2020-06-10 17:08:12', 2, '2020-06-10 17:08:12', 1);
INSERT INTO `sys_role_menu` VALUES (18, 1, 3, 2, '2020-06-10 17:08:12', 2, '2020-06-10 17:08:12', 1);
INSERT INTO `sys_role_menu` VALUES (19, 1, 4, 2, '2020-06-10 17:08:12', 2, '2020-06-10 17:08:12', 1);
INSERT INTO `sys_role_menu` VALUES (20, 7, 1, 2, '2020-06-10 17:34:39', 2, '2020-06-10 17:34:39', 1);
INSERT INTO `sys_role_menu` VALUES (21, 7, 2, 2, '2020-06-10 17:34:39', 2, '2020-06-10 17:34:39', 1);
INSERT INTO `sys_role_menu` VALUES (22, 7, 3, 2, '2020-06-10 17:34:39', 2, '2020-06-10 17:34:39', 1);
INSERT INTO `sys_role_menu` VALUES (23, 7, 4, 2, '2020-06-10 17:34:39', 2, '2020-06-10 17:34:39', 1);
INSERT INTO `sys_role_menu` VALUES (24, 4, 1, 2, '2020-06-10 17:36:15', 2, '2020-06-10 17:36:15', 1);
INSERT INTO `sys_role_menu` VALUES (25, 4, 2, 2, '2020-06-10 17:36:15', 2, '2020-06-10 17:36:15', 1);
INSERT INTO `sys_role_menu` VALUES (26, 4, 3, 2, '2020-06-10 17:36:15', 2, '2020-06-10 17:36:15', 1);
INSERT INTO `sys_role_menu` VALUES (27, 4, 4, 2, '2020-06-10 17:36:15', 2, '2020-06-10 17:36:15', 1);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  `auth_user_id` bigint(20) UNSIGNED NOT NULL COMMENT '员工id',
  `create_by` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT current_timestamp() COMMENT '创建时间',
  `last_modify_by` bigint(20) DEFAULT NULL COMMENT '上次修改人id',
  `last_modify_time` datetime(0) NOT NULL DEFAULT current_timestamp() COMMENT '上次修改时间',
  `logical_deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '逻辑删除1.正常2.已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (2, 2, 2, 1, '2020-06-04 10:44:54', 1, '2020-06-04 10:45:00', 1);
INSERT INTO `sys_role_user` VALUES (4, 2, 3, 1, '2020-06-04 10:44:54', 1, '2020-06-04 10:45:00', 1);
INSERT INTO `sys_role_user` VALUES (5, 1, 3, 1, '2020-06-04 10:44:54', 1, '2020-06-04 10:45:00', 1);
INSERT INTO `sys_role_user` VALUES (6, 2, 1, 1, '2020-06-04 10:44:54', 1, '2020-06-04 10:45:00', 1);
INSERT INTO `sys_role_user` VALUES (8, 4, 1, 1, '2020-06-04 10:44:54', 1, '2020-06-04 10:45:00', 1);
INSERT INTO `sys_role_user` VALUES (14, 1, 1, 2, '2020-06-05 15:55:58', 2, '2020-06-05 15:55:58', 1);
INSERT INTO `sys_role_user` VALUES (15, 1, 2, 2, '2020-06-05 15:55:58', 2, '2020-06-05 15:55:58', 1);
INSERT INTO `sys_role_user` VALUES (16, 7, 1, 2, '2020-06-10 17:33:09', 2, '2020-06-10 17:33:09', 1);
INSERT INTO `sys_role_user` VALUES (17, 7, 24, 2, '2020-06-10 17:33:09', 2, '2020-06-10 17:33:09', 1);
INSERT INTO `sys_role_user` VALUES (18, 4, 24, 2, '2020-06-10 17:36:39', 2, '2020-06-10 17:36:39', 1);

SET FOREIGN_KEY_CHECKS = 1;
