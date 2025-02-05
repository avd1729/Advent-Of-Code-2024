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

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(content.toString());

        int totalSum = 0;

        while (matcher.find()) {
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));
            int product = num1 * num2;
            totalSum += product;
            System.out.println("Found: " + matcher.group() + " => " + num1 + " * " + num2 + " = " + product);
        }

        System.out.println("Total Sum: " + totalSum);
    }
}
