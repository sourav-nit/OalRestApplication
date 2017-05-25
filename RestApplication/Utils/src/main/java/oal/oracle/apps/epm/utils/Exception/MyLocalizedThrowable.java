package oal.oracle.apps.epm.utils.Exception;


import java.io.FileNotFoundException;
import java.io.IOException;

import oal.oracle.apps.epm.utils.properties.PropertyMessage;

public class MyLocalizedThrowable extends Throwable {
    
    private static final long serialVersionUID = 1L;
    private String lang;


    public MyLocalizedThrowable(String messageKey) {
        super(messageKey);
    }

    public String getLocalizedMessage() {
            return PropertyMessage.getkeyValue(getMessage(),lang);
    }
    
    public void setLang(String language){
        this.lang=language;
    }
}
