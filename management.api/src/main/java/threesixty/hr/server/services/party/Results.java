package threesixty.hr.server.services.party;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import threesixty.hr.core.party.Party;

public class Results {

	private final Date logicalDate;
	private List<Party> parties; 
	private List<Party> references;
	
	public Results() {
		
		this(new Date());
	}
	
	public Results(
			final Date logicalDate) {
		
		this.logicalDate = logicalDate;
	}

	private List<Long> getReferencedPartyIdentifiers() {
		
		List<Long> primaryParties = 
				getParties().stream()
					.map(Party::getPartyId)
					.collect(Collectors.toList());
		
		final List<Long> referencedParties =
				getParties().stream()
					.map(party -> party.getRelationships())
					.flatMap(l -> l.stream())
					.map(relationship -> Arrays.asList(relationship.fromPartyId, relationship.toPartyId) )
					.flatMap(l -> l.stream())
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
		
		getParties().stream()
					.map(party -> party.getIdentifiers())
					.flatMap(l -> l.stream())
					.map(identifier -> Arrays.asList(identifier.assignedToPartyId, identifier.issuedByPartyId) )
					.flatMap(l -> l.stream())
					.filter(Objects::nonNull)
					.forEach(referencedParties::add);
		
		getParties().stream()
					.map(party -> party.getNames())
					.flatMap(l -> l.stream())
					.map(name -> Arrays.asList(name.labelledByPartyId, name.issuedByPartyId) )
					.flatMap(l -> l.stream())
					.filter(Objects::nonNull)
					.forEach(referencedParties::add);
		
		referencedParties.removeAll(primaryParties);
		
		return referencedParties;
	}
	
	private List<Party> getReferencedParties() {
		
		return Party.find(getReferencedPartyIdentifiers());
	}
	
	public List<Party> getParties() { return parties; }
	
	public List<Party> getReferences() { 
	
		if (this.references == null) {
			
			this.references = getReferencedParties();
		}
		
		return references; 
	}
	
//	public Results withParties(
//			final List<Party> parties) {
//		
//		if (this.parties != null) {
//			this.parties.clear();
//		}
//	
//		if (parties != null) {
//			
//			parties.stream()
//				.forEach(party -> party.removeInactiveItems(logicalDate));
//			
//			this.parties = 
//					parties.stream()
//						.collect(Collectors.toUnmodifiableList());
//		}
//		
//		return this;
//	}
	
	public Results withRefereces(
			final List<Party> references) {
		
		if (this.references != null) {
			this.references.clear();
		}
	
		if (references != null) {
			this.references = 
					references.stream()
						.collect(Collectors.toUnmodifiableList());
		}
		
		return this;
	}
}
