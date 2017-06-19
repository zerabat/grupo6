-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tenant1
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.21-MariaDB

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrador` (
  `id_administrador` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `passowd` varchar(512) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `cedula` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_administrador`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (2,'admin@admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','Administrador','Administrador','12345678');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada`
--

DROP TABLE IF EXISTS `entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrada` (
  `id_entrada` int(11) NOT NULL AUTO_INCREMENT,
  `numero_asiento` int(11) NOT NULL,
  `fecha_compra` date DEFAULT NULL,
  `precio` int(11) NOT NULL,
  `id_vendedor` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `id_portero` int(11) DEFAULT NULL,
  `id_espectaculo` int(11) NOT NULL,
  `id_sector` int(11) DEFAULT NULL,
  `id_realizacion_espectaculo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_entrada`),
  KEY `fk_entrada_espectaculo_idx` (`id_espectaculo`),
  KEY `fk_entrada_sector_idx` (`id_sector`),
  KEY `fk_realizacion_espectaculo` (`id_realizacion_espectaculo`),
  KEY `fk_entrada_usuario_idx` (`id_usuario`),
  KEY `fk_entrada_vendedor_idx` (`id_vendedor`),
  KEY `fk_entrada_portero_idx` (`id_portero`),
  CONSTRAINT `fk_entrada_espectaculo` FOREIGN KEY (`id_espectaculo`) REFERENCES `espectaculo` (`id_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entrada_portero` FOREIGN KEY (`id_portero`) REFERENCES `portero` (`id_portero`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entrada_sector` FOREIGN KEY (`id_sector`) REFERENCES `sector` (`id_sector`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entrada_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entrada_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id_vendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_realizacion_espectaculo` FOREIGN KEY (`id_realizacion_espectaculo`) REFERENCES `realizacion_espectaculo` (`id_realizacion_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1925 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada`
--

LOCK TABLES `entrada` WRITE;
/*!40000 ALTER TABLE `entrada` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `espectaculo`
--

DROP TABLE IF EXISTS `espectaculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `espectaculo` (
  `id_espectaculo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  `id_realizacion_espectaculo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_espectaculo`),
  KEY `fk_espectaculo_realizacion_espectaculo_idx` (`id_realizacion_espectaculo`),
  CONSTRAINT `fk_espectaculo_realizacion_espectaculo` FOREIGN KEY (`id_realizacion_espectaculo`) REFERENCES `realizacion_espectaculo` (`id_realizacion_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `espectaculo`
--

LOCK TABLES `espectaculo` WRITE;
/*!40000 ALTER TABLE `espectaculo` DISABLE KEYS */;
INSERT INTO `espectaculo` VALUES (4,'Peñarol - Nacional','Descripcion espectaculo1',NULL),(5,'Cerro - Rampla','Descripcion espectaculo2',NULL),(6,'Defensor - Danubio','Descripcion espectaculo3',NULL),(7,'Fenix - Liverpool','Descripcion espectaculo4',NULL),(8,'El Tanque - Bosston River','Descripcion espectaculo5',NULL),(9,'Danza Contemporanea','Descripcion espectaculo6',NULL),(10,'Matrix (1999)','Descripcion espectaculo6',NULL);
/*!40000 ALTER TABLE `espectaculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `espectaculo_tipo_espectaculo`
--

DROP TABLE IF EXISTS `espectaculo_tipo_espectaculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `espectaculo_tipo_espectaculo` (
  `id_espectaculo_tipo_espectaculo` int(11) NOT NULL AUTO_INCREMENT,
  `id_espectaculo` int(11) NOT NULL,
  `id_tipo_espectaculo` int(11) NOT NULL,
  PRIMARY KEY (`id_espectaculo_tipo_espectaculo`),
  KEY `fk_EscpTEsp_TipoEspectaculo_idx` (`id_tipo_espectaculo`),
  KEY `fk_EspTEsp_Espectaculo_idx` (`id_espectaculo`),
  CONSTRAINT `fk_EspTEsp_Espectaculo` FOREIGN KEY (`id_espectaculo`) REFERENCES `espectaculo` (`id_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EspTEsp_TipoEspectaculo` FOREIGN KEY (`id_tipo_espectaculo`) REFERENCES `tipo_espectaculo` (`id_tipo_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `espectaculo_tipo_espectaculo`
--

LOCK TABLES `espectaculo_tipo_espectaculo` WRITE;
/*!40000 ALTER TABLE `espectaculo_tipo_espectaculo` DISABLE KEYS */;
INSERT INTO `espectaculo_tipo_espectaculo` VALUES (3,4,1),(4,5,1),(5,6,1),(6,7,1),(7,8,1),(8,9,3),(9,10,2);
/*!40000 ALTER TABLE `espectaculo_tipo_espectaculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `espectaculo_tipo_pago`
--

DROP TABLE IF EXISTS `espectaculo_tipo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `espectaculo_tipo_pago` (
  `id_espectaculo_tipo_pago` int(11) NOT NULL AUTO_INCREMENT,
  `id_espectaculo` int(11) NOT NULL,
  `id_tipo_pago` int(11) NOT NULL,
  PRIMARY KEY (`id_espectaculo_tipo_pago`),
  KEY `fk_tipo_pago_idx` (`id_tipo_pago`),
  KEY `fk_espectaculo_idx` (`id_espectaculo`),
  CONSTRAINT `fk_esptipopago_espectaculo` FOREIGN KEY (`id_espectaculo`) REFERENCES `espectaculo` (`id_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_esptipopago_tipo_pago` FOREIGN KEY (`id_tipo_pago`) REFERENCES `tipo_pago` (`id_tipo_pago`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `espectaculo_tipo_pago`
--

LOCK TABLES `espectaculo_tipo_pago` WRITE;
/*!40000 ALTER TABLE `espectaculo_tipo_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `espectaculo_tipo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial_entradas`
--

DROP TABLE IF EXISTS `historial_entradas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historial_entradas` (
  `id_historial_entradas` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `fecha_compra` varchar(45) NOT NULL,
  `id_vendedor` int(11) DEFAULT NULL,
  `id_portero` int(11) DEFAULT NULL,
  `fecha_verificacion` date DEFAULT NULL,
  `id_entrada` int(11) NOT NULL,
  PRIMARY KEY (`id_historial_entradas`),
  KEY `fk_histentr_usuario_idx` (`id_usuario`),
  KEY `fk_histentr_entrada_idx` (`id_entrada`),
  KEY `fk_histentr_portero_idx` (`id_portero`),
  KEY `fk_histentr_vendedor_idx` (`id_vendedor`),
  CONSTRAINT `fk_histentr_entrada` FOREIGN KEY (`id_entrada`) REFERENCES `entrada` (`id_entrada`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_histentr_portero` FOREIGN KEY (`id_portero`) REFERENCES `portero` (`id_portero`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_histentr_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_histentr_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id_vendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial_entradas`
--

LOCK TABLES `historial_entradas` WRITE;
/*!40000 ALTER TABLE `historial_entradas` DISABLE KEYS */;
/*!40000 ALTER TABLE `historial_entradas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portero`
--

DROP TABLE IF EXISTS `portero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `portero` (
  `id_portero` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `password` varchar(512) NOT NULL,
  PRIMARY KEY (`id_portero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portero`
--

LOCK TABLES `portero` WRITE;
/*!40000 ALTER TABLE `portero` DISABLE KEYS */;
/*!40000 ALTER TABLE `portero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `realizacion_espectaculo`
--

DROP TABLE IF EXISTS `realizacion_espectaculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `realizacion_espectaculo` (
  `id_realizacion_espectaculo` int(11) NOT NULL AUTO_INCREMENT,
  `id_espectaculo` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `id_sala` int(11) NOT NULL,
  PRIMARY KEY (`id_realizacion_espectaculo`),
  KEY `fk_sala_idx` (`id_sala`),
  KEY `fk_espectaculo_idx` (`id_espectaculo`),
  CONSTRAINT `fk_espectaculo` FOREIGN KEY (`id_espectaculo`) REFERENCES `espectaculo` (`id_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sala` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `realizacion_espectaculo`
--

LOCK TABLES `realizacion_espectaculo` WRITE;
/*!40000 ALTER TABLE `realizacion_espectaculo` DISABLE KEYS */;
INSERT INTO `realizacion_espectaculo` VALUES (5,4,'2017-08-18 15:17:16',4),(6,4,'2017-08-18 15:17:16',4),(7,4,'2017-08-18 15:17:16',4),(8,4,'2017-08-18 15:17:16',4),(9,5,'2017-08-18 15:17:16',4),(10,5,'2017-08-18 15:17:16',4),(11,5,'2017-08-18 15:17:16',4),(12,6,'2017-08-18 15:17:16',4),(13,6,'2017-08-18 15:17:16',4),(14,6,'2017-08-18 15:17:16',4),(15,7,'2017-08-18 15:17:16',4),(16,7,'2017-08-18 15:17:16',4),(17,7,'2017-08-18 15:17:16',4),(18,7,'2017-08-18 15:17:16',4),(19,8,'2017-08-18 15:17:16',4),(20,8,'2017-08-18 15:17:16',4),(21,8,'2017-08-18 15:17:16',4),(22,8,'2017-08-18 15:17:16',4),(23,9,'2017-08-18 15:17:16',3),(24,9,'2017-08-18 15:17:16',3),(25,10,'2017-08-18 15:17:16',5),(26,10,'2017-08-18 15:17:16',5);
/*!40000 ALTER TABLE `realizacion_espectaculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sala` (
  `id_sala` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `total_localidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (3,'Auditorio Sodre','Direccion Sala1',200),(4,'Estadio Centenario','Direccion Sala2',400),(5,'Sala3 Relleno','Direccion Sala3',200);
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector`
--

DROP TABLE IF EXISTS `sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sector` (
  `id_sector` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `id_sala` int(11) NOT NULL,
  PRIMARY KEY (`id_sector`),
  KEY `fk_sala_idx` (`id_sala`),
  CONSTRAINT `fk_sector_sala` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` VALUES (3,'Sector VIP',100,3),(4,'Sector Normal',100,3),(5,'Amsterdam',100,4),(6,'America',100,4),(7,'Colombes',100,4),(8,'Olimpica',100,4),(9,'Sector Relleno 1',100,5),(10,'Sector Relleno 2',100,5);
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suscripcion_espectaculo`
--

DROP TABLE IF EXISTS `suscripcion_espectaculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suscripcion_espectaculo` (
  `id_suscripcion` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `id_espectaculo` int(11) DEFAULT NULL,
  `id_realziacion_espectaculo` int(11) DEFAULT NULL,
  `id_tipo_espectaculoco` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_suscripcion`),
  KEY `fk_suscEsp_espectaculo_idx` (`id_espectaculo`),
  KEY `fk_suscEsp_usuario_idx` (`id_usuario`),
  KEY `fk_susc_Real_Espectaculo_idx` (`id_realziacion_espectaculo`),
  KEY `fk_susc_Tipo_Espectaculo_idx` (`id_tipo_espectaculoco`),
  CONSTRAINT `fk_suscEsp_espectaculo` FOREIGN KEY (`id_espectaculo`) REFERENCES `espectaculo` (`id_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_suscEsp_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_susc_Real_Espectaculo` FOREIGN KEY (`id_realziacion_espectaculo`) REFERENCES `realizacion_espectaculo` (`id_realizacion_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_susc_Tipo_Espectaculo` FOREIGN KEY (`id_tipo_espectaculoco`) REFERENCES `tipo_espectaculo` (`id_tipo_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suscripcion_espectaculo`
--

LOCK TABLES `suscripcion_espectaculo` WRITE;
/*!40000 ALTER TABLE `suscripcion_espectaculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `suscripcion_espectaculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_espectaculo`
--

DROP TABLE IF EXISTS `tipo_espectaculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_espectaculo` (
  `id_tipo_espectaculo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id_tipo_espectaculo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_espectaculo`
--

LOCK TABLES `tipo_espectaculo` WRITE;
/*!40000 ALTER TABLE `tipo_espectaculo` DISABLE KEYS */;
INSERT INTO `tipo_espectaculo` VALUES (1,'Deportes'),(2,'Cine'),(3,'Teatro');
/*!40000 ALTER TABLE `tipo_espectaculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pago`
--

DROP TABLE IF EXISTS `tipo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_pago` (
  `id_tipo_pago` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id_tipo_pago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pago`
--

LOCK TABLES `tipo_pago` WRITE;
/*!40000 ALTER TABLE `tipo_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `password` varchar(512) DEFAULT NULL,
  `gmail_token` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'camilo','orquera','c.orquera1@gmail.com','51119557','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','112566390495131992689');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor` (
  `id_vendedor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `password` varchar(512) NOT NULL,
  PRIMARY KEY (`id_vendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-18 16:33:28
