CREATE DATABASE  IF NOT EXISTS `railway` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `railway`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: us-cdbr-east-06.cleardb.net    Database: railway
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
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
  `direccion` varchar(255) NOT NULL,
  PRIMARY KEY (`dni`),
  KEY `cliente_os_fk_idx` (`id_obraSocial`),
  CONSTRAINT `cliente_os_fk` FOREIGN KEY (`id_obraSocial`) REFERENCES `obras_sociales` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (123,'Mollo','Bruno ','medife@gmial.com','12321312','MELINCUE','Santa Fe','1212-12-12',24,'de'),(123123,'Messi','Roberto','fasfa@gmail.com','12312312','ALDEA BELEIRO','Chubut','1998-09-05',34,'12312'),(134123,'Lombardo','Pepito','aasdasd@gmail.com','13123123aaa','PUERTO DESEADO','Santa Cruz','1222-12-02',24,'3 dew febrero21212312'),(1312313,'Maidana','Roberto','aasdasd@gmail.com','12312123','28 DE NOVIEMBRE','Santa Cruz','1222-12-02',14,'3 dew febrero212');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dosis`
--

LOCK TABLES `dosis` WRITE;
/*!40000 ALTER TABLE `dosis` DISABLE KEYS */;
INSERT INTO `dosis` VALUES (94,000002321312,34,'mg'),(134,000000000088,678,'mg'),(154,000034321321,34,'mg'),(364,000000000200,12,'mg');
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
) ENGINE=InnoDB AUTO_INCREMENT=384 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drogas`
--

LOCK TABLES `drogas` WRITE;
/*!40000 ALTER TABLE `drogas` DISABLE KEYS */;
INSERT INTO `drogas` VALUES (94,'ibuprofeno'),(104,'xml de mierda'),(114,'dewdew'),(124,'dwqdwq'),(134,'wer'),(154,'hola'),(174,'ad'),(184,'NuevaDroga'),(364,'KEtamina'),(374,'nnpnpnklnl');
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
) ENGINE=InnoDB AUTO_INCREMENT=314 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratorios`
--

LOCK TABLES `laboratorios` WRITE;
/*!40000 ALTER TABLE `laboratorios` DISABLE KEYS */;
INSERT INTO `laboratorios` VALUES (1,'lab1','1245','asd@gmia.com'),(4,'Pfizer','13123','12@gmail.com'),(74,'Pfizer','123','123333@gmail.com'),(124,'Bayer','349123123','bayer@gmail.com'),(174,'BayerMunich','123123','pep@alg.com'),(224,'lab2','12312','12@gmail.com'),(284,'bayer','123412','bayer@gmail.com'),(304,'labzz','123','la@bz.com');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_ventas`
--

LOCK TABLES `linea_ventas` WRITE;
/*!40000 ALTER TABLE `linea_ventas` DISABLE KEYS */;
INSERT INTO `linea_ventas` VALUES (4,2321312,2,12),(14,2321312,1,12),(24,2321312,1,12),(44,2321312,1,12),(54,1,2,5000),(64,1,1,5000),(64,2321312,101,12),(74,2321312,80,12),(84,88,15,111.11),(94,88,3,111.11),(94,2321312,5,12),(94,34321321,2,45);
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
  PRIMARY KEY (`codigoBarra`),
  KEY `fk_medicamento_laboratorio_idx` (`codigoLaboratorio`),
  CONSTRAINT `fk_medicamento_laboratorio` FOREIGN KEY (`codigoLaboratorio`) REFERENCES `laboratorios` (`codigo`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamentos`
--

LOCK TABLES `medicamentos` WRITE;
/*!40000 ALTER TABLE `medicamentos` DISABLE KEYS */;
INSERT INTO `medicamentos` VALUES (000000000001,1,'IBUMAX',30,'capsulas'),(000000000088,1,'Tad',444,'mg'),(000000000200,124,'Actron',200,'unidades'),(000002321312,1,'Tafirol',300,'unidades'),(000034321321,1,'Atomo',300,'unidades');
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obras_sociales`
--

LOCK TABLES `obras_sociales` WRITE;
/*!40000 ALTER TABLE `obras_sociales` DISABLE KEYS */;
INSERT INTO `obras_sociales` VALUES (14,'Del Sud','132','del@sud.com',5),(24,'Otra OS','345','r@r.com',5),(34,'PARTICULAR','1','N@A.RN',0);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio_medicamento`
--

LOCK TABLES `precio_medicamento` WRITE;
/*!40000 ALTER TABLE `precio_medicamento` DISABLE KEYS */;
INSERT INTO `precio_medicamento` VALUES (000000000001,'2022-10-31',5000),(000000000088,'2022-11-01',23),(000000000088,'2022-12-05',889.79),(000000000088,'2022-12-12',69.69),(000000000088,'2023-01-01',1),(000000000088,'2023-01-02',2),(000000000088,'2023-03-05',87.58),(000000000088,'2023-05-06',111.11),(000000000200,'2022-11-03',2131),(000002321312,'2022-10-30',12),(000034321321,'2022-10-30',45);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
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
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (4,'2022-11-02 14:16:13',24,NULL,NULL,'a'),(14,'2022-11-02 14:24:41',12,NULL,NULL,'a'),(24,'2022-11-02 14:37:59',12,NULL,NULL,'a'),(34,'2022-11-03 11:30:08',0,NULL,NULL,'a'),(44,'2022-11-04 18:47:39',12,NULL,NULL,'a'),(54,'2022-11-04 19:05:26',10000,NULL,NULL,'a'),(64,'2022-11-04 19:53:31',6212,NULL,NULL,'a'),(74,'2022-11-08 10:17:54',960,NULL,NULL,'a'),(84,'2022-11-08 10:19:31',1666.65,NULL,NULL,'Rami'),(94,'2022-11-08 10:22:03',483.33,NULL,NULL,'Rami');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'railway'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-08 19:52:54
