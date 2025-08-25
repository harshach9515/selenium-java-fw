# Selenium Java Automation Framework

This repository contains  Selenium Java framework designed for scalable and maintainable test automation.

## Features

* Page Object Model (POM) design for reusable page classes
* Local and Remote WebDriver execution (Selenium Grid / Sauce Labs)
* Browser support: Chrome, Firefox, Internet Explorer (IE), Safari
* Browser options abstraction via `BrowserOptions` class
* TestNG integration for structured test execution
* Logging via Log4j for detailed test logs
* Config-driven execution using `ConfigReader`
* Thread-safe WebDriver management with `DriverManager`
* Modular and extensible structure for maintainable automation

## Setup & Usage

### Clone the repository:

```bash
git clone https://github.com/harshach9515/selenium-java-fw.git
cd selenium-java-fw

## Run Tests
mvn clean test
run testng suite - src/test/resources/testsuites/testng.xml


