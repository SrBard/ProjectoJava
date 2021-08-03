-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Versión del servidor: 5.7.26
-- Versión de PHP: 7.2.18 

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `company`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clients`
--

create database company;
use company;

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `clientsid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`clientsid`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clients`
--

INSERT INTO `clients` (`clientsid`, `name`, `phone`, `address`) VALUES
(1, 'Jimmy Jones', '1234567890', 'Victoria Pavilion, Las Vegas, NV'),
(3, 'Henry Clark', '0987654321', '4125 Sydney Place, Miami, FL'),
(4, 'Ruby Young', '1234512345', '6170 Peshwar Place, Washington, DC'),
(6, 'Julia King', '0987612345', 'Mountain View Hospital, Victoria, TX');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `employeid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `jobtitle` varchar(50) NOT NULL,
  `salary` float DEFAULT NULL,
  PRIMARY KEY (`employeid`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `employees`
--

INSERT INTO `employees` (`employeid`, `name`, `jobtitle`, `salary`) VALUES
(1, AES_ENCRYPT('John Doe','llave'), 'Web Developer', 3500),
(2, AES_ENCRYPT('Mary Smith','llave'), 'Web Developer', 3500),
(3, AES_ENCRYPT('James Brown','llave'), 'Web Developer', 3500),
(4,AES_ENCRYPT('Mike Walker','llave'), 'Web Developer', 3500),
(5, AES_ENCRYPT('Linda Jones','llave'), 'Web Developer', 3500),
(6, AES_ENCRYPT('John Doe','llave'), 'Systems Administrator', 3400),
(7, AES_ENCRYPT('James Smith','llave'), 'Systems Administrator', 3400),
(8, AES_ENCRYPT('John Brown','llave'), 'Systems Administrator', 3400),
(10, AES_ENCRYPT('Daniel Jones','llave'), 'Systems Administrator', 3400),
(11, AES_ENCRYPT('Paul Anderson','llave'), 'Systems Administrator', 3400),
(12, AES_ENCRYPT('Mark Davis','llave'), 'IT Support Manager', 3200),
(13, AES_ENCRYPT('Steven Thomas','llave'), 'IT Support Manager', 3200),
(15, AES_ENCRYPT('Brian King','llave'), 'IT Support Manager', 3200),
(16, AES_ENCRYPT('Kevin Hall','llave'), 'IT Support Manager', 3200),
(17, AES_ENCRYPT('Jason Lee','llave'), 'IT Support Manager', 3200),
(18, AES_ENCRYPT('Jose Young','llave'), 'Database Administrator', 3700),
(19, AES_ENCRYPT('Frank Smith','llave'), 'Database Administrator', 3700),
(20, AES_ENCRYPT('Eric Jones','llave'), 'Database Administrator', 3700),
(21, AES_ENCRYPT('Jerry Martin','llave'), 'Database Administrator', 3700),
(23, AES_ENCRYPT('Peter King','llave'), 'Database Administrator', 3700),
(24, AES_ENCRYPT('Henry Clark','llave'), 'Application Developer', 3800),
(25, AES_ENCRYPT('Carl White','llave'), 'Application Developer', 3800),
(26, AES_ENCRYPT('Joe Thomas','llave'), 'Application Developer', 3800),
(27, AES_ENCRYPT('Jack Smith','llave'), 'Application Developer', 3800),
(28, AES_ENCRYPT('Roy King','llave'), 'Application Developer', 3800);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
