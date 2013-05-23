package com.parkAndGo.modele;

import java.util.*;

public class Historique {
	

    
    
    private ArrayList<Session> listeSession;
	
	// constructeurs 
	public Historique(){
		listeSession = new ArrayList<Session>();
	}

        
        
        
     // getteur et setteur
    public ArrayList<Session> getListeSession() {
        return listeSession;
    }

    public void setListeSession(ArrayList<Session> listeSession) {
        this.listeSession = listeSession;
    }
	
	
	
	
	
	// methodes  ----------------
	
	public void ajouterSession(Session nouvSession) {//throws SessionDejaPresenteException {
		
           if(!this.verifierDuplicata(nouvSession)){ 
            
            this.listeSession.add(nouvSession);
           }
//           }else{
//               throw new SessionDejaPresenteException(nouvSession.getCode(),nouvSession.getNom(), 
//                       nouvSession.getPrenom(), "cette session existe déja dans le historique");
//        
//            }
	}
	
	
	
	// methode de verification 
	public boolean verifierDuplicata(Session session){
            
        Iterator iterateur = listeSession.iterator();
        Session sessionCourante;
        while (iterateur.hasNext()) {
        	sessionCourante = (Session) iterateur.next();
            if (session.equals(sessionCourante))
            	return true;
        }
        return false;

	}

        
        
        public void afficherContenuHistorique(){
		// declaration et instanciation de l'iterateur
		Iterator iterateur = this.listeSession.iterator();
                Session session;
                
                // parcour et affichage de la ArrayList
                while(iterateur.hasNext()){
		
                  session = (Session)iterateur.next();
                 
                  System.out.println(session);
                }
        }


}
