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
### Step 2: Build the Project using Maven
In the project directory, use the following command to build the project:
``` bash
mvn clean install
```
- **clean:** Clears previous build files (if any).
- **install:** Resolves dependencies, compiles the code, and creates a packaged JAR file.

### Step 3: Run the Project
1. Run the main class: If you have a defined `main` class for execution (e.g., `Main`), run the command:
``` bash
   mvn exec:java -Dexec.mainClass="com.example.Main"
```
Replace `com.example.Main` with the actual path to your main class.
1. Run the compiled JAR file: After the build, a `.jar` file will be created in the `target` directory, for example:
``` 
   target/your-project-name-1.0-SNAPSHOT.jar
```
To execute the JAR file, run the following command:
``` bash
   java -jar target/your-project-name-1.0-SNAPSHOT.jar
```
## Project Structure
``` 
your-project
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── models
│   │   │       └── store
│   │   │           ├── OrderDTO.java
│   │   │           └── ApiResponseDTO.java
│   │   └── resources
│   └── test
│       └── java
│
├── pom.xml
└── README.md
```
### Folder Descriptions:
- `src/main/java`: Contains the main application Java classes.
- `src/main/resources`: Stores configuration files or additional resources.
- `src/test/java`: Holds the test files for the project.
- `pom.xml`: Maven configuration file where dependencies are managed.

## Adding Dependencies
If you need additional dependencies, such as Jackson for working with JSON, add them to `pom.xml`. For example:
``` xml
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.2</version>
    </dependency>
</dependencies>
```
After adding dependencies, re-run the build using:
``` bash
mvn clean install
```
## Testing the Project
To run the tests in your project, use:
``` bash
mvn test
```
## Additional Notes
- **Maven Plugin for Running the Application:**
  If your project requires a Maven plugin to run the Java class, add this section to the `pom.xml`:
``` xml
  <build>
      <plugins>
          <plugin>
              <groupId>org.codehaus.mojo</groupId>
              <artifactId>exec-maven-plugin</artifactId>
              <version>3.1.0</version>
              <executions>
                  <execution>
                      <goals>
                          <goal>java</goal>
                      </goals>
                  </execution>
              </executions>
          </plugin>
      </plugins>
  </build>
```
This allows you to use `mvn exec:java -Dexec.mainClass="your.main.Class"` to run the `main` class.
