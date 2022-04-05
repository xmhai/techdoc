## Persist to database  
- @Enumerated(EnumType.ORDINAL)  
  The ordinal of an Enum depends on the ordering of its values and can create problems, if we need to add new ones.  
- @Enumerated(EnumType.STRING)  
   verbose and renaming a value will break the database mapping.  
- Attribute Converter  
  @Converter(autoApply = true)  
  implements AttributeConverter  

## Convert to Json  
- @JsonValue
- @JsonCreator