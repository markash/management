package threesixty.hr.management.shared.services.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.scout.rt.platform.holders.NVPair;

public class SqlStatement<T> {
	private final StringBuilder sql = new StringBuilder();
	private final List<NVPair> bindBases = new ArrayList<>();
	private T object;
	
	public String getSql() { return sql.toString(); }
	public Object[] getBindBases() { return bindBases.toArray(new NVPair[0]); }
	public T getIntoResult() { return this.object; }
	
	public static NotNullPredicate NOT_NULL = new NotNullPredicate();
	
	public static NotEmptyPredicate NOT_EMPTY = new NotEmptyPredicate();
	
	public SqlStatement<T> appendStatement(
			final String sql) {
		
		this.sql.append(sql);
		return this;
	}
	
	public SqlStatement<T> bind(
			final NVPair bind) {
		
		this.bindBases.add(bind);
		return this;
	}
	
	public SqlStatement<T> appendStatementBind(
			final String sql,
			final String name,
			final Object value) {
		
		this.sql.append(sql);
		this.bindBases.add(new NVPair(name, value));
		
		return this;
	}
	
	public SqlStatement<T> appendStatementBindIf(
			final String sql,
			final String name,
			final Object value,
			final Predicate<Object> filter) {
		
		if (filter.test(value)) {
			
			appendStatementBind(sql, name, value);
		}
		
		return this;
	}
	
	public SqlStatement<T> intoStatementBind(
			final String sql,
			final String name,
			final T value) {
		
		this.sql.append(sql);
		this.bindBases.add(new NVPair(name, value));
		this.object = value;
		
		return this;
	}
	
	public static class NotNullPredicate implements Predicate<Object> {

		@Override
		public boolean test(
				final Object object) {
			
			return object != null;
		}
	}
	
	public static class NotEmptyPredicate implements Predicate<Object> {

		@Override
		public boolean test(
				final Object object) {
			
			if (object instanceof String) {
				return !((String) object).equals("");
			}
			
			if (object instanceof Object[]) {
				return ((Object[]) object).length > 0;
			}
			
			return false;
		}
	}
}
