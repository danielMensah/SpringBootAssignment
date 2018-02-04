create table if NOT EXISTS employee
(
  id IDENTITY auto_increment not null,
  name VARCHAR(155) not null,
  salary DECIMAL(155,2) not null,
  PRIMARY KEY(id)
);