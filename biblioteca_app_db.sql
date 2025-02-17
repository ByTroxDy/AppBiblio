-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 13-06-2023 a las 12:58:07
-- Versión del servidor: 8.0.33
-- Versión de PHP: 7.4.3-4ubuntu2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `app_biblioteca`
--
CREATE DATABASE IF NOT EXISTS `app_biblioteca` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `app_biblioteca`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autores`
--

CREATE TABLE `autores` (
  `id_autor` int NOT NULL,
  `nombre_autor` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bibliotecas`
--

CREATE TABLE `bibliotecas` (
  `cp_biblioteca` int NOT NULL,
  `nombre_biblioteca` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `bibliotecas`
--

INSERT INTO `bibliotecas` (`cp_biblioteca`, `nombre_biblioteca`) VALUES
(12580, 'Benicarlo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id_categoria` int NOT NULL,
  `nombre_categoria` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `id_comentarios` int NOT NULL,
  `isbn` int NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `optinion` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentales`
--

CREATE TABLE `documentales` (
  `isbn` int NOT NULL,
  `productora` varchar(45) NOT NULL,
  `premios` varchar(45) DEFAULT NULL,
  `doc_relacionados` varchar(45) DEFAULT NULL,
  `duracion` int NOT NULL,
  `formato` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `documentales`
--

INSERT INTO `documentales` (`isbn`, `productora`, `premios`, `doc_relacionados`, `duracion`, `formato`) VALUES
(3, '33', '33', '33', 33, 'Físico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentos`
--

CREATE TABLE `documentos` (
  `isbn` int NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `titulo` varchar(45) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `replicas` int NOT NULL DEFAULT '1',
  `biblioteca` varchar(45) NOT NULL,
  `fecha_alta` date DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `documentos`
--

INSERT INTO `documentos` (`isbn`, `tipo`, `titulo`, `autor`, `replicas`, `biblioteca`, `fecha_alta`, `fecha_baja`) VALUES
(1, 'Llibre', '11', '11', 11, 'Benicarlo', '2023-06-13', NULL),
(2, 'Pel·lícula', '22', '22', 22, 'Benicarlo', '2023-06-13', NULL),
(3, 'Documental', '33', '33', 33, 'Benicarlo', '2023-06-13', NULL),
(4, 'Música', '44', '44', 44, 'Benicarlo', '2023-06-13', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentos_has_autores`
--

CREATE TABLE `documentos_has_autores` (
  `documentos_isbn` int NOT NULL,
  `autores_id_autor` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentos_has_categorias`
--

CREATE TABLE `documentos_has_categorias` (
  `documentos_isbn` int NOT NULL,
  `categorias_id_categoria` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos`
--

CREATE TABLE `grupos` (
  `id_grupo` int NOT NULL,
  `nombre_grupo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `grupos`
--

INSERT INTO `grupos` (`id_grupo`, `nombre_grupo`) VALUES
(3, 'admin'),
(2, 'gestor'),
(1, 'socio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `isbn` int NOT NULL,
  `editorial` varchar(45) NOT NULL,
  `npaginas` int NOT NULL,
  `tematica` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`isbn`, `editorial`, `npaginas`, `tematica`) VALUES
(1, '11', 11, 'Ciencies');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `musica`
--

CREATE TABLE `musica` (
  `isbn` int NOT NULL,
  `lugar` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `duracion` int NOT NULL,
  `formato` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `musica`
--

INSERT INTO `musica` (`isbn`, `lugar`, `fecha`, `duracion`, `formato`) VALUES
(4, '44', '2023-06-13', 44, 'Digital');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

CREATE TABLE `peliculas` (
  `isbn` int NOT NULL,
  `director` varchar(45) NOT NULL,
  `actores` varchar(45) NOT NULL,
  `premios` varchar(45) DEFAULT NULL,
  `duracion` int NOT NULL,
  `formato` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`isbn`, `director`, `actores`, `premios`, `duracion`, `formato`) VALUES
(2, '22', '22', '22', 22, 'Digital');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `id_prestamo` int NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `isbn` int NOT NULL,
  `fecha_prestamo` date NOT NULL,
  `fecha_devolucion` date NOT NULL,
  `dias_retardo` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id_reserva` int NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `isbn` int NOT NULL,
  `fecha_reserva` date NOT NULL,
  `dias_pendientes` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sancion` int DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `usuario`, `password`, `rol`, `email`, `sancion`) VALUES
(1, 'socio', 'socio', 'socio', 'socio@app.com', 0),
(2, 'gestor', 'gestor', 'gestor', 'gestor@app.com', 0),
(3, 'admin', 'admin', 'admin', 'admin@app.com', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autores`
--
ALTER TABLE `autores`
  ADD PRIMARY KEY (`id_autor`);

--
-- Indices de la tabla `bibliotecas`
--
ALTER TABLE `bibliotecas`
  ADD PRIMARY KEY (`cp_biblioteca`),
  ADD KEY `nombre_biblioteca` (`nombre_biblioteca`) USING BTREE;

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`id_comentarios`,`isbn`,`usuario`),
  ADD KEY `fk_comentarios_documentos1_idx` (`isbn`),
  ADD KEY `fk_comentarios_usuarios1_idx` (`usuario`);

--
-- Indices de la tabla `documentales`
--
ALTER TABLE `documentales`
  ADD PRIMARY KEY (`isbn`),
  ADD KEY `fk_documentales_documentos1_idx` (`isbn`);

--
-- Indices de la tabla `documentos`
--
ALTER TABLE `documentos`
  ADD PRIMARY KEY (`isbn`,`biblioteca`),
  ADD KEY `fk_documentos_bibliotecas1_idx` (`biblioteca`);

--
-- Indices de la tabla `documentos_has_autores`
--
ALTER TABLE `documentos_has_autores`
  ADD PRIMARY KEY (`documentos_isbn`,`autores_id_autor`),
  ADD KEY `fk_documentos_has_autores_autores1_idx` (`autores_id_autor`),
  ADD KEY `fk_documentos_has_autores_documentos_idx` (`documentos_isbn`);

--
-- Indices de la tabla `documentos_has_categorias`
--
ALTER TABLE `documentos_has_categorias`
  ADD PRIMARY KEY (`documentos_isbn`,`categorias_id_categoria`),
  ADD KEY `fk_documentos_has_categorias_categorias1_idx` (`categorias_id_categoria`),
  ADD KEY `fk_documentos_has_categorias_documentos1_idx` (`documentos_isbn`);

--
-- Indices de la tabla `grupos`
--
ALTER TABLE `grupos`
  ADD PRIMARY KEY (`id_grupo`),
  ADD KEY `nombre_grupo` (`nombre_grupo`) USING BTREE;

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`isbn`),
  ADD KEY `fk_libros_documentos1_idx` (`isbn`);

--
-- Indices de la tabla `musica`
--
ALTER TABLE `musica`
  ADD PRIMARY KEY (`isbn`),
  ADD KEY `fk_musica_documentos1_idx` (`isbn`);

--
-- Indices de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD PRIMARY KEY (`isbn`),
  ADD KEY `fk_peliculas_documentos1_idx` (`isbn`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`id_prestamo`,`usuario`,`isbn`),
  ADD KEY `fk_prestamos_usuarios1_idx` (`usuario`),
  ADD KEY `fk_prestamos_documentos1_idx` (`isbn`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reserva`,`usuario`,`isbn`),
  ADD KEY `fk_reservas_usuarios1_idx` (`usuario`),
  ADD KEY `fk_reservas_documentos1_idx` (`isbn`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`,`usuario`,`rol`),
  ADD UNIQUE KEY `usuario` (`usuario`) USING BTREE,
  ADD KEY `fk_usuarios_grupos1_idx` (`rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `autores`
--
ALTER TABLE `autores`
  MODIFY `id_autor` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id_categoria` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `id_comentarios` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `documentos`
--
ALTER TABLE `documentos`
  MODIFY `isbn` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8799;

--
-- AUTO_INCREMENT de la tabla `grupos`
--
ALTER TABLE `grupos`
  MODIFY `id_grupo` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `id_prestamo` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id_reserva` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `fk_comentarios_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `documentales`
--
ALTER TABLE `documentales`
  ADD CONSTRAINT `fk_documentales_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `documentos_has_autores`
--
ALTER TABLE `documentos_has_autores`
  ADD CONSTRAINT `fk_documentos_has_autores_autores1` FOREIGN KEY (`autores_id_autor`) REFERENCES `autores` (`id_autor`),
  ADD CONSTRAINT `fk_documentos_has_autores_documentos` FOREIGN KEY (`documentos_isbn`) REFERENCES `documentos` (`isbn`);

--
-- Filtros para la tabla `documentos_has_categorias`
--
ALTER TABLE `documentos_has_categorias`
  ADD CONSTRAINT `fk_documentos_has_categorias_categorias1` FOREIGN KEY (`categorias_id_categoria`) REFERENCES `categorias` (`id_categoria`),
  ADD CONSTRAINT `fk_documentos_has_categorias_documentos1` FOREIGN KEY (`documentos_isbn`) REFERENCES `documentos` (`isbn`);

--
-- Filtros para la tabla `libros`
--
ALTER TABLE `libros`
  ADD CONSTRAINT `fk_libros_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `musica`
--
ALTER TABLE `musica`
  ADD CONSTRAINT `fk_musica_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD CONSTRAINT `fk_peliculas_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `fk_prestamos_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_prestamos_usuarios1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `fk_reservas_documentos1` FOREIGN KEY (`isbn`) REFERENCES `documentos` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_reservas_usuarios1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Eventos
--
CREATE EVENT `actualizar_dias_pendientes` ON SCHEDULE EVERY 30 MINUTE STARTS '2023-06-05 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE reservas r
    SET r.dias_pendientes = (
        SELECT GREATEST(DATEDIFF(p.fecha_devolucion, CURDATE()), 0)
        FROM prestamos p
        WHERE p.isbn = r.isbn
        ORDER BY ABS(DATEDIFF(p.fecha_devolucion, CURDATE()))
        LIMIT 1
    )$$

CREATE EVENT `actualizar_dias_retraso` ON SCHEDULE EVERY 30 MINUTE STARTS '2023-06-05 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    -- Actualizar la tabla de préstamos para calcular los días de retraso y aplicar la penalización
    UPDATE prestamos
    SET dias_retardo = DATEDIFF(CURDATE(), fecha_devolucion)
    WHERE fecha_devolucion < CURDATE() AND dias_retardo IS NULL;
END$$

CREATE EVENT `actualizar_sancion_diaria` ON SCHEDULE EVERY 30 MINUTE STARTS '2023-06-05 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
  UPDATE usuarios
  SET sancion = sancion - 1
  WHERE sancion > 0;
END$$

CREATE EVENT `aplicar_penalizacion` ON SCHEDULE EVERY 30 MINUTE STARTS '2023-06-05 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
	DECLARE retardo INT;
    
    SELECT DATEDIFF(CURRENT_DATE, fecha_devolucion) INTO retardo FROM prestamos WHERE fecha_devolucion < CURRENT_DATE;

    -- Aplicar la penalización a los usuarios con retraso
    UPDATE usuarios
    SET sancion = sancion + (retardo * 5)
    WHERE usuario IN (SELECT DISTINCT usuario FROM prestamos WHERE dias_retardo > 0);
END$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
