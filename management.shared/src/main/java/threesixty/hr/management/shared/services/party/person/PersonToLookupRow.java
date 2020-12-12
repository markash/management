package threesixty.hr.management.shared.services.party.person;

import java.util.function.Function;

import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class PersonToLookupRow implements Function<Person, LookupRow<Long>> {

	@Override
	public LookupRow<Long> apply(
			final Person person) {
		
		return new LookupRow<Long>(
				mapToCells(person), 
				Long.class);
	}

	private Object[] mapToCells(
			final Person person) {
		
		return new Object[] {
				person.id							/* key		*/,
				person.name							/* text		*/,
				null 								/* iconId	*/,
				person.name							/* tool tip	*/
		};
	}
}
