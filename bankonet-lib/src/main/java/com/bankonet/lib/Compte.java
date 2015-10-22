package com.bankonet.lib;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Compte implements CompteStat {
	//attributs
	@Id
	private String numero;
	@Column(name = "intitule")
	private String intitule;
	@Column(name = "solde")
	private Double solde;
	@Transient
	private static Integer nbCompte = 0;
	
	//constructeurs
	public Compte(){}
	
	public Compte(String numero){
		setNumero(numero);
	}
	
	public Compte(String numero, String intitule, Double solde){
		setNumero(numero);
		setIntitule(intitule);
		if (solde < 0){
			setSolde(0.0);
			System.out.println("Le solde ne peut être négatif lors de la création");
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
			throw new DebitException("Débit impossible!");
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

}
