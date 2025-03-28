-- Script de creación de la base de datos RedEmpleo
CREATE DATABASE IF NOT EXISTS redempleo;
USE redempleo;

-- Tabla de empresas
CREATE TABLE IF NOT EXISTS empresas (
    id_empresa INT AUTO_INCREMENT PRIMARY KEY,
    razon_social VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    direccion_fiscal VARCHAR(200) NOT NULL,
    pais VARCHAR(50) NOT NULL
);

-- Tabla de categorías
CREATE TABLE IF NOT EXISTS categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(2000) NULL
);

-- Tabla de perfiles (roles)
CREATE TABLE IF NOT EXISTS perfiles (
    id_perfil INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    username VARCHAR(45) PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled INT NOT NULL DEFAULT 1,
    fecha_registro DATE NOT NULL
);

-- Tabla de relación entre usuarios y perfiles
CREATE TABLE IF NOT EXISTS usuario_perfil (
    username VARCHAR(45) NOT NULL,
    id_perfil INT NOT NULL,
    PRIMARY KEY (username, id_perfil),
    CONSTRAINT FK_usuario_perfil_username FOREIGN KEY (username) REFERENCES usuarios(username) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_usuario_perfil_id_perfil FOREIGN KEY (id_perfil) REFERENCES perfiles(id_perfil) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de vacantes
CREATE TABLE IF NOT EXISTS vacantes (
    id_vacante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha DATE NOT NULL,
    salario DOUBLE NULL,
    estado ENUM('CREADA', 'CANCELADA', 'ASIGNADA') NOT NULL DEFAULT 'CREADA',
    destacado INT NULL DEFAULT 0,
    imagen VARCHAR(250) NULL,
    detalles TEXT NULL,
    id_categoria INT NOT NULL,
    id_empresa INT NOT NULL,
    CONSTRAINT FK_vacantes_categoria FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FK_vacantes_empresa FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Tabla de solicitudes
CREATE TABLE IF NOT EXISTS solicitudes (
    id_solicitud INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    archivo VARCHAR(250) NULL,
    comentarios VARCHAR(2000) NULL,
    estado INT NOT NULL DEFAULT 0,
    id_vacante INT NOT NULL,
    username VARCHAR(45) NOT NULL,
    CONSTRAINT FK_solicitudes_vacante FOREIGN KEY (id_vacante) REFERENCES vacantes(id_vacante) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FK_solicitudes_username FOREIGN KEY (username) REFERENCES usuarios(username) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Índices para mejorar el rendimiento
CREATE INDEX idx_vacantes_estado ON vacantes(estado);
CREATE INDEX idx_vacantes_destacado ON vacantes(destacado);
CREATE INDEX idx_vacantes_categoria ON vacantes(id_categoria);
CREATE INDEX idx_vacantes_empresa ON vacantes(id_empresa);
CREATE INDEX idx_solicitudes_vacante ON solicitudes(id_vacante);
CREATE INDEX idx_solicitudes_usuario ON solicitudes(username);
CREATE INDEX idx_solicitudes_estado ON solicitudes(estado);