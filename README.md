# KikiOnTheRun

## Problem 01

### Delivery Cost Estimation With Offers

Points to note:

1. Only one offer code can be applied for a given package
2. Package should meet the required mentioned offer criterias
3. If offer code is not valid/found discounted amount will be equal to 0.

<img width="314" alt="Screenshot 2021-04-27 103757" src="https://user-images.githubusercontent.com/12389045/116703220-a70b3080-a9e7-11eb-9871-79e914e5c3cb.png">

I have added a new offer code **| OFR004 | 20% discount | Distance Range - 100.4 - 250.0 | Weight - null |**

Delivery cost is calculated using the below formula 

TOTAL COST = BASECOST + WEIGHT * 10 + DISTANCE *  5
--------------------------------------------------- |
DELIVERYCOST = TOTALCOST - DISCOUNT(IF ANY)

#### ASSUMPTIONS 

1. If the user doesn't have a valid Offer code he can enter NA or null.
2. OfferCodes can either have weightRange as null or Distance Range as null or both.
3. If the user enters a valid OfferCode, the OfferCode will be checked against offerCriterias, the package should satisfy all the criterias for the offerCode to work.
4. If the input is invalid, system will prompt with an error message "ERROR: INVALID INPUT! Please enter correct input" and user has to restart.
5. Output will be in the format of | Package name | Discount | TotalCost | Time |.
6. Packages names can't be the same, if it's same we wont be able to differentiate between packages in the output.
7. Weights entered for each package cant be less than or equal to 0 and greater than maximum load specified.
8. Distance, Load, Speed, Vehicles cannot be less than or equal to 0.
9. Base cost can be equal to 0 but not less than 0.

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

I have approached the problem via three different methods and have chosen Method 3 after analysing each method's efficiency.

**Method 1(First Come First Service)**

Lets assume the package arrive in **First Come First Service order** and we club the packages by summing their weights upto the maximum weight.

Packages | SUM
---------|------
PK1 + PK2 (50 + 75) | <200
PK3 (175) | <200
PK5 (155) | <200
PK4 (110) | <200

#### Limitations : 
In this method, the other possible combinations are ignored which might be more efficient. <br>
For Example

Combinations1 | Combinations 2
----------------------|------------
PK3 (175)	|			PK3(175)
PK5 (155)|PK5(155)
PK4 + PK1 (110 + 50) | PK4 + PK2 (110 + 75)
PK2 (75) 	|		              PK1(50)

**Method 2(All Permutations and Combinations)**

Find combinations for each package by iterating through the list of packages and add it into a nested list <br><br>
List<List\<Double\>> combinations;
  
  PK | Combinations
  ------------|---------
  50 | 75, 110
  75 | 50, 110
  110 | 50, 75
  155 | NA
  175 | NA
  
  From the above list we select the feasible combinations which will combine **most weighted** packages into one group.
 
 #### Limitations
 This method will have a time complexity of **O(2^n)**.
 
 **Method 3(Chosen Method)**

#### Binary search <br>
We start by sorting the given packages by its weight in descending order. This will give us the heavier packages in the beggining and the lighter packages at the end.
<br>
Now with the sorted list we can combine the heavier packages with lighter packages, if we don't find any lighter package to combine, the heavier packages will go on its own as it will be close to the Load specified.

#### Input Packages with its weight

PK1 | PK2 | PK3 | PK4 | PK5
----|-----|-----|-----|----
50|75|175|110|155

#### Step 1
#### After sorting the packages BY WEIGHT 
  
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
