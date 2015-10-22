package com.bankonet.commande.jpa;

import java.util.Scanner;

import com.bankonet.metier.jpa.BankonetMetierConseiller;

public abstract class CommandeFactoryJPA {
	public abstract void setId(Integer id);
	public abstract Integer getId();
	public abstract void setLibelle(String lib);
	public abstract String getLibelle();
	public abstract void setScanner(Scanner sc);
	public abstract Scanner getScanner();
	public abstract void setConseiller(BankonetMetierConseiller bmc);
	public abstract BankonetMetierConseiller getConseiller();
	public abstract void execute();
}
