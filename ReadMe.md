# xxAMIDOxx.xxSTACKSxx.Gatling.Tests

xxAMIDOxx.xxSTACKSxx.Gatling.Tests is a sample project demonstrating performance testing (Load testing)
using the tool `Gatling`.

The project contains a small framework and a couple of tests that can be used to build specific load tests
on projects using the Amido Stack.

## Useful Documentation

Gatling docs - https://gatling.io/docs/current

Blog post by Amir Gharai which was used to set up the framework - 
[Performance Testing Framework with Gatling and Maven](https://www.testingexcellence.com/gatling-maven-performance-test-framework/)

## Dependencies

- Java SDK 8
- Maven
- IDE for Scala development. I.e. IntelliJ IDEA

## Quick start

Once the project has been cloned locally, tests can be run using the following command:

`mvn clean gatling:test -Denv=local`

By default, this will run a load test against all simulations with 1 user with a ramp up duration of 1 second.

There are optional parameters that can be added to change the load test:

- `-DrampUsers=X` - This allows you to set the number of users that will be simulated in the tests.
- `-DrampDuration=X` - This sets the amount of seconds the test will ramp the test load from 0 users to X users in the test.
- `-DatOnceUsers=X - ` - Injects a given number of users at once.
- `-DconstantUsersPerSec=X -DconstUsersDuration=X` - Injects users at a constant rate, defined in users per second, during a given duration. Users will be injected at regular intervals.
##### Environment settings

There are 4 environment contexts that you can run the performance tests in, you must set the
system property `-Denv` to point to one of them e.g. `mvn clean gatling:test -Denv=test`

- **local, dev, test, perf**

These environments will programmatically point to their respective properties files.
e.g. 

- **local - local.application.properties**

- **dev - dev.application.properties**

##### Examples

- Run all simulations

`mvn clean gatling:test -Denv=local -DrampUsers=10 -DrampDuration=5 -DatOnceUsers=4`

- Run a single simulation

`mvn clean gatling:test -Denv=local -Dgatling.simulationClass=com.amido.simulations.menu.GetMenuSimulation -DrampUsers=2 -DrampDuration=2`

##### Deleting menu resources

In the root folder of the project run the following commands to delete menu resources by passing the base uri as an argument.

1. `cd src/test/scala/com/amido/utils`
2. `sh tearDownDeleteMenuItems.sh http://localhost:9000`



## Folder Structure

```
└── src
    ├── resources
    │   ├── bodies
    │   └── data
    └── scala
        └── xxAMIDOxx.xxSTACKSxx.Gatling.Tests
            ├── config
            ├── requests
            ├── scenarios
            └── simulations
```

This folder structure and the contents of each folder follows the guideline outlined in the blog post on 
[Testing Excellence](https://www.testingexcellence.com/gatling-maven-performance-test-framework/).
It is recommended that users of this framework refer back to this blog post, as it contains all required details.

_NB: Testing Excellence was written using Gatling 2.3.0, whereas this project is using the current latest version 3.0.3. 
Contrary to what the blog post says, to run the tests you should use `mvn clean gatling:test` rather than `mvn clean gatling:execute`.
More details here: (https://gatling.io/docs/current/migration_guides/2.3-to-3.0)_