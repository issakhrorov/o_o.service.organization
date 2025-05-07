# organization-service

A Kotlin Spring Boot microservice for managing organizational data, built with a modern stack including Jimmer ORM and PostgreSQL.

## 📦 Features

- 🔧 Built with **Spring Boot (Kotlin)**
- 🧬 Uses **Jimmer** as the ORM (Immutable entities & powerful fetchers)
- 🗃️ **PostgreSQL** as the relational database
- 🧪 Designed with extensibility and maintainability in mind
- 🧩 Ideal as part of a larger microservice architecture

## 🛠️ Tech Stack

- **Language:** Kotlin
- **Framework:** Spring Boot
- **ORM:** [Jimmer](https://github.com/babyfish-ct/jimmer) (alternative to Spring Data JPA)
- **Database:** PostgreSQL
- **Build Tool:** Gradle (Kotlin DSL)

## 🧱 Architecture

- Domain-driven structure with clean separation of concerns
- Immutable entities with associations and fetchers using Jimmer
- Repository and service layers for business logic encapsulation
- Supports both eager and lazy fetching strategies

## 🚀 Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/your-username/organization-service.git
cd organization-service
