package threesixty.hr.management.server.services.party.person;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import threesixty.hr.management.shared.services.DirectNamedEntityNameLookup;
import threesixty.hr.management.shared.services.party.person.IPersonLookupService;
import threesixty.hr.management.shared.services.party.person.Person;
import threesixty.hr.management.shared.services.party.person.PersonToLookupRow;

public class PersonLookupService implements IPersonLookupService {

	private final DirectNamedEntityNameLookup<Person, Long> lookup =
			DirectNamedEntityNameLookup.Builder
				.forDirectNamedEntity(Person.class, Long.class)
				.withSupplier(() -> BEANS.get(PersonResourceClient.class).retrieveAll())
				.withMapper(new PersonToLookupRow())
				.build();
			
	@Override
	public List<? extends ILookupRow<Long>> getDataByKey(
			final ILookupCall<Long> call) {
		
		return lookup
				.search("*")
				.stream()
				.filter(row -> row.getKey().equals(call.getKey()))
				.collect(Collectors.toList());
	}

	@Override
	public List<? extends ILookupRow<Long>> getDataByText(
			final ILookupCall<Long> call) {

		return lookup.search(call.getText());
	}

	@Override
	public List<? extends ILookupRow<Long>> getDataByAll(
			final ILookupCall<Long> call) {

		return lookup.retrieveAll();
	}

	@Override
	public List<? extends ILookupRow<Long>> getDataByRec(
			final ILookupCall<Long> call) {
		
		return lookup.retrieveAll();
	}
}
