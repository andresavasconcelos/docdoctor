-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema docdoctor_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema docdoctor_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `docdoctor_db` DEFAULT CHARACTER SET utf8 ;
USE `docdoctor_db` ;

-- -----------------------------------------------------
-- Table `docdoctor_db`.`tbl_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `docdoctor_db`.`tbl_users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(255) NULL,
  `email` VARCHAR(100) NULL,
  `phone` VARCHAR(45) NULL,
  `birth_date` DATE NULL,
  `user_type` VARCHAR(45) NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
