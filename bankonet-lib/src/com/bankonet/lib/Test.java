package com.bankonet.lib;

import com.bankonet.metier.BankonetMetierConseiller;

public class Test {

	public static void main(String[] args) {
		BankonetMetierConseiller bmc = new BankonetMetierConseiller();
		bmc.initialisation();
		
		System.out.println("Fini!");
		bmc.fermeture();
	}

}
