package com.bankonet.dao.jpa;

import com.bankonet.dao.client.jpa.BankonetClientFactory;
import com.bankonet.dao.compte.jpa.BankonetCompteFactory;

public interface BankonetDAOFactory {
	public BankonetCompteFactory getCompteFactory();
	public BankonetClientFactory getClientFactory();	

}
