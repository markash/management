package threesixty.hr.core.work.order;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import threesixty.hr.core.TypeEntity;

@Entity
@Table(name = "WORK_ORDER_ROLE_TYPE")
public class WorkOrderRoleType extends PanacheEntityBase implements TypeEntity {

	@Id
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PROCESSED")
	private LocalDateTime processed;
	
	@Override
	public String getName() { return this.name; }
	@Override
	public void setName(final String name) { this.name = name; }
	
	@Override
	public String getDescription() { return this.description; }
	@Override
	public void setDescription(String description) { this.description = description; }

	@Override
	public LocalDateTime getProcessed() { return this.processed; }
	@Override
	public void setProcessed(LocalDateTime processed) { this.processed = processed; }
}
