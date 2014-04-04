package com.boo.app;

import com.boo.app.ngrams.api.INGramsProcessor;
import com.boo.app.ngrams.api.INGramsReader;
import com.boo.app.ngrams.impl.NGramsProcessor;
import com.boo.app.ngrams.impl.StringStreamNGramsReader;
import com.boo.app.ngrams.impl.StringStreamNGramsSaver;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by akarpinska on 4/1/14.
 */
public class NGramsProcessorTest {

    @Test(groups = { "main" })
    public void oneString() {

        String data = "abcd abc xyz z";
        String expected = "abc: 2\n" +
                "bcd: 1\n" +
                "xyz: 1\n";

        INGramsReader reader = new StringStreamNGramsReader(data);
        StringStreamNGramsSaver saver = new StringStreamNGramsSaver();
        INGramsProcessor processor = new NGramsProcessor();

        saver.saveNGrams( processor.sortNGramsByUsage( reader.readNGrams(3) ) );
        Assert.assertEquals(saver.getResult(), expected);
    }

    @Test(groups = { "main" })
    public void threeStrings() {

        String data = "abcd abc xyz z\n" +
                      "z xxxyz z\n" +
                      "abxabcd";

        String expected = "abc: 3\n" +
                          "bcd: 2\n" +
                          "xyz: 2\n" +
                          "abx: 1\n" +
                          "bxa: 1\n" +
                          "xab: 1\n" +
                          "xxx: 1\n" +
                          "xxy: 1\n";

        INGramsReader reader = new StringStreamNGramsReader(data);
        StringStreamNGramsSaver saver = new StringStreamNGramsSaver();
        INGramsProcessor processor = new NGramsProcessor();

        saver.saveNGrams( processor.sortNGramsByUsage( reader.readNGrams(3) ) );
        Assert.assertEquals(saver.getResult(), expected);
    }

    @Test(groups = { "main" })
    public void noNGrams() {

        String data = "abcd abc xyz z\n";

        String expected = "";
        INGramsReader reader = new StringStreamNGramsReader(data);
        StringStreamNGramsSaver saver = new StringStreamNGramsSaver();
        INGramsProcessor processor = new NGramsProcessor();

        saver.saveNGrams( processor.sortNGramsByUsage( reader.readNGrams(10) ) );
        Assert.assertEquals(saver.getResult(), expected);
    }

    @Test(groups = { "main" })
    public void EmptyString() {

        String data = "";
        String expected = "";
        INGramsReader reader = new StringStreamNGramsReader(data);
        StringStreamNGramsSaver saver = new StringStreamNGramsSaver();
        INGramsProcessor processor = new NGramsProcessor();

        saver.saveNGrams( processor.sortNGramsByUsage( reader.readNGrams(10) ) );
        Assert.assertEquals(saver.getResult(), expected);
    }

    @Test(groups = { "main" })
    public void OneCharacter() {

        String data = "aaaaa aaaa aa";
        String expected = "a: 11\n";

        INGramsReader reader = new StringStreamNGramsReader(data);
        StringStreamNGramsSaver saver = new StringStreamNGramsSaver();
        INGramsProcessor processor = new NGramsProcessor();

        saver.saveNGrams( processor.sortNGramsByUsage( reader.readNGrams(1) ) );
        Assert.assertEquals(saver.getResult(), expected);
    }

    @Test(groups = { "main" })
    public void ZeroLength() {

        String data = "aaaaa aaaa aa";
        String expected = "";

        INGramsReader reader = new StringStreamNGramsReader(data);
        StringStreamNGramsSaver saver = new StringStreamNGramsSaver();
        INGramsProcessor processor = new NGramsProcessor();

        saver.saveNGrams( processor.sortNGramsByUsage( reader.readNGrams(0) ) );
        Assert.assertEquals(saver.getResult(), expected);
    }
}
