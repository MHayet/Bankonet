package com.bankonet.dao.compte.jpa;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.bankonet.dao.compte.jpa.BankonetCompteFactory;
import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteEpargne;

public class BankonetCompteJPA implements BankonetCompteFactory {
	//attributs
	private EntityManagerFactory emf;

	//construteurs
	public BankonetCompteJPA(EntityManagerFactory emf) {
		setEmf(emf);
	}

	@Override
	public void setComptes(ArrayList<Compte> comptes) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		for (Compte compte:comptes){
			if (compte.getClass().equals(CompteCourant.class)){
				em.persist((CompteCourant)compte);				
			}else{
				em.persist((CompteEpargne)compte);
			}
		}
		et.commit();
		em.close();
	}

	@Override
	public ArrayList<Compte> getComptes(Client client) {
		EntityManager em = emf.createEntityManager();
		ArrayList<Compte> comptes = new ArrayList<>();
		
		Query queryc = em.createQuery("SELECT c FROM CompteCourant c, clientcourant cc WHERE c.numero = cc.NumeroCompte and cc.IdentifiantClient = :id", CompteCourant.class);
		queryc.setParameter("id", client.getIdentifiant());
		Query querye = em.createQuery("SELECT c FROM CompteEpargne c, clientepargne cc WHERE c.numero = cc.NumeroCompte and cc.IdentifiantClient = :id", CompteEpargne.class);
		querye.setParameter("id", client.getIdentifiant());
		
		comptes.addAll(queryc.getResultList());
		comptes.addAll(querye.getResultList());
				
		em.close();
		
		return comptes;
	}
	
	//accesseurs
	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

}
