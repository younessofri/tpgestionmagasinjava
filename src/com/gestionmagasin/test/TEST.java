package com.gestionmagasin.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.gestionmagasin.beans.Categories;
import com.gestionmagasin.buisness.service.Singleton;

public class TEST {

	
	public static void main(String[] args) {
		Connection con;
		Statement st;
		PreparedStatement prepst;
		ResultSet rs;
		
		con = Singleton.getInstance().getConnection();
		List<Categories> list = new ArrayList<Categories>();
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
		
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getLibelleCategorie());

			
			
		}
		
	}

}
