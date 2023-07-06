drop database itss;
create database itss;
use itss;

CREATE TABLE tablenum (
  seq INT
);

INSERT INTO tablenum (seq)
SELECT 1 UNION ALL
SELECT 2 UNION ALL
SELECT 3 UNION ALL
SELECT 4 UNION ALL
SELECT 5 UNION ALL
SELECT 6 UNION ALL
SELECT 7 UNION ALL
SELECT 8 UNION ALL
SELECT 9 UNION ALL
SELECT 10 UNION ALL
SELECT 11 UNION ALL
SELECT 12 UNION ALL
SELECT 13 UNION ALL
SELECT 14 UNION ALL
SELECT 15 UNION ALL
SELECT 16 UNION ALL
SELECT 17 UNION ALL
SELECT 18 UNION ALL
SELECT 19 UNION ALL
SELECT 20 UNION ALL
SELECT 21 UNION ALL
SELECT 22 UNION ALL
SELECT 23 UNION ALL
SELECT 24 UNION ALL
SELECT 25 UNION ALL
SELECT 26 UNION ALL
SELECT 27 UNION ALL
SELECT 28 UNION ALL
SELECT 29 UNION ALL
SELECT 30 UNION ALL
SELECT 31 UNION ALL
SELECT 32 UNION ALL
SELECT 33 UNION ALL
SELECT 34 UNION ALL
SELECT 35 UNION ALL
SELECT 36 UNION ALL
SELECT 37 UNION ALL
SELECT 38 UNION ALL
SELECT 39 UNION ALL
SELECT 40 UNION ALL
SELECT 41 UNION ALL
SELECT 42 UNION ALL
SELECT 43 UNION ALL
SELECT 44 UNION ALL
SELECT 45 UNION ALL
SELECT 46 UNION ALL
SELECT 47 UNION ALL
SELECT 48 UNION ALL
SELECT 49 UNION ALL
SELECT 50 UNION ALL
SELECT 51 UNION ALL
SELECT 52 UNION ALL
SELECT 53 UNION ALL
SELECT 54 UNION ALL
SELECT 55 UNION ALL
SELECT 56 UNION ALL
SELECT 57 UNION ALL
SELECT 58 UNION ALL
SELECT 59 UNION ALL
SELECT 60 UNION ALL
SELECT 61 UNION ALL
SELECT 62 UNION ALL
SELECT 63 UNION ALL
SELECT 64 UNION ALL
SELECT 65 UNION ALL
SELECT 66 UNION ALL
SELECT 67 UNION ALL
SELECT 68 UNION ALL
SELECT 69 UNION ALL
SELECT 70 UNION ALL
SELECT 71 UNION ALL
SELECT 72 UNION ALL
SELECT 73 UNION ALL
SELECT 74 UNION ALL
SELECT 75 UNION ALL
SELECT 76 UNION ALL
SELECT 77 UNION ALL
SELECT 78 UNION ALL
SELECT 79 UNION ALL
SELECT 80 UNION ALL
SELECT 81 UNION ALL
SELECT 82 UNION ALL
SELECT 83 UNION ALL
SELECT 84 UNION ALL
SELECT 85 UNION ALL
SELECT 86 UNION ALL
SELECT 87 UNION ALL
SELECT 88 UNION ALL
SELECT 89 UNION ALL
SELECT 90 UNION ALL
SELECT 91 UNION ALL
SELECT 92 UNION ALL
SELECT 93 UNION ALL
SELECT 94 UNION ALL
SELECT 95 UNION ALL
SELECT 96 UNION ALL
SELECT 97 UNION ALL
SELECT 98 UNION ALL
SELECT 99 UNION ALL
SELECT 100;

-- Tạo bảng room
CREATE TABLE room (
id INT PRIMARY KEY auto_increment,
status BIT,
name VARCHAR(255)
);

-- Tạo bảng room_equipment
-- CREATE TABLE room_equipment (
-- roomId INT,
-- equipmentId int PRIMARY KEY
-- );

-- Tạo bảng equipment_category
CREATE TABLE equipment_category (
id INT PRIMARY KEY auto_increment,
name VARCHAR(255),
code VARCHAR(255)
);

-- Tạo bảng equipment
CREATE TABLE equipment (
equipmentCategoryId INT,
id int PRIMARY KEY auto_increment,
name VARCHAR(255),
status INT,
mfg DATETIME,
yearOfUse DATETIME,
numberOfRepairs INT,
note VARCHAR(255),
roomId int
);

-- Tạo bảng user
CREATE TABLE user (
id INT PRIMARY KEY auto_increment,
name VARCHAR(255),
username VARCHAR(255),
password VARCHAR(255),
role VARCHAR(255)
);

-- Tạo bảng pay_borrow
CREATE TABLE pay_borrow (
id INT PRIMARY KEY auto_increment,
fromDate DATETIME,
toDate DATETIME,
status VARCHAR(255),
borrowReason VARCHAR(255),
refuseReason VARCHAR(255),
borrowerId INT
);

-- Tạo bảng pay_borrow_equipment
CREATE TABLE pay_borrow_equipment (
payBorrowId INT,
equipmentId int
);

-- Tạo bảng room_report
CREATE TABLE room_report (
id INT PRIMARY KEY auto_increment,
roomId INT,
status VARCHAR(255),
createdAt DATETIME,
reporterId INT,
approverId INT,
note varchar(255)
);

-- Tạo bảng room_equipment_report
CREATE TABLE room_equipment_report (
roomReportId INT,
equipmentId int,
status BIT
);

-- Tạo bảng room_schedule
CREATE TABLE room_schedule (
id INT PRIMARY KEY auto_increment,
teacherId INT,
roomId INT,
startTime DATETIME,
endTime DATETIME,
reason VARCHAR(255)
);

-- Thêm khóa ngoại vào các bảng
-- ALTER TABLE room_equipment
-- ADD CONSTRAINT FK_room_equipment_roomId
-- FOREIGN KEY (roomId)
-- REFERENCES room(id)
-- ON DELETE CASCADE
-- ON UPDATE CASCADE;

ALTER TABLE equipment
ADD CONSTRAINT FK_equipment_roomId
FOREIGN KEY (roomId)
REFERENCES room(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE equipment
ADD CONSTRAINT FK_equipment_equipmentCategoryId
FOREIGN KEY (equipmentCategoryId)
REFERENCES equipment_category(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE pay_borrow
ADD CONSTRAINT FK_pay_borrow_borrowerId
FOREIGN KEY (borrowerId)
REFERENCES user(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE room_report
ADD CONSTRAINT FK_room_report_roomId
FOREIGN KEY (roomId)
REFERENCES room(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE room_schedule
ADD CONSTRAINT FK_room_schedule_roomId
FOREIGN KEY (roomId)
REFERENCES room(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE room_schedule
ADD CONSTRAINT FK_room_schedule_teacherId
FOREIGN KEY (teacherId)
REFERENCES user(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE room_report
ADD CONSTRAINT FK_room_report_reporterId
FOREIGN KEY (reporterId)
REFERENCES user(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE room_report
ADD CONSTRAINT FK_room_report_approverId
FOREIGN KEY (approverId)
REFERENCES user(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE room_equipment_report
ADD CONSTRAINT FK_room_equipment_report_roomReportId
FOREIGN KEY (roomReportId)
REFERENCES room_report(roomId)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE room_equipment_report
ADD CONSTRAINT FK_room_equipment_report_equipmentId
FOREIGN KEY (equipmentId)
REFERENCES equipment(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE pay_borrow_equipment
ADD CONSTRAINT FK_pay_borrow_equipment_payBorrowId
FOREIGN KEY (payBorrowId)
REFERENCES pay_borrow(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE pay_borrow_equipment
ADD CONSTRAINT FK_pay_borrow_equipment_equipmentId
FOREIGN KEY (equipmentId)
REFERENCES equipment(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

-- Thêm 100 bộ dữ liệu vào bảng room
INSERT INTO room (id, status, name)
SELECT seq, RAND() > 0.5, CONCAT('Room ', seq)
FROM (SELECT seq FROM tablenum WHERE seq BETWEEN 1 AND 100) AS temp;

-- Thêm 100 bộ dữ liệu vào bảng user
INSERT INTO user (id, name, username, password, role)
SELECT seq, CONCAT('User ', seq), CONCAT('username', seq), CONCAT('password', seq), 'role'
FROM (SELECT seq FROM tablenum WHERE seq BETWEEN 1 AND 100) AS temp;
-- Thêm 100 bộ dữ liệu vào bảng equipment_category
INSERT INTO equipment_category (id, name, code)
VALUES
    (1, 'MayChieu', 'MC'),
    (2, 'ManMayChieu', 'MMC'),
    (3, 'BangDen', 'BD'),
    (4, 'MayTinh', 'MT'),
    (5, 'Loa', 'L'),
    (6, 'Ban', 'B'),
    (7, 'Ghe', 'G'),
    (8, 'Den', 'D'),
    (9, 'KeSach', 'KS');

INSERT INTO equipment (equipmentCategoryId, id, name, status, mfg, yearOfUse, numberOfRepairs, note, roomId)
VALUES
(1,1,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(2,2,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',2),
(4,3,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',3),
(6,4,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',4),
(7,5,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',5),
(3,6,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',6),
(5,7,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',7),
(8,8,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',8),
(9,9,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',9),
(1,10,'MayChieu',2,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',10),
(2,11,'ManMayChieu',2,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',11),
(4,12,'MayTinh',2,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',12),
(6,13,'Ban',2,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',13),
(7,14,'Ghe',2,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',14),
(3,15,'BangDen',2,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',15),
(5,16,'Loa',2,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',16),
(8,17,'Den',2,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',17),
(9,18,'KeSach',2,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',18),
(1,19,'MayChieu',3,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',19),
(2,20,'ManMayChieu',3,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',20),
(4,21,'MayTinh',3,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',21),
(6,22,'Ban',3,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',22),
(7,23,'Ghe',3,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',23),
(3,24,'BangDen',3,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',24),
(5,25,'Loa',3,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',25),
(8,26,'Den',3,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',26),
(9,27,'KeSach',3,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',27),
(1,28,'MayChieu',4,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',28),
(2,29,'ManMayChieu',4,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',29),
(4,30,'MayTinh',4,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',30),
(6,31,'Ban',4,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',31),
(7,32,'Ghe',4,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',32),
(3,33,'BangDen',4,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',33),
(5,34,'Loa',4,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',34),
(8,35,'Den',4,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',35),
(9,36,'KeSach',4,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',36),
(1,37,'MayChieu',5,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',37),
(2,38,'ManMayChieu',5,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',38),
(4,39,'MayTinh',5,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',39),
(6,40,'Ban',5,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',40),
(7,41,'Ghe',5,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',41),
(3,42,'BangDen',5,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',42),
(5,43,'Loa',5,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',43),
(8,44,'Den',5,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',44),
(9,45,'KeSach',5,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',45),
(1,46,'MayChieu',6,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',46),
(2,47,'ManMayChieu',6,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',47),
(4,48,'MayTinh',6,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',48),
(6,49,'Ban',6,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',49),
(7,50,'Ghe',6,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',50),
(3,51,'BangDen',6,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',51),
(5,52,'Loa',6,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',52),
(8,53,'Den',6,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',53),
(9,54,'KeSach',6,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',54),
(1,55,'MayChieu',7,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',55),
(2,56,'ManMayChieu',7,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',56),
(4,57,'MayTinh',7,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',57),
(6,58,'Ban',7,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',58),
(7,59,'Ghe',7,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',59),
(3,60,'BangDen',7,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',60),
(5,61,'Loa',7,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',61),
(8,62,'Den',7,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',62),
(9,63,'KeSach',7,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',63),
(1,64,'MayChieu',8,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',64),
(2,65,'ManMayChieu',8,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',65),
(4,66,'MayTinh',8,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',66),
(6,67,'Ban',8,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',67),
(7,68,'Ghe',8,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',68),
(3,69,'BangDen',8,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',69),
(5,70,'Loa',8,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',70),
(8,71,'Den',8,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',71),
(9,72,'KeSach',8,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',72),
(1,73,'MayChieu',9,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',73),
(2,74,'ManMayChieu',9,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',74),
(4,75,'MayTinh',9,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',75),
(6,76,'Ban',9,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',76),
(7,77,'Ghe',9,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',77),
(3,87,'BangDen',9,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',87),
(5,88,'Loa',9,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',88),
(8,89,'Den',9,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',89),
(9,90,'KeSach',9,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',90);

INSERT INTO pay_borrow (id, fromDate, toDate, status, borrowReason, refuseReason, borrowerId)
SELECT seq, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 10) DAY), DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 10) DAY), 'status', CONCAT('Borrow Reason ', seq), CONCAT('Refuse Reason ', seq), seq
FROM (SELECT seq FROM tablenum WHERE seq BETWEEN 1 AND 100) AS temp;




INSERT INTO pay_borrow_equipment (payBorrowId, equipmentId) 
values (1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 15),
(4, 16),
(4, 17),
(4, 18),
(4, 19),
(4, 20),
(5, 21),
(5, 22),
(5, 23),
(5, 24),
(5, 25),
(6, 26),
(6, 27),
(6, 28),
(6, 29),
(6, 30),
(7, 31),
(7, 32),
(7, 33),
(7, 34),
(7, 35),
(8, 36),
(8, 37),
(8, 38),
(8, 39),
(8, 40),
(9, 41),
(9, 42),
(9, 43),
(9, 44),
(9, 45);

-- INSERT INTO room_equipment (roomId, equipmentId)
-- VALUES  
-- (1, 1),
-- (1, 2),
-- (1, 3),
-- (1, 4),
-- (1, 5),
-- (1, 6),
-- (1, 7),
-- (1, 8),
-- (1, 9),
-- (2, 10),
-- (2, 11),
-- (2, 12),
-- (2, 13),
-- (2, 14),
-- (2, 15),
-- (2, 16),
-- (2, 17),
-- (2, 18),
-- (3, 19),
-- (3, 20),
-- (3, 21),
-- (3, 22),
-- (3, 23),
-- (3, 24),
-- (3, 25),
-- (3, 26),
-- (3, 27),
-- (4, 28),
-- (4, 29),
-- (4, 30),
-- (4, 31),
-- (4, 32),
-- (4, 33),
-- (4, 34),
-- (4, 35),
-- (4, 36),
-- (5, 37),
-- (5, 38),
-- (5, 39),
-- (5, 40),
-- (5, 41),
-- (5, 42),
-- (5, 43),
-- (5, 44),
-- (5, 45),
-- (6, 46),
-- (6, 47),
-- (6, 48),
-- (6, 49),
-- (6, 50),
-- (6, 51),
-- (6, 52),
-- (6, 53),
-- (6, 54),
-- (7, 55),
-- (7, 56),
-- (7, 57),
-- (7, 58),
-- (7, 59),
-- (7, 60),
-- (7, 61),
-- (7, 62),
-- (7, 63),
-- (8, 64),
-- (8, 65),
-- (8, 66),
-- (8, 67),
-- (8, 68),
-- (8, 69),
-- (8, 70),
-- (8, 71),
-- (8, 72),
-- (9, 73),
-- (9, 74),
-- (9, 75),
-- (9, 76),
-- (9, 77),
-- (9, 87),
-- (9, 88),
-- (9, 89),
-- (9, 90);


INSERT INTO `room_report` (`id`, `roomId`, `status`, `createdAt`, `reporterId`, `approverId`, `note`) VALUES
(1,1,'varchar 1', CURRENT_TIMESTAMP(), 1, 2,"note room_report"),
(2,2,'varchar 2', CURRENT_TIMESTAMP(), 2, 3,"note room_report"),
(3,3,'varchar 3', CURRENT_TIMESTAMP(), 3, 4,"note room_report"),
(4,4,'varchar 4', CURRENT_TIMESTAMP(), 4, 5,"note room_report"),
(5,5,'varchar 5', CURRENT_TIMESTAMP(), 5, 6,"note room_report"),
(6,6,'varchar 6', CURRENT_TIMESTAMP(), 6, 7,"note room_report"),
(7,7,'varchar 7', CURRENT_TIMESTAMP(), 7, 8,"note room_report"),
(8,8,'varchar 8', CURRENT_TIMESTAMP(), 8, 9,"note room_report"),
(9,9,'varchar 9', CURRENT_TIMESTAMP(), 9, 10,"note room_report"),
(10,10,'varchar 10', CURRENT_TIMESTAMP(), 10, 11,"note room_report"),
(11,11,'varchar 11', CURRENT_TIMESTAMP(), 11, 12,"note room_report"),
(12,12,'varchar 12', CURRENT_TIMESTAMP(), 12, 13,"note room_report"),
(13,13,'varchar 13', CURRENT_TIMESTAMP(), 13, 14,"note room_report"),
(14,14,'varchar 14', CURRENT_TIMESTAMP(), 14, 15,"note room_report"),
(15,15,'varchar 15', CURRENT_TIMESTAMP(), 15, 16,"note room_report"),
(16,16,'varchar 16', CURRENT_TIMESTAMP(), 16, 17,"note room_report"),
(17,17,'varchar 17', CURRENT_TIMESTAMP(), 17, 18,"note room_report"),
(18,18,'varchar 18', CURRENT_TIMESTAMP(), 18, 19,"note room_report"),
(19,19,'varchar 19', CURRENT_TIMESTAMP(), 19, 20,"note room_report"),
(20,20,'varchar 20', CURRENT_TIMESTAMP(), 20, 21,"note room_report"),
(21,21,'varchar 21', CURRENT_TIMESTAMP(), 21, 22,"note room_report"),
(22,22,'varchar 22', CURRENT_TIMESTAMP(), 22, 23,"note room_report"),
(23,23,'varchar 23', CURRENT_TIMESTAMP(), 23, 24,"note room_report"),
(24,24,'varchar 24', CURRENT_TIMESTAMP(), 24, 25,"note room_report"),
(25,25,'varchar 25', CURRENT_TIMESTAMP(), 25, 26,"note room_report"),
(26,26,'varchar 26', CURRENT_TIMESTAMP(), 26, 27,"note room_report"),
(27,27,'varchar 27', CURRENT_TIMESTAMP(), 27, 28,"note room_report"),
(28,28,'varchar 28', CURRENT_TIMESTAMP(), 28, 29,"note room_report"),
(29,29,'varchar 29', CURRENT_TIMESTAMP(), 29, 30,"note room_report"),
(30,30,'varchar 30', CURRENT_TIMESTAMP(), 30, 31,"note room_report"),
(31,31,'varchar 31', CURRENT_TIMESTAMP(), 31, 32,"note room_report"),
(32,32,'varchar 32', CURRENT_TIMESTAMP(), 32, 33,"note room_report"),
(33,33,'varchar 33', CURRENT_TIMESTAMP(), 33, 34,"note room_report"),
(34,34,'varchar 34', CURRENT_TIMESTAMP(), 34, 35,"note room_report"),
(35,35,'varchar 35', CURRENT_TIMESTAMP(), 35, 36,"note room_report"),
(36,36,'varchar 36', CURRENT_TIMESTAMP(), 36, 37,"note room_report"),
(37,37,'varchar 37', CURRENT_TIMESTAMP(), 37, 38,"note room_report"),
(38,38,'varchar 38', CURRENT_TIMESTAMP(), 38, 39,"note room_report"),
(39,39,'varchar 39', CURRENT_TIMESTAMP(), 39, 40,"note room_report"),
(40,40,'varchar 40', CURRENT_TIMESTAMP(), 40, 41,"note room_report"),
(41,41,'varchar 41', CURRENT_TIMESTAMP(), 41, 42,"note room_report"),
(42,42,'varchar 42', CURRENT_TIMESTAMP(), 42, 43,"note room_report"),
(43,43,'varchar 43', CURRENT_TIMESTAMP(), 43, 44,"note room_report"),
(44,44,'varchar 44', CURRENT_TIMESTAMP(), 44, 45,"note room_report"),
(45,45,'varchar 45', CURRENT_TIMESTAMP(), 45, 46,"note room_report"),
(46,46,'varchar 46', CURRENT_TIMESTAMP(), 46, 47,"note room_report"),
(47,47,'varchar 47', CURRENT_TIMESTAMP(), 47, 48,"note room_report"),
(48,48,'varchar 48', CURRENT_TIMESTAMP(), 48, 49,"note room_report"),
(49,49,'varchar 49', CURRENT_TIMESTAMP(), 49, 50,"note room_report"),
(50,50,'varchar 50', CURRENT_TIMESTAMP(), 50, 51,"note room_report"),
(51,51,'varchar 51', CURRENT_TIMESTAMP(), 51, 52,"note room_report"),
(52,52,'varchar 52', CURRENT_TIMESTAMP(), 52, 53,"note room_report"),
(53,53,'varchar 53', CURRENT_TIMESTAMP(), 53, 54,"note room_report"),
(54,54,'varchar 54', CURRENT_TIMESTAMP(), 54, 55,"note room_report"),
(55,55,'varchar 55', CURRENT_TIMESTAMP(), 55, 56,"note room_report"),
(56,56,'varchar 56', CURRENT_TIMESTAMP(), 56, 57,"note room_report"),
(57,57,'varchar 57', CURRENT_TIMESTAMP(), 57, 58,"note room_report"),
(58,58,'varchar 58', CURRENT_TIMESTAMP(), 58, 59,"note room_report"),
(59,59,'varchar 59', CURRENT_TIMESTAMP(), 59, 60,"note room_report"),
(60,60,'varchar 60', CURRENT_TIMESTAMP(), 60, 61,"note room_report"),
(61,61,'varchar 61', CURRENT_TIMESTAMP(), 61, 62,"note room_report"),
(62,62,'varchar 62', CURRENT_TIMESTAMP(), 62, 63,"note room_report"),
(63,63,'varchar 63', CURRENT_TIMESTAMP(), 63, 64,"note room_report"),
(64,64,'varchar 64', CURRENT_TIMESTAMP(), 64, 65,"note room_report"),
(65,65,'varchar 65', CURRENT_TIMESTAMP(), 65, 66,"note room_report"),
(66,66,'varchar 66', CURRENT_TIMESTAMP(), 66, 67,"note room_report"),
(67,67,'varchar 67', CURRENT_TIMESTAMP(), 67, 68,"note room_report"),
(68,68,'varchar 68', CURRENT_TIMESTAMP(), 68, 69,"note room_report"),
(69,69,'varchar 69', CURRENT_TIMESTAMP(), 69, 70,"note room_report"),
(70,70,'varchar 70', CURRENT_TIMESTAMP(), 70, 71,"note room_report"),
(71,71,'varchar 71', CURRENT_TIMESTAMP(), 71, 72,"note room_report"),
(72,72,'varchar 72', CURRENT_TIMESTAMP(), 72, 73,"note room_report"),
(73,73,'varchar 73', CURRENT_TIMESTAMP(), 73, 74,"note room_report"),
(74,74,'varchar 74', CURRENT_TIMESTAMP(), 74, 75,"note room_report"),
(75,75,'varchar 75', CURRENT_TIMESTAMP(), 75, 76,"note room_report"),
(76,76,'varchar 76', CURRENT_TIMESTAMP(), 76, 77,"note room_report"),
(77,77,'varchar 77', CURRENT_TIMESTAMP(), 77, 78,"note room_report"),
(87,87,'varchar 87', CURRENT_TIMESTAMP(), 87, 88,"note room_report"),
(88,88,'varchar 88', CURRENT_TIMESTAMP(), 88, 89,"note room_report"),
(89,89,'varchar 89', CURRENT_TIMESTAMP(), 89, 90,"note room_report"),
(90,90,'varchar 90', CURRENT_TIMESTAMP(), 90, 91,"note room_report"),
(91,91,'varchar 91', CURRENT_TIMESTAMP(), 91, 92,"note room_report"),
(92,92,'varchar 92', CURRENT_TIMESTAMP(), 92, 93,"note room_report"),
(93,93,'varchar 93', CURRENT_TIMESTAMP(), 93, 94,"note room_report"),
(94,94,'varchar 94', CURRENT_TIMESTAMP(), 94, 95,"note room_report"),
(95,95,'varchar 95', CURRENT_TIMESTAMP(), 95, 96,"note room_report"),
(96,96,'varchar 96', CURRENT_TIMESTAMP(), 96, 97,"note room_report"),
(97,97,'varchar 97', CURRENT_TIMESTAMP(), 97, 98,"note room_report"),
(98,98,'varchar 98', CURRENT_TIMESTAMP(), 98, 99,"note room_report"),
(99,99,'varchar 99', CURRENT_TIMESTAMP(), 99, 100,"note room_report");

INSERT INTO `room_schedule` (`id`, `teacherId`, `roomId`, `startTime`, `endTime`, `reason`) VALUES

(1,1,1, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 1'),
(2,2,2, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 2'),  
(3,3,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 3'),  
(4,4,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 4'),  
(5,5,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 5'),  
(6,6,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 6'),  
(7,7,7, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 7'),  
(8,8,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 8'),  
(9,9,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 9'),  
(10,10,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 10'),
(11,11,11, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 11'),
(12,12,12, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 12'),
(13,13,13, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 13'),
(14,14,14, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 14'),
(15,15,15, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 15'),
(16,16,16, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 16'),
(17,17,17, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 17'),
(18,18,18, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 18'),
(19,19,19, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 19'),
(20,20,20, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 20'),
(21,21,21, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 21'),
(22,22,22, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 22'),
(23,23,23, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 23'),
(24,24,24, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 24'),
(25,25,25, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 25'),
(26,26,26, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 26'),
(27,27,27, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 27'),
(28,28,28, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 28'),
(29,29,29, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 29'),
(30,30,30, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 30'),
(31,31,31, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 31'),
(32,32,32, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 32'),
(33,33,33, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 33'),
(34,34,34, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 34'),
(35,35,35, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 35'),
(36,36,36, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 36'),
(37,37,37, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 37'),
(38,38,38, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 38'),
(39,39,39, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 39'),
(40,40,40, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 40'),
(41,41,41, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 41'),
(42,42,42, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 42'),
(43,43,43, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 43'),
(44,44,44, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 44'),
(45,45,45, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 45'),
(46,46,46, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 46'),
(47,47,47, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 47'),
(48,48,48, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 48'),
(49,49,49, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 49'),
(50,50,50, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 50'),
(51,51,51, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 51'),
(52,52,52, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 52'),
(53,53,53, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 53'),
(54,54,54, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 54'),
(55,55,55, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 55'),
(56,56,56, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 56'),
(57,57,57, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 57'),
(58,58,58, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 58'),
(59,59,59, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 59'),
(60,60,60, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 60'),
(61,61,61, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 61'),
(62,62,62, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 62'),
(63,63,63, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 63'),
(64,64,64, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 64'),
(65,65,65, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 65'),
(66,66,66, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 66'),
(67,67,67, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 67'),
(68,68,68, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 68'),
(69,69,69, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 69'),
(70,70,70, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 70'),
(71,71,71, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 71'),
(72,72,72, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 72'),
(73,73,73, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 73'),
(74,74,74, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 74'),
(75,75,75, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 75'),
(76,76,76, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 76'),
(77,77,77, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 77'),
(78,78,78, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 78'),
(79,79,79, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 79'),
(80,80,80, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 80'),
(81,81,81, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 81'),
(82,82,82, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 82'),
(83,83,83, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 83'),
(84,84,84, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 84'),
(85,85,85, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 85'),
(86,86,86, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 86'),
(87,87,87, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 87'),
(88,88,88, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 88'),
(89,89,89, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 89'),
(90,90,90, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 90'),
(91,91,91, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 91'),
(92,92,92, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 92'),
(93,93,93, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 93'),
(94,94,94, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 94'),
(95,95,95, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 95'),
(96,96,96, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 96'),
(97,97,97, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 97'),
(98,98,98, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 98'),
(99,99,99, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 99');



INSERT INTO room_equipment_report (roomReportId, equipmentId,status)
values 
(1, 1, 1),
(1, 2, 0),
(1, 3, 1),
(2, 4, 0),
(2, 5, 0),
(2, 6, 0),
(3, 7, 1),
(3, 8, 0),
(3, 9, 1),
(4, 10, 0),
(4, 11, 0),
(4, 12, 0),
(5, 13, 1),
(5, 14, 0),
(5, 15, 1),
(6, 16, 0),
(6, 17, 0),
(6, 18, 0);


