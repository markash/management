CREATE TABLE asset 
(
		asset_id					BIGINT				NOT NULL		IDENTITY(1, 1)
	,	asset_number				VARCHAR(128)		NOT NULL
	,	asset_type					VARCHAR(128)		NOT NULL
	,	name						VARCHAR(128)		NOT NULL
	,	description					VARCHAR(256)		NULL
	,	processed					DATETIME2			NOT NULL 		CONSTRAINT ASSET_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT ASSET_PK
		PRIMARY KEY
		(
			asset_id
		)
	,	CONSTRAINT ASSET_NUMBER_UQ
		UNIQUE
		(
			asset_number
		)
	,	CONSTRAINT ASSET_ASSET_TYPE_FK
		FOREIGN KEY
		(
			asset_type
		)
		REFERENCES asset_type
		(
			name
		)
)