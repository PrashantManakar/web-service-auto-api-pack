# Java API Webservice Automation Pack �
This framework demonstrates a clean, maintainable, and scalable approach to API automation using Java, Cucumber BDD, RestAssured, and common design patterns.

## Project Structure

src/test/java
 └─ com.example.api
     ├─ clients
     │    └─ BaseMethodApi.java        # API client encapsulating REST calls (Singleton)
     ├─ models
     │    └─ MobilePhoneItem.java       # POJO with Builder pattern
     ├─ utils
     │    └─ TestContext.java           # Shared test state (Context pattern)
     ├─ steps
     │    └─ MobilePhoneApiSteps.java   # Cucumber step definitions
     └─ runners
          └─ RunCucumberTest.java       # Test runner
src/test/resources
 └─ features
      └─ MobilePhoneStore.feature                # Feature file(s)



## How to Use

1. Set up the Maven project with the above files in the proper structure.
2. Run tests with:

```bash
mvn clean test
```

3. View detailed test reports in `target/cucumber-reports.html`.

---

## Benefits of This Design

- **Separation of concerns:** API client, models, steps, and test context are well separated.
- **Reusability:** API client methods can be reused across many tests.
- **Maintainability:** Changes in API endpoints or payloads require minimal updates.
- **Test data management:** Builder pattern makes creating flexible test data easy.
- **State management:** TestContext cleanly shares data between steps without static state.
- **Clean code:** Readable, well-named methods and classes.
- **Scalability:** Easy to add more endpoints, models, and scenarios.
- **Setup/teardown:** Hooks ensure test isolation and cleanup.


