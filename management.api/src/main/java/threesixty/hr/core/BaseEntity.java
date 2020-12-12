package threesixty.hr.core;

import java.time.LocalDateTime;

/**
 * Base entity
 * @author Mark Ashworth
 */
public interface BaseEntity {

	/**
	 * The date and time the entity was processed
	 * @return The processed date & time
	 */
	LocalDateTime getProcessed();
	void setProcessed(final LocalDateTime processed);
}
