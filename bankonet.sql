-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 20 Octobre 2015 à 14:30
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

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`Nom`, `Prenom`, `Identifiant`, `Login`, `MDP`) VALUES
('Kirigaya', 'Kazuto', 'KirigayaKazuto', 'kirito', 'Secret'),
('Yuki', 'Asuna', 'YukiAsuna', 'asuna', 'Secret');

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

--
-- Contenu de la table `clientcompte`
--

INSERT INTO `clientcompte` (`IdentifiantClient`, `NumeroCompte`) VALUES
('KirigayaKazuto', 'cckk01'),
('YukiAsuna', 'ccya01'),
('YukiAsuna', 'ccya02'),
('KirigayaKazuto', 'cekk01'),
('YukiAsuna', 'ceya01');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `Numero` varchar(20) NOT NULL,
  `Intitule` varchar(50) NOT NULL,
  `Solde` double NOT NULL,
  `DecouvertMaximum` double NOT NULL,
  `TauxInteret` double NOT NULL,
  PRIMARY KEY (`Numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`Numero`, `Intitule`, `Solde`, `DecouvertMaximum`, `TauxInteret`) VALUES
('cckk01', 'cckk01', 100, 10, 0),
('ccya01', 'ccya01', 400, 40, 0),
('ccya02', 'ccya02', 1600, 160, 0),
('cekk01', 'cekk01', 200, 0, 2),
('ceya01', 'ceya01', 800, 0, 8);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `clientcompte`
--
ALTER TABLE `clientcompte`
  ADD CONSTRAINT `fk_Client_Identifiant` FOREIGN KEY (`IdentifiantClient`) REFERENCES `client` (`Identifiant`),
  ADD CONSTRAINT `fk_Compte_Numero` FOREIGN KEY (`NumeroCompte`) REFERENCES `compte` (`Numero`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
