# Task1
Ordering system is a console app. During startup program creates a h2 in-memory database from a create.sql file located in src/main/resources folder. Below there is a eer diagram of the database schema that I came up with
![orderingsystemdiagram](https://user-images.githubusercontent.com/20241683/31469908-f6102558-aee3-11e7-99bb-51a4b5eeaf1c.jpg)
Each Meal/drink is represented as MenuItem row. Product table was created for storing main_courses/drinks/drink_additives/desserts. They can be distinguished by analyzing ProductCategory. Main_courses and desserts contains also reference to cuisine(Country table).
