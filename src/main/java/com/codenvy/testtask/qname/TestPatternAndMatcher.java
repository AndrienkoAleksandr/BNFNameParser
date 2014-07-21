package com.codenvy.testtask.qname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 19.07.2014.
 */
public class TestPatternAndMatcher {
    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("X{3}");
        Matcher matcher = pattern.matcher("X");
        System.out.println(matcher.find());
        matcher = pattern.matcher("XX");
        System.out.println(matcher.find());
        matcher = pattern.matcher("XXX");
        System.out.println(matcher.find());
        pattern = Pattern.compile("X??");
        matcher = pattern.matcher("XX");
        System.out.println(matcher.find());

        pattern = Pattern.compile("[a. | .a] ");
        matcher = pattern.matcher("aa");
        System.out.println(matcher.find());
         if ('\u0020' == ' ') {
                System.out.println("ll");
            }
    }
}
