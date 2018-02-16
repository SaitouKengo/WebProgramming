create table user ( 
  id serial PRIMARY KEY
  , login_id varchar (255) UNIQUE NOT NULL
  , name varchar (255) NOT NULL
  , birth_date date NOT NULL
  , password varchar (255) NOT NULL
  , create_date DATETIME NOT NULL
  , update_date DATETIME NOT NULL
); 

insert user ( 
  login_id
  , name
  , birth_date
  , password
  , create_date
  , update_date
) 
values ( 
  'admin'
  , 'ä«óùé“'
  , '1999-01-01'
  , 'password'
  , '1999-01-01 00:00:00'
  , '1999-01-01 00:00:00'
) 
select
  * 
from
  user; 



