DROP TABLE Buys;
DROP TABLE Services;
ALTER TABLE Hotels DROP FOREIGN KEY c_hotels_fk;
DROP TABLE Serves;
DROP TABLE Staff;
DROP TABLE Checkins;
DROP TABLE PaymentInfo;
DROP TABLE Customers;
DROP TABLE Rooms;
DROP TABLE RoomPrices;
DROP TABLE Hotels;

CREATE TABLE Hotels (
hotelId int AUTO_INCREMENT,
Name varchar(100) NOT NULL,
Address varchar(255) NOT NULL,
City varchar(50) NOT NULL,
State varchar(50) NOT NULL,
Country varchar(50) NOT NULL,
phoneNumber varchar(10) NOT NULL,
managerId int UNIQUE,
CONSTRAINT c_hotels_pk PRIMARY KEY(hotelId)
);
ALTER TABLE Hotels  AUTO_INCREMENT=0001;


CREATE TABLE RoomPrices (
category varchar (50),
maxOccupancy int,
price int NOT NULL,
CONSTRAINT c_roomprices_pk  PRIMARY KEY (category, maxOccupancy)
);


CREATE TABLE Rooms (
roomNumber int,
hotelId int,
category varchar (50) NOT NULL,
maxOccupancy int NOT NULL,
availability boolean NOT NULL,
CONSTRAINT c_rooms_pk  PRIMARY KEY(roomNumber, hotelId),
CONSTRAINT c_rooms_fk1 FOREIGN KEY(hotelId) REFERENCES Hotels(hotelId),
CONSTRAINT c_rooms_fk2 FOREIGN KEY (category, maxOccupancy) REFERENCES RoomPrices (category, maxOccupancy)
);


CREATE TABLE Customers (
customerId int AUTO_INCREMENT,
name varchar (50) NOT NULL,
dateOfBirth date,
phoneNumber varchar (10) NOT NULL,
email varchar (60),
CONSTRAINT c_customer_pk PRIMARY KEY(customerId)
);
ALTER TABLE Customers  AUTO_INCREMENT=1001;


CREATE TABLE PaymentInfo (
paymentId int AUTO_INCREMENT,
SSN char (10),
billingAddress varchar (255) NOT NULL,
City varchar(50) NOT NULL,
State varchar(50) NOT NULL,
Country varchar(50) NOT NULL,
paymentMethod char (15) NOT NULL,
cardNumber char (16),
customerId int NOT NULL,
CONSTRAINT c_paymentinfo_pk PRIMARY KEY(paymentId),
CONSTRAINT c_paymentinfo_fk FOREIGN KEY(customerId) REFERENCES Customers (customerId)
);
ALTER TABLE PaymentInfo  AUTO_INCREMENT=3000;

CREATE TABLE Checkins (
checkinId int AUTO_INCREMENT,
startDate date NOT NULL,
endDate date NOT NULL,
checkinTime time NOT NULL,
checkoutTime time,
numberOfGuests int NOT NULL,
total FLOAT,
customerId int NOT NULL,
hotelId int NOT NULL,
roomNumber int NOT NULL,
paymentId int NOT NULL,
CONSTRAINT c_checkins_pk PRIMARY KEY(checkinId),
CONSTRAINT c_checkins_fk1 FOREIGN KEY(customerId) REFERENCES Customers (customerId),
CONSTRAINT c_checkins_fk2  FOREIGN KEY(hotelId, roomNumber) REFERENCES Rooms (hotelId, roomNumber),
CONSTRAINT c_checkins_fk3  FOREIGN KEY(paymentId) REFERENCES PaymentInfo (paymentId)
);
ALTER TABLE Checkins  AUTO_INCREMENT=4000;


CREATE TABLE Staff (
staffId int AUTO_INCREMENT,
name varchar (50) NOT NULL,
age int,
jobTitle varchar (50) NOT NULL,
phoneNumber varchar (10) NOT NULL,
address varchar (255),
City varchar(50),
State varchar(50),
Country varchar(50),
hotelId int,
CONSTRAINT c_staff_pk  PRIMARY KEY(staffId),
CONSTRAINT c_staff_fk FOREIGN KEY(hotelId) REFERENCES Hotels(hotelId)
);
ALTER TABLE Staff  AUTO_INCREMENT=100;


CREATE TABLE Services (
serviceId int AUTO_INCREMENT,
name varchar (50) NOT NULL,
basePrice int NOT NULL,
CONSTRAINT c_services_pk  PRIMARY KEY(serviceId)
);

CREATE TABLE Buys (
serviceId int,
checkinId int,
price int NOT NULL,
CONSTRAINT c_buys_pk PRIMARY KEY (serviceId, checkinId),
CONSTRAINT c_buys_fk1 FOREIGN KEY(checkinId) REFERENCES Checkins (checkinId),
CONSTRAINT c_buys_fk2 FOREIGN KEY(serviceId) REFERENCES Services (serviceId)
);


CREATE TABLE Serves(
staffId int,
checkinId int,
CONSTRAINT c_serves_pk PRIMARY KEY (staffId, checkinId),
CONSTRAINT c_serves_fk1 FOREIGN KEY(checkinId) REFERENCES Checkins (checkinId),
CONSTRAINT c_serves_fk2 FOREIGN KEY(staffId) REFERENCES Staff(staffId)
);

ALTER TABLE Hotels ADD CONSTRAINT c_hotels_fk FOREIGN KEY(managerId) REFERENCES Staff(staffId);

CREATE TRIGGER makeManager AFTER INSERT ON Staff
FOR EACH ROW update Hotels set managerId=new.staffId
where hotelId=new.hotelId and new.jobTitle = 'Manager' and managerId is NULL;

INSERT INTO Services(name, basePrice)
Values ("phone bills", 5), ("dry cleaning", 16),("gyms", 15),("room service", 10),("special requests", 20);

INSERT INTO RoomPrices (category, maxOccupancy, price)
Values( "Economy", 1, 100), ( "Deluxe", 2, 200), ( "Executive", 3, 1000), ( "Presidential", 4, 5000 );

INSERT INTO Customers ( name, dateOfBirth, phoneNumber, email)
Values ("David", "1980-01-30", "123", "david@gmail.com"),
("Sarah", "1971-01-30", "456", "sarah@gmail.com"),
( "Joseph", "1987-01-30", "789", "joseph@gmail.com"),
("Lucy", "1985-01-30", "213", "lucy@gmail.com");

INSERT INTO PaymentInfo (SSN, billingAddress, city, state, country,paymentMethod, cardNumber,customerId)
Values ("593-9846", "980 TRT St",  "Raleigh", "NC","USA","credit", "1052", 1001),
("777-8352", "7720 MHT St", "Greensboro","NC", "USA", "hotel credit", "3020", 1002),
("858-9430", "231 DRY St","Rochester","NY 78", "USA", "credit", "2497", 1003),
("440-9328", "24 BST Dr","Dallas","TX 14", "USA", "cash", NULL, 1004);

INSERT INTO Hotels (name,address, City, State, Country, phoneNumber)
Values("Hotel A", "21 ABC St","Raleigh", "NC 27","USA", "919"),
("Hotel B", "25 XYZ St", "Rochester","NY 54","USA","718"),
( "Hotel C", "29 PQR St", "Greensboro", "NC 27","USA", "984"),
( "Hotel D", "28 GHW St", "Raleigh", "NC 32","USA", "920");

INSERT INTO Staff(name, age, jobTitle, phoneNumber, address, City, State, Country, hotelId)
Values( "Mary", 40, "Manager",  "654", "90 ABC St", "Raleigh", "NC 27","USA",1),
( "John", 45, "Manager" , "564" , "798 XYZ St", "Rochester", "NY 54","USA",2),
( "Carol", 55, "Manager", "546", "351 MH St", "Greensboro", "NC 27","USA", 3),
("Emma", 55, "Front Desk Staff","546","49 ABC St", "Raleigh", "NC 27","USA",1),
("Ava", 55, "Catering Staff","777","425 RG St", "Raleigh", "NC 27","USA",1),
("Peter", 52, "Manager","724","475 RG St", "Raleigh", "NC 27","USA",4),
("Olivia", 27, "Front Desk Staff","799","325 PD St", "Raleigh", "NC 27","USA",4);

INSERT INTO Rooms (roomNumber, hotelId, category, maxOccupancy, availability)
Values  (01, 0001, "Economy",1, TRUE),(02, 0001, "Deluxe",2, TRUE),
(03, 0002, "Economy",1, TRUE),(02, 0003, "Executive", 3,FALSE),
(01, 0004, "Presidential",4, TRUE), (05, 0001, "Deluxe", 2, TRUE);

INSERT INTO Checkins (startDate, endDate, checkinTime, checkoutTime, numberOfGuests, total, customerId, hotelId, roomNumber, paymentId)
Values ("2017-05-10", "2017-05-13", "15:17:00", "10:22:00",1,  200, 1001, 0001, 01, 3000),
( "2017-05-10", "2017-05-13", "16:11:00", "9:27:00",4,  320, 1002, 0001, 02, 3001),
("2016-05-10", "2016-05-14", "15:45:00", "11:10:00",1,  400, 1003, 0002, 03, 3002),
("2018-05-10", "2018-05-12", "14:30:00", "10:00:00",2,  400, 1004, 0003, 02, 3003);

INSERT INTO Buys(serviceId, checkinId, price)
Values (2, 4000, 16), (3, 4000,15), (3, 4001,15),(4, 4002,10),(1, 4003,5);

INSERT INTO Serves(staffId,checkinId)
Values ( 100,4000), ( 101,4001), (102,4001),( 101,4000),( 103,4001),( 100,4002),(102,4003);
