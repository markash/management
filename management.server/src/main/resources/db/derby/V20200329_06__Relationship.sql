CREATE TABLE Relationship
(
		RelationshipId		BIGINT			NOT NULL		GENERATED ALWAYS AS IDENTITY CONSTRAINT RELATIONSHIP_PK PRIMARY KEY
	,	RelationshipTypeId	INT				NOT NULL
	,	FromPartyId			BIGINT			NOT NULL		
	,	ToPartyId			BIGINT			NOT NULL
	,	Efd					TIMESTAMP		NOT NULL
	,	Etd					TIMESTAMP
	,	CONSTRAINT RELATIONSHIP_TYPE_ID_FK
		FOREIGN KEY
		(
			RelationshipTypeId
		)
		REFERENCES RelationshipType
		(
			RelationshipTypeId
		)
	,	CONSTRAINT RELATIONSHIP_FROM_FK 
		FOREIGN KEY (
			FromPartyId
		)
		REFERENCES Party
		(
			PartyId
		)
	,	CONSTRAINT RELATIONSHIP_TO_FK 
		FOREIGN KEY (
			ToPartyId
		)
		REFERENCES Party
		(
			PartyId
		)
)