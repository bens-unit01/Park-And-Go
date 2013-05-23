package com.parkAndGo.modele;

import java.io.Serializable;
import java.util.*;


// cette classe est le bean du programme Park and Go


public class Session implements Serializable {
	//attributs 
	
	private String userName, numPlace;
	private double solde;
	private Date date;
	private  Time heureDebut, heureFin;  
	// constructeurs
	
	
	
	public Session() {
		super();
//		heureDebut = new Time();
//		heureFin = new Time();
//		date = new Date();
	}
	
	
	public Session(String idUtilisateur, String numPlace, double solde,
			Date date, Time heureDebut, Time heureFin) {
		super();
		this.userName = idUtilisateur;
		this.numPlace = numPlace;
		this.solde = solde;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}


	// getteurs et setteurs 
	public String getUserName() {
		return userName;
	}

	public void setUserName(String idUtilisateur) {
		this.userName = idUtilisateur;
	}
	public String getNumPlace() {
		return numPlace;
	}
	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Time getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}
	
	
	
	
	

}
