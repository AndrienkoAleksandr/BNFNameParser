package com.codenvy.testtask.qname;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class QNameParserTest {

    private QNameParser qNameParser;
    private QName qName;
    static final char[] All_WRONG_CHAR_FOR_SYMPLE_NAME;

    static {
        String allWrongSymbol =
                String.valueOf(Constant.SPECIAL_CHECK)  +
                String.valueOf(Constant.WHITE_SPACE) +
                String.valueOf(Constant.WRONG_ONE_CHAR_SIMPLE_NAME);
        All_WRONG_CHAR_FOR_SYMPLE_NAME = allWrongSymbol.toCharArray();
    }

    @Before
    public void init() {
        qNameParser = new QNameParser();
        qName = null;
    }

    @Test(expected = IllegalNameException.class)
    public void testNullLine() throws IllegalNameException {
        qNameParser.parse(null);
    }

    @Test(expected = IllegalNameException.class)
    public void testEmptyLine() throws IllegalNameException {
        qNameParser.parse("");
    }

    @Test
    public void testValidSimpleNameWithOneSymbol() throws IllegalNameException {
        qName = qNameParser.parse("a");
        assertTrue(qName.getLocalName().equals("a") && qName.getPrefix().equals(""));
    }

    @Test
    public void testValidSimpleNameWithTwoSymbol() throws IllegalNameException {
        qName = qNameParser.parse("bb");
        assertTrue(qName.getLocalName().equals("bb") && qName.getPrefix().equals(""));

    }

    @Test
    public void testValidSimpleNameWithTreeSymbol() throws IllegalNameException {
        qName = qNameParser.parse("bbb");
        assertTrue(qName.getLocalName().equals("bbb") && qName.getPrefix().equals(""));
    }

    @Test
    public void testValidSimpleNameWithMoreThenTreeSymbol() throws IllegalNameException {
        qName = qNameParser.parse("bbcc");
        assertTrue(qName.getLocalName().equals("bbcc") && qName.getPrefix().equals(""));
    }

    @Test(expected = IllegalNameException.class)
    public void testInvalidSimpleNameWithOneChar() throws IllegalNameException {
        for (char line: All_WRONG_CHAR_FOR_SYMPLE_NAME) {
            qNameParser.parse(String.valueOf(line));
        }
    }

    @Test(expected = IllegalNameException.class)
    public void testInvalidSimpleNameWithTwoChar() throws IllegalNameException {
        for (char line: All_WRONG_CHAR_FOR_SYMPLE_NAME) {
            qNameParser.parse('a' + String.valueOf(line));
            qNameParser.parse(String.valueOf(line) + 'a');
        }
    }

    @Test(expected = IllegalNameException.class)
    public void testInvalidSimpleNameWithTreeChar() throws IllegalNameException {
        for (char line: All_WRONG_CHAR_FOR_SYMPLE_NAME) {
            qNameParser.parse('a' + String.valueOf(line) + 'b');
            qNameParser.parse(String.valueOf(line) + "ab");
            qNameParser.parse("ab" + String.valueOf(line));
        }
    }

    @Test(expected = IllegalNameException.class)
    public void testInvalidSimpleNameWithMoreThenTreeChar() throws IllegalNameException {
        for (char line: All_WRONG_CHAR_FOR_SYMPLE_NAME) {
            qNameParser.parse("ab" + String.valueOf(line) + "cde");
            qNameParser.parse(String.valueOf(line) + "abcde");
            qNameParser.parse("abcde" + String.valueOf(line));
        }
    }

    @Test(expected = IllegalNameException.class)
    public void testSimpleNameWithTwoDifferentInvalidChar() throws IllegalNameException {
        int size = All_WRONG_CHAR_FOR_SYMPLE_NAME.length;
        for (int i = 0, j = size - 1; i < size; i++, j--) {
            qNameParser.parse("ab" + All_WRONG_CHAR_FOR_SYMPLE_NAME[i] + All_WRONG_CHAR_FOR_SYMPLE_NAME[j]);
            qNameParser.parse(All_WRONG_CHAR_FOR_SYMPLE_NAME[i] + All_WRONG_CHAR_FOR_SYMPLE_NAME[j] + "ab");
            qNameParser.parse("cd" + All_WRONG_CHAR_FOR_SYMPLE_NAME[i] + All_WRONG_CHAR_FOR_SYMPLE_NAME[j] + "ab");
        }
    }
}
