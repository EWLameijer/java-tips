package org.example.localizationdemo;

import org.example.utils.AskFor;

import static org.example.utils.Internationalization.LOCALE;

public class LocalizationDemo {
    public static void main(String[] args) {
        double height = AskFor.fractionalNumber("Please give the height of a triangle: ");
        double base = AskFor.fractionalNumber("Please give the length of the triangle's base: ");
        System.out.printf(LOCALE, "The area of the triangle is %f.\n", 0.5 * height * base);
    }
}
