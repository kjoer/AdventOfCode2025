package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day5 {
    public static void main(String[] args) throws IOException {
        System.out.println(day5Prob1());//798
        System.out.println(day5Prob2());//366181852921027
    }

    static int day5Prob1() throws IOException {
        List<BigInteger[]> ranges = new ArrayList<>();
        List<BigInteger> ids = new ArrayList<>();
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Day5.txt"))) {
            String zeile;
            while ((zeile = br.readLine()) != null && !zeile.isBlank()) {
                String[] parts = zeile.trim().split("\\s*-\\s*");
                BigInteger start = new BigInteger(parts[0]);
                BigInteger end = new BigInteger(parts[1]);
                ranges.add(new BigInteger[]{start, end});
            }
            while ((zeile = br.readLine()) != null) {
                if (!zeile.isBlank()) {
                    ids.add(new BigInteger(zeile.trim()));
                }
            }
        }
        for (BigInteger id : ids) {
            for (BigInteger[] r : ranges) {
                BigInteger a = r[0];
                BigInteger b = r[1];
                if (id.compareTo(a) >= 0 && id.compareTo(b) <= 0) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    static BigInteger day5Prob2() {
        List<BigInteger[]> ranges = new ArrayList<>();
        BigInteger count = new BigInteger(String.valueOf(0));
        try (BufferedReader br = new BufferedReader(new FileReader("Day5Ranges.txt"))) {
            String zeile;
            while ((zeile = br.readLine()) != null && !zeile.isBlank()) {
                String[] parts = zeile.trim().split("\\s*-\\s*");
                BigInteger start = new BigInteger(parts[0]);
                BigInteger end = new BigInteger(parts[1]);
                ranges.add(new BigInteger[]{start, end});
            }
            ranges.sort(Comparator.comparing(a -> a[0]));
            List<BigInteger[]> merged = new ArrayList<>();
            for (BigInteger[] r : ranges) {
                if (merged.isEmpty()) {
                    merged.add(r);
                } else {
                    BigInteger[] last = merged.get(merged.size() - 1);
                    if (r[0].compareTo(last[1]) <= 0) {
                        if (r[1].compareTo(last[1]) > 0) {
                            last[1] = r[1];
                        }
                    } else {
                        merged.add(r);
                    }
                }
            }
            for (BigInteger[] m : merged) {
                count = count.add(m[1].subtract(m[0]).add(BigInteger.ONE));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
