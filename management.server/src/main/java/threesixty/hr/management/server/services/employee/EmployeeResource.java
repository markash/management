package threesixty.hr.management.server.services.employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.holders.BeanArrayHolder;
import org.eclipse.scout.rt.server.jdbc.ISqlService;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import threesixty.hr.management.shared.employee.PartySearchFormData;
import threesixty.hr.management.shared.services.employee.IPersonResource;
import threesixty.hr.management.shared.services.employee.Organization;
import threesixty.hr.management.shared.services.employee.Person;
import threesixty.hr.management.shared.services.employee.SqlStatement;

public class EmployeeResource implements IPersonResource {

	@Override
	public List<Person> retrieve(
			final SearchFilter filter) {
		
		StringBuilder sql = new StringBuilder(PERSON_SELECT);
		
		PartySearchFormData formData = (PartySearchFormData) filter.getFormData();
		
		SqlStatement<BeanArrayHolder<Person>> statement = 
				SELECT_PERSONS_INTO(formData.getName().getValue());
		
		BEANS.get(ISqlService.class)
				.selectInto(statement.getSql(), statement.getBindBases());
		
		return Arrays.stream(statement.getIntoResult().getBeans())
			.collect(Collectors.toList());
	}
	
	private static SqlStatement<BeanArrayHolder<Person>> SELECT_PERSONS_INTO(
			final String name) {
		
		BeanArrayHolder<Person> object = new BeanArrayHolder<>(Person.class);
		
		return new SqlStatement<BeanArrayHolder<Person>>()
					.appendStatement(PERSON_SELECT)
					.appendStatementBindIf(AND_LIKE_NAME_CLAUSE, PARTY_ID, name, SqlStatement.NOT_EMPTY)
					.intoStatementBind(INTO_OBJECT, OBJECT, object);
	}
	
	
//	/**
//	 * Retrieve the employees
//	 * @return List of employees
//	 */
//	@Override
//	public List<EmploymentRelationship> retrieveEmploymentRelationships(
//			final SearchFilter filter) {
//		
//		BeanArrayHolder<EmploymentRelationship> holder = new BeanArrayHolder<>(EmploymentRelationship.class);
//		
//		/* Retrieve the base relationship */
//		BEANS.get(ISqlService.class)
//				.selectInto(
//						RELATIONSHIP_SELECT_INTO_OBJECT, 
//						new NVPair("relationshipTypeId", RelationshipType.EMPLOYMENT.getId()),
//						new NVPair("object", holder));
//		
//		/* Embellish with the person and organization */
//		List<EmploymentRelationship> results =
//				Arrays.stream(holder.getBeans())
//					.map(relationship -> embellish(relationship, filter))
//					.filter(relationship -> relationship != null)
//					.collect(Collectors.toList());
//		
//		
//		return results;
//	}
//
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
	
//	private static final String RELATIONSHIP_SELECT =
//			"SELECT									\r\n" + 
//			"		R.RELATIONSHIPID 				\r\n" + 
//			"	,	R.RELATIONSHIPTYPEID 			\r\n" + 
//			"	,	R.FROMPARTYID					\r\n" + 
//			"	,	R.TOPARTYID						\r\n" + 
//			"	,	R.EFD							\r\n" + 
//			"	,	R.ETD							\r\n" + 
//			"FROM RELATIONSHIP						\r\n" + 
//			"	AS	R								\r\n" + 
//			"INNER JOIN RELATIONSHIPTYPE			\r\n" + 
//			"	AS	T								\r\n" + 
//			"	ON	T.RELATIONSHIPTYPEID  = R.RELATIONSHIPTYPEID 	\r\n" + 
//			"WHERE													\r\n" + 
//			"		CURRENT_TIMESTAMP >= R.EFD 						\r\n" + 
//			"	AND (R.ETD <  CURRENT_TIMESTAMP OR R.ETD IS NULL)";
//			
//	private static final String AND_EQ_PARTYID_CLAUSE = 
//			"		R.RELATIONSHIPTYPEID = :{relationshipTypeId}	\r\n";
//	
	
	private static final String PARTY_ID = "partyId";
	private static final String NAME = "name";
	private static final String OBJECT = "object";
	
//	private static final String RELATIONSHIP_SELECT_INTO_OBJECT =
//			RELATIONSHIP_SELECT										+ "	\r\n" +
//			" INTO 														\r\n" +
//			"		:{object." + PartyRelationship.FIELD_ID 				+ "}\r\n" +
//			"	,	:{object." + PartyRelationship.FIELD_TYPE_ID 		+ "}\r\n" +
//			"	,	:{object." + PartyRelationship.FIELD_FROM_ID 		+ "}\r\n" +
//			"	,	:{object." + PartyRelationship.FIELD_TO_ID 			+ "}\r\n" +
//			"	,	:{object." + PartyRelationship.FIELD_EFFECTIVE_FROM 	+ "}\r\n" +
//			"	,	:{object." + PartyRelationship.FIELD_EFFECTIVE_TO 	+ "}\r\n" ;
//			
	private static final String PERSON_SELECT =
			"SELECT									\r\n" + 
			"		E.PARTYID						\r\n" + 
			"	,	P.PARTYTYPEID					\r\n" +
			"	,	E.FIRSTNAME						\r\n" + 
			"	,	E.LASTNAME						\r\n" + 
			"	,	E.DATEOFBIRTH					\r\n" + 
			"	,	E.IDENTITYNUMBER				\r\n" + 
			"	,	E.PROCESSEDDATETIME				\r\n" +
			"FROM PERSON							\r\n" + 
			"	AS E								\r\n" +
			"INNER JOIN PARTY						\r\n" +
			"	AS P								\r\n" +
			"	ON P.PARTYID = E.PARTYID			\r\n" +
			"WHERE 1=1"
			;
	
	private static final String AND_EQ_PARTYID_CLAUSE = 
			"	AND E.PARTYID = :partyId";
	
	private static final String AND_LIKE_NAME_CLAUSE = 
			"	AND (LOWER(E.FIRSTNAME) LIKE LOWER(:name || '%') OR LOWER(E.LASTNAME) LIKE LOWER(:name || '%'))";
	
	private static final String INTO_OBJECT = 
			" INTO 														\r\n" +
			"		:{object." + Person.FIELD_PARTY_ID 				+ "}\r\n" +
			"	,	:{object." + Person.FIELD_PARTY_TYPE_ID 		+ "}\r\n" +
			"	,	:{object." + Person.FIELD_FIRST_NAME 			+ "}\r\n" +
			"	,	:{object." + Person.FIELD_LAST_NAME 			+ "}\r\n" +
			"	,	:{object." + Person.FIELD_DATE_OF_BIRTH 		+ "}\r\n" +
			"	,	:{object." + Person.FIELD_IDENTITY_NUMBER 		+ "}\r\n" +
			"	,	:{object." + Person.FIELD_PROCESSED_DATE_TIME	+ "}\r\n";
	
	private static final String ORGANIZATION_SELECT =
			"SELECT									\r\n" + 
			"		E.PARTYID						\r\n" + 
			"	,	P.PARTYTYPEID					\r\n" +
			"	,	E.NAME							\r\n" + 
			"	,	E.DATEOFREGISTRATION			\r\n" + 
			"	,	E.REGISTRATIONNUMBER			\r\n" + 
			"	,	E.PROCESSEDDATETIME				\r\n" +
			"FROM ORGANIZATION						\r\n" + 
			"	AS E								\r\n" +
			"INNER JOIN PARTY						\r\n" +
			"	AS P								\r\n" +
			"	ON P.PARTYID = E.PARTYID			\r\n" +
			"WHERE 1=1								\r\n"
			;
	
	private static final String ORGANIZATION_INTO_OBJECT = 
			" INTO 																\r\n" +
			"		:{object." + Organization.FIELD_PARTY_ID 				+ "}\r\n" +
			"	,	:{object." + Organization.FIELD_PARTY_TYPE_ID 			+ "}\r\n" +
			"	,	:{object." + Organization.FIELD_NAME 					+ "}\r\n" +
			"	,	:{object." + Organization.FIELD_DATE_OF_REGISTRATION 	+ "}\r\n" +
			"	,	:{object." + Organization.FIELD_REGISTRATION_NUMBER 	+ "}\r\n" +
			"	,	:{object." + Organization.FIELD_PROCESSED_DATE_TIME		+ "}\r\n";
}
