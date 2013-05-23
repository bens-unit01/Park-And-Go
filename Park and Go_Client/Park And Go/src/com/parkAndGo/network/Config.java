package com.parkAndGo.network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.DriverManager;
import java.util.Properties;

import android.database.SQLException;
import android.util.Log;

import com.parkAndGo.gui.IdentificationActivity;
import com.parkAndGo.network.modele.Server;


public class Config {

	// attribut

	public static final int SERVEUR1 = 1, SERVEUR2 = 2, SERVEUR3 = 3;
	
	// l'objet config est implementé avec le DP singleton 
	

	private Server server;
	private static Config refConfig = null;
	// constructeurs
	 private Config() {
		super();
	}
	
	 // 
	 public static Config getInstance(){
		 
		 //-----### debug	
	     Log.d("tag1", "Config - getInstance -  1");
		 
		 
		 if(null == refConfig){
			 refConfig = new Config();

				 //-----### debug	
			     Log.d("tag1", "Config - getInstance -  try 1");
			     
				Server server = createFromProp("properties.cfg");
				refConfig.setServer(server);
				
				
				//-----### debug	
			     Log.d("tag1", "Config - getInstance -  try 2 server:"+server.toString());
				

		 }
		 return refConfig;
	 }
 	
private static Server createFromProp  (String nomFichierProp){ //throws IOException, ClassNotFoundException, SQLException {
	Server returnValue = null;
    
    
    try {
    	
    	//-----### debug	
	     Log.d("tag1", "Config - createFromProp 1");
		BufferedReader br = new BufferedReader(new InputStreamReader(IdentificationActivity.getInstance().openFileInput(nomFichierProp))); // paramServeur.getNomServeur()

		// boucle de lecture du fichier
		// on recherche un User dans le fichier
		String[] tabString;
		String tmp = null;
		boolean trouve = false;
		
		//-----### debug	
	     Log.d("tag1", "Config - createFromProp 2");
		
		while(((tmp =br.readLine())!=null)&&(!trouve)){
			
			tabString = tmp.split(";");
			
			//-----### debug	
		     Log.d("tag1", "Config - createFromProp - 3 tabString0: "+tabString[0]+" tabString1: "+tabString[1]);
			
			if(("nomServeur").equals(tabString[0])){ 
				trouve = true;  // on a trouvé le nom d'utilisateur dans le fichier
				returnValue = new Server();
				returnValue.setNomServeur(tabString[1]);
				
				//#################
				// -------- debug 
				//Log.d("tag1","methode1  "+user.getUserName()+"  "+user.getPassword());
				//------------ end debug
			}
				
			
		}
		
		
		

	} catch (FileNotFoundException e) {
		returnValue = null;

		//-----### debug	
		
	     Log.d("tag1", "Config - createFromProp - catch 1");
	     refConfig = null;
		
		
	} catch (IOException e) {
		returnValue = null;

		//-----### debug	
	     Log.d("tag1", "Config - createFromProp - catch 2");
	     refConfig =null;
	} 


	return returnValue;
	}

//	public Config(int config) {
//
//		// lecture du fichier de configurations serveurs.cfg
//
//		// récupération des détails du serveur
//
//		//instanciation initialisation de l'attribut "serveur"
//		
//		server = new Server();
//		server.setNomServeur("profiles.cfg");
//
//	}

	// getteurs et setteurs
	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	// methodes de la classe

	// c'est le constructeur principal de la classe, il  utilise le fichier
	// serveurs.cfg pour
	// retourner un objet Config contenant tout les parametres nécessaire pour ce
	// connecter au seveur.
	
}
