-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 04, 2012 at 05:46 AM
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
  `deadline` date NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `poll`
--

INSERT INTO `poll` (`pid`, `name`, `description`, `deadline`) VALUES
(1, 'knt.exceedvote.com', 'dwasd', '2012-10-03'),
(2, 'test2', 'tessssst', '2012-10-05');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `pollchoice`
--

INSERT INTO `pollchoice` (`pcid`, `pid`, `tid`) VALUES
(1, 1, 1),
(2, 1, 2);

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
  PRIMARY KEY (`uid`),
  UNIQUE KEY `kid` (`uid`),
  KEY `tyid` (`tyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `password`, `tyid`) VALUES
('123', '1000:aba667a93ff3d6621d5d761c4d4179e218b16fec671061bb:89c51cff2ae8337ac4d3dfd039ce61b35568d404e77db68f', 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `votes`
--

INSERT INTO `votes` (`vid`, `pid`, `uid`, `tid`, `votes`) VALUES
(1, 1, '123', 1, 1),
(2, 1, '123', 2, 1),
(3, 1, '123', 1, 1),
(4, 1, '123', 2, 1),
(5, 1, '123', 1, 1);

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
  ADD CONSTRAINT `votes_ibfk_3` FOREIGN KEY (`pid`) REFERENCES `poll` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `votes_ibfk_4` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `votes_ibfk_5` FOREIGN KEY (`tid`) REFERENCES `teams` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
