# --- !Ups

create table people (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name varchar(255) not null,
  age int not null,
  PRIMARY KEY (id)
);