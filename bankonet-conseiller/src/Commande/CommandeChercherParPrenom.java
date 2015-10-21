package Commande;

import java.util.Scanner;

import com.bankonet.metier.jpa.BankonetMetierConseiller;

public class CommandeChercherParPrenom implements CommandeFactoryJPA {
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
	public CommandeChercherParPrenom(Integer id, String lib) {
		setId(id);
		setLibelle(lib);
	}
	
	public CommandeChercherParPrenom(Integer id, String lib, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setConseiller(bmc);
	}
	
	public CommandeChercherParPrenom(Integer id, String lib, Scanner sc, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setScanner(sc);
		setConseiller(bmc);
	}
	
	//methodes
	@Override
	public void execute() {
		System.out.println("**** Recherche par prenom ****");
		System.out.println("Prenom du client: ");
		String prenom = sc.next();
		if (bmc.chercherParPrenom(prenom)){
			System.out.println("Client trouvé");
		}else{
			System.out.println("Client introuvable!");
		}
		System.out.println("");
	}

}
