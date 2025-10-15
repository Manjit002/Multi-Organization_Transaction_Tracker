Perfect ✅ — here’s a **professional, ready-to-use `README.md`** for your project
👉 *“Multi-Organization Transaction Tracker (Spring Boot + PostgreSQL + Google OAuth2 + JWT)”*

You can copy-paste this into your project root (`README.md`) before pushing to GitHub.

---

## 🧾 README.md

```markdown
# 🏢 Multi-Organization Transaction Tracker

A secure, scalable **Spring Boot backend API** for managing multiple organizations and their sales/purchase transactions — featuring **Google OAuth2 Login**, **JWT Authentication**, and **PostgreSQL** persistence.

---

## 🚀 Features

✅ **Authentication**
- Google OAuth2 login using Spring Security.
- Email/Password signup and login with JWT-based session management.

✅ **Organization Management**
- Users can create and manage multiple organizations.
- Each organization has a name, description, and timestamps.

✅ **Transactions**
- Track sales and purchase transactions per organization.
- Fields: type (SALE/PURCHASE), amount, item, date, and optional party.

✅ **Security**
- All endpoints are protected using JWT.
- Users can only access data belonging to their organizations.

✅ **Tech Stack**
- Java 21+
- Spring Boot (latest)
- Spring Security + JWT
- Google OAuth2
- PostgreSQL + JPA/Hibernate
- Lombok
- Maven

---

## 🧩 Project Structure

```

src/
├── main/
│   ├── java/com/mjt/Multi_Organization_Transaction_Tracker
│   │   ├── config/          # Security + JWT Config
│   │   ├── controller/      # REST Controllers
│   │   ├── dto/             # Data Transfer Objects
│   │   ├── entity/          # JPA Entities
│   │   ├── repo/            # JPA Repositories
│   │   └── service/         # Business Logic
│   └── resources/
│       ├── application-example.properties  # Safe sample config
│       └── application.properties (ignored)
└── test/

````

---

## ⚙️ Setup Instructions

### 1️⃣ Prerequisites
- Java 21 or higher  
- PostgreSQL installed  
- Maven installed  

### 2️⃣ Clone the repository
```bash
git clone https://github.com/Manjit002/Multi-Organization_Transaction_Tracker.git
cd Multi-Organization_Transaction_Tracker
````

### 3️⃣ Configure your environment

Create a `.env` or `application.properties` file (untracked by Git):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mott
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update

app.jwt.secret=YOUR_32B_BASE64_SECRET
app.jwt.issuer=mott.api
app.jwt.expirationMinutes=120

app.oauth2.google.clientId=YOUR_GOOGLE_CLIENT_ID
app.oauth2.google.issuer=https://accounts.google.com
app.oauth2.google.jwkSetUri=https://www.googleapis.com/oauth2/v3/certs
```

---

### 4️⃣ Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

The backend will start at 👉 `http://localhost:8080`.

---

## 🧠 API Overview

### 🔐 Authentication

| Method | Endpoint           | Description                              |
| ------ | ------------------ | ---------------------------------------- |
| `POST` | `/api/auth/google` | Login via Google OAuth2 (send `idToken`) |
| `POST` | `/api/auth/signup` | Register via email/password              |
| `POST` | `/api/auth/login`  | Login via email/password                 |

### 🏢 Organizations

| Method   | Endpoint            | Description               |
| -------- | ------------------- | ------------------------- |
| `POST`   | `/api/orgs`         | Create a new organization |
| `GET`    | `/api/orgs`         | List all organizations    |
| `DELETE` | `/api/orgs/{orgId}` | Delete an organization    |

### 💰 Transactions

| Method   | Endpoint                         | Description              |
| -------- | -------------------------------- | ------------------------ |
| `POST`   | `/api/orgs/{orgId}/txns`         | Create a new transaction |
| `GET`    | `/api/orgs/{orgId}/txns`         | List all transactions    |
| `DELETE` | `/api/orgs/{orgId}/txns/{txnId}` | Delete a transaction     |

> All endpoints require `Authorization: Bearer <JWT>` header.

---

## 🧰 Tools Used

* **Spring Boot** (REST API Framework)
* **Spring Security + JWT**
* **Google OAuth2**
* **PostgreSQL** (Database)
* **Maven** (Build Tool)
* **Swagger UI** for API testing

Access Swagger UI at:
👉 `http://localhost:8080/swagger-ui/index.html`

---

## 🧑‍💻 Author

**👤 Manjit Patel**

---

