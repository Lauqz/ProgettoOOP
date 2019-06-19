package com.progetto;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

@RestController
public class ChoiceController {
	
	@RequestMapping(value = "/meta", method = RequestMethod.GET)
    public String getMetadata(HttpServletResponse response) throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        mapper.acceptJsonFormatVisitor(Catasto.class, visitor);
		JsonSchema schema = visitor.finalSchema();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
    }
	
}
