-- Script para insertar datos iniciales en la base de datos RedEmpleo
USE redempleo;

-- Insertar perfiles básicos
INSERT INTO perfiles (nombre) VALUES
('ADMIN'),
('EMPRESA'),
('USUARIO');

-- Insertar administrador por defecto (password: admin123)
INSERT INTO usuarios (username, nombre, apellidos, email, password, enabled, fecha_registro) VALUES
('admin', 'Administrador', 'Sistema', 'admin@redempleo.com', 'admin123', 1, CURRENT_DATE());

-- Asignar perfil administrador
INSERT INTO usuario_perfil (username, id_perfil) VALUES
('admin', 1);

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
('Otros', 'Otras categorías no especificadas');

-- Insertar empresas de ejemplo
INSERT INTO empresas (razon_social, descripcion, direccion_fiscal, pais) VALUES
('TechSolutions Inc.', 'Empresa líder en soluciones tecnológicas', 'Calle Innovación 123, Madrid', 'España'),
('Marketing Digital SL', 'Agencia de marketing digital especializada en SEO y SEM', 'Avenida Digital 456, Barcelona', 'España'),
('RRHH Global', 'Consultora de recursos humanos con presencia internacional', 'Calle Gestión 789, Valencia', 'España'),
('Diseños Creativos', 'Estudio de diseño gráfico y branding', 'Plaza Arte 10, Sevilla', 'España'),
('Finanzas Plus', 'Asesoría financiera y contable', 'Calle Números 15, Bilbao', 'España');

-- Insertar usuarios de ejemplo (password: user123)
INSERT INTO usuarios (username, nombre, apellidos, email, password, enabled, fecha_registro) VALUES
('empresa1', 'Representante', 'TechSolutions', 'contact@techsolutions.com', 'admin123', 1, CURRENT_DATE()),
('empresa2', 'Representante', 'Marketing Digital', 'contact@marketingdigital.com', 'admin123', 1, CURRENT_DATE()),
('usuario1', 'Juan', 'Pérez López', 'juan.perez@email.com', 'admin123', 1, CURRENT_DATE()),
('usuario2', 'María', 'García Sánchez', 'maria.garcia@email.com', 'admin123', 1, CURRENT_DATE());

-- Asignar perfiles a usuarios
INSERT INTO usuario_perfil (username, id_perfil) VALUES
('empresa1', 2),
('empresa2', 2),
('usuario1', 3),
('usuario2', 3);

-- Insertar vacantes de ejemplo
INSERT INTO vacantes (nombre, descripcion, fecha, salario, estado, destacado, id_categoria, id_empresa) VALUES
('Desarrollador Full Stack', 'Buscamos desarrollador Full Stack con experiencia en React y Spring Boot.', CURRENT_DATE(), 35000, 'CREADA', 1, 1, 1),
('Especialista en SEO', 'Se requiere especialista en SEO con conocimientos avanzados en optimización de contenidos.', CURRENT_DATE(), 28000, 'CREADA', 1, 3, 2),
('Diseñador UX/UI', 'Buscamos diseñador UX/UI con experiencia en Figma y Adobe XD.', CURRENT_DATE(), 30000, 'CREADA', 0, 4, 1),
('Desarrollador Backend Java', 'Desarrollador con experiencia en Java, Spring Boot y bases de datos relacionales.', CURRENT_DATE(), 32000, 'CREADA', 1, 1, 1),
('Community Manager', 'Gestión de redes sociales y creación de contenido para diversas marcas.', CURRENT_DATE(), 25000, 'CREADA', 0, 3, 2);

-- Insertar solicitudes de ejemplo
INSERT INTO solicitudes (fecha, comentarios, estado, id_vacante, username) VALUES
(CURRENT_DATE(), 'Me interesa mucho esta posición y creo que mi perfil se ajusta perfectamente.', 0, 1, 'usuario1'),
(CURRENT_DATE(), 'Tengo amplia experiencia en el sector y estaría encantado de formar parte de su equipo.', 0, 2, 'usuario1'),
(CURRENT_DATE(), 'Me apasiona el diseño UX/UI y me gustaría aportar valor a su empresa.', 0, 3, 'usuario2');