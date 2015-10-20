package com.bankonet.dao.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;

public class BankonetClientMySQL implements BankonetClientFactory {

	@Override
	public void setClients(ArrayList<Client> clients) {
		if (!clients.isEmpty()){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankonet","Conseiller","conseiller");
				Statement statement = connection.createStatement();				
				statement.executeUpdate("DELETE FROM CLIENT");
				
				String requete = "INSERT INTO CLIENT(NOM,PRENOM,IDENTIFIANT,LOGIN,MDP) VALUES ";
				String requete2 = "INSERT INTO CLIENTCOMPTE(IDENTIFIANTCLIENT,NUMEROCOMPTE) VALUES ";
				for (Client client:clients){
					requete += "(\"" + client.getNom() + "\",\"" + client.getPrenom() + "\",\"" + client.getIdentifiant()
							+ "\",\"" + client.getLogin() + "\",\"" + client.getMdp() + "\"),";
					for (Compte compte:client.getComptesList()){
						requete2 += "(\"" + client.getIdentifiant() + "\",\"" + compte.getNumero() + "\"),";
					}
				}
				
				requete = requete.substring(0,requete.length()-1);
				requete += ";";				
				statement.executeUpdate(requete);
				
				requete2 = requete2.substring(0,requete2.length()-1);
				requete2 += ";";
				statement.executeUpdate(requete2);
				
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
	public ArrayList<Client> getClients() {
		ArrayList<Client> clients = new ArrayList<>(); 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankonet","Conseiller","conseiller");
			Statement statement = connection.createStatement();

			ResultSet resultats = statement.executeQuery("SELECT * FROM CLIENT");
			while(resultats.next()) {
				clients.add(new Client(resultats.getString("NOM"), resultats.getString("PRENOM")
						, resultats.getString("IDENTIFIANT"), resultats.getString("LOGIN")
						, resultats.getString("MDP")));
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clients;
	}

}
