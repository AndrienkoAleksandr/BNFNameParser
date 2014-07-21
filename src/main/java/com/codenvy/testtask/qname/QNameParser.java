package com.codenvy.testtask.qname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parse line to class QName
 * @author Created by Andrienko Alexander on 16.07.2014.
 * @version 0.2
 */
public class QNameParser {

    private boolean isPrefixedName;
    private String lineForParsing;

    public QNameParser() {
        isPrefixedName = false;
    }

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
        return result;
    }

    /**
    * This method check line is prefixed name or not.
    * Prefixed name separated by symbol ':'.
    * Order for checking prefixedname ::= prefix ':' localname/
    * @param line
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
     * This method check line is null or line equals char ''.
     * If line is empty throws IllegalNameException
     * @param line
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
    *onecharsimplename ::= (* Any Unicode character except:
     '.', '/', ':', '[', ']', '*',
     ''', '"', '|' or any whitespace
     character *)
     //todo this is java comment doesn't complete
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
        System.out.println("is valid " + line);
    }

    private void validLocalName(String line) throws IllegalNameException {
        if (line.length() > 0 && line.length() <= 2) {
            linesConsistOfNonSpace(line);
            return;
        }
        validLineWithTreeOrMoreChar(line);// if line.length >= 3
    }

    private void validLineWithTreeOrMoreChar(String line) throws IllegalNameException {
        int sizeLine = line.length();
        linesConsistOfOneCharSimpleName(line.substring(0, 1));
        linesConsistOfOneCharSimpleName(line.substring(sizeLine - 1, sizeLine));
        String string = line.substring(1, sizeLine - 1);
        String wrongSequence = String.valueOf(Constant.NON_SPACE_CHAR)
                + String.valueOf(Constant.WHITE_SPACE);
        linesConsistOfRightSequence(string, wrongSequence);
    }

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

    private void validPrefix(String line) {

    }

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

    private void linesConsistOfRightSequence(String line, String wrongSequence) throws IllegalNameException {
        Pattern pattern = Pattern.compile("[" + wrongSequence + "]");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            throw new IllegalNameException();
        }
        /*special check for symbol '[' and ']',
        which Pattern can't check it's separator symbol for finding class symbols
        */
        for (char elem: Constant.SPECIAL_CHECK) {
            if (line.contains(String.valueOf(elem))) {
                throw new IllegalNameException();
            }
        }
    }

    //todo
//    private int charExistAmountTimes(String line, char symbol) {
//        Pattern pattern = Pattern.compile("[" + symbol + "]");
//        Matcher matcher = pattern.matcher(line);
//        int count = 0;
//        while (matcher.find()) {
//            count++;
//        }
//        return count;
//    }
}
