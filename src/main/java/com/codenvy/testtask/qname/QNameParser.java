package com.codenvy.testtask.qname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses line to class QName
 * @author Created by Andrienko Alexander on 16.07.2014.
 * @version 0.2
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
        if (!isPrefixedName(line)) {
            validSimpleName(line);
            result.setLocalName(line);
            return result;
        } else {
            validAndParsePrefixedName(line, result);
        }
        //todo line 30-31 need delete after completing project
        System.out.println("is valid " + line);
        return result;
    }

    /**
    * This method checks line is prefixed name or not.
    * Prefixed name separated by symbol ':'.
    * Order for checking prefixedname ::= prefix ':' localname/
    * @param line this is line must be parse to class QName
    * @throws IllegalNameException
    */
    private boolean isPrefixedName(String line) throws IllegalNameException{
        Pattern pattern = Pattern.compile("[:]+");//':' is exist at least one time
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            //todo this check is kostul
            if (line.length() <= 2) { //if line is ':' or 'X:' or ':X'
                throw new IllegalNameException();
            }
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
        if (line.equals("")) {
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
        String wrongSequence = String.valueOf(Constant.NON_SPACE_CHAR)
                + String.valueOf(Constant.WHITE_SPACE);
        linesConsistOfRightSequence(line, wrongSequence);
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
        //if one symbol ':' then split line to prefix and localName
        Pattern pattern = Pattern.compile("[:?+]");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            int start = matcher.start();
            prefix = line.substring(0, start);
            localName = line.substring(start + 1, line.length());
            if (matcher.find()) {
                throw new IllegalNameException();
            }
            validPrefix(prefix);
            validLocalName(localName);
            result.setLocalName(localName);
            result.setPrefix(prefix);
        }
    }

    //todo
    private void validPrefix(String line) {

    }

    /**
     * This method checks line. Line mustn't has NON_SPACE_CHAR.
     * @param line
     * @throws IllegalNameException
     */
    private void linesConsistOfNonSpace(String line) throws IllegalNameException {
        String wrongSequence = String.valueOf(Constant.NON_SPACE_CHAR)
                + String.valueOf(Constant.WHITE_SPACE) + Constant.SPACE;
        linesConsistOfRightSequence(line, wrongSequence);
    }

    private void linesConsistOfOneCharSimpleName(String line) throws IllegalNameException {
        String wrongSequence = String.valueOf(Constant.WRONG_ONE_CHAR_SIMPLE_NAME)
                + String.valueOf(Constant.WHITE_SPACE) + Constant.SPACE;
        linesConsistOfRightSequence(line, wrongSequence);
    }

    /**
     * This method check line. Line mustn't has wrong chars from wrongSequence.
     * @param line must be tested for a rightness
     * @param wrongSequence "wrong" symbols
     * @throws IllegalNameException
     */
    private void linesConsistOfRightSequence(String line, String wrongSequence) throws IllegalNameException {
        Pattern pattern = Pattern.compile("[" + wrongSequence + "]");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            throw new IllegalNameException();
        }
        /*special check for symbol '[' and ']',
        * which classPattern can't check, because it's separator symbol for finding class symbols.
        */
        for (char elem: Constant.SPECIAL_CHECK) {
            if (line.contains(String.valueOf(elem))) {
                throw new IllegalNameException();
            }
        }
    }
}
