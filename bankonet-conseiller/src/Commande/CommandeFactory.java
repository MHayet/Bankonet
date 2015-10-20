package Commande;

import java.util.Scanner;

import com.bankonet.metier.BankonetMetierConseiller;

public interface CommandeFactory {
	public void setId(Integer id);
	public Integer getId();
	public void setLibelle(String lib);
	public String getLibelle();
	public void setScanner(Scanner sc);
	public Scanner getScanner();
	public void setConseiller(BankonetMetierConseiller bmc);
	public BankonetMetierConseiller getConseiller();
	public void execute();
}
