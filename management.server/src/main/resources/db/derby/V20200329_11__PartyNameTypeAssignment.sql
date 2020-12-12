CREATE TABLE PartyNameTypeAssignment 
(
		PartyNameType			VARCHAR(128)		NOT NULL
	,	PartyTypeId				INT				NOT NULL
	,	Efd						TIMESTAMP		NOT NULL
	,	Etd						TIMESTAMP
	,	Comment					VARCHAR(256)
	,	CONSTRAINT PARTY_NAME_TYPE_ASSIGNMENT_PK
		PRIMARY KEY
		(
			PartyNameType
		,	PartyTypeId
		,	Efd
		)
	,	CONSTRAINT PARTY_NAME_TYPE_ASSIGNMENT_PARTY_NAME_TYPE_FK
		FOREIGN KEY
		(
			PartyNameType
		)
		REFERENCES PartyNameType
		(
			Name
		)
	,	CONSTRAINT PARTY_NAME_TYPE_ASSIGNMENT_PARTY_TYPE_FK
		FOREIGN KEY
		(
			PartyTypeId
		)
		REFERENCES PartyType
		(
			PartyTypeId
		)
)