package com.bankonet.dao.compte;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteEpargne;

public class BankonetCompteFile implements BankonetCompteFactory {
	private String compteFilePath = "C:\\Users\\ETY\\Desktop\\Projets Formation\\Java\\TP\\Bankonet\\compte.properties";

	@Override
	public void setComptes(ArrayList<Compte> comptes) {

		//ouverture des writers
		try(BufferedWriter buffco = new BufferedWriter(new FileWriter(compteFilePath))){
			for (Compte compte:comptes){
				String strco = compte.getNumero()+"=intitule:"+compte.getIntitule()+"&solde:"+compte.getSolde().toString()+"&param:";
				if(compte.getClass().equals(CompteCourant.class)){
					CompteCourant cpt = (CompteCourant)compte;
					strco += cpt.getMontantDecouvertAutorise();
				}else{
					CompteEpargne cpt = (CompteEpargne)compte;
					strco += cpt.getTauxInteret();
				}
				buffco.write(strco+"\n");
			}			
		}catch (Exception e){
			System.out.println("erreur de sauvegarde");
		}
	}

	@Override
	public ArrayList<Compte> getComptes(Client client) {
		//Liste des comptes a renvoyer
		ArrayList<Compte> comptes= new ArrayList<>();

		//ouverture du reader
		try(BufferedReader buff = new BufferedReader(new FileReader(compteFilePath))){			
			String line;
			
			//tant qu'on n'est pas au bout du fichier
			while ((line = buff.readLine()) != null) {
				//split multiple pour recuperer les bons elements
				String[] numero = line.split("=");
				
				String[] data = numero[1].split("&");
				
				String[] intitule = data[0].split(":");
				String[] solde = data[1].split(":");
				String[] param = data[2].split(":");
				
				//parcours des comptes du client passe en parametre
				for (Compte cpt:client.getComptesList()){
					if (cpt.getNumero().equals(numero[0])){
						if (cpt instanceof CompteCourant){
							comptes.add(new CompteCourant(numero[0],intitule[1],Double.valueOf(solde[1]),Double.valueOf(param[1])));
						}else{
							comptes.add(new CompteEpargne(numero[0],intitule[1],Double.valueOf(solde[1]),Double.valueOf(param[1])));
						}
					}
				}	
			}
		}catch (Exception e){
			System.out.println("Fichier compte introuvable!");
		}
		return comptes;
	}

}
