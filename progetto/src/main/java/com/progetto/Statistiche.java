package com.progetto;

import java.io.Serializable;

/**
 * La classe Statistiche contiene gli attributi su cui verranno effettuate le statistiche
 */
public class Statistiche implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected int Altitudine;
	protected double Frequenza;
	protected String Operatore;
	
	/**
	 * Costruttore della classe, richiede come argomenti i valori di Altitudine, Frequenza e Operatore
	 */
	public Statistiche (String Altitudine, String Frequenza, String Operatore) {
		this.Altitudine = Integer.parseInt(Altitudine);
		this.Frequenza = Double.parseDouble(Frequenza);
		this.Operatore = Operatore;
	}

	/**
	 * Somma le altitudini di tutti gli oggetti creati ad ogni nuovo oggetto creato. All'ultimo oggetto restituirà la somma di tutte le altitudini.
	 */
	public int sumAltitudine(int n) {
		n+=Altitudine;
		return n;
	}
}
