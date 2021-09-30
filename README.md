# SellingSimplifiedTask

i used spring boot, hibernate JPA, mysql database

here i have created restfull api for adding book, delete book, searching book by using ISBN, search book by using author and genre

i have use restcontroller to build API's

and to check or to test this api i have use the postman 

i have use the mysql database but didn't create table, jpa automatically create all the tables based on my entity/model classes

and i have uses single table for book that contain ISBN, book title, price , date,author, genre.
all the api are works properly any front end app can consume it.
here i used log as well to print the info messages.
actually i am trying to dockerize my application but my laptop doesn't support the docker thats why i skipped it. i know the how to dockerize spring boot app

i have put all the file of my spring boot application, please check it
i dont have advance knowledge of spring boot but according to my knowledge i build the app.



CREATE TABLE `book` (
  `isbn` bigint NOT NULL,
  `book_author` varchar(255) DEFAULT NULL,
  `book_genre` varchar(255) DEFAULT NULL,
  `book_title` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `publication_date` date DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


above query or table automatically created by hibernate jpa for me 

please check all files thank you
