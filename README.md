# SET09803 DevOps Group Coursework - World Population Reporting System

* Master Build Status ![Master](https://img.shields.io/github/actions/workflow/status/nick0411/set09803-devops-group-18/main.yml?branch=master)
* Develop Build Status ![Develop](https://img.shields.io/github/actions/workflow/status/nick0411/set09803-devops-group-18/main.yml?branch=develop)
* License [![License](https://img.shields.io/badge/license-Apache--2.0-green.svg)](LICENSE)
* Release ![Release](https://img.shields.io/github/v/release/nick0411/set09803-devops-group-18?color=grey)


## Project Overview
This repository contains a containerized Java 17 reporting application that processes global population data from the MySQL `world` database. Developed for the SET09803 DevOps module, the project demonstrates continuous integration, container orchestration, and automated building via GitHub Actions.

---

## Tech Stack
* **Language:** Java 17 (Eclipse Temurin)
* **Build System:** Apache Maven (Assembly Plugin for Fat-JAR packaging)
* **Database:** MySQL (`world` database)
* **Containerization:** Docker and Docker Compose
* **CI/CD Pipeline:** GitHub Actions
* **Project Management:** Zube.io / GitHub Projects (Kanban & Sprint Boards)

---

## Quick Start & Deployment

### Prerequisites
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running.
* [Git](https://git-scm.com/) installed.

### Running with Docker Compose
1. Clone the repository:
   ```bash
   git clone https://github.com/nick0411/set09803-devops-group-18.git
   cd set09803-devops-group-18
   
2. Build and run the system:
    ```bash
   docker compose up --build

* The db service initializes the world database automatically.
* The app service waits for db health checks before executing queries.

# Project Governance & Methodology

* Code of Conduct: Refer to [Code of Conduct](CODE_OF_CONDUCT.md) for team collaboration rules and conflict resolution policies.
* Branching Strategy: We follow the GitFlow model (master, develop, release, and feature/*).
* Task Tracking: Integrated with Zube.io for User Stories, Backlog management, and Sprint Tracking.

# Coursework Requirements Status

    27 requirements out of 32 have been implemented (84.375%).

| ID | Requirement Name | Met | Screenshot |
| --- | --- | --- | --- |
| 1 | All the countries in the world organised by largest population to smallest. | Yes |![req1_1.png](img/req1_1.png)![req1_2.png](img/req1_2.png)|
| 2 | All the countries in a continent organised by largest population to smallest. | Yes |![req2_1.png](img/req2_1.png)![req2_2.png](img/req2_2.png)|
| 3 | All the countries in a region organised by largest population to smallest. | Yes |![req3_1.png](img/req3_1.png)![req3_2.png](img/req3_2.png)|
| 4 | The top N populated countries in the world where N is provided by the user. | Yes |![req4.png](img/req4.png)|
| 5 | The top N populated countries in a continent where N is provided by the user. | Yes |![req5.png](img/req5.png)|
| 6 | The top N populated countries in a region where N is provided by the user. | Yes |![req6.png](img/req6.png)|
| 7 | All the cities in the world organised by largest population to smallest. | Yes |![req7_1.png](img/req7_1.png)![req7_2.png](img/req7_2.png)|
| 8 | All the cities in a continent organised by largest population to smallest. | Yes |![req8_1.png](img/req8_1.png)![req8_2.png](img/req8_2.png)|
| 9 | All the cities in a region organised by largest population to smallest. | Yes |![req9_1.png](img/req9_1.png)![req9_2.png](img/req9_2.png)|
| 10 | All the cities in a country organised by largest population to smallest. | Yes |![req10_1.png](img/req10_1.png)![req10_2.png](img/req10_2.png)|
| 11 | All the cities in a district organised by largest population to smallest. | Yes |![req11_1.png](img/req11_1.png)![req11_2.png](img/req11_2.png)|
| 12 | The top N populated cities in the world where N is provided by the user. | No |  |
| 13 | The top N populated cities in a continent where N is provided by the user. | No |  |
| 14 | The top N populated cities in a region where N is provided by the user. | No |  |
| 15 | The top N populated cities in a country where N is provided by the user. | No |  |
| 16 | The top N populated cities in a district where N is provided by the user. | No |  |
| 17 | All the capital cities in the world organised by largest population to smallest. | Yes |![req17_1.png](img/req17_1.png)![req17_2.png](img/req17_2.png)|
| 18 | All the capital cities in a continent organised by largest population to smallest. | Yes |![req18_1.png](img/req18_1.png)![req18_2.png](img/req18_2.png)|
| 19 | All the capital cities in a region organised by largest to smallest. | Yes |![req19_1.png](img/req19_1.png)![req19_2.png](img/req19_2.png)|
| 20 | The top N populated capital cities in the world where N is provided by the user. | Yes |![req20_1.png](img/req20_1.png)![req20_2.png](img/req20_2.png)|
| 21 | The top N populated capital cities in a continent where N is provided by the user. | Yes |![req21.png](img/req21.png)|
| 22 | The top N populated capital cities in a region where N is provided by the user. | Yes |![req22.png](img/req22.png)|
| 23 | Population of people living in and outside cities in each continent. | Yes |![img23.png](img/req23.png)|
| 24 | Population of people living in and outside cities in each region. | Yes |![req24_1.png](img/req24_1.png)![req24_2.png](img/req24_2.png)|
| 25 | Population of people living in and outside cities in each country. | Yes |![req25_1.png](img/req25_1.png)![req25_2.png](img/req25_2.png)|
| 26 | The population of the world. | Yes |![req26.png](img/req26.png)|
| 27 | The population of a continent. | Yes |![req27.png](img/req27.png)|
| 28 | The population of a region. | Yes |![req28_1.png](img/req28_1.png)![req28_2.png](img/req28_2.png)|
| 29 | The population of a country. | Yes |![req29_1.png](img/req29_1.png)![req29_2.png](img/req29_2.png)|
| 30 | The population of a district. | Yes |![req30_1.png](img/req30_1.png)![req30_2.png](img/req30_2.png)|
| 31 | The population of a city. | Yes |![req31_1.png](img/req31_1.png)![req31_2.png](img/req31_2.png)|
| 32 | Language report (Chinese, English, Hindi, Spanish, Arabic with world percentages). | Yes |![img.png](img/req32.png)|

| Matriculation Number | Code Review 1 | Code Review 2 |
| :--- | :--- | :--- |
| 40841082 | 17 | - |
| 40841050 | 17 | - |
| 40841051 | 17 | - |
| 40840488 | 17 | - |
| 40843780 | 17 | - |
| 40841049 | 15 | - |
| **Total** | **100** | **-** |