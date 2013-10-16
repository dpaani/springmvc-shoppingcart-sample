Shopping Cart Application Using Spring MVC, AngularJS and Bootstrap
--------------------------------------------------------------------------------

Problem
-----------------

TAXES PROBLEM
Basic sales tax is applicable at a rate of 10% on all goods, except books, food,
and medical products that are exempt. Import duty is an additional sales tax
applicable on all imported goods at a rate of 5%, with no
exemptions.
When I purchase items I receive a receipt that lists the name of all the items and
their price (including tax), finishing with the total cost of the items, and the total
amounts of sales tax paid. The rounding rules for sales tax are that for a tax rate
of n%, a shelf price of p contains np/100 rounded up to the nearest 0.05 amount
of sales tax.

INPUTS:
	Input 1:
		1 book at 12.49
		1 music CD at 14.99
		1 chocolate bar at 0.85
	Input 2:
		1 imported box of chocolates at 10.00
		1 imported bottle of perfume at 47.50
	Input 3:
		1 imported bottle of perfume at 27.99
		1 bottle of perfume at 18.99
		1 packet of headache pills at 9.75
		1 box of imported chocolates at 11.25

OUTPUTS:
	Output 1:
		1 book : 12.49
		1 music CD: 16.49
		1 chocolate bar: 0.85
		Sales Taxes: 1.50
		Total: 29.83
	Output 2:
		1 imported box of chocolates: 10.50
		1 imported bottle of perfume: 54.65
		Sales Taxes: 7.65
		Total: 65.15
	Output 3:

Solution
-----------------
Implemented a simple shopping cart application , which can load shopping cart data from the static file ,
caluclate total and sales tax and show purchase receipt.

Assumption
-------------------
1. No security required
2. No database required
3. Shopping cart data will be stroed temporarily
4. Implementation can be done using any latest technology and this solution uses Spring
5. Require maven to run this application

To get started, please complete the following steps:

1. Download/checkout project
2. Extract the file to a folder
3. Go to the exracted project root folder
4. Run this command from Terminal/Command Line - "run mvn clean install mvn jetty:run" and wait for server to start successfully
5. Open any broser and go to this url - http://localhost:8080/shoppingcart/


Technology Used
-----------------

Spring 3.2,maven,  Apache commons, jackson JSON API, bootstrap and AngularJS

Services Details
----------------

1. Loads bucket to shopping cart
	URL Pattern - GET http://localhost:8080/shoppingcart/loadcart/{bucket}
	Sample URL - GET http://localhost:8080/shoppingcart/loadcart/1
	Bucket values : 1, 2, 3

	Response -  HTTP 200

2. GET http://localhost:8080/shoppingcart/viewcart

3. View Bucket input data

	URL Pattern -  GET http://localhost:8080/shoppingcart/viewbucketinput?bucket={bucket}
	Sample URL - GET http://localhost:8080/shoppingcart/viewbucketinput?bucket=1

	Bucket values : 1, 2, 3

	Sample Response
	---------------
	[
		"1 book at 12.49",
		"1 music CD at 14.99",
		"1 chocolate bar at 0.85"
	]


4. View purchase receipt ( this service will return data that is currently stored in shopping cart)
	URL pattern - http://localhost:8080/shoppingcart/report/purchase

	Sample Response
	----------------
		{
	   lineItems:[
	      {
	         product:{
	            id:0,
	            description:"book",
	            price:12.49,
	            imported:false
	         },
	         quantity:1,
	         amount:12.49,
	         totalCost:12.49
	      },
	      {
	         product:{
	            id:0,
	            description:"music CD",
	            price:14.99,
	            imported:false
	         },
	         quantity:1,
	         amount:14.99,
	         totalCost:16.49
	      },
	      {
	         product:{
	            id:0,
	            description:"chocolate bar",
	            price:0.85,
	            imported:false
	         },
	         quantity:1,
	         amount:0.85,
	         totalCost:0.84
	      }
	   ],
	   salesTax:1.5,
	   total:29.83
	}

5. Add new line items to shopping cart -
	URL Pattern - POST hhttp://localhost:8080/shoppingcart/addcart

	Request payload sample
	--------------------
	1 music MP3 CD1 at 20.00

	After adding data, please use http://localhost:8080/shoppingcart/report/purchase to view data in shopping cart

6. Error response - In case of exception, exception object will be converted to ErrorResponse object and return appropriate HTTP error code back to client
    Sample invalid ERROR URL - http://localhost:8080/shoppingcart/loadcart/4

	Sample Error response
	------------------------
	{
	   "errorReferenceId":"81db7119-4a75-4cbb-a0aa-4b64211afeef",
	   "name":null,
	   "type":"NOT_FOUND",
	   "message":"class path resource [data/input11] cannot be opened because it does not exist"
	}
