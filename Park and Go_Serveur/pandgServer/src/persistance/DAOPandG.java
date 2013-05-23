/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *class DAOpandg
 * @author "Messaoud BENSALEM"
 * @version 1.0  2012-12-27 
 */
public class DAOPandG {
    
    
    
   private static EntityManagerFactory emf = null;
   private static  EntityManager em = null ;
   private static EntityTransaction et = null;
    
    
    
    public static EntityManagerFactory getEntityManagerfactory(String pu){
      if( null == emf){
          emf = Persistence.createEntityManagerFactory(pu);
      }
      return emf;
    }

    public static EntityManager creerEntityManager(){
                   

        return getInstanceEM();
         
    }

    public static void fermerEntityManager (){
        if (em != null) {
            em.close();
            em = null;
        }
           
    }

    public static EntityTransaction creerEntityManagerTransactionel(){
   
       
        if (null == et) {
         
            et = getInstanceEM().getTransaction();
         
        }
        
        return et;
    }

    public static void fermerEntityManagerTransactionel(){
       
       try{ 
            if (et != null) {
                et.commit();
                et = null;
            }
       }catch(Exception ex){
           et.rollback();
           System.out.println(" erreur de sauvegarde");
       }
         
    }

    
   // methodes utilitaires 
    private static EntityManager getInstanceEM(){
      try{
        if (null == em) {
           
           em =  emf.createEntityManager();
            
        }
      }catch(Exception ex){
          System.out.println("erreur de sauvegarde ");  
       }
      return em;
    }
    
    


}
