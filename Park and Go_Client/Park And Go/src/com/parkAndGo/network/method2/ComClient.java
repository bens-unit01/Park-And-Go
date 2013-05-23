package com.parkAndGo.network.method2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.parkAndGo.network.Config;

import android.util.Log;
import android.view.View;

public class ComClient {
	
	
	// --
	
	public static String rpc(String commande){
		
		// le code de cette méthode est inspiré du site
		// http://android-er.blogspot.in/2011/01/simple-communication-using.html
		
		
			    String returnValue ="";
			    Socket socket = null;
			    DataOutputStream dataOutputStream = null;
			    DataInputStream dataInputStream = null;
			    
			    Config conf = Config.getInstance();
			    try {
			    //-----### debug	
			     Log.d("tag1", "ComClient - rpc()-  1 ok ");	
			    String adIp = conf.getServer().getNomServeur();
			    //-----### debug	
			     Log.d("tag1", "ComClient - rpc()-  2 ip: "+adIp);
			    
			     socket = new Socket(adIp, 8888);
			     
			
			     dataOutputStream = new DataOutputStream(socket.getOutputStream());
			     

			     
			     dataInputStream = new DataInputStream(socket.getInputStream());
			     
			     //---------------------------
			     // envoi
			     dataOutputStream.writeUTF(commande);
			     // reception 
			    returnValue = dataInputStream.readUTF();
			    
				// ###################
				// ----------debug
				Log.d("tag1", "ComClient  rpc() - returnValue:" + returnValue);

			     
			    } catch (UnknownHostException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
			    finally{
			     if (socket != null){
			      try {
			       socket.close();
			      } catch (IOException e) {
			       // TODO Auto-generated catch block
			       e.printStackTrace();
			      }
			     }

			     if (dataOutputStream != null){
			      try {
			       dataOutputStream.close();
			      } catch (IOException e) {
			       // TODO Auto-generated catch block
			       e.printStackTrace();
			      }
			     }

			     if (dataInputStream != null){
			      try {
			       dataInputStream.close();
			      } catch (IOException e) {
			       // TODO Auto-generated catch block
			       e.printStackTrace();
			      }
			     }
			    }
			    
			     // debug 
			     Log.d("tag1", "ComClient 3 ok ");
		
		return returnValue;
	}

}
