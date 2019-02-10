SonarQube Custom PHP Rules
=========================
Created for ACME Inc., a dummy company.

Maybe you're looking for this one: SonarQube [Custom Rule for PHP Example](https://github.com/SonarSource/sonar-custom-rules-examples/tree/master/php-custom-rules)

That's the original one. Go click it! :)

If you wish to look some for new nice rules, feel free to take a look on it.


Definition of Priorities
=========================
Developer must indicate proper reason why rule is skipped, regardless of priority when they're going to deploy
their project.

### Priority.MINOR
  - Least priority
  - Consider refactor if it doesn't conflict the program's functionality.
  - Program's Functionality > This Rule

### Priority.MAJOR
  - Regular priority.
  - Always refactor code. There's always a way to follow this rule and make the program functional.
  - If code refactor is very complicated process, then this rule may be skipped, along with proper reason,
    and this rule may be refactored in the next deployment schedule.

### Priority.CRITICAL
  - Highest priority.
  - Always refactor code. There's always a way to follow this rule and make the program functional.
  - If code refactor is very complicated process, then this rule may be skipped, along with proper reason,
    and this rule may be refactored in the next deployment schedule.

Definition of Tags
=========================
Each rule may have more than one rules. **convention** is a mandatory tag, thus, all rules do have this tag.

### convention
  - Mandatory tag.

### code-smell
  - Indicates code smell.

### vulnerability
  - Indicates possible security vulnerability in the application, unless code is refactored.
  - Usually, this rule has higher priority among other rules.


Code Smell
=========================
  - Bloats the source code unnecessarily
  - Indicates unnecessary complexity
  - Possibly harder to read, and there's more better approach to make code more readable
  - Causes potential problem / bug that can only be resolved

