package threesixty.hr.management.server.services.rest;

import threesixty.hr.management.shared.services.ICodes;

public enum RestParamTypes implements ICodes<Integer> {
	
	PATH(1),
	QUERY(2);

	private final Integer id;
	
	private RestParamTypes(final Integer id) {
	
		this.id = id;
	}
	
	@Override
	public Integer getId() { return this.id; }
}
