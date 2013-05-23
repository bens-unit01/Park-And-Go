package com.parkAndGo.network.modele;

public class Server {

	private String nomServeur, connectionProtocol;
	private int numPort;
	private Login serverLogin;
	// constructeurs

	public Server() {
		super();
	}

	
	
	// getteurs et setteurs

	public Server(String nomServeur, String connectionProtocol, int numPort,
			Login serverLogin) {
		super();
		this.nomServeur = nomServeur; // url ou adresse ip du serveur 
		this.connectionProtocol = connectionProtocol;
		this.numPort = numPort;
		this.serverLogin = serverLogin;
	}



	public String getNomServeur() {
		return nomServeur;
	}

	public void setNomServeur(String nomServeur) {
		this.nomServeur = nomServeur;
	}

	public int getNumPort() {
		return numPort;
	}

	public void setNumPort(int numPort) {
		this.numPort = numPort;
	}
    // getteurs et setteurs
	
	public class Login{
		
		private String userName, password;
		// 

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
		
		//

		public Login() {
			super();
		}

		public Login(String userName, String password) {
			super();
			this.userName = userName;
			this.password = password;
		}
		
		
	}
	
}
