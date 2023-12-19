insert into users (id, username, email, password)
values (1, 'Admin', 'admin@admin.com', '$2a$10$HzqnEszPXL962Yo0WFQFQu6mT2gDr/jDyZeheSmvHISlGxD/PqH.C');

insert into recipes (ingredients, method_of_preparation)
values ('Beef burger (380g)  ingredients', 'Beef burger (380g) - method of preparation'),
       ('Beef burger (290g)  ingredients', 'Beef burger (290g) - method of preparation'),
       ('Chicken cheeseburger ingredients', 'Chicken cheeseburger - method of preparation'),
       ('Pepperoni Pizza ingredients', 'Pepperoni Pizza - method of preparation'),
       ('Pizza napoletana ingredients', 'bPizza napoletana - method of preparation'),
       ('Greek Pizza Recipe ingredients', 'Greek Pizza Recipe - method of preparation'),
       ('Pasta with cherry tomatoes', 'Pasta with cherry tomatoes - method of preparation'),
       ('Pasta Carbonara ingredients', 'Pasta Carbonara - method of preparation'),
       ('Red Curry Pasta', 'Red Curry Pasta - method of preparation');
;

insert into products (name, description, price, image_url, product_type, recipe_id)
values ('Beef burger (380g)', 'Brioche bun, ground beef, blue cheese melted on crispy bacon, BBQ sauce, fries', 10, 'product-pictures/burger-1.jpg', 'BURGER', 1),
       ('Beef burger (290g)', 'Pulled pork, iceberg, tomato, egg, cheddar, sauce', 20, 'product-pictures/burger-2.jpg', 'BURGER', 2),
       ('Chicken cheeseburger (280g)', 'Crispy chicken fillet, lettuce, cheese mousse, tomato, cheddar', 30, 'product-pictures/burger-3.jpg', 'BURGER', 3),
       ('Pepperoni Pizza', 'delicious_p1', 10, 'product-pictures/pizza-1.jpg', 'PIZZA', 4),
       ('Pizza napoletana', 'delicious_p2', 20, 'product-pictures/pizza-2.jpg', 'PIZZA', 5),
       ('Greek Pizza Recipe', 'delicious_p3', 30, 'product-pictures/pizza-3.jpg', 'PIZZA', 6),
       ('Pasta with cherry tomatoes', 'delicious_pa1', 10, 'product-pictures/pasta-1.jpg', 'PASTA', 7),
       ('Pasta Carbonara', 'delicious_pa2', 20, 'product-pictures/pasta-2.jpg', 'PASTA', 8),
       ('Red Curry Pasta', 'delicious_pa3', 30, 'product-pictures/pasta-3.jpg', 'PASTA', 9);

insert into users_roles (user_id, role_id)
values (1, 2),
       (1, 1);

