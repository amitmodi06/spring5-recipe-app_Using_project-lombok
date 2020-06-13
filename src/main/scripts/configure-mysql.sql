## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3307:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE amit_dev;
CREATE DATABASE amit_prod;

#Create database service accounts
CREATE USER 'amit_dev_user'@'localhost' IDENTIFIED BY 'amit';
CREATE USER 'amit_prod_user'@'localhost' IDENTIFIED BY 'amit';
CREATE USER 'amit_dev_user'@'%' IDENTIFIED BY 'amit';
CREATE USER 'amit_prod_user'@'%' IDENTIFIED BY 'amit';

#Database grants
GRANT SELECT ON amit_dev.* to 'amit_dev_user'@'localhost';
GRANT INSERT ON amit_dev.* to 'amit_dev_user'@'localhost';
GRANT DELETE ON amit_dev.* to 'amit_dev_user'@'localhost';
GRANT UPDATE ON amit_dev.* to 'amit_dev_user'@'localhost';
GRANT SELECT ON amit_prod.* to 'amit_prod_user'@'localhost';
GRANT INSERT ON amit_prod.* to 'amit_prod_user'@'localhost';
GRANT DELETE ON amit_prod.* to 'amit_prod_user'@'localhost';
GRANT UPDATE ON amit_prod.* to 'amit_prod_user'@'localhost';
GRANT SELECT ON amit_dev.* to 'amit_dev_user'@'%';
GRANT INSERT ON amit_dev.* to 'amit_dev_user'@'%';
GRANT DELETE ON amit_dev.* to 'amit_dev_user'@'%';
GRANT UPDATE ON amit_dev.* to 'amit_dev_user'@'%';
GRANT SELECT ON amit_prod.* to 'amit_prod_user'@'%';
GRANT INSERT ON amit_prod.* to 'amit_prod_user'@'%';
GRANT DELETE ON amit_prod.* to 'amit_prod_user'@'%';
GRANT UPDATE ON amit_prod.* to 'amit_prod_user'@'%';