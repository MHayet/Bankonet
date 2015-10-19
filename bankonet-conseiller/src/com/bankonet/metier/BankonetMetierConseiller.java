package com.bankonet.metier;

import java.util.ArrayList;

import com.bankonet.dao.BankonetDAOFactory;
//import com.bankonet.dao.BankonetFactoryFile;
import com.bankonet.dao.BankonetFactoryMySQL;
import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteEpargne;

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
		bdf = new BankonetFactoryMySQL();
	}

	//methodes
	@Override
	public void initialisation() {
		setClients(bdf.getClientFactory().getClients());
		for (Client client:clients){
			client.setComptesList(bdf.getCompteFactory().getComptes(client));
		}
	}

	@Override
	public Boolean creerCompte(String nom, String prenom, String identifiant, String login) {
		if (clients.add(new Client(nom, prenom, identifiant, login, "Secret"))){
			return true;
		}
		return false;
	}

	@Override
	public Boolean creerCompteCourant(String loginClient, String numero, String intitule, Double solde, Double dma) {
		Client newCompte = null;
		for (Client client:clients){
			if (client.getLogin().equals(loginClient)){
				newCompte = client;
			}
		}
		if (newCompte != null){
			newCompte.creerCompte(new CompteCourant(numero, intitule, solde, dma));
			return true;
		}
		return false;
	}

	@Override
	public Boolean creerCompteEpargne(String loginClient, String numero, String intitule, Double solde, Double ta) {
		Client newCompte = null;
		for (Client client:clients){
			if (client.getLogin().equals(loginClient)){
				newCompte = client;
			}
		}
		if (newCompte != null){
			newCompte.creerCompte(new CompteEpargne(numero, intitule, solde, ta));
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Client> getListeClients() {
		return getClients();
	}

	@Override
	public void fermeture() {
		ArrayList<Compte> comptes = new ArrayList<>(); 
		for (Client client:getClients()){
			comptes.addAll(client.getComptesList());
		}
		bdf.getCompteFactory().setComptes(comptes);
		bdf.getClientFactory().setClients(getClients());
	}

}
