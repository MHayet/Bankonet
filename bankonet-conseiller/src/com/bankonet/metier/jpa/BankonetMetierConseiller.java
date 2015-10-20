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

	/*private void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}*/

	//constructeurs
	public BankonetMetierConseiller() {
		clients = new ArrayList<>();
		//bdf = new BankonetFactoryFile();
		bdf = new BankonetFactoryJPA();
	}

	//methodes
	@Override
	public void initialisation() {
		/*setClients(bdf.getClientFactory().getClients());
		for (Client client:clients){
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
		bdf.getCompteFactory().setComptes(comptes);
		bdf.getClientFactory().setClients(getClients());*/
	}

}
