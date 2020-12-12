package threesixty.hr.management.shared.services.work.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import threesixty.hr.management.shared.services.TypeEntity;

/**
 * 
 * @author Mark Ashworth
 */
@Builder
@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderType implements TypeEntity {

	private String name;
	private String description;
	private Boolean capitalize;
	private Boolean estimate;
	
	/**
	 * Whether the work order is capitalized in the financial accounting
	 * @return True if capitalized
	 */
	public Boolean isCapitalize() { return capitalize; }
	
	/**
	 * Whether the work order is an estimate
	 * @return True if an estimate
	 */
	public Boolean isEstimate() { return estimate; }
}
