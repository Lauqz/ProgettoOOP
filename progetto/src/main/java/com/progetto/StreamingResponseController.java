package com.progetto;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

@RestController  // It is also possible to @Controller.
public class StreamingResponseController {
    @RequestMapping(value = "downloadTestFile", method = RequestMethod.GET)
    public void getSteamingFile(HttpServletResponse response) throws Exception {
    Boolean culo=true;
	if(culo){
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"t1.csv\"");
		try {
			
			URLConnection openConnection = new URL("https://www.dati.gov.it/api/3/action/package_show?id=537c1138-4c5f-41bb-aec8-862954b67b28").openConnection();
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();
			
			 String data = "";
			 String line = "";
			 try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
				   System.out.println( line );
			   }
			 } finally {
			   in.close();
			 }
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data); 
			JSONObject objI = (JSONObject) (obj.get("result"));
			JSONArray objA = (JSONArray) (objI.get("resources"));
			
			for(Object o: objA){
			    if ( o instanceof JSONObject ) {
			        JSONObject o1 = (JSONObject)o; 
			        String format = (String)o1.get("format");
			        String urlD = (String)o1.get("url");
			        System.out.println(format + " | " + urlD);
			        if(format.equals("csv")) {
			        	download(urlD, "t1.csv");
			        }
			    }
			}
			System.out.println( "OK" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    }
    
    public static void download(String url, String fileName) {
	    try {
	    	/*
	    	InputStream in = URI.create(url).toURL().openStream();
	    	System.out.println("PD1");
	        //Files.copy(in, Paths.get(fileName));
	    	IOUtils.copy(in, new FileOutputStream(new File(fileName)));
	    	*/
	    	FileUtils.
	    } catch (Exception e) {
	    	System.out.println("ciao");
	    }
	}
}