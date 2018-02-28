update mysql.user set password=password('password') where user = 'root';
flush privileges;
