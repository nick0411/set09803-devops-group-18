# Use Cases: Country Reports (Requirements 1 - 6)

## USE CASE: Produce Country Reports

### CHARACTERISTICS IN CONTEXT
* **Goal in Context:** As a Data Analyst / User, I want to produce country population reports (global, by continent, by region, and top N variations) so that demographic analyses can be conducted.
* **Scope:** World Population Reporting System[cite: 5].
* **Level:** Primary task.
* **Preconditions:** Database is populated with up-to-date world population data.
* **Success End Condition:** A formatted report is generated displaying requested country population details.
* **Failed End Condition:** No report generated or incorrect parameters specified.
* **Primary Actor:** Data Analyst / Policy Maker.
* **Trigger:** User requests a country population report.

### MAIN SUCCESS SCENARIO
1. User selects desired country report type:
    - Req 1: All countries in the world ordered by population (descending).
    - Req 2: All countries in a specified continent ordered by population.
    - Req 3: All countries in a specified region ordered by population.
    - Req 4: Top N populated countries in the world (where N is provided by user).
    - Req 5: Top N populated countries in a specified continent.
    - Req 6: Top N populated countries in a specified region.
2. User provides required parameters (e.g., continent name, region name, or N value).
3. System queries the database for matching country records sorted by population in descending order.
4. System formats and outputs the report.

### EXTENSIONS
* **2a / 3a / 5a / 6a. Invalid location input:**
    1. System informs user that the specified continent or region does not exist.
    2. User re-enters valid location parameters.
* **4a / 5a / 6a. Invalid N value (N <= 0):**
    1. System alerts user to provide a positive integer.
    2. User inputs a valid value for N.