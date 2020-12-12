package threesixty.hr.infrastructure.json;

import javax.inject.Singleton;
import javax.json.bind.JsonbConfig;

import io.quarkus.jsonb.JsonbConfigCustomizer;

@Singleton
public class RegisterJsonbConfigCustomizer implements JsonbConfigCustomizer {

    public void customize(final JsonbConfig config) {
    	
    	config.withDateFormat("yyyy-MM-dd'T'HH:mm:ss", null);
    }
}