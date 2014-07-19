package com.codenvy.testtask.qname;

/**
 * Created by USER on 16.07.2014.
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
    //TODO ask for right string scope
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
