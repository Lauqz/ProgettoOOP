package com.progetto;

import java.io.Serializable;
import java.util.ArrayList;

public class Catasto extends Statistiche implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String ID;
	private String Tipo;
	private String Ubicazione;
	private String Provincia;
	private String Latitudine;
	private String Longitudine;
	private String Channel;
	private String Progetto;
	private String ERP;
	
	public Catasto (ArrayList<String> parse) {
		super(parse.get(7), parse.get(9), parse.get(0));
		ID = parse.get(1);
		Tipo = parse.get(2);
		Ubicazione = parse.get(3);
		Provincia = parse.get(4);
		Latitudine = parse.get(5);
		Longitudine = parse.get(6);
		Channel = parse.get(8);
		Progetto = parse.get(10);
		ERP = parse.get(11);
	}
	
	@Override
	public String toString() {
		return "Oggetto" + ID + "," + Tipo + "," + Ubicazione + ERP;
	}
	
	public String getOperatore(){
		return Operatore;
	}
	
	public String getID(){
		return ID;
	}
	
	public String getTipo(){
		return Tipo;
	}
	
	public String getUbicazione(){
		return Ubicazione;
	}
	
	public String getProvincia(){
		return Provincia;
	}
		
	public String getLatitudine(){
		return Latitudine;
	}
	
	public String getLongitudine(){
		return Longitudine;
	}
	
	public int getAltitudine(){
		return Altitudine;
	}
	
	public String getChannel(){
		return Channel;
	}
	
	public Double getFrequenza(){
		return Frequenza;
	}
	
	public String getProgetto(){
		return Progetto;
	}
	
	public String getERP(){
		return ERP;
	}
}
