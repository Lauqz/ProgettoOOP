package com.progetto;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
