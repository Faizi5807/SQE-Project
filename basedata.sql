# SQL configs
#SET SQL_MODE ='IGNORE_SPACE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


#CREATE DATABASE IF NOT EXISTS ecommjava;
#USE ecommjava;

#CREATE TABLE IF NOT EXISTS CATEGORY(
#category_id int unique key not null auto_increment primary key,
#name        varchar(255) null
#);

#ALTER TABLE CATEGORY MODIFY COLUMN category_id INT AUTO_INCREMENT PRIMARY KEY;

#INSERT INTO CATEGORY(category_id,name) VALUES (2,'Fruits'),
                                  #(3,'Vegetables'),
                                  #(4,'Meat'),
                                  #(5,'Fish'),
                                  #(6,'Dairy'),
                                  #(7,'Bakery'),
                                  #(8,'Drinks'),
                                  #(9,'Sweets'),
                                  #(10,'Other');


#CREATE TABLE IF NOT EXISTS CUSTOMER(
#id       int unique key not null auto_increment primary key,
#address  varchar(255) null,
#email    varchar(255) null,
#password varchar(255) null,
#role     varchar(255) null,
#username varchar(255) null
#);

# insert default customers
#INSERT INTO CUSTOMER(id,address, email, password, role, username) VALUES
                                                                   #(1,'123, Albany Street', 'admin@nyan.cat', '123', 'ROLE_ADMIN', 'admin'),
                                                                   #(2,'765, 5th Avenue', 'lisa@gmail.com', '765', 'ROLE_NORMAL', 'lisa');

# create the product table
#CREATE TABLE IF NOT EXISTS PRODUCT(
#product_id  int unique key not null auto_increment primary key,
#description varchar(255) null,
#image       varchar(255) null,
#name        varchar(255) null,
#price       int null,
#quantity    int null,
#weight      int null,
#category_id int null,
#customer_id int null
#);

# insert default products
#INSERT INTO PRODUCT(product_id,description, image, name, price, quantity, weight, category_id) VALUES
                                                                                        #(1,'Fresh and juicy', 'https://freepngimg.com/save/9557-apple-fruit-transparent/744x744', 'Apple', 3, 40, 76, 1),
                                                                                        #(2,'Woops! There goes the eggs...', 'https://www.nicepng.com/png/full/813-8132637_poiata-bunicii-cracked-egg.png', 'Cracked Eggs', 1, 90, 43, 9);


 #create indexes
#CREATE INDEX FK7u438kvwr308xcwr4wbx36uiw
    #ON PRODUCT (category_id);

#CREATE INDEX FKt23apo8r9s2hse1dkt95ig0w5
 #   ON PRODUCT (customer_id);