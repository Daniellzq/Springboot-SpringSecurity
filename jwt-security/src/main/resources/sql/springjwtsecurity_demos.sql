CREATE DATABASE springjwtsecurity_demos;
USE springjwtsecurity_demos;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `role_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                       `description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'user', 'this is a user role.');
INSERT INTO `role` VALUES (2, 'admin', 'this is a admin role.');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                       `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                       `age` int(11) NULL DEFAULT NULL,
                       `address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                       `role_id` int(11) NOT NULL,
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'jsbintask', '123456', 22, 'China, Wuhan', 1);

SET FOREIGN_KEY_CHECKS = 1;