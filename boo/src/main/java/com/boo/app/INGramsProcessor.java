package com.boo.app;

import java.util.Map;
import java.util.Set;

/**
 * Created by akarpinska on 4/3/14.
 */
public interface INGramsProcessor {

    Set<NGramsByUsage> sortNGramsByUsage( Map<String, Integer> _ngrams );

}
