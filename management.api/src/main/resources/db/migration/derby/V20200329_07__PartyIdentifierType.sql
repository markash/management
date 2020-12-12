CREATE TABLE Party_Identifier_Type
(
		Name						VARCHAR(128)		NOT NULL
	,	Description					VARCHAR(256)
	,	ManagedBy_Party_Id			BIGINT
	,	Processed					TIMESTAMP		NOT NULL 		DEFAULT CURRENT_TIMESTAMP
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_PK
		PRIMARY KEY
		(
			Name
		)
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_MANAGED_BY_FK
		FOREIGN KEY
		(
			ManagedBy_Party_Id
		)
		REFERENCES Party
		(
			Party_Id
		)
);