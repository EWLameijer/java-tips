# JavaScript for Java developers

## Contents 

- terms to know 


### Differences to watch out for 
1) Wherever you need to use a boolean in Java (if-statements, ternary operators, do and while loops, for-loops) you can use _any_ value in JavaScript, because JavaScript can interpret any value as 'truthy' or 'falsy'. There are 6 (or 7, depending on your count) falsy values 0, "", '', undefined, false, NaN, and null (see https://www.educative.io/answers/what-are-falsy-values-and-truthy-in-javascript). All other values are 'truthy'.

2) One odd effect of truthy and falsy values is that || and && don't return booleans either! || returns the first truthy value (or the second value if no value is truthy), && returns the first falsy value (or the second value if no value is falsy). While this may seem rather convoluted, for booleans it displays exactly the same behavior as && and || in Java, including short-circuiting! Once could say that JavaScript's && and || are a generalization of Java's && and ||, though due to their versatility understanding them can be tricky for beginners. 

3) "" vs ''. In JavaScript, " and ' can be used interchangeably to make string literals, though a string must always start and end with the same character ("hello' won't work!). Since JavaScript, in contrast to Java, does not have a separate char type, only strings, This freedom (like in Python) makes it easier to make string literals that include double quotes, like '"I like apples," she said...' without requiring escape characters like in Java ("\"I like apples,\" she said..."). Of course, this can lead to inconsistent-looking code like (https://github.com/microsoft/Web-Dev-For-Beginners/blob/main/6-space-game/2-drawing-to-canvas/solution/app.js) and many discussions about whether as a team one should use single or double quotes. Technically, using single quotes can be desirable in situations where you use HTML in JavaScript, because HTML uses double quotes. JSON, however, only uses double quotes. Douglas Crockford, one of the best-known JavaScript thinkers recommends double quotes (https://www.youtube.com/watch?v=MBWAP_8zxaM&t=608s), so I suppose that makes most sense (especially since it is consistent with Java), unless you have a linter or team with different default settings.

4. In JavaScript, beware of (and generally avoid) != and ==, as both of them try to convert/coerce the values on both sides into the same type. However, the type coercion rules in JavaScript are quite complex and even seem inconsistent (see https://www.youtube.com/watch?v=vcFBwt1nu2U around 21:35) 0 == '0' is true, 0 == '' is true, but '' == '0' is false! Most normal people would not want to take the time and trouble to remember all those rules or take the time to check code that relies on such subtle gotchas-so please use === and !==! Your code may be less 'clever' and possibly a tad longer, but it will be far more readable and understandable to people, including to you in a few month's time.

5. Semicolons. In JavaScript, semicolons are optional, and some developers omit them (extra typing, visual clutter). However, the JavaScript engine can have some unusual interpretations of code when semicolons are missing (in contrast to (compiled) languages like Kotlin or Swift or Go, which work fine without semicolons). See for example https://www.freecodecamp.org/news/lets-talk-about-semicolons-in-javascript-f1fe08ab4e53/ for some "fun examples" of code that looks rather normal can give weird results due to the peculiarities of JavaScript semicolon insertion (because their translations work like expected in languages like Kotlin). In short, while semicolon inference is a great idea that is used by multiple newer languages, the JavaScript implementation of it rather sucks, so, while adding semicolons everywhere can be a bit annoying, it is still less annoying than hunting for elusive JavaScript bugs if you don't use semicolons. So please use semicolons in JavaScipt, even if they are 'optional'.


### Printing out/interacting with user 
Java:
- input 
  - Scanner in = new Scanner(System.in); var input = in.next();
- output 
  - System.out.println("hallo");
  - System.out.print("hallo");
  - System.out.printf("Hallo, ik heet %s.", name);
  
JavaScript 
- input 
	+ (via browser-window) const input = prompt("Please give a value: ", 3); (both arguments are optional)
	+ (via browser-window) const input = confirm("Do you wish to continue?");
-output
	+ (via browser window) alert("Hello world!");
	+ (via browser console) console.log("Hello world!"); (note that console has many different output methods, see https://developer.mozilla.org/en-US/docs/Web/API/console)
	

### String interpolation 
In Java, if you want to mix a String with the values of variables, you have two choices
1. Mix literal text and variables/values using String concatenation (+)
	"Hello world, I am " + myName + "!";
2. Use String formatting 
	String.format("Hello world, I am %s!", myName);
	
In JavaScript, while you can also use string concatenation, there is also an option to use string interpolation; you need to start and end the string with backticks (`), and use ${} around variable names 
```
   `Hello world, I am ${myName}!`
```
	
### Declaring variables/constants
JavaScript has four(!) ways to declare variables.
- The scripting way: simply `a = 3`; Since this way causes lots of bugs (ask any Python developer), this method is nowadays rarely seen in JavaScript; most developers disable it by adding "use strict" to the top of their file, or using so-called linters.
- var. `var a = 3;` Var was introduced to solve the problems of the 'raw' initialisation. However, var still conserves the rather weird original JavaScript scoping rules (a variable is valid in the entire function where it is defined, even outside its block, and even before it has been declared), which is not consistent with how most other programming languages work, and is still bug-prone, be it less bug-prone than the 'scripting' method. So while you may still see 'var' in lots of JavaScript code, new code should use other ways to declare a variable, namely let or const
- const is the JavaScript equivalent of "final var" in Java. It is the preferred way of declaring any variable, as you can avoid many bugs by making variables immutable. However, it of course cannot be applied to ALL variables  (you may still need to use loop indices or running totals). In any case, const is block-scoped, so works intuitively. Note: const has the same limitations as Java's final; if you have `const arr=[1,2,4]` you can always say `arr[2]=3;`, and that works! The only way to prevent changing the contents of an object type is to call Object.freeze(obj); (Object.unfreeze(obj) unfreezes it). Anyway, freezing can be nice for extra security, but due to the extra verbosity I'm not sure if it is used that much in JavaScript. 
- let: also block-scoped (like const, unlike var and 'scripting mode'): use let for variables of which the values must change. A useful way is to initially declare all variables as const, then look for runtime errors claiming a const changes, remove the bugs you find thereby, and then change the const for the (few) variables that really MUST change into let. And really, those cases are relatively rare. In a random one of my own code examples, I turned out to have 10 consts and 2 lets, of which 1 let should actually have been a const, so 11 consts vs 1 let. So variables actually only rarely need to be really variable, perhaps 1 in 10 times!

### Datatypes
In Java there are eight primitive types and a nearly infinite (and extensible) amount of reference types (classes/objects). Java-developers (can) work with integral primitives like byte, short, int and long, the decimal primitive types float and double, the char type for characters and the boolean type for booleans. Next to that, newer versions of Java have over 4000 classes and interfaces, and as a Java developer you almost always create one or more new classes whenever you create a new Java program.

The datatypes of JavaScript are far fewer, and possibly weirder.
JavaScript has only three 'primitive' types: boolean, number (which seems the same as Java's double), and string. So no type for integer numbers, nor for separate characters.
Note that string being a primitive in JavaScript means you can use ==, !=, <=  and >/>= intuitively on strings, which is definitely nice!
Interestingly, despite being a primitive, you can use methods on a string, which in turn looks like Java. `"hello".toUpperCase()` works! This is because if you apply a method to a string, JavaScript secretly converts the string primitive to a String object, which DOES have methods.
Then JavaScript has a type "undefined" which has only one possible value, undefined. Sounds weird? This is a concept well-known from functional programming languages, where it is more often known as "Unit".

And then there are JavaScript objects. One could say that JavaScript has 2 (or 4!) kinds of objects: null, 'regular' objects, arrays and functions. However, all types of objects (except for null) have the same basic structure. Objects are comparable with a Java-style Map<String, Object>: they are maps of (string) values to JavaScript-types, whether those are boolean, numbers, strings, undefined, or objects themselves. Objects are generally defined as `const o = {a: 12, b: true, c: "hello"};` their members can be accessed using either the "dot notation": `o.a` (which gives 12) or the square brackets notation: `o["a"]`, which seems cumbersome but allows one to use numbers or variables to indicate the key.

Note that there is also a shorthand to add a variable to an object. If you have `const pet = "Golden Retriever";`

You can create an object like `const person = {name: "Bill Gates", pet };`, which is the same as `const person = {name: "Bill Gates", pet: "Golden Retriever"};`

Objects can always be modified after creation, you can always add, change, or delete properties:

```
const bill = {name: "Bill Gates", founded: "Microsoft" };
	// result: {name: 'Bill Gates', founded: 'Microsoft'};
	
bill.hobby = "reading"; // add property / key/value pair
	// result: {name: 'Bill Gates', founded: 'Microsoft', hobby: 'reading'}
	
bill.founded = "McDonalds"; // modify property 
	// result: {name: 'Bill Gates', founded: 'McDonalds', hobby: 'reading'}

delete bill.name
	// result: {founded: 'McDonalds', hobby: 'reading'}
```

Arrays are ALSO objects (so basically maps), be it that you construct arrays with [] instead of {}. const a = [2,3,4]; is approximately the same as const a = {0:2, 1:3, 2:4, length: 3}, as arrays basically are objects with strings (representing numbers) as keys. Though arrays also have some useful extra methods. Arrays ARE objects, so you can easily add `a.hello = 17;` or even `a[1.5]=3.8;`. And also note that arrays, like objects, can have vastly different data types, in contrast to Java arrays. A JavaScript array can be like `const diverse = [1, true, "hello", {name: "Bill"}, [4,3,2]];` Of course, having different data types in an array can make code hard to debug (I remember one particular debugging session with PHP), so it is not _advisable_ to store different data types in an array, but it is definitely possible in JavaScript!

Functions are their own type ('function'), but functions are basically specialized objects; if you have a function like `const hello = () => console.log("Hello everyone!");` you can easily add `hello.text = 2;`. More useful is perhaps that since functions are objects, you can pass functions as values and therefore as arguments, and can return functions from other functions.

`null` is also an object. But trying to use a field or call a method on null throws an error, similar to Java throwing a NullPointerException.

So JavaScript has basically 8 types: 4 primitive (number, boolean, string and undefined) and 4 objects (object, array, function, null). Though I guess it would be more correct to say that there are 7 primitive types, as null is a primitive type despite typeof(null) returning 'object', and BigInt (a number followed by an n) and symbol are also primitive types, even though those are to my knowledge not widely used.

### JavaScript classes
JavaScript traditionally did not have classes in the sense of Java or C++, it had objects, and objects based on other objects with fields and methods added, changed or deleted. Some people found that very elegant; however, it seems a majority of programmers found it confusing, complicated and error-prone, so modern JavaScript has "classes" that at least superficially resemble the classes in Java, and are approximately defined and used like Java classes, even if they are actually special JavaScript functions that create objects.

```
class Person {
	// constructor is always called constructor, instead of the name 
	// of the class in Java. Is actually quite clear and handy for renaming,
	// even if an inexperienced programmer may not understand why he/she 
	// can call person.greet() but not person.constructor("Frank")
	
	// another oddity is that while this looks like Java, the "this" is 
	// obligatory for all fields in all cases; whereas in Java you can 
	// be creative with constructor arguments (public Person(String aName) 
	// { name = aName; }, the JavaScript "translation" constructor(aName) 
	// {name = aName} would not have any effect, as name would then not 
	// be made a field, just a local variable only known in the constructor
	// 'function'
	
	// finally, notice that in JavaScript there is no "public" modifier
	// needed (and no return type). Private methods and fields are 
	// prefixed with # (so #secretName) 
	
	// classes can also have static members, but those can only be
	// accessed via the class name, not the object name 
	// (so via Point.displayName instead of via p.displayName),
	// which is different from Java
    constructor(name) { this.name = name; }
    
    // fields are more like properties (key/values) of JavaScript objects
    // than like variables, so you can't use let or const. Which is a shame 
    // in the case of const; there are (rather complicated workarounds- 
    // like https://stackoverflow.com/questions/10843572/how-to-create-javascript-constants-as-properties-of-objects-using-const-keyword 
    // but it's a far cry from final in Java.
    species = "human";
    
    // a method. If you call it from within this class, prefix it
    // with "this.", so this.greet();
    greet() { console.log(`Hi! I'm a ${this.species} named ${this.name}!`); }
    
    // a getter. Allows one to write console.log(henk.species) 
    // instead of the Java-ish way of console.log(henk.getSpecies())
    // Note that you may not NEED getters, since you can easily use 
    // Person p = new Person("Pete"); console.log(p.name);
    // Getters only have use if you want to return a value in a different 
    // format (like immutable) or want to return the value of a private
    // variable.
    get species() {
    	return this.species;
    }
    
    // a setter. Allows one to write henk.species = "horse" 
    // instead of the Java-ish way of henk.setSpecies("horse")
    // Like getters, setters are optional in JavaScript as you 
    // can just call henk.name = "John", as long as name is not 
    // a private field. Setters are mainly useful if you want 
    // to do some check on whether a new value is valid (for 
    // example, if it is a string of more than 1 letter)
    set species(newSpecies) {
    	this.species = newSpecies;
    }
}
```

I repeat: JavaScript classes are actually functions that create JavaScript objects, and you can always change objects later.

```
const p = new Person("John");
p.greet = () => "I'm a little teapot!";
p.greet(); // will log "I'm a little teapot!" instead of the normal greeting
```

So, while in Java you can usually trust objects to have methods and fields do as you expect from their class, in JavaScript, an object can do or contain anything; it's quite telling that typeof(p) is 'object', not 'Person'!

Note that similar to Java, you can inherit from classes by using the extends keyword, and extending a class means that you likely need to call the super() constructor as first statement in your constructor.

### Declaring functions and methods

Important in JavaScript vs Java
1) function parameters do NOT have types
2) functions do NOT have return values 
3) if you can functions with too many or too few arguments, Java won't compile the code. JavaScript will just discard the last arguments (if there are too many arguments) or set the value of parameters to `undefined` (if the user passes too few arguments)
4) since functions are objects too, it is easy to pass them as parameters or return a function from another function
5) varargs (a variable number of arguments) works like in Java: you make something like function sum(...numbers) { /*... */ }, and numbers will be an array containing all the parameters. 
6) if you pass an object to a function, and only need part of its values, you can 'decompose' the object in the parameter list 

```
const p = {"name": "Clovis", occupation: "king" };

function greet({name}) {
	console.log(`Hi ${name}!`);
}

greet(p); // outputs "Hi Clovis!"


```
7) if a function takes multiple arguments, and you have those arguments in an array, you can use ... again to unpack the arguments (which is not the same as ... in the parameter list!)
```
const winners = ["Betty", "Oscar", "Thomas", "Melany"];

function firstThree(first, second, third) {
	console.log(`The gold medal goes to ${first}!`);
	console.log(`The silver medal goes to ${second},`);
	console.log(`and the bronze medal goes to ${third}!`);
} 

firstThree(winners); /* will print 
The gold medal goes to Betty,Oscar,Thomas,Melany!
The silver medal goes to undefined,
and the bronze medal goes to undefined!
*/

firstThree(...winners); /* will print 
The gold medal goes to Betty!
The silver medal goes to Oscar,
and the bronze medal goes to Thomas!
```
8) Functions can have default arguments
```
function greet(name = "Fred") {​
    console.log(`Hello, ${name}!");​
}

greet("Annika"); // Hello, Annika!​
greet(); // Hello, Fred!
```

#### Global/'loose' functions 
There are three ways to declare global functions in JavaScript:
```
function greet1() {
	console.log("Hello there!(1)");
}

const greet2 = function() {
	console.log("Hello there!(2)");
}

const greet3 = () => console.log("Hello there!(3)");
```

The second and third way, due to using const, avoid possible errors due to redeclaring a function with the same name. Though a tool like ESLint, which can be automatically applied by VS Code, will also find that problem - but you must then not forget to activate it!

The third way is of course shorter than the second way, and is therefore the way I usually prefer.

#### Functions in objects 

Functions can be added to objects, just like regular values

```
const q = {r: function() { console.log("Calling R!"); }};
q.r(); // displays "Calling R!"
```

However, there is also a more modern and shorter equivalent: 
```
const r = {s() { console.log("Calling S!"); }}
r.s(); // displays "Calling S!"
```

of course, getting external functions into your object works the same as it did for `pet` earlier:

```
const t = () => "Calling T!";
const u = {t};
u.t(); // displays "Calling T!"
```

#### Functions in classes 
Functions in classes were discussed earlier.

### Analyzing and copying objects and arrays 

-Getting the keys of an object (which, in case of an array, are the indices): Object.keys(obj), which returns an array of the keys.
```
const arr = [4, 5, 6];
const person = { name: "Hanja", hobby: "hiphop" };
Object.keys(arr); // returns ['0', '1', '2'] // remember: indices are really strings, not numbers!
Object.keys(person); // returns ['name', 'hobby']
```

-Getting the values of an object (which, in case of an array, are the regular values). Object.values(obj), which returns an array of the values 
```
const arr = [4, 5, 6];
const person = { name: "Hanja", hobby: "hiphop" };
Object.values(arr); // returns [4, 5, 6] // the values of this array are numbers, not strings!
Object.values(person); // returns ['Hanja', 'hiphop']
```

-Getting key/value pairs of an object! Object.entries(obj), which returns an array of 2-member arrays 
```
const arr = [4, 5, 6];
const person = { name: "Hanja", hobby: "hiphop" };
Object.entries(arr); // returns [['0', 4], ['1', 5], ['2', 6]]
Object.entries(person); // returns [['name', 'Hanja'], ['hobby', 'hiphop']]
```

-Copying an array: since `const a1 = [1,2,3]; const a2 = a1;` simply makes a2 a pseudonym for a1, not a true copy (similar as what would happen in Java), JavaScript has a modern notation for array-copying, new = [...old]
```
const a1 = [1, 2, 3];
const a2 = a1;
a2.push(-5);
console.log(a1);  // displays [1, 2, 3, -5]. Not what you generally want!

const b1 = [1, 2, 3];
const b2 = [...b1];
b2.push(-5);
console.log(b1);  // displays [1, 2, 3], the desired result! b2 is of course [1,2,3,-5]
```

-Copying an object: copying an object works nearly the same as copying an array (as arrays are objects, after all). You just use {} instead of [], so new = {...old}
```
const p1 = {name: "Brenda", hobby: "beet-growing" }
const p2 = p1; 
p2.hobby = "beagle-breeding";
console.log(p1); // outputs {name: 'Brenda', hobby: 'beagle-breeding'}. NOT what you want!

const q1 = {name: "Brenda", hobby: "beet-growing" }
const q2 = {...q1}; 
q2.hobby = "beagle-raising";
console.log(q1); // outputs {name: 'Brenda', hobby: 'beet-growing'}. q2 is of course the dog-breeding Brenda...
```

-Making a modified copy of an object: often you want all values of the old object, but change a value, and/or add a value. This can also be done with ..., you simply add a key: value pair (or the variable equivalent)

``` 
const czar = {name: "Ivan", nickname: "the Terrible" };
const isAlive = true;
const czar2 = {...czar, name: "Vladimir", isAlive };
console.log(czar2); // outputs "{name: 'Vladimir', nickname: 'the Terrible', isAlive: true}"
``` 

-Making a modified copy of an array: as arrays only have implicit keys, you can only make a larger array using ..., or concatenate arrays. It is still useful, sometimes, though...
``` 
const myFavoriteNumbers = [3, 7];
const myNewFavoriteNumbers = [...myFavoriteNumbers, 16];
console.log(myNewFavoriteNumbers); // outputs [3, 7, 16]
const yourFavoriteNumbers = [5, 11, 13];
const allOurFavoriteNumbers = [...myNewFavoriteNumbers, ...yourFavoriteNumbers];
console.log(allOurFavoriteNumbers); // outputs [3, 7, 16, 5, 11, 13]
``` 


### Destructuring assignment 
Sometimes you have an array or other object and you want to easily use one of its values without having to repeatedly use obj.key or arr[index]. For example

```
const mostExpensiveCompanies = ['Apple', 'Microsoft', 'Saudi Aramco', 'Google', 'Amazon'];

const discussCompanies1 = listing => {
	console.log(`The most expensive company in the world is ${listing[0]}.
	Can ${listing[1]} ever catch up to the greatness that is ${listing[0]}?
	They will surely try, and knowing ${listing[1]}, they will use all their    
	resources to crush ${listing[0]}, possibly buying up ${listing[2]} to 	
	grow!`);
}

// not only are names like listing[1] repeated, risking typos, the names
// are also less clear than ideal. Let's try it again

const discussCompanies2 = listing => {
	const [mostExpensive, secondMostExpensive, thirdMostExpensive] = listing;
	console.log(`The most expensive company in the world is ${mostExpensive}.
	Can ${secondMostExpensive} ever catch up to the greatness that is ${mostExpensive}?
	They will surely try, and knowing ${secondMostExpensive}, they will use all their    
	resources to crush ${mostExpensive}, possibly buying up ${thirdMostExpensive} to 	
	grow!`);
}

// note that you don't have to match the entire length of the list, you 
// could still find Google as listing[3]. If you want to list the non-top-3, // you'd use const [mostExpensive, secondMostExpensive, thirdMostExpensive, // ...rest], so


const discussCompanies3 = listing => {
	const [mostExpensive, secondMostExpensive, thirdMostExpensive, ...rest] = listing;
	console.log(`The most expensive company in the world is ${mostExpensive}.
	Can ${secondMostExpensive} ever catch up to the greatness that is ${mostExpensive}?
	They will surely try, and knowing ${secondMostExpensive}, they will use all their    
	resources to crush ${mostExpensive}, possibly buying up ${thirdMostExpensive} to 	
	grow! (We just ignore unimportant companies like ${rest}).`);
}
``` 

You can also do destructuring assignment on objects, though that works a little differently since there is not really an official, predictable order in which the keys of an object are listed. You simply have to know the names, but then you can use the name without prefixing it with `theNameOfTheObject.` You can even give elements a different name, and, with ..., create an object that contains the rest of the key/value pairs. Which is, again, optional, you don't have to catch all values of an object in the assigment!

```
const myMenu = { breakfast: "porridge", lunch: "bread", dinner: "salad" };

const {breakfast, dinner: supper, ...rest} = myMenu;
console.log(breakfast); // instead of myMenu.breakfast 
console.log(supper); // will print "salad"
console.log(rest); // will print {lunch: 'bread'}
```


### Repetition
Java: 
 - while-loops 
 - do-loops
 - for-loops 
 - enhanced for-loops
 - recursion
 - lambdas/streams
 
JavaScript:
 - while-loops 
 - do-loops 
 - for-loops
While, do, and for-loops are the same as in Java, but can use any value, not just booleans, in their conditions. Those values are of course evaluated for 'truthiness'
- enhanced for-loops: look a bit like Java, but there are two different types
```
const dwarves = ["Happy", "Bashful", "Sneezy", "Grumpy", "Sleepy", "Dopey", "Doc"];

for (const dwarf in dwarves) console.log(dwarf); // prints 0,1,2,3,4,5,6!

for (const dwarf of dwarves) console.log(dwarf); // prints Happy, Bashful, Sneezy, Grumpy, Sleepy, Dopey, Doc

const myHouse = {onTop: "roof", onBottom: "floor", inFront: "door"};

for (const side in myHouse) console.log(side); // prints onTop, onBottom, inFront 

for (const part of myHouse) console.log(part); // prints roof, floor, door
``` 

In short, `for in` shows indices/keys, `for of` shows falues (ehm... values)

-recursion works the same in JavaScript as in Java (though the stop conditions can again be truthy or falsy instead of only regular boolean expressions)

-lambas/streams are basically arrays and array methods in JavaScript, the most important methods being map, filter and reduce 

```
const dwarves = ["Happy", "Bashful", "Sneezy", "Grumpy", "Sleepy", "Dopey", "Doc"];

const dDwarves = dwarves.filter(dwarf => dwarf.startsWith("D"));
console.log(dDwarves); // lists ['Dopey', 'Doc']

const bigDwarves = dwarves.map(dwarf => dwarf.toUpperCase());
console.log(bigDwarves); // lists ['HAPPY', 'BASHFUL', 'SNEEZY', 'GRUMPY', 'SLEEPY', 'DOPEY', 'DOC']

const quickDwarves = dwarves.reduce((soFar, current) => soFar+current, "");
console.log(quickDwarves); // outputs "HappyBashfulSneezyGrumpySleepyDopeyDoc"
```

Reduce is the most complex to understand, it takes either one or two arguments, its first argument being a function that takes up to three arguments (the accumulator, the current value, and the current index), that produces the next value for the accumulator. If the second argument for reduce is not given, the first element of the array is taken as the accumulator, and reduce starts the function at the second argument. So 

```
const dwarves = ["Happy", "Bashful", "Sneezy", "Grumpy", "Sleepy", "Dopey", "Doc"];

// using ONE argument for reduce
const separatedDwarves = dwarves.reduce((soFar, current) => soFar + ";" + current);//
console.log(separatedDwarves); //outputs Happy;Bashful;Sneezy;Grumpy;Sleepy;Dopey;Doc , Happy is NOT preceded by a ';'

// using third argument for function passed to reduce (the index) 
const numberedDwarves = dwarves.reduce((soFar, current, currentIndex) => `${soFar} ${currentIndex + 1}. ${current}!`, "");
console.log(numberedDwarves); // outputs " 1. Happy! 2. Bashful! 3. Sneezy! 4. Grumpy! 5. Sleepy! 6. Dopey! 7. Doc!"
```

### Choices
JavaScript's if, ternary operator and switch statement are identical to Java (of course accepting all values to decide on a branch, not just booleans).

There is only no equivalent in JavaScript to Java's newer switch expression, the best you can do (and useful sometimes) is make an object, like 
```
const choices = { yes: "I agree!", no: "You should reconsider...", maybe: "I should explain it to you again..." };

const answer = prompt("Do you agree with proposal 362?");
alert(choices[answer] || "Please answer with 'yes', 'no' or 'maybe'!")
```

 
### Error-handling

try-catch-finally and throw work the same as in Java, though, as you may have expected, in JavaScript you can throw (and catch) any value, not just Exceptions/Throwables. Like most programming languages, JavaScript does not have a "throws".

### Iterators 

Iterators are objects that have a next() method, which produces objects with value and done properties. For example:
```
const a = ['p','r'].values(); // values generates an iterator
a.value; // undefined 
a.done; // undefined 
const b = a.next() // b becomes {value: 'p', done: false}
const c = a.next() // c becomes {value: 'r', done: false}
const d = a.next() // d becomes {value: undefined, done: true}
const e = a.next() // e becomes {value: undefined, done: true}
```

### Importing and exporting 
JavaScript, unlike Java, does not work with packages (as it really wasn't intended at the start to make huge programs).
Nowadays, due to popular demand, JavaScript can work with multi-codefile-projects.

If you have resources in a JavaScript file (variables, classes, methods) that you want other JavaScript to use, you can use the export statement.

```
// great_module.js

class GreatClass {}

const greatFunc = () => console.log("Hello world!");

const greatNumber = 1;

const greatString = "Hello!";

export { greatFunc, greatNumber as greaterNumber, greatString };

export default GreatClass;
```

To be complete, export statements in JavaScript are hugely versatile, meaning that there are many ways to do exactly the same thing :(.

For example, you can use export before a declaration, so you have export const greaterFunc = () => console.log("Hello universe!");

You can also export something as default, or "export *" for reexporting another module...

Why "default"?

Well, let's look at how _importing_ works.

```
import GreatClass from './great_module';
import {greatFunc, greaterNumber as greatestNumber } from './great_module';
import * as gm from './great_module';
```

The (single) default export allows you to import this particular export without using {}. Note that you can give it any name you like, import OtherGreatClass from './great_module' would also work.

Non-default exports need to be surrounded by {}, you can use `as` to give them another name in the importing module.

`import * as ...` imports everything from a module, and allows you to use them using the given prefix. So in this case, the importing code could use `gm.greatString`.


### Promises 

fetch returns a Promise object. There is much to say about Promises (especially if you want to create one yourself manually) https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise, but you just need to know that the "thing" that fetch returns has a .then method that can be chained, it means "as soon as whatever fetch does, finishes, give the result to the function in then". There can also be a .catch in the end to process errors.

```
fetch(`http://localhost:8080/api/v1/items`)
  .then(response => response.json())
  .then(actualData => setItems(actualData))
  .catch(err => console.log(`An error has occurred: ${err.message}.`)
```


### Array methods 
Full overview at https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array 
- adding element to array 
	end: push(firstValue[, secondValue , ...]). Returns new length of array
	start: unshift(firstValue[, secondValue, ...])  Returns new length of array
- removing an element from an array (and returning it)
	end: pop() // returns value removed 
	start: shift() // returns value removed 
- getting sublist of array
	slice([startIndex [,indexAfterEnd]]): returns sublist 
		[1,2,3,4].slice() // [1,2,3,4], same as {...[1,2,3,4]}
		[1,2,3,4].slice(1) // [2,3,4]
		[1,2,3,4].slice(1,3) // [2,3]
-changing part of array (insert, delete, replace): splice 
	splice([startOfDelete, [deleteCount-defaults to all, [...newValues]])
		const arr = ['a', 'b', 'c', 'd'];
		arr.splice(); // returns empty array, arr unmodified 
		arr.splice(1) // returns ['b', 'c', 'd'], arr now is ['a']
		arr.splice(1,1) // returns ['b'], arr is now ['a','c','d'] 
		arr.splice(1,1,5,6,7) // returns ['b'], arr is now ['a', 5, 6, 7, 'c' ,'d']
- concatenating elements to a single string 
	[1,2,3].join('-') // results in "1-2-3"
	
- do all elements in the array obey this predicate?	
	+ [1,2,3].every (number => number % 2 == 0) // returns false 
- does at least one element in the array obey this predicate?
	[1,2,3].some(number => number % 2 == 0) // returns true 
- returns the first/last element in the array obeying a predicate
	[1,2,3].find(number => number % 2 != 0) // returns 1 
	[1,2,3].findLast(number => number % 2 != 0) // returns 3 
	[1,2,3].findIndex(number => number % 2 != 0) // returns 0 
	[1,2,3].findLastIndex(number => number % 2 != 0) // returns 2
	
-is there a specified element?
	[1,2,3,4].includes(2) // returns true
-find the index of a specified element?
	[1,2,3,1].indexOf(1) // returns 0 
	[1,2,3,1].lastIndexOf(1) // returns 3 

Changing order: sort, reverse 
Making a (modified) copy toSorted, toReversed, toSpliced, with(index, newValue), concat (a.concat(b), same as [...a, ...b]) 

Changing content: fill(newValue, [start, [,indexAfterEnd]), returns: the new array, though it ALSO modifies the array itself 
const arr = [1,2,3,4] 
arr.fill(2); // returns [2,2,2,2], arr=[2,2,2,2]
arr.fill(3,1) // returns [2, 3, 3, 3], arr = [2, 3, 3, 3] 
arr.fill(7,2,3) // returns [2, 3, 7, 3], arr = [2, 3, 7, 3]

Getting elements at an index, including negative indices indicating from the end: [1,2,3,'q'].at(-1) // 'q'

keys() and values() and entries() return iterators to keys and values and entries , respectively

Filter/map/reduce: 
- filter 

- flattener: 
	- flat (flatten, so [[1,2],[3,4] => [1,2,3,4])),

- mappers: 
	- map
	- foreach (map, but does not return the values, useful for [1,2,3].foreach(n => console.log(n+ "!")
	- flatMap(maps then flattens, turning array of arrays into a long array),
	
-reducers
	- reduce 
	- reduceRight (reduce from other side of array)


### `this` and binding
Inside a class, or in methods, this refers to the object itself. But it is also possible to make 'normal' functions that use `this` [`this` WON'T work in arrow functions!]. That may seem useless (`this` would then generally refer to the browser window), but you can make `this` point to specific objects.

```
const ralph = { firstName: "Ralph", lastName: "Wiggum" };

const introduce = function() {
	console.log(`Hi, I'm ${this.firstName}!`);
}

introduce(); // shows "Hi, I'm undefined!"

introduce.call(ralph); // displays "Hi, I'm Ralph!"
```

Now if you wished to call the function multiple times for Ralph, using 'call' all the time may be annoying. You could do `ralph.introduce = introduce;`, but that would modify the ralph object, which may not be what you want.

But you CAN make a copy of the function that takes an object as its default this value: 

```
const ralphIntroduce = introduce.bind(ralph);
ralph.introduce(); // Uncaught TypeError: ralph.introduce is not a function
ralphIntroduce(); // displays "Hi, I'm Ralph!"
``` 



























## Terms to know

-const 
-for 
-let
-var