/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

/**
 *class Server
 * @author "Messaoud BENSALEM"
 * @version 1.0  2012-12-24 
 */
import gui.GUIPandG;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Handler;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.*;
import persistance.DAOPandG;
import utils.Interperter;
import utils.Services;


public class ComServer {
    public static boolean listenerSwitch = true; // pour démarrer ou arrêter le listener
   
    public static GUIPandG refGui =null; // reference vers le GUI
   
    
 public static void main(String[] args){
 
     
     // lencement du service listener
 //    listener(args);

  //Interperter.execute("login;user1;1111");
     
  

//        Runnable runnable = new Runnable() {
//                public void run() {
 //                  listener();	
//                }
//        };
//        new Thread(runnable).start();
     
 //  Interperter.execute("saveTrx;p07;user1;23:00;23:30;49.2");
 //  Interperter.execute("saveTrx;p01;user1;21:28;23:30;49.2");

   
   // lancement du GUI
   
//          java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                refGui = new GUIPandG();
//                refGui.setVisible(true);
//                sync = true;
//               
//            }
//        });
 
  refGui = new GUIPandG();
  refGui.setVisible(true);  
  listener();
  System.out.println("Server -- fin execution ---");
 
 }
 public static void listener(){

     // le code de cette méthode est inspiré du site
     // http://android-er.blogspot.ca/2011/01/implement-simple-socket-server-in.html
     
     
  display("Serveur en cours d'execution ..."); 
  ServerSocket serverSocket = null;
  Socket socket = null;
  DataInputStream dataInputStream = null;
  DataOutputStream dataOutputStream = null;
  String commande, err;
  
  try {
   serverSocket = new ServerSocket(8888);
   display("Port : 8888");
  } catch (IOException e) {
 
   e.printStackTrace();
  }
  
  while(true){
   try {
    socket = serverSocket.accept();
    dataInputStream = new DataInputStream(socket.getInputStream());
    dataOutputStream = new DataOutputStream(socket.getOutputStream());
 
    display("IP du client :"+ socket.getInetAddress());
    
    
    //--------------------------
    // reception 
    commande = dataInputStream.readUTF();
    err = Interperter.execute(commande);  // lancer l'execution de la commande 
    
    // -------- debug 
    System.out.println("ComServer - listener() -- commande: " + commande+" -- err: "+err);
    
    // envoi 
    dataOutputStream.writeUTF(err);
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
   finally{
    if( socket!= null){
     try {
      socket.close();
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
    }
    
    if( dataInputStream!= null){
     try {
      dataInputStream.close();
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
    }
    
    if( dataOutputStream!= null){
     try {
      dataOutputStream.close();
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
    }
   }
  }
 }

    public static void display(String message) {
           refGui.ajouterContenuMonitoring(message);
    }
}
