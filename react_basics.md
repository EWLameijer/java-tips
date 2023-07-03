# React basics 

## Creating a React app 

There is a "create React app", but nowawadays (July 2023) I would recommend using Vite: `npm create vite@latest`, then choose React, and JavaScript (or, if you are an advanced React user and confident about your TypeScript abilities, TypeScript). A project is then created and built, Vite gives instructions how to run it. 

The next stage is deleting all unnecessary boilerplate code in the app and stylesheets, and get started with your own app!

## How React works
There are many fascinating things going on under React's hood, but most of those things are more if you strive for great mastery, not to get started.

The core elements of React are a HTML file that looks somewhat like the below:
```
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>My title</title>
  </head>
  <body>
    <div id="root"></div>
    <script type="module" src="/src/main.jsx"></script>
  </body>
</html> 
```

Pay special attention to two elements here: the div with id="root", and the included main.jsx script.

Looking at main.jsx, we'll see something like 
``` 
import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
```

This code means that the JavaScript code fills the "root" div with an `App` element (the StrictMode are utility stuff, which gives checks and warnings in development mode https://www.knowledgehut.com/blog/web-development/react-strict-mode; it is not placed in the HTML of the final webpage). It just causes things like sending all requests to APIs twice, so if you see that, it's not a bug in your program; it's React's StrictMode.

Why JSX? Because the HTML-like code in render is not really HTML, it is "JavaScript XML", actually it is parsed to JavaScript code that modifies the DOM. In the past, some JavaScript compilers only parsed JSX correctly if they saw that the file had an .jsx extension. Nowadays, almost all tools parse JSX correctly even if it is in a .js file, however, Vite still depends on it to work properly.

Let's now look at the App!

```
import './App.css'

import { useEffect, useState } from 'react';

const App = () => {
  const [items, setItems] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/api/v1/items`)
      .then(response => response.json())
      .then(actualData => setItems(actualData))
      .catch(err => console.log(`An error has occurred: ${err.message}.`))
  }, []);

  return <ol>
    {items.map(item => <li key={item.id}>{item.name}</li>)}
  </ol>
}

export default App
```

While only 15 lines of code, there is a lot to take in for a novice React developer.

`import './App.css'` seems simple enough: just import the stylesheet 

`import { useEffect, useState } from 'react';` For this, you need to know a bit about modern JavaScript imports and exports, but a good guess would be that we need the `useEffect` and `useState` functions from the react library.

`const App = () => {` This seems a modern JavaScript arrow function, which likely in some way corresponds to the `<App />` we saw in main.jsx. But it is unconventional to start a JavaScript function with a capital letter; looking historically, React components (this is a React component, a loose equivalent to a HTML component) were classes, and hence started with capital letters. Nowaways, most new React components are functions, but I guess they liked the convention as it makes it easier to distinguish components from regular JavaScript functions.

`const [items, setItems] = useState([]);` If you know something about destructuring declarations, you can deduce that useState is a function, 