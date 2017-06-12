package oal.oracle.apps.epm.entities;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class EntityListener_UpdateDate {
    public EntityListener_UpdateDate() {
        super();
    }
    
    
    @PrePersist
    @PreUpdate
    public void setLastUpdatedDate(final BaseEntity entity){
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        entity.setLastUpdatedDate(sqlDate);
    }
}
