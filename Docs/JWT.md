# 🔐 Sistema de Autenticación con JWT en RedEmpleo

## ¿Qué es JWT y por qué lo usamos?

**JWT (JSON Web Token)** es un estándar abierto que permite transmitir información de forma segura entre el cliente (por ejemplo, un navegador web) y el servidor.

En **RedEmpleo**, lo usamos para **autenticación y autorización de usuarios** debido a sus ventajas:

- **Sin estado (Stateless)**: No se guarda sesión en el servidor; el token contiene toda la información.
- **Escalabilidad**: Permite escalar horizontalmente sin gestionar sesiones en memoria.
- **Seguridad**: Firmado digitalmente; garantiza integridad y autenticidad.
- **Experiencia de usuario**: Ideal para aplicaciones tipo SPA (Single Page Application).

---

## 🔀 ¿Cómo funciona la autenticación JWT en RedEmpleo?

1. El usuario envía credenciales al endpoint `POST /api/auth/login`.
2. El servidor verifica las credenciales.
3. Si son válidas, se genera y retorna un JWT.
4. El cliente guarda el token (usualmente en `localStorage` o `cookies`).
5. Para peticiones futuras, el token se envía en el encabezado HTTP:

```
Authorization: Bearer [token]
```

6. El servidor valida el token y procesa la solicitud si es válido.

---

## 🧹 Componentes principales del sistema JWT

### 1. `JwtTokenProvider.java`

Ubicación: `src/main/java/com/redempleo/security/JwtTokenProvider.java`

Responsabilidades:

- **Generar tokens JWT** tras autenticación válida.
- **Validar tokens**: firma, expiración y formato.

```java
public String generateToken(Authentication authentication) {
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

    String authorities = userPrincipal.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    return Jwts.builder()
        .setSubject(userPrincipal.getUsername())
        .claim("roles", authorities)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(key)
        .compact();
}
```

---

### 2. `JwtAuthenticationFilter.java`

Ubicación: `src/main/java/com/redempleo/security/JwtAuthenticationFilter.java`

Este filtro intercepta peticiones HTTP y:

- Extrae el JWT del encabezado.
- Valida el token.
- Si es válido, configura la autenticación en el contexto de seguridad.

```java
@Override
protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain) throws ServletException, IOException {

    try {
        String jwt = getJwtFromRequest(request);

        if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
            String username = jwtTokenProvider.getUsernameFromToken(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    } catch (Exception ex) {
        logger.error("Could not set user authentication in security context", ex);
    }

    filterChain.doFilter(request, response);
}
```

---

### 3. `JwtAuthenticationEntryPoint.java`

Ubicación: `src/main/java/com/redempleo/security/JwtAuthenticationEntryPoint.java`

Maneja errores de autenticación, devolviendo HTTP 401 (No autorizado) en formato JSON si el token es inválido o expirado.

---

### 4. `CustomUserDetailsService.java`

Ubicación: `src/main/java/com/redempleo/security/CustomUserDetailsService.java`

Se encarga de:

- Buscar el usuario en la base de datos.
- Verificar si está habilitado.
- Retornar un `UserPrincipal` con roles y credenciales.

---

### 5. `UserPrincipal.java`

Ubicación: `src/main/java/com/redempleo/security/UserPrincipal.java`

Representa al usuario autenticado. Implementa `UserDetails` de Spring.

Incluye:

- Nombre, email, contraseña
- Roles y autoridades
- Métodos para cuenta habilitada, no expirada, etc.

---

### 6. `SecurityConfig.java`

Ubicación: `src/main/java/com/redempleo/config/SecurityConfig.java`

Configura:

- Filtro JWT
- Rutas públicas y protegidas
- Políticas CORS
- Autenticación basada en roles (`ADMIN`, `EMPRESA`, `USUARIO`)

---

### 7. `AuthService` y `AuthController`

- **`AuthController`**: expone los endpoints:
    - `POST /api/auth/login`
    - `POST /api/auth/signup`
- **`AuthService`**: lógica para login y registro.

---

## 🛡️ Seguridad y consideraciones

- **Secreto JWT**: configurado en `application.properties`. No debe exponerse.
- **Expiración**: tokens tienen vida limitada (`jwt.expiration`).
- **Almacenamiento**: el frontend guarda y envía el token en cada petición.
- **HTTPS**: obligatorio en producción para evitar interceptaciones.

---

## 📦 Ejemplo de uso

### 🔑 Inicio de sesión

Petición:
POST /api/auth/login
```json
{
  "username": "usuario1",
  "password": "user123"
}
```

Respuesta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "usuario1",
  "nombre": "Juan",
  "apellidos": "Pérez López",
  "email": "juan.perez@email.com",
  "roles": ["ROLE_USUARIO"]
}
```

---

### 🔐 Acceso a recursos protegidos

Encabezado HTTP:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

Si el token es válido y el usuario tiene permisos, se procesa la solicitud. En caso contrario:

- `401 Unauthorized`: token inválido o expirado
- `403 Forbidden`: sin permisos

---

## ✅ Conclusión

El sistema de autenticación **JWT en RedEmpleo** proporciona una solución segura, escalable y sin estado para manejar autenticación y autorización de usuarios. Ideal para arquitecturas distribuidas y microservicios.

---

## 🖼️ Diagrama de arquitectura

![Arquitectura JWT en RedEmpleo](grafico.png)
<!-- Reemplaza "grafico.png" con la URL o ruta relativa de la imagen -->

