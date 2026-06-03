# Habit Tracker 🧠📊

A full-stack Habit Tracker web application built with **Java Spring Boot** and **PostgreSQL**, allowing users to create habits, track daily progress, and view personal analytics.

---

## 🚀 Features

* 👤 User registration and login system (session-based authentication)
* ➕ Create, update, and delete habits
* ✅ Mark daily habit completion
* 📊 Personal analytics dashboard (completion rate, streaks, stats)
* 📅 Weekly progress tracking
* 🌐 REST API backend
* ☁️ Deployed on Render with cloud PostgreSQL database

---

## 🛠️ Tech Stack

**Backend:**

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA (Hibernate)
* PostgreSQL

**Frontend:**

* HTML
* CSS
* JavaScript

**Deployment:**

* Render (Backend + Database)

---

## 📂 Project Structure

```
habittracker/
 ├── src/main/java
 │    ├── controller
 │    ├── service
 │    ├── repository
 │    ├── model
 │    └── dto
 │
 ├── src/main/resources
 │    ├── static (frontend files)
 │    └── application.properties
```

---

## ⚙️ Setup & Installation

### 1. Clone the repository

```bash
git clone https://github.com/your-username/habittracker.git
cd habittracker
```

---

### 2. Configure Database (PostgreSQL)

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://<HOST>/<DB_NAME>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3. Run the application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

### 4. Open in browser

```
http://localhost:8080
```

---

## 📡 API Endpoints

### Habits

* `POST /habits` → Create habit
* `GET /habits` → Get user habits
* `DELETE /habits/{id}` → Delete habit
* `PUT /habits/{id}/toggle` → Toggle completion
* `PUT /habits/{id}/today` → Mark today completion

### Analytics

* `GET /habits/analytics` → Get user analytics
* `GET /habits/weekly` → Weekly stats

---

## 🔐 Authentication

* Session-based login system
* Each user can only access their own habits
* User stored in server session (`HttpSession`)

---

## 🌍 Deployment

The project is deployed on Render:

```
https://your-app-name.onrender.com
```

---

## 📌 Future Improvements

* JWT authentication
* React frontend upgrade
* Habit streak reminders
* Mobile-responsive UI
* Email notifications

---

## 👨‍💻 Author

Built by Yeldos Rakhymzhanov

---
