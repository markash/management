CREATE TABLE timesheet 
(
		timesheet_id				BIGINT				NOT NULL		IDENTITY(1, 1)
	,	work_order_id				BIGINT
	,	submit_party_id				BIGINT
	,	approve_party_id			BIGINT
	,	activity_id					BIGINT
	,	activity_start				DATETIME
	,	activity_end				DATETIME
	,	activity_duration			INT
	,	comment						VARCHAR(512)
	,	processed					DATETIME2							CONSTRAINT TIMESHEET_PROCESSED_DF DEFAULT GETDATE()
	,	approved  					DATETIME
	,	CONSTRAINT TIMESHEET_PK
		PRIMARY KEY
		(
			timesheet_id
		)
	,	CONSTRAINT TIMESHEET_WORK_ORDER_ID_FK
		FOREIGN KEY
		(
			work_order_id
		)
		REFERENCES work_order
		(
			work_order_id
		)
)