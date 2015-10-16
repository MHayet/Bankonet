package com.bankonet.dao.compte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteEpargne;

public class BankonetCompteFile implements BankonetCompteFactory {
	private String compteFilePath = "C:\\Users\\ETY\\Desktop\\Projets Formation\\Java\\TP\\Bankonet\\compte.properties";

	@Override
	public void setComptes(ArrayList<Compte> comptes) {
		
	}

	@Override
	public ArrayList<Compte> getComptes(String[] listCC, String[] listCE) {
		//Liste des comptes a renvoyer
		ArrayList<Compte> comptes= new ArrayList<>();
		
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
							comptes.add(new CompteCourant(numero[0],intitule[1],Double.parseDouble(solde[1]),Double.parseDouble(param[1])));
						}
					}
				}
				//parcours des numeros de comptes epargnes passes en parametres
				if(listCE != null){
					for (String ce:listCE){
						//si sa correspond, on le stock
						if (ce.equals(numero[0])){
							comptes.add(new CompteEpargne(numero[0],intitule[1],Double.parseDouble(solde[1]),Double.parseDouble(param[1])));
						}
					}
				}
	
			}
			//fermeture du reader
			buff.close();
			
		}catch (Exception e){
			System.out.println("Fichier compte introuvable!");
		}
		return comptes;
	}

}
