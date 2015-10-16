package com.bankonet.dao;

import com.bankonet.dao.client.BankonetClientFactory;
import com.bankonet.dao.client.BankonetClientFile;
import com.bankonet.dao.compte.BankonetCompteFactory;
import com.bankonet.dao.compte.BankonetCompteFile;

public class BankonetFactoryFile implements BankonetDAOFactory{

	@Override
	public BankonetCompteFactory getCompteFactory() {
		return new BankonetCompteFile();
	}

	@Override
	public BankonetClientFactory getClientFactory() {
		return new BankonetClientFile();
	}

}
