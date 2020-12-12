
INSERT INTO PARTY_TYPE (Type_Id, Type_Name) VALUES 
	(1, 'ExternalOrganization')
,	(2, 'Person')
,	(3,	'InternalOrganization');

INSERT INTO Relationship_Type (Type_Id, Type_Name) VALUES 
	(1,		'Employment')
,	(2,		'Team Member')
,	(3,		'Manager');

INSERT INTO PARTY_NAME_TYPE (NAME, DESCRIPTION) VALUES
			('GivenName', 		'Another term for first name')
		,	('MiddleName', 		'An additional given name')
		,	('MaidenName',		'The original surname of a married woman who uses her husband''s surname name after marriage.')
		,	('FamilyName',		'The family name of the person.')
		,	('CorporateName',	'Corporate name is the legal name under which a corporate body conducts its business.')
		,	('Nickname',		'A nickname is an alternative name for a party.')
		,	('TeamName',		'A team name for some internal organization of people.')
		,	('DepartmentName',	'A formal department name for an internal orgtanization of people.')
		;

INSERT INTO PARTY_NAME_TYPE_ASSIGNMENT (PARTY_NAME_TYPE, PARTY_TYPE_ID, EFD, COMMENT) VALUES
	(
			'GivenName'
		,	(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'Person')
		,	GETDATE()
		,	'A given name can be assigned to a person'	
	)
,	(
			'MiddleName'
		,	(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'Person')
		,	GETDATE()
		,	'A middle name can be assigned to a person'	
	)
,	(
			'MaidenName'
		,	(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'Person')
		,	GETDATE()
		,	'A maiden name can be assigned to a person'	
	)
,	(
			'FamilyName'
		,	(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'Person')
		,	GETDATE()
		,	'The family name can be assigned to a person'	
	)
,	(
			'TeamName'
		,	(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'InternalOrganization')
		,	GETDATE()
		,	'The team name can be assigned to an internal organization'	
	)
,	(
			'DepartmentName'
		,	(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'InternalOrganization')
		,	GETDATE()
		,	'The department name can be assigned to an internal organization'	
	)
,	(
			'CorporateName'
		,	(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'ExternalOrganization')
		,	GETDATE()
		,	'The corporate name can be assigned to an external organization'	
	)
,	(
			'Nickname'
		,	(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'ExternalOrganization')
		,	GETDATE()
		,	'The nickname can be assigned to an external organization'	
	)
,	(
			'Nickname'
		,	(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'Person')
		,	GETDATE()
		,	'The nickname can be assigned to a person'	
	)
;

/* Define the organizations for structure
 * Company (External Organization)
 *   |___HR Department (Internal Organization)
 *   |___IT Department
 *      |___HR Team
 *   |____|____Mark Ashworth (Employment, Team Member)
 *   |____|____Natasja Ashworth (Employment, Team Member)
 *
 */

/**************************************************************************************
 * External Organization: CIPRO
 **************************************************************************************/
INSERT INTO PARTY (TYPE_ID, PROCESSED) VALUES 
	(
			(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'ExternalOrganization')
		,	GETDATE()
	);
INSERT INTO EXTERNAL_ORGANIZATION (PARTY_ID, NAME, PROCESSED) VALUES 
	(
			SCOPE_IDENTITY()
		,	'CIPRO'
		,	GETDATE()
	);
INSERT INTO PARTY_IDENTIFIER_TYPE (MANAGEDBY_PARTY_ID, NAME, DESCRIPTION) VALUES 
	(
			SCOPE_IDENTITY()
		,	'CompanyRegistrationNumber'
		,	'The registration number issued by the South African CIPRO'
	);

 /**************************************************************************************
 * External Organization: Company
 **************************************************************************************/
DECLARE @vPartyId				BIGINT

INSERT INTO PARTY (TYPE_ID, PROCESSED) VALUES 
	(	
			(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'ExternalOrganization')
		, 	GETDATE()
	);

SET @vPartyId = SCOPE_IDENTITY();

INSERT INTO EXTERNAL_ORGANIZATION (PARTY_ID, NAME, PROCESSED) VALUES 
	(
			@vPartyId 
		,	'Company'
		,	GETDATE()
	);

INSERT INTO PARTY_NAME (LABELLED_PARTY_ID, NAME, PARTY_NAME_TYPE, ISSUEDBY_PARTY_ID, COMMENT, EFD, ETD) VALUES
	(
			@vPartyId
		,	'Company Plc'
		,	'CorporateName'
		,	(SELECT PARTY_ID FROM EXTERNAL_ORGANIZATION WHERE NAME = 'CIPRO')
		,	'Company incorporation name'
		,	'2000-01-01 00:00:00'
		,	NULL
	);

/* -------------------------------------------------------------------------------------
   Internal Organization: IT Department 
 --------------------------------------------------------------------------------------*/
INSERT INTO PARTY (TYPE_ID, PROCESSED) VALUES 	
	(
			(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'InternalOrganization')
		, 	GETDATE()
	);

SET @vPartyId = SCOPE_IDENTITY();

INSERT INTO INTERNAL_ORGANIZATION (PARTY_ID, NAME, PROCESSED) VALUES 
	(
			@vPartyId
		,	'IT Department'
		,	GETDATE()
	);

INSERT INTO PARTY_NAME (LABELLED_PARTY_ID, NAME, PARTY_NAME_TYPE, ISSUEDBY_PARTY_ID, COMMENT, EFD, ETD) VALUES
	(
			@vPartyId
		,	'Information Technology Department'
		,	'DepartmentName'
		,	NULL
		,	NULL
		,	'2000-01-01 00:00:00'
		,	NULL
	);

INSERT INTO RELATIONSHIP (RELATIONSHIP_TYPE_ID, FROM_PARTY_ID, TO_PARTY_ID, EFD, ETD) VALUES
	(
			1
		,	(SELECT PARTY_ID FROM EXTERNAL_ORGANIZATION WHERE NAME = 'Company')
		,	(SELECT PARTY_ID FROM INTERNAL_ORGANIZATION WHERE NAME = 'IT Department')
		,	'2000-01-01 00:00:00'
		,	NULL
	);

/* -------------------------------------------------------------------------------------
   Internal Organization: HR Team 
 --------------------------------------------------------------------------------------*/
INSERT INTO PARTY (TYPE_ID, PROCESSED) VALUES 
	(
			(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'InternalOrganization')
		,	GETDATE()
	);

SET @vPartyId = SCOPE_IDENTITY();

INSERT INTO INTERNAL_ORGANIZATION (PARTY_ID, NAME, PROCESSED) VALUES 
	(
			@vPartyId
		,	'HR Team'
		,	GETDATE()
	);

INSERT INTO PARTY_NAME (LABELLED_PARTY_ID, NAME, PARTY_NAME_TYPE, ISSUEDBY_PARTY_ID, COMMENT, EFD, ETD) VALUES
	(
			@vPartyId
		,	'HR Team'
		,	'TeamName'
		,	NULL
		,	NULL
		,	'2000-01-01 00:00:00'
		,	NULL
	);

INSERT INTO RELATIONSHIP (RELATIONSHIP_TYPE_ID, FROM_PARTY_ID, TO_PARTY_ID, EFD, ETD) VALUES
	(
			1
		,	(SELECT PARTY_ID FROM INTERNAL_ORGANIZATION WHERE NAME = 'IT Department')
		,	(SELECT PARTY_ID FROM INTERNAL_ORGANIZATION WHERE NAME = 'HR Team')
		,	'2000-01-01 00:00:00'
		,	NULL
	);

/* -------------------------------------------------------------------------------------
   Internal Organization: HR Department 
 --------------------------------------------------------------------------------------*/
INSERT INTO PARTY (TYPE_ID, PROCESSED) VALUES 
	(
			(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'InternalOrganization')
		,	GETDATE()
	);

SET @vPartyId = SCOPE_IDENTITY();

INSERT INTO INTERNAL_ORGANIZATION (PARTY_ID, NAME, PROCESSED) VALUES 
	(
			@vPartyId
		,	'HR Department'
		,	GETDATE()
	);

INSERT INTO PARTY_NAME (LABELLED_PARTY_ID, NAME, PARTY_NAME_TYPE, ISSUEDBY_PARTY_ID, COMMENT, EFD, ETD) VALUES
	(
			@vPartyId
		,	'HR Department'
		,	'DepartmentName'
		,	NULL
		,	NULL
		,	'2000-01-01 00:00:00'
		,	NULL
	);

INSERT INTO RELATIONSHIP (RELATIONSHIP_TYPE_ID, FROM_PARTY_ID, TO_PARTY_ID, EFD, ETD) VALUES
	(
			1
		,	(SELECT PARTY_ID FROM EXTERNAL_ORGANIZATION WHERE NAME = 'Company')
		,	(SELECT PARTY_ID FROM INTERNAL_ORGANIZATION WHERE NAME = 'HR Department')
		,	'2000-01-01 00:00:00'
		,	NULL
	);	


/**************************************************************************************
 * External Organization: Another
 **************************************************************************************/
INSERT INTO PARTY (TYPE_ID, PROCESSED) VALUES 
	(
			(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'ExternalOrganization')
		,	GETDATE()
	);

SET @vPartyId = SCOPE_IDENTITY();

INSERT INTO EXTERNAL_ORGANIZATION (PARTY_ID, NAME, PROCESSED) VALUES 
	(
			@vPartyId
		,	'Another'
		,	GETDATE()
	);

/**************************************************************************************
 * External Organization: Home Affairs
 **************************************************************************************/
INSERT INTO PARTY (TYPE_ID, PROCESSED) VALUES 
	(
			(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'ExternalOrganization')
		,	GETDATE()
	);

SET @vPartyId = SCOPE_IDENTITY();

INSERT INTO EXTERNAL_ORGANIZATION (PARTY_ID, NAME, PROCESSED) VALUES 
	(
			@vPartyId
		,	'Home Affairs'
		,	GETDATE()
	);
INSERT INTO PARTY_IDENTIFIER_TYPE (MANAGEDBY_PARTY_ID, NAME, DESCRIPTION) VALUES (
			@vPartyId
		,	'IdentityNumber'
		,	'The identity number issued by the South African Home Affairs Department'
	);



INSERT INTO PARTY_IDENTIFIER_TYPE (MANAGEDBY_PARTY_ID, NAME, DESCRIPTION) VALUES (NULL,	'EmployeeNumber', 'The employee number assigned by the company');

/**************************************************************************************
 * Person: Mark Ashworth
 **************************************************************************************/
INSERT INTO PARTY (TYPE_ID, PROCESSED) VALUES 
	(
			(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'Person')
		, 	GETDATE()
	);

SET @vPartyId = SCOPE_IDENTITY();

INSERT INTO PERSON (PARTY_ID, First_Name, Last_Name, BIRTH_DATE, PROCESSED) VALUES 
	(
			@vPartyId
		,	'Mark'
		,	'Ashworth'
		,	'1974-05-31'
		,	GETDATE()
	);

INSERT INTO PARTY_IDENTIFIER (ASSIGNEDTO_PARTY_ID, IDENTIFIER, VALUE, ISSUEDBY_PARTY_ID, EFD) VALUES
	(
			(SELECT PARTY_ID FROM PERSON WHERE Last_Name = 'Ashworth' AND BIRTH_DATE = '1974-05-31')
		,	'IdentityNumber'
		,	'740531'
		,	(SELECT PARTY_ID FROM EXTERNAL_ORGANIZATION WHERE Name = 'Home Affairs')
		,	GETDATE()
	);

INSERT INTO PARTY_NAME (LABELLED_PARTY_ID, NAME, PARTY_NAME_TYPE, ISSUEDBY_PARTY_ID, COMMENT, EFD, ETD) VALUES
	(
			(SELECT PARTY_ID FROM PERSON WHERE LAST_NAME = 'Ashworth' AND BIRTH_DATE = '1974-05-31')
		,	'Mark'
		,	'GivenName'
		,	NULL
		,	'First name'
		,	'1974-05-31 00:00:00'
		,	NULL
	);

INSERT INTO PARTY_NAME (LABELLED_PARTY_ID, NAME, PARTY_NAME_TYPE, ISSUEDBY_PARTY_ID, COMMENT, EFD, ETD) VALUES
	(
			(SELECT PARTY_ID FROM PERSON WHERE LAST_NAME = 'Ashworth' AND BIRTH_DATE = '1974-05-31')
		,	'Phillip'
		,	'MiddleName'
		,	NULL
		,	'Middle name'
		,	'1974-05-31 00:00:00'
		,	NULL
	);

INSERT INTO PARTY_NAME (LABELLED_PARTY_ID, NAME, PARTY_NAME_TYPE, ISSUEDBY_PARTY_ID, COMMENT, EFD, ETD) VALUES
	(
			(SELECT PARTY_ID FROM PERSON WHERE LAST_NAME = 'Ashworth' AND BIRTH_DATE = '1974-05-31')
		,	'Ashworth'
		,	'FamilyName'
		,	NULL
		,	NULL
		,	'1974-05-31 00:00:00'
		,	NULL
	);

INSERT INTO RELATIONSHIP (RELATIONSHIP_TYPE_ID, FROM_PARTY_ID, TO_PARTY_ID, EFD, ETD) VALUES
	(
			1
		,	(SELECT PARTY_ID FROM EXTERNAL_ORGANIZATION WHERE NAME = 'Another')
		,	(SELECT PARTY_ID FROM PERSON WHERE First_Name = 'Mark' AND Last_Name = 'Ashworth')
		,	'2001-01-01 00:00:00'
		,	'2008-11-30 23:59:59'
	);

INSERT INTO RELATIONSHIP (RELATIONSHIP_TYPE_ID, FROM_PARTY_ID, TO_PARTY_ID, EFD, ETD) VALUES
	(
			(SELECT TYPE_ID FROM RELATIONSHIP_TYPE WHERE TYPE_NAME = 'Employment')
		,	(SELECT PARTY_ID FROM EXTERNAL_ORGANIZATION WHERE NAME = 'Company')
		,	(SELECT PARTY_ID FROM PERSON WHERE First_Name = 'Mark' AND Last_Name = 'Ashworth')
		,	'2008-12-01 00:00:00'
		,	NULL
	);

INSERT INTO RELATIONSHIP (RELATIONSHIP_TYPE_ID, FROM_PARTY_ID, TO_PARTY_ID, EFD, ETD) VALUES
	(
			(SELECT TYPE_ID FROM RELATIONSHIP_TYPE WHERE TYPE_NAME = 'Team Member')
		,	(SELECT PARTY_ID FROM INTERNAL_ORGANIZATION WHERE NAME = 'HR Team')
		,	(SELECT PARTY_ID FROM PERSON WHERE First_Name = 'Mark' AND Last_Name = 'Ashworth')
		,	'2008-12-01 00:00:00'
		,	NULL
	);

/**************************************************************************************
 * Person: Natasja Ashworth
 **************************************************************************************/
INSERT INTO PARTY (TYPE_ID, PROCESSED) VALUES 
	(
			(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'Person')
		,	GETDATE()
	);

SET @vPartyId = SCOPE_IDENTITY();

INSERT INTO PERSON (PARTY_ID, First_Name, Last_Name, BIRTH_DATE, PROCESSED) VALUES 
	(
			@vPartyId
		,	'Natasja'
		,	'Ashworth'
		,	'1975-01-10'
		,	GETDATE()
	);
INSERT INTO PARTY_IDENTIFIER (ASSIGNEDTO_PARTY_ID, IDENTIFIER, VALUE, ISSUEDBY_PARTY_ID, EFD) VALUES
	(
			(SELECT PARTY_ID FROM PERSON WHERE Last_Name = 'Ashworth' AND BIRTH_DATE = '1975-01-10')
		,	'IdentityNumber'
		,	'750110'
		,	(SELECT PARTY_ID FROM EXTERNAL_ORGANIZATION WHERE Name = 'Home Affairs')
		,	GETDATE()
	);

INSERT INTO PARTY_NAME (LABELLED_PARTY_ID, NAME, PARTY_NAME_TYPE, ISSUEDBY_PARTY_ID, COMMENT, EFD, ETD) VALUES
	(
			(SELECT PARTY_ID FROM PERSON WHERE LAST_NAME = 'Ashworth' AND BIRTH_DATE = '1975-01-10')
		,	'Natasja'
		,	'GivenName'
		,	NULL
		,	NULL
		,	'1975-01-10 00:00:00'
		,	NULL
	);

INSERT INTO PARTY_NAME (LABELLED_PARTY_ID, NAME, PARTY_NAME_TYPE, ISSUEDBY_PARTY_ID, COMMENT, EFD, ETD) VALUES
	(
			(SELECT PARTY_ID FROM PERSON WHERE LAST_NAME = 'Ashworth' AND BIRTH_DATE = '1975-01-10')
		,	'Olivier'
		,	'FamilyName'
		,	NULL
		,	NULL
		,	'1975-01-10 00:00:00'
		,	'2014-12-06 23:59:59'
	);

INSERT INTO PARTY_NAME (LABELLED_PARTY_ID, NAME, PARTY_NAME_TYPE, ISSUEDBY_PARTY_ID, COMMENT, EFD, ETD) VALUES
	(
			(SELECT PARTY_ID FROM PERSON WHERE LAST_NAME = 'Ashworth' AND BIRTH_DATE = '1975-01-10')
		,	'Ashworth'
		,	'FamilyName'
		,	NULL
		,	NULL
		,	'2014-12-07 00:00:00'
		,	NULL
	);

INSERT INTO RELATIONSHIP (RELATIONSHIP_TYPE_ID, FROM_PARTY_ID, TO_PARTY_ID, EFD, ETD) VALUES
	(
			(SELECT TYPE_ID FROM RELATIONSHIP_TYPE WHERE TYPE_NAME = 'Employment')
		,	(SELECT PARTY_ID FROM EXTERNAL_ORGANIZATION WHERE NAME = 'Company')
		,	(SELECT PARTY_ID FROM PERSON WHERE First_Name = 'Natasja' AND Last_Name = 'Ashworth')
		,	'2008-12-01 00:00:00'
		,	NULL
	);

INSERT INTO RELATIONSHIP (RELATIONSHIP_TYPE_ID, FROM_PARTY_ID, TO_PARTY_ID, EFD, ETD) VALUES
	(
			(SELECT TYPE_ID FROM RELATIONSHIP_TYPE WHERE TYPE_NAME = 'Team Member')
		,	(SELECT PARTY_ID FROM INTERNAL_ORGANIZATION WHERE NAME = 'HR Team')
		,	(SELECT PARTY_ID FROM PERSON WHERE First_Name = 'Natasja' AND Last_Name = 'Ashworth')
		,	'2008-12-01 00:00:00'
		,	NULL
	);

/**************************************************************************************
 * Person: Mark Watney
 **************************************************************************************/
INSERT INTO PARTY (TYPE_ID, PROCESSED) VALUES 
	(
			(SELECT TYPE_ID FROM PARTY_TYPE WHERE TYPE_NAME = 'Person')
		,	GETDATE()
	);

SET @vPartyId = SCOPE_IDENTITY();

INSERT INTO PERSON (PARTY_ID, First_Name, Last_Name, BIRTH_DATE, PROCESSED) VALUES 
	(
			@vPartyId
		,	'Mark'
		,	'Watney'
		,	'1975-05-10'
		,	GETDATE()
	);