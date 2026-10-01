# USE CASE: 12 Produce a Report on the Top N Populated Cities in the World

## CHARACTERISTIC INFORMATION

### Goal in Context
As a population researcher, I want to view the top N populated cities in the world ordered from largest to smallest population, so that I can identify the most populated cities globally.

### Scope
World Population Reporting System

### Level
Primary task.

### Preconditions
Database contains current city and population data.

### Success End Condition
A city report listing the requested top N populated cities in the world, ordered from largest to smallest population, is displayed or generated. The report contains each city's name, country, district and population.

### Failed End Condition
No report is generated or the city population data is inaccurate/incomplete.

### Primary Actor
Population Researcher.

### Trigger
The user requests a report containing the top N populated cities in the world.

## MAIN SUCCESS SCENARIO

1. Population researcher requests the top N populated cities in the world.
2. Application receives the requested value of N.
3. Application queries the database for city population data.
4. Application sorts the cities in descending order by population.
5. Application selects the first N cities from the sorted results.
6. Application displays/exports the formatted city report containing each city's name, country, district and population to the researcher.

## EXTENSIONS

2. Invalid value of N is provided:
1. Application informs the researcher that a valid value of N is required.
2. Researcher enters a valid value of N.

3. Database connection fails or required city data is missing:
1. Application logs database failure error.
2. Application informs the researcher that city population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

DUE DATE: Release 1.0