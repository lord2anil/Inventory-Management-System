drop DATABASE if exists vendordb;
create database vendordb;

use vendordb;


create table vendor (
vendor_id varchar(100) primary key, 
vendor_name varchar(100) not null, 
vendor_address varchar(100),
contact_number bigint(10) not null,
contact_person varchar(100) not null
);

insert into vendor (vendor_id, vendor_name, vendor_address, contact_number, contact_person) values('V001','Only Vimal','Stock Home Road,Sector 22, New Delhi-110 001','9002348970','John');
insert into vendor (vendor_id, vendor_name, vendor_address, contact_number, contact_person) values('V002','PRR Enterprises','10, GST Road, Mumbai-400 001','8700112345','Sam');
insert into vendor (vendor_id, vendor_name, vendor_address, contact_number, contact_person) values('V003','SRS Enterprises','Section - B, Gandhi Nagar, West Bengal-700 010','9444467812','Elizabeth');
insert into vendor (vendor_id, vendor_name, vendor_address, contact_number, contact_person) values('V004','SKR Traders','16, Park Town Street,Nungambakkam Chennai-600 003','9003406789','Amelia');
insert into vendor (vendor_id, vendor_name, vendor_address, contact_number, contact_person) values('V005','Mind Tree Textiles','Mind Tree Division, Sector-12, Bengaluru-560 003','9989076758','Peter');
