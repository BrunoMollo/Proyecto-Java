create database IF NOT EXISTS farmacia;
use farmacia;

-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: farmacia
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.22.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
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
  `idObraSocial` int NOT NULL,
  `dniCliente` int NOT NULL,
  PRIMARY KEY (`numeroAfiliado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
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
  `dni` int NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `localidad` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `fechaNac` date NOT NULL,
  `id_obraSocial` int NOT NULL,
  `nroAfiliado` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) NOT NULL,
  PRIMARY KEY (`dni`),
  KEY `cliente_os_fk_idx` (`id_obraSocial`),
  CONSTRAINT `cliente_os_fk` FOREIGN KEY (`id_obraSocial`) REFERENCES `obras_sociales` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (43313296,'Mollo','Bruno','dia.mollo.bruno@gmail.com','3364383712','SAN NICOLAS DE LOS ARROYOS','Buenos Aires','2001-08-12',84,'3-06185333-04|','Sabin 1134');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dosis`
--

DROP TABLE IF EXISTS `dosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dosis` (
  `codigoDroga` int NOT NULL,
  `codigoMedicamento` varchar(20) NOT NULL,
  `cantidad` double NOT NULL,
  `unidad` varchar(2) NOT NULL,
  PRIMARY KEY (`codigoDroga`,`codigoMedicamento`),
  KEY `fk_dosis_medicamento_idx` (`codigoMedicamento`),
  CONSTRAINT `dosis_FK` FOREIGN KEY (`codigoMedicamento`) REFERENCES `medicamentos` (`codigoBarra`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_dosis_droga` FOREIGN KEY (`codigoDroga`) REFERENCES `drogas` (`codigo`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dosis`
--

LOCK TABLES `dosis` WRITE;
/*!40000 ALTER TABLE `dosis` DISABLE KEYS */;
INSERT INTO `dosis` VALUES (444,'779815398087',400,'mg'),(445,'779815398087',200,'mg'),(446,'7790072001038',150,'mg');
/*!40000 ALTER TABLE `dosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drogas`
--

DROP TABLE IF EXISTS `drogas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drogas` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=447 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drogas`
--

LOCK TABLES `drogas` WRITE;
/*!40000 ALTER TABLE `drogas` DISABLE KEYS */;
INSERT INTO `drogas` VALUES (444,'Ibuprofeno'),(445,'Diclofenac'),(446,'Levotiroxina');
/*!40000 ALTER TABLE `drogas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratorios`
--

DROP TABLE IF EXISTS `laboratorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laboratorios` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(25) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=345 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratorios`
--

LOCK TABLES `laboratorios` WRITE;
/*!40000 ALTER TABLE `laboratorios` DISABLE KEYS */;
INSERT INTO `laboratorios` VALUES (344,'Labo','444444444','lab@contacto.com');
/*!40000 ALTER TABLE `laboratorios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_ventas`
--

DROP TABLE IF EXISTS `linea_ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `linea_ventas` (
  `nroVenta` int NOT NULL,
  `codBarra` varchar(20) NOT NULL,
  `cantidad` int DEFAULT NULL,
  `precioUnidad` double NOT NULL,
  PRIMARY KEY (`nroVenta`,`codBarra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_ventas`
--

LOCK TABLES `linea_ventas` WRITE;
/*!40000 ALTER TABLE `linea_ventas` DISABLE KEYS */;
INSERT INTO `linea_ventas` VALUES (175,'779815398087',2,1200),(176,'7790072001038',1,280);
/*!40000 ALTER TABLE `linea_ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamentos`
--

DROP TABLE IF EXISTS `medicamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicamentos` (
  `codigoBarra` varchar(20) NOT NULL,
  `codigoLaboratorio` int NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `size` double NOT NULL,
  `unidad` varchar(25) NOT NULL,
  `baja` datetime DEFAULT NULL,
  PRIMARY KEY (`codigoBarra`),
  KEY `fk_medicamento_laboratorio_idx` (`codigoLaboratorio`),
  KEY `index_nombre` (`nombre`),
  CONSTRAINT `fk_medicamento_laboratorio` FOREIGN KEY (`codigoLaboratorio`) REFERENCES `laboratorios` (`codigo`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamentos`
--

LOCK TABLES `medicamentos` WRITE;
/*!40000 ALTER TABLE `medicamentos` DISABLE KEYS */;
INSERT INTO `medicamentos` VALUES ('7790072001038',344,'Euthyrox',50,'dosis',NULL),('779815398087',344,'Ibumax',50,'unidades',NULL);
/*!40000 ALTER TABLE `medicamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obras_sociales`
--

DROP TABLE IF EXISTS `obras_sociales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obras_sociales` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `descuentoGeneral` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obras_sociales`
--

LOCK TABLES `obras_sociales` WRITE;
/*!40000 ALTER TABLE `obras_sociales` DISABLE KEYS */;
INSERT INTO `obras_sociales` VALUES (84,'Medife','341123456','dia.mollo.bruno@gmail.com',30);
/*!40000 ALTER TABLE `obras_sociales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio_medicamento`
--

DROP TABLE IF EXISTS `precio_medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precio_medicamento` (
  `codigoMedicamento` varchar(20) NOT NULL,
  `fecha` date NOT NULL,
  `monto` double NOT NULL,
  PRIMARY KEY (`codigoMedicamento`,`fecha`),
  CONSTRAINT `precio_medicamento_FK` FOREIGN KEY (`codigoMedicamento`) REFERENCES `medicamentos` (`codigoBarra`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio_medicamento`
--

LOCK TABLES `precio_medicamento` WRITE;
/*!40000 ALTER TABLE `precio_medicamento` DISABLE KEYS */;
INSERT INTO `precio_medicamento` VALUES ('7790072001038','2023-03-14',400),('779815398087','2023-03-14',1000),('779815398087','2023-03-15',1200);
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
  `rol` int NOT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('root','root','Juancito','Administrador',0),('sell','sell','Pepito','Vendedor',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `nroVenta` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `total` double NOT NULL,
  `dniCLiente` int DEFAULT NULL,
  `nroReceta` int DEFAULT NULL,
  `vendidoPor` varchar(50) NOT NULL,
  PRIMARY KEY (`nroVenta`),
  KEY `fk_venta_usuario_idx` (`vendidoPor`),
  CONSTRAINT `fk_venta_usuario` FOREIGN KEY (`vendidoPor`) REFERENCES `usuarios` (`usuario`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (174,'2023-03-14 15:56:53',2400,NULL,NULL,'root'),(175,'2023-03-14 15:57:47',2400,NULL,NULL,'root'),(176,'2023-03-14 15:59:38',280,43313296,7873212,'root');
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

-- Dump completed on 2023-03-14 16:28:38
