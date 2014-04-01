package com.boo.app;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Task 1: Given the list of phrases (in the form of text file with a phrase for a line) you need to count and output
 * the number of times each n-gram is used among all the phrases’ words. The results should be sorted by the number
 * of usage in the descending order and printed to stdout.
 *
 */
public class App 
{
    public static void main( String[] args ) throws  IOException {

        Scanner input = null;
        Formatter output = null;

        try {
            input = new Scanner( new BufferedReader( new FileReader(args[0]) ));
            output = new Formatter( new FileWriter(args[1]));
            NGrams ngrams = new NGrams();
            ngrams.calculateNGrams( input, output, Integer.parseInt(args[2]) );
        }
        finally {
            if ( input != null ) {
                input.close();
            }
            if ( output != null ) {
                output.close();
            }
        }
    }
}
