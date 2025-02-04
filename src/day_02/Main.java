package day_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String filePath = "src/day_02/data.txt";
        int count = 0;
        List<List<Integer>> allLevels = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                List<Integer> currentList = new ArrayList<>();
                for (String token : tokens) {
                    currentList.add(Integer.parseInt(token));
                }
                allLevels.add(currentList);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(List<Integer> l: allLevels){
            if(isValid(l)){
                count++;
            }
        }

        System.out.println(count);
    }

    private static boolean isValid(List<Integer> list) {

        if (checkValid(list)) {
            return true;
        }

        for (int i = 0; i < list.size(); i++) {
            List<Integer> sublist = new ArrayList<>(list);
            sublist.remove(i);

            if (checkValid(sublist)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkValid(List<Integer> sublist) {
        boolean isIncreasing = false;

        if (sublist.get(1) > sublist.get(0)) {
            isIncreasing = true;
        }

        for (int i = 1; i < sublist.size(); i++) {
            int diff = sublist.get(i) - sublist.get(i - 1);

            if (isIncreasing) {
                if (!(diff >= 1 && diff <= 3)) {
                    return false;
                }
            } else {
                if (!(diff <= -1 && diff >= -3)) {
                    return false;
                }
            }
        }

        return true;
    }

}



