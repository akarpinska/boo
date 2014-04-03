package com.boo.app.ngrams.api;

import com.boo.app.ngrams.impl.NGramsByUsage;

import java.util.Set;

/**
 * Created by akarpinska on 4/3/14.
 */
public interface INGramsSaver {

    void saveNGrams(Set<NGramsByUsage> _ngrams);
}
