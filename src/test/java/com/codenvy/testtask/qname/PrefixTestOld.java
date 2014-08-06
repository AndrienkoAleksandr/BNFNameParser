package com.codenvy.testtask.qname;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Created by Andrienko Alexander on 25.07.2014.
 * @version 0.3
 * Unit test for testing of QNameParser's prefix
 */
public class PrefixTestOld {

    private QNameParser qNameParser;
    private QName qName;

    static final int maxIndexSymbolXMLName = 0xD7A3;

    static final String RIGHT_SYMBOLS_FOR_FIRST_CHAR_XML_NAME =
            CreatorOfRightSequenceOld.RIGHT_SYMBOLS_FOR_FIRST_CHAR_XML_NAME;

    static final String RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME =
            CreatorOfRightSequenceOld.RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME;

    @Before
    public void init() {
        qNameParser = new QNameParser();
        qName = null;
    }

    @Test(expected = IllegalNameException.class)
    public void testLineWithMore3CharAndTwoPoints1() throws IllegalNameException {
        qNameParser.parse(":abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testLineWithMore3CharAndTwoPoints2() throws IllegalNameException {
        qNameParser.parse("abc:");
    }
    @Test
    public void checkFirstCharXmlName1() throws IllegalNameException {
        for (char symbol: RIGHT_SYMBOLS_FOR_FIRST_CHAR_XML_NAME.toCharArray()) {
            qName = qNameParser.parse(symbol + ":localName");
            assertTrue(qName.getLocalName().equals("localName") && qName.getPrefix().equals(String.valueOf(symbol)));
        }
    }

    @Test
    public void checkFirstCharOfXmlName2() {
        int count = 0;
        for (int i = 0; i <= maxIndexSymbolXMLName; i++) {
            try {
                char symbol = (char) i;
                qNameParser.parse(symbol + ":localName");
                if (RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.contains(String.valueOf(symbol))) {
                    count++;
                } else {
                    fail("wrong symbol without exception " + symbol);
                }
            } catch (IllegalNameException e) {
            }
        }
        assertTrue(RIGHT_SYMBOLS_FOR_FIRST_CHAR_XML_NAME.length() == count
                && count == RIGHT_SYMBOLS_FOR_FIRST_CHAR_XML_NAME.length());
    }

    @Test
    public void checkSecondSymbolOfXmlName1() throws IllegalNameException {
        for (char symbol: RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.toCharArray()) {
            qName = qNameParser.parse("a" + symbol + ":localName");
            assertTrue(qName.getLocalName().equals("localName") && qName.getPrefix().equals("a" + symbol));
        }
    }

    @Test
    public void checkSecondSymbolOfXmlName2() {
        int count = 0;
        for (int i = 0; i <= maxIndexSymbolXMLName; i++) {
            try {
                char symbol = (char) i;
                qNameParser.parse("A" + symbol + ":localName");
                if (RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.contains(String.valueOf(symbol))) {
                    count++;
                } else {
                    fail("wrong symbol without exception " + symbol);
                }
            } catch (IllegalNameException e) {
            }
        }
        assertTrue(RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.length() == count
                && count == RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.length());
    }

    @Test(expected = IllegalNameException.class)
    public void checkSecondSymbolOfXmlName3() throws IllegalNameException {
            qNameParser.parse("a[" + ":localName");
    }

    @Test(expected = IllegalNameException.class)
    public void checkSecondSymbolOfXmlName4() throws IllegalNameException {
            qNameParser.parse("a]" + ":localName");
    }

    @Test
    public void checkThirdSymbolOfXmlName1() {
    int count = 0;
    for (int i = 0; i <= maxIndexSymbolXMLName; i++) {
        try {
            char symbol = (char) i;
            qNameParser.parse("AB" + symbol + ":localName");
            if (RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.contains(String.valueOf(symbol))) {
                count++;
            } else {
                fail("wrong symbol without exception " + symbol);
            }
        } catch (IllegalNameException e) {
        }
    }
    assertTrue(RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.length() == count
            && count == RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.length());
    }

    @Test
    public void checkThirdSymbolOfXmlName2() throws IllegalNameException {
        for (char symbol: RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.toCharArray()) {
            qName = qNameParser.parse("ab" + symbol + ":localName");
            assertTrue(qName.getLocalName().equals("localName") && qName.getPrefix().equals("ab" + symbol));
        }
    }

    @Test
    public void checkThirdSymbolOfXmlName3() {
        int count = 0;
        for (int i = 0; i <= maxIndexSymbolXMLName; i++) {
            try {
                char symbol = (char) i;
                qNameParser.parse("A" + symbol + "B:localName");
                if (RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.contains(String.valueOf(symbol))) {
                    count++;
                } else {
                    fail("wrong symbol without exception " + symbol);
                }
            } catch (IllegalNameException e) {
            }
        }
        assertTrue(RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.length() == count
                && count == RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.length());
    }

    @Test
    public void checkThirdSymbolOfXmlName4() throws IllegalNameException {
        for (char symbol: RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME.toCharArray()) {
            qName = qNameParser.parse("ab" + symbol + "c:localName");
            assertTrue(qName.getLocalName().equals("localName") && qName.getPrefix().equals("ab" + symbol + "c"));
        }
    }

    @Test(expected = IllegalNameException.class)
    public void checkThirdSymbolOfXmlName5() throws IllegalNameException {
        qNameParser.parse("@ab:localname");
    }

    @Test(expected = IllegalNameException.class)
    public void checkThirdSymbolOfXmlName6() throws IllegalNameException {
        qNameParser.parse("a@b:localname");
    }
}
