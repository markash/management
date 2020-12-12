CREATE TABLE party_name_type_assignment 
(
		party_name_type				VARCHAR(128)		NOT NULL
	,	party_type_id				INT					NOT NULL
	,	efd							DATETIME2			NOT NULL
	,	etd							DATETIME2
	,	comment						VARCHAR(256)
	,	processed					DATETIME2			NOT NULL 		CONSTRAINT PARTY_NAME_TYPE_ASSIGN_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT PARTY_NAME_TYPE_ASSIGNMENT_PK
		PRIMARY KEY
		(
			party_name_type
		,	party_type_id
		,	efd
		)
	,	CONSTRAINT PARTY_NAME_TYPE_ASSIGNMENT_PARTY_NAME_TYPE_FK
		FOREIGN KEY
		(
			party_name_type
		)
		REFERENCES party_name_type
		(
			name
		)
	,	CONSTRAINT PARTY_NAME_TYPE_ASSIGNMENT_PARTY_TYPE_FK
		FOREIGN KEY
		(
			party_type_id
		)
		REFERENCES party_type
		(
			type_id
		)
)