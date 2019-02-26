DELIMITER $
CREATE FUNCTION `CheckRoomAvailability` 
(
	`@RoomID` INT,
    `@ArrivalDate` DATE,
    `@DepartureDate` DATE
)
RETURNS BIT
BEGIN
	DECLARE valid BIT;
	SET valid = 1;
    
    IF EXISTS(
		SELECT *
        FROM RoomsReservations
        WHERE RoomReservations.RoomID = @RoomID
        AND (@ArrivalDate >= RoomsReservations.ArrivalDate AND @DepartureDate <= RoomsReservations.DepartureDate)
        OR (@ArrivalDate >= RoomsReservations.ArrivalDate AND @ArrivalDate < RoomsReservations.DepartureDate)
        OR (@DepartureDate > RoomsReservations.ArrivalDate AND @DepartureDate <= RoomsReservations.DepartureDate)
    )
		THEN SET valid = 0;
	END IF;

RETURN valid;
END; $
DELIMITER ;



DELIMITER $
CREATE FUNCTION `GetTotalRate`( `@RoomType` VARCHAR(20) )
RETURNS DECIMAL(7,2)
BEGIN
	DECLARE totalrate DECIMAL(7,2);
    
    SELECT  roomres.ReservationID,
					SUM(
							 (DATEDIFF(
											LEAST(roomres.DepartureDate, roomrate.EndDate),
											GREATEST(roomres.ArrivalDate, roomrate.StartDate))) * roomrate.RoomRate) into totalrate
    FROM RoomRates roomrate
    INNER JOIN RoomsReservations roomres
        ON 
           (roomrate.StartDate BETWEEN roomres.ArrivalDate AND roomres.DepartureDate) OR
		   (roomrate.EndDate BETWEEN roomres.ArrivalDate AND roomres.DepartureDate)
    WHERE @RoomType = rrate.RoomType;

RETURN rate;
END; $
DELIMITER ;



DELIMITER $
CREATE FUNCTION `GetTotalAddOn`
(
	`@ReservationID` INT
)
RETURNS DECIMAL(7,2)
BEGIN
	DECLARE totalAddOn DECIMAL(7,2);
    SET totalAddOn = 0;
    
    IF EXISTS (
		SELECT *
		FROM AddOns
		WHERE @ReservationID = AddOns.ReservationID
	)
    THEN SELECT SUM(AddOns.AddOnPrice) into totalAddOn
			   FROM AddOns
			   WHERE @ReservationID = AddOns.ReservationID;
     END IF;          
RETURN totalAddOn;
END; $
DELIMITER ;




    
    
    
    
    
    
    
    
    
    
    