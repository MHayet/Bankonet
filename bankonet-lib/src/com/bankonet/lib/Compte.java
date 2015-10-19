package com.bankonet.lib;

public abstract class Compte implements CompteStat {
	//attributs
	private String numero;
	private String intitule;
	private Double solde;
	private static Integer nbCompte = 0;
	
	//accesseurs
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}
	
	//constructeurs
	public Compte(String numero){
		setNumero(numero);
	}
	
	public Compte(String numero, String intitule, Double solde){
		setNumero(numero);
		setIntitule(intitule);
		if (solde < 0){
			setSolde(0.0);
			System.out.println("Le solde ne peut �tre n�gatif lors de la cr�ation");
		} else {
			setSolde(solde);
		}
		nbComptePlus();
}
	
	//methodes de classe
	public static Integer getNbCompte(){
		return nbCompte;
	}
	
	public static void nbComptePlus(){
		nbCompte ++;
	}
	
	//methodes
	public abstract void crediter(Double somme) throws CompteException;
	
	public void debiter(Double somme) throws DebitException{
		if (somme < debitMax()){
			setSolde(getSolde()-somme);
		} else {
			throw new DebitException("D�bit impossible!");
		}
	}
	
	public void effectuerVirement(Compte compte, Double montant) throws CompteException{
		try{
			debiter(montant);
			compte.crediter(montant);
		}catch(CompteException e){
			throw e;
		}
	}
	
	public abstract Double debitMax();
	
	@Override
	public String toString() {
		return String.format("numero=%s, intitule=%s, solde=%s", numero, intitule, solde);
	}

}
