package com.parkAndGo.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parkAndGo.modele.Session;
import com.parkAndGo.utils.Operations;

public class IdentificationActivity extends Activity {
	
	private EditText txtUtilisateur,txtMotPasse;
	private TextView lblUtilisateur,lblPas;
	private Button butOuvrir;
	private static Context context = null;
	// singleton 
	public static Context getInstance(){
	 return context;
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_identification);
        setWidgets();
        setListeners();
    }

    private void setListeners() {
		
    	butOuvrir.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "Verification d'information", Toast.LENGTH_SHORT).show();
			// appel de la méthode pour verifier le nom d'utilisateur et le mot de passe et établit la connexion au serveur
			Operations objOperations = new Operations();
			int etatRetour = objOperations.login(txtUtilisateur.getText().toString(), txtMotPasse.getText().toString(), IdentificationActivity.this); 
			
			
			//################
			//----------debug 
			Integer wrapEtat = new Integer(etatRetour);
			Log.d("tag1","Identification   "+wrapEtat.toString());
			// ----------- end debug 
			
			
			if(Operations.SUCCESS == etatRetour){
					afficherEcranPaiement(objOperations.getSession());
			}else{
				if(Operations.CONNECTION_ERROR == etatRetour){
					Toast.makeText(getApplicationContext(), "probleme de connexion !!", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "nom d'utilisateur ou mot de passe incorrect !!", Toast.LENGTH_SHORT).show();
				}
			}
			}

		});
		
	}
    
    private void afficherEcranPaiement(Session session){
    	
    	Intent intent = new Intent(this,PaiementActivity.class);
    	intent.putExtra("key_session",session);
		startActivity(intent);
    	
    }

	private void setWidgets() {
	
		lblUtilisateur=(TextView) findViewById(R.id.lblUtil);
		lblPas=(TextView) findViewById(R.id.lblMotPasse);
		txtUtilisateur=(EditText) findViewById(R.id.txtNomUtil);
		txtMotPasse=(EditText) findViewById(R.id.txtMotPass);
		butOuvrir=(Button) findViewById(R.id.btOuvrirSess);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_identification, menu);
        return true;
    }
	
}
