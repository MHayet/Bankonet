package com.bankonet.lib;

import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
@NamedQueries({
	@NamedQuery(name="client.chercherParNom", query="select c from Client c where c.nom = :nom"),
	@NamedQuery(name="client.chercherParPrenom", query="select c from Client c where c.prenom = :prenom"),
	@NamedQuery(name="client.supprimerToutClients", query="delete from Client")
})
public class Client {
	//attributs
	@Column(name = "Nom")
	private String nom;
	@Column(name = "Prenom")
	private String prenom;
	@Id
	private String identifiant;
	@Column(name = "Login")
	private String login;
	@Column(name = "MDP")
	private String mdp;
	/*@ManyToMany
	@JoinTable(
		name="RES_CH",
		joinColumns=@JoinColumn(name="ID_RES", referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="ID_CH", referencedColumnName="ID")
	)*/
	private ArrayList<Compte> comptesList;
	
	//accesseurs
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public ArrayList<Compte> getComptesList(){
		return this.comptesList; 
	}
	
	public void setComptesList(ArrayList<Compte> comptesList) {
		this.comptesList = comptesList;
	}

	//constructeurs
	public Client(){}
	
	public Client(String nom, String prenom, String id, String login, String mdp){
		setNom(nom);
		setPrenom(prenom);
		setIdentifiant(id);
		setLogin(login);
		setMdp(mdp);
		this.comptesList = new ArrayList<Compte>();
	}

	@Override
	public String toString() {
		return String.format("Client: id=%s, login=%s, nom=%s, prenom=%s, comptes-courants:%d, comptes-epargnes:%d", identifiant, login, nom, prenom, getNbComptesCourants(), getNbComptesEpargnes());
	}
	
	//methodes
	public Double calculerAvoirGlobal(){
		Iterator<Compte> it = comptesList.iterator();
		Double avoir = 0.0;
		
		while (it.hasNext()){
			avoir += it.next().getSolde();
		}
		
		return avoir;
	}
	
	public Integer getNbComptesCourants(){
		try{
			Integer nb = 0;
			Iterator<Compte> it = comptesList.iterator();
		
			while (it.hasNext()){
				if (it.next().getClass().equals(CompteCourant.class)){
					nb++;
				}
			}
			return nb;
		}catch(NullPointerException e){
			return 0;
		}
	}
	
	public Integer getNbComptesEpargnes(){
		try{
			Integer nb = 0;
			Iterator<Compte> it = comptesList.iterator();

			while (it.hasNext()){
				if (it.next().getClass().equals(CompteEpargne.class)){
					nb++;
				}
			}
			return nb;
		}catch(NullPointerException e){
			return 0;
		}
		
	}
	
	public void creerCompte(Compte compte){
		this.comptesList.add(compte);
	}
	
	public void supprimerCompte(Compte compte){
		this.comptesList.remove(compte);
	}
	
	public void supprimerCompte(Integer key){
		this.comptesList.remove(key);
	}
	
	public Compte retournerCompte(String numero) throws CompteNonTrouveException{
		Iterator<Compte> it = comptesList.iterator();
		Compte compte;
		
		while (it.hasNext()){
			compte = it.next();
			if (compte.getNumero().equals(numero)){
				return compte;
			}
		}
		
		throw new CompteNonTrouveException("Compte inexistant!");
	}
	
	public void supprimerCompte(String numero){
		try {
			supprimerCompte(retournerCompte(numero));			
		}catch(CompteNonTrouveException e){
			System.out.println(e);
		}
	}
}
