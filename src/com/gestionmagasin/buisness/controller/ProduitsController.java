package com.gestionmagasin.buisness.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gestionmagasin.beans.Produits;
import com.gestionmagasin.buisness.service.Singleton;

public class ProduitsController {
	
	private Connection con;
	private Statement st;
	private PreparedStatement prepst;
	private ResultSet rs;
	private boolean check;
	

	/*AJOUTER*/
	public boolean ajouterProduits (String libelle,int quantite,int id) {
		
		con = Singleton.getInstance().getConnection();
		boolean check = false;
		try {
			prepst = con.prepareStatement("insert into produits (libelle_produit,quantite_produit,id_cat) values (?,?,?)");
			prepst.setString(1, libelle);
			prepst.setInt(2, quantite);
			prepst.setInt(3, id);
	
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
	

	/*MODIFIER*/
	public boolean modifierProduits(int id, String libelle, int quantite, int idCat) {
		
		con = Singleton.getInstance().getConnection();
		boolean check = false;
		try {
			prepst = con.prepareStatement("update produits set libelle_produit=? , quantite_produit=?, id_cat=? where id=?");
			prepst.setString(1, libelle);
			prepst.setInt(2, quantite);
			prepst.setInt(3, idCat);
			prepst.setInt(4, id);

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
	public boolean supprimerProduits(int id)
	{
		con = Singleton.getInstance().getConnection();
		check = false;
		try {
			prepst = con.prepareStatement("delete from produits where id=?");
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
	
	/*AFFICHER TOUS*/
	public ArrayList<Produits> getProduits() {
		con = Singleton.getInstance().getConnection();
		ArrayList<Produits> list = new ArrayList<Produits>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from produits");
			
			if(!rs.next()) {
				list = null;
			}
			else {
				rs.absolute(0);
				while(rs.next()) {
					Produits produit = new Produits();
					
					produit.setId(rs.getInt("id"));
					produit.setLibelleProduit(rs.getString("libelle_produit"));
					produit.setQuantiteProduit(rs.getInt("quantite_produit"));
					produit.setIdCategorie(rs.getInt("id_cat"));
					list.add(produit);
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
