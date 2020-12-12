package threesixty.hr.management.server.services.db.derby;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public abstract class AbstractMigration extends BaseJavaMigration {

	private final String SYSTABLES = "SELECT tablename, tabletype FROM sys.systables";
	
	private final Set<Table> tables = new HashSet<>();
			
	protected void migrateIfNotExists(
			final Context context,
			final String tableName,
			final String sql) throws SQLException {
		
		if (!tableExists(context, tableName)) {
			
			try (PreparedStatement statement = context.getConnection().prepareStatement(sql)) {
			
				statement.execute();
	       }
		}
	}
	
	protected void dropIfExists(
			final Context context,
			final String tableName) throws SQLException {
		
		Table table = getTable(context, tableName);
		
		if (table == null) { return; }
		
		String sql = null;
		
		switch (table.getType()) {
		case T: 
			sql = "DROP TABLE " + table.getName();
			break;
		case V:
			sql = "DROP VIEW " + table.getName();
			break;
		case S:
			throw new ProcessingException("Cannot drop a system table or view {}", table.getName());
		}
		
		try (PreparedStatement statement = context.getConnection().prepareStatement(sql)) {
		
			statement.execute();
       }
	}
	
	private boolean tableExists(
			final Context context, 
			final String tableName) throws SQLException {
	
		return getTable(context, tableName) != null;
	}
	
	private AbstractMigration.Table getTable(
			final Context context, 
			final String tableName) throws SQLException {
	
		return getExistingTables(context)
				.stream()
				.filter(table -> table.isTableName(tableName))
				.findFirst()
				.orElse(null);
	}
	
	private Set<Table> getExistingTables(
			final Context context) throws SQLException {
		
		if (tables.size() == 0) {
			
			try (PreparedStatement pstmt = context.getConnection().prepareStatement(SYSTABLES)) {
				
				ResultSet results = pstmt.executeQuery();
				while (results.next()) {
					tables.add(new Table(results));
				}
			}
		}
		
		return tables;
	}
	
	public static enum TableType {
		S, T, V;
	}
	
	private static class Table {
		private String name;
		private TableType type;
		
		public Table(final ResultSet result) throws SQLException {
			
			this.name = result.getString(1);
			this.type = TableType.valueOf(result.getString(2));
		}
		
		public String getName() { return name; }
		public TableType getType() { return type; }
		
		public boolean isTableName(final String tableName) {
			
			return tableName != null && tableName.equalsIgnoreCase(this.name);
		}
	}
}
