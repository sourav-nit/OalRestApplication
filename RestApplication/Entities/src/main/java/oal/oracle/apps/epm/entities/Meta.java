package oal.oracle.apps.epm.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Meta {
    
    private int offset;
    private int limit;
    
    public Meta() {
        super();
    }
    public Meta(int offset,int limit) {
        this.offset=offset;
        this.limit=limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    @XmlElement 
    public int getOffset() {
        return offset;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    
    @XmlElement 
    public int getLimit() {
        return limit;
    }

}
