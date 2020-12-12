package threesixty.hr.core.party;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A party naming strategy defines the party names that together provide the common name for a party.
 * 
 * For example, the given name, middle name (if exists) and family name provide the common name
 * for an individual person, where as the registered company name is used for an external organization.
 * 
 * @author Mark Ashworth
 */
public interface PartyNamingStrategy {

	/**
	 * The predicate to test whether the party is handled by the strategy
	 * @return The predicate
	 */
	Predicate<Party> getPredicate();
	
	/**
	 * The function to convert the party into a common name
	 * @return The common name for the party
	 */
	Function<Party, String> getStrategy();
	
	/**
	 * Determines whether the strategy handles the party by calling the predicate
	 * @param party The party
	 * @return True if the predicate handles the party else false
	 */
	default boolean handles(final Party party) {
		
		return getPredicate().test(party);
	}
	
	/**
	 * Maps the party to a string
	 * @param party The party
	 * @return The name of the party
	 */
	default String map(final Party party) {
		
		return getStrategy().apply(party);
	}
}
