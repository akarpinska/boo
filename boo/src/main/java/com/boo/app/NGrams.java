package com.boo.app;

import java.util.*;

/**
 * Task 1: Given the list of phrases (in the form of text file with a phrase for a line) you need to count and output
 * the number of times each n-gram is used among all the phrases’ words. The results should be sorted by the number
 * of usage in the descending order and printed to stdout.
 *
 */
public class NGrams
{
    public void calculateNGrams(Scanner input, Formatter output, int n) {
        if ( n == 0 ) {
            return;
        }

        Map<String, Integer> ngrams = new HashMap<String, Integer>();

        // Read all words from the input stream and count n-grams
        while (input.hasNext()) {
            String word = input.next();

            for ( int i = 0; i <= word.length() - n; ++i ) {
                String sub = word.substring( i, i+n );
                if ( ngrams.containsKey( sub ) ) {
                    ngrams.put( sub, ngrams.get( sub ) + 1);
                }
                else {
                    ngrams.put(sub, 1);
                }
            }
        }

        // Sort n-grams by number of usage and alphabetically
        Set<NGramsUsage> sortedNgrams = new TreeSet<NGramsUsage>();
        Iterator<String> it = ngrams.keySet().iterator();
        while ( it.hasNext() ) {
            String key = it.next();
            sortedNgrams.add(new NGramsUsage( ngrams.get(key), key ));
        }

        // Print n-grams to output stream
        Iterator<NGramsUsage> ngramsIt = sortedNgrams.iterator();
        while ( ngramsIt.hasNext() ) {
            NGramsUsage ngram = ngramsIt.next();
            output.format("%s: %d\n", ngram.m_ngram, ngram.m_count);
        }
    }

    /**
     * Class used to store n-gram and its number of usage.
     * Compares objects by number of usages first and then alphabetically.
     */
    private class NGramsUsage implements Comparable
    {
        final Integer m_count;
        final String m_ngram;

        public NGramsUsage( Integer count, String element ) {
            m_count = count;
            m_ngram = element;
        }

        public int compareTo( Object obj ) {
            if (obj instanceof NGramsUsage) {
                NGramsUsage pair = (NGramsUsage)obj;
                int cmpCount = pair.m_count.compareTo(m_count);
                if (cmpCount != 0) {
                    return cmpCount;
                }
                return m_ngram.compareTo(pair.m_ngram);
            }
            else {
                return 0;
            }
        }
    }
}
