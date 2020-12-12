package threesixty.hr.core.work.order;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import threesixty.hr.core.TypeEntity;

@Builder
@ToString
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name", callSuper = false)
@Entity @Table(name = "WORK_ORDER_TYPE")
public class WorkOrderType extends PanacheEntityBase implements TypeEntity {

	@Id
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "ESTIMATE")
	private Boolean estimate;
	
	@Column(name = "CAPITALIZE")
	private Boolean capitalize;
	
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

	public Boolean isEstimate() { return estimate; }
	public void setEstimate(Boolean estimate) { this.estimate = estimate; }
	
	public Boolean isCapitalize() { return capitalize; }
	public Boolean getCapitalize() { return isCapitalize(); }
	
	@Override
	public LocalDateTime getProcessed() { return this.processed; }
	@Override
	public void setProcessed(LocalDateTime processed) { this.processed = processed; }
}
