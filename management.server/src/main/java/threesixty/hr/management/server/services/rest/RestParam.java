package threesixty.hr.management.server.services.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RestParam implements IRestParam {

	private String name;
	private Object value;
	private RestParamTypes type;
	
	@Override
	public String getName() { return name; }
	
	@Override
	public Object getValue() { return value; }
	
	@Override
	public RestParamTypes getType() { return this.type; }
	
	public static class Builder {
		
		private RestParam param = null;
		
		private static Builder name(final String name) {
			
			Builder builder = new Builder();
			builder.param = new RestParam();
			builder.param.name = name;
			
			return builder;
		}

		public static Builder pathParameter(final String name) {
			
			Builder builder = name(name);
			builder.param.type = RestParamTypes.PATH;
			
			return builder;
		}

		public static Builder queryParameter(final String name) {
			
			Builder builder = name(name);
			builder.param.type = RestParamTypes.QUERY;
			
			return builder;
		}

		public Builder value(final String value) {
			
			this.param.value = value;
			return this;
		}
		
		public Builder value(final Long value) {
			
			this.param.value = value;
			return this;
		}

		public Builder value(final Integer value) {
			
			this.param.value = value;
			return this;
		}

		public Builder value(final LocalDate value) {
			
			return value(value, DateTimeFormatter.ISO_DATE);
		}

		public Builder value(final LocalDate value, final DateTimeFormatter dateTimeFormatter) {
			
			this.param.value = dateTimeFormatter.format(value);
			return this;
		}

		public RestParam build() {
			
			return param;
		}
	}

	
}
