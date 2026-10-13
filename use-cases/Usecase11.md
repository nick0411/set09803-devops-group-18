# USE CASE: 11 Produce a Report of Cities in a District by Population

## CHARACTERISTIC INFORMATION

### ***Goal in Context***

As a *local administrator*, I want *to view all the cities in a district organised by largest population to smallest*, so that *I can manage district-level urban resources.*

### ***Scope***

World Population Reporting System

### ***Level***

Primary task.

### ***Preconditions***

Database contains current district, city, and population data.

### ***Success End Condition***

A report listing all cities in the selected district ordered from largest population to smallest is displayed or generated.

### ***Failed End Condition***

No report is generated or city population data is inaccurate/incomplete.

### ***Primary Actor***

Local Administrator.

### ***Trigger***

The user requests a report of all cities in a specific district ordered by population.

## MAIN SUCCESS SCENARIO

1. Local administrator selects or specifies a district.
2. Application validates the selected district.
3. Application queries the database for all cities in the selected district.
4. Application retrieves the city name and population for each city.
5. Application sorts the cities by population in descending order.
6. Application formats the city population report.
7. Application displays/exports the ordered city population report to the local administrator.

## EXTENSIONS

2. ***Invalid or unsupported district is specified***:

1. Application informs the local administrator that the selected district is invalid.
2. Application requests a valid district.

3/4. ***Database connection fails or required city population data is missing***:

1. Application logs database failure or missing-data error.
2. Application informs the local administrator that city population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0