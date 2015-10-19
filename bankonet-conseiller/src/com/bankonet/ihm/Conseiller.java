package com.bankonet.ihm;

import com.bankonet.lib.*;
import com.bankonet.metier.*;

import java.util.Iterator;
import java.util.Scanner;

public class Conseiller {	
	//*affichage du menu principale
	public static void afficherMenu(){
		System.out.println("**** Application Conseiller Bancaire ****");
		System.out.println("0: Arréter le programme");
		System.out.println("1: Ouvrir un compte client");
		System.out.println("2: Ouvrir un compte courant");
		System.out.println("3: Ouvrir un compte épargne");
		System.out.println("4: Lister tout les clients");
		System.out.print("Veuillez choisir une action: ");
	}
	
	//*affichage du menu de creation d'un compte client
	public static void creerCompte(Scanner cc, BankonetMetierConseiller bmc){
		
		System.out.println("**** Creation d'un compte ****");
		System.out.println("Veuillez saisir le nom: ");
		String nom = cc.next();
		System.out.println("Veuillez saisir le prénom: ");
		String prenom = cc.next();
		System.out.println("Veuillez saisir le login: ");
		String login = cc.next();
		
		//ajout du nouveau client dans le programme
		bmc.creerCompte(nom, prenom, nom+prenom, login);
	}
	
	//*affichage du menu de creation d'un compte courant
	public static void creerCompteCourant(Scanner ccc, BankonetMetierConseiller bmc){
		boolean existe = false;
		
		System.out.println("**** Creation d'un compte courant****");
		//*saisie de l'id du client pour vérifier son existance
		System.out.println("Identifiant du client: ");
		String idc = ccc.next();
		System.out.println("Veuillez saisir le numéro: ");
		String num = ccc.next();
		System.out.println("Veuillez saisir l'intitulé: ");
		String intit = ccc.next();
		System.out.println("Veuillez saisir le solde: ");
		Double solde = ccc.nextDouble();
		System.out.println("Veuillez saisir le découvert maximum autorisé: ");
		Double dma = ccc.nextDouble();
		
		existe = bmc.creerCompteCourant(idc, num, intit, solde, dma);
		//*sinon on demande la verification de l'id, ou la creation du compte client
		if (!existe){
			System.out.println("Client non trouvé!");
			System.out.println("Veuillez vérifiez l'identifiant saisie");
			System.out.println("Ou veuillez créer le compte client");
		}
		System.out.println("");
		
	}
	
	//*affichage du menu de creation d'un compte epargne
	public static void creerCompteEpargne(Scanner ccc, BankonetMetierConseiller bmc){
		boolean existe = false;
		
		System.out.println("**** Creation d'un compte epargne****");
		//*saisie de l'id du client pour verifier son existance
		System.out.println("Identifiant du client: ");
		String idc = ccc.next();
		System.out.println("Veuillez saisir le numéro: ");
		String num = ccc.next();
		System.out.println("Veuillez saisir l'intitulé: ");
		String intit = ccc.next();
		System.out.println("Veuillez saisir le solde: ");
		Double solde = ccc.nextDouble();
		System.out.println("Veuillez saisir le taux d'intéret: ");
		Double ta = ccc.nextDouble();
		
		existe = bmc.creerCompteEpargne(idc, num, intit, solde, ta);
		//*sinon on demande la verification de l'id, ou la creation du compte client
		if (!existe){
			System.out.println("Client non trouvé!");
			System.out.println("Veuillez vérifiez l'identifiant saisie");
			System.out.println("Ou veuillez créer le compte client");
		}
		System.out.println("");
		
	}
	
	//*affichage de la liste des clients
	public static void afficherListeClients(BankonetMetierConseiller bmc){
		Iterator<Client> it = bmc.getListeClients().iterator();
		System.out.println("**** Liste de clients ****");
		while (it.hasNext()){
			System.out.println(it.next());
		}
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
		
		do{
			//lancement du scanner
			Scanner sc = new Scanner(System.in);
			String action = "-1";
			//bouclage tant que la saisie ne correspond pas aux attentes
			while (action.equals("-1")){
				afficherMenu();
				action = sc.next();
				if ( (!action.equals("0")) && (!action.equals("1")) && (!action.equals("2"))
						&& (!action.equals("3")) && (!action.equals("4")) ){
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
			//creation d'un compte client
			case "1":
				creerCompte(sc, bmc);
				System.out.println("Compte créé: mdp par defaut: 'secret'");
				break;
			//creation d'un compte courant, a associer a un client
			case "2":
				creerCompteCourant(sc, bmc);
				break;
			//creation d'un compte epargne a associer a un client
			case "3":
				creerCompteEpargne(sc, bmc);
				break;
			//affichage de la liste des clients
			case "4":
				afficherListeClients(bmc);
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
