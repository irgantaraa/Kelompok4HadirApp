
# Kelompok4HadirApp

## Overview

Kelompok4HadirApp is a project designed to automate testing for the HadirApp system. It ensures the quality of features such as employee check-ins, login, registration, and data retrieval by using automation testing frameworks like Selenium, TestNG, and Cucumber.

---

## Prerequisites

Make sure the following tools are installed before setting up the project:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) 17  
  Required for running and building the Java-based project.

- [Apache Maven](https://maven.apache.org/download.cgi)  
  Used for managing dependencies and building the project.

- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)  
  A recommended IDE for Java development and integration with Maven.

---

## Project Setup

Follow these steps to set up the project:

1. **Clone the repository:**

   Clone the project repository to your local machine using the following command:
   ```bash
   git clone https://github.com/irgantaraa/Kelompok4HadirApp.git
   ```

2. **Navigate to the project directory:**

   Use the terminal or command prompt to move into the project directory:
   ```bash
   cd Kelompok4HadirApp
   ```

3. **Open the project in IntelliJ IDEA:**

    - Launch IntelliJ IDEA.  
    - Go to `File -> Open`.  
    - Navigate to the directory where the project was cloned and click `OK`.  

4. **Build the project using Maven:**

    - Open the Terminal window in IntelliJ IDEA.  
    - Run the following command to clean and build the project:
      ```bash
      mvn clean install
      ```
    - Ensure there are no errors during the build process.

---

## Running Tests

To execute the tests:

1. Open the terminal or use the Run/Debug configuration in IntelliJ IDEA.
2. Use the following Maven command to run tests:
   ```bash
   mvn test
   ```
3. View the test results in the terminal or check the generated test reports.

---

## Test Report

Cucumber generates detailed test reports after execution. Reports can be found in the `target/cucumber-reports` directory. Open the `index.html` file in your browser to view the report.

---

## Contributing

Contributions are welcome! For major changes, please open an issue first to discuss your ideas.

---

## License

This project is licensed under the MIT License. See the [LICENSE](https://opensource.org/licenses/MIT) file for more details.
