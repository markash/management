package threesixty.hr.management.server.services.db.derby;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.Charsets;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.flywaydb.core.api.migration.Context;

import threesixty.hr.management.shared.services.employee.PartyType;
import threesixty.hr.management.shared.services.employee.RelationshipType;

public class R__Party extends AbstractMigration {

	private NVPair[] migrations = new NVPair[] {
			new NVPair("vwPersonToOrganization", 	"/db/derby/R__00_PersonToOrganizationView.sql"),
			new NVPair("vwEmployee", 				"/db/derby/R__01_EmployeeView.sql")
	};
	
	@Override
	public void migrate(
			final Context context) throws Exception {
		
//		for (NVPair migration : migrations) {
//			
//			dropIfExists(context, migration.getName());
//			
//			migrateIfNotExists(context, migration.getName(), getSql((String) migration.getValue()));
//		}
		
		merge(context, PartyType.values());
		merge(context, RelationshipType.values());
	}

	private String getSql(
			final String fileName) throws Exception {
		
		URL url = this.getClass().getResource(fileName);
		
		if (url == null) {
			throw new ProcessingException("SQL script migration file {} could not be loaded", fileName);
		}
		
		return new String(IOUtility.readFromUrl(url), Charsets.UTF_8);
	}
	
	/**
	 * Merge the party types
	 * @param partyTypes The party types
	 */
	private void merge(
			final Context context,
			final PartyType[] partyTypes) throws SQLException {
		
		Connection connection = context.getConnection();
			
		for (PartyType partyType : partyTypes) {
			
			if (!exists(connection, partyType)) {
				
				insert(connection, partyType);
			}
		}
	}
	
	/**
	 * Merge the relationship types
	 * @param relationshipTypes The relationship types
	 */
	private void merge(
			final Context context,
			final RelationshipType[] relationshipTypes) throws SQLException {
		
		Connection connection = context.getConnection();
			
		for (RelationshipType relationType : relationshipTypes) {
			
			if (!exists(connection, relationType)) {
				
				insert(connection, relationType);
			}
		}
	}
	
	private boolean exists(
			final Connection connection,
			final PartyType partyType) throws SQLException {
		
		String SELECT = "SELECT PartyTypeId FROM PartyType WHERE PartyTypeId = ?";
		
		try (PreparedStatement pstmt = connection.prepareStatement(SELECT)) {
			
			pstmt.setInt(1, partyType.getId());
			
			ResultSet results = pstmt.executeQuery();
			
			return  (results.next());
		}
	}
	
	private boolean insert(
			final Connection connection, 
			final PartyType partyType) throws SQLException {
		
		String SELECT = 
				"INSERT INTO PartyType ( 		\r\n" +
				"	PartyTypeId					\r\n" +
				",	PartyTypeName				\r\n" +
				") VALUES (?, ?)";
		
		try (PreparedStatement pstmt = connection.prepareStatement(SELECT)) {
			
			pstmt.setInt(1, partyType.getId());
			pstmt.setString(2, partyType.getName());
			
			return pstmt.execute();
		}
	}
	
	private boolean exists(
			final Connection connection,
			final RelationshipType relationshipType) throws SQLException {
		
		String SELECT = "SELECT RelationshipTypeId FROM RelationshipType WHERE RelationshipTypeId = ?";
		
		try (PreparedStatement pstmt = connection.prepareStatement(SELECT)) {
			
			pstmt.setInt(1, relationshipType.getId());
			
			ResultSet results = pstmt.executeQuery();
			
			return  (results.next());
		}
	}
	
	private boolean insert(
			final Connection connection, 
			final RelationshipType relationshipType) throws SQLException {
		
		String SELECT = 
				"INSERT INTO RelationshipType ( \r\n" +
				"	RelationshipTypeId			\r\n" +
				",	RelationshipTypeName		\r\n" +
				") VALUES (?, ?)";
		
		try (PreparedStatement pstmt = connection.prepareStatement(SELECT)) {
			
			pstmt.setInt(1, relationshipType.getId());
			pstmt.setString(2, relationshipType.getName());
			
			return pstmt.execute();
		}
	}
}
