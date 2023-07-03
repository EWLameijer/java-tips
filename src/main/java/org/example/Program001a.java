package org.example;



class Parent001a {
    public static int seed;

    protected static int result;

    public static void run() {
        new Child001a();
    }
}

class Child001a extends Parent001a {

    public Child001a() {
        calculate();
    }

    private void calculate() {
        result = seed * seed;
    }
}

public class Program001a {
    public static void main(String[] args) {
        Parent001a.seed = 5;
        Parent001a.run();
        System.out.println(Parent001a.result);
    }
}