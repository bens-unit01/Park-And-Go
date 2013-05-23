package com.parkAndGo.network;

import com.parkAndGo.modele.Session;
import com.parkAndGo.network.modele.User;

import android.content.Context;

/**
 * 
 * @author user1
 *
 */

/* dans ce projet Connectable est implémenté par bdeb.network.Methode1 et utilisé 
 * par bdeb.utils.Operations
 */

public interface Connectable {
	
	static  final int TRUE = 1, FALSE= 0;
	static final int SUCCESS=1, CONNECTION_ERROR = -1, LOGIN_FAILED_ERROR=-2;
	
	// méthodes de l'interface
	
	/**
	 * verifie si le code de l'emplacement entré en parametre est valide
	 * @param codePlace represente le code de l'emplacement de stationnement
	 * @return TRUE si le code est valide, FALSE sinon, CONNECTION_ERROR s'il
	 *  		y a un problem de connexion
	 */
	int  isExist(String codePlace);
	/**
	 * 
	 * @param userName le nom d'utilisateur pour son profil sur le site de ParkAndGo
	 * @param password le mot de passe de l'utilisateur
	 * @return le solde si la connexion réussi,  LOGIN_FAILED_ERROR si le login échoue, mauvais 
	 * 			password ou userName
	 */
	/**
	 * 
	 * @param user l'objet user contient le nom d'utilisateur et le mot de passe, il contient le solde 
	 * de l'utilisateur si la connexion se déroule normalement
	 * @param config spécifie le type de configuration qu'on va utiliser pour nous connecter 
	 * @param context 
	 * @return SUCCESS si tout se déroule normalement, CONNECTION_ERROR en cas d'erreur de connection,
	 * LOGIN_FAILED si le nom d'utilisateur ou le mot de passe n'est pas bon.
	 */
	int login(User user, int config, Context context);
	
	/**
	 * cette methode met-à-jour le solde dans le profil de l'utilsateur "userName"
	 * @param userName   le nom d'utilisateur du profil concerné
	 * @param nouveauSolde  le solde calculer après une transaction
	 * @return SUCCESS si l'operation réussi, CONNECTION_ERROR sinon
	 */
	int setSolde(String userName,double nouveauSolde);
	
	/**
	 * enregistre la session comme étant payée sur le serveur
	 * @param session cette objet contient toutes les informations concerant la session, 
	 * 			voir bdeb.model.Session
	 * @return  le numéro de la transaction au niveau du serveur, retourne CONNECTION_ERROR 
	 * 			s'il y a un problème lors de l'enregistrement de la transaction
	 */
	int enregistrerTransaction(Session session);
	
	
}
