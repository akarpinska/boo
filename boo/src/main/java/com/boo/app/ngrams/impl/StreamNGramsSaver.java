package com.boo.app.ngrams.impl;

import java.util.Formatter;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by akarpinska on 4/3/14.
 */
public class StreamNGramsSaver {

    public void saveNGrams(Set<NGramsByUsage> _ngrams, Formatter _stream) {
        // Print n-grams to output stream
        Iterator<NGramsByUsage> ngramsIt = _ngrams.iterator();
        while (ngramsIt.hasNext()) {
            NGramsByUsage ngram = ngramsIt.next();
            _stream.format("%s: %d\n", ngram.m_ngram, ngram.m_usages);
        }
    }
}
