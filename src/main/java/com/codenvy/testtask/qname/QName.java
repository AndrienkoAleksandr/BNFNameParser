package com.codenvy.testtask.qname;

/**
 * @author Created by Andrienko Alexander on 16.07.2014.
 * @version 0.3
 */
public class QName {
    private String prefix = "";
    private String localName;
    public String getPrefix() {
        return prefix;
    }
    public String getLocalName() {
        return localName;
    }
    public String getAsString() {
        return "[" + prefix + "]" + localName;
    }

    public void setPrefix(String prefix) {
            this.prefix = prefix;
    }

    public void setLocalName(String localName) {
            this.localName = localName;
    }
}