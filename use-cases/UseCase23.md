# USE CASE: 23 Produce a Report on Urban and Rural Population by Continent

## CHARACTERISTIC INFORMATION

### ***Goal in Context***
As a *demographic researcher*, I want *to view the total population, urban population, and rural population in each continent*, so that *I can assess global urbanization rates.*

### ***Scope***
World Population Reporting System

### ***Level***
Primary task.

### ***Preconditions***
Database contains current continent, country, city, and population data.

### ***Success End Condition***
A report listing each continent, total population, urban population, and rural population is displayed or generated.

### ***Failed End Condition***
No report is generated or population metrics are inaccurate/incomplete.

### ***Primary Actor***
Demographic Researcher.

### ***Trigger***
The user requests a report on total, urban, and rural population for each continent.

## MAIN SUCCESS SCENARIO

1. Demographic researcher requests a report on total, urban, and rural population for each continent.
2. Application queries the database for the total population of each continent.
3. Application queries the total urban population for each continent.
4. Application calculates the rural population for each continent by subtracting the urban population from the total population.
5. Application organizes the population data by continent.
6. Application displays/exports the formatted population report to the researcher.

## EXTENSIONS

2/3. ***Database connection fails or required population data is missing***:
1. Application logs database failure error.
2. Application informs the researcher that population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0