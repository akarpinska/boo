package com.calculator.app;

import com.calculator.app.calculator.Calculator;

import java.util.Map;

/**
 * Task 1: Given the list of phrases (in the form of text file with a phrase for a line) you need to count and output
 * the number of times each n-gram is used among all the phrases’ words. The results should be sorted by the number
 * of usage in the descending order and printed to stdout.
 */
public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.run();
    }
}
