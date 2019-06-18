package com.progetto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.io.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@Controller
public class StreamingResponseController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSteamingFile(HttpServletResponse response) throws Exception {
    	
      File f = new File("t1.csv");
      
      if(!f.exists() && !f.isDirectory()){
    	  
		//response.setContentType("text/csv"); SERVONO??
		//response.setHeader("Content-Disposition", "attachment; filename=\"test.csv");
    	  
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
    	 ArrayList<Catasto> obj = new ArrayList();
    	 String data = var.nextLine();
    	 String data2 = null;
    	 String full = null;
    	 int j = 0;
    	 while (var.hasNextLine()) {
    		 data = var.nextLine();
    		 data2 = var.nextLine();
    		 full = data + data2;
    		 ArrayList<String> parts = new ArrayList();
    		 String[] parti = full.split(",");
    		 for (int i = 0; i<12; i++) {
    			 parts.add(parti[i]);
    		 }
   			 Catasto foo = new Catasto(parts);
    		 obj.add(foo);
    		 System.out.println(foo.toString());
    	}
    	 var.close();
      }
      catch (IOException e) {
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