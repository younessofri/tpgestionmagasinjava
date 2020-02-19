package com.gestionmagasin.buisness.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {

	private static Singleton instance = new Singleton();
	private Connection connexion;
	private String dbUrl = "jdbc:mysql://localhost:3306/magasin";
	private String dbUser = "root";
	private String dbPw = "";

	private Singleton() {
		if (connexion == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connexion = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Singleton getInstance() {
		return instance;
	}

	public Connection getConnection() {
		return connexion;
	}

}
