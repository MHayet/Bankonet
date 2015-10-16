package com.bankonet.dao.client;

import java.util.ArrayList;

import com.bankonet.lib.Client;

public interface BankonetClientFactory {
	public void setClients(ArrayList<Client> clients);
	public ArrayList<Client> getClients();

}
