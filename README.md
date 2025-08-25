# 🔎 Foro Hub

<p align="center">
  <a href="https://www.java.com/">
    <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  </a>
  <a href="https://spring.io/">
    <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring"/>
  </a>
<a href="https://www.mysql.com/">
  <img src="https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
</a>
  <a href="https://maven.apache.org/">
    <img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
  </a>
</p>

API RESTful desarrollada con Spring Boot que permite gestionar un foro de discusión con tópicos, autores y cursos. La aplicación implementa operaciones CRUD, validaciones, consultas especializadas y persiste la información en una base de datos relacional MySQL.

---

## 🌠 Funcionalidades implementadas

- Crear un nuevo tópico asociado a un autor y curso
- Listar todos los tópicos con paginación y ordenados por fecha de creación
- Obtener un tópico específico por ID
- Listar tópicos filtrados por curso y año
- Actualizar información de un tópico (título, mensaje, estado)
- Eliminar un tópico por ID
- Validaciones de integridad y reglas de negocio
- Estados de un tópico definidos en un enum (NO_RESPONDIDO, NO_SOLUCIONADO, SOLUCIONADO, CERRADO)
- Seguridad configurada con @SecurityRequirement(name = "bearer-key") lista para integración con JWT

---

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- MySQL Driver
- Spring Security (estructura lista para integrar JWT)
- Bean Validation (`jakarta.validation`)
- Lombok
- Maven
- SpringDoc / OpenAPI (documentación y seguridad con Bearer Token lista para JWT)

---

## ⚙️ Configuración inicial

### 1. Clonar repositorio:

```
git clone https://github.com/tu-usuario/Forohub.git
cd Forohub
```

### 2. Crear la base de datos MySQL

```sql
CREATE DATABASE foro_hub;
```

### 3. Configurar las credenciales de conexión

Crear `src/main/resources/application.properties`:

```properties
# Configuracion de MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuracion de JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Configuracion de JWT
jwt.secret=token_secreto_para_jwt
jwt.expiration=86400000

# Configuracion de Swagger
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.enabled=true
springdoc.show-actuator=true

# Puerto
server.port=8080
```

### 4. Ejecutar aplicación

```
mvn clean install
mvn spring-boot:run
```

## 🎯 Endpoints disponibles

| Método | Ruta             | Descripción                               |
| ------ | ---------------- | ----------------------------------------- |
| POST   | `/topicos`       | Crear un nuevo tópico                     |
| GET    | `/topicos`       | Listar todos los tópicos (con paginación) |
| GET    | `/topicos/{id}`  | Obtener un tópico por ID                  |
| GET    | `/topicos/curso` | Listar tópicos filtrados por curso y año  |
| PUT    | `/topicos`       | Actualizar un tópico existente            |
| DELETE | `/topicos/{id}`  | Eliminar un tópico por ID                 |

### 📝 Ejemplo de JSON para crear un tópico

```json
{
  "titulo": "¿Qué es Spring Boot?",
  "mensaje": "No entiendo cómo funciona la inyección de dependencias",
  "autorId": 1,
  "cursoId": 2
}
```

### 📝 Ejemplo JSON para actualizar un tópico

```json
{
  "id": 1,
  "titulo": "¿Qué es Spring Boot?",
  "mensaje": "Entiendo la inyección, pero ¿cómo funciona con @Autowired?",
  "estado": "SOLUCIONADO"
}
```

---

### 📁 Estructura del proyecto

```
src/
└── main/
    ├── java/
    │   └── com.forohub.forohub/
    │       ├── controller/       # Controladores REST
    │       ├── domain/           # Entidades JPA
    │       ├── dto/              # DTOs para entrada/salida
    |       |── exceptions/       # Excepciones
    │       ├── repository/       # Repositorios JPA
    |       ├── security/         # Seguridad
    │       ├── service/          # Lógica de negocio
    │       └── ForoApplication.java
    └── resources/
        ├── application.properties

```

## 📄 Licencia

MIT License – Ver LICENSE para detalles completos.

Nota: Proyecto desarrollado con fines educativos y demostrativos.

## ⭐ Insignia de logro

Insignia de logro por completar el challenge **Practicando Spring Framework: Challenge Foro Hub**

<p align="center">
  <img src="image/Badge-Spring.png" alt="Vista de insignia" width="100"/>
</p>
