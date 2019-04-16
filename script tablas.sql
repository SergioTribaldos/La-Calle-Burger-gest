select * from USUARIO;
select * from PRODUCTO_HAS_pedido WHERE PEDIDO_FECHA BETWEEN '2019-04-09 22:58:00'  AND '2019-05-09 23:00:00';
select * from PEDIDO;
DESC PRODUCTO_HAS_PEDIDO;
SELECT * FROM PRODUCTO_HAS_PEDIDO;
DELETE FROM ELABORACION;
select * from producto;
SELECT * FROM PEDIDO;
select * from restaurante;
select * from PRODUCTO_HAS_Usuario_usuariopedido;
desc PRODUCTO_HAS_pedido;
drop table producto,restaurante,usuario,pedido,producto_has_pedido;

SET GLOBAL time_zone = '+1:00';

insert into producto values (0,"HAMBURGUESA DE AGUJA 20ud",1,20,'UD','HAMBURGUESA');
insert into producto values(1,"HAMBURGUESA DE ANGUS 20ud",1,20,'UD','HAMBURGUESA');
insert into producto values(2,"HAMBURGUESA DE ENTRAÑA 20ud",1,20,'UD','HAMBURGUESA');
insert into producto values(3,"HAMBURGUESA DE GARBANZOS 30ud",1,30,'UD','HAMBURGUESA');
insert into producto values(4,"HAMBURGUESA DE LENTEJAS 30ud",1,30,'UD','HAMBURGUESA');
insert into producto values(5,"HAMBURGUESA DE VACIO 20ud",1,20,'UD','HAMBURGUESA');
insert into producto values(6,"HAMBURGUESA DOBLE 30ud",1,30,'UD','HAMBURGUESA');
insert into producto values(7,"ALITAS DE POLLO 2kg",1,2.0,'KG','ENTRANTE');
insert into producto values(8,"BACON CRUJIENTE bolsa 400gr",1,0.4,'KG','ENTRANTE');
insert into producto values(9,"CEBOLLA AL VINO bolsa 1,5kg",1,1.5,'KG','ENTRANTE');
insert into producto values(10,"CHAMPIÑON PORTOBELLO bolsa 1kg",1,1.0,'KG','ENTRANTE');
insert into producto values(11,"COSTILLAS DE CERDO bolsa 2kg",1,2.0,'KG','ENTRANTE');
insert into producto values(12,"ENTRAÑA PARA BROCHETA bolsa 500gr",1,0.5,'KG','ENTRANTE');
insert into producto values(13,"FINGERS DE POLLO bolsa 2kg",1,2.0,'KG','ENTRANTE');
insert into producto values(14,"PIMIENTO CONFITADO",1,12,'UD','ENTRANTE');
insert into producto values(15,"POLLO MARINADO bandeja 2,5kg",1,2.5,'KG','ENTRANTE');
insert into producto values(16,"SALSA CALAMBRITO bolsa 2 k",1,2.0,'KG','ENTRANTE');
insert into producto values(17,"CALDO PARA SALTEADO bolsa 1 kg",1,1.0,'KG','SALSA');
insert into producto values(18,"KETCHUP LA CALLE bolsa 1 kg",1,1.0,'KG','SALSA');
insert into producto values(19,"MAYONESA LA CALLE bolsa 2 kg",1,2.0,'KG','SALSA');
insert into producto values(20,"MAYONESA DE CHIMICHURRI bolsa 2 kg",1,2.0,'KG','SALSA');
insert into producto values(21,"MAYONESA DE PIMIENTA Y TRUFA bolsa 2 kg",1,2.0,'KG','SALSA');
insert into producto values(22,"MAYONESA SWEET CHILI bolsa 1 kg",1,1.0,'KG','SALSA');
insert into producto values(23,"MAYONESA VEGANA bolsa 500 g",1,0.5,'KG','SALSA');
insert into producto values(24,"SALSA BBQ bolsa 2 kg",1,2.0,'KG','SALSA');
insert into producto values(25,"SALSA BBQ TOFFEE bolsa 1 kg",1,1.0,'KG','SALSA');
insert into producto values(26,"SALSA CALLEJERA bolsa 2 kg",1,2.0,'KG','SALSA');
insert into producto values(27,"SALSA CÉSAR bolsa 1 kg",1,1.0,'KG','SALSA');
insert into producto values(28,"SALSA DE ALITAS bolsa 2 kg",1,2.0,'KG','SALSA');
insert into producto values(29,"VINAGRETA PARA ENSALADA DE COL bolsa 1 kg",1,1.0,'KG','SALSA');
insert into producto values(30,"COCHINITA PIBIL bolsa 1 kg",1,1.0,'KG','BOCADILLO');
insert into producto values(31,"ENTRECOT MARINADO bolsa 500 g",1,0.5,'KG','BOCADILLO');
insert into producto values(32,"SALCHICHAS bolsa 8 ud",1,8,'UD','BOCADILLO');
insert into producto values(33,"TARTA OREO caja 16 ud",1,16,'UD','POSTRE');
insert into producto values(34,"TARTA QUESO caja 16 ud",1,16,'UD','POSTRE');
insert into producto values(35,"TARTA SACHER caja 20 ud",1,20,'UD','POSTRE');
insert into producto values(36,"TARTA ZANAHORIA caja 20 ud",1,20,'UD','POSTRE');

insert into restaurante values("S7741870E","Centro","Calle Mosquera 3. Málaga","951 46 58 72","CENTRO");
insert into restaurante values("S3341870E","Teatinos","Av. de Gregorio Prieto 27. Málaga ","951 02 30 50","TEATINOS");
insert into restaurante values("S5541870E","Fuengirola","Calle Marconi 32, Fuengirola Málaga","951 10 11 35","FUENGIROLA");
insert into restaurante values("S4441870E","Pedregalejo","Paseo Marítimo El Pedregal 11. Málaga Pedregalejo","951 50 35 64","PEDREGALEJO");
insert into restaurante values("S8841870E","Americas","Avenida de las Américas 9. Málaga","951 07 97 94","AMERICAS");
insert into restaurante values("S1141870E","Plaza Mayor","Calle Alfonso Ponce de León 3. Málaga","952 02 64 64","PLAZA_MAYOR");
insert into restaurante values("S0041870E","Parque Oeste","Calle Diamantino García Acosta 1. Málaga","951 91 77 15","PARQUE_OESTE");
insert into restaurante values("S0041870E","Gamarra","Calle Sondalezas 33. Málaga","951 77 70 93","GAMARRA");
insert into restaurante values("S0041870E","San Pedro","Avenida de Burgos 22. San Pedro de Alcántara","951 482 590","SAN_PEDRO");
insert into restaurante values("S0041870E","Marbella","Avenida Miguel Cano 1. Edif. Milenium. Marbella","951 812 128","MARBELLA");





-- MySQL Script generated by MySQL Workbench
-- Thu Apr 11 20:56:25 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lacalle
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lacalle
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lacalle` DEFAULT CHARACTER SET utf8 ;
USE `lacalle` ;

-- -----------------------------------------------------
-- Table `lacalle`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lacalle`.`producto` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(75) NOT NULL,
  `precio` DECIMAL(5,2) NULL,
  `cantidadPorUnidad` DECIMAL(5,2) NULL,
  `unidadDeMedida` VARCHAR(2) NULL,
  `tipoProducto` VARCHAR(15) NULL,
  PRIMARY KEY (`id`, `nombre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lacalle`.`Restaurante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lacalle`.`Restaurante` (
  `cif` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(150) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `codigoRestaurante` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigoRestaurante`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lacalle`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lacalle`.`Pedido` (
  `Fecha` DATETIME NOT NULL,
  `Restaurante_codigoRestaurante` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Fecha`),
  INDEX `fk_Pedido_Restaurante1_idx` (`Restaurante_codigoRestaurante` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Restaurante1`
    FOREIGN KEY (`Restaurante_codigoRestaurante`)
    REFERENCES `lacalle`.`Restaurante` (`codigoRestaurante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lacalle`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lacalle`.`Usuario` (
  `usuario` VARCHAR(15) NOT NULL,
  `contraseña` VARCHAR(15) NOT NULL,
  `Restaurante_codigoRestaurante` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`usuario`),
  INDEX `fk_Usuario_Restaurante1_idx` (`Restaurante_codigoRestaurante` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_Restaurante1`
    FOREIGN KEY (`Restaurante_codigoRestaurante`)
    REFERENCES `lacalle`.`Restaurante` (`codigoRestaurante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lacalle`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lacalle`.`Administrador` (
  `nombre` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`, `contraseña`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lacalle`.`producto_has_Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lacalle`.`producto_has_Pedido` (
  `producto_id` INT NOT NULL,
  `producto_nombre` VARCHAR(75) NOT NULL,
  `Pedido_Fecha` DATETIME NOT NULL,
  `cantidad` INT NULL,
  PRIMARY KEY (`producto_id`, `producto_nombre`, `Pedido_Fecha`),
  INDEX `fk_producto_has_Pedido_Pedido1_idx` (`Pedido_Fecha` ASC) VISIBLE,
  INDEX `fk_producto_has_Pedido_producto1_idx` (`producto_id` ASC, `producto_nombre` ASC) VISIBLE,
  CONSTRAINT `fk_producto_has_Pedido_producto1`
    FOREIGN KEY (`producto_id` , `producto_nombre`)
    REFERENCES `lacalle`.`producto` (`id` , `nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_has_Pedido_Pedido1`
    FOREIGN KEY (`Pedido_Fecha`)
    REFERENCES `lacalle`.`Pedido` (`Fecha`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
