CREATE TABLE work_order_asset 
(
		work_order_id				BIGINT
	,	asset_id					BIGINT
	,	efd							DATETIME			NOT NULL
	,	etd							DATETIME
	,	processed					DATETIME2			NOT NULL 		CONSTRAINT WORK_ORDER_ASSET_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT WORK_ORDER_ASSET_PK
		PRIMARY KEY
		(
			work_order_id
		,	asset_id
		)
)