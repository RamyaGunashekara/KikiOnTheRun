# KikiOnTheRun

## Problem 01

### Delivery Cost Estimation With Offers

Points to note:

1. Only one offer code can be applied for a given package
2. Package should meet the required mentioned offer criterias
3. If offer code is not valid/found discounted amount will be equal to 0.

<img width="314" alt="Screenshot 2021-04-27 103757" src="https://user-images.githubusercontent.com/12389045/116703220-a70b3080-a9e7-11eb-9871-79e914e5c3cb.png">

I have added a new offer code **OFR004 20% discount Distance - 100.4 - 250.0 Weight - null**

Delivery cost is calculated using the below formula 

TOTAL COST = BASECOST + WEIGHT * 10 + DISTANCE *  5
--------------------------------------------------- |
DELIVERYCOST = TOTALCOST - DISCOUNT(IF ANY)

#### ASSUMPTIONS 

1. If the user doesn't have a valid Offer code he can enter NA.
2. If the input is invalid, system will prompt the user with "Please enter correct input" and user has to restart.
4. Output will be in the format of Package name Discount TotalCost
5. OfferCodes can either have weightRange as null or Distance Range as null or both.
6. Weights entered for each package cant be <=0 or > maximum load specified 
7. Distance, Load, Speed, Vehicles, Base Delivery Cost cannot be less than 0.

--------------------------------------------------------------------------------------------------------------------------------

## Problem 02

### Delivery Time Estimation

Points to note:
1. Each vehicle has a limit on Weight that it can carry.
2. All vehicle travel at the same speed and same route
3. We have to pick maximum number of packages in every shipment.


**Thought process in picking and clubbing packages**

Example considered 

Packages | BaseCost | Vehicles | Speed | Load 
---------|----------|----------|-------|-----
5|100|2|70|200

PackageName|Weight|Distance
-----------|------|--------
PK1|50|30
PK2|75|125
PK3|175|100
PK4|110|60
PK5|155|95

**Method 1**

Lets assume the package arrive in First Come First Service order and we club the packages by summing their weights upto the maximum weight.


Packages | SUM
---------|------
PK1 + PK2 (50 + 75) | <200
PK3 (175) | <200
PK5 (155) | <200
PK4 (110) | <200

In this method, the other possible combinations are ignored which might be more efficient. <br>
For Example

Combinations1 | Combinations 2
----------------------|------------
PK3 (175)	|			PK3(175)
PK5 (155)|PK5(155)
PK4 + PK1 (110 + 50) | PK4 + PK2 (110 + 75)
PK2 (75) 	|		              PK1(50)

**Method 2**

Find combinations for each package by iterating through the list of packages and add it into a nested list <br><br>
List<List\<Double\>> combinations;
  
  PK | Combinations
  ------------|---------
  50 | 75, 110
  75 | 50, 110
  110 | 50, 75
  155 | NA
  175 | NA
  
 Select the best combination possible from the list.
 This method will have a time complexity of O(2^n).
 
 **Method 3**

#### Divide and Conquer <br>
Sort the List by Weight in descending order, by doing this we will have all the heavy packages in the beginning of the list which can be combined with lighter packages at the last. 

PK1 | PK2 | PK3 | PK4 | PK5
----|-----|-----|-----|----
50|75|175|110|155

#### SORT BY WEIGHT 
  
  PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  175|155|110|75|50
  
  
  #### Let start = 175 and End = 50 and Add start value to the list 
    * List     
     * 175
  
  PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  **_175_**|155|110|75|**_50_**
  
 #### Value of start+end (175+50) > load, which means we dont have any package with smaller weight that can be clubbed with 175. 
#### Increment start. 
  
  #### start = 155 end = 50
  
   * List     
     * 175
     * 155
  
   PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  175|**_155_**|110|75|**_50_**
  
  
  #### 155+50 > Load(200), Increment start
  
  * List     
     * 175
     * 155
     * 110

  PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  175|155|**_110_**|75|**_50_**
  
  
 #### 110+50 < Load(200), Add 50 to the list and decrement end
  
  * List     
     * 175
     * 155
     * 110,50 
  
  
  PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  175|155|**_110_**|**_75_**|**_50_**
 
   
  #### 110+50+75 > load(200), increment start 
  
  PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  175|155|110|**_75_**|50
 
 #### start == end, End of loop.
  * List     
     * 175
     * 155
     * 110,50 
     * 75
