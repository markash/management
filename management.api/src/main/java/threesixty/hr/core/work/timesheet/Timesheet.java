package threesixty.hr.core.work.timesheet;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import threesixty.hr.core.BaseEntity;
import threesixty.hr.core.date.DateRange;
import threesixty.hr.core.party.Party;
import threesixty.hr.core.work.order.WorkOrder;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "TIMESHEET")
public class Timesheet extends PanacheEntityBase implements BaseEntity {

	@Id @Column(name = "TIMESHEET_ID") @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WORK_ORDER_ID")
	private WorkOrder workOrder;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUBMIT_PARTY_ID")
	private Party submitter;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "APPROVE_PARTY_ID")
	private Party approver;
	
	//@OneToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name = "ACTIVITY_ID")
	//private Activity activity;
	
	@Column(name = "ACTIVITY_START")
	private LocalDateTime activityStart;
	
	@Column(name = "ACTIVITY_END")
	private LocalDateTime activityEnd;
	
	@Column(name = "ACTIVITY_DURATION")
	private Integer activityDuration;
	
	@Column(name = "COMMENT")
	private String comment;
		
	@Column(name = "PROCESSED")
	private LocalDateTime processed;
	
	@Column(name = "APPROVED")
	private LocalDateTime approved;
	
	@Override
	public LocalDateTime getProcessed() { return this.processed; }
	@Override
	public void setProcessed(LocalDateTime processed) { this.processed = processed; }
	
	public LocalDateTime getApproved() { return approved; }
	public void setApproved(LocalDateTime approved) { this.approved = approved; }
	
	public boolean hasActivityDateRange() {

		return Objects.nonNull(getActivityStart()) && Objects.nonNull(getActivityEnd());
	}

	public DateRange getActivityDateRange() {

		return DateRange.Builder
				.from(this.activityStart)
				.to(this.activityEnd).build();
	}



	public static List<Timesheet> findBySubmitter(
			final Long partyId,
			final DateRange dateRange) {
		
		final String query = 
					"submitter.partyId = :partyId " + 
				"AND activityStart    >= :from " +
				"AND activityEnd      <= :to";
		
		final Parameters parameters = 
				Parameters
					.with("partyId", partyId)
					.and("from", dateRange.getFrom())
					.and("to", dateRange.getTo());
		
        return list(query, parameters);
    }
}
