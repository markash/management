CREATE TABLE Relationship
(
		Relationship_Id			BIGINT			NOT NULL		GENERATED ALWAYS AS IDENTITY CONSTRAINT RELATIONSHIP_PK PRIMARY KEY
	,	Relationship_Type_Id	INT				NOT NULL
	,	From_Party_Id			BIGINT			NOT NULL		
	,	To_Party_Id				BIGINT			NOT NULL
	,	Efd						TIMESTAMP		NOT NULL
	,	Etd						TIMESTAMP
	,	Processed				TIMESTAMP		NOT NULL 		DEFAULT CURRENT_TIMESTAMP
	,	CONSTRAINT RELATIONSHIP_TYPE_ID_FK
		FOREIGN KEY
		(
			Relationship_Type_Id
		)
		REFERENCES Relationship_Type
		(
			Type_Id
		)
	,	CONSTRAINT RELATIONSHIP_FROM_FK 
		FOREIGN KEY (
			From_Party_Id
		)
		REFERENCES Party
		(
			Party_Id
		)
	,	CONSTRAINT RELATIONSHIP_TO_FK 
		FOREIGN KEY (
			To_Party_Id
		)
		REFERENCES Party
		(
			Party_Id
		)
)