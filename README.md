# Bajaj Finserv Health Challenge - Spring Boot Application

This Spring Boot application implements the Bajaj Finserv Health Challenge requirements:

## 🧩 **What it does**

1. **On startup**, sends a `POST` request to generate a webhook with user credentials
2. **Solves an SQL problem** based on the registration number (odd/even logic)
3. **Submits the SQL query** to the webhook URL using JWT authentication

## 🚀 **Quick Start**

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Running the application

1. **Clone the repository**

   ```bash
   git clone <your-repo-url>
   cd health-challenge
   ```

2. **Build the project**

   ```bash
   .\mvnw.cmd clean compile
   ```

3. **Run the application**

   ```bash
   .\mvnw.cmd spring-boot:run
   ```

4. **Build JAR file**
   ```bash
   .\mvnw.cmd clean package
   java -jar target/health-challenge-0.0.1-SNAPSHOT.jar
   ```

## ⚙️ **Configuration**

Edit `src/main/resources/application.yml` to customize:

```yaml
bajaj:
  user:
    name: "Your Name" # Replace with your name
    reg-no: "REG12347" # Replace with your registration number
    email: "your@example.com" # Replace with your email
```

## 🏗️ **Project Structure**

```
src/
├── main/java/com/bajaj/finserv/healthchallenge/
│   ├── BajajHealthChallengeApplication.java  # Main application class
│   ├── model/                                # Data models
│   │   ├── WebhookGenerateRequest.java
│   │   ├── WebhookResponse.java
│   │   └── SqlSubmissionRequest.java
│   ├── service/
│   │   └── WebhookService.java              # Core business logic
│   ├── util/
│   │   └── SqlSolver.java                   # SQL problem solver
│   └── runner/
│       └── StartupRunner.java               # Startup execution logic
└── resources/
    └── application.yml                      # Configuration
```

## 🔧 **How it works**

1. **Startup Execution**: `StartupRunner` automatically executes the workflow when the application starts
2. **Webhook Generation**: `WebhookService.generateWebhook()` sends user details to get webhook URL and JWT token
3. **SQL Problem Solving**: `SqlSolver.getSqlQuery()` determines which SQL question to solve based on registration number:
   - **Odd last two digits** → Question 1
   - **Even last two digits** → Question 2
4. **SQL Submission**: `WebhookService.submitSqlQuery()` sends the solution using JWT authentication

## 📝 **Important Notes**

- **SQL Queries**: The current SQL queries in `SqlSolver.java` are example queries. You need to:

  1. Access the Google Drive links provided in the challenge
  2. Replace the example queries with the actual SQL problems
  3. Ensure your queries solve the specific problems mentioned

- **User Configuration**: Before running, update your personal details in `application.yml`

- **Logging**: The application provides detailed logging to track the execution flow

## 🔍 **Customization Required**

1. **Update SQL Queries**: Replace the example SQL queries in `SqlSolver.java` with the actual problems from:

   - Question 1 (Odd): [Google Drive Link 1]
   - Question 2 (Even): [Google Drive Link 2]

2. **Update User Details**: Modify `application.yml` with your actual:
   - Name
   - Registration Number
   - Email

## 🚢 **Submission**

1. **Build the JAR file**:

   ```bash
   .\mvnw.cmd clean package
   ```

2. **Upload to your public GitHub repository**

3. **Submit via the form**: https://forms.office.com/r/ZbcqfgSeSw

## 📋 **API Endpoints Used**

- **Generate Webhook**: `POST https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
- **Submit SQL**: `POST https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`

## 🐛 **Troubleshooting**

- Check logs for detailed error messages
- Ensure Java 17+ is installed
- Verify internet connection for API calls
- Confirm your registration number format is correct
