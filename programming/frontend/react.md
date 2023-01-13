## Think in React  
https://reactjs.org/docs/thinking-in-react.html  
- Identify components (Single Responsibility)
- Create static components (use render() function only)
- Handle dynamic content
  - Identify data with props (for content display) or state (for interactivity)
  - Is data a prop or state (pass in, computed, can change)
  - Which component own the state (highest level component that needs the state)
  - Use callback to update the state in upper level
  - Handle page state

## Concept
- Benefit
  - Reactive  
    for front-end UI
  - Declarative  
    React is to build UI in declarative way instead of using JS to create all the elements.
  - Components  
    React is well-organized  into components.
- React application is single page application, thus only one HTML is defined to provide the DOM.
- React manages a virtual DOM and populates to real HTML DOM.
  - useRef to get the reference to actual DOM element.
- React application consists of Components.
  - Component is a js function return JSX code (const Component = ()=> return ....).
  - JSX code is translated in the js code (CreateReactDOM(...)), and that is the reason that only one JSX element can be returned. Use Fragment to wrapper multiple elements.
  - Can use Portal to mount components in another HTML element.
- State is used to manage the component state.
- Properties is used to pass the state to children elements.

## JSX
- can embed JS expression or variable in {}
- built-in JSX component with attributes
- custom JSX component

## React
- npm is the nodejs equvilant of maven

## Main Features (Render UI & React to user input)
- Evaluate & Render JSX
- Manage State & Props
- React to user Events & Input
- Re-evalute Components upon State & Prop changes

## Advanced Features (Side Effects, non-UI events or functions)
- Http Requests
- Local Storage
- Side effect of user input data
- useEffect
  - run when the component first time mount when dependency is empty array [].
  - if no dependency defined, it runs for subsequent re-render cycle.
  - if dependency defined, it runs for subsequent re-render cycle and when dependency value changed.
  - clear up function (defined in useEffect return) is called before each time useEffect is called.

## State
- use separate State for each input.

## useReducer
- Mulitple place to update the state, e.g. a list with SET, ADD, DELETE actions
- Multiple connected states.

## useContext
- For those state changed in-frequently, e.g. Authentication status

## useEffect
- Used for some functions that running on conditions.
- e.g. To retrieve data

## useCallback & useMemo
- To improve performance by reducing ReactDOM re-rendering
- For simple component, the performance might not be worthy as userCallback and useMemo also incur some checking logic.

## Custom Hook
- For common code which contains useXXX functions.

## Form
- Form State
  - useRef: if only interested in the value when submission
  - useState: if wants to set the value (can be manipulated by using Ref but not recommended as the DOM should be managed by React) or get value for each keystoke
- Form Validation
  - When form submission  
    Check input valid state.  
  - When lost focus
    Set touched state to true.  
  - At each key stoke  
    Set touched state to true.  
- Error Feedback
  - useState (e.g. inputIsTouched) to store touched state
  - use const, which is the validation result of input value, to store valid state
  - Above two can be implmented as hook
  - show error element
  - use error CSS class on FormControl element
- 3-party Library
  - Formik

## Routing
- Dynamic page
- Index.js: BrowserRouter/App
- App: Layout/Route/Switch/
- Layout: Navigation Component/main                                     
- Navigation Component: Link/NavLink -> update the browser URL and reload the App
- useHistory (push/replace) to programtically

## CSS
- one css for one component
- use different classname which includes component name
- use css module, React can transfer css module to component css

## Wrapper Component
- props.children