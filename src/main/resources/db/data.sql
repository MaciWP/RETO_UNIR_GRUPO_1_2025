-- src/main/resources/db/data.sql
USE redempleo;

-- Insertar perfiles básicos
INSERT INTO perfiles (nombre) VALUES
('ADMIN'),
('EMPRESA'),
('USUARIO'),
('MODERADOR');

-- Insertar administradores por defecto (password: admin123)
INSERT INTO usuarios (username, nombre, apellidos, email, password, enabled, fecha_registro) VALUES
('admin', 'Administrador', 'Sistema', 'admin@redempleo.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('moderador', 'Moderador', 'Contenidos', 'moderador@redempleo.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE());

-- Asignar perfiles administrativos
INSERT INTO usuario_perfil (username, id_perfil, id_empresa, comprobado) VALUES
('admin', 1, 0, 1),
('moderador', 4, 0, 1);

-- Insertar categorías de vacantes
INSERT INTO categorias (nombre, descripcion) VALUES
('Tecnología', 'Puestos relacionados con desarrollo de software, sistemas, redes, etc.'),
('Administración', 'Puestos administrativos y de gestión'),
('Marketing', 'Puestos de marketing digital, tradicional, ventas, etc.'),
('Diseño', 'Puestos de diseño gráfico, UX/UI, etc.'),
('Recursos Humanos', 'Puestos relacionados con la gestión de personal'),
('Finanzas', 'Puestos relacionados con contabilidad, finanzas, etc.'),
('Educación', 'Puestos relacionados con enseñanza y formación'),
('Salud', 'Puestos relacionados con medicina, enfermería, etc.'),
('Legal', 'Puestos relacionados con abogacía, asesoría jurídica, etc.'),
('Otros', 'Otras categorías no especificadas'),
('Logística', 'Puestos relacionados con la cadena de suministro, almacenaje y distribución'),
('Construcción', 'Puestos relacionados con obras, proyectos de edificación y arquitectura'),
('Hostelería', 'Puestos en hoteles, restaurantes y servicios turísticos'),
('Atención al cliente', 'Puestos enfocados en la relación con clientes'),
('Informática', 'Subcategoría específica dentro de Tecnología para roles informáticos'),
('Investigación', 'Puestos relacionados con I+D+i en diversos sectores'),
('Medio Ambiente', 'Trabajos relacionados con sostenibilidad y gestión ambiental'),
('Telecomunicaciones', 'Especialistas en redes y sistemas de comunicación'),
('Audiovisual', 'Producción, edición y gestión de contenidos audiovisuales'),
('Comercial', 'Ventas, negociación y representación comercial');

-- Insertar empresas
INSERT INTO empresas (razon_social, descripcion, direccion_fiscal, pais) VALUES
('TechSolutions Inc.', 'Empresa líder en soluciones tecnológicas', 'Calle Innovación 123, Madrid', 'España'),
('Marketing Digital SL', 'Agencia de marketing digital especializada en SEO y SEM', 'Avenida Digital 456, Barcelona', 'España'),
('RRHH Global', 'Consultora de recursos humanos con presencia internacional', 'Calle Gestión 789, Valencia', 'España'),
('Diseños Creativos', 'Estudio de diseño gráfico y branding', 'Plaza Arte 10, Sevilla', 'España'),
('Finanzas Plus', 'Asesoría financiera y contable', 'Calle Números 15, Bilbao', 'España'),
('Consultora TechPro', 'Servicios de consultoría en tecnología y desarrollo de software', 'Calle Innovación 45, Madrid', 'España'),
('Hospital Central', 'Centro hospitalario con especialidades médicas', 'Avenida Salud 123, Barcelona', 'España'),
('Academia Formadores', 'Centro de formación profesional y cursos especializados', 'Plaza Educación 78, Valencia', 'España'),
('Transportes Rápidos', 'Servicios de logística y transporte nacional e internacional', 'Calle Logística 34, Sevilla', 'España'),
('Construcciones Modernas', 'Empresa dedicada a proyectos de construcción y arquitectura', 'Avenida Arquitectura 56, Bilbao', 'España'),
('EcoSolutions', 'Consultoría e implementación de proyectos sostenibles', 'Calle Verde 22, Madrid', 'España'),
('LegalAdvice', 'Despacho de abogados especializado en derecho mercantil', 'Avenida Justicia 87, Barcelona', 'España'),
('MediaPro', 'Productora audiovisual con proyectos nacionales e internacionales', 'Calle Cine 45, Madrid', 'España'),
('RestaurantChain', 'Cadena de restaurantes de comida mediterránea', 'Plaza Gastronomía 12, Valencia', 'España'),
('TurismoPlus', 'Agencia de viajes especializada en turismo de aventura', 'Calle Destinos 56, Málaga', 'España');

-- Insertar usuarios (password: admin123 - ya hashada)
INSERT INTO usuarios (username, nombre, apellidos, email, password, enabled, fecha_registro) VALUES
('empresa1', 'Representante', 'TechSolutions', 'contact@techsolutions.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('empresa2', 'Representante', 'Marketing Digital', 'contact@marketingdigital.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('empresa3', 'Representante', 'Hospital Central', 'contact@hospitalcentral.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('empresa4', 'Representante', 'Academia Formadores', 'contact@academiaformadores.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('empresa5', 'Representante', 'Construcciones Modernas', 'contact@construccionesmodernas.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('empresa6', 'Representante', 'EcoSolutions', 'contact@ecosolutions.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('empresa7', 'Representante', 'MediaPro', 'contact@mediapro.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('usuario1', 'Juan', 'Pérez López', 'juan.perez@email.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('usuario2', 'María', 'García Sánchez', 'maria.garcia@email.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('usuario3', 'Carlos', 'Rodríguez Gómez', 'carlos.rodriguez@email.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('usuario4', 'Laura', 'Martínez Fernández', 'laura.martinez@email.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('usuario5', 'Pablo', 'Sánchez Torres', 'pablo.sanchez@email.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('usuario6', 'Ana', 'Jiménez Ruiz', 'ana.jimenez@email.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('usuario7', 'David', 'López Martín', 'david.lopez@email.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('usuario8', 'Carmen', 'González Torres', 'carmen.gonzalez@email.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE()),
('usuario9', 'Javier', 'Díaz Romero', 'javier.diaz@email.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 0, CURRENT_DATE()),
('usuario10', 'Lucía', 'Hernández Gil', 'lucia.hernandez@email.com', '$2a$10$wv8A85hyMV.jzZ6TsdBB6eNGmkhVMWuLK34Ye2qHLsnh6IZPMipBO', 1, CURRENT_DATE());

-- Asignar perfiles a usuarios
INSERT INTO usuario_perfil (username, id_perfil, id_empresa, comprobado) VALUES
('empresa1', 2, 1, 1),
('empresa2', 2, 2, 1),
('empresa3', 2, 7, 1),
('empresa4', 2, 8, 1),
('empresa5', 2, 10, 1),
('empresa6', 2, 11, 0),
('empresa7', 2, 13, 0),
('usuario1', 3, 0, 1),
('usuario2', 3, 0, 1),
('usuario3', 3, 0, 1),
('usuario4', 3, 0, 1),
('usuario5', 3, 0, 0),
('usuario6', 3, 0, 1),
('usuario7', 3, 0, 1),
('usuario8', 3, 0, 1),
('usuario9', 3, 0, 0),
('usuario10', 3, 0, 1);

-- Insertar vacantes
INSERT INTO vacantes (nombre, descripcion, fecha, salario, estado, destacado, id_categoria, id_empresa) VALUES
-- Vacantes TechSolutions (id_empresa = 1)
('Desarrollador Full Stack', 'Buscamos desarrollador Full Stack con experiencia en React y Spring Boot.', CURRENT_DATE(), 35000, 'CREADA', 1, 1, 1),
('Diseñador UX/UI', 'Buscamos diseñador UX/UI con experiencia en Figma y Adobe XD.', CURRENT_DATE(), 30000, 'CREADA', 0, 4, 1),
('Desarrollador Backend Java', 'Desarrollador con experiencia en Java, Spring Boot y bases de datos relacionales.', CURRENT_DATE(), 32000, 'CREADA', 1, 1, 1),
('Administrativo/a', 'Puesto administrativo para gestión documental y atención telefónica.', CURRENT_DATE(), 22000, 'CREADA', 0, 2, 1),
('Desarrollador/a Frontend', 'Buscamos desarrollador/a frontend con experiencia en React y Vue.', CURRENT_DATE(), 38000, 'CREADA', 1, 1, 1),
('DevOps Engineer', 'Ingeniero DevOps con experiencia en CI/CD, Docker y Kubernetes.', DATE_SUB(CURRENT_DATE(), INTERVAL 30 DAY), 42000, 'ASIGNADA', 0, 1, 1),
('Analista de Datos', 'Especialista en análisis de datos con conocimientos de Python y herramientas BI.', DATE_SUB(CURRENT_DATE(), INTERVAL 15 DAY), 36000, 'CREADA', 1, 1, 1),

-- Vacantes Marketing Digital SL (id_empresa = 2)
('Especialista en SEO', 'Se requiere especialista en SEO con conocimientos avanzados en optimización de contenidos.', CURRENT_DATE(), 28000, 'CREADA', 1, 3, 2),
('Community Manager', 'Gestión de redes sociales y creación de contenido para diversas marcas.', CURRENT_DATE(), 25000, 'CREADA', 0, 3, 2),
('Soporte Técnico IT', 'Técnico de soporte para resolver incidencias informáticas.', CURRENT_DATE(), 28000, 'CREADA', 0, 15, 2),
('Responsable Atención Cliente', 'Coordinar equipo de atención al cliente y gestionar reclamaciones.', CURRENT_DATE(), 32000, 'CREADA', 1, 14, 2),
('Especialista en Google Ads', 'Profesional con experiencia en campañas de Google Ads y certificaciones.', DATE_SUB(CURRENT_DATE(), INTERVAL 10 DAY), 30000, 'CREADA', 1, 3, 2),
('Analista de Marketing', 'Análisis de campañas y métricas para optimización de estrategias.', DATE_SUB(CURRENT_DATE(), INTERVAL 20 DAY), 29000, 'CANCELADA', 0, 3, 2),

-- Vacantes Hospital Central (id_empresa = 7)
('Enfermero/a UCI', 'Necesitamos enfermero/a para la unidad de cuidados intensivos con experiencia mínima de 2 años.', CURRENT_DATE(), 32000, 'CREADA', 1, 8, 7),
('Recepcionista Hospital', 'Recepcionista para hospital de 4 estrellas con idiomas (inglés y francés).', CURRENT_DATE(), 25000, 'CREADA', 0, 13, 7),
('Médico Especialista', 'Especialista en medicina interna para consultas y seguimiento de pacientes.', DATE_SUB(CURRENT_DATE(), INTERVAL 45 DAY), 60000, 'CREADA', 1, 8, 7),
('Auxiliar de Enfermería', 'Personal auxiliar para planta de hospitalización.', DATE_SUB(CURRENT_DATE(), INTERVAL 5 DAY), 22000, 'CREADA', 0, 8, 7),

-- Vacantes Academia Formadores (id_empresa = 8)
('Profesor/a de Programación', 'Buscamos profesor/a para impartir cursos de programación web (HTML, CSS, JavaScript).', CURRENT_DATE(), 30000, 'CREADA', 1, 7, 8),
('Conductor/a de Reparto', 'Conductor con carnet B para reparto en zona urbana.', CURRENT_DATE(), 24000, 'CREADA', 0, 11, 8),
('Formador/a en Marketing Digital', 'Profesor/a para cursos presenciales y online sobre marketing digital.', DATE_SUB(CURRENT_DATE(), INTERVAL 15 DAY), 32000, 'CREADA', 1, 7, 8),
('Orientador/a Laboral', 'Profesional para asesoramiento y orientación a estudiantes.', DATE_SUB(CURRENT_DATE(), INTERVAL 25 DAY), 26000, 'ASIGNADA', 0, 7, 8),

-- Vacantes Construcciones Modernas (id_empresa = 10)
('Responsable de RRHH', 'Gestión del departamento de recursos humanos y procesos de selección.', CURRENT_DATE(), 42000, 'CREADA', 1, 5, 10),
('Arquitecto/a Técnico', 'Profesional para supervisión de obras y proyectos de edificación.', CURRENT_DATE(), 37000, 'CREADA', 1, 12, 10),
('Ingeniero/a Civil', 'Ingeniero/a para diseño y cálculo de estructuras.', DATE_SUB(CURRENT_DATE(), INTERVAL 60 DAY), 40000, 'CREADA', 1, 12, 10),
('Jefe/a de Obra', 'Dirección y coordinación de equipos en obra.', DATE_SUB(CURRENT_DATE(), INTERVAL 30 DAY), 38000, 'CANCELADA', 0, 12, 10),

-- Vacantes EcoSolutions (id_empresa = 11)
('Consultor/a Medioambiental', 'Consultoría para proyectos de sostenibilidad y eficiencia energética.', CURRENT_DATE(), 32000, 'CREADA', 1, 17, 11),
('Técnico de Calidad Ambiental', 'Responsable de evaluaciones y certificaciones ambientales.', CURRENT_DATE(), 29000, 'CREADA', 0, 17, 11),
('Investigador/a Energías Renovables', 'Investigación aplicada en tecnologías renovables.', DATE_SUB(CURRENT_DATE(), INTERVAL 20 DAY), 36000, 'CREADA', 1, 16, 11),

-- Vacantes MediaPro (id_empresa = 13)
('Editor/a de Vídeo', 'Montaje y postproducción de contenidos audiovisuales.', CURRENT_DATE(), 28000, 'CREADA', 1, 19, 13),
('Operador/a de Cámara', 'Grabación de eventos y producciones audiovisuales.', CURRENT_DATE(), 26000, 'CREADA', 0, 19, 13),
('Director/a de Producción', 'Coordinación de producciones audiovisuales.', DATE_SUB(CURRENT_DATE(), INTERVAL 10 DAY), 45000, 'CREADA', 1, 19, 13);

-- Insertar solicitudes
INSERT INTO solicitudes (fecha, comentarios, estado, destacado, id_vacante, username) VALUES
-- Solicitudes de usuario1
(CURRENT_DATE(), 'Me interesa mucho esta posición y creo que mi perfil se ajusta perfectamente.', 0, 0, 1, 'usuario1'),
(CURRENT_DATE(), 'Tengo amplia experiencia en el sector y estaría encantado de formar parte de su equipo.', 0, 0, 8, 'usuario1'),
(CURRENT_DATE(), 'He impartido cursos similares durante 3 años en otra academia.', 0, 0, 17, 'usuario1'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 45 DAY), 'Cuento con las certificaciones necesarias y experiencia en proyectos similares.', 1, 0, 6, 'usuario1'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 15 DAY), 'Mi formación en sostenibilidad y mi experiencia previa me permiten aportar valor a su equipo.', 0, 0,  28, 'usuario1'),

-- Solicitudes de usuario2
(CURRENT_DATE(), 'Me apasiona el diseño UX/UI y me gustaría aportar valor a su empresa.', 0, 0, 2, 'usuario2'),
(CURRENT_DATE(), 'Tengo amplia experiencia en UCI y actualmente busco un nuevo reto profesional.', 0, 0, 14, 'usuario2'),
(CURRENT_DATE(), 'Cuento con más de 7 años de experiencia en RRHH en empresas similares.', 0, 0, 22, 'usuario2'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 25 DAY), 'Mi experiencia previa como docente y mi formación continua me hacen un candidato idóneo.', 1, 0, 20, 'usuario2'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 20 DAY), 'Estoy especializado en dirección de equipos y optimización de recursos.', 2, 0, 25, 'usuario2'),

-- Solicitudes de usuario3
(CURRENT_DATE(), 'Mi experiencia en frontend es de 5 años trabajando con diversas tecnologías.', 0, 0, 5, 'usuario3'),
(CURRENT_DATE(), 'Domino inglés (C1) y francés (B2) y tengo experiencia en hoteles.', 0, 0, 15, 'usuario3'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 10 DAY), 'Mi portfolio incluye varios proyectos audiovisuales que demuestran mi capacidad técnica.', 0, 0, 30, 'usuario3'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 30 DAY), 'Tengo experiencia en análisis de datos en el sector sanitario.', 0, 0, 16, 'usuario3'),

-- Solicitudes de usuario4
(CURRENT_DATE(), 'Me especializo en React y tengo varios proyectos que puedo mostrar.', 0, 0, 5, 'usuario4'),
(CURRENT_DATE(), 'Mi formación en marketing digital y experiencia en SEO me permiten optimizar estrategias efectivas.', 0, 0, 8, 'usuario4'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 15 DAY), 'Como arquitecto he trabajado en proyectos similares con excelentes resultados.', 0, 0, 23, 'usuario4'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 5 DAY), 'Mi experiencia en entornos hospitalarios garantiza una rápida adaptación.', 0, 0, 16, 'usuario4'),

-- Solicitudes de usuario5
(CURRENT_DATE(), 'Soy arquitecto técnico colegiado con 10 años de experiencia en el sector.', 0, 0, 23, 'usuario5'),
(CURRENT_DATE(), 'Mi experiencia en soporte técnico es de 4 años resolviendo incidencias.', 0, 0, 10, 'usuario5'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 20 DAY), 'Mi formación como ingeniero y mi experiencia en obra me capacitan para este puesto.', 0, 0, 24, 'usuario5'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 10 DAY), 'Cuento con formación específica en eficiencia energética y construcción sostenible.', 0, 0, 28, 'usuario5'),

-- Solicitudes de usuario6
(CURRENT_DATE(), 'Mi experiencia en desarrollo backend con Java y Spring me permite aportar soluciones robustas.', 0, 0, 3, 'usuario6'),
(CURRENT_DATE(), 'He trabajado como analista de marketing en sectores diversos con resultados medibles.', 0, 0, 13, 'usuario6'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 30 DAY), 'Mi formación médica y experiencia en UCI son ideales para esta posición.', 0, 0, 16, 'usuario6'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 5 DAY), 'Mi experiencia en diseño de interfaces centradas en usuario aporta valor añadido.', 0, 0, 2, 'usuario6'),

-- Solicitudes de usuario7
(CURRENT_DATE(), 'Mi experiencia en entornos DevOps me permite optimizar los procesos de desarrollo.', 0, 0, 6, 'usuario7'),
(CURRENT_DATE(), 'Como consultor medioambiental he participado en proyectos de gran impacto.', 0, 0, 28, 'usuario7'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 15 DAY), 'Mi formación en producción audiovisual me permite coordinar equipos eficientemente.', 0, 0, 31, 'usuario7'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 10 DAY), 'Cuento con experiencia en campañas de Google Ads con ROI superior al promedio del sector.', 0, 0, 12, 'usuario7'),

-- Solicitudes de usuario8
(CURRENT_DATE(), 'Como analista de datos he implementado soluciones que han mejorado la toma de decisiones.', 0, 7, 'usuario8'),
(CURRENT_DATE(), 'Mi experiencia docente y conocimientos en programación me permiten transmitir conceptos complejos.', 0, 17, 'usuario8'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 25 DAY), 'Mi formación en enfermería y experiencia en UCI garantizan una atención de calidad.', 0, 0, 14, 'usuario8'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 5 DAY), 'He dirigido equipos de atención al cliente mejorando los índices de satisfacción.', 0, 0, 11, 'usuario8'),

-- Solicitudes de usuario10
(CURRENT_DATE(), 'Mi experiencia como operador de cámara incluye eventos deportivos y culturales.', 0, 30, 'usuario10'),
(CURRENT_DATE(), 'Como técnico de calidad ambiental he implementado sistemas de gestión ISO 14001.', 0, 29, 'usuario10'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 20 DAY), 'Mi formación en administración y experiencia en gestión documental son idóneas.', 0, 4, 'usuario10'),
(DATE_SUB(CURRENT_DATE(), INTERVAL 15 DAY), 'He trabajado en investigación de energías renovables con publicaciones relevantes.', 0, 29, 'usuario10');