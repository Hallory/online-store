insert into category (created_at,description,modified_at,name,parent_id,id)
values (current_timestamp,'root category',current_timestamp,'Root Category',NULL,nextval('category_seq'));

--insert into product