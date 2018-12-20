----------------------------------------------------------------
-----------------------------Server-----------------------------
----------------------------------------------------------------

port=9999
context-path=/example



----------------------------------------------------------------
----------------------------DATABASE----------------------------
----------------------------------------------------------------

user = "example"
password = "example"
name = "example"



----------------------------------------------------------------
-------------------------USER CREDENTIAL------------------------
----------------------------------------------------------------

-------------------------------------------------------
	User		Password		 Role
-------------------------------------------------------
"john@rokomari.com"		"123456" 	"admin"
"steiv@rokomari.com"		"123456"	"user"
"sen@rokomari.com"		"123456"	"user"
"henrrey@rokomari.com"		"123456"	"user"

NOTE: Only #Admin has the authority to delete any data(as for example)



----------------------------------------------------------------
-----------------------------TOKEN------------------------------
----------------------------------------------------------------

When a user login, a #TOKEN is given in the #HEADER part as #jwt_token
<<<<<<< HEAD
#TOKEN always start with "Bearer ". So the Ultimate token will be "Bearer "+generated_token
#TOKEN is added in HEADER as "jwt_token" key
=======

NOTE: 	#TOKEN always start with "Peace ". So the Ultimate token will be "Peace "+generated_token
	#TOKEN is added in HEADER as "Authorization" key
>>>>>>> branch 'master' of https://github.com/shacho0011/peace.git
		
