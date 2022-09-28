package com;

import java.math.BigInteger;

public class Main {

    /** Attention! From the 20th perfect number, the process will start to slow down, as large numbers take up a lot of memory, RAM will be overloaded. */

    public static void main(String[] args) {
        String line = "\n----------------------------------------------------------------------------------------------------\n";
        int endPoint;
        if (args.length == 0) endPoint = 50000;
        else endPoint = Integer.parseInt(args[0]);

        System.out.println("Finding mersenne prime and his perfect numbers, [2^2-1, ... 2^" + endPoint + "-1].\n");
        System.out.println("MERSENNE PRIME:\t2^2-1\nDIGITS:\t1\n\n3\n\n");
        System.out.println("PERFECT NUMBER:\t2^1 * (2^2-1)\nDIGITS:\t1\n\n6" + line);

        for (int p = 3; p <= endPoint; p += 2) {
            if (isPrime(p) && isMersennePrime(p)) {
                calculateMersennePrime(p);
                calculatePerfectNumber(p, line);
            }
        }
        System.out.println();
    }

    public static void calculateMersennePrime(int p) {
        int o;
        char one = '1';
        String binary = "";

        for (o = 0; o < p; o++) binary += one;
        BigInteger mersennePrime = new BigInteger(binary, 2);

        System.out.println("MERSENNE PRIME:\t2^" + p + "-1\nDIGITS:\t" + numberLength(mersennePrime) + "\n\n" + mersennePrime + "\n\n");
    }

    public static void calculatePerfectNumber(int p, String line) {
        int o, z;
        char one = '1';
        char zero = '0';
        String binary = "";
        String ones = "";
        String zeros = "";

        for (o = 1; o <= p; o++) ones += one;
        for (z = 1; z <= p - 1; z++) zeros += zero;
        binary += ones + zeros;
        BigInteger perfectNumber = new BigInteger(binary, 2);

        System.out.println("PERFECT NUMBER:\t2^" + (p - 1) + " * (2^" + p + "-1)\nDIGITS:\t" + numberLength(perfectNumber) + "\n\n" + perfectNumber);
        System.out.println(line);
    }

    public static boolean isPrime(int p) {
        if (p == 2) return true;
        else if (p < 2 || p % 2 == 0) return false;
        else {
            int to = (int) Math.sqrt(p);
            for (int i = 3; i <= to; i += 2) if (p % i == 0) return false;
            return true;
        }
    }

    public static boolean isMersennePrime(int p) {
        if (p == 2) return true;
        else {
            BigInteger mp = BigInteger.ONE.shiftLeft(p).subtract(BigInteger.ONE);
            BigInteger sum = BigInteger.valueOf(4);
            for (int i = 3; i <= p; i++) sum = sum.multiply(sum).subtract(BigInteger.valueOf(2)).mod(mp);
            return sum.equals(BigInteger.ZERO);
        }
    }

    public static int numberLength(BigInteger n) {return String.valueOf(n).length();}

}