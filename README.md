# Problem: Trains

## Prerequisites
What things you need to preparations
* `$ java -version`
* `openjdk version "12.0.1" or jdk "1.8"`
*  ``junit-4.12 jar hamcrest-core-1.3.jar for junit test``



## How to run the program?
* Program entry is under the package of **Main.java**

>  ``$ java Main [graph data file] [request file]``
 
> EG:  $ java Main /root/graph.txt /root/command.txt

*Content of **cgraph.txt***  
`AE7`    
`AB5`  
`BC4`  
`CD8`  
`DC8`  
`DE6`  
`AD5`  
`CE2`  
`EB3` 

*Content of **command.txt***  
`Distance? A-B-C`  
`Distance? A-D`  
`Distance? A-D-C`  
`Distance? A-E-B-C-D`  
`Distance? A-E-D`  
`AvailableRoutes? C-C :stops le 3`  
`AvailableRoutes? A-C : stops eq 4`  
`ShortestDistance? A-C `  
`ShortestDistance? B-B`  
`AvailableRoutes? C-C : distance lt 30`   

### Grammar explanation for command.txt

**Distance? A-B-C**  means calculate the route distance from A->B->C  
**AvailableRoutes? C-C :stops le 3** means calculate how many routes from C and end with C, stops less then 3 ?  
**ShortestDistance? A-C** means calculate the shortest distance from A and end with C  

> So , the command grammar is "command?routes:conditions" 

currently, command only supported **Distance, AvailableRoutes, ShortestDistance**,
filter conditions only supported **stops, distance**  
operation symbol only support **eq, lt, le**:    
`eq  indicate ==`  `lt  indicate <`  `le  indicate <=`   

## Architecture Design Thinking
* Load the route data into memory model. memory model is a whole graph map.
include every station node information (current station name, go out station nodes, come in station nodes)
* Adopt command pattern. client create command, broker receive & send command, executor as a role to execute command. it is better for decouple,
and extension(if we want to refactor to distribution)
* Adopt reflect mechanism and simple factory pattern to create suitable executor. It is better for decouple.


  