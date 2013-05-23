package com.parkAndGo.network;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;

import com.parkAndGo.modele.Session;
import com.parkAndGo.network.method2.ComClient;
import com.parkAndGo.network.modele.Server;
import com.parkAndGo.network.modele.User;

public class Methode2 implements Connectable {
	
	// code des methodes à appeler
	 public static final String LOGIN = "login", SAVE_TRX = "saveTrx", SET_SOLDE = "setSolde";
	 
	 // constructeur 
	 public Methode2(){}

	public int isExist(String codePlace) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int login(User user, int config, Context context) {
		// TODO Auto-generated method stub
		
		
		int returnValue; // valeur de retour
		String commande;
		//#### debug
		Log.d("tag1", "Methode2  1");
		
		try {
			// envoi d'un paquet pour un appel de procedure à distance 
			// le format du paquet est comme suit 
			// "code_commande;userName;password"
			commande = LOGIN+";"+user.getUserName()+";"+user.getPassword();
			//#### debug
			Log.d("tag1", "Methode2  2");
			
			String err = ComClient.rpc(commande); // appel à distance de la méthode "login"
			
			//#### debug
			Log.d("tag1", "Methode2  3 err: "+err);
			String[] tabErr = err.split(";");
			// ###################
			// ----------debug
			Log.d("tag1", "Methode2  err:" + err);
			
			if(SUCCESS == Integer.parseInt(tabErr[0])){  // login réussi 
				returnValue = SUCCESS;
				user.setSolde(Double.parseDouble(tabErr[1])); // récupération du solde
			}else{
				if(LOGIN_FAILED_ERROR == Integer.parseInt(tabErr[0]))
				{
					returnValue = LOGIN_FAILED_ERROR;
				}else{
					
					returnValue = CONNECTION_ERROR;
				}
			}
			


			
		} catch (Exception e) {
			returnValue = CONNECTION_ERROR;
		} 

		return returnValue;
		
	}

	public int setSolde(String userName, double nouveauSolde) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int enregistrerTransaction(Session session) {
		// TODO Auto-generated method stub
		
		int returnValue = CONNECTION_ERROR;
		String commande;
		// préparation du paquet commande
		 // le format du paquet "commande" est : 
        // "code_commande;codeEmplacment;userName;heureDebut;heureFin;nouveauSolde" 
		commande = SAVE_TRX+";"+session.getNumPlace()+";"+session.getUserName()+";"+
        new Integer(session.getHeureDebut().getHeure()).toString()+":"+new Integer(session.getHeureDebut().getMinute()).toString()+";"+
        new Integer(session.getHeureFin().getHeure()).toString()+":"+new Integer(session.getHeureFin().getMinute()).toString()+";"+
        new Double(session.getSolde()).toString();
		
					// ###################
					// ----------debug
					Log.d("tag1", "Methode2 - enregistrerTransaction  commande: "+commande);
					
		// appel à distance de la méthode "SAVE_TRX" pour enregistrer la transaction 
		String err = ComClient.rpc(commande); 
		
					// ###################
					// ----------debug
					Log.d("tag1", "Methode2 - enregistrerTransaction  err: "+err);
					
		try{
			if(SUCCESS == Integer.parseInt(err)){
				returnValue = SUCCESS;
			}else{
				returnValue = CONNECTION_ERROR;
			}
		}catch(IllegalArgumentException e){
			returnValue = CONNECTION_ERROR;
		}	
		
		return returnValue;
	}

}
