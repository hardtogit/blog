/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2015-12-10 22:22:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bi_comment
-- ----------------------------
DROP TABLE IF EXISTS `bi_comment`;
CREATE TABLE `bi_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `comment_author` varchar(45) DEFAULT NULL,
  `comment_author_email` varchar(100) DEFAULT NULL,
  `comment_author_url` varchar(200) DEFAULT NULL,
  `comment_author_ip` varchar(100) DEFAULT NULL,
  `comment_date` datetime DEFAULT NULL,
  `comment_content` text,
  `comment_agent` varchar(255) DEFAULT NULL,
  `comment_type` varchar(45) DEFAULT NULL COMMENT '评论类型',
  `comment_parent` int(11) DEFAULT NULL COMMENT '父级',
  `user_id` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_bi_comment_bi_post1_idx` (`post_id`),
  CONSTRAINT `fk_bi_comment_bi_post1` FOREIGN KEY (`post_id`) REFERENCES `bi_post` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Records of bi_comment
-- ----------------------------

-- ----------------------------
-- Table structure for bi_post
-- ----------------------------
DROP TABLE IF EXISTS `bi_post`;
CREATE TABLE `bi_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_author` int(11) DEFAULT NULL COMMENT '作者',
  `post_date` datetime DEFAULT NULL COMMENT '投递日期',
  `post_content` longtext COMMENT '内容',
  `post_title` text COMMENT '标题',
  `post_status` varchar(45) DEFAULT 'publish' COMMENT '状态',
  `comment_status` varchar(45) DEFAULT 'open' COMMENT '评论状态',
  `post_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `post_parent` int(11) DEFAULT NULL COMMENT '父级',
  `post_type` varchar(45) DEFAULT 'post' COMMENT '内容类型',
  `post_mime_type` varchar(45) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL COMMENT '评论数',
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';

-- ----------------------------
-- Records of bi_post
-- ----------------------------

-- ----------------------------
-- Table structure for bi_post_term
-- ----------------------------
DROP TABLE IF EXISTS `bi_post_term`;
CREATE TABLE `bi_post_term` (
  `post_id` int(11) NOT NULL,
  `term_id` varchar(45) NOT NULL,
  PRIMARY KEY (`post_id`,`term_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章词汇关系表';

-- ----------------------------
-- Records of bi_post_term
-- ----------------------------
INSERT INTO `bi_post_term` VALUES ('1', '1');
INSERT INTO `bi_post_term` VALUES ('1', '19');
INSERT INTO `bi_post_term` VALUES ('1', '20');
INSERT INTO `bi_post_term` VALUES ('3', '1');
INSERT INTO `bi_post_term` VALUES ('3', '20');
INSERT INTO `bi_post_term` VALUES ('3', '7');
INSERT INTO `bi_post_term` VALUES ('4', '16');
INSERT INTO `bi_post_term` VALUES ('4', '17');
INSERT INTO `bi_post_term` VALUES ('4', '18');
INSERT INTO `bi_post_term` VALUES ('4', '20');

-- ----------------------------
-- Table structure for bi_term
-- ----------------------------
DROP TABLE IF EXISTS `bi_term`;
CREATE TABLE `bi_term` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `slug` varchar(100) DEFAULT NULL COMMENT '描述',
  `type` varchar(255) NOT NULL COMMENT '类型',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级',
  `parent_ids` varchar(255) DEFAULT NULL COMMENT '所有父类',
  `del_flag` int(11) NOT NULL COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='词汇表';

-- ----------------------------
-- Records of bi_term
-- ----------------------------
INSERT INTO `bi_term` VALUES ('1', 'nih', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('2', '今天', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('3', 'whah', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('4', 'nihao', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('5', '嗯嗯', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('6', '明天', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('7', '你好', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('8', 'hhaha', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('9', 'wo hel', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('10', '1', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('11', '名称', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('12', '民田', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('13', '我', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('14', 'hah', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('15', '新增', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('16', '测试', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('17', '什么情况', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('18', 'aaa', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('19', '起点', null, 'post_tag', null, '0,', '0');
INSERT INTO `bi_term` VALUES ('20', '私密', null, 'post_tag', null, '0,', '0');

-- ----------------------------
-- Table structure for sys_activity
-- ----------------------------
DROP TABLE IF EXISTS `sys_activity`;
CREATE TABLE `sys_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(45) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '内容',
  `from_id` int(11) DEFAULT NULL COMMENT '发送者',
  `to_id` int(11) DEFAULT NULL COMMENT '接收者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `course_id` int(11) DEFAULT NULL COMMENT '赛程id',
  `is_read` tinyint(4) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态表';

-- ----------------------------
-- Records of sys_activity
-- ----------------------------

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content_type` varchar(255) NOT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_size` int(11) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` int(11) NOT NULL COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '是', '1', 'yes_no', '', '1', null, '0');
INSERT INTO `sys_dict` VALUES ('2', '否', '0', 'yes_no', '', '2', null, '0');
INSERT INTO `sys_dict` VALUES ('3', '正常', '0', 'del_flag_type', '数据状态类型', '1', null, '0');
INSERT INTO `sys_dict` VALUES ('4', '失效', '1', 'del_flag_type', '数据状态类型', '2', null, '0');
INSERT INTO `sys_dict` VALUES ('5', '锁定', '2', 'del_flag_type', '数据状态类型', '3', null, '0');
INSERT INTO `sys_dict` VALUES ('6', '草稿', 'draft', 'post_status', '文章状态', '1', '', '0');
INSERT INTO `sys_dict` VALUES ('7', '待审核', 'pending', 'post_status', '文章状态', '2', '', '0');
INSERT INTO `sys_dict` VALUES ('8', '发布', 'publish', 'post_status', '文章状态', '3', '', '0');
INSERT INTO `sys_dict` VALUES ('9', '允许', 'open', 'comment_status', '', '1', '', '0');
INSERT INTO `sys_dict` VALUES ('10', '不允许', 'close', 'comment_status', '', '2', '', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL COMMENT '日志类型（1：接入日志；2：异常日志）',
  `create_by` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(255) DEFAULT NULL COMMENT '操作方式',
  `params` longtext COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_date`),
  KEY `FK_5yqmukedkuti9qdm38cgn5qpo` (`create_by`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_out_1.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('2', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_in_0.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('3', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_in_2.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('4', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_out_0.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('5', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_in_1.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('6', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_in_3.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('7', '2', '1', '2015-12-02 14:18:05', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_out_3.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('8', '2', '1', '2015-12-02 14:18:05', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_scanner_.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('9', '2', '1', '2015-12-02 14:18:05', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_out_2.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('10', '2', '1', '2015-12-02 14:18:37', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_out_1.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('11', '1', '2', '2015-12-03 13:57:21', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/login', 'POST', 'password=&username=ju', '', '0');
INSERT INTO `sys_log` VALUES ('12', '1', '2', '2015-12-03 13:57:23', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/login', 'POST', 'password=&username=ju', '', '0');
INSERT INTO `sys_log` VALUES ('13', '1', '2', '2015-12-03 13:57:24', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/login', 'POST', 'password=&username=ju', '', '0');
INSERT INTO `sys_log` VALUES ('14', '2', '2', '2015-12-03 14:34:09', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/bill/d', 'GET', 'no=201512020000983', 'org.apache.tiles.impl.CannotRenderException: JSPException including path \'/WEB-INF/views/bill/bill.jsp\'.', '0');
INSERT INTO `sys_log` VALUES ('15', '2', '2', '2015-12-03 14:58:07', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/bill/d', 'GET', 'no=201512020000983', 'org.apache.tiles.impl.CannotRenderException: JSPException including path \'/WEB-INF/views/bill/bill.jsp\'.', '0');
INSERT INTO `sys_log` VALUES ('16', '1', '1', '2015-12-03 15:22:42', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/user/save', 'POST', 'id=&confirmPassword=&phone=&email=luodun@qq.com&name=&plainPassword=&avatar=&password=&salt=&loginName=luodun&delFlag=0', '', '0');
INSERT INTO `sys_log` VALUES ('17', '2', '1', '2015-12-03 15:26:24', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/admin/user/form', 'GET', 'id=2', 'org.apache.tiles.impl.CannotRenderException: JSPException including path \'/WEB-INF/views/admin/user-form.jsp\'.', '0');
INSERT INTO `sys_log` VALUES ('18', '2', '1', '2015-12-03 15:26:46', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/admin/user/form', 'GET', 'id=2', 'org.apache.tiles.impl.CannotRenderException: JSPException including path \'/WEB-INF/views/admin/user-form.jsp\'.', '0');
INSERT INTO `sys_log` VALUES ('19', '1', '1', '2015-12-03 15:28:15', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/user/save', 'POST', 'id=2&phone=&email=thon@gmail.com&name=鞠学见&role.id=1&avatar=&password=&salt=839811171b82c106&loginName=ju&delFlag=0', '', '0');
INSERT INTO `sys_log` VALUES ('20', '1', '3', '2015-12-03 15:29:46', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/login', 'POST', 'password=&username=thon', '', '0');
INSERT INTO `sys_log` VALUES ('21', '1', '3', '2015-12-03 15:29:50', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/login', 'POST', 'password=&username=thon', '', '0');
INSERT INTO `sys_log` VALUES ('22', '2', '3', '2015-12-03 15:29:52', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/login;JSESSIONID=4f41de8a-32ca-4633-b198-12906b69fc66', 'GET', '', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('23', '2', '3', '2015-12-03 15:30:12', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/login;JSESSIONID=4f41de8a-32ca-4633-b198-12906b69fc66', 'GET', '', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('24', '1', '1', '2015-12-03 15:30:31', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/user/save', 'POST', 'id=3&phone=&email=luodun@qq.com&name=luodun&role.id=3&avatar=&password=&salt=2b50ddadd099262c&loginName=luodun&delFlag=0', '', '0');
INSERT INTO `sys_log` VALUES ('25', '2', '3', '2015-12-10 22:18:41', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36', '/demo/api/office/tree', 'GET', '', 'org.hibernate.exception.SQLGrammarException: could not extract ResultSet', '0');
INSERT INTO `sys_log` VALUES ('26', '2', '3', '2015-12-10 22:18:44', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36', '/demo/api/office/tree', 'GET', '', 'org.hibernate.exception.SQLGrammarException: could not extract ResultSet', '0');

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(255) DEFAULT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `type` varchar(1) DEFAULT NULL COMMENT '机构类型（1：公司；2：部门；3：小组）',
  `grade` varchar(1) NOT NULL COMMENT '机构等级（1：一级；2：二级；3：三级；4：四级）',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `del_flag` tinyint(2) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_office_parent_ids` (`parent_ids`),
  KEY `sys_office_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES ('1', '0', '0,', '总公司', '1', '1', null, null, null, null, null, null, '0', null);
INSERT INTO `sys_office` VALUES ('2', '1', '0,1,', '分公司', '2', '2', null, null, null, null, null, null, '0', null);
INSERT INTO `sys_office` VALUES ('3', '2', '0,1,2,', '五公司', '3', '3', null, null, null, null, null, null, '0', null);
INSERT INTO `sys_office` VALUES ('4', '3', '0,1,2,3,', 'xx项目', '4', '4', null, null, null, null, null, null, '0', null);
INSERT INTO `sys_office` VALUES ('5', '4', '0,1,2,3,4', 'xx拌和站', '5', '5', null, null, null, null, null, null, '0', null);
INSERT INTO `sys_office` VALUES ('6', '4', '0,1,2,3,4', '1号站', '5', '5', null, null, null, null, null, null, '0', null);
INSERT INTO `sys_office` VALUES ('7', '4', '0,1,2,3,4', '2号站', '5', '5', null, null, null, null, null, null, '0', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `enname` varchar(100) DEFAULT NULL COMMENT '英文名称',
  `role_type` varchar(100) DEFAULT NULL COMMENT '角色类型',
  `data_scope` varchar(255) DEFAULT NULL COMMENT '数据范围（0：所有数据；1：所在公司及以下数据；2：所在公司数据；3：所在部门及以下数据；4：所在部门数据；8：仅本人数据；9：按明细设置）',
  `del_flag` int(11) NOT NULL COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', 'assignment', '1', '0');
INSERT INTO `sys_role` VALUES ('3', '用户', 'user', null, '9', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) NOT NULL COMMENT '登录名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别（0：男；1：女；2：保密）',
  `birthdate` date DEFAULT NULL COMMENT '生日',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `host` varchar(255) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `avatar` int(11) DEFAULT NULL COMMENT '头像',
  `role_id` int(11) DEFAULT NULL,
  `del_flag` int(11) NOT NULL COMMENT '删除标记（0：正常；1：删除）',
  `company_id` int(11) DEFAULT NULL COMMENT '归属公司',
  `office_id` int(11) DEFAULT NULL COMMENT '归属部门',
  PRIMARY KEY (`id`),
  KEY `sys_user_login_name` (`login_name`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'thon', 'e4ea697b4fe767dc7fdb5980333660d1ed32f969', '839811171b82c106', 'thon', '0', '1985-04-10', null, '1231', '127.0.0.1', '2015-12-03 16:35:03', null, '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('2', 'ju', 'e4ea697b4fe767dc7fdb5980333660d1ed32f969', '839811171b82c106', '鞠学见', null, null, 'thon@gmail.com', '', '127.0.0.1', '2015-12-03 16:36:35', null, '1', '0', null, null);
INSERT INTO `sys_user` VALUES ('3', 'luodun', 'f37564ad267cc6052103915f55c38c6f01d98ede', '2b50ddadd099262c', 'luodun', null, null, 'luodun@qq.com', '', '0:0:0:0:0:0:0:1', '2015-12-10 22:21:00', null, '3', '0', null, null);
