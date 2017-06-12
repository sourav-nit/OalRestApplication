package oal.oracle.apps.epm.entities;

import javax.persistence.PrePersist;

public class EntityListener_CreationDate {
    public EntityListener_CreationDate() {
        super();
    }
    
    @PrePersist
    public void setCreationDate(final BaseEntity entity){
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        entity.setCreationDate(sqlDate);
    }
}
