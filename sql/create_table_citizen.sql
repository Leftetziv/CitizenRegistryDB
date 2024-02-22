CREATE TABLE citizen (
    id varchar(8) PRIMARY KEY,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    gender varchar(1) NOT NULL,
    dob varchar(255) NOT NULL,
    afm varchar(9),
    address varchar(255)
);