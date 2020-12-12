CREATE TABLE WORK_ORDER_ROLE 
(
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
)