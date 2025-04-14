Here is the **README.md** file for your project with detailed instructions on how to run it using Maven:
# Test Automation framework for petsore
## Project Description
This is test project to check 4 requests
## Requirements
Before running this project, ensure that the following requirements are met on your system:
1. **JDK 19** or later.
   You can check your installed version with this command:
``` bash
   java -version
```
1. **Apache Maven** (version 3.6.x or later).
   You can check your installed version with this command:
``` bash
   mvn -version
```
1. (Optional) **IDE:** Use IntelliJ IDEA or any preferred text editor for editing the code.

## How to Build and Run the Project
### Step 1: Clone the Repository
Clone the project repository to your local machine:
``` bash
git clone https://github.com/your-username/your-project.git
cd your-project
```
- **clean:** Clears previous build files (if any).
- **install:** Resolves dependencies, compiles the code, and creates a packaged JAR file.

### Step 2: Run test
1. To run all tests just execute mvn test inside project folder
``` bash
   mvn test
```

### Step 3: HTML Report
1. You can find default surefire html report in path target\surefire-reports\emailable-report.html 
or target\surefire-reports\index.html after test run finished

## Tips about tests architecture:
Test framework designed to add and maintain API tests. All assertions done in tests, all requests
(RestAssured requests) are in controllers package.
Actions like converting response to DTO are in actions package. All DTO files located in dto package with proper names.
Top level configuration like service URL located in data/APIData file.
Response and Request specifications with JSON schema validation located in utils folder

If you wish to add new tests for new endpoint do next:
Create DTO for your response in dto/<yourEndpointName>/<ExampleName>DTO
Create request itself in controllers folder controllers/<yourEndpointName>/<ExampleName>Controller
Create wrapper for Request that do schema validation and returns DTO object in actions/controls/<yourEndpointName>Actions

