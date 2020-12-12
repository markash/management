CREATE TABLE external_organization
(
		party_id				BIGINT			NOT NULL
	,	name					VARCHAR(128)	NOT NULL
	,	registration_date		DATE
	,	processed				DATETIME2		NOT NULL 		CONSTRAINT EXTERNAL_ORGANIZATION_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT EXTERNAL_ORG_PARTY_PK
		PRIMARY KEY
		(
			party_id
		)
	,	CONSTRAINT EXTERNAL_ORG_PARTY_FK
		FOREIGN KEY
		(
			party_id
		)
		REFERENCES party
		(
			party_id
		)
);

CREATE TABLE internal_organization
(
		party_id				BIGINT			NOT NULL
	,	name					VARCHAR(128)	NOT NULL
	,	registration_date		DATE
	,	processed				DATETIME2		NOT NULL 		CONSTRAINT INTERNAL_ORGANIZATION_PROCESSED_DF DEFAULT GETDATE()
	,	CONSTRAINT INTERNAL_ORG_PARTY_PK
		PRIMARY KEY
		(
			party_id
		)
	,	CONSTRAINT INTERNAL_ORG_PARTY_FK
		FOREIGN KEY
		(
			party_id
		)
		REFERENCES Party
		(
			party_id
		)
);