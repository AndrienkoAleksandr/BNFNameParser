package com.codenvy.testtask.qname;

import java.util.Scanner;

/**
 * This is class for checking of working QNameParser
 * @author Created by Andrienko Alexander on 16.07.2014.
 * @version 0.3
 */
public class App {
    public static void main( String[] args ) {
        String qualifiedName;
        String result;
        Scanner scanner = new Scanner(System.in);
        QNameParser qNameParser = new QNameParser();
        do {
            System.out.println("Enter please qualified name.\nFor exit enter stop");
            qualifiedName = scanner.nextLine();
            if (qualifiedName == null) {
                System.out.println("You entered nothing!!!");
            } else {
                if (qualifiedName.equals("stop")) {
                    break;
                }
                try {
                    QName qName = qNameParser.parse(qualifiedName);
                    System.out.println(qName.getAsString() + " element is valid");
                } catch (IllegalNameException e) {
                    e.printStackTrace();
                }
            }
        } while (true);
    }
}
