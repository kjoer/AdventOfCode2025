package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {
    public static void main(String[] args) throws IOException {
        System.out.println(day7Prob1());//1504
        System.out.println(day7Prob2());//5137133207830
    }

    static int day7Prob1() throws IOException {
        int count = 0;
        List<String> lines = Files.readAllLines(
                Paths.get("Day7.txt")
        );
        char[] arr = new char[lines.getFirst().length()];
        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            for (int j = 0; j < s.length() - 1; j++) {
                if (s.charAt(j) == 'S') {
                    arr[j] = '|';
                } else if (s.charAt(j) == '^') {
                    if (arr[j] == '|') {
                        count++;
                        if (j < s.length() - 2 && j > 0) {
                            arr[j - 1] = '|';
                            arr[j + 1] = '|';
                        } else if (j == 0) {
                            arr[j + 1] = '|';
                        } else {
                            arr[j - 1] = '|';
                        }
                        arr[j] = '.';

                    }

                }
            }
        }
        return count;
    }

    static long day7Prob2() throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get("Day7.txt")
        );
        int width = lines.getFirst().length();
        int startCol = 0;
        for (int j = 0; j < width; j++) {
            if (lines.getFirst().charAt(j) == 'S') {
                startCol = j;
                break;
            }
        }
        Map<String, Long> memo = new HashMap<>();
        return recurse(startCol, 0, lines, memo);
    }

    static long recurse(int col, int fromRow, List<String> lines, Map<String, Long> memo) {
        String key = col + "," + fromRow;
        if (memo.containsKey(key))
            return memo.get(key);
        int height = lines.size();
        for (int row = fromRow + 1; row < height; row++) {
            char c = lines.get(row).charAt(col);
            if (c == '^') {
                long timelines = 0;
                if (col > 0)
                    timelines += recurse(col - 1, row, lines, memo);
                if (col < lines.getFirst().length() - 1)
                    timelines += recurse(col + 1, row, lines, memo);
                memo.put(key, timelines);
                return timelines;
            }
        }
        memo.put(key, 1L);
        return 1L;
    }
}
