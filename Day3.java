
package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day3 {
    public static void main(String[] args) {
        long d3p1 = day3();
        System.out.println(d3p1); //17074
        long d3p2 = day3Prob2();
        System.out.println(d3p2);//169512729575727
    }

    static long day3() {
        String pfad = "C:\\ude\\git\\CodeOfAdvent\\src\\Day3.txt";
        long count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(pfad))) {
            String zeile;
            while ((zeile = br.readLine()) != null) {
                int maxJoltage = -1;
                for (int i = 0; i < zeile.length(); i++) {
                    for (int j = i + 1; j < zeile.length(); j++) {
                        int jol = Integer.parseInt(zeile.charAt(i) + "" + zeile.charAt(j));
                        maxJoltage = Math.max(maxJoltage, jol);
                    }

                }
                count += maxJoltage;

            }

        } catch (IOException e) {
        }
        return count;
    }

    static long day3Prob2() {
        String pfad = "C:\\ude\\git\\CodeOfAdvent\\src\\Day3.txt";
        long count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(pfad))) {
            String zeile;
            while ((zeile = br.readLine()) != null) {
                int stIdx = 0;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 12; i++) {
                    int maxDiIdx = stIdx;
                    for (int j = stIdx; j <= zeile.length() - (12 - i); j++) {
                        if (zeile.charAt(j) > zeile.charAt(maxDiIdx)) {
                            maxDiIdx = j;
                        }
                    }
                    sb.append(zeile.charAt(maxDiIdx));
                    stIdx = maxDiIdx + 1;
                }
                count += Long.parseLong(sb.toString());
            }
        } catch (
                IOException e) {
        }
        return count;
    }
}

