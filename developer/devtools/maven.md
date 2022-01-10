## Concept  
A build framework using plugins to manage build processes: Compiling, Testing, Reporting, Packaging  
- Standard project directory layout (Archetypes, can build own and use as local)  
- Manage dependency  
  Handle transitive dependency  
- lifecycle, build phases, goals
- plugins  
e.g. mvn dependency:tree is using plugin  
- pom.xml  

## Configuration
- pom.xml  
  pom.xml is inherited from maven super pom, parent pom. e.g. "central" repository is defined in super pom  
  - repository  
    define repository with an **id**, this id will be referred in settings.xml.  
- settings.xml  
  - proxy (enterprise environment)   
  - can encrypt password  
  - mirrors (enterprise environment)  
    for maven to use enterprise repository by having it mirror all repositry request, this overrides what is in pom.xml
  - servers  
    the id of the server need to match the id of repository defined in pom.xml.  

## Dependency
- dependency mediation  
  - closest to the tree  
  - first-found dependency  
  - can override by explicitly include the JAR in pom  
  - mvn dependency:tree  
  - to resolve JAR conflict, can use \<exclusion> tag  

## Properties
- Implicit Properties which can be referred directly in pom  
  project.xxx, env.xxx, settings.xxx  
- User-Defined Properties  
  defined under \<properties>

## Lifecycle
- Goal
  - Maven goals are granular and typically perform one task  
  - Goals in Maven are packaged in plugins  
    e.g. run "mvn complier:compile", **compiler** is the **plugin** that provides the **goal compile**
- Plugin  
  https://www.baeldung.com/core-maven-plugins  
  - JAR file that can performs some tasks (goals)  
  - Use @Mojo to define custom plugin goal and default phase (either use @Mojo(defaultPhase=...) annotaion or @goal and @phase in Javadoc)  
  - Two types: Build plugin and reporting plugin  
  - Build plugin can have a default phase
- Lifecycle
  Maven’s build lifecycle constitutes a series of stages (phases) that get executed in the same order. (https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference)  
  Maven project has the following three built-in lifecycles:  
  - default  
    responsible for all steps in the build process.
  - clean
  - site  
    in charge of building a site, showing Maven related information of the project.  
- Phase
  - The \<packaging> element in the pom.xml file will automatically assign the right goals for each of the phases.  
  - Each phase is associated with zero or more goals.  
  - For phases without any goals associated, Maven will skip the phase execution. Such phases serve as placeholders for users and third-party vendors to associate their custom-built goals.
  - Maven automatically executes all the phases prior to a requested phase.

## mvn commandline  
- mvn -v  

Execute a goal  
- mvn **[plugin_identifier]:[goal_identifier]**  
- mvn dependency:tree
- mvn complier:compile
- mvn help:describe -Dplugin=compiler

Execute one or more phases  
- mvn **[phase] [phase]**  
- mvn package

## Maven plugins
Maven default plugins are developed as Maven project (e.g. maven-surefire-plugin, maven-jar-plugin...)
- help (built-in)
- compiler
- clean
- ...

## Multiple Module
- parent POM  
  \<packaging>pom\<\packaging>  
  \<modules>\<module>...\<\modules>  
- \<pluginmanagement>  
  affect this POM and all inheriting POMs. 
- \<pluginmanagement>

## Question
- Which lifecycle is invoked?  
  Decided by the phases passed in mvn command.  
- Where are the phase defined for a lifecycle?  
  https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference  
- Is the phase name unique?  
  yes
- Where the the phase goals are defined?  
  When create plugin, we can specify the default phase or goal (refer to above plugin section),  
  also we can specify extra plugin, goals and phase in \<build> section.  
  Can customize own maven phase:  
  https://stackoverflow.com/questions/12433120/creating-a-new-phase  
- How does the short form plugin name like to plugin jar file? e.g. mvn jetty:run  
  This is done automatically if you follow the convention of using ${prefix}-maven-plugin (or maven-${prefix}-plugin if the plugin is part of the Apache Maven project)  
  https://maven.apache.org/guides/plugin/guide-java-plugin-development.html  
- To execute JUnit test only  
  mvn -Dtest=NameOfTest surefire:test  
