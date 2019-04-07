drop table if exists person;

create table person
(
  phone     varchar(20) primary key,
  firstName varchar,
  lastName  varchar,
  position  varchar,
  password  varchar
);