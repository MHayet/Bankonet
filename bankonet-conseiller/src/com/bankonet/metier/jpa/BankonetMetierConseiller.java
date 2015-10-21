package com.bankonet.metier.jpa;

import java.util.ArrayList;

import com.bankonet.dao.BankonetDAOFactory;
import com.bankonet.dao.BankonetFactoryJPA;
import com.bankonet.lib.Client;

public class BankonetMetierConseiller implements BankonetMetierFactory {
	//attributs
	private ArrayList<Client> clients;
	private BankonetDAOFactory bdf;
	
	//accesseurs
	private ArrayList<Client> getClients() {
		return clients;
	}

	private void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}

	//constructeurs
	public BankonetMetierConseiller() {
		clients = new ArrayList<>();
		//bdf = new BankonetFactoryFile();
		bdf = new BankonetFactoryJPA();
	}

	//methodes
	@Override
	public void initialisation() {
		setClients(bdf.getClientFactory().getClients());
		/*for (Client client:clients){
			client.setComptesList(bdf.getCompteFactory().getComptes(client));
		}*/
	}

	@Override
	public void init() {
		ArrayList<Client> clients = new ArrayList<>();
		clients.add(new Client("Kirigaya", "Kazuto", "KirigayaKazuto", "kirito", "asuna"));
		clients.add(new Client("Yuki", "Asuna", "YukiAsuna", "asuna", "kirito"));
		clients.add(new Client("Kirigaya", "Yui", "KirigayaYui", "yui", "mhcp01"));
		clients.add(new Client("Kirigaya", "Suguha", "KirigayaSuguha", "leaffa", "alo"));
		clients.add(new Client("Kono", "Yuki", "KonoYuki", "yuki", "rosario"));
		bdf.getClientFactory().setClients(clients);
		
		this.clients.addAll(clients);
	}

	@Override
	public ArrayList<Client> getListeClients() {
		return getClients();
	}

	@Override
	public void fermeture() {
		/*ArrayList<Compte> comptes = new ArrayList<>(); 
		for (Client client:getClients()){
			comptes.addAll(client.getComptesList());
		}
		bdf.getCompteFactory().setComptes(comptes);*/
		bdf.getClientFactory().updateClients(getClients());
	}

	@Override
	public boolean chercherParNom(String nom) {
		Boolean ok = false;
		Client client = bdf.getClientFactory().getParNom(nom);
		if (client != null){
			this.clients.add(client);
			ok = true;
		}
		return ok;
	}

	@Override
	public boolean chercherParPrenom(String prenom) {
		Boolean ok = false;
		Client client = bdf.getClientFactory().getParPrenom(prenom);
		if (client != null){
			this.clients.add(client);
			ok = true;
		}
		return ok;
	}

	@Override
	public boolean renommerClient(String id, String nom) {
		Boolean ok = false;
		if (clients.size() > 0){
			for (Client client:clients){
				if (client.getIdentifiant().equals(id)){
					client.setNom(nom);
					bdf.getClientFactory().updateClients(getClients());
					ok = true;
				}
			}
		}		
		return ok;		
	}

	@Override
	public boolean supprimerClient(String id) {
		Boolean ok = false;
		if (clients.size() > 0){
			Integer i = 0;
			for (Client client:clients){
				if (client.getIdentifiant().equals(id)){
					clients.remove(client);
					bdf.getClientFactory().supprimerClient(id);
					ok = true;
				}
				i++;
			}
		}		
		return ok;	
	}

	@Override
	public void supprimerToutClients() {
		clients.removeAll(clients);
		bdf.getClientFactory().supprimerToutClients();
	}

}
