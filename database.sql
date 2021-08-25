create database loginManagement;

use loginManagement;

create table User
(
    Id           int primary key auto_increment,
    name         varchar(100) not null,
    address      varchar(260) not null,
    contact      varchar(60)  not null,
    emailAddress varchar(100) not null,
    password     varchar(15)  not null
);

desc User;

insert into User (name, address, contact, emailAddress, password)
VALUES ("thilina", "thuduwawa", "54668735165", "thilina456456@gmail.com", "123456789");

select *
from User;

select *
from User
where emailAddress = "admin@gmail.com"
  and password = "admin";

update User
set name="awanthi",
    address="ambalangoda",
    contact="54444744",
    emailAddress="awanthi456456",
    password="123456789"
where Id = "11";


update User
set name=?,
    address=?,
    contact=?,
    emailAddress=?,
    password=?
where Id = ?;

delete
from user
where Id = 11
