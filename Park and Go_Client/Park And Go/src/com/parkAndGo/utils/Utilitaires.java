package com.parkAndGo.utils;

import java.text.DecimalFormat;
import java.util.Calendar;

import com.parkAndGo.modele.Time;

import android.R.string;
import android.util.Log;

public class Utilitaires {
	public static final int HEURE = 1;
	public static final int MINUTE = 0;

	// cette methode retourne l'heure actuelle sous le format String
	public static String getHeureDebutToString() {

		Calendar calendar = Calendar.getInstance();
		int heure = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		String stringHeure = format1(heure);
		String stringMinute = format1(minute);
		return stringHeure + ":" + stringMinute;
	}

	private static String format1(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	// cette methode calcule l'heure de fin du stationnement, retourne l'heure de fin sous forme String
	public static String getHeureFinToString(int heureInput, int minuteInput) {
	
		Calendar calendar = Calendar.getInstance();
		int varHeure = calendar.get(Calendar.HOUR_OF_DAY);
		int varMinute = calendar.get(Calendar.MINUTE);
		
		varMinute+=minuteInput;
		if(varMinute>=60){
			varMinute = varMinute-60;
			varHeure++;
			varHeure+=heureInput;
		}else{
			varHeure+=heureInput;
		}
		String stringHeure = format1(varHeure);
		String stringMinute = format1(varMinute);
		return stringHeure + ":" + stringMinute;
		//return stringHeure + ":" + stringMinute+" "+ampmToString(calendar.get(Calendar.AM_PM));
	}
	private static String ampmToString(int calendarAmPm){
		String stringAmPm = null;
		if(calendarAmPm == Calendar.AM)
		{
			stringAmPm = "am";
		}else{
			stringAmPm = "pm";
		}
		return stringAmPm;
	}

	public static String getDate(){
		Calendar calendar = Calendar.getInstance();
		String stringJour = String.valueOf(calendar.DAY_OF_MONTH);
		String stringAnnee = String.valueOf(calendar.YEAR);
		int mois = calendar.MONTH;
		String stringMois = null;
		
		 switch (mois) {
         case 1:  stringMois = "JAN";
                  break;
         case 2:  stringMois = "FÉV";
                  break;
         case 3:  stringMois = "MAR";
                  break;
         case 4:  stringMois = "AVR";
                  break;
         case 5:  stringMois = "MAI";
                  break;
         case 6:  stringMois = "JUN";
                  break;
         case 7:  stringMois = "JUL";
                  break;
         case 8:  stringMois = "AOÛ";
                  break;
         case 9:  stringMois = "SEP";
                  break;
         case 10: stringMois = "OCT";
                  break;
         case 11: stringMois = "NOV";
                  break;
         case 12: stringMois = "DÉC";
                  break;
         default: stringMois = "Err";
                  break;
     }
		 
		 
		 return stringJour+" "+stringMois+" "+stringAnnee;
		 
	}
	
	// cette méthode calcule le cout de stationnement 
	public static String calculerCoutToString(int periodeEnMinute){
		DecimalFormat df = new DecimalFormat("#.##");
		double tarifHoraire = ReglesDAfaires.TARIF_HORAIRE_STATIONNEMENT;
		double cout = (tarifHoraire/60)*((double)periodeEnMinute);
		
        return df.format(cout);
        
	}
	public static double calculerCoutToDouble(int periodeEnMinute){
		double tarifHoraire = ReglesDAfaires.TARIF_HORAIRE_STATIONNEMENT;
		double cout = (tarifHoraire/60)*((double)periodeEnMinute);
		
        return cout;
        
	}

	// cette méthode règle la vitesse de la boucle du thread de l'affichage de la progressBar de la classe ProlongerActivity.java
	public static int calculerTempsAttente(Time heureDebut, Time heureFin) {
		// TODO Auto-generated method stub
		
	int heureDebutEnMinute = (heureDebut.getHeure()*60)+heureDebut.getMinute();
	int heureFinEnMinute = (heureFin.getHeure()*60)+heureFin.getMinute();
	int periodeEnSeconde = (heureFinEnMinute-heureDebutEnMinute)*60;
	
	//debug 
	Log.d("tag1", "Utilitaies -calculTempsAttente:"
			+ String.valueOf(heureDebutEnMinute)+"-"+String.valueOf(heureFinEnMinute)+"-"+String.valueOf(periodeEnSeconde));
	// fin debug
	
//		Calendar calendar = Calendar.getInstance();
//		int tempsSystemeEnMinute = (calendar.get(Calendar.HOUR_OF_DAY))*60+calendar.get(Calendar.MINUTE);  // (heure en mode 24 heures * 60) + minutes
//		int periodeEcouleEnMinute = tempsSystemeEnMinute - tempsDebut;
		//calendar.

//		//debug 
//		Log.d("tag1", "Utilitaies -returnValue:"
//				+ String.valueOf(returnValue));
		// fin debug
		return periodeEnSeconde;
	}

	public static int getHeureFin(int heureInput, int minuteInput, int mode) {
		// TODO Auto-generated method stub
		
		Calendar calendar = Calendar.getInstance();
		int varHeure = calendar.get(Calendar.HOUR_OF_DAY);
		int varMinute = calendar.get(Calendar.MINUTE);
		
		varMinute+=minuteInput;
		if(varMinute>=60){
			varMinute = varMinute-60;
			varHeure++;
			varHeure+=heureInput;
		}else{
			varHeure+=heureInput;
		}
		
		if(mode == HEURE){
			return varHeure;
		}else{
			return varMinute;
		}
	}

	public static String calculerTempsRestantToString(Time heureFin) {
		// TODO Auto-generated method stub
	
		Calendar calendar = Calendar.getInstance();
		int varHeure = calendar.get(Calendar.HOUR_OF_DAY);
		int varMinute = calendar.get(Calendar.MINUTE);
		int varSeconde = calendar.get(Calendar.SECOND);
		
		varSeconde = ((varHeure*60)+ varMinute)*60+varSeconde;  // heure actuelle en seconde
		int heureFinSeconde =((heureFin.getHeure()*60)+heureFin.getMinute())*60;
		varSeconde = heureFinSeconde-varSeconde; // periode d'attente en seconde 
		//conversion periode d'attente en hh:mm:ss
		varHeure = varSeconde/3600;
		varMinute = (varSeconde%3600)/60;
		varSeconde = (varSeconde%3600)%60;
		
		return  String.format("%02d:%02d:%02d", varHeure, varMinute, varSeconde);
	}
	
	// cette méthode retourne le temps restant en seconde
	public static int calculerTempsRestantToInt(Time heureFin) {
		// TODO Auto-generated method stub
	
		Calendar calendar = Calendar.getInstance();
		int varHeure = calendar.get(Calendar.HOUR_OF_DAY);
		int varMinute = calendar.get(Calendar.MINUTE);
		int varSeconde = calendar.get(Calendar.SECOND);
		
		varSeconde = ((varHeure*60)+ varMinute)*60+varSeconde;  // heure actuelle en seconde
		int heureFinSeconde =((heureFin.getHeure()*60)+heureFin.getMinute())*60;
		varSeconde = heureFinSeconde-varSeconde; // periode d'attente en seconde 
		
		return varSeconde;
	}

}
