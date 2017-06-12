package oal.oracle.apps.epm.entities;

import java.util.Date;

public abstract class BaseEntity  {
    public BaseEntity() {
        super();
    }
    
    public abstract void setCreationDate(Date date);
    public abstract void setLastUpdatedDate(Date date);
    public abstract void setCreatedBy(String name);
    public abstract void setLastUpdatedBy(String name);
    public abstract String getCreationDate();
    public abstract String getLastUpdatedDate();
    public abstract String getCreatedBy();
    public abstract String getLastUpdatedBy();
    
}
