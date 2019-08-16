/*
 Navicat Premium Data Transfer

 Source Server         : arthas
 Source Server Type    : MySQL
 Source Server Version : 100137
 Source Host           : localhost:3306
 Source Schema         : refuse_classification

 Target Server Type    : MySQL
 Target Server Version : 100137
 File Encoding         : 65001

 Date: 14/08/2019 00:27:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for garbage
-- ----------------------------
DROP TABLE IF EXISTS `garbage`;
CREATE TABLE `garbage`  (
  `gname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `variety` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `handle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `garbage_gname_uindex`(`gname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of garbage
-- ----------------------------
INSERT INTO `garbage` VALUES ('头发', '干垃圾', '人体产生的无用组织,既不属于可回收物和湿垃圾,也不属于有害垃圾,是一种生活废弃物,属于干垃圾', '处理时尽量使其不带有水分');
INSERT INTO `garbage` VALUES ('塑料瓶', '可回收物', '可回收', '清空压扁投放');
INSERT INTO `garbage` VALUES ('干头发', '干垃圾', '生活废弃物,属于干垃圾', '无需处理即可投放');
INSERT INTO `garbage` VALUES ('易拉罐', '可回收物', '金属制品,可回收再利用', '清空内容物,清理干净,压扁投放,若有棱角将棱角磨平.');
INSERT INTO `garbage` VALUES ('香蕉皮', '湿垃圾', '湿垃圾', '湿垃圾`');
INSERT INTO `garbage` VALUES ('果核', '湿垃圾', '湿垃圾', '湿垃圾');
INSERT INTO `garbage` VALUES ('旧书', '可回收物', '可回收物', '可回收物');
INSERT INTO `garbage` VALUES ('矿泉水瓶', '可回收物', '可回收物', '可回收物');
INSERT INTO `garbage` VALUES ('餐巾纸', '干垃圾', '干垃圾', '干垃圾');
INSERT INTO `garbage` VALUES ('包装袋', '干垃圾', '干垃圾', '干垃圾');
INSERT INTO `garbage` VALUES ('餐盒', '干垃圾', '干垃圾', '干垃圾');
INSERT INTO `garbage` VALUES ('剩菜', '湿垃圾', '湿垃圾', '湿垃圾');
INSERT INTO `garbage` VALUES ('茄子', '湿垃圾', '湿垃圾', '湿垃圾');
INSERT INTO `garbage` VALUES ('饮料', '湿垃圾', '湿垃圾', '湿垃圾');
INSERT INTO `garbage` VALUES ('奶茶', '湿垃圾', '湿垃圾', '湿垃圾');
INSERT INTO `garbage` VALUES ('电脑', '可回收物', '可回收物', '可回收物');
INSERT INTO `garbage` VALUES ('废金属', '可回收物', '可回收物', '可回收物');
INSERT INTO `garbage` VALUES ('电脑配件', '可回收物', '可回收物', '可回收物');

-- ----------------------------
-- Table structure for test_login
-- ----------------------------
DROP TABLE IF EXISTS `test_login`;
CREATE TABLE `test_login`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test_login
-- ----------------------------
INSERT INTO `test_login` VALUES ('1', 'root', '367494');
INSERT INTO `test_login` VALUES ('2', 'wbb', '271637');
INSERT INTO `test_login` VALUES ('3', 'Tom', '123456');

SET FOREIGN_KEY_CHECKS = 1;
