CREATE TABLE PartyIdentifierType
(
		Name					VARCHAR(128)		NOT NULL
	,	Description				VARCHAR(256)
	,	ManagedByPartyId		BIGINT

	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_PK
		PRIMARY KEY
		(
			Name
		)
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_MANAGED_BY_FK
		FOREIGN KEY
		(
			ManagedByPartyId
		)
		REFERENCES Party
		(
			PartyId
		)
);