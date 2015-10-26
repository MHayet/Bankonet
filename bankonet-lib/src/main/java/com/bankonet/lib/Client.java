package com.bankonet.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	@ManyToMany
	@JoinTable(
		name="clientcourant",
		joinColumns = @JoinColumn(name="IdentifiantClient", referencedColumnName="Identifiant"),
		inverseJoinColumns = @JoinColumn(name="NumeroCompte", referencedColumnName="Numero")
	)
	private List<CompteCourant> comptesListCourant = new ArrayList<>();
	@ManyToMany
	@JoinTable(
		name="clientepargne",
		joinColumns = @JoinColumn(name="IdentifiantClient", referencedColumnName="Identifiant"),
		inverseJoinColumns = @JoinColumn(name="NumeroCompte", referencedColumnName="Numero")
	)
	private List<CompteEpargne> comptesListEpargne = new ArrayList<>();

	//constructeurs
	public Client(){}
	
	public Client(String nom, String prenom, String id, String login, String mdp){
		setNom(nom);
		setPrenom(prenom);
		setIdentifiant(id);
		setLogin(login);
		setMdp(mdp);
		setMdp(12);
	}

	@Override
	public String toString() {
		return String.format("Client: id=%s, login=%s, nom=%s, prenom=%s", identifiant, login, nom, prenom);
		//return String.format("Client: id=%s, login=%s, nom=%s, prenom=%s, comptes-courants:%d, comptes-epargnes:%d", identifiant, login, nom, prenom, getNbComptesCourants(), getNbComptesEpargnes());
	}
	
	//methodes
	public Double calculerAvoirGlobal(){
		Iterator<CompteCourant> itc = comptesListCourant.iterator();
		Iterator<CompteEpargne> ite = comptesListEpargne.iterator();
		Double avoir = 0.0;
		
		while (itc.hasNext()){
			avoir += itc.next().getSolde();
		}
		while (ite.hasNext()){
			avoir += ite.next().getSolde();
		}
		
		return avoir;
	}
	
	public Integer getNbComptesCourants(){
		return this.comptesListCourant.size();
	}
	
	public Integer getNbComptesEpargnes(){
		return this.comptesListEpargne.size();
	}
	
	public void creerCompte(Compte compte){
		if (compte.getClass().equals(CompteCourant.class)){
			this.comptesListCourant.add((CompteCourant)compte);
		}else{
			this.comptesListEpargne.add((CompteEpargne)compte);
		}
	}
	
	public void supprimerCompte(Compte compte){
		if (compte.getClass().equals(CompteCourant.class)){
			this.comptesListCourant.remove((CompteCourant)compte);
		}else{
			this.comptesListEpargne.remove((CompteEpargne)compte);
		}
	}
	
	public Compte retournerCompte(String numero) throws CompteNonTrouveException{
		Iterator<CompteCourant> itc = comptesListCourant.iterator();
		Iterator<CompteEpargne> ite = comptesListEpargne.iterator();
		Compte compte;
		
		while (itc.hasNext()){
			compte = itc.next();
			if (compte.getNumero().equals(numero)){
				return compte;
			}
		}
		while (ite.hasNext()){
			compte = ite.next();
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
		ArrayList<Compte> comptes = new ArrayList<>();
		comptes.addAll(comptesListCourant);
		comptes.addAll(comptesListEpargne);
		return comptes; 
	}
	
	public void setComptesList(ArrayList<Compte> comptesList) {
		for (Compte compte:comptesList){
			if(compte.getClass().equals(CompteCourant.class)){
				this.comptesListCourant.add((CompteCourant)compte);
			}else{
				this.comptesListEpargne.add((CompteEpargne)compte);
			}
		}		
	}
}
