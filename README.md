# Journal-API
Inlämningsuppgift - Min Journal



Detta är backend-delen av Journal-applikationen. Backend är byggd med **Spring Boot**, **Spring Security (JWT)** och **JPA/Hibernate**. API:et tillhandahåller endpoints för autentisering, CRUD-operationer för inlägg samt statistik över användarens status.

## Funktioner

* **Autentisering & registrering** (JWT-baserad säkerhet)
* **Skapa, hämta, lista och uppdatera inlägg** (kopplade till inloggad användare)
* **Statistik** för en vald tidsperiod (andelar av olika MoodStatus)
* **Persistens** via JPA/Hibernate till SQL-databas

## Teknologier

* Java 21
* Spring Boot
* Spring Security (JWT)
* JPA/Hibernate
* Lombok
* Maven


## Kom igång

1. Klona repot

   ```bash
   git clone <backend-repo-url>
   cd Journal-API
   ```

2. Konfigurera databasen i `application.properties` ( MySQL).

   **application.properties**

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/journal
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Kör projektet

   ```bash
   mvn spring-boot:run
   ```

4. API:et finns nu på:

   ```
   http://localhost:8080
   ```

## Viktiga endpoints

| Metod | Endpoint             | Beskrivning                   | Kräver Auth |
| ----- | -------------------- | ----------------------------- | ----------- |
| POST  | `/api/auth/register` | Skapa konto                   | Nej         |
| POST  | `/api/auth/login`    | Logga in, returnerar JWT      | Nej         |
| POST  | `/api/posts/newPost` | Skapa nytt inlägg             | Ja          |
| GET   | `/api/posts/all`     | Lista alla användarens inlägg | Ja          |
| GET   | `/api/posts/{id}`    | Hämta specifikt inlägg        | Ja          |
| GET   | `/api/statistics`    | Hämta statistik för period    | Ja          |

**Exempel (statistik-anrop i Postman):**

```
GET http://localhost:8080/api/statistics?startDate=2025-10-01&endDate=2025-10-02
Authorization: Bearer <jwt-token>
```

## Databasmodell

### Post

* `id: Long`
* `content: String`
* `status: MoodStatus`
* `date: LocalDateTime`
* `userId: Long`

### User

* `id: Long`
* `username: String`
* `email: String`
* `password: String (krypterat)`

---

