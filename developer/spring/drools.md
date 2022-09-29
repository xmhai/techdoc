## Document
- Refer below link for sample code and excel rule  
    - https://www.baeldung.com/drools-excel  
https://medium.com/codex/spring-boot-with-drools-engine-7119774c559f  
    - https://paras301.medium.com/working-with-drools-using-excel-sheet-supplied-from-outside-project-part-1-8be30afc8180
    - Official drools doc  
    https://docs.drools.org/5.4.0.CR1/drools-expert-docs/html/ch06.html
- Some Thinking
  - Flexibility. No need UI, and Business user can define the rules.
  - Testability
  - Declarative
- Rules principle
  - Each rule should try NOT to depend on any other specific rule. 
  - Rules should depend only on the data provided by the domain.
  - Small and Automicity.
  - Rules don't follow one specific order (only allow on specific cases).


## Drools Programming
- KieServices
- KieFileSystem (kieServices.newKieFileSystem())
- KieBuilder (kieServices.newKieBuilder(kieFileSystem))
  - kieBuilder.buildAll()
    Generate KieModule, i.e. kjar
- KieModule (kieBuilder.KieModule())
- KieContainer (kieServices.newKieContainer(kieModule.getReleaseId()))
- KieSession (kieContainer.newKieSession)
- To upload single rule
  - save the new rules file to existing KieFileSystem:  
    ```java
    // the folder must be under src/main/resources 
    // must keep the original file name, .drl or .xlsx
    kieFileSystem.write("src/main/resources/rules"+filename, file);
    ```
  - Create a new KieBuilder with the KieFileSystem.
  - Create a new container.

## Concept
- DRL rules are converted to Java class.
- Decision table (Excel file) are converted to DRL, and each row is converted to one DRL rule.
- Ideally, rules are authored without regard for the order of rows.
- Drools works by:   
    - Taking all of the rules up front and evaluating whether their conditions are satisfied. Each of these rules is called a "match".
    - When you fire the rules, Drools collects all of the matches, orders them (either naturally or by salience), and then iterates through and executes them one by one.
    - https://stackoverflow.com/questions/66708703/drools-decision-table-action-execution-order
- Rules evaluation and execution is separated. Action and data are put in Agenda.
- It is common to have multiple instances dealing with different rules and data than just one big instance.

## Best Practice
- **Define Rules**  
  - The output and input are defined in model class, use setXXX to return value.
  - RuleTable name must be unique (must change the value if copy-and-paste the excel).
  - Multiple OBJECT
  - Multiple CONDITION
      - each condition must have a Class defined
      - multiple CONDITION with same Class header can be merged.
  - Multiple ACTION
  - For equal operator, can only define the field in header and value in cell.
  - Object defined with prefix $, and it is for use in ACTION.
  - How to define if-else rule:  
    Put the generic condition before specific conditions, and set Sequential to true.
  - Parent-Child  
    Insert both fact to session.
  - avoid using && and || in condition, try to separate to two rules or two conditions.
- **How to store rules in the system**  
  Store the rules in DB, and assign a RULE_ID for each rule.
- **When the rules are loaded**  
  During system initialization @PostConstruct
- **How to dynamically reload rules**
  Recreate the KieFileSystem or only update one file of KieFileSystem.
- **How to invoke only rule**  
  - Option 1: Define rule name with same prefix. e.g. Point Award, (the rule name will be auto converted to Pascal Case)
  new RuleNameStartsWithAgendaFilter("Point Award")
  To findout the rules triggered, use DebugAgendaEventListener  
    ```java
    ksession.addEventListener( new DebugRuleRuntimeEventListener() );
    ```
  - Option 2: Define agenda group  
    For excel, define AGENDA-GROUP in RuleSet section, and set group name (need double quoted, e.g. "promotion")
    by default, all the rules have an implicit MAIN agenda group "MAIN".
    ```java
    ksession.getAgenda().getAgendaGroup("promotions").setFocus();
    ```

## Troubleshooting
- Drools converts the decision table to DRL. Due to that, dealing with errors and typos in the Excel file can be hard. Often the errors refer to the content of the DRL. So to troubleshoot, it helps to print and analyze the DRL.

