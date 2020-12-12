CREATE TABLE PartyIdentifier
(
		AssignedToPartyId	BIGINT				NOT NULL
	,	Identifier			VARCHAR(256)		NOT NULL
	,	Value				VARCHAR(512)		NOT NULL
	,	IssuedByPartyId		BIGINT
	,	Efd					TIMESTAMP			NOT NULL
	,	Etd					TIMESTAMP
	,	CONSTRAINT PARTY_IDENTIFIER_PK
		PRIMARY KEY
		(
			AssignedToPartyId
		,	Identifier
		,	Value
		)
	,	CONSTRAINT PARTY_IDENTIFIER_ASSIGNED_TO_FK
		FOREIGN KEY
		(
			AssignedToPartyId
		)
		REFERENCES Party
		(
			PartyId
		)
	,	CONSTRAINT PARTY_IDENTIFIER_ISSUED_BY_FK
		FOREIGN KEY
		(
			IssuedByPartyId
		)
		REFERENCES Party
		(
			PartyId
		)
);