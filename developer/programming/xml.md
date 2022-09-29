## Concept
https://stackify.com/java-xml-jackson/  
- Jackson XmlMapper is an extension of OjbectMapper used for XML.
- Addtional annotion to support XML.

## XmlMapper Annoation
- @JacksonXmlProperty(localName = "element")
- @JacksonXmlElementWrapper(localName = "list")
- For List, e.g. List<Person> persons
  - By default, wrapper element and individual item will have name as defined in @JacksonXmlProperty.
  - So to set wrapper element name, use @JacksonXmlElementWrapper(localName = "wrapper_name")
- @JacksonXmlElementWrapper(useWrapping = false)
  - Set to false when Wrapper element is not required.
- @JacksonXmlCData
- @JacksonXmlRootElement(namespace = "urn:stackify:jacksonxml", localName = "PersonData")
- By default, Jackson will always use a wrapper element for collections, which is also different to how JAXB works.

## Options
- Jaxb vs Jackson: Jackson performance is better.
