package com.codenvy.testtask.qname;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * @author Created by Andrienko Alexander on 6.8.2014.
 * @version 0.4
 *This is test for class QNameParser.
 * Test checks rightness of parsing prefix.
 */
public class PrefixTest {
    private QNameParser qNameParser;
    private QName qName;

    @Before
    public void init() {
        qNameParser = new QNameParser();
        qName = null;
    }

    @Test(expected = IllegalNameException.class)
    public void testPrefixCanNOTBeNull() throws IllegalNameException {
        qNameParser.parse(null);
    }

    @Test(expected = IllegalNameException.class)
    public void testPrefixCanNOTBeEmpty() throws IllegalNameException {
        qNameParser.parse(":localname");
    }

    @Test(expected = IllegalNameException.class)
    public void testPrefixCanNOTBeWithoutLocalName() throws IllegalNameException {
        qNameParser.parse("abc:");
    }

    @Test
    public void testPrefixCanConsistOfOnlyOneSymbol() throws IllegalNameException {
        qName = qNameParser.parse("a:localname");
        assertTrue(qName.getPrefix().equals("a"));
    }

    /*
    Test for first symbol of Prefix
    */

    @Test
    public void testFirsSymbolCanBeTwoPoints() throws IllegalNameException {
        qName = qNameParser.parse(":abc:localname");
        assertTrue(qName.getPrefix().equals(":abc"));
    }

    @Test
    public void testFirsSymbolCanBeUnderscore() throws IllegalNameException {
        qName = qNameParser.parse("_abc:localname");
        assertTrue(qName.getPrefix().equals("_abc"));
    }

    @Test(expected = IllegalNameException.class)
    public void testFirsSymbolCantBeAT_SIGN() throws IllegalNameException {
        qNameParser.parse("@abc:localname");
    }

    //BASE_CHAR

    @Test
    public void testFirstSymbolCanBeBASE_CHAR_1() throws IllegalNameException {
        qName = qNameParser.parse('\u0041'+ "abc:localname");
        assertTrue(qName.getPrefix().equals('\u0041'+ "abc"));
    }

    @Test
    public void testFirstSymbolCanBeBASE_CHAR_2() throws IllegalNameException {
        qName = qNameParser.parse('\u0050'+ "abc:localname");
        assertTrue(qName.getPrefix().equals('\u0050'+ "abc"));
    }

    @Test
    public void testFirstSymbolCanBeBASE_CHAR_3() throws IllegalNameException {
        qName = qNameParser.parse('\u005A'+ "abc:localname");
        assertTrue(qName.getPrefix().equals('\u005A'+ "abc"));
    }

    //IDEOGRAPHIC

    @Test
    public void testFirstSymbolCanBeIDEOGRAPHIC_1() throws IllegalNameException {
        qName = qNameParser.parse('\u3007'+ "abc:localname");
        assertTrue(qName.getPrefix().equals('\u3007'+ "abc"));
    }

    @Test
    public void testFirstSymbolCanBeIDEOGRAPHIC_2() throws IllegalNameException {
        qName = qNameParser.parse('\u3021'+ "abc:localname");
        assertTrue(qName.getPrefix().equals('\u3021'+ "abc"));
    }

    @Test
    public void testFirstSymbolCanBeIDEOGRAPHIC_3() throws IllegalNameException {
        qName = qNameParser.parse('\u9FA5'+ "abc:localname");
        assertTrue(qName.getPrefix().equals('\u9FA5'+ "abc"));
    }

    //symbols between right BASE_CHAR Symbols

    @Test(expected = IllegalNameException.class)
    public void testFirstSymbolCanNOTBeCharBetweenBASE_CHAR_Sequence_1() throws IllegalNameException {
        qNameParser.parse('\u0060'+ "abc:localname");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstSymbolCanNOTBeCharBetweenBASE_CHAR_Sequence_2() throws IllegalNameException {
        qNameParser.parse('\u00D7'+ "abc:localname");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstSymbolCanNOTBeCharBetweenBASE_CHAR_Sequence_3() throws IllegalNameException {
        qNameParser.parse('\u00F7'+ "abc:localname");
    }

    //symbols between right IDEOGRAPHIC Symbols

    @Test(expected = IllegalNameException.class)
    public void testFirstSymbolCanNOTBeCharBetweenIDEOGRAPHIC_Sequence_1() throws IllegalNameException {
        qNameParser.parse('\u3008'+ "abc:localname");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstSymbolCanNOTBeCharBetweenIDEOGRAPHIC_Sequence_2() throws IllegalNameException {
        qNameParser.parse('\u9FA6'+ "abc:localname");
    }

    @Test(expected = IllegalNameException.class)
    public void testFirstSymbolCanNOTBeCharBetweenIDEOGRAPHIC_Sequence_3() throws IllegalNameException {
        qNameParser.parse('\u3030'+ "abc:localname");
    }

    /*
    Test for all symbols of Prefix except first
    */

    //!'@'
    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeAT_SIGN_1() throws IllegalNameException {
        qNameParser.parse("a@b:localname");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeAT_SIGN_2() throws IllegalNameException {
        qNameParser.parse("ab@:localname");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeAT_SIGN_3() throws IllegalNameException {
        qNameParser.parse("a@@:localname");
    }

    //'_'

    @Test
    public void testSecondAndNextSymbolsCanBeBottomUnderlining_1() throws IllegalNameException {
        qName = qNameParser.parse("a_bc:fgf");
        assertTrue(qName.getPrefix().equals("a_bc"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeBottomUnderlining_2() throws IllegalNameException {
        qName = qNameParser.parse("ab_c:fgf");
        assertTrue(qName.getPrefix().equals("ab_c"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeBottomUnderlining_3() throws IllegalNameException {
        qName = qNameParser.parse("abc_:fgf");
        assertTrue(qName.getPrefix().equals("abc_"));
    }

    //'-'

    @Test
    public void testSecondAndNextSymbolsCanBeDash_1() throws IllegalNameException {
        qName = qNameParser.parse("a-bc:fgf");
        assertTrue(qName.getPrefix().equals("a-bc"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeDash_2() throws IllegalNameException {
        qName = qNameParser.parse("ab-c:fgf");
        assertTrue(qName.getPrefix().equals("ab-c"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeDash_3() throws IllegalNameException {
        qName = qNameParser.parse("abc-:fgf");
        assertTrue(qName.getPrefix().equals("abc-"));
    }

    //'.'

    @Test
    public void testSecondAndNextSymbolsCanBePoint_1() throws IllegalNameException {
        qName = qNameParser.parse("a.bc:fgf");
        assertTrue(qName.getPrefix().equals("a.bc"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBePoint_2() throws IllegalNameException {
        qName = qNameParser.parse("ab.c:fgf");
        assertTrue(qName.getPrefix().equals("ab.c"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBePoint_3() throws IllegalNameException {
        qName = qNameParser.parse("abc.:fgf");
        assertTrue(qName.getPrefix().equals("abc."));
    }

    //':'

    @Test
    public void testSecondAndNextSymbolsCanBeColon_1() throws IllegalNameException {
        qName = qNameParser.parse("a:bc:fgf");
        assertTrue(qName.getPrefix().equals("a:bc"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeColon_2() throws IllegalNameException {
        qName = qNameParser.parse("ab:c:fgf");
        assertTrue(qName.getPrefix().equals("ab:c"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeColon_3() throws IllegalNameException {
        qName = qNameParser.parse("abc::fgf");
        assertTrue(qName.getPrefix().equals("abc:"));
    }

    //BASE_CHAR

    @Test
    public void testSecondAndNextSymbolsCanBeBASE_CHAR_1() throws IllegalNameException {
        qName = qNameParser.parse("a" + '\u0041' + "bc:fgf");
        assertTrue(qName.getPrefix().equals("a" + '\u0041' + "bc"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeBASE_CHAR_2() throws IllegalNameException {
        qName = qNameParser.parse("ab" + '\u0041' + "c:fgf");
        assertTrue(qName.getPrefix().equals("ab" + '\u0041' + "c"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeBASE_CHAR_3() throws IllegalNameException {
        qName = qNameParser.parse("abc" + '\u0041' + ":fgf");
        assertTrue(qName.getPrefix().equals("abc" + '\u0041'));
    }

    //IDEOGRAPHIC

    @Test
    public void testSecondAndNextSymbolsCanBeIDEOGRAPHIC_1() throws IllegalNameException {
        qName = qNameParser.parse("a" + '\u3007' + "bc:fgf");
        assertTrue(qName.getPrefix().equals("a" + '\u3007' + "bc"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeIDEOGRAPHIC_2() throws IllegalNameException {
        qName = qNameParser.parse("ab" + '\u3007' + "c:fgf");
        assertTrue(qName.getPrefix().equals("ab" + '\u3007' + "c"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeIDEOGRAPHIC_3() throws IllegalNameException {
        qName = qNameParser.parse("abc" + '\u3007' + ":fgf");
        assertTrue(qName.getPrefix().equals("abc" + '\u3007'));
    }

    //DIGIT

    @Test
     public void testSecondAndNextSymbolsCanBeDIGIT_1() throws IllegalNameException {
        qName = qNameParser.parse("a1bc:fgf");
        assertTrue(qName.getPrefix().equals("a1bc"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeDIGIT_2() throws IllegalNameException {
        qName = qNameParser.parse("ab1c:fgf");
        assertTrue(qName.getPrefix().equals("ab1c"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeDIGIT_3() throws IllegalNameException {
        qName = qNameParser.parse("abc1:fgf");
        assertTrue(qName.getPrefix().equals("abc1"));
    }

    //COMBINING_CHAR

    @Test
    public void testSecondAndNextSymbolsCanBeCOMBINING_CHAR_1() throws IllegalNameException {
        qName = qNameParser.parse("a" + '\u0300' + "bc:fgf");
        assertTrue(qName.getPrefix().equals("a" + '\u0300' + "bc"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeCOMBINING_CHAR_2() throws IllegalNameException {
        qName = qNameParser.parse("ab" + '\u0300' + "c:fgf");
        assertTrue(qName.getPrefix().equals("ab" + '\u0300' + "c"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeCOMBINING_CHAR_3() throws IllegalNameException {
        qName = qNameParser.parse("abc" + '\u0300' +":fgf");
        assertTrue(qName.getPrefix().equals("abc" + '\u0300'));
    }

    //EXTENDER

    @Test
    public void testSecondAndNextSymbolsCanBeEXTENDER_1() throws IllegalNameException {
        qName = qNameParser.parse("a" + '\u00B7' + "bc:fgf");
        assertTrue(qName.getPrefix().equals("a" + '\u00B7' + "bc"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeCEXTENDER_2() throws IllegalNameException {
        qName = qNameParser.parse("ab" + '\u00B7' + "c:fgf");
        assertTrue(qName.getPrefix().equals("ab" + '\u00B7' + "c"));
    }

    @Test
    public void testSecondAndNextSymbolsCanBeEXTENDER_3() throws IllegalNameException {
        qName = qNameParser.parse("abc" + '\u00B7' +":fgf");
        assertTrue(qName.getPrefix().equals("abc" + '\u00B7'));
    }


    ////////////////////////////////////////////////////////////////////

    //symbols between BASE_CHAR Symbols

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenBASE_CHAR_Sequence_1()
            throws IllegalNameException {
        qNameParser.parse("a" + '\u0040' + "bc:fgf");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenBASE_CHAR_Sequence_2()
            throws IllegalNameException {
        qNameParser.parse("ab" + '\u0040' + "c:fgf");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenBASE_CHAR_Sequence_3()
            throws IllegalNameException {
        qNameParser.parse("abc" + '\u0040' + ":fgf");
    }

    //symbols between IDEOGRAPHIC Symbols

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenIDEOGRAPHIC_Sequence_1()
            throws IllegalNameException {
        qNameParser.parse("a" + '\u9FA6' + "bc:fgf");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenIDEOGRAPHIC_Sequence_2()
            throws IllegalNameException {
        qNameParser.parse("ab" + '\u9FA6' + "c:fgf");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenIDEOGRAPHIC_Sequence_3()
            throws IllegalNameException {
        qNameParser.parse("abc" + '\u9FA6' + ":fgf");
    }

    //symbols between DIGIT Symbols

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenDIGIT_Sequence_1()
            throws IllegalNameException {
        qNameParser.parse("a" + '\u002F' + "bc:fgf");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenDIGIT_Sequence_2()
            throws IllegalNameException {
        qNameParser.parse("ab" + '\u002F' + "c:fgf");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenDIGIT_Sequence_3()
            throws IllegalNameException {
        qNameParser.parse("abc" + '\u002F' + ":fgf");
    }

    //symbols between COMBINING_CHAR Sequence

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenCOMBINING_CHAR_Sequence_1()
            throws IllegalNameException {
        qNameParser.parse("a" + '\u0346' + "bc:fgf");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenCOMBINING_CHAR_Sequence_2()
            throws IllegalNameException {
        qNameParser.parse("ab" + '\u0346' + "c:fgf");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenCOMBINING_CHAR_Sequence_3()
            throws IllegalNameException {
        qNameParser.parse("abc" + '\u0346' +":fgf");
    }

    //symbols between EXTENDER Sequence

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenEXTENDER_Sequence_1()
            throws IllegalNameException {
        qNameParser.parse("a" + '\u00B8' + "bc:fgf");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenCEXTENDER_Sequence_2()
            throws IllegalNameException {
        qNameParser.parse("ab" + '\u00B8' + "c:fgf");
    }

    @Test(expected = IllegalNameException.class)
    public void testSecondAndNextSymbolsCanNOTBeCharsBetweenEXTENDER_Sequence_3()
            throws IllegalNameException {
        qNameParser.parse("abc" + '\u00B8' +":fgf");
    }
}
