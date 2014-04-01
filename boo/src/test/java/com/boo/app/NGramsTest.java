package com.boo.app;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by akarpinska on 4/1/14.
 */
public class NGramsTest {

    @Test(groups = { "main" })
    public void oneString() {

        String data = "abcd abc xyz z";
        String expected = "abc: 2\n" +
                          "bcd: 1\n" +
                          "xyz: 1\n";
        Scanner input = new Scanner( new BufferedReader( new StringReader( data )));
        Formatter output = new Formatter( new StringWriter() );
        NGrams ngrams = new NGrams();
        ngrams.calculateNGrams(input, output, 3);
        Assert.assertEquals(output.toString(), expected);
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

        Scanner input = new Scanner( new BufferedReader( new StringReader( data )));
        Formatter output = new Formatter( new StringWriter() );
        NGrams ngrams = new NGrams();
        ngrams.calculateNGrams(input, output, 3);
        Assert.assertEquals(output.toString(), expected);
    }

    @Test(groups = { "main" })
    public void noNGrams() {

        String data = "abcd abc xyz z\n";

        String expected = "";

        Scanner input = new Scanner( new BufferedReader( new StringReader( data )));
        Formatter output = new Formatter( new StringWriter() );
        NGrams ngrams = new NGrams();
        ngrams.calculateNGrams(input, output, 10);
        Assert.assertEquals(output.toString(), expected);
    }

    @Test(groups = { "main" })
    public void EmptyString() {

        String data = "";

        String expected = "";

        Scanner input = new Scanner( new BufferedReader( new StringReader( data )));
        Formatter output = new Formatter( new StringWriter() );
        NGrams ngrams = new NGrams();
        ngrams.calculateNGrams(input, output, 10);
        Assert.assertEquals(output.toString(), expected);
    }

    @Test(groups = { "main" })
    public void OneCharacter() {

        String data = "aaaaa aaaa aa";

        String expected = "a: 11\n";

        Scanner input = new Scanner( new BufferedReader( new StringReader( data )));
        Formatter output = new Formatter( new StringWriter() );
        NGrams ngrams = new NGrams();
        ngrams.calculateNGrams(input, output, 1);
        Assert.assertEquals(output.toString(), expected);
    }

    @Test(groups = { "main" })
    public void ZeroLength() {

        String data = "aaaaa aaaa aa";

        String expected = "";

        Scanner input = new Scanner( new BufferedReader( new StringReader( data )));
        Formatter output = new Formatter( new StringWriter() );
        NGrams ngrams = new NGrams();
        ngrams.calculateNGrams(input, output, 0);
        Assert.assertEquals(output.toString(), expected);
    }
}
