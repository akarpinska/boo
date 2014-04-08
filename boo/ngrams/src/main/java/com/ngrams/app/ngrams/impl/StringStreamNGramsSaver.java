package com.ngrams.app.ngrams.impl;

import com.ngrams.app.ngrams.api.INGramsSaver;

import java.io.StringWriter;
import java.util.Formatter;
import java.util.Set;

/**
 * Created by akarpinska on 4/3/14.
 */
public class StringStreamNGramsSaver extends StreamNGramsSaver implements INGramsSaver {

    String m_outputString;

    public StringStreamNGramsSaver() {
        m_outputString = null;
    }

    public void saveNGrams(Set<NGramsByUsage> _ngrams) {
        Formatter output = new Formatter(new StringWriter());
        saveNGrams(_ngrams, output);
        m_outputString = output.toString();
    }

    public String getResult() {
        return m_outputString;
    }
}
