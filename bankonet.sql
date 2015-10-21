-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 21 Octobre 2015 à 14:56
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
-- Structure de la table `comptecourant`
--

CREATE TABLE IF NOT EXISTS `comptecourant` (
  `Numero` varchar(20) NOT NULL,
  `Intitule` varchar(50) NOT NULL,
  `Solde` double NOT NULL,
  `DecouvertMaximum` double NOT NULL,
  PRIMARY KEY (`Numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `compteepargne`
--

CREATE TABLE IF NOT EXISTS `compteepargne` (
  `Numero` varchar(20) NOT NULL,
  `Intitule` varchar(50) NOT NULL,
  `Solde` double NOT NULL,
  `TauxInteret` double NOT NULL,
  PRIMARY KEY (`Numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `clientcourant`
--

CREATE TABLE IF NOT EXISTS `clientcourant` (
  `IdentifiantClient` varchar(75) NOT NULL,
  `NumeroCompte` varchar(20) NOT NULL,
  PRIMARY KEY (`IdentifiantClient`,`NumeroCompte`),
  CONSTRAINT `fk_cc_compteCourant_numero` FOREIGN KEY (`NumeroCompte`) REFERENCES `comptecourant` (`Numero`),
  CONSTRAINT `fk_cc_client_identifiant` FOREIGN KEY (`IdentifiantClient`) REFERENCES `client` (`Identifiant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `clientepargne`
--

CREATE TABLE IF NOT EXISTS `clientepargne` (
  `IdentifiantClient` varchar(75) NOT NULL,
  `NumeroCompte` varchar(20) NOT NULL,
  PRIMARY KEY (`IdentifiantClient`,`NumeroCompte`),
  CONSTRAINT `fk_ce_compteEpargne_numero` FOREIGN KEY (`NumeroCompte`) REFERENCES `compteepargne` (`Numero`),
  CONSTRAINT `fk_ce_client_identifiant` FOREIGN KEY (`IdentifiantClient`) REFERENCES `client` (`Identifiant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
