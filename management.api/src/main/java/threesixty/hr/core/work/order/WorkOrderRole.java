package threesixty.hr.core.work.order;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import threesixty.hr.core.BaseEntity;
import threesixty.hr.core.EffectiveDateEntity;
import threesixty.hr.core.party.Party;

@Entity
@Table(name = "WORK_ORDER_ROLE")
public class WorkOrderRole extends PanacheEntityBase implements BaseEntity, EffectiveDateEntity, Serializable {
	private static final long serialVersionUID = -2690159999633105175L;

	@Id
	@Column(name = "WORK_ORDER_ID")
	private Long workOrderId;
	
	@Id
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ASSIGNED_PARTY_ID")
	private Party assignedTo;
	
	@Id
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WORK_ORDER_ROLE_TYPE")
	private WorkOrderRoleType roleType;
	
	@Column(name = "EFD")
	private LocalDateTime effectiveFrom;
	
	@Column(name = "ETD")
	private LocalDateTime effectiveTo;
	
	@Column(name = "PROCESSED")
	private LocalDateTime processed;
	
	public Long getWorkOrderId() { return workOrderId; }
	public void setWorkOrderId(Long workOrderId) { this.workOrderId = workOrderId; }
	
	public Party getAssignedTo() { return assignedTo; }
	public void setAssignedTo(final Party assignedTo) { this.assignedTo = assignedTo; }
	
	public WorkOrderRoleType getRoleType() { return roleType; }
	public void setRoleType(final WorkOrderRoleType roleType) { this.roleType = roleType; }
	
	@Override
	public LocalDateTime getEffectiveFrom() { return this.effectiveFrom; }
	@Override
	public void setEffectiveFrom(final LocalDateTime effectiveFrom) { this.effectiveFrom = effectiveFrom; }
	
	@Override
	public LocalDateTime getEffectiveTo() { return this.effectiveTo; }
	@Override
	public void setEffectiveTo(final LocalDateTime effectiveTo) { this.effectiveTo = effectiveTo; }

	@Override
	public LocalDateTime getProcessed() { return this.processed; }
	@Override
	public void setProcessed(LocalDateTime processed) { this.processed = processed; }
}


/**
WORK_ORDER_ID				BIGINT
,	ASSIGNED_PARTY_ID			BIGINT
,	WORK_ORDER_ROLE_TYPE		VARCHAR(128)
,	EFD							TIMESTAMP			NOT NULL
,	ETD							TIMESTAMP
,	PROCESSED					TIMESTAMP			NOT NULL 		DEFAULT CURRENT_TIMESTAMP
,	CONSTRAINT WORK_ORDER_ROLE_PK
	PRIMARY KEY
	(
		WORK_ORDER_ID
	,	ASSIGNED_PARTY_ID
	,	WORK_ORDER_ROLE_TYPE
	)

*/