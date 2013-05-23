package com.parkAndGo.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.parkAndGo.modele.Session;
import com.parkAndGo.network.Config;
import com.parkAndGo.network.Connectable;
import com.parkAndGo.network.Methode2;
import com.parkAndGo.network.modele.User;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

public class Operations {

	// attributs

	// -- les constantes suivante sont utilisé au niveau du GUI pour connaitre
	// le déroulement
	// méthodes appelées
	public static final int TRUE = 1, FALSE = 0;
	public static final int SUCCESS = 1, CONNECTION_ERROR = -1,
			LOGIN_FAILED_ERROR = -2;

	private Session session; // attribut en lecture seul

	// constructeurs

	public Operations() {
		super();
	}

	// getteurs et setteurs

	public Session getSession() {
		return session;
	}

	// pas de setteur pour l'attribut session, attribut en lecture seul.
	// ---------------------------
	// methodes

	public int login(String userName, String password, Context context){

		Connectable objMethode2 = new Methode2(); // on utilise la 2eme méthode
													// pour nous connecter

		// préparation de l'objet user pour la connection
		User objUser = new User();
		objUser.setUserName(userName);
		objUser.setPassword(password);

		int err = objMethode2.login(objUser, Config.SERVEUR1, context); // connection
																		// au
		
		// ###################
		// ----------debug
		Integer wrapEtat = new Integer(err);
		Log.d("tag1", "Operations  " + wrapEtat.toString());
		// ----------- end debug 

		if (Connectable.SUCCESS == err) {
			// la connexion a réussi
			// une nouvelle session est créé
			// à ce niveau, l'attribut "solde" de l'objet objUser cotient déja
			// le solde

			session = new Session();
			session.setUserName(userName);
			session.setSolde(objUser.getSolde());
			

			// ###################
			// ----------debug
			Log.d("tag1", "Operations - login  solde:" + objUser.getSolde());
			// ----------- end debug 

			return SUCCESS;
		} else {
			if (Connectable.CONNECTION_ERROR == err) {
				// erreur de connexion
				return CONNECTION_ERROR;
			} else {
				// mot de passe ou nom d'utlisateur érroné
				return LOGIN_FAILED_ERROR;
			}
		}

	}

	// --------------------------------

	public int enregistrerTransaction(Session session, Context context) {

		Connectable objMethode2 = new Methode2(); 
		int returnValue = CONNECTION_ERROR;
		
		
		int err = objMethode2.enregistrerTransaction(session);
		
		if(Connectable.SUCCESS == err ){ // l'operation s'est terminé normalement
			returnValue=SUCCESS;
			
		}else{
			returnValue = CONNECTION_ERROR;
			
		}
		
		
		return returnValue;
	}

	public void enregistrerSession(Session session, Context context) {

	}

	public boolean verifierCodeEmplacement(int codeEmplacement) {
		return true;
	}

	

}
