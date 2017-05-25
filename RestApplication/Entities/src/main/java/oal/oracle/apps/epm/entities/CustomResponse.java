package oal.oracle.apps.epm.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomResponse {
    
    @XmlElement
    private String status;
    @XmlElement
    private ErrorMessage error;
    @XmlElement
    private Meta meta;
    @XmlElement
    private List<BaseEntity> data;

    public CustomResponse() {
        super();
    }
    public CustomResponse(String status,ErrorMessage error,Meta meta,List<BaseEntity> data) {
        this.status=status;
        this.error=error;
        this.meta=meta;
        this.data=data;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement 
    public String getStatus() {
        return status;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }
    
    @XmlElement 
    public ErrorMessage getError() {
        return error;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @XmlElement 
    public Meta getMeta() {
        return meta;
    }

    public void setData(List<BaseEntity> data) {
        this.data = data;
    }
    
    @XmlElement 
    public List<BaseEntity> getData() {
        return data;
    }
}
