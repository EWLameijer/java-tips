package org.example.utils;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;

import static org.example.utils.Internationalization.LOCALE;

public class AskFor {
    public static double fractionalNumber(String question) {
        Scanner in = new Scanner(System.in);
        System.out.print(question);
        do {
            var answer = in.nextLine();
            try {
                // "1,2" would be parsed as 12, as "grouping separators" are skipped by default.
                char groupingSeparator = DecimalFormatSymbols.getInstance(LOCALE).getGroupingSeparator();
                var groupingSeparatorPosition = answer.indexOf(groupingSeparator);
                if (groupingSeparatorPosition != -1)
                    throw new ParseException("No '" + groupingSeparator + "' allowed!", groupingSeparatorPosition);
                return NumberFormat.getNumberInstance(LOCALE).parse(answer).doubleValue();

                // DecimalFormat unfortunately has the same shortcoming, also needs the groupingseparator logic...
                // var value = new DecimalFormat("", new DecimalFormatSymbols(Locale.ITALIAN)).parse(answer).doubleValue();
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a valid number");
            }
        } while (true);
    }
}
