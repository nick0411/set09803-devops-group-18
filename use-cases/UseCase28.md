# USE CASE: 28 Retrieve the Population of a Specific Region

## CHARACTERISTIC INFORMATION

### ***Goal in Context***
As a *data researcher*, I want *to retrieve the population of a specific region*, so that *I can review regional totals.*

### ***Scope***
World Population Reporting System

### ***Level***
Primary task.

### ***Preconditions***
Database contains current country, region, and population data.

### ***Success End Condition***
The total population of the selected region is displayed or generated.

### ***Failed End Condition***
No population figure is generated or the selected region's population value is inaccurate/incomplete.

### ***Primary Actor***
Data Researcher.

### ***Trigger***
The user requests the population of a specific region.

## MAIN SUCCESS SCENARIO

1. Data researcher selects or specifies a region.
2. Application validates the selected region.
3. Application queries the database for all countries belonging to the selected region.
4. Application retrieves the population of each country in the selected region.
5. Application calculates the total population of the region by summing the country populations.
6. Application displays/exports the total population of the selected region to the researcher.

## EXTENSIONS

2. ***Invalid or unsupported region is specified***:
1. Application informs the researcher that the selected region is invalid.
2. Application requests a valid region.

3/4/5. ***Database connection fails or required population data is missing***:
1. Application logs database failure or missing-data error.
2. Application informs the researcher that region population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0