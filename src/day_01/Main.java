package day_01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/day_01/data.txt";
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+"); // Split by spaces
                if (parts.length == 2) {
                    list1.add(Integer.parseInt(parts[0]));
                    list2.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(list1);
        Collections.sort(list2);

        int ans = 0;
        for(int i=0; i<list1.size(); i++){
            ans += Math.abs(list1.get(i) - list2.get(i));
        }

        System.out.println(ans);

        int sim = 0;
        for (int i = 0; i < list1.size(); i++) {
            sim += list1.get(i) * count(list2, list1.get(i));
        }

        System.out.println(sim);
    }

    private static int count(List<Integer> list, int num){
        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == num){
                ans++;
            }
        }
        return ans;
    }
}


