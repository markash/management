CREATE TABLE asset_type
(
		name					VARCHAR(128)		NOT NULL
	,	description				VARCHAR(256)
	,	processed				DATETIME2			NOT NULL 		CONSTRAINT ASSET_TYPE_PROCESSED_DF DEFAULT CURRENT_TIMESTAMP
	,	CONSTRAINT ASSET_TYPE_PK
		PRIMARY KEY
		(
			name
		)
)