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
  user_id INT,
  start_time datetime,
  end_time datetime,
  total_hour INT
);

CREATE Table Salary(
  user_id INT,
  workplace_id INT,
  total_hour INT,
  salary DOUBLE,
  total_salary DOUBLE
)
