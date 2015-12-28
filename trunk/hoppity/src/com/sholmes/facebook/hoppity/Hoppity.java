package com.sholmes.facebook.hoppity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Playing with Facebook puzzles 1 - HoppityHop.
 * send to 1051962371@fb.com
 *
 * @author Justin McCoy {mailto:justinmccoy@acm.org}
 *
 */
public final class Hoppity {

    /**
     * Default constructor for utility class is private.
     */
    private Hoppity() {
    }

    /**
     * @param args argument 1 is the filename to open
     */
    public static void main(final String[] args) {
        try {
            playHoppity(readNumberFromFile(args[0]));
        } catch (FileNotFoundException e) {
            System.err.println("Error finding file: " + args[0] + " exiting.");
            return; // Exit if the file cannot be find.
        } catch (IOException e) {
            System.err.println("Error reading file: " + args[0] + " exiting.");
        }
    }

    /**
     * Prints out a sequence of Strings to STDOUT depending on the parameter
     * aNumber. Loops 1 through aNumber checking if the value is divisible by 3,
     * 5 or both.
     *
     * - if value is divisible by 3 print Hoppity - if value is divisible by 5
     * print Hophop - if value is divisible by 3 and 5 print Hop
     *
     * @param aNumber number used for playing HoppityHop
     */
    public static void playHoppity(final int aNumber) {
        final int three = 3;
        final int five = 5;

        if (aNumber < 1) {
            return; // Sanity Check
        }
        // Ok we are sane continue
        for (int inc = 1; inc <= aNumber; inc++) {
            if (inc % three == 0) {
                if (inc % five == 0) {
                    System.out.println("Hop"); // divisible by 3 and 5
                } else {
                    System.out.println("Hoppity"); // divisible by 3
                }
            } else if (inc % five == 0) {
                System.out.println("Hophop"); // divisible by 5
            }
        }
    }

    /**
     * Reads first line of a file returning the integer representation.
     *
     * @param aFilename filename to open
     * @return the integer from aFilename
     * @throws IOException
     *             FileNotFoundException
     */
    public static int readNumberFromFile(final String aFilename)
            throws IOException {
        int integerFoundinFile = -1;
        final BufferedReader br = new BufferedReader(new FileReader(aFilename));
        integerFoundinFile = Integer.valueOf(br.readLine().trim());
        br.close();
        return integerFoundinFile;
    }
}
