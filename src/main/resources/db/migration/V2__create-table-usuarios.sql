
create table IF NOT EXISTS usuarios(

    id int not null auto_increment PRIMARY KEY,
    nombre varchar(100) not null,
    correo_electronico varchar(100) not null,
    contrasena varchar(300) not null,
    perfil_id int,

    foreign key (perfil_id) references perfiles(id)

);
