package com.bankonet.metier;

import java.util.ArrayList;

import com.bankonet.dao.BankonetDAOFactory;
import com.bankonet.dao.BankonetFactoryFile;
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
		bdf = new BankonetFactoryFile();
	}

	//methodes
	@Override
	public void initialisation() {
		setClients(bdf.getClientFactory().getClients());		
	}

	@Override
	public void creerCompte(String nom, String prenom, String identifiant, String login, String mdp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerCompteCourant(String numero, String intitule, Double solde, Double dma) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerCompteEpargne(String numero, String intitule, Double solde, Double ta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Client> getListeClients() {
		return getClients();
	}

	@Override
	public void fermeture() {
		bdf.getClientFactory().setClients(getClients());
	}

}
