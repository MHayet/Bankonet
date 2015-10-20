package Commande;

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
		
		Collections.sort(commandes, new Comparator<CommandeFactoryJPA>() {
			@Override
			public int compare(CommandeFactoryJPA cmd1, CommandeFactoryJPA cmd2){
				return cmd1.getId().compareTo(cmd2.getId());
			}
		});
	}

}
