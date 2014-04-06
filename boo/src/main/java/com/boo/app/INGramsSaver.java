package com.boo.app;

import java.util.Set;

/**
 * Created by akarpinska on 4/3/14.
 */
public interface INGramsSaver {

    void saveNGrams( Set<NGramsByUsage> _ngrams );
}
