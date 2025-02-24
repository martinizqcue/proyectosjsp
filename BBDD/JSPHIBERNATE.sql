drop database if exists jsphibernate;
create database jsphibernate;
use jsphibernate;
CREATE TABLE IF NOT EXISTS Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    rol ENUM('admin', 'invitado') NOT NULL
);

CREATE TABLE IF NOT EXISTS Proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proyecto VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_inicio DATE,
    fecha_fin DATE,
    estado ENUM('en curso', 'completado') NOT NULL,
    idUsuario INT,
    FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);


CREATE TABLE IF NOT EXISTS Tareas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idProyecto INT,
    descripcion_tarea TEXT,
    responsable VARCHAR(255),
    fecha_inicio DATE,
    fecha_fin DATE,
    estado ENUM('pendiente', 'en progreso', 'completada') NOT NULL,
    idUsuario INT,
    FOREIGN KEY (idProyecto) REFERENCES proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);

INSERT INTO Usuarios (nombre_usuario, email, contraseña, rol)
VALUES ('admin', 'admin@gmail.com', 'admin', 'admin'),
       ('invitado', 'invitado@gmail.com', 'invitado', 'invitado');



-- Insertar un proyecto asignado a un usuario con id 1 (por ejemplo, admin)
INSERT INTO Proyectos (nombre_proyecto, descripcion, fecha_inicio, fecha_fin, estado, idUsuario)
VALUES ('Proyecto de Gestión', 'Descripción del proyecto', '2025-02-01', '2025-05-01', 'en curso', 1);

-- Insertar una tarea asignada a un usuario con id 2 (por ejemplo, invitado)
INSERT INTO Tareas (idProyecto, descripcion_tarea, responsable, fecha_inicio, fecha_fin, estado, idUsuario)
VALUES (1, 'Realizar la planificación', 'Juan Pérez', '2025-02-01', '2025-02-10', 'pendiente', 2);
