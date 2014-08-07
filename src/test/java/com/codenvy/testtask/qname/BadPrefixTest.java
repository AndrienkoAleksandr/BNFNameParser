package com.codenvy.testtask.qname;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Created by Andrienko Alexander on 7.8.2014.
 * @version 0.4
 *This is test for class QNameParser.
 * Test checks rightness of parsing prefix in bad cases.
 */
@RunWith(Parameterized.class)
public class BadPrefixTest {
    private QNameParser qNameParser;
    private String badLineForParsing;

    public BadPrefixTest(String badLineForParsing) {
        this.badLineForParsing = badLineForParsing;
    }

    @Parameterized.Parameters
    public static Collection getAllCases() {
        return Arrays.asList(new Object[][]{
                {null},
                {":localname"},
                {"abc:"},
                {"@abc:localname"},
                {'\u0060'+ "abc:localname"},
                {'\u00D7'+ "abc:localname"},
                {'\u00F7'+ "abc:localname"},
                {'\u3008'+ "abc:localname"},
                {'\u9FA6'+ "abc:localname"},
                {'\u3030'+ "abc:localname"},
                {"a@b:localname"},
                {"ab@:localname"},
                {"a@@:localname"},
                {"a" + '\u0040' + "bc:fgf"},
                {"ab" + '\u0040' + "c:fgf"},
                {"abc" + '\u0040' + ":fgf"},
                {"a" + '\u9FA6' + "bc:fgf"},
                {"ab" + '\u9FA6' + "c:fgf"},
                {"abc" + '\u9FA6' + ":fgf"},
                {"a" + '\u002F' + "bc:fgf"},
                {"ab" + '\u002F' + "c:fgf"},
                {"abc" + '\u002F' + ":fgf"},
                {"a" + '\u0346' + "bc:fgf"},
                {"ab" + '\u0346' + "c:fgf"},
                {"abc" + '\u0346' +":fgf"},
                {"a" + '\u00B8' + "bc:fgf"},
                {"ab" + '\u00B8' + "c:fgf"},
                {"abc" + '\u00B8' +":fgf"}});
    }

    @Before
    public void init() {
        qNameParser = new QNameParser();
    }

    @Test(expected = IllegalNameException.class)
    public void testLineWithBadPrefix() throws IllegalNameException {
        qNameParser.parse(badLineForParsing);
    }
}
