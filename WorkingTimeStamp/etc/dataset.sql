use sytech;

CREATE TABLE User(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
position VARCHAR(50),
workplace_id INT,
full_name VARCHAR(100),
email VARCHAR(200),
password VARCHAR(100)
);

CREATE TABLE TimeStamp(
  id INT AUTO_INCREMENT PRIMARY KEY,
  workplace_id INT,
  username VARCHAR(200),
  start_time LONG,
  end_time LONG,
  total_hour INT
);

CREATE Table Salary(
  user_id INT,
  workplace_id INT,
  total_hour INT,
  salary DOUBLE,
  total_salary DOUBLE
)
