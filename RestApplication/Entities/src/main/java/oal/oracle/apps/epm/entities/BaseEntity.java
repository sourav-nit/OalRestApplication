package oal.oracle.apps.epm.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class BaseEntity  {
    public BaseEntity() {
        super();
    }
    
    public abstract void setCreationDate();
    public abstract void setLastUpdatedDate();
    public abstract void setCreatedBy(String name);
    public abstract void setLastUpdatedBy(String name);
    public abstract String getCreationDate();
    public abstract String getLastUpdatedDate();
    public abstract String getCreatedBy();
    public abstract String getLastUpdatedBy();
    
}
