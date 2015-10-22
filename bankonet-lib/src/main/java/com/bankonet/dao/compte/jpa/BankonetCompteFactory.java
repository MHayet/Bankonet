package com.bankonet.dao.compte.jpa;

import java.util.ArrayList;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;

public interface BankonetCompteFactory {
	public void setComptes(ArrayList<Compte> comptes);
	public ArrayList<Compte> getComptes(Client client);

}
