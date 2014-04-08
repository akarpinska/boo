package com.ngrams.app.ngrams.impl;

import com.ngrams.app.ngrams.api.INGramsProcessor;

import java.util.*;

/**
 * Task 1: Given the list of phrases (in the form of text file with a phrase for a line) you need to count and output
 * the number of times each n-gram is used among all the phrases’ words. The results should be sorted by the number
 * of usage in the descending order and printed to stdout.
 */
public class NGramsProcessor implements INGramsProcessor {
    public Set<NGramsByUsage> sortNGramsByUsage(Map<String, Integer> _ngrams) {
        // Sort n-grams by number of usage and alphabetically
        Set<NGramsByUsage> sortedNgrams = new TreeSet<NGramsByUsage>();
        Iterator<String> it = _ngrams.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            sortedNgrams.add(new NGramsByUsage(_ngrams.get(key), key));
        }

        return sortedNgrams;
    }
}
