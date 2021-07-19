## Concept

## dependency

## plugin
- plugin  
  Common maven plugins: clean, complier, surefire, jar, war, javadoc  
  SpringBoot parent pom includes a list of plugins. e.g. maven-compiler-plugin, maven-jar-plugin etc.    
  - build plugins
    configured under \<build>
  - report plugins
    configured under \<reporting>
- goal
  - A plugin can have multiple goals.
  - goals are bound to phase of default lifecycle  
  phase -> plugin:goal
- lifecycle  
  With lifecycle, don't need to run individual goal separately.
  - default
  - site
  - clean
- phase
- \<pluginManagement>  
  defines the **settings** for plugins that will be inherited by modules in your build. This is great for cases where you have a **parent pom** file.

## Usage
- mvn [phase]  
  Maven runs all goals associated with each of the phases up to and including the specific phase.
- mvn [plugin-name]:[goal-name]
- 