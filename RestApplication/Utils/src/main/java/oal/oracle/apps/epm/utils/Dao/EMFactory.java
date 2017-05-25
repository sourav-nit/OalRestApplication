package oal.oracle.apps.epm.utils.Dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactory {
    
    private static EntityManagerFactory emf=null;

    public static EntityManagerFactory getemFactory(){
        if(emf==null){
            emf = Persistence.createEntityManagerFactory("Entities");
        }
        return emf;
    }
}
