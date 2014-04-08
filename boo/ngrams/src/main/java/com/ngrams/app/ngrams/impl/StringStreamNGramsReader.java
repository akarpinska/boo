package com.ngrams.app.ngrams.impl;

import com.ngrams.app.ngrams.api.INGramsReader;

import java.io.StringReader;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by akarpinska on 4/3/14.
 */
public class StringStreamNGramsReader extends StreamNGramsReader implements INGramsReader {

    String m_inputString;

    public StringStreamNGramsReader(String _inputString) {
        m_inputString = _inputString;
    }

    public Map<String, Integer> readNGrams(int _length) {

        Scanner input = new Scanner(new StringReader(m_inputString));
        return readNGrams(_length, input);
    }
}
