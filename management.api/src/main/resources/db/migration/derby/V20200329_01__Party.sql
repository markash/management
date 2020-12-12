CREATE TABLE Party
(
		Party_Id					BIGINT			NOT NULL		GENERATED ALWAYS AS IDENTITY CONSTRAINT PARTY_PK PRIMARY KEY
	,	Type_Id						INT 			NOT NULL
	,	Processed					TIMESTAMP		NOT NULL 		DEFAULT CURRENT_TIMESTAMP
	,	CONSTRAINT PARTY_TYPE_FK 
		FOREIGN KEY 
		(
			Type_Id
		)
		REFERENCES Party_Type 
		(
			Type_Id
		)
)