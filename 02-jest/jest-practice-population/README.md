# Populatron


## Objectives
- Implement TDD in pairs to create a command line interface application in JavaScript
- Use Jest testing library to write tests & assertions
- Mock external dependencies using Jest


## Background
In this repository you will find a CSV file containing information on the cities in the world.

You will also find an npm project with a single integration test.

The test follows the outside-in paradigm, and there is currently a failing user-acceptance test.

When you run this application in the command line with the filepath of a CSV file (included in the repo), it should compute and print out the world's population like this:

> The population of the world is: 1,347,982,728

## Acceptance Criteria

- Isolate the entry point from the application class as depicted in the attached [class diagram](class-diagram.png).
- Filesystem access should happen in its own class so it can be mocked.
- Use Jest to mock and inject your file I/O class.
- Isolate your CSV parsing logic from your app by refactoring it into a parser that returns a generic list of objects.

## Instructions

- To install the dependencies, run `npm install`.
- To start the application, run `npm start`.
- To run the tests, run `npm test`.
