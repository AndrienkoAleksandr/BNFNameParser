package com.codenvy.testtask.qname;

/**
 * @author Created by Andrienko Alexander on 25.07.2014.
 * @version 0.3
 * This class generates data for PrefixTest class
 */
public class CreatorOfRightSequence {

    //Name	::=	(Letter | '_' | ':') (NameChar)*; Letter ::= BaseChar | Ideographic
    static final String RIGHT_SYMBOLS_FOR_FIRST_CHAR_XML_NAME =
            initStringWithAllRightSymbolsFromStringSequence(Constant.BASE_CHAR) +
                    initStringWithAllRightSymbolsFromStringSequence(Constant.IDEOGRAPHIC) + "_:" ;

    //NameChar ::= Letter | Digit | '.' | '-' | '_' | ':' | CombiningChar | Extender
    //Letter ::= BaseChar | Ideographic
    static final String RIGHT_SYMBOL_FOR_SECOND_AND_NEXT_CHARS_OF_XML_NAME =
            initStringWithAllRightSymbolsFromStringSequence(Constant.BASE_CHAR) +
                    initStringWithAllRightSymbolsFromStringSequence(Constant.IDEOGRAPHIC) +
                    initStringWithAllRightSymbolsFromStringSequence(Constant.DIGIT) +
                    ".-_:" +
                    initStringWithAllRightSymbolsFromStringSequence(Constant.COMBINING_CHAR) +
                    initStringWithAllRightSymbolsFromStringSequence(Constant.EXTENDER);

    /*
     *This method generate all right symbols through a sequence
     */
    private static String initStringWithAllRightSymbolsFromStringSequence(String sequence) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == '-') {
                for (int j = sequence.charAt(i - 1); j <= sequence.charAt(i + 1) - 1; j++) {
                    result.append((char)(j + 1));
                }
                i++;
            } else {
                result.append(sequence.charAt(i));
            }
        }
        return result.toString();
    }
}
