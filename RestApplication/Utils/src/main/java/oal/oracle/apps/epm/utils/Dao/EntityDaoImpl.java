package oal.oracle.apps.epm.utils.Dao;


import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import oal.oracle.apps.epm.entities.BaseEntity;



/**
 Generic entity Dao class implemetation
 */

public class EntityDaoImpl implements EntityDao{
    
    private final EntityManager em;
    //protected EntityManagerFactory emf;
    private Class type;
    private UserTransaction transaction;

    
    /**
     *  Below constructor is used to get connection for a given persistence unit.Also,it finds
        the type of generic class used.
     */
    
    public EntityDaoImpl() throws NamingException {
       // emf = Persistence.createEntityManagerFactory("Entities");
        //em = emf.createEntityManager();
        em=EMFactory.getemFactory().createEntityManager();  
        transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction"); 
    }
    
    /**
     *Method used for mocking entity Manager Object
     * @param emo Entity Manger Object used by mockito
     * @throws NamingException
     */
    public EntityDaoImpl(EntityManager emo,UserTransaction transaction) throws NamingException {
       this.em=emo;
       this.transaction =transaction; 
    }
    /**
     * This method is to close the entityManager
     */
    public void closeEntityManager(){
        em.close();
    }

    /**
     * This is to set the entity class
     * @param Entity Class
     */
    public void setEntity(Class temp){
        type=(Class)temp;    
    }
    

    /**
     *This method is used to store data in the table for a particular entity if object doesn't exist else it would update the object.
     * @param t Entity Object
     * @return Entity Object same as paramater entity object
     */
    public BaseEntity create(final BaseEntity t) throws NotSupportedException, SystemException, RollbackException,
                                                        HeuristicMixedException, HeuristicRollbackException {
        transaction.begin();
        em.joinTransaction();
        t.setCreatedBy("Oracle");
        t.setCreationDate();
        t.setLastUpdatedDate();
        t.setLastUpdatedBy("Oracle");
        this.em.persist(t);
        transaction.commit();
        return t;
    }
    
    /**
     *This method is used to delete data in the table for a particular entity
     * @param id primary key for entity object.
     */

    public void delete(final Object id) throws NotSupportedException, SystemException, RollbackException,
                                               HeuristicMixedException, HeuristicRollbackException {
            transaction.begin();
            em.joinTransaction();
            this.em.remove(this.em.getReference(type, id));
            transaction.commit();
    }
    
    /**
     *This method is used to get data from the table for a particular entity and primary key value.
     * @param id primary key for entity object.
     * @return Entity Object
     */

    public BaseEntity find(final Object id) {
        return (BaseEntity)this.em.find(type, id);
    }


    /**
     * This method is used to update data in the table for a particular entity
     * @param t updated Entity Object 
     * @return updated Entity Object same as in parameter 
     */

    public BaseEntity update(final BaseEntity t) throws NotSupportedException, SystemException, RollbackException,
                                                        HeuristicMixedException, HeuristicRollbackException {
        transaction.begin();
        em.joinTransaction();
        t.setLastUpdatedDate();
        t.setLastUpdatedBy("Oracle");
        BaseEntity be=this.em.merge(t);
        transaction.commit();
        return be;
    }

    
    /**
     *This method is used to get the list of data for a particular entity.
     * @return List of Entity Objects
     */
        
    public List<BaseEntity> getData(int offset,int limit,HashMap<String,String> queryData){
        if(queryData==null)
            return (List<BaseEntity>) em.createQuery("select e from " + type.getSimpleName() + " e").setFirstResult(offset).setMaxResults(limit).getResultList();
        else{
            int i=1;
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT e FROM ")
                    .append(type.getSimpleName())
                    .append(" e WHERE ");
            for (String field : queryData.keySet()) {
                    sql.append("e.")
                            .append(field)
                            .append("=")
                            .append(queryData.get(field));
                    ++i;
                if(i<=queryData.keySet().size())
                    sql.append(" and ");
            }
            return (List<BaseEntity>) em.createQuery(sql.toString()).setFirstResult(offset).setMaxResults(limit).getResultList();
        }
    }
}

