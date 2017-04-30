-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2017 at 04:03 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `preferate`
--
CREATE DATABASE IF NOT EXISTS `preferate` DEFAULT CHARACTER SET utf16 COLLATE utf16_unicode_ci;
USE `preferate`;

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `restaurant_address` int(20) NOT NULL,
  `a_street` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `a_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `a_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `a_zip_code` int(20) NOT NULL,
  `a_county` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `a_country` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `user_id1` int(20) NOT NULL,
  `user_id2` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `relation_id` int(20) NOT NULL,
  `group_id` int(20) NOT NULL,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `group_member` bigint(20) NOT NULL,
  `owner` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`relation_id`, `group_id`, `group_name`, `group_member`, `owner`) VALUES
(1, 1, 'Hello', 3, 3),
(2, 1, 'Hello', 2, 3),
(3, 1, 'Hello', 1, 3),
(6, 2, 'Pizza Team', 10208861183747429, 1454948344518241),
(7, 2, 'Pizza Team', 460286064315953, 1454948344518241),
(8, 2, 'Pizza Team', 1454948344518241, 1454948344518241),
(9, 3, 'Moes Knows', 10208861183747429, 1454948344518241),
(10, 3, 'Moes Knows', 10203219280360494, 1454948344518241),
(11, 3, 'Moes Knows', 1454948344518241, 1454948344518241);

-- --------------------------------------------------------

--
-- Table structure for table `restaurants`
--

CREATE TABLE `restaurants` (
  `restaurant_id` int(20) NOT NULL,
  `restaurant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `restaurant_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `restaurant_review` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone number` int(20) NOT NULL,
  `diet` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `website` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `avg_wait_times` int(20) NOT NULL,
  `wifi` varchar(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_reviews`
--

CREATE TABLE `restaurant_reviews` (
  `restaurant_review` int(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `food_rating` float NOT NULL,
  `menu_rating` float NOT NULL,
  `service_rating` float NOT NULL,
  `comments` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `restaurant_id` int(20) NOT NULL,
  `restaurant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Dumping data for table `restaurant_reviews`
--

INSERT INTO `restaurant_reviews` (`restaurant_review`, `user_id`, `food_rating`, `menu_rating`, `service_rating`, `comments`, `restaurant_id`, `restaurant_name`) VALUES
(1, 1, 0.4, 0.6, 0.7, ' tasty food ', 1, ' Moes'),
(2, 3, 0.3, 0.5, 0.4, 'Not a fan of this location', 6, ' Cusatos'),
(3, 3, 0.8, 0.5, 0.4, 'Good pasta', 11, 'Testos'),
(4, 3, 0.5, 0.3, 0.4, ' Okay junk food', 11, ' McDonalds'),
(5, 3, 0.8, 0.3, 0.6, ' Best pizza', 11, ' Pizza Bella'),
(6, 3, 0.6, 0.4, 0.8, 'Very fast service', 11, 'Burger King'),
(7, 3, 0.8, 0.7, 0.1, ' Very good pizza', 8, ' Pizza Hut'),
(12, 12, 0.7, 0.1, 0.1, '  Very bad!', 70, '  Applebees');

-- --------------------------------------------------------

--
-- Table structure for table `suggestions`
--

CREATE TABLE `suggestions` (
  `suggestion_id` int(20) NOT NULL,
  `sender_id` int(20) NOT NULL,
  `receiver_id` int(20) NOT NULL,
  `restaurant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `diet_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_allergy` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gluten` varchar(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `kosher` varchar(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `lactose` varchar(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `meats` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `eating_environment` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `diet_type`, `user_allergy`, `gluten`, `kosher`, `lactose`, `meats`, `eating_environment`) VALUES
(33, '\0B\0r\0a\0n', '\0t\0e\0s\0t\02', '\0t\0e\0s\0t', '\0n', '\0n', '\0n', '\0t\0e\0s\0t\02', '\0t\0e\0s\0t'),
(1454948344518241, 'Brandon Thorne', 'Non-Vegetarian', 'None', 'No', 'No', 'Yes', 'None', 'Silent'),
(10208861183747429, 'Ryan Sherman', 'Non-Vegetarian', 'None', 'No', 'NA', 'Yes', 'None', 'Lively');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`restaurant_address`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD KEY `user_id1` (`user_id1`),
  ADD KEY `user_id2` (`user_id2`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`relation_id`);

--
-- Indexes for table `restaurants`
--
ALTER TABLE `restaurants`
  ADD PRIMARY KEY (`restaurant_id`);

--
-- Indexes for table `restaurant_reviews`
--
ALTER TABLE `restaurant_reviews`
  ADD PRIMARY KEY (`restaurant_review`);

--
-- Indexes for table `suggestions`
--
ALTER TABLE `suggestions`
  ADD PRIMARY KEY (`suggestion_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `relation_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `restaurant_reviews`
--
ALTER TABLE `restaurant_reviews`
  MODIFY `restaurant_review` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
