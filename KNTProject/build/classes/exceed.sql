-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 24, 2012 at 08:00 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `exceed`
--
CREATE DATABASE `exceed` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `exceed`;

-- --------------------------------------------------------

--
-- Table structure for table `maxvote`
--

CREATE TABLE IF NOT EXISTS `maxvote` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `tyid` int(11) NOT NULL,
  `maximum` int(11) NOT NULL,
  PRIMARY KEY (`mid`),
  KEY `pid` (`pid`,`tyid`,`maximum`),
  KEY `maximum` (`maximum`),
  KEY `tyid` (`tyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `poll`
--

CREATE TABLE IF NOT EXISTS `poll` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` text NOT NULL,
  `image` varchar(40) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `poll`
--

INSERT INTO `poll` (`pid`, `name`, `description`, `image`) VALUES
(1, 'Best design on a project?', 'Which project has the best design', 'design.jpg'),
(2, 'Best Algorithm on a project', 'Best Algorithm used in a project', 'teamwork.jpg'),
(3, 'Best Layout?', 'Layout what is nice', 'Matrix.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `pollchoice`
--

CREATE TABLE IF NOT EXISTS `pollchoice` (
  `pcid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  PRIMARY KEY (`pcid`),
  KEY `pid` (`pid`),
  KEY `tid` (`tid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `pollchoice`
--

INSERT INTO `pollchoice` (`pcid`, `pid`, `tid`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 1),
(4, 3, 2),
(5, 2, 2),
(6, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE IF NOT EXISTS `teams` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`tid`, `name`) VALUES
(1, 'team1'),
(2, 'team2');

-- --------------------------------------------------------

--
-- Table structure for table `types`
--

CREATE TABLE IF NOT EXISTS `types` (
  `tyip` int(11) NOT NULL AUTO_INCREMENT,
  `typ` varchar(50) NOT NULL,
  PRIMARY KEY (`tyip`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `types`
--

INSERT INTO `types` (`tyip`, `typ`) VALUES
(1, 'student');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `uid` varchar(20) NOT NULL,
  `password` varchar(103) NOT NULL,
  `tyid` int(11) NOT NULL,
  `firstlogin` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `kid` (`uid`),
  KEY `tyid` (`tyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `password`, `tyid`, `firstlogin`) VALUES
('123', '1000:ebc5bbb7400574658869a5bd3524aabf52441cc034d36cc8:a599535a1e2aaaff535928d58d7751c77d477a6710b2f378', 1, 0),
('aaa', '1000:6b33146fb817125d0036caca93e8e9bdaa33509526ea78d0:f99c52f830fa916b2df97dfb103fd2e655958c9b6953a4cd', 1, 1),
('b5510040327', '1000:1f91a20af4d04f788400dccba83158e5b632fb8d2e241508:e1a6bb8811525ecc18755444b904354deb7a8624397da2b1', 1, 1),
('harry', '1000:e70a5f1919fce09a69167f923116f44c91c388770c21cf65:11907fd8d7012b07d257eb105dfdbc0a5220709ee1ba0c20', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `votes`
--

CREATE TABLE IF NOT EXISTS `votes` (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `uid` varchar(20) NOT NULL,
  `tid` int(11) NOT NULL,
  `votes` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`vid`),
  KEY `pcid` (`pid`,`uid`),
  KEY `uid` (`uid`),
  KEY `tid` (`tid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `votes`
--

INSERT INTO `votes` (`vid`, `pid`, `uid`, `tid`, `votes`) VALUES
(17, 1, 'harry', 1, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `maxvote`
--
ALTER TABLE `maxvote`
  ADD CONSTRAINT `maxvote_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `poll` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `maxvote_ibfk_2` FOREIGN KEY (`tyid`) REFERENCES `types` (`tyip`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pollchoice`
--
ALTER TABLE `pollchoice`
  ADD CONSTRAINT `pollchoice_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `poll` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pollchoice_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `teams` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`tyid`) REFERENCES `types` (`tyip`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `votes`
--
ALTER TABLE `votes`
  ADD CONSTRAINT `votes_ibfk_10` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `votes_ibfk_11` FOREIGN KEY (`tid`) REFERENCES `teams` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `votes_ibfk_9` FOREIGN KEY (`pid`) REFERENCES `poll` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
