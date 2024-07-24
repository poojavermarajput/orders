Spring Boot REST API project

Export project to Intellij and start the service using Spring Boot. Tomcat application will start on port : 8080

Project supports :
1. Adding and retrieving inventory
2. Adding offers for inventory
3. Adding and Retrieving orders

p.s : all invalid scenarios are not handled.

In order to succesfully create order we need to add inventory first otherwise order creation will fail because of validation check that verifies all inventory exists for an order.

1. Create Inventory : http://localhost:8080/items/item-add
2. Create Offers : http://localhost:8080/offers/offer [ Optional ] 
3. Create order http://localhost:8080/orders/order/
Other supported apis :
1. Get order by orderId : http://localhost:8080/orders/order/6 [ 6 is order id, replace with correct value ]
2. Get all orders :  http://localhost:8080/orders/order

Sample request body : 
1. http://localhost:8080/items/item-add 
 Http method type : POST, header : name : Content-Type value : application/json 
[
    {
        "name": "orange",
        "price": 0.25
    },
    {
        "name": "apple",
        "price": 0.60
    },
  
    {
        "name": "banana",
        "price": 1.0
    }

]

2. http://localhost:8080/offers/offer : 
Http method type : POST, header : name : Content-Type value : application/json
 [
    {
        "name": "orange",
        "offerType": "THREE_FOR_PRICE_OF_TWO"
    },
    {
        "name": "apple",
        "offerType": "BUY_ONE_GET_ONE_FREE"
    }

]

3. http://localhost:8080/orders/order/
 Http method type : POST, header : name : Content-Type value : application/json 
 
[
    {
        "name": "orange",
        "quantity": 50
    },
    {
        "name": "apple",
        "quantity": 60
    },
  
    {
        "name": "banana",
        "quantity": 10
    }

]
