# USE CASE: 29 Retrieve the Population of a Specific Country

## CHARACTERISTIC INFORMATION

### ***Goal in Context***

As a *data researcher*, I want *to retrieve the population of a specific country*, so that *I can reference official national figures.*

### ***Scope***

World Population Reporting System

### ***Level***

Primary task.

### ***Preconditions***

Database contains current country and population data.

### ***Success End Condition***

The population of the selected country is displayed or generated.

### ***Failed End Condition***

No population figure is generated or the selected country's population value is inaccurate/incomplete.

### ***Primary Actor***

Data Researcher.

### ***Trigger***

The user requests the population of a specific country.

## MAIN SUCCESS SCENARIO

1. Data researcher selects or specifies a country.
2. Application validates the selected country.
3. Application queries the database for the selected country.
4. Application retrieves the population of the selected country.
5. Application displays/exports the population of the selected country to the researcher.

## EXTENSIONS

2. ***Invalid or unsupported country is specified***:
3. Application informs the researcher that the selected country is invalid.
4. Application requests a valid country.

3/4. ***Database connection fails or required population data is missing***:

1. Application logs database failure or missing-data error.
2. Application informs the researcher that country population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0
