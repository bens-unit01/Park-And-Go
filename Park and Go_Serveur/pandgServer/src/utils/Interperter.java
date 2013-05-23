/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import model.*;

/**
 *class Interperter
 * @author "Messaoud BENSALEM"
 * @version 1.0  2012-12-27 
 */
public class Interperter {
    
    public static final String LOGIN = "login", SAVE_TRX = "saveTrx", SET_SOLDE = "setSolde";
    private static final String[] commandes = {LOGIN, SAVE_TRX,SET_SOLDE}; // liste des commandes 
    
    // methodes 
    public static String execute(String commande){
        String returnValue = "";
        int choix = -1;
        String[] tabCom = commande.split(";");
        
        // décodage de la commande 
        
        if(tabCom[0].equals(LOGIN) || tabCom[0].equals(SAVE_TRX) || tabCom[0].equals(SET_SOLDE)){
            if(tabCom[0].equals(LOGIN)) choix =1;
            if(tabCom[0].equals(SAVE_TRX)) choix =2;
            if(tabCom[0].equals(SET_SOLDE)) choix =3;
            
        }else{
            choix = -1;
        }
        
        switch(choix){
        
            case 1: returnValue=login(tabCom[1], tabCom[2]);  //  tabCom[1] c'est le userName,  tabCom[2] c'est le password. Le format du paquet est comme suit:
                                                                // "code_commande;userName;password"
                break;   
            case 2  : returnValue = enregistrerTransaction (commande);// sauvegarde de la transaction, le paquet "commande" a le format suivant 
                                                        // "code_commande;codeEmplacment;userName;heureDebut;heureFin;nouveauSolde" 
                break;
            case 3  : // setSolde();
                break;    
            default : break;
        
        }
    
        return returnValue;
    }

    private static String login(String userName, String password) {
       Services service = new Services();
     // composition du paquet à retourner au client 
       // le format est le suivant "valeur de retour de login()"+";"+"valeur du solde"
       String returnValue = (new Integer(service.login(userName, password))).toString()+";";
       
       //------- debug 
        System.out.println("Interpreter - login-- returnValue: "+returnValue);
       returnValue += (new Double(service.getUser().getSolde())).toString();
      

       
       return returnValue;
       
       
    }

    private static String enregistrerTransaction(String commande) {
        
        // création de l'objet Transaction
        //------------------------
        // le format du paquet "commande" est : 
        // "code_commande;codeEmplacment;userName;heureDebut;heureFin;nouveauSolde" 
        
        Transaction objTrx = new Transaction();
        String[] tabCom = commande.split(";");
        objTrx.setCodeEmplacement(tabCom[1]);
        Calendar date = Calendar.getInstance();
        objTrx.setDate(date);
        // récupération de heureDebut et heureFin 
        // le format de l'heure est "heure:minutes" 
        int[] aujourdhui = {date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH)}; // ce tableau contient la date d'aujourd'hui [année, mois, jour]
        String[] tabHeureDebut = tabCom[3].split(":");  // heureDebut 
        Calendar heureDebut = Calendar.getInstance();
        heureDebut.set(aujourdhui[0], aujourdhui[1], aujourdhui[2], Integer.parseInt(tabHeureDebut[0]), Integer.parseInt(tabHeureDebut[1]));
        objTrx.setHeureDebut(new Time(heureDebut.getTime().getTime()));
        
        String[] tabHeureFin = tabCom[4].split(":");  // heureDebut 
        Calendar heureFin = Calendar.getInstance();
        heureFin.set(aujourdhui[0], aujourdhui[1], aujourdhui[2], Integer.parseInt(tabHeureFin[0]), Integer.parseInt(tabHeureFin[1]));
        objTrx.setHeureFin(new Time(heureFin.getTime().getTime()));
        
        // enregistrement de la transaction 
        Services svc = new Services();
        User objUser = new User();
        objUser.setUserName(tabCom[2]);
        objUser.setSolde(Double.parseDouble(tabCom[5]));
        svc.setUser(objUser);
        
       return (new Integer(svc.enregistrerTransaction(objTrx))).toString();
        
    }
    

}
