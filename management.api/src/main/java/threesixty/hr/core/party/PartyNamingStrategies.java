package threesixty.hr.core.party;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PartyNamingStrategies {

	private static final Set<PartyNamingStrategy> STRATEGIES = new HashSet<PartyNamingStrategy>();
	
	static {
		STRATEGIES.add(new PersonNamingStrategy());
	}
	
	public static String name(
			final Party party) {
			
		return STRATEGIES.stream()
				.filter(strategy -> strategy.handles(party))
				.map(strategy -> strategy.map(party))
				.findFirst()
				.orElse(null);
	}
	
	public static class PersonNamingStrategy implements PartyNamingStrategy {

		@Override
		public Predicate<Party> getPredicate() { 
			
			return party -> party.getType() == PartyType.PERSON; 
		}

		@Override
		public Function<Party, String> getStrategy() { 
			
			final PersonNamePredicate personNames = new PersonNamePredicate();
			
			final PersonNameComparator personNamesOrder = new PersonNameComparator();
			
			return party -> party.getNames(LocalDate.now())
								.stream()
								.map(PersonName::map)
								.filter(personNames)
								.sorted(personNamesOrder)
								.map(name -> name.name)
								.collect(Collectors.joining(" ")); 
		}
	}


	public static class PersonName {
		
		public String name;
		public String type;
		
		public String getName() { return this.name; }
		
		public static PersonName map(
				final PartyName partyName) {
			
			if (partyName == null) { return null; }
			
			PersonName name = new PersonName();
			name.name = partyName.getName();
			name.type = Optional.ofNullable(partyName.type).map(type -> type.name).orElse("");
			
			return name;
		}
	}
	
	public static class PersonNameFunction {
		
		private final Map<String, Integer> order = new HashMap<String, Integer>();
		
		public PersonNameFunction() {
			
			this.order.put(PartyNameTypes.GivenName.name().toUpperCase(), 1);
			this.order.put(PartyNameTypes.MiddleName.name().toUpperCase(), 2);
			this.order.put(PartyNameTypes.FamilyName.name().toUpperCase(), 3);
		}
		
		public boolean contains(final String partyNameType) { return this.order.containsKey(partyNameType); }
		public Integer getOrder(final String partyNameType) { return this.order.get(partyNameType); }
	}
	
	public static class PersonNamePredicate extends PersonNameFunction implements Predicate<PersonName> {

		@Override
		public boolean test(
				final PersonName name) {
			
			return contains(name.type.toUpperCase());
		}
	}
	
	public static class PersonNameComparator extends PersonNameFunction implements Comparator<PersonName> {

		@Override
		public int compare(
				final PersonName o1, 
				final PersonName o2) {
			
			Integer i1 = Optional.ofNullable(o1.type)
						.map(type -> getOrder(type.toUpperCase()))
						.orElse(99);

			Integer i2 = Optional.ofNullable(o2.type)
					.map(type -> getOrder(type.toUpperCase()))
					.orElse(99);
			
			return i1.compareTo(i2);
		}
	}
}
