CREATE TABLE relationship
(
		relationship_id			BIGINT			NOT NULL		IDENTITY(1, 1) CONSTRAINT RELATIONSHIP_PK PRIMARY KEY
	,	relationship_type_id	INT				NOT NULL
	,	from_party_id			BIGINT			NOT NULL		
	,	to_party_id				BIGINT			NOT NULL
	,	efd						DATETIME		NOT NULL
	,	etd						DATETIME
	,	processed				DATETIME2		NOT NULL 		CONSTRAINT RELATIONSHIP_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT RELATIONSHIP_TYPE_ID_FK
		FOREIGN KEY
		(
			relationship_type_id
		)
		REFERENCES relationship_type
		(
			type_id
		)
	,	CONSTRAINT RELATIONSHIP_FROM_FK 
		FOREIGN KEY
		(
			from_party_id
		)
		REFERENCES party
		(
			party_id
		)
	,	CONSTRAINT RELATIONSHIP_TO_FK 
		FOREIGN KEY 
		(
			to_party_id
		)
		REFERENCES party
		(
			party_id
		)
)