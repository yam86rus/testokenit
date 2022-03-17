-- Создание пользователя, и бд.
-- Остальное в liquibase

CREATE USER okenit_test_user WITH PASSWORD 'Okenit_Test_User_123#@!';
ALTER ROLE okenit_test_user createdb;
CREATE DATABASE testokenit_db;

