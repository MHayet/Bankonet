package com.bankonet.dao.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bankonet.dao.client.jpa.BankonetClientFactory;
import com.bankonet.dao.client.jpa.BankonetClientJPA;
import com.bankonet.dao.compte.jpa.BankonetCompteFactory;
import com.bankonet.dao.compte.jpa.BankonetCompteJPA;

public class BankonetFactoryJPA implements BankonetDAOFactory {
	private EntityManagerFactory emf;
	
	public BankonetFactoryJPA() {
		emf = Persistence.createEntityManagerFactory("bankonet-lib");
	}

	@Override
	public BankonetCompteFactory getCompteFactory() {
		return new BankonetCompteJPA();
	}

	@Override
	public BankonetClientFactory getClientFactory() {
		return new BankonetClientJPA(emf);
	}

}
