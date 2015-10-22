package com.bankonet.commande;

import java.util.Scanner;

import com.bankonet.metier.BankonetMetierConseiller;

public class CommandeCreerCompteEpargne implements CommandeFactory {
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
	public CommandeCreerCompteEpargne(Integer id, String lib) {
		setId(id);
		setLibelle(lib);
	}
	
	public CommandeCreerCompteEpargne(Integer id, String lib, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setConseiller(bmc);
	}
	
	public CommandeCreerCompteEpargne(Integer id, String lib, Scanner sc, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setScanner(sc);
		setConseiller(bmc);
	}
	
	//methodes
	@Override
	public void execute() {
		boolean existe = false;
		
		System.out.println("**** Creation d'un compte epargne****");
		//*saisie de l'id du client pour verifier son existance
		System.out.println("Identifiant du client: ");
		String idc = sc.next();
		System.out.println("Veuillez saisir le numéro: ");
		String num = sc.next();
		System.out.println("Veuillez saisir l'intitulé: ");
		String intit = sc.next();
		System.out.println("Veuillez saisir le solde: ");
		Double solde = sc.nextDouble();
		System.out.println("Veuillez saisir le taux d'intéret: ");
		Double ta = sc.nextDouble();
		
		existe = bmc.creerCompteEpargne(idc, num, intit, solde, ta);
		//*sinon on demande la verification de l'id, ou la creation du compte client
		if (!existe){
			System.out.println("Client non trouvé!");
			System.out.println("Veuillez vérifiez l'identifiant saisie");
			System.out.println("Ou veuillez créer le compte client");
		}
		System.out.println("");
	}

}
