# CalorieTrackerWS
Server side components of a mobile distributed system for a CalorieTracker app. It consists of two main parts, Java 8 ee backed REST API and DB. 

## Table of contents
* [Project description](#project-description)
* [Technologies](#technologies)


## Project description

Components: 
 1) a database (Java DB database in NetBeans) 
 2) a RESTful web service that enables querying this data and updating it if necessary.
 
## Technologies
Project is created with:
 * Java 8 EE
 * JAX-RS 
 * Java DB
 * JPQL
	
## DB ER diagram
![Image](https://github.com/ZakharA/CalorieTrackerWS/blob/master/Screen%20Shot%202020-08-19%20at%2019.00.00.png)

## REST API
REST API utilizes both static and dynamic queries to access the db. 
Features:
 * calculation of BMR, calories burned per step, resting calories burned, total calires burned.
 * Reports data generation. 

An example of API endpoints. 
![Image](https://github.com/ZakharA/CalorieTrackerWS/blob/master/Screen%20Shot%202020-08-19%20at%2019.03.50.png)


