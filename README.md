# Event Manager Backend

REST API for an event management application built with Spring Boot and PostgreSQL.

Users can discover and create events, manage their participations, connect with other users, and review past events.

The Angular frontend is available in the [event-manager-frontend](https://github.com/aliceboursin/event-manager-frontend) repository.

## Project Background

Event Manager was originally developed by **Alice Boursin and Paul Malet** as a two-person university project at **Université Paris Dauphine - PSL**.

The original application and its Git history have been preserved. Alice is currently revisiting the project, focusing on documentation, reproducible local setup, and maintainability.

## Features

- User registration and authentication with BCrypt password hashing
- User account management
- Event creation and deletion
- Event discovery, search and filtering
- Upcoming and past events
- Event categories
- Event participation
- Friendship management
- Event discovery based on friends' participations
- Reviews and ratings for past events
- OpenAPI / Swagger documentation

## Tech Stack

- **Java 21**
- **Spring Boot 3.3**
- **Spring Web**
- **Spring Data JPA / Hibernate**
- **Spring Security / BCrypt**
- **PostgreSQL**
- **Maven**
- **Springdoc OpenAPI / Swagger UI**

## Prerequisites

Make sure the following tools are installed:

- Java 21
- Maven
- PostgreSQL

You can verify your installation with:

```bash
java -version
mvn -version
psql --version
```

## Database Setup

The project uses a local PostgreSQL database named `event_manager`.

Database setup scripts are available in:

```text
database/
├── create-database.sql
├── create-tables.sql
├── populate-data.sql
└── init-database.ps1
```

On Windows, initialize the database from the backend project directory with:

```powershell
.\database\init-database.ps1
```

The script creates the database and tables, then inserts the required categories and sample data.

> The script is intended for initial setup. It will fail if the `event_manager` database already exists rather than overwrite existing data.

## Configuration

Database credentials are provided through environment variables and are not stored in the repository.

| Variable | Example |
| --- | --- |
| `DB_HOST` | `localhost` |
| `DB_PORT` | `5432` |
| `DB_NAME` | `event_manager` |
| `DB_USERNAME` | `postgres` |
| `DB_PASSWORD` | your local PostgreSQL password |

In PowerShell:

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="5432"
$env:DB_NAME="event_manager"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="your-postgresql-password"
```

## Running the Application

Start the backend with:

```bash
mvn spring-boot:run
```

The API runs at:

```text
http://localhost:8080
```

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

## Demo Data

The initialization script provides sample users, events, participations, and reviews so the application can be explored immediately after setup.

The preloaded users are fixtures and are **not intended for authentication**. Create your own account through the application to test authentication and user features.

To test friendships, you can add one of the preloaded users using its ID:

| Username | User ID |
| --- | --- |
| `alex_demo` | `10000000-0000-0000-0000-000000000001` |
| `sam_demo` | `10000000-0000-0000-0000-000000000002` |
| `charlie_demo` | `10000000-0000-0000-0000-000000000003` |

These users own or participate in sample events, allowing friendship-related event discovery to be tested.

## Frontend

The Angular frontend is maintained in a separate repository:

[aliceboursin/event-manager-frontend](https://github.com/aliceboursin/event-manager-frontend)

It is configured to communicate with the backend at `http://localhost:8080/v1/`.

## Authors

**Original university project**
- Alice Boursin
- Paul Malet (https://github.com/LeptaLuma)

**Current portfolio maintenance**
- Alice Boursin