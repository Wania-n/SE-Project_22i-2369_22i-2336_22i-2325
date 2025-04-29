create database EventManagementSystem;
use EventManagementSystem;

-- Creating tables now
create table Organizer(
	userID int auto_increment primary key,
    org_username varchar(50) not null unique,
    org_password varchar(50) not null,
    phone varchar(11) not null,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(50),
    address varchar(100),
    DOB date
);

ALTER TABLE organizer MODIFY org_password VARCHAR(255);

select * from Organizer;

-- TODO-LIST for the Organizer
create table Organizer_ToDoList(
	organizerID int,
	foreign key (organizerID) references Organizer(userID),
    task varchar(100) not null,
    task_completed boolean not null default false
);

create table Admin(
	userID int auto_increment primary key,
    admin_username varchar(50) not null unique,
    admin_password varchar(50) not null unique,
    phone varchar(11) not null,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(50),
    address varchar(100)
);

select * from Admin;
INSERT INTO Admin VALUES (1, 'ADMIN', 'ADMIN123', '1234567890', "ADMIN", 'ADMIN', 'admin@gmail.com', 'isb, pakistan');


create table Vendor(
	serviceID int auto_increment primary key,
    service_name varchar(100) not null,
    phone varchar(11),
    email varchar(50),
    address varchar(100),
    service_type varchar(100),
    price float
);

ALTER TABLE Vendor RENAME COLUMN service_name TO serviceName;

select * from Vendor;
DESCRIBE vendor;
SELECT serviceName FROM vendor;
alter table Vendor drop column serviceType;

insert into Vendor values(1, "Ratatouile", "12345678900", "ratatouile@gmail.com", "isb, Pakistan", "Buffet", 200);
insert into Vendor values(2, "Bistro", "12345678900", "bistro@gmail.com", "isb, Pakistan", "Buffet", 200);


create table Vendor_Menu(
	vendorID int,
	foreign key (vendorID) references Vendor(serviceID),
    cuisine_type varchar(100),
    info varchar(200)
);

create table InteriorDesigner(
	serviceID int auto_increment primary key,
    service_name varchar(100) not null,
    phone varchar(11),
    email varchar(50),
    address varchar(100),
    service_type varchar(100),
    price float
);

select * from InteriorDesigner;
insert into InteriorDesigner values(1, "Sparkles", "1234567890", "sparkles@gmail.com","isb, Pakistan", "decor", "500");

create table InteriorDesigner_Design(
	interiorDesignerID int,
	foreign key (interiorDesignerID) references InteriorDesigner(serviceID),
    theme varchar(100),
    info varchar(200)
);

create table Venue(
	venueID int auto_increment primary key,
    venue_name varchar(100),
    capacity int,
    address varchar(100),
    price float
);

select * from Venue;
insert into Venue values (1,"Pearl Continental", 3000, "isb, Paksitan", 100.00);

create table Event(
	eventID int auto_increment primary key,
	event_name varchar(100),
	event_date date,
    event_time time,
    total_price float,
    organizerID int,
    venueID int,
    vendorID int,
    interiorDesignerID int,
	foreign key (organizerID) references Organizer(userID),
    foreign key (venueID) references Venue(venueID) on delete set null,
    foreign key (vendorID) references Vendor(serviceID) on delete set null,
    foreign key (interiorDesignerID) references InteriorDesigner(serviceID) on delete set null
);

ALTER TABLE Event ADD COLUMN interiorDesignerID INT, ADD CONSTRAINT FK_interiorDesigner FOREIGN KEY (interiorDesignerID) REFERENCES InteriorDesigner(serviceID) ON DELETE SET NULL;

select * from Event;

SELECT CONSTRAINT_NAME FROM information_schema.KEY_COLUMN_USAGE WHERE TABLE_NAME = 'Event' AND COLUMN_NAME = 'interior_designerid';
SELECT CONSTRAINT_NAME FROM information_schema.KEY_COLUMN_USAGE WHERE TABLE_NAME = 'Event' AND COLUMN_NAME = 'interiorDesignerID';

alter table Event drop foreign key FKhvchgw4n443t5ss5kl9fep5nb;
alter table Event drop foreign key event_ibfk_4;
;


SHOW COLUMNS FROM Event;
select * from Event;
ALTER TABLE Event DROP COLUMN guestID;
alter table Event drop column interiorDesignerID;


alter table Event add column event_end_time time;

create table Guest(
	guestID int auto_increment primary key,
	guest_name varchar(100),
    email varchar(100),
    eventID int,
    organizerID int,
    foreign key (eventID) references Event(eventID),
    foreign key (organizerID) references Organizer(userID)
);

show databases
