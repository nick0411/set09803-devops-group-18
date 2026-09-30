# USE CASE: 32 Produce a Report on Global Language Distribution

## CHARACTERISTIC INFORMATION

### Goal in Context
As a *linguistic researcher*, I want *to view the number of people who speak Chinese, English, Hindi, Spanish and Arabic ordered from greatest to smallest, including their percentage of the world population*, so that *I can understand global language distribution.*

### Scope
World Population Reporting System

### Level
Primary task.

### Preconditions
Database contains current population and language data (Chinese, English, Hindi, Spanish, and Arabic).

### Success End Condition
A report listing the top 5 languages, total speakers and their respective world population percentages is displayed or generated.

### Failed End Condition
No report is generated or language metrics are inaccurate/incomplete.

### Primary Actor
Linguistic Researcher.

### Trigger
The user requests a report on the specified global languages.

## MAIN SUCCESS SCENARIO

1. Linguistic researcher requests a report on the specific target languages (Chinese, English, Hindi, Spanish, Arabic).
2. Application queries the database for total world population.
3. Application queries total speakers for each specified language.
4. Application calculates each language's speaker count as a percentage of the total world population.
5. Application sorts the language data in descending order by total speaker count.
6. Application displays/exports the formatted language report to the researcher.

## EXTENSIONS

2/3. **Database connection fails or required language data is missing**:
1. Application logs database failure error.
2. Application informs the researcher that language data is currently unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0