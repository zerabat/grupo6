-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tenant1
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 ;
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
  `passowd` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `cedula` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_administrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
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
  CONSTRAINT `fk_realizacion_espectaculo` FOREIGN KEY (`id_realizacion_espectaculo`) REFERENCES `realizacion_espectaculo` (`id_realizacion_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entrada_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entrada_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id_vendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `id_tipo_espectaculo` int(11) DEFAULT NULL,
  `id_realizacion_espectaculo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_espectaculo`),
  KEY `fk_tipo_espectaculo_idx` (`id_tipo_espectaculo`),
  KEY `fk_espectaculo_realizacion_espectaculo_idx` (`id_realizacion_espectaculo`),
  CONSTRAINT `fk_espectaculo_realizacion_espectaculo` FOREIGN KEY (`id_realizacion_espectaculo`) REFERENCES `realizacion_espectaculo` (`id_realizacion_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipo_espectaculo` FOREIGN KEY (`id_tipo_espectaculo`) REFERENCES `tipo_espectaculo` (`id_tipo_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `espectaculo`
--

LOCK TABLES `espectaculo` WRITE;
/*!40000 ALTER TABLE `espectaculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `espectaculo` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
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
  `fecha` date DEFAULT NULL,
  `id_sala` int(11) NOT NULL,
  PRIMARY KEY (`id_realizacion_espectaculo`),
  KEY `fk_sala_idx` (`id_sala`),
  KEY `fk_espectaculo_idx` (`id_espectaculo`),
  CONSTRAINT `fk_espectaculo` FOREIGN KEY (`id_espectaculo`) REFERENCES `espectaculo` (`id_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sala` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `realizacion_espectaculo`
--

LOCK TABLES `realizacion_espectaculo` WRITE;
/*!40000 ALTER TABLE `realizacion_espectaculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `realizacion_espectaculo` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
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
  PRIMARY KEY (`id_suscripcion`),
  KEY `fk_suscEsp_espectaculo_idx` (`id_espectaculo`),
  KEY `fk_suscEsp_usuario_idx` (`id_usuario`),
  CONSTRAINT `fk_suscEsp_espectaculo` FOREIGN KEY (`id_espectaculo`) REFERENCES `espectaculo` (`id_espectaculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_suscEsp_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_espectaculo`
--

LOCK TABLES `tipo_espectaculo` WRITE;
/*!40000 ALTER TABLE `tipo_espectaculo` DISABLE KEYS */;
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
  `password` varchar(512)  NULL,
  `gmail_token` varchar(512) NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
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

-- Dump completed on 2017-05-06 14:49:00
