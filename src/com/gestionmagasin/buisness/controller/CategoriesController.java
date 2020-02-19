package com.gestionmagasin.buisness.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.gestionmagasin.beans.Categories;
import com.gestionmagasin.buisness.service.Singleton;

public class CategoriesController {

	private Connection con;
	private Statement st;
	private PreparedStatement prepst;
	private ResultSet rs;
	
	public boolean ajouterCategorie (String libelle) {
		
		con = Singleton.getInstance().getConnection();
		boolean check = false;
		try {
			st = con.createStatement();
			int result = st.executeUpdate("insert into categories (libelle_categorie) values ('" + libelle +"')");
			
			
			if(result > 0)
				check = true;
				
				
			rs.close();
			st.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
		
	}
	
	public void modifierCategorie(int id) {
		
	}
	
	public void supprimerCategorie(int id) {
		
	}
	
	public Categories getCategorie(int id) {
		con = Singleton.getInstance().getConnection();
		Categories cat = new Categories();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from Categories where id=" + id);
			
			
			if(!rs.next()) {
				cat = null;
			}
			else {
				while(rs.next()) {
					cat.setId(rs.getInt("id"));
					cat.setLibelleCategorie(rs.getString("libelle_categorie"));
				}
			}
			
			rs.close();
			st.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cat;
	}
	
	public ArrayList<Categories> getCategorie() {
		con = Singleton.getInstance().getConnection();
		ArrayList<Categories> list = new ArrayList<Categories>();
		Categories cat = new Categories();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from Categories");
			
			
			if(!rs.next()) {
				list = null;
			}
			else {
				while(rs.next()) {
					cat.setId(rs.getInt("id"));
					cat.setLibelleCategorie(rs.getString("libelle_categorie"));
					
					list.add(cat);
				}
			}

			rs.close();
			st.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;	
	}
	
}
