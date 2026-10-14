# USE CASE: 4 Produce Report on Top N Populated Countries in the World

## CHARACTERISTICS IN CONTEXT
* **Goal in Context:** As a Policy Maker, I want to view the top N populated countries in the world so that I can focus on major global population centers.
* **Scope:** World Population Reporting System
* **Level:** Primary task
* **Preconditions:** Database contains populated country data.
* **Success End Condition:** A report displaying the top N populated countries in the world is generated.
* **Failed End Condition:** No report is produced or N parameter is invalid.
* **Primary Actor:** Policy Maker / Data Analyst
* **Trigger:** The user requests the top N global countries report and inputs N.

## MAIN SUCCESS SCENARIO
1. User selects the top N world countries report and inputs the number N.
2. System queries database for all countries ordered by population descending and limits results to top N.
3. System presents the formatted report to the user.

## EXTENSIONS
* **1a. Invalid N value (N <= 0 or non-numeric):**
    1. System alerts user to enter a positive integer for N.
    2. User inputs a valid integer.

## SUB-VARIATIONS
* None.

## SCHEDULE
* **DUE DATE:** Release v1.0