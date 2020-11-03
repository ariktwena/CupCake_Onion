DROP USER IF EXISTS 'testuser'@'localhost';
DROP DATABASE IF EXISTS cupcake;

# FOREIGN gives problem because it's reference '
# CREATE DATABASE IF NOT EXISTS cupcake;
# CREATE USER IF NOT EXISTS 'testuser'@'localhost';
# grant  INSERT, SELECT, DELETE, UPDATE, DROP, CREATE on cupcake.* TO 'testuser'@'localhost';

CREATE DATABASE IF NOT EXISTS cupcake;
CREATE USER IF NOT EXISTS 'testuser'@'localhost';
grant all privileges on cupcake.* TO 'testuser'@'localhost';