package oal.oracle.apps.epm.entities;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class EntityListener_CreatedBy {
    public EntityListener_CreatedBy() {
        super();
    }
    
    @PrePersist
    public void setCreatedBy(final BaseEntity entity) {
        entity.setCreatedBy("Oracle");
    }

}
