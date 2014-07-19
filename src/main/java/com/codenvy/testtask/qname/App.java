package com.codenvy.testtask.qname;

import java.util.Scanner;

/**
 * This is class for checking of working QNameParser
 *
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
                try {
                    QName qName =  qNameParser.parse(qualifiedName);
                    System.out.println(qName.getAsString());
                } catch (IllegalNameException e) {
                    e.printStackTrace();
                }
            }
        } while (!qualifiedName.equals("stop"));
    }
}
