package com.parkAndGo.gui;

import com.parkAndGo.modele.Session;
import com.parkAndGo.utils.Operations;
import com.parkAndGo.utils.Utilitaires;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProlongerActivity extends Activity {

	private Button btnProlonge;
	private TextView lblTempsRestant, txtTempsRestant;
	private ProgressBar prgBar;
	private Session session; // attribut referençant la session en cours
	private Handler handler; // handler pour le thread qui gere la progressBar
	private TextView txtTempsEcoule;
	private TextView txtHeureDebut;
	private TextView txtHeureFin;
	private static boolean flag = true; // pour la synchronisation du thread de
										// la progressBar avec l'application

	// private static int tempsAttente = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prolonger);
		setWidgets();
		setListeners();
		getSession(); // récupération de la session en cours
		refreshAffichage(); // gere l'affichage de la progressBar
		enregistrerTransaction();
	}

	private void enregistrerTransaction() {
		// TODO Auto-generated method stub
		Operations objOperations = new Operations();
		
		// ################
		// ----------debug
		Log.d("tag1", "ProlongerActivity - enregistrerTransaction ");
		
		if(objOperations.enregistrerTransaction(session, this) != Operations.SUCCESS)
		{
			flag = false; // pour arreter le thread de la progressBar

			Toast.makeText(getApplicationContext(), "Probleme de connexion ",
					Toast.LENGTH_SHORT).show();
		}
		
	}

	private void getSession() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		session = (Session) intent.getSerializableExtra("key_session");

		// ################
		// ----------debug
		Double wrapDouble = new Double(session.getSolde());
		Log.d("tag1", "Prolonger solde:  ");
		// ----------- end debug

	}

	private void setWidgets() {

		btnProlonge = (Button) findViewById(R.id.button1);
		txtTempsEcoule = (TextView) findViewById(R.id.txtTempsEcoule);
		txtTempsRestant = (TextView) findViewById(R.id.textView4);
		txtHeureDebut = (TextView) findViewById(R.id.textView6);
		txtHeureFin = (TextView) findViewById(R.id.textView8);
		prgBar = (ProgressBar) findViewById(R.id.progressBar1);
		prgBar.setProgress(0);
		handler = new Handler();

	}

	private void setListeners() {

		btnProlonge.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				flag = false; // pour stopper l'execution du thread de
								// refreshAffichage
				envoiDonnees();

			}

		});

		// thread qui gere l'affichage de la progressBar

	}

	public void refreshAffichage() {

		// affichage heure de début et de fin
				txtHeureDebut.setText(" "
						+ String.valueOf((session.getHeureDebut()).getHeure()) + ":"
						+ String.valueOf((session.getHeureDebut()).getMinute()));
				txtHeureFin.setText(" "
						+ String.valueOf((session.getHeureFin()).getHeure()) + ":"
						+ String.valueOf((session.getHeureFin()).getMinute()));

				Runnable runnable = new Runnable() {
					public void run() {
						int i = 0;
						final int tempsAttente = Utilitaires.calculerTempsAttente(
								session.getHeureDebut(), session.getHeureFin()); // temps d'attente  récupéré en seconde

						flag = true;
						
						while ((i < tempsAttente) && flag) {
							// on calcul le temps écoulé 
							final int tempsEcoule = (tempsAttente-Utilitaires.calculerTempsRestantToInt(session.getHeureFin()));
							try {
								// ---- debug
								Log.d("tag2",
										"thread en cours d'excution - tempsEcoule :"
												+ String.valueOf(tempsEcoule));
								// ---- fin debug
								Thread.sleep(1000); // temps d'attente en
																	// milliseconde
							} catch (InterruptedException e) {
								e.printStackTrace();
								Toast.makeText(ProlongerActivity.this,
										"erreur dans l'application, code: 001",
										Toast.LENGTH_SHORT).show();
							}
							handler.post(new Runnable() {
								public void run() {
									
									//debug 
									Log.d("tag2",
											"thread en cours d'excution - tempsAttente :"
													+ String.valueOf(tempsAttente));
									// fin debug
									final int graduation =tempsEcoule/(tempsAttente/100);
									
									if(0==(tempsEcoule%(tempsAttente/100))){
										prgBar.setProgress(graduation);
										txtTempsEcoule.setText("  "
											+ String.valueOf(tempsEcoule/(tempsAttente/100)) + "%");
									}
									if(100 == graduation){  // le temps est écoulé 
										flag = false;
									}else{
										txtTempsRestant.setText(Utilitaires
											.calculerTempsRestantToString(session.getHeureFin()));
									}
									
									//debug 
									Log.d("tag2", "thread en cours d'excution - tempsRestant:"
											+String.valueOf(Utilitaires
											.calculerTempsRestantToString(session.getHeureFin())));
									// fin debug

								}
							});

							i++;
						}

						// sortie
						//debug
						Log.d("tag1", "--- sortie du thread");
						// fin debug
					}
				};
				new Thread(runnable).start();
				
				
	}

	private void envoiDonnees() {
		Intent intent = new Intent(this, PaiementActivity.class);
		intent.putExtra("key_session", session);
		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_prolonger, menu);
		return true;
	}

}
