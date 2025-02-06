package day_03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("src/day_03/data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(content);

        // Pattern to match both control instructions and mul operations
        Pattern pattern = Pattern.compile("(do\\(\\)|don't\\(\\))|mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(content);

        boolean mulEnabled = true;  // Start with mul enabled
        int totalSum = 0;

        while (matcher.find()) {
            String match = matcher.group();

            if (match.equals("do()")) {
                mulEnabled = true;
                System.out.println("Enabling mul operations");
            }
            else if (match.equals("don't()")) {
                mulEnabled = false;
                System.out.println("Disabling mul operations");
            }
            else if (mulEnabled) {
                int num1 = Integer.parseInt(matcher.group(2));
                int num2 = Integer.parseInt(matcher.group(3));
                int product = num1 * num2;
                totalSum += product;
                System.out.println("Found: " + match + " => " + num1 + " * " + num2 + " = " + product);
            }
            else {
                System.out.println("Skipping disabled multiplication: " + match);
            }
        }
        System.out.println("Total Sum: " + totalSum);
    }
}
