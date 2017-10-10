
DROP TABLE country IF EXISTS;
DROP TABLE menuitem IF EXISTS;
DROP TABLE menuitem_product IF EXISTS ;
DROP TABLE FoodOrder IF EXISTS;
DROP TABLE orderstatus IF EXISTS ;
DROP TABLE product IF EXISTS;
DROP TABLE productcategory IF EXISTS;

CREATE TABLE FoodOrder (
  FoodOrderID INTEGER IDENTITY PRIMARY KEY,
  orderDate timestamp NOT NULL,
  OrderStatusID INTEGER NOT NULL
);

CREATE TABLE country (
  CountryID 	INTEGER IDENTITY PRIMARY KEY,
  CountryName 	varchar(45) NOT NULL
);

CREATE TABLE menuitem (
  MenuItemID 	INTEGER IDENTITY PRIMARY KEY,
  FoodOrderID 		INTEGER NOT NULL
);
CREATE TABLE menuitem_product (
  MenuItemID INTEGER NOT NULL,
  ProductID INTEGER NOT NULL,
  PRIMARY KEY (MenuItemID,ProductID),
);
CREATE TABLE orderstatus (
  OrderStatusID INTEGER IDENTITY PRIMARY KEY,
  Status varchar(45) NOT NULL
);
CREATE TABLE product (
  ProductID INTEGER IDENTITY PRIMARY KEY,
  Name varchar(45) NOT NULL,
  CountryID INTEGER DEFAULT NULL,
  ProductCategoryID INTEGER NOT NULL,
  Price decimal(10,0) NOT NULL,
);
CREATE TABLE productcategory (
  ProductCategoryID INTEGER IDENTITY PRIMARY KEY,
  Category varchar(45) NOT NULL
);
ALTER TABLE menuitem ADD CONSTRAINT fk_MenuItem_Order1 FOREIGN KEY (FoodOrderID) REFERENCES FoodOrder (FoodOrderID);
ALTER TABLE menuitem_product ADD CONSTRAINT fk_MenuItem_Product_MenuItem1 FOREIGN KEY (MenuItemID) REFERENCES menuitem (MenuItemID);
ALTER TABLE menuitem_product ADD CONSTRAINT fk_MenuItem_Product_Product1 FOREIGN KEY (ProductID) REFERENCES product (ProductID);
ALTER TABLE FoodOrder ADD CONSTRAINT fk_Order_OrderStatus FOREIGN KEY (OrderStatusID) REFERENCES orderstatus (OrderStatusID);
ALTER TABLE product ADD CONSTRAINT fk_Product_Country1 FOREIGN KEY (CountryID) REFERENCES country (CountryID);
ALTER TABLE product ADD CONSTRAINT fk_Product_ProductCategory1 FOREIGN KEY (ProductCategoryID) REFERENCES productcategory (ProductCategoryID);

insert into country values (1,'Polish'),(2,'Mexican'),(3,'Italian');
insert into orderstatus values (1,'new'),(2,'canceled'),(3,'complete');
insert into productcategory values (1,'drink'),(2,'main_course'),(3,'drink_additive'),(4,'dessert');
insert into product values (1,'Polish main course Pierogi',1,2,24.32),(2,'Polish main course Flaki',1,2,20.99),
(3,'Mexican main course Fajitas',2,2,13.00),(4,'Mexican main course Enchilada',2,2,45.37),
(5,'Italian main course Pizza',3,2,21.30),(6,'Italian main course Spaghetti',3,2,27.86),
(7,'Polish dessert Kremówka',1,4,5.41),(8,'Polish dessert P¹czek',1,4,6.34),
(9,'Mexican dessert Nachos',2,4,7.21),(10,'Mexican dessert Tachos',2,4,8.78),
(11,'Italian dessert Focaccia',3,4,9.99),(12,'Italian dessert Raviollo',3,4,9.45),
(13,'drink water',null,1,4.50),(14,'drink Martini',null,1,7.60),
(15,'ice cubes',null,3,0),(16,'lemon',null,3,0);
