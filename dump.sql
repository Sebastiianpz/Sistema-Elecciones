CREATE DATABASE IF NOT EXISTS padron
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE padron;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(64) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS personas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nro_documento VARCHAR(20) UNIQUE NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    sexo CHAR(1) CHECK (sexo IN ('M','F','X')),
    domicilio VARCHAR(200),
    habilitado_votar BOOLEAN DEFAULT TRUE,
    fecha_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS imagenes_dni (
    id INT PRIMARY KEY AUTO_INCREMENT,
    persona_id INT NOT NULL,
    nombre_archivo VARCHAR(255),
    contenido LONGBLOB,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (persona_id)
        REFERENCES personas(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_nro_doc
ON personas(nro_documento);

INSERT INTO usuarios (username, password)
SELECT 'admin', 'admin123'
WHERE NOT EXISTS (
    SELECT *
    FROM usuarios
    WHERE username = 'admin'
);