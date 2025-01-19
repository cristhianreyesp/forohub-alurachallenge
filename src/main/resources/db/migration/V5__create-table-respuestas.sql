
create table respuestas (

    id int not null auto_increment PRIMARY KEY,
    mensaje text not null,
    topico_id int,
    fecha_creacion DATETIME not null,
    autor_id int,
    solucion BOOLEAN default false,

    foreign key (topico_id) references topicos(id),
    foreign key (autor_id) references usuarios(id)
);
