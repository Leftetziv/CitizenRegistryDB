CREATE TABLE address (
	citizen_id varchar(8),
    street varchar(50),
	strNumber varchar(10),
	postalCode 	varchar(5),
    PRIMARY KEY (citizen_id, street, strNumber, postalCode),
    CONSTRAINT FK_CITIZEN FOREIGN KEY (citizen_id) REFERENCES citizen(id)
);