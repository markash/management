package threesixty.hr.core.comparison;

public class PropertyComparison<T> {

	private PropertyAccessor<T> accessor;

	private PropertyComparison(
		final Class<T> entityClass,
		final String propertyName) throws NoSuchMethodException {

		this.accessor = 
			new PropertyAccessor<>(entityClass)
				.property(propertyName);
	}

	public static <T> PropertyComparison<T> of(
		final Class<T> entityClass, 
		final String propertyName) throws NoSuchMethodException {

		return new PropertyComparison<>(entityClass, propertyName);
	}

	public ValueChange compare(
		final T firstEntity, 
		final T secondEntity) throws PropertyComparisonException {

		try {

			return new ValueChange(
				accessor.getPropertyName(), 
				this.accessor.extract(firstEntity), 
				this.accessor.extract(secondEntity));

		} catch (Exception e) {
			throw new PropertyComparisonException("Unable to compare", e);
		}	
	}
}
