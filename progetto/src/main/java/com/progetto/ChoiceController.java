package com.progetto;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

@RestController
public class ChoiceController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/obj", method = RequestMethod.GET)
    public String getObjects(HttpServletResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		ArrayList<Catasto> arr = new ArrayList<Catasto>();
		String allJson = new String();
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("oggetti.dat")));
			arr = (ArrayList<Catasto>) in.readObject();
			for (Catasto s : arr) {
				String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s);
				allJson += jsonString;
			}
			in.close();
		}
		catch (IOException e) {
			System.out.println("Errore  "+ e);
		}
		return allJson;
	}
	
	@RequestMapping(value = "/meta", method = RequestMethod.GET)
    public String getMetadata(HttpServletResponse response) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.acceptJsonFormatVisitor(Catasto.class, visitor);
        JsonSchema schema = visitor.finalSchema();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
        
        return json;
    }
	
	@RequestMapping(value = "/stat", method = RequestMethod.GET)
    public String getStats(@RequestParam int Att,
            @RequestParam int Stat) throws Exception {
		String response = null;
		switch (Att) {
		case 0: ArrayList<String> temp0 = allOperatori();
		switch (Stat) {
		case 0: response = "Non e' possibile eseguire questa operazione su questo tipo di dato"; break;
		case 1: response = "Non e' possibile eseguire questa operazione su questo tipo di dato"; break;
		case 2: response = "Non e' possibile eseguire questa operazione su questo tipo di dato"; break;
		case 3: response = "Non e' possibile eseguire questa operazione su questo tipo di dato"; break;
		case 4: response = "Non e' possibile eseguire questa operazione su questo tipo di dato"; break;
		case 5:
			ArrayList<String> uniques = new ArrayList<String>();
			int c = 0;
			for (int i = 0; i < temp0.size(); i++) {
				for (int j = 0; j < temp0.size(); j++) {
					if (i == j) {}
					else {
						if (temp0.get(i).equals(temp0.get(j))) {
						c++;
						}
					}
			}
			if (c == 0) 
				uniques.add(temp0.get(i));
			c = 0;
		}
		response = uniques.toString();
		break;
		}
		break;
		
		case 1: ArrayList<Integer> temp1 = allAltitudine();
		switch (Stat) {
		case 0: response="pupu"; break;
		case 1: response="pupu"; break;
		case 2: response="pupu"; break;
		case 3: response="pupu"; break;
		case 4: response="pupu"; break;
		case 5: response="pupu"; break;
		}
		break;
		
		case 2: ArrayList<Double> temp2 = allFrequenza();
		switch (Stat) {
		case 0: response="pupu"; break;
		case 1: response="pupu"; break;
		case 2: response="pupu"; break;
		case 3: response="pupu"; break;
		case 4: response="pupu"; break;
		case 5: response="pupu"; break;
		}
		break;
		}
		
		return response;
	}
	
	
	protected static ArrayList<String> allOperatori(){
		ArrayList<Catasto> arr = new ArrayList<Catasto>();
		ArrayList<String> temp = new ArrayList<String>();
		
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("oggetti.dat")));
			arr = (ArrayList<Catasto>) in.readObject();
			for (Catasto s : arr) {
				temp.add(s.getOperatore());
			}
			in.close();
			
		}
		catch (ClassNotFoundException e) {
			System.out.println("Errore  "+ e);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	protected static ArrayList<Double> allFrequenza(){
		ArrayList<Catasto> arr = new ArrayList<Catasto>();
		ArrayList<Double> temp = new ArrayList<Double>();
		
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("oggetti.dat")));
			arr = (ArrayList<Catasto>) in.readObject();
			for (Catasto s : arr) {
				temp.add(s.getFrequenza());
			}
			in.close();
			
		}
		catch (ClassNotFoundException e) {
			System.out.println("Errore  "+ e);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
		}

	
	protected static ArrayList<Integer> allAltitudine(){
		ArrayList<Catasto> arr = new ArrayList<Catasto>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
	
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("oggetti.dat")));
			arr = (ArrayList<Catasto>) in.readObject();
			for (Catasto s : arr) {
				temp.add(s.getAltitudine());
			}
			in.close();
		
		}
		catch (ClassNotFoundException e) {
			System.out.println("Errore  "+ e);
		} 
		catch (IOException e) {
			e.printStackTrace();
	}
		return temp;
	}
}
