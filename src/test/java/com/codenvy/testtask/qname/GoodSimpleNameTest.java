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
 * Test checks rightness of parsing simplename in good cases.
 */
@RunWith(Parameterized.class)
public class GoodSimpleNameTest {
    private QNameParser qNameParser;
    private QName qName;
    private String goodLineForParsing;
    private String expectedResult;

    public GoodSimpleNameTest(String goodLineForParsing, String expectedResult) {
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
                {"a", "a"},
                {".a", ".a"},
                {"a.", "a."},
                {"aa", "aa"},
                {"aaa", "aaa"},
                {"aaaa", "aaaa"},
                {"a b", "a b"},
                {"ab c d", "ab c d"},
                {"ab   d", "ab   d"},
        });
    }

    @Test
    public void testLineWithGoodSimpleName() throws IllegalNameException {
        qName = qNameParser.parse(goodLineForParsing);
        assertTrue(qName.getLocalName().equals(expectedResult) && qName.getPrefix().equals(""));
    }
}
