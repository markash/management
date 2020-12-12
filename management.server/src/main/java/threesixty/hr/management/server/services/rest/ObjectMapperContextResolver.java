package threesixty.hr.management.server.services.rest;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper>{

	private final ObjectMapper mapper;
	
	public ObjectMapperContextResolver() {
        
		this.mapper = createObjectMapper();
    }

    @Override
    public ObjectMapper getContext(
    		final Class<?> type) {
        
    	return mapper;
    }

    private ObjectMapper createObjectMapper() {
        
    	ObjectMapper mapper = new ObjectMapper();
    	
    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
    	mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    	mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    	mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        mapper.registerModule(new JavaTimeModule());
        
        return mapper;
    }
}
