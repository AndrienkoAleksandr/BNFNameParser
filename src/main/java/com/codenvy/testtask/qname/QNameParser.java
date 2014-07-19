package com.codenvy.testtask.qname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parse line to class QName
 * @author Created by Andrienko Alexander on 16.07.2014.
 * @version 0.1
 */
public class QNameParser {

    private boolean isPrefixedName;

    {
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
            validPrefixedName(line);
        }
//todo it'll be delete
//        System.out.println(result.getAsString());
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
     */
    private void validSimpleName(String line) throws IllegalNameException {
        String wrongSequence = String.valueOf(Constant.WRONG_ONE_CHAR_SIMPLE_NAME)+ String.valueOf(Constant.WHITE_SPACE);
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

    private void validPrefixedName(String line) {

    }

    private void validPrefix(String line) {

    }

    private void validLocalName(String line) {

    }

    private void isValidXmlName() {

    }
}
