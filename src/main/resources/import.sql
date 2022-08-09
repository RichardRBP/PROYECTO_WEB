/* Populate tables */

-- insert into aulas
insert into aulas (aula) values ('A');
insert into aulas (aula) values ('B');
insert into aulas (aula) values ('C');
insert into aulas (aula) values ('D');

INSERT INTO cursos (etapa,nivel) VALUES ('5to Secundaria',1);
INSERT INTO cursos (etapa,nivel) VALUES ('Verano',2);
INSERT INTO cursos (etapa,nivel) VALUES ('Semestral',3);

INSERT INTO profesores (nombre,apellido,nif,telefono,email,titulacion) VALUES ('Juan','Perez','12345678','984528488','juan@gmail.com','Ing. Sistemas')
INSERT INTO profesores (nombre,apellido,nif,telefono,email,titulacion) VALUES ('Pablo','Rodríguez','12345679','984531488','pablo@gmail.com','Ing. Civil')

INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa 5to Secundaria','Tarifa para los alumnos de 5to Secundaria', 544.21)
INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa Verano','Tarifa para los alumnos de Verano', 700.21)
INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa Semestral','Tarifa para los alumnos de Semestre', 852.24)



