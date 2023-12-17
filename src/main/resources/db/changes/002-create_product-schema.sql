create table product (
    discount_percentage float(53),
    price numeric(38,2),
    rating float(53),
    stock integer,
    created_at timestamp(6),
    updated_at timestamp(6),
    brand varchar(255),
    category_id varchar(255),
    description varchar(255),
    id varchar(255) not null,
    thumbnail varchar(255),
    title varchar(255),
    images varchar(255) array,
    primary key (id),
    unique (title)
);