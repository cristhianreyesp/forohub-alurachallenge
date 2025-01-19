
create table IF NOT EXISTS topicos(

    id int not null auto_increment PRIMARY KEY,
    titulo varchar(200) not null,
    mensaje text not null,
    fecha_creacion DATETIME not null,
    status varchar(50) not null,
    autor_id int,
    curso_id int,

    foreign key (autor_id) references usuarios(id),
    foreign key (curso_id) references cursos(id)
);
