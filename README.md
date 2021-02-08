# Junit5 features

[JUnit5](https://junit.org/junit5/), [Selenide](https://selenide.org/) for UI testing

#### Run tests locally
1. Install JDK 1.8, maven 3.3.9+
2. Execute inside repository directory
```
$ mvn clean test -Dselenide.browser=chrome
mvn -Dtest=Junit5ExtraFeaturesTestUseCase test
mvn test -Dgroups="Smoke"
mvn -Dtest=Junit5ParallelTest test
```
