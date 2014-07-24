package com.codenvy.testtask.qname;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * @author Created by Andrienko Alexander on 16.07.2014.
 * @version 0.3
 * Unit test for QNameParser
 */
public class QNameParserTest {

    private QNameParser qNameParser;
    private QName qName;
    static final String All_WRONG_CHARS_FOR_NON_SPACE;
    static final String All_WRONG_CHARS_FOR_ONE_CHAR_SIMPLE_NAME;
    static final String All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS;
    static final String All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE;

    static {
        String allWrongSymbolsForNonSpaceWithoutSpace =
                Constant.SPECIAL_CHECK  +
                Constant.WHITE_SPACE +
                Constant.WRONG_NON_SPACE_CHAR;
        String allWrongSymbolsForNonSpace = allWrongSymbolsForNonSpaceWithoutSpace +
                Constant.SPACE;
        All_WRONG_CHARS_FOR_NON_SPACE = allWrongSymbolsForNonSpace;

        All_WRONG_CHARS_FOR_ONE_CHAR_SIMPLE_NAME = allWrongSymbolsForNonSpace + Constant.POINT;

        int index = allWrongSymbolsForNonSpace.indexOf(':');
        All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS =
                allWrongSymbolsForNonSpace.substring(0, index) +
                allWrongSymbolsForNonSpace.substring(index + 1, allWrongSymbolsForNonSpace.length());

        index = allWrongSymbolsForNonSpaceWithoutSpace.indexOf(':');
        All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE =
                allWrongSymbolsForNonSpaceWithoutSpace.substring(0, index) +
                allWrongSymbolsForNonSpaceWithoutSpace.substring(index + 1, allWrongSymbolsForNonSpaceWithoutSpace.length());
    }

    @Before
    public void init() {
        qNameParser = new QNameParser();
        qName = null;
    }

    //Check simplename with one symbols

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
    public void testValidNameWithOneSymbol() {
        int count = 0;
        for (char elem: All_WRONG_CHARS_FOR_ONE_CHAR_SIMPLE_NAME.toCharArray()) {
            try {
                qNameParser.parse(String.valueOf(elem));
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_ONE_CHAR_SIMPLE_NAME.length());
    }

    @Test
    public void testValidSimpleNameWithTwoSymbol() throws IllegalNameException {
        qName = qNameParser.parse("bb");
        assertTrue(qName.getLocalName().equals("bb") && qName.getPrefix().equals(""));
        qName = qNameParser.parse("b.");
        assertTrue(qName.getLocalName().equals("b.") && qName.getPrefix().equals(""));
        qName = qNameParser.parse(".b");
        assertTrue(qName.getLocalName().equals(".b") && qName.getPrefix().equals(""));
    }

    @Test(expected = IllegalNameException.class)
    public void testInvalidSimpleNameWithTwoSymbol1() throws IllegalNameException {
        qName = qNameParser.parse("..");
    }

    @Test
    public void testValidSimpleNameWithTwoSymbol1() {
        int count = 0;
        for (char elem : All_WRONG_CHARS_FOR_NON_SPACE.toCharArray()) {
            try {
                qNameParser.parse('a' + String.valueOf(elem));
            } catch (IllegalNameException e) {
                count++;
            } catch (Exception e) {
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE.length());
    }

    @Test
    public void testValidSimpleNameWithTwoSymbol2() {
        int count = 0;
        for (char elem : All_WRONG_CHARS_FOR_NON_SPACE.toCharArray()) {
            try {
                qNameParser.parse(String.valueOf(elem) + 'a');
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE.length());
    }

    //Check simplename with more then tree symbols

    @Test(expected = IllegalNameException.class)
    public void testSimpleNameWithInvalidChar1() throws IllegalNameException {
        qNameParser.parse(".abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testSimpleNameWithInvalidChar2() throws IllegalNameException {
        qNameParser.parse("abc.");
    }

    @Test(expected = IllegalNameException.class)
    public void testSimpleNameWithInvalidChar3() throws IllegalNameException {
        qNameParser.parse(".abc.");
    }

    @Test
    public void testSimpleNameWithOneDifferentInvalidChar4() {
        int count = 0;
        for (char elem : All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS.toCharArray()) {
            try {
                qNameParser.parse("abc" + elem);
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS.length());
    }

    @Test
    public void testSimpleNameWithOneDifferentInvalidChar5() {
        int count = 0;
        for (char elem : All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS.toCharArray()) {
            try {
                qNameParser.parse(elem + "abc");
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS.length());
    }

    @Test
    public void testSimpleNameWithOneDifferentInvalidChar6() {
        int count = 0;
        for (char elem : All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.toCharArray()) {
            try {
                qNameParser.parse("fde" + elem + "abc");
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.length());
    }

    @Test
    public void testSimpleNameWithOneDifferentInvalidChar7() {
        int count = 0;
        int size = All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.length();
        for (int i = 0, j = size - 1; i < size; i++, j--) {
            try {
                qNameParser.parse("abc" + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(i)  + "" +
                        + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(j));
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == size);
    }

    @Test
    public void testSimpleNameWithOneDifferentInvalidChar8() {
        int count = 0;
        int size = All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.length();
        for (int i = 0, j = size - 1; i < size; i++, j--) {
            try {
                qNameParser.parse(All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(i)  + "" +
                        + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(j) + "abc" );
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == size);
    }

    @Test
    public void testSimpleNameWithOneDifferentInvalidChar9() {
        int count = 0;
        int size = All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.length();
        for (int i = 0, j = size - 1; i < size; i++, j--) {
            try {
                qNameParser.parse("abc" + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(i) + "" +
                        + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(j) + "fde");
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == size);
    }

    @Test
    public void testValidSimpleNameWithTreeSymbol() throws IllegalNameException {
        qName = qNameParser.parse("bbb");
        assertTrue(qName.getLocalName().equals("bbb") && qName.getPrefix().equals(""));
    }

    @Test
    public void testValidSimpleNameWithMoreThenTreeSymbol1() throws IllegalNameException {
        qName = qNameParser.parse("bbcc");
        assertTrue(qName.getLocalName().equals("bbcc") && qName.getPrefix().equals(""));
    }

    @Test
    public void testValidSimpleNameWithMoreThenTreeSymbol2() throws IllegalNameException {
        qName = qNameParser.parse("bb cc");
        assertTrue(qName.getLocalName().equals("bb cc") && qName.getPrefix().equals(""));
    }

    @Test
    public void testValidSimpleNameWithMoreThenTreeSymbol3() throws IllegalNameException {
        qName = qNameParser.parse("bb cc dd");
        assertTrue(qName.getLocalName().equals("bb cc dd") && qName.getPrefix().equals(""));
    }

    @Test(expected = IllegalNameException.class)
    public void testInValidSimpleNameWithMoreThenTreeSymbol1() throws IllegalNameException {
        qName = qNameParser.parse("bb cc dd ");
    }

    @Test(expected = IllegalNameException.class)
    public void testInValidSimpleNameWithMoreThenTreeSymbol2() throws IllegalNameException {
        qName = qNameParser.parse(" bb cc dd");
    }

    @Test(expected = IllegalNameException.class)
    public void testInValidSimpleNameWithMoreThenTreeSymbol3() throws IllegalNameException {
        qName = qNameParser.parse(" bb cc dd ");
    }

    @Test
    public void testValidSimpleNameWithMoreThenTreeSymbol7() throws IllegalNameException {
        qName = qNameParser.parse("b bc.cdd");
    }

    //test localname

    @Test
     public void testValidLocalNameWithOneOreTwoChar() throws IllegalNameException {
        qName = qNameParser.parse("prefix:a");
        qName = qNameParser.parse("prefix:.");
        qName = qNameParser.parse("prefix:aa");
        qName = qNameParser.parse("prefix:.a");
        qName = qNameParser.parse("prefix:..");
        qName = qNameParser.parse("prefix:a.");
    }

    @Test
     public void testValidLocalNameWithOneChar() {
        int count = 0;
        for (char elem: All_WRONG_CHARS_FOR_NON_SPACE.toCharArray()) {
            try {
                qName = qNameParser.parse("prefix:" + String.valueOf(elem));
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE.length());
    }

    @Test
    public void testValidLocalNameWithTwoChars2() {
        int count = 0;
        for (char elem: All_WRONG_CHARS_FOR_NON_SPACE.toCharArray()) {
            try {
                qName = qNameParser.parse("prefix:" + 'a' +String.valueOf(elem));
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE.length());
    }

    @Test
    public void testInValidLocalNameWithTwoChars3() {
        int count = 0;
        for (char elem: All_WRONG_CHARS_FOR_NON_SPACE.toCharArray()) {
            try {
                qName = qNameParser.parse("prefix:" + String.valueOf(elem) + 'a');
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE.length());
    }

//    @Test
//    public void testInValidLocalNameWithTwoChars4() {
//        int count = 0;
//        int size = All_WRONG_CHARS_FOR_NON_SPACE.length;
//        for (int i = 0, j = size - 1; i < size; i++, j--) {
//            try {
//                qName = qNameParser.parse("prefix:" +
//                        String.valueOf(All_WRONG_CHARS_FOR_NON_SPACE[i]) +
//                        String.valueOf(All_WRONG_CHARS_FOR_NON_SPACE[j]));
//            } catch (IllegalNameException e) {
//                count++;
//            }
//        }
//        assertTrue(count == size);
//    }

    //test localname.length > 3

    @Test(expected = IllegalNameException.class)
    public void testLocalNameWithInvalidChar1() throws IllegalNameException {
        qNameParser.parse("prefix:.abc");
    }

    @Test(expected = IllegalNameException.class)
    public void testLocalNameWithInvalidChar2() throws IllegalNameException {
        qNameParser.parse("prefix:abc.");
    }

    @Test(expected = IllegalNameException.class)
    public void testLocalNameWithInvalidChar3() throws IllegalNameException {
        qNameParser.parse("prefix:.abc.");
    }

    @Test
    public void testLocalNameWithOneDifferentInvalidChar4() {
        int count = 0;
        for (char elem : All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS.toCharArray()) {
            try {
                qNameParser.parse("prefix:abc" + elem);
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS.length());
    }

    @Test
    public void testLocalNameWithOneDifferentInvalidChar5() {
        int count = 0;
        for (char elem : All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS.toCharArray()) {
            try {
                qNameParser.parse("prefix:" + elem + "abc");
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS.length());
    }

    @Test
    public void testLocalNameWithOneDifferentInvalidChar6() {
        int count = 0;
        for (char elem : All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.toCharArray()) {
            try {
                qNameParser.parse("prefix:fde" + elem + "abc");
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.length());
    }

    @Test
    public void testLocalNameWithOneDifferentInvalidChar7() {
        int count = 0;
        int size = All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.length();
        for (int i = 0, j = size - 1; i < size; i++, j--) {
            try {
                qNameParser.parse("prefix:abc" + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(i)  + "" +
                        + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(j));
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == size);
    }

    @Test
    public void testLocalNameWithOneDifferentInvalidChar8() {
        int count = 0;
        int size = All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.length();
        for (int i = 0, j = size - 1; i < size; i++, j--) {
            try {
                qNameParser.parse("prefix:" + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(i)  + "" +
                        + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(j) + "abc" );
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == size);
    }

    @Test
    public void testLocalNameWithOneDifferentInvalidChar9() {
        int count = 0;
        int size = All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.length();
        for (int i = 0, j = size - 1; i < size; i++, j--) {
            try {
                qNameParser.parse("prefix:abc" + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(i) + "" +
                        + All_WRONG_CHARS_FOR_NON_SPACE_WITHOUT_TWO_POINTS_AND_SPACE.charAt(j) + "fde");
            } catch (IllegalNameException e) {
                count++;
            }
        }
        assertTrue(count == size);
    }

    @Test
    public void testValidLocalNameWithTreeSymbol() throws IllegalNameException {
        qName = qNameParser.parse("prefix:bbb");
        assertTrue(qName.getLocalName().equals("bbb") && qName.getPrefix().equals("prefix"));
    }

    @Test
    public void testValidLocalNameWithMoreThenTreeSymbol1() throws IllegalNameException {
        qName = qNameParser.parse("prefix:bbcc");
        assertTrue(qName.getLocalName().equals("bbcc") && qName.getPrefix().equals("prefix"));
    }

    @Test
    public void testValidLocalNameWithMoreThenTreeSymbol2() throws IllegalNameException {
        qName = qNameParser.parse("prefix:bb cc");
        assertTrue(qName.getLocalName().equals("bb cc") && qName.getPrefix().equals("prefix"));
    }

    @Test
    public void testValidLocalNameWithMoreThenTreeSymbol3() throws IllegalNameException {
        qName = qNameParser.parse("prefix:bb cc dd");
        assertTrue(qName.getLocalName().equals("bb cc dd") && qName.getPrefix().equals("prefix"));
    }

    @Test(expected = IllegalNameException.class)
    public void testInValidLocalNameWithMoreThenTreeSymbol1() throws IllegalNameException {
        qName = qNameParser.parse("prefix:bb cc dd ");
    }

    @Test(expected = IllegalNameException.class)
    public void testInValidLocalNameWithMoreThenTreeSymbol2() throws IllegalNameException {
        qName = qNameParser.parse("prefix: bb cc dd");
    }

    @Test(expected = IllegalNameException.class)
    public void testInValidLocalNameWithMoreThenTreeSymbol3() throws IllegalNameException {
        qName = qNameParser.parse("prefix: bb cc dd ");
    }

    @Test
    public void testValidLocalNameWithMoreThenTreeSymbol7() throws IllegalNameException {
        qName = qNameParser.parse("prefix:b bc.cdd");
    }
}
