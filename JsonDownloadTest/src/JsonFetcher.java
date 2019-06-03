import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

public class JsonFetcher {
    
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try {         
            URL oracle = new URL("https://www.dati.gov.it/api/3/action/package_show?id=537c1138-4c5f-41bb-aec8-862954b67b28"); // URL to Parse
            URLConnection yc = oracle.openConnection();
            yc.setRequestProperty("User-Agent","pokemon");
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            BufferedWriter out = new BufferedWriter(new FileWriter("prova.json"));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) { 
                out.write(inputLine);
            }
            in.close();
            out.close();
            try{
                Object obj = parser.parse(new FileReader("prova.json"));
                JSONObject jsonObject = (JSONObject) obj;
                System.out.println(jsonObject.get("help"));
            } catch (IOException | ParseException e) {
				e.printStackTrace();
			}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }   
}
}