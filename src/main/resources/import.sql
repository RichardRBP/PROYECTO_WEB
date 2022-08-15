/* Populate tables */

insert into areas (area) values ('AREA "A" - CIENCIAS DE LA VIDA Y DE LA SALUD');
insert into areas (area) values ('AREA "B" - CIENCIAS BÁSICAS Y TECNOLÓGICAS');
insert into areas (area) values ('AREA "C" - CIENCIAS DE LA PERSONA');
insert into areas (area) values ('AREA "D" - CIENCIAS ECONÓMICAS');

insert into carreras (nombre,puntajePromedio,idArea) values ('ING de sistemas',180,2);

insert into roles (rol, descripcion) values ('administrador','con acceso a todos los campos');
insert into roles (rol, descripcion) values ('empleado','con acceso a las vistas para empleado');
insert into roles (rol, descripcion) values ('alumno','con acceso a las vistas de alumno');

insert into ciclos (ciclo, anio) values ('verano',2022);
insert into ciclos (ciclo, anio) values ('semestral I',2022);
insert into ciclos (ciclo, anio) values ('semestral II',2022); 

insert into empleados (cargo, nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo) values ('secretaria','gianina','pastor','973152460','gianinapastor25@gmail.com','','av san juan nro 404','75954821',25,'FEMENINO'); 
insert into empleados (cargo, nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo) values ('profesor','pedro','hidalgo','934752460','pedrohidalgo47@gmail.com','','av los laureles nro 500','71392864',35,'MASCULINO'); 



insert into aulas (aula,area_id) values ('A',1);
insert into aulas (aula,area_id) values ('B',2);
insert into aulas (aula,area_id) values ('C',3);
insert into aulas (aula,area_id) values ('D',4);
insert into aulas (aula,area_id) values ('E',1);
insert into aulas (aula,area_id) values ('F',2);
insert into aulas (aula,area_id) values ('G',3);
insert into aulas (aula,area_id) values ('H',4);

INSERT INTO cursos (etapa,nivel) VALUES ('5to Secundaria',1);
INSERT INTO cursos (etapa,nivel) VALUES ('Verano',2);
INSERT INTO cursos (etapa,nivel) VALUES ('Semestral',3);

INSERT INTO profesores (nombre,apellido,nif,telefono,email,titulacion,foto) VALUES ('Juan','Perez','12345678','984528488','juan@gmail.com','Ing. Sistemas','')
INSERT INTO profesores (nombre,apellido,nif,telefono,email,titulacion,foto) VALUES ('Pablo','Rodríguez','12345679','984531488','pablo@gmail.com','Ing. Civil','')

INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa 5to Secundaria','Tarifa para los alumnos de 5to Secundaria', 544.21)
INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa Verano','Tarifa para los alumnos de Verano', 700.21)
INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa Semestral','Tarifa para los alumnos de Semestre', 852.24)
INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa Semestral','Tarifa para los alumnos de Semestre', 852.24)
INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa Semestral','Tarifa para los alumnos de Semestre', 852.24)
INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa Semestral','Tarifa para los alumnos de Semestre', 852.24)
INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa Semestral','Tarifa para los alumnos de Semestre', 852.24)
INSERT INTO tarifas (nombre,descripcion,precio) VALUES ('Tarifa Semestral','Tarifa para los alumnos de Semestre', 852.24)

insert into tipoAlumnos (nombreTipo) values ('5to grado');
insert into tipoAlumnos (nombreTipo) values ('Regular');
insert into tipoAlumnos (nombreTipo) values ('Cepunt');

insert into matriculas (codigo_operacion,fecha_operacion,banco,monto,foto,estado,turno,tarifa_id) values (9465243,'2022-08-08','Interbank',500,'','EN PROCESO','TARDE',1);
insert into matriculas (codigo_operacion,fecha_operacion,banco,monto,foto,estado,turno,tarifa_id) values (9465243,'2022-08-08','BCP',500,'','ACEPTADA','NOCHE',1);




