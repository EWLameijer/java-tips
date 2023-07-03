package org.example;

public class Program001b {
    public static void main(String[] args) {
        Settings settings = new Settings(5);
        System.out.println(settings.getResult());
    }
}

class Settings {
    final private int seed;

    final private int result;

    public Settings(int seed) {
        this.seed = seed;
        result = calculate();
    }

    private int calculate() {
        return seed * seed;
    }

    public int getResult() {
        return result;
    }
}

/*
Comments

Program001a works and works well, but is relatively unusual Java code, so it may be harder to understand and maintain
for experienced Java developers.

And to be fair, it does have some 'code smells'. Having a non-final static variable means "mutable shared state", which
is notoriously hard to understand, test and debug (let alone be extended to support parallelism).

Public fields (especially public non-final fields) are generally a bad idea, as any method in any class can modify them,
possibly making the object have an invalid state. Also, you cannot change their name or type without breaking all
calling code, so usually you try to make as few fields (and methods!) public as possible.

While inheritance is popular in Java learning books (class Cat extends Animal...) and it definitely does have its uses
in production code, it is in my experience not used that widely, and often frowned upon ("favor composition over
inheritance" is a famous programming motto). Using inheritance can be defensible if data fields are common between
classes (like an Employee having a first name and a last name, like a Person), but using inheritance of static fields
to pass values between parents and children seems rather opaque/unclear: from "new Child001a();" (which is weird,
it is uncommon just to create an object on the heap and not assign it to a reference, as the object could be
garbage collected immediately afterwards. Nowhere in the statement new Child001a() is it clear that the result field
is given a value based on the seed field! You have to dig into the Child001a constructor to see that it actually calls
another method.

Which is also odd, as a constructor is supposed to give initial values to an object, not to run all kinds of other
routines; "single responsibility principle": a method should only do one thing. And "principle of least surprise":
experienced developers expect getters to return values and not change the content of the object, setters to return void
and change the content of the object, and constructors to set the fields of an object to their appropriate values.

That a constructor calls a method to set a field of the superclass to a certain value is rather unusual; setting a value
in a superclass would usually be the responsibility of the superclass itself, in particular the superclass constructor.


The new version seems (at least to me) better in at least some regards

1) the Settings class now only has private final (non-static) fields, which are the kinds of fields that are ideal
for maintainable software.

2) the result is returned via a getter, which is the expected Java idiom for returning values from a class.

3) Settings get assigned its seed value via the constructor, not via setting a public field. Setting values in a
constructor is the more normal idiom, and is also preferable since it makes it possible to check whether the value
makes sense (what if only values greater than 10 would be allowed?)

4) Both fields are now final, so protected against inadvertent modification and/or corruption by methods outside the
Settings class.

5) You cannot easily get the order wrong anymore; with a static class you can call run before you seed, or get the
final value before you call 'run'. With a normal object, the compiler makes it impossible to make a mistake in the order
of actions (for example, when you are refactoring code)

 */


