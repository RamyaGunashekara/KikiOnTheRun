# KikiOnTheRun

**Problem 01**

**Delivery Cost Estimation With Offers**
Points to note:

1. Only one offer code can be applied for a given package
2. Package should meet the required mentioned offer criterias
3. If offer code is not valid/found discounted amount will be equal to 0.

<img width="314" alt="Screenshot 2021-04-27 103757" src="https://user-images.githubusercontent.com/12389045/116703220-a70b3080-a9e7-11eb-9871-79e914e5c3cb.png">

I have added a new offer code **OFR004 20% discount Distance - 100.4 - 250.0 Weight - null**

Delivery cost is calculated using the below formula 

![image](https://user-images.githubusercontent.com/12389045/117232165-42fcc800-ae3e-11eb-9591-8614ee5b2019.png)

Assumptions 

1. If the user doesn't have a valid Offer code he can enter NA.
2. If the input is invalid, system will prompt the user with "Please enter correct input" and user has to restart.
4. Output will be in the format of Package name Discount TotalCost
5. OfferCodes can either have weightRange as null or Distance Range as null or both.
6. Weights entered for each package cant be <=0 or > maximum load specified 
7. Distance, Load, Speed, Vehicles, Base Delivery Cost cannot be less than 0. 

**Problem 02**

**Delivery Time Estimation**

Points to note:
1. Each vehicle has a limit on Weight that it can carry.
2. All vehicle travel at the same speed and same route
3. We have to pick maximum number of packages in every shipment.


**Thought process in picking and clubbing packages**

Example considered 

![image](https://user-images.githubusercontent.com/12389045/117234029-daafe580-ae41-11eb-80dd-bdbeb8ace2af.png)

**Method 1**

Lets assume the package arrive in First Come First Service order and we club the packages by summing their weights upto the maximum weight.
PK1 + PK2 (50 + 75)
PK3 (175) can’t be clubbed with any other PK
PK5 (155) can’t be clubbed with any other PK
PK4 (110) can’t be clubbed with any other PK

In this method, the other possible combinations are ignored which might be more efficient. 
For Example

PK3 (175)				
PK5 (155)
PK4 + PK1 (110 + 50) **OR** PK4 + PK2 (110 + 75)
PK2 (75) 			              PK1(50)

**Method 2**

Find all the possible combinations of the input and then select the best. 






