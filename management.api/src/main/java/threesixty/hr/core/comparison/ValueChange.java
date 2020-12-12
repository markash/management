package threesixty.hr.core.comparison;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ValueChange extends PropertyChange {

	private Object left;
	private Object right;

	public ValueChange() {
		super();
	}

	public ValueChange(
			final String propertyName,
			final Object left,
			final Object right) {

		super(propertyName);

		this.left = left;
		this.right = right;
	}

	@Builder
	public static ValueChange create(
			final String propertyName,
			final Object left,
			final Object right) {
		
		return new ValueChange(propertyName, left, right);
	}
	
	public boolean isSame() {
		
		return Objects.equals(left, right);
	}

	public boolean isDifferent() {
		
		return !Objects.equals(left, right);
	}
}
