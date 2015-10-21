package com.bankonet.dao.client;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.bankonet.lib.Client;

public class BankonetClientJPA implements BankonetClientFactory {
	//attributs
	private EntityManagerFactory emf;
	
	//accesseurs
	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	//construteurs
	public BankonetClientJPA(EntityManagerFactory emf) {
		setEmf(emf);
	}

	//methodes
	@Override
	public void setClients(ArrayList<Client> clients) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		for (Client client:clients){
			em.persist(client);	
		}
		et.commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Client> getClients() {
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT c FROM Client c", Client.class); //attention, le nom ici n'est pas celui de la table, mais celui de la classe
		
		ArrayList<Client> clients = (ArrayList<Client>) query.getResultList();
		
		em.close();
		
		return clients;
	}
	
	public void updateClients(ArrayList<Client> clients){
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		for (Client client:clients){
			em.merge(client);	
		}
		et.commit();
		em.close();		
	}

	@Override
	public Client getParNom(String nom) {
		EntityManager em = emf.createEntityManager();
		ArrayList<Client> clients = new ArrayList<>();
		
		clients =  (ArrayList<Client>) em.createNamedQuery("client.chercherParNom",Client.class)
				.setParameter("nom", nom).getResultList();
		
		em.close();
		
		if (clients.size() > 0)
			return clients.get(0);
		else
			return null;
	}

	@Override
	public Client getParPrenom(String prenom) {
		EntityManager em = emf.createEntityManager();
		ArrayList<Client> clients = new ArrayList<>();
		
		clients =  (ArrayList<Client>) em.createNamedQuery("client.chercherParPrenom",Client.class)
				.setParameter("prenom", prenom).getResultList();
		
		em.close();
		
		if (clients.size() > 0)
			return clients.get(0);
		else
			return null;
	}

	@Override
	public void supprimerClient(String id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et  = em.getTransaction();
		
		et.begin();
		Client client = em.find(Client.class, id);
		if (client != null){
			em.remove(client);
		}
		et.commit();
		
		em.close();		
	}

	@Override
	public void supprimerToutClients() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Query query = em.createNamedQuery("client.supprimerToutClients");
		query.executeUpdate();
		et.commit();
		em.close();	
		
	}

}
