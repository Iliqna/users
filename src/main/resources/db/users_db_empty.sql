DROP DATABASE IF EXISTS `users_db`;

CREATE DATABASE `users_db`;

USE `users_db`;

DROP TABLE IF EXISTS `t_people`;
CREATE TABLE `t_people` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(90) NOT NULL,
  `pin` varchar(10),
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_mails`;
CREATE TABLE `t_mails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `t_people_id` int NOT NULL,
  `email_type` varchar(5) NOT NULL,
  `email` varchar(40),
   PRIMARY KEY (`id`),
   CONSTRAINT `t_mails_t_people_id` FOREIGN KEY (`t_people_id`) REFERENCES `users_db`.`t_people` (`id`)
)  DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_addresses`;
CREATE TABLE `t_addresses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `t_people_id` int NOT NULL,
  `addr_type` varchar(5) NOT NULL,
  `addr_info` varchar(300),
   PRIMARY KEY (`id`),
   CONSTRAINT `t_addresses_t_people_id` FOREIGN KEY (`t_people_id`) REFERENCES `users_db`.`t_people` (`id`)
)  DEFAULT CHARSET=utf8;


INSERT INTO t_people(full_name, pin) VALUES
("Ivaylo Ivanov", "0123456789"),
("Ivan Ivanov", "1111111111"),
("Стоян Иванов", "2222222222"),
("Ivan Ivanov", "6666666666");

INSERT INTO t_mails(t_people_id, email_type, email) VALUES
((select id from t_people where full_name = "Ivaylo Ivanov"), 'A', "ivan_iv@abv.bg"),
((select id from t_people where full_name = "Ivan Ivanov" and pin = "1111111111"), "A", null),
((select id from t_people where full_name = "Стоян Иванов"), "B", null),
((select id from t_people where full_name = "Ivan Ivanov" and pin = "6666666666"), "B", null)
;


INSERT INTO t_addresses(t_people_id, addr_type, addr_info) VALUES
((select id from t_people where full_name = "Ivaylo Ivanov"), "A", "Ivan Vazov 3"),
((select id from t_people where full_name = "Ivan Ivanov" and pin = "1111111111"), "A", "Boris I"),
((select id from t_people where full_name = "Стоян Иванов"), "B", "Hristo Botev"),
((select id from t_people where full_name = "Ivan Ivanov" and pin = "6666666666"), "B", null);