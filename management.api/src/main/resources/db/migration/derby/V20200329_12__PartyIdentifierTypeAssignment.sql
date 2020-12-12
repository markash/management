CREATE TABLE Party_Identifier_Type_Assignment 
(
		Party_Identifier_Type		VARCHAR(128)		NOT NULL
	,	Party_Type_Id				INT					NOT NULL
	,	Efd							TIMESTAMP			NOT NULL
	,	Etd							TIMESTAMP
	,	Comment						VARCHAR(256)
	,	Processed					TIMESTAMP			NOT NULL 		DEFAULT CURRENT_TIMESTAMP
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_ASSIGNMENT_PK
		PRIMARY KEY
		(
			Party_Identifier_Type
		,	Party_Type_Id
		,	Efd
		)
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_ASSIGNMENT_PARTY_IDENTIFIER_TYPE_FK
		FOREIGN KEY
		(
			Party_Identifier_Type
		)
		REFERENCES Party_Identifier_Type
		(
			Name
		)
	,	CONSTRAINT PARTY_IDENTIFIER_TYPE_ASSIGNMENT_PARTY_TYPE_FK
		FOREIGN KEY
		(
			Party_Type_Id
		)
		REFERENCES Party_Type
		(
			Type_Id
		)
)