CREATE TABLE PartyIdentifierTypeAssignment 
(
		PartyIdentifierType		VARCHAR(128)		NOT NULL
	,	PartyTypeId				INT				NOT NULL
	,	Efd						TIMESTAMP		NOT NULL
	,	Etd						TIMESTAMP
	,	Comment					VARCHAR(256)
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_ASSIGNMENT_PK
		PRIMARY KEY
		(
			PartyIdentifierType
		,	PartyTypeId
		,	Efd
		)
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_ASSIGNMENT_PARTY_IDENTIFIER_TYPE_FK
		FOREIGN KEY
		(
			PartyIdentifierType
		)
		REFERENCES PartyIdentifierType
		(
			Name
		)
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_ASSIGNMENT_PARTY_TYPE_FK
		FOREIGN KEY
		(
			PartyTypeId
		)
		REFERENCES PartyType
		(
			PartyTypeId
		)
)