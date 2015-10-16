package com.bankonet.dao.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.bankonet.dao.compte.BankonetCompteFile;
import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;

public class BankonetClientFile implements BankonetClientFactory {
	private String clientFilePath = "C:\\Users\\ETY\\Desktop\\Projets Formation\\Java\\TP\\Bankonet\\client.properties";

	@Override
	public void setClients(ArrayList<Client> clients) {
		//Liste des comptes
		ArrayList<Compte> comptes = new ArrayList<>();
		
		try{
			//ouverture des writers
			BufferedWriter buffcl = new BufferedWriter(new FileWriter(clientFilePath));
			
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
					comptes.add(compte);
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
		}catch (Exception e){
			System.out.println("erreur de sauvegarde");
		}
		new BankonetCompteFile().setComptes(comptes);
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
