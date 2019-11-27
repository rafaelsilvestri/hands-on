# About
This is a hands-on project built in a Design & Archtecture training

## WIP


#### Exercicies
We are gonna build an e-comerce application for a given customer. This software is gonna receive orders and process them from text files. Here is the layout

001|Customer Name|CPF|DELIVERY ADDRESS|PAYMENT METHOD (EXEMPLE: CREDIT CARD OR CASH)|
002|PRODUCT ID|PRODUCT NAME|VALUE|QTD
002|PRODUCT ID|PRODUCT NAME|VALUE|QTD

These order will arrive at once during the night and need to be processed as soon they are available. They are from partners, and systems that are still not able to call APIS.

The requirements known so far are:

A single file should contain a single order
for each file processed, system needs to outpput return a confirmation that the order was confirmed.
Consumers will later on want to query their orders and export it in differnt formats (TXT, PDF, .DOC)

Files will be received in different formats on the (XML, CSV, ETC)
The customer will ask to expose and API REST on the future to process order via http.

Several things can goes wrong like:
	- Orders without payment method
	- Orders with no items inside
	- No product price
	- Not minimal ammount of products (at least 3)

