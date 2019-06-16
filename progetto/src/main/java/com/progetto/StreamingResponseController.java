package com.progetto;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.apache.commons.io.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@RestController  // It is also possible to @Controller.
public class StreamingResponseController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void getSteamingFile(HttpServletResponse response) throws Exception {
    	
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
			        String urlA = (String)o1.get("url"); //urlD
			        URL urlD = new URL (urlA); //prova 3
			        URL urlProva = new URL ("https://docs.google.com/spreadsheets/d/e/2PACX-1vT6GDZNWIJgVmd3GLlEL4dPA-B_VkatLA--B4EPWhvTYRs8u244QXeaE8Ij7ikJsuUj9oyBnvH6OAGL/pub?gid=67122230&single=true&output=csv");
			        System.out.println(format + " | " + urlD);
			        if(format.equals("csv")) {
			        	File fname = new File ("t1.csv"); //prova 3
			        	download(urlProva, fname); //"t1.csv" al posto di fname
			        }
			    }
			}
			System.out.println( "OK" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
      else System.out.println("File presente, impossibile scaricare");
}
    
    public static void download(URL url, File fileName) {
	    try {
	    	/*prova 1
	    	 * InputStream in = URI.create(url).toURL().openStream();
	    	System.out.println("prova errore");
	        Files.copy(in, Paths.get(fileName));
	        System.out.println("prova errore 2");
	        in.close();*/

	    	/*prova 2
	    	 *  BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());  
	    	FileOutputStream fileOS = new FileOutputStream("t1-prova.csv");
	    	byte data[] = new byte[1024];
	    	int byteContent;
	    	while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
	    	       fileOS.write(data, 0, byteContent);
	    	}
	    	fileOS.close();
	    	inputStream.close();*/
	    	
	    	
	    	//prova 3
	    	System.out.println("err1");
	    	FileUtils.copyURLToFile(url, fileName);
	    	System.out.println("err2");
	    	
	    } catch (IOException e) {
	    	System.out.println("ciao");
	    }
	}
}