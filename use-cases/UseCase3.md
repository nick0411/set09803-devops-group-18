# USE CASE: 3 Produce Report on All Countries in a Region by Population

## CHARACTERISTICS IN CONTEXT
* **Goal in Context:** As a Data Analyst, I want to produce a report on all countries in a specific region organized by largest population to smallest so that I can analyze regional demographics.
* **Scope:** World Population Reporting System
* **Level:** Primary task
* **Preconditions:** Database contains populated country data.
* **Success End Condition:** A report listing all countries in the specified region sorted by population in descending order is produced.
* **Failed End Condition:** No report is produced or invalid region specified.
* **Primary Actor:** Data Analyst
* **Trigger:** The user requests a regional country population report and specifies a region.

## MAIN SUCCESS SCENARIO
1. User selects the report for countries in a specified region and inputs the region name.
2. System queries database for countries in the specified region ordered by population descending.
3. System presents the formatted report to the user.

## EXTENSIONS
* **2a. Specified region does not exist:**
    1. System alerts user that the region was not found.
    2. User inputs a valid region name.

## SUB-VARIATIONS
* None.

## SCHEDULE
* **DUE DATE:** Release v1.0