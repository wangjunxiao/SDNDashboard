CREATE DATABASE  IF NOT EXISTS `sdnbox` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sdnbox`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 192.168.0.102    Database: sdnbox
-- ------------------------------------------------------
-- Server version	5.5.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ryu_groupdesc_info`
--

DROP TABLE IF EXISTS `ryu_groupdesc_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ryu_groupdesc_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `switch_id` int(11) NOT NULL,
  `ctrl_id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT '',
  `group_id` int(11) NOT NULL,
  `buckets` varchar(255) DEFAULT '',
  `ctime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `switch_id` (`switch_id`),
  CONSTRAINT `ryu_groupdesc_info_ibfk_1` FOREIGN KEY (`switch_id`) REFERENCES `s_switch_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ryu_groupdesc_info`
--

LOCK TABLES `ryu_groupdesc_info` WRITE;
/*!40000 ALTER TABLE `ryu_groupdesc_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `ryu_groupdesc_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_link_info`
--

DROP TABLE IF EXISTS `s_link_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_link_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `src_switch_id` int(11) NOT NULL,
  `src_port_id` int(11) NOT NULL,
  `dst_switch_id` int(11) NOT NULL,
  `dst_port_id` int(11) NOT NULL,
  `ctime` datetime DEFAULT NULL,
  `mtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dst_switch_id` (`dst_switch_id`),
  KEY `src_switch_id` (`src_switch_id`),
  KEY `src_port_id` (`src_port_id`),
  KEY `dst_port_id` (`dst_port_id`),
  CONSTRAINT `s_link_info_ibfk_4` FOREIGN KEY (`dst_switch_id`) REFERENCES `s_switch_info` (`id`),
  CONSTRAINT `s_link_info_ibfk_5` FOREIGN KEY (`src_switch_id`) REFERENCES `s_switch_info` (`id`),
  CONSTRAINT `s_link_info_ibfk_6` FOREIGN KEY (`src_port_id`) REFERENCES `s_port_info` (`id`),
  CONSTRAINT `s_link_info_ibfk_7` FOREIGN KEY (`dst_port_id`) REFERENCES `s_port_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1917 DEFAULT CHARSET=utf8 COMMENT='描述链路信息\r\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_link_info`
--

LOCK TABLES `s_link_info` WRITE;
/*!40000 ALTER TABLE `s_link_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_link_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_switch_info`
--

DROP TABLE IF EXISTS `s_switch_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_switch_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ctrl_id` int(11) NOT NULL,
  `dp_id` varchar(255) DEFAULT NULL,
  `n_tables` int(11) DEFAULT NULL COMMENT 'Number of tables supported by datapath.',
  `dp_desc` varchar(255) DEFAULT NULL COMMENT 'datapathDescription',
  `sw_desc` varchar(255) DEFAULT NULL COMMENT 'softwareDescription',
  `hw_desc` varchar(255) DEFAULT NULL COMMENT 'hardwareDescription',
  `serial_num` varchar(255) DEFAULT NULL COMMENT 'serialNumber',
  `mfr_desc` varchar(255) DEFAULT NULL COMMENT 'manufacturerDescription',
  `ctime` datetime DEFAULT NULL,
  `mtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ctrl_id` (`ctrl_id`),
  CONSTRAINT `s_switch_info_ibfk_1` FOREIGN KEY (`ctrl_id`) REFERENCES `s_controller_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3118 DEFAULT CHARSET=utf8 COMMENT='描述交换机信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_switch_info`
--

LOCK TABLES `s_switch_info` WRITE;
/*!40000 ALTER TABLE `s_switch_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_switch_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_flow_info`
--

DROP TABLE IF EXISTS `s_flow_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_flow_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `switch_id` int(11) NOT NULL,
  `table_id` int(11) DEFAULT NULL,
  `byte_count` bigint(20) DEFAULT NULL,
  `packet_count` double(30,0) DEFAULT '0',
  `idle_timeout` int(11) DEFAULT NULL,
  `hard_timeout` int(11) DEFAULT NULL,
  `duration_sec` int(11) NOT NULL DEFAULT '0',
  `duration_nsec` int(11) NOT NULL DEFAULT '0',
  `priority` int(11) DEFAULT NULL,
  `cookie` varchar(255) DEFAULT NULL,
  `dl_type` varchar(255) DEFAULT NULL COMMENT 'dataLayerType(POX:LLDP,Floodlight:0x0000)',
  `dl_src` varchar(255) DEFAULT NULL COMMENT 'dataLayerSource',
  `dl_dst` varchar(255) DEFAULT NULL COMMENT 'dataLayerDestination',
  `dl_vlan` int(11) DEFAULT NULL COMMENT 'dataLayerVirtualLan',
  `nw_proto` int(11) DEFAULT NULL COMMENT 'networkProtocol(POX:1,Floodlight:0)',
  `nw_tos` int(11) DEFAULT NULL COMMENT 'networkTypeOfService(POX:0,Floodlight:0)',
  `nw_src` varchar(255) DEFAULT NULL COMMENT 'networkSource',
  `nw_dst` varchar(255) DEFAULT NULL COMMENT 'networkDestination',
  `nw_src_masklen` int(11) DEFAULT NULL COMMENT 'networkMaskLength',
  `nw_dst_masklen` int(11) DEFAULT NULL COMMENT 'networkMaskLength',
  `tp_src` int(11) DEFAULT NULL COMMENT 'transportSource',
  `tp_dst` int(11) DEFAULT NULL COMMENT 'transportDestination',
  `in_port` int(11) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `mtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `flow_id` (`id`),
  KEY `switch_id` (`switch_id`),
  CONSTRAINT `s_flow_info_ibfk_1` FOREIGN KEY (`switch_id`) REFERENCES `s_switch_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=971 DEFAULT CHARSET=utf8 COMMENT='描述流表信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_flow_info`
--

LOCK TABLES `s_flow_info` WRITE;
/*!40000 ALTER TABLE `s_flow_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_flow_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ryu_meter_info`
--

DROP TABLE IF EXISTS `ryu_meter_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ryu_meter_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `switch_id` int(11) NOT NULL,
  `ctrl_id` int(11) NOT NULL DEFAULT '0',
  `meter_id` int(11) NOT NULL DEFAULT '0',
  `len` int(11) DEFAULT '0',
  `flow_count` int(11) DEFAULT '0',
  `packet_in_count` int(11) DEFAULT '0',
  `byte_in_count` int(11) DEFAULT '0',
  `duration_sec` bigint(20) DEFAULT '0',
  `duration_nsec` varchar(255) DEFAULT '',
  `band_stats` varchar(255) DEFAULT '[]',
  `ctime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `switch_id` (`switch_id`),
  CONSTRAINT `ryu_meter_info_ibfk_1` FOREIGN KEY (`switch_id`) REFERENCES `s_switch_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ryu_meter_info`
--

LOCK TABLES `ryu_meter_info` WRITE;
/*!40000 ALTER TABLE `ryu_meter_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `ryu_meter_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_host_info`
--

DROP TABLE IF EXISTS `s_host_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_host_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `switch_id` int(11) NOT NULL,
  `port_id` int(11) NOT NULL,
  `mac_addr` varchar(255) DEFAULT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `mtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `switch_id` (`switch_id`),
  KEY `port_id` (`port_id`),
  CONSTRAINT `s_host_info_ibfk_1` FOREIGN KEY (`switch_id`) REFERENCES `s_switch_info` (`id`),
  CONSTRAINT `s_host_info_ibfk_2` FOREIGN KEY (`port_id`) REFERENCES `s_port_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='描述host信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_host_info`
--

LOCK TABLES `s_host_info` WRITE;
/*!40000 ALTER TABLE `s_host_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_host_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_port_stats`
--

DROP TABLE IF EXISTS `s_port_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_port_stats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dp_id` varchar(255) NOT NULL,
  `port_no` bigint(20) DEFAULT NULL,
  `rcv_packets` int(11) DEFAULT NULL,
  `trsm_packets` int(11) DEFAULT NULL,
  `rcv_bytes` int(15) DEFAULT NULL,
  `trsm_bytes` int(15) DEFAULT NULL,
  `rcv_drop` int(11) DEFAULT NULL,
  `trsm_drop` int(11) DEFAULT NULL,
  `rcv_err` int(11) DEFAULT NULL,
  `trsm_err` int(11) DEFAULT NULL,
  `rcv_frm_err` int(11) DEFAULT NULL,
  `rcv_over_err` int(11) DEFAULT NULL,
  `rcv_CRC_err` int(11) DEFAULT NULL,
  `collisions` int(11) DEFAULT NULL,
  `duration_sec` bigint(20) DEFAULT NULL,
  `duration_nsec` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6146 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_port_stats`
--

LOCK TABLES `s_port_stats` WRITE;
/*!40000 ALTER TABLE `s_port_stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_port_stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ryu_groupfeatures_info`
--

DROP TABLE IF EXISTS `ryu_groupfeatures_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ryu_groupfeatures_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `switch_id` int(11) NOT NULL,
  `ctrl_id` int(11) NOT NULL,
  `types` varchar(255) DEFAULT '',
  `capabilities` varchar(255) DEFAULT '',
  `max_groups` varchar(255) DEFAULT '',
  `actions` varchar(255) DEFAULT '',
  `ctime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `switch_id` (`switch_id`),
  CONSTRAINT `ryu_groupfeatures_info_ibfk_1` FOREIGN KEY (`switch_id`) REFERENCES `s_switch_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ryu_groupfeatures_info`
--

LOCK TABLES `ryu_groupfeatures_info` WRITE;
/*!40000 ALTER TABLE `ryu_groupfeatures_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `ryu_groupfeatures_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_controller_info`
--

DROP TABLE IF EXISTS `s_controller_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_controller_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL COMMENT '控制器ip地址',
  `rest_url` varchar(255) DEFAULT NULL COMMENT 'restAPI请求的URL前缀',
  `ctime` datetime DEFAULT NULL COMMENT 'created time',
  `mtime` timestamp NULL DEFAULT NULL COMMENT 'modified time',
  PRIMARY KEY (`id`),
  KEY `ctrller_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 COMMENT='描述控制器信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_controller_info`
--

LOCK TABLES `s_controller_info` WRITE;
/*!40000 ALTER TABLE `s_controller_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_controller_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_port_info`
--

DROP TABLE IF EXISTS `s_port_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_port_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `switch_id` int(11) NOT NULL,
  `port_no` bigint(20) NOT NULL,
  `mac_addr` varchar(255) DEFAULT NULL,
  `port_name` varchar(255) DEFAULT NULL,
  `config` int(11) DEFAULT '0',
  `state` int(11) DEFAULT '0',
  `curr` int(11) DEFAULT '0',
  `advertised` int(11) DEFAULT '0',
  `supported` int(11) DEFAULT '0',
  `peer` int(11) DEFAULT '0',
  `curr_speed` int(11) DEFAULT '0',
  `max_speed` int(11) DEFAULT '0',
  `ctime` timestamp NULL DEFAULT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `switch_id` (`switch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=209967 DEFAULT CHARSET=utf8 COMMENT='描述交换机的物理端口信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_port_info`
--

LOCK TABLES `s_port_info` WRITE;
/*!40000 ALTER TABLE `s_port_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_port_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ryu_meterconfig_info`
--

DROP TABLE IF EXISTS `ryu_meterconfig_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ryu_meterconfig_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `switch_id` int(11) NOT NULL,
  `ctrl_id` int(11) NOT NULL DEFAULT '0',
  `meter_id` int(11) NOT NULL DEFAULT '0',
  `flags` varchar(255) DEFAULT '[]',
  `bands` varchar(255) DEFAULT '[]',
  `ctime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `switch_id` (`switch_id`),
  CONSTRAINT `ryu_meterconfig_info_ibfk_1` FOREIGN KEY (`switch_id`) REFERENCES `s_switch_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ryu_meterconfig_info`
--

LOCK TABLES `ryu_meterconfig_info` WRITE;
/*!40000 ALTER TABLE `ryu_meterconfig_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `ryu_meterconfig_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ryu_meterfeatures_info`
--

DROP TABLE IF EXISTS `ryu_meterfeatures_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ryu_meterfeatures_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `switch_id` int(11) NOT NULL,
  `ctrl_id` int(11) NOT NULL DEFAULT '0',
  `max_meter` int(11) DEFAULT '0',
  `band_types` varchar(255) DEFAULT '[]',
  `capabilities` varchar(255) DEFAULT '[]',
  `max_bands` int(11) DEFAULT '0',
  `max_color` int(11) DEFAULT '0',
  `ctime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `switch_id` (`switch_id`),
  CONSTRAINT `ryu_meterfeatures_info_ibfk_1` FOREIGN KEY (`switch_id`) REFERENCES `s_switch_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ryu_meterfeatures_info`
--

LOCK TABLES `ryu_meterfeatures_info` WRITE;
/*!40000 ALTER TABLE `ryu_meterfeatures_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `ryu_meterfeatures_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ryu_groupstats_info`
--

DROP TABLE IF EXISTS `ryu_groupstats_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ryu_groupstats_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `switch_id` int(11) NOT NULL,
  `ctrl_id` int(11) NOT NULL,
  `length` int(11) DEFAULT '0',
  `group_id` int(11) NOT NULL,
  `ref_count` int(11) DEFAULT '0',
  `packet_count` int(11) DEFAULT '0',
  `byte_count` int(11) DEFAULT '0',
  `duration_sec` int(11) DEFAULT '0',
  `duration_nsec` varchar(255) DEFAULT '',
  `bucket_stats` varchar(255) DEFAULT '',
  `ctime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `switch_id` (`switch_id`),
  CONSTRAINT `ryu_groupstats_info_ibfk_1` FOREIGN KEY (`switch_id`) REFERENCES `s_switch_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ryu_groupstats_info`
--

LOCK TABLES `ryu_groupstats_info` WRITE;
/*!40000 ALTER TABLE `ryu_groupstats_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `ryu_groupstats_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-01 11:39:14
