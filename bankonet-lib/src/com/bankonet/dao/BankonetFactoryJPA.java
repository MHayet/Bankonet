package com.bankonet.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bankonet.dao.client.BankonetClientFactory;
import com.bankonet.dao.client.BankonetClientJPA;
import com.bankonet.dao.compte.BankonetCompteFactory;
import com.bankonet.dao.compte.BankonetCompteJPA;

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
