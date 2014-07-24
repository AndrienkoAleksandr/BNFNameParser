package com.codenvy.testtask.qname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses line to class QName
 * @author Created by Andrienko Alexander on 16.07.2014.
 * @version 0.3
 */
public class QNameParser {

    private boolean isPrefixedName;

    public QNameParser() {
        isPrefixedName = false;
    }

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
    * This method checks line is prefixed name or not.
    * Prefixed name separated by symbol ':'.
    * Order for checking prefixedname ::= prefix ':' localname/
    * @param line this is line must be parse to class QName
    * @throws IllegalNameException
    */
    private boolean isFullName(String line) throws IllegalNameException{
        Pattern pattern = Pattern.compile("[:]+");//':' is exist at least one time
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            //todo this check is
//            if (line.length() <= 2) { //if line is ':' or 'X:' or ':X'
//                throw new IllegalNameException();
//            }
            isPrefixedName = true;
            return true;
        }
        return false;
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
        if (line.length() == 0 || line.equals("")) {
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
                      if (line.charAt(0) == '.' || line.charAt(1) == '.') {
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
        linesConsistOfOneCharSimpleName(string.substring(0, 1));
        linesConsistOfOneCharSimpleName(string.substring(sizeLine - 1, sizeLine));
        String line = string.substring(1, sizeLine - 1);
        String wrongSequence = Constant.WRONG_NON_SPACE_CHAR
                + Constant.WHITE_SPACE;
        checkLineWithHelpWrongSequence(line, wrongSequence);
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
        validPrefix(prefix);
        validLocalName(localName);
        result.setLocalName(localName);
        result.setPrefix(prefix);
    }

    /**
     * This method checks prefix. Line must be valid xml name.
     * @param line xml name
     * @throws IllegalNameException
     */
    private void validPrefix(String line) throws IllegalNameException {
        validXMLName(line);
    }

    /**
     * This method checks line. Line mustn't has WRONG_NON_SPACE_CHAR.
     * @param line must be tested for a rightness
     * @throws IllegalNameException
     */
    private void linesConsistOfNonSpace(String line) throws IllegalNameException {
        String wrongSequence = Constant.WRONG_NON_SPACE_CHAR + Constant.WHITE_SPACE + Constant.SPACE;
        checkLineWithHelpWrongSequence(line, wrongSequence);
    }

    /**
     * This method checks line. Line mustn't has WRONG_ONE_CHAR_SIMPLE_NAME.
     * @param line must be tested for a rightness
     * @throws IllegalNameException
     */
    private void linesConsistOfOneCharSimpleName(String line) throws IllegalNameException {
        String wrongSequence = Constant.WRONG_ONE_CHAR_SIMPLE_NAME + Constant.WHITE_SPACE + Constant.SPACE;
        checkLineWithHelpWrongSequence(line, wrongSequence);
    }

    /**
     * This method check line. Line mustn't has wrong chars from wrongSequence.
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
        //todo one element!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
    private void checkLineWithHelpRightSequence(String line, String rightSequence) throws IllegalNameException {
        Pattern pattern = Pattern.compile("[" + rightSequence + "]" + "{" + line.length() + "}");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            throw new IllegalNameException("line has invalid symbols");
        }
    }

    //todo
    public static void main(String[] args) throws IllegalNameException {
        QNameParser qNameParser = new QNameParser();
        qNameParser.checkLineWithHelpRightSequence("Adf", Constant.LETTER + "_:");
        qNameParser.checkLineWithHelpRightSequence("adJ", "a-zJ");
        qNameParser.validXMLName("_ere r");
    }

    /**
     * This methods checks xml name
     * @param xmlName simple xml name for checking
     * @throws IllegalNameException
     */
    private void validXMLName(String xmlName) throws IllegalNameException {
        checkingForEmptiness(xmlName);
        checkFirstSymbolXML(xmlName);
        checkLineWithHelpRightSequence(xmlName.substring(1, xmlName.length()), Constant.NAME_CHAR);
    }

    /**
     * This methods checks first char of xml name
     * @param xmlName simple xml name for checking
     * @throws IllegalNameException
     */
    private void checkFirstSymbolXML(String xmlName) throws IllegalNameException {
        checkLineWithHelpRightSequence(xmlName.substring(0, 1), Constant.FIRST_CHAR_XML_NAME);
    }
}
