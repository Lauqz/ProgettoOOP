package com.progetto;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

@RestController
public class ChoiceController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/obj", method = RequestMethod.GET, produces="application/json")
    public String getObjects(HttpServletResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		ArrayList<Catasto> arr = new ArrayList<Catasto>();
		String allJson = new String();
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("oggetti.dat")));
			arr = (ArrayList<Catasto>) in.readObject();
			for (Catasto s : arr) {
				String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s);
				allJson += jsonString + "\n";
			}
			in.close();
		}
		catch (IOException e) {
			System.out.println("Errore  "+ e);
		}
		return allJson;
	}
	
	@RequestMapping(value = "/meta", method = RequestMethod.GET, produces="application/json")
    public String getMetadata(HttpServletResponse response) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.acceptJsonFormatVisitor(Catasto.class, visitor);
        JsonSchema schema = visitor.finalSchema();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
        
        return json;
    }
	
	@RequestMapping(value = "/stat", method = RequestMethod.GET, produces="application/json")
    public HashMap<String,String> getStats(@RequestParam int Att,
            @RequestParam int Stat) throws Exception {
		String data = null;
		String response = null;
		switch (Att) {
		case 0: ArrayList<String> temp0 = allOperatori();
		switch (Stat) {
		case 0: data = "Errore"; response = "Non e' possibile eseguire questa operazione su questo tipo di dato"; break;
		case 1: data = "Errore"; response = "Non e' possibile eseguire questa operazione su questo tipo di dato"; break;
		case 2: data = "Errore"; response = "Non e' possibile eseguire questa operazione su questo tipo di dato"; break;
		case 3: data = "Errore"; response = "Non e' possibile eseguire questa operazione su questo tipo di dato"; break;
		case 4: data = "Errore"; response = "Non e' possibile eseguire questa operazione su questo tipo di dato"; break;
		case 5:
			data = "Elementi unici";
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
		case 0: 
			data = "Average";
			int sum = 0;
		    for (int i=0; i< temp1.size(); i++) {
		          sum += temp1.get(i);
		    }
		    response = Integer.toString(sum / temp1.size());
		    break;
		case 1: 
			data="Min";
			int minIndex = Collections.min(temp1);
			response = Integer.toString(minIndex);
			break;
		case 2: 
			data="Max";
			int maxIndex = Collections.max(temp1);
			response = Integer.toString(maxIndex); 
			break;
		case 3: 
			data="Sum";
			int sum1 = 0;
			for (int i=0; i< temp1.size(); i++) {
		          sum1 += temp1.get(i);
		    }
		    response = Integer.toString(sum1);
			break;
		case 4: 
			data="DevStandard";
			ArrayList<Double> input2 = new ArrayList<Double>();
			double sum0 = 0;
		    double sum2 = 0;
		    double sd = 0;
		    for (int i = 0; i < temp1.size(); i++) {
		        sum0 = sum0 + temp1.get(i);
		    }
		    double mean = sum0 / temp1.size();
		    for (int i = 0; i < temp1.size(); i++) {
		        input2.add((Math.pow((temp1.get(i) - mean), 2)));
		    }
		    for (int i = 0; i < input2.size(); i++) {
		        sum2 = sum2 + input2.get(i);
		    }
		    double mean2 = sum2 / input2.size();

		    sd = Math.sqrt(mean2);
		    response = Double.toString(sd);
			break;
		case 5: 
			data="Conta elementi"; 
			response = Integer.toString(temp1.size());
			break;
		}
		break;
		
		case 2: ArrayList<Double> temp2 = allFrequenza();
		switch (Stat) {
		case 0: 
			data = "Average";
			double sum = 0;
		    for (int i=0; i< temp2.size(); i++) {
		          sum += temp2.get(i);
		    }
		    response = Double.toString(sum / temp2.size());
		    break;
		case 1:
			data="Min";
			double minIndex = Collections.min(temp2);
			response = Double.toString(minIndex);
			break;
		case 2: 
			data="Max";
			double maxIndex = Collections.max(temp2);
			response = Double.toString(maxIndex);
			break;
		case 3: 
			data="Sum";
			double sum1 = 0;
			for (int i=0; i< temp2.size(); i++) {
		          sum1 += temp2.get(i);
		    }
		    response = Double.toString(sum1);
			break;
		case 4: 
			data="DevStandard";
			ArrayList<Double> input2 = new ArrayList<Double>();
			double sum0 = 0;
		    double sum2 = 0;
		    double sd = 0;
		    for (int i = 0; i < temp2.size(); i++) {
		        sum0 = sum0 + temp2.get(i);
		    }
		    double mean = sum0 / temp2.size();
		    for (int i = 0; i < temp2.size(); i++) {
		        input2.add((Math.pow((temp2.get(i) - mean), 2)));
		    }
		    for (int i = 0; i < input2.size(); i++) {
		        sum2 = sum2 + input2.get(i);
		    }
		    double mean2 = sum2 / input2.size();

		    sd = Math.sqrt(mean2);
		    response = Double.toString(sd);
			break;
		case 5: 
			data="Conta elementi"; 
			response = Integer.toString(temp2.size());
			break;
		}
		break;
		}
		
		HashMap<String,String> prova = new HashMap<String,String>();
		prova.put(data, response);
		return prova;
	}
	
	
	@RequestMapping(value = "/filters", method = RequestMethod.POST, produces="application/json")
    public String getFilters(@RequestParam int Att,
            @RequestParam int Stat, @RequestParam String Num,
            @RequestParam int Logic,@RequestParam int Stat1,
            @RequestParam String Num1) throws Exception {
		ArrayList<Catasto> carr = new ArrayList<Catasto>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String allJson = new String();
		if (Num.equals("")) {
			HashMap<String, String> res = new HashMap<String, String>();
			System.out.println("errore");
			res.put("errore", "Inserimento non valido");
			return "errore";
		}
		    
		else {
			if (Num1.equals("")) 
			{
				
				switch (Att)
				{
				case 1:
					int value=Integer.parseInt(Num);
					carr=findmajminAltid(Stat,value);
				break;
				case 2:
					double value_1=Double.parseDouble(Num);
					carr=findmajminFreq(Stat,value_1);
			    break;
				}
			}
			else {
				switch (Att)
				{
				case 1:
					int value=Integer.parseInt(Num);
					carr=findmajminAltid(Stat,value);
					ArrayList<Catasto> carr1 = new ArrayList<Catasto>();
					switch (Stat1)
					{
					case 0:
						int value2=Integer.parseInt(Num);
						int value2_1=Integer.parseInt(Num1);
						carr=findmajminAltid(Stat,value2);
						carr1=findmajminAltid(Stat1, value2_1);
						if (Logic == 0) {
							carr.addAll(carr1);
						}
						else {
							if (Logic == 1)
							{
								carr = IntersectionAlt(carr,carr1);
								System.out.println("ciao1");
							}
								
						}
							
					break;
					case 1:
						int value3=Integer.parseInt(Num);
						int value3_1=Integer.parseInt(Num1);
						carr=findmajminAltid(Stat,value3);
						carr1=findmajminAltid(Stat1,value3_1);
						if (Logic == 0) {
							carr.addAll(carr1);
						}
						else {
							if (Logic == 1)
							{
								carr = IntersectionAlt(carr,carr1);
								System.out.println("ciao2");
							}
								
						}
				    break;
					}
				break;
				case 2:
					double value002=Double.parseDouble(Num);
					carr=findmajminFreq(Stat,value002);
					ArrayList<Catasto> carr2 = new ArrayList<Catasto>();
					switch (Stat1)
					{
					case 0:
						double value02=Double.parseDouble(Num);
						double value02_1=Double.parseDouble(Num1);
						carr=findmajminFreq(Stat,value02);
						carr1=findmajminFreq(Stat1, value02_1);
						if (Logic == 0) {
							carr.addAll(carr2);
						}
						else {
							if (Logic == 1)
							{
								carr = IntersectionFreq(carr,carr2);
								System.out.println("ciao1");
							}
								
						}
							
					break;
					case 1:
						double value03=Double.parseDouble(Num);
						double value03_1=Double.parseDouble(Num1);
						carr=findmajminFreq(Stat,value03);
						carr1=findmajminFreq(Stat1,value03_1);
						if (Logic == 0) {
							carr.addAll(carr2);
						}
						else {
							if (Logic == 1)
							{
								carr = IntersectionFreq(carr,carr2);
								System.out.println("ciao2");
							}
								
						}
				    break;
					}
				break;

				}
			}
				
		}
		
		for (Catasto s : carr) {
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s);
			allJson += jsonString + "\n";
		}
		return allJson;
		
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
	
	
	protected static ArrayList<Catasto> getCatasti()
	{
		ArrayList<Catasto> arr = new ArrayList<Catasto>();
		
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("oggetti.dat")));
			arr = (ArrayList<Catasto>) in.readObject();
			
		
		}
		catch (ClassNotFoundException e) {
			System.out.println("Errore  "+ e);
		} 
		catch (IOException e) {
			e.printStackTrace();
	}
		return arr;
	}
	
	
	protected static ArrayList<Catasto> findmajminAltid(int c,int lim)
	{
		ArrayList<Catasto> cat = new ArrayList<Catasto>();
		ArrayList<Catasto> filtered = new ArrayList<Catasto>();
		cat=getCatasti();
		switch (c)
		{
		case 0: 
			for (Catasto s : cat)
			{
				if (s.getAltitudine() <= lim) {
					filtered.add(s);
				}
			}
		break;
		case 1:
			for (Catasto s : cat)
			{
				if (s.getAltitudine() >= lim) {
					filtered.add(s);
				}
			}
		break;
		}
		return filtered;
	}
	
	
	protected static ArrayList<Catasto> findmajminFreq(int c,Double lim)
	{
		ArrayList<Catasto> cat = new ArrayList<Catasto>();
		ArrayList<Catasto> filtered = new ArrayList<Catasto>();
		cat=getCatasti();
		switch (c)
		{
		case 0: 
			for (Catasto s : cat)
			{
				if (s.getFrequenza() <= lim) {
					filtered.add(s);
				}
			}
		break;
		case 1:
			for (Catasto s : cat)
			{
				if (s.getFrequenza() >= lim) {
					filtered.add(s);
				}
			}
		break;
		}
		return filtered;
	}
	
	
	protected ArrayList<Catasto> IntersectionAlt(ArrayList<Catasto> list1, ArrayList<Catasto> list2)
	{
	ArrayList<Catasto> cat = new ArrayList<Catasto>();
		
	for(Catasto c1 : list1) {
		for(Catasto c2: list2) {
			if(ugualeAlt(c1, c2)) {
				boolean flag = false;
				for (Catasto s: cat) {
					if (ugualeAlt(s, c1))
						flag = true;
				}
				if (!flag)
					cat.add(c1);
			}
		}
	}
	return cat;	
	}
	
	
	protected ArrayList<Catasto> IntersectionFreq(ArrayList<Catasto> list1, ArrayList<Catasto> list2)
	{
	ArrayList<Catasto> cat = new ArrayList<Catasto>();
	
		
	for(Catasto c1 : list1) {
		for(Catasto c2: list2) {
			if(ugualeFreq(c1, c2)) {
				boolean flag = false;
				for (Catasto s: cat) {
					if (ugualeFreq(s, c1))
						flag = true;
				}
				if (!flag)
					cat.add(c1);
			}
		}
	}
	return cat;	
	}
	
	
	protected boolean ugualeAlt(Catasto c1, Catasto c2) {
		
		if(c1.getAltitudine() == c2.getAltitudine()) {
			return true;							
		}				
		return false;
	}
	
	
	protected boolean ugualeFreq(Catasto c1, Catasto c2) {
		
		if(c1.getFrequenza() == c2.getFrequenza()) {
			return true;						
		}		
		return false;
	}
}
