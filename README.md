# 🏦 Smart Banking Management System

A production-quality, feature-rich Desktop Banking Application built using **Java Swing** and **MySQL**. This system follows clean architecture principles (MVC + Layered Architecture) to ensure scalability and maintainability.

## 🌟 Key Features

### 🔹 Core Banking
- **Secure Authentication**: User registration and login with SHA-256 password hashing.
- **Account Management**: Automatic generation of unique account numbers.
- **Financial Transactions**: Secure Deposit, Withdrawal, and Peer-to-Peer Transfers using SQL Transactions (ACID properties).
- **Transaction History**: Real-time logging of all activities in a searchable table.

### 🔹 Smart Intelligence
- **Spending Analytics**: Automatic categorization of expenses (Food, Travel, Bills, etc.).
- **Top Category Insights**: Visual summary of your highest spending area.
- **Fraud Detection**: Automatic flagging of suspicious transactions (over ₹50,000).
- **Low Balance Alerts**: Proactive notifications when the balance drops below ₹1,000.

## 🛠️ Technology Stack
- **Language**: Java 8+
- **UI Framework**: Java Swing (Desktop GUI)
- **Database**: MySQL 8.0
- **Connectivity**: JDBC (Java Database Connectivity)
- **Security**: SHA-256 Hashing for sensitive data

## 📂 Project Structure
```text
smart-banking-system/
├── src/
│   ├── models/       # Data entities (User, Account, Transaction)
│   ├── dao/          # Database Access Objects (SQL Logic)
│   ├── services/     # Business logic & Smart features
│   ├── ui/           # Swing GUI Screens
│   ├── utils/        # Security, Validation, and Helpers
│   └── Main.java     # Entry point
├── resources/        # Configuration & Icons
├── sql/              # Database schema scripts
└── lib/              # JDBC Driver (MySQL Connector)
```

## 🚀 Getting Started

### 1. Prerequisites
- **JDK 8 or higher** installed.
- **MySQL Server** running locally.
- **Eclipse IDE** (recommended) or any Java-supporting IDE.

### 2. Database Setup
Execute the following script in your MySQL terminal to set up the database:
```sql
CREATE DATABASE smart_banking;
USE smart_banking;
-- (Run the full sql/schema.sql script provided in the repository)
```

### 3. Configuration
Update the database credentials in `resources/config.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/smart_banking
db.user=your_username
db.password=your_password
```

### 4. Running the Application
1. **Import into Eclipse**: `File -> Import -> Existing Projects into Workspace`.
2. **Add Library**: Ensure `lib/mysql-connector-j.jar` is in your Build Path.
3. **Run**: Execute `src/Main.java`.

## 📜 License
This project is open-source and available under the [MIT License](LICENSE).

---
Developed by [SivaPanyam](https://github.com/SivaPanyam)
