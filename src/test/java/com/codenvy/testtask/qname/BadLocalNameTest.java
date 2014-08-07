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
 * Test checks rightness of parsing localname  in bad cases.
 */
@RunWith(Parameterized.class)
public class BadLocalNameTest {

    private QNameParser qNameParser;
    private String badLineForParsing;

    public BadLocalNameTest(String badLineForParsing) {
        this.badLineForParsing = badLineForParsing;
    }

    @Before
    public void init() {
        qNameParser = new QNameParser();
    }

    @Parameterized.Parameters
    public static Collection getAllCases() {
        return Arrays.asList(new Object[][]{
                {"prefix:"},
                {"prefix:/"},
                {"prefix::"},
                {"prefix:["},
                {"prefix:]"},
                {"prefix:*"},
                {"prefix:'"},
                {"prefix:\""},
                {"prefix:|"},
                {"prefix: "},
                {"prefix:\n"},
                {"prefix:\r"},
                {"prefix:/ab"},
                {"prefix:/abc"},
                {"prefix:[ab"},
                {"prefix:[abc"},
                {"prefix:]ab"},
                {"prefix:]abc"},
                {"prefix:*ab"},
                {"prefix:*abc"},
                {"prefix:'ab"},
                {"prefix:'abc"},
                {"prefix:\"ab"},
                {"prefix:\"abc"},
                {"prefix:|ab"},
                {"prefix:|abc"},
                {"prefix:\nab"},
                {"prefix:\nabc"},
                {"prefix:\rab"},
                {"prefix:\rabc"},
                {"prefix:ab/"},
                {"prefix:abc/"},
                {"prefix:ab:"},
                {"prefix:abc:"},
                {"prefix:ab["},
                {"prefix:abc["},
                {"prefix:ab]"},
                {"prefix:abc]"},
                {"prefix:ab*"},
                {"prefix:abc*"},
                {"prefix:ab'"},
                {"prefix:abc'"},
                {"prefix:ab\""},
                {"prefix:abc\""},
                {"prefix:ab|"},
                {"prefix:abc|"},
                {"prefix:ab "},
                {"prefix:abc "},
                {"prefix:ab\n"},
                {"prefix:abc\n"},
                {"prefix:ab\r"},
                {"prefix:abc\r"},
                {"prefix:a/b"},
                {"prefix:ab/c"},
                {"prefix:a[b"},
                {"prefix:ab[c"},
                {"prefix:a]b"},
                {"prefix:ab]c"},
                {"prefix:a*b"},
                {"prefix:ab*c"},
                {"prefix:a'b"},
                {"prefix:ab'c"},
                {"prefix:a\"b"},
                {"prefix:ab\"c"},
                {"prefix:a|b"},
                {"prefix:ab|c"},
                {"prefix:a\nb"},
                {"prefix:ab\nc"},
                {"prefix:a\rb"},
                {"prefix:abc\r"}});
    }

    @Test(expected = IllegalNameException.class)
    public void testLineWithBadLocalName() throws IllegalNameException {
        qNameParser.parse(badLineForParsing);
    }
}