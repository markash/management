package threesixty.hr.management.server.services.party;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrganizationName implements Function<List<PartyName>, String> {

	@Override
	public String apply(
			final List<PartyName> names) {
	
		return names.stream()
			.filter(name -> NameComponent.VALID_TYPES.contains(name.getType().getName()))
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
					PartyNameTypes.CorporateName);
			
		public NameComponent(
				final PartyName partyName) {
			
			switch(partyName.getType().getName()) {
			
			case CorporateName:
				this.order = 1;
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
