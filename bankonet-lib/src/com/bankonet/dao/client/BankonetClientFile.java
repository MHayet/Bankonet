package com.bankonet.dao.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.bankonet.dao.compte.BankonetCompteFile;
import com.bankonet.lib.Client;

public class BankonetClientFile implements BankonetClientFactory {
	private String clientFilePath = "C:\\Users\\ETY\\Desktop\\Projets Formation\\Java\\TP\\Bankonet\\client.properties";

	@Override
	public void setClients(ArrayList<Client> client) {
		
	}

	@Override
	public ArrayList<Client> getClients() {
		//Liste des clients a renvoyer
		ArrayList<Client> clients= new ArrayList<>();
		
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
				client.setComptesList(new BankonetCompteFile().getComptes(listCC, listCE));

				//ajout du client dans le programme			
				clients.add(client);
			}
			//fermeture du reader
			buff.close();
			
		}catch (Exception e){
			System.out.println("Fichier client introuvable!");
		}
		
		return clients;
	}

}
