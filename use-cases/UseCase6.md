# USE CASE: 6 Produce Report on Top N Populated Countries in a Region

## CHARACTERISTICS IN CONTEXT
* **Goal in Context:** As a Policy Maker, I want to view the top N populated countries in a region so that I can identify leading population centers in that region.
* **Scope:** World Population Reporting System
* **Level:** Primary task
* **Preconditions:** Database contains populated country data.
* **Success End Condition:** A report displaying top N populated countries for the specified region is generated.
* **Failed End Condition:** No report is produced or invalid parameters specified.
* **Primary Actor:** Policy Maker / Data Analyst
* **Trigger:** The user requests top N countries in a region and inputs region name and N.

## MAIN SUCCESS SCENARIO
1. User inputs region name and value for N.
2. System queries database for countries in the region ordered by population descending and limits results to N.
3. System presents the formatted report to the user.

## EXTENSIONS
* **1a. Invalid region name or invalid N:**
    1. System alerts user regarding invalid input.
    2. User re-enters valid region and N parameters.

## SUB-VARIATIONS
* None.

## SCHEDULE
* **DUE DATE:** Release v1.0