package threesixty.hr.management.shared.services;

import java.io.Serializable;

/**
 * A direct named entity has a single name that defines the entity. Some entities, like Party,
 * have names assigned that could be effective from a certain date, i.e. Family name for 
 * a spouse that takes the partner's name.
 * 
 * @author Mark Ashworth
 */
public interface DirectNamedEntity extends Serializable {
	
	/**
	 * The name of the entity
	 * @return The name of the entity
	 */
	String getName();
	void setName(String name);
}
