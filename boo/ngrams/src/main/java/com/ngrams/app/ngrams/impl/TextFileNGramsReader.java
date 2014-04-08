package com.ngrams.app.ngrams.impl;

import com.ngrams.app.ngrams.api.INGramsReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by akarpinska on 4/3/14.
 */
public class TextFileNGramsReader extends StreamNGramsReader implements INGramsReader {

    String m_inputFileName;

    public TextFileNGramsReader(String _fileName) {
        m_inputFileName = _fileName;
    }

    public Map<String, Integer> readNGrams(int _length) {

        Map<String, Integer> ngrams = null;

        Scanner input = null;

        try {
            input = new Scanner(new BufferedReader(new FileReader(m_inputFileName)));
            ngrams = readNGrams(_length, input);
        } catch (IOException e) {
            System.out.printf("Unable to read file %s.\n", m_inputFileName);
        } finally {
            if (input != null)
                input.close();
        }

        return ngrams;
    }
}
