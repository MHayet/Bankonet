package com.bankonet.dao.compte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteEpargne;

public class BankonetCompteMySQL implements BankonetCompteFactory {

	@Override
	public void setComptes(ArrayList<Compte> comptes) {
		if (!comptes.isEmpty()){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankonet","Conseiller","conseiller");
				Statement statement = connection.createStatement();
	
				statement.executeUpdate("DELETE FROM COMPTE");
				
				String requete = "INSERT INTO COMPTE(NUMERO,INTITULE,SOLDE,DECOUVERTMAXIMUM,TAUXINTERET) VALUES ";
				for (Compte compte:comptes){
					if(compte instanceof CompteCourant){
						requete += "(\"" + compte.getNumero() + "\",\"" + compte.getIntitule() + "\"," + compte.getSolde().toString()
								+ "," + ((CompteCourant)compte).getMontantDecouvertAutorise().toString() + ",0),";
					}else{
						requete += "(\"" + compte.getNumero() + "\",\"" + compte.getIntitule() + "\"," + compte.getSolde().toString()
								+ ",0,"+ ((CompteEpargne)compte).getTauxInteret().toString() +"),";					
					}
				}
				
				requete = requete.substring(0,requete.length()-1);
				requete += ";";
				
				statement.executeUpdate(requete);
				connection.close();
	
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Compte> getComptes(Client client) {
		ArrayList<Compte> comptes = new ArrayList<>(); 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankonet","Conseiller","conseiller");
			Statement statement = connection.createStatement();

			ResultSet resultats = statement.executeQuery("SELECT * FROM COMPTE");
			while(resultats.next()) {
				if(resultats.getFloat("TauxInteret") == 0){
					comptes.add(new CompteCourant(resultats.getString("NUMERO"), resultats.getString("INTITULE")
						, resultats.getDouble("SOLDE"), resultats.getDouble("DECOUVERTMAXIMUM")));
				}else{
					comptes.add(new CompteEpargne(resultats.getString("NUMERO"), resultats.getString("INTITULE")
							, resultats.getDouble("SOLDE"), resultats.getDouble("TAUXINTERET")));
				}
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comptes;
	}

}
