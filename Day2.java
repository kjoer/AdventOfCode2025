package org.example;

import java.io.IOException;

public class Day2 {
    static void day2Prob1() {
        FileReader f = new FileReader();
        String[] arr;
        long sum = 0;
        try {
            arr = f.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < arr.length; i++) {
            String[] temp = arr[i].split("-");
            String a = temp[0];
            String b = temp[1];
            a = a.trim();
            b = b.trim();
            if (a.length() % 2 != 0 && b.length() % 2 != 0) continue;
            else {
                long c = Long.parseLong(a);
                long d = Long.parseLong(b);
                for (long j = c; j < d; j++) {
                    if (isInvalidID(j)) {
                        sum += j;
                    }
                }
            }
        }
        System.out.println(sum);
    }

    static void day2Prob2() {
        FileReader f = new FileReader();
        String[] arr;
        long sum = 0;
        try {
            arr = f.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < arr.length; i++) {
            String[] temp = arr[i].split("-");
            String a = temp[0];
            String b = temp[1];
            a = a.trim();
            b = b.trim();
            long c = Long.parseLong(a);
            long d = Long.parseLong(b);
            for (long j = c; j < d; j++) {
                if (isInvalidID2(j)) {
                    sum += j;
                }
            }

        }
        System.out.println(sum);
    }


    private static boolean isInvalidID(long n) {
        String s = String.valueOf(n);
        if (s.length() % 2 != 0) {
            return false;
        }
        int half = s.length() / 2;
        String first = s.substring(0, half);
        String second = s.substring(half);
        return first.equals(second);
    }

    private static boolean isInvalidID2(long n) {
        String s = String.valueOf(n);
        int len = s.length();
        for (int i = 1; i <= len/2; i++) {
            if (len % i != 0) continue;
            String parts = s.substring(0, i);
            int rep = len / i;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < rep; j++) {
                sb.append(parts);
            }
            if (sb.toString().equals(s)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        day2Prob1();
        day2Prob2();
    }
}
