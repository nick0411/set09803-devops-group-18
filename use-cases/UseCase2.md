# USE CASE: 2 Produce Report on All Countries in a Continent by Population

## CHARACTERISTICS IN CONTEXT
* **Goal in Context:** As a Data Analyst, I want to produce a report on all countries in a specific continent organized by largest population to smallest so that I can compare population sizes within a continent.
* **Scope:** World Population Reporting System
* **Level:** Primary task
* **Preconditions:** Database contains populated country data.
* **Success End Condition:** A report listing all countries in the specified continent sorted by population in descending order is produced.
* **Failed End Condition:** No report is produced or invalid continent specified.
* **Primary Actor:** Data Analyst
* **Trigger:** The user requests a continent country population report and provides a continent name.

## MAIN SUCCESS SCENARIO
1. User selects the report for countries in a specified continent and inputs the continent name.
2. System queries database for countries matching the specified continent ordered by population descending.
3. System presents the formatted report to the user.

## EXTENSIONS
* **2a. Specified continent does not exist:**
    1. System alerts user that the continent was not found.
    2. User inputs a valid continent name.

## SUB-VARIATIONS
* None.

## SCHEDULE
* **DUE DATE:** Release v1.0