# USE CASE: 22 Produce a Report on the Top N Populated Capital Cities in a Region

## CHARACTERISTIC INFORMATION

### Goal in Context
As a *government researcher*, I want *to view the top N populated capital cities in a region*, so that *I can compare regional capitals.*

### Scope
World Population Reporting System

### Level
Primary task.

### Preconditions
Database contains country data, city data, including each country's capital city.

### Success End Condition
A report listing the N most populated capital cities in the chosen region, with their name, country and population, ordered from largest to smallest population, is displayed or generated.

### Failed End Condition
No report is generated or the list does not contain the correct N capital cities.

### Primary Actor
Government Researcher.

### Trigger
The user requests a report on the top N populated capital cities in a chosen region.

## MAIN SUCCESS SCENARIO

1. Government researcher requests a report on the top N populated capital cities in a region and specifies the region and N.
2. Application checks that the region is valid and that N is a positive whole number.
3. Application queries the database for all capital cities in that region with their name, country and population.
4. Application sorts the capital cities in descending order by population.
5. Application keeps only the first N capital cities.
6. Application displays the formatted capital city report to the government researcher.

## EXTENSIONS

2a. **Region is not recognised**:
1. Application informs the government researcher that the region was not found.
2. No report is generated.

2b. **N is invalid (zero, negative or not a number)**:
1. Application informs the government researcher that N must be a positive whole number.
2. No report is generated.

3a. **Database connection fails or capital city data is missing**:
1. Application logs database failure error.
2. Application informs the government researcher that capital city data is currently unavailable.

5a. **N is larger than the number of capital cities available**:
1. Application returns all available capital cities.
2. Application informs the government researcher that fewer than N were found.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0