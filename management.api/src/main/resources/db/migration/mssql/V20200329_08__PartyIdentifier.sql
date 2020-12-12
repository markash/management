CREATE TABLE party_identifier
(
		assignedto_party_id			BIGINT				NOT NULL
	,	identifier					VARCHAR(256)		NOT NULL
	,	value						VARCHAR(512)		NOT NULL
	,	issuedby_party_id			BIGINT
	,	efd							DATETIME			NOT NULL
	,	etd							DATETIME
	,	processed					DATETIME2			NOT NULL 		CONSTRAINT PARTY_DENTIFIER_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT PARTY_IDENTIFIER_PK
		PRIMARY KEY
		(
			assignedto_party_id
		,	identifier
		,	value
		)
	,	CONSTRAINT PARTY_IDENTIFIER_ASSIGNED_TO_FK
		FOREIGN KEY
		(
			assignedto_party_id
		)
		REFERENCES party
		(
			party_id
		)
	,	CONSTRAINT PARTY_IDENTIFIER_ISSUED_BY_FK
		FOREIGN KEY
		(
			issuedby_party_id
		)
		REFERENCES party
		(
			party_id
		)
);