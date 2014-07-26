BNFNameParser
=============

    This is a simple parser for QName. Parser gets String's line and parses it to class QName.
If you want begin test for checking of the parser you need download Maven 3.1.1 or higher
and will extract it to a directory where you wish install Maven. After that you'll need
set environment variable of M2_HOME (http://maven.apache.org/download.cgi).
    When you installed Maven you can start test for project in terminal. Download this
project, extract somewhere, start terminal, go to directory BNFNameParser. Write command:
                        mvn package
    This is command package and test project. If you want only test project use command:
                        mvn test
    After packaging project you can go to the directory ...BNFNameParser\target in console and
you can run jar:
                        java -jar Parser.jar
    Program will suggest you enter qualified name for parsing. Enter some name witch you
want to parse and program will get you result. For exit enter stop.



