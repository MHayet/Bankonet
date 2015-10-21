package com.bankonet.commande.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import com.bankonet.metier.jpa.BankonetMetierConseiller;

public class MainCommandeJPA {
	private ArrayList<CommandeFactoryJPA> commandes  = new ArrayList<>();

	public ArrayList<CommandeFactoryJPA> getCommandes() {
		return commandes;
	}

	public void setCommandes(ArrayList<CommandeFactoryJPA> commandes) {
		this.commandes = commandes;
	}

	public MainCommandeJPA(Scanner sc, BankonetMetierConseiller bmc) {
		commandes.add(new CommandeInit(1, "Initialiser", sc, bmc));
		commandes.add(new CommandeAfficherListeClientJPA(7, "Afficher la liste des clients", sc, bmc));
		commandes.add(new CommandeChercherParNom(2, "Rechercher un client par son nom", sc, bmc));
		commandes.add(new CommandeChercherParPrenom(3, "Rechercher un client par son prenom", sc, bmc));
		commandes.add(new CommandeModifierNomClient(4, "Modifier le nom d'un client", sc, bmc));
		commandes.add(new CommandeSupprimerClient(5, "Supprimer un client", sc, bmc));
		commandes.add(new CommandeSupprimerToutClients(6, "Supprimer tout les clients", sc, bmc));
		
		Collections.sort(commandes, new Comparator<CommandeFactoryJPA>() {
			@Override
			public int compare(CommandeFactoryJPA cmd1, CommandeFactoryJPA cmd2){
				return cmd1.getId().compareTo(cmd2.getId());
			}
		});
	}

}
