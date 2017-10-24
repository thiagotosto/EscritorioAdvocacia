-- MySQL dump 10.16  Distrib 10.1.21-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.1.21-MariaDB-1~xenial

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
-- Table structure for table `advogado`
--

DROP TABLE IF EXISTS `advogado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advogado` (
  `idadvogado` int(11) NOT NULL AUTO_INCREMENT,
  `oab` varchar(45) NOT NULL,
  `idfuncionario` int(11) NOT NULL,
  PRIMARY KEY (`idadvogado`,`idfuncionario`),
  UNIQUE KEY `idadvogado_UNIQUE` (`idadvogado`),
  UNIQUE KEY `oab_UNIQUE` (`oab`),
  UNIQUE KEY `idfuncionario_UNIQUE` (`idfuncionario`),
  CONSTRAINT `fk_advogado_1` FOREIGN KEY (`idfuncionario`) REFERENCES `funcionario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advogado`
--

LOCK TABLES `advogado` WRITE;
/*!40000 ALTER TABLE `advogado` DISABLE KEYS */;
INSERT INTO `advogado` VALUES (1,'123456789',2),(7,'1354689849',11),(8,'95175635489',12),(4,'987654321',8);
/*!40000 ALTER TABLE `advogado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE KEY `idcliente_UNIQUE` (`idcliente`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'William Marinho Tosto','46464678921'),(3,'Marcelo Modolo','13468463164');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `senha_UNIQUE` (`senha`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (2,'Thiago Tosto','201310072611','ttosto','ttostosenha'),(8,'Maria Cristina','201310072613','mariac','mariacsenha'),(11,'Marcelo Modolo','201310072614','modolo','modolosenha'),(12,'Rossiclerio Tosto','201310072612','rossi','rossisenha');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gerente`
--

DROP TABLE IF EXISTS `gerente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gerente` (
  `idgerente` int(11) NOT NULL AUTO_INCREMENT,
  `idfuncionario` int(11) NOT NULL,
  PRIMARY KEY (`idgerente`),
  UNIQUE KEY `idfuncionario_UNIQUE` (`idfuncionario`),
  KEY `fk_gerentes_1_idx` (`idfuncionario`),
  CONSTRAINT `fk_gerentes_1` FOREIGN KEY (`idfuncionario`) REFERENCES `funcionario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gerente`
--

LOCK TABLES `gerente` WRITE;
/*!40000 ALTER TABLE `gerente` DISABLE KEYS */;
INSERT INTO `gerente` VALUES (1,2);
/*!40000 ALTER TABLE `gerente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `motoboy`
--

DROP TABLE IF EXISTS `motoboy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `motoboy` (
  `idmotoboy` int(11) NOT NULL AUTO_INCREMENT,
  `idfuncionario` int(11) NOT NULL,
  PRIMARY KEY (`idmotoboy`),
  UNIQUE KEY `idmotoboy_UNIQUE` (`idmotoboy`),
  UNIQUE KEY `idfuncionario_UNIQUE` (`idfuncionario`),
  CONSTRAINT `fk_motoboy_1` FOREIGN KEY (`idfuncionario`) REFERENCES `funcionario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motoboy`
--

LOCK TABLES `motoboy` WRITE;
/*!40000 ALTER TABLE `motoboy` DISABLE KEYS */;
/*!40000 ALTER TABLE `motoboy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processo`
--

DROP TABLE IF EXISTS `processo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processo` (
  `idprocesso` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) DEFAULT NULL,
  `numero` bigint(20) NOT NULL,
  PRIMARY KEY (`idprocesso`),
  UNIQUE KEY `id_UNIQUE` (`idprocesso`),
  UNIQUE KEY `numero_UNIQUE` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processo`
--

LOCK TABLES `processo` WRITE;
/*!40000 ALTER TABLE `processo` DISABLE KEYS */;
INSERT INTO `processo` VALUES (1,'processo teste',123),(2,'processo2 teste',124),(4,'processo3 teste2',125),(7,'processo4 teste',126);
/*!40000 ALTER TABLE `processo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processo_advogado`
--

DROP TABLE IF EXISTS `processo_advogado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processo_advogado` (
  `idpeticao_advogado` int(11) NOT NULL AUTO_INCREMENT,
  `idadvogado` int(11) NOT NULL,
  `idpeticao` int(11) NOT NULL,
  PRIMARY KEY (`idpeticao_advogado`),
  UNIQUE KEY `idpeticao_advogado_UNIQUE` (`idpeticao_advogado`),
  KEY `fk_peticao_advogado_1_idx` (`idadvogado`),
  KEY `fk_peticao_advogado_2_idx` (`idpeticao`),
  CONSTRAINT `fk_peticao_advogado_1` FOREIGN KEY (`idadvogado`) REFERENCES `advogado` (`idadvogado`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_peticao_advogado_2` FOREIGN KEY (`idpeticao`) REFERENCES `processo` (`idprocesso`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processo_advogado`
--

LOCK TABLES `processo_advogado` WRITE;
/*!40000 ALTER TABLE `processo_advogado` DISABLE KEYS */;
/*!40000 ALTER TABLE `processo_advogado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processo_cliente`
--

DROP TABLE IF EXISTS `processo_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processo_cliente` (
  `idprocesso_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `idprocesso` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL,
  PRIMARY KEY (`idprocesso_cliente`),
  UNIQUE KEY `idprocesso_cliente_UNIQUE` (`idprocesso_cliente`),
  KEY `fk_processo_cliente_1_idx` (`idprocesso`),
  KEY `fk_processo_cliente_2_idx` (`idcliente`),
  CONSTRAINT `fk_processo_cliente_1` FOREIGN KEY (`idprocesso`) REFERENCES `processo` (`idprocesso`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_processo_cliente_2` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processo_cliente`
--

LOCK TABLES `processo_cliente` WRITE;
/*!40000 ALTER TABLE `processo_cliente` DISABLE KEYS */;
INSERT INTO `processo_cliente` VALUES (1,1,1),(2,1,3),(4,2,3);
/*!40000 ALTER TABLE `processo_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processo_documento`
--

DROP TABLE IF EXISTS `processo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processo_documento` (
  `idprocesso` int(11) NOT NULL,
  `caminho` varchar(260) NOT NULL,
  `idprocesso_documento` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idprocesso_documento`),
  UNIQUE KEY `idprocesso_advogado_UNIQUE` (`idprocesso_documento`),
  KEY `fk_processo_documentos_1_idx` (`idprocesso`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processo_documento`
--

LOCK TABLES `processo_documento` WRITE;
/*!40000 ALTER TABLE `processo_documento` DISABLE KEYS */;
INSERT INTO `processo_documento` VALUES (1,'/teste/caminho',1,NULL),(1,'/teste/caminho2',2,NULL),(2,'/teste/caminho3',3,NULL),(4,'/teste/caminho4',6,'pdf4'),(4,'/teste/caminho5',7,NULL),(7,'/teste/caminhos6',12,'pdf6'),(7,'/teste/caminhos7',13,'doc7');
/*!40000 ALTER TABLE `processo_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secretaria`
--

DROP TABLE IF EXISTS `secretaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `secretaria` (
  `idsecretaria` int(11) NOT NULL AUTO_INCREMENT,
  `idfuncionario` int(11) NOT NULL,
  PRIMARY KEY (`idsecretaria`),
  UNIQUE KEY `idsecretaria_UNIQUE` (`idsecretaria`),
  UNIQUE KEY `idfuncionario_UNIQUE` (`idfuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secretaria`
--

LOCK TABLES `secretaria` WRITE;
/*!40000 ALTER TABLE `secretaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `secretaria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-24 19:57:13
