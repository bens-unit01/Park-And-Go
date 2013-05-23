package com.parkAndGo.network.modele;

public class User {
	
	
	// attributs 
	
	private String userName, password;
	private double solde;
	
	// constructeurs 
	public User() {
		super();
	}
	
	
	// getteurs et setteurs

	public User(String userName, String password, double solde) {
		super();
		this.userName = userName;
		this.password = password;
		this.solde = solde;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	// redefinition de toString()

	@Override
	public String toString() {
		return userName + ";" + password+ ";" + solde;
	}
	
	

	
	
	
	

}
