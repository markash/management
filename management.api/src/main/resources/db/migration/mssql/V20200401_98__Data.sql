DECLARE
		@FALSE			BIT = 0
	,	@TRUE			BIT = 1;

INSERT INTO WORK_ORDER_TYPE 
(
		NAME
	,	DESCRIPTION
	,	ESTIMATE
	,	CAPITALIZE
) VALUES
	(
		'Build'
	,	'A build project to implement a new software or infrastructure system or capability.'
	,	@FALSE
	,	@TRUE		
	)
,	(
		'Maintenance'
	,	'A maintenance project for a software or infrastructure system.'
	,	@FALSE
	,	@FALSE		
	)
,	(
		'Build Estimate'
	,	'An estimation project for a build project'
	,	@TRUE
	,	@FALSE
	)
,	(
		'Maintenance Estimate'
	,	'An estimation project for a maintenance project'
	,	@TRUE
	,	@TRUE
	);

INSERT INTO WORK_ORDER_ROLE_TYPE 
(
		NAME
	,	DESCRIPTION
) VALUES
	(
		'Project Manager'
	,	'Project manager of a work order'		
	)
,	(
		'Delivery Manager'
	,	'Delivery manager of a work order'		
	)
,	(
		'Sponsor'
	,	'Sponsor for a work order'		
	)
,	(
		'Team Lead'
	,	'Team lead for a work order'		
	)
,	(
		'Developer'
	,	'Developer of a work order'		
	)
,	(
		'Tester'
	,	'Tester of a work order'		
	)
,	(
		'Epic Owner'
	,	'Epic owner'		
	);


INSERT INTO WORK_ORDER
(
		NAME
	,	SCHEDULED_START
	,	SCHEDULED_END
	,	ACTUAL_START
	,	ACTUAL_END
	,	WORK_ORDER_TYPE
) VALUES

	(
		'System X Build Project'
	,	'2019-08-01 00:00:00'
	,	'2019-02-28 00:00:00'
	,	'2019-09-15 00:00:00'
	,	'2020-03-04 00:00:00'
	,	'Build'
	);

INSERT INTO WORK_ORDER_ROLE
(
		WORK_ORDER_ID
	,	ASSIGNED_PARTY_ID
	,	WORK_ORDER_ROLE_TYPE
	,	EFD
	,	ETD
) VALUES
	(
		SCOPE_IDENTITY()
	,	(SELECT PARTY_ID FROM PERSON WHERE FIRST_NAME = 'Natasja' AND LAST_NAME = 'Ashworth')
	,	'Project Manager'
	,	'2019-09-15 00:00:00'
	,	'2020-03-04 00:00:00'
	)
,	(
		SCOPE_IDENTITY()
	,	(SELECT PARTY_ID FROM PERSON WHERE FIRST_NAME = 'Mark' AND LAST_NAME = 'Ashworth')
	,	'Developer'
	,	'2019-09-15 00:00:00'
	,	'2020-03-04 00:00:00'
	);



INSERT INTO WORK_ORDER
(
		NAME
	,	SCHEDULED_START
	,	SCHEDULED_END
	,	ACTUAL_START
	,	ACTUAL_END
	,	WORK_ORDER_TYPE
) VALUES

	(
		'System X Maintenance Project'
	,	'2020-04-01 00:00:00'
	,	NULL
	,	NULL
	,	NULL
	,	'Maintenance'
	);

INSERT INTO WORK_ORDER_ROLE
(
		WORK_ORDER_ID
	,	ASSIGNED_PARTY_ID
	,	WORK_ORDER_ROLE_TYPE
	,	EFD
	,	ETD
) VALUES
	(
		SCOPE_IDENTITY()
	,	(SELECT PARTY_ID FROM PERSON WHERE FIRST_NAME = 'Mark' AND LAST_NAME = 'Ashworth')
	,	'Developer'
	,	'2019-09-15 00:00:00'
	,	NULL
	)
