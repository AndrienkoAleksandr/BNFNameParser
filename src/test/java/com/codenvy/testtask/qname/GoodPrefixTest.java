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
 * Test checks rightness of parsing prefix in good cases.
 */
@RunWith(Parameterized.class)
public class GoodPrefixTest {
    private QNameParser qNameParser;
    private QName qName;
    private String goodLineForParsing;
    private String expectedResult;

    public GoodPrefixTest(String goodLineForParsing, String expectedResult) {
        this.goodLineForParsing = goodLineForParsing;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection getAllCases() {
        return Arrays.asList(new Object[][]{
                {"a:localname", "a"},
                {":abc:localname", ":abc"},
                {"_abc:localname", "_abc"},
                {'\u0041'+ "abc:localname", '\u0041'+ "abc"},
                {'\u0050'+ "abc:localname", '\u0050'+ "abc"},
                {'\u005A'+ "abc:localname", '\u005A'+ "abc"},
                {'\u3007'+ "abc:localname", '\u3007'+ "abc"},
                {'\u3021'+ "abc:localname", '\u3021'+ "abc"},
                {'\u9FA5'+ "abc:localname", '\u9FA5'+ "abc"},
                {"a_bc:fgf", "a_bc"},
                {"ab_c:fgf", "ab_c"},
                {"abc_:fgf", "abc_"},
                {"a-bc:fgf", "a-bc"},
                {"ab-c:fgf", "ab-c"},
                {"abc-:fgf", "abc-"},
                {"a.bc:fgf", "a.bc"},
                {"ab.c:fgf", "ab.c"},
                {"abc.:fgf", "abc."},
                {"a:bc:fgf", "a:bc"},
                {"ab:c:fgf", "ab:c"},
                {"abc::fgf", "abc:"},
                {"a" + '\u0041' + "bc:fgf", "a" + '\u0041' + "bc"},
                {"ab" + '\u0041' + "c:fgf", "ab" + '\u0041' + "c"},
                {"abc" + '\u0041' + ":fgf", "abc" + '\u0041'},
                {"a" + '\u3007' + "bc:fgf", "a" + '\u3007' + "bc"},
                {"ab" + '\u3007' + "c:fgf", "ab" + '\u3007' + "c"},
                {"abc" + '\u3007' + ":fgf", "abc" + '\u3007'},
                {"a1bc:fgf", "a1bc"},
                {"ab1c:fgf", "ab1c"},
                {"abc1:fgf", "abc1"},
                {"a" + '\u0300' + "bc:fgf", "a" + '\u0300' + "bc"},
                {"ab" + '\u0300' + "c:fgf", "ab" + '\u0300' + "c"},
                {"abc" + '\u0300' +":fgf", "abc" + '\u0300'},
                {"a" + '\u00B7' + "bc:fgf", "a" + '\u00B7' + "bc"},
                {"ab" + '\u00B7' + "c:fgf", "ab" + '\u00B7' + "c"},
                {"abc" + '\u00B7' +":fgf", "abc" + '\u00B7'}});
    }

    @Before
    public void init() {
        qNameParser = new QNameParser();
        qName = null;
    }

    @Test
    public void testLineWithGoodPrefix() throws IllegalNameException {
        qName = qNameParser.parse(goodLineForParsing);
        assertTrue(qName.getPrefix().equals(expectedResult));
    }
}
