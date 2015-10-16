package com.bankonet.dao.compte;

import java.util.ArrayList;

import com.bankonet.lib.Compte;

public interface BankonetCompteFactory {
	public void setComptes(ArrayList<Compte> comptes);
	public ArrayList<Compte> getComptes(String[] listCC, String[] listCE);

}
