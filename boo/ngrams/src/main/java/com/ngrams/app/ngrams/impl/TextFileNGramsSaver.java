package com.ngrams.app.ngrams.impl;

import com.ngrams.app.ngrams.api.INGramsSaver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Set;

/**
 * Created by akarpinska on 4/3/14.
 */
public class TextFileNGramsSaver extends StreamNGramsSaver implements INGramsSaver {

    String m_outputFileName;

    public TextFileNGramsSaver(String _fileName) {
        m_outputFileName = _fileName;
    }

    public void saveNGrams(Set<NGramsByUsage> _ngrams) {
        Formatter output = null;

        try {
            output = new Formatter(new BufferedWriter(new FileWriter(m_outputFileName)));
            saveNGrams(_ngrams, output);
        } catch (IOException e) {
            System.out.printf("Unable to write file %s.\n", m_outputFileName);
        }
    }
}
