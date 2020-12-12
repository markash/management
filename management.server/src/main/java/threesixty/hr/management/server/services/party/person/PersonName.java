package threesixty.hr.management.server.services.party.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import threesixty.hr.management.server.services.party.PartyName;
import threesixty.hr.management.server.services.party.PartyNameTypes;

public class PersonName implements Function<List<PartyName>, String> {

	private final List<PartyNameTypes> partyNameTypes;
	
	public PersonName() {
		
		this.partyNameTypes = NameComponent.VALID_TYPES;
	}
	
	public PersonName(
			final PartyNameTypes...partyNameTypes) {
		
		this(Arrays.asList(partyNameTypes));
	}
	
	public PersonName(
			final List<PartyNameTypes> partyNameTypes) {
		
		this.partyNameTypes = 
				Optional.ofNullable(partyNameTypes)
					.orElse(new ArrayList<>())
					.stream()
					.filter(NameComponent.VALID_TYPES::contains)
					.collect(Collectors.toUnmodifiableList());
	}
	
	@Override
	public String apply(
			final List<PartyName> names) {
	
		return names.stream()
			.filter(name -> partyNameTypes.contains(name.getType().getName()))
			.map(NameComponent::new)
			.filter(NameComponent::hasValue)
			.sorted()
			.map(Objects::toString)
			.collect(Collectors.joining(" "));
	}

	private static final class NameComponent implements Comparable<NameComponent> {
		
		private final int order;
		
		private final String component;
		
		private static final List<PartyNameTypes> VALID_TYPES = 
				Arrays.asList(
					PartyNameTypes.FamilyName,
					PartyNameTypes.MiddleName,
					PartyNameTypes.GivenName);
			
		public NameComponent(
				final PartyName partyName) {
			
			switch(partyName.getType().getName()) {
			
			case GivenName:
				this.order = 1;
				this.component = partyName.getName();
				break;
				
			case MiddleName:
				this.order = 5;
				this.component = partyName.getName();
				break;
				
			case FamilyName:
				this.order = 10;
				this.component = partyName.getName();
				break;
				
			default:
				/* Ignore */
				this.order = -1;
				this.component = null;
			}
		}

		public boolean hasValue() {
		
			return Objects.nonNull(component);
		}
		
		@Override
		public int compareTo(
				final NameComponent o) {
			
			return Integer.valueOf(this.order).compareTo(o.order);
		}

		@Override
		public String toString() {
			return Objects.toString(component, "");
		}
	}
}
