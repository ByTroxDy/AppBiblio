-- MySQL dump 10.13  Distrib 5.7.34, for Linux (x86_64)
--
-- Host: 10.2.18.166    Database: app_biblioteca
-- ------------------------------------------------------
-- Server version	8.0.33-0ubuntu0.20.04.2

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
-- Table structure for table `autores`
--

DROP TABLE IF EXISTS `autores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autores` (
  `id_autor` int NOT NULL AUTO_INCREMENT,
  `nombre_autor` varchar(45) NOT NULL,
  PRIMARY KEY (`id_autor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autores`
--

LOCK TABLES `autores` WRITE;
/*!40000 ALTER TABLE `autores` DISABLE KEYS */;
/*!40000 ALTER TABLE `autores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bibliotecas`
--

DROP TABLE IF EXISTS `bibliotecas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bibliotecas` (
  `cp_biblioteca` int NOT NULL,
  `nombre_biblioteca` varchar(45) NOT NULL,
  PRIMARY KEY (`cp_biblioteca`),
  KEY `nombre_biblioteca` (`nombre_biblioteca`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibliotecas`
--

LOCK TABLES `bibliotecas` WRITE;
/*!40000 ALTER TABLE `bibliotecas` DISABLE KEYS */;
INSERT INTO `bibliotecas` VALUES (12580,'Benicarlo'),(12500,'Vinaros');
/*!40000 ALTER TABLE `bibliotecas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(45) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentarios` (
  `id_comentarios` int NOT NULL AUTO_INCREMENT,
  `isbn` int NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `optinion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_comentarios`,`isbn`,`usuario`),
  KEY `fk_comentarios_documentos1_idx` (`isbn`),
  KEY `fk_comentarios_usuarios1_idx` (`usuario`),
  CONSTRAINT `fk_comentarios_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`),
  CONSTRAINT `fk_comentarios_usuarios1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentales`
--

DROP TABLE IF EXISTS `documentales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentales` (
  `isbn` int NOT NULL,
  `productora` varchar(45) NOT NULL,
  `premios` varchar(45) DEFAULT NULL,
  `doc_relacionados` varchar(45) DEFAULT NULL,
  `duracion` decimal(10,0) NOT NULL,
  `formato` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  KEY `fk_documentales_documentos1_idx` (`isbn`),
  CONSTRAINT `fk_documentales_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentales`
--

LOCK TABLES `documentales` WRITE;
/*!40000 ALTER TABLE `documentales` DISABLE KEYS */;
INSERT INTO `documentales` VALUES (123,'Donal Tramp',NULL,NULL,155,NULL);
/*!40000 ALTER TABLE `documentales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentos`
--

DROP TABLE IF EXISTS `documentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentos` (
  `isbn` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `autor` varchar(45) NOT NULL,
  `replicas` int NOT NULL DEFAULT '1',
  `biblioteca` varchar(45) NOT NULL,
  `fecha_alta` date DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`isbn`,`biblioteca`) USING BTREE,
  KEY `fk_documentos_bibliotecas1_idx` (`biblioteca`),
  CONSTRAINT `fk_documentos_bibliotecas1` FOREIGN KEY (`biblioteca`) REFERENCES `bibliotecas` (`nombre_biblioteca`)
) ENGINE=InnoDB AUTO_INCREMENT=123455790 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentos`
--

LOCK TABLES `documentos` WRITE;
/*!40000 ALTER TABLE `documentos` DISABLE KEYS */;
INSERT INTO `documentos` VALUES (1,'adada','asdasd',0,'Benicarlo',NULL,NULL),(5,'a','a',4,'Benicarlo','2023-05-31',NULL),(123455789,'Morghi','Ayoub',5,'Benicarlo','2023-05-30',NULL);
/*!40000 ALTER TABLE `documentos` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`biblio_admin`@`%`*/ /*!50003 TRIGGER `altaFecha` BEFORE INSERT ON `documentos` FOR EACH ROW SET NEW.fecha_alta = CURRENT_DATE */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `documentos_has_autores`
--

DROP TABLE IF EXISTS `documentos_has_autores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentos_has_autores` (
  `documentos_isbn` int NOT NULL,
  `autores_id_autor` int NOT NULL,
  PRIMARY KEY (`documentos_isbn`,`autores_id_autor`),
  KEY `fk_documentos_has_autores_autores1_idx` (`autores_id_autor`),
  KEY `fk_documentos_has_autores_documentos_idx` (`documentos_isbn`),
  CONSTRAINT `fk_documentos_has_autores_autores1` FOREIGN KEY (`autores_id_autor`) REFERENCES `autores` (`id_autor`),
  CONSTRAINT `fk_documentos_has_autores_documentos` FOREIGN KEY (`documentos_isbn`) REFERENCES `documentos` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentos_has_autores`
--

LOCK TABLES `documentos_has_autores` WRITE;
/*!40000 ALTER TABLE `documentos_has_autores` DISABLE KEYS */;
/*!40000 ALTER TABLE `documentos_has_autores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentos_has_categorias`
--

DROP TABLE IF EXISTS `documentos_has_categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentos_has_categorias` (
  `documentos_isbn` int NOT NULL,
  `categorias_id_categoria` int NOT NULL,
  PRIMARY KEY (`documentos_isbn`,`categorias_id_categoria`),
  KEY `fk_documentos_has_categorias_categorias1_idx` (`categorias_id_categoria`),
  KEY `fk_documentos_has_categorias_documentos1_idx` (`documentos_isbn`),
  CONSTRAINT `fk_documentos_has_categorias_categorias1` FOREIGN KEY (`categorias_id_categoria`) REFERENCES `categorias` (`id_categoria`),
  CONSTRAINT `fk_documentos_has_categorias_documentos1` FOREIGN KEY (`documentos_isbn`) REFERENCES `documentos` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentos_has_categorias`
--

LOCK TABLES `documentos_has_categorias` WRITE;
/*!40000 ALTER TABLE `documentos_has_categorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `documentos_has_categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupos` (
  `id_grupo` int NOT NULL AUTO_INCREMENT,
  `nombre_grupo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_grupo`),
  KEY `nombre_grupo` (`nombre_grupo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (1,'admin'),(2,'gestor'),(3,'socio');
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libros` (
  `isbn` int NOT NULL,
  `editorial` varchar(45) NOT NULL,
  `npaginas` int NOT NULL,
  `tematica` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  KEY `fk_libros_documentos1_idx` (`isbn`),
  CONSTRAINT `fk_libros_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,'aasda',125,'Ciencias'),(5,'asdas',123,'Ciencias'),(123455789,'elLib',95,'Literatura');
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musica`
--

DROP TABLE IF EXISTS `musica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `musica` (
  `isbn` int NOT NULL,
  `lugar` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `duracion` decimal(10,0) NOT NULL,
  `formato` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  KEY `fk_musica_documentos1_idx` (`isbn`),
  CONSTRAINT `fk_musica_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica`
--

LOCK TABLES `musica` WRITE;
/*!40000 ALTER TABLE `musica` DISABLE KEYS */;
/*!40000 ALTER TABLE `musica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculas`
--

DROP TABLE IF EXISTS `peliculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `peliculas` (
  `isbn` int NOT NULL,
  `director` varchar(45) NOT NULL,
  `actores` varchar(45) NOT NULL,
  `premios` varchar(45) DEFAULT NULL,
  `duracion` decimal(10,0) NOT NULL,
  `formato` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  KEY `fk_peliculas_documentos1_idx` (`isbn`),
  CONSTRAINT `fk_peliculas_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
/*!40000 ALTER TABLE `peliculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prestamos` (
  `id_prestamo` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `isbn` int NOT NULL,
  `fecha_prestamo` date NOT NULL,
  `fecha_devolucion` date NOT NULL,
  `dias_retardo` int DEFAULT NULL,
  PRIMARY KEY (`id_prestamo`,`usuario`,`isbn`),
  KEY `fk_prestamos_usuarios1_idx` (`usuario`),
  KEY `fk_prestamos_documentos1_idx` (`isbn`),
  CONSTRAINT `fk_prestamos_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`),
  CONSTRAINT `fk_prestamos_usuarios1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (1,'socio',123,'2023-05-24','2023-06-08',NULL),(2,'socio',123,'2023-05-24','2023-06-08',NULL),(3,'socio',123,'2023-05-24','2023-06-08',NULL),(6,'socio',1,'2023-05-30','2023-06-14',NULL);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservas` (
  `id_reserva` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `isbn` int NOT NULL,
  `fecha_reserva` date NOT NULL,
  `dias_pendientes` int DEFAULT NULL,
  PRIMARY KEY (`id_reserva`,`usuario`,`isbn`),
  KEY `fk_reservas_usuarios1_idx` (`usuario`),
  KEY `fk_reservas_documentos1_idx` (`isbn`),
  CONSTRAINT `fk_reservas_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`),
  CONSTRAINT `fk_reservas_usuarios1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,'socio',123,'2023-05-24',NULL),(2,'socio',55,'2023-05-24',NULL),(3,'socio',1,'2023-05-25',NULL);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `baneado` tinyint DEFAULT '0',
  PRIMARY KEY (`id_usuario`,`rol`),
  KEY `fk_usuarios_grupos1_idx` (`rol`) /*!80000 INVISIBLE */,
  KEY `usuario` (`usuario`) USING BTREE,
  CONSTRAINT `fk_usuarios_grupos1` FOREIGN KEY (`rol`) REFERENCES `grupos` (`nombre_grupo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'socio','socio','socio',NULL,0),(2,'hmngjhgh','jghjhgjhgj','socio',NULL,0),(3,'','','socio',NULL,0),(4,'admin','admin','admin',NULL,0),(5,'gestor','gestor','gestor',NULL,0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-31 19:39:00
