package com.bankonet.dao.client;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

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

	@Override
	public ArrayList<Client> getClients() {
		ArrayList<Client> clients = new ArrayList<>();
		EntityManager em = emf.createEntityManager();
		
		em.find(Client.class, clients);
		
		return clients;
	}

}
