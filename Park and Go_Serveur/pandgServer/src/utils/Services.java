/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import control.ComServer;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import javax.persistence.*;
import model.*;
import org.hibernate.exception.ConstraintViolationException;
import persistance.DAOPandG;

/**
 *class Services
 * @author "Messaoud BENSALEM"
 * @version 1.0  2012-12-27 
 */
public class Services {

    

    
      // attributs 
        private User user;
        // definition des erreurs 
    	public static final int TRUE = 1, FALSE = 0;
	public static final int SUCCESS = 1, CONNECTION_ERROR = -1, LOGIN_FAILED_ERROR = -2, DB_ERROR = -3;
        
        // constucteur 
        
        // constucteur 

        public Services() {
            user = new User();
        }
        
        // getteur setteurs 

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
   // méthodes 
    public static List rechercherListeTransactions(int selectedIndex, String keyword) {
        String query = "";
        if (0 == selectedIndex) { // rechercher les transactions par codeEmplacement 
            query = "from Transaction t where t.codeEmplacement = '"+keyword+"'";
                    
        } else { // rechercher les transaction par username
            query = "from Transaction t where t.username = '"+keyword+"'";;
        }
        return hqlQuery(query);
    }

    private static List hqlQuery(String queryString) {
        List returnValue = null;
        DAOPandG.getEntityManagerfactory("pandgServerPU");
        EntityManager em = DAOPandG.creerEntityManager();

       try{ 
           
       
            Query query = em.createQuery(queryString);
            returnValue = (List) query.getResultList();  
           
            
       }catch(NoResultException e){
         e.printStackTrace();
         returnValue = null;
           
       }catch(PersistenceException e){
          e.printStackTrace();
          returnValue = null;
          // monitoring 
           
          
       }
       
        DAOPandG.fermerEntityManager();
        
        return returnValue;
    }
   
    public static User rechercherUtilisateur(String userName){
    
         User returnValue=null; 
         DAOPandG.getEntityManagerfactory("pandgServerPU");
        EntityManager em = DAOPandG.creerEntityManager();

       try{ 
           
       
            Query query = em.createQuery("from User u where u.username = '"+userName+"'");
            returnValue = (User)query.getSingleResult();   
          
            
            
       }catch(NoResultException e){
          
           returnValue = null;  
           // monitoring
           ComServer.display("Mauvais login - username: "+userName);
           
       }catch(PersistenceException e){
          
          returnValue = null; 

       }


       DAOPandG.fermerEntityManager();
       
       //----## debug 
        System.out.println("Services -- rechercherUtilisateur - user:"+returnValue);
        return returnValue;
    }

        // cette methode verifie si l'utilisateur dont le nom d'utilisateur "username" et le mot de passe "password" 
        // existe, si oui, le retour sera SUCCESS et  l'attribut this.user.solde va contenir le solde 
        // sinon un code d'erreur est retourné 
    
    
    public  int login(String userName, String password){
        int returnValue = CONNECTION_ERROR;
        
        DAOPandG.getEntityManagerfactory("pandgServerPU");
        EntityManager em = DAOPandG.creerEntityManager();

       try{ 
           
       
            Query query = em.createQuery("from User u where u.username = '"+userName+"' and u.password = '"+password+"'");
            user = (User)query.getSingleResult();   // à ce niveau l'objet user contient le solde dans user.solde
            returnValue = SUCCESS;    // login réussi 
            // monitoring 
            ComServer.display("Login réussi - username: "+userName);
            
       }catch(NoResultException e){
           user.setSolde(-1);
           returnValue = LOGIN_FAILED_ERROR; // erreur de login 
           // monitoring
           ComServer.display("Mauvais login - username: "+userName);
           
       }catch(PersistenceException e){
           user.setSolde(-1);
          returnValue = CONNECTION_ERROR;  // erreur du sgbd
          // monitoring 
           ComServer.display("Probleme de connexion - username: "+userName);
          
       }


       DAOPandG.fermerEntityManager();
        System.out.println("Services -- login ok !!");
        return returnValue;
    }

    
    // cette methode enregistre une nouvelle transaction dans la BD
    
     public static int enregistrerUtilisateur(User user){
    
        int returnValue = CONNECTION_ERROR;
        
        DAOPandG.getEntityManagerfactory("pandgServerPU");
        EntityManager em = DAOPandG.creerEntityManager();
        EntityTransaction et = DAOPandG.creerEntityManagerTransactionel();
        
          try{  
        
             et.begin();
             em.persist(user);
             returnValue = SUCCESS;
             
             
         }catch(PersistenceException e){
          returnValue = CONNECTION_ERROR;
           // monitoring 
         
      }catch(Exception e){
          returnValue = CONNECTION_ERROR;
       
      }
       try{ 
         DAOPandG.fermerEntityManagerTransactionel();
         DAOPandG.fermerEntityManager();
       }catch(org.hibernate.exception.ConstraintViolationException e){ 
           returnValue = DB_ERROR;
       }catch(IllegalStateException e){
           returnValue = DB_ERROR;
       }catch(Exception e){
            returnValue = DB_ERROR;
       }
         
        
    return returnValue;
        
    }
    
    public static int supprimerUtilisateur(String userName) {
       int returnValue = CONNECTION_ERROR;
        
        DAOPandG.getEntityManagerfactory("pandgServerPU");
        EntityManager em = DAOPandG.creerEntityManager();
        EntityTransaction et = DAOPandG.creerEntityManagerTransactionel();
        
          try{  
        
             et.begin();
             User user = em.find(User.class, userName);
             if(user != null){
                em.remove(user);
                returnValue = SUCCESS;
             }else{
                 returnValue = DB_ERROR;
             }
             
             
      }catch(PersistenceException e){
          returnValue = CONNECTION_ERROR;
             
         
      }catch(Exception e){
          returnValue = CONNECTION_ERROR;
             
       
      }
       try{ 
         DAOPandG.fermerEntityManagerTransactionel();
         DAOPandG.fermerEntityManager();
       }catch(org.hibernate.exception.ConstraintViolationException e){ 
           returnValue = DB_ERROR;
          
       }catch(IllegalStateException e){
           returnValue = DB_ERROR;
      
       }catch(Exception e){
            returnValue = DB_ERROR;
          
       }
         
        
    return returnValue;
    }
    
    public static int updateUtilisateur(User user) {
        int returnValue = CONNECTION_ERROR;
        
        DAOPandG.getEntityManagerfactory("pandgServerPU");
        EntityManager em = DAOPandG.creerEntityManager();
        EntityTransaction et = DAOPandG.creerEntityManagerTransactionel();
        
          try{  
        
             et.begin();
             User userNew = em.find(User.class, user.getUserName());
             if(userNew != null){
                userNew.setNom(user.getNom());
                userNew.setPrenom(user.getPrenom());
                userNew.setPassword(user.getPassword());
                userNew.setSolde(user.getSolde());
                userNew.setNumTelephone(user.getNumTelephone());
                 returnValue = SUCCESS;
             }else{
                    returnValue = DB_ERROR;
             }  
             
             
      }catch(PersistenceException e){
          returnValue = CONNECTION_ERROR;
              System.out.println(" 1"+e.toString());
         
      }catch(Exception e){
          returnValue = CONNECTION_ERROR;
              System.out.println(" 2"+e.toString());
       
      }
       try{ 
         DAOPandG.fermerEntityManagerTransactionel();
         DAOPandG.fermerEntityManager();
       }catch(org.hibernate.exception.ConstraintViolationException e){ 
           returnValue = DB_ERROR;
        
       }catch(IllegalStateException e){
           returnValue = DB_ERROR;
         
       }catch(Exception e){
            returnValue = DB_ERROR;
         
       }
         
        
    return returnValue;
    }

    
    public  int enregistrerTransaction(Transaction transaction){
        
        int returnValue = CONNECTION_ERROR;
        
        DAOPandG.getEntityManagerfactory("pandgServerPU");
        EntityManager em = DAOPandG.creerEntityManager();
        EntityTransaction et = DAOPandG.creerEntityManagerTransactionel();
      try{  
        
        et.begin();
        
        
        
       
        //recherche de l'utilisateur 
        User tempUser = em.find(User.class, user.getUserName());
        if( tempUser != null ){
            // changement du solde de l'utilisateur
            
            tempUser.setSolde(user.getSolde());
            // enregistrement de la transaction 
            Transaction trx = new Transaction();
            trx = transaction;
            trx.setUser(tempUser);
            
            if(null == tempUser.getHistorique()){ // on verifie si c'est la premiere transaction de l'utilisateur 
                tempUser.setHistorique(new HashSet<Transaction>());
            }
            
          
            tempUser.getHistorique().add(trx);
            em.persist(tempUser);
            returnValue= SUCCESS;
            // monitoring 
           ComServer.display("Transaction enregistrée - username: "+user.getUserName());
        
            
                
        }else{ // utilisateur inexistant 
            
            returnValue = CONNECTION_ERROR;
             // monitoring 
           ComServer.display("Problème de connexion - username: "+user.getUserName());
        }
        
      }catch(PersistenceException e){
          returnValue = CONNECTION_ERROR;
           // monitoring 
           ComServer.display("Transaction enregistrée - username: "+user.getUserName());
      }catch(Exception e){
          returnValue = CONNECTION_ERROR;
           // monitoring 
         ComServer.display("Transaction enregistrée - username: "+user.getUserName());
      }
        
        
        
     
      
     
         DAOPandG.fermerEntityManagerTransactionel();
         DAOPandG.fermerEntityManager();


        
      
    return returnValue;
    }
    
    
    // cette methode retourne la liste de toutes les transactions
     public static List lireListeTransactions() {
         List<Object> returnValue = null;
         
         DAOPandG.getEntityManagerfactory("pandgServerPU");
         EntityManager em = DAOPandG.creerEntityManager(); 
         
                Query query = em.createQuery("from Transaction ");
               returnValue = (List<Object>)query.getResultList(); 
               
             
        
         
              
         DAOPandG.fermerEntityManager();
         
         return returnValue;
        
    }

     
      public static List lireListeUtilisateurs() {
      return hqlQuery("from User u order by u.username");
    }
}
