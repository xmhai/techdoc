## variable
- Old JS  
  var
- New JS  
  let, const

## arrow function
- Old JS  
  function myFunction (name) {return name;}
- New JS  
  const myFunction = (name) => {return name;}
- Can remove pranthesis if only one parameter:  
  const myFunction = name => {return name;}
- Can remove curly bracket and return if there is only one return statement
  const myFunction = name => name;

## Export and Import
- default export
  import name from './some.js'
- named export
  import {function [as alias]} from './some.js'
  import * as alias from './some.js'

## Class
- Define properties directly
- Arrow function as method

## Three dots operator (Can be used to copy oject)
- Spread array
- Spread Javascript Object properties
- Rest: Convert parameter to array

## Destructuring
- Extract Array element
- Extract Javascript Object property

## Array Functions
- map(arrow_function)
- filter(arrow_function)

