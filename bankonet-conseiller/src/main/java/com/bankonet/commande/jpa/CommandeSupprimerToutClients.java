package com.bankonet.commande.jpa;

import java.util.Scanner;

import com.bankonet.metier.jpa.BankonetMetierConseiller;

public class CommandeSupprimerToutClients implements CommandeFactoryJPA {
	//attributs
	private Integer id;
	private String libelle;
	private Scanner sc;
	private BankonetMetierConseiller bmc;

	//accesseurs
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public void setLibelle(String lib) {
		this.libelle = lib;
	}

	@Override
	public String getLibelle() {
		return this.libelle;
	}

	@Override
	public void setScanner(Scanner sc) {
		this.sc = sc;
	}

	@Override
	public Scanner getScanner() {
		return sc;
	}

	@Override
	public void setConseiller(BankonetMetierConseiller bmc) {
		this.bmc = bmc;
	}

	@Override
	public BankonetMetierConseiller getConseiller() {
		return bmc;
	}
	
	//constructeurs
	public CommandeSupprimerToutClients(Integer id, String lib) {
		setId(id);
		setLibelle(lib);
	}
	
	public CommandeSupprimerToutClients(Integer id, String lib, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setConseiller(bmc);
	}
	
	public CommandeSupprimerToutClients(Integer id, String lib, Scanner sc, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setScanner(sc);
		setConseiller(bmc);
	}
	
	//methodes
	@Override
	public void execute() {
		System.out.println("**** Supprimer tout les clients ****");
		System.out.println("Voulez-vous vraiment supprimer tout les clients? [o/N]");
		String supp = sc.next();
		if (supp.toUpperCase().equals("O")){
			bmc.supprimerToutClients();
			System.out.println("Clients supprimés");
		}else{
			System.out.println("Clients non supprimés");
		}
		System.out.println("");
	}

}
