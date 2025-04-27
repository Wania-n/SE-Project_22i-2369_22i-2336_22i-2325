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
