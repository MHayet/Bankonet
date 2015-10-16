package com.bankonet.dao;

import com.bankonet.dao.client.BankonetClientFactory;
import com.bankonet.dao.compte.BankonetCompteFactory;

public interface BankonetDAOFactory {
	public BankonetCompteFactory getCompteFactory();
	public BankonetClientFactory getClientFactory();	

}
