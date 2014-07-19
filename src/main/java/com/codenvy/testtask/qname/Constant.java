package com.codenvy.testtask.qname;



/**
 * Created by User on 18.07.2014.
 */
public interface Constant {
    char[] NON_SPACE_CHAR = { '/', ':', '*', '\'', '"', '|'};
    char[] WRONG_ONE_CHAR_SIMPLE_NAME = {'.', '/', ':', '*', '\'', '"', '|'};
    char[] SPECIAL_CHECK = {'[', ']'};
    //Todo don't forget for two symbols which crashed!!!!!! It's need to check!!!!!!!
    char[] WHITE_SPACE = {
            '\u0009',
            '\u000B',
            '\u000C',
            '\u0020',
            '\u0085',
            '\u00A0',
            '\u1680',
            '\u2000',
            '\u2001',
            '\u2002',
            '\u2003',
            '\u2004',
            '\u2005',
            '\u2006',
            '\u2007',
            '\u2008',
            '\u2009',
            '\u200A',
            '\u2028',
            '\u2029',
            '\u202F',
            '\u205F',
            '\u3000',
    };
}
