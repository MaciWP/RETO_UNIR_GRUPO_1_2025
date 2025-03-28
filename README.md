# RedEmpleo - Sistema de Gestión de Vacantes

Este proyecto implementa el backend de un sistema de gestión de vacantes y solicitudes de empleo utilizando Spring Boot y MySQL.

## Requisitos

- Java 17 o superior
- Maven 3.6 o superior
- MySQL 8.0

## Configuración

1. **Base de datos**:
    - Crear una base de datos MySQL llamada `redempleo`
    - Ejecutar los scripts de inicialización:
        - `src/main/resources/db/schema.sql`
        - `src/main/resources/db/data.sql`

2. **Configuración de la aplicación**:
    - Revisar y ajustar los valores en `src/main/resources/application.properties`
    - Configurar la ruta de almacenamiento de archivos (`file.upload.directory`)

## Ejecución

### Compilar el proyecto
```bash
mvn clean package
```

### Ejecutar la aplicación
```bash
java -jar target/RedEmpleo-1.0-SNAPSHOT.jar
```

O usar Maven para ejecutarlo:
```bash
mvn spring-boot:run
```

## Endpoints principales

La aplicación estará disponible en `http://localhost:8081/api`

- **Documentación API**: `http://localhost:8081/api/swagger-ui/index.html`
- **Autenticación**:
    - Login: `POST /api/auth/login`
    - Registro: `POST /api/auth/signup`
- **Vacantes**:
    - Listar vacantes públicas: `GET /api/vacantes/publicas`
    - Buscar vacantes: `GET /api/vacantes/search`
- **Solicitudes**:
    - Crear solicitud: `POST /api/solicitudes`
    - Mis solicitudes: `GET /api/solicitudes/mis-solicitudes`

## Credenciales por defecto

- **Admin**:
    - Username: `admin`
    - Password: `admin123`
- **Empresa**:
    - Username: `empresa1`
    - Password: `user123`
- **Usuario**:
    - Username: `usuario1`
    - Password: `user123`

## Configuración del directorio de uploads

Asegúrate de que el directorio configurado en `file.upload.directory` exista y tenga permisos de escritura.

## Swagger / OpenAPI

La documentación completa de la API estará disponible en:

- http://localhost:8081/api/swagger-ui/index.html