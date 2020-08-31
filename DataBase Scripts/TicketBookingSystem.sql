---Script to create database
CREATE DATABASE ticket_booking;

---Script to create users table
CREATE TABLE  users (
id INT(11) NOT NULL AUTO_INCREMENT ,
userName  Varchar(100) NOT NULL ,
Phone_Number Varchar(20) NOT NULL ,
PRIMARY KEY (id)
)
DEFAULT CHARACTER SET = utf8;

---Script to create ticketDetail table
CREATE TABLE  Ticket_Details (
Ticket_id INT(11) NOT NULL AUTO_INCREMENT ,
Ticket_Timing  datetime NOT NULL ,
User_id INT(11) NOT NULL ,
Status Varchar(20) NOT NULL ,
PRIMARY KEY (Ticket_id)
)
DEFAULT CHARACTER SET = utf8;

---Script to create movieShowsDetail table
CREATE TABLE  Shows (
Show_id INT(11) NOT NULL AUTO_INCREMENT ,
Show_Name Varchar(20) NOT NULL ,
Show_Timing  datetime NOT NULL ,
TicketCount INT(11) default 0 NOT NULL ,
PRIMARY KEY (Show_id)
)
DEFAULT CHARACTER SET = utf8;


---Insert script to insert data in user table
INSERT INTO `ticket_booking`.`users` (`id`, `userName`, `Phone_Number`) VALUES ('1', 'arvind', '1234567');
INSERT INTO `ticket_booking`.`users` (`id`, `userName`, `Phone_Number`) VALUES ('2', 'mohit', '1236987425');
INSERT INTO `ticket_booking`.`users` (`id`, `userName`, `Phone_Number`) VALUES ('3', 'ajay', '1236987425');

---Insert script to insert data in ticketdeatil table
INSERT INTO `ticket_booking`.`ticket_details` (`Ticket_id`, `Ticket_Timing`, `User_id`, `Status`) VALUES ('1', '2020-08-29 05:31:50', '1', 'Active');

---Insert Script to insert data in shows table
INSERT INTO `ticket_booking`.`Shows` (`Show_id`, `Show_Name`, `Show_Timing`) VALUES ('1', 'Thor1', '2020-08-31 11:30:00');
INSERT INTO `ticket_booking`.`Shows` (`Show_id`, `Show_Name`, `Show_Timing`) VALUES ('2', 'Wanted', '2020-08-30 18:30:00');
INSERT INTO `ticket_booking`.`Shows` (`Show_id`, `Show_Name`, `Show_Timing`) VALUES ('3', 'Thor', '2020-08-31 11:00:00');

