CREATE TABLE party_name
(
		labelled_party_id		BIGINT				NOT NULL
	,	name					VARCHAR(256)		NOT NULL
	,	party_name_type			VARCHAR(128)		NOT NULL
	,	issuedby_party_id		BIGINT
	,	comment					VARCHAR(256)
	,	efd						DATETIME			NOT NULL
	,	etd						DATETIME
	,	processed				DATETIME2			NOT NULL 		CONSTRAINT PARTY_NAME_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT PARTY_NAME_PK
		PRIMARY KEY
		(
			labelled_party_id
		,	name
		)
	,	CONSTRAINT PARTY_NAME_LABELLED_BY_FK
		FOREIGN KEY
		(
			labelled_party_id
		)
		REFERENCES party
		(
			party_id
		)
	,	CONSTRAINT PARTY_NAME_ISSUED_BY_FK
		FOREIGN KEY
		(
			issuedby_party_id
		)
		REFERENCES party
		(
			party_id
		)
	,	CONSTRAINT PARTY_NAME_TYPE_FK
		FOREIGN KEY
		(
			party_name_type
		)
		REFERENCES party_name_type
		(
			name
		)
)