package com.bankonet.ihm;

import java.util.ArrayList;
import java.util.Scanner;

import com.bankonet.commande.jpa.CommandeFactoryJPA;
import com.bankonet.commande.jpa.MainCommandeJPA;
import com.bankonet.metier.jpa.BankonetMetierConseiller;

public class ConseillerJPA {
	public static void afficherMenu(ArrayList<CommandeFactoryJPA> commandes){
		System.out.println("**** Application Conseiller Bancaire ****");
		System.out.println("0: Arr�ter le programme");
		for (CommandeFactoryJPA commande:commandes){
			System.out.println(commande.getId() + ": " + commande.getLibelle());
		}
		System.out.print("Veuillez choisir une action: ");
	}
	
	public static void main(String[] args) {
		//variable qui permet de boucler sur le menu
		Boolean terminated = false;
		
		/*
		 *initialisation du client cote conseiller
		 *lecture des fichiers client et compte
		 */
		BankonetMetierConseiller bmc = new BankonetMetierConseiller();
		bmc.initialisation();
		
		//lancement du scanner
		Scanner sc = new Scanner(System.in);
		
		MainCommandeJPA cmd = new MainCommandeJPA(sc, bmc);
		
		do{
			String action = "-1";
			//bouclage tant que la saisie ne correspond pas aux attentes
			while (action.equals("-1")){
				afficherMenu(cmd.getCommandes());
				action = sc.next();
				if ( (!action.equals("0")) && (!action.equals("1")) && (!action.equals("2"))
						&& (!action.equals("3")) && (!action.equals("4")) && (!action.equals("5"))
						 && (!action.equals("6")) && (!action.equals("7"))){
					action = "-1";
					System.out.println("Mauvais choix!");
				}
				System.out.println("");
			}
			
			//choix de l'action en fonction de la saisie
			switch (action){
			//fermeture
			case "0":
				sc.close();
				bmc.fermeture();
				System.out.println("Arret de l'application!");
				terminated = true;
				break;
			case "1":
				cmd.getCommandes().get(0).execute();
				break;
			case "2":
				cmd.getCommandes().get(1).execute();
				break;
			case "3":
				cmd.getCommandes().get(2).execute();
				break;
			case "4":
				cmd.getCommandes().get(3).execute();
				break;
			case "5":
				cmd.getCommandes().get(4).execute();
				break;
			case "6":
				cmd.getCommandes().get(5).execute();
				break;
			case "7":
				cmd.getCommandes().get(6).execute();
				break;
			default :
				System.out.println("Not yet implemented!");
				break;
			}
			System.out.println("");
			action = "-1";
		} while (!terminated);
	}

}
