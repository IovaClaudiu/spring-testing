create table stock (
	stock_id int NOT NULL AUTO_INCREMENT,
	company_name varchar(50),
	symbol varchar(50),
	price decimal(10,2),
	PRIMARY KEY(stock_id)
);