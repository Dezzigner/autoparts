-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema autoparts
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `autoparts` ;

-- -----------------------------------------------------
-- Schema autoparts
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `autoparts` DEFAULT CHARACTER SET utf8 ;
USE `autoparts` ;

-- -----------------------------------------------------
-- Table `autoparts`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `autoparts`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoparts`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `autoparts`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(100) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_user_role_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `autoparts`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoparts`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `autoparts`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(100) NOT NULL,
  `lastname` VARCHAR(100) NOT NULL,
  `middlename` VARCHAR(100) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_customer_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_customer_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `autoparts`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoparts`.`supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `autoparts`.`supplier` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(200) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_supplier_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_supplier_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `autoparts`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoparts`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `autoparts`.`car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `release_year` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoparts`.`warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `autoparts`.`warehouse` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoparts`.`detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `autoparts`.`detail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `price` INT NOT NULL,
  `car_id` INT NOT NULL,
  `warehouse_id` INT NOT NULL,
  `supplier_id` INT NOT NULL,
  `count_in_warehouse` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_detail_car1_idx` (`car_id` ASC),
  INDEX `fk_detail_warehouse1_idx` (`warehouse_id` ASC),
  INDEX `fk_detail_supplier1_idx` (`supplier_id` ASC),
  CONSTRAINT `fk_detail_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `autoparts`.`car` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_detail_warehouse1`
    FOREIGN KEY (`warehouse_id`)
    REFERENCES `autoparts`.`warehouse` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_detail_supplier1`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `autoparts`.`supplier` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoparts`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `autoparts`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_order_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_order_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `autoparts`.`customer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoparts`.`order_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `autoparts`.`order_detail` (
  `order_id` INT NOT NULL,
  `detail_id` INT NOT NULL,
  `count` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_order_item_order1_idx` (`order_id` ASC),
  INDEX `fk_order_item_detail1_idx` (`detail_id` ASC),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_order_item_order1`
  FOREIGN KEY (`order_id`)
  REFERENCES `autoparts`.`order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_item_detail1`
  FOREIGN KEY (`detail_id`)
  REFERENCES `autoparts`.`detail` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
