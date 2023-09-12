package org.example.scannerdemo;

import java.util.Scanner;

// or: why you should not close scanners that scan System.in... (scanners that scan FILES are another matter...,
// though it is likely fine to close their contents with a try-with-resources instead of the Scanner itself)
// https://www.quora.com/Why-do-we-need-to-close-the-scanner-class-in-java
// https://stackoverflow.com/questions/12519335/resource-leak-in-is-never-closed
// http://web.eecs.utk.edu/~bvanderz/teaching/cs365Sp17/examples/datacheck.html#:~:text=If%20you%20do%20not%20close,as%20you%20exhaust%20its%20input.
// : so apparently, even at sites of universities, it is possible to have erroneous content...
// https://www.reddit.com/r/learnjava/comments/ldas7s/to_close_the_scanner_or_not_close_the_scanner/
// I would say taftster in the last link gives the best answer.

public class ScannerDemo {
    public static void main(String[] args) {
        String firstWord = scanFirst();
        String secondWord = scanAgain();
        System.out.println(firstWord + secondWord);
    }

    private static String scanFirst() {
        Scanner in = new Scanner(System.in);
        System.out.print("Give a first word: ");
        String firstWord = in.nextLine();
        in.close(); // this should be removed for reasons clear if you do NOT remove it
        return firstWord;
    }

    private static String scanAgain() {
        Scanner in = new Scanner(System.in);
        System.out.print("Give the second word: ");
        return in.nextLine();
    }
}
