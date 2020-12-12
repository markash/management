CREATE TABLE Party_Identifier
(
		AssignedTo_Party_Id			BIGINT				NOT NULL
	,	Identifier					VARCHAR(256)		NOT NULL
	,	Value						VARCHAR(512)		NOT NULL
	,	IssuedBy_Party_Id			BIGINT
	,	Efd							TIMESTAMP			NOT NULL
	,	Etd							TIMESTAMP
	,	Processed					TIMESTAMP			NOT NULL 		DEFAULT CURRENT_TIMESTAMP
	,	CONSTRAINT PARTY_IDENTIFIER_PK
		PRIMARY KEY
		(
			AssignedTo_Party_Id
		,	Identifier
		,	Value
		)
	,	CONSTRAINT PARTY_IDENTIFIER_ASSIGNED_TO_FK
		FOREIGN KEY
		(
			AssignedTo_Party_Id
		)
		REFERENCES Party
		(
			Party_Id
		)
	,	CONSTRAINT PARTY_IDENTIFIER_ISSUED_BY_FK
		FOREIGN KEY
		(
			IssuedBy_Party_Id
		)
		REFERENCES Party
		(
			Party_Id
		)
);