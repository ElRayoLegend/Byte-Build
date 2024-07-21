-- Configuración inicial
ALTER USER '2023370_IN5BV'@'localhost' IDENTIFIED WITH mysql_native_password BY 'abc123!!';
SET GLOBAL time_zone = '-6:00';

-- Información del proyecto
/*
* Nombre: Ricardo Figueroa 
* Fecha de creación: 7/07/2024 
* Última Fecha de edición : 19/07/2024
*
* Esta es la base de datos del proyecto, totalmente funcional, 
* con la entidad cliente y sus procedimientos almacenados, y la entidad proveedores.
*/

-- Eliminar la base de datos si existe
DROP DATABASE IF EXISTS byteBuild;

-- Crear la base de datos
CREATE DATABASE byteBuild;
USE byteBuild;

-- Crear las tablas

-- Tabla Cliente
CREATE TABLE Cliente (
    clienteId INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    PRIMARY KEY (clienteId)
);

-- Tabla Proveedor
CREATE TABLE Proveedor (
    proveedorId INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    PRIMARY KEY (proveedorId)
);

-- Tabla Producto
CREATE TABLE Producto (
    productoId INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (productoId)
);

-- Tabla CarritoCompras
CREATE TABLE CarritoCompras (
    carritoId INT NOT NULL AUTO_INCREMENT,
    cantidad INT NOT NULL,
    nombreProducto VARCHAR(100) NOT NULL,
    precioProducto DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (carritoId)
);

-- Tabla SoporteTecnico
CREATE TABLE SoporteTecnico (
    soporteId INT NOT NULL AUTO_INCREMENT,
    numeroTelefono VARCHAR(15) NOT NULL,
    correoElectronico VARCHAR(100) NOT NULL,
    descripcionProblema TEXT NOT NULL,
    fechaProblema DATE,
    PRIMARY KEY (soporteId)
);

-- Tabla Compra
CREATE TABLE Compra (
    compraId INT NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(60),
    fechaCompra DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (compraId)
);

-- Tabla Factura
CREATE TABLE Factura (
    facturaId INT NOT NULL AUTO_INCREMENT,
    compraId INT NOT NULL,
    fechaFactura DATE NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (facturaId)
);

-- Insertar datos en las tablas

-- Datos en la tabla Cliente
INSERT INTO Cliente (nombre, direccion, telefono, correo) VALUES
('Juan Perez', 'Av. Principal 123', '12345678', 'juan@example.com'),
('Ana Gomez', 'Calle Falsa 456', '87654321', 'ana@example.com'),
('Luis Martinez', 'Av. Secundaria 789', '11223344', 'luis@example.com'),
('Marta Lopez', 'Calle Real 101', '44332211', 'marta@example.com'),
('Carlos Ruiz', 'Av. Independencia 202', '99887766', 'carlos@example.com');

-- Datos en la tabla Proveedor
INSERT INTO Proveedor (nombre, direccion, telefono, correo) VALUES
('Proveedor A', 'Zona Industrial 1', '12349876', 'proveedora@example.com'),
('Proveedor B', 'Parque Empresarial 2', '56784321', 'proveedorb@example.com'),
('Proveedor C', 'Av. Comercial 3', '78906543', 'proveedorc@example.com'),
('Proveedor D', 'Calle Empresa 4', '65437890', 'proveedord@example.com'),
('Proveedor E', 'Boulevard 5', '32198765', 'proveedore@example.com');

-- Datos en la tabla Producto
INSERT INTO Producto (nombre, descripcion, precio, stock) VALUES
('Producto 1', 'Descripcion del Producto 1', 10.50, 100),
('Producto 2', 'Descripcion del Producto 2', 20.75, 50),
('Producto 3', 'Descripcion del Producto 3', 15.30, 200),
('Producto 4', 'Descripcion del Producto 4', 25.00, 150),
('Producto 5', 'Descripcion del Producto 5', 30.10, 75);

-- Datos en la tabla CarritoCompras
INSERT INTO CarritoCompras (cantidad, nombreProducto, precioProducto, total) VALUES
(2, 'Producto 1', 10.50, 21.00),
(1, 'Producto 2', 20.75, 20.75),
(3, 'Producto 3', 15.30, 45.90),
(4, 'Producto 4', 25.00, 100.00),
(5, 'Producto 5', 30.10, 150.50);

-- Datos en la tabla SoporteTecnico
INSERT INTO SoporteTecnico (numeroTelefono, correoElectronico, descripcionProblema, fechaProblema) VALUES
('12345678', 'soporte1@example.com', 'Problema con el producto 1', '2024-07-01'),
('87654321', 'soporte2@example.com', 'Problema con el producto 2', '2024-07-02'),
('11223344', 'soporte3@example.com', 'Problema con el producto 3', '2024-07-03'),
('44332211', 'soporte4@example.com', 'Problema con el producto 4', '2024-07-04'),
('99887766', 'soporte5@example.com', 'Problema con el producto 5', '2024-07-05');

-- Datos en la tabla Compra
INSERT INTO Compra (descripcion, fechaCompra, total) VALUES
('Compra 1', '2024-07-01', 100.00),
('Compra 2', '2024-07-02', 200.00),
('Compra 3', '2024-07-03', 150.00),
('Compra 4', '2024-07-04', 250.00),
('Compra 5', '2024-07-05', 300.00);

-- Datos en la tabla Factura
INSERT INTO Factura (compraId, fechaFactura, monto) VALUES
(1, '2024-07-01', 100.00),
(2, '2024-07-02', 200.00),
(3, '2024-07-03', 150.00),
(4, '2024-07-04', 250.00);