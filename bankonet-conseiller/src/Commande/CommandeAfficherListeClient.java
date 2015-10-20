package Commande;

import java.util.Iterator;
import java.util.Scanner;

import com.bankonet.lib.Client;
import com.bankonet.metier.BankonetMetierConseiller;

public class CommandeAfficherListeClient implements CommandeFactory {
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
	public CommandeAfficherListeClient(Integer id, String lib) {
		setId(id);
		setLibelle(lib);
	}
	
	public CommandeAfficherListeClient(Integer id, String lib, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setConseiller(bmc);
	}
	
	public CommandeAfficherListeClient(Integer id, String lib, Scanner sc, BankonetMetierConseiller bmc) {
		setId(id);
		setLibelle(lib);
		setScanner(sc);
		setConseiller(bmc);
	}
	
	//methodes
	@Override
	public void execute() {
		Iterator<Client> it = bmc.getListeClients().iterator();
		System.out.println("**** Liste de clients ****");
		while (it.hasNext()){
			System.out.println(it.next());
		}
	}

}
