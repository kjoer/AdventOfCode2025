package org.example;

import java.io.*;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        String pfad = "C:\\ude\\git\\CodeOfAdvent\\src\\Day1.txt";
        int count = 0;
        int curr = 50;
        try (BufferedReader br = new BufferedReader(new FileReader(pfad))) {
            String zeile;
            while ((zeile = br.readLine()) != null) {
                char c = zeile.charAt(0);
                int rem = Integer.parseInt(zeile.substring(1));
                rem = rem % 100;
                if (c == 'L') {
                    curr -= rem;
                    if (curr < 0) curr = 100 + curr;
                } else {
                    curr = curr + rem;
                    curr = curr % 100;
                }
                if (curr == 0) count++;
            }
        } catch (IOException e) {

        }
        System.out.println(count);
        day1Prob2();
    }

    static void day1Prob2() {
        String pfad = "C:\\ude\\git\\CodeOfAdvent\\src\\Day1.txt";
        int count = 0;
        int curr = 50;
        try (BufferedReader br = new BufferedReader(new FileReader(pfad))) {
            String zeile;
            while ((zeile = br.readLine()) != null) {
                char c = zeile.charAt(0);
                int distanz = Integer.parseInt(zeile.substring(1));
                for (int i = 0; i < distanz; i++) {
                    if (c == 'L') {
                        curr = (curr - 1 + 100) % 100;
                    } else {
                        curr = (curr + 1) % 100;
                    }
                    if (curr == 0) {
                        count++;
                    }
                }
            }
        } catch (IOException e) {

        }

        System.out.println(count);
    }

}



