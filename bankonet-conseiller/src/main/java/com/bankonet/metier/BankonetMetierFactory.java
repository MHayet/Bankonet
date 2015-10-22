package com.bankonet.metier;

import java.util.ArrayList;

import com.bankonet.lib.Client;

public interface BankonetMetierFactory {	
	public void initialisation();
	public Boolean creerCompte(String nom, String prenom, String identifiant, String login);
	public Boolean creerCompteCourant(String loginClient, String numero, String intitule, Double solde, Double dma);
	public Boolean creerCompteEpargne(String loginClient, String numero, String intitule, Double solde, Double ta);
	public ArrayList<Client> getListeClients();
	public void fermeture();

}
