package com.ngrams.app.ngrams.impl;

/**
 * Class used to store n-gram and its number of usage.
 * Compares objects by number of usages first and then alphabetically.
 */
public class NGramsByUsage implements Comparable {
    final Integer m_usages;
    final String m_ngram;

    public NGramsByUsage(Integer _usages, String _ngram) {
        m_usages = _usages;
        m_ngram = _ngram;
    }

    public int compareTo(Object _obj) {
        if (_obj instanceof NGramsByUsage) {
            NGramsByUsage pair = (NGramsByUsage) _obj;
            int cmpCount = pair.m_usages.compareTo(m_usages);
            if (cmpCount != 0) {
                return cmpCount;
            }
            return m_ngram.compareTo(pair.m_ngram);
        } else {
            return 0;
        }
    }
}