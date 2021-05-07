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

----------------------------------------------------------------------------------------------------------------------------------------------------------------

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
 
 ------------------------------------------------------------------------------------------------------------------------------------------------------------
 
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
##### After sorting the packages BY WEIGHT 
  
  PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  175|155|110|75|50
 ---------------------------------------- 
#### Step2 
##### let start = 175 End = 50. Add start = 175 to sub List\<Double\> combo.
#### combo = [175.00]
  -------------------------------------------
#### Step3
  PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  **_175_**|155|110|75|**_50_**
  
 ##### SUM values of start+end (175+50), Check SUM > load(200), which means we dont have any package with smaller weight that can be clubbed with 175. 
 ##### Stop here and add sub List<\Double\> combo to final List<List\<Double\> combinations; and clear the values of sub list combo.
 #### Combinations = [[175.00]]
 #### Combo = []
 -------------------------------------------
 #### Step 4
 ##### start = 155 end = 50. Add start = 155 to sub list combo.
 #### combo = [155.0]
  
   PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  175|**_155_**|110|75|**_50_**
  
 ##### SUM values 155+50, check SUM > Load(200). Increment start and add sub list combo to final list combinations. Clear the values of sub list combo.
 #### Combinations = [[175.00] , [155.00]]
 #### Combo = []
-----------------------------------------------------------------------------------------
#### Step5 
##### start = 110 end = 50. Add start = 110 to sub list combo.
#### combo = [110.0]

  PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  175|155|**_110_**|75|**_50_**
  
  
 ##### SUM Values 110+50, SUM < Load(200), we need to now check if we have any more smaller weights that we can still combine 
 ##### Add value of end(50) to sublist combo and decrement END.
 #### combo = [110.00 , 50.00]
  
  PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  175|155|**_110_**|**_75_**|**_50_**
  
 ##### SUM = 110+50 =160, Add end(75) to the sum, SUM = 160+75 > Load 
 ##### Increment start and add sublist combo to final list combinations. Clear sub list combo
 #### Combinations = [[175.00] , [155.00] , [110.00,50.00]]
 #### Combo = []
 -----------------------------------
 #### Step6 
 ##### start = end = 75. Add start = 75 to final list combinations and end the loop.
  
  PK3|PK5|PK4|PK2|PK1
  ---|---|---|---|---
  175|155|110|**_75_**|50
 
 #### finalCombinations = [[175.00] , [155.00] , [110.00,50.00] , [75.00]]
------------------------------
