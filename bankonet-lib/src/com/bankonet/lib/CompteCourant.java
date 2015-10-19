package com.bankonet.lib;

public class CompteCourant extends Compte{
	//attributs
	private Double montantDecouvertAutorise;
	
	//accesseurs
	public Double getMontantDecouvertAutorise() {
		return montantDecouvertAutorise;
	}

	public void setMontantDecouvertAutorise(Double montantDecouvertAutorise) {
		this.montantDecouvertAutorise = montantDecouvertAutorise;
	}
	
	//constructeurs
	public CompteCourant(String numero){
		super(numero);
	}
	
	public CompteCourant(String numero, String intitule, Double solde, Double decouvertMax){
		super (numero, intitule, solde);
		setMontantDecouvertAutorise(decouvertMax);
	}
	
	//methodes
	@Override
	public String toString() {
		return String.format("CompteCourant: %s, montantDecouvertAutorise=%s",
				super.toString(), montantDecouvertAutorise);
	}
	
	public Double debitMax(){
		return getSolde()+getMontantDecouvertAutorise();
	}
	
	public void crediter(Double somme){
		if (somme > 0){
			setSolde(getSolde()+somme);
		} else {
			System.out.println("Cr�dit n�gatif");
		}
	}
}
