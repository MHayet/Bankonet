package com.bankonet.metier.jpa;

import java.util.ArrayList;

import com.bankonet.lib.Client;

public interface BankonetMetierFactory {
	public void initialisation();
	public void init();
	public ArrayList<Client> getListeClients();
	public void fermeture();

}
