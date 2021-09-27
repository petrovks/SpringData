create table categories(
    id bigserial primary key,
    title varchar (255));
insert into categories (title)
values ('Food'),
       ('Alcohol');

create table products(
    id bigserial primary key,
    title VARCHAR(255),
    price int,
    category_id bigint references categories (id)
);
insert into products(title, price, category_id)
values ('Bread', 10, 1),
       ('Jam', 20, 1),
       ('Milk', 25, 1),
       ('Meat', 78, 1),
       ('Potato', 10, 1),
       ('Tomato', 15, 1),
       ('Orange', 18, 1),
       ('Eggs', 10, 1),
       ('Cucumber', 10, 1),
       ('Pasta', 20, 1),
       ('Zucchini', 12, 1),
       ('Chicken', 23, 1),
       ('Mushrooms', 13, 1),
       ('Beer', 12, 2),
       ('Vodka', 90, 2),
       ('Brandy', 100, 2),
       ('Sandwich', 10, 1),
       ('Cheesecake', 10, 1),
       ('ice cream', 10, 1),
       ('cheeps', 10, 1);