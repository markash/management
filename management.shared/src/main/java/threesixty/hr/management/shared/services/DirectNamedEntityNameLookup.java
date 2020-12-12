package threesixty.hr.management.shared.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class DirectNamedEntityNameLookup<T extends DirectNamedEntity, ID extends Serializable> {

	@SuppressWarnings("unused")
	private final Class<ID> identifierClass;
	private final Class<T> entityClass;
	private final Function<T, LookupRow<ID>> mapper;
	private final Supplier<Collection<T>> supplier;
	
	private DirectNamedEntityNameLookup(
			final Function<T, LookupRow<ID>> mapper,
			final Supplier<Collection<T>> supplier,
			final Class<T> entityClass,
			final Class<ID> identifierClass) {
	
		this.mapper = mapper;
		this.supplier = supplier;
		this.entityClass = entityClass;
		this.identifierClass = identifierClass;
	}

	public List<? extends ILookupRow<ID>> retrieveAll() {
		return search(null);
	}
	
	public List<? extends ILookupRow<ID>> search(
			final String searchText) {
		
		Predicate<T> filter;
		
		/* If no search text then return all else filter */
		if (StringUtility.isNullOrEmpty(searchText)) {
			filter = o -> true;
		} else {
			filter = DirectNamedEntityNameFilter
					.fromKey(entityClass, searchText, false)
					.getPredicate();
		}
			
		return supplier.get()
				.stream()
				.filter(filter)
				.map(mapper)
				.collect(Collectors.toList());
	}
	
	public static class Builder<T extends DirectNamedEntity, ID extends Serializable> {
		
		private final Class<ID> identifierClass;
		private final Class<T> entityClass;
		
		private Supplier<Collection<T>> supplier;
		private Function<T, LookupRow<ID>> mapper;
		
		private Builder(
				final Class<T> entityClass,
				final Class<ID> identifierClass) {
			
			this.entityClass = entityClass;
			this.identifierClass = identifierClass;
		}
		
		public static <T extends DirectNamedEntity, ID extends Serializable> Builder<T, ID> forDirectNamedEntity(
				final Class<T> entityClass,
				final Class<ID> identifierClass) {
			
			return new Builder<>(entityClass, identifierClass);
		}
		
		public Builder<T, ID> withSupplier(
				final Supplier<Collection<T>> supplier) {
		
			this.supplier = supplier;
			return this;
		}
		
		public Builder<T, ID> withMapper(
				final Function<T, LookupRow<ID>> mapper) {
		
			this.mapper = mapper;
			return this;
		}
		
		public DirectNamedEntityNameLookup<T, ID> build() {
			
			return new DirectNamedEntityNameLookup<T, ID>(
					mapper, 
					supplier, 
					entityClass,
					identifierClass);
		}
	}
}
