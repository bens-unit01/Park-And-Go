package com.parkAndGo.modele;

import java.io.Serializable;

// cette classe a était créé pour être utilisée dans le bean Session

public class Time implements Serializable{

	
	private int heure,minute,ampm;

	public Time() {
		super();
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getAmpm() {
		return ampm;
	}

	public void setAmpm(int ampm) {
		this.ampm = ampm;
	}

	@Override
	public String toString() {
		return heure + ":" + minute ;
				
	}
	
	//
	
}
