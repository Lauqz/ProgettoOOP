package com.progetto;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	public String getOperatore(){
		return Operatore;
	}
	
	@JsonIgnore
	public String getID(){
		return ID;
	}
	
	@JsonIgnore
	public String getTipo(){
		return Tipo;
	}
	
	@JsonIgnore
	public String getUbicazione(){
		return Ubicazione;
	}
	
	@JsonIgnore
	public String getProvincia(){
		return Provincia;
	}
	
	@JsonIgnore
	public String getLatitudine(){
		return Latitudine;
	}
	
	@JsonIgnore
	public String getLongitudine(){
		return Longitudine;
	}
	
	@JsonIgnore
	public int getAltitudine(){
		return Altitudine;
	}
	
	@JsonIgnore
	public String getChannel(){
		return Channel;
	}
	
	@JsonIgnore
	public Double getFrequenza(){
		return Frequenza;
	}
	
	@JsonIgnore
	public String getProgetto(){
		return Progetto;
	}
	
	@JsonIgnore
	public String getERP(){
		return ERP;
	}
	
	@JsonIgnore
	public boolean uguale(Catasto c)
	{
		if (this.Operatore == c.getOperatore() && this.ID == c.getID() && this.Tipo == c.getTipo() && this.Ubicazione == c.getUbicazione() && this.Provincia == c.getProvincia() && this.Latitudine == c.getLatitudine() && this.Longitudine == c.getLongitudine() && this.Frequenza == c.getFrequenza() && this.Channel == c.getChannel() && this.Altitudine == c.getAltitudine() && this.Progetto == c.getProgetto() && this.ERP == c.getERP() ) {
			return true;
		}
		else {
			
			return false;
		}
	}
}
