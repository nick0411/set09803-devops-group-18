# USE CASE: 30 Retrieve the Population of a Specific District

## CHARACTERISTIC INFORMATION

### ***Goal in Context***

As a *data researcher*, I want *to retrieve the population of a specific district*, so that *I can examine local administrative data.*

### ***Scope***

World Population Reporting System

### ***Level***

Primary task.

### ***Preconditions***

Database contains current district and population data.

### ***Success End Condition***

The population of the selected district is displayed or generated.

### ***Failed End Condition***

No population figure is generated or the selected district's population value is inaccurate/incomplete.

### ***Primary Actor***

Data Researcher.

### ***Trigger***

The user requests the population of a specific district.

## MAIN SUCCESS SCENARIO

1. Data researcher selects or specifies a district.
2. Application validates the selected district.
3. Application queries the database for the selected district.
4. Application retrieves the population of the selected district.
5. Application displays/exports the population of the selected district to the researcher.

## EXTENSIONS

2. ***Invalid or unsupported district is specified***:
3. Application informs the researcher that the selected district is invalid.
4. Application requests a valid district.

3/4. ***Database connection fails or required population data is missing***:

1. Application logs database failure or missing-data error.
2. Application informs the researcher that district population data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

***DUE DATE***: Release 1.0