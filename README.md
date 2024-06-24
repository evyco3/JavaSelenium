# Automated Test Suite for E-commerce Application

## Overview
This repository contains an automated test suite designed for testing an e-commerce web application. The tests cover various functionalities from user authentication and registration to product selection, checkout, and order processing. The project utilizes Java, Selenium WebDriver, TestNG, and Allure for test execution, automation, and reporting.

## Key Features
- **Modular Test Structure**: Tests are organized into logical modules such as user authentication, registration, product selection, and checkout.
- **Page Object Model (POM)**: Utilizes the POM design pattern for maintaining test scripts and page interactions separately, enhancing maintainability and readability.
- **Allure Reporting**: Integrated with Allure for detailed test execution reports, including step-by-step test case execution, screenshots, and attachments.
- **Data-Driven Testing**: Utilizes TestNG's data providers to support data-driven testing for scenarios like user login, registration, and product selection.
- **Logging**: Includes a custom logging utility (`LoggerUtils`) for capturing and reporting detailed execution logs, aiding in troubleshooting and debugging.

## Technologies Used
- **Programming Language**: Java
- **Automation Framework**: Selenium WebDriver
- **Testing Framework**: TestNG
- **Reporting**: Allure
- **Build Automation**: Maven

## Project Structure
- **src/main**: Contains core framework components like WebDriver factory, page objects, utilities, and configuration files.
- **src/test**: Houses test classes organized by functional areas such as authentication, registration, product selection, and checkout.
- **Configuration Files**: Includes configurations for environment setup, browser preferences, and test data.

## How to Use
1. Clone the repository to your local machine.
2. Ensure Java and Maven are installed.
3. Update configuration files (`config.properties`) with appropriate settings.
4. Run tests using Maven commands (`mvn clean test`) or directly from the IDE.



