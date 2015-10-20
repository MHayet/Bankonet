package Commande;

import java.util.Scanner;

import com.bankonet.metier.BankonetMetierConseiller;

public class CommandeCreerCompte implements CommandeFactory {
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
	public CommandeCreerCompte(Integer id, String lib) {
		setId(id);
		setLibelle(lib);
	}
	
	public CommandeCreerCompte(Integer id, String lib, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setConseiller(bmc);
	}
	
	public CommandeCreerCompte(Integer id, String lib, Scanner sc, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setScanner(sc);
		setConseiller(bmc);
	}
	
	//methodes
	@Override
	public void execute() {
		System.out.println("**** Creation d'un compte ****");
		System.out.println("Veuillez saisir le nom: ");
		String nom = sc.next();
		System.out.println("Veuillez saisir le prénom: ");
		String prenom = sc.next();
		System.out.println("Veuillez saisir le login: ");
		String login = sc.next();
		
		//ajout du nouveau client dans le programme
		bmc.creerCompte(nom, prenom, nom+prenom, login);
		
		System.out.println("Compte créé: mdp par defaut: 'secret'");
	}

}
