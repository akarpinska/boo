package com.boo.app.ngrams.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by akarpinska on 4/3/14.
 */
public class StreamNGramsReader {

    public Map<String, Integer> readNGrams(int _length, Scanner _stream) {

        Map<String, Integer> ngrams = new HashMap<String, Integer>();

        if (_length == 0)
            return ngrams;

        // Read all words from the input stream and count n-grams
        while (_stream.hasNext()) {
            String word = _stream.next();

            for (int i = 0; i <= word.length() - _length; ++i) {
                String sub = word.substring(i, i + _length);
                if (ngrams.containsKey(sub)) {
                    ngrams.put(sub, ngrams.get(sub) + 1);
                } else {
                    ngrams.put(sub, 1);
                }
            }
        }

        return ngrams;
    }
}
