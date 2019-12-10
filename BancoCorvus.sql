-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-12-2019 a las 16:36:31
-- Versión del servidor: 10.4.10-MariaDB
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bancocorvus`
--
DROP DATABASE IF EXISTS `bancocorvus`;
CREATE DATABASE IF NOT EXISTS `bancocorvus` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `bancocorvus`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `Id` int(10) NOT NULL,
  `Nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `ApellidoP` varchar(50) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `ApellidoM` varchar(50) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `NoCuenta` int(10) NOT NULL DEFAULT 0,
  `Saldo` float NOT NULL DEFAULT 0,
  `Calle` varchar(100) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `Colonia` varchar(100) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `Municipio` varchar(100) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `Pais` varchar(100) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `CP` varchar(10) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `Telefono` varchar(20) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `Prestamos` tinyint(1) NOT NULL DEFAULT 0,
  `TarjetaD` tinyint(1) NOT NULL DEFAULT 0,
  `TarjetaC` tinyint(1) NOT NULL DEFAULT 0,
  `Usuario` varchar(100) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `Password` varchar(100) COLLATE utf8_spanish_ci NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Id`, `Nombre`, `ApellidoP`, `ApellidoM`, `NoCuenta`, `Saldo`, `Calle`, `Colonia`, `Municipio`, `Pais`, `CP`, `Telefono`, `Prestamos`, `TarjetaD`, `TarjetaC`, `Usuario`, `Password`) VALUES
(1, 'Frank', 'Ramon', 'Pork', 1, 1000, 'Be alive #451', 'Le colomos', 'Zapopan', 'Mexico', '45525', '', 1, 1, 0, 'Frank12', '123456');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `UNIQUE_USERNAME` (`Usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
