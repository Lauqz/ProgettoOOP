package com.progetto;

import java.util.ArrayList;

public class Catasto extends Statistiche {
	private String ID;
	private String Tipo;
	private String Ubicazione;
	private String Provincia;
	private String Latitudine;
	private String Longitudine;
	private int Channel;
	private String Progetto;
	private String ERP;
	
	public Catasto (ArrayList<String> parse) {
		super(parse.get(7),parse.get(9),parse.get(0));
		ID = parse.get(1);
		Tipo = parse.get(2);
		Ubicazione = parse.get(3);
		Provincia = parse.get(4);
		Latitudine = parse.get(5);
		Longitudine = parse.get(6);
		Channel = Integer.parseInt(parse.get(8));
		Progetto = parse.get(10);
		ERP = parse.get(11);
	}
	

}
