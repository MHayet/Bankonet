-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 19 Octobre 2015 à 15:08
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `bankonet`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(25) NOT NULL,
  `Identifiant` varchar(75) NOT NULL,
  `Login` varchar(20) NOT NULL,
  `MDP` varchar(20) NOT NULL,
  PRIMARY KEY (`Identifiant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `clientcompte`
--

CREATE TABLE IF NOT EXISTS `clientcompte` (
  `IdentifiantClient` varchar(75) NOT NULL,
  `NumeroCompte` varchar(20) NOT NULL,
  PRIMARY KEY (`IdentifiantClient`,`NumeroCompte`),
  KEY `fk_Compte_Numero` (`NumeroCompte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `Numero` varchar(20) NOT NULL,
  `Intitule` varchar(50) NOT NULL,
  `Solde` float NOT NULL,
  `DecouvertMaximum` float NOT NULL,
  `TauxInteret` float NOT NULL,
  PRIMARY KEY (`Numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `clientcompte`
--
ALTER TABLE `clientcompte`
  ADD CONSTRAINT `fk_Compte_Numero` FOREIGN KEY (`NumeroCompte`) REFERENCES `compte` (`Numero`),
  ADD CONSTRAINT `fk_Client_Identifiant` FOREIGN KEY (`IdentifiantClient`) REFERENCES `client` (`Identifiant`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
