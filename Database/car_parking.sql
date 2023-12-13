-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 22, 2021 at 10:34 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `car_parking`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_login`
--

CREATE TABLE `admin_login` (
  `id` int(11) NOT NULL,
  `username` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_login`
--

INSERT INTO `admin_login` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `all_booking`
--

CREATE TABLE `all_booking` (
  `id` int(11) NOT NULL,
  `vehicle_no` varchar(256) NOT NULL,
  `plot_name` varchar(256) NOT NULL,
  `slot_name` varchar(256) NOT NULL,
  `date_of_booking` varchar(256) NOT NULL,
  `end_date_of_booking` datetime NOT NULL DEFAULT current_timestamp(),
  `time_of_booking` varchar(256) NOT NULL,
  `end_time_of_booking` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `all_booking`
--

INSERT INTO `all_booking` (`id`, `vehicle_no`, `plot_name`, `slot_name`, `date_of_booking`, `end_date_of_booking`, `time_of_booking`, `end_time_of_booking`) VALUES
(1, 'MH27 CC4569', 'Plot 2', 'Slot 2', '12/04/2020', '2020-04-11 15:51:29', '02:30', '2020-04-11 10:21:29'),
(3, 'MH27 AC1245', 'Plot 1', 'Slot 8', '11/04/2020', '2020-04-11 15:55:43', '3:30', '2020-04-11 10:25:43'),
(4, 'MH27 BC4629', 'Plot 2', 'Slot 2', '11/4/2020', '2020-04-11 15:58:23', '3:30', '2020-04-11 10:28:23'),
(5, 'MH27 CA1111', 'Plot 2', 'Slot 1', '11/04/2020', '2020-04-11 15:59:40', '04:00', '2020-04-11 10:29:40'),
(6, 'MH27 CB0089', 'Plot 2', 'Slot 2', '11/4/2020', '2020-04-11 18:08:22', '18:15', '2020-04-11 12:38:22');

-- --------------------------------------------------------

--
-- Table structure for table `car_slot`
--

CREATE TABLE `car_slot` (
  `id` int(11) NOT NULL,
  `plot_name` varchar(256) NOT NULL,
  `slot_name` varchar(256) NOT NULL,
  `number_of_slot` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `car_slot`
--

INSERT INTO `car_slot` (`id`, `plot_name`, `slot_name`, `number_of_slot`) VALUES
(1, 'Plot A', 'Slot 1', '8'),
(2, 'Plot 1', 'Plot 1', '8'),
(3, 'P-2', 'P-2', '8');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `vehicle_no` varchar(256) NOT NULL,
  `mobile_no` varchar(256) NOT NULL,
  `user_name` varchar(256) NOT NULL,
  `feedback` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `vehicle_no`, `mobile_no`, `user_name`, `feedback`) VALUES
(1, 'MH27 CB0089', '8208187488', 'Nikhil Shende', 'Very Good App to save time for car Parking......'),
(6, 'MH27 CB0089', '8208187488', '', 'Very Nice App'),
(7, 'MH27 CB0089', '8208187488', '', 'good'),
(8, 'MH27 CB0089', '8208187488', '', 'This app is very good for secure parking'),
(9, 'MH27 CC3489', '7346291686', '', 'This app is help to save my time');

-- --------------------------------------------------------

--
-- Table structure for table `parking_booking`
--

CREATE TABLE `parking_booking` (
  `id` int(11) NOT NULL,
  `book_vehicle_no` varchar(256) NOT NULL,
  `plot_name` varchar(256) NOT NULL,
  `slot_name` varchar(256) NOT NULL,
  `booked` varchar(256) NOT NULL,
  `date_of_booking` varchar(256) NOT NULL,
  `time_of_booking` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parking_booking`
--

INSERT INTO `parking_booking` (`id`, `book_vehicle_no`, `plot_name`, `slot_name`, `booked`, `date_of_booking`, `time_of_booking`) VALUES
(29, 'MH27 CB0089', 'Plot 1', 'Slot 1', '1', '11/4/2020', '11:30'),
(34, 'MH27 CC2546', 'Plot 1', 'Slot 3', '1', '11/04/2020', '05:50');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `vehicle_no` varchar(256) NOT NULL,
  `name` varchar(256) NOT NULL,
  `mobile_no` varchar(256) NOT NULL,
  `email_id` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `vehicle_no`, `name`, `mobile_no`, `email_id`, `password`) VALUES
(1, 'MH27 CB0089', 'Nikhil Shende', '8208187488', 'nshende12@gmail.com', '123456'),
(4, 'MH27 CA6789', 'Amit Kapse', '7276366717', 'amit@gmail.com', 'amit@12'),
(5, 'MH27 CC3489', 'Amit Kapse', '7346291686', 'amit@gmail.com', 'asdasd');

-- --------------------------------------------------------

--
-- Table structure for table `watchmen`
--

CREATE TABLE `watchmen` (
  `id` int(11) NOT NULL,
  `watchmen_id` varchar(256) NOT NULL,
  `watchmen_name` varchar(256) NOT NULL,
  `watchmen_mobile_no` varchar(256) NOT NULL,
  `watchmen_password` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `watchmen`
--

INSERT INTO `watchmen` (`id`, `watchmen_id`, `watchmen_name`, `watchmen_mobile_no`, `watchmen_password`) VALUES
(1, '3001', 'aaaaaa', '9876543210', '123456789'),
(2, '3002', 'Nikhil Shende', '8208187488', 'asdasd'),
(3, '', '', '', ''),
(4, '3003', 'Vilash Agrawal', '8953466143', '123123'),
(5, '3004', 'Asdasd', '7878465646', '123asd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_login`
--
ALTER TABLE `admin_login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `all_booking`
--
ALTER TABLE `all_booking`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `car_slot`
--
ALTER TABLE `car_slot`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `parking_booking`
--
ALTER TABLE `parking_booking`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `watchmen`
--
ALTER TABLE `watchmen`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_login`
--
ALTER TABLE `admin_login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `all_booking`
--
ALTER TABLE `all_booking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `car_slot`
--
ALTER TABLE `car_slot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `parking_booking`
--
ALTER TABLE `parking_booking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `watchmen`
--
ALTER TABLE `watchmen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
