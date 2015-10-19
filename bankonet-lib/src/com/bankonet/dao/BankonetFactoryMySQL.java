package com.bankonet.dao;

import com.bankonet.dao.client.BankonetClientFactory;
import com.bankonet.dao.client.BankonetClientMySQL;
import com.bankonet.dao.compte.BankonetCompteFactory;
import com.bankonet.dao.compte.BankonetCompteMySQL;

public class BankonetFactoryMySQL implements BankonetDAOFactory {

	@Override
	public BankonetCompteFactory getCompteFactory() {
		return new BankonetCompteMySQL();
	}

	@Override
	public BankonetClientFactory getClientFactory() {
		return new BankonetClientMySQL();
	}

}
