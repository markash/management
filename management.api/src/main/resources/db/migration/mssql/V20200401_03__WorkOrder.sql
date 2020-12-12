CREATE TABLE work_order 
(
		work_order_id				BIGINT				NOT NULL		IDENTITY(1, 1)
	,	name						VARCHAR(128)		NOT NULL
	,	scheduled_start				DATE
	,	scheduled_end				DATE
	,	actual_start				DATE
	,	actual_end					DATE
	,	work_order_type				VARCHAR(128)		NOT NULL
	,	processed					DATETIME2			NOT NULL 		CONSTRAINT WORK_ORDER_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT WORK_ORDER_PK
		PRIMARY KEY
		(
			work_order_id
		)
	,	CONSTRAINT WORK_ORDER_NAME_UQ
		UNIQUE
		(
			name
		)
	,	CONSTRAINT WORK_ORDER_WORK_ORDER_TYPE_FK
		FOREIGN KEY
		(
			work_order_type
		)
		REFERENCES work_order_type
		(
			name
		)
)