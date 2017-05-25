package oal.oracle.apps.epm.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
    
    private String errorMessage;
    private String errorCode;
    
    public ErrorMessage() {
        super();
    }
    public ErrorMessage(String errorMessage,String errorCode) {
        this.errorMessage=errorMessage;
        this.errorCode=errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @XmlElement 
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    @XmlElement 
    public String getErrorCode() {
        return errorCode;
    }
}
