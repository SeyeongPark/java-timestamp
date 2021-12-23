use sytech;

CREATE TABLE Employee(
emp_id int,
manager_id int,
workplace_id int,
name varchar(100),
salary double,
start_time datetime,
end_time datetime,
email varchar(200),
password varchar(100)
);



CREATE TABLE Manager(
manager_id INT NOT NULL AUTO_INCREMENT,
workplace_id INT,
name VARCHAR(100),
email VARCHAR(200),
password VARCHAR(100),
PRIMARY KEY (manager_id)
);