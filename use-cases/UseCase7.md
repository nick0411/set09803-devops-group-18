# USE CASE: 7 Produce a Report of Cities in the World by Population

## CHARACTERISTIC INFORMATION

### ***Goal in Context***

As an *urban planner*, I want *to view all the cities in the world organised by largest population to smallest*, so that *I can study global urbanization patterns.*

### ***Scope***

World Population Reporting System

### ***Level***

Primary task.

### ***Preconditions***

Database contains current city and population data for cities around the world.

### ***Success End Condition***

A report listing all cities in the world ordered from largest population to smallest is displayed or generated.

### ***Failed End Condition***

No report is generated or city population data is inaccurate/incomplete.

### ***Primary Actor***

Urban Planner.

### ***Trigger***

The user requests a report of all cities in the world ordered by population.

## MAIN SUCCESS SCENARIO

1. Urban planner requests a report of all cities in the world by population.
2. Application queries the database for all city population data.
3. Application retrieves the city name, country and population for each city.
4. Application sorts the cities by population in descending order.
5. Application formats the city population report.
6. Application displays/exports the ordered city population report to the urban planner.

## EXTENSIONS

2/3. ***Database connection fails or required city population data is missing***:

1. Application logs database failure or missing-data error.
2. Application informs the urban planner that city population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0