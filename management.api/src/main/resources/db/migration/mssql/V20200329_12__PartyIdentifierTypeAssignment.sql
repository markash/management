CREATE TABLE party_identifier_type_assignment 
(
		party_identifier_type		VARCHAR(128)		NOT NULL
	,	party_type_id				INT					NOT NULL
	,	efd							DATETIME			NOT NULL
	,	etd							DATETIME
	,	comment						VARCHAR(256)
	,	processed					DATETIME2			NOT NULL 		CONSTRAINT PARTY_IDENTIFIER_TYPE_ASSIGN_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_ASSIGNMENT_PK
		PRIMARY KEY
		(
			party_identifier_type
		,	party_type_id
		,	efd
		)
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_ASSIGNMENT_PARTY_IDENTIFIER_TYPE_FK
		FOREIGN KEY
		(
			party_identifier_type
		)
		REFERENCES party_identifier_type
		(
			name
		)
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_ASSIGNMENT_PARTY_TYPE_FK
		FOREIGN KEY
		(
			party_type_id
		)
		REFERENCES party_type
		(
			type_id
		)
)