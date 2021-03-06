CREATE TABLE Party
(
		PartyId					BIGINT			NOT NULL		GENERATED ALWAYS AS IDENTITY CONSTRAINT PARTY_PK PRIMARY KEY
	,	PartyTypeId				INT 			NOT NULL
	,	ProcessedDateTime		TIMESTAMP		NOT NULL 		DEFAULT CURRENT_TIMESTAMP
	,	CONSTRAINT PARTY_TYPE_FK 
		FOREIGN KEY 
		(
			PartyTypeId
		)
		REFERENCES PartyType 
		(
			PartyTypeId
		)
)