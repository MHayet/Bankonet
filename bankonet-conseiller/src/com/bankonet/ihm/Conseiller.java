package com.bankonet.ihm;

import com.bankonet.lib.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Conseiller {
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
	public static void creerCompte(Scanner cc){
		
		System.out.println("**** Creation d'un compte ****");
		System.out.println("Veuillez saisir le nom: ");
		String nom = cc.next();
		System.out.println("Veuillez saisir le prénom: ");
		String prenom = cc.next();
		System.out.println("Veuillez saisir le login: ");
		String login = cc.next();
		
		//ajout du nouveau client dans le programme
		clients.add(new Client(nom, prenom, nom+prenom, login, "secret"));
	}
	
	//*affichage du menu de creation d'un compte courant
	public static void creerCompteCourant(Scanner ccc){
		boolean existe = false;
		Client client;
		
		System.out.println("**** Creation d'un compte courant****");
		//*saisie de l'id du client pour vérifier son existance
		System.out.println("Identifiant du client: ");
		String idc = ccc.next();
		Iterator<Client> it = clients.iterator();
		while ( (!existe) && it.hasNext() ){
			client = it.next();
			//*si le client existe, on cree le compte courant
			if (client.getIdentifiant().equals(idc)){
				existe = true;
				System.out.println("Veuillez saisir le numéro: ");
				String num = ccc.next();
				System.out.println("Veuillez saisir l'intitulé: ");
				String intit = ccc.next();
				System.out.println("Veuillez saisir le solde: ");
				Double solde = ccc.nextDouble();
				System.out.println("Veuillez saisir le découvert maximum autorisé: ");
				Double dma = ccc.nextDouble();
				
				client.creerCompte(new CompteCourant(num, intit, solde, dma));
			}			
		}
		//*sinon on demande la verification de l'id, ou la creation du compte client
		if (!existe){
			System.out.println("Client non trouvé!");
			System.out.println("Veuillez vérifiez l'identifiant saisie");
			System.out.println("Ou veuillez créer le compte client");
		}
		System.out.println("");
		
	}
	
	//*affichage du menu de creation d'un compte epargne
	public static void creerCompteEpargne(Scanner ccc){
		boolean existe = false;
		Client client;
		
		System.out.println("**** Creation d'un compte epargne****");
		//*saisie de l'id du client pour verifier son existance
		System.out.println("Identifiant du client: ");
		String idc = ccc.next();
		Iterator<Client> it = clients.iterator();
		while ( (!existe) && it.hasNext() ){
			client = it.next();
			//*si le client existe, on cree le compte epargne
			if (client.getIdentifiant().equals(idc)){
				existe = true;
				System.out.println("Veuillez saisir le numéro: ");
				String num = ccc.next();
				System.out.println("Veuillez saisir l'intitulé: ");
				String intit = ccc.next();
				System.out.println("Veuillez saisir le solde: ");
				Double solde = ccc.nextDouble();
				System.out.println("Veuillez saisir le taux d'intéret: ");
				Double ta = ccc.nextDouble();
				
				client.creerCompte(new CompteEpargne(num, intit, solde, ta));
				
				System.out.println("Compte courant créé!");
			}			
		}
		//*sinon on demande la verification de l'id, ou la creation du compte client
		if (!existe){
			System.out.println("Client non trouvé!");
			System.out.println("Veuillez vérifiez l'identifiant saisie");
			System.out.println("Ou veuillez créer le compte client");
		}
		System.out.println("");
		
	}
	
	//*affichage de la liste des clients
	public static void afficherListeClients(){
		Iterator<Client> it = clients.iterator();
		System.out.println("**** Liste de clients ****");
		while (it.hasNext()){
			System.out.println(it.next());
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

	public static void main(String[] args) {
		//variable qui permet de boucler sur le menu
		Boolean terminated = false;
		
		/*
		 *initialisation du client cote conseiller
		 *lecture des fichiers client et compte
		 */
		initialisation();
		
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
				fermeture();
				System.out.println("Arret de l'application!");
				terminated = true;
				break;
			//creation d'un compte client
			case "1":
				creerCompte(sc);
				System.out.println("Compte créé: mdp par defaut: 'secret'");
				break;
			//creation d'un compte courant, a associer a un client
			case "2":
				creerCompteCourant(sc);
				break;
			//creation d'un compte epargne a associer a un client
			case "3":
				creerCompteEpargne(sc);
				break;
			//affichage de la liste des clients
			case "4":
				afficherListeClients();
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
