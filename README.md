# player-assignment

This implementation is a naive solution that meets the functionality required in the task.
In order to improve the solution, I would refer to: 

- Save the given file in a safe and highly available place (such as S3) in order to avoid a situation where the file is deleted or cannot be accessed for any reason

- Pagination: Using pagination instead of loading all data from a database offers several benefits, including improved performance, reduced resource consumption, and better scalability. By fetching data in smaller batches, pagination helps optimize resource utilization, minimize network overhead, and ensure the application remains scalable and responsive, even with large datasets.
 
 - Caching

 - Performance: Reading data from a file is suitable for simple use cases with small datasets and low performance requirements. On the other hand, reading data from a database like Redis provides scalability, performance, advanced querying capabilities, and data persistence, making it suitable for more complex applications with larger datasets and higher performance demands.

- API tests
- Logging
  
