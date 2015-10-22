package com.bankonet.commande;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import com.bankonet.metier.BankonetMetierConseiller;

public class MainCommande {
	private ArrayList<CommandeFactory> commandes  = new ArrayList<>();

	public MainCommande(Scanner sc, BankonetMetierConseiller bmc) {
		commandes.add(new CommandeCreerCompteEpargne(3, "Ouvrir un compte epargne", sc, bmc));
		commandes.add(new CommandeCreerCompte(1, "Ouvrir un compte client", sc, bmc));
		commandes.add(new CommandeAfficherListeClient(4, "Lister tout les clients", bmc));
		commandes.add(new CommandeCreerCompteCourant(2, "Ouvrir un compte courant", sc, bmc));
		
		Collections.sort(commandes, new Comparator<CommandeFactory>() {
			@Override
			public int compare(CommandeFactory cmd1, CommandeFactory cmd2){
				return cmd1.getId().compareTo(cmd2.getId());
			}
		});
	}

	public ArrayList<CommandeFactory> getCommandes() {
		return commandes;
	}

	public void setCommandes(ArrayList<CommandeFactory> commandes) {
		this.commandes = commandes;
	}

}
