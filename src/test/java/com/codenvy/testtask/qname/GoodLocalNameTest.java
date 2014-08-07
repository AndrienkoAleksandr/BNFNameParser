package com.codenvy.testtask.qname;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

/**
 * @author Created by Andrienko Alexander on 7.8.2014.
 * @version 0.4
 *This is test for class QNameParser.
 * Test checks rightness of parsing localname in good cases.
 */
@RunWith(Parameterized.class)
public class GoodLocalNameTest {

    private QNameParser qNameParser;
    private QName qName;
    private String goodLineForParsing;
    private String expectedResult;

    public GoodLocalNameTest(String goodLineForParsing, String expectedResult) {
        this.goodLineForParsing = goodLineForParsing;
        this.expectedResult = expectedResult;
    }

    @Before
    public void init() {
        qNameParser = new QNameParser();
        qName = null;
    }

    @Parameterized.Parameters
    public static Collection getAllCases() {
        return Arrays.asList(new Object[][]{
                {"prefix:a", "a"},
                {"prefix:.", "."},
                {"prefix:..", ".."},
                {"prefix:.a", ".a"},
                {"prefix:a.", "a."},
                {"prefix:aa", "aa"},
                {"prefix:aaa", "aaa"},
                {"prefix:aaaa", "aaaa"},
                {"prefix:a b", "a b"},
                {"prefix:ab c d", "ab c d"},
                {"prefix:ab   d", "ab   d"},
        });
    }

    @Test
    public void testLineWithGoodLocalName() throws IllegalNameException {
        qName = qNameParser.parse(goodLineForParsing);
        assertTrue(qName.getLocalName().equals(expectedResult) && qName.getPrefix().equals("prefix"));
    }
}
