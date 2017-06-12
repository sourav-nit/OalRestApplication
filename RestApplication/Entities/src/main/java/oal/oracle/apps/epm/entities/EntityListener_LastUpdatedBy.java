package oal.oracle.apps.epm.entities;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class EntityListener_LastUpdatedBy {
    public EntityListener_LastUpdatedBy() {
        super();
    }
    
    @PrePersist
    @PreUpdate
    public void setLastUpdatedBy(final BaseEntity entity) {
        entity.setLastUpdatedBy("Oracle");
    }
}
