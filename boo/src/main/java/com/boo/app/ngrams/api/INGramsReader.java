package com.boo.app.ngrams.api;

import java.util.Map;

/**
 * Created by akarpinska on 4/3/14.
 */
public interface INGramsReader {

    Map<String, Integer> readNGrams(int _length);
}
