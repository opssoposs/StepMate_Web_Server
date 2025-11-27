# StepMate Web Server

This project is a Kotlin/Spring Boot server that exposes example endpoints documenting the StepMate ERD using Swagger/OpenAPI annotations.

## Running and testing

The build depends on `springdoc-openapi-starter-webmvc-ui`. In this environment Maven Central access is blocked, so Gradle cannot download the dependency and the build fails. To run successfully you need one of the following:

- Allow outbound access to Maven Central (or another mirror) so Gradle can download `springdoc-openapi-starter-webmvc-ui` and its transitive dependencies.
- Pre-install the dependency into your local Maven cache (e.g., `~/.m2/repository`), then run the build again. The `mavenLocal()` repository is enabled in `build.gradle.kts` to pick it up.

Once the dependency is available, you can start the application with:

```bash
./gradlew bootRun
```

Then open Swagger UI at `http://localhost:8080/swagger-ui.html` to browse the documented endpoints.
