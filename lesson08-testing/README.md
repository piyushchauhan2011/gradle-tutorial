# Lesson 8: Testing in Gradle

## Description
Integrating and running tests.

## Key Concepts
- Test tasks
- Frameworks
- Reporting

## Runnable Examples
- Run `./g -p lesson08-testing test`.
- Alternative: `./gradlew -p lesson08-testing test`

## Test Output Options

You can view test output with various verbosity levels:

```bash
# Show all test output with verbose logging
./g -p lesson08-testing test --info

# Show extra verbose output (stack traces, etc.)
./g -p lesson08-testing test --debug

# Run specific test
./g -p lesson08-testing test --tests CalculatorTest.testAdd

# List all tests without running
./g -p lesson08-testing test --dry-run
```

## Exercises
1. Write and run a failing test, then fix it.
