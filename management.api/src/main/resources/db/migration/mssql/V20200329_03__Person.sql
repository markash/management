CREATE TABLE person
(
		party_id				BIGINT			NOT NULL
	,	first_name				VARCHAR(128)	NOT NULL
	,	last_name				VARCHAR(128)	NOT NULL
	,	birth_date				DATE
	,	processed				DATETIME2		NOT NULL 		CONSTRAINT PERSON_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT PERSON_PK PRIMARY KEY
		(
			party_id
		)
	,	CONSTRAINT PERSON_PARTY_FK
		FOREIGN KEY
		(
			party_id
		)
		REFERENCES party
		(
			party_id
		)
);