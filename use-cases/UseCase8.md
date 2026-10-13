# USE CASE: 8 Produce a Report of Cities in a Continent by Population

## CHARACTERISTIC INFORMATION

### ***Goal in Context***

As a *regional planner*, I want *to view all the cities in a continent organised by largest population to smallest*, so that *I can analyze urban centers at a continental level.*

### ***Scope***

World Population Reporting System

### ***Level***

Primary task.

### ***Preconditions***

Database contains current continent, country, city, and population data.

### ***Success End Condition***

A report listing all cities in the selected continent ordered from largest population to smallest is displayed or generated.

### ***Failed End Condition***

No report is generated or city population data is inaccurate/incomplete.

### ***Primary Actor***

Regional Planner.

### ***Trigger***

The user requests a report of all cities in a specific continent ordered by population.

## MAIN SUCCESS SCENARIO

1. Regional planner selects or specifies a continent.
2. Application validates the selected continent.
3. Application queries the database for all cities in the selected continent.
4. Application retrieves the city name, country, and population for each city.
5. Application sorts the cities by population in descending order.
6. Application formats the city population report.
7. Application displays/exports the ordered city population report to the regional planner.

## EXTENSIONS

2. ***Invalid or unsupported continent is specified***:

1. Application informs the regional planner that the selected continent is invalid.
2. Application requests a valid continent.

3/4. ***Database connection fails or required city population data is missing***:

1. Application logs database failure or missing-data error.
2. Application informs the regional planner that city population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0