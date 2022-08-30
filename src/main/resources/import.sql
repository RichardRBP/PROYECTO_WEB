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

insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('Alex','Gonzales','934752460','alex12@gmail.com','549bb20f-83f4-401c-8c58-eba575cc7f5a_Ingresante3.jpg','av los laureles nro 900','12345678',18,'MASCULINO', 1, 1,'SI');
insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('Lucho','Rodríguez','932752460','luchito@gmail.com','alumno1.PNG','av los laureles nro 500','23456789',18,'MASCULINO', 2, 3,'NO');
insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('Fernanda','Rouse','934752410','axel@gmail.com','alumna1.PNG','av los laureles nro 100','34567890',20,'FEMENINO', 3, 3,'NO');
insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('Ricardo','Lora','934752410','axel@gmail.com','alumno2.PNG','av los laureles nro 200','45678901',20,'MASCULINO', 3, 3,'NO');
insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('REBECA','BELTRAN','934752410','bet@gmail.com','alumna2.PNG','av los laureles nro 600','56789012',20,'FEMENINO', 3, 3,'NO');
insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('Luis','Gutierrez','934252460','luis@gmail.com','7d91f734-dba8-4988-9324-bae241ed34df_Ingresante1.jpg','av los laureles nro 500','67890123',21,'MASCULINO', 1, 3,'SI');
insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('Julieta','Ramos','934752410','jul@gmail.com','alumna3.PNG','av los laureles nro 700','71392864',20,'FEMENINO', 3, 3,'NO');
insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('Jullisa','Castañeda','934752410','julissa@gmail.com','alumna4.PNG','av los laureles nro 200','78901234',20,'FEMENINO', 3, 3,'NO');
insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('Leandro','Villarroel','934752410','lea@gmail.com','alumno3.PNG','av los laureles nro 500','89012345',20,'MASCULINO', 3, 3,'NO');
insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('Ricardo','Villarroel','934752410','ric@gmail.com','alumno4.PNG','av los laureles nro 700','90123456',20,'MASCULINO', 3, 3,'NO');
insert into alumnos (nombres, apellidos, telefono, email, foto, direccion, dni, edad, sexo, carrera_id, tipo_alumno_id,ingresante) values ('Jesus','Cárdenas','934152460','jesus@gmail.com','66452156-0e29-413b-86d4-c7a9efe88c3c_Ingresante2.jpg','av los laureles nro 500','01234567',22,'MASCULINO', 1, 3,'SI');

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

insert into matriculas (codigo_operacion,fecha_operacion,banco,monto,foto,estado,turno,tarifa_id,alumno_id) values (9465243,'2022-08-08','Interbank',500,'','RESERVADA','TARDE',1,1);
insert into matriculas (codigo_operacion,fecha_operacion,banco,monto,foto,estado,turno,tarifa_id,alumno_id) values (9465243,'2022-08-08','BCP',500,'','ACEPTADA','NOCHE',1,1);
insert into matriculas (codigo_operacion,fecha_operacion,banco,monto,foto,estado,turno,tarifa_id,alumno_id) values (9465243,'2022-08-08','CONTINENTAL',800,'','RESERVADA','TARDE',1,1);

insert into simulacros (nombre,fecha) values ('Simulacro AREA "A"','2022-08-06');
insert into simulacros (nombre,fecha) values ('Simulacro AREA "B"','2022-08-06');
insert into simulacros (nombre,fecha) values ('Simulacro AREA "C"','2022-08-06');
insert into simulacros (nombre,fecha) values ('Simulacro AREA "D"','2022-08-06');

insert into matriculasimulacro (matricula_id,simulacro_id,pcorrecta,pincorrecta,nota) values (1,1,30,5,115);

