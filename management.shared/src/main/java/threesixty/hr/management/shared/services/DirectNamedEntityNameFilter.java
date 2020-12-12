package threesixty.hr.management.shared.services;

import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.scout.rt.platform.util.Pair;
import org.eclipse.scout.rt.platform.util.StringUtility;

import threesixty.hr.management.shared.text.TextSearch;

public class DirectNamedEntityNameFilter<T extends DirectNamedEntity> extends Pair<TextSearch, String> {
	private static final long serialVersionUID = 933655347576451782L;

	private final TextSearch search;
	private final String key;
	private final Predicate<T> predicate;
	
	private DirectNamedEntityNameFilter(
			final TextSearch search,
			final String key,
			final Predicate<T> predicate) {
	
		this.search = search;
		this.key = key;
		this.predicate = predicate;
	}
	
	@Override
	public TextSearch getLeft() { return this.search; }

	@Override
	public String getRight() { return this.key; }
	
	public Predicate<T> getPredicate() { return predicate; }
	
	public static <T extends DirectNamedEntity> DirectNamedEntityNameFilter<T> fromKey(
			final Class<T> entityType,
			final String key,
			final boolean caseSensitive) {
		
		if (StringUtility.isNullOrEmpty(key)) {	 return null; }
		
		final boolean atStart = key.startsWith("*");
		final boolean atEnd = key.endsWith("*");
		final String searchText = defineSearchText(key, atStart, atEnd, caseSensitive);
		
		if (atStart && atEnd) {
			
			return new DirectNamedEntityNameFilter<T>(
					TextSearch.CONTAINS, 
					searchText, 
					(typeEntity) -> Optional.ofNullable(typeEntity)
								.map(DirectNamedEntity::getName)
								.map(t -> caseSensitive ? t : t.toLowerCase())
								.orElse("")
								.contains(searchText));
			
		} else if (!atStart && atEnd) {
			
			return new DirectNamedEntityNameFilter<T>(
					TextSearch.STARTS_WITH, 
					searchText, 
					(typeEntity) -> Optional.ofNullable(typeEntity)
								.map(DirectNamedEntity::getName)
								.map(t -> caseSensitive ? t : t.toLowerCase())
								.orElse("")
								.startsWith(searchText));
		
		} else {
			
			return new DirectNamedEntityNameFilter<T>(
					TextSearch.ENDS_WITH, 
					searchText,
					(typeEntity) -> Optional.ofNullable(typeEntity)
								.map(DirectNamedEntity::getName)
								.map(t -> caseSensitive ? t : t.toLowerCase())
								.orElse("")
								.endsWith(searchText));
		}
	}

	private static String defineSearchText(
			final String key, 
			final boolean atStart,
			final boolean atEnd,
			final boolean caseSensitive) {
		
		String searchText = key;
		
		if (atStart) {
			searchText = searchText.substring(1);
		}
		if (atEnd) {
			searchText = searchText.substring(0, searchText.length() - 1 < 0 ? 0 : searchText.length() -1);
		}
		if (!caseSensitive) {
			searchText = searchText.toLowerCase();
		}
		
		return searchText;
	}
}
