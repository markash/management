package threesixty.hr.management.server.services.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.holders.BeanArrayHolder;
import org.eclipse.scout.rt.server.jdbc.ISqlService;

import threesixty.hr.management.shared.services.employee.IPartyRelationshipResource;
import threesixty.hr.management.shared.services.employee.Party;
import threesixty.hr.management.shared.services.employee.PartyRelationship;
import threesixty.hr.management.shared.services.employee.RelationshipType;
import threesixty.hr.management.shared.services.employee.SqlStatement;

public class PartyRelationshipResource implements IPartyRelationshipResource {

	/**
	 * Retrieve the party relationships
	 * @param party The part for which to return relationships
	 * @param types The types of party relationships to filter by, else return all
	 * @return List of party relationships
	 */
	@Override
	public List<PartyRelationship> retrieve(
			final Party party,
			final RelationshipType...types) {
		
		if (party == null || party.getId() == null) {
			return new ArrayList<>();
		}
		
		SqlStatement<BeanArrayHolder<PartyRelationship>> statement = SELECT_RELATIONSHIPS_INTO(party, types);
		
		/* Retrieve the base relationship */
		BEANS.get(ISqlService.class)
				.selectInto(statement.getSql(), statement.getBindBases());
		
		return Arrays
				.stream(statement.getIntoResult().getBeans())
				.collect(Collectors.toList());
	}

	private static SqlStatement<BeanArrayHolder<PartyRelationship>> SELECT_RELATIONSHIPS_INTO(
			final Party party,
			final RelationshipType...types) {
		
		BeanArrayHolder<PartyRelationship> object = new BeanArrayHolder<>(PartyRelationship.class);
		
		return new SqlStatement<BeanArrayHolder<PartyRelationship>>()
					.appendStatement(RELATIONSHIP_SELECT)
					.appendStatementBindIf(AND_EQ_PARTY_ID_CLAUSE, PARTY_ID, party.getId(), SqlStatement.NOT_NULL)
					.appendStatementBindIf(AND_IN_RELATIONSHIP_TYPE_ID_CLAUSE, RELATIONSHIP_TYPE_ID, types, SqlStatement.NOT_EMPTY)
					.intoStatementBind(INTO_OBJECT, OBJECT, object);
	}
	
	
	
//	private EmploymentRelationship embellish(
//			final EmploymentRelationship relationship,
//			final SearchFilter filter) {
//		
//		StringBuilder sql = new StringBuilder(PERSON_SELECT);
//		
//		PartySearchFormData formData = (PartySearchFormData) filter.getFormData();
//		
//		if (formData.getName().isValueSet()) {
//			
//			sql.append(AND_LIKE_NAME_CLAUSE);
//		}	
//		sql.append(AND_EQ_PARTYID_CLAUSE);
//		
//		sql.append(PERSON_INTO_OBJECT);
//		
//		BEANS.get(ISqlService.class)
//				.selectInto(
//					sql.toString(),
//					new NVPair("partyId", relationship.getTo().getId()),
//					new NVPair("name", formData.getName().getValue()),
//					new NVPair("object", relationship.getTo()));
//		
//		
//		sql = new StringBuilder(ORGANIZATION_SELECT);
//		sql.append(AND_EQ_PARTYID_CLAUSE);
//		sql.append(ORGANIZATION_INTO_OBJECT);
//		
//		BEANS.get(ISqlService.class)
//				.selectInto(
//					sql.toString(),
//					new NVPair("partyId", relationship.getFrom().getId()),
//					new NVPair("object", relationship.getFrom()));
//	
//		EmploymentRelationship result = (relationship.getTo().getId() != null && relationship.getFrom().getId() != null) ? relationship : null;
//		
//		return result;
//	}
	
	private static final String OBJECT = "object";
	private static final String PARTY_ID = "partyId";
	private static final String RELATIONSHIP_TYPE_ID = "relationshipTypeId";
	
	private static final String RELATIONSHIP_SELECT =
			"SELECT									\r\n" + 
			"		R.RELATIONSHIPID 				\r\n" + 
			"	,	R.RELATIONSHIPTYPEID 			\r\n" + 
			"	,	R.FROMPARTYID					\r\n" + 
			"	,	R.TOPARTYID						\r\n" + 
			"	,	R.EFD							\r\n" + 
			"	,	R.ETD							\r\n" + 
			"FROM RELATIONSHIP						\r\n" + 
			"	AS	R								\r\n" + 
			"INNER JOIN RELATIONSHIPTYPE			\r\n" + 
			"	AS	T								\r\n" + 
			"	ON	T.RELATIONSHIPTYPEID  = R.RELATIONSHIPTYPEID 		\r\n" + 
			"WHERE														\r\n" + 
			"		CURRENT_TIMESTAMP >= R.EFD 							\r\n" + 
			"	AND (R.ETD > CURRENT_TIMESTAMP OR R.ETD IS NULL)		\r\n";
			
	private static final String AND_EQ_RELATIONSHIP_TYPE_ID_CLAUSE = 
			"	AND	R.RELATIONSHIPTYPEID = :{relationshipTypeId}		\r\n";
	
	private static final String AND_IN_RELATIONSHIP_TYPE_ID_CLAUSE = 
			"	AND	R.RELATIONSHIPTYPEID IN :{relationshipTypeId}		\r\n";
	
	private static final String AND_EQ_PARTY_ID_CLAUSE = 
			"	AND (													\r\n" +
			"			R.FROMPARTYID 	= :{partyId}					\r\n" +
			"		OR	R.TOPARTYID		= :{partyId}					\r\n" +
			"		)													\r\n";
	
	private static final String INTO_OBJECT =
			" INTO 														\r\n" +
			"		:{object." + PartyRelationship.FIELD_ID 			+ "}\r\n" +
			"	,	:{object." + PartyRelationship.FIELD_TYPE_ID 		+ "}\r\n" +
			"	,	:{object." + PartyRelationship.FIELD_FROM_ID 		+ "}\r\n" +
			"	,	:{object." + PartyRelationship.FIELD_TO_ID 			+ "}\r\n" +
			"	,	:{object." + PartyRelationship.FIELD_EFFECTIVE_FROM 	+ "}\r\n" +
			"	,	:{object." + PartyRelationship.FIELD_EFFECTIVE_TO 	+ "}\r\n" ;
			
	
}
