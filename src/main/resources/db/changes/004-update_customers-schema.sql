alter table if exists address add constraint FKe54x81nmccsk5569hsjg1a6ka foreign key (country_id) references country;
alter table if exists address add constraint FK93c3js0e22ll1xlu21nvrhqgg foreign key (customer_id) references customer;
