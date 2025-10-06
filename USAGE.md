# 🚀 Usage Instructions for Bajaj Finserv Health Challenge

## ✅ **Successfully Completed Tasks**

1. ✅ **Spring Boot Application Created** - Fully functional with all required components
2. ✅ **Webhook Generation** - Tested and working successfully
3. ✅ **SQL Problem Solving** - Automatically determines Question 1/2 based on registration number
4. ✅ **JWT Authentication** - Successfully submits SQL query using access token
5. ✅ **Startup Execution** - Runs automatically when application starts
6. ✅ **JAR File Built** - Ready for submission (`health-challenge-0.0.1-SNAPSHOT.jar`)

## 🔧 **Required Customizations Before Submission**

### 1. **Update Personal Information**

Edit `src/main/resources/application.yml`:

```yaml
bajaj:
  user:
    name: "Your Full Name" # Replace with your actual name
    reg-no: "YOUR_REG_NUMBER" # Replace with your registration number
    email: "your@email.com" # Replace with your email
```

### 2. **Update SQL Queries**

Edit `src/main/java/com/bajaj/finserv/healthchallenge/util/SqlSolver.java`:

- **Replace `getQuestion1Query()`** method with the actual SQL from Google Drive Question 1
- **Replace `getQuestion2Query()`** method with the actual SQL from Google Drive Question 2

Current queries are examples - you need to implement the actual challenge queries.

### 3. **Rebuild After Changes**

```bash
.\mvnw.cmd clean package
```

## 📋 **Submission Checklist**

- [ ] Updated personal information in `application.yml`
- [ ] Implemented actual SQL queries from Google Drive links
- [ ] Rebuilt JAR file with `.\mvnw.cmd clean package`
- [ ] Tested application with `.\mvnw.cmd spring-boot:run`
- [ ] Pushed code to **public GitHub repository**
- [ ] Submitted via form: https://forms.office.com/r/ZbcqfgSeSw

## 🧪 **Testing Your Changes**

1. **Run the application:**

   ```bash
   .\mvnw.cmd spring-boot:run
   ```

2. **Check the logs** for:
   - ✅ Webhook generation success
   - ✅ SQL query determination (odd/even logic)
   - ✅ Successful submission with response: `{"success":true,"message":"Webhook processed successfully"}`

## 📁 **Key Files Structure**

```
src/
├── main/java/com/bajaj/finserv/healthchallenge/
│   ├── BajajHealthChallengeApplication.java  # Main application
│   ├── runner/StartupRunner.java             # Executes workflow on startup
│   ├── service/WebhookService.java           # API calls logic
│   ├── util/SqlSolver.java                   # SQL problem solver ⚠️ UPDATE THIS
│   └── model/                                # Data models
└── resources/
    └── application.yml                       # Configuration ⚠️ UPDATE THIS
```

## 🎯 **How It Works**

1. **Application starts** → `StartupRunner` triggers the workflow
2. **Webhook generation** → POST to `/generateWebhook/JAVA` with your details
3. **SQL determination** → Last 2 digits of regNo determine question (odd=Q1, even=Q2)
4. **SQL submission** → POST to `/testWebhook/JAVA` with JWT token and SQL query

## 🚨 **Important Notes**

- The application runs on **port 8081** (changed from 8080 due to conflict)
- **Replace example SQL queries** with actual challenge queries before submission
- Ensure your **GitHub repository is public** for evaluation
- The JAR file is located at: `target/health-challenge-0.0.1-SNAPSHOT.jar`

## 🏆 **Success Confirmation**

When everything works correctly, you should see logs like:

```
Webhook generated successfully: https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA
Registration number ends with [odd/even] digits, using Question [1/2]
SQL query submitted successfully: {"success":true,"message":"Webhook processed successfully"}
Complete workflow executed successfully
```

Good luck with your submission! 🍀
