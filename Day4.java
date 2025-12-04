package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        System.out.println(day4Prob1());//1363
        System.out.println(day4Prob2());//8184
    }

    static char[][] arr;
    
    static long day4Prob1() {
        long count = 0;
        fillArr();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                CoordinateDay4 c = new CoordinateDay4(i, j);
                if (isRoll(c)) {
                    if (isLessThan4Adjacant(c)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }


	static long day4Prob2() {
    long countOverall = 0;
    fillArr();
    long removedThisIteration;
    do {
      removedThisIteration = 0;
         for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                CoordinateDay4 c = new CoordinateDay4(i, j);
                    if (isRoll(c)) {
                        if (isLessThan4Adjacant(c)) {
                            countOverall++;
                            removedThisIteration++;
                            arr[i][j] = 'X';
                        }
                    }
                }
            }
        }while(removedThisIteration !=0);
        return countOverall;
    }


    static boolean isRoll(CoordinateDay4 c) {
        return arr[c.x()][c.y()] == '@';
    }

    static boolean isLessThan4Adjacant(CoordinateDay4 c) {
        int count = 0;
        int x = c.x();
        int y = c.y();
        if (isValid(x - 1, y) && arr[x - 1][y] == '@') count++;
        if (isValid(x + 1, y) && arr[x + 1][y] == '@') count++;
        if (isValid(x - 1, y + 1) && arr[x - 1][y + 1] == '@') count++;
        if (isValid(x - 1, y - 1) && arr[x - 1][y - 1] == '@') count++;
        if (isValid(x + 1, y + 1) && arr[x + 1][y + 1] == '@') count++;
        if (isValid(x + 1, y - 1) && arr[x + 1][y - 1] == '@') count++;
        if (isValid(x, y + 1) && arr[x][y + 1] == '@') count++;
        if (isValid(x, y - 1) && arr[x][y - 1] == '@') count++;

        return count < 4;
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < arr.length && y >= 0 && y < arr[x].length;
    }


    static void fillArr() {
        String pfad = "C:\\ude\\git\\CodeOfAdvent\\src\\Day4.txt";
        String zeile;
        try (BufferedReader br = new BufferedReader(new FileReader(pfad))) {
            List<String> lines = new ArrayList<>();
            while ((zeile = br.readLine()) != null) {
                lines.add(zeile);
            }
            arr = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                arr[i] = lines.get(i).toCharArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


