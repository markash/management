package threesixty.hr.management.server.services.db.derby;

import java.net.URL;

import org.apache.commons.codec.Charsets;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.flywaydb.core.api.migration.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V2020032900__Party extends AbstractMigration {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private NVPair[] migrations = new NVPair[] {
			new NVPair("PartyType", 			"/db/derby/V20200329_00__PartyType.sql"),
			new NVPair("Party", 				"/db/derby/V20200329_01__Party.sql"),
			new NVPair("Person", 				"/db/derby/V20200329_03__Person.sql"),
			new NVPair("Organization", 			"/db/derby/V20200329_04__Organization.sql"),
			new NVPair("RelationshipType", 		"/db/derby/V20200329_05__RelationshipType.sql"),
			new NVPair("Relationship", 			"/db/derby/V20200329_06__Relationship.sql"),
			new NVPair("PartyIdentifier",		"/db/derby/V20200329_07__PartyIdentifier.sql")
	};
	
	@Override
	public void migrate(
			final Context context) throws Exception {
		
		for (NVPair migration : migrations) {
			
			log.info("Migrating {} with script {}", migration.getName(), migration.getValue());
			
			migrateIfNotExists(context, migration.getName(), getSql((String) migration.getValue()));
		}
	}

	private String getSql(
			final String fileName) throws Exception {
		
		URL url = this.getClass().getResource(fileName);
		
		if (url == null) {
			throw new ProcessingException("SQL script migration file {} could not be loaded", fileName);
		}
		
		return new String(IOUtility.readFromUrl(url), Charsets.UTF_8);
	}
}
