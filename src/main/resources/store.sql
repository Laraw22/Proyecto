drop schema if exists store;
drop user if exists usuario_supremo;
CREATE SCHEMA store ;

/*Se crea un usuario para la base de datos llamado "usuario_prueba" y tiene la contraseña "Usuario_Clave."*/
create user 'usuario_supremo'@'%' identified by 'jefe.';

/*Se asignan los prvilegios sobr ela base de datos TechShop al usuario creado */
grant all privileges on store.* to 'usuario_supremo'@'%';
flush privileges;


CREATE TABLE store.categoria (
    id_categoria INT NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL,
    ruta_imagen VARCHAR(1024),
    activo BOOLEAN,
    PRIMARY KEY(id_categoria));

CREATE TABLE  store.ropa (
    id_ropa INT NOT NULL AUTO_INCREMENT,
    id_categoria INT NOT NULL,
    descripcion VARCHAR(250) NOT NULL,
    talla VARCHAR(50),
    existencias INT,
    precio DOUBLE,
    ruta_imagen VARCHAR(1024),
    activo BOOLEAN,
    PRIMARY KEY(id_ropa),
    FOREIGN KEY fk_ropa_categoria (id_categoria) REFERENCES categoria(id_categoria));

CREATE TABLE store.usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(512) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    correo VARCHAR(50),
    telefono VARCHAR(15),
    ruta_imagen VARCHAR(1024),
    activo BOOLEAN,
    PRIMARY KEY(id_usuario)
);

CREATE TABLE store.factura (
    id_factura INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    fecha DATE,
    total DOUBLE,
    estado INT,
    PRIMARY KEY(id_factura),
    FOREIGN KEY fk_factura_usuario (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE store.compra (
    id_compra INT NOT NULL AUTO_INCREMENT,
    id_factura INT NOT NULL,
    id_ropa INT NOT NULL,
    precio DOUBLE,
    cantidad INT,
    PRIMARY KEY(id_compra),
    FOREIGN KEY fk_compras_factura (id_factura) REFERENCES factura(id_factura),
    FOREIGN KEY fk_compras_ropas (id_ropa) REFERENCES ropa(id_ropa)
);

CREATE TABLE store.rango (
    id_rango INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(20),
    id_usuario INT,
    PRIMARY KEY(id_rango),
    FOREIGN KEY fk_rango_usuario (id_usuario) REFERENCES usuario(id_usuario));



INSERT INTO store.usuario (id_usuario, username, password, nombre, correo, telefono, ruta_imagen, activo)VALUES
(1, 'Juan', 'jefe123', 'Juan', 'jRamirez@gmail.com', '7004-4222', 'https://stoneturn.com/wp-content/uploads/2023/06/Juan-Migone_HS.webp' , true),
(2,'Maria', 'subjefa123', 'Maria', 'Mmarin@gmail.com', '7004-1212', 'https://media.licdn.com/dms/image/C5603AQEynOMLdLYJJw/profile-displayphoto-shrink_800_800/0/1615128525648?e=2147483647&v=beta&t=aBeyKm2yscVyE2l9720UxaxIMbIhcdZj7zGmS0yDg90',  true),
(3, 'juanito', '123', 'Juancho', 'juanito@gmail.com', '7004-1232', 'https://guerrero.quadratin.com.mx/www/wp-content/uploads/2019/08/ricardocastilloperez-personanormal-1160x700.jpg',true);

INSERT INTO store.categoria (id_categoria,descripcion, ruta_imagen, activo)VALUES
(1,'Camisas de hombres', 'https://i.pinimg.com/236x/c0/46/3e/c0463e30f67b8c60192b657d58c7be31.jpg', true),
(2, 'Camisas de mujeres', 'https://i.pinimg.com/236x/2a/a6/58/2aa658e7d3108e1935db0de5526afded.jpg', true);

INSERT INTO store.ropa (id_ropa, id_categoria, descripcion, talla, existencias, precio, ruta_imagen, activo)VALUES
(1, 1, 'Camisa muy comoda y elegante para cualquier situación, Camisa roja, negra y blanco', 'S-M-L', 25, 2000, 'https://i.pinimg.com/236x/c0/46/3e/c0463e30f67b8c60192b657d58c7be31.jpg', true),
(2, 2,'Camisa elegante y bonita, Camisa blanca','XS-S-M' ,30, 2500, 'https://i.pinimg.com/236x/2a/a6/58/2aa658e7d3108e1935db0de5526afded.jpg', true);

INSERT INTO store.factura (id_factura, id_usuario, fecha, total, estado)VALUES
(1, 1,'2023-05-02', 2000, 2);

INSERT INTO store.compra (id_compra, id_factura, id_ropa, precio, cantidad)VALUES
(1, 1,2, 2000, 1);

INSERT INTO store.rango (id_rango, nombre, id_usuario)VALUES
(1,'Rango_Admin', 1),(2,'Rango_vendedor', 1),(3,'Rango_usuario', 1),
(4,'Rango_vendedor', 2),(5,'Rango_usuario', 2),
(6,'Rango_usuario', 3);