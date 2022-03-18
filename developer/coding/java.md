## Annotation  
https://docs.oracle.com/javase/tutorial/java/annotations/  
- Use
  - Information to compiler  
    e.g. @Override
  - Compile-time or deployment-time processing  
    e.g. generate source code
  - Runtime processing  
    
- Define  
  - Declare @interface which is similar to interface.  
  - Add meta-annotations: @Retention, @Target  

- Format  
  @annotation, @annotation(name="value"), @annotation("value")

- Frequent Used Annotation  
  - Java language: @Override, @SuppressWarnings, @Interface, @Deprecated, @FunctionInterface  
  - meta-annotations: @Retention, @Target, @Documented  
  - Frameworks

- Processing
  - Compiler check the annotation in source code.
  - Custom classes check the annotation stored in .class.

- Use Case
  - maven-compiler-plugin  
    annotation processor
  - lombok: @Getter
  - Java Validation: @Valid
  - JPA: @Entity