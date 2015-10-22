package com.bankonet.commande.jpa;

import java.util.Scanner;

import com.bankonet.metier.jpa.BankonetMetierConseiller;

public class CommandeChercherParNom extends CommandeFactoryJPA {
	//attributs
	private Integer id;
	private String libelle;
	private Scanner sc;
	private BankonetMetierConseiller bmc;
	
	//constructeurs
	public CommandeChercherParNom(Integer id, String lib) {
		setId(id);
		setLibelle(lib);
	}
	
	public CommandeChercherParNom(Integer id, String lib, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setConseiller(bmc);
	}
	
	public CommandeChercherParNom(Integer id, String lib, Scanner sc, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setScanner(sc);
		setConseiller(bmc);
	}
	
	//methodes
	@Override
	public void execute() {
		System.out.println("**** Recherche par nom ****");
		System.out.println("Nom du client: ");
		String nom = sc.next();
		if (bmc.chercherParNom(nom)){
			System.out.println("Client trouvé");
		}else{
			System.out.println("Client introuvable!");
		}
		System.out.println("");
	}

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

}
