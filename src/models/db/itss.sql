drop database itss;

create database itss;

use itss;

CREATE TABLE tablenum ( seq INT );

INSERT INTO tablenum (seq)
SELECT 1
UNION ALL
SELECT 2
UNION ALL
SELECT 3
UNION ALL
SELECT 4
UNION ALL
SELECT 5
UNION ALL
SELECT 6
UNION ALL
SELECT 7
UNION ALL
SELECT 8
UNION ALL
SELECT 9
UNION ALL
SELECT 10
UNION ALL
SELECT 11
UNION ALL
SELECT 12
UNION ALL
SELECT 13
UNION ALL
SELECT 14
UNION ALL
SELECT 15
UNION ALL
SELECT 16
UNION ALL
SELECT 17
UNION ALL
SELECT 18
UNION ALL
SELECT 19
UNION ALL
SELECT 20
UNION ALL
SELECT 21
UNION ALL
SELECT 22
UNION ALL
SELECT 23
UNION ALL
SELECT 24
UNION ALL
SELECT 25
UNION ALL
SELECT 26
UNION ALL
SELECT 27
UNION ALL
SELECT 28
UNION ALL
SELECT 29
UNION ALL
SELECT 30
UNION ALL
SELECT 31
UNION ALL
SELECT 32
UNION ALL
SELECT 33
UNION ALL
SELECT 34
UNION ALL
SELECT 35
UNION ALL
SELECT 36
UNION ALL
SELECT 37
UNION ALL
SELECT 38
UNION ALL
SELECT 39
UNION ALL
SELECT 40
UNION ALL
SELECT 41
UNION ALL
SELECT 42
UNION ALL
SELECT 43
UNION ALL
SELECT 44
UNION ALL
SELECT 45
UNION ALL
SELECT 46
UNION ALL
SELECT 47
UNION ALL
SELECT 48
UNION ALL
SELECT 49
UNION ALL
SELECT 50
UNION ALL
SELECT 51
UNION ALL
SELECT 52
UNION ALL
SELECT 53
UNION ALL
SELECT 54
UNION ALL
SELECT 55
UNION ALL
SELECT 56
UNION ALL
SELECT 57
UNION ALL
SELECT 58
UNION ALL
SELECT 59
UNION ALL
SELECT 60
UNION ALL
SELECT 61
UNION ALL
SELECT 62
UNION ALL
SELECT 63
UNION ALL
SELECT 64
UNION ALL
SELECT 65
UNION ALL
SELECT 66
UNION ALL
SELECT 67
UNION ALL
SELECT 68
UNION ALL
SELECT 69
UNION ALL
SELECT 70
UNION ALL
SELECT 71
UNION ALL
SELECT 72
UNION ALL
SELECT 73
UNION ALL
SELECT 74
UNION ALL
SELECT 75
UNION ALL
SELECT 76
UNION ALL
SELECT 77
UNION ALL
SELECT 78
UNION ALL
SELECT 79
UNION ALL
SELECT 80
UNION ALL
SELECT 81
UNION ALL
SELECT 82
UNION ALL
SELECT 83
UNION ALL
SELECT 84
UNION ALL
SELECT 85
UNION ALL
SELECT 86
UNION ALL
SELECT 87
UNION ALL
SELECT 88
UNION ALL
SELECT 89
UNION ALL
SELECT 90
UNION ALL
SELECT 91
UNION ALL
SELECT 92
UNION ALL
SELECT 93
UNION ALL
SELECT 94
UNION ALL
SELECT 95
UNION ALL
SELECT 96
UNION ALL
SELECT 97
UNION ALL
SELECT 98
UNION ALL
SELECT 99
UNION ALL
SELECT 100;

-- Tạo bảng room

CREATE TABLE
    room (
        id INT PRIMARY KEY,
        status BIT,
        name VARCHAR(255)
    );

-- Tạo bảng room_equipment

CREATE TABLE
    room_equipment (
        roomId INT,
        equipmentId VARCHAR(255) PRIMARY KEY
    );

-- Tạo bảng equipment_category

CREATE TABLE
    equipment_category (
        id INT PRIMARY KEY,
        name VARCHAR(255),
        code VARCHAR(255)
    );

-- Tạo bảng equipment

CREATE TABLE
    equipment (
        equipmentCategoryId INT,
        id VARCHAR(255) PRIMARY KEY,
        name VARCHAR(255),
        status INT,
        mfg DATE,
        yearOfUse DATE,
        numberOfRepairs INT,
        note VARCHAR(255)
    );

-- Tạo bảng user

CREATE TABLE
    user (
        id INT PRIMARY KEY,
        name VARCHAR(255),
        username VARCHAR(255),
        password VARCHAR(255),
        role VARCHAR(255)
    );

-- Tạo bảng pay_borrow

CREATE TABLE
    pay_borrow (
        id INT PRIMARY KEY,
        fromDate DATE,
        toDate DATE,
        status VARCHAR(255),
        borrowReason VARCHAR(255),
        refuseReason VARCHAR(255),
        borrowerId INT
    );

-- Tạo bảng pay_borrow_equipment

CREATE TABLE
    pay_borrow_equipment (
        payBorrowId INT,
        equipmentId INT
    );

-- Tạo bảng room_report

CREATE TABLE
    room_report (
        id INT PRIMARY KEY,
        roomId INT,
        status VARCHAR(255),
        createdAt DATE,
        reporterId INT,
        approverId INT
    );

-- Tạo bảng room_equipment_report

CREATE TABLE
    room_equipment_report (
        roomReportId INT,
        equipmentId VARCHAR(255),
        status BIT
    );

-- Tạo bảng room_schedule

CREATE TABLE
    room_schedule (
        id INT PRIMARY KEY,
        teacherId INT,
        roomId INT,
        startTime DATE,
        endTime DATE,
        reason VARCHAR(255)
    );

-- Thêm khóa ngoại vào các bảng

ALTER TABLE room_equipment
ADD
    CONSTRAINT FK_room_equipment_roomId FOREIGN KEY (roomId) REFERENCES room(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE room_equipment
ADD
    CONSTRAINT FK_room_equipment_equipmentId FOREIGN KEY (equipmentId) REFERENCES equipment(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE equipment
ADD
    CONSTRAINT FK_equipment_equipmentCategoryId FOREIGN KEY (equipmentCategoryId) REFERENCES equipment_category(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE pay_borrow
ADD
    CONSTRAINT FK_pay_borrow_borrowerId FOREIGN KEY (borrowerId) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE room_report
ADD
    CONSTRAINT FK_room_report_roomId FOREIGN KEY (roomId) REFERENCES room(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE room_schedule
ADD
    CONSTRAINT FK_room_schedule_roomId FOREIGN KEY (roomId) REFERENCES room(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE room_report
ADD
    CONSTRAINT FK_room_report_reporterId FOREIGN KEY (reporterId) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE room_report
ADD
    CONSTRAINT FK_room_report_approverId FOREIGN KEY (approverId) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE
    room_equipment_report
ADD
    CONSTRAINT FK_room_equipment_report_roomReportId FOREIGN KEY (roomReportId) REFERENCES room_report(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE
    room_equipment_report
ADD
    CONSTRAINT FK_room_equipment_report_equipmentId FOREIGN KEY (equipmentId) REFERENCES equipment(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE
    pay_borrow_equipment
ADD
    CONSTRAINT FK_pay_borrow_equipment_payBorrowId FOREIGN KEY (payBorrowId) REFERENCES pay_borrow(id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Thêm 100 bộ dữ liệu vào bảng room

INSERT INTO
    room (id, status, name)
SELECT
    seq,
    RAND() > 0.5,
    CONCAT('Room ', seq)
FROM (
        SELECT seq
        FROM tablenum
        WHERE
            seq BETWEEN 1 AND 100
    ) AS temp;

-- Thêm 100 bộ dữ liệu vào bảng user

INSERT INTO
    user (
        id,
        name,
        username,
        password,
        role
    )
SELECT
    seq,
    CONCAT('User ', seq),
    CONCAT('username', seq),
    CONCAT('password', seq),
    'role'
FROM (
        SELECT seq
        FROM tablenum
        WHERE
            seq BETWEEN 1 AND 100
    ) AS temp;

-- Thêm 100 bộ dữ liệu vào bảng equipment_category

INSERT INTO
    equipment_category (id, name, code)
VALUES (1, 'MayChieu', 'MC'), (2, 'ManMayChieu', 'MMC'), (3, 'BangDen', 'BD'), (4, 'MayTinh', 'MT'), (5, 'Loa', 'L'), (6, 'Ban', 'B'), (7, 'Ghe', 'G'), (8, 'Den', 'D'), (9, 'KeSach', 'KS');

INSERT INTO
    equipment (
        equipmentCategoryId,
        id,
        name,
        status,
        mfg,
        yearOfUse,
        numberOfRepairs,
        note
    )
VALUES (
        1,
        'MC001',
        'MayChieu',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        1,
        'MC002',
        'MayChieu',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        1,
        'MC003',
        'MayChieu',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        1,
        'MC004',
        'MayChieu',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        1,
        'MC005',
        'MayChieu',
        2,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        1,
        'MC006',
        'MayChieu',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        1,
        'MC007',
        'MayChieu',
        2,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        1,
        'MC008',
        'MayChieu',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        1,
        'MC009',
        'MayChieu',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        1,
        'MC010',
        'MayChieu',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        2,
        'MMC001',
        'ManMayChieu',
        0,
        '2023-01-01',
        '2023-01-01',
        0,
        'Note 1'
    ), (
        2,
        'MMC002',
        'ManMayChieu',
        0,
        '2023-01-01',
        '2023-01-01',
        0,
        'Note 1'
    ), (
        2,
        'MMC003',
        'ManMayChieu',
        1,
        '2023-01-01',
        '2023-01-01',
        0,
        'Note 1'
    ), (
        2,
        'MMC004',
        'ManMayChieu',
        1,
        '2023-01-01',
        '2023-01-01',
        0,
        'Note 1'
    ), (
        2,
        'MMC005',
        'ManMayChieu',
        2,
        '2023-01-01',
        '2023-01-01',
        0,
        'Note 1'
    ), (
        2,
        'MMC006',
        'ManMayChieu',
        1,
        '2023-01-01',
        '2023-01-01',
        0,
        'Note 1'
    ), (
        2,
        'MMC007',
        'ManMayChieu',
        0,
        '2023-01-01',
        '2023-01-01',
        0,
        'Note 1'
    ), (
        2,
        'MMC008',
        'ManMayChieu',
        1,
        '2023-01-01',
        '2023-01-01',
        0,
        'Note 1'
    ), (
        2,
        'MMC009',
        'ManMayChieu',
        2,
        '2023-01-01',
        '2023-01-01',
        0,
        'Note 1'
    ), (
        2,
        'MMC010',
        'ManMayChieu',
        1,
        '2023-01-01',
        '2023-01-01',
        0,
        'Note 1'
    ), (
        3,
        'BD001',
        'BangDen',
        1,
        '2020-01-01',
        '2020-01-01',
        0,
        'Note 1'
    ), (
        3,
        'BD002',
        'BangDen',
        1,
        '2020-01-01',
        '2020-01-01',
        0,
        'Note 1'
    ), (
        3,
        'BD003',
        'BangDen',
        1,
        '2020-01-01',
        '2020-01-01',
        0,
        'Note 1'
    ), (
        3,
        'BD004',
        'BangDen',
        0,
        '2020-01-01',
        '2020-01-01',
        0,
        'Note 1'
    ), (
        3,
        'BD005',
        'BangDen',
        1,
        '2020-01-01',
        '2020-01-01',
        0,
        'Note 1'
    ), (
        3,
        'BD006',
        'BangDen',
        2,
        '2020-01-01',
        '2020-01-01',
        0,
        'Note 1'
    ), (
        3,
        'BD007',
        'BangDen',
        1,
        '2020-01-01',
        '2020-01-01',
        0,
        'Note 1'
    ), (
        3,
        'BD008',
        'BangDen',
        1,
        '2020-01-01',
        '2020-01-01',
        0,
        'Note 1'
    ), (
        3,
        'BD009',
        'BangDen',
        0,
        '2020-01-01',
        '2020-01-01',
        0,
        'Note 1'
    ), (
        3,
        'BD010',
        'BangDen',
        1,
        '2020-01-01',
        '2020-01-01',
        0,
        'Note 1'
    ), (
        4,
        'MT001',
        'MayTinh',
        0,
        '2021-01-01',
        '2021-01-01',
        0,
        'Note 1'
    ), (
        4,
        'MT002',
        'MayTinh',
        0,
        '2021-01-01',
        '2021-01-01',
        0,
        'Note 1'
    ), (
        4,
        'MT003',
        'MayTinh',
        1,
        '2021-01-01',
        '2021-01-01',
        0,
        'Note 1'
    ), (
        4,
        'MT004',
        'MayTinh',
        1,
        '2021-01-01',
        '2021-01-01',
        0,
        'Note 1'
    ), (
        4,
        'MT005',
        'MayTinh',
        0,
        '2021-01-01',
        '2021-01-01',
        0,
        'Note 1'
    ), (
        4,
        'MT006',
        'MayTinh',
        1,
        '2021-01-01',
        '2021-01-01',
        0,
        'Note 1'
    ), (
        4,
        'MT007',
        'MayTinh',
        1,
        '2021-01-01',
        '2021-01-01',
        0,
        'Note 1'
    ), (
        4,
        'MT008',
        'MayTinh',
        2,
        '2021-01-01',
        '2021-01-01',
        0,
        'Note 1'
    ), (
        4,
        'MT009',
        'MayTinh',
        1,
        '2021-01-01',
        '2021-01-01',
        0,
        'Note 1'
    ), (
        4,
        'MT010',
        'MayTinh',
        1,
        '2021-01-01',
        '2021-01-01',
        0,
        'Note 1'
    ), (
        5,
        'L001',
        'Loa',
        0,
        '2019-01-01',
        '2019-01-01',
        0,
        'Note 1'
    ), (
        5,
        'L002',
        'Loa',
        0,
        '2019-01-01',
        '2019-01-01',
        0,
        'Note 1'
    ), (
        5,
        'L003',
        'Loa',
        0,
        '2019-01-01',
        '2019-01-01',
        0,
        'Note 1'
    ), (
        5,
        'L004',
        'Loa',
        1,
        '2019-01-01',
        '2019-01-01',
        0,
        'Note 1'
    ), (
        5,
        'L005',
        'Loa',
        1,
        '2019-01-01',
        '2019-01-01',
        0,
        'Note 1'
    ), (
        5,
        'L006',
        'Loa',
        2,
        '2019-01-01',
        '2019-01-01',
        0,
        'Note 1'
    ), (
        5,
        'L007',
        'Loa',
        1,
        '2019-01-01',
        '2019-01-01',
        0,
        'Note 1'
    ), (
        5,
        'L008',
        'Loa',
        1,
        '2019-01-01',
        '2019-01-01',
        0,
        'Note 1'
    ), (
        5,
        'L009',
        'Loa',
        1,
        '2019-01-01',
        '2019-01-01',
        0,
        'Note 1'
    ), (
        5,
        'L010',
        'Loa',
        1,
        '2019-01-01',
        '2019-01-01',
        0,
        'Note 1'
    ), (
        6,
        'B001',
        'Ban',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        6,
        'B002',
        'Ban',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        6,
        'B003',
        'Ban',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        6,
        'B004',
        'Ban',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        6,
        'B005',
        'Ban',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        6,
        'B006',
        'Ban',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        6,
        'B007',
        'Ban',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        6,
        'B008',
        'Ban',
        2,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        6,
        'B009',
        'Ban',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        6,
        'B010',
        'Ban',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        7,
        'G001',
        'Ghe',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        7,
        'G002',
        'Ghe',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        7,
        'G003',
        'Ghe',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        7,
        'G004',
        'Ghe',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        7,
        'G005',
        'Ghe',
        2,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        7,
        'G006',
        'Ghe',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        7,
        'G007',
        'Ghe',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        7,
        'G008',
        'Ghe',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        7,
        'G009',
        'Ghe',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        7,
        'G010',
        'Ghe',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        8,
        'D001',
        'Den',
        2,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        8,
        'D002',
        'Den',
        2,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        8,
        'D003',
        'Den',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        8,
        'D004',
        'Den',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        8,
        'D005',
        'Den',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        8,
        'D006',
        'Den',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        8,
        'D007',
        'Den',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        8,
        'D008',
        'Den',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        8,
        'D009',
        'Den',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        8,
        'D010',
        'Den',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        9,
        'KS001',
        'KeSach',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        9,
        'KS002',
        'KeSach',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        9,
        'KS003',
        'KeSach',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        9,
        'KS004',
        'KeSach',
        0,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        9,
        'KS005',
        'KeSach',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        9,
        'KS006',
        'KeSach',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        9,
        'KS007',
        'KeSach',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        9,
        'KS008',
        'KeSach',
        1,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        9,
        'KS009',
        'KeSach',
        2,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    ), (
        9,
        'KS010',
        'KeSach',
        2,
        '2022-01-01',
        '2022-01-01',
        0,
        'Note 1'
    );

INSERT INTO
    pay_borrow (
        id,
        fromDate,
        toDate,
        status,
        borrowReason,
        refuseReason,
        borrowerId
    )
SELECT
    seq,
    DATE_SUB(
        NOW(),
        INTERVAL FLOOR(RAND() * 10) DAY
    ),
    DATE_ADD(
        NOW(),
        INTERVAL FLOOR(RAND() * 10) DAY
    ),
    'status',
    CONCAT('Borrow Reason ', seq),
    CONCAT('Refuse Reason ', seq),
    seq
FROM (
        SELECT seq
        FROM tablenum
        WHERE
            seq BETWEEN 1 AND 100
    ) AS temp;

INSERT INTO
    pay_borrow_equipment (payBorrowId, equipmentId)
values (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    ), (
        FLOOR(1 + RAND() * 100),
        FLOOR(1 + RAND() * 100)
    );

-- insert into room_equipment (roomId, equipmentId) select room.id, equipment.id from room, equipment;

INSERT INTO
    `room_report` (
        `id`,
        `roomId`,
        `status`,
        `createdAt`,
        `reporterId`,
        `approverId`
    )
VALUES (
        1,
        1,
        'varchar 1',
        CURRENT_TIMESTAMP(),
        1,
        2
    ), (
        2,
        2,
        'varchar 2',
        CURRENT_TIMESTAMP(),
        2,
        3
    ), (
        3,
        3,
        'varchar 3',
        CURRENT_TIMESTAMP(),
        3,
        4
    ), (
        4,
        4,
        'varchar 4',
        CURRENT_TIMESTAMP(),
        4,
        5
    ), (
        5,
        5,
        'varchar 5',
        CURRENT_TIMESTAMP(),
        5,
        6
    ), (
        6,
        6,
        'varchar 6',
        CURRENT_TIMESTAMP(),
        6,
        7
    ), (
        7,
        7,
        'varchar 7',
        CURRENT_TIMESTAMP(),
        7,
        8
    ), (
        8,
        8,
        'varchar 8',
        CURRENT_TIMESTAMP(),
        8,
        9
    ), (
        9,
        9,
        'varchar 9',
        CURRENT_TIMESTAMP(),
        9,
        10
    ), (
        10,
        10,
        'varchar 10',
        CURRENT_TIMESTAMP(),
        10,
        11
    ), (
        11,
        11,
        'varchar 11',
        CURRENT_TIMESTAMP(),
        11,
        12
    ), (
        12,
        12,
        'varchar 12',
        CURRENT_TIMESTAMP(),
        12,
        13
    ), (
        13,
        13,
        'varchar 13',
        CURRENT_TIMESTAMP(),
        13,
        14
    ), (
        14,
        14,
        'varchar 14',
        CURRENT_TIMESTAMP(),
        14,
        15
    ), (
        15,
        15,
        'varchar 15',
        CURRENT_TIMESTAMP(),
        15,
        16
    ), (
        16,
        16,
        'varchar 16',
        CURRENT_TIMESTAMP(),
        16,
        17
    ), (
        17,
        17,
        'varchar 17',
        CURRENT_TIMESTAMP(),
        17,
        18
    ), (
        18,
        18,
        'varchar 18',
        CURRENT_TIMESTAMP(),
        18,
        19
    ), (
        19,
        19,
        'varchar 19',
        CURRENT_TIMESTAMP(),
        19,
        20
    ), (
        20,
        20,
        'varchar 20',
        CURRENT_TIMESTAMP(),
        20,
        21
    ), (
        21,
        21,
        'varchar 21',
        CURRENT_TIMESTAMP(),
        21,
        22
    ), (
        22,
        22,
        'varchar 22',
        CURRENT_TIMESTAMP(),
        22,
        23
    ), (
        23,
        23,
        'varchar 23',
        CURRENT_TIMESTAMP(),
        23,
        24
    ), (
        24,
        24,
        'varchar 24',
        CURRENT_TIMESTAMP(),
        24,
        25
    ), (
        25,
        25,
        'varchar 25',
        CURRENT_TIMESTAMP(),
        25,
        26
    ), (
        26,
        26,
        'varchar 26',
        CURRENT_TIMESTAMP(),
        26,
        27
    ), (
        27,
        27,
        'varchar 27',
        CURRENT_TIMESTAMP(),
        27,
        28
    ), (
        28,
        28,
        'varchar 28',
        CURRENT_TIMESTAMP(),
        28,
        29
    ), (
        29,
        29,
        'varchar 29',
        CURRENT_TIMESTAMP(),
        29,
        30
    ), (
        30,
        30,
        'varchar 30',
        CURRENT_TIMESTAMP(),
        30,
        31
    ), (
        31,
        31,
        'varchar 31',
        CURRENT_TIMESTAMP(),
        31,
        32
    ), (
        32,
        32,
        'varchar 32',
        CURRENT_TIMESTAMP(),
        32,
        33
    ), (
        33,
        33,
        'varchar 33',
        CURRENT_TIMESTAMP(),
        33,
        34
    ), (
        34,
        34,
        'varchar 34',
        CURRENT_TIMESTAMP(),
        34,
        35
    ), (
        35,
        35,
        'varchar 35',
        CURRENT_TIMESTAMP(),
        35,
        36
    ), (
        36,
        36,
        'varchar 36',
        CURRENT_TIMESTAMP(),
        36,
        37
    ), (
        37,
        37,
        'varchar 37',
        CURRENT_TIMESTAMP(),
        37,
        38
    ), (
        38,
        38,
        'varchar 38',
        CURRENT_TIMESTAMP(),
        38,
        39
    ), (
        39,
        39,
        'varchar 39',
        CURRENT_TIMESTAMP(),
        39,
        40
    ), (
        40,
        40,
        'varchar 40',
        CURRENT_TIMESTAMP(),
        40,
        41
    ), (
        41,
        41,
        'varchar 41',
        CURRENT_TIMESTAMP(),
        41,
        42
    ), (
        42,
        42,
        'varchar 42',
        CURRENT_TIMESTAMP(),
        42,
        43
    ), (
        43,
        43,
        'varchar 43',
        CURRENT_TIMESTAMP(),
        43,
        44
    ), (
        44,
        44,
        'varchar 44',
        CURRENT_TIMESTAMP(),
        44,
        45
    ), (
        45,
        45,
        'varchar 45',
        CURRENT_TIMESTAMP(),
        45,
        46
    ), (
        46,
        46,
        'varchar 46',
        CURRENT_TIMESTAMP(),
        46,
        47
    ), (
        47,
        47,
        'varchar 47',
        CURRENT_TIMESTAMP(),
        47,
        48
    ), (
        48,
        48,
        'varchar 48',
        CURRENT_TIMESTAMP(),
        48,
        49
    ), (
        49,
        49,
        'varchar 49',
        CURRENT_TIMESTAMP(),
        49,
        50
    ), (
        50,
        50,
        'varchar 50',
        CURRENT_TIMESTAMP(),
        50,
        51
    ), (
        51,
        51,
        'varchar 51',
        CURRENT_TIMESTAMP(),
        51,
        52
    ), (
        52,
        52,
        'varchar 52',
        CURRENT_TIMESTAMP(),
        52,
        53
    ), (
        53,
        53,
        'varchar 53',
        CURRENT_TIMESTAMP(),
        53,
        54
    ), (
        54,
        54,
        'varchar 54',
        CURRENT_TIMESTAMP(),
        54,
        55
    ), (
        55,
        55,
        'varchar 55',
        CURRENT_TIMESTAMP(),
        55,
        56
    ), (
        56,
        56,
        'varchar 56',
        CURRENT_TIMESTAMP(),
        56,
        57
    ), (
        57,
        57,
        'varchar 57',
        CURRENT_TIMESTAMP(),
        57,
        58
    ), (
        58,
        58,
        'varchar 58',
        CURRENT_TIMESTAMP(),
        58,
        59
    ), (
        59,
        59,
        'varchar 59',
        CURRENT_TIMESTAMP(),
        59,
        60
    ), (
        60,
        60,
        'varchar 60',
        CURRENT_TIMESTAMP(),
        60,
        61
    ), (
        61,
        61,
        'varchar 61',
        CURRENT_TIMESTAMP(),
        61,
        62
    ), (
        62,
        62,
        'varchar 62',
        CURRENT_TIMESTAMP(),
        62,
        63
    ), (
        63,
        63,
        'varchar 63',
        CURRENT_TIMESTAMP(),
        63,
        64
    ), (
        64,
        64,
        'varchar 64',
        CURRENT_TIMESTAMP(),
        64,
        65
    ), (
        65,
        65,
        'varchar 65',
        CURRENT_TIMESTAMP(),
        65,
        66
    ), (
        66,
        66,
        'varchar 66',
        CURRENT_TIMESTAMP(),
        66,
        67
    ), (
        67,
        67,
        'varchar 67',
        CURRENT_TIMESTAMP(),
        67,
        68
    ), (
        68,
        68,
        'varchar 68',
        CURRENT_TIMESTAMP(),
        68,
        69
    ), (
        69,
        69,
        'varchar 69',
        CURRENT_TIMESTAMP(),
        69,
        70
    ), (
        70,
        70,
        'varchar 70',
        CURRENT_TIMESTAMP(),
        70,
        71
    ), (
        71,
        71,
        'varchar 71',
        CURRENT_TIMESTAMP(),
        71,
        72
    ), (
        72,
        72,
        'varchar 72',
        CURRENT_TIMESTAMP(),
        72,
        73
    ), (
        73,
        73,
        'varchar 73',
        CURRENT_TIMESTAMP(),
        73,
        74
    ), (
        74,
        74,
        'varchar 74',
        CURRENT_TIMESTAMP(),
        74,
        75
    ), (
        75,
        75,
        'varchar 75',
        CURRENT_TIMESTAMP(),
        75,
        76
    ), (
        76,
        76,
        'varchar 76',
        CURRENT_TIMESTAMP(),
        76,
        77
    ), (
        77,
        77,
        'varchar 77',
        CURRENT_TIMESTAMP(),
        77,
        78
    ), (
        87,
        87,
        'varchar 87',
        CURRENT_TIMESTAMP(),
        87,
        88
    ), (
        88,
        88,
        'varchar 88',
        CURRENT_TIMESTAMP(),
        88,
        89
    ), (
        89,
        89,
        'varchar 89',
        CURRENT_TIMESTAMP(),
        89,
        90
    ), (
        90,
        90,
        'varchar 90',
        CURRENT_TIMESTAMP(),
        90,
        91
    ), (
        91,
        91,
        'varchar 91',
        CURRENT_TIMESTAMP(),
        91,
        92
    ), (
        92,
        92,
        'varchar 92',
        CURRENT_TIMESTAMP(),
        92,
        93
    ), (
        93,
        93,
        'varchar 93',
        CURRENT_TIMESTAMP(),
        93,
        94
    ), (
        94,
        94,
        'varchar 94',
        CURRENT_TIMESTAMP(),
        94,
        95
    ), (
        95,
        95,
        'varchar 95',
        CURRENT_TIMESTAMP(),
        95,
        96
    ), (
        96,
        96,
        'varchar 96',
        CURRENT_TIMESTAMP(),
        96,
        97
    ), (
        97,
        97,
        'varchar 97',
        CURRENT_TIMESTAMP(),
        97,
        98
    ), (
        98,
        98,
        'varchar 98',
        CURRENT_TIMESTAMP(),
        98,
        99
    ), (
        99,
        99,
        'varchar 99',
        CURRENT_TIMESTAMP(),
        99,
        100
    );

INSERT INTO
    `room_schedule` (
        `id`,
        `teacherId`,
        `roomId`,
        `startTime`,
        `endTime`,
        `reason`
    )
VALUES (
        1,
        1,
        1,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 1'
    ), (
        2,
        2,
        2,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 2'
    ), (
        3,
        3,
        3,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 3'
    ), (
        4,
        4,
        4,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 4'
    ), (
        5,
        5,
        5,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 5'
    ), (
        6,
        6,
        6,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 6'
    ), (
        7,
        7,
        7,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 7'
    ), (
        8,
        8,
        8,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 8'
    ), (
        9,
        9,
        9,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 9'
    ), (
        10,
        10,
        10,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 10'
    ), (
        11,
        11,
        11,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 11'
    ), (
        12,
        12,
        12,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 12'
    ), (
        13,
        13,
        13,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 13'
    ), (
        14,
        14,
        14,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 14'
    ), (
        15,
        15,
        15,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 15'
    ), (
        16,
        16,
        16,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 16'
    ), (
        17,
        17,
        17,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 17'
    ), (
        18,
        18,
        18,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 18'
    ), (
        19,
        19,
        19,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 19'
    ), (
        20,
        20,
        20,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 20'
    ), (
        21,
        21,
        21,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 21'
    ), (
        22,
        22,
        22,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 22'
    ), (
        23,
        23,
        23,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 23'
    ), (
        24,
        24,
        24,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 24'
    ), (
        25,
        25,
        25,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 25'
    ), (
        26,
        26,
        26,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 26'
    ), (
        27,
        27,
        27,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 27'
    ), (
        28,
        28,
        28,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 28'
    ), (
        29,
        29,
        29,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 29'
    ), (
        30,
        30,
        30,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 30'
    ), (
        31,
        31,
        31,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 31'
    ), (
        32,
        32,
        32,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 32'
    ), (
        33,
        33,
        33,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 33'
    ), (
        34,
        34,
        34,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 34'
    ), (
        35,
        35,
        35,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 35'
    ), (
        36,
        36,
        36,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 36'
    ), (
        37,
        37,
        37,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 37'
    ), (
        38,
        38,
        38,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 38'
    ), (
        39,
        39,
        39,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 39'
    ), (
        40,
        40,
        40,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 40'
    ), (
        41,
        41,
        41,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 41'
    ), (
        42,
        42,
        42,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 42'
    ), (
        43,
        43,
        43,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 43'
    ), (
        44,
        44,
        44,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 44'
    ), (
        45,
        45,
        45,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 45'
    ), (
        46,
        46,
        46,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 46'
    ), (
        47,
        47,
        47,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 47'
    ), (
        48,
        48,
        48,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 48'
    ), (
        49,
        49,
        49,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 49'
    ), (
        50,
        50,
        50,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 50'
    ), (
        51,
        51,
        51,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 51'
    ), (
        52,
        52,
        52,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 52'
    ), (
        53,
        53,
        53,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 53'
    ), (
        54,
        54,
        54,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 54'
    ), (
        55,
        55,
        55,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 55'
    ), (
        56,
        56,
        56,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 56'
    ), (
        57,
        57,
        57,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 57'
    ), (
        58,
        58,
        58,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 58'
    ), (
        59,
        59,
        59,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 59'
    ), (
        60,
        60,
        60,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 60'
    ), (
        61,
        61,
        61,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 61'
    ), (
        62,
        62,
        62,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 62'
    ), (
        63,
        63,
        63,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 63'
    ), (
        64,
        64,
        64,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 64'
    ), (
        65,
        65,
        65,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 65'
    ), (
        66,
        66,
        66,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 66'
    ), (
        67,
        67,
        67,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 67'
    ), (
        68,
        68,
        68,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 68'
    ), (
        69,
        69,
        69,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 69'
    ), (
        70,
        70,
        70,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 70'
    ), (
        71,
        71,
        71,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 71'
    ), (
        72,
        72,
        72,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 72'
    ), (
        73,
        73,
        73,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 73'
    ), (
        74,
        74,
        74,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 74'
    ), (
        75,
        75,
        75,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 75'
    ), (
        76,
        76,
        76,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 76'
    ), (
        77,
        77,
        77,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 77'
    ), (
        78,
        78,
        78,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 78'
    ), (
        79,
        79,
        79,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 79'
    ), (
        80,
        80,
        80,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 80'
    ), (
        81,
        81,
        81,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 81'
    ), (
        82,
        82,
        82,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 82'
    ), (
        83,
        83,
        83,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 83'
    ), (
        84,
        84,
        84,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 84'
    ), (
        85,
        85,
        85,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 85'
    ), (
        86,
        86,
        86,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 86'
    ), (
        87,
        87,
        87,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 87'
    ), (
        88,
        88,
        88,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 88'
    ), (
        89,
        89,
        89,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 89'
    ), (
        90,
        90,
        90,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 90'
    ), (
        91,
        91,
        91,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 91'
    ), (
        92,
        92,
        92,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 92'
    ), (
        93,
        93,
        93,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 93'
    ), (
        94,
        94,
        94,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 94'
    ), (
        95,
        95,
        95,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 95'
    ), (
        96,
        96,
        96,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 96'
    ), (
        97,
        97,
        97,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 97'
    ), (
        98,
        98,
        98,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 98'
    ), (
        99,
        99,
        99,
        DATE_ADD(
            NOW(),
            INTERVAL FLOOR(RAND() * 30) DAY
        ),
        DATE_ADD(
            DATE_ADD(
                NOW(),
                INTERVAL FLOOR(RAND() * 30) DAY
            ),
            INTERVAL 10 DAY
        ),
        'varchar 99'
    );

insert into
    room_equipment_report (
        roomReportId,
        equipmentId,
        status
    )
select
    room_report.id,
    equipment.id,
    FLOOR(RAND() * 2)
from room_report, equipment;

select * from equipment;

INSERT INTO
    room_equipment (roomId, equipmentId)
VALUES (1, "MC001"), (1, "MMC001"), (1, "BD001"), (1, "MT001"), (2, "MC002"), (2, "MMC002"), (2, "BD002"), (3, "MC003"), (3, "MMC003"), (3, "BD003"), (3, "MT003"), (3, "L003"), (4, "MT004"), (4, "MMC004"), (4, "BD004"), (4, "L004"), (5, "MC005"), (5, "MMC005"), (5, "BD005"), (5, "MT005"), (6, "MC006"), (6, "MMC006"), (6, "BD006"), (6, "MT006"), (6, "L006");