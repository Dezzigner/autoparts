
create database

cd src/main/resources/database
mysql -u root -p < schema.sql
for filling database

cd src/main/resources/database
mysql -u root -p < seeder.sql