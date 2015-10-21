package com.bankonet.metier.jpa;

import java.util.ArrayList;

import com.bankonet.lib.Client;

public interface BankonetMetierFactory {
	public void initialisation();
	public void init();
	public ArrayList<Client> getListeClients();
	public void fermeture();
	public boolean chercherParNom(String nom);
	public boolean chercherParPrenom(String prenom);
	public boolean renommerClient(String id, String nom);
	public boolean supprimerClient(String id);
	public void supprimerToutClients();

}
