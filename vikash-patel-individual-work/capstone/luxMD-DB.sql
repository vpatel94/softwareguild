-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema luxMD-DB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `luxMD-DB` ;

-- -----------------------------------------------------
-- Schema luxMD-DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `luxMD-DB` DEFAULT CHARACTER SET utf8 ;
USE `luxMD-DB` ;

-- -----------------------------------------------------
-- Table `luxMD-DB`.`Patients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luxMD-DB`.`Patients` (
  `PatientID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `PhoneNumber` VARCHAR(45) NOT NULL,
  `StreetAddress` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `State` VARCHAR(45) NOT NULL,
  `ZipCode` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PatientID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `luxMD-DB`.`Services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luxMD-DB`.`Services` (
  `ServiceID` INT NOT NULL AUTO_INCREMENT,
  `CareType` VARCHAR(45) NOT NULL,
  `FlatRate` INT NOT NULL,
  PRIMARY KEY (`ServiceID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `luxMD-DB`.`Doctors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luxMD-DB`.`Doctors` (
  `DoctorID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `PhoneNumber` VARCHAR(45) NOT NULL,
  `ServiceID` INT NULL,
  PRIMARY KEY (`DoctorID`),
  INDEX `ServiceID_FK_idx` (`ServiceID` ASC),
  CONSTRAINT `ServiceID_FK`
    FOREIGN KEY (`ServiceID`)
    REFERENCES `luxMD-DB`.`Services` (`ServiceID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `luxMD-DB`.`Appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luxMD-DB`.`Appointments` (
  `AppointmentID` INT NOT NULL AUTO_INCREMENT,
  `PatientID` INT NULL,
  `DoctorID` INT NULL,
  `Date` DATE NOT NULL,
  `Time` TIME NOT NULL,
  `Notes` VARCHAR(500) NULL,
  `TotalDue` INT NOT NULL,
  `DueDate` DATE NOT NULL,
  PRIMARY KEY (`AppointmentID`),
  INDEX `PatientID_FK_idx` (`PatientID` ASC),
  INDEX `DoctorID_FK_idx` (`DoctorID` ASC),
  CONSTRAINT `PatientID_FK`
    FOREIGN KEY (`PatientID`)
    REFERENCES `luxMD-DB`.`Patients` (`PatientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `DoctorID_FK`
    FOREIGN KEY (`DoctorID`)
    REFERENCES `luxMD-DB`.`Doctors` (`DoctorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `luxMD-DB`.`Patients`
-- -----------------------------------------------------
START TRANSACTION;
USE `luxMD-DB`;
INSERT INTO `luxMD-DB`.`Patients` (`PatientID`, `FirstName`, `LastName`, `PhoneNumber`, `StreetAddress`, `City`, `State`, `ZipCode`) VALUES (1, 'Vikash', 'Patel', '8284235493', '606 NC Music Factory Blvd', 'Charlotte', 'North Carolina', '28206');

COMMIT;


-- -----------------------------------------------------
-- Data for table `luxMD-DB`.`Services`
-- -----------------------------------------------------
START TRANSACTION;
USE `luxMD-DB`;
INSERT INTO `luxMD-DB`.`Services` (`ServiceID`, `CareType`, `FlatRate`) VALUES (1, 'Primary Care', 119);
INSERT INTO `luxMD-DB`.`Services` (`ServiceID`, `CareType`, `FlatRate`) VALUES (2, 'Nurse Care', 79);
INSERT INTO `luxMD-DB`.`Services` (`ServiceID`, `CareType`, `FlatRate`) VALUES (3, 'Physical Therapy', 79);
INSERT INTO `luxMD-DB`.`Services` (`ServiceID`, `CareType`, `FlatRate`) VALUES (4, 'Nutritional Consultation', 59);

COMMIT;


-- -----------------------------------------------------
-- Data for table `luxMD-DB`.`Doctors`
-- -----------------------------------------------------
START TRANSACTION;
USE `luxMD-DB`;
INSERT INTO `luxMD-DB`.`Doctors` (`DoctorID`, `FirstName`, `LastName`, `PhoneNumber`, `ServiceID`) VALUES (1, 'John', 'Hopkins', '7047047040', 1);
INSERT INTO `luxMD-DB`.`Doctors` (`DoctorID`, `FirstName`, `LastName`, `PhoneNumber`, `ServiceID`) VALUES (2, 'Gregory', 'House', '7047047041', 1);
INSERT INTO `luxMD-DB`.`Doctors` (`DoctorID`, `FirstName`, `LastName`, `PhoneNumber`, `ServiceID`) VALUES (3, 'Oprah', 'Winfrey', '7047047042', 2);
INSERT INTO `luxMD-DB`.`Doctors` (`DoctorID`, `FirstName`, `LastName`, `PhoneNumber`, `ServiceID`) VALUES (4, 'Ellen', 'DeGeneres', '7047047043', 2);
INSERT INTO `luxMD-DB`.`Doctors` (`DoctorID`, `FirstName`, `LastName`, `PhoneNumber`, `ServiceID`) VALUES (5, 'Lance', 'Armstrong', '7047047044', 3);
INSERT INTO `luxMD-DB`.`Doctors` (`DoctorID`, `FirstName`, `LastName`, `PhoneNumber`, `ServiceID`) VALUES (6, 'Roger', 'Federer', '7047047045', 3);
INSERT INTO `luxMD-DB`.`Doctors` (`DoctorID`, `FirstName`, `LastName`, `PhoneNumber`, `ServiceID`) VALUES (7, 'Emeril', 'Lagasse', '7047047046', 4);
INSERT INTO `luxMD-DB`.`Doctors` (`DoctorID`, `FirstName`, `LastName`, `PhoneNumber`, `ServiceID`) VALUES (8, 'Guy', 'Fieri', '7047047047', 4);

COMMIT;

