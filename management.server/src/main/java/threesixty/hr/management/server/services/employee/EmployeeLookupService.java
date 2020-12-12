package threesixty.hr.management.server.services.employee;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.server.services.lookup.AbstractLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import threesixty.hr.management.shared.services.employee.IEmployeeLookupService;
import threesixty.hr.management.shared.services.party.Party;

/** TODO Fix this class, possible to be like PersonLookupService */
public class EmployeeLookupService extends AbstractLookupService<Long> implements IEmployeeLookupService {

	@Override
	public List<? extends ILookupRow<Long>> getDataByKey(
			final ILookupCall<Long> call) {
		
		return null;
//		return createRows(
//				call,
//				BEANS.get(IEmployeeManager.class).lookup(call));
	}

	@Override
	public List<? extends ILookupRow<Long>> getDataByText(
			final ILookupCall<Long> call) {
		
		return null;
//		return createRows(
//				call,
//				BEANS.get(IEmployeeManager.class).lookup(call));
	}

	@Override
	public List<? extends ILookupRow<Long>> getDataByAll(ILookupCall<Long> call) {
		
		return null;
//		return createRows(
//				call,
//				BEANS.get(IEmployeeManager.class).lookup(call));
	}

	@Override
	public List<? extends ILookupRow<Long>> getDataByRec(ILookupCall<Long> call) {
		
		return null;
//		return createRows(
//				call,
//				BEANS.get(IEmployeeManager.class).lookup(call));
	}

	public List<? extends ILookupRow<Long>> createRows(
			final ILookupCall<Long> call,
			final List<Party> parties) {
		
		Object[][] data = 
				parties.stream()
					.map(party -> new Object[] { party.id, party.name })
					.collect(Collectors.toList())
					.toArray(new Object[0][]); 
		
		return createLookupRowArray(data, call, Long.class);
	}
}
