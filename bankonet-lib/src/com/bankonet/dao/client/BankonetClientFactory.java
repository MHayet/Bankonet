package com.bankonet.dao.client;

import java.util.ArrayList;

import com.bankonet.lib.Client;

public interface BankonetClientFactory {
	public void setClients(ArrayList<Client> clients);
	public ArrayList<Client> getClients();
	public void updateClients(ArrayList<Client> clients);
	public Client getParNom(String nom);
	public Client getParPrenom(String prenom);
	public void supprimerClient(String id);
	public void supprimerToutClients();

}
