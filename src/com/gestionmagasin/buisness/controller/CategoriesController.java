package com.gestionmagasin.buisness.controller;

import java.sql.*;
import java.util.ArrayList;

import com.gestionmagasin.beans.Categories;
import com.gestionmagasin.buisness.service.Singleton;

public class CategoriesController {

	private Connection con;
	private Statement st;
	private PreparedStatement prepst;
	private ResultSet rs;
	private boolean check;
	
	/*AJOUTER*/
	public boolean ajouterCategorie (String libelle) {
		
		con = Singleton.getInstance().getConnection();
		check = false;
		try {
			prepst = con.prepareStatement("insert into categories (libelle_categorie) values (?)");
			prepst.setString(1, libelle);

			int result = prepst.executeUpdate();
			
			if(result > 0)
				check = true;
				
			prepst.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	/*MODIFIER*/
	public boolean modifierCategorie(int id, String libelle) {
		
		con = Singleton.getInstance().getConnection();
		check = false;
		try {
			prepst = con.prepareStatement("update categories set libelle_categorie=? where id=?");
			prepst.setString(1, libelle);
			prepst.setInt(2, id);

			int result = prepst.executeUpdate();
			
			
			if(result > 0)
				check = true;
				
			
			prepst.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;

	}
	
	/*SUPPRIMER*/
	public boolean supprimerCategorie(int id) {
		
		con = Singleton.getInstance().getConnection();
		check = false;
		try {
			prepst = con.prepareStatement("delete from categories where id=?");
			prepst.setInt(1, id);
	
			int result = prepst.executeUpdate();
			
			if(result == 1)
				check = true;
				

			prepst.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	
	/*AFFICHER SELON ID*/
	public Categories getCategorie(int id) {
		con = Singleton.getInstance().getConnection();
		Categories cat = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from categories where id=" + id);
			
			if(!rs.next()) {
				cat = null;
			}
			else {
				rs.absolute(0);

				while(rs.next()) {
					cat = new Categories();
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
	
	/*AFFICHER TOUS*/
	public ArrayList<Categories> getCategorie() {
		con = Singleton.getInstance().getConnection();
		ArrayList<Categories> list = new ArrayList<Categories>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from categories");
			
			
			if(!rs.next()) {
				list = null;
			}
			else {
				rs.absolute(0);
				while(rs.next()) {
					Categories cat = new Categories();
					
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
