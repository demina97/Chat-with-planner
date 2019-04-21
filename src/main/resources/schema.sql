drop table if exists person;

create table person
(
  phone     varchar(20) primary key,
  firstName varchar,
  lastName  varchar,
  position  varchar,
  password  varchar
);

drop table if exists chat_message;

create table chat_message
(
  id            serial primary key,
  sender        varchar(20) references person (phone) not null,
  recipient     varchar(20) references person (phone) not null,
  messageText   text,
  timeOfSending timestamp default now(),
  status        numeric default 1
);

drop table if exists planner;

create table planner
(
  idTask      serial primary key,
  textTask    text,
  statusTask  boolean,
  ownerTask   varchar(20) references person (phone) not null,
  dateTask    timestamp default now()
);
