package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day6 {
    public static void main(String[] args) throws IOException {
        System.out.println(day6Prob1());//6417439773370
        System.out.println(day6Prob2());//11044319475191
    }

    static long day6Prob1() throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get("Day6.txt")
        );
        int rows = lines.size();
        int cols = lines.stream().mapToInt(String::length).max().orElse(0);

        char[][] grid = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            char[] row = lines.get(r).toCharArray();
            for (int c = 0; c < cols; c++) {
                grid[r][c] = (c < row.length) ? row[c] : ' ';
            }
        }

        long grandTotal = 0;
        int c = 0;
        while (c < cols) {
            while (c < cols && isColumnEmpty(grid, rows, c)) {
                c++;
            }
            if (c >= cols) break;
            List<StringBuilder> blockBuilders = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                blockBuilders.add(new StringBuilder());
            }
            while (c < cols && !isColumnEmpty(grid, rows, c)) {
                for (int r = 0; r < rows; r++) {
                    blockBuilders.get(r).append(grid[r][c]);
                }
                c++;
            }
            List<String> block = new ArrayList<>();
            for (StringBuilder sb : blockBuilders) {
                block.add(sb.toString().trim());
            }
            String opStr = block.get(rows - 1);
            if (opStr.isEmpty()) continue;
            char op = opStr.charAt(0);
            long result = (op == '+') ? 0 : 1;
            for (int i = 0; i < rows - 1; i++) {
                if (block.get(i).isEmpty()) continue;
                long v = Long.parseLong(block.get(i));
                if (op == '+') result += v;
                else result *= v;
            }
            grandTotal += result;
        }
        return grandTotal;
    }

    private static boolean isColumnEmpty(char[][] grid, int rows, int c) {
        for (int r = 0; r < rows; r++) {
            if (grid[r][c] != ' ')
                return false;
        }
        return true;

    }

    static long day6Prob2() throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get("Day6.txt")
        );
        int rows = lines.size();
        int cols = lines.stream().mapToInt(String::length).max().orElse(0);
        char[][] grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            char[] row = lines.get(r).toCharArray();
            for (int c = 0; c < cols; c++) {
                grid[r][c] = (c < row.length) ? row[c] : ' ';
            }
        }
        long grandTotal = 0;
        int c = cols - 1;

        while (c >= 0) {
            while (c >= 0 && isColumnEmpty(grid, rows, c)) {
                c--;
            }
            if (c < 0) break;
            List<String> columnDigits = new ArrayList<>();
            while (c >= 0 && !isColumnEmpty(grid, rows, c)) {
                StringBuilder sb = new StringBuilder();
                for (int r = 0; r < rows - 1; r++) {
                    if (grid[r][c] != ' ')
                        sb.append(grid[r][c]);
                }
                columnDigits.add(sb.toString());
                c--;
            }
            int opColumn = c + 1;
            char op = grid[rows - 1][opColumn];
            long result = (op == '+') ? 0 : 1;
            for (String s : columnDigits) {
                if (!s.isEmpty()) {
                    long v = Long.parseLong(s);
                    if (op == '+') result += v;
                    else result *= v;
                }
            }
            grandTotal += result;
        }
        return grandTotal;
    }
}
