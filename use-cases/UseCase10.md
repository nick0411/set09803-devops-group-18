# USE CASE: 10 Produce a Report of Cities in a Country by Population

## CHARACTERISTIC INFORMATION

### ***Goal in Context***

As a *local analyst*, I want *to view all the cities in a country organised by largest population to smallest*, so that *I can understand the urban hierarchy of that country.*

### ***Scope***

World Population Reporting System

### ***Level***

Primary task.

### ***Preconditions***

Database contains current country, city, and population data.

### ***Success End Condition***

A report listing all cities in the selected country ordered from largest population to smallest is displayed or generated.

### ***Failed End Condition***

No report is generated or city population data is inaccurate/incomplete.

### ***Primary Actor***

Local Analyst.

### ***Trigger***

The user requests a report of all cities in a specific country ordered by population.

## MAIN SUCCESS SCENARIO

1. Local analyst selects or specifies a country.
2. Application validates the selected country.
3. Application queries the database for all cities in the selected country.
4. Application retrieves the city name and population for each city.
5. Application sorts the cities by population in descending order.
6. Application formats the city population report.
7. Application displays/exports the ordered city population report to the local analyst.

## EXTENSIONS

2. ***Invalid or unsupported country is specified***:

1. Application informs the local analyst that the selected country is invalid.
2. Application requests a valid country.

3/4. ***Database connection fails or required city population data is missing***:

1. Application logs database failure or missing-data error.
2. Application informs the local analyst that city population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0