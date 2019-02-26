-- MySQL Script generated by MySQL Workbench
-- Fri Dec 14 13:39:26 2018
-- Model: HotelReservation    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema hotelres
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hotelres` ;

-- -----------------------------------------------------
-- Schema hotelres
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotelres` DEFAULT CHARACTER SET utf8 ;
USE `hotelres` ;

-- -----------------------------------------------------
-- Table `hotelres`.`RoomRates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`RoomRates` (
  `RoomRateID` INT NOT NULL AUTO_INCREMENT,
  `RoomRate` DECIMAL(7,2) NOT NULL,
  `RoomType` VARCHAR(20) NOT NULL,
  `OccupancyLimit` INT NOT NULL,
  PRIMARY KEY (`RoomRateID`),
  UNIQUE INDEX `RoomRateID_UNIQUE` (`RoomRateID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelres`.`Rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`Rooms` (
  `RoomID` INT NOT NULL,
  `Floor` INT NOT NULL,
  `RoomRateID` INT NOT NULL,
  PRIMARY KEY (`RoomID`),
  UNIQUE INDEX `RoomID_UNIQUE` (`RoomID` ASC),
  INDEX `RoomRateID_idx` (`RoomRateID` ASC),
  CONSTRAINT `RoomRateID`
    FOREIGN KEY (`RoomRateID`)
    REFERENCES `hotelres`.`RoomRates` (`RoomRateID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelres`.`Amenities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`Amenities` (
  `AmenityID` INT NOT NULL AUTO_INCREMENT,
  `AmenityName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AmenityID`),
  UNIQUE INDEX `AmenityID_UNIQUE` (`AmenityID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelres`.`RoomsAmenities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`RoomsAmenities` (
  `RoomID` INT NOT NULL,
  `AmenityID` INT NOT NULL,
  PRIMARY KEY (`RoomID`, `AmenityID`),
  INDEX `AmenityID_idx` (`AmenityID` ASC),
  CONSTRAINT `RoomID`
    FOREIGN KEY (`RoomID`)
    REFERENCES `hotelres`.`Rooms` (`RoomID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `AmenityID`
    FOREIGN KEY (`AmenityID`)
    REFERENCES `hotelres`.`Amenities` (`AmenityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelres`.`Customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`Customers` (
  `CustomerID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `PhoneNumber` VARCHAR(12) NOT NULL,
  `EmailAddress` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CustomerID`),
  UNIQUE INDEX `CustomerID_UNIQUE` (`CustomerID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelres`.`Promotions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`Promotions` (
  `PromotionID` INT NOT NULL AUTO_INCREMENT,
  `PromoName` VARCHAR(45) NOT NULL,
  `DiscountAmount` DECIMAL(7,2) NULL,
  `DiscountPercent` INT NULL,
  `StartDate` DATE NOT NULL,
  `EndDate` DATE NULL,
  PRIMARY KEY (`PromotionID`),
  UNIQUE INDEX `PromotionID_UNIQUE` (`PromotionID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelres`.`Reservations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`Reservations` (
  `ReservationID` INT NOT NULL AUTO_INCREMENT,
  `CustomerID` INT NOT NULL,
  `PromotionID` INT NULL,
  PRIMARY KEY (`ReservationID`),
  UNIQUE INDEX `ReservationID_UNIQUE` (`ReservationID` ASC),
  INDEX `CustomerID_idx` (`CustomerID` ASC),
  INDEX `PromotionID_idx` (`PromotionID` ASC),
  CONSTRAINT `CustomerID`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `hotelres`.`Customers` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `PromotionID`
    FOREIGN KEY (`PromotionID`)
    REFERENCES `hotelres`.`Promotions` (`PromotionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelres`.`Guests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`Guests` (
  `GuestID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Age` INT NOT NULL,
  `ReservationID` INT NOT NULL,
  PRIMARY KEY (`GuestID`),
  UNIQUE INDEX `GuestID_UNIQUE` (`GuestID` ASC),
  INDEX `ReservationID_idx` (`ReservationID` ASC),
  CONSTRAINT `ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `hotelres`.`Reservations` (`ReservationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelres`.`AddOns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`AddOns` (
  `AddOnID` INT NOT NULL AUTO_INCREMENT,
  `AddOnType` VARCHAR(45) NOT NULL,
  `AddOnPrice` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`AddOnID`),
  UNIQUE INDEX `AddOnID_UNIQUE` (`AddOnID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelres`.`ReservationsAddOns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`ReservationsAddOns` (
  `ReservationID` INT NOT NULL,
  `AddOnID` INT NOT NULL,
  PRIMARY KEY (`ReservationID`, `AddOnID`),
  INDEX `AddOnID_idx` (`AddOnID` ASC),
  CONSTRAINT `ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `hotelres`.`Reservations` (`ReservationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `AddOnID`
    FOREIGN KEY (`AddOnID`)
    REFERENCES `hotelres`.`AddOns` (`AddOnID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelres`.`RoomsReservations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`RoomsReservations` (
  `RoomID` INT NOT NULL,
  `ReservationID` INT NOT NULL,
  `ArrivalDate` DATE NOT NULL COMMENT 'create UDF to enable a check constraint for overlapping dates when inserting.',
  `DepartureDate` DATE NOT NULL,
  INDEX `ReservationID_idx` (`ReservationID` ASC),
  PRIMARY KEY (`RoomID`, `ReservationID`),
  CONSTRAINT `RoomID`
    FOREIGN KEY (`RoomID`)
    REFERENCES `hotelres`.`Rooms` (`RoomID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `hotelres`.`Reservations` (`ReservationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `hotelres` ;

-- -----------------------------------------------------
-- Placeholder table for view `hotelres`.`Billing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelres`.`Billing` (`ReservationID` INT, `FirstName` INT, `LastName` INT, `ArrivalDate` INT, `DepartureDate` INT, `RoomID` INT, `RoomType` INT, `RoomRate` INT, `Tax` INT, `Total` INT);

-- -----------------------------------------------------
-- View `hotelres`.`Billing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotelres`.`Billing`;
USE `hotelres`;
CREATE  OR REPLACE VIEW Billing
AS
SELECT
	Reservations.ReservationID,
	Customers.FirstName,
	Customers.LastName,
	RoomsReservations.ArrivalDate,
    RoomsReservations.DepartureDate,
    Rooms.RoomID,
    RoomsRates.RoomType,
    RoomsRates.RoomRate,
    (RoomsRates.RoomRate * 0.07) as `Tax`,
    (RoomsRates.RoomRate * 1.07) as `Total`
FROM Reservations
JOIN Customers
	ON Customers.CustomerID = Reservations.CustomerID
JOIN RoomsReservations
	ON RoomsReservations.ReservationID = Reservations.ReservationID
JOIN Rooms
	ON Rooms.RoomID = RoomsReservations.RoomID
JOIN RoomRates
	ON RoomRates.RoomRateID = Rooms.RoomRateID;
JOIN ReservationsAddOns
	ON ReservationsAddOns.ReservationID = Reservations.ReservationID
JOIN AddOns
	ON AddOns.AddOnID = ReservationsAddOns.AddOnID;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;