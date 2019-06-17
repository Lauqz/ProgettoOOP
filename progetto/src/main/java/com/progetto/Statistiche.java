package com.progetto;

public class Statistiche {
	
	private int Altitudine;
	private double Frequenza;
	private String Operatore;
	
	public Statistiche (String Altitudine, String Frequenza, String Operatore) {
		this.Altitudine = Integer.parseInt(Altitudine);
		this.Frequenza = Double.parseDouble(Frequenza);
		this.Operatore = Operatore;
	}
}
