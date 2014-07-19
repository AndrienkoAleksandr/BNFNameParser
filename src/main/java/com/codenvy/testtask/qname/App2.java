package com.codenvy.testtask.qname;

/**
 * Created by User on 18.07.2014.
 */
public class App2 {
    public static void main(String[] args) {
        for (int i = 0; i < Constant.WHITE_SPACE.length; i++) {
            System.out.println(Constant.WHITE_SPACE[i] + " " + i);
        }
        System.out.println("df".indexOf("["));
    }
}
