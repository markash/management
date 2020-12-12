package threesixty.hr.management.server.services.party.internalOrganization;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import threesixty.hr.management.shared.services.DirectNamedEntityNameLookup;
import threesixty.hr.management.shared.services.party.internalOrganization.IInternalOrganizationLookupService;
import threesixty.hr.management.shared.services.party.internalOrganization.InternalOrganization;
import threesixty.hr.management.shared.services.party.internalOrganization.InternalOrganizationToLookupRow;

public class InternalOrganizationLookupService implements IInternalOrganizationLookupService {

	private final DirectNamedEntityNameLookup<InternalOrganization, Long> lookup =
			DirectNamedEntityNameLookup.Builder
				.forDirectNamedEntity(InternalOrganization.class, Long.class)
				.withSupplier(() -> BEANS.get(InternalOrganizationResourceClient.class).retrieveAll())
				.withMapper(new InternalOrganizationToLookupRow())
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
