create table cliente(
    id serial not null primary key,
    nome varchar(100) not null,
    sobrenome varchar(100),
    telefone varchar(11) not null,
    email varchar(200) not null unique,
    senha varchar(100) not null,
    is_barbeiro boolean
);