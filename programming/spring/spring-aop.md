## Reference
https://niels.nu/blog/2017/spring-boot-aop

## Concept
An Aspect is a piece of functionality (such as functionality to log method call durations). It will be defined in a class annotated with the @Aspect annotation. It’s a module that contains one more more advices; pieces of code that get applied to join points (in the case of Spring AOP join points are always methods). Join points (methods) are selected in Pointcut expressions: declarative patterns that tell Spring AOP which methods to apply your logic to.

- Aspect: functionality  
  @Aspect
- Join Point: methods
  @Timed
- Point Cut: annotation or expression to select join points  
  @Around("@annotation(com.nibado.example.springaop.aspects.Timed)
- Advice: pieces of code that get applied to join points  
  public Object time(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {...}

