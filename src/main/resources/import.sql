/* Populate tables */
insert into areas (area) values ('AREA "A" - CIENCIAS DE LA VIDA Y DE LA SALUD');
insert into areas (area) values ('AREA "B" - CIENCIAS BÁSICAS Y TECNOLÓGICAS');
insert into areas (area) values ('AREA "C" - CIENCIAS DE LA PERSONA');
insert into areas (area) values ('AREA "D" - CIENCIAS ECONÓMICAS');

insert into carreras (nombre,puntaje_promedio,area_id) values ('ING de Sistemas',180,2);
insert into carreras (nombre,puntaje_promedio,area_id) values ('ING Civil',256,2);
insert into carreras (nombre,puntaje_promedio,area_id) values ('Medicina',350,1);

insert into tipo_alumnos (nombre_tipo) values ('5to grado');
insert into tipo_alumnos (nombre_tipo) values ('Regular');
insert into tipo_alumnos (nombre_tipo) values ('Cepunt');

insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id) values ('Alex','Gonzales','934752460','alex12@gmail.com','','av los laureles nro 500','71392864',18,'MASCULINO', 1, 1);

insert into roles (rol, descripcion) values ('administrador','con acceso a todos los campos');
insert into roles (rol, descripcion) values ('empleado','con acceso a las vistas para empleado');
insert into roles (rol, descripcion) values ('alumno','con acceso a las vistas de alumno');

insert into ciclos (ciclo, anio) values ('verano',2022);
insert into ciclos (ciclo, anio) values ('semestral I',2022);
insert into ciclos (ciclo, anio) values ('semestral II',2022); 
insert into ciclos (ciclo, anio) values ('5to Secundaria',2022);

insert into empleados (cargo, nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo) values ('secretaria','gianina','pastor','973152460','gianinapastor25@gmail.com','','av san juan nro 404','75954821',25,'FEMENINO'); 
insert into empleados (cargo, nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo) values ('profesor','pedro','hidalgo','934752460','pedrohidalgo47@gmail.com','','av los laureles nro 500','71392864',35,'MASCULINO'); 

INSERT INTO profesores (nombre,apellido,nif,telefono,email,titulacion,foto) VALUES ('Juan','Perez','12345678','984528488','juan@gmail.com','Ing. Sistemas','');
INSERT INTO profesores (nombre,apellido,nif,telefono,email,titulacion,foto) VALUES ('Pablo','Rodríguez','12345679','984531488','pablo@gmail.com','Ing. Civil','');

INSERT INTO cursos (nombre,etapa,nivel,profesor_id) VALUES ('Raz. Lógico','5to Secundaria',1,1);
INSERT INTO cursos (nombre,etapa,nivel,profesor_id) VALUES ('Raz. Matemático','Verano',2,2);
INSERT INTO cursos (nombre,etapa,nivel,profesor_id) VALUES ('Raz. Verbal','Semestral',3,1);
INSERT INTO cursos (nombre,etapa,nivel,profesor_id) VALUES ('Física','Semestral',3,1);
INSERT INTO cursos (nombre,etapa,nivel,profesor_id) VALUES ('Matemática','Semestral',3,1);
INSERT INTO cursos (nombre,etapa,nivel,profesor_id) VALUES ('Biología','Semestral',3,1);
INSERT INTO cursos (nombre,etapa,nivel,profesor_id) VALUES ('Inglés','Semestral',3,1);
INSERT INTO cursos (nombre,etapa,nivel,profesor_id) VALUES ('Filosofía','Semestral',3,1);
INSERT INTO cursos (nombre,etapa,nivel,profesor_id) VALUES ('Psicología','Semestral',3,1);
INSERT INTO cursos (nombre,etapa,nivel,profesor_id) VALUES ('Anatomía','Semestral',3,1);

insert into aulas (aula,area_id,curso_id) values ('A',1,10);
insert into aulas (aula,area_id,curso_id) values ('B',2,4);
insert into aulas (aula,area_id,curso_id) values ('C',3,7);
insert into aulas (aula,area_id,curso_id) values ('D',4,9);
insert into aulas (aula,area_id,curso_id) values ('E',1,6);
insert into aulas (aula,area_id,curso_id) values ('F',2,5);
insert into aulas (aula,area_id,curso_id) values ('G',3,8);
insert into aulas (aula,area_id,curso_id) values ('H',4,8);
insert into aulas (aula,area_id,curso_id) values ('I',1,1);
insert into aulas (aula,area_id,curso_id) values ('J',2,3);
insert into aulas (aula,area_id,curso_id) values ('K',3,9);
insert into aulas (aula,area_id,curso_id) values ('L',4,5);
insert into aulas (aula,area_id,curso_id) values ('M',1,2);
insert into aulas (aula,area_id,curso_id) values ('O',3,1);
insert into aulas (aula,area_id,curso_id) values ('P',4,3);

INSERT INTO tarifas (nombre,descripcion,precio,ciclo_id,tipo_alumno_id) VALUES ('Tarifa 5to Secundaria','Tarifa para los alumnos de 5to Secundaria', 544.21,4,1);
INSERT INTO tarifas (nombre,descripcion,precio,ciclo_id,tipo_alumno_id) VALUES ('Tarifa Verano','Tarifa para los alumnos de Verano', 700.21,1,2);
INSERT INTO tarifas (nombre,descripcion,precio,ciclo_id,tipo_alumno_id) VALUES ('Tarifa Semestral','Tarifa para los alumnos de Semestre', 852.24,2,2);
INSERT INTO tarifas (nombre,descripcion,precio,ciclo_id,tipo_alumno_id) VALUES ('Tarifa Cepunt','Tarifa para los alumnos de Cepunt', 852.24,2,3);

insert into matriculas (codigo_operacion,fecha_operacion,banco,monto,foto,estado,turno,tarifa_id) values (9465243,'2022-08-08','Interbank',500,'','EN PROCESO','TARDE',1);
insert into matriculas (codigo_operacion,fecha_operacion,banco,monto,foto,estado,turno,tarifa_id) values (9465243,'2022-08-08','BCP',500,'','ACEPTADA','NOCHE',1);

insert into simulacros (nombre,fecha) values ('Simulacro AREA "A"','2022-08-06');
insert into simulacros (nombre,fecha) values ('Simulacro AREA "B"','2022-08-06');
insert into simulacros (nombre,fecha) values ('Simulacro AREA "C"','2022-08-06');
insert into simulacros (nombre,fecha) values ('Simulacro AREA "D"','2022-08-06');

insert into matriculasimulacro (matricula_id,simulacro_id,pcorrecta,pincorrecta,nota) values (1,1,30,5,115);

