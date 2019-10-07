-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema payrolldb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `payrolldb` ;

-- -----------------------------------------------------
-- Schema payrolldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `payrolldb` DEFAULT CHARACTER SET utf8 ;
USE `payrolldb` ;

-- -----------------------------------------------------
-- Table `employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `employee` ;

CREATE TABLE IF NOT EXISTS `employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `base_salary` DOUBLE NULL,
  `hourly_rate` DOUBLE NULL,
  `loan_amount` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payroll`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `payroll` ;

CREATE TABLE IF NOT EXISTS `payroll` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `week` VARCHAR(45) NULL,
  `period_start` DATE NULL,
  `period_end` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `record`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `record` ;

CREATE TABLE IF NOT EXISTS `record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `overtime` DOUBLE NULL,
  `hours` DOUBLE NULL,
  `total` DOUBLE NULL,
  `commission` DOUBLE NULL DEFAULT 0,
  `bonus` DOUBLE NULL DEFAULT 0,
  `employee_id` INT NOT NULL,
  `payroll_week_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_record_employee_idx` (`employee_id` ASC),
  INDEX `fk_record_payroll_week1_idx` (`payroll_week_id` ASC),
  CONSTRAINT `fk_record_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_record_payroll_week1`
    FOREIGN KEY (`payroll_week_id`)
    REFERENCES `payroll` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS payrolluser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'payrolluser'@'localhost' IDENTIFIED BY 'payrolluser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'payrolluser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `payrolldb`;
INSERT INTO `employee` (`id`, `name`, `role`, `base_salary`, `hourly_rate`, `loan_amount`) VALUES (1, 'Justin', 'payroll', 0, 15.00, 0);
INSERT INTO `employee` (`id`, `name`, `role`, `base_salary`, `hourly_rate`, `loan_amount`) VALUES (2, 'Tess', 'admin', 0, 12.50, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `payroll`
-- -----------------------------------------------------
START TRANSACTION;
USE `payrolldb`;
INSERT INTO `payroll` (`id`, `week`, `period_start`, `period_end`) VALUES (1, '36', '2019-09-01', '2019-09-14');

COMMIT;


-- -----------------------------------------------------
-- Data for table `record`
-- -----------------------------------------------------
START TRANSACTION;
USE `payrolldb`;
INSERT INTO `record` (`id`, `overtime`, `hours`, `total`, `commission`, `bonus`, `employee_id`, `payroll_week_id`) VALUES (1, 15, 40, 937.5, 0, 0, 1, 1);
INSERT INTO `record` (`id`, `overtime`, `hours`, `total`, `commission`, `bonus`, `employee_id`, `payroll_week_id`) VALUES (2, 0, 40, 500, 0, 0, 2, 1);

COMMIT;

