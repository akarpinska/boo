package com.ngrams.app;

import com.ngrams.app.ngrams.api.*;
import com.ngrams.app.ngrams.impl.*;

import java.util.Map;

/**
 * Task 1: Given the list of phrases (in the form of text file with a phrase for a line) you need to count and output
 * the number of times each n-gram is used among all the phrases’ words. The results should be sorted by the number
 * of usage in the descending order and printed to stdout.
 */
public class App {
    public static void main(String[] args) {

        runNGrams();
    }

    private static void runNGrams() {
        System.out.print("Enter input file name\n");
        String inputFileName = System.console().readLine();
        System.out.print("Enter output file name\n");
        String outputFileName = System.console().readLine();

        INGramsReader reader = new TextFileNGramsReader(inputFileName);
        INGramsSaver saver = new TextFileNGramsSaver(outputFileName);
        INGramsProcessor processor = new NGramsProcessor();

        Map ngrams = reader.readNGrams(3);
        if (ngrams != null)
            saver.saveNGrams(processor.sortNGramsByUsage(ngrams));
    }
}
