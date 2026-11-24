# FreelanceHub – Freelancing Platform Management System

FreelanceHub is a web-based freelancing platform designed to connect clients with skilled freelancers. It allows clients to post projects and freelancers to submit proposals. The platform also manages contracts, payments and reviews.

The project is developed using **Java, Spring Boot, Thymeleaf and MySQL**, with a focus on database management and backend development.

## 🚀 Features

### 1. Client Management
- View registered clients.
- Register new clients.
- Store client details in the database.

### 2. Freelancer Management
- View registered freelancers.
- Register new freelancers.
- Maintain freelancer profiles and experience details.

### 3. Project Management
- Post new projects.
- View available projects.
- View individual project details.
- Manage project descriptions, budgets and deadlines.

### 4. Proposal Management
- Submit proposals for projects.
- View submitted proposals.
- Accept or reject proposals.

### 5. Contract Management
- Automatically create contracts when proposals are accepted.
- Maintain contract details and status.

### 6. Payment Management
- Maintain payment records.
- Track payment amounts and transaction details.
- Manage payment statuses.

### 7. Review Management
- Display client feedback.
- Maintain freelancer ratings and reviews.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java 21 | Backend programming |
| Spring Boot 4.2.0 | Application framework |
| Spring MVC | Web request handling |
| Thymeleaf | Frontend template engine |
| JDBC Template | Database connectivity and SQL operations |
| MySQL | Relational database |
| HTML5 | Page structure |
| CSS3 | Styling and responsive design |
| Maven | Project and dependency management |
| IntelliJ IDEA | Development environment |

## 🗄️ Database Design

The application uses a MySQL relational database named `freelancing_platform`.

It contains the following nine tables:

1. `clients` – Stores client information.
2. `freelancers` – Stores freelancer profiles.
3. `skills` – Maintains available skills.
4. `freelancer_skills` – Maps freelancers to their skills.
5. `projects` – Stores project details.
6. `proposals` – Stores freelancer proposals.
7. `contracts` – Maintains accepted project agreements.
8. `payments` – Stores payment transactions.
9. `reviews` – Stores client feedback and ratings.

The database uses primary keys, foreign keys and relational constraints to maintain data integrity.

## ⚙️ Installation and Setup

### Prerequisites

Make sure you have installed:

- Java JDK 21
- IntelliJ IDEA
- MySQL Server
- MySQL Workbench
- Maven

### Step 1: Clone the Repository

```bash
git clone https://github.com/Dev1joshi/freelancing-platform.git
```

### Step 2: Open the Project

Open the cloned project in IntelliJ IDEA and allow Maven to download the required dependencies.

### Step 3: Create the Database

Open MySQL Workbench and execute:

```sql
CREATE DATABASE freelancing_platform;
```

Create the nine tables using the project's database schema SQL script.

### Step 4: Configure Database Connection

Open:

`src/main/resources/application.properties`

Configure your MySQL credentials:

```properties
spring.application.name=freelancing-platform

spring.datasource.url=jdbc:mysql://localhost:3306/freelancing_platform
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.sql.init.mode=never
spring.thymeleaf.cache=false

server.port=8080
```

Replace `YOUR_MYSQL_PASSWORD` with your local MySQL password.

### Step 5: Run the Application

Run the main Spring Boot application class:

`FreelancingPlatformApplication.java`

### Step 6: Open in Browser

Visit:

```text
http://localhost:8080
```

## 🔄 Application Workflow

1. A client registers on the platform.
2. A client posts a project with its description, budget and deadline.
3. Freelancers explore available projects.
4. Freelancers submit proposals.
5. Clients review and accept or reject proposals.
6. Accepting a proposal automatically creates a contract.
7. Payment records are maintained.
8. Clients can provide reviews and ratings.

## 📂 Project Structure

```text
freelancing-platform/
│
├── src/
│   └── main/
│       ├── java/com/freelancing/
│       │   ├── controller/
│       │   ├── service/
│       │   ├── repository/
│       │   ├── model/
│       │   └── FreelancingPlatformApplication.java
│       │
│       └── resources/
│           ├── templates/
│           │   ├── index.html
│           │   ├── clients.html
│           │   ├── freelancers.html
│           │   ├── projects.html
│           │   ├── proposals.html
│           │   ├── contracts.html
│           │   ├── payments.html
│           │   ├── reviews.html
│           │   ├── register.html
│           │   ├── register-client.html
│           │   └── register-freelancer.html
│           │
│           └── application.properties
│
├── pom.xml
└── README.md
```

## 🎯 Project Objectives

- Understand relational database management systems.
- Implement CRUD operations using Java and MySQL.
- Apply primary keys and foreign key relationships.
- Implement database operations using JDBC Template.
- Develop a web application using Spring Boot MVC.
- Understand the complete project-to-contract workflow.

## 🔮 Future Enhancements

- User authentication and role-based authorization.
- Freelancer skill-based project recommendations.
- Real payment gateway integration.
- Advanced search and filtering.
- Notifications and messaging between clients and freelancers.
- Project progress tracking.

## 👨‍💻 Author

**Dev Joshi**

MCA Student | Java Backend Development

GitHub: [Dev1joshi](https://github.com/Dev1joshi)

---

*FreelanceHub – Find Work. Build Your Future.*