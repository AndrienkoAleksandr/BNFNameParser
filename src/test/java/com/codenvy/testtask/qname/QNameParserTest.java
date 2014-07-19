package com.codenvy.testtask.qname;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class QNameParserTest {

    private QNameParser qNameParser;
    private QName qName;

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
    public void testValidSimpleName() throws IllegalNameException {
        //simple name consist of one symbol
        qName = qNameParser.parse("a");
        assertTrue(qName.getLocalName().equals("a") && qName.getPrefix().equals(""));
        //simple name consist of two symbol
        qName = qNameParser.parse("bb");
        assertTrue(qName.getLocalName().equals("bb") && qName.getPrefix().equals(""));
        //simple name consist of tree symbol
        qName = qNameParser.parse("bbb");
        assertTrue(qName.getLocalName().equals("bbb") && qName.getPrefix().equals(""));
        //simple name consist of more than tree symbol
        qName = qNameParser.parse("bbcc");
        assertTrue(qName.getLocalName().equals("bbcc") && qName.getPrefix().equals(""));
    }

    @Test(expected = IllegalNameException.class)
    public void testInvalidSimpleName() throws IllegalNameException {
            
    }

}
