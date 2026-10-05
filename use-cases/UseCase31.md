# USE CASE: 31 Retrieve the Population of a Specific City

## CHARACTERISTIC INFORMATION

### ***Goal in Context***

As a *data researcher*, I want *to retrieve the population of a specific city*, so that *I can check municipal figures.*

### ***Scope***

World Population Reporting System

### ***Level***

Primary task.

### ***Preconditions***

Database contains current city and population data.

### ***Success End Condition***

The population of the selected city is displayed or generated.

### ***Failed End Condition***

No population figure is generated or the selected city's population value is inaccurate/incomplete.

### ***Primary Actor***

Data Researcher.

### ***Trigger***

The user requests the population of a specific city.

## MAIN SUCCESS SCENARIO

1. Data researcher selects or specifies a city.
2. Application validates the selected city.
3. Application queries the database for the selected city.
4. Application retrieves the population of the selected city.
5. Application displays/exports the population of the selected city to the researcher.

## EXTENSIONS

2. ***Invalid or unsupported city is specified***:
3. Application informs the researcher that the selected city is invalid.
4. Application requests a valid city.

3/4. ***Database connection fails or required population data is missing***:

1. Application logs database failure or missing-data error.
2. Application informs the researcher that city population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0