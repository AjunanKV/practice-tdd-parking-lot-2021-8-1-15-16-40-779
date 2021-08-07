#Story 1
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

[X] Case 4
Given a wrong parking ticket
When fetch the car  
Then return no car

[X] Case 5
Given a valid parking ticket but already been used  
When fetch the car  
Then return no car  

[X] Case 6
Given full parking lot  
When park the car  
Then car cannot park and no parking ticket  

# story 2
[X] Case 1
Given wrong ticket  
When fetch the car  
Then return error message: Unrecognized parking ticket.  

[X] Case 2
Given a full parking lot, a car  
When park a car  
Then return error message: No available position.   

#Story 3
[X] Case1  
Given a parking lot, and a car  
When park the car with parking boy  
Then return a parking ticket.

[X] Case 2
Given a valid parking ticket  
When fetch the car   
Then return the car

[X] Case 3
Given a parking lot with two parked cars and two parking tickets  
When fetch twice with parking boy  
Then return right car

[X] Case 4
Given a wrong parking ticket
When fetch the car with parking boy  
Then return no car

[] Case 5
Given a valid parking ticket but already been used  
When fetch the car with parking boy  
Then return no car

[] Case 6
Given full parking lot  
When park the car with parking boy  
Then car cannot park and no parking ticket  
