# Java tips
Some tips about (Java) programming

## Naming in general
Naming is one of the hardest activities in programming. While it can be easy (Person person), there are enough cases in which it is hard to think of a good name (happyCustomers? numberOfHappyCustomers? secondBeforeLastFibonacciNumber?). There are some general guidelines, though, from Peter Hilton: (https://www.youtube.com/watch?v=rpGJQz3KFKE, https://hilton.org.uk/blog/science-on-naming)
  - Only use dictionary words (acc -> account) [Peter makes an exception for id]
  - Use 1-4 words (acc -> savingsAccount)
  - Be precise and meaningful (data -> accountBalance)
  - Use a larger vocabulary (receiver -> recipient)
  - Use domain terms (user -> accountHolder)
  - Omit type information (dateOpened -> opened)
  - Use collective nouns (accEntries -> statement)

Steve McConnell, in Code Complete 2, even has an entire chapter on variable naming (Chapter 11), which is great if you want to know all the details. McConnall actually also mentions research, which suggests that debugging was easiest if variable names averaged 10-16 characters (but 8-20 was nearly as easy). 

## Creating classes 

### Naming classes

Classes should usually be named after a (non-plural) noun, or combination of nouns. Naming is in UpperCamelCase. Examples:
   - Good: Person, Car, Item, Phone, Employee, CardManager
   - Bad: (not a noun) Blue, Good, Serializable, And. 
	   + Note: an adjective like Serializable can make a good name for an _interface_, though... But an interface is not a class!
   - Bad: (due to plural form) Cars, Persons, CardManagers
   - Bad: (due to presence of article) ACar, ThePerson, AnEmployee 
   

### Naming fields
Like classes, the names of fields should generally be a noun, possibly modified. An exception are boolean values, which often are named with isX... Field names in Java are generally lowerCamelCase (static final fields are MACRO_CASE). In contrast to classes, fields can represent collections of values, so plural forms of nouns are allowable! Some developers use the aX convention (`Person aPerson` instead of `Person person`). If a team has that as style guideline, it's okay; otherwise, I'd avoid it since the extra typing does not seem to add much value to me...
Usually, you avoid repeating the name of the class itself in a method or field, a Fan class would have a setting field instead of a fanSetting field, otherwise you get code like Fan myFan; myFan.getFanSetting()... myFan.getSetting() is shorter and clear enough. And does not give you extra work if you ever want to rename the Fan class to a Ventilator class...

	- Good (non-boolean): person, car, item, numberOfCustomers
	- Good (boolean): isMarried, isStudent; though includeTaxes may also be a  good enough name.
	- Good (plural) List`<Person>` persons; Set`<Student>` students;
	- Bad (not noun-based): blue, good, serializable, and
	- Situational (can be correct or bad based on team coding conventions): aPerson, anEmployee
	- Bad (with definite article): thePerson, theCar, theItem 
	- Not advised: class name in field name. like personName (in Person), carColor (in Car), or itemPrice (in Item)
	
### Naming methods

For methods, basically the same rules go as for fields. Of course, there are conventions for certain methods (the famous getters and setters), which are _usually_ in the form getX() and setX(X x). Note that getters and setters have one exception: namely for booleans. For a boolean isMarried, you would not generate getIsMarried and setIsMarried, but isMarried() and setMarried(boolean married)

### Types of fields and initializations
With few exceptions, all fields should be private. However, there are modifiers like final and static, and circumstances that determine whether you should initialize a field at its declaration, or in the constructor, or later
 - 'ideal fields': private final X, like private final firstName; If at all possible, try to make fields immutable, since that can prevent bugs. Having a private final dateOfBirth is much preferable over a private int age, which must be updated regularly. Usually, you assign private final fields their value in the constructor.
 - regular fields: private but not final. In most cases, you need to initialize regular fields in the constructor; but sometimes there is a default value that makes sense. If you want to register a newborn at city hall, it does not seem to make sense to ask whether the baby is married, and in by far most cases it is still alive; so setting isMarried to false and dateOfDeath to null (or Optional.empty()) at the place of declaration is far better than wasting extra lines - and/or extra parameters- in the constructor!
 - static non-final fields: I won't say much about those, as you seldom use those; shared mutable state (as static non-final fields are) are especially bug-prone, and their value is seldom worth their cost!
 - static final fields (which should be in MACRO_CASE). There are actually two types of static final fields: some fields are defined so other classes can have access to them (Math.PI) - those should be public. Sometimes you however need a constant (like MAX_LINE_LENGTH) that is used by multiple methods in the class; then a private final static is called for.
	
