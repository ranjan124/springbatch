#!/bin/bash
sudo apt-get -y autoremove --purge mysql-server

sudo apt-get update
sudo apt-get -y install mysql-server libmysqlclient-dev
mysql -uroot -prootpass -e "CREATE USER 'username'@'192.168.0.182' IDENTIFIED BY 'password';"
mysql -uroot -prootpass -e "CREATE USER 'username'@'192.168.99.101' IDENTIFIED BY 'password';"
mysql -uroot -prootpass -e "GRANT ALL ON *.* TO 'trjena'@'192.168.0.182' WITH GRANT OPTION;";
mysql -uroot -prootpass -e "GRANT ALL ON *.* TO 'trjena'@'192.168.99.101' WITH GRANT OPTION;";
sudo sed -ie 's/127.0.0.1/192.168.99.101/g' /etc/mysql/mysql.conf.d/mysqld.cnf
sudo service mysql restart