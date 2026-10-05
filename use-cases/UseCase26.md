# USE CASE: 26 Retrieve the Population of the World

## CHARACTERISTIC INFORMATION

### ***Goal in Context***
As a *data researcher*, I want *to retrieve the population of the world*, so that *I can establish a baseline for global statistics.*

### ***Scope***
World Population Reporting System

### ***Level***
Primary task.

### ***Preconditions***
Database contains current population data for all countries in the world.

### ***Success End Condition***
The total population of the world is displayed or generated.

### ***Failed End Condition***
No population figure is generated or the world population value is inaccurate/incomplete.

### ***Primary Actor***
Data Researcher.

### ***Trigger***
The user requests the total population of the world.

## MAIN SUCCESS SCENARIO

1. Data researcher requests the total population of the world.
2. Application queries the database for population data for all countries.
3. Application calculates the total world population by summing the population of all countries.
4. Application verifies the calculated world population value.
5. Application displays/exports the total world population to the researcher.

## EXTENSIONS

2/3. ***Database connection fails or required population data is missing***:
1. Application logs database failure or missing-data error.
2. Application informs the researcher that world population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0