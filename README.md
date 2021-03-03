# series-4

## json parser   
implement classes in sbu.cs.parser.json package.  
* extract good methods  
* declare classes where is needed
* all json that you need to parse have only 5 possible value types
    1. String
    2. number
    3. boolean (true or false)
    4. null
    5. array
* all json that you need to parse have these rules
    1. keys are in double quote (" ")
    2. between key and value there is :
    3. between each key/value pair there is a comma (,)
    4. a json object will always start with { and end with }
    

#### more score
* implement function getValue which return the value of a key
   which is not necessarily a String. it can be int, null, boolean 
   or another Json object. for this you probably need to declare classes
   for values.
   
* write a json parser which can parse json that have a key mapped to
   another json. (6th possible value type, another object)

## dom
implement classes in sbu.cs.parser.html package.
* extract good methods
* declare classes where is needed
* in all html Strings given to parse these rules are considered.
    1. all tags that start will end eventually. there is no tag that 
       opens and never close.
    2. tags may have attributes and all attributes are in key/value pairs
#### more score
* implement function toHTMLString that gets a Dom object (Node root) and 
    return a String that is the html representation of dom object (opposite of parse)
