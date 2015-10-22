package com.bankonet.lib;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CompteEpargne extends Compte{
	//constantes
	private static Double plafond = 500.0;
	
	//attributs
	@Column(name = "tauxinteret")
	private Double tauxInteret;
	
	//accesseurs
	public Double getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(Double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}
	
	//constructeurs
	public CompteEpargne(){}
	
	public CompteEpargne(String numero){
		super(numero);
	}
	
	public CompteEpargne(String numero, String intitule, Double solde, Double tauxInteret){
		super (numero, intitule, solde);
		setTauxInteret(tauxInteret);
	}
	
	@Override
	public String toString() {
		return String.format("CompteEpargne: %s, tauxInteret=%s", super.toString(), tauxInteret);
	}
	
	public Double debitMax(){
		return getSolde();
	}
	
	public void crediter(Double somme) throws CreditException{
		if (somme < plafond){
			setSolde(getSolde()+somme);
		} else{
			throw new CreditException("Credit impossible: plafond dépassé!");
		}
	}
}
