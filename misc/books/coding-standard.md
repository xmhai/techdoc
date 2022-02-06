## Preface
This coding standard, principle and pratice are extracted from most famous coding books:-  
- Clean Code  
  https://dzone.com/articles/in-defense-of-clean-code-100-pieces-of-timeless-ad?edition=703417  
- Effective Java
- Refactoring
- The pragmatic programmer

## Java
- Class length should be around 200
- Method length should be short, e.g. less than 10 lines
- Method parameter should be less than 3
- Use uppercase for static variables
- Throw exception instead of return error code
- Application exception don't inherit from checked exception, should inherit from RuntimeException

## Pattern
- Prefer static factory over constructor
- Wrap third party class exceptions and throw own exception
- Avoid use third party classes in public API interface. e.g. instead of using Map, create a wrapper class with Map as field.
- Use SPI + Adaptor for 3rd party classes

## Practice
- Creat Unit Test for 3rd party classes (also called learning test, to make sure the interface not break in new version)
- Clean Unit Test (BUILD-OPERATE-CHECK)
  - Build up the test data
  - Operate on the test data
  - Check that the operation yielded the expected result
- Rules:
  - Unit Testing
  - Refactoring to remove duplicated code
  - Expressive Naming

## Reference
- Code Clean - Chapter 17
