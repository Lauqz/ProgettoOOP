package com.progetto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@Controller
public class StreamingResponseController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSteamingFile() throws Exception {
    	
      File f = new File("t1.csv");
      
      if(!f.exists() && !f.isDirectory()){
    	  
		try {
			
			 URLConnection openConnection = new URL("https://www.dati.gov.it/api/3/action/package_show?id=537c1138-4c5f-41bb-aec8-862954b67b28").openConnection();
			 openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			 InputStream in = openConnection.getInputStream();
			
			 String data = "";
			 String line = "";
			 try {
			   InputStreamReader inR = new InputStreamReader(in);
			   BufferedReader buf = new BufferedReader(inR);
			  
			   while (( line = buf.readLine()) != null) {
				   data+=line;
				   System.out.println(line);
			   }
			   
			 } finally {
			   in.close();
			 }
			 
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data); 
			JSONObject objI = (JSONObject) (obj.get("result"));
			JSONArray objA = (JSONArray) (objI.get("resources"));
			
			for(Object o: objA){
			    if (o instanceof JSONObject) {
			        JSONObject o1 = (JSONObject)o; 
			        String format = (String)o1.get("format");
			        String urlA = (String)o1.get("url");
			        urlA = urlA.replaceAll("&amp;","&");
			        URL urlD = new URL (urlA);
			        System.out.println(format + " | " + urlD);
			        if(format.equals("csv")) {
			        	File fname = new File ("t1.csv");
			        	download(urlD, fname);
			        }
			    }
			}
			System.out.println( "OK" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
      else System.out.println("File presente, impossibile scaricare");
      
      try {
    	 Scanner var = new Scanner(new BufferedReader(new FileReader ("t1.csv")));
    	 ArrayList<Catasto> obj = new ArrayList<Catasto>();
    	 String data = var.nextLine();
    	 String data2 = null;
    	 String data3 = null;
    	 String full = null;

    	 while (var.hasNextLine()) {
    		 data = var.nextLine();
    		 data2 = var.nextLine();
    		 if (data2.endsWith("--") && (var.nextLine()).startsWith("V:")) {
    			 data2+=data3;
    		 }
    		 full = data + data2;
    		 
    		 Matcher m = Pattern.compile("\"[^\"]*\"").matcher(full);
    		 StringBuffer sb = new StringBuffer();
    		 while(m.find()) {
    		       m.appendReplacement(sb, m.group().replaceAll(",", "."));
    		 }
    		 m.appendTail(sb);

    		 full = sb.toString();
    		 full = full.replaceAll("\"","");
    		 System.out.println(full);
    		 
    		 ArrayList<String> parts = new ArrayList<String>();
    		 String[] parti = full.split(",");
    		 System.out.println(Arrays.toString(parti)); //prova stampa

    		 for (int i = 0; i<12; i++) {
    				 parts.add(parti[i]);
    		 }
   			 Catasto foo = new Catasto(parts);
    		 obj.add(foo);
    		 System.out.println(foo.toString());
    	}
    	 var.close();
    	 
    	 ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("oggetti.dat")));
   	  	 out.writeObject(obj);
   	  	 out.close();
      }
      catch (Exception e) {
    	  System.out.println("Errore di lettura" + e);
      }
      
      return "index";
}
    
    
    public static void download(URL url, File fileName) {
	    try {
	    	FileUtils.copyURLToFile(url, fileName); 	
	    } catch (IOException e) {
	    	System.out.println("Errore di Input/Output" + e);
	    }
	}
    
    
}