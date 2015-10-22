package com.bankonet.ihm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bankonet.lib.*;

public class Utilisateur {
	//*Liste des clients
	private static List<Client> clients = new ArrayList<Client>();
	
	//*lecture des comptes lors de l'initialisation
	public static void lireComptes(Client client, String[] listCC, String[] listCE){
		//fichier de stockage
		String compteFilePath = "E:\\Formation Java-JEE\\Java\\TP\\TP2\\compte.properties";
		
		try{
			//ouverture du reader
			BufferedReader buff = new BufferedReader(new FileReader(compteFilePath));
			
			String line;
			
			//tant qu'on n'est pas au bout du fichier
			while ((line = buff.readLine()) != null) {
				//split multiple pour recuperer les bons elements
				String[] numero = line.split("=");
				
				String[] data = numero[1].split("&");
				
				String[] intitule = data[0].split(":");
				String[] solde = data[1].split(":");
				String[] param = data[2].split(":");
				
				//parcours des numeros de comptes courants passes en parametres
				if (listCC != null){
					for (String cc:listCC){
						//si sa correspond, on le stock
						if (cc.equals(numero[0])){
							client.creerCompte(new CompteCourant(numero[0],intitule[1],Double.parseDouble(solde[1]),Double.parseDouble(param[1])));
						}
					}
				}
				//parcours des numeros de comptes epargnes passes en parametres
				if(listCE != null){
					for (String ce:listCE){
						//si sa correspond, on le stock
						if (ce.equals(numero[0])){
							client.creerCompte(new CompteEpargne(numero[0],intitule[1],Double.parseDouble(solde[1]),Double.parseDouble(param[1])));
						}
					}
				}
			}
			//fermeture du reader
			buff.close();
			
		}catch (Exception e){
			System.out.println("Fichier compte introuvable!");
		}
		
	}
	
	//*lecture des clients lors de l'initialisation
	public static void initialisation(){
		//fichier de stockage
		String clientFilePath = "E:\\Formation Java-JEE\\Java\\TP\\TP2\\client.properties";
		
		try{
			//ouverture du reader
			BufferedReader buff = new BufferedReader(new FileReader(clientFilePath));
			
			String line;
			
			//tant qu'on n'est pas a la fin du fichier 
			while ((line = buff.readLine()) != null) {
				//split multiples pour recuperer les bons elements
				String[] id = line.split("=");
				
				String[] data = id[1].split("&");
				
				String[] nom = data[0].split(":");
				String[] prenom = data[1].split(":");
				String[] login = data[2].split(":");
				String[] mdp = data[3].split(":");
				String[] cc = data[4].split(":");
				String[] ce = data[5].split(":");
				
				String[] listCC = null;
				String[] listCE = null;
				if (cc.length > 1){
					listCC = cc[1].split(",");	
				}
				if (ce.length > 1){
					listCE = ce[1].split(",");
				}
				
				Client client = new Client(nom[1],prenom[1],id[0],login[1],mdp[1]);
				
				//*appel a la lecture des comptes
				lireComptes(client, listCC, listCE);

				//ajout du client dans le programme			
				clients.add(client);
			}
			//fermeture du reader
			buff.close();
			
		}catch (Exception e){
			System.out.println("Fichier client introuvable!");
		}
	}
	
	/**
	 * fermeture du programme
	 * enregistrement des clients et comptes
	 */
	public static void fermeture(){
		//fichiers de stockages
		String clientFilePath = "E:\\Formation Java-JEE\\Java\\TP\\TP2\\client.properties";
		String compteFilePath = "E:\\Formation Java-JEE\\Java\\TP\\TP2\\compte.properties";
		
		try{
			//ouverture des writers
			BufferedWriter buffcl = new BufferedWriter(new FileWriter(clientFilePath));
			BufferedWriter buffco = new BufferedWriter(new FileWriter(compteFilePath));
			
			for (Client client:clients){
				List<String> listCC = new ArrayList<>();
				List<String> listCE = new ArrayList<>();
				String strcl = client.getIdentifiant()+"=nom:"+client.getNom()+"&prenom:"+client.getPrenom()+"&login:"+client.getLogin()+"&mdp:"+client.getMdp();
				
				for (Compte compte:client.getComptesList()){
					String strco = compte.getNumero()+"=intitule:"+compte.getIntitule()+"&solde:"+compte.getSolde().toString()+"&param:";
					if(compte.getClass().equals(CompteCourant.class)){
						listCC.add(compte.getNumero());
						CompteCourant cpt = (CompteCourant)compte;
						strco += cpt.getMontantDecouvertAutorise();
					}else{
						listCE.add(compte.getNumero());
						CompteEpargne cpt = (CompteEpargne)compte;
						strco += cpt.getTauxInteret();
					}
					buffco.write(strco+"\n");
				}
				strcl += "&comptescourants:";
				for (String str:listCC){
					strcl += str + ",";
				}
				if(listCC.size() != 0){
					strcl = strcl.substring(0, strcl.length()-1);
				}
				strcl += "&comptesepargnes:";
				for (String str:listCE){
					strcl += str + ",";
				}
				if(listCE.size() != 0){
					strcl = strcl.substring(0, strcl.length()-1);
				}
				
				buffcl.write(strcl+"\n");
			}
			//fermeture des writers
			buffcl.close();
			buffco.close();			
		}catch (Exception e){
			System.out.println("erreur de sauvegarde");
		}
		
	};
	
	public static Client connection(Scanner cc){
		Client cli = null;
		do{
			System.out.println("**** Connection client ****");
			System.out.print("Login: ");
			String login = cc.next();
			System.out.print("Mot de passe: ");
			String mdp = cc.next();
			for (Client client:clients){
				if ( (client.getLogin().equals(login)) && (client.getMdp().equals(mdp)) ){
					cli = client;
				}
			}
			System.out.println("");
		}while(cli == null);
		return cli;
	}
	
	//*affichage du menu principale
	public static void afficherMenu(){
		System.out.println("**** Application Client ****");
		System.out.println("0: Arréter le programme");
		System.out.println("1: Consulter les soldes des comptes");
		System.out.println("2: Effectuer un dépot");
		System.out.println("3: Effectuer un retrait");
		System.out.println("4: Effectuer un virement entre compte");
		System.out.print("Veuillez choisir une action: ");
	}
	
	//*affichage du solde des comptes
	public static void consulterSoldesComptes(Client client){
		System.out.println("**** Affichage du solde des comptes ****");
		for (Compte compte:client.getComptesList()){
			System.out.println(compte);
		}
		System.out.println("");
	}
	
	//*depot sur un compte courant
	public static void crediterCompteCourant(Scanner sc, Client client){
		CompteCourant cc = null;
		
		do{
			Integer nbCompte = 0;
			System.out.println("**** Affichage des comptes courants****");
			for (Compte compte:client.getComptesList()){
				if (compte.getClass().equals(CompteCourant.class)){
					System.out.println(nbCompte + ": " + compte);
					nbCompte++;
				}
			}
			System.out.println("");
			System.out.print("Déposer sur quel compte?");
			String ccc = sc.next();
			try{
				if (Integer.parseInt(ccc) < nbCompte){
					Integer i = 0;
					for (Compte compte:client.getComptesList()){
						if (compte.getClass().equals(CompteCourant.class)){
							if(i == Integer.parseInt(ccc)){
								cc = (CompteCourant)compte;
							}
							i++;
						}
					}
				}else{
					System.out.println("Saisie invalide!");
				}
			}catch(Exception e){
				System.out.println("Saisie invalide!");
			}
		}while(cc == null);
		System.out.print("Combien voulez-vous déposer: ");
		Double depot = sc.nextDouble();
		cc.crediter(depot);
		System.out.println("");
	}
	
	//*retrait sur un compte courant
	public static void retirerCompteCourant(Scanner sc, Client client){
		CompteCourant cc = null;
		Boolean debit = true;
		do{
			debit = true;
			do{
				Integer nbCompte = 0;
				System.out.println("**** Affichage des comptes courants****");
				for (Compte compte:client.getComptesList()){
					if (compte.getClass().equals(CompteCourant.class)){
						System.out.println(nbCompte + ": " + compte);
						nbCompte++;
					}
				}
				System.out.println("");
				System.out.print("Retirer sur quel compte?");
				String ccc = sc.next();
				try{
					if (Integer.parseInt(ccc) < nbCompte){
						Integer i = 0;
						for (Compte compte:client.getComptesList()){
							if (compte.getClass().equals(CompteCourant.class)){
								if(i == Integer.parseInt(ccc)){
									cc = (CompteCourant)compte;
								}
								i++;
							}
						}
					}else{
						System.out.println("Saisie invalide!");
					}
				}catch(Exception e){
					System.out.println("Saisie invalide!");
				}
			}while(cc == null);
			System.out.print("Combien voulez-vous retirer: ");
			Double retrait = sc.nextDouble();
			try{
				cc.debiter(retrait);
			}catch (DebitException e){
				System.out.println("Retrait impossible, veuiller saisir un montant moins élevé!");
				debit = false;
			}
			System.out.println("");
		}while(!debit);
	}
	
	//*virement d'un compte a un autre
	public static void virementInterCompte(Scanner sc, Client client){
		Compte cr = null;
		Compte cd = null;
		Double transfert;
		
		Boolean valide = true;
		do{
			valide = true;
			do{
				System.out.print("Combien voulez-vous transférer: ");
				transfert = sc.nextDouble();
				System.out.println("");
				Integer nbCompte = 0;
				System.out.println("**** Affichage des comptes ****");
				for (Compte compte:client.getComptesList()){
					System.out.println(nbCompte + ": " + compte);
					nbCompte++;
				}
				System.out.println("");
				System.out.print("Compte a débiter?");
				String ccc = sc.next();
				try{
					if (Integer.parseInt(ccc) < nbCompte){
						Integer i = 0;
						for (Compte compte:client.getComptesList()){
							if(i == Integer.parseInt(ccc)){
								cr = compte;
							}
							i++;
						}
					}else{
						System.out.println("Saisie invalide!");
					}
				}catch(Exception e){
					System.out.println("Saisie invalide!");
				}
			}while(cr == null);
			System.out.println("");
			while( (cd == null) && valide){
				Integer nbCompte = 0;
				System.out.println("**** Affichage des comptes ****");
				for (Compte compte:client.getComptesList()){
					System.out.println(nbCompte + ": " + compte);
					nbCompte++;
				}
				System.out.println("");
				System.out.print("Déposer sur quel compte?");
				String ccc = sc.next();
				try{
					if (Integer.parseInt(ccc) < nbCompte){
						Integer i = 0;
						for (Compte compte:client.getComptesList()){
							if(i == Integer.parseInt(ccc)){
								cd = compte;
							}
							i++;
						}
					}else{
						System.out.println("Saisie invalide!");
					}
				}catch(Exception e){
					System.out.println("Saisie invalide!");
				}
			}
			try{
				cr.effectuerVirement(cd, transfert);
			}catch (CompteException e){
				System.out.println("Transfert impossible, veuiller saisir un montant moins élevé!");
				valide = false;
			}
		}while(!valide);
		System.out.println("");
	}

	public static void main(String[] args) {
		//variable qui permet de boucler sur le menu
		Boolean terminated = false;
		
		/*
		 *initialisation du client cote client
		 *lecture des fichiers client et compte
		 */
		initialisation();
		
		//lancement du scanner
		Scanner sc = new Scanner(System.in);
		
		Client client = connection(sc);
		do{
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
				fermeture();
				System.out.println("Arret de l'application!");
				terminated = true;
				break;
			//creation d'un compte client
			case "1":
				consulterSoldesComptes(client);
				break;
			//creation d'un compte courant, a associer a un client
			case "2":
				crediterCompteCourant(sc, client);
				break;
			//creation d'un compte epargne a associer a un client
			case "3":
				retirerCompteCourant(sc, client);
				break;
			//affichage de la liste des clients
			case "4":
				virementInterCompte(sc, client);
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
