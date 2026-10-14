# USE CASE: 1 Produce Report on All Countries in the World by Population

## CHARACTERISTICS IN CONTEXT
* **Goal in Context:** As a Data Analyst, I want to produce a report on all countries in the world organized by largest population to smallest so that I can analyze global demographic distributions.
* **Scope:** World Population Reporting System
* **Level:** Primary task
* **Preconditions:** Database contains populated country data.
* **Success End Condition:** A report listing all countries in the world sorted by population in descending order is produced.
* **Failed End Condition:** No report is produced or database connection fails.
* **Primary Actor:** Data Analyst
* **Trigger:** The user requests the all-countries population report.

## MAIN SUCCESS SCENARIO
1. User selects the report for all countries in the world by population.
2. System queries database for all countries ordered by population descending.
3. System extracts country details (Code, Name, Continent, Region, Population, Capital).
4. System presents the formatted report to the user.

## EXTENSIONS
* **2a. Database connection fails:**
    1. System displays an error message to the user.

## SUB-VARIATIONS
* None.

## SCHEDULE
* **DUE DATE:** Release v1.0