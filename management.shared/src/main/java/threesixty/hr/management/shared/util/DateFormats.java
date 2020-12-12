package threesixty.hr.management.shared.util;

public enum DateFormats {
	ISO_DATE("yyyy-MM-dd");
	
	private final String format;
	
	private DateFormats(
			final String format) {
		
		this.format = format;
	}
	
	public String getFormat() { return format; }
}
