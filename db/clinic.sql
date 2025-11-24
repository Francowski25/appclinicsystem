-- Crear base de datos
CREATE DATABASE clinic;
USE clinic;

-- Tabla: tpatient (Pacientes)
CREATE TABLE tpatient (
    idPatient VARCHAR(37) PRIMARY KEY,       
    dni VARCHAR(8) UNIQUE NOT NULL,                 
    firstName VARCHAR(50) NOT NULL,                 
    surNameP VARCHAR(100) NOT NULL,           
    surNameM VARCHAR(100) NOT NULL,                             
    gender ENUM('masculino', 'femenino', 'otro') NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,            
    phone VARCHAR(20),                              
    password VARCHAR(100) NOT NULL,                  
    createdAt datetime not null,
    status ENUM('activo', 'desactivo')  
);

-- Tabla: temployee (Empleados)
CREATE TABLE temployee (
    idEmployee VARCHAR(37) PRIMARY KEY,       
    dni VARCHAR(8) UNIQUE NOT NULL,         
    firstName VARCHAR(100) NOT NULL,                 
    surNameP VARCHAR(100) NOT NULL,            
    surNameM VARCHAR(100) NOT NULL,                                        
    role ENUM('administrador', 'recepcionista', 'medico') NOT NULL, 
    phone VARCHAR(20), 
    email VARCHAR(100),                             
    password VARCHAR(100) NOT NULL,
    createdAt datetime not null,
    status ENUM('activo', 'desactivo')                    
);


-- Tabla: tappointmentRequest (Solicitud de cita)
CREATE TABLE tappointmentRequest (
    idRequest VARCHAR(37) PRIMARY KEY,        
    idPatient VARCHAR(37) NOT NULL,                          
    idEmployee VARCHAR(37) NOT NULL,                         
    requestDate DATE NOT NULL,                        
    requestTime TIME NOT NULL,                       
    reason TEXT,                                     
    status ENUM('pendiente', 'aprobada', 'rechazada') DEFAULT 'pendiente', 
    createdAt datetime not null,   
    FOREIGN KEY (idPatient) REFERENCES tpatient(idPatient),
    FOREIGN KEY (idEmployee) REFERENCES temployee(idEmployee)
);

-- Tabla: tappointment (Citas)
CREATE TABLE tappointment (
    idAppt VARCHAR(37) PRIMARY KEY,     
    idPatient VARCHAR(37) NOT NULL,                           
    idEmployee VARCHAR(37) NOT NULL,                          
    apptDate DATE NOT NULL,                    
    apptTime TIME NOT NULL,                   
    reason TEXT,                                      
    status ENUM('confirmada', 'cancelada', 'atendida', 'reprogramada') DEFAULT 'confirmada',
    type ENUM('online', 'offline') NOT NULL DEFAULT 'offline',
    createdAt datetime not null,        
    FOREIGN KEY (idPatient) REFERENCES tpatient(idPatient),
    FOREIGN KEY (idEmployee) REFERENCES temployee(idEmployee)
);

-- Tabla: tprescription (Recetas)
CREATE TABLE tprescription (
    idPrescription VARCHAR(37) PRIMARY KEY,    
    medicine VARCHAR(100) NOT NULL,                   
    dosage VARCHAR(50) NOT NULL,                      
    frequency VARCHAR(50),                            
    duration VARCHAR(50),                             
    instructions TEXT                                 
);

-- Tabla: tconsultation (Consultas médicas)
CREATE TABLE tconsultation (
    idConsultation VARCHAR(37) PRIMARY KEY,   
    idAppt VARCHAR(37) NOT NULL,                       
    idEmployee VARCHAR(37) NOT NULL,                          
    idPrescription VARCHAR(37) NOT NULL,                      
    createdAt datetime not null,                  
    diagnosis TEXT,                                   
    treatment TEXT,                                   
    FOREIGN KEY (idAppt) REFERENCES tappointment(idAppt),
    FOREIGN KEY (idEmployee) REFERENCES temployee(idEmployee),
    FOREIGN KEY (idPrescription) REFERENCES tprescription(idPrescription)
);

-- Tabla: tschedule (Horarios)
CREATE TABLE tschedule (
    idSchedule VARCHAR(37) PRIMARY KEY,        
    idEmployee VARCHAR(37) NOT NULL,                          
    startDate DATE NOT NULL,                          
    endDate DATE NOT NULL,                            
    startTime TIME NOT NULL,                          
    endTime TIME NOT NULL,                            
    FOREIGN KEY (idEmployee) REFERENCES temployee(idEmployee)
);

-- Tabla: tscheduleDetail (Horarios por dia)
CREATE TABLE tscheduleDetail (
    idDetail VARCHAR(37) PRIMARY KEY,
    idSchedule VARCHAR(37) NOT NULL,
    scheduleDate DATE NOT NULL,
    startTime TIME NOT NULL,
    endTime TIME NOT NULL,
    status ENUM('libre', 'ocupado') DEFAULT 'libre',
    FOREIGN KEY (idSchedule) REFERENCES tschedule(idSchedule)
);

-- Tabla: tspecialty (Especialidades)
CREATE TABLE tspecialty (
    idSpecialty VARCHAR(37) PRIMARY KEY,       
    name VARCHAR(100) NOT NULL UNIQUE,                        
    description VARCHAR(100)                          
);

-- Tabla: tservice (Servicios)
CREATE TABLE tservice (
    idService VARCHAR(37) PRIMARY KEY,         
    idSpecialty VARCHAR(37) NOT NULL,                         
    name VARCHAR(100) NOT NULL UNIQUE,                       
    description VARCHAR(500),                        
    FOREIGN KEY (idSpecialty) REFERENCES tspecialty(idSpecialty)
);

-- Tabla: tdoctorSpecialty (Relación médico-especialidad)
CREATE TABLE tdoctorSpecialty (
    idEmployee VARCHAR(37) NOT NULL,                          
    idSpecialty VARCHAR(37) NOT NULL,                         
    PRIMARY KEY (idEmployee, idSpecialty),
    FOREIGN KEY (idEmployee) REFERENCES temployee(idEmployee),
    FOREIGN KEY (idSpecialty) REFERENCES tspecialty(idSpecialty)
);

INSERT INTO tpatient VALUES
('id-franco','75576009','Franco','Taype','Huamani','masculino','franco@gmail.com','918355614','franco25','2025-01-01','activo'),
('pat-002','23456789','Maria','Gomez','Torres','femenino','maria.gomez@example.com','987111222','pass2','2025-01-02','activo'),
('pat-003','34567890','Luis','Ramirez','Soto','masculino','luis.ramirez@example.com','944332211','pass3','2025-01-03','activo'),
('pat-004','45678901','Ana','Torres','Cruz','femenino','ana.torres@example.com','933221144','pass4','2025-01-04','activo'),
('pat-005','56789012','Carlos','Vega','Perez','masculino','carlos.vega@example.com','912223344','pass5','2025-01-05','activo'),
('pat-006','67890123','Lucia','Lopez','Mendoza','femenino','lucia.lopez@example.com','955667788','pass6','2025-01-06','activo'),
('pat-007','78901234','Jorge','Sanchez','Diaz','masculino','jorge.sanchez@example.com','966778899','pass7','2025-01-07','activo'),
('pat-008','89012345','Rosa','Flores','Garcia','femenino','rosa.flores@example.com','911223355','pass8','2025-01-08','activo'),
('pat-009','90123456','Pedro','Castro','Aguilar','masculino','pedro.castro@example.com','944556677','pass9','2025-01-09','activo'),
('pat-010','01234567','Elena','Paredes','Nuñez','femenino','elena.paredes@example.com','955334466','pass10','2025-01-10','activo');

INSERT INTO temployee VALUES
('emp-001','11111111','Jack Amilcar','Aymara','Enciso','medico','987000111','jack@gmail.com','jack','2025-01-01','activo'),
('emp-002','22222222','Roger','Juro','Ayquipa','administrador','987000222','roger@gmail.com','roger','2025-01-02','activo'),
('emp-003','33333333','Diego','Mendoza','Vargas','medico','987000333','diego.mendoza@clinic.com','pass3','2025-01-03','desactivo'),
('emp-004','44444444','Sofia','Torres','Castro','medico','987000444','sofia.torres@clinic.com','pass4','2025-01-04','activo'),
('emp-005','55555555','Ricardo','Salazar','Reyes','medico','987000555','ricardo.salazar@clinic.com','pass5','2025-01-05','activo'),
('emp-006','66666666','Yamil','Quispe','Lopez','recepcionista','987000666','yamil@gmail.com','yamil','2025-01-06','activo'),
('emp-007','77777777','Fernando','Vega','Huaman','medico','987000777','fernando.vega@clinic.com','pass7','2025-01-07','activo'),
('emp-008','88888888','Patricia','Diaz','Mora','medico','987000888','patricia.diaz@clinic.com','pass8','2025-01-08','activo'),
('emp-009','99999999','Oscar','Rojas','Silva','medico','987000999','oscar.rojas@clinic.com','pass9','2025-01-09','activo'),
('emp-010','10101010','Andrea','Aguilar','Perez','recepcionista','987001010','andrea.aguilar@clinic.com','pass10','2025-01-10','activo');

INSERT INTO tspecialty VALUES
('spe-001','Cardiología','Corazón y sistema circulatorio'),
('spe-002','Pediatría','Salud infantil'),
('spe-003','Dermatología','Piel y enfermedades dérmicas'),
('spe-004','Neurología','Cerebro y sistema nervioso'),
('spe-005','Ginecología','Salud femenina'),
('spe-006','Traumatología','Huesos y músculos'),
('spe-007','Oftalmología','Vista y ojos'),
('spe-008','Odontología','Salud dental'),
('spe-009','Urología','Vías urinarias'),
('spe-010','Psiquiatría','Salud mental');

INSERT INTO tservice VALUES
('srv-001','spe-001','Electrocardiograma','Prueba de actividad eléctrica del corazón'),
('srv-002','spe-002','Consulta pediátrica','Atención para niños y adolescentes'),
('srv-003','spe-003','Limpieza dermatológica','Procedimiento de limpieza profunda de piel'),
('srv-004','spe-004','Electroencefalograma','Estudio de actividad cerebral'),
('srv-005','spe-005','Control ginecológico','Evaluación de salud femenina'),
('srv-006','spe-006','Rehabilitación física','Terapia para recuperación muscular'),
('srv-007','spe-007','Examen de vista','Evaluación de agudeza visual'),
('srv-008','spe-008','Limpieza dental','Procedimiento dental preventivo'),
('srv-009','spe-009','Consulta urológica','Evaluación del sistema urinario'),
('srv-010','spe-010','Evaluación psiquiátrica','Diagnóstico de salud mental');

INSERT INTO tdoctorSpecialty VALUES
('emp-003','spe-001'),
('emp-003','spe-004'),
('emp-004','spe-005'),
('emp-004','spe-003'),
('emp-005','spe-006'),
('emp-007','spe-001'),
('emp-007','spe-007'),
('emp-008','spe-008'),
('emp-009','spe-010'),
('emp-009','spe-009');

INSERT INTO tschedule VALUES
('sch-001','emp-003','2025-01-01','2025-01-30','08:00','14:00'),
('sch-002','emp-004','2025-01-01','2025-01-30','09:00','15:00'),
('sch-003','emp-005','2025-01-01','2025-01-30','08:00','13:00'),
('sch-004','emp-007','2025-01-01','2025-01-30','10:00','16:00'),
('sch-005','emp-008','2025-01-01','2025-01-30','07:00','12:00'),
('sch-006','emp-009','2025-01-01','2025-01-30','11:00','17:00'),
('sch-007','emp-003','2025-02-01','2025-02-28','08:00','14:00'),
('sch-008','emp-004','2025-02-01','2025-02-28','09:00','15:00'),
('sch-009','emp-005','2025-02-01','2025-02-28','08:00','13:00'),
('sch-010','emp-007','2025-02-01','2025-02-28','10:00','16:00');

INSERT INTO tprescription VALUES
('pre-001','Ibuprofeno','400mg','Cada 8 horas','5 días','Tomar con alimentos'),
('pre-002','Paracetamol','500mg','Cada 6 horas','3 días','No exceder la dosis'),
('pre-003','Amoxicilina','500mg','Cada 12 horas','7 días','Completar tratamiento'),
('pre-004','Omeprazol','20mg','Cada 24 horas','10 días','Tomar en ayunas'),
('pre-005','Loratadina','10mg','Cada 24 horas','5 días','Evitar alcohol'),
('pre-006','Prednisona','5mg','Cada 12 horas','5 días','Tomar después de comida'),
('pre-007','Azitromicina','500mg','Cada 24 horas','3 días','Tomar con agua'),
('pre-008','Metformina','850mg','Cada 12 horas','Indefinido','Tomar después de comida'),
('pre-009','Atorvastatina','20mg','Cada 24 horas','Indefinido','Tomar por la noche'),
('pre-010','Salbutamol','2 puff','Cada 8 horas','Según necesidad','Uso inhalado');

INSERT INTO tappointmentRequest VALUES
('req-001','id-franco','emp-001',CURDATE(),'08:00','Dolor de pecho','pendiente',CURDATE()),
('req-002','id-franco','emp-004',CURDATE(),'09:00','Fiebre alta','aprobada',CURDATE()),
('req-003','id-franco','emp-005',CURDATE(),'10:00','Dolor muscular','pendiente',CURDATE()),
('req-004','id-franco','emp-001',CURDATE(),'11:00','Migraña','rechazada',CURDATE()),
('req-005','pat-005','emp-008',CURDATE(),'14:00','Problema dental','aprobada',CURDATE()),
('req-006','pat-006','emp-009',CURDATE(),'16:00','Ansiedad','pendiente',CURDATE()),
('req-007','pat-007','emp-003','2025-12-07','10:00','Revisión general','aprobada',CURDATE()),
('req-008','pat-008','emp-004','2025-12-08','12:00','Consulta dermatológica','pendiente',CURDATE()),
('req-009','pat-009','emp-005','2025-12-09','13:00','Dolor de espalda','aprobada',CURDATE()),
('req-010','pat-010','emp-007','2025-12-10','15:00','Cansancio excesivo','pendiente',CURDATE());

INSERT INTO tappointment VALUES
('apt-001','id-franco','emp-001','2025-01-02','08:00','Dolor de pecho','confirmada','offline',CURDATE()),
('apt-002','pat-002','emp-004','2025-01-03','09:00','Fiebre','confirmada','offline',CURDATE()),
('apt-003','pat-003','emp-005','2025-01-03','10:00','Dolor muscular','atendida','offline',CURDATE()),
('apt-004','pat-004','emp-007','2025-01-04','11:00','Migraña','cancelada','online',CURDATE()),
('apt-005','pat-005','emp-001','2025-01-05','14:00','Problema dental','confirmada','offline',CURDATE()),
('apt-006','pat-006','emp-009','2025-01-06','16:00','Ansiedad','atendida','online',CURDATE()),
('apt-007','pat-007','emp-001','2025-01-07','10:00','Revisión general','confirmada','offline',CURDATE()),
('apt-008','pat-008','emp-004','2025-01-08','12:00','Piel','atendida','offline',CURDATE()),
('apt-009','pat-009','emp-005','2025-01-09','13:00','Dolor de espalda','reprogramada','offline',CURDATE()),
('apt-010','pat-010','emp-007','2025-01-10','15:00','Cansancio','confirmada','online',CURDATE());

INSERT INTO tconsultation VALUES
('con-001','apt-001','emp-003','pre-001','2025-01-02','Angina leve','Reposo'),
('con-002','apt-002','emp-004','pre-002','2025-01-03','Infección viral','Hidratación'),
('con-003','apt-003','emp-005','pre-003','2025-01-03','Contractura','Fisioterapia'),
('con-004','apt-004','emp-007','pre-004','2025-01-04','Migraña','Analgesia'),
('con-005','apt-005','emp-008','pre-005','2025-01-05','Caries','Tratamiento dental'),
('con-006','apt-006','emp-009','pre-006','2025-01-06','Estrés','Reposo'),
('con-007','apt-007','emp-003','pre-007','2025-01-07','Control general','Hábitos saludables'),
('con-008','apt-008','emp-004','pre-008','2025-01-08','Dermatitis','Crema tópica'),
('con-009','apt-009','emp-005','pre-009','2025-01-09','Lumbalgia','Ejercicios'),
('con-010','apt-010','emp-007','pre-010','2025-01-10','Fatiga','Evaluación adicional');

select * from tappointmentRequest;
select * from tscheduleDetail;