package com.parkAndGo.gui;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parkAndGo.modele.Session;
import com.parkAndGo.modele.Time;
import com.parkAndGo.utils.ReglesDAfaires;
import com.parkAndGo.utils.Utilitaires;

public class PaiementActivity extends Activity {

	private TextView lblNumeroDePlace, lblHeureFin, lblTarif, txtHeureFin,
			txtTarif, txtDate, lblCout, txtCout, lblDateEtHeure, txtHeureDebut,
			lblHeureDebut, txtEtat;
	private EditText txtNumeroDePlace;
	private Button btnAnnuler, btnConfirmer;
	private TimePicker timePicker1;
	private Session session; // attribut référençant la session en cours
	private Calendar calendar; // attribut pour la manipulation de la date et
								// l'heure

	// handler utilisé dans refresh affichage
	// private Handler handler;
	private static boolean flag = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paiement);
		setWidgets();
		setListeners();
		getSession(); // récupération de la session en cours
		initTimePicker(); // initialisation du timePicker

	}

	private void getSession() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		session = (Session) intent.getSerializableExtra("key_session");

		// ################
		// ----------debug
		Double wrapDouble = new Double(session.getSolde());
		Log.d("tag1", "Paiement solde:  " + wrapDouble.toString());
		// ----------- end debug

	}

	private void setWidgets() {
		lblNumeroDePlace = (TextView) findViewById(R.id.lblNumeroDePlace);
		txtNumeroDePlace = (EditText) findViewById(R.id.txtNumeroDePlace);
		lblHeureFin = (TextView) findViewById(R.id.lblHeureFin);
		txtHeureFin = (TextView) findViewById(R.id.txtHeureFin);
		lblTarif = (TextView) findViewById(R.id.lblTarif);
		txtTarif = (TextView) findViewById(R.id.txtTarif);
		lblCout = (TextView) findViewById(R.id.lblCout);
		txtCout = (TextView) findViewById(R.id.txtCout);
		txtDate = (TextView) findViewById(R.id.txtDateEtHeure);
		lblDateEtHeure = (TextView) findViewById(R.id.lblDateEtHeure);
		txtHeureDebut = (TextView) findViewById(R.id.txtHeureDebut);
		lblHeureDebut = (TextView) findViewById(R.id.lblHeureDebut);
		txtEtat = (TextView) findViewById(R.id.txtEtat);
		btnAnnuler = (Button) findViewById(R.id.btnAnnuler);
		btnConfirmer = (Button) findViewById(R.id.btnConfirmer);
		timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
		// handler = new Handler(); // initiation du handler pour la methode
		// refreshAffichage()

	}

	private void setListeners() {
		btnAnnuler.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				viderEcran();
			}

		});

		btnConfirmer.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				afficherEcranProlonger(session);

			}

		});

		timePicker1
				.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

					public void onTimeChanged(TimePicker view, int hourOfDay,
							int minute) {
						// TODO Auto-generated method stub

						// txtHeureFin.setText("--- 1");
						// txtDureeDeStationnement.setText("---  2");
						// txtCout.setText("--- 3");
						// txtDateEtHeure.setText("--- 4");
						// txtHeureDebut.setText("--- 5");
						// txtEtat.setText("--- 6");
						int heureInput = timePicker1.getCurrentHour();
						int minuteInput = timePicker1.getCurrentMinute();
						int periodeEnMinute = (heureInput * 60) + minuteInput;
						int nbreHeureMax = ReglesDAfaires.NOMBRE_HEURE_MAX_STATIONNEMENT;
						String symbolMonetaire = ReglesDAfaires.SYMBOLE_MONETAIRE;

						if (((heureInput >= nbreHeureMax) && (minuteInput > 0))
								|| (heureInput > nbreHeureMax)) {
							timePicker1.setCurrentHour(0);
							timePicker1.setCurrentMinute(0);
							txtEtat.setText("  			la periode maximale est "
									+ String.valueOf(nbreHeureMax) + " heures");
						} else {
							txtEtat.setText("");
						}
						txtHeureDebut.setText(Utilitaires.getHeureDebutToString());
						txtHeureFin.setText(Utilitaires.getHeureFinToString(heureInput,
								minuteInput));
						txtTarif.setText(String
								.valueOf(ReglesDAfaires.TARIF_HORAIRE_STATIONNEMENT)
								+ " "
								+ String.valueOf(symbolMonetaire)
								+ "/heure");
						txtCout.setText(Utilitaires
								.calculerCoutToString(periodeEnMinute)
								+ " "
								+ symbolMonetaire);
						txtDate.setText(Utilitaires.getDate());

						Log.d("tag1", "paiement-timePicker heureDebut"
								+ Utilitaires.getHeureDebutToString());

					}
				});

	}// fin setListeners()

	private void afficherEcranProlonger(Session session) {
		// variables locales 
		int heureInput = timePicker1.getCurrentHour();
		int minuteInput = timePicker1.getCurrentMinute();
		int periodeEnMinute = (heureInput * 60) + minuteInput;
		Time heureDebut = new Time();
		Time heureFin = new Time();
		Calendar calendar = Calendar.getInstance();
		
		// on verifie si le solde est suffisant
		if ((session.getSolde()
				- Utilitaires.calculerCoutToDouble(periodeEnMinute) < 0)) { // solde
																			// insufisant
			txtEtat.setText("Solde insufisant, votre solde actuel: "
					+ session.getSolde() + " "
					+ ReglesDAfaires.SYMBOLE_MONETAIRE);

		} else {
			Toast.makeText(getApplicationContext(), "En cours de paiement",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, ProlongerActivity.class);
			// l'objet session prend tous les details de la session
				// 1- nouveau solde
			double nouvSolde = session.getSolde()-Utilitaires.calculerCoutToDouble(periodeEnMinute);
			session.setSolde(nouvSolde);
			
			// ################
			// ----------debug
			Double wrapDouble = new Double(session.getSolde());
			Log.d("tag1", "PaiementActivity - afficherEcranProlonger -nouveau solde:  "+wrapDouble);
				// 2- heure de début
			heureDebut.setHeure(calendar.get(Calendar.HOUR_OF_DAY));
			heureDebut.setMinute(calendar.get(Calendar.MINUTE));
			session.setHeureDebut(heureDebut);
				// 3- heure de fin
			heureFin.setHeure(Utilitaires.getHeureFin(heureInput,minuteInput , Utilitaires.HEURE));
			heureFin.setMinute(Utilitaires.getHeureFin(heureInput,minuteInput, Utilitaires.MINUTE));
			session.setHeureFin(heureFin);
				// 4- code de l'emplacement 
			session.setNumPlace(txtNumeroDePlace.getText().toString());
			// passer la session à l'intent
			intent.putExtra("key_session", session);
			startActivity(intent);
		}
	}

	// ------------ MENU --------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_paiement, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_historique:
			Toast.makeText(PaiementActivity.this, "historique",
					Toast.LENGTH_SHORT).show();
			afficherEcranHistorique();
			return true;
		case R.id.menu_logout:
			Toast.makeText(PaiementActivity.this, "se déconnecter",
					Toast.LENGTH_SHORT).show();
			finish();
			return true;
		case R.id.menu_settings:
			Toast.makeText(PaiementActivity.this, "settings",
					Toast.LENGTH_SHORT).show();
			return true;

		}
		return false;
	}

	private void afficherEcranHistorique() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, HistoriqueActivity.class);
		startActivity(intent);

	}

	public void viderEcran() {
		// txtHeureFin.setText("");
		// txtTarif.setText("");
		// txtCout.setText("");
		// txtDate.setText("");
		// txtHeureDebut.setText("");
		// txtEtat.setText("");
		timePicker1.setCurrentHour(0);
		timePicker1.setCurrentMinute(0);
	}

	// initialisation du timePicker

	public void initTimePicker() {

		timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

		// final Calendar c = Calendar.getInstance();
		// hour = c.get(Calendar.HOUR_OF_DAY);
		// minute = c.get(Calendar.MINUTE);
		//

		// set current time into timepicker
		timePicker1.setIs24HourView(true); // pour enlever la partie am/pm
		timePicker1.setClickable(true);
		timePicker1.setCurrentHour(0);
		timePicker1.setCurrentMinute(0);

	}
}
