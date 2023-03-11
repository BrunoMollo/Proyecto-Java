-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: us-cdbr-east-06.cleardb.net    Database: heroku_5c56859fce36a7d
-- ------------------------------------------------------
-- Server version	5.6.50-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `afiliados`
--

DROP TABLE IF EXISTS `afiliados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `afiliados` (
  `numeroAfiliado` varchar(45) NOT NULL,
  `idObraSocial` int(11) NOT NULL,
  `dniCliente` int(11) NOT NULL,
  PRIMARY KEY (`numeroAfiliado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afiliados`
--

LOCK TABLES `afiliados` WRITE;
/*!40000 ALTER TABLE `afiliados` DISABLE KEYS */;
/*!40000 ALTER TABLE `afiliados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `dni` int(11) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `localidad` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `fechaNac` date NOT NULL,
  `id_obraSocial` int(11) NOT NULL,
  `nroAfiliado` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) NOT NULL,
  PRIMARY KEY (`dni`),
  KEY `cliente_os_fk_idx` (`id_obraSocial`),
  CONSTRAINT `cliente_os_fk` FOREIGN KEY (`id_obraSocial`) REFERENCES `obras_sociales` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (123,'Mollo','Bruno ','medife@gmial.com','12321312','MELINCUE','Santa Fe','1212-12-12',24,'123','de'),(453,'Mitre','Bartolome','rre@eqrew','123','BARRIO COSTA ESTE','R�o Negro','1212-12-12',67,NULL,'mitre 123'),(123123,'Messi','Roberto      ','fasfa@gmail.com','12312312','ALDEA BELEIRO','Chubut','1998-09-05',34,'321','12312'),(1233244,'apellido1','nombre1','la@bz.com','675','BARRIO DE LOS PESCADORES','Chaco','1997-06-08',70,'678678','342dsf '),(1312313,'Maidana','Robertow','aasdasd@gmail.com','12312123','28 DE NOVIEMBRE','Santa Cruz','1222-12-02',14,'789','3 dew febrero212'),(43000000,'Di Giacinti','Ramiro ','aaa@aaa.coaaa','777558855','SAN NICOLAS DE LOS ARROYOS','Buenos Aires','2002-04-01',70,'1','Nose 50'),(77777777,'Pelado','Bicho ','siuuuuuu@siuuuuu.siu','465756','YERBA BUENA - MARCOS PAZ','Tucum?n','1997-06-08',67,'45677','Nose 50'),(123232323,'apellido','nombre  ','das@mb.com','123','11 DE SEPTIEMBRE','Buenos Aires','2022-08-08',64,'879789876','rewr 1324');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dosis`
--

DROP TABLE IF EXISTS `dosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dosis` (
  `codigoDroga` int(11) NOT NULL,
  `codigoMedicamento` int(12) unsigned zerofill NOT NULL,
  `cantidad` double NOT NULL,
  `unidad` varchar(2) NOT NULL,
  PRIMARY KEY (`codigoDroga`,`codigoMedicamento`),
  KEY `fk_dosis_medicamento_idx` (`codigoMedicamento`),
  CONSTRAINT `fk_dosis_droga` FOREIGN KEY (`codigoDroga`) REFERENCES `drogas` (`codigo`) ON UPDATE CASCADE,
  CONSTRAINT `fk_dosis_medicamento` FOREIGN KEY (`codigoMedicamento`) REFERENCES `medicamentos` (`codigoBarra`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dosis`
--

LOCK TABLES `dosis` WRITE;
/*!40000 ALTER TABLE `dosis` DISABLE KEYS */;
INSERT INTO `dosis` VALUES (94,000000456789,85,'mg'),(94,000000652645,45,'mg'),(94,000002321312,34,'mg'),(94,000543543543,654,'mg'),(104,000000456789,500,'mg'),(104,000005432756,344,'mg'),(134,000000000088,678,'mg'),(154,000034321321,34,'mg'),(364,000000000200,12,'mg'),(384,000000532465,100,'mg');
/*!40000 ALTER TABLE `dosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drogas`
--

DROP TABLE IF EXISTS `drogas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drogas` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=444 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drogas`
--

LOCK TABLES `drogas` WRITE;
/*!40000 ALTER TABLE `drogas` DISABLE KEYS */;
INSERT INTO `drogas` VALUES (94,'Paracetamol'),(104,'Ibuprofeno'),(114,'Lanzoprazol'),(124,'Lorazepam'),(134,'Bromazepam'),(154,'Midazolam'),(174,'Clobazam'),(184,'Fentanil'),(364,'Diazepam'),(374,'Amoxicilina'),(384,'Aspirina'),(386,'Droga1'),(394,'Nueva Droga'),(404,'Nueva Droga2'),(414,'Nueva Droga3');
/*!40000 ALTER TABLE `drogas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratorios`
--

DROP TABLE IF EXISTS `laboratorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laboratorios` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(25) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=344 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratorios`
--

LOCK TABLES `laboratorios` WRITE;
/*!40000 ALTER TABLE `laboratorios` DISABLE KEYS */;
INSERT INTO `laboratorios` VALUES (1,'Kellerhoff','1245','asd@gmia.com'),(4,'Pfizer','13123','12@gmail.com'),(74,'Bago','123','123333@gmail.com'),(124,'Bayer','349123123','bayer@gmail.com'),(174,'Monserrat-Eclair','123123','pep@alg.com'),(224,'Teva','12312','12@gmail.com'),(284,'Medisol','123412','algo@gmail.com'),(314,'AstraZeneca','6666555','as@tra.zeneca'),(324,'Laborat0','1213','a@sa.co'),(334,'Laborat0','21342','a@sa.co');
/*!40000 ALTER TABLE `laboratorios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_ventas`
--

DROP TABLE IF EXISTS `linea_ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `linea_ventas` (
  `nroVenta` int(11) NOT NULL,
  `codBarra` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precioUnidad` double NOT NULL,
  PRIMARY KEY (`nroVenta`,`codBarra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_ventas`
--

LOCK TABLES `linea_ventas` WRITE;
/*!40000 ALTER TABLE `linea_ventas` DISABLE KEYS */;
INSERT INTO `linea_ventas` VALUES (4,2321312,2,12),(14,2321312,1,12),(24,2321312,1,12),(44,2321312,1,12),(54,1,2,5000),(64,1,1,5000),(64,2321312,101,12),(74,2321312,80,12),(84,88,15,111.11),(94,88,3,111.11),(94,2321312,5,12),(94,34321321,2,45),(104,1,1,5000),(106,88,1,111.11),(106,2321312,1,12),(108,1,1,5000),(109,1,1,5000),(110,1,1,5000),(111,34321321,1,45),(112,200,1,2131),(112,2321312,2,12),(113,1,1,5000),(114,88,5,111.11),(114,2321312,53,12),(115,88,1,111.11),(116,2321312,68,12),(117,2321312,5,12),(118,88,1,500),(118,532465,1,150),(118,2321312,1,12),(119,34321321,1,45),(120,532465,1,150),(120,2321312,3,12),(121,2321312,1,12),(122,2321312,1,12),(123,2321312,1,12),(124,2321312,5,12),(125,200,6,2131),(126,1,1,5000),(127,1,1,5000),(130,2321312,1,12),(131,2321312,51,12),(132,2321312,100,12),(133,2321312,5,12),(134,2321312,2,12),(136,88,1,500),(137,88,1,500),(138,88,1,500),(139,200,5,2131),(139,2321312,2,12),(140,88,5,500),(140,200,4,2131),(141,2321312,1,12),(142,1,1,5000),(143,88,1,500),(144,1,2,5000),(145,1,6,4750),(146,1,5,4750),(147,1,5,5000),(148,1,1,5000),(149,1,1,4750),(150,1,1,5000),(151,1,1,5000),(152,1,1,5000),(153,2321312,1,11.399999999999999),(155,1,1,5000),(155,34321321,1,45),(156,1,1,5000),(157,1,1,5000),(164,5432756,1,777);
/*!40000 ALTER TABLE `linea_ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamentos`
--

DROP TABLE IF EXISTS `medicamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicamentos` (
  `codigoBarra` int(12) unsigned zerofill NOT NULL,
  `codigoLaboratorio` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `size` double NOT NULL,
  `unidad` varchar(25) NOT NULL,
  `baja` datetime DEFAULT NULL,
  PRIMARY KEY (`codigoBarra`),
  KEY `fk_medicamento_laboratorio_idx` (`codigoLaboratorio`),
  KEY `index_nombre` (`nombre`),
  CONSTRAINT `fk_medicamento_laboratorio` FOREIGN KEY (`codigoLaboratorio`) REFERENCES `laboratorios` (`codigo`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamentos`
--

LOCK TABLES `medicamentos` WRITE;
/*!40000 ALTER TABLE `medicamentos` DISABLE KEYS */;
INSERT INTO `medicamentos` VALUES (000000000001,1,'IBUMAX',30,'capsulas',NULL),(000000000088,1,'Tad',444,'mg',NULL),(000000000200,124,'Actron',200,'unidades',NULL),(000000456789,1,'Medicamento1',25,'ml',NULL),(000000532465,124,'Aspirinetas',20,'unidades',NULL),(000000652645,1,'Eliminame',54,'ml',NULL),(000002321312,1,'Tafirol',300,'unidades',NULL),(000005432756,1,'Test1',777,'unidades',NULL),(000034321321,1,'Atomo',300,'unidades',NULL),(000543543543,1,'sdfgsdfg',234,'dosis','2023-03-10 00:00:00');
/*!40000 ALTER TABLE `medicamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obras_sociales`
--

DROP TABLE IF EXISTS `obras_sociales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obras_sociales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `descuentoGeneral` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obras_sociales`
--

LOCK TABLES `obras_sociales` WRITE;
/*!40000 ALTER TABLE `obras_sociales` DISABLE KEYS */;
INSERT INTO `obras_sociales` VALUES (14,'Galeno','132','dia.mollo.bruno@gmail.com',5),(24,'Osde','345','dia.mollo.bruno@gmail.com',5),(34,'PARTICULAR','1','N@A.RN',0),(64,'Medife','132','medife@medife.com',5),(67,'Sancor Salud','132','sancor@salud.com',45),(70,'Avalian','222','aval@ian.cm',7.5),(74,'OS1','124','a@sa.co',67);
/*!40000 ALTER TABLE `obras_sociales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio_medicamento`
--

DROP TABLE IF EXISTS `precio_medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precio_medicamento` (
  `codigoMedicamento` int(12) unsigned zerofill NOT NULL,
  `fecha` date NOT NULL,
  `monto` double NOT NULL,
  PRIMARY KEY (`codigoMedicamento`,`fecha`),
  CONSTRAINT `fk_precio_medicamento` FOREIGN KEY (`codigoMedicamento`) REFERENCES `medicamentos` (`codigoBarra`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio_medicamento`
--

LOCK TABLES `precio_medicamento` WRITE;
/*!40000 ALTER TABLE `precio_medicamento` DISABLE KEYS */;
INSERT INTO `precio_medicamento` VALUES (000000000001,'2022-10-31',5000),(000000000001,'2023-04-01',5001),(000000000088,'2022-11-01',23),(000000000088,'2022-12-05',889.79),(000000000088,'2022-12-12',69.69),(000000000088,'2023-01-01',1),(000000000088,'2023-01-02',2),(000000000088,'2023-03-05',87.58),(000000000088,'2023-05-06',111.11),(000000000088,'2023-05-07',500),(000000000200,'2022-11-03',2131),(000000456789,'2022-11-14',369),(000000532465,'2022-11-14',150),(000002321312,'2022-10-30',12),(000005432756,'2023-03-10',777),(000034321321,'2022-10-30',45),(000543543543,'2023-03-10',55555);
/*!40000 ALTER TABLE `precio_medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `usuario` varchar(50) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `rol` int(11) NOT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('a','a','a','todopederoso',0),('admin','admin','admin','admin',0),('Bruno','123','Bruno','Mollo',1),('Rami','123','Ramiro','Di Giacinti',1),('root','root','root','root',0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `nroVenta` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `total` double NOT NULL,
  `dniCLiente` int(11) DEFAULT NULL,
  `nroReceta` int(11) DEFAULT NULL,
  `vendidoPor` varchar(50) NOT NULL,
  PRIMARY KEY (`nroVenta`),
  KEY `fk_venta_usuario_idx` (`vendidoPor`),
  CONSTRAINT `fk_venta_usuario` FOREIGN KEY (`vendidoPor`) REFERENCES `usuarios` (`usuario`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (4,'2022-11-02 14:16:13',24,NULL,NULL,'a'),(14,'2022-11-02 14:24:41',12,NULL,NULL,'a'),(24,'2022-11-02 14:37:59',12,NULL,NULL,'a'),(34,'2022-11-03 11:30:08',0,NULL,NULL,'a'),(44,'2022-11-04 18:47:39',12,NULL,NULL,'a'),(54,'2022-11-04 19:05:26',10000,NULL,NULL,'a'),(64,'2022-11-04 19:53:31',6212,NULL,NULL,'a'),(74,'2022-11-08 10:17:54',960,NULL,NULL,'a'),(84,'2022-11-08 10:19:31',1666.65,NULL,NULL,'Rami'),(94,'2022-11-08 10:22:03',483.33,NULL,NULL,'Rami'),(104,'2022-11-09 16:13:25',5000,NULL,NULL,'a'),(105,'2022-11-10 16:30:17',123.11,NULL,NULL,'a'),(106,'2022-11-10 16:30:28',123.11,NULL,NULL,'a'),(107,'2022-11-10 16:42:06',5000,NULL,NULL,'a'),(108,'2022-11-10 16:54:16',5000,NULL,NULL,'a'),(109,'2022-11-10 16:54:42',5000,NULL,NULL,'a'),(110,'2022-11-10 16:54:57',5000,NULL,NULL,'a'),(111,'2022-11-10 16:55:38',45,NULL,NULL,'a'),(112,'2022-11-10 16:57:24',2155,NULL,NULL,'a'),(113,'2022-11-10 17:08:07',5000,NULL,NULL,'a'),(114,'2022-11-11 15:16:54',1191.55,NULL,NULL,'Rami'),(115,'2022-11-11 15:19:55',111.11,NULL,NULL,'Rami'),(116,'2022-11-11 15:20:13',816,NULL,NULL,'Rami'),(117,'2022-11-13 19:54:12',60,NULL,NULL,'Rami'),(118,'2022-11-14 08:45:36',662,NULL,NULL,'root'),(119,'2022-11-14 10:10:43',45,NULL,NULL,'a'),(120,'2022-11-14 10:39:11',186,NULL,NULL,'root'),(121,'2022-12-20 15:37:27',12,NULL,123,'root'),(122,'2022-12-20 15:46:08',12,NULL,5555,'Rami'),(123,'2022-12-20 15:49:58',12,123,555666,'Rami'),(124,'2023-01-03 15:07:53',60,NULL,NULL,'Rami'),(125,'2023-01-03 15:08:41',12786,123,656565,'Rami'),(126,'2023-01-06 08:42:13',5000,NULL,NULL,'a'),(127,'2023-01-09 16:51:14',5000,NULL,NULL,'Bruno'),(128,'2023-01-09 16:55:15',5678,123,12312321,'Bruno'),(129,'2023-01-09 18:00:00',123123,1312313,12312,'Bruno'),(130,'2023-01-19 09:38:16',12,123,55,'a'),(131,'2023-01-19 09:43:56',612,123,789,'a'),(132,'2023-01-19 09:54:10',1140,123,77777,'Rami'),(133,'2023-01-19 09:59:51',55.5,43000000,6,'a'),(134,'2023-01-19 15:58:49',24,NULL,NULL,'Rami'),(135,'2023-01-19 16:00:11',0,NULL,NULL,'Rami'),(136,'2023-01-19 16:03:12',500,NULL,NULL,'Rami'),(137,'2023-01-19 16:04:26',500,NULL,NULL,'Rami'),(138,'2023-01-19 16:05:04',475,123,78,'Rami'),(139,'2023-01-19 16:08:27',9878.075,43000000,789,'Rami'),(140,'2023-01-19 16:11:13',10197.2,43000000,555,'Rami'),(141,'2023-01-19 16:21:31',11.100000000000001,43000000,55,'Rami'),(142,'2023-01-19 16:24:30',4625,43000000,66,'Rami'),(143,'2023-01-19 16:27:10',500,NULL,NULL,'Rami'),(144,'2023-02-03 16:50:09',9500,1312313,5,'Rami'),(145,'2023-02-03 16:57:10',27075,1312313,7985,'Rami'),(146,'2023-02-03 17:00:13',23750,1312313,0,'Rami'),(147,'2023-02-03 17:00:38',25000,NULL,NULL,'Rami'),(148,'2023-02-03 17:03:39',5000,NULL,NULL,'Rami'),(149,'2023-02-08 16:02:26',4750,123,6,'Rami'),(150,'2023-02-08 16:03:13',5000,NULL,NULL,'Rami'),(151,'2023-02-08 16:55:15',5000,NULL,NULL,'Rami'),(152,'2023-02-08 16:55:17',5000,NULL,NULL,'Rami'),(153,'2023-02-08 16:58:38',11.399999999999999,123,55555555,'Rami'),(154,'2023-02-08 18:22:34',0,NULL,NULL,'a'),(155,'2023-02-08 18:26:22',5045,NULL,NULL,'a'),(156,'2023-02-09 14:34:56',5000,NULL,NULL,'a'),(157,'2023-02-09 14:47:39',5000,NULL,NULL,'a'),(164,'2023-03-10 18:41:08',777,NULL,NULL,'a');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-10 18:48:30
