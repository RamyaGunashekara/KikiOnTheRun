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
3. Formula to calculate delivery cost ![Uploading image.png…]()
      ![image](https://user-images.githubusercontent.com/12389045/116703932-72e43f80-a9e8-11eb-82d6-2699e4dc756b.png)
4. Output will be in the format of Package name Discount TotalCost
5. OfferCodes can either have weightRange as null or Distance Range as null or both.
6. Weights entered for each package cant be <=0 or > maximum load specified 
7. Distance, Load, Speed, Vehicles, Base Delivery Cost cannot be less than 0. 



