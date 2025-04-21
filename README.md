# repo-scorer

This project helps score GitHub repositories based on criteria like stars, forks, and recency of updates.

You can run this application with or without Java 21 installed. Below are the instructions for both scenarios.

---

## Running the Application

### 1. **Without Java 21 (Recommended for Users Without Java)**

If you don’t have Java 21 installed, you can easily run the application using **Docker**.

#### Prerequisites:
- Install [Docker](https://www.docker.com/get-started).

#### Steps to Run:

1. Clone the repository:

   ```bash
   git clone https://github.com/aeminkaplan/repo-scorer.git
   cd repo-scorer

2. Run the application using Docker Compose:
   ```bash
   docker-compose up -d
The app will be available at http://localhost:8080.


### 2. **With Java 21 Installed (Run Without Docker)**

If you already have Java 21 installed, you can run the application directly on your local machine.

#### Prerequisites:
- Java 21 or higher installed (check with java -version).
#### Steps to Run:

1. Clone the repository:

   ```bash
   git clone https://github.com/aeminkaplan/repo-scorer.git
   cd repo-scorer


2. Run the JAR directly using Java:
   ```bash
   java -jar release/repo-scorer-0.0.1-SNAPSHOT.jar

The app will be available at http://localhost:8080.


## Running the Tests
#### Prerequisites:
- Make sure you have Maven installed. Check with the following command:
   ```bash
   mvn -v
   
#### Steps to Run:
1. Run tests with Maven:
   ```bash
   cd repo-scorer
   mvn test
#### You can modify application settings via the application.properties file.
cache.duration.in.minutes=10

cache.size=100

   




