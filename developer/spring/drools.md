## Document
- Refer below link for sample code and excel rule  
    - https://www.baeldung.com/drools-excel  
https://medium.com/codex/spring-boot-with-drools-engine-7119774c559f  
    - https://paras301.medium.com/working-with-drools-using-excel-sheet-supplied-from-outside-project-part-1-8be30afc8180
    - Official drools doc  
    https://docs.drools.org/5.4.0.CR1/drools-expert-docs/html/ch06.html

## Concept
- DRL rules are converted to Java class.
- Decision table (Excel file) are converted to DRL, and each row is converted to one DRL rule.
- Ideally, rules are authored without regard for the order of rows.
- Drools works by:   
    - Taking all of the rules up front and evaluating whether their conditions are satisfied. Each of these rules is called a "match".
    - When you fire the rules, Drools collects all of the matches, orders them (either naturally or by salience), and then iterates through and executes them one by one.
    - https://stackoverflow.com/questions/66708703/drools-decision-table-action-execution-order

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
- **How to store rules in the system**?  
  Store the rules in DB, and assign a RULE_ID for each rule.
- **When the rules are loaded**???  
  During system initialization or rule triggered first time???
- How to dynamically reload rules???
- **How to invoke the rule**???


## Troubleshooting
- Drools converts the decision table to DRL. Due to that, dealing with errors and typos in the Excel file can be hard. Often the errors refer to the content of the DRL. So to troubleshoot, it helps to print and analyze the DRL.

