package com.codenvy.testtask.qname;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * @author Created by Andrienko Alexander on 6.8.2014.
 * @version 0.4
 *This is test for class QNameParser.
 * Test checks rightness of parsing simplename.
 */
public class SimpleNameTest {
    private QNameParser qNameParser;
    private QName qName;

    @Before
    public void init() {
        qNameParser = new QNameParser();
        qName = null;
    }

    @Test(expected = IllegalNameException.class)
    public void testSimpleNameCanNOTBeNull() throws IllegalNameException {
        qNameParser.parse(null);
    }

    @Test(expected = IllegalNameException.class)
    public void testSimpleNameCanNOTBeEmptyLine() throws IllegalNameException {
        qNameParser.parse("");
    }

    /*Checking simplename with one symbols*/

    @Test
    public void testSimpleNameCanConsistOfOneSymbol() throws IllegalNameException {
        qName = qNameParser.parse("a");
        assertTrue(qName.getLocalName().equals("a") && qName.getPrefix().equals(""));
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBePoint() throws IllegalNameException {
        qNameParser.parse(".");
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBeSlesh() throws IllegalNameException{
        qNameParser.parse("/");
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBeColon() throws IllegalNameException{
        qNameParser.parse(":");
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBeLeftSquareBracket() throws IllegalNameException{
        qNameParser.parse("[");
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBeRightSquareBracket() throws IllegalNameException{
        qNameParser.parse("]");
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBeAsterisk() throws IllegalNameException{
        qNameParser.parse("*");
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBeApostrophe() throws IllegalNameException{
        qNameParser.parse("'");
    }
    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBeQuote() throws IllegalNameException{
        qNameParser.parse("\"");
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBePipe() throws IllegalNameException{
        qNameParser.parse("|");
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBeAnyWhiteSpace_1() throws IllegalNameException{
        qNameParser.parse(" ");
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBeAnyWhiteSpace_2() throws IllegalNameException{
        qNameParser.parse("\n");
    }

    @Test(expected = IllegalNameException.class)
    public void testFistSymbolCanNOTBeAnyWhiteSpace_3() throws IllegalNameException{
        qNameParser.parse("\r");
    }

    /*Checking simplename with two symbols*/
    @Test(expected = IllegalNameException.class)
    public void testFistTwoSymbolsCanNOTBeTwoPoints() throws IllegalNameException{
        qNameParser.parse("..");
    }

    @Test
    public void testFistOrSecondSymbolsCanBePoints_1() throws IllegalNameException{
        qName = qNameParser.parse(".a");
        assertTrue(qName.getLocalName().equals(".a"));
    }

    @Test
    public void testFistOrSecondSymbolsCanBePoints_2() throws IllegalNameException{
        qName = qNameParser.parse("a.");
        assertTrue(qName.getLocalName().equals("a."));
    }

    @Test
    public void testFistAndSecondSymbolsCanBeAnyUnicodeSymbolsExceptWRONG_SEQUENCE_NON_SPACE()
            throws IllegalNameException{
        qName = qNameParser.parse("aa");
        assertTrue(qName.getLocalName().equals("aa"));
    }

    /*Checking simplename with three or more symbols*/

    //checking first symbols of the three or more symbols simplename

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheTheeCanNOTBeSlesh() throws IllegalNameException{
        qNameParser.parse("/ab");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheMoreThenTheeCanNOTBeSlesh() throws IllegalNameException{
        qNameParser.parse("/abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheTheeCanNOTBeColon() throws IllegalNameException{
        qNameParser.parse(":ab");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheMoreThenTheeCanNOTBeColon() throws IllegalNameException{
        qNameParser.parse(":abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheTheeCanNOTBeLeftSquareBracket() throws IllegalNameException{
        qNameParser.parse("[ab");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheMoreThenTheeCanNOTBeLeftSquareBracket() throws IllegalNameException{
        qNameParser.parse("[abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheTheeCanNOTBeRightSquareBracket() throws IllegalNameException{
        qNameParser.parse("]ab");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheMoreThenTheeCanNOTBeRightSquareBracket() throws IllegalNameException{
        qNameParser.parse("]abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheTheeCanNOTBeAsterisk() throws IllegalNameException{
        qNameParser.parse("*ab");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheMoreThenTheeCanNOTBeAsterisk() throws IllegalNameException{
        qNameParser.parse("*abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheTheeCanNOTBeApostrophe() throws IllegalNameException{
        qNameParser.parse("'ab");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheMoreThenTheeCanNOTBeApostrophe() throws IllegalNameException{
        qNameParser.parse("'abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheTheeCanNOTBeQuote() throws IllegalNameException{
        qNameParser.parse("\"ab");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheMoreThenTheeCanNOTBeQuote() throws IllegalNameException{
        qNameParser.parse("\"abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheTheeCanNOTBePipe() throws IllegalNameException{
        qNameParser.parse("|ab");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheMoreThenTheeCanNOTBePipe() throws IllegalNameException{
        qNameParser.parse("|abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheTheeCanNOTBeWhiteSpace_2() throws IllegalNameException{
        qNameParser.parse("\nab");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheMoreThenTheeCanNOTBeWhiteSpace_2() throws IllegalNameException{
        qNameParser.parse("\nabc");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheTheeCanNOTBeWhiteSpace_3() throws IllegalNameException{
        qNameParser.parse("\rab");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstCharOfTheMoreThenTheeCanNOTBeWhiteSpace_3() throws IllegalNameException{
        qNameParser.parse("\rabc");
    }

    //checking last symbols of the three or more symbols simplename

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBeSlesh() throws IllegalNameException{
        qNameParser.parse("ab/");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBeSlesh() throws IllegalNameException{
        qNameParser.parse("abc/");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBeColon() throws IllegalNameException{
        qNameParser.parse("ab:");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBeColon() throws IllegalNameException{
        qNameParser.parse("abc:");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBeLeftSquareBracket() throws IllegalNameException{
        qNameParser.parse("ab[");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBeLeftSquareBracket() throws IllegalNameException{
        qNameParser.parse("abc[");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBeRightSquareBracket() throws IllegalNameException{
        qNameParser.parse("ab]");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBeRightSquareBracket() throws IllegalNameException{
        qNameParser.parse("abc]");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBeAsterisk() throws IllegalNameException{
        qNameParser.parse("ab*");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBeAsterisk() throws IllegalNameException{
        qNameParser.parse("abc*");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBeApostrophe() throws IllegalNameException{
        qNameParser.parse("ab'");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBeApostrophe() throws IllegalNameException{
        qNameParser.parse("abc'");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBeQuote() throws IllegalNameException{
        qNameParser.parse("ab\"");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBeQuote() throws IllegalNameException{
        qNameParser.parse("abc\"");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBePipe() throws IllegalNameException{
        qNameParser.parse("ab|");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBePipe() throws IllegalNameException{
        qNameParser.parse("abc|");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBeWhiteSpace_1() throws IllegalNameException{
        qNameParser.parse("ab ");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBeWhiteSpace_1() throws IllegalNameException{
        qNameParser.parse("abc ");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBeWhiteSpace_2() throws IllegalNameException{
        qNameParser.parse("ab\n");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBeWhiteSpace_2() throws IllegalNameException{
        qNameParser.parse("abc\n");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheTheeCanNOTBeWhiteSpace_3() throws IllegalNameException{
        qNameParser.parse("ab\r");
    }

    @Test(expected = IllegalNameException.class)
    public void testLastCharOfTheMoreThenTheeCanNOTBeWhiteSpace_3() throws IllegalNameException{
        qNameParser.parse("abc\r");
    }

    @Test
    public void testFirstAndLastCharOfTheTheeCanBeAnyUnicodeSymbolsExceptWRONG_SEQUENCE_NON_SPACE()
            throws IllegalNameException{
        qName = qNameParser.parse("aaa");
        assertTrue(qName.getLocalName().equals("aaa"));
    }

    @Test
    public void  testFirstAndLastCharOfTheMoreThenTreeCanBeAnyUnicodeSymbolsExceptWRONG_SEQUENCE_NON_SPACE()
            throws IllegalNameException{
        qName = qNameParser.parse("aaaa");
        assertTrue(qName.getLocalName().equals("aaaa"));
    }

    //checking all symbols except first and last of simplename. The simplename has three or more symbols.

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeSlesh_1() throws IllegalNameException{
        qNameParser.parse("a/b");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeSlesh_2() throws IllegalNameException{
        qNameParser.parse("ab/c");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeColon_1() throws IllegalNameException{
        qNameParser.parse(":ab");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeColon_2() throws IllegalNameException{
        qNameParser.parse(":abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeLeftSquareBracket_1() throws IllegalNameException{
        qNameParser.parse("a[b");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeLeftSquareBracket_2() throws IllegalNameException{
        qNameParser.parse("ab[c");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeRightSquareBracket_1() throws IllegalNameException{
        qNameParser.parse("a]b");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeRightSquareBracket_2() throws IllegalNameException{
        qNameParser.parse("ab]c");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeAsterisk_1() throws IllegalNameException{
        qNameParser.parse("a*b");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeAsterisk_2() throws IllegalNameException{
        qNameParser.parse("ab*c");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeApostrophe_1() throws IllegalNameException{
        qNameParser.parse("a'b");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeApostrophe_2() throws IllegalNameException{
        qNameParser.parse("ab'c");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeQuote_1() throws IllegalNameException{
        qNameParser.parse("a\"b");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeQuote_2() throws IllegalNameException{
        qNameParser.parse("ab\"c");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBePipe_1() throws IllegalNameException{
        qNameParser.parse("a|b");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBePipe_2() throws IllegalNameException{
        qNameParser.parse("ab|c");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeWhiteSpaceExceptEasySpace_1()
            throws IllegalNameException{
        qNameParser.parse("a\nb");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeWWhiteSpaceExceptEasySpace_2()
            throws IllegalNameException{
        qNameParser.parse("ab\nc");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeWhiteSpaceExceptEasySpace_3()
            throws IllegalNameException{
        qNameParser.parse("a\rb");
    }

    @Test(expected = IllegalNameException.class)
    public void testAllSymbolsExceptFirstAndLastCanNOTBeWhiteSpaceExceptEasySpace_4()
            throws IllegalNameException{
        qNameParser.parse("abc\r");
    }
    @Test
    public void testAllSymbolsExceptFirstAndLastCanBeEasySpace_1() throws IllegalNameException{
        qName = qNameParser.parse("a b");
        assertTrue(qName.getLocalName().equals("a b"));
    }

    @Test
    public void testAllSymbolsExceptFirstAndLastCanBeEasySpace_3() throws IllegalNameException{
        qName = qNameParser.parse("ab c d");
        assertTrue(qName.getLocalName().equals("ab c d"));
    }

    @Test
    public void testAllSymbolsExceptFirstAndLastCanBeEasySpace_4() throws IllegalNameException{
        qName = qNameParser.parse("ab   d");
        assertTrue(qName.getLocalName().equals("ab   d"));
    }
}
