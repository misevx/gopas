CREATE DATABASE	bookstore;
\c bookstore;
CREATE TABLE Book (id varchar(64) not null primary key, title varchar(32) not null, author varchar(32) not null);
