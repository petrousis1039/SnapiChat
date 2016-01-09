DROP TABLE IF EXISTS `user_location`;

CREATE TABLE `user_location` (
	`uid` INT(11) NOT NULL,
	`longitude` VARCHAR(255) NOT NULL,
	`latitude` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`uid`),
	CONSTRAINT `uid_location` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
);
