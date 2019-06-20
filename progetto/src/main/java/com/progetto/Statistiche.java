package com.progetto;

import java.io.Serializable;


public class Statistiche implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected int Altitudine;
	protected double Frequenza;
	protected String Operatore;
	
	public Statistiche (String Altitudine, String Frequenza, String Operatore) {
		this.Altitudine = Integer.parseInt(Altitudine);
		this.Frequenza = Double.parseDouble(Frequenza);
		this.Operatore = Operatore;
	}

	public int sumAltitudine(int n) {
		n+=Altitudine;
		return n;
	}
}
