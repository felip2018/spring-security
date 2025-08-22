CREATE USER jwtexampleuser@localhost IDENTIFIED BY "Jwt25@"
GRANT ALL PRIVILEGES ON taskdb.* TO jwtexampleuser@localhost
FLUSH PRIVILEGES
SHOW GRANTS FOR jwtexampleuser@localhost