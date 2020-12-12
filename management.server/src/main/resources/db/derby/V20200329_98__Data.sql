
INSERT INTO PartyType (PartyTypeId, PartyTypeName) VALUES (1, 'Company');
INSERT INTO PartyType (PartyTypeId, PartyTypeName) VALUES (2, 'Person');

INSERT INTO RelationshipType (RelationshipTypeId, RelationshipTypeName) VALUES 
	(1,		'Employment')
,	(2,		'Team Member')
,	(3,		'Manager');

INSERT INTO PartyNameType (Name, Description) VALUES
	('GivenName', 		'Another term for first name')
,	('MiddleName', 		'An additional given name')
,	('MaidenName',		'The original surname of a married woman who uses her husband''s surname name after marriage.')
,	('FamilyName',		'The family name of the person.')
,	('CorporateName',	'Corporate name is the legal name under which a corporate body conducts its business.')
,	('Nickname',		'A nickname is an alternative name for a party.')
;

INSERT INTO PartyNameTypeAssignment (PartyNameType, PartyTypeId, Efd, Comment) VALUES
	(
			'GivenName'
		,	(SELECT PartyTypeId FROM PartyType WHERE PartyTypeName = 'Person')
		,	CURRENT_TIMESTAMP
		,	'A given name can be assigned to a person'	
	)
,	(
			'MiddleName'
		,	(SELECT PartyTypeId FROM PartyType WHERE PartyTypeName = 'Person')
		,	CURRENT_TIMESTAMP
		,	'A middle name can be assigned to a person'	
	)
,	(
			'MaidenName'
		,	(SELECT PartyTypeId FROM PartyType WHERE PartyTypeName = 'Person')
		,	CURRENT_TIMESTAMP
		,	'A maiden name can be assigned to a person'	
	)
,	(
			'FamilyName'
		,	(SELECT PartyTypeId FROM PartyType WHERE PartyTypeName = 'Person')
		,	CURRENT_TIMESTAMP
		,	'The family name can be assigned to a person'	
	)
,	(
			'CorporateName'
		,	(SELECT PartyTypeId FROM PartyType WHERE PartyTypeName = 'Company')
		,	CURRENT_TIMESTAMP
		,	'The corporate name can be assigned to a company'	
	)
,	(
			'Nickname'
		,	(SELECT PartyTypeId FROM PartyType WHERE PartyTypeName = 'Company')
		,	CURRENT_TIMESTAMP
		,	'The nickname can be assigned to a company'	
	)
,	(
			'Nickname'
		,	(SELECT PartyTypeId FROM PartyType WHERE PartyTypeName = 'Person')
		,	CURRENT_TIMESTAMP
		,	'The nickname can be assigned to a person'	
	)
;


INSERT INTO PARTY (PARTYTYPEID, PROCESSEDDATETIME) VALUES (1, CURRENT_TIMESTAMP);
INSERT INTO Organization (PartyId, Name, ProcessedDateTime) VALUES (IDENTITY_VAL_LOCAL(), 'Company', CURRENT_TIMESTAMP);

INSERT INTO PARTY (PARTYTYPEID, PROCESSEDDATETIME) VALUES (1, CURRENT_TIMESTAMP);
INSERT INTO Organization (PartyId, Name, ProcessedDateTime) VALUES (IDENTITY_VAL_LOCAL(), 'Another', CURRENT_TIMESTAMP);

INSERT INTO PARTY (PARTYTYPEID, PROCESSEDDATETIME) VALUES (1, CURRENT_TIMESTAMP);
INSERT INTO Organization (PartyId, Name, ProcessedDateTime) VALUES (IDENTITY_VAL_LOCAL(), 'Home Affairs', CURRENT_TIMESTAMP);
INSERT INTO PartyIdentifierType (ManagedByPartyId, Name, Description) VALUES (IDENTITY_VAL_LOCAL(),	'IdentityNumber', 'The identity number issued by the South African Home Affairs Department');

INSERT INTO PARTY (PARTYTYPEID, PROCESSEDDATETIME) VALUES (1, CURRENT_TIMESTAMP);
INSERT INTO Organization (PartyId, Name, ProcessedDateTime) VALUES (IDENTITY_VAL_LOCAL(), 'CIPRO', CURRENT_TIMESTAMP);
INSERT INTO PartyIdentifierType (ManagedByPartyId, Name, Description) VALUES (IDENTITY_VAL_LOCAL(), 'CompanyRegistrationNumber', 'The registration number issued by the South African CIPRO');

INSERT INTO PartyIdentifierType (ManagedByPartyId, Name, Description) VALUES (NULL,	'EmployeeNumber', 'The employee number assigned by the company');

INSERT INTO PARTY (PARTYTYPEID, PROCESSEDDATETIME) VALUES (2, CURRENT_TIMESTAMP);
INSERT INTO Person (PartyId, FirstName, LastName, DateOfBirth, ProcessedDateTime) VALUES (IDENTITY_VAL_LOCAL(), 'Mark', 'Ashworth', '1974-05-31', CURRENT_TIMESTAMP);
INSERT INTO PartyIdentifier (AssignedToPartyId, Identifier, Value, IssuedByPartyId, Efd) VALUES
	(
			(SELECT PartyId FROM Person WHERE LastName = 'Ashworth' AND DateOfBirth = '1974-05-31')
		,	'IdentityNumber'
		,	'740531'
		,	(SELECT PartyId FROM Organization WHERE Name = 'Home Affairs')
		,	CURRENT_TIMESTAMP
	);

INSERT INTO PARTY (PARTYTYPEID, PROCESSEDDATETIME) VALUES (2, CURRENT_TIMESTAMP);
INSERT INTO Person (PartyId, FirstName, LastName, DateOfBirth, ProcessedDateTime) VALUES (IDENTITY_VAL_LOCAL(), 'Natasja', 'Ashworth', '1975-01-10', CURRENT_TIMESTAMP);
INSERT INTO PartyIdentifier (AssignedToPartyId, Identifier, Value, IssuedByPartyId, Efd) VALUES
	(
			(SELECT PartyId FROM Person WHERE LastName = 'Ashworth' AND DateOfBirth = '1975-01-10')
		,	'IdentityNumber'
		,	'750110'
		,	(SELECT PartyId FROM Organization WHERE Name = 'Home Affairs')
		,	CURRENT_TIMESTAMP
	);





