# The refactoring of the project: 
<hr>
<h3>The changes:</h3>
<ul>
  <li>General DAO Class: Introduced DatabaseDAO, a centralized class containing essential methods for executing SQL queries (insert, delete, update, select, closeConnection).</li>
  <li>GenericDAO Interface: Created GenericDAO interface defining standard methods (insert, update, delete, getById, getAll) that all specific DAO classes must implement.</li>
  <li>Specific DAO Classes: Each DAO class (GameDAO, UserDAO, OrderDAO, GenreDAO, PlatformDAO, ReviewDAO, DeveloperDAO) now
    inherits from DatabaseDAO and implements GenericDAO tailored to its specific entity type.</li>
  <li>Unit Test Adjustments: Updated unit tests to accommodate the refactored DAO structure, ensuring all database operations remain thoroughly tested and validated.</li>

</ul>
<hr>
<h3>Advantages of changes:</h3>
<ul>
  <li>By introducing the DatabaseDAO class, all common SQL operations (insert, delete, update, select, closeConnection) are centralized. This means that database access logic is consolidated in one place, reducing code duplication across multiple DAO classes (GameDAO, UserDAO, OrderDAO, etc.).</li>
   <li>The GenericDAO interface sets a common blueprint for how different DAO classes should interact with the database. It outlines essential methods like insert (to add data), update (to modify data), delete (to remove data), getById (to fetch specific data by ID), and getAll (to retrieve all data). By requiring all DAO classes to implement these methods, 
     it ensures that database operations across the application follow a uniform approach.</li>
  <li>The refactored approach enhances the application's scalability. New DAO classes can be easily added for additional entities (GenreDAO, PlatformDAO, etc.) by simply inheriting from DatabaseDAO and implementing GenericDAO. </li>
</ul>
<h3>Used resources:</h3>
<ul>
  <li>https://www.youtube.com/watch?v=pCK6prSq8aw&ab_channel=LucidSoftware</li>
  <li>https://habr.com/ru/articles/150041/</li>
  <li>https://edrawmax.wondershare.com/diagram-tips/component-diagram.html</li>
  <li>https://dou.ua/forums/topic/40575/</li>
  <li>https://www.lucidchart.com/pages/uml-object-diagram</li>
  <li>https://chat.openai.com/</li>
</ul>
