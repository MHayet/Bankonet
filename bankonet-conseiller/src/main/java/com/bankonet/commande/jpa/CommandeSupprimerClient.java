package com.bankonet.commande.jpa;

import java.util.Scanner;

import com.bankonet.metier.jpa.BankonetMetierConseiller;

public class CommandeSupprimerClient extends CommandeFactoryJPA {
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
	public CommandeSupprimerClient(Integer id, String lib) {
		setId(id);
		setLibelle(lib);
	}
	
	public CommandeSupprimerClient(Integer id, String lib, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setConseiller(bmc);
	}
	
	public CommandeSupprimerClient(Integer id, String lib, Scanner sc, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setScanner(sc);
		setConseiller(bmc);
	}
	
	//methodes
	@Override
	public void execute() {
		Boolean ok = false;
		System.out.println("**** Supprimer un client ****");
		System.out.println("ID du client: ");
		String id = sc.next();
		System.out.println("Voulez-vous vraiment supprimer le client? [o/N]");
		String supp = sc.next();
		if (supp.toUpperCase().equals("O")){
			ok = bmc.supprimerClient(id);
		}else{
			System.out.println("Client non supprimé");
		}
		if (ok){
			System.out.println("Client supprimé");
		}else{
			System.out.println("Client non supprimé");
		}
		System.out.println("");
	}

}
