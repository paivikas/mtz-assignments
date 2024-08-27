create table if not exists contact(
    id uuid primary key,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    mobile varchar(10),
    deleted boolean default false
);