# USE CASE: 27 Retrieve the Population of a Specific Continent

## CHARACTERISTIC INFORMATION

### ***Goal in Context***
As a *data researcher*, I want *to retrieve the population of a specific continent*, so that *I can analyze continental-scale numbers.*

### ***Scope***
World Population Reporting System

### ***Level***
Primary task.

### ***Preconditions***
Database contains current country, continent, and population data.

### ***Success End Condition***
The total population of the selected continent is displayed or generated.

### ***Failed End Condition***
No population figure is generated or the selected continent's population value is inaccurate/incomplete.

### ***Primary Actor***
Data Researcher.

### ***Trigger***
The user requests the population of a specific continent.

## MAIN SUCCESS SCENARIO

1. Data researcher selects or specifies a continent.
2. Application validates the selected continent.
3. Application queries the database for all countries belonging to the selected continent.
4. Application retrieves the population of each country in the selected continent.
5. Application calculates the total population of the continent by summing the country populations.
6. Application displays/exports the total population of the selected continent to the researcher.

## EXTENSIONS

2. ***Invalid or unsupported continent is specified***:
1. Application informs the researcher that the selected continent is invalid.
2. Application requests a valid continent.

3/4/5. ***Database connection fails or required population data is missing***:
1. Application logs database failure or missing-data error.
2. Application informs the researcher that continent population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0