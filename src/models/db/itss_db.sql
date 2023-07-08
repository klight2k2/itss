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
REFERENCES room_report(id)
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
VALUES
(1,0,'nhakho'),
(2, 1, 'D5P101'),
(3, 1, 'D9P101'),
(4, 1, 'D5P102'),
(5, 1, 'D9P102'),
(6, 1, 'D5P103'),
(7, 1, 'D9P103'),
(8, 1, 'D5P104'),
(9, 1, 'D9P104'),
(10, 1, 'D5P201'),
(11, 1, 'D9P201'),
(12, 1, 'D5P202'),
(13, 1, 'D9P202'),
(14, 1, 'D5P203'),
(15, 1, 'D9P203'),
(16, 1, 'D5P204'),
(17, 1, 'D9P204'),
(18, 1, 'D5P301'),
(19, 1, 'D9P301'),
(20, 1, 'D5P302'),
(21, 1, 'D9P302'),
(22, 1, 'D5P303'),
(23, 1, 'D9P303'),
(24, 1, 'D5P304'),
(25, 1, 'D9P304');

-- Thêm 100 bộ dữ liệu vào bảng user
INSERT INTO user (name, username, password, role)
values 
('Dao Xuan Minh', 'daoxuanminh', 'admin123', 'Admin'),
('Pham Duc Thang', 'phamducthang', 'admin123', 'Admin'),
('Pham Minh Quang', 'phamminhquang', 'admin123', 'Admin'),
('Vu Anh Quan', 'vuanhquan', 'admin123', 'Admin'),
('Pham Quang Nhat', 'phamquangnhat', 'admin123', 'Teacher'),
('Tran Thi Bao', 'tranthibao', 'admin123', 'Teacher'),
('Nguyen Van Tung', 'nguyenvantung', 'admin123', 'Teacher'),
('Pham Thu Ninh', 'phamthuninh', 'admin123', 'Teacher'),
('Vu Tuan Trinh', 'vutuantrinh', 'admin123', 'Teacher'),
('Dang Minh Van', 'dangminhvan', 'admin123', 'Teacher'),
('Nguyen Van Anh', 'nguyenvananh', 'admin123', 'Teacher'),
('Vu Tuan Do', 'vutuando', 'admin123', 'Teacher'),
('Nguyen Van Uan', 'nguyenvanuan', 'admin123', 'Teacher'),
('Tran Thi Trinh', 'tranthitrinh', 'admin123', 'Teacher'),
('Hoang Ngoc Do', 'hoangngocdo', 'admin123', 'Teacher'),
('Le Huu Thang', 'lehuuthang', 'admin123', 'Teacher'),
('Pham Thu Tuong', 'phamthutuong', 'admin123', 'Teacher'),
('Bui Gia Canh', 'buigiacanh', 'admin123', 'Teacher'),
('Pham Thu Duy', 'phamthuduy', 'admin123', 'Teacher'),
('Dang Minh Trinh', 'dangminhtrinh', 'admin123', 'Teacher'),
('Nguyen Van Tuan', 'nguyenvantuan', 'admin123', 'Teacher'),
('Pham Thu Anh', 'phamthuanh', 'admin123', 'Teacher'),
('Hoang Ngoc Trinh', 'hoangngoctrinh', 'admin123', 'Teacher'),
('Vu Tuan Bao', 'vutuanbao', 'admin123', 'Teacher'),
('Le Huu Ly', 'lehuuly', 'admin123', 'Teacher'),
('Tran Thi Phuc', 'tranthiphuc', 'admin123', 'Teacher'),
('Hoang Ngoc Tung', 'hoangngoctung', 'admin123', 'Teacher'),
('Vu Tuan Tuan', 'vutuantuan', 'admin123', 'Teacher'),
('Hoang Ngoc Phuc', 'hoangngocphuc', 'admin123', 'Teacher'),
('Huynh Thao Son', 'huynhthaoson', 'admin123', 'Teacher'),
('Dang Minh Canh', 'dangminhcanh', 'admin123', 'Teacher'),
('Tran Thi Toan', 'tranthitoan', 'admin123', 'Teacher'),
('Phan Duc Thanh', 'phanducthanh', 'admin123', 'Teacher'),
('Phan Duc Hoan', 'phanduchoan', 'admin123', 'Teacher'),
('Tran Thi Son', 'tranthison', 'admin123', 'Teacher'),
('Vu Tuan Uan', 'vutuanuan', 'admin123', 'Teacher'),
('Vu Tuan Tung', 'vutuantung', 'admin123', 'Teacher'),
('Phan Duc Tuong', 'phanductuong', 'admin123', 'Teacher'),
('Huynh Thao Canh', 'huynhthaocanh', 'admin123', 'Teacher'),
('Huynh Thao Linh', 'huynhthaolinh', 'admin123', 'Teacher'),
('Huynh Thao Han', 'huynhthaohan', 'admin123', 'Teacher'),
('Tran Thi Tung', 'tranthitung', 'admin123', 'Teacher'),
('Le Huu Han', 'lehuuhan', 'admin123', 'Teacher'),
('Le Huu Quy', 'lehuuquy', 'admin123', 'Teacher'),
('Nguyen Van Ninh', 'nguyenvanninh', 'admin123', 'Teacher'),
('Bui Gia Ly', 'buigialy', 'admin123', 'Teacher'),
('Dang Minh Do', 'dangminhdo', 'admin123', 'Teacher'),
('Dang Minh Son', 'dangminhson', 'admin123', 'Teacher'),
('Huynh Thao Do', 'huynhthaodo', 'admin123', 'Teacher'),
('Phan Duc Tuan', 'phanductuan', 'admin123', 'Teacher'),
('Nguyen Van Quyen', 'nguyenvanquyen', 'admin123', 'Teacher'),
('Le Huu Quyen', 'lehuuquyen', 'admin123', 'Teacher'),
('Vu Tuan Phuc', 'vutuanphuc', 'admin123', 'Teacher'),
('Hoang Ngoc Son', 'hoangngocson', 'admin123', 'Teacher'),
('Dang Minh Linh', 'dangminhlinh', 'admin123', 'Teacher'),
('Tran Thi Linh', 'tranthilinh', 'admin123', 'Teacher'),
('Le Huu Tuong', 'lehuutuong', 'admin123', 'Teacher'),
('Vu Tuan Duy', 'vutuanduy', 'admin123', 'Teacher'),
('Dang Minh Ly', 'dangminhly', 'admin123', 'Teacher'),
('Phan Duc Uan', 'phanducuan', 'admin123', 'Teacher'),
('Bui Gia Quy', 'buigiaquy', 'admin123', 'Teacher'),
('Hoang Ngoc Bao', 'hoangngocbao', 'admin123', 'Teacher'),
('Vu Tuan Toan', 'vutuantoan', 'admin123', 'Teacher'),
('Hoang Ngoc Duy', 'hoangngocduy', 'admin123', 'Teacher'),
('Huynh Thao Van', 'huynhthaovan', 'admin123', 'Teacher'),
('Le Huu Linh', 'lehuulinh', 'admin123', 'Teacher'),
('Le Huu Hoan', 'lehuuhoan', 'admin123', 'Teacher'),
('Nguyen Van Duy', 'nguyenvanduy', 'admin123', 'Teacher'),
('Bui Gia Quyen', 'buigiaquyen', 'admin123', 'Teacher'),
('Huynh Thao Thang', 'huynhthaothang', 'admin123', 'Teacher'),
('Nguyen Van Phuc', 'nguyenvanphuc', 'admin123', 'Teacher'),
('Bui Gia Van', 'buigiavan', 'admin123', 'Teacher'),
('Hoang Ngoc Uan', 'hoangngocuan', 'admin123', 'Teacher'),
('Le Huu Canh', 'lehuucanh', 'admin123', 'Teacher'),
('Pham Thu Tuan', 'phamthutuan', 'admin123', 'Teacher'),
('Tran Thi Han', 'tranthihan', 'admin123', 'Teacher'),
('Dang Minh Toan', 'dangminhtoan', 'admin123', 'Teacher'),
('Tran Thi Thang', 'tranthithang', 'admin123', 'Teacher'),
('Phan Duc Ninh', 'phanducninh', 'admin123', 'Teacher'),
('Bui Gia Tuong', 'buigiatuong', 'admin123', 'Teacher'),
('Bui Gia Ninh', 'buigianinh', 'admin123', 'Teacher'),
('Huynh Thao Hoan', 'huynhthaohoan', 'admin123', 'Teacher'),
('Nguyen Van Thanh', 'nguyenvanthanh', 'admin123', 'Teacher'),
('Pham Thu Uan', 'phamthuuan', 'admin123', 'Teacher'),
('Tran Thi Do', 'tranthido', 'admin123', 'Teacher'),
('Huynh Thao Quy', 'huynhthaoquy', 'admin123', 'Teacher'),
('Phan Duc Quyen', 'phanducquyen', 'admin123', 'Teacher'),
('Dang Minh Han', 'dangminhhan', 'admin123', 'Teacher');
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
(1,1,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',2),
(2,2,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',2),
(4,3,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',2),
(6,4,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',2),
(7,5,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',2),
(3,6,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',2),
(5,7,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',2),
(8,8,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',2),
(9,9,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',2),
(1,10,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',3),
(2,11,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',3),
(4,12,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',3),
(6,13,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',3),
(7,14,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',3),
(3,15,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',3),
(5,16,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',3),
(8,17,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',3),
(9,18,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',3),
(1,19,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',4),
(2,20,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',4),
(4,21,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',4),
(6,22,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',4),
(7,23,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',4),
(3,24,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',4),
(5,25,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',4),
(8,26,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',4),
(9,27,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',4),
(1,28,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',5),
(2,29,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',5),
(4,30,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',5),
(6,31,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',5),
(7,32,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',5),
(3,33,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',5),
(5,34,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',5),
(8,35,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',5),
(9,36,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',5),
(1,37,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',6),
(2,38,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',6),
(4,39,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',6),
(6,40,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',6),
(7,41,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',6),
(3,42,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',6),
(5,43,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',6),
(8,44,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',6),
(9,45,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',6),
(1,46,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',7),
(2,47,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',7),
(4,48,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',7),
(6,49,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',7),
(7,50,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',7),
(3,51,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',7),
(5,52,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',7),
(8,53,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',7),
(9,54,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',7),
(1,55,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',8),
(2,56,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',8),
(4,57,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',8),
(6,58,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',8),
(7,59,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',8),
(3,60,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',8),
(5,61,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',8),
(8,62,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',8),
(9,63,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',8),
(1,64,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',9),
(2,65,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',9),
(4,66,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',9),
(6,67,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',9),
(7,68,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',9),
(3,69,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',9),
(5,70,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',9),
(8,71,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',9),
(9,72,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',9),
(1,73,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',10),
(2,74,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',10),
(4,75,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',10),
(6,76,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',10),
(7,77,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',10),
(3,78,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',10),
(5,79,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',10),
(8,80,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',10),
(9,81,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',10),
(1,82,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',11),
(2,83,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',11),
(4,84,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',11),
(6,85,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',11),
(7,86,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',11),
(3,87,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',11),
(5,88,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',11),
(8,89,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',11),
(9,90,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',11),
(1,91,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',12),
(2,92,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',12),
(4,93,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',12),
(6,94,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',12),
(7,95,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',12),
(3,96,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',12),
(5,97,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',12),
(8,98,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',12),
(9,99,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',12),
(1,100,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',13),
(2,101,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',13),
(4,102,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',13),
(6,103,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',13),
(7,104,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',13),
(3,105,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',13),
(5,106,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',13),
(8,107,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',13),
(9,108,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',13),
(1,109,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',14),
(2,110,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',14),
(4,111,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',14),
(6,112,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',14),
(7,113,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',14),
(3,114,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',14),
(5,115,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',14),
(8,116,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',14),
(9,117,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',14),
(1,118,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',15),
(2,119,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',15),
(4,120,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',15),
(6,121,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',15),
(7,122,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',15),
(3,123,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',15),
(5,124,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',15),
(8,125,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',15),
(9,126,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',15),
(1,127,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',16),
(2,128,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',16),
(4,129,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',16),
(6,130,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',16),
(7,131,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',16),
(3,132,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',16),
(5,133,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',16),
(8,134,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',16),
(9,135,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',16),
(1,136,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',17),
(2,137,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',17),
(4,138,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',17),
(6,139,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',17),
(7,140,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',17),
(3,141,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',17),
(5,142,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',17),
(8,143,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',17),
(9,144,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',17),
(1,145,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',18),
(2,146,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',18),
(4,147,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',18),
(6,148,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',18),
(7,149,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',18),
(3,150,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',18),
(5,151,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',18),
(8,152,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',18),
(9,153,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',18),
(1,154,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',19),
(2,155,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',19),
(4,156,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',19),
(6,157,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',19),
(7,158,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',19),
(3,159,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',19),
(5,160,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',19),
(8,161,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',19),
(9,162,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',19),
(1,163,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',20),
(2,164,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',20),
(4,165,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',20),
(6,166,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',20),
(7,167,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',20),
(3,168,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',20),
(5,169,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',20),
(8,170,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',20),
(9,171,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',20),
(1,172,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',21),
(2,173,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',21),
(4,174,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',21),
(6,175,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',21),
(7,176,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',21),
(3,177,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',21),
(5,178,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',21),
(8,179,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',21),
(9,180,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',21),
(1,181,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',22),
(2,182,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',22),
(4,183,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',22),
(6,184,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',22),
(7,185,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',22),
(3,186,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',22),
(5,187,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',22),
(8,188,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',22),
(9,189,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',22),
(1,190,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',23),
(2,191,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',23),
(4,192,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',23),
(6,193,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',23),
(7,194,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',23),
(3,195,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',23),
(5,196,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',23),
(8,197,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',23),
(9,198,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',23),
(1,199,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',24),
(2,200,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',24),
(4,201,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',24),
(6,202,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',24),
(7,203,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',24),
(3,204,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',24),
(5,205,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',24),
(8,206,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',24),
(9,207,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',24),
(1,208,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',25),
(2,209,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',25),
(4,210,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',25),
(6,211,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',25),
(7,212,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',25),
(3,213,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',25),
(5,214,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',25),
(8,215,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',25),
(9,216,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',25),
(1,217,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(2,218,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(4,219,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(6,220,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(7,221,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(3,222,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(5,223,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(8,224,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(9,225,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(1,226,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(2,227,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(4,228,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(6,229,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(7,230,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(3,231,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(5,232,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(8,233,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(9,234,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(1,235,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(2,236,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(4,237,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(6,238,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(7,239,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(3,240,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(5,241,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(8,242,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(9,243,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(1,244,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(2,245,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(4,246,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(6,247,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(7,248,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(3,249,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(5,250,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(8,251,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(9,252,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(1,253,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(2,254,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(4,255,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(6,256,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(7,257,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(3,258,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(5,259,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(8,260,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(9,261,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(1,262,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(2,263,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(4,264,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(6,265,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(7,266,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(3,267,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(5,268,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(8,269,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(9,270,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(1,271,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(2,272,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(4,273,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(6,274,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(7,275,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(3,276,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(5,277,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(8,278,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(9,279,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(1,280,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(2,281,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(4,282,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(6,283,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(7,284,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(3,285,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(5,286,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(8,287,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(9,288,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(1,289,'MayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(2,290,'ManMayChieu',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(4,291,'MayTinh',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(6,292,'Ban',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 2',1),
(7,293,'Ghe',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(3,294,'BangDen',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(5,295,'Loa',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 1',1),
(8,296,'Den',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1),
(9,297,'KeSach',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),0,'Note 0',1);

INSERT INTO pay_borrow (id, fromDate, toDate, status, borrowReason, refuseReason, borrowerId) values
(1,DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY),'PENDING','Tao dieu kien cho cac hoat dong tuong tac',NULL,2),
(2,DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY),'REJECTED','Tang cuong viec tra cuu tai lieu','Dang bao tri va sua chua',28),
(3,DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY),'PENDING','Nang cao chat luong giang day',NULL,6),
(4,DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY),'PAID','Tang cuong viec tra cuu tai lieu',NULL,60),
(5,DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY),'BORROWING','Ho tro trinh bay va chia se thong tin',NULL,66),
(6,DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY),'PAID','Ho tro trinh bay va chia se thong tin',NULL,46),
(7,DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY),'APPROVED','Tang cuong viec tra cuu tai lieu',NULL,39),
(8,DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY),'REJECTED','Ho tro trinh bay va chia se thong tin','Thieu thong tin va giay to can thiet',24),
(9,DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY),'APPROVED','Tao dieu kien cho cac hoat dong tuong tac',NULL,13);




-- INSERT INTO pay_borrow_equipment (payBorrowId, equipmentId) 
-- values 
-- (1, 210),
-- (1, 211),
-- (1, 212),
-- (1, 213),
-- (1, 214),
-- (2, 215),
-- (2, 216),
-- (2, 217),
-- (2, 218),
-- (2, 219),
-- (3, 220),
-- (3, 221),
-- (3, 222),
-- (3, 223),
-- (3, 224),
-- (4, 225),
-- (4, 226),
-- (4, 227),
-- (4, 228),
-- (4, 229),
-- (5, 230),
-- (5, 231),
-- (5, 232),
-- (5, 233),
-- (5, 234),
-- (6, 235),
-- (6, 236),
-- (6, 237),
-- (6, 238),
-- (6, 239),
-- (7, 240),
-- (7, 241),
-- (7, 242),
-- (7, 243),
-- (7, 244),
-- (8, 245),
-- (8, 246),
-- (8, 247),
-- (8, 248),
-- (8, 249),
-- (9, 250),
-- (9, 251),
-- (9, 252),
-- (9, 253),
-- (9, 254);

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


INSERT INTO `room_report` (`roomId`, `status`, `createdAt`, `reporterId`, `approverId`, `note`) VALUES
(2,'APPROVED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),5,1,""),
(3,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),6,1,""),
(4,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),7,1,""),
(5,'PENDING',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),8,1,""),
(6,'PENDING',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),9,1,""),
(7,'APPROVED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),10,1,""),
(8,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),11,1,""),
(9,'PENDING',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),12,1,""),
(10,'PENDING',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),13,1,""),
(11,'PENDING',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),14,1,""),
(12,'PENDING',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),15,1,""),
(13,'PENDING',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),16,1,""),
(14,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),17,1,""),
(15,'APPROVED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),18,1,""),
(16,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),19,1,""),
(17,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),20,1,""),
(18,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),21,1,""),
(19,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),22,1,""),
(20,'APPROVED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),23,1,""),
(21,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),24,1,""),
(22,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),25,1,""),
(23,'APPROVED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),26,1,""),
(24,'REJECTED',DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY),27,1,"");

INSERT INTO `room_schedule` (`teacherId`, `roomId`, `startTime`, `endTime`, `reason`) VALUES

(2,2, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 2'),  
(3,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 3'),  
(4,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 4'),  
(5,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 5'),  
(6,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 6'),  
(7,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 7'),  
(8,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 8'),  
(9,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 9'),  
(10,2, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 10');
-- (11,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 11'),
-- (12,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 12'),
-- (13,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 13'),
-- (14,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 14'),
-- (15,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 15'),
-- (16,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 16'),
-- (17,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 17'),
-- (18,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 18'),
-- (19,3, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 19'),
-- (20,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 20'),
-- (21,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 21'),
-- (22,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 22'),
-- (23,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 23'),
-- (24,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 24'),
-- (25,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 25'),
-- (26,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 26'),
-- (27,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 27'),
-- (28,4, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 28'),
-- (29,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 29'),
-- (30,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 30'),
-- (31,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 31'),
-- (32,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 32'),
-- (33,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 33'),
-- (34,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 34'),
-- (35,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 35'),
-- (36,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 36'),
-- (37,5, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 37'),
-- (38,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 38'),
-- (39,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 39'),
-- (40,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 40'),
-- (41,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 41'),
-- (42,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 42'),
-- (43,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 43'),
-- (44,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 44'),
-- (45,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 45'),
-- (46,6, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 46'),
-- (47,7, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 47'),
-- (48,7, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 48'),
-- (49,7, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 49'),
-- (50,7, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 50'),
-- (51,7, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 51'),
-- (52,7, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 52'),
-- (53,7, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 53'),
-- (54,7, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 54'),
-- (55,7, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 55'),
-- (56,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 56'),
-- (57,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 57'),
-- (58,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 58'),
-- (59,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 59'),
-- (60,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 60'),
-- (61,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 61'),
-- (62,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 62'),
-- (63,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 63'),
-- (64,8, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 64'),
-- (65,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 65'),
-- (66,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 66'),
-- (67,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 67'),
-- (68,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 68'),
-- (69,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 69'),
-- (70,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 70'),
-- (71,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 71'),
-- (72,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 72'),
-- (73,9, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 73'),
-- (74,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 74'),
-- (75,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 75'),
-- (76,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 76'),
-- (77,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 77'),
-- (78,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 78'),
-- (79,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 79'),
-- (80,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 80'),
-- (81,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 81'),
-- (82,10, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 82'),
-- (83,11, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 83'),
-- (84,11, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 84'),
-- (85,11, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 85'),
-- (86,11, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 86'),
-- (86,11, DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), DATE_ADD(DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 30) DAY), INTERVAL 10 DAY), 'varchar 87');



INSERT INTO room_equipment_report (roomReportId, equipmentId, status)
values 
(2, 1, 0),
(2, 2, 0),
(2, 3, 0),
(2, 4, 0),
(3, 12, 0),
(3, 13, 0),
(3, 14, 0),
(3, 15, 0),
(4, 19, 0),
(4, 23, 0),
(4, 24, 0),
(4, 25, 0),
(5, 28, 0),
(5, 30, 0),
(5, 32, 0),
(5, 34, 0),
(6, 41, 0),
(6, 42, 0),
(6, 43, 0),
(6, 44, 0),
(7, 46, 0),
(7, 47, 0),
(7, 48, 0),
(7, 49, 0),
(8, 60, 0),
(8, 61, 0),
(8, 62, 0),
(8, 63, 0),
(9, 68, 0),
(9, 69, 0),
(9, 70, 0),
(9, 71, 0),
(10, 73, 0),
(10, 74, 0),
(10, 75, 0),
(10, 76, 0),
(11, 82, 0),
(11, 83, 0),
(11, 84, 0),
(11, 85, 0),
(12, 92, 0),
(12, 93, 0),
(12, 94, 0),
(12, 95, 0),
(13, 104, 0),
(13, 105, 0),
(13, 106, 0),
(13, 107, 0),
(14, 111, 0),
(14, 112, 0),
(14, 113, 0),
(14, 114, 0),
(15, 118, 0),
(15, 119, 0),
(15, 120, 0),
(15, 121, 0),
(16, 127, 0),
(16, 128, 0),
(16, 129, 0),
(16, 130, 0),
(17, 139, 0),
(17, 140, 0),
(17, 141, 0),
(17, 142, 0),
(18, 146, 0),
(18, 147, 0),
(18, 148, 0),
(18, 149, 0),
(19, 156, 0),
(19, 157, 0),
(19, 158, 0),
(19, 159, 0),
(20, 164, 0),
(20, 165, 0),
(20, 166, 0),
(20, 167, 0),
(21, 173, 0),
(21, 174, 0),
(21, 175, 0),
(21, 176, 0),
(22, 181, 0),
(22, 182, 0),
(22, 183, 0),
(22, 184, 0),
(23, 195, 0),
(23, 196, 0),
(23, 197, 0),
(23, 198, 0);



