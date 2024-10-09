DROP DATABASE IF EXISTS `materialdb`;
CREATE DATABASE `materialdb`;
USE materialdb;
DROP TABLE IF EXISTS `material_category`;
CREATE TABLE IF NOT EXISTS `material_category` (
	`category_id` VARCHAR(255) NOT NULL,
	`category_name` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`category_id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

#DELETE FROM `material_category`;

INSERT INTO material_category (category_id, category_name) VALUES 
('C001', 'Thread'),
('C002', 'Cloth'),
('C003', 'Button');

commit;

DROP TABLE IF EXISTS `material_type`;
CREATE TABLE `material_type` (
	`type_id` VARCHAR(255) NOT NULL,
	`type_name` VARCHAR(255) NULL DEFAULT NULL,
	`category_id` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`type_id`),
	INDEX `FKog7jr70v8qrs5cw98jitw2jba` (`category_id`),
	CONSTRAINT `FKog7jr70v8qrs5cw98jitw2jba` FOREIGN KEY (`category_id`) REFERENCES `material_category` (`category_id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;
 
#DELETE FROM `material_type`;

INSERT INTO material_type (type_id,type_name,category_id) VALUES
('T001','Silk', 'C001'),
('T002','Silk', 'C002'),
('T003','Linen', 'C001'),
('T004','Linen', 'C002'),
('T005','Silk Cotton', 'C003'),
('T006','Suit', 'C003'),
('T007','Silk Cotton', 'C002');

commit;



DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
	`unit_id` VARCHAR(255) NOT NULL,
	`unit_name` VARCHAR(255) NULL DEFAULT NULL,
	`category_id` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`unit_id`),
	INDEX `FKn0u0wbg8o9xm58y0nvwfen6st` (`category_id`),
	CONSTRAINT `FKn0u0wbg8o9xm58y0nvwfen6st` FOREIGN KEY (`category_id`) REFERENCES `material_category` (`category_id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

#DELETE from `unit`;

INSERT INTO unit (unit_id,unit_name,category_id) VALUES
('U001','Metres','C001'),
('U002','Metres','C002'),
('U003','Yards','C001'),
('U004','Yards','C002'),
('U005','Kilograms','C003');

commit;
