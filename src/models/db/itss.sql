-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th7 01, 2023 lúc 12:50 AM
-- Phiên bản máy phục vụ: 5.7.40-log
-- Phiên bản PHP: 7.4.33
drop database itss;
create database itss;
use itss;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `zigisoft`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `equipment`
--

CREATE TABLE `equipment` (
  `equipmentCategoryId` int(11) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `mfg` date DEFAULT NULL,
  `yearOfUse` date DEFAULT NULL,
  `numberOfRepairs` int(11) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `equipment`
--

INSERT INTO `equipment` (`equipmentCategoryId`, `id`, `name`, `status`, `mfg`, `yearOfUse`, `numberOfRepairs`, `note`) VALUES
(6, 'B002', 'Ban', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(6, 'B003', 'Ban', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(6, 'B004', 'Ban', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(6, 'B005', 'Ban', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(6, 'B006', 'Ban', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(6, 'B007', 'Ban', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(6, 'B008', 'Ban', 2, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(6, 'B009', 'Ban', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(6, 'B010', 'Ban', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(3, 'BD002', 'BangDen', 1, '2020-01-01', '2020-01-01', 0, 'Note 1'),
(3, 'BD003', 'BangDen', 1, '2020-01-01', '2020-01-01', 0, 'Note 1'),
(3, 'BD004', 'BangDen', 0, '2020-01-01', '2020-01-01', 0, 'Note 1'),
(3, 'BD005', 'BangDen', 1, '2020-01-01', '2020-01-01', 0, 'Note 1'),
(3, 'BD006', 'BangDen', 2, '2020-01-01', '2020-01-01', 0, 'Note 1'),
(3, 'BD007', 'BangDen', 1, '2020-01-01', '2020-01-01', 0, 'Note 1'),
(3, 'BD008', 'BangDen', 1, '2020-01-01', '2020-01-01', 0, 'Note 1'),
(3, 'BD009', 'BangDen', 0, '2020-01-01', '2020-01-01', 0, 'Note 1'),
(3, 'BD010', 'BangDen', 1, '2020-01-01', '2020-01-01', 0, 'Note 1'),
(8, 'D002', 'Den', 2, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(8, 'D003', 'Den', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(8, 'D004', 'Den', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(8, 'D005', 'Den', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(8, 'D006', 'Den', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(8, 'D007', 'Den', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(8, 'D008', 'Den', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(8, 'D009', 'Den', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(8, 'D010', 'Den', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(7, 'G002', 'Ghe', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(7, 'G003', 'Ghe', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(7, 'G004', 'Ghe', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(7, 'G005', 'Ghe', 2, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(7, 'G006', 'Ghe', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(7, 'G007', 'Ghe', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(7, 'G008', 'Ghe', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(7, 'G009', 'Ghe', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(7, 'G010', 'Ghe', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(9, 'KS002', 'KeSach', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(9, 'KS003', 'KeSach', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(9, 'KS004', 'KeSach', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(9, 'KS005', 'KeSach', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(9, 'KS006', 'KeSach', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(9, 'KS007', 'KeSach', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(9, 'KS008', 'KeSach', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(9, 'KS009', 'KeSach', 2, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(9, 'KS010', 'KeSach', 2, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(5, 'L002', 'Loa', 0, '2019-01-01', '2019-01-01', 0, 'Note 1'),
(5, 'L003', 'Loa', 0, '2019-01-01', '2019-01-01', 0, 'Note 1'),
(5, 'L004', 'Loa', 1, '2019-01-01', '2019-01-01', 0, 'Note 1'),
(5, 'L005', 'Loa', 1, '2019-01-01', '2019-01-01', 0, 'Note 1'),
(5, 'L006', 'Loa', 2, '2019-01-01', '2019-01-01', 0, 'Note 1'),
(5, 'L007', 'Loa', 1, '2019-01-01', '2019-01-01', 0, 'Note 1'),
(5, 'L008', 'Loa', 1, '2019-01-01', '2019-01-01', 0, 'Note 1'),
(5, 'L009', 'Loa', 1, '2019-01-01', '2019-01-01', 0, 'Note 1'),
(5, 'L010', 'Loa', 1, '2019-01-01', '2019-01-01', 0, 'Note 1'),
(1, 'MC002', 'MayChieu', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(1, 'MC003', 'MayChieu', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(1, 'MC004', 'MayChieu', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(1, 'MC005', 'MayChieu', 2, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(1, 'MC006', 'MayChieu', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(1, 'MC007', 'MayChieu', 2, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(1, 'MC008', 'MayChieu', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(1, 'MC009', 'MayChieu', 0, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(1, 'MC010', 'MayChieu', 1, '2022-01-01', '2022-01-01', 0, 'Note 1'),
(2, 'MMC002', 'ManMayChieu', 0, '2023-01-01', '2023-01-01', 0, 'Note 1'),
(2, 'MMC003', 'ManMayChieu', 1, '2023-01-01', '2023-01-01', 0, 'Note 1'),
(2, 'MMC004', 'ManMayChieu', 1, '2023-01-01', '2023-01-01', 0, 'Note 1'),
(2, 'MMC005', 'ManMayChieu', 2, '2023-01-01', '2023-01-01', 0, 'Note 1'),
(2, 'MMC006', 'ManMayChieu', 1, '2023-01-01', '2023-01-01', 0, 'Note 1'),
(2, 'MMC007', 'ManMayChieu', 0, '2023-01-01', '2023-01-01', 0, 'Note 1'),
(2, 'MMC008', 'ManMayChieu', 1, '2023-01-01', '2023-01-01', 0, 'Note 1'),
(2, 'MMC009', 'ManMayChieu', 2, '2023-01-01', '2023-01-01', 0, 'Note 1'),
(2, 'MMC010', 'ManMayChieu', 1, '2023-01-01', '2023-01-01', 0, 'Note 1'),
(4, 'MT002', 'MayTinh', 0, '2021-01-01', '2021-01-01', 0, 'Note 1'),
(4, 'MT003', 'MayTinh', 1, '2021-01-01', '2021-01-01', 0, 'Note 1'),
(4, 'MT004', 'MayTinh', 1, '2021-01-01', '2021-01-01', 0, 'Note 1'),
(4, 'MT005', 'MayTinh', 0, '2021-01-01', '2021-01-01', 0, 'Note 1'),
(4, 'MT006', 'MayTinh', 1, '2021-01-01', '2021-01-01', 0, 'Note 1'),
(4, 'MT007', 'MayTinh', 1, '2021-01-01', '2021-01-01', 0, 'Note 1'),
(4, 'MT008', 'MayTinh', 2, '2021-01-01', '2021-01-01', 0, 'Note 1'),
(4, 'MT009', 'MayTinh', 1, '2021-01-01', '2021-01-01', 0, 'Note 1'),
(4, 'MT010', 'MayTinh', 1, '2021-01-01', '2021-01-01', 0, 'Note 1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `equipment_category`
--

CREATE TABLE `equipment_category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `equipment_category`
--

INSERT INTO `equipment_category` (`id`, `name`, `code`) VALUES
(1, 'MayChieu', 'MC'),
(2, 'ManMayChieu', 'MMC'),
(3, 'BangDen', 'BD'),
(4, 'MayTinh', 'MT'),
(5, 'Loa', 'L'),
(6, 'Ban', 'B'),
(7, 'Ghe', 'G'),
(8, 'Den', 'D'),
(9, 'KeSach', 'KS');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pay_borrow`
--

CREATE TABLE `pay_borrow` (
  `id` int(11) NOT NULL,
  `fromDate` date DEFAULT NULL,
  `toDate` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `borrowReason` varchar(255) DEFAULT NULL,
  `refuseReason` varchar(255) DEFAULT NULL,
  `borrowerId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `pay_borrow`
--

INSERT INTO `pay_borrow` (`id`, `fromDate`, `toDate`, `status`, `borrowReason`, `refuseReason`, `borrowerId`) VALUES
(1, '2023-06-28', '2023-06-30', 'status', 'Borrow Reason 1', 'Refuse Reason 1', 1),
(2, '2023-06-28', '2023-07-03', 'status', 'Borrow Reason 2', 'Refuse Reason 2', 2),
(3, '2023-06-30', '2023-07-01', 'status', 'Borrow Reason 3', 'Refuse Reason 3', 3),
(4, '2023-06-23', '2023-07-01', 'status', 'Borrow Reason 4', 'Refuse Reason 4', 4),
(5, '2023-06-25', '2023-07-04', 'status', 'Borrow Reason 5', 'Refuse Reason 5', 5),
(6, '2023-06-26', '2023-07-08', 'status', 'Borrow Reason 6', 'Refuse Reason 6', 6),
(7, '2023-06-29', '2023-07-01', 'status', 'Borrow Reason 7', 'Refuse Reason 7', 7),
(8, '2023-06-30', '2023-07-09', 'status', 'Borrow Reason 8', 'Refuse Reason 8', 8),
(9, '2023-06-25', '2023-06-30', 'status', 'Borrow Reason 9', 'Refuse Reason 9', 9),
(10, '2023-06-25', '2023-07-04', 'status', 'Borrow Reason 10', 'Refuse Reason 10', 10),
(11, '2023-06-22', '2023-07-06', 'status', 'Borrow Reason 11', 'Refuse Reason 11', 11),
(12, '2023-06-23', '2023-07-06', 'status', 'Borrow Reason 12', 'Refuse Reason 12', 12),
(13, '2023-06-29', '2023-07-06', 'status', 'Borrow Reason 13', 'Refuse Reason 13', 13),
(14, '2023-06-22', '2023-07-04', 'status', 'Borrow Reason 14', 'Refuse Reason 14', 14),
(15, '2023-06-25', '2023-07-02', 'status', 'Borrow Reason 15', 'Refuse Reason 15', 15),
(16, '2023-06-21', '2023-07-07', 'status', 'Borrow Reason 16', 'Refuse Reason 16', 16),
(17, '2023-06-30', '2023-07-08', 'status', 'Borrow Reason 17', 'Refuse Reason 17', 17),
(18, '2023-06-30', '2023-07-08', 'status', 'Borrow Reason 18', 'Refuse Reason 18', 18),
(19, '2023-06-22', '2023-07-08', 'status', 'Borrow Reason 19', 'Refuse Reason 19', 19),
(20, '2023-06-23', '2023-07-02', 'status', 'Borrow Reason 20', 'Refuse Reason 20', 20),
(21, '2023-06-21', '2023-06-30', 'status', 'Borrow Reason 21', 'Refuse Reason 21', 21),
(22, '2023-06-28', '2023-07-01', 'status', 'Borrow Reason 22', 'Refuse Reason 22', 22),
(23, '2023-06-30', '2023-07-08', 'status', 'Borrow Reason 23', 'Refuse Reason 23', 23),
(24, '2023-06-29', '2023-07-03', 'status', 'Borrow Reason 24', 'Refuse Reason 24', 24),
(25, '2023-06-30', '2023-07-03', 'status', 'Borrow Reason 25', 'Refuse Reason 25', 25),
(26, '2023-06-25', '2023-07-07', 'status', 'Borrow Reason 26', 'Refuse Reason 26', 26),
(27, '2023-06-28', '2023-07-06', 'status', 'Borrow Reason 27', 'Refuse Reason 27', 27),
(28, '2023-06-24', '2023-07-04', 'status', 'Borrow Reason 28', 'Refuse Reason 28', 28),
(29, '2023-06-29', '2023-07-05', 'status', 'Borrow Reason 29', 'Refuse Reason 29', 29),
(30, '2023-06-29', '2023-07-01', 'status', 'Borrow Reason 30', 'Refuse Reason 30', 30),
(31, '2023-06-26', '2023-07-04', 'status', 'Borrow Reason 31', 'Refuse Reason 31', 31),
(32, '2023-06-28', '2023-07-09', 'status', 'Borrow Reason 32', 'Refuse Reason 32', 32),
(33, '2023-06-24', '2023-07-07', 'status', 'Borrow Reason 33', 'Refuse Reason 33', 33),
(34, '2023-06-23', '2023-07-04', 'status', 'Borrow Reason 34', 'Refuse Reason 34', 34),
(35, '2023-06-30', '2023-07-09', 'status', 'Borrow Reason 35', 'Refuse Reason 35', 35),
(36, '2023-06-23', '2023-07-06', 'status', 'Borrow Reason 36', 'Refuse Reason 36', 36),
(37, '2023-06-29', '2023-07-05', 'status', 'Borrow Reason 37', 'Refuse Reason 37', 37),
(38, '2023-06-25', '2023-07-08', 'status', 'Borrow Reason 38', 'Refuse Reason 38', 38),
(39, '2023-06-22', '2023-07-04', 'status', 'Borrow Reason 39', 'Refuse Reason 39', 39),
(40, '2023-06-30', '2023-07-05', 'status', 'Borrow Reason 40', 'Refuse Reason 40', 40),
(41, '2023-06-22', '2023-07-03', 'status', 'Borrow Reason 41', 'Refuse Reason 41', 41),
(42, '2023-06-26', '2023-06-30', 'status', 'Borrow Reason 42', 'Refuse Reason 42', 42),
(43, '2023-06-30', '2023-07-01', 'status', 'Borrow Reason 43', 'Refuse Reason 43', 43),
(44, '2023-06-24', '2023-07-05', 'status', 'Borrow Reason 44', 'Refuse Reason 44', 44),
(45, '2023-06-23', '2023-07-02', 'status', 'Borrow Reason 45', 'Refuse Reason 45', 45),
(46, '2023-06-29', '2023-07-07', 'status', 'Borrow Reason 46', 'Refuse Reason 46', 46),
(47, '2023-06-28', '2023-07-01', 'status', 'Borrow Reason 47', 'Refuse Reason 47', 47),
(48, '2023-06-21', '2023-07-05', 'status', 'Borrow Reason 48', 'Refuse Reason 48', 48),
(49, '2023-06-25', '2023-07-01', 'status', 'Borrow Reason 49', 'Refuse Reason 49', 49),
(50, '2023-06-30', '2023-06-30', 'status', 'Borrow Reason 50', 'Refuse Reason 50', 50),
(51, '2023-06-21', '2023-07-07', 'status', 'Borrow Reason 51', 'Refuse Reason 51', 51),
(52, '2023-06-22', '2023-06-30', 'status', 'Borrow Reason 52', 'Refuse Reason 52', 52),
(53, '2023-06-23', '2023-07-03', 'status', 'Borrow Reason 53', 'Refuse Reason 53', 53),
(54, '2023-06-26', '2023-07-04', 'status', 'Borrow Reason 54', 'Refuse Reason 54', 54),
(55, '2023-06-24', '2023-06-30', 'status', 'Borrow Reason 55', 'Refuse Reason 55', 55),
(56, '2023-06-29', '2023-07-08', 'status', 'Borrow Reason 56', 'Refuse Reason 56', 56),
(57, '2023-06-25', '2023-07-03', 'status', 'Borrow Reason 57', 'Refuse Reason 57', 57),
(58, '2023-06-30', '2023-06-30', 'status', 'Borrow Reason 58', 'Refuse Reason 58', 58),
(59, '2023-06-30', '2023-07-03', 'status', 'Borrow Reason 59', 'Refuse Reason 59', 59),
(60, '2023-06-27', '2023-07-06', 'status', 'Borrow Reason 60', 'Refuse Reason 60', 60),
(61, '2023-06-28', '2023-07-02', 'status', 'Borrow Reason 61', 'Refuse Reason 61', 61),
(62, '2023-06-25', '2023-07-09', 'status', 'Borrow Reason 62', 'Refuse Reason 62', 62),
(63, '2023-06-28', '2023-07-02', 'status', 'Borrow Reason 63', 'Refuse Reason 63', 63),
(64, '2023-06-23', '2023-07-09', 'status', 'Borrow Reason 64', 'Refuse Reason 64', 64),
(65, '2023-06-27', '2023-07-07', 'status', 'Borrow Reason 65', 'Refuse Reason 65', 65),
(66, '2023-06-30', '2023-07-08', 'status', 'Borrow Reason 66', 'Refuse Reason 66', 66),
(67, '2023-06-30', '2023-07-09', 'status', 'Borrow Reason 67', 'Refuse Reason 67', 67),
(68, '2023-06-28', '2023-07-06', 'status', 'Borrow Reason 68', 'Refuse Reason 68', 68),
(69, '2023-06-24', '2023-07-09', 'status', 'Borrow Reason 69', 'Refuse Reason 69', 69),
(70, '2023-06-21', '2023-07-09', 'status', 'Borrow Reason 70', 'Refuse Reason 70', 70),
(71, '2023-06-23', '2023-07-08', 'status', 'Borrow Reason 71', 'Refuse Reason 71', 71),
(72, '2023-06-30', '2023-07-07', 'status', 'Borrow Reason 72', 'Refuse Reason 72', 72),
(73, '2023-06-23', '2023-07-05', 'status', 'Borrow Reason 73', 'Refuse Reason 73', 73),
(74, '2023-06-26', '2023-07-04', 'status', 'Borrow Reason 74', 'Refuse Reason 74', 74),
(75, '2023-06-29', '2023-07-03', 'status', 'Borrow Reason 75', 'Refuse Reason 75', 75),
(76, '2023-06-28', '2023-07-02', 'status', 'Borrow Reason 76', 'Refuse Reason 76', 76),
(77, '2023-06-25', '2023-07-09', 'status', 'Borrow Reason 77', 'Refuse Reason 77', 77),
(78, '2023-06-27', '2023-07-05', 'status', 'Borrow Reason 78', 'Refuse Reason 78', 78),
(79, '2023-06-24', '2023-07-08', 'status', 'Borrow Reason 79', 'Refuse Reason 79', 79),
(80, '2023-06-27', '2023-07-02', 'status', 'Borrow Reason 80', 'Refuse Reason 80', 80),
(81, '2023-06-22', '2023-07-08', 'status', 'Borrow Reason 81', 'Refuse Reason 81', 81),
(82, '2023-06-25', '2023-07-03', 'status', 'Borrow Reason 82', 'Refuse Reason 82', 82),
(83, '2023-06-30', '2023-07-02', 'status', 'Borrow Reason 83', 'Refuse Reason 83', 83),
(84, '2023-06-22', '2023-07-07', 'status', 'Borrow Reason 84', 'Refuse Reason 84', 84),
(85, '2023-06-28', '2023-07-07', 'status', 'Borrow Reason 85', 'Refuse Reason 85', 85),
(86, '2023-06-27', '2023-07-01', 'status', 'Borrow Reason 86', 'Refuse Reason 86', 86),
(87, '2023-06-21', '2023-06-30', 'status', 'Borrow Reason 87', 'Refuse Reason 87', 87),
(88, '2023-06-26', '2023-07-03', 'status', 'Borrow Reason 88', 'Refuse Reason 88', 88),
(89, '2023-06-30', '2023-07-05', 'status', 'Borrow Reason 89', 'Refuse Reason 89', 89),
(90, '2023-06-28', '2023-07-09', 'status', 'Borrow Reason 90', 'Refuse Reason 90', 90),
(91, '2023-06-22', '2023-07-02', 'status', 'Borrow Reason 91', 'Refuse Reason 91', 91),
(92, '2023-06-23', '2023-07-02', 'status', 'Borrow Reason 92', 'Refuse Reason 92', 92),
(93, '2023-06-24', '2023-07-06', 'status', 'Borrow Reason 93', 'Refuse Reason 93', 93),
(94, '2023-06-27', '2023-07-06', 'status', 'Borrow Reason 94', 'Refuse Reason 94', 94),
(95, '2023-06-26', '2023-07-01', 'status', 'Borrow Reason 95', 'Refuse Reason 95', 95),
(96, '2023-06-25', '2023-06-30', 'status', 'Borrow Reason 96', 'Refuse Reason 96', 96),
(97, '2023-06-23', '2023-07-04', 'status', 'Borrow Reason 97', 'Refuse Reason 97', 97),
(98, '2023-06-28', '2023-07-08', 'status', 'Borrow Reason 98', 'Refuse Reason 98', 98),
(99, '2023-06-26', '2023-07-06', 'status', 'Borrow Reason 99', 'Refuse Reason 99', 99),
(100, '2023-06-30', '2023-07-02', 'status', 'Borrow Reason 100', 'Refuse Reason 100', 100);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pay_borrow_equipment`
--

CREATE TABLE `pay_borrow_equipment` (
  `payBorrowId` int(11) DEFAULT NULL,
  `equipmentId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `pay_borrow_equipment`
--

INSERT INTO `pay_borrow_equipment` (`payBorrowId`, `equipmentId`) VALUES
(26, 43),
(33, 39),
(92, 45),
(46, 95),
(37, 99),
(83, 19),
(46, 71),
(15, 63),
(71, 65),
(10, 55),
(45, 61),
(69, 59),
(90, 72),
(91, 39),
(21, 86),
(69, 86),
(24, 58),
(20, 27),
(72, 79),
(79, 59),
(58, 10),
(78, 57),
(52, 86),
(77, 24),
(89, 73),
(98, 69),
(52, 51),
(98, 36),
(84, 15),
(19, 52),
(3, 59),
(84, 45),
(69, 11),
(49, 10),
(4, 89),
(32, 93),
(69, 63),
(10, 59),
(67, 56),
(78, 20),
(68, 77),
(83, 81),
(56, 37),
(14, 62);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `room`
--

INSERT INTO `room` (`id`, `status`, `name`) VALUES
(1, b'1', 'Room 1'),
(2, b'0', 'Room 2'),
(3, b'0', 'Room 3'),
(4, b'1', 'Room 4'),
(5, b'0', 'Room 5'),
(6, b'0', 'Room 6'),
(7, b'0', 'Room 7'),
(8, b'1', 'Room 8'),
(9, b'1', 'Room 9'),
(10, b'0', 'Room 10'),
(11, b'1', 'Room 11'),
(12, b'0', 'Room 12'),
(13, b'1', 'Room 13'),
(14, b'1', 'Room 14'),
(15, b'1', 'Room 15'),
(16, b'0', 'Room 16'),
(17, b'0', 'Room 17'),
(18, b'0', 'Room 18'),
(19, b'1', 'Room 19'),
(20, b'1', 'Room 20'),
(21, b'1', 'Room 21'),
(22, b'0', 'Room 22'),
(23, b'1', 'Room 23'),
(24, b'1', 'Room 24'),
(25, b'1', 'Room 25'),
(26, b'1', 'Room 26'),
(27, b'0', 'Room 27'),
(28, b'1', 'Room 28'),
(29, b'1', 'Room 29'),
(30, b'1', 'Room 30'),
(31, b'0', 'Room 31'),
(32, b'1', 'Room 32'),
(33, b'0', 'Room 33'),
(34, b'1', 'Room 34'),
(35, b'0', 'Room 35'),
(36, b'1', 'Room 36'),
(37, b'1', 'Room 37'),
(38, b'1', 'Room 38'),
(39, b'0', 'Room 39'),
(40, b'0', 'Room 40'),
(41, b'0', 'Room 41'),
(42, b'1', 'Room 42'),
(43, b'0', 'Room 43'),
(44, b'0', 'Room 44'),
(45, b'0', 'Room 45'),
(46, b'0', 'Room 46'),
(47, b'0', 'Room 47'),
(48, b'0', 'Room 48'),
(49, b'0', 'Room 49'),
(50, b'0', 'Room 50'),
(51, b'1', 'Room 51'),
(52, b'1', 'Room 52'),
(53, b'0', 'Room 53'),
(54, b'0', 'Room 54'),
(55, b'1', 'Room 55'),
(56, b'0', 'Room 56'),
(57, b'1', 'Room 57'),
(58, b'0', 'Room 58'),
(59, b'0', 'Room 59'),
(60, b'0', 'Room 60'),
(61, b'0', 'Room 61'),
(62, b'0', 'Room 62'),
(63, b'0', 'Room 63'),
(64, b'1', 'Room 64'),
(65, b'0', 'Room 65'),
(66, b'0', 'Room 66'),
(67, b'1', 'Room 67'),
(68, b'0', 'Room 68'),
(69, b'0', 'Room 69'),
(70, b'1', 'Room 70'),
(71, b'0', 'Room 71'),
(72, b'0', 'Room 72'),
(73, b'0', 'Room 73'),
(74, b'0', 'Room 74'),
(75, b'0', 'Room 75'),
(76, b'1', 'Room 76'),
(77, b'0', 'Room 77'),
(78, b'1', 'Room 78'),
(79, b'0', 'Room 79'),
(80, b'0', 'Room 80'),
(81, b'0', 'Room 81'),
(82, b'1', 'Room 82'),
(83, b'1', 'Room 83'),
(84, b'0', 'Room 84'),
(85, b'0', 'Room 85'),
(86, b'0', 'Room 86'),
(87, b'0', 'Room 87'),
(88, b'1', 'Room 88'),
(89, b'0', 'Room 89'),
(90, b'1', 'Room 90'),
(91, b'0', 'Room 91'),
(92, b'1', 'Room 92'),
(93, b'1', 'Room 93'),
(94, b'0', 'Room 94'),
(95, b'0', 'Room 95'),
(96, b'1', 'Room 96'),
(97, b'1', 'Room 97'),
(98, b'0', 'Room 98'),
(99, b'1', 'Room 99'),
(100, b'1', 'Room 100');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `room_equipment`
--

CREATE TABLE `room_equipment` (
  `roomId` int(11) DEFAULT NULL,
  `equipmentId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `room_equipment`
--

INSERT INTO `room_equipment` (`roomId`, `equipmentId`) VALUES
(1, 'B002'),
(1, 'B002'),
(2, 'B003'),
(3, 'B004'),
(4, 'B005'),
(5, 'B006'),
(6, 'B007'),
(7, 'B002'),
(8, 'B002'),
(9, 'B002'),
(10, 'B002'),
(11, 'B002'),
(12, 'B002'),
(13, 'B002'),
(14, 'B002'),
(15, 'B002'),
(16, 'B002'),
(17, 'B002'),
(18, 'B002'),
(19, 'B002'),
(20, 'B002'),
(21, 'B002'),
(22, 'B002'),
(23, 'B002'),
(24, 'B002'),
(25, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002'),
(1, 'B002');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `room_equipment_report`
--

CREATE TABLE `room_equipment_report` (
  `roomReportId` int(11) DEFAULT NULL,
  `equipmentId` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `room_equipment_report`
--

INSERT INTO `room_equipment_report` (`roomReportId`, `equipmentId`, `status`) VALUES
(72, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0'),
(73, 'B006', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `room_report`
--

CREATE TABLE `room_report` (
  `id` int(11) NOT NULL,
  `roomId` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `createdAt` date DEFAULT NULL,
  `reporterId` int(11) DEFAULT NULL,
  `approverId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `room_report`
--

INSERT INTO `room_report` (`id`, `roomId`, `status`, `createdAt`, `reporterId`, `approverId`) VALUES
(1, 1, 'ok', '2023-06-21', 1, 1),
(2, 1, 'ok', '2023-06-29', 1, 1),
(3, 1, 'ok', '2023-06-29', 1, 1),
(4, 1, 'ok', '2023-06-29', 1, 1),
(5, 1, 'ok', '2023-06-29', 1, 1),
(6, 1, 'ok', '2023-06-29', 1, 1),
(7, 1, 'ok', '2023-06-29', 1, 1),
(8, 1, 'ok', '2023-06-29', 1, 1),
(9, 1, 'ok', '2023-06-29', 1, 1),
(10, 1, 'ok', '2023-06-29', 1, 1),
(11, 1, 'ok', '2023-06-29', 1, 1),
(12, 1, 'ok', '2023-06-29', 1, 1),
(13, 1, 'ok', '2023-06-29', 1, 1),
(14, 1, 'ok', '2023-06-29', 1, 1),
(15, 1, 'ok', '2023-06-29', 1, 1),
(16, 1, 'ok', '2023-06-29', 1, 1),
(17, 1, 'ok', '2023-06-29', 1, 1),
(18, 1, 'ok', '2023-06-29', 1, 1),
(19, 1, 'ok', '2023-06-29', 1, 1),
(20, 1, 'ok', '2023-06-29', 1, 1),
(21, 1, 'ok', '2023-06-29', 1, 1),
(22, 1, 'ok', '2023-06-29', 1, 1),
(23, 1, 'ok', '2023-06-29', 1, 1),
(24, 1, 'ok', '2023-06-29', 1, 1),
(25, 1, 'ok', '2023-06-29', 1, 1),
(26, 1, 'ok', '2023-06-29', 1, 1),
(27, 1, 'ok', '2023-06-29', 1, 1),
(28, 1, 'ok', '2023-06-29', 1, 1),
(29, 1, 'ok', '2023-06-29', 1, 1),
(30, 1, 'ok', '2023-06-29', 1, 1),
(31, 1, 'ok', '2023-06-29', 1, 1),
(32, 1, 'ok', '2023-06-29', 1, 1),
(33, 1, 'ok', '2023-06-29', 1, 1),
(34, 1, 'ok', '2023-06-29', 1, 1),
(35, 1, 'ok', '2023-06-29', 1, 1),
(36, 1, 'ok', '2023-06-29', 1, 1),
(37, 1, 'ok', '2023-06-29', 1, 1),
(38, 1, 'ok', '2023-06-29', 1, 1),
(39, 1, 'ok', '2023-06-29', 1, 1),
(40, 1, 'ok', '2023-06-29', 1, 1),
(41, 1, 'ok', '2023-06-29', 1, 1),
(42, 1, 'ok', '2023-06-29', 1, 1),
(43, 1, 'ok', '2023-06-29', 1, 1),
(44, 1, 'ok', '2023-06-29', 1, 1),
(45, 1, 'ok', '2023-06-29', 1, 1),
(46, 1, 'ok', '2023-06-29', 1, 1),
(47, 1, 'ok', '2023-06-29', 1, 1),
(48, 1, 'ok', '2023-06-29', 1, 1),
(49, 1, 'ok', '2023-06-29', 1, 1),
(50, 1, 'ok', '2023-06-29', 1, 1),
(51, 1, 'ok', '2023-06-29', 1, 1),
(52, 1, 'ok', '2023-06-29', 1, 1),
(53, 1, 'ok', '2023-06-29', 1, 1),
(55, 1, 'ok', '2023-06-29', 1, 1),
(56, 1, 'ok', '2023-06-29', 1, 1),
(57, 1, 'ok', '2023-06-29', 1, 1),
(58, 1, 'ok', '2023-06-29', 1, 1),
(59, 1, 'ok', '2023-06-29', 1, 1),
(60, 1, 'ok', '2023-06-29', 1, 1),
(61, 1, 'ok', '2023-06-29', 1, 1),
(62, 1, 'ok', '2023-06-29', 1, 1),
(63, 1, 'ok', '2023-06-29', 1, 1),
(64, 1, 'ok', '2023-06-29', 1, 1),
(65, 1, 'ok', '2023-06-29', 1, 1),
(66, 1, 'ok', '2023-06-29', 1, 1),
(67, 1, 'ok', '2023-06-29', 1, 1),
(68, 1, 'ok', '2023-06-29', 1, 1),
(69, 1, 'ok', '2023-06-29', 1, 1),
(70, 1, 'ok', '2023-06-29', 1, 1),
(71, 1, 'ok', '2023-06-29', 1, 1),
(72, 1, 'ok', '2023-06-29', 1, 1),
(73, 1, 'ok', '2023-06-29', 1, 1),
(74, 1, 'ok', '2023-06-29', 1, 1),
(75, 1, 'ok', '2023-06-29', 1, 1),
(76, 1, 'ok', '2023-06-29', 1, 1),
(77, 1, 'ok', '2023-06-29', 1, 1),
(78, 1, 'ok', '2023-06-29', 1, 1),
(79, 1, 'ok', '2023-06-29', 1, 1),
(80, 1, 'ok', '2023-06-29', 1, 1),
(81, 1, 'ok', '2023-06-29', 1, 1),
(82, 1, 'ok', '2023-06-29', 1, 1),
(83, 1, 'ok', '2023-06-29', 1, 1),
(84, 1, 'ok', '2023-06-29', 1, 1),
(85, 1, 'ok', '2023-06-29', 1, 1),
(86, 1, 'ok', '2023-06-29', 1, 1),
(87, 1, 'ok', '2023-06-29', 1, 1),
(88, 1, 'ok', '2023-06-29', 1, 1),
(89, 1, 'ok', '2023-06-29', 1, 1),
(90, 1, 'ok', '2023-06-29', 1, 1),
(91, 1, 'ok', '2023-06-29', 1, 1),
(92, 1, 'ok', '2023-06-29', 1, 1),
(93, 1, 'ok', '2023-06-29', 1, 1),
(94, 1, 'ok', '2023-06-29', 1, 1),
(95, 1, 'ok', '2023-06-29', 1, 1),
(96, 1, 'ok', '2023-06-29', 1, 1),
(97, 1, 'ok', '2023-06-29', 1, 1),
(98, 1, 'ok', '2023-06-29', 1, 1),
(99, 1, 'ok', '2023-06-29', 1, 1),
(100, 1, 'ok', '2023-06-29', 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `room_schedule`
--

CREATE TABLE `room_schedule` (
  `id` int(11) NOT NULL,
  `teacherId` int(11) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  `startTime` date DEFAULT NULL,
  `endTime` date DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `room_schedule`
--

INSERT INTO `room_schedule` (`id`, `teacherId`, `roomId`, `startTime`, `endTime`, `reason`) VALUES
(1, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(2, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(3, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(4, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(5, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(6, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(7, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(8, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(9, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(10, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(11, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(12, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(13, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(14, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(15, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(16, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(17, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(18, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(19, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(20, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(21, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(22, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(23, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(24, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(25, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(26, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(27, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(28, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(29, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(30, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(31, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(32, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(33, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(34, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(35, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(36, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(37, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(38, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(39, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(40, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(41, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(42, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(43, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(44, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(45, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(46, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(47, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(48, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(49, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(50, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(51, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(59, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(60, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(61, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(62, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(63, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(64, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(65, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(66, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(67, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(68, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(69, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(70, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(71, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(72, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(73, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(74, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(75, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(76, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(77, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(78, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(79, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(80, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(81, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(82, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(83, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(84, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(85, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(86, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(87, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(88, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(89, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(90, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(91, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(92, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(93, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(94, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(95, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(96, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(97, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(98, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(99, 1, 1, '2023-06-15', '2023-06-29', 'ok a'),
(100, 1, 1, '2023-06-15', '2023-06-29', 'ok a');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tablenum`
--

CREATE TABLE `tablenum` (
  `seq` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tablenum`
--

INSERT INTO `tablenum` (`seq`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24),
(25),
(26),
(27),
(28),
(29),
(30),
(31),
(32),
(33),
(34),
(35),
(36),
(37),
(38),
(39),
(40),
(41),
(42),
(43),
(44),
(45),
(46),
(47),
(48),
(49),
(50),
(51),
(52),
(53),
(54),
(55),
(56),
(57),
(58),
(59),
(60),
(61),
(62),
(63),
(64),
(65),
(66),
(67),
(68),
(69),
(70),
(71),
(72),
(73),
(74),
(75),
(76),
(77),
(78),
(79),
(80),
(81),
(82),
(83),
(84),
(85),
(86),
(87),
(88),
(89),
(90),
(91),
(92),
(93),
(94),
(95),
(96),
(97),
(98),
(99),
(100);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `name`, `username`, `password`, `role`) VALUES
(1, 'User 1', 'username1', 'password1', 'role'),
(2, 'User 2', 'username2', 'password2', 'role'),
(3, 'User 3', 'username3', 'password3', 'role'),
(4, 'User 4', 'username4', 'password4', 'role'),
(5, 'User 5', 'username5', 'password5', 'role'),
(6, 'User 6', 'username6', 'password6', 'role'),
(7, 'User 7', 'username7', 'password7', 'role'),
(8, 'User 8', 'username8', 'password8', 'role'),
(9, 'User 9', 'username9', 'password9', 'role'),
(10, 'User 10', 'username10', 'password10', 'role'),
(11, 'User 11', 'username11', 'password11', 'role'),
(12, 'User 12', 'username12', 'password12', 'role'),
(13, 'User 13', 'username13', 'password13', 'role'),
(14, 'User 14', 'username14', 'password14', 'role'),
(15, 'User 15', 'username15', 'password15', 'role'),
(16, 'User 16', 'username16', 'password16', 'role'),
(17, 'User 17', 'username17', 'password17', 'role'),
(18, 'User 18', 'username18', 'password18', 'role'),
(19, 'User 19', 'username19', 'password19', 'role'),
(20, 'User 20', 'username20', 'password20', 'role'),
(21, 'User 21', 'username21', 'password21', 'role'),
(22, 'User 22', 'username22', 'password22', 'role'),
(23, 'User 23', 'username23', 'password23', 'role'),
(24, 'User 24', 'username24', 'password24', 'role'),
(25, 'User 25', 'username25', 'password25', 'role'),
(26, 'User 26', 'username26', 'password26', 'role'),
(27, 'User 27', 'username27', 'password27', 'role'),
(28, 'User 28', 'username28', 'password28', 'role'),
(29, 'User 29', 'username29', 'password29', 'role'),
(30, 'User 30', 'username30', 'password30', 'role'),
(31, 'User 31', 'username31', 'password31', 'role'),
(32, 'User 32', 'username32', 'password32', 'role'),
(33, 'User 33', 'username33', 'password33', 'role'),
(34, 'User 34', 'username34', 'password34', 'role'),
(35, 'User 35', 'username35', 'password35', 'role'),
(36, 'User 36', 'username36', 'password36', 'role'),
(37, 'User 37', 'username37', 'password37', 'role'),
(38, 'User 38', 'username38', 'password38', 'role'),
(39, 'User 39', 'username39', 'password39', 'role'),
(40, 'User 40', 'username40', 'password40', 'role'),
(41, 'User 41', 'username41', 'password41', 'role'),
(42, 'User 42', 'username42', 'password42', 'role'),
(43, 'User 43', 'username43', 'password43', 'role'),
(44, 'User 44', 'username44', 'password44', 'role'),
(45, 'User 45', 'username45', 'password45', 'role'),
(46, 'User 46', 'username46', 'password46', 'role'),
(47, 'User 47', 'username47', 'password47', 'role'),
(48, 'User 48', 'username48', 'password48', 'role'),
(49, 'User 49', 'username49', 'password49', 'role'),
(50, 'User 50', 'username50', 'password50', 'role'),
(51, 'User 51', 'username51', 'password51', 'role'),
(52, 'User 52', 'username52', 'password52', 'role'),
(53, 'User 53', 'username53', 'password53', 'role'),
(54, 'User 54', 'username54', 'password54', 'role'),
(55, 'User 55', 'username55', 'password55', 'role'),
(56, 'User 56', 'username56', 'password56', 'role'),
(57, 'User 57', 'username57', 'password57', 'role'),
(58, 'User 58', 'username58', 'password58', 'role'),
(59, 'User 59', 'username59', 'password59', 'role'),
(60, 'User 60', 'username60', 'password60', 'role'),
(61, 'User 61', 'username61', 'password61', 'role'),
(62, 'User 62', 'username62', 'password62', 'role'),
(63, 'User 63', 'username63', 'password63', 'role'),
(64, 'User 64', 'username64', 'password64', 'role'),
(65, 'User 65', 'username65', 'password65', 'role'),
(66, 'User 66', 'username66', 'password66', 'role'),
(67, 'User 67', 'username67', 'password67', 'role'),
(68, 'User 68', 'username68', 'password68', 'role'),
(69, 'User 69', 'username69', 'password69', 'role'),
(70, 'User 70', 'username70', 'password70', 'role'),
(71, 'User 71', 'username71', 'password71', 'role'),
(72, 'User 72', 'username72', 'password72', 'role'),
(73, 'User 73', 'username73', 'password73', 'role'),
(74, 'User 74', 'username74', 'password74', 'role'),
(75, 'User 75', 'username75', 'password75', 'role'),
(76, 'User 76', 'username76', 'password76', 'role'),
(77, 'User 77', 'username77', 'password77', 'role'),
(78, 'User 78', 'username78', 'password78', 'role'),
(79, 'User 79', 'username79', 'password79', 'role'),
(80, 'User 80', 'username80', 'password80', 'role'),
(81, 'User 81', 'username81', 'password81', 'role'),
(82, 'User 82', 'username82', 'password82', 'role'),
(83, 'User 83', 'username83', 'password83', 'role'),
(84, 'User 84', 'username84', 'password84', 'role'),
(85, 'User 85', 'username85', 'password85', 'role'),
(86, 'User 86', 'username86', 'password86', 'role'),
(87, 'User 87', 'username87', 'password87', 'role'),
(88, 'User 88', 'username88', 'password88', 'role'),
(89, 'User 89', 'username89', 'password89', 'role'),
(90, 'User 90', 'username90', 'password90', 'role'),
(91, 'User 91', 'username91', 'password91', 'role'),
(92, 'User 92', 'username92', 'password92', 'role'),
(93, 'User 93', 'username93', 'password93', 'role'),
(94, 'User 94', 'username94', 'password94', 'role'),
(95, 'User 95', 'username95', 'password95', 'role'),
(96, 'User 96', 'username96', 'password96', 'role'),
(97, 'User 97', 'username97', 'password97', 'role'),
(98, 'User 98', 'username98', 'password98', 'role'),
(99, 'User 99', 'username99', 'password99', 'role'),
(100, 'User 100', 'username100', 'password100', 'role');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `equipmentCategoryId` (`equipmentCategoryId`);

--
-- Chỉ mục cho bảng `equipment_category`
--
ALTER TABLE `equipment_category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `pay_borrow`
--
ALTER TABLE `pay_borrow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `borrowerId` (`borrowerId`);

--
-- Chỉ mục cho bảng `pay_borrow_equipment`
--
ALTER TABLE `pay_borrow_equipment`
  ADD KEY `payBorrowId` (`payBorrowId`);

--
-- Chỉ mục cho bảng `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `room_equipment`
--
ALTER TABLE `room_equipment`
  ADD KEY `roomId` (`roomId`),
  ADD KEY `equipmentId` (`equipmentId`);

--
-- Chỉ mục cho bảng `room_equipment_report`
--
ALTER TABLE `room_equipment_report`
  ADD KEY `roomReportId` (`roomReportId`),
  ADD KEY `equipmentId` (`equipmentId`);

--
-- Chỉ mục cho bảng `room_report`
--
ALTER TABLE `room_report`
  ADD PRIMARY KEY (`id`),
  ADD KEY `roomId` (`roomId`),
  ADD KEY `reporterId` (`reporterId`),
  ADD KEY `approverId` (`approverId`);

--
-- Chỉ mục cho bảng `room_schedule`
--
ALTER TABLE `room_schedule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `roomId` (`roomId`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`equipmentCategoryId`) REFERENCES `equipment_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `pay_borrow`
--
ALTER TABLE `pay_borrow`
  ADD CONSTRAINT `pay_borrow_ibfk_1` FOREIGN KEY (`borrowerId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `pay_borrow_equipment`
--
ALTER TABLE `pay_borrow_equipment`
  ADD CONSTRAINT `pay_borrow_equipment_ibfk_1` FOREIGN KEY (`payBorrowId`) REFERENCES `pay_borrow` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `room_equipment`
--
ALTER TABLE `room_equipment`
  ADD CONSTRAINT `room_equipment_ibfk_1` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `room_equipment_ibfk_2` FOREIGN KEY (`equipmentId`) REFERENCES `equipment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `room_equipment_report`
--
ALTER TABLE `room_equipment_report`
  ADD CONSTRAINT `room_equipment_report_ibfk_1` FOREIGN KEY (`roomReportId`) REFERENCES `room_report` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `room_equipment_report_ibfk_2` FOREIGN KEY (`equipmentId`) REFERENCES `equipment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `room_report`
--
ALTER TABLE `room_report`
  ADD CONSTRAINT `room_report_ibfk_1` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `room_report_ibfk_2` FOREIGN KEY (`reporterId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `room_report_ibfk_3` FOREIGN KEY (`approverId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `room_schedule`
--
ALTER TABLE `room_schedule`
  ADD CONSTRAINT `room_schedule_ibfk_1` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
