CREATE TABLE PartyName
(
		LabelledByPartyId		BIGINT			NOT NULL
	,	Name					VARCHAR(256)	NOT NULL
	,	PartyNameType			VARCHAR(128)		NOT NULL
	,	IssuedByPartyId			BIGINT
	,	Comment					VARCHAR(256)
	,	Efd						TIMESTAMP		NOT NULL
	,	Etd						TIMESTAMP
	,	CONSTRAINT PARTY_NAME_PK
		PRIMARY KEY
		(
			LabelledByPartyId
		,	Name
		)
	,	CONSTRAINT PARTY_NAME_LABELLED_BY_FK
		FOREIGN KEY
		(
			LabelledByPartyId
		)
		REFERENCES Party
		(
			PartyId
		)
	,	CONSTRAINT PARTY_NAME_ISSUED_BY_FK
		FOREIGN KEY
		(
			IssuedByPartyId
		)
		REFERENCES Party
		(
			PartyId
		)
	,	CONSTRAINT PARTY_NAME_TYPE_FK
		FOREIGN KEY
		(
			PartyNameType
		)
		REFERENCES PartyNameType
		(
			Name
		)
)