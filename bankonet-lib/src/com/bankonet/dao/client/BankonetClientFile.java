package com.bankonet.dao.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteEpargne;

public class BankonetClientFile implements BankonetClientFactory {
	private String clientFilePath = "C:\\Users\\ETY\\Desktop\\Projets Formation\\Java\\TP\\Bankonet\\client.properties";

	@Override
	public void setClients(ArrayList<Client> clients) {
		//ouverture des writers
		try(BufferedWriter buffcl = new BufferedWriter(new FileWriter(clientFilePath))){
			
			for (Client client:clients){
				ArrayList<String> listCC = new ArrayList<>();
				ArrayList<String> listCE = new ArrayList<>();
				String strcl = client.getIdentifiant()+"=nom:"+client.getNom()+"&prenom:"+client.getPrenom()+"&login:"+client.getLogin()+"&mdp:"+client.getMdp();
				
				for (Compte compte:client.getComptesList()){
					if(compte.getClass().equals(CompteCourant.class)){
						listCC.add(compte.getNumero());
					}else{
						listCE.add(compte.getNumero());
					}
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
		}catch (Exception e){
			System.out.println("erreur de sauvegarde");
		}
	}

	@Override
	public ArrayList<Client> getClients() {
		//Liste des clients a renvoyer
		ArrayList<Client> clients= new ArrayList<>();

		//ouverture du reader
		try(BufferedReader buff = new BufferedReader(new FileReader(clientFilePath))){			
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
				
				String[] listCC = cc[1].split(",");
				String[] listCE = ce[1].split(",");
				
				Client client = new Client(nom[1],prenom[1],id[0],login[1],mdp[1]);

				if (listCC.length > 0){
					for(String str:listCC){
						client.creerCompte(new CompteCourant(str));
					}
				}
				if (listCE.length > 0){
					for(String str:listCE){
						client.creerCompte(new CompteEpargne(str));
					}
				}

				//ajout du client dans le programme			
				clients.add(client);
			}
		}catch (Exception e){
			System.out.println("Fichier client introuvable!");
		}
		
		return clients;
	}

}
