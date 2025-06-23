Choose auto-configuration or custom configuration
- uri is a better option, but most of time need password injection, and the password has to be urlencoded, so it is not applicable in real project.
- auto-configuration rely on spring.data.mongodb, either uri or other support properties  
- custom configuration  
  - define mongodb properties in app.mongodb secction, remove standard spring mongo configurations.
  - define CustomMongoProperties
  - define MongoConfig with MongoClient, MongoDatbaseFactory and MongoTemplate (MongoReactiveClient etc for reactive driver)
- can use sync and async driver.