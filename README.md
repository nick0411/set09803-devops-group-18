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

    0 requirements out of 32 have been implemented (0.0%).

| ID | Requirement Name | Met | Screenshot |
| --- | --- | --- | --- |
| 1 | All the countries in the world organised by largest population to smallest. | No |  |
| 2 | All the countries in a continent organised by largest population to smallest. | No |  |
| 3 | All the countries in a region organised by largest population to smallest. | No |  |
| 4 | The top N populated countries in the world where N is provided by the user. | No |  |
| 5 | The top N populated countries in a continent where N is provided by the user. | No |  |
| 6 | The top N populated countries in a region where N is provided by the user. | No |  |
| 7 | All the cities in the world organised by largest population to smallest. | No |  |
| 8 | All the cities in a continent organised by largest population to smallest. | No |  |
| 9 | All the cities in a region organised by largest population to smallest. | No |  |
| 10 | All the cities in a country organised by largest population to smallest. | No |  |
| 11 | All the cities in a district organised by largest population to smallest. | No |  |
| 12 | The top N populated cities in the world where N is provided by the user. | No |  |
| 13 | The top N populated cities in a continent where N is provided by the user. | No |  |
| 14 | The top N populated cities in a region where N is provided by the user. | No |  |
| 15 | The top N populated cities in a country where N is provided by the user. | No |  |
| 16 | The top N populated cities in a district where N is provided by the user. | No |  |
| 17 | All the capital cities in the world organised by largest population to smallest. | No |  |
| 18 | All the capital cities in a continent organised by largest population to smallest. | No |  |
| 19 | All the capital cities in a region organised by largest to smallest. | No |  |
| 20 | The top N populated capital cities in the world where N is provided by the user. | No |  |
| 21 | The top N populated capital cities in a continent where N is provided by the user. | No |  |
| 22 | The top N populated capital cities in a region where N is provided by the user. | No |  |
| 23 | Population of people living in and outside cities in each continent. | No |  |
| 24 | Population of people living in and outside cities in each region. | No |  |
| 25 | Population of people living in and outside cities in each country. | No |  |
| 26 | The population of the world. | No |  |
| 27 | The population of a continent. | No |  |
| 28 | The population of a region. | No |  |
| 29 | The population of a country. | No |  |
| 30 | The population of a district. | No |  |
| 31 | The population of a city. | No |  |
| 32 | Language report (Chinese, English, Hindi, Spanish, Arabic with world percentages). | No |  |