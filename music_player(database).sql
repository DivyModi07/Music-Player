-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 24, 2024 at 06:29 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `music_player`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `DisplayOnlinePlaylistSong` ()   BEGIN
     SELECT SongName FROM songdetails;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DisplaySongDetails` (IN `SongID_in` VARCHAR(10), IN `Song_path_in` VARCHAR(30))   BEGIN
    SELECT SongName,SingerName,Duration from songdetails where SongID=SongID_in
    AND SongPath=Song_Path_in;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DisplayUserPlaylistSong` (IN `UserId_in` VARCHAR(10))   BEGIN
    SELECT SongName FROM playlistdetails WHERE UserID=UserId_in ;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `playlistdetails`
--

CREATE TABLE `playlistdetails` (
  `UserID` varchar(6) NOT NULL,
  `SongName` varchar(20) NOT NULL,
  `SongPath` varchar(26) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `playlistdetails`
--

INSERT INTO `playlistdetails` (`UserID`, `SongName`, `SongPath`) VALUES
('Kavi15', 'Khalasi', 'D://Music/Khalasi.wav'),
('Bhav08', 'Woh Din', 'D://Music/Woh Din.wav'),
('a-b-12', 'WOH DIN', 'D://Music/WOH DIN.wav'),
('loki03', 'DEVA DEVA', 'D://Music/DEVA DEVA.wav'),
('loki03', 'RAABTA', 'D://Music/RAABTA.wav'),
('kavi09', 'DEVA DEVA', 'D://Music/DEVA DEVA.wav'),
('kavi09', 'KHALASI', 'D://Music/KHALASI.wav'),
('Divy12', 'DEVA DEVA', 'D://Music/DEVA DEVA.wav'),
('Divy12', 'RAABTA', 'D://Music/RAABTA.wav'),
('Divy12', 'ILHALI', 'D://Music/ILHALI.wav'),
('Divy20', 'DEVA DEVA', 'D://Music/DEVA DEVA.wav'),
('Divy20', 'ILHALI', 'D://Music/ILHALI.wav'),
('Divy24', 'DEVA DEVA', 'D://Music/DEVA DEVA.wav'),
('Divy24', 'ILHALI', 'D://Music/ILHALI.wav'),
('Divy24', 'NAMO NAMO', 'D://Music/NAMO NAMO.wav'),
('Divy19', 'DEVA DEVA', 'D://Music/DEVA DEVA.wav'),
('Divy19', 'KUN FAYA KUN', 'D://Music/KUN FAYA KUN.wav'),
('Divy19', 'ILHALI', 'D://Music/ILHALI.wav'),
('Divy19', 'HAWAYEIN', 'D://Music/HAWAYEIN.wav');

-- --------------------------------------------------------

--
-- Table structure for table `songdetails`
--

CREATE TABLE `songdetails` (
  `SongID` varchar(7) NOT NULL,
  `SongName` varchar(16) NOT NULL,
  `SingerName` varchar(20) NOT NULL,
  `SongPath` varchar(50) NOT NULL,
  `Duration` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `songdetails`
--

INSERT INTO `songdetails` (`SongID`, `SongName`, `SingerName`, `SongPath`, `Duration`) VALUES
('S01', 'DEVA DEVA', 'Arijit Singh', 'D://Music/DEVA DEVA.wav', '3:11'),
('S02', 'MATARGASHTI', 'Mohit Chauhan', 'D://Music/MATARGASHTI.wav', '4:25'),
('S03', 'NADAAN PARINDE', 'A R Rahman', 'D://Music/NADAAN PARINDE.wav', '6:42'),
('S04', 'WOH DIN', 'Arijit Singh', 'D://Music/WOH DIN.wav', '5:17'),
('S05', 'NAMO NAMO', 'Amit Trivedi', 'D://Music/NAMO NAMO.wav', '5:28'),
('S06', 'ILHALI', 'Arijit Singh', 'D://Music/ILHALI.wav', '3:23'),
('S07', 'RAABTA', 'Arijit Singh', 'D://Music/RAABTA.wav', '3:34'),
('S08', 'KAISE HUA', 'Vishal Mishra', 'D://Music/KAISE HUA.wav', '4:14'),
('S09', 'KHALASI', 'Aditya Gadhvi', 'D://Music/KHALASI.wav', '4:24'),
('S10', 'SUBHANALLAH', 'Sreema Chandra', 'D://Music/SUBHANALLAH.wav', '2:47'),
('S11', 'SAJNI RE', 'Arijit Singh', 'D://Music/SAJNI RE.wav', '3:10'),
('S12', 'NAINA KYA KASUR', 'Ayushman Khurrana', 'D://Music/NAINA KYA KASUR.wav', '3:38'),
('S13', 'KESARIYA', 'Arijit Singh', 'D://Music/KESARIYA.wav', '2:52'),
('S14', 'HAWAYEIN', 'Arijit Singh', 'D://Music/HAWAYEIN.wav', '4:51'),
('S15', 'KUN FAYA KUN', 'A R Rahman', 'D://Music/KUN FAYA KUN.wav', '6:21');

-- --------------------------------------------------------

--
-- Table structure for table `user1`
--

CREATE TABLE `user1` (
  `UserID` varchar(7) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `MobileNo` varchar(15) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user1`
--

INSERT INTO `user1` (`UserID`, `Name`, `MobileNo`, `DateOfBirth`, `Password`) VALUES
('Bhav08', 'Bhavin B Patel', '+91 9898989900', '2005-09-08', '1111'),
('Divy19', 'Divy M Modi', '+91 9898909090', '2007-08-19', '1222'),
('Divy24', 'Divy M modi', '+91 9090909090', '2005-03-24', '1234'),
('Kavi15', 'Kavish S Patel', '+91 9090989890', '2005-03-15', '1234'),
('loki03', 'loki', '+91 7984502575', '2006-05-03', '7475'),
('Prac18', 'Prachi B Agja', '+91 9090909090', '2004-09-18', '1212');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `songdetails`
--
ALTER TABLE `songdetails`
  ADD PRIMARY KEY (`SongID`);

--
-- Indexes for table `user1`
--
ALTER TABLE `user1`
  ADD PRIMARY KEY (`UserID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
