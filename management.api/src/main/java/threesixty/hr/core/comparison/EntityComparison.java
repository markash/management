package threesixty.hr.core.comparison;

import java.util.ArrayList;
import java.util.List;

public class EntityComparison<T> {
    
    private List<PropertyComparison<T>> comparisons;

    private EntityComparison(
		final Class<T> entityClass,
		final String...propertyNames) throws NoSuchMethodException {

        this.comparisons = new ArrayList<>();
        
        for(String propertyName : propertyNames) {
        
            comparisons.add(PropertyComparison.of(entityClass, propertyName));
        }
	}

	public static <T> EntityComparison<T> of(
		final Class<T> entityClass, 
		final String...propertyNames) throws PropertyComparisonException {

        try {
            
            return new EntityComparison<>(entityClass, propertyNames);
        } catch (NoSuchMethodException e) {

            throw new PropertyComparisonException("Unable to compare entity", e);
        }
    }
    
    public ValueChanges compare(
		final T firstEntity, 
		final T secondEntity) throws PropertyComparisonException {

		try {

            ValueChanges result = new ValueChanges();
            
            for (PropertyComparison<T> comparison : comparisons) {
                
                result.add(comparison.compare(firstEntity, secondEntity));
            }

            return result;
		} catch (Exception e) {
			throw new PropertyComparisonException("Unable to compare", e);
		}	
	}
}
