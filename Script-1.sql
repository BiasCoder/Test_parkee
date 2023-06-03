create database test_parkee;

create table books(
 int isbn not null,
 varchar title,  
 int stock,
 primary key (isbn)
);

create table borrowers(
 int ktp not null,
 varchar name,
 varchar email,
 date borrowed_date,
 date return_date,
 boolean on_time_returned,
 primary key (ktp)
);

create table borrowings(
 int id,
 date borrowed_date,
 date return_date,
 boolean on_time_returned,
 int borrower_id
 int book_id
 primary key(id)
)




