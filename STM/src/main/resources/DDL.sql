CREATE SCHEMA `stm_db`;
CREATE USER `stm_user` IDENTIFIED BY 'qwerty';
GRANT ALL PRIVILEGES ON `stm_db`.* TO 'stm_user';
