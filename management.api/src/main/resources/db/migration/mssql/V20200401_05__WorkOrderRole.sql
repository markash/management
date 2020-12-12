CREATE TABLE work_order_role 
(
		work_order_id				BIGINT
	,	assigned_party_id			BIGINT
	,	work_order_role_type		VARCHAR(128)
	,	efd							DATETIME			NOT NULL
	,	etd							DATETIME
	,	processed					DATETIME2			NOT NULL 		CONSTRAINT WORK_ORDER_ROLE_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT WORK_ORDER_ROLE_PK
		PRIMARY KEY
		(
			work_order_id
		,	assigned_party_id
		,	work_order_role_type
		)
)