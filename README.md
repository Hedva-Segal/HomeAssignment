# player-assignment

This repository contains a solution for the player assignment task. The current implementation provides the necessary functionality in a naive solution, suggestions for improvement are detailed below

## Input Validation
Validation has been added to the playerId input in the API. Based on the data in the player.csv file, the playerId is expected to end with 2 digits and be between 8 and 9 characters long.

## Swagger UI
<img src="https://static1.smartbear.co/swagger/media/assets/images/swagger_logo.svg" width="200" height="100">
Swagger UI can be used to see and execute the API's that the application exposes, after the application is up and running go to http://localhost:8080/swagger-ui/index.html

## Run with docker 
<img src="https://blog.knoldus.com/wp-content/uploads/2020/06/docker-1-e1592755683903.png" width="200" height="100">

In order to run the application on docker the following steps are required:
- Change the file.path in the apllication.propetries file to usr/src/player.csv
- Build the docker image - docker build -t intuit-home-assignment .
- Run the docker container - docker run -p 8080:8080 intuit-home-assignment

## Improvement Steps
To enhance the solution, the following steps could be taken:

### 1. File Management
Secure Storage: Consider storing the given file in a secure and highly available location such as Amazon S3. This ensures the file is safely stored and accessible, minimizing the risk of data loss or unavailability.

### 2. Pagination
Improved Data Retrieval: Implement pagination for data retrieval instead of loading all data from the database at once. Pagination offers benefits such as enhanced performance, reduced resource consumption, and better scalability. By fetching data in smaller chunks, pagination optimizes resource utilization and ensures responsiveness, especially with large datasets.

 ### 3. Caching
Data Caching: Implement caching mechanisms to store frequently accessed data in memory. Caching can significantly improve performance by reducing the need to fetch data from the database repeatedly.

### 4. Performance Optimization
Database Usage: Evaluate the performance requirements and consider migrating from file-based storage to a database solution like Redis. Redis offers scalability, advanced querying capabilities, and data persistence, making it suitable for applications with larger datasets and higher performance demands.

By implementing these improvements, the solution can become more robust, scalable, and efficient.
