drop table tasks;
create table tasks(
    id int primary key auto_increment,
    decription varchar(100) not null,
    done bit;
)