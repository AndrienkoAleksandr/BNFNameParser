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
 * Test checks rightness of parsing simplename in bad cases.
 */
@RunWith(Parameterized.class)
public class BadSimpleNameTest {
    private QNameParser qNameParser;
    private String badLineForParsing;

    public BadSimpleNameTest(String badLineForParsing) {
        this.badLineForParsing = badLineForParsing;
    }

    @Parameterized.Parameters
    public static Collection getAllCases() {
        return Arrays.asList(new Object[][]{
                {null},
                {""},
                {"."},
                {"/"},
                {":"},
                {"["},
                {"]"},
                {"*"},
                {"'"},
                {"\""},
                {"|"},
                {" "},
                {"\n"},
                {"\r"},
                {".."},
                {"/ab"},
                {"/abc"},
                {":ab"},
                {":abc"},
                {"[ab"},
                {"[abc"},
                {"]ab"},
                {"]abc"},
                {"*ab"},
                {"*abc"},
                {"'ab"},
                {"'abc"},
                {"\"ab"},
                {"\"abc"},
                {"|ab"},
                {"|abc"},
                {"\nab"},
                {"\nabc"},
                {"\rab"},
                {"\rabc"},
                {"ab/"},
                {"abc/"},
                {"ab:"},
                {"abc:"},
                {"ab["},
                {"abc["},
                {"ab]"},
                {"abc]"},
                {"ab*"},
                {"abc*"},
                {"ab'"},
                {"abc'"},
                {"ab\""},
                {"abc\""},
                {"ab|"},
                {"abc|"},
                {"ab "},
                {"abc "},
                {"ab\n"},
                {"abc\n"},
                {"ab\r"},
                {"abc\r"},
                {"a/b"},
                {"ab/c"},
                {"a[b"},
                {"ab[c"},
                {"a]b"},
                {"ab]c"},
                {"a*b"},
                {"ab*c"},
                {"a'b"},
                {"ab'c"},
                {"a\"b"},
                {"ab\"c"},
                {"a|b"},
                {"ab|c"},
                {"a\nb"},
                {"ab\nc"},
                {"a\rb"},
                {"abc\r"}});
    }

    @Before
    public void init() {
        qNameParser = new QNameParser();
    }

    @Test(expected = IllegalNameException.class)
    public void testLineWithSimpleName() throws IllegalNameException {
        qNameParser.parse(badLineForParsing);
    }
}
