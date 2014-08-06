package com.codenvy.testtask.qname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses line to class QName
 * @author Created by Andrienko Alexander on 16.07.2014.
 * @version 0.3
 */
public class QNameParser {
    /**
     * This method parses line to class QName
     * @param line this is line must be parse to class QName
     * @return result of parsing line to class QName
     * @throws IllegalNameException
     */
    QName parse(String line) throws IllegalNameException {
        checkingForEmptiness(line);
        QName result = new QName();
        if (!isFullName(line)) {
            validSimpleName(line);
            result.setLocalName(line);
            return result;
        } else {
            validAndParsePrefixedName(line, result);
        }
        return result;
    }

    /**
    * This method checks line is full name.
    * Prefixed name separated by symbol ':'.
    * Order for checking prefixedname ::= prefix ':' localname
    * @param line this is line must be parse to class QName
    * @throws IllegalNameException
    */
    private boolean isFullName(String line) throws IllegalNameException{
        Pattern pattern = Pattern.compile("[:]+");//':' is exist at least one time
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    /**
     * This method checks line is null or line equals char ''.
     * If line is empty throws IllegalNameException
     * @param line this is line must be parse to class QName
     * @throws IllegalNameException
     */
    private void checkingForEmptiness(String line) throws IllegalNameException {
        if (line == null) {
            IllegalNameException e = new IllegalNameException();
            e.initCause(new NullPointerException());
            throw e;
        }
        if (line.length() == 0) {
            throw new IllegalNameException("Line is empty.");
        }
    }

    /**
     * This method does verification of rightness Simple Name.
     * Order for checking:
     * simplename ::=onecharsimplename | twocharsimplename | threeormorecharname
     * onecharsimplename ::= (* Any Unicode character except:
     * '.', '/', ':', '[', ']', '*',
     * ''', '"', '|' or any whitespace
     * character *)
     * twocharsimplename ::= '.' onecharsimplename | onecharsimplename '.' |onecharsimplename onecharsimplename
     * threeormorecharname ::= nonspace string nonspace
     * string ::= char | string char
     * char ::= nonspace | ' '
     * nonspace ::= (* Any Unicode character except: '/', ':', '[', ']', '*', ''', '"', '|'
     * or any whitespace character *)
     * @param line this is line must be parse to class QName
     * @throws IllegalNameException
     */
    private void validSimpleName(String line) throws IllegalNameException {
        switch (line.length()) {
            case 1:
                linesConsistOfOneCharSimpleName(line);
                return;
            case 2:
                  if (line.equals("..")) {
                      throw new IllegalNameException("double ':'");
                  } else {
                      if (line.charAt(0) == '.' || line.charAt(1) == '.') {//check first and second chars of line
                          String checkLine = line.replace(".", "");
                          linesConsistOfOneCharSimpleName(checkLine);
                      } else {
                          linesConsistOfOneCharSimpleName(line);
                      }
                      return;
                  }
            default:// if line.length >= 3
                validLineWithTreeOrMoreChar(line);
            break;
        }
    }

    /**
     * This method checks line of localname
     * @param localName this is parameter is line localname
     * @throws IllegalNameException
     */
    private void validLocalName(String localName) throws IllegalNameException {
        checkingForEmptiness(localName);
        if (localName.length() > 0 && localName.length() <= 2) {
            linesConsistOfNonSpace(localName);
            return;
        }
        validLineWithTreeOrMoreChar(localName);// if line.length >= 3
    }

    /**
     * This method realizes order of parsing:
     * threeormorecharname ::= nonspace string nonspace
     * string ::= char | string char
     * char ::= nonspace | ' '
     * nonspace ::= (* Any Unicode character except: '/', ':', '[', ']', '*', ''', '"', '|'
     * or any whitespace character *)
     * @param string this line must be tested for a rightness
     * @throws IllegalNameException
     */
    private void validLineWithTreeOrMoreChar(String string) throws IllegalNameException {
        int sizeLine = string.length();
        linesConsistOfOneCharSimpleName(string.substring(0, 1));//get and check first symbol
        linesConsistOfOneCharSimpleName(string.substring(sizeLine - 1, sizeLine));//get and check last symbol
        String line = string.substring(1, sizeLine - 1);//get all symbols between firs and last chars
        checkLineWithHelpWrongSequence(line, Constant.WRONG_SEQUENCE_NON_SPACE_WITHOUT_SPACE);
    }

    /**
     * This method parses line prefixedname to prefix and localname
     * @param line this is line must be parse to class QName
     * @param result this is link to object QName
     * @throws IllegalNameException
     */
    private void validAndParsePrefixedName(String line, QName result) throws IllegalNameException {
        String prefix;
        String localName;
        Pattern pattern = Pattern.compile("[:?+]");
        Matcher matcher = pattern.matcher(line);
        int start = 0;
        while (matcher.find()) {
                start = matcher.start();
        }
        prefix = line.substring(0, start);
        localName = line.substring(start + 1, line.length());
        validXMLName(prefix);
        validLocalName(localName);
        result.setLocalName(localName);
        result.setPrefix(prefix);
    }

    /**
     * This method checks line. Line mustn't has WRONG_NON_SPACE_WITHOUT_WHITE_SPACE.
     * @param line must be tested for a rightness
     * @throws IllegalNameException
     */
    private void linesConsistOfNonSpace(String line) throws IllegalNameException {
        checkLineWithHelpWrongSequence(line, Constant.WRONG_SEQUENCE_NON_SPACE);
    }

    /**
     * This method checks line. Line mustn't has WRONG_ONE_CHAR_SIMPLE_NAME_WITHOUT_WHITE_SPACE.
     * @param line must be tested for a rightness
     * @throws IllegalNameException
     */
    private void linesConsistOfOneCharSimpleName(String line) throws IllegalNameException {
        checkLineWithHelpWrongSequence(line, Constant.WRONG_SEQUENCE_ONE_CHAR_SIMPLE_NAME);
    }

    /**
     * This method check line. Line mustn't has wrong chars from WRONG_SEQUENCE_NON_SPACE.
     * @param line must be tested for a rightness
     * @param wrongSequence "wrong" symbols
     * @throws IllegalNameException
     */
    private void checkLineWithHelpWrongSequence(String line, String wrongSequence) throws IllegalNameException {
        Pattern pattern = Pattern.compile("[" + wrongSequence + "]");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            throw new IllegalNameException();
        }
        /*special check for symbol '[' and ']',
        * which classPattern can't check, because it's separator symbol for finding class symbols.
        */
        for (char elem: Constant.SPECIAL_CHECK.toCharArray()) {
            if (line.contains(String.valueOf(elem))) {
                throw new IllegalNameException();
            }
        }
    }

    /**
     * This method check line. Line must has right chars from rightSequence.
     * @param line must be tested for a rightness
     * @param rightSequence "right" symbols
     * @throws IllegalNameException
     */
    private void checkLineWithHelpRightSequence(String line, String rightSequence)
            throws IllegalNameException {
        Pattern pattern = Pattern.compile("[" + rightSequence + "]" + "{" + line.length() + "}");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            throw new IllegalNameException("line has invalid symbols");
        }
    }

    /**
     * This methods checks xml name
     * @param xmlName simple xml name for checking
     * @throws IllegalNameException
     */
    private void validXMLName(String xmlName) throws IllegalNameException {
        checkingForEmptiness(xmlName);
        checkFirstCharOfXmlName(xmlName);
        checkSecondAndNextCharsOfXmlName(xmlName);
    }

    /**
     * This methods checks first char of xmlname
     * @param xmlName simple xml name for checking
     * @throws IllegalNameException
     */
    private void checkFirstCharOfXmlName(String xmlName) throws IllegalNameException {
        checkLineWithHelpRightSequence(xmlName.substring(0, 1), Constant.FIRST_CHAR_XML_NAME);
    }

    /**
     * This methods checks all chars of xmlname except first char
     * @param xmlName simple xml name for checking
     * @throws IllegalNameException
     */
    private void checkSecondAndNextCharsOfXmlName(String xmlName) throws IllegalNameException {
        checkLineWithHelpRightSequence(xmlName, Constant.SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME);
    }
}