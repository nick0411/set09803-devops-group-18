# USE CASE: 19 Produce a Report on All Capital Cities in a Region by Population

## CHARACTERISTIC INFORMATION

### Goal in Context
As a *political scientist*, I want *to view all the capital cities in a region organised by largest to smallest*, so that *I can evaluate regional capital populations.*

### Scope
World Population Reporting System

### Level
Primary task.

### Preconditions
Database contains country data, city data, including each country's capital city.

### Success End Condition
A report listing all capital cities in the chosen region, with their name, country and population, ordered from largest to smallest population, is displayed.

### Failed End Condition
No report is generated or the capital city list is incomplete/inaccurate.

### Primary Actor
Political Scientist.

### Trigger
The user requests a report on all capital cities in a chosen region.

## MAIN SUCCESS SCENARIO

1. Political scientist requests a report on all capital cities in a region and specifies the region.
2. Application checks that the region is valid.
3. Application queries the database for all capital cities in that region with their name, country and population.
4. Application sorts the capital cities in descending order by population.
5. Application displays the formatted capital city report to the political scientist.

## EXTENSIONS

2a. **Region is not recognised**:
1. Application informs the political scientist that the region was not found.
2. No report is generated.

3a. **Database connection fails or capital city data is missing**:
1. Application logs database failure error.
2. Application informs the political scientist that capital city data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0