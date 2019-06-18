package com.progetto;

public class Statistiche {
	
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
