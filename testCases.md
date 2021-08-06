# story 1
[X] Case1  
Given a parking lot, and a car  
When park the car  
Then return a parking ticket. 

[X] Case 2
Given a valid parking ticket  
When fetch the car   
Then return the car 

[X] Case 3
Given a parking lot with two parked cars and two parking tickets  
When fetch twice  
Then return right car 

[] Case 4
Given a wrong parking ticket
When fetch the car  
Then return no car  


[] Case 5
Given a no parking ticket
When fetch the car  
Then return no car

[] Case 6
Given a valid parking ticket but already been used  
When fetch the car  
Then return no car  

[] Case 7
Given full parking lot  
When park the car  
Then car cannot park and no parking ticket  









[] Case 6
Given a parked car  
When park the car  
Then no changes

[] Case 7
Given park null car
When park the car
Then no parking ticket