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

create table users
(
    id          bigserial primary key,
    username    varchar(30) not null,
    password    varchar(80) not null,
    email       varchar(50) unique,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table roles
(
    id          bigserial primary key,
    name        varchar(50) not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2y$10$PFDtzhglT3DRPKlc/ARqD.JHXSN3vNZEoefh/EAiol.gEviSSeAnK', 'bob_johnson@gmail.com'),
       ('admin', '$2y$10$PFDtzhglT3DRPKlc/ARqD.JHXSN3vNZEoefh/EAiol.gEviSSeAnK', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);

create table orders
(
    id         bigserial primary key,
    user_id    bigint references users (id),
    address    varchar(255),
    phone      varchar(255),
    price      integer,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    order_id          bigint references orders (id),
    product_id        bigint references products (id),
    quantity          integer,
    price_per_product integer,
    price             integer,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

insert into orders (user_id, address, phone, price)
values (1, '1111', '1111', 100);

insert into order_items (order_id, product_id, quantity, price_per_product, price)
values (1, 1, 10, 10, 100);