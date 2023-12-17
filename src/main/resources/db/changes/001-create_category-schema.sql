create table category (
    created_at timestamp(6),
    id varchar(255) not null,
    name varchar(255),
    primary key (id),
    unique (name)
);