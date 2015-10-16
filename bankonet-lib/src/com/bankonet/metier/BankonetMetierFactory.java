package com.bankonet.metier;

import java.util.ArrayList;

import com.bankonet.lib.Client;

public interface BankonetMetierFactory {	
	public void initialisation();
	public void creerCompte(String nom, String prenom, String identifiant, String login, String mdp);
	public void creerCompteCourant(String numero, String intitule, Double solde, Double dma);
	public void creerCompteEpargne(String numero, String intitule, Double solde, Double ta);
	public ArrayList<Client> getListeClients();
	public void fermeture();

}
