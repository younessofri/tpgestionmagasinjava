package com.gestionmagasin.buisness.controller;

import java.sql.*;

import com.gestionmagasin.beans.Utilisateurs;
import com.gestionmagasin.buisness.service.Singleton;

public class AuthentificationController {
	
	public Utilisateurs seConnecter(String login, String pw) {
		Connection con = Singleton.getInstance().getConnection();
		Utilisateurs user = new Utilisateurs();
		boolean exist = false;
		
		try {
			Statement st = con != null ? con.createStatement() : null ;
			
			ResultSet rs = st.executeQuery("select * from Utilisateurs where login_utilisateur='" + login +"'");
			
			while(rs.next())
			{
				if(pw.equals(rs.getString("pw_utilisateur")))
				{
					user.setId(rs.getInt("id"));
					user.setNomUtilisateur(rs.getString("nom_utilisateur"));
					user.setPrenomUtilisateur(rs.getString("prenom_utilisateur"));
					user.setLoginUtilisateur(rs.getString("login_utilisateur"));
					user.setPwUtilisateur(rs.getString("pw_utilisateur"));

					exist = true;
				}
			}
			
			rs.close();
			st.close();				
		}
		catch (SQLException e) {
				e.printStackTrace();
			}
		
		return user;
	}
}
