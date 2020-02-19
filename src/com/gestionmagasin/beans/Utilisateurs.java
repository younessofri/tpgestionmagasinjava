package com.gestionmagasin.beans;

public class Utilisateurs {
	
	private int id;
	private String nomUtilisateur;
	private String prenomUtilisateur;
	private String loginUtilisateur;
	private String pwUtilisateur;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}
	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}
	public String getLoginUtilisateur() {
		return loginUtilisateur;
	}
	public void setLoginUtilisateur(String loginUtilisateur) {
		this.loginUtilisateur = loginUtilisateur;
	}
	public String getPwUtilisateur() {
		return pwUtilisateur;
	}
	public void setPwUtilisateur(String pwUtilisateur) {
		this.pwUtilisateur = pwUtilisateur;
	}
	
	public String getNomComplet() {
		return this.prenomUtilisateur + " " + this.nomUtilisateur;
	}
	
	
}
